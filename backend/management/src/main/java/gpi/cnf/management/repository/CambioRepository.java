package gpi.cnf.management.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gpi.cnf.management.entities.CambioEntity;

@Repository("CambioRepository")
public interface CambioRepository extends JpaRepository<CambioEntity, Long> {

    @Query(value = "SELECT * FROM Cambio", nativeQuery = true)
    public List<CambioEntity> getAll();
    
}


