package gpi.cnf.management.service;

import java.util.List;

import gpi.cnf.management.dtos.UserDTO;
public interface UserService {

	public UserDTO createUser(UserDTO user);
	public boolean removeUser(Long idUser);
	public List<UserDTO> getUsers();
	public UserDTO updateUser(UserDTO user);
	public UserDTO getUser(Long idUser);
}
