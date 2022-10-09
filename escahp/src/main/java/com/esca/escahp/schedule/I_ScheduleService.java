package com.esca.escahp.schedule;

import com.esca.escahp.schedule.dto.ScheduleRequest;
import com.esca.escahp.schedule.dto.ScheduleResponse;

import java.util.List;

public interface I_ScheduleService {

    List<ScheduleResponse> getScheduleList();

    ScheduleResponse selectSchedule(long id);

    ScheduleResponse addSchedule(ScheduleRequest request);

    ScheduleResponse updateSchedule(long id, ScheduleRequest request);

    void deleteSchedule(long id);

}
