package com.esca.escahp.notice;

import com.esca.escahp.notice.dto.NoticeRequest;
import com.esca.escahp.notice.dto.NoticeResponse;
import java.util.List;

public interface I_NoticeService {
    List<NoticeResponse> getNoticeList();

    NoticeResponse selectNotice(Long id);

    Long insertNotice(NoticeRequest notice);

    NoticeResponse updateNotice(Long id, NoticeRequest noticeBoard);

    void deleteNotice(Long id);

    void updateViewCount(Long id);
}