package com.esca.escahp.controller;

import com.esca.escahp.dto.GalleryBoardDto;
import com.esca.escahp.service.I_GalleryBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GalleryBoardController {

    private final I_GalleryBoardService galleryBoardService;

    @GetMapping("/gallery")
    public List<GalleryBoardDto> selectGalleryBoardList(){

        return galleryBoardService.selectGalleryBoardList();
    }

    @GetMapping("/gallery/{id}")
    public GalleryBoardDto selectGalleryBoard(@PathVariable Long id){
        GalleryBoardDto gbd = galleryBoardService.selectGalleryBoard(id);
        System.out.println("view: " + gbd.getViewCnt());
        System.out.println("userId: " + gbd.getUserId());
        return gbd;
    };

    @PostMapping("/gallery")
    public int insertGalleryBoard(@RequestBody GalleryBoardDto params){

        return galleryBoardService.insertGalleryBoard(params);
    };

   @PutMapping("/gallery/{id}")
    public int updateGalleryBoard(@PathVariable Long id, @RequestBody GalleryBoardDto params){
        params.setId(id);
        return galleryBoardService.updateGalleryBoard(params);
    };

    @PatchMapping("/gallery/{id}")
    public int deleteGalleryBoard(@PathVariable Long id, @RequestBody GalleryBoardDto params){
        params.setId(id);
        return galleryBoardService.deleteGalleryBoard(params);
    };
}
