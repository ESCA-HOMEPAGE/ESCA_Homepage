package com.esca.escahp.service;

import com.esca.escahp.domain.FreeBoard;
import com.esca.escahp.dto.FreeBoardDto;
import java.util.List;

public interface I_FreeBoardService {
    FreeBoard getArticle(long id);
    List<FreeBoard> getArticles();
    void writeArticle(FreeBoardDto dto);
    void modifyArticle(long id, FreeBoardDto dto);
    void deleteArticle(long id);
    void updateReport(long id);
}
