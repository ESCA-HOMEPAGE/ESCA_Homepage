package com.esca.escahp.domain;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Entity
@Setter
@NoArgsConstructor
public class StudyBoard {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String file;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column
    private LocalDateTime deletedAt;

    @Column(nullable = false)
    private String deleteYn;

    @Column(nullable = false)
    private long viewCnt;

    @Column(nullable = false)
    private int likes;

    public StudyBoard(String title, String content, String writer, String category, String file){
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

    public void update(){
        this.viewCnt = this.viewCnt + 1;
    }

}