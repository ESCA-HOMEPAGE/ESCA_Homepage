package com.esca.escahp.study;

import com.esca.escahp.study.dto.StudyReplyRequest;
import com.esca.escahp.study.dto.StudyReplyResponse;
import java.util.List;

public interface I_StudyReplyService {
    List<StudyReplyResponse> getStudyReplyList(long postId);

    Long postStudyReply(long postId, long userId, StudyReplyRequest sr);

    void deleteStudyReply(Long id, long userId);
}
