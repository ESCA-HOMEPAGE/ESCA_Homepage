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
        if (dto != null) {
            dto.setViewCnt(dto.getViewCnt() + 1);
        }
        return dto;
    }

    @Override
    public List<FreeBoardDto> getArticles() {
        return freeBoardDao.selectAll();
    }

    @Override
    public long writeArticle(FreeBoardDto dto) {
        freeBoardDao.insert(dto);
        return dto.getId();
    }

//    @Override
//    public long modifyArticle(FreeBoardDto dto) {
//        return freeBoardDao.update(dto);
//    }
//
//    @Override
//    public long deleteArticle(long no) {
//        return freeBoardDao.delete(no);
//    }
}
