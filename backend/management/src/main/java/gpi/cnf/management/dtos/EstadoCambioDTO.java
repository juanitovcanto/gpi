
package gpi.cnf.management.dtos;

import lombok.Data;

@Data

public class EstadoCambioDTO {
	private Long cambioId;	
	private Long estado; 
	private String fechaInicio;	
	private String fechaTermino;	
	private Long idUsuario;	
	private Long idCambio;	
	private Long idRama;
} 
