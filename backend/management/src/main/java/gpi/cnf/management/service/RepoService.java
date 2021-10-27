package gpi.cnf.management.service;

import java.util.List;

import gpi.cnf.management.dtos.RepoDTO;
public interface RepoService {

	public RepoDTO createRepo(RepoDTO repo);
	public boolean removeRepo(Long idRepo);
	public List<RepoDTO> getRepos();
	public RepoDTO updateRepo(RepoDTO repo);
	public RepoDTO getRepo(Long idRepo);
}
