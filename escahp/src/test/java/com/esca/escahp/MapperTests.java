package com.esca.escahp;

import com.esca.escahp.dto.NoticeBoardDto;
import com.esca.escahp.mapper.NoticeBoardDao;

import java.util.List;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

@SpringBootTest
public class MapperTests {
    @Autowired
    private NoticeBoardDao noticeBoardDao;

    @Test
    public void testSelectList(){
        int boardTotalCount = noticeBoardDao.selectNoticeBoardTotal();
        if(boardTotalCount > 0){
            List<NoticeBoardDto> noticeBoardList = noticeBoardDao.selectNoticeBoardList();
            if(!CollectionUtils.isEmpty(noticeBoardList)){
                for(NoticeBoardDto board : noticeBoardList){
                    System.out.println("==================================");
                    System.out.println(board.getTitle());
                    System.out.println(board.getWriter());
                    System.out.println(board.getCreatedAt());
                    System.out.println("==================================");
                }
            }
        }
    }

}
