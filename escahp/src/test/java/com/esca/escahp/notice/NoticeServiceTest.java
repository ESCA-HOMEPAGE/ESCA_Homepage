package com.esca.escahp.notice;

import com.esca.escahp.notice.dto.NoticeRequest;
import com.esca.escahp.notice.dto.NoticeResponse;
import com.esca.escahp.notice.entity.Notice;
import com.esca.escahp.notice.repository.NoticeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest()
@ActiveProfiles("test")
class NoticeServiceTest {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeRepository noticeRepository;

    @BeforeEach
    void before() {
        Notice notice1 = new Notice("title", "category", "writer", "content", "file");
        Notice notice2 = new Notice("title2", "category2", "writer2", "content2", "file2");
        Notice notice3 = new Notice("title3", "category3", "writer3", "content3", "file3");
        noticeRepository.save(notice1);
        noticeRepository.save(notice2);
        noticeRepository.save(notice3);
    }

    @AfterEach
    void after() {
        noticeRepository.deleteAll();
    }

    @Test
    void getNoticeList() {
        // when
        List<NoticeResponse> notices = noticeService.getNoticeList();

        // then
        assertEquals(notices.size(), 3);
    }

    @Test
    void selectNotice() {
        // when
        NoticeResponse notice = noticeService.getNoticeList().get(0);
        NoticeResponse selectedNotice = noticeService.selectNotice(notice.getId());

        // then
        assertEquals(notice.getTitle(), selectedNotice.getTitle());
    }

    @Test
    void insertNotice() {
        // when
        NoticeRequest request = new NoticeRequest("titlee", "category", "writer", "content", "file");
        Long noticeId = noticeService.insertNotice(request);

        // then
        NoticeResponse noticeResponse = noticeService.selectNotice(noticeId);
        assertEquals(noticeResponse.getTitle(), request.getTitle());
    }

    @Test
    void updateNotice() {
        // when
        NoticeResponse notice = noticeService.getNoticeList().get(0);
        String updatedTitle = "updatedTitle";
        NoticeRequest request = new NoticeRequest(updatedTitle, notice.getCategory(), notice.getWriter(), notice.getWriter(), notice.getFile());
        NoticeResponse response = noticeService.updateNotice(notice.getId(), request);

        // then
        assertEquals(response.getTitle(), updatedTitle);
    }

    @Test
    void deleteNotice() {
        // when
        NoticeResponse notice = noticeService.getNoticeList().get(0);
        noticeService.deleteNotice(notice.getId());

        // then
        assertEquals(noticeService.getNoticeList().size(), 2);
    }

    @Test
    void updateViewCount() {
        // when
        NoticeResponse notice = noticeService.getNoticeList().get(0);
        noticeService.updateViewCount(notice.getId());
        NoticeResponse updatedNotice = noticeService.selectNotice(notice.getId());

        // then
        assertEquals(updatedNotice.getViewCnt(), notice.getViewCnt() + 1);
    }
}