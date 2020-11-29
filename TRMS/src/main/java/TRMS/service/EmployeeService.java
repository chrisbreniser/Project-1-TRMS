package TRMS.service;

import TRMS.model.Employee;

public interface EmployeeService {

	public Employee readEmployeeByUsername(String username);

	public Employee readEmployeeById(int id);	
}
