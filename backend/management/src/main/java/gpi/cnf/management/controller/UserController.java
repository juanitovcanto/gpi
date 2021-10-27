package gpi.cnf.management.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;


import gpi.cnf.management.dtos.UserDTO;
import gpi.cnf.management.service.UserService;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
@RequestMapping("/users")

public class UserController {

	@Autowired
	UserService service;
	
	

	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long userId) {
		return new ResponseEntity<>(service.getUser(userId), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		return new ResponseEntity<>(service.getUsers(), HttpStatus.OK);
	}
	@DeleteMapping("/{userId}")
	public ResponseEntity<Boolean> deleteById(@PathVariable Long userId) {
		return new ResponseEntity<>(service.removeUser(userId), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<UserDTO> update(@RequestBody @Valid UserDTO userDTO) {
		return new ResponseEntity<>(service.updateUser(userDTO), HttpStatus.OK);
	}		
}



