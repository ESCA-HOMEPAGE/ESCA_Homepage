package com.esca.escahp.controller;

import com.esca.escahp.dto.NoticeBoardDto;
import com.esca.escahp.mapper.NoticeBoardDao;
import com.esca.escahp.service.I_NoticeBoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NoticeBoardController {

    private final I_NoticeBoardService noticeBoardService;

    @RequestMapping(value="/notice", method = RequestMethod.GET)
    public List<NoticeBoardDto> selectNoticeBoardList() {
        return noticeBoardService.selectNoticeBoardList();
    }

    @GetMapping("/notice/{id}")
    public NoticeBoardDto selectNoticeBoard(@PathVariable Long id){
        noticeBoardService.updateViewCount(id);
        return noticeBoardService.selectNoticeBoard(id);
    }

    @PostMapping("/notice")
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

    @PutMapping("/notice/{id}")
    public String updateNoticeBoard(@PathVariable Long id, @RequestBody NoticeBoardDto noticeBoardDto){
        noticeBoardDto.setId(id);
        noticeBoardService.updateNoticeBoard(noticeBoardDto);
        return "Update Success";
    }

    @PatchMapping("/notice/{id}")
    public String deleteNoticeBoard(@PathVariable Long id, @RequestBody NoticeBoardDto noticeBoardDto){
        noticeBoardDto.setId(id);
        noticeBoardService.deleteNoticeBoard(noticeBoardDto);
        return "Delete Success";
    }


}