package com.esca.escahp.service;

import com.esca.escahp.domain.FreeBoard;
import com.esca.escahp.dto.FreeBoardDto;
import java.util.List;


public interface I_FreeBoardService {
    public FreeBoard getArticle(long id);
    public List<FreeBoard> getArticles();
    public void writeArticle(FreeBoardDto dto);
    public void modifyArticle(long id, FreeBoardDto dto);
    public void deleteArticle(long id);
    public void updateReport(long id);
}
