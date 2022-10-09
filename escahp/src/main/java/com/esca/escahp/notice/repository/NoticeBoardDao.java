package com.esca.escahp.notice.repository;

import com.esca.escahp.notice.dto.NoticeBoardDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeBoardDao {
    // 게시글 조회
    public NoticeBoardDto selectNoticeBoard(long id);

    // 게시글 생성
    public void insertNoticeBoard(NoticeBoardDto params);

    // 게시글 수정
    public void updateNoticeBoard(NoticeBoardDto params);

    // 게시글 삭제
    public void deleteNoticeBoard(NoticeBoardDto params);

    // 게시글 목록 조회
    public List<NoticeBoardDto> selectNoticeBoardList();

    // 게시글 조회수 카운트
    public void updateViewCount(long id);

    // TODO 삭제 게시글 조회 => 페이징 처리에 필요
    public int selectNoticeBoardTotal();

}