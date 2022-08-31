package com.esca.escahp.keeper.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Setter
@NoArgsConstructor
public class TimeTable {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private String semester;

    @Column(nullable = false)
    @CreationTimestamp()
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;
}
