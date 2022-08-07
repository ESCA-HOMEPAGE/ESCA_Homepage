package com.esca.escahp.gallery;

import com.esca.escahp.gallery.dto.GalleryBoardDto;
import com.esca.escahp.gallery.repository.GalleryBoardDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryBoardService implements I_GalleryBoardService{

    private final GalleryBoardDao galleryBoardDao;

    @Override
    public List<GalleryBoardDto> selectGalleryBoardList(){

        return galleryBoardDao.selectGalleryBoardList();
    }

    //1203추가가
   @Override
    public GalleryBoardDto selectGalleryBoard(long id){

        return galleryBoardDao.selectGalleryBoard(id);
    }

    @Override
    public void insertGalleryBoard(GalleryBoardDto params){
        galleryBoardDao.insertGalleryBoard(params);
    };

    @Override
    public void updateGalleryBoard(GalleryBoardDto params){
        galleryBoardDao.updateGalleryBoard(params);
    };

    @Override
    public void deleteGalleryBoard(GalleryBoardDto params){
        galleryBoardDao.deleteGalleryBoard(params);
    };

    public void updateViewCnt(long id) {galleryBoardDao.updateViewCnt(id);}
}
