package com.esca.escahp.domain;

import com.esca.escahp.dto.FreeBoardDto;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class FreeBoard {

    private long id;

    private String title;

    private String content;

    private String writer;

    private String file;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private LocalDate deletedAt;

    private String deleteYn;

    private int viewCnt;

    private int report;

    private int likes;

    public FreeBoard(String title, String content, String writer, String file) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.file = file;
    }

    public void delete() {
        this.deletedAt = LocalDate.now();
        this.deleteYn = "Y";
    }

    public void update(FreeBoardDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.file = dto.getFile();
    }

    public void updateReport() {
        this.report++;
    }

    public void mosaicTitle() {
        if (this.report >= 5) {
            this.title = "블라인드된 글입니다.";
        }
    }

    public void addViewCount() {
        this.viewCnt++;
    }
}
