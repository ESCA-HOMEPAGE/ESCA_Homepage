package com.esca.escahp.controller;


import com.esca.escahp.dto.FreeBoardDto;
import com.esca.escahp.service.FreeBoardService;
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
@RequestMapping("/free")
@RequiredArgsConstructor
public class FreeBoardController {
    private final FreeBoardService freeBoardService;

    @GetMapping
    public ResponseEntity<List<FreeBoardDto>> getFreeArticles() {
        List<FreeBoardDto> list = freeBoardService.getArticles();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FreeBoardDto> getFreeArticle(@PathVariable long id) {
        FreeBoardDto dto = freeBoardService.getArticle(id);
        if (dto == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<FreeBoardDto> writeFreeArticle(@RequestBody FreeBoardDto freeBoardDto) {
        freeBoardService.writeArticle(freeBoardDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(freeBoardDto.getId())
            .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FreeBoardDto> modifyFreeArticle(@PathVariable long id, @RequestBody FreeBoardDto freeBoardDto) {
        freeBoardDto.setId(id);
        freeBoardService.modifyArticle(freeBoardDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .buildAndExpand()
            .toUri();

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> deleteFreeArticle(@PathVariable long id, @RequestBody FreeBoardDto freeBoardDto) {
        freeBoardDto.setId(id);
        freeBoardService.deleteArticle(freeBoardDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .buildAndExpand()
            .toUri();

        return ResponseEntity.created(location).build();
    }

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
