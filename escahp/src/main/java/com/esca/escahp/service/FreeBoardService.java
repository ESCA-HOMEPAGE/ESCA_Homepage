package com.esca.escahp.service;

import com.esca.escahp.domain.FreeBoard;
import com.esca.escahp.dto.FreeBoardDto;
import com.esca.escahp.exception.EscaException;
import com.esca.escahp.mapper.FreeBoardDao;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FreeBoardService implements I_FreeBoardService {
    private final FreeBoardDao freeBoardDao;

    @Override
    @Transactional
    public FreeBoard getArticle(long no) {
        FreeBoard board = freeBoardDao.select(no);
        if (board == null) {
            // 에러 처리
        }
        board.addViewCount();
        freeBoardDao.update(board);
        // 블라인드 처리해줄 것
        // 신고 수 누적
        return board;
    }

    @Override
    public List<FreeBoard> getArticles() {
        List<FreeBoard> list = freeBoardDao.selectAll();
        for (FreeBoard board : list) {
            board.mosaicTitle();
        }
        return list;
    }

    @Override
    @Transactional
    public void writeArticle(FreeBoardDto dto) {
        FreeBoard board = new FreeBoard(dto.getTitle(), dto.getContent(), dto.getWriter(), dto.getFile());
        freeBoardDao.insert(board);
    }

    @Override
    @Transactional
    public void modifyArticle(long id, FreeBoardDto dto) {
        FreeBoard board = freeBoardDao.select(id);
        board.update(dto);
        freeBoardDao.update(board);
    }

    @Override
    @Transactional
    public void deleteArticle(long id) {
        FreeBoard board = freeBoardDao.select(id);
        board.delete();
        freeBoardDao.update(board);
    }

    @Override
    @Transactional
    public void updateReport(long id) {
        FreeBoard board = freeBoardDao.select(id);
        board.updateReport();
        freeBoardDao.update(board);
    }
}
