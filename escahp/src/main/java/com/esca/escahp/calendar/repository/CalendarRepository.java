package com.esca.escahp.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esca.escahp.calendar.entity.Calendar;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {

}