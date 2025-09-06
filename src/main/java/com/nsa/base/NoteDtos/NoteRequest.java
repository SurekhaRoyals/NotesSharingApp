package com.nsa.base.NoteDtos;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;


@Component
public class NoteRequest {

	@NotBlank 
	private String title;
	  private String content;
	  private String tags;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	  
	  
}
