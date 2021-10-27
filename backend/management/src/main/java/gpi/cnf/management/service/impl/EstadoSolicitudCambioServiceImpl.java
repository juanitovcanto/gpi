
package gpi.cnf.management.service.impl;
import java.util.List;
import java.util.UUID;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import gpi.cnf.management.dtos.EstadoSolicitudCambioDTO;
import gpi.cnf.management.entities.EstadoSolicitudCambioEntity;
import gpi.cnf.management.mapper.MapperUtils;
import gpi.cnf.management.repository.EstadoSolicitudCambioRepository;
import gpi.cnf.management.service.EstadoSolicitudCambioService;


@Service("estadoSolicitudCambioService")
public class EstadoSolicitudCambioServiceImpl implements EstadoSolicitudCambioService {
	@Autowired
	EstadoSolicitudCambioRepository estadoSolicitudCambioRepository;
	
	private EstadoSolicitudCambioEntity mapEstadoSolicitudCambioEntity(EstadoSolicitudCambioDTO estadoSolicitudCambioDTO) {
		EstadoSolicitudCambioEntity estadoSolicitudCambio= new EstadoSolicitudCambioEntity();		
		estadoSolicitudCambio.setEstado(estadoSolicitudCambioDTO.getEstado());
		return estadoSolicitudCambio;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<EstadoSolicitudCambioDTO> getEstadoSolicitudCambios() {
		 return (List<EstadoSolicitudCambioDTO>) MapperUtils.mapAsList(estadoSolicitudCambioRepository.getAll(), new TypeToken<List<EstadoSolicitudCambioDTO>>() {}.getType());
	}

	@Override
	public EstadoSolicitudCambioDTO getEstadoSolicitudCambio(Long idEstadoSolicitudCambio) {
		return (EstadoSolicitudCambioDTO) MapperUtils.map(this.estadoSolicitudCambioRepository.findById(idEstadoSolicitudCambio).get(), EstadoSolicitudCambioDTO.class);
	}
}

