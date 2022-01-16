package com.esca.escahp.controller;

import com.esca.escahp.dto.GalleryBoardDto;
import com.esca.escahp.service.I_GalleryBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/gallery")
@RequiredArgsConstructor
public class GalleryBoardController {

    private final I_GalleryBoardService galleryBoardService;

    @GetMapping
    public ResponseEntity<List<GalleryBoardDto>> getAllGalleryBoard(){
        List<GalleryBoardDto> galleryBoard = galleryBoardService.selectGalleryBoardList();
        return ResponseEntity.ok().body(galleryBoard);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GalleryBoardDto> getGalleryBoardById(@PathVariable long id){
        GalleryBoardDto galleryBoardDto = galleryBoardService.selectGalleryBoard(id);
        if(galleryBoardDto == null){
            return ResponseEntity.noContent().build();
        }
        galleryBoardService.updateViewCnt(id);
        return ResponseEntity.ok().body(galleryBoardDto);
    }

    @PostMapping
    public ResponseEntity<GalleryBoardDto> insertGalleryBoard(@RequestBody GalleryBoardDto params){
        galleryBoardService.insertGalleryBoard(params);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(params.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    };

   @PutMapping("/{id}")
    public ResponseEntity<GalleryBoardDto> updateGalleryBoard(@PathVariable Long id, @RequestBody GalleryBoardDto params){
        params.setId(id);
        galleryBoardService.updateGalleryBoard(params);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> deleteGalleryBoard(@PathVariable Long id, @RequestBody GalleryBoardDto params){
        params.setId(id);
        galleryBoardService.deleteGalleryBoard(params);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
