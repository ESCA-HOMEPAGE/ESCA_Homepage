package com.esca.escahp.study;

import com.esca.escahp.study.entity.StudyBoard;
import com.esca.escahp.study.dto.StudyResponse;
import com.esca.escahp.study.StudyService;
import io.swagger.annotations.ApiOperation;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @ApiOperation(value = "id에 해당하는 게시물 정보 반환")
    @GetMapping("/{id}")
    public ResponseEntity<StudyResponse> getStudyBoardById(@PathVariable long id) {
        StudyResponse result = studyService.selectStudyBoard(id);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }

        studyService.updateViewCnt(id);
        return ResponseEntity.ok().body(result);
    }

    @ApiOperation(value = "게시물 객체 추가")
    @PostMapping
    public ResponseEntity<StudyResponse> insertStudyBoard(
        @RequestBody StudyBoard studyBoard) {
        studyService.addBoard(studyBoard);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(studyBoard.getId())
            .toUri();

        return ResponseEntity.created(location).build();

    }

    @ApiOperation(value = "id에 해당하는 게시물 정보 수정")
    @PutMapping("/{id}")
    public ResponseEntity<StudyResponse> updateStudyBoard(@PathVariable Long id,
        @RequestBody StudyBoard studyBoard) {
        studyBoard.setId(id);
        studyService.updateBoard(studyBoard);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .buildAndExpand()
            .toUri();

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "id에 해당하는 게시물 정보 삭제")
    @PatchMapping("/{id}")
    public ResponseEntity<Object> deleteAction(@PathVariable Long id,
        @RequestBody StudyBoard studyBoard) {
        studyBoard.setId(id);
        studyService.deleteBoard(studyBoard);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .buildAndExpand()
            .toUri();

        return ResponseEntity.created(location).build();

    }
}
