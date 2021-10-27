package gpi.cnf.management.repository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gpi.cnf.management.entities.CargoUsuarioEntity;

@Repository("CargoUsuarioRepository")
public interface CargoUsuarioRepository extends JpaRepository<CargoUsuarioEntity, Long> {

    	@Query(value = "SELECT * FROM Cargo_usuario", nativeQuery = true)
   	public List<CargoUsuarioEntity> getAll();
    
}
