
package gpi.cnf.management.service.impl;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gpi.cnf.management.dtos.RamaDTO;
import gpi.cnf.management.dtos.RepoDTO;
import gpi.cnf.management.dtos.RamaResponseDTO;
import gpi.cnf.management.entities.RamaEntity;
import gpi.cnf.management.mapper.MapperUtils;
import gpi.cnf.management.repository.RamaRepository;
import gpi.cnf.management.service.RamaService;
import gpi.cnf.management.service.RepoService;


@Service("ramaService")
public class RamaServiceImpl implements RamaService {
	@Autowired
	RamaRepository ramaRepository;
	
	private RamaEntity mapRamaEntity(RamaDTO ramaDTO) {
		RamaEntity rama= new RamaEntity();		
		rama.setRamaName(ramaDTO.getRamaName());
		rama.setRepoId(ramaDTO.getRepoId());
		return rama;
	}
	@Override
	public RamaDTO createRama(RamaDTO ramaDTO) {
		RamaEntity ramaEntity = this.mapRamaEntity(ramaDTO);
		ramaEntity =this.ramaRepository.save(ramaEntity);
		ramaDTO=(RamaDTO) MapperUtils.map(ramaEntity, RamaDTO.class);
		return ramaDTO;
	}
	
	@Override
	public boolean removeRama(Long idRama) {
		boolean delete=true;		
		this.ramaRepository.deleteById(idRama);		
		return delete;
	}
	@Override
	public RamaDTO updateRama(RamaDTO ramaDTO) {
		RamaEntity ramaEntity = this.mapRamaEntity(ramaDTO);
		ramaEntity.setRamaId(ramaDTO.getRamaId());
		ramaEntity =this.ramaRepository.save(ramaEntity);
		ramaDTO=(RamaDTO) MapperUtils.map(ramaEntity, RamaDTO.class);
		return ramaDTO;

	}
	@Override
	public RamaDTO getRama(Long idRama) {
		return (RamaDTO) MapperUtils.map(this.ramaRepository.findById(idRama).get(), RamaDTO.class);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<RamaDTO> getRamas() {
		 return (List<RamaDTO>) MapperUtils.mapAsList(ramaRepository.getAll(), new TypeToken<List<RamaDTO>>() {}.getType());

	}
	@Autowired
	RepoService repoService;
	@Override
	public RamaResponseDTO getRamaDetallado(Long idRama) {
		RamaDTO rama = new RamaDTO();
		rama = getRama(idRama);

		RepoDTO repo = new RepoDTO();
		repo = repoService.getRepo(rama.getRepoId());
		List<RepoDTO> listRepo = new ArrayList<RepoDTO>();
		listRepo.add(repo);

		return new RamaResponseDTO(rama,listRepo);
	}
}

