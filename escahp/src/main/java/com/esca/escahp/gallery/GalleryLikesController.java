package com.esca.escahp.gallery;

import com.esca.escahp.gallery.dto.GalleryLikeRequest;
import com.esca.escahp.gallery.dto.GalleryLikeResponse;
import com.esca.escahp.gallery.dto.GalleryRequest;
import com.esca.escahp.gallery.dto.GalleryResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


public class GalleryLikesController {
    private final GalleryLikesService galleryLikesService;

    public GalleryLikesController(GalleryLikesService galleryLikesService) {
        this.galleryLikesService = galleryLikesService;
    }

    @ApiOperation(value = "갤러리 게시판의 좋아요 표시")
    @GetMapping("/{id}")
    public ResponseEntity<List<GalleryLikeResponse>> getGalleryLikes() {
        List<GalleryLikeResponse> galleryLikes = galleryLikesService.getGalleryLikesList();
        return ResponseEntity.ok(galleryLikes);
    }

    @ApiOperation(value = "id에 해당하는 게시물 정보 반환")
    @GetMapping("/{id}")
    public ResponseEntity<GalleryLikeResponse> getGalleryBoardById(@PathVariable long id) {
        GalleryLikeResponse result = galleryLikesService.selectGalleryLikesBoard(id);

        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "갤러 게시판의 좋아요 추가")
    @PostMapping
    public ResponseEntity<Long> insertGalleryLikes(
            @RequestBody GalleryLikeRequest galleryLikeRequest) {
        Long id = galleryLikesService.addGalleryLikes(galleryLikeRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
