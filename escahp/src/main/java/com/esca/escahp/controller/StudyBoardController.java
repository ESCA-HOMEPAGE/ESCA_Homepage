package com.esca.escahp.controller;

import com.esca.escahp.dto.StudyBoardDto;
import com.esca.escahp.service.I_StudyBoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/study")
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})        // 외부에서도 접속이 가능하게 해주는 어노테이션
@Api(value = "StudyBoardDto")		// 스웨거 설정
public class StudyBoardController {

	private final I_StudyBoardService studyBoardService;

	@ApiOperation(value = "스터디 게시판의 전체 목록 보여주기")
	@GetMapping
	public ResponseEntity<List<StudyBoardDto>> getAllStudyBoard() {
		List<StudyBoardDto> studyBoard = studyBoardService.getStudyBoardList();
		return ResponseEntity.ok().body(studyBoard);
	}

	@ApiOperation(value = "id에 해당하는 게시물 정보 반환")
	@GetMapping("/{id}")
	public ResponseEntity<StudyBoardDto> getStudyBoardById(@PathVariable long id) {
		StudyBoardDto studyBoardDto = studyBoardService.selectStudyBoard(id);
		if (studyBoardDto == null) {
			return ResponseEntity.noContent().build();
		}

		studyBoardService.updateViewCnt(id);
		return ResponseEntity.ok().body(studyBoardDto);
	}

	@ApiOperation(value = "게시물 객체 추가")
	@PostMapping
	public ResponseEntity<StudyBoardDto> insertStudyBoard(
		@RequestBody StudyBoardDto studyBoardDto) {
		studyBoardService.addBoard(studyBoardDto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(studyBoardDto.getId())
			.toUri();

		return ResponseEntity.created(location).build();

	}

	@ApiOperation(value = "id에 해당하는 게시물 정보 수정")
	@PutMapping("/{id}")
	public ResponseEntity<StudyBoardDto> updateStudyBoard(@PathVariable Long id,
		@RequestBody StudyBoardDto studyBoardDto) {
		studyBoardDto.setId(id);
		studyBoardService.updateBoard(studyBoardDto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.buildAndExpand()
			.toUri();

		return ResponseEntity.created(location).build();
	}

	@ApiOperation(value = "id에 해당하는 게시물 정보 삭제")
	@PatchMapping("/{id}")
	public ResponseEntity<Object> deleteAction(@PathVariable Long id,
		@RequestBody StudyBoardDto studyBoardDto) {
		studyBoardDto.setId(id);
		studyBoardService.deleteBoard(studyBoardDto);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.buildAndExpand()
			.toUri();

		return ResponseEntity.created(location).build();

	}
}