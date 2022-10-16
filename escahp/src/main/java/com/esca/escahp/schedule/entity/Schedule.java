package com.esca.escahp.schedule.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Schedule {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String tag;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Schedule(String title, String tag, String content, LocalDateTime startDate, LocalDateTime endDate){
        this.title = title;
        this.tag = tag;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void update(String title, String tag, String content, LocalDateTime startDate, LocalDateTime endDate) {
        this.title = title;
        this.tag = tag;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}