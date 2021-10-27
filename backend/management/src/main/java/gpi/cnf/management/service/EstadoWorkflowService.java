

package gpi.cnf.management.service;

import java.util.List;

import gpi.cnf.management.dtos.EstadoWorkflowDTO;
public interface EstadoWorkflowService{

	public List<EstadoWorkflowDTO> getEstadoWorkflows();
	public EstadoWorkflowDTO getEstadoWorkflow(Long idEstadoWorkflow);
}
