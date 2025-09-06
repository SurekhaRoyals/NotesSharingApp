package com.nsa.base.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nsa.base.NoteDtos.NoteRequest;
import com.nsa.base.NoteDtos.NoteResponse;
import com.nsa.base.Service.NoteService;
import com.nsa.base.ShareDtos.PagedResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

	@Autowired
	NoteService svc;
	  
	  @PostMapping
	  public NoteResponse create(Principal p, @Valid @RequestBody NoteRequest req) {
	    return svc.create(p.getName(), req);
	  }

	  @GetMapping
	  public PagedResponse<NoteResponse> myNotes(
	      Principal p,
	      @RequestParam(defaultValue = "0") int page,
	      @RequestParam(defaultValue = "10") int size,
	      @RequestParam(defaultValue = "updatedAt") String sortBy,
	      @RequestParam(defaultValue = "desc") String sortDir,
	      @RequestParam(required = false) String search) {
	    Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
	    Pageable pageable = PageRequest.of(page, size, sort);
	    return svc.myNotes(p.getName(), search, pageable);
	  }

	  @GetMapping("/shared-with-me")
	  public PagedResponse<NoteResponse> sharedWithMe(
	      Principal p,
	      @RequestParam(defaultValue = "0") int page,
	      @RequestParam(defaultValue = "10") int size) {
	    return svc.sharedWithMe(p.getName(), PageRequest.of(page, size));
	  }

	  @PutMapping("/{id}")
	  public NoteResponse update(Principal p, @PathVariable Long id, @Valid @RequestBody NoteRequest req) {
	    return svc.update(p.getName(), id, req);
	  }

	  @DeleteMapping("/{id}")
	  public String delete(Principal p, @PathVariable Long id) {
	    svc.delete(p.getName(), id);
	    return "Deleted";
	  }
}
