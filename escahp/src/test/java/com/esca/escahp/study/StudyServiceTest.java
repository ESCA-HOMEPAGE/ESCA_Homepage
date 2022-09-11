package com.esca.escahp.study;

import com.esca.escahp.study.dto.StudyResponse;
import com.esca.escahp.study.repository.StudyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@ActiveProfiles("test")
class StudyServiceTest {
    @Autowired
    private StudyService studyService;

    @Autowired
    private StudyRepository studyRepository;

    @Test
    void getStudyBoardList() {
        //when
        List<StudyResponse> list = studyService.getStudyBoardList();

        //then
        assertEquals(list.size(), 1);
    }
}