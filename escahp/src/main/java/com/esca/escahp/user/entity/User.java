package com.esca.escahp.user.entity;

import com.esca.escahp.user.dto.AuthRequest;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Setter
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String userId;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private int generation;

	@Column(nullable = false)
	private String nickname;

	@Column(nullable = false)
	private String name;

	@Column
	private int rank;

	@Column(nullable = false)
	private String email;

	@Column
	private String profileImg;

	@Column
	private String pr;

	public User(AuthRequest auth){
		this.id = auth.getId();
		this.userId = auth.getUserId();
		this.password = auth.getPassword();
		this.generation = auth.getGeneration();
		this.nickname = auth.getNickname();
		this.name = auth.getName();
		this.rank = auth.getRank();
		this.email = auth.getEmail();
		this.profileImg = auth.getProfileImg();
		this.pr = auth.getPr();
	}

	public void updatePassword(String newPassword) {
		this.password = newPassword;
	}
}
