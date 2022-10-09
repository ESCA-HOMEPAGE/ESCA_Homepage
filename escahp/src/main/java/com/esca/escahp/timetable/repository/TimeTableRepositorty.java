package com.esca.escahp.timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esca.escahp.timetable.entity.TimeTable;

@Repository
public interface TimeTableRepositorty extends JpaRepository<TimeTable, Long>{
    
}
