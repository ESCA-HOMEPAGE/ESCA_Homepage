package com.esca.escahp.timetable.entity;

import javax.persistence.*;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Setter
@NoArgsConstructor
public class UserTimeTable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "days_id")
    private Days day;

    @ManyToOne
    @JoinColumn(name = "times_id")
    private Times time;
}
