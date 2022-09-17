package com.esca.escahp.schedule;

import com.esca.escahp.exception.EscaException;
import com.esca.escahp.schedule.dto.ScheduleRequest;
import com.esca.escahp.schedule.dto.ScheduleResponse;
import com.esca.escahp.schedule.entity.Schedule;
import com.esca.escahp.schedule.exceptions.ScheduleExceptions;
import com.esca.escahp.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService implements I_ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<ScheduleResponse> getScheduleList() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream().map(ScheduleResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleResponse selectSchedule(long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new EscaException(ScheduleExceptions.NOT_FOUND_SCHEDULE));
        return new ScheduleResponse(schedule);
    }

    @Transactional
    @Override
    public ScheduleResponse addSchedule(ScheduleRequest request) {
        Schedule schedule = new Schedule(request.getTitle(), request.getTag(), request.getContent(), request.getStartDate(), request.getStartDate());
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponse(savedSchedule);
    }

    @Transactional
    @Override
    public ScheduleResponse updateSchedule(long id, ScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new EscaException(ScheduleExceptions.NOT_FOUND_SCHEDULE));
        schedule.update(request.getTitle(), request.getTag(), request.getContent(), request.getStartDate(), request.getEndDate());
        return new ScheduleResponse(schedule);
    }

    @Transactional
    @Override
    public void deleteSchedule(long id) {
        scheduleRepository.findById(id)
                .orElseThrow(() -> new EscaException(ScheduleExceptions.NOT_FOUND_SCHEDULE));
        scheduleRepository.deleteById(id);
    }
}
