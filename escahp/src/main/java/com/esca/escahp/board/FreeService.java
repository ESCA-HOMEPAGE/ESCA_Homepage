package com.esca.escahp.board;

import com.esca.escahp.board.dto.FreeRequest;
import com.esca.escahp.board.dto.FreeResponse;
import com.esca.escahp.board.entity.FreeBoard;
import com.esca.escahp.common.exceptions.BoardExceptions;
import com.esca.escahp.board.repository.FreeRepository;
import com.esca.escahp.exception.EscaException;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class FreeService implements I_FreeBoardService {

    private final FreeRepository freeRepository;

    public FreeService(FreeRepository freeRepository) {
        this.freeRepository = freeRepository;
    }

    @Override
    public List<FreeResponse> getFreeBoardList() {
        return freeRepository.findByDeleteYn("N")
            .stream()
            .map(FreeResponse::new)
            .collect(Collectors.toList());
    }

    @Override
    public FreeResponse selectFreeBoard(long id) {
        FreeBoard board = freeRepository.findById(id)
            .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));
        return new FreeResponse(board);
    }

    @Transactional
    @Override
    public Long postBoard(FreeRequest f) {
        return freeRepository.save(f.toEntity()).getId();
    }

    @Transactional
    @Override
    public void updateBoard(Long id, FreeRequest f) {
        FreeBoard origin = freeRepository.findById(id)
            .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));
        origin.update(f.getTitle(), f.getContent(), f.getFile());
    }

    @Transactional
    @Override
    public void deleteBoard(Long id) {
        FreeBoard delete = freeRepository.findById(id)
            .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));
        delete.delete();
    }

    @Transactional
    @Override
    public void increaseViewCnt(long id) {
        FreeBoard board = freeRepository.findById(id)
            .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));
        board.increaseViewCnt();
    }
}
