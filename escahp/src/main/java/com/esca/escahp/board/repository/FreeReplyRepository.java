package com.esca.escahp.board.repository;

import com.esca.escahp.board.entity.FreeReply;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreeReplyRepository extends JpaRepository<FreeReply, Long> {

    List<FreeReply> findAllByFreeBoardId(Long boardId);
}
