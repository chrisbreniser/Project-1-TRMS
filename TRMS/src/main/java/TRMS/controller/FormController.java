package TRMS.controller;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import TRMS.model.Employee;
import TRMS.model.Form;
import TRMS.service.AuthService;
import TRMS.service.AuthServiceHardCoded;
import TRMS.service.EmployeeService;
import TRMS.service.EmployeeServiceFullStack;
import TRMS.service.FormService;
import TRMS.service.FormServiceFullStack;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

public class FormController {
	
	private static Logger log = Logger.getRootLogger();

	private static FormService formService = new FormServiceFullStack();
	
	private static EmployeeService employeeService = new EmployeeServiceFullStack();

	private static AuthController authController = new AuthController();
	
	private Employee currentUser;

	public void createForm(Context ctx) {
		
		log.info("FormController.createFrom[Received context " + ctx.toString() + " in controller. Parsing formParams");
		
		try {
			authController.checkUser(ctx);
			currentUser = employeeService.readEmployeeByUsername(ctx.cookieStore("username"));
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}
		
		// pull data out of ctx for form creation
		String event_type = ctx.formParam("event_type");		
		String event_date = ctx.formParam("event_date");
		String event_time = ctx.formParam("event_time");
		String event_location = ctx.formParam("event_location");
		String event_description = ctx.formParam("event_description");
		String grading_format = ctx.formParam("grading_format");
		double event_cost = Double.parseDouble(ctx.formParam("event_cost"));
		String justification = ctx.formParam("justification"); 
		double hours_missed = Double.parseDouble(ctx.formParam("hours_missed", "0"));

		// pull out an uploaded file if exists, otherwise, set to null
//		UploadedFile event_attach_upload = ctx.uploadedFile("event_attach"); 
		byte[] event_attach = null;
//		try {
//			if(event_attach_upload != null){
//				event_attach = event_attach_upload.component1().readAllBytes();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		// pull out an uploaded file if exists, otherwise, set to null
//		UploadedFile pre_app_attach_upload = ctx.uploadedFile("pre_app_attach"); 
		byte[] pre_app_attach = null;
//		try {
//			if(pre_app_attach_upload != null) {
//				pre_app_attach = pre_app_attach_upload.component1().readAllBytes();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		// create new form to be added to DB later
		Form form = new Form(
				currentUser.getEmployee_id(),
				event_type,
				event_date,
				event_time,
				event_location,
				event_description,
				event_cost,
				event_attach,
				justification,
				grading_format,
				pre_app_attach,
				hours_missed);
		
		// call create from service to add form to DB and get newly created id back in form object
		formService.createFrom(form);
		
		// check if form got a new id that was not -1 and return appropriate response
		if(form.getFormId() != -1) {
			ctx.status(201);
			ctx.html("Created: " + form.toString());
			ctx.redirect("check_pending_forms.html");
		} else {
			ctx.status(500);
			ctx.html("[ERROR: New ID was not generated by DB]");
		}
	}

	public void readPendingForms(Context ctx) {
		log.info("FormController.readPendingFroms[Received request and checking cookie for user: " + ctx.cookieStore("username") + "]");
		
		try {
			authController.checkUser(ctx);
			currentUser = employeeService.readEmployeeByUsername(ctx.cookieStore("username"));
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}
		
		log.info("FormController.readPendingFroms[Username found, getting forms from formService and returning as JSON]");
		
		List<Form> forms = formService.readPendingForms(currentUser.getEmployee_id());
		
		ctx.json(forms);
	}
	
	public void readAssignedForms(Context ctx) {
		log.info("FormController.readAssignedFroms[Received request and calling service]");
		
		log.info("FormController.readAssignedFroms[Checking cookie for user: " + ctx.cookieStore("username") + "]");
		
		try {
			authController.checkUser(ctx);
			currentUser = employeeService.readEmployeeByUsername(ctx.cookieStore("username"));
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}
		
		Set<Form> forms = null;
		
		if((boolean)ctx.cookieStore("is_ben_co") == true) {
			forms = formService.readAssignedForms(currentUser.getUser_name());
		}
		
		ctx.json(forms);
	}

	public void updateForm(Context ctx) {
		// TODO Auto-generated method stub
	}
	
}
