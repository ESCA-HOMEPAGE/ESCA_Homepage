package com.esca.escahp.gallery;

import com.esca.escahp.gallery.dto.GalleryBoardDto;
import com.esca.escahp.gallery.I_GalleryBoardService;
import com.esca.escahp.gallery.dto.GalleryRequest;
import com.esca.escahp.gallery.dto.GalleryResponse;
import com.esca.escahp.gallery.entity.GalleryBoard;
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
@CrossOrigin(origins = {"*"})
@Api(value = "GalleryBoardDto")// 외부에서도 접속이 가능하게 해주는 어노테이션	// 스웨거 설정
public class GalleryController {

    private final GalleryService galleryService;

    public GalleryController(GalleryService galleryService){this.galleryService = galleryService;}

    @ApiOperation(value = "갤러리 게시판의 전체 목록 보여주기")
    @GetMapping
    public ResponseEntity<List<GalleryResponse>> getAllGalleryBoard(){
        List<GalleryResponse> galleryBoard = galleryService.getGalleryBoardList();
        return ResponseEntity.ok().body(galleryBoard);
    }

    @ApiOperation(value = "id에 해당하는 게시물 정보 반환")
    @GetMapping("/{id}")
    public ResponseEntity<GalleryResponse> getGalleryBoardById(@PathVariable long id){
        GalleryResponse result = galleryService.selectGalleryBoard(id);
        if(result == null){
            return ResponseEntity.noContent().build();
        }
        galleryService.updateViewCnt(id);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "게시물 객체 추가")
    @PostMapping
    public ResponseEntity<GalleryResponse> insertGalleryBoard(
            @RequestBody GalleryRequest galleryBoard){
        Long id = galleryService.addBoard(galleryBoard);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "id에 해당하는 게시물 정보 수정")
   @PutMapping("/{id}")
    public ResponseEntity<GalleryResponse> updateGalleryBoard(@PathVariable Long id, @RequestBody GalleryRequest galleryBoard){
        galleryService.updateBoard(id, galleryBoard);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "id에 해당하는 게시물 정보 삭제")
    @PatchMapping("/{id}")
    public ResponseEntity<Object> deleteAction(@PathVariable Long id){
        galleryService.deleteBoard(id);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
