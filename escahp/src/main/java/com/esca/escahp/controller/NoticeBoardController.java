package com.esca.escahp.controller;

import com.esca.escahp.dto.NoticeBoardDto;
import com.esca.escahp.service.I_NoticeBoardService;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
@RequiredArgsConstructor
public class NoticeBoardController {

    private final I_NoticeBoardService noticeBoardService;

    @GetMapping
    public ResponseEntity<List<NoticeBoardDto>> getAllNoticeBoard(){
        List<NoticeBoardDto> noticeBoard = noticeBoardService.selectNoticeBoardList();
        return ResponseEntity.ok().body(noticeBoard);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticeBoardDto> getNoticeBoardById(@PathVariable long id){
        NoticeBoardDto noticeBoard = noticeBoardService.selectNoticeBoard(id);
        if(noticeBoard == null){
            return ResponseEntity.noContent().build();
        }
        noticeBoardService.updateViewCount(id);
        return ResponseEntity.ok().body(noticeBoard);
    }

    @PostMapping
    public ResponseEntity<NoticeBoardDto> insertNoticeBoard(@RequestBody NoticeBoardDto noticeBoardDto){
        noticeBoardService.insertNoticeBoard(noticeBoardDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(noticeBoardDto.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoticeBoardDto> updateNoticeBoard(@PathVariable Long id, @RequestBody NoticeBoardDto noticeBoardDto){
        noticeBoardDto.setId(id);
        noticeBoardService.updateNoticeBoard(noticeBoardDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(location).build();
    }

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