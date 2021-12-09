package com.esca.escahp.controller;

import com.esca.escahp.dto.StudyBoardDto;
import com.esca.escahp.service.I_StudyBoardService;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping("/study/write")
	public String writeAction(@RequestBody StudyBoardDto studyBoardDto){
		studyBoardService.addBoard(studyBoardDto);
		return "studyBoardDto";
	}

}