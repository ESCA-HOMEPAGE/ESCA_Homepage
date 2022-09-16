package com.esca.escahp.gallery;

import com.esca.escahp.gallery.dto.GalleryRequest;
import com.esca.escahp.gallery.dto.GalleryResponse;


import java.util.List;

public interface I_GalleryBoardService {
    List<GalleryResponse> getGalleryBoardList();

    GalleryResponse selectGalleryBoard(long id);

    Long addBoard(GalleryRequest b);

    void updateBoard(Long id, GalleryRequest b);

    void deleteBoard(Long id);

    void updateViewCnt(long id);

}
