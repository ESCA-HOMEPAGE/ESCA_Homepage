package com.esca.escahp.board;

import com.esca.escahp.board.dto.FreeReplyRequest;
import com.esca.escahp.board.dto.FreeReplyResponse;
import java.util.List;

public interface I_FreeReplyService {
    List<FreeReplyResponse> getFreeReplyList(long postId);

    Long postFreeReply(long postId, long userId, FreeReplyRequest fr);

    void deleteFreeReply(Long id, long userId);
}
