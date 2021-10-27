package gpi.cnf.management.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gpi.cnf.management.entities.RepoEntity;

@Repository("RepoRepository")
public interface RepoRepository extends JpaRepository<RepoEntity, Long> {

    @Query(value = "SELECT * FROM Repositorio", nativeQuery = true)
    public List<RepoEntity> getAll();
    
}


