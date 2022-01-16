package com.esca.escahp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudyBoardController {
	@GetMapping("/study")
	public String projectInfo(){
		return "Project Name is Preword.";
	}
}