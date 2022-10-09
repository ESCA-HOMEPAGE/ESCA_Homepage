package com.esca.escahp.user.repository;

import com.esca.escahp.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/*
	* 회원 등급으로 회원 관리
	* rank
	* 	1 = 임원진(a.k.a 관리자)
	* 	2 = 일반 부원
	* 	3 = 졸업기수
	* 	4 = 회원가입 미인증 회원
	* */
	User findByUserId(String userId);

	User findByNicknameAndGeneration(
		String nickname,
		int generation
	);

	User findByNameAndEmail(String name, String email);
}
