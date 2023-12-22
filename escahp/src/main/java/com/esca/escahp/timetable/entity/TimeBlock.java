package com.esca.escahp.timetable.entity;

import javax.persistence.*;


import com.esca.escahp.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Setter
@NoArgsConstructor
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"days_id", "times_id", "timetable_id"})
})
public class TimeBlock {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "timetable_id")
    private TimeTable timeTable;

    @OneToMany(mappedBy = "timeBlock")
    private List<UserTimeBlock> userTimeBlocks;

    @ManyToOne
    @JoinColumn(name = "days_id")
    private Day day;

    @ManyToOne
    @JoinColumn(name = "times_id")
    private Time time;

    @Column()
    private boolean isFull;

    @Column()
    private int limit;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
