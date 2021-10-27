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


import gpi.cnf.management.dtos.EstadoCambioDTO;
import gpi.cnf.management.dtos.EstadoCambioResponseDTO;
import gpi.cnf.management.service.EstadoCambioService;
import gpi.cnf.management.service.EstadoWorkflowService;

import gpi.cnf.management.dtos.EstadoWorkflowDTO;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/estadoCambios")

public class EstadoCambioController {

	@Autowired
	EstadoCambioService service;
	
	@Autowired
	EstadoWorkflowService estadoService;
	
	@PostMapping
	public ResponseEntity<EstadoCambioDTO> save(@RequestBody @Valid EstadoCambioDTO solicitudDTO) {
		return new ResponseEntity<>(service.createEstadoCambio(solicitudDTO), HttpStatus.CREATED);
	}

	/*@GetMapping("/{cambioId}")
	public ResponseEntity<EstadoCambioDTO> findById(@PathVariable Long cambioId) {
		return new ResponseEntity<>(service.getEstadoCambio(cambioId), HttpStatus.OK);
	}
	*/
	@GetMapping
	public ResponseEntity<List<EstadoCambioDTO>> findAll(Long cambioId) {
		return new ResponseEntity<>(service.getEstadoCambios(cambioId), HttpStatus.OK);
	}
	@GetMapping("/estados")
	public ResponseEntity<List<EstadoWorkflowDTO>> findEstados() {
		return new ResponseEntity<>(estadoService.getEstadoWorkflows(), HttpStatus.OK);
	}

	@DeleteMapping("/{cambioId}")
	public ResponseEntity<Boolean> deleteById(@PathVariable Long cambioId) {
		return new ResponseEntity<>(service.removeEstadoCambio(cambioId), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<EstadoCambioDTO> update(@RequestBody @Valid EstadoCambioDTO solicitudDTO) {
		return new ResponseEntity<>(service.updateEstadoCambio(solicitudDTO), HttpStatus.OK);
	}
	@GetMapping("/{cambioId}")	
	public ResponseEntity<EstadoCambioResponseDTO> findByIdDetallado(@PathVariable Long cambioId) {
		return new ResponseEntity<>(service.getEstadoCambioDetallado(cambioId), HttpStatus.OK);
	}
	@GetMapping("/detallados/{cambioId}")	
	public ResponseEntity<List<EstadoCambioResponseDTO>> findDetallados(@PathVariable Long cambioId) {
		return new ResponseEntity<>(service.getEstadoCambioDetallados(cambioId), HttpStatus.OK);
	}
}

