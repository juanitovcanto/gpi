package gpi.cnf.management.service;

import java.util.List;

import gpi.cnf.management.dtos.SolicitudCambioDTO;
import gpi.cnf.management.dtos.SolicitudCambioResponseDTO;
public interface SolicitudCambioService {

	public SolicitudCambioDTO createSolicitudCambio(SolicitudCambioDTO solicitud);
	public boolean removeSolicitudCambio(Long idSolicitudCambio);
	public List<SolicitudCambioDTO> getSolicitudCambios();
	public SolicitudCambioDTO updateSolicitudCambio(SolicitudCambioDTO solicitud);
	public SolicitudCambioDTO getSolicitudCambio(Long idSolicitudCambio);

	public SolicitudCambioResponseDTO getSolicitudCambioDetallado(Long idSolicitudCambio);
	public List<SolicitudCambioResponseDTO> getSolicitudCambioDetallados();
}
