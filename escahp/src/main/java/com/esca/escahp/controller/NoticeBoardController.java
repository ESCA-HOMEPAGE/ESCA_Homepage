package com.esca.escahp.controller;

import com.esca.escahp.dto.NoticeBoardDto;
import com.esca.escahp.mapper.NoticeBoardDao;
import com.esca.escahp.service.I_NoticeBoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        return noticeBoardService.selectNoticeBoard(id);
    }
}