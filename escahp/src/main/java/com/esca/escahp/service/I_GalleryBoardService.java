package com.esca.escahp.service;

import com.esca.escahp.dto.GalleryBoardDto;
import com.esca.escahp.mapper.GalleryBoardDao;

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
