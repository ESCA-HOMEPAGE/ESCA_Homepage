package com.esca.escahp.gallery;

import com.esca.escahp.common.exceptions.BoardExceptions;
import com.esca.escahp.exception.EscaException;
import com.esca.escahp.gallery.dto.GalleryLikeRequest;
import com.esca.escahp.gallery.dto.GalleryLikeResponse;
import com.esca.escahp.gallery.dto.GalleryResponse;
import com.esca.escahp.gallery.entity.GalleryBoard;
import com.esca.escahp.gallery.entity.GalleryBoardLike;
import com.esca.escahp.gallery.repository.GalleryLikesRepository;
import com.esca.escahp.gallery.repository.GalleryRepository;
import com.esca.escahp.user.entity.User;
import com.esca.escahp.user.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

public class GalleryLikesService implements I_GalleryLikesService{
    private final GalleryLikesRepository galleryLikesRepository;
    private final GalleryRepository galleryRepository;
    private final UserRepository userRepository;

    public GalleryLikesService(GalleryLikesRepository galleryLikesRepository, GalleryRepository galleryRepository, UserRepository userRepository){this.galleryLikesRepository = galleryLikesRepository;
        this.galleryRepository = galleryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public Long addGalleryLikes(GalleryLikeRequest b){
        GalleryBoard gallery = galleryRepository.findById(b.getPostId())
                .orElseThrow(()-> new IllegalAccessError("[id=" + b.getPostId() + "] 해당 게시글은 존재하지 않습니다."));
        User user = userRepository.findById(b.getUserId())
                .orElseThrow(()->new IllegalAccessError("[id=" + b.getUserId() + "] 해당 사용자는 존재하지 않습니다."));
        GalleryBoardLike galleryBoardLike = b.toEntity(gallery,user);
        GalleryBoardLike save = galleryLikesRepository.save(galleryBoardLike);
        return save.getId();
    }


    @Override
    public List<GalleryLikeResponse> getGalleryLikesList() {
        List<GalleryBoardLike> galleryBoardLikes = galleryLikesRepository.findAll();
        return galleryBoardLikes
                .stream()
                .map(GalleryLikeResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public GalleryLikeResponse selectGalleryLikesBoard(long id){
        GalleryBoardLike galleryBoard = galleryLikesRepository.findById(id)
                .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));
        return new GalleryLikeResponse(galleryBoard);
    }
}
