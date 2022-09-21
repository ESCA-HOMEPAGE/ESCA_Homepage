package com.esca.escahp;

import com.esca.escahp.schedule.entity.Schedule;
import com.esca.escahp.schedule.repository.ScheduleRepository;
import com.esca.escahp.study.entity.StudyBoard;
import com.esca.escahp.study.repository.StudyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Profile("test")
public class DataLoader implements CommandLineRunner {
    public static final StudyBoard board1 = new StudyBoard("title", "content", "writer", "category", "file");

    public static final Schedule schedule1 = new Schedule("scheduleTitle1", "tag", "scheduleContent1", LocalDateTime.now(), LocalDateTime.now().plusDays(1));
    public static final Schedule schedule2 = new Schedule("scheduleTitle2", "tag", "scheduleContent2", LocalDateTime.now(), LocalDateTime.now().plusDays(1));
    public static final Schedule schedule3 = new Schedule("scheduleTitle3", "tag", "scheduleContent3", LocalDateTime.now(), LocalDateTime.now().plusDays(1));

    private final StudyRepository studyRepository;
    private final ScheduleRepository scheduleRepository;

    public DataLoader(StudyRepository studyRepository, ScheduleRepository scheduleRepository) {
        this.studyRepository = studyRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public void run(String... args){
        studyRepository.save(board1);
        scheduleRepository.save(schedule1);
        scheduleRepository.save(schedule2);
        scheduleRepository.save(schedule3);
    }
}
