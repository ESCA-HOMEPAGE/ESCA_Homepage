package com.esca.escahp.schedule;

import com.esca.escahp.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService implements I_ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }
}
