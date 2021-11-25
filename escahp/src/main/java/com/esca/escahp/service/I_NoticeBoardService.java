package com.esca.escahp.service;

import com.esca.escahp.dto.NoticeBoardDto;
import com.esca.escahp.mapper.NoticeBoardDao;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

public interface I_NoticeBoardService {

    public List<NoticeBoardDto> selectNoticeBoardList();

    public  NoticeBoardDto selectNoticeBoard(Long id);
}