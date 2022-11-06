package com.esca.escahp.notice;

import com.esca.escahp.common.exceptions.BoardExceptions;
import com.esca.escahp.exception.EscaException;
import com.esca.escahp.notice.dto.NoticeRequest;
import com.esca.escahp.notice.dto.NoticeResponse;
import com.esca.escahp.notice.entity.Notice;
import com.esca.escahp.notice.repository.NoticeRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoticeService implements I_NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoticeResponse> getNoticeList(){
        return noticeRepository.findAll()
                .stream()
                .map(NoticeResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public NoticeResponse selectNotice(Long id){
        Notice notice = noticeRepository.findById(id)
            .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));
        return new NoticeResponse(notice);
    }

    @Override
    @Transactional
    public Long insertNotice(NoticeRequest notice){
        return noticeRepository.save(notice.toEntity()).getId();
    }

    @Override
    @Transactional
    public NoticeResponse updateNotice(Long id, NoticeRequest noticeBoard){
        Notice notice = noticeRepository.findById(id)
            .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));
        notice.update(noticeBoard.getTitle(), noticeBoard.getContent(),
            noticeBoard.getFile(), noticeBoard.getCategory());
        return new NoticeResponse(notice);
    }

    @Override
    @Transactional
    public void deleteNotice(Long id){
        Notice notice = noticeRepository.findById(id)
            .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));
        noticeRepository.delete(notice);
    }

    @Override
    @Transactional
    public void updateViewCount(Long id){
        Notice notice = noticeRepository.findById(id)
            .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));
        notice.updateViewCnt();
    }
}
