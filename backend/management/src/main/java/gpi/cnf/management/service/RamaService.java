
package gpi.cnf.management.service;

import java.util.List;

import gpi.cnf.management.dtos.RamaDTO;
import gpi.cnf.management.dtos.RamaResponseDTO;
public interface RamaService {

	public RamaDTO createRama(RamaDTO rama);
	public boolean removeRama(Long idRama);
	public List<RamaDTO> getRamas();
	public RamaDTO updateRama(RamaDTO rama);
	public RamaDTO getRama(Long idRama);

	public RamaResponseDTO getRamaDetallado(Long idRama);
}
