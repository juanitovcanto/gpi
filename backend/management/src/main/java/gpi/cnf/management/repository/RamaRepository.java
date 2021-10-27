
package gpi.cnf.management.repository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gpi.cnf.management.entities.RamaEntity;

@Repository("RamaRepository")
public interface RamaRepository extends JpaRepository<RamaEntity, Long> {

    	@Query(value = "SELECT * FROM Rama_repositorio", nativeQuery = true)
   	public List<RamaEntity> getAll();
    
}
