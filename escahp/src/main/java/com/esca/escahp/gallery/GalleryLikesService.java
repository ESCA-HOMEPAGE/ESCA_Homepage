package com.esca.escahp.gallery;

import com.esca.escahp.gallery.dto.GalleryLikeResponse;
import com.esca.escahp.gallery.entity.GalleryBoard;
import com.esca.escahp.gallery.entity.GalleryBoardLike;
import com.esca.escahp.gallery.repository.GalleryLikesRepository;
import com.esca.escahp.gallery.repository.GalleryRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

public class GalleryLikesService implements I_GalleryLikesService{
    private final GalleryLikesRepository galleryLikesRepository;

    public GalleryLikesService(GalleryLikesRepository galleryLikesRepository){this.galleryLikesRepository = galleryLikesRepository;}

    @Transactional
    @Override
    public void countLikes(long id){
        GalleryBoardLike board = galleryLikesRepository.findById(id)
                .orElseThrow(()->new IllegalAccessError("좋아요 에러 입니다."));
        board.countLikes();
    }

    @Override
    public List<GalleryLikeResponse> getGalleryLikesList() {
        List<GalleryBoardLike> galleryBoardLikes = galleryLikesRepository.findAll();
        return galleryBoardLikes
                .stream()
                .map(GalleryLikeResponse::new)
                .collect(Collectors.toList());
    }
}
