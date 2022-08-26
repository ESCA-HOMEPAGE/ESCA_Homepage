package com.esca.escahp.board.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class FreeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    private String file;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @Column(nullable = false)
    private String deleteYn;

    @Column(nullable = false)
    private long viewCnt;

    @Column(nullable = false)
    private int report;

    @Column(nullable = false)
    private int likes;

    @Builder
    public FreeBoard(String title, String content, String writer, String file) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.file = file;
        this.deleteYn = "N";
        this.createdAt = LocalDateTime.now();
    }

    public void delete() {
        this.deleteYn = "Y";
        this.deletedAt = LocalDateTime.now();
    }

    public void update(String title, String content, String file) {
        this.title = title;
        this.content = content;
        this.file = file;
        this.updatedAt = LocalDateTime.now();
    }

    public void increaseViewCnt() {
        this.viewCnt = this.viewCnt + 1;
    }

    public void updateReport() {
        this.report = this.report + 1;
    }

    public void updateLikes() {
        this.likes = this.likes + 1;
    }

    public void mosaicTitle() {
        if (this.report >= 5) {
            this.title = "블라인드된 글입니다.";
        }
    }
}
