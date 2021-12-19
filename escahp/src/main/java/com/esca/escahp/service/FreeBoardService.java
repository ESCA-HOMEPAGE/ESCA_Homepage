package com.esca.escahp.service;

import com.esca.escahp.dto.FreeBoardDto;
import com.esca.escahp.mapper.FreeBoardDao;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FreeBoardService implements I_FreeBoardService {
    private final FreeBoardDao freeBoardDao;

    @Override
    public FreeBoardDto getArticle(long no) {
        FreeBoardDto dto = freeBoardDao.select(no);
        // 블라이드 처리해줄 것
        // 신고 수 누적
        if (dto != null) {
            freeBoardDao.updateViewCnt(no);
            dto.setViewCnt(dto.getViewCnt()+1);
        }
        return dto;
    }

    @Override
    public List<FreeBoardDto> getArticles() {
        List<FreeBoardDto> list = freeBoardDao.selectAll();
        for (FreeBoardDto dto : list) {
            if (dto.getReport() >= 5) {
                dto.setTitle("블라인드된 글입니다.");
            }
        }
        return list;
    }

    @Override
    public long writeArticle(FreeBoardDto dto) {
        freeBoardDao.insert(dto);
        return dto.getId();
    }

    @Override
    public int modifyArticle(FreeBoardDto dto) {
        return freeBoardDao.update(dto);
    }

    @Override
    public int deleteArticle(FreeBoardDto dto) {
        return freeBoardDao.delete(dto);
    }

    @Override
    public void updateReport(FreeBoardDto dto) {
        freeBoardDao.updateReport(dto);
    }
}
