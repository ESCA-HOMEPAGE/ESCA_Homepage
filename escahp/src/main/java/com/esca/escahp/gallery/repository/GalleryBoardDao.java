package com.esca.escahp.mapper;

import com.esca.escahp.dto.GalleryBoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;
@Mapper
public interface GalleryBoardDao {
    public void insertGalleryBoard(GalleryBoardDto params);

    public GalleryBoardDto selectGalleryBoard(long id);

    public void updateGalleryBoard(GalleryBoardDto params);

    public void deleteGalleryBoard(GalleryBoardDto params);

    public List<GalleryBoardDto> selectGalleryBoardList();

    public void updateViewCnt(long id);

    //public int selectGalleryBoardTotal();


}
