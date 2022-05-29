package com.esca.escahp.service;

import com.esca.escahp.dao.StudyRepository;
import com.esca.escahp.domain.StudyBoard;
import com.esca.escahp.dto.StudyBoardDto;
import com.esca.escahp.dto.response.StudyResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudyService implements I_StudyBoardService {

    private final StudyRepository studyRepository;

    public StudyService(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    @Override
    public List<StudyResponse> getStudyBoardList() {
        List<StudyBoard> studies = studyRepository.findAll();
        return result = studies
            .stream()
            .map(StudyResponse::new)
            .collect(Collectors.toList());
    }

    @Override
    public StudyBoardDto selectStudyBoard(long id) {
        return null;
    }

    @Override
    public void addBoard(StudyBoardDto b) {

    }

    @Override
    public void updateBoard(StudyBoardDto b) {

    }

    @Override
    public void deleteBoard(StudyBoardDto b) {

    }

    @Override
    public void updateViewCnt(long id) {

    }
}
