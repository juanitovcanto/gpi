
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
@Table(name = "Rama_repositorio")
public class RamaEntity {

	@Id
	@Column(name = "ID", nullable = false,columnDefinition = "int(10) unsigned")
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment",strategy = "increment")
	private Long  ramaId;		

	@Column(name = "NOMBRE_RAMA", nullable = false)
	private String ramaName;

	@Column(name = "ID_REPOSITORIO", nullable = false)
	private Long repoId;
}
