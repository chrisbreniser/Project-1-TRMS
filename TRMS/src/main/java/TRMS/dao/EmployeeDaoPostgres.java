package TRMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import TRMS.model.Department;
import TRMS.model.Employee;
import TRMS.util.ConnectionUtil;
import kotlin.collections.ArrayDeque;

public class EmployeeDaoPostgres implements EmployeeDao {
	
	private static Logger log = Logger.getRootLogger();
	
	private PreparedStatement preparedStatement;
	
	private ConnectionUtil connUtil = new ConnectionUtil();

	@Override
	public Employee selectEmployeeById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> selectAllEmployees() {
		
		log.info("EmployeeDao.selectAllEmployees[Received request, creating 'select *' statement]");
		
		List<Employee> employees = new ArrayList<Employee>();		
		
		String sql = "select * from employee";
		
		try(Connection conn = connUtil.createConnection()){
			preparedStatement = conn.prepareStatement(sql);
			
			log.info("EmployeeDaoPostgres.selectEmployees[Created prepared statement, attempting to execute]");
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next() ) {
				employees.add(new Employee(
						rs.getInt("employee_id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("user_name"),
						rs.getString("pass_word"),
						rs.getString("email"),
						rs.getInt("dep_id"),
						rs.getInt("reports_to"),
						rs.getBoolean("is_ben_co"),
						rs.getBoolean("is_dep_head"),
						rs.getBoolean("is_supervisor")
						));
			}
			
			log.info("EmployeeDaoPostgres.selectEmployees[Result of query as a list of employees: " 
					+ employees.toString() + "]");
			
		} catch (Exception e) {
			log.info("EmployeeDaoPostgres.selectEmployees[In catch block, SQLException: " 
					+ e.getMessage() 
					+ "]");
		}
		
		return employees;
	}

	@Override
	public List<Employee> selectEmployeeByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateEmployee(Employee e, int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEmployee(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
