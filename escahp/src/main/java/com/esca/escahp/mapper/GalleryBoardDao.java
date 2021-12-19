package com.esca.escahp.mapper;

import com.esca.escahp.dto.GalleryBoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;
@Mapper
public interface GalleryBoardDao {
    public int insertGalleryBoard(GalleryBoardDto params);

    public GalleryBoardDto selectGalleryBoard(Long id);

    public int updateGalleryBoard(GalleryBoardDto params);

    public int deleteGalleryBoard(GalleryBoardDto params);

    public List<GalleryBoardDto> selectGalleryBoardList();

    //public int selectGalleryBoardTotal();


}
