package com.esca.escahp.gallery;

import com.esca.escahp.gallery.dto.GalleryBoardDto;
import com.esca.escahp.gallery.repository.GalleryBoardDao;

import java.util.List;

public interface I_GalleryBoardService {
    public List<GalleryBoardDto> selectGalleryBoardList();

    //1203추가가
    public GalleryBoardDto selectGalleryBoard(long id);

    public void insertGalleryBoard(GalleryBoardDto params);

    public void updateGalleryBoard(GalleryBoardDto params);

    public void deleteGalleryBoard(GalleryBoardDto params);

    public void updateViewCnt(long id);

}
