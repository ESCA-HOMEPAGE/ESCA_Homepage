package com.esca.escahp.gallery;

import com.esca.escahp.gallery.dto.GalleryLikeResponse;

import java.util.List;

public interface I_GalleryLikesService {
    void countLikes(long id);
    List<GalleryLikeResponse> getGalleryLikesList();
}
