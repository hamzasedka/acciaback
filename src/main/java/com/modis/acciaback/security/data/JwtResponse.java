package com.modis.acciaback.security.data;

import com.modis.acciaback.model.UserRole;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Integer id;
	private String username;
	private String email;
	private UserRole role; 

	public JwtResponse(String token, Integer id, String username, String email) {
		this.token = token;
		this.id = id;
		this.username = username;
		this.email = email;
	}
	
	public JwtResponse(String token, Integer id, String username, String email, UserRole role) {
		this.token = token;
		this.id = id;
		this.username = username;
		this.email = email;
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

}
