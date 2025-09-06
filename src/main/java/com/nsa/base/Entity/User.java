package com.nsa.base.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name="users")
public class User {

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  @Column(unique = true, nullable = false)
	  private String username;

	  @Column(nullable = false)
	  private String passwordHash;

	  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
	  private List<Note> notes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String username, String passwordHash, List<Note> notes) {
		super();
		this.id = id;
		this.username = username;
		this.passwordHash = passwordHash;
		this.notes = notes;
	}




















}












