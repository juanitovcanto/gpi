package gpi.cnf.management.dtos;

import lombok.Data;

@Data

public class SolicitudCambioDTO {
	private Long solicitudId;	
	private String titulo; 
	private String texto;	
	private int estado;	
	private String fecha;	
	private Long id_usuario;	
	private Long id_repositorio;
} 
