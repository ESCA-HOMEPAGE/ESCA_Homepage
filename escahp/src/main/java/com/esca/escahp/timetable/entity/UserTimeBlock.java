package com.esca.escahp.timetable.entity;

import com.esca.escahp.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserTimeBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "timeblock_id")
    private TimeBlock timeBlock;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
