package gpi.cnf.management.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gpi.cnf.management.entities.SolicitudCambioEntity;

@Repository("solicitudCambioRepository")
public interface SolicitudCambioRepository extends JpaRepository<SolicitudCambioEntity, Long> {

    @Query(value = "SELECT * FROM Solicitud_cambio", nativeQuery = true)
    public List<SolicitudCambioEntity> getAll();
    
}


