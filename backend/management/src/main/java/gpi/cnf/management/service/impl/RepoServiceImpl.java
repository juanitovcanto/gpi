package gpi.cnf.management.service.impl;
import java.util.List;
import java.util.UUID;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gpi.cnf.management.dtos.RepoDTO;
import gpi.cnf.management.entities.RepoEntity;
import gpi.cnf.management.mapper.MapperUtils;
import gpi.cnf.management.repository.RepoRepository;
import gpi.cnf.management.service.RepoService;


@Service("RepoService")
public class RepoServiceImpl implements RepoService {
	@Autowired
	RepoRepository repoRepository;

	
	private RepoEntity mapRepoEntity(RepoDTO repoDTO) {
		RepoEntity repo= new RepoEntity();		
		repo.setRepoName(repoDTO.getRepoName());
		repo.setRepoUrl(repoDTO.getRepoUrl());
		return repo;
	}
	@Override
	public RepoDTO createRepo(RepoDTO repoDTO) {
		RepoEntity repoEntity = this.mapRepoEntity(repoDTO);
		repoEntity =this.repoRepository.save(repoEntity);
		repoDTO=(RepoDTO) MapperUtils.map(repoEntity, RepoDTO.class);
		return repoDTO;
	}
	
	@Override
	public boolean removeRepo(Long idRepo) {
		boolean delete=true;		
		this.repoRepository.deleteById(idRepo);		
		return delete;
	}
	@Override
	public RepoDTO updateRepo(RepoDTO repoDTO) {
		RepoEntity repoEntity = this.mapRepoEntity(repoDTO);
		repoEntity.setRepoId(repoDTO.getRepoId());
		repoEntity =this.repoRepository.save(repoEntity);
		repoDTO=(RepoDTO) MapperUtils.map(repoEntity, RepoDTO.class);
		return repoDTO;

	}
	@Override
	public RepoDTO getRepo(Long idRepo) {
		return (RepoDTO) MapperUtils.map(this.repoRepository.findById(idRepo).get(), RepoDTO.class);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<RepoDTO> getRepos() {
		 return (List<RepoDTO>) MapperUtils.mapAsList(repoRepository.getAll(), new TypeToken<List<RepoDTO>>() {}.getType());
	}

}

