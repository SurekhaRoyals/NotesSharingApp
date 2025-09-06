package com.nsa.base.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nsa.base.Entity.Note;
import com.nsa.base.Entity.SharePermission;
import com.nsa.base.Entity.User;
import com.nsa.base.NoteDtos.NoteRequest;
import com.nsa.base.NoteDtos.NoteResponse;
import com.nsa.base.Repository.NoteRepository;
import com.nsa.base.Repository.NoteShareRepository;
import com.nsa.base.Repository.UserRepository;
import com.nsa.base.ShareDtos.PagedResponse;

@Service
public class NoteService {

	
	@Autowired
	NoteRepository notes;
	@Autowired
	 NoteShareRepository shares;
	@Autowired
	  UserRepository users;


	  private User current(String username) {
	    return users.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
	  }

	  public NoteResponse create(String username, NoteRequest req) {
	    var u = current(username);
	    var n = new Note();
	    n.setTitle(req.getTitle());
	    n.setContent(req.getContent());
	    n.setTags(req.getTags());
	    n.setOwner(u);
	    var saved = notes.save(n);
	    return toDto(saved);
	  }

	  public PagedResponse<NoteResponse> myNotes(String username, String search, Pageable pageable) {
	    var u = current(username);
	    Page<NoteResponse> page = (search != null && !search.isBlank())
	      ? notes.searchMyNotes(u.getId(), search, pageable)
	      : notes.findMyNotes(u.getId(), pageable);
	    return wrap(page);
	  }

	  public PagedResponse<NoteResponse> sharedWithMe(String username, Pageable pageable) {
	    var u = current(username);
	    var page = shares.findSharedWithMe(u.getId(), pageable);
	    return wrap(page);
	  }

	  public NoteResponse update(String username, Long id, NoteRequest req) {
	    var u = current(username);
	    var note = notes.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));

	    boolean isOwner = note.getOwner().getId().equals(u.getId());
	    boolean canEdit = isOwner || shares.permissionFor(id, u.getId()).map(p -> p == SharePermission.EDIT).orElse(false);
	    if (!canEdit) throw new RuntimeException("Not allowed to edit this note");

	    note.setTitle(req.getTitle());
	    note.setContent(req.getContent());
	    note.setTags(req.getTags());
	    var saved = notes.save(note);
	    return toDto(saved);
	  }

	  public void delete(String username, Long id) {
	    var u = current(username);
	    var note = notes.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));
	    if (!note.getOwner().getId().equals(u.getId()))
	      throw new RuntimeException("Only owner can delete the note");
	    notes.delete(note);
	  }

	  private NoteResponse toDto(Note n) {
	    return new NoteResponse(n.getId(), n.getTitle(), n.getContent(), n.getTags(),
	      n.getCreatedAt(), n.getUpdatedAt(), n.getOwner().getUsername());
	  }

	  private <T> PagedResponse<T> wrap(Page<T> p) {
	    return new PagedResponse<>(p.getContent(), p.getNumber(), p.getTotalPages(), p.getTotalElements(), p.isLast());
	  }
}
