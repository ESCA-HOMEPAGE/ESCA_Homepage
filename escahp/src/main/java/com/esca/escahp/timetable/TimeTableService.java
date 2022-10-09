package com.esca.escahp.timetable;

import com.esca.escahp.schedule.repository.ScheduleRepository;
import com.esca.escahp.timetable.repository.TimeTableRepositorty;

import org.springframework.stereotype.Service;

@Service
public class TimeTableService implements I_TimeTableService {
    private final ScheduleRepository scheduleRepository;
    private final TimeTableRepositorty timeTableRepositorty;

    public TimeTableService(ScheduleRepository keeperRepository, TimeTableRepositorty timeTableRepositorty) {
        this.scheduleRepository = keeperRepository;
        this.timeTableRepositorty = timeTableRepositorty;
    }
}