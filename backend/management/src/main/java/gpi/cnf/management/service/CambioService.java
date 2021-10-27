
package gpi.cnf.management.service;

import java.util.List;

import gpi.cnf.management.dtos.CambioDTO;
import gpi.cnf.management.dtos.CambioResponseDTO;
public interface CambioService {

	public CambioDTO createCambio(CambioDTO solicitud);
	public CambioDTO getCambio(Long idCambio);
	public List<CambioDTO> getCambios();
	public CambioResponseDTO getCambioDetallado(Long idCambio);
	public List<CambioResponseDTO> getCambioDetallados();
}
