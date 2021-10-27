
package gpi.cnf.management.dtos;

import lombok.Data;
import java.util.List;
@Data

public class EstadoCambioResponseDTO {
	public EstadoCambioResponseDTO(){
	
	}
	public EstadoCambioResponseDTO(EstadoCambioDTO estadoCambio,List<CambioResponseDTO> cambio, List<RamaDTO> rama,List<UserDTO> user,List<EstadoWorkflowDTO> estado){
		this.estadoCambio = estadoCambio;
		this.cambio = cambio;
		this.rama = rama;
		this.user = user;
		this.estado= estado;
	}
	private EstadoCambioDTO estadoCambio;
	private List<UserDTO> user;
	private List<RamaDTO> rama;
	private List<CambioResponseDTO> cambio;
	private List<EstadoWorkflowDTO> estado;
	
} 
