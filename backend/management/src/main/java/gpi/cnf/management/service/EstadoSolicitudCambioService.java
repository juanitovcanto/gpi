
package gpi.cnf.management.service;

import java.util.List;

import gpi.cnf.management.dtos.EstadoSolicitudCambioDTO;
public interface EstadoSolicitudCambioService{

	public List<EstadoSolicitudCambioDTO> getEstadoSolicitudCambios();
	public EstadoSolicitudCambioDTO getEstadoSolicitudCambio(Long idEstadoSolicitudCambio);
}
