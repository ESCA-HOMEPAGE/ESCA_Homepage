package com.esca.escahp.keeper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esca.escahp.keeper.entity.Calendar;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {

}