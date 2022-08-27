package com.esca.escahp.gallery;

import com.esca.escahp.gallery.dto.GalleryBoardDto;
import com.esca.escahp.gallery.dto.GalleryResponse;
import com.esca.escahp.gallery.entity.GalleryBoard;
import com.esca.escahp.gallery.repository.GalleryBoardDao;

import java.util.List;

public interface I_GalleryBoardService {
    public List<GalleryResponse> getGalleryBoardList();

    //1203추가가
    public GalleryResponse selectGalleryBoard(long id);

    public void addBoard(GalleryBoard b);

    public void updateBoard(GalleryBoard b);

    public void deleteBoard(GalleryBoard b);

    public void updateViewCnt(long id);

}
