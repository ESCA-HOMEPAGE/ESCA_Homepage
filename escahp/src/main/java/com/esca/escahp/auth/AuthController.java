package com.esca.escahp.auth;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Login"})
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {"*"})
public class AuthController {
	
}
