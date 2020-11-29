package TRMS.service;

import java.util.List;

import org.apache.log4j.Logger;

import TRMS.dao.EmployeeDao;
import TRMS.dao.EmployeeDaoPostgres;
import TRMS.model.Employee;

public class EmployeeServiceFullStack implements EmployeeService {
	
	private static Logger log = Logger.getRootLogger();
	
	EmployeeDao employeeDao = new EmployeeDaoPostgres();

	@Override
	public Employee readEmployeeByUsername(String username) {
		
		List<Employee> employees = employeeDao.selectAllEmployees();
		
		log.info("EmployeeServiceFullStack.readEmployeeByUsername[Received request for user, searching through list of all employees]");
		
		for(Employee e: employees) {
			if(e.getUser_name().equals(username)) {
				log.info("EmployeeServiceFullStack.readEmployeeByUsername[Found " + username + " returning that employee]");
				return e;
			}
		}
		log.info("EmployeeServiceFullStack.readEmployeeByUsername[Could not find " + username + " returning null");
		return null;
	}
	
	@Override
	public Employee readEmployeeById(int id) {
		
		List<Employee> employees = employeeDao.selectAllEmployees();
		
		log.info("EmployeeServiceFullStack.readEmployeeByUsername[Received request for employee, searching through list of all employees]");
		
		for(Employee e: employees) {
			if(e.getEmployee_id() == id) {
				log.info("EmployeeServiceFullStack.readEmployeeByUsername[Found employee with id " + id + ", returning " + e.getUser_name() + "]");
				return e;
			}
		}
		log.info("EmployeeServiceFullStack.readEmployeeByUsername[Could not find employee with id " + id + ", returning null");
		return null;
	}
}
