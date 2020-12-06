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
						rs.getBoolean("is_supervisor"),
						rs.getDouble("pending_funds"),
						rs.getDouble("approved_funds")
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
	public boolean updateEmployee(Employee employee, int id) {
		log.info("EmployeeDao.updateEmployee[Received request, creating 'select *' statement]");
		
		boolean updated = false;
		
		String sql = "update employee set first_name=?, last_name=?, email=?, dep_id=?, reports_to=?, pending_funds=?, approved_funds=? where employee_id = ?";
		
		try (Connection conn = connUtil.createConnection()) {
			
			conn.setAutoCommit(false);
			
			preparedStatement = conn.prepareStatement(sql);
			
			preparedStatement.setString(1, employee.getFirst_name());
			preparedStatement.setString(2, employee.getLast_name());
			preparedStatement.setString(3, employee.getEmail());
			preparedStatement.setInt(4, employee.getDep());
			preparedStatement.setInt(5, employee.getReports_to());
			preparedStatement.setDouble(6, employee.getPending_funds());
			preparedStatement.setDouble(7, employee.getApproved_funds());
			preparedStatement.setInt(8, id);
			
			int rowsAffected = preparedStatement.executeUpdate();
			
			log.info("EmployeeDaoPostgres.updateEmployee[Result of query as a list of employees: " 
					+ employee.toString() + "]");
			
			if(rowsAffected == 1) {
				updated = true;	
				conn.commit();
			} else {
				log.info("EmployeeDaoPostgres.updateEmployee[Incorrect update! Rows Affected: " + rowsAffected + ". Reverting changes]");
			}
			
			conn.setAutoCommit(true);
			
		} catch (Exception e1) {
			log.info("EmployeeDaoPostgres.updateEmployee[In catch block, SQLException: " 
					+ e1.getMessage() 
					+ "]");
		}
		
		return updated;
	}

	@Override
	public boolean deleteEmployee(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
