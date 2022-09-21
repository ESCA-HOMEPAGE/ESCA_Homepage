package com.esca.escahp.schedule;

import com.esca.escahp.schedule.dto.ScheduleRequest;
import com.esca.escahp.schedule.dto.ScheduleResponse;
import com.esca.escahp.schedule.entity.Schedule;
import com.esca.escahp.schedule.repository.ScheduleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@ActiveProfiles("test")
class ScheduleServiceTest {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired ScheduleRepository scheduleRepository;

    @BeforeEach
    void before() {
        Schedule schedule1 = new Schedule("scheduleTitle1", "tag", "scheduleContent1", LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        Schedule schedule2 = new Schedule("scheduleTitle2", "tag", "scheduleContent2", LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        Schedule schedule3 = new Schedule("scheduleTitle3", "tag", "scheduleContent3", LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        scheduleRepository.save(schedule1);
        scheduleRepository.save(schedule2);
        scheduleRepository.save(schedule3);
    }

    @AfterEach
    void after() {
        scheduleRepository.deleteAll();
    }

    @Test
    @DisplayName("연간일정들을 가져온다.")
    void getScheduleList() {
        // when
        List<ScheduleResponse> schedules = scheduleService.getScheduleList();

        // then
        assertEquals(schedules.size(), 3);
    }

    @Test
    @DisplayName("연간일정을 하나 가져온다.")
    void selectSchedule() {
        // when
        ScheduleResponse schedule = scheduleService.getScheduleList().get(0);
        ScheduleResponse selectedSchedule = scheduleService.selectSchedule(schedule.getId());

        // then
        assertEquals(schedule.getTitle(), selectedSchedule.getTitle());
    }

    @Test
    @DisplayName("연간일정을 추가한다.")
    void addSchedule() {
        // when
        ScheduleRequest request = new ScheduleRequest("title", "tag", "content", LocalDateTime.now(), LocalDateTime.now());
        ScheduleResponse response = scheduleService.addSchedule(request);
        System.out.println(response.getId() +  response.getTitle() + response.getTag() + response.getContent());

        // then
        assertAll(
                () -> assertNotNull(response.getId()),
                () -> assertEquals(response.getTitle(), request.getTitle()),
                () -> assertEquals(response.getTag(), request.getTag()),
                () -> assertEquals(response.getContent(), request.getContent())
        );
    }

    @Test
    @DisplayName("연간일정을 수정한다.")
    void updateSchedule() {
        // when
        ScheduleResponse schedule = scheduleService.getScheduleList().get(0);
        String updatedTitle = "updatedTitle";
        String updatedTag = "otherTag";
        String updatedContent = "updatedContent";
        ScheduleRequest updateRequest = new ScheduleRequest(updatedTitle, updatedTag, updatedContent, LocalDateTime.now(), LocalDateTime.now());
        ScheduleResponse response = scheduleService.updateSchedule(schedule.getId(), updateRequest);

        // then
        assertAll(
                () -> assertEquals(schedule.getId(), response.getId()),
                () -> assertEquals(response.getTitle(), updatedTitle),
                () -> assertEquals(response.getTag(), updatedTag),
                () -> assertEquals(response.getContent(), updatedContent)
        );
    }

    @Test
    @DisplayName("연간일정을 삭제한다.")
    void deleteSchedule() {
        // when
        ScheduleResponse schedule = scheduleService.getScheduleList().get(0);
        scheduleService.deleteSchedule(schedule.getId());

        // then
        assertEquals(scheduleService.getScheduleList().size(), 2);
    }
}