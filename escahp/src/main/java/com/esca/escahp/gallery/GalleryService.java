package com.esca.escahp.gallery;

import com.esca.escahp.common.exceptions.BoardExceptions;
import com.esca.escahp.exception.EscaException;
import com.esca.escahp.gallery.dto.GalleryRequest;
import com.esca.escahp.gallery.dto.GalleryResponse;
import com.esca.escahp.gallery.entity.GalleryBoard;
import com.esca.escahp.gallery.repository.GalleryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GalleryService implements I_GalleryBoardService {


    private final GalleryRepository galleryRepository;

    public GalleryService(GalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;
    }

    @Override
    public List<GalleryResponse> getGalleryBoardList() {
        List<GalleryBoard> galleries = galleryRepository.findAll();
        return galleries
                .stream()
                .map(GalleryResponse::new)
                .collect(Collectors.toList());

    }

    @Override
    public GalleryResponse selectGalleryBoard(long id) {
        GalleryBoard gallery = galleryRepository.findById(id)
                .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));
        return new GalleryResponse(gallery);
    }

    @Transactional
    @Override
    public Long addBoard(GalleryRequest b) {
        return galleryRepository.save(b.toEntity()).getId();
    }

    @Transactional
    @Override
    public void updateBoard(Long id, GalleryRequest b) {
        GalleryBoard origin = galleryRepository.findById(id)
                .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));

        origin.update(b.getTitle(), b.getContent(), b.getFile());
    }

    @Transactional
    @Override
    public void deleteBoard(Long id) {
        GalleryBoard delete = galleryRepository.findById(id)
                .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));

        delete.delete();
    }

    @Transactional
    @Override
    public void updateViewCnt(long id) {
        GalleryBoard board = galleryRepository.findById(id)
                .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));
        board.updateViewCount();
    }
}

