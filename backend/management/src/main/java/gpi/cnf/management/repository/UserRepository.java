package gpi.cnf.management.repository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gpi.cnf.management.entities.UserEntity;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByUsername(String userName);


	//@Query(value = "SELECT USERNAME FROM Usuario where USERNAME = :username ",nativeQuery = true)
	//public UserEntity findByUsername(String username)
    	@Query(value = "SELECT * FROM Usuario", nativeQuery = true)
   	public List<UserEntity> getAll();
    
}


