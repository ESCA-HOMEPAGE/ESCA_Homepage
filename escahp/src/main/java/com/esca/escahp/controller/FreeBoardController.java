package com.esca.escahp.controller;


import com.esca.escahp.dto.FreeBoardDto;
import com.esca.escahp.service.FreeBoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequestMapping("/free")
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})        // 외부에서도 접속이 가능하게 해주는 어노테이션
@Api(value = "FreeBoardDto")		// 스웨거 설정
public class FreeBoardController {
    private final FreeBoardService freeBoardService;

    @ApiOperation(value = "자유 게시판의 전체 목록 보여주기")
    @GetMapping
    public ResponseEntity<List<FreeBoardDto>> getFreeArticles() {
        List<FreeBoardDto> list = freeBoardService.getArticles();
        return ResponseEntity.ok(list);
    }

    @ApiOperation(value = "id에 해당하는 게시물 정보 반환")
    @GetMapping("/{id}")
    public ResponseEntity<FreeBoardDto> getFreeArticle(@PathVariable long id) {
        FreeBoardDto dto = freeBoardService.getArticle(id);
        if (dto == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dto);
    }

    @ApiOperation(value = "게시물 객체 추가")
    @PostMapping
    public ResponseEntity<FreeBoardDto> writeFreeArticle(@RequestBody FreeBoardDto freeBoardDto) {
        freeBoardService.writeArticle(freeBoardDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(freeBoardDto.getId())
            .toUri();

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "id에 해당하는 게시물 정보 수정")
    @PutMapping("/{id}")
    public ResponseEntity<FreeBoardDto> modifyFreeArticle(@PathVariable long id, @RequestBody FreeBoardDto freeBoardDto) {
        freeBoardDto.setId(id);
        freeBoardService.modifyArticle(freeBoardDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .buildAndExpand()
            .toUri();

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "id에 해당하는 게시물 정보 삭제")
    @PatchMapping("/{id}")
    public ResponseEntity<Object> deleteFreeArticle(@PathVariable long id, @RequestBody FreeBoardDto freeBoardDto) {
        freeBoardDto.setId(id);
        freeBoardService.deleteArticle(freeBoardDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .buildAndExpand()
            .toUri();

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "id에 해당하는 게시물의 신고 횟수 증가")
    @PatchMapping("/report/{id}")
    public ResponseEntity<Object> increaseReport(@PathVariable long id, @RequestBody FreeBoardDto freeBoardDto) {
        freeBoardDto.setId(id);
        freeBoardService.updateReport(freeBoardDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .buildAndExpand()
            .toUri();

        return ResponseEntity.created(location).build();
    }
}
