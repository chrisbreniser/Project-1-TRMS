package TRMS.service;

import java.util.List;
import java.util.Set;

import TRMS.dao.FormDao;
import TRMS.dao.FormDaoPostgres;
import TRMS.model.Form;

public interface FormService {
	
	FormDao formDao = new FormDaoPostgres();
	
	public String createFrom(Form form);

	public List<Form> readForms();
	
	public List<Form> readPendingForms(int EmployeeId); 
	
	public Set<Form> readAssignedForms(String username);
		
	public boolean updateForm(int formId, Form form);
	
	public boolean approveFormSupervisor(int formId);
	
	public boolean approveFormDepHead(int formId);
	
	public boolean approveFormBenCo(int formId);

	public boolean rejectForm(int formId, String rejectionReason);

	Form readFormById(int formId);
	
}
