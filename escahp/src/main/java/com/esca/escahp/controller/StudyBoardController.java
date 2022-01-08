package com.esca.escahp.controller;

import com.esca.escahp.dto.StudyBoardDto;
import com.esca.escahp.service.I_StudyBoardService;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class StudyBoardController {

	private final I_StudyBoardService studyBoardService;

	@GetMapping
	public ResponseEntity<List<StudyBoardDto>> getAllStudyBoard() {
		List<StudyBoardDto> studyBoard = studyBoardService.getStudyBoardList();
		return new ResponseEntity<>(studyBoard, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StudyBoardDto> getStudyBoardById(@PathVariable long id){
		StudyBoardDto studyBoardDto = studyBoardService.selectStudyBoard(id);
		if(studyBoardDto == null){
			return ResponseEntity.noContent().build();
		}

		studyBoardService.updateViewCnt(id);
		return ResponseEntity.ok().body(studyBoardDto);
	}

	@PostMapping
	public ResponseEntity<StudyBoardDto> insertStudyBoard(@RequestBody StudyBoardDto studyBoardDto){
		studyBoardService.addBoard(studyBoardDto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(studyBoardDto.getId())
					.toUri();

		return ResponseEntity.created(location).body(studyBoardDto);

	}

	@PutMapping("/{id}")
	public ResponseEntity<StudyBoardDto> updateStudyBoard(@PathVariable Long id, @RequestBody StudyBoardDto studyBoardDto){
		studyBoardDto.setId(id);
		studyBoardService.updateBoard(studyBoardDto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.buildAndExpand()
					.toUri();

		return ResponseEntity.created(location).body(studyBoardDto);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Object> deleteAction(@PathVariable Long id, @RequestBody StudyBoardDto studyBoardDto){
		studyBoardDto.setId(id);
		studyBoardService.deleteBoard(studyBoardDto);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.buildAndExpand()
					.toUri();

		return ResponseEntity.created(location).build();

	}

}