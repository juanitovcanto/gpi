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
@Data
@Entity
@Table(name = "Solicitud_cambio")
public class SolicitudCambioEntity {

	@Id
	@Column(name = "ID", nullable = false,columnDefinition = "int(10) unsigned")
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment",strategy = "increment")
	private Long  solicitudId;		

	@Column(name = "TITULO_SOLICITUD", nullable = false)
	private String titulo;

	@Column(name = "TEXTO", nullable = false)
	private String texto;

	@Column(name = "ESTADO_SOLICITUD", nullable = false)
	private int estado;

	@Column(name = "FECHA", nullable = false,updatable = false)
	@CreationTimestamp
	private Date fecha;

	@Column(name = "ID_USUARIO", nullable = false)
	private Long id_usuario;

	@Column(name = "ID_REPOSITORIO", nullable = false)
	private Long id_repositorio;
}
