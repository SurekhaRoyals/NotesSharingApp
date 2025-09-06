package com.nsa.base.Security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nsa.base.Repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


//  second step
@Component
public class JwtAuthFilter extends OncePerRequestFilter{

	@Autowired
	 UserRepository userRepo;

	@Autowired
	JwtUtil jwtUtil;
	
	  @Override
	  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
	      throws ServletException, IOException {
	    String header = req.getHeader("Authorization");
	    if (header != null && header.startsWith("Bearer ")) {
	      String token = header.substring(7);
	      if (jwtUtil.valid(token)) {
	        String username = jwtUtil.username(token);
	        var user = userRepo.findByUsername(username).orElse(null);
	        if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	          var auth = new UsernamePasswordAuthenticationToken(
	              user.getUsername(), null, List.of(new SimpleGrantedAuthority("ROLE_USER")));
	          auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
	          SecurityContextHolder.getContext().setAuthentication(auth);
	        }
	      }
	    }
	    chain.doFilter(req, res);
	  }
}
