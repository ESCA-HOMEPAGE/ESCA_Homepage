package com.esca.escahp.user;

import com.esca.escahp.user.dto.AuthResponse;
import com.esca.escahp.user.entity.User;

public interface I_AuthService {
	// 회원가입 여부 확인
	public AuthResponse isCheckUser(String userId);

	// 회원가입
	public void addUser(User auth);

	// 회원가입 인증
	public void validateUser(String userId);

	// 비밀번호 변경
	public void resetPassword(String userId, String password);

	// 비밀번호 일치 확인
	public boolean checkOldPassword(String userId);

	// 회원 탈퇴
	public void deleteUser(String userId);

	// 아이디 찾기
	public String findUserId(String email);

}
