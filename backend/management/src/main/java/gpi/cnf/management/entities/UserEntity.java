package gpi.cnf.management.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;

@Data
@Entity
@Table(name = "Usuario")
public class UserEntity {

	@Id
	@Column(name = "ID", nullable = false,columnDefinition = "int(10) unsigned")
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment",strategy = "increment")
	private Long  userId;		

	@Column(name = "USERNAME", nullable = false)
	private String username;

	@Column(name = "NOMBRE", nullable = false)
	private String firstName;

	@Column(name = "APELLIDO", nullable = false)
	private String lastName;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "ID_CARGO", nullable = false)
	private Long cargo;

}
