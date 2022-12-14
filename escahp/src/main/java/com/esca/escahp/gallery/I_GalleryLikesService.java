package com.esca.escahp.gallery;

import com.esca.escahp.gallery.dto.GalleryLikeRequest;
import com.esca.escahp.gallery.dto.GalleryLikeResponse;

import java.util.List;

public interface I_GalleryLikesService {

    List<GalleryLikeResponse> getGalleryLikesList();

    Long addGalleryLikes(GalleryLikeRequest b);

    GalleryLikeResponse selectGalleryLikesBoard(long id);
}
