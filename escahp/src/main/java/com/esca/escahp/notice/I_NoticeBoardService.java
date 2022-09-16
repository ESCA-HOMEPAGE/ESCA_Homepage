package com.esca.escahp.notice;

import com.esca.escahp.notice.dto.NoticeRequest;
import com.esca.escahp.notice.dto.NoticeResponse;
import java.util.List;

public interface I_NoticeBoardService {
    List<NoticeResponse> getNoticeBoardList();

    NoticeResponse selectNoticeBoard(Long id);

    Long insertNoticeBoard(NoticeRequest notice);

    void updateNoticeBoard(Long id, NoticeRequest noticeBoard);

    void deleteNoticeBoard(Long id);

    void updateViewCount(Long id);
}