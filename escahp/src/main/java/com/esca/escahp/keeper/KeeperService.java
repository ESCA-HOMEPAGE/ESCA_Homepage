package com.esca.escahp.keeper;

import com.esca.escahp.keeper.repository.CalendarRepository;
import com.esca.escahp.keeper.repository.TimeTableRepositorty;

import org.springframework.stereotype.Service;

@Service
public class KeeperService implements I_KeeperService{
    private final CalendarRepository calendarRepository;
    private final TimeTableRepositorty timeTableRepositorty;

    public KeeperService(CalendarRepository keeperRepository, TimeTableRepositorty timeTableRepositorty) {
        this.calendarRepository = keeperRepository;
        this.timeTableRepositorty = timeTableRepositorty;
    }
}