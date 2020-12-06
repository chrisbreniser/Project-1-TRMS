package TRMS.controller;

import org.apache.log4j.Logger;

import TRMS.model.Employee;
import TRMS.service.AuthService;
import TRMS.service.AuthServiceHardCoded;
import TRMS.service.EmployeeService;
import TRMS.service.EmployeeServiceFullStack;
import io.javalin.http.Context;

public class AuthController {
	
	private static Logger log = Logger.getRootLogger();
	
	private AuthService auth = new AuthServiceHardCoded();
	
	private EmployeeService employeeService = new EmployeeServiceFullStack();

	private Employee currentUser = new Employee();
	
	public void login(Context ctx) {
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		boolean authenticated = auth.authenticateUser(currentUser, username, password);
		if (authenticated) {
			currentUser = employeeService.readEmployeeByUsername(username);
			log.info("AuthController.login[Successfully logged in as " + currentUser.toString() + "]");
			ctx.cookieStore("username", currentUser.getUser_name());
			ctx.cookieStore("employee_id", currentUser.getEmployee_id());
			ctx.cookieStore("dep_id", currentUser.getDep());
			ctx.cookieStore("reports_to", currentUser.getReports_to());
//			ctx.cookieStore("is_supervisor", currentUser.getIs_supervisor());
//			ctx.cookieStore("is_dep_head", currentUser.getIs_dep_head());
//			ctx.cookieStore("is_ben_co", currentUser.getIs_ben_co());
			ctx.cookieStore("security", auth.createToken(username));
			
			ctx.status(200);
			ctx.redirect("main_menu.html");
		} else {
			ctx.status(401);
			ctx.redirect("login.html?error=failed-login");
		}
	}
	
	public boolean checkUser(Context ctx) {
	
		if(auth.validateToken(ctx.cookieStore("security")) != null) {
			log.info("AuthController.checkUser[good token for user: " + ctx.cookie("username") + "]");
			return true;
		} else {
			log.info("AuthController.checkUser[bad token]");
			return false;
		}
	}
}
