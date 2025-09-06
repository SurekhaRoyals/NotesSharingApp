package com.nsa.base.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nsa.base.Service.ShareService;
import com.nsa.base.ShareDtos.ShareRequest;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/share")
public class ShareController {

	@Autowired
	ShareService shareService;
	  public ShareController(ShareService shareService) { this.shareService = shareService; }

	  @PostMapping("/{noteId}")
	  public String share(Principal p, @PathVariable Long noteId, @Valid @RequestBody ShareRequest req) {
	    shareService.share(p.getName(), noteId, req);
	    return "Shared";
	  }

	  @DeleteMapping("/{noteId}")
	  public String unshare(Principal p, @PathVariable Long noteId, @RequestParam String targetUsername) {
	    shareService.unshare(p.getName(), noteId, targetUsername);
	    return "Unshared";
	  }
}
