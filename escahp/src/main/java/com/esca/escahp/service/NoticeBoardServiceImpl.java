package com.esca.escahp.service;

import com.esca.escahp.dto.NoticeBoardDto;
import com.esca.escahp.mapper.NoticeBoardDao;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeBoardServiceImpl implements I_NoticeBoardService {

    private final NoticeBoardDao noticeBoardDao;

    @Override
    public List<NoticeBoardDto> selectNoticeBoardList(){
        return noticeBoardDao.selectNoticeBoardList();
    }

    @Override
    public NoticeBoardDto selectNoticeBoard(Long id){
        //noticeBoardDao.updateViewCount(id);
        return noticeBoardDao.selectNoticeBoard(id);
    }

    @Override
    public boolean insertNoticeBoard(NoticeBoardDto params){
        int queryCount = 0;

        if(params.getId() == 0){
            queryCount = noticeBoardDao.insertNoticeBoard(params);
        } else{
            queryCount = noticeBoardDao.insertNoticeBoard(params);
            //queryCount = noticeBoardDao.updateNoticeBoard(params);
        }
        // 정상 실행이면 1
        return (queryCount == 1) ? true : false;
    }

    @Override
    public void updateNoticeBoard(NoticeBoardDto params){
        noticeBoardDao.updateNoticeBoard(params);
    }

    @Override
    public void deleteNoticeBoard(NoticeBoardDto params){
        noticeBoardDao.deleteNoticeBoard(params);
    }

    @Override
    public void updateViewCount(Long id){
        noticeBoardDao.updateViewCount(id);
    }
}
