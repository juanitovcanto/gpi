
package gpi.cnf.management.dtos;

import lombok.Data;
import java.util.List;
@Data

public class RamaResponseDTO {
	public RamaResponseDTO(){
	
	}
	public RamaResponseDTO(RamaDTO rama,List<RepoDTO> repo){
		this.repo = repo;
		this.rama = rama;
	}
	private RamaDTO rama;
	private List<RepoDTO> repo;
	
} 
