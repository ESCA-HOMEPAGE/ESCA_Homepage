package com.esca.escahp.study.repository;

import com.esca.escahp.study.entity.StudyReply;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyReplyRepository extends JpaRepository<StudyReply, Long> {

    List<StudyReply> findByStudyBoardIdAndDeleteYn(Long boardId, String yn);
}
