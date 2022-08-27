package com.esca.escahp.gallery.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Entity
@Setter
@NoArgsConstructor
public class GalleryBoard {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String file;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private LocalDateTime deletedAt;

    @Column(nullable = false)
    private String deleteYn;

    @Column(nullable = false)
    private long viewCnt;

    @Column(nullable = false)
    private int likes;

    @Builder
    public GalleryBoard(String title, String content, String writer, String category, String file){
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.category = category;
        this.file = file;
        this.createdAt = LocalDateTime.now();
        this.deleteYn = "N";
    }

    public void delete(){
        this.deletedAt = LocalDateTime.now();
        this.deleteYn = "Y";
    }

    public void update(String title, String content, String file){
        this.title = title;
        this.content = content;
        this.file = file;
        this.updatedAt = LocalDateTime.now();
    }

    public void update(){this.viewCnt = this.viewCnt + 1;}
}
