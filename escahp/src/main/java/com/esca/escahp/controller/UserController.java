package com.esca.escahp.controller;

import com.esca.escahp.dto.UserDto;
import com.esca.escahp.service.I_UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final I_UserService userService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<UserDto> getUser(){
		return userService.getUserList();
	}
}
