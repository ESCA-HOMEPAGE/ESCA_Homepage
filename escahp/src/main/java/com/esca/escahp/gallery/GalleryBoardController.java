package com.esca.escahp.gallery;

import com.esca.escahp.gallery.dto.GalleryBoardDto;
import com.esca.escahp.gallery.I_GalleryBoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@CrossOrigin(origins = {"*"})        // 외부에서도 접속이 가능하게 해주는 어노테이션
@Api(value = "GalleryBoardDto")		// 스웨거 설정
public class GalleryBoardController {

    private final I_GalleryBoardService galleryBoardService;

    @ApiOperation(value = "갤러리 게시판의 전체 목록 보여주기")
    @GetMapping
    public ResponseEntity<List<GalleryBoardDto>> getAllGalleryBoard(){
        List<GalleryBoardDto> galleryBoard = galleryBoardService.selectGalleryBoardList();
        return ResponseEntity.ok().body(galleryBoard);
    }

    @ApiOperation(value = "id에 해당하는 게시물 정보 반환")
    @GetMapping("/{id}")
    public ResponseEntity<GalleryBoardDto> getGalleryBoardById(@PathVariable long id){
        GalleryBoardDto galleryBoardDto = galleryBoardService.selectGalleryBoard(id);
        if(galleryBoardDto == null){
            return ResponseEntity.noContent().build();
        }
        galleryBoardService.updateViewCnt(id);
        return ResponseEntity.ok().body(galleryBoardDto);
    }

    @ApiOperation(value = "게시물 객체 추가")
    @PostMapping
    public ResponseEntity<GalleryBoardDto> insertGalleryBoard(@RequestBody GalleryBoardDto params){
        galleryBoardService.insertGalleryBoard(params);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(params.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    };

    @ApiOperation(value = "id에 해당하는 게시물 정보 수정")
   @PutMapping("/{id}")
    public ResponseEntity<GalleryBoardDto> updateGalleryBoard(@PathVariable Long id, @RequestBody GalleryBoardDto params){
        params.setId(id);
        galleryBoardService.updateGalleryBoard(params);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "id에 해당하는 게시물 정보 삭제")
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
