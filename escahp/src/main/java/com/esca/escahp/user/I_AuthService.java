package com.esca.escahp.user;

import com.esca.escahp.user.entity.User;

public interface I_AuthService {

	// 회원가입 여부 확인
	public User checkUserId(String userId);

	// 회원가입
	public User addUser(User auth);

	// 회원가입 인증
	public void sendEmail(String email, String code, String message);

	// 비밀번호 변경
	public boolean resetPassword(String userId, String oldPassword, String password);

	// 비밀번호 초기화
	public boolean resetPassword(String userId);

	// 랜덤 비밀번호 생성
	public String randomPassword();

	// 회원 탈퇴
	public void deleteUser(Long id);

	// 아이디 찾기
	public String findUserId(String name, String email);


}
