package gpi.cnf.management.repository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gpi.cnf.management.entities.EstadoWorkflowEntity;

@Repository("EstadoWorkflowRepository")
public interface EstadoWorkflowRepository extends JpaRepository<EstadoWorkflowEntity, Long> {

    	@Query(value = "SELECT * FROM Estados_workflow", nativeQuery = true)
   	public List<EstadoWorkflowEntity> getAll();
    
}
