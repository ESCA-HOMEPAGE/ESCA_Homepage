package com.esca.escahp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeBoardController {

    @GetMapping("/notice")
    public String projectInfo(){
        return "Project name";
    }
}