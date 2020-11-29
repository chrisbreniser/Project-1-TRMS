package TRMS.service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.auth0.jwt.algorithms.Algorithm;

import TRMS.dao.EmployeeDao;
import TRMS.dao.EmployeeDaoPostgres;
import TRMS.model.Employee;

public class AuthServiceJWT implements AuthService {

	Algorithm algorithmHS = Algorithm.HMAC256("My-Super-Dooper-Secret!");
	
	private static Logger log = Logger.getRootLogger();
	
	private static byte[] salt = new SecureRandom().getSeed(16);

	private Map<String, String> tokenRepo = new HashMap<>();
	
	private EmployeeDao employeeDao = new EmployeeDaoPostgres();
	
	@Override
	public boolean authenticateUser(Employee currentUser, String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String createToken(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String validateToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}
}
