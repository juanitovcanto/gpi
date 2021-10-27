package gpi.cnf.management.dtos;

import lombok.Data;
import java.util.List;
@Data

public class SolicitudCambioResponseDTO {
	public SolicitudCambioResponseDTO(){
	
	}
	public SolicitudCambioResponseDTO(SolicitudCambioDTO solicitud,List<UserDTO> user , List<RepoDTO> repo,List<EstadoSolicitudCambioDTO> estado){
		this.solicitud = solicitud;
		this.user = user;
		this.repo = repo;
		this.estado = estado;
	}
	private SolicitudCambioDTO solicitud;
	private List<UserDTO> user;
	private List<RepoDTO> repo;
	private List<EstadoSolicitudCambioDTO> estado;
	
} 
