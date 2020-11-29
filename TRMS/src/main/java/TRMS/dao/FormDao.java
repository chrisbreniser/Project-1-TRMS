package TRMS.dao;

import java.util.List;

import TRMS.model.Form;
import TRMS.util.ConnectionUtil;

public interface FormDao {
	
	public void setConnUtil(ConnectionUtil connUtil);
	
	public void insertForm(Form form);
	
	public List<Form> selectForms();
	
	public boolean updateForm(int formId, Form form);
	
	

}
