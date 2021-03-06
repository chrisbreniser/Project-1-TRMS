package TRMS.dao;

import TRMS.model.Employee;
import java.util.List;

public interface EmployeeDao {
	
	public Employee selectEmployeeById(int id);
	
	public List<Employee> selectAllEmployees();
	
	public List<Employee> selectEmployeeByTitle(String title); // maybe maybe not
	
	public boolean updateEmployee(Employee e, int id);
	
	public boolean deleteEmployee(int id);

}
