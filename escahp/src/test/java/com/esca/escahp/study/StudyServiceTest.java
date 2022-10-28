package com.esca.escahp.study;

import com.esca.escahp.study.dto.StudyResponse;
import com.esca.escahp.study.entity.StudyBoard;
import com.esca.escahp.study.repository.StudyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void before() {
        StudyBoard board1 = new StudyBoard("title", "content", "writer", "category", "file");
        studyRepository.save(board1);
    }

    @AfterEach
    void after() {
        studyRepository.deleteAll();
    }

    @Test
    void getStudyBoardList() {
        //when
        List<StudyResponse> list = studyService.getStudyBoardList();

        //then
        assertEquals(list.size(), 1);
    }
}