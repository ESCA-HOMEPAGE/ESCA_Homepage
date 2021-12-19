package com.esca.escahp.controller;

import com.esca.escahp.dto.StudyBoardDto;
import com.esca.escahp.service.I_StudyBoardService;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class StudyBoardController {
	@Autowired
	I_StudyBoardService studyBoardService;

	@GetMapping("/study")
	List<StudyBoardDto> getStudy(){
		return studyBoardService.getStudyBoardList();
	}

	@GetMapping("/study/{id}")
	public StudyBoardDto selectStudyBoard(@PathVariable Long id){

		return studyBoardService.selectStudyBoard(id);
	}

	@PostMapping("/study")
	public String writeAction(@RequestBody final StudyBoardDto studyBoardDto){
		try{
			boolean isInsert = studyBoardService.addBoard(studyBoardDto);
			if(!isInsert){
				// 게시글 등록 실패
			}
		} catch (DataAccessException e){
			// 데이터 처리 과정 문제
		} catch (Exception e){
			// 시스템 문제
		}

		return "studyBoardDto Success";
	}

	@PutMapping("/study/{id}")
	public String updateAction(@PathVariable Long id, @RequestBody StudyBoardDto studyBoardDto){
		studyBoardDto.setId(id);
		studyBoardService.updateBoard(studyBoardDto);
		return "update";
	}

	@PatchMapping("/study/{id}")
	public String deleteAction(@PathVariable Long id, @RequestBody StudyBoardDto studyBoardDto){
		studyBoardDto.setId(id);
		if(studyBoardService.deleteBoard(studyBoardDto))
			return "success";
		else{
			return studyBoardDto.getDeleteYn();
		}

	}

}