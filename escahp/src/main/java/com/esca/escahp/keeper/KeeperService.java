package com.esca.escahp.keeper;

import com.esca.escahp.keeper.repository.CalendarRepository;
import com.esca.escahp.keeper.repository.ExTimeTableRepositorty;

import org.springframework.stereotype.Service;

@Service
public class KeeperService implements I_KeeperService{
    private final CalendarRepository calendarRepository;
    private final ExTimeTableRepositorty exTimeTableRepositorty;

    public KeeperService(CalendarRepository keeperRepository, ExTimeTableRepositorty exTimeTableRepositorty) {
        this.calendarRepository = keeperRepository;
        this.exTimeTableRepositorty = exTimeTableRepositorty;
    }
}