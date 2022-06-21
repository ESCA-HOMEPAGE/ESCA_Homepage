package com.esca.escahp.service;

import com.esca.escahp.dao.StudyRepository;
import com.esca.escahp.domain.StudyBoard;
import com.esca.escahp.dto.StudyBoardDto;
import com.esca.escahp.dto.response.StudyResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
            .orElseThrow(() -> new IllegalAccessError("[id=" + id + "] 해당 게시글은 존재하지 않습니다."));
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
            .orElseThrow(() -> new IllegalAccessError("[id=" + b.getId() + "] 해당 게시글은 존재하지 않습니다."));

        origin.update(b.getTitle(), b.getContent(), b.getFile());
    }

    @Transactional
    @Override
    public void deleteBoard(StudyBoard b) {
        StudyBoard delete = studyRepository.findById(b.getId())
            .orElseThrow(() -> new IllegalAccessError("[id=" + b.getId() + "] 해당 게시글은 존재하지 않습니다."));

        delete.delete();
    }

    @Override
    public void updateViewCnt(long id) {
        StudyBoard board = studyRepository.findById(id)
            .orElseThrow(() -> new IllegalAccessError("[id=" + id + "] 해당 게시글은 존재하지 않습니다."));
        board.update();

    }
}
