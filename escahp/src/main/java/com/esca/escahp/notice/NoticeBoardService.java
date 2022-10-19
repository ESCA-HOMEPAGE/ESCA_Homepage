package com.esca.escahp.notice;

import com.esca.escahp.notice.dto.NoticeRequest;
import com.esca.escahp.notice.dto.NoticeResponse;
import com.esca.escahp.notice.entity.NoticeBoard;
import com.esca.escahp.notice.repository.NoticeRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoticeBoardService implements I_NoticeBoardService {

    private final NoticeRepository noticeRepository;

    public NoticeBoardService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoticeResponse> getNoticeBoardList(){
        return noticeRepository.findByDeleteYn("N")
                .stream()
                .map(NoticeResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public NoticeResponse selectNoticeBoard(Long id){
        NoticeBoard noticeBoard = noticeRepository.findById(id)
            .orElseThrow(() -> new IllegalAccessError("[id=" + id + "] 해당 게시글은 존재하지 않습니다."));
        return new NoticeResponse(noticeBoard);
    }

    @Override
    @Transactional
    public Long insertNoticeBoard(NoticeRequest notice){
        return noticeRepository.save(notice.toEntity()).getId();
    }

    @Override
    @Transactional
    public void updateNoticeBoard(Long id, NoticeRequest noticeBoard){
        NoticeBoard notice = noticeRepository.findById(id)
            .orElseThrow(() -> new IllegalAccessError("[id=" + id + "] 해당 게시글은 존재하지 않습니다."));
        notice.update(noticeBoard.getTitle(), noticeBoard.getContent(),
            noticeBoard.getFile(), noticeBoard.getCategory());
    }

    @Override
    @Transactional
    public void deleteNoticeBoard(Long id){
        NoticeBoard notice = noticeRepository.findById(id)
            .orElseThrow(() -> new IllegalAccessError("[id=" + id + "] 해당 게시글은 존재하지 않습니다."));
        notice.delete();
    }

    @Override
    @Transactional
    public void updateViewCount(Long id){
        NoticeBoard notice = noticeRepository.findById(id)
            .orElseThrow(() -> new IllegalAccessError("[id=" + id + "] 해당 게시글은 존재하지 않습니다."));
        notice.updateViewCnt();

    }
}
