package TRMS.service;

import TRMS.model.Employee;

public interface AuthService {
	
	public boolean authenticateUser(Employee currentUser, String username, String password);
	
	public String createToken(String username);
	
	public String validateToken(String token);

}
