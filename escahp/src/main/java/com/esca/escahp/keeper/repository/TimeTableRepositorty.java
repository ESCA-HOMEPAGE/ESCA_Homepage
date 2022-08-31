package com.esca.escahp.keeper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esca.escahp.keeper.entity.TimeTable;

@Repository
public interface TimeTableRepositorty extends JpaRepository<TimeTable, Long>{
    
}
