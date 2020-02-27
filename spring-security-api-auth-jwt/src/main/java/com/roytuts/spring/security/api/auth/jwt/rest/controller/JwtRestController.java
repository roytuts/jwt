package com.roytuts.spring.security.api.auth.jwt.rest.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roytuts.spring.security.api.auth.jwt.exception.DisabledUserException;
import com.roytuts.spring.security.api.auth.jwt.exception.InvalidUserCredentialsException;
import com.roytuts.spring.security.api.auth.jwt.service.UserAuthService;
import com.roytuts.spring.security.api.auth.jwt.util.JwtUtil;
import com.roytuts.spring.security.api.auth.jwt.vo.JwtRequest;
import com.roytuts.spring.security.api.auth.jwt.vo.JwtResponse;
import com.roytuts.spring.security.api.auth.jwt.vo.UserVo;

@RestController
public class JwtRestController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserAuthService userAuthService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/signin")
	public ResponseEntity<JwtResponse> generateJwtToken(@RequestBody JwtRequest jwtRequest) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getUserpwd()));
		} catch (DisabledException e) {
			throw new DisabledUserException("User Inactive");
		} catch (BadCredentialsException e) {
			throw new InvalidUserCredentialsException("Invalid Credentials");
		}
		UserDetails userDetails = userAuthService.loadUserByUsername(jwtRequest.getUsername());
		String username = userDetails.getUsername();
		String userpwd = userDetails.getPassword();
		Set<String> roles = userDetails.getAuthorities().stream().map(r -> r.getAuthority())
				.collect(Collectors.toSet());
		UserVo user = new UserVo();
		user.setUsername(username);
		user.setUserpwd(userpwd);
		user.setRoles(roles);
		String token = jwtUtil.generateToken(user);
		return new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody UserVo userVo) {
		UserVo u = userAuthService.getUserByUsername(userVo.getUsername());

		if (u == null) {
			userAuthService.saveUser(userVo);
			return new ResponseEntity<String>("User successfully registered", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("User already exists", HttpStatus.CONFLICT);
		}
	}

}
