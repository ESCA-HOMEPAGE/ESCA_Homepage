package com.esca.escahp.user.entity;

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

}
