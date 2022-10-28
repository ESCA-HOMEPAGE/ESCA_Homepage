package com.esca.escahp.study;

import com.esca.escahp.study.dto.StudyReplyRequest;
import com.esca.escahp.study.dto.StudyReplyResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Api(tags = "StudyReply")
@RestController
@RequestMapping("/study")
@CrossOrigin(origins = {"*"})
public class StudyReplyController {
    private final StudyReplyService studyReplyService;

    public StudyReplyController(StudyReplyService studyReplyService) {
        this.studyReplyService = studyReplyService;
    }

    @ApiOperation(value = "스터디 게시물의 댓글 목록 보여주기", response = List.class)
    @GetMapping("/{postId}/reply")
    public ResponseEntity<List<StudyReplyResponse>> getAllStudyReplyByPostId(@PathVariable long postId) {
        List<StudyReplyResponse> replies = studyReplyService.getStudyReplyList(postId);
        return ResponseEntity.ok().body(replies);
    }

    @ApiOperation(value = "스터디 게시물에 댓글 객체 추가")
    @PostMapping("/{postId}/reply/{userId}")
    public ResponseEntity<StudyReplyResponse> postStudyReply(@PathVariable long postId, @PathVariable long userId, @RequestBody StudyReplyRequest studyReplyRequest) {
        Long id = studyReplyService.postStudyReply(postId, userId, studyReplyRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri();

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "id에 해당하는 댓글 정보 삭제")
    @PatchMapping("/reply/{id}/{userId}")
    public ResponseEntity<Object> deleteReply(@PathVariable long id, @PathVariable long userId) {
        studyReplyService.deleteStudyReply(id, userId);

        return ResponseEntity.noContent().build();
    }
}
