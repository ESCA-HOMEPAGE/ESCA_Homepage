package com.esca.escahp.notice.repository;

import com.esca.escahp.notice.entity.NoticeBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeBoard, Long> {
    List<NoticeBoard> findByDeleteYn(String yn);
}