package com.esca.escahp.notice;

import com.esca.escahp.notice.dto.NoticeRequest;
import com.esca.escahp.notice.dto.NoticeResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/notices")
@CrossOrigin(origins = {"*"})
@Api(tags = "Notice")
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @ApiOperation(value = "공지 게시판의 전체 목록 보여주기", response = List.class)
    @GetMapping
    public ResponseEntity<List<NoticeResponse>> getAllNoticeBoard(){
        List<NoticeResponse> noticeBoard = noticeService.getNoticeList();
        return ResponseEntity.ok().body(noticeBoard);
    }

    @ApiOperation(value = "id에 해당하는 게시물 정보 반환")
    @GetMapping("/{id}")
    public ResponseEntity<NoticeResponse> getNoticeBoardById(@PathVariable Long id){
        NoticeResponse noticeBoard = noticeService.selectNotice(id);
        if(noticeBoard == null){
            return ResponseEntity.noContent().build();
        }
        NoticeResponse response = noticeService.updateViewCount(id);
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "게시물 객체 추가")
    @PostMapping
    public ResponseEntity<NoticeResponse> insertNoticeBoard(@RequestBody NoticeRequest notice){
        Long id = noticeService.insertNotice(notice);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "id에 해당하는 게시물 정보 수정")
    @PutMapping("/{id}")
    public ResponseEntity<NoticeResponse> updateNoticeBoard(@PathVariable Long id, @RequestBody NoticeRequest notice){
        NoticeResponse response = noticeService.updateNotice(id, notice);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "id에 해당하는 게시물 정보 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteNoticeBoard(@PathVariable Long id){
        noticeService.deleteNotice(id);
        return ResponseEntity.noContent().build();
    }
}