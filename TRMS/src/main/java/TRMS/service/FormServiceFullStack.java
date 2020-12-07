package TRMS.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import TRMS.dao.EmployeeDao;
import TRMS.dao.EmployeeDaoPostgres;
import TRMS.dao.FormDao;
import TRMS.dao.FormDaoPostgres;
import TRMS.model.Employee;
import TRMS.model.Form;

public class FormServiceFullStack implements FormService {
	
	private static Logger log = Logger.getRootLogger();
	
	FormDao formDao = new FormDaoPostgres();
	
	EmployeeDao employeeDao = new EmployeeDaoPostgres();
	
	EmployeeService employeeService = new EmployeeServiceFullStack();
	
	@Override
	public String createFrom(Form form) {
		log.info("FormServiceFullStack.createForm[Received " + form.toString() + " in Service. Setting reimbursment ammount based on event_type Calling Dao]");
		
		String status = "success";
		
		Employee formUser = employeeService.readEmployeeById(form.getEmployeeId());
		// setup approvals based on user status
		if(formUser.getIs_dep_head()) {
			System.out.println("Setting form up: User was dep head: setting two approvals to true");
			form.setSupervisorApproved(true);
			form.setDepHeadApproved(true);
		} else if(formUser.getIs_supervisor()) {
			System.out.println("Setting form up: User was supervisor: setting one approval to true");
			form.setSupervisorApproved(true);
		}
		
		// set reimbursement amount based on type
		switch(form.getEventType()) {
		case "university_course":
			form.setReimbursmentAmount(form.getEventCost() * .8);
			break;
			
		case "seminar":
			form.setReimbursmentAmount(form.getEventCost() * .6);
			break;
			
		case "certification_preparation_class":
			form.setReimbursmentAmount(form.getEventCost() * .75);
			break;
			
		case "certification":
			form.setReimbursmentAmount(form.getEventCost());
			break;
			
		case "technical_training":
			form.setReimbursmentAmount(form.getEventCost() * .9);
			break;
			
		case "other":
			form.setReimbursmentAmount(form.getEventCost() * .3);
			break;
			
		default:
			form.setReimbursmentAmount(0);
		}
		
		if(form.getReimbursmentAmount() + formUser.getPending_funds() + formUser.getApproved_funds() < 1000) {
			log.info("FormServiceFullStack.createForm[form.getReimbursmentAmount() + formUser.getPending_funds() + formUser.getApproved_funds(): "
					+ form.getReimbursmentAmount() + "+" + formUser.getPending_funds() + "+" + formUser.getApproved_funds()+ "]");
			log.info("FormServiceFullStack.createForm[Form is setup for insertion, calling dao. Form: " + form.toString() + "]");
			
			double formAmount = form.getReimbursmentAmount();
			double userPending = formUser.getPending_funds();
			formUser.setPending_funds(userPending + formAmount);
			employeeDao.updateEmployee(formUser, formUser.getEmployee_id());
			
			formDao.insertForm(form);
		} else {
			status = "invalid funds";
		}
			
		return status;
	}

	@Override
	public List<Form> readForms() {
		log.info("FormServiceFullStack.readForm[Calling Dao]");

		List<Form> forms = formDao.selectForms();
		
		return forms;
	}
	
	@Override
	public Form readFormById(int formId) {
		log.info("FormServiceFullStack.readFormById[Calling Dao]");
		
		List<Form> forms = formDao.selectForms();
		for(Form f : forms) {
			if(f.getFormId() == formId) {
				log.info("FormServiceFullStack.readFormById[Found form: " + f.toString() + "]");
				return f;
			}
		}
		
		return null;
	}

	@Override
	public boolean updateForm(int formId, Form form) {
		log.info("FormServiceFullStack.updateForm[Received id: " 
				+ formId + " and form: " 
				+ form.toString() + ", calling formDao]");
		
		return formDao.updateForm(formId, form);		
	}
	
	@Override
	public List<Form> readPendingForms(int EmployeeId) {
		// get all forms
		log.info("FormServiceFullStack.readPendingForms[Received EmployeeId: " + EmployeeId + ", calling dao]");

		List<Form> forms = formDao.selectForms();
		
		List<Form> vettedForms = new ArrayList<Form>();
		
		// filter out forms that don't match employee id
		log.info("FormServiceFullStack.readPendingForms[Vetting Forms]");
		for(Form f : forms) {
			if(f.getEmployeeId() == EmployeeId) {
				vettedForms.add(f);
			}
		}
		
		log.info("FormServiceFullStack.readPendingForms[Vetted forms: " + vettedForms.toString() + "]");
		// return forms
		return vettedForms;
	}
	
	@Override
	public Set<Form> readAssignedForms(String username) {
		// get all forms and holder forms for the vetted list
		log.info("FormServiceFullStack.readAssignedForms[Calling Dao]");

		List<Form> forms = formDao.selectForms();
		Set<Form> vettedForms = new HashSet<Form>();
		
		// get user based on id to determine status
		Employee currentUser = employeeService.readEmployeeByUsername(username);
		
		System.out.println("In readAssignedFroms: pulled in currentUser: " + currentUser.toString());
		
		// Determine the employee status
		if(currentUser.getIs_ben_co()) {
			//check list for forms that have all approval but ben_co
			System.out.println("User was a Ben_Co");
			for(Form f : forms) {
				System.out.println("Checking: " + f.toString());
				if(f.isSupervisorApproved() == true 
						&& f.isDepHeadApproved() == true 
//						&& f.isBenCoApproved() == false 
						&& f.getEmployeeId() != currentUser.getEmployee_id()
						&& (f.getStatus().equals("pending") || (f.getStatus().equals("pending-final") && f.getGrade() != null))) {
					System.out.println("Added!");
					vettedForms.add(f);
				}
			}
			
		}
		else if(currentUser.getIs_dep_head()) {
			//check list of forms that have all approval except ben_co and supervisor
			System.out.println("User was a Dep Head");
			for(Form f : forms) {
//				System.out.println("Checking: " + f.toString());
				Employee formUser = employeeService.readEmployeeById(f.getEmployeeId());
				if(formUser.getDep() == currentUser.getDep() 
						&& f.isSupervisorApproved() == true 
						&& f.isDepHeadApproved() == false 
						&& f.isBenCoApproved() == false
						&& f.getStatus().equals("pending")) {
//					System.out.println("Added!");
					vettedForms.add(f);
				}
			}
		}
		else if(currentUser.getIs_supervisor()) {
			//check list of forms that do not have supervisor approval
			System.out.println("User was a Supervisor");
			for(Form f : forms) {
//				System.out.println("Checking: " + f.toString());
				Employee formUser = employeeService.readEmployeeById(f.getEmployeeId());
				if(formUser.getDep() == currentUser.getDep() 
						&& f.isSupervisorApproved() == false 
						&& f.isDepHeadApproved() == false 
						&& f.isBenCoApproved() == false
						&& f.getStatus().equals("pending")) {
//					System.out.println("Added!");
					vettedForms.add(f);
				}
			}
		}
		return vettedForms;
	}

	@Override
	public boolean approveFormSupervisor(int formId) {
		
		log.info("FormServiceFullStack.approveFormSupervisor[Received id: " + formId + "]");
		
		FormDaoPostgres formDao = new FormDaoPostgres();
		
		List<Form> forms = formDao.selectForms();
		forms = formDao.selectForms();
		
		// search through list until we find our formId and then call formDao and return
		for(Form f : forms) {
			if(f.getFormId() == formId) {
				log.info("FormServiceFullStack.approveFormSupervisor[Found form, updating approval status and callind dao]");
				f.setSupervisorApproved(true);
				formDao.updateForm(formId, f);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean approveFormDepHead(int formId) {
		FormDaoPostgres formDao = new FormDaoPostgres();
		
		List<Form> forms = formDao.selectForms();
		forms = formDao.selectForms();
		
		// search through list until we find our formId and then call formDao and return
		for(Form f : forms) {
			if(f.getFormId() == formId) {
				f.setDepHeadApproved(true);
				formDao.updateForm(formId, f);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean approveFormBenCo(int formId) {
		FormDaoPostgres formDao = new FormDaoPostgres();
		
		List<Form> forms = formDao.selectForms();
		forms = formDao.selectForms();
				
		// search through list until we find our formId and then set to approved, call formDao and return
		for(Form f : forms) {
			if(f.getFormId() == formId) {
				
				if(f.getGrade() == null) {
					f.setBenCoApproved(true);
					f.setStatus("pending-final");
					formDao.updateForm(formId, f);
					return true;
				} else {
					Employee employee = employeeService.readEmployeeById(f.getEmployeeId());
					double formAmount = f.getReimbursmentAmount();
					double userPending = employee.getPending_funds();
					double userApproved = employee.getApproved_funds();
					employee.setApproved_funds(userApproved + formAmount); 
					employee.setPending_funds(userPending - formAmount);
					employeeDao.updateEmployee(employee, employee.getEmployee_id());
					f.setStatus("approved");
					formDao.updateForm(formId, f);
					return true;
				}
				
			}
		}
		return false;
	}
	
	@Override
	public boolean rejectForm(int formId, String rejectionReason) {
		
		FormDaoPostgres formDao = new FormDaoPostgres();
		
		List<Form> forms = formDao.selectForms();
		forms = formDao.selectForms();
		
		// search through list until we find our formId and then set to rejected, call formDao and return
		for(Form f : forms) {
			if(f.getFormId() == formId) {
				f.setRejected(true);
				f.setStatus("rejected");
				f.setRejReason(rejectionReason);
				Employee employee = employeeService.readEmployeeById(f.getEmployeeId());
				double formAmount = f.getReimbursmentAmount();
				employee.setPending_funds(employee.getPending_funds() - formAmount);
				employeeDao.updateEmployee(employee, employee.getEmployee_id());
				formDao.updateForm(formId, f);
				return true;
			}
		}
		return false;
	}

}
