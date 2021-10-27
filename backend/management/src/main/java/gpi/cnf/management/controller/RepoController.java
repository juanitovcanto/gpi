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


import gpi.cnf.management.dtos.RepoDTO;
import gpi.cnf.management.service.RepoService;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
@RequestMapping("/repos")

public class RepoController {

	@Autowired
	RepoService service;
	
	
	@PostMapping
	public ResponseEntity<RepoDTO> save(@RequestBody @Valid RepoDTO repoDTO) {
		return new ResponseEntity<>(service.createRepo(repoDTO), HttpStatus.CREATED);
	}

	@GetMapping("/{repoId}")
	public ResponseEntity<RepoDTO> findById(@PathVariable Long repoId) {
		return new ResponseEntity<>(service.getRepo(repoId), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<RepoDTO>> findAll() {
		return new ResponseEntity<>(service.getRepos(), HttpStatus.OK);
	}
	@DeleteMapping("/{repoId}")
	public ResponseEntity<Boolean> deleteById(@PathVariable Long repoId) {
		return new ResponseEntity<>(service.removeRepo(repoId), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<RepoDTO> update(@RequestBody @Valid RepoDTO repoDTO) {
		return new ResponseEntity<>(service.updateRepo(repoDTO), HttpStatus.OK);
	}		
}



