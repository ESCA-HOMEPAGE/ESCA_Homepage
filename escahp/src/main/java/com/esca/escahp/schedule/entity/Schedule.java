package com.esca.escahp.schedule.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Schedule {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String tag;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @CreationTimestamp()
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;
}