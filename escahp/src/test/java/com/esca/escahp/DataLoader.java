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


    private final StudyRepository studyRepository;

    public DataLoader(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    @Override
    public void run(String... args){
        studyRepository.save(board1);
    }
}
