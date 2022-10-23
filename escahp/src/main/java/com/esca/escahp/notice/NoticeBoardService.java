package com.esca.escahp.notice;

import com.esca.escahp.common.exceptions.BoardExceptions;
import com.esca.escahp.exception.EscaException;
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
            .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));
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
            .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));
        notice.update(noticeBoard.getTitle(), noticeBoard.getContent(),
            noticeBoard.getFile(), noticeBoard.getCategory());
    }

    @Override
    @Transactional
    public void deleteNoticeBoard(Long id){
        NoticeBoard notice = noticeRepository.findById(id)
            .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));
        noticeRepository.delete(notice);
    }

    @Override
    @Transactional
    public void updateViewCount(Long id){
        NoticeBoard notice = noticeRepository.findById(id)
            .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));
        notice.updateViewCnt();
    }
}
