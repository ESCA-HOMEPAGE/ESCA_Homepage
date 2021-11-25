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
        return noticeBoardDao.selectNoticeBoard(id);
    }

}
