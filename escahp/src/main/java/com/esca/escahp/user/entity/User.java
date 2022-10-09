package com.esca.escahp.user.entity;

import com.esca.escahp.user.dto.AuthRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Entity
@Setter
@NoArgsConstructor
public class User implements UserDetails {

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

	@ElementCollection(fetch = FetchType.EAGER)
	@Builder.Default
	private List<String> roles = new ArrayList<>();

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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public String getUsername() {
		return this.userId;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isEnabled() {
		return true;
	}
}
