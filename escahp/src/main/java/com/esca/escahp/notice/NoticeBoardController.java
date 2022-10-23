package com.esca.escahp.notice;

import com.esca.escahp.notice.dto.NoticeRequest;
import com.esca.escahp.notice.dto.NoticeResponse;
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

@RestController
@RequestMapping("/notice")
@CrossOrigin(origins = {"*"})
@Api(tags = "Notice")
public class NoticeBoardController {

    private final NoticeBoardService noticeBoardService;

    public NoticeBoardController(NoticeBoardService noticeBoardService) {
        this.noticeBoardService = noticeBoardService;
    }

    @ApiOperation(value = "공지 게시판의 전체 목록 보여주기", response = List.class)
    @GetMapping
    public ResponseEntity<List<NoticeResponse>> getAllNoticeBoard(){
        List<NoticeResponse> noticeBoard = noticeBoardService.getNoticeBoardList();
        return ResponseEntity.ok().body(noticeBoard);
    }

    @ApiOperation(value = "id에 해당하는 게시물 정보 반환")
    @GetMapping("/{id}")
    public ResponseEntity<NoticeResponse> getNoticeBoardById(@PathVariable Long id){
        NoticeResponse noticeBoard = noticeBoardService.selectNoticeBoard(id);
        if(noticeBoard == null){
            return ResponseEntity.noContent().build();
        }
        noticeBoardService.updateViewCount(id);
        return ResponseEntity.ok().body(noticeBoard);
    }

    @ApiOperation(value = "게시물 객체 추가")
    @PostMapping
    public ResponseEntity<NoticeResponse> insertNoticeBoard(@RequestBody NoticeRequest notice){
        Long id = noticeBoardService.insertNoticeBoard(notice);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "id에 해당하는 게시물 정보 수정")
    @PutMapping("/{id}")
    public ResponseEntity<NoticeResponse> updateNoticeBoard(@PathVariable Long id, @RequestBody NoticeRequest notice){
        noticeBoardService.updateNoticeBoard(id, notice);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "id에 해당하는 게시물 정보 삭제")
    @PatchMapping("/{id}")
    public ResponseEntity<Object> deleteNoticeBoard(@PathVariable Long id){
        noticeBoardService.deleteNoticeBoard(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(location).build();
    }
}