package com.roytuts.spring.security.api.auth.jwt.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiRestController {

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/greet/user")
	public ResponseEntity<String> greetingUser() {
		return new ResponseEntity<String>("Welcome, you have USER role", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/greet/admin")
	public ResponseEntity<String> greetingAdmin() {
		return new ResponseEntity<String>("Welcome, you have ADMIN role", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/greet/userOrAdmin")
	public ResponseEntity<String> greetingUserOrAdmin() {
		return new ResponseEntity<String>("Welcome, you have USER and ADMIN role", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ANONYMOUS')")
	@GetMapping("/greet/anonymous")
	public ResponseEntity<String> greetingAnonymous() {
		return new ResponseEntity<String>("Welcome, you have USER and ADMIN role", HttpStatus.OK);
	}

}
