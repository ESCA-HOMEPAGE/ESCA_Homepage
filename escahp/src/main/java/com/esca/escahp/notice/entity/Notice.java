package com.esca.escahp.notice.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column
    private String file;

    @Column(columnDefinition = "bigint default 0")
    private Long viewCnt = 0L;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder
    public Notice(String title, String category, String writer, String content, String file) {
        this.title = title;
        this.category = category;
        this.writer = writer;
        this.content = content;
        this.file = file;
        this.createdAt = LocalDateTime.now();
    }

    public void update(String title, String content, String file, String category) {
        this.title = title;
        this.content = content;
        this.file = file;
        this.category = category;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateViewCnt() {
        this.viewCnt = this.viewCnt + 1;
    }
}
