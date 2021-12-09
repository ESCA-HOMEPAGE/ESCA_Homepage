package com.esca.escahp.service;

import com.esca.escahp.dto.StudyBoardDto;
import com.esca.escahp.mapper.StudyBoardDao;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class StudyBoardServiceImpl implements I_StudyBoardService {
	@Autowired
	private StudyBoardDao SBDao;

	@Override
	public List<StudyBoardDto> getStudyBoardList() {
		return SBDao.getStudyBoardList();
	}

	public StudyBoardDto selectStudyBoard(Long id){
		return SBDao.selectStudyBoard(id);
	}

	public boolean addBoard(StudyBoardDto b){
		return SBDao.addBoard(b);
	}
	//@Override
	/*public StudyBoardDto getArticle(Long no){
		return SBDao.select(no);
	}
	 */

	
}
