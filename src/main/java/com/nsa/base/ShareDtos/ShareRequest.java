package com.nsa.base.ShareDtos;

import org.springframework.stereotype.Component;

import com.nsa.base.Entity.SharePermission;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Component
public class ShareRequest {

	
	@NotBlank
	private String targetUsername;
	
	  @NotNull 
	  private SharePermission permission;
	  
	  
	  
	public String getTargetUsername() {
		return targetUsername;
	}
	public void setTargetUsername(String targetUsername) {
		this.targetUsername = targetUsername;
	}
	public SharePermission getPermission() {
		return permission;
	}
	public void setPermission(SharePermission permission) {
		this.permission = permission;
	}

	  
	  
}
