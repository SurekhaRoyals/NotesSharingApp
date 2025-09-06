package com.nsa.base.NoteDtos;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class NoteResponse {

	
	private Long id;
	  private String title;
	  private String content;
	  private String tags;
	  private LocalDateTime createdAt;
	  private LocalDateTime updatedAt;
	  private String ownerUsername;
	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getTags() {
		return tags;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public String getOwnerUsername() {
		return ownerUsername;
	}
	
	
	
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setOwnerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}
	public NoteResponse(Long id, String title, String content, String tags, LocalDateTime createdAt,
			LocalDateTime updatedAt, String ownerUsername) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.tags = tags;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.ownerUsername = ownerUsername;
	}
	public NoteResponse() {
		super();
		// TODO Auto-generated constructor stub
	}



}
