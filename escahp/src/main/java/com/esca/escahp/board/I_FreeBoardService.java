package com.esca.escahp.board;

import com.esca.escahp.board.dto.FreeBoardDto;
import java.util.List;


public interface I_FreeBoardService {
    public FreeBoardDto getArticle(long no);
    public List<FreeBoardDto> getArticles();
    public void writeArticle(FreeBoardDto dto);
    public void modifyArticle(FreeBoardDto dto);
    public void deleteArticle(FreeBoardDto dto);
    public void updateReport(FreeBoardDto dto);
}
