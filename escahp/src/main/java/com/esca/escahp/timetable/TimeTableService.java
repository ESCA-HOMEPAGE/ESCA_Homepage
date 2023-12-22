package com.esca.escahp.timetable;

import com.esca.escahp.schedule.repository.ScheduleRepository;
import com.esca.escahp.timetable.repository.TimeTableRepositorty;

import org.springframework.stereotype.Service;

@Service
public class TimeTableService {
    private final TimeTableRepositorty timeTableRepositorty;

    public TimeTableService(TimeTableRepositorty timeTableRepositorty) {
        this.timeTableRepositorty = timeTableRepositorty;
    }
}