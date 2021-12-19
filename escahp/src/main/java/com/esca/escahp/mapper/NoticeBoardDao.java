package com.esca.escahp.mapper;

import com.esca.escahp.dto.NoticeBoardDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

@Mapper
public interface NoticeBoardDao {
    // 게시글 생성
    public Integer insertNoticeBoard(NoticeBoardDto params);

    // 게시글 조회
    public NoticeBoardDto selectNoticeBoard(Long id);

    // 게시글 수정
    public int updateNoticeBoard(NoticeBoardDto params);

    // 게시글 삭제
    public int deleteNoticeBoard(NoticeBoardDto params);

    // 게시글 목록 조회
    public List<NoticeBoardDto> selectNoticeBoardList();

    // 게시글 조회수 카운트
    public void updateViewCount(Long id);

    /* 삭제 게시글 조회 => 페이징 처리에 필요 */
    public int selectNoticeBoardTotal();

}