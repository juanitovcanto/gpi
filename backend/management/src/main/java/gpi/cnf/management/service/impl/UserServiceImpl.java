package gpi.cnf.management.service.impl;
import java.util.List;
import java.util.UUID;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import gpi.cnf.management.dtos.UserDTO;
import gpi.cnf.management.entities.UserEntity;
import gpi.cnf.management.mapper.MapperUtils;
import gpi.cnf.management.repository.UserRepository;
import gpi.cnf.management.service.UserService;


@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;
	
	private UserEntity mapUserEntity(UserDTO userDTO) {
		UserEntity user= new UserEntity();		
		user.setUsername(userDTO.getUsername());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setCargo(userDTO.getCargo());
		return user;
	}
	@Override
	public UserDTO createUser(UserDTO userDTO) {
		userDTO.setPassword(encoder.encode(userDTO.getPassword()));
		UserEntity userEntity = this.mapUserEntity(userDTO);
		userEntity =this.userRepository.save(userEntity);
		userDTO=(UserDTO) MapperUtils.map(userEntity, UserDTO.class);
		return userDTO;
	}
	
	@Override
	public boolean removeUser(Long idUser) {
		boolean delete=true;		
		this.userRepository.deleteById(idUser);		
		return delete;
	}
	@Override
	public UserDTO updateUser(UserDTO userDTO) {
		UserEntity userEntity = this.mapUserEntity(userDTO);
		userEntity.setUserId(userDTO.getUserId());
		userEntity =this.userRepository.save(userEntity);
		userDTO=(UserDTO) MapperUtils.map(userEntity, UserDTO.class);
		return userDTO;

	}
	@Override
	public UserDTO getUser(Long idUser) {
		return (UserDTO) MapperUtils.map(this.userRepository.findById(idUser).get(), UserDTO.class);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<UserDTO> getUsers() {
		 return (List<UserDTO>) MapperUtils.mapAsList(userRepository.getAll(), new TypeToken<List<UserDTO>>() {}.getType());
	}

}

