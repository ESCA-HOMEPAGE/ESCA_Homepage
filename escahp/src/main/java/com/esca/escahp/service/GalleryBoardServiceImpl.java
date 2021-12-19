package com.esca.escahp.service;

import com.esca.escahp.dto.GalleryBoardDto;
import com.esca.escahp.mapper.GalleryBoardDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryBoardServiceImpl implements I_GalleryBoardService{
    @Autowired
    private final GalleryBoardDao galleryBoardDao;

    @Override
    public List<GalleryBoardDto> selectGalleryBoardList(){

        return galleryBoardDao.selectGalleryBoardList();
    }

    //1203추가가
   @Override
    public GalleryBoardDto selectGalleryBoard(Long id){

        return galleryBoardDao.selectGalleryBoard(id);
    }

    @Override
    public int insertGalleryBoard(GalleryBoardDto params){
        galleryBoardDao.insertGalleryBoard(params);
        return 1;
    };

    @Override
    public int updateGalleryBoard(GalleryBoardDto params){
        galleryBoardDao.updateGalleryBoard(params);
        return 1;
    };

    @Override
    public int deleteGalleryBoard(GalleryBoardDto params){
        galleryBoardDao.deleteGalleryBoard(params);
        return 1;
    };
}
