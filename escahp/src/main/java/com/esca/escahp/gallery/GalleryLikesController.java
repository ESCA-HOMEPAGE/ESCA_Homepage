package com.esca.escahp.gallery;

import com.esca.escahp.gallery.dto.GalleryLikeResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class GalleryLikesController {
    private final GalleryLikesService galleryLikesService;
    public GalleryLikesController(GalleryLikesService galleryLikesService){this.galleryLikesService= galleryLikesService;}

    @ApiOperation(value = "갤러리 게시판의 좋아요 표시")
    @GetMapping
    public ResponseEntity<List<GalleryLikeResponse>> getGalleryLikes(){
        List<GalleryLikeResponse> galleryLikes = galleryLikesService.getGalleryLikesList();
        return ResponseEntity.ok().body(galleryLikes);
    }
}
