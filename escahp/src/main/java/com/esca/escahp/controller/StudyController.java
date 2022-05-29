package com.esca.escahp.controller;

import com.esca.escahp.dto.StudyBoardDto;
import com.esca.escahp.dto.response.StudyResponse;
import com.esca.escahp.service.StudyService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/study")
@CrossOrigin(origins = {"*"})
public class StudyController {

    private final StudyService studyService;

    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    @ApiOperation(value = "스터디 게시판의 전체 목록 보여주기")
    @GetMapping
    public ResponseEntity<List<StudyResponse>> getAllStudyBoard() {
        List<StudyResponse> studyBoard = studyService.getStudyBoardList();
        return ResponseEntity.ok().body(studyBoard);
    }

}
