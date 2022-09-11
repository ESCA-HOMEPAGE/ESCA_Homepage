package com.esca.escahp;

import com.esca.escahp.study.entity.StudyBoard;
import com.esca.escahp.study.repository.StudyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class DataLoader implements CommandLineRunner {

    private final StudyRepository studyRepository;

    public DataLoader(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    @Override
    public void run(String... args){
        StudyBoard board = new StudyBoard("title", "content", "writer", "category", "file");
        studyRepository.save(board);
    }
}
