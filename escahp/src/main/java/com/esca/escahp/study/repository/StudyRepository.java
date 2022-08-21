package com.esca.escahp.study.repository;

import com.esca.escahp.study.entity.StudyBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRepository extends JpaRepository<StudyBoard, Long> {

}
