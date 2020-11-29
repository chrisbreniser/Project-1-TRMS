package TRMS.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import TRMS.dao.EmployeeDao;
import TRMS.dao.EmployeeDaoPostgres;
import TRMS.model.Employee;

public class AuthServiceHardCoded implements AuthService {
	
	private static Logger log = Logger.getRootLogger();
	
	private static byte[] salt = new SecureRandom().getSeed(16);

	private Map<String, String> tokenRepo = new HashMap<>();
	
	private EmployeeDao employeeDao = new EmployeeDaoPostgres();
	
	@Override
	public boolean authenticateUser(Employee currentUser, String username, String password) {
		
		// get all employees, and check if user and pass match any of them
		log.info("AuthSurviceHardCoded.authenticateUser[Checking if " + username + " and " + password + " is valid. Calling employeeDao for list of employees]");
		
		List<Employee> employees = employeeDao.selectAllEmployees();
		
		log.info("AuthSurviceHardCoded.authenticateUser[Got list of employees from Dao: " + employees.toString() + "]");
		
		for(Employee e : employees) {
			if(e.getUser_name().equals(username) && e.getPass_word().equals(password)) {
				log.info("AuthSurviceHardCoded.authenticateUser[Login successful for user: " + username + "]");
				currentUser = e;
				log.info("AuthSurviceHardCoded.authenticateUser[Created currentUser: " + currentUser + "]");
				return true;
			}
		}
		log.info("AuthSurviceHardCoded.authenticateUser[Login unsuccessful for user: " + username + "]");
		return false;
	}

	@Override
	public String createToken(String username) {
		String token = simpleHash(username);
		tokenRepo.put(token, username);
		return token;
	}

	@Override
	public String validateToken(String token) {
		return tokenRepo.get(token);
	}
	
	private String simpleHash(String username) {
		
		String hash = null;
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(salt);
			
			byte[] bytes = md.digest(username.getBytes());

			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(0));
			}
			
			hash = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hash;
	}

}
