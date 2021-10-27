package gpi.cnf.management.service.impl;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gpi.cnf.management.dtos.SolicitudCambioDTO;
import gpi.cnf.management.dtos.UserDTO;
import gpi.cnf.management.dtos.RepoDTO;
import gpi.cnf.management.dtos.EstadoSolicitudCambioDTO;

import gpi.cnf.management.dtos.SolicitudCambioResponseDTO;
import gpi.cnf.management.entities.SolicitudCambioEntity;
import gpi.cnf.management.mapper.MapperUtils;
import gpi.cnf.management.repository.SolicitudCambioRepository;
import gpi.cnf.management.service.SolicitudCambioService;

import gpi.cnf.management.service.UserService;
import gpi.cnf.management.service.RepoService;
import gpi.cnf.management.service.EstadoSolicitudCambioService;

@Service("SolicitudCambioService")
public class SolicitudCambioServiceImpl implements SolicitudCambioService {
	@Autowired
	SolicitudCambioRepository solicitudRepository;

	
	private SolicitudCambioEntity mapSolicitudCambioEntity(SolicitudCambioDTO solicitudDTO) {
		SolicitudCambioEntity solicitud= new SolicitudCambioEntity();		
		solicitud.setTitulo(solicitudDTO.getTitulo());
		solicitud.setTexto(solicitudDTO.getTexto());
		solicitud.setEstado(solicitudDTO.getEstado());
		solicitud.setId_usuario(solicitudDTO.getId_usuario());
		solicitud.setId_repositorio(solicitudDTO.getId_repositorio());
		return solicitud;
	}
	@Override
	public SolicitudCambioDTO createSolicitudCambio(SolicitudCambioDTO solicitudDTO) {
		solicitudDTO.setEstado(1);
		SolicitudCambioEntity solicitudEntity = this.mapSolicitudCambioEntity(solicitudDTO);
		solicitudEntity =this.solicitudRepository.save(solicitudEntity);
		solicitudDTO=(SolicitudCambioDTO) MapperUtils.map(solicitudEntity, SolicitudCambioDTO.class);
		return solicitudDTO;
	}
	
	@Override
	public boolean removeSolicitudCambio(Long idSolicitudCambio) {
		boolean delete=true;		
		this.solicitudRepository.deleteById(idSolicitudCambio);		
		return delete;
	}
	@Override
	public SolicitudCambioDTO updateSolicitudCambio(SolicitudCambioDTO solicitudDTO) {
		int nuevoEstado;
		nuevoEstado = solicitudDTO.getEstado();
		solicitudDTO = getSolicitudCambio(solicitudDTO.getSolicitudId());	
		solicitudDTO.setEstado(nuevoEstado);
		
		SolicitudCambioEntity solicitudEntity = this.mapSolicitudCambioEntity(solicitudDTO);
		solicitudEntity.setSolicitudId(solicitudDTO.getSolicitudId());
		solicitudEntity =this.solicitudRepository.save(solicitudEntity);
		solicitudDTO=(SolicitudCambioDTO) MapperUtils.map(solicitudEntity, SolicitudCambioDTO.class);
		return solicitudDTO;

	}
	@Override
	public SolicitudCambioDTO getSolicitudCambio(Long idSolicitudCambio) {
		return (SolicitudCambioDTO) MapperUtils.map(this.solicitudRepository.findById(idSolicitudCambio).get(), SolicitudCambioDTO.class);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<SolicitudCambioDTO> getSolicitudCambios() {
		 return (List<SolicitudCambioDTO>) MapperUtils.mapAsList(solicitudRepository.getAll(), new TypeToken<List<SolicitudCambioDTO>>() {}.getType());
	}

		
	@Autowired
	UserService userService;
	
	@Autowired	
	RepoService repoService;

	@Autowired	
	EstadoSolicitudCambioService estadoSolicitudCambioService;
	@Override
	public SolicitudCambioResponseDTO getSolicitudCambioDetallado(Long idSolicitudCambio) {
		SolicitudCambioDTO solicitud = new SolicitudCambioDTO();
		solicitud = getSolicitudCambio(idSolicitudCambio);

		UserDTO user = new UserDTO();
		user = userService.getUser(solicitud.getId_usuario());
		List<UserDTO> listUser = new ArrayList<UserDTO>();
		listUser.add(user);

		EstadoSolicitudCambioDTO estado = new EstadoSolicitudCambioDTO();
		estado = estadoSolicitudCambioService.getEstadoSolicitudCambio(Long.valueOf(solicitud.getEstado()));
		List<EstadoSolicitudCambioDTO> listEstado = new ArrayList<EstadoSolicitudCambioDTO>();
		listEstado.add(estado);

		RepoDTO repository = new RepoDTO();
		repository = repoService.getRepo(solicitud.getId_repositorio());
		List<RepoDTO> listRepo = new ArrayList<RepoDTO>();
		listRepo.add(repository);		
		return new SolicitudCambioResponseDTO(solicitud,listUser,listRepo,listEstado);
	}
	public List<SolicitudCambioResponseDTO> getSolicitudCambioDetallados() {

		List<SolicitudCambioResponseDTO> solicitudCambiosDetallados = new ArrayList<SolicitudCambioResponseDTO>();
 		List<SolicitudCambioDTO> cambios = new ArrayList<SolicitudCambioDTO>();

		cambios = getSolicitudCambios();	

		for (SolicitudCambioDTO solicitudCambioDTO : cambios ) {
				
			UserDTO user = new UserDTO();
			user = userService.getUser(solicitudCambioDTO.getId_usuario());
			List<UserDTO> listUser = new ArrayList<UserDTO>();
			listUser.add(user);

			EstadoSolicitudCambioDTO estado = new EstadoSolicitudCambioDTO();
			estado = estadoSolicitudCambioService.getEstadoSolicitudCambio(Long.valueOf(solicitudCambioDTO.getEstado()));
			List<EstadoSolicitudCambioDTO> listEstado = new ArrayList<EstadoSolicitudCambioDTO>();
			listEstado.add(estado);

			RepoDTO repository = new RepoDTO();
			repository = repoService.getRepo(solicitudCambioDTO.getId_repositorio());
			List<RepoDTO> listRepo = new ArrayList<RepoDTO>();
			listRepo.add(repository);		
			
			SolicitudCambioResponseDTO solicitudCambio = new SolicitudCambioResponseDTO(solicitudCambioDTO,listUser,listRepo,listEstado);
			solicitudCambiosDetallados.add(solicitudCambio);
				

		}	
			
		return solicitudCambiosDetallados;
	}
	
}
