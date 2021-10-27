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


import gpi.cnf.management.dtos.SolicitudCambioDTO;
import gpi.cnf.management.dtos.SolicitudCambioResponseDTO;
import gpi.cnf.management.service.SolicitudCambioService;
import gpi.cnf.management.service.EstadoSolicitudCambioService;

import gpi.cnf.management.dtos.EstadoSolicitudCambioDTO;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/solicituds")

public class SolicitudCambioController {

	@Autowired
	SolicitudCambioService service;
	
	@Autowired
	EstadoSolicitudCambioService estadoService;
	
	@PostMapping
	public ResponseEntity<SolicitudCambioDTO> save(@RequestBody @Valid SolicitudCambioDTO solicitudDTO) {
		return new ResponseEntity<>(service.createSolicitudCambio(solicitudDTO), HttpStatus.CREATED);
	}

	/*@GetMapping("/{solicitudId}")
	public ResponseEntity<SolicitudCambioDTO> findById(@PathVariable Long solicitudId) {
		return new ResponseEntity<>(service.getSolicitudCambio(solicitudId), HttpStatus.OK);
	}
	*/
	@GetMapping
	public ResponseEntity<List<SolicitudCambioDTO>> findAll() {
		return new ResponseEntity<>(service.getSolicitudCambios(), HttpStatus.OK);
	}
	@GetMapping("/estados")
	public ResponseEntity<List<EstadoSolicitudCambioDTO>> findEstados() {
		return new ResponseEntity<>(estadoService.getEstadoSolicitudCambios(), HttpStatus.OK);
	}

	@DeleteMapping("/{solicitudId}")
	public ResponseEntity<Boolean> deleteById(@PathVariable Long solicitudId) {
		return new ResponseEntity<>(service.removeSolicitudCambio(solicitudId), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<SolicitudCambioDTO> update(@RequestBody @Valid SolicitudCambioDTO solicitudDTO) {
		return new ResponseEntity<>(service.updateSolicitudCambio(solicitudDTO), HttpStatus.OK);
	}
	@GetMapping("/{solicitudId}")	
	public ResponseEntity<SolicitudCambioResponseDTO> findByIdDetallado(@PathVariable Long solicitudId) {
		return new ResponseEntity<>(service.getSolicitudCambioDetallado(solicitudId), HttpStatus.OK);
	}
	@GetMapping("/detallados")	
	public ResponseEntity<List<SolicitudCambioResponseDTO>> findDetallados() {
		return new ResponseEntity<>(service.getSolicitudCambioDetallados(), HttpStatus.OK);
	}
}



