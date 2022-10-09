package com.esca.escahp.notice;

import com.esca.escahp.notice.dto.NoticeBoardDto;
import com.esca.escahp.notice.I_NoticeBoardService;
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

@Api(tags = "Notice")		// 스웨거 설정
@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})        // 외부에서도 접속이 가능하게 해주는 어노테이션
public class NoticeBoardController {

    private final I_NoticeBoardService noticeBoardService;

    @ApiOperation(value = "공지 게시판의 전체 목록 보여주기", response = List.class)
    @GetMapping
    public ResponseEntity<List<NoticeBoardDto>> getAllNoticeBoard(){
        List<NoticeBoardDto> noticeBoard = noticeBoardService.selectNoticeBoardList();
        return ResponseEntity.ok().body(noticeBoard);
    }

    @ApiOperation(value = "id에 해당하는 게시물 정보 반환")
    @GetMapping("/{id}")
    public ResponseEntity<NoticeBoardDto> getNoticeBoardById(@PathVariable long id){
        NoticeBoardDto noticeBoard = noticeBoardService.selectNoticeBoard(id);
        if(noticeBoard == null){
            return ResponseEntity.noContent().build();
        }
        noticeBoardService.updateViewCount(id);
        return ResponseEntity.ok().body(noticeBoard);
    }

    @ApiOperation(value = "게시물 객체 추가")
    @PostMapping
    public ResponseEntity<NoticeBoardDto> insertNoticeBoard(@RequestBody NoticeBoardDto noticeBoardDto){
        noticeBoardService.insertNoticeBoard(noticeBoardDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(noticeBoardDto.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "id에 해당하는 게시물 정보 수정")
    @PutMapping("/{id}")
    public ResponseEntity<NoticeBoardDto> updateNoticeBoard(@PathVariable Long id, @RequestBody NoticeBoardDto noticeBoardDto){
        noticeBoardDto.setId(id);
        noticeBoardService.updateNoticeBoard(noticeBoardDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "id에 해당하는 게시물 정보 삭제")
    @PatchMapping("/{id}")
    public ResponseEntity<Object> deleteNoticeBoard(@PathVariable Long id, @RequestBody NoticeBoardDto noticeBoardDto){
        noticeBoardDto.setId(id);
        noticeBoardService.deleteNoticeBoard(noticeBoardDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(location).build();
    }

}