package gpi.cnf.management.service.impl;
import java.util.List;
import java.util.UUID;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import gpi.cnf.management.dtos.CargoUsuarioDTO;
import gpi.cnf.management.entities.CargoUsuarioEntity;
import gpi.cnf.management.mapper.MapperUtils;
import gpi.cnf.management.repository.CargoUsuarioRepository;
import gpi.cnf.management.service.CargoUsuarioService;


@Service("cargoUsuarioService")
public class CargoUsuarioServiceImpl implements CargoUsuarioService {
	@Autowired
	CargoUsuarioRepository cargoUsuarioRepository;
	
	private CargoUsuarioEntity mapCargoUsuarioEntity(CargoUsuarioDTO cargoUsuarioDTO) {
		CargoUsuarioEntity cargoUsuario= new CargoUsuarioEntity();		
		cargoUsuario.setCargo(cargoUsuarioDTO.getCargo());
		return cargoUsuario;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<CargoUsuarioDTO> getCargoUsuarios() {
		 return (List<CargoUsuarioDTO>) MapperUtils.mapAsList(cargoUsuarioRepository.getAll(), new TypeToken<List<CargoUsuarioDTO>>() {}.getType());
	}

}

