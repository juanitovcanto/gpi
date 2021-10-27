package gpi.cnf.management.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import gpi.cnf.management.payload.request.LoginRequest;
import gpi.cnf.management.payload.response.JwtResponse;
import gpi.cnf.management.payload.response.MessageResponse;
import gpi.cnf.management.service.jwt.JwtUtils;
import gpi.cnf.management.service.UserDetailsImpl;

import org.springframework.http.HttpStatus;

import gpi.cnf.management.dtos.UserDTO;
import gpi.cnf.management.service.UserService;

import gpi.cnf.management.dtos.CargoUsuarioDTO;
import gpi.cnf.management.service.CargoUsuarioService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	UserService service;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	CargoUsuarioService cargoService;

	@GetMapping("/cargoUsuarios")
	public ResponseEntity<List<CargoUsuarioDTO>> findAll() {
		return new ResponseEntity<>(cargoService.getCargoUsuarios(), HttpStatus.OK);
	}		
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getFirstName(), 
												 userDetails.getLastName(), 
												 userDetails.getEmail() 
												 ));
	}
	@PostMapping("/signup")	

	public ResponseEntity<UserDTO> save(@RequestBody @Valid UserDTO userDTO) {
		return new ResponseEntity<>(service.createUser(userDTO), HttpStatus.CREATED);
	}
}
