package com.esca.escahp.keeper;

import com.esca.escahp.keeper.repository.CalendarRepository;
import org.springframework.stereotype.Service;

@Service
public class KeeperService implements I_KeeperService{
    private final CalendarRepository calendarRepository;

    public KeeperService(CalendarRepository keeperRepository) {
        this.calendarRepository = keeperRepository;
    }
}