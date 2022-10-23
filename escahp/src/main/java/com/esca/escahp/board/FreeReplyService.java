package com.esca.escahp.board;

import com.esca.escahp.board.dto.FreeReplyRequest;
import com.esca.escahp.board.dto.FreeReplyResponse;
import com.esca.escahp.board.entity.FreeBoard;
import com.esca.escahp.board.entity.FreeReply;
import com.esca.escahp.common.exceptions.BoardExceptions;
import com.esca.escahp.board.repository.FreeReplyRepository;
import com.esca.escahp.board.repository.FreeRepository;
import com.esca.escahp.exception.EscaException;
import com.esca.escahp.user.entity.User;
import com.esca.escahp.user.exception.UserExceptions;
import com.esca.escahp.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class FreeReplyService implements I_FreeReplyService {

    private final FreeReplyRepository freeReplyRepository;
    private final FreeRepository freeRepository;
    private final UserRepository userRepository;

    public FreeReplyService(FreeReplyRepository freeReplyRepository, FreeRepository freeRepository, UserRepository userRepository) {
        this.freeReplyRepository = freeReplyRepository;
        this.freeRepository = freeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<FreeReplyResponse> getFreeReplyList(long postId) {
        getFreeBoard(postId);

        List<FreeReply> replies = freeReplyRepository.findAllByFreeBoardId(postId);
        return replies
            .stream()
            .map(FreeReplyResponse::new)
            .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Long postFreeReply(long postId, long userId, FreeReplyRequest fr) {
        FreeBoard board = getFreeBoard(postId);

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EscaException(UserExceptions.NOT_FOUND_USER));

        return freeReplyRepository.save(fr.toEntity(board, user)).getId();
    }

    @Transactional
    @Override
    public void deleteFreeReply(Long id, long userId) {
        FreeReply reply = freeReplyRepository.findById(id)
            .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EscaException(UserExceptions.NOT_FOUND_USER));

        if (reply.getUser().equals(user)) {
            reply.delete();
        } else {
            throw new EscaException(BoardExceptions.FORBIDDEN_DELETE_REPLY);
        }
    }

    FreeBoard getFreeBoard(long postId) {
        FreeBoard freeBoard = freeRepository.findById(postId)
            .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));
        if (freeBoard.getDeleteYn().equals("Y") || freeBoard.getReport() >= 5) {
            throw new EscaException(BoardExceptions.NOT_FOUND_BOARD);
        }

        return freeBoard;
    }
}
