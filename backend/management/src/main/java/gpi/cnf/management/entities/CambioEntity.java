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

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
@Data
@Entity
@Table(name = "Cambio")
public class CambioEntity{

	@Id
	@Column(name = "ID", nullable = false,columnDefinition = "int(10) unsigned")
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment",strategy = "increment")
	private Long  id;		

	@CreationTimestamp
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@Column(name = "ID_SOLICITUD_CAMBIO", nullable = false)
	private Long idSolicitudCambio;
}

