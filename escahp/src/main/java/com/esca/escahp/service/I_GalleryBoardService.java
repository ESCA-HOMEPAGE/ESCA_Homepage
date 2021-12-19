package com.esca.escahp.service;

import com.esca.escahp.dto.GalleryBoardDto;
import com.esca.escahp.mapper.GalleryBoardDao;

import java.util.List;

public interface I_GalleryBoardService {
    public List<GalleryBoardDto> selectGalleryBoardList();

    //1203추가가
    public GalleryBoardDto selectGalleryBoard(Long id);

    public int insertGalleryBoard(GalleryBoardDto params);

    public int updateGalleryBoard(GalleryBoardDto params);

    public int deleteGalleryBoard(GalleryBoardDto params);

}
