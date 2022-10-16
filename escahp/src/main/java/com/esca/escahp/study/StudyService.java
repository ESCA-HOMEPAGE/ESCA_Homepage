package com.esca.escahp.study;

import com.esca.escahp.common.exceptions.BoardExceptions;
import com.esca.escahp.exception.EscaException;
import com.esca.escahp.study.repository.StudyRepository;
import com.esca.escahp.study.entity.StudyBoard;
import com.esca.escahp.study.dto.StudyResponse;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StudyService implements I_StudyBoardService {

    private final StudyRepository studyRepository;

    public StudyService(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    @Override
    public List<StudyResponse> getStudyBoardList() {
        List<StudyBoard> studies = studyRepository.findAll();
        return studies
            .stream()
            .map(StudyResponse::new)
            .collect(Collectors.toList());
    }

    @Override
    public StudyResponse selectStudyBoard(long id) {
        StudyBoard study = studyRepository.findById(id)
            .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));
        return new StudyResponse(study);
    }

    @Transactional
    @Override
    public void addBoard(StudyBoard b) {
        studyRepository.save(b);
    }

    @Transactional
    @Override
    public void updateBoard(StudyBoard b) {
        StudyBoard origin = studyRepository.findById(b.getId())
            .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));

        origin.update(b.getTitle(), b.getContent(), b.getFile());
    }

    @Transactional
    @Override
    public void deleteBoard(StudyBoard b) {
        StudyBoard delete = studyRepository.findById(b.getId())
            .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));

        delete.delete();
    }

    @Override
    public void updateViewCnt(long id) {
        StudyBoard board = studyRepository.findById(id)
            .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));
        board.update();

    }
}
