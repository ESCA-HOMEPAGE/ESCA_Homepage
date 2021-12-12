package com.esca.escahp.service;

import com.esca.escahp.dto.FreeBoardDto;
import java.util.List;


public interface I_FreeBoardService {
    public FreeBoardDto getArticle(long no);
    public List<FreeBoardDto> getArticles();
    public long writeArticle(FreeBoardDto dto);
    public int modifyArticle(FreeBoardDto dto);
    public int deleteArticle(FreeBoardDto dto);
    public void updateReport(FreeBoardDto dto);
}
