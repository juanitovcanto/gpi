
package gpi.cnf.management.service;

import java.util.List;

import gpi.cnf.management.dtos.EstadoCambioDTO;
import gpi.cnf.management.dtos.EstadoCambioResponseDTO;
public interface EstadoCambioService {

	public EstadoCambioDTO createEstadoCambio(EstadoCambioDTO estadoCambio);
	public boolean removeEstadoCambio(Long idEstadoCambio);
	public List<EstadoCambioDTO> getEstadoCambios(Long idCambio);
	public EstadoCambioDTO updateEstadoCambio(EstadoCambioDTO estadoCambio);
	public EstadoCambioDTO getEstadoCambio(Long idEstadoCambio);

	public EstadoCambioResponseDTO getEstadoCambioDetallado(Long idEstadoCambio);
	public List<EstadoCambioResponseDTO> getEstadoCambioDetallados(Long idCambio);
}
