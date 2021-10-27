
package gpi.cnf.management.dtos;

import lombok.Data;
import java.util.List;
@Data

public class CambioResponseDTO {
	public CambioResponseDTO(){
	
	}
	public CambioResponseDTO(CambioDTO cambio,List<SolicitudCambioResponseDTO> solicitud){
		this.cambio = cambio;
		this.solicitud = solicitud;
	}
	private CambioDTO cambio;
	private List<SolicitudCambioResponseDTO> solicitud;
}
