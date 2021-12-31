package com.esca.escahp.controller;

import com.esca.escahp.dto.NoticeBoardDto;
import com.esca.escahp.service.I_NoticeBoardService;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<>(noticeBoard, HttpStatus.OK);
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

    // 왜 이렇게 사용하는지에 대한 대답 https://www.inflearn.com/questions/176104
    // body 보낼 건지?
    @PostMapping
    public ResponseEntity<NoticeBoardDto> insertNoticeBoard(@RequestBody NoticeBoardDto noticeBoardDto){
        noticeBoardService.insertNoticeBoard(noticeBoardDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(noticeBoardDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(noticeBoardDto);
        //이 방법도 있음 URI.create("/notice/" + noticeBoardDto.getId())
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoticeBoardDto> updateNoticeBoard(@PathVariable Long id, @RequestBody NoticeBoardDto noticeBoardDto){
        noticeBoardDto.setId(id);
        noticeBoardService.updateNoticeBoard(noticeBoardDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(location).body(noticeBoardDto);
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

    /*
    @GetMapping
    public List<NoticeBoardDto> selectNoticeBoardList() {
        return noticeBoardService.selectNoticeBoardList();
    }

    @GetMapping("/{id}")
    public NoticeBoardDto selectNoticeBoard(@PathVariable Long id){
        noticeBoardService.updateViewCount(id);
        return noticeBoardService.selectNoticeBoard(id);
    }

    @PostMapping
    public String insertNoticeBoard(@RequestBody final NoticeBoardDto noticeBoardDto){
        try{
            boolean isInsert = noticeBoardService.insertNoticeBoard(noticeBoardDto);
            if(isInsert==false){
                //게시글 등록 실패
            }
        } catch(DataAccessException e){
            //데이터 처리 과정 이상
        } catch(Exception e){
            //시스템 문제
        }
        return "Success";
    }

    @PutMapping("/{id}")
    public String updateNoticeBoard(@PathVariable Long id, @RequestBody NoticeBoardDto noticeBoardDto){
        noticeBoardDto.setId(id);
        noticeBoardService.updateNoticeBoard(noticeBoardDto);
        return "Update Success";
    }

    @PatchMapping("/{id}")
    public String deleteNoticeBoard(@PathVariable Long id, @RequestBody NoticeBoardDto noticeBoardDto){
        noticeBoardDto.setId(id);
        noticeBoardService.deleteNoticeBoard(noticeBoardDto);
        return "Delete Success";
    }
*/

}