package com.esca.escahp.service;

import com.esca.escahp.dto.FreeBoardDto;
import java.util.List;


public interface I_FreeBoardService {
    public FreeBoardDto getArticle(long no);
    public List<FreeBoardDto> getArticles();
    public long writeArticle(FreeBoardDto dto);
    public long modifyArticle(FreeBoardDto dto);
    public long deleteArticle(long no);
}
