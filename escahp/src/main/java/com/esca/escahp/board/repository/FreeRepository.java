package com.esca.escahp.board.repository;

import com.esca.escahp.board.entity.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreeRepository extends JpaRepository<FreeBoard, Long> {
}
