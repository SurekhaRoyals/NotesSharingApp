package com.nsa.base.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor
@AllArgsConstructor
@Entity 
@Table(name="notes")
public class Note {

	
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  @Column(nullable = false)
	  private String title;

	  @Column(length = 5000)
	  private String content;

	  private String tags;

	  private LocalDateTime createdAt;
	  private LocalDateTime updatedAt;

	  @ManyToOne(optional = false)
	  @JoinColumn(name = "owner_id")
	  private User owner;

	  @PrePersist
	  void onCreate() { createdAt = updatedAt = LocalDateTime.now(); }

	  @PreUpdate
	  void onUpdate() { updatedAt = LocalDateTime.now(); }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Note(Long id, String title, String content, String tags, LocalDateTime createdAt, LocalDateTime updatedAt,
			User owner) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.tags = tags;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.owner = owner;
	}











}
