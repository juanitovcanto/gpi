
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


import gpi.cnf.management.dtos.RamaDTO;
import gpi.cnf.management.dtos.RamaResponseDTO;
import gpi.cnf.management.service.RamaService;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
@RequestMapping("/Ramas")

public class RamaController {

	@Autowired
	RamaService service;
	
	
	@PostMapping
	public ResponseEntity<RamaDTO> save(@RequestBody @Valid RamaDTO ramaDTO) {
		return new ResponseEntity<>(service.createRama(ramaDTO), HttpStatus.CREATED);
	}

	@GetMapping("/{ramaId}")
	public ResponseEntity<RamaResponseDTO> findById(@PathVariable Long ramaId) {
		return new ResponseEntity<>(service.getRamaDetallado(ramaId), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<RamaDTO>> findAll() {
		return new ResponseEntity<>(service.getRamas(), HttpStatus.OK);
	}
	@DeleteMapping("/{ramaId}")
	public ResponseEntity<Boolean> deleteById(@PathVariable Long ramaId) {
		return new ResponseEntity<>(service.removeRama(ramaId), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<RamaDTO> update(@RequestBody @Valid RamaDTO RamaDTO) {
		return new ResponseEntity<>(service.updateRama(RamaDTO), HttpStatus.OK);
	}		
}
