package com.esca.escahp.timetable.entity;

import javax.persistence.*;


import com.esca.escahp.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Setter
@NoArgsConstructor
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"user_id", "days_id", "times_id", "timetable_id"})
})
public class UserTimeTable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "days_id")
    private Days day;

    @ManyToOne
    @JoinColumn(name = "times_id")
    private Times time;
    
    @ManyToOne
    @JoinColumn(name = "timetable_id")
    private TimeTable timeTable;


}
