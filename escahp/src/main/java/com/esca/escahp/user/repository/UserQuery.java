package com.esca.escahp.user.repository;

public class UserQuery {

	public static final String updatePassword = "UPDATE user"
		+ "SET password = :password"
		+ "WHERE id = :id";
}
