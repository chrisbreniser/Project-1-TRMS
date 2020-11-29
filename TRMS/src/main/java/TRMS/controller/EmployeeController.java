package TRMS.controller;

import java.util.List;

import org.apache.log4j.Logger;

import TRMS.dao.EmployeeDao;
import TRMS.dao.EmployeeDaoPostgres;
import TRMS.model.Employee;
import io.javalin.http.Context;

public class EmployeeController {
	
	private static Logger log = Logger.getRootLogger();
	
	private EmployeeDao employeeDao = new EmployeeDaoPostgres();
	
	public void getAllEmployees(Context ctx) {
		
		log.info("EmployeeController.getAllEmployees[Received request and calling service]");
		
		List<Employee> employees = employeeDao.selectAllEmployees();
		
		ctx.html(employees.toString());
		ctx.json(employees.toString());
	}
}
