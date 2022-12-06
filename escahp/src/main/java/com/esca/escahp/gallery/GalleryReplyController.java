package com.esca.escahp.gallery;

import com.esca.escahp.gallery.dto.GalleryReplyRequest;
import com.esca.escahp.gallery.dto.GalleryReplyResponse;
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

@Api(tags = "GalleryReply")
@RestController
@RequestMapping("/gallery")
@CrossOrigin(origins = {"*"})
public class GalleryReplyController {
    private final GalleryReplyService galleryReplyService;

    public GalleryReplyController(GalleryReplyService galleryReplyService) {
        this.galleryReplyService = galleryReplyService;
    }

    @ApiOperation(value = "갤러리 게시물의 댓글 목록 보여주기", response = List.class)
    @GetMapping("/{postId}/reply")
    public ResponseEntity<List<GalleryReplyResponse>> getAllGalleryReplyByPostId(@PathVariable long postId) {
        List<GalleryReplyResponse> replies = galleryReplyService.getGalleryReplyList(postId);
        return ResponseEntity.ok().body(replies);
    }

    @ApiOperation(value = "갤러리 게시물에 댓글 객체 추가")
    @PostMapping("/{postId}/reply/{userId}")
    public ResponseEntity<GalleryReplyResponse> postGalleryReply(@PathVariable long postId, @PathVariable long userId, @RequestBody GalleryReplyRequest galleryReplyRequest) {
        Long id = galleryReplyService.postGalleryReply(postId, userId, galleryReplyRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri();

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "id에 해당하는 댓글 정보 삭제")
    @PatchMapping("/reply/{id}/{userId}")
    public ResponseEntity<Object> deleteReply(@PathVariable long id, @PathVariable long userId) {
        galleryReplyService.deleteGalleryReply(id, userId);

        return ResponseEntity.noContent().build();
    }
}
