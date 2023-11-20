package com.modis.acciaback.payloads;

import java.util.ArrayList;
import java.util.List;

public class ChatResponse {
	//private String query;
	private String intention;
	private List<EntityResponse> entities;
	private String criticityLevel;
	private UserResponse assignedUser;

	public ChatResponse() {
		entities = new ArrayList<>();
	}

//	public String getQuery() {
//		return query;
//	}
//
//	public void setQuery(String query) {
//		this.query = query;
//	}

	public String getIntention() {
		return intention;
	}

	public void setIntention(String intents) {
		this.intention = intents;
	}

	public List<EntityResponse> getEntities() {
		return entities;
	}

	public void setEntities(List<EntityResponse> entities) {
		this.entities = entities;
	}

	public String getCriticityLevel() {
		return criticityLevel;
	}

	public void setCriticityLevel(String criticity_level) {
		this.criticityLevel = criticity_level;
	}

	public UserResponse getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(UserResponse assignedUser) {
		this.assignedUser = assignedUser;
	}
}
