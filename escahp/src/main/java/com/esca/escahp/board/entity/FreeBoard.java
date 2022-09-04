package com.esca.escahp.board.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
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

    @Column
    private String file;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column
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
    }

    public void delete() {
        this.deleteYn = "Y";
        this.deletedAt = LocalDateTime.now();
    }

    public void update(String title, String content, String file) {
        this.title = title;
        this.content = content;
        this.file = file;
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

    public String mosaicTitle() {
        if (this.report >= 5)
            return "블라인드된 글입니다.";
        else
            return this.title;
    }
}
