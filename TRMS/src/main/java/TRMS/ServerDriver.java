package TRMS;

import TRMS.controller.EmployeeController;
import TRMS.controller.AuthController;
import io.javalin.Javalin;

public class ServerDriver {
	
	private static String HELLO_PATH = "/hello";
	private static String LOGIN_PATH = "/login";
	private static String EMPLOYEE_PATH = "/employee";
	
	private static AuthController authController = new AuthController();
	private static EmployeeController employeeController = new EmployeeController();
	
	public static void main(String[] ohWellHelloThere) {
		
		Javalin app = Javalin.create().start(9090);
		
		app.get(HELLO_PATH, ctx -> ctx.html("Hello World"));
		app.post(LOGIN_PATH, ctx -> authController.login(ctx));
		app.get(LOGIN_PATH, ctx -> authController.checkUser(ctx));
		app.post(EMPLOYEE_PATH, ctx -> employeeController.getAllEmployees(ctx));
		
	}

}
