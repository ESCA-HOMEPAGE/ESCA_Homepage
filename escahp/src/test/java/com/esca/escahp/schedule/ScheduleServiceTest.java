package com.esca.escahp.schedule;

import com.esca.escahp.schedule.repository.ScheduleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@ActiveProfiles("test")
class ScheduleServiceTest {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Test
    @DisplayName("연간일정들을 가져온다.")
    void getScheduleList() {
    }

    @Test
    @DisplayName("연간일정을 하나 가져온다.")
    void selectSchedule() {
    }

    @Test
    @DisplayName("연간일정을 추가한다.")
    void addSchedule() {
    }

    @Test
    @DisplayName("연간일정을 수정한다.")
    void updateSchedule() {
    }

    @Test
    @DisplayName("연간일정을 삭제한다.")
    void deleteSchedule() {
    }
}