package com.esca.escahp.gallery;

import com.esca.escahp.gallery.dto.GalleryLikeRequest;
import com.esca.escahp.gallery.dto.GalleryLikeResponse;
import com.esca.escahp.gallery.dto.GalleryRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class GalleryLikesController {
    private final GalleryLikesService galleryLikesService;
    public GalleryLikesController(GalleryLikesService galleryLikesService){this.galleryLikesService= galleryLikesService;}

    @ApiOperation(value = "갤러리 게시판의 좋아요 표시")
    @GetMapping("/{id}")
    public ResponseEntity<List<GalleryLikeResponse>> getGalleryLikes(){
        List<GalleryLikeResponse> galleryLikes = galleryLikesService.getGalleryLikesList();
        return ResponseEntity.ok().body(galleryLikes);
    }

    @ApiOperation(value = "갤러 게시판의 좋아요 추가")
    @PostMapping("/{id}")
    public ResponseEntity<Long> insertGalleryLikes(
            @RequestBody GalleryLikeRequest galleryLikeRequest){
        Long id = galleryLikesService.addGalleryLikes(galleryLikeRequest);

        return ResponseEntity.ok(id);
    }
}
