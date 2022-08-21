package com.esca.escahp.notice;

import com.esca.escahp.notice.dto.NoticeBoardDto;
import com.esca.escahp.notice.repository.NoticeBoardDao;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

public interface I_NoticeBoardService {

    // 게시글 목록 조회 서비스
    public List<NoticeBoardDto> selectNoticeBoardList();

    // 단일 게시글 조회 서비스
    public NoticeBoardDto selectNoticeBoard(long id);

    // 게시글 생성 서비스
    public void insertNoticeBoard(NoticeBoardDto params);

    // 게시글 수정 서비스
    public void updateNoticeBoard(NoticeBoardDto noticeBoardDto);

    // 게시글 삭제 서비스
    public void deleteNoticeBoard(NoticeBoardDto params);

    // 게시글 조회수 서비스
    public void updateViewCount(long id);
}