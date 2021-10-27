package gpi.cnf.management.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gpi.cnf.management.entities.EstadoCambioEntity;

@Repository("EstadoCambioRepository")
public interface EstadoCambioRepository extends JpaRepository<EstadoCambioEntity, Long> {

    @Query(value = "SELECT * FROM Estado_cambio where ID_CAMBIO = :idCambio ", nativeQuery = true)
    public List<EstadoCambioEntity> getAll(Long idCambio);
    
}
