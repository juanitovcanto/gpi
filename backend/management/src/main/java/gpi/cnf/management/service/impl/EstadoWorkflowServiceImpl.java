

package gpi.cnf.management.service.impl;
import java.util.List;
import java.util.UUID;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import gpi.cnf.management.dtos.EstadoWorkflowDTO;
import gpi.cnf.management.entities.EstadoWorkflowEntity;
import gpi.cnf.management.mapper.MapperUtils;
import gpi.cnf.management.repository.EstadoWorkflowRepository;
import gpi.cnf.management.service.EstadoWorkflowService;


@Service("estadoWorkflowService")
public class EstadoWorkflowServiceImpl implements EstadoWorkflowService {
	@Autowired
	EstadoWorkflowRepository estadoWorkflowRepository;
	
	private EstadoWorkflowEntity mapEstadoWorkflowEntity(EstadoWorkflowDTO estadoWorkflowDTO) {
		EstadoWorkflowEntity estadoWorkflow= new EstadoWorkflowEntity();		
		estadoWorkflow.setEstado(estadoWorkflowDTO.getEstado());
		return estadoWorkflow;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<EstadoWorkflowDTO> getEstadoWorkflows() {
		 return (List<EstadoWorkflowDTO>) MapperUtils.mapAsList(estadoWorkflowRepository.getAll(), new TypeToken<List<EstadoWorkflowDTO>>() {}.getType());
	}

	@Override
	public EstadoWorkflowDTO getEstadoWorkflow(Long idEstadoWorkflow) {
		return (EstadoWorkflowDTO) MapperUtils.map(this.estadoWorkflowRepository.findById(idEstadoWorkflow).get(), EstadoWorkflowDTO.class);
	}
}

