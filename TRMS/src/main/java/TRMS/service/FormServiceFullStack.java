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
	
//	EmployeeDao employeeDao = new EmployeeDaoPostgres();
	
	EmployeeService employeeService = new EmployeeServiceFullStack();
	
	@Override
	public void createFrom(Form form) {
		log.info("FormServiceFullStack.createForm[Received " + form.toString() + " in Service. Setting reimbursment ammount based on event_type Calling Dao]");
		
		Employee currentUser = employeeService.readEmployeeById(form.getEmployeeId());
		// setup approvals based on user status
		if(currentUser.getIs_dep_head()) {
			form.setSupervisorApproved(true);
			form.setDepHeadApproved(true);
		} else if(currentUser.getIs_supervisor()) {
			form.setSupervisorApproved(true);
		}
		
		// set reimbursement amount based on type
		switch(form.getEventType()) {
		case "univercity_course":
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
		
		
		formDao.insertForm(form);
		
	}

	@Override
	public List<Form> readForms() {
		log.info("FormServiceFullStack.readForm[Calling Dao]");

		List<Form> forms = formDao.selectForms();
		
		return forms;
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
		log.info("FormServiceFullStack.readPendingForms[Calling Dao]");

		List<Form> forms = formDao.selectForms();
		
		// filter out forms that don't match employee id
		List<Form> vettedForms = new ArrayList<Form>();
		
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
				if(f.isSupervisorApproved() == true && f.isDepHeadApproved() == true && f.isBenCoApproved() == false) {
					System.out.println("Added!");
					vettedForms.add(f);
				}
			}
			
		}
		else if(currentUser.getIs_dep_head()) {
			//check list of forms that have all approval except ben_co and supervisor
			System.out.println("User was a Dep Head");
			for(Form f : forms) {
				System.out.println("Checking: " + f.toString());
				if(f.isSupervisorApproved() == true && f.isDepHeadApproved() == false && f.isBenCoApproved() == false) {
					System.out.println("Added!");
					vettedForms.add(f);
				}
			}
		}
		else if(currentUser.getIs_supervisor()) {
			//check list of forms that do not have supervisor approval
			System.out.println("User was a Supervisor");
			for(Form f : forms) {
				System.out.println("Checking: " + f.toString());
				if(f.isSupervisorApproved() == false && f.isDepHeadApproved() == false && f.isBenCoApproved() == false) {
					System.out.println("Added!");
					vettedForms.add(f);
				}
			}
		}
		
		
		return vettedForms;
	}

	@Override
	public boolean approveFormSupervisor() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean approveFormDepHead() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean approveFormBenCo() {
		// TODO Auto-generated method stub
		return false;
	}

}
