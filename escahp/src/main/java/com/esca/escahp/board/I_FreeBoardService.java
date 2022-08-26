package com.esca.escahp.board;

import com.esca.escahp.board.dto.FreeRequest;
import com.esca.escahp.board.dto.FreeResponse;
import java.util.List;


public interface I_FreeBoardService {
    List<FreeResponse> getFreeBoardList();
    FreeResponse selectFreeBoard(long id);
    Long postBoard(FreeRequest f);
    void updateBoard(Long id, FreeRequest f);
    void deleteBoard(Long id);
    void increaseViewCnt(long id);

    // 좋아요, 신고는 회원정보와 같이
    //public void updateReport(long id);
}
