
package gpi.cnf.management.service.impl;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gpi.cnf.management.dtos.EstadoCambioDTO;
import gpi.cnf.management.dtos.CambioResponseDTO;
import gpi.cnf.management.dtos.UserDTO;
import gpi.cnf.management.dtos.RamaDTO;
import gpi.cnf.management.dtos.EstadoWorkflowDTO;

import gpi.cnf.management.dtos.EstadoCambioResponseDTO;
import gpi.cnf.management.entities.EstadoCambioEntity;
import gpi.cnf.management.mapper.MapperUtils;
import gpi.cnf.management.repository.EstadoCambioRepository;

import gpi.cnf.management.service.EstadoCambioService;
import gpi.cnf.management.service.UserService;
import gpi.cnf.management.service.CambioService;
import gpi.cnf.management.service.RamaService;
import gpi.cnf.management.service.EstadoWorkflowService;

@Service("EstadoCambioService")
public class EstadoCambioServiceImpl implements EstadoCambioService {
	@Autowired
	EstadoCambioRepository estadoCambioRepository;

	
	private EstadoCambioEntity mapEstadoCambioEntity(EstadoCambioDTO estadoCambioDTO) {
		EstadoCambioEntity estadoCambio= new EstadoCambioEntity();		
		estadoCambio.setEstado(estadoCambioDTO.getEstado());
		estadoCambio.setIdUsuario(estadoCambioDTO.getIdUsuario());
		estadoCambio.setIdCambio(estadoCambioDTO.getIdCambio());
		estadoCambio.setIdRama(estadoCambioDTO.getIdRama());
		return estadoCambio;
	}
	@Override
	public EstadoCambioDTO createEstadoCambio(EstadoCambioDTO estadoCambioDTO) {
		EstadoCambioEntity estadoCambioEntity = this.mapEstadoCambioEntity(estadoCambioDTO);
		estadoCambioEntity =this.estadoCambioRepository.save(estadoCambioEntity);
		estadoCambioDTO=(EstadoCambioDTO) MapperUtils.map(estadoCambioEntity, EstadoCambioDTO.class);
		return estadoCambioDTO;
	}
	
	@Override
	public boolean removeEstadoCambio(Long idEstadoCambio) {
		boolean delete=true;		
		this.estadoCambioRepository.deleteById(idEstadoCambio);		
		return delete;
	}
	@Override
	public EstadoCambioDTO updateEstadoCambio(EstadoCambioDTO estadoCambioDTO) {
		estadoCambioDTO = getEstadoCambio(estadoCambioDTO.getCambioId());	
		
		EstadoCambioEntity estadoCambioEntity = this.mapEstadoCambioEntity(estadoCambioDTO);
		estadoCambioEntity.setCambioId(estadoCambioDTO.getCambioId());
		estadoCambioEntity =this.estadoCambioRepository.save(estadoCambioEntity);
		estadoCambioDTO=(EstadoCambioDTO) MapperUtils.map(estadoCambioEntity, EstadoCambioDTO.class);
		return estadoCambioDTO;

	}
	@Override
	public EstadoCambioDTO getEstadoCambio(Long idEstadoCambio) {
		return (EstadoCambioDTO) MapperUtils.map(this.estadoCambioRepository.findById(idEstadoCambio).get(), EstadoCambioDTO.class);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<EstadoCambioDTO> getEstadoCambios(Long idCambio) {
		 return (List<EstadoCambioDTO>) MapperUtils.mapAsList(estadoCambioRepository.getAll(idCambio), new TypeToken<List<EstadoCambioDTO>>() {}.getType());
	}

		
	@Autowired
	UserService userService;
	
	@Autowired	
	RamaService ramaService;

	@Autowired	
	CambioService cambioService;

	@Autowired	
	EstadoWorkflowService estadoWorkflowService;
	@Override
	public EstadoCambioResponseDTO getEstadoCambioDetallado(Long idEstadoCambio) {
		EstadoCambioDTO estadoCambio = new EstadoCambioDTO();
		estadoCambio = getEstadoCambio(idEstadoCambio);

		CambioResponseDTO cambio = new CambioResponseDTO();
		cambio = cambioService.getCambioDetallado(estadoCambio.getIdCambio());
		List<CambioResponseDTO> listCambio = new ArrayList<CambioResponseDTO>();
		listCambio.add(cambio);

		UserDTO user = new UserDTO();
		user = userService.getUser(estadoCambio.getIdUsuario());
		List<UserDTO> listUser = new ArrayList<UserDTO>();
		listUser.add(user);

		EstadoWorkflowDTO estado = new EstadoWorkflowDTO();
		estado = estadoWorkflowService.getEstadoWorkflow(estadoCambio.getEstado());
		List<EstadoWorkflowDTO> listEstado = new ArrayList<EstadoWorkflowDTO>();
		listEstado.add(estado);

		RamaDTO rama = new RamaDTO();
		rama = ramaService.getRama(estadoCambio.getIdRama());
		List<RamaDTO> listRama = new ArrayList<RamaDTO>();
		listRama.add(rama);		

		return new EstadoCambioResponseDTO(estadoCambio,listCambio,listRama,listUser,listEstado);
	}
	public List<EstadoCambioResponseDTO> getEstadoCambioDetallados(Long idCambio) {

		List<EstadoCambioResponseDTO> estadoCambiosDetallados = new ArrayList<EstadoCambioResponseDTO>();
 		List<EstadoCambioDTO> cambios = new ArrayList<EstadoCambioDTO>();

		cambios = getEstadoCambios(idCambio);	

		for (EstadoCambioDTO estadoCambioDTO : cambios ) {
				
			UserDTO user = new UserDTO();
			user = userService.getUser(estadoCambioDTO.getIdUsuario());
			List<UserDTO> listUser = new ArrayList<UserDTO>();
			listUser.add(user);

			CambioResponseDTO cambio = new CambioResponseDTO();
			cambio = cambioService.getCambioDetallado(estadoCambioDTO.getIdCambio());
			List<CambioResponseDTO> listCambio = new ArrayList<CambioResponseDTO>();
			listCambio.add(cambio);

			EstadoWorkflowDTO estado = new EstadoWorkflowDTO();
			estado = estadoWorkflowService.getEstadoWorkflow(Long.valueOf(estadoCambioDTO.getEstado()));
			List<EstadoWorkflowDTO> listEstado = new ArrayList<EstadoWorkflowDTO>();
			listEstado.add(estado);

			RamaDTO rama = new RamaDTO();
			rama = ramaService.getRama(estadoCambioDTO.getIdRama());
			List<RamaDTO> listRama = new ArrayList<RamaDTO>();
			listRama.add(rama);		
			
			EstadoCambioResponseDTO estadoCambio = new EstadoCambioResponseDTO(estadoCambioDTO,listCambio,listRama,listUser,listEstado);
			estadoCambiosDetallados.add(estadoCambio);
				

		}	
			
		return estadoCambiosDetallados;
	}
	
}
