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
@Table(name = "Repositorio")
public class RepoEntity {

	@Id
	@Column(name = "ID", nullable = false,columnDefinition = "int(10) unsigned")
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment",strategy = "increment")
	private Long  repoId;		

	@Column(name = "NOMBRE_REPOSITORIO", nullable = false)
	private String repoName;

	@Column(name = "URL_REPOSITORIO", nullable = false)
	private String repoUrl;

}
