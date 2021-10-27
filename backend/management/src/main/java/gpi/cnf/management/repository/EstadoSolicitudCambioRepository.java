package gpi.cnf.management.repository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gpi.cnf.management.entities.EstadoSolicitudCambioEntity;

@Repository("EstadoSolicitudCambioRepository")
public interface EstadoSolicitudCambioRepository extends JpaRepository<EstadoSolicitudCambioEntity, Long> {

    	@Query(value = "SELECT * FROM Estado_solicitud_cambio", nativeQuery = true)
   	public List<EstadoSolicitudCambioEntity> getAll();
    
}
