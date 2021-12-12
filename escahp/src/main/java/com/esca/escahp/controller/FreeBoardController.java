package com.esca.escahp.controller;


import com.esca.escahp.dto.FreeBoardDto;
import com.esca.escahp.service.FreeBoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/free")
@RequiredArgsConstructor
public class FreeBoardController {
    private final FreeBoardService freeBoardService;

    @GetMapping
    public List<FreeBoardDto> getFreeArticles() {
        return freeBoardService.getArticles();
    }

    @GetMapping("/{no}")
    public FreeBoardDto getFreeArticle(@PathVariable long no) {
        return freeBoardService.getArticle(no);
    }

    @PostMapping
    public long writeFreeArticle(@RequestBody FreeBoardDto freeBoardDto) {
        return freeBoardService.writeArticle(freeBoardDto);
    }

    @PutMapping("/{no}")
    public long modifyFreeArticle(@PathVariable long no, @RequestBody FreeBoardDto freeBoardDto) {
        freeBoardDto.setId(no);
        return freeBoardService.modifyArticle(freeBoardDto);
    }

    @PatchMapping("/{no}")
    public long deleteFreeArticle(@PathVariable long no, @RequestBody FreeBoardDto freeBoardDto) {
        freeBoardDto.setId(no);
        return freeBoardService.deleteArticle(freeBoardDto);
    }

    @PatchMapping("/report/{no}")
    public void increaseReport(@PathVariable long no, @RequestBody FreeBoardDto freeBoardDto) {
        freeBoardDto.setId(no);
        freeBoardService.updateReport(freeBoardDto);
    }
}
