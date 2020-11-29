package TRMS.service;

import java.util.List;
import java.util.Set;

import TRMS.dao.FormDao;
import TRMS.dao.FormDaoPostgres;
import TRMS.model.Form;

public interface FormService {
	
	FormDao formDao = new FormDaoPostgres();
	
	public void createFrom(Form form);

	public List<Form> readForms();
	
	public List<Form> readPendingForms(int EmployeeId); 
	
	public Set<Form> readAssignedForms(String username);
		
	public boolean updateForm(int formId, Form form);
	
	public boolean deleteForm();
	
	public boolean approveFormSupervisor();
	
	public boolean approveFormDepHead();
	
	public boolean approveFormBenCo();
	
}
