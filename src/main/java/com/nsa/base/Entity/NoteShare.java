package com.nsa.base.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
@NoArgsConstructor
@Entity
@Table(name="note_shares",
uniqueConstraints=@UniqueConstraint(columnNames={"note_id","shared_with_id"}))

public class NoteShare {

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  @ManyToOne(optional = false)
	  @JoinColumn(name="note_id")
	  private Note note;

	  @ManyToOne(optional = false) @JoinColumn(name="shared_with_id")
	  private User sharedWith;

	  @Enumerated(EnumType.STRING)
	  @Column(nullable = false)
	  private SharePermission permission;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public User getSharedWith() {
		return sharedWith;
	}

	public void setSharedWith(User sharedWith) {
		this.sharedWith = sharedWith;
	}

	public SharePermission getPermission() {
		return permission;
	}

	public void setPermission(SharePermission permission) {
		this.permission = permission;
	}

	public NoteShare() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoteShare(Long id, Note note, User sharedWith, SharePermission permission) {
		super();
		this.id = id;
		this.note = note;
		this.sharedWith = sharedWith;
		this.permission = permission;
	}
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
}
