
package gpi.cnf.management.entities;

import java.util.UUID;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
@Data
@Entity
@Table(name = "Estado_cambio")
public class EstadoCambioEntity {

	@Id
	@Column(name = "ID", nullable = false,columnDefinition = "int(10) unsigned")
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment",strategy = "increment")
	private Long  cambioId;		

	@Column(name = "ESTADO", nullable = false)
	private Long estado;

	@Column(name = "FECHA_INICIO", nullable = false,updatable = false)
	@CreationTimestamp
	private Date fechaInicio;

	@UpdateTimestamp
	@Column(name = "FECHA_TERMINO")
	private Date fechaTermino;

	@Column(name = "ID_USUARIO", nullable = false)
	private Long idUsuario;

	@Column(name = "ID_CAMBIO", nullable = false)
	private Long idCambio;

	@Column(name = "ID_RAMA_REPOSITORIO", nullable = false)
	private Long idRama;
}
