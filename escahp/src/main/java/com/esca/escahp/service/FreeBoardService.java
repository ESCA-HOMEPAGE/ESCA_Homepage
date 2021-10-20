package com.esca.escahp.service;

import com.esca.escahp.dto.FreeBoardDto;
import com.esca.escahp.mapper.FreeBoardDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class FreeBoardService implements I_FreeBoardService {
    @Autowired
    private FreeBoardDao dao;

    @Override
    public FreeBoardDto getArticle(long no) {
        return dao.select(no);
    }

    @Override
    public List<FreeBoardDto> getArticles() {
        return dao.selectAll();
    }

    @Override
    public long writeArticle(FreeBoardDto dto) {
        return dao.insert(dto);
    }

    @Override
    public long modifyArticle(FreeBoardDto dto) {
        return dao.update(dto);
    }

    @Override
    public long deleteArticle(long no) {
        return dao.delete(no);
    }
}
