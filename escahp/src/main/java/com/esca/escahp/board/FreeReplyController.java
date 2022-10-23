package com.esca.escahp.board;

import com.esca.escahp.board.dto.FreeReplyRequest;
import com.esca.escahp.board.dto.FreeReplyResponse;
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

@Api(tags = "FreeReply")
@RestController
@RequestMapping("/free")
@CrossOrigin(origins = {"*"})
public class FreeReplyController {
    private final FreeReplyService freeReplyService;

    public FreeReplyController(FreeReplyService freeReplyService) {
        this.freeReplyService = freeReplyService;
    }

    @ApiOperation(value = "자유 게시물의 댓글 목록 보여주기", response = List.class)
    @GetMapping("/{postId}/reply")
    public ResponseEntity<List<FreeReplyResponse>> getAllFreeReplyByPostId(@PathVariable long postId) {
        List<FreeReplyResponse> replies = freeReplyService.getFreeReplyList(postId);
        return ResponseEntity.ok().body(replies);
    }

    @ApiOperation(value = "자유 게시물에 댓글 객체 추가")
    @PostMapping("/{postId}/reply/{userId}")
    public ResponseEntity<FreeReplyResponse> postFreeReply(@PathVariable long postId, @PathVariable long userId, @RequestBody FreeReplyRequest freeReplyRequest) {
        Long id = freeReplyService.postFreeReply(postId, userId, freeReplyRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri();

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "id에 해당하는 댓글 정보 삭제")
    @PatchMapping("/reply/{id}/{userId}")
    public ResponseEntity<Object> deleteReply(@PathVariable long id, @PathVariable long userId) {
        freeReplyService.deleteFreeReply(id, userId);

        return ResponseEntity.noContent().build();
    }

}
