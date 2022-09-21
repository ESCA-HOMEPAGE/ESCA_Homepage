package com.esca.escahp.schedule.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleTest {

    @Test
    @DisplayName("연간일정은 업데이트될 수 있다.")
    void update() {
        LocalDateTime now = LocalDateTime.now();
        Schedule schedule = new Schedule("title", "tag", "content", now, now.plusDays(1));
        String updatedTitle = "updatedTitle";
        String updatedTag = "otherTag";
        String updatedContent = "updatedContent";
        schedule.update(updatedTitle, updatedTag, updatedContent, now, now.plusDays(1));
        assertAll(
                () -> assertEquals(schedule.getTitle(),updatedTitle),
                () -> assertEquals(schedule.getTag(),updatedTag),
                () -> assertEquals(schedule.getContent(),updatedContent)
        );
    }
}