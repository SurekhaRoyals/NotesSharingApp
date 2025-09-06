package com.nsa.base.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.nsa.base.Entity.NoteShare;
import com.nsa.base.Repository.NoteRepository;
import com.nsa.base.Repository.NoteShareRepository;
import com.nsa.base.Repository.UserRepository;
import com.nsa.base.ShareDtos.ShareRequest;

import jakarta.transaction.Transactional;

@Service
public class ShareService {

	
	@Autowired
	NoteRepository notes;
	@Autowired
	NoteShareRepository shares;
	@Autowired
	UserRepository users;
	@Autowired
	SimpMessagingTemplate ws;

	  public ShareService(NoteRepository notes, NoteShareRepository shares, UserRepository users, SimpMessagingTemplate ws) {
	    this.notes = notes; this.shares = shares; this.users = users; this.ws = ws;
	  }

	  @Transactional
	  public void share(String ownerUsername, Long noteId, ShareRequest req) {
	    var owner = users.findByUsername(ownerUsername).orElseThrow();
	    var note = notes.findById(noteId).orElseThrow(() -> new RuntimeException("Note not found"));
	    if (!note.getOwner().getId().equals(owner.getId()))
	      throw new RuntimeException("Only owner can share");

	    var target = users.findByUsername(req.getTargetUsername())
	      .orElseThrow(() -> new RuntimeException("Target user not found"));

	    var share = shares.findByNote_IdAndSharedWith_Id(noteId, target.getId()).orElse(new NoteShare());
	    share.setNote(note);
	    share.setSharedWith(target);
	    share.setPermission(req.getPermission());
	    shares.save(share);

	    // notify target user in real-time
	    ws.convertAndSend("/topic/notes/" + target.getUsername(),
	        "A note was shared with you: " + note.getTitle() + " (" + req.getPermission() + ")");
	  }

	  @Transactional
	  public void unshare(String ownerUsername, Long noteId, String targetUsername) {
	    var owner = users.findByUsername(ownerUsername).orElseThrow();
	    var note = notes.findById(noteId).orElseThrow();
	    if (!note.getOwner().getId().equals(owner.getId()))
	      throw new RuntimeException("Only owner can unshare");
	    var target = users.findByUsername(targetUsername).orElseThrow();
	    shares.deleteShare(noteId, target.getId());
	  }
}
