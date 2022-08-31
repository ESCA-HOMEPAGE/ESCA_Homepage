package com.esca.escahp.timetable;

import com.esca.escahp.calendar.repository.CalendarRepository;
import com.esca.escahp.timetable.repository.TimeTableRepositorty;

import org.springframework.stereotype.Service;

@Service
public class TimeTableService implements I_TimeTableService {
    private final CalendarRepository calendarRepository;
    private final TimeTableRepositorty timeTableRepositorty;

    public TimeTableService(CalendarRepository keeperRepository, TimeTableRepositorty timeTableRepositorty) {
        this.calendarRepository = keeperRepository;
        this.timeTableRepositorty = timeTableRepositorty;
    }
}