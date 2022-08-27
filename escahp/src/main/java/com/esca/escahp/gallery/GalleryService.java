package com.esca.escahp.gallery;

import com.esca.escahp.gallery.dto.GalleryBoardDto;
import com.esca.escahp.gallery.dto.GalleryResponse;
import com.esca.escahp.gallery.entity.GalleryBoard;
import com.esca.escahp.gallery.repository.GalleryBoardDao;
import com.esca.escahp.gallery.repository.GalleryRepository;
import com.esca.escahp.study.StudyService;
import com.esca.escahp.study.entity.StudyBoard;
import com.esca.escahp.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GalleryService implements I_GalleryBoardService{


    private final GalleryRepository galleryRepository;

    public GalleryService(GalleryRepository galleryRepository){this.galleryRepository = galleryRepository;}

    @Override
    public List<GalleryResponse> getGalleryBoardList(){
        List<GalleryBoard> galleries = galleryRepository.findAll();
        return galleries
                .stream()
                .map(GalleryResponse::new)
                .collect(Collectors.toList());

    }

    @Override
    public GalleryResponse selectGalleryBoard(long id){
        GalleryBoard gallery = galleryRepository.findById(id)
                .orElseThrow(()-> new IllegalAccessError("[id=" + id + "] 해당 게시글은 존재하지 않습니다."));
        return new GalleryResponse(gallery);
    }

    @Transactional
    @Override
    public void addBoard(GalleryBoard b){galleryRepository.save(b);}

    @Transactional
    @Override
    public void updateBoard(GalleryBoard b){
        GalleryBoard origin = galleryRepository.findById(b.getId())
                .orElseThrow(()->new IllegalAccessError("[id=" + b.getId() + "] 해당 게시글은 존재하지 않습니다."));

        origin.update(b.getTitle(), b.getContent(), b.getFile());
    }

    @Transactional
    @Override
    public void deleteBoard(GalleryBoard b){
        GalleryBoard delete = galleryRepository.findById(b.getId())
                .orElseThrow(() -> new IllegalAccessError("[id=" + b.getId() + "] 해당 게시글은 존재하지 않습니다."));

        delete.delete();
    }

    @Override
    public void updateViewCnt(long id){
        GalleryBoard board = galleryRepository.findById(id)
                .orElseThrow(() -> new IllegalAccessError("[id=" + id + "] 해당 게시글은 존재하지 않습니다."));
        board.update();
    }
}

