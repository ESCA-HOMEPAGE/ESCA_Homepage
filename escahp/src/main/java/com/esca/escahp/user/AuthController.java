package com.esca.escahp.user;

import com.esca.escahp.user.dto.AuthRequest;
import com.esca.escahp.user.dto.AuthResponse;
import com.esca.escahp.user.dto.ChangePasswordRequest;
import com.esca.escahp.user.dto.FindRequest;
import com.esca.escahp.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"User"})
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"*"})
public class AuthController {

	private final AuthService authService;

	private final String SUCCESS = "SUCCESS";

	public AuthController(AuthService authService) { this.authService = authService; }

	@ApiOperation(value = "회원가입")
	@PostMapping
	public ResponseEntity<AuthResponse> signUp(@RequestBody AuthRequest request) {
		AuthResponse result = new AuthResponse(
			authService.addUser(
				new User(request)
			)
		);
		return ResponseEntity.ok().body(result);
	}

	@ApiOperation(value = "아이디 찾기")
	@GetMapping("/find-id")
	public ResponseEntity<String> findId(@RequestParam("name") String name, @RequestParam("email") String email) {
		String result = authService.findUserId(name, email);
		return ResponseEntity.ok().body(result);
	}

	@ApiOperation(value = "사용자 자의에 의한 비밀번호 변경")
	@PutMapping("/change-password")
	public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
		authService.resetPassword(
					request.getUserId(),
					request.getOldPassword(),
					request.getOldPassword()
				);

		return ResponseEntity.ok().body("");
	}

	@ApiOperation(value = "분실한 비밀번호 재설정")
	@PutMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody String userId) {
		authService.resetPassword(userId);
		return ResponseEntity.ok().body("");
	}

	@ApiOperation(value = "회원탈퇴")
	@DeleteMapping
	public ResponseEntity<String> deleteUser(@RequestBody Long id){
		authService.deleteUser(id);
		return ResponseEntity.ok().body(SUCCESS);
	}
}
