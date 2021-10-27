
package gpi.cnf.management.service.impl;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gpi.cnf.management.dtos.CambioDTO;
import gpi.cnf.management.dtos.SolicitudCambioResponseDTO;
import gpi.cnf.management.dtos.SolicitudCambioDTO;

import gpi.cnf.management.dtos.CambioResponseDTO;
import gpi.cnf.management.entities.CambioEntity;
import gpi.cnf.management.mapper.MapperUtils;
import gpi.cnf.management.repository.CambioRepository;
import gpi.cnf.management.service.CambioService;
import gpi.cnf.management.service.SolicitudCambioService;


@Service("CambioService")
public class CambioServiceImpl implements CambioService {
	@Autowired
	CambioRepository cambioRepository;

	
	private CambioEntity mapCambioEntity(CambioDTO cambioDTO) {
		CambioEntity cambio= new CambioEntity();		
		cambio.setIdSolicitudCambio(cambioDTO.getIdSolicitudCambio());
		return cambio;
	}

	@Override
	public CambioDTO getCambio(Long idCambio) {
		return (CambioDTO) MapperUtils.map(this.cambioRepository.findById(idCambio).get(), CambioDTO.class);
	}
	@Override
	public CambioDTO createCambio(CambioDTO cambioDTO) {
		CambioEntity cambioEntity = this.mapCambioEntity(cambioDTO);
		cambioEntity =this.cambioRepository.save(cambioEntity);
		cambioDTO=(CambioDTO) MapperUtils.map(cambioEntity, CambioDTO.class);
		return cambioDTO;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<CambioDTO> getCambios() {
		 return (List<CambioDTO>) MapperUtils.mapAsList(cambioRepository.getAll(), new TypeToken<List<CambioDTO>>() {}.getType());
	}
	@Autowired
	SolicitudCambioService solicitudCambioService;
		
	@Override
	public CambioResponseDTO getCambioDetallado(Long idCambio) {
		CambioDTO Cambio = new CambioDTO();
		Cambio = getCambio(idCambio);

		SolicitudCambioResponseDTO solicitud = new SolicitudCambioResponseDTO();
		solicitud = solicitudCambioService.getSolicitudCambioDetallado(Cambio.getIdSolicitudCambio());
		List<SolicitudCambioResponseDTO> listSolicitud = new ArrayList<SolicitudCambioResponseDTO>();
		listSolicitud.add(solicitud);		 
		
		return new CambioResponseDTO(Cambio,listSolicitud);
	}
	public List<CambioResponseDTO> getCambioDetallados() {

		List<CambioResponseDTO> cambiosDetallados = new ArrayList<CambioResponseDTO>();
 		List<CambioDTO> cambios = new ArrayList<CambioDTO>();

		cambios = getCambios();	

		for (CambioDTO cambioDTO : cambios ) {

			SolicitudCambioResponseDTO solicitud = new SolicitudCambioResponseDTO();
			solicitud = solicitudCambioService.getSolicitudCambioDetallado(cambioDTO.getIdSolicitudCambio());	

			List<SolicitudCambioResponseDTO> listSolicitud = new ArrayList<SolicitudCambioResponseDTO>();
			listSolicitud.add(solicitud);

			CambioResponseDTO cambioResponseDTO = new CambioResponseDTO(cambioDTO,listSolicitud);
			cambiosDetallados.add(cambioResponseDTO);		 
		}	
			
		return cambiosDetallados;
	}
	
}

