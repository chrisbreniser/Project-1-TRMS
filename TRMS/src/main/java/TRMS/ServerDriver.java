package TRMS;

import TRMS.controller.EmployeeController;
import TRMS.controller.FormController;
import TRMS.controller.AuthController;
import io.javalin.Javalin;

public class ServerDriver {
	
	private static String HELLO_PATH = "/hello";
	private static String LOGIN_PATH = "/login";
	private static String EMPLOYEE_PATH = "/employee";
	private static String FORM_PATH = "/form";
	
	private static AuthController authController = new AuthController();
	private static EmployeeController employeeController = new EmployeeController();
	private static FormController formController = new FormController();
	
	public static void main(String[] ohWellHelloThere) {
		
		Javalin app = Javalin.create( config -> {
			config.addStaticFiles("/public");
		}).start(9090); //sets up and starts our server
		
		app.routes(() -> {
			app.get("/", ctx -> ctx.redirect("/login.html"));
			app.get(HELLO_PATH, ctx -> ctx.html("Hello World"));
			app.post(LOGIN_PATH, ctx -> authController.login(ctx));
			app.get(LOGIN_PATH, ctx -> authController.checkUser(ctx));
			app.get(EMPLOYEE_PATH, ctx -> employeeController.getAllEmployees(ctx));
			app.post(FORM_PATH, ctx -> formController.createForm(ctx));
			app.get(FORM_PATH + "/pending", ctx -> formController.readPendingForms(ctx));
			app.get(FORM_PATH + "/assigned", ctx -> formController.readAssignedForms(ctx));
			//fetch users submitted forms
			//fetch forms to approve by user
			app.put(FORM_PATH, ctx -> formController.updateForm(ctx));
		});
		
	}

}
