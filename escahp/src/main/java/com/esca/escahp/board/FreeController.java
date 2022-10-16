package com.esca.escahp.board;


import com.esca.escahp.board.dto.FreeRequest;
import com.esca.escahp.board.dto.FreeResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Api(tags = "Free")		// 스웨거 설정
@RestController
@RequestMapping("/free")
@CrossOrigin(origins = {"*"})        // 외부에서도 접속이 가능하게 해주는 어노테이션
public class FreeController {
    private final FreeService freeService;

    public FreeController(FreeService freeService) {
        this.freeService = freeService;
    }

    @ApiOperation(value = "자유 게시판의 전체 목록 보여주기")
    @GetMapping
    public ResponseEntity<List<FreeResponse>> getAllFreeBoard() {
        List<FreeResponse> list = freeService.getFreeBoardList();
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = "id에 해당하는 게시물 정보 반환")
    @GetMapping("/{id}")
    public ResponseEntity<FreeResponse> getFreeBoardById(@PathVariable long id) {
        FreeResponse result = freeService.selectFreeBoard(id);
        if (result == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "게시물 객체 추가")
    @PostMapping
    public ResponseEntity<FreeResponse> postFreeBoard(@RequestBody FreeRequest freeBoard) {
        Long id = freeService.postBoard(freeBoard);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri();

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "id에 해당하는 게시물 정보 수정")
    @PutMapping("/{id}")
    public ResponseEntity<FreeResponse> updateFreeBoard(@PathVariable Long id, @RequestBody FreeRequest freeBoard) {
        freeService.updateBoard(id, freeBoard);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .buildAndExpand()
            .toUri();

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "id에 해당하는 게시물 정보 삭제")
    @PatchMapping("/{id}")
    public ResponseEntity<Object> deleteFreeBoard(@PathVariable Long id) {
        freeService.deleteBoard(id);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .buildAndExpand()
            .toUri();

        return ResponseEntity.created(location).build();
    }

//    @ApiOperation(value = "id에 해당하는 게시물의 신고 횟수 증가")
//    @PatchMapping("/report/{id}")
//    public ResponseEntity<Object> updateReport(@PathVariable long id, @RequestBody FreeBoard freeBoard) {
//        freeBoard.setId(id);
//        freeBoardService.updateReport(freeBoardDto);
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//            .buildAndExpand()
//            .toUri();
//
//        return ResponseEntity.created(location).build();
//    }
}
