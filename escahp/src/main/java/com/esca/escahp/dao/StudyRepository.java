package com.esca.escahp.dao;

import com.esca.escahp.domain.StudyBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRepository extends JpaRepository<StudyBoard, Long> {

}
