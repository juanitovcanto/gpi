package gpi.cnf.management.dtos;

import lombok.Data;

@Data

public class UserDTO {
	private Long userId;	
	private String username; 
	private String lastName;	
	private String firstName;	
	private String email;	
	private String password;	
	private Long cargo;
} 
