package com.esca.escahp.board;

import com.esca.escahp.board.dto.FreeReplyRequest;
import com.esca.escahp.board.dto.FreeReplyResponse;
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

@RestController
@RequestMapping("/free")
@CrossOrigin(origins = {"*"})
public class FreeReplyController {
    private final FreeReplyService freeReplyService;

    public FreeReplyController(FreeReplyService freeReplyService) {
        this.freeReplyService = freeReplyService;
    }

    @GetMapping("/{postId}/reply")
    public ResponseEntity<List<FreeReplyResponse>> getAllFreeReplyByPostId(@PathVariable long postId) {
        List<FreeReplyResponse> replies = freeReplyService.getFreeReplyList(postId);
        return ResponseEntity.ok().body(replies);
    }

    @PostMapping("/{postId}/reply/{userId}")
    public ResponseEntity<FreeReplyResponse> postFreeReply(@PathVariable long postId, @PathVariable long userId, @RequestBody FreeReplyRequest freeReplyRequest) {
        Long id = freeReplyService.postFreeReply(postId, userId, freeReplyRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri();

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/reply/{id}/{userId}")
    public ResponseEntity<Object> deleteReply(@PathVariable long id, @PathVariable long userId) {
        freeReplyService.deleteFreeReply(id, userId);

        return ResponseEntity.noContent().build();
    }

}
