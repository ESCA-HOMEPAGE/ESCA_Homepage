package com.esca.escahp.keeper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esca.escahp.keeper.entity.ExTimeTable;

@Repository
public class ExTimeTableRepositorty extends JpaRepository<ExTimeTable, Long>{
    
}
