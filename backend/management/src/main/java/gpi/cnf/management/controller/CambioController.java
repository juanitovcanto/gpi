
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


import gpi.cnf.management.dtos.CambioDTO;
import gpi.cnf.management.dtos.CambioResponseDTO;
import gpi.cnf.management.service.CambioService;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
@RequestMapping("/cambio")

public class CambioController {

	@Autowired
	CambioService service;
	
	@PostMapping
	public ResponseEntity<CambioDTO> save(@RequestBody @Valid CambioDTO solicitudDTO) {
		return new ResponseEntity<>(service.createCambio(solicitudDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<CambioResponseDTO>> findAll() {
		return new ResponseEntity<>(service.getCambioDetallados(), HttpStatus.OK);
	}
	@GetMapping("{solicitudId}")	
	public ResponseEntity<CambioResponseDTO> findByIdDetallado(@PathVariable Long solicitudId) {
		return new ResponseEntity<>(service.getCambioDetallado(solicitudId), HttpStatus.OK);
	}
}



