package TRMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.log4j.Logger;

import TRMS.model.Form;
import TRMS.util.ConnectionUtil;
import io.javalin.http.UploadedFile;


public class FormDaoPostgres implements FormDao {

	private static Logger log = Logger.getRootLogger();
	
	private PreparedStatement preparedStatement;
	
	private ConnectionUtil connUtil = new ConnectionUtil();
	
	public FormDaoPostgres() {
	}
	
	public FormDaoPostgres(ConnectionUtil connUtil) {
		this.connUtil = connUtil;
	}
	
	@Override
	public void setConnUtil(ConnectionUtil connUtil) {
		this.connUtil = connUtil;
	}

	@Override
	public void insertForm(Form form) {
		log.info("FormDaoPostgres.insertFrom[Received " + form.toString() + " in Dao. Creating insert statement");
		
		// changed position 7 and 10 to null in order to figure
		String sql = "insert into reimbursement_form "
				+ "(employee_id, event_type, event_date, event_time, event_location, event_description, "
				+ "event_attach, event_cost, justification, grading_format, grade, pre_approval_attach, "
				+ "hours_missed, reimbursment_amount, status, supervisor_approval, "
				+ "dep_head_approval, ben_co_approval, rejected, rej_reason) "
				+ "values (?, ?::event_type_enum, ?::date, ?::time, ?, ?, ?, ?, ?, ?, null, ?, ?, ?, ?, ?, ?, false, false, null);";
		
		try(Connection conn = connUtil.createConnection()){
			conn.setAutoCommit(false);
			preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			log.info("FormDaoPostgres.insertFrom[Preparing insert statement with received form]");
			
			preparedStatement.setInt(1, form.getEmployeeId());
			preparedStatement.setString(2, form.getEventType());
			preparedStatement.setString(3, form.getEventDate());
			preparedStatement.setString(4, form.getEventTime());
			preparedStatement.setString(5, form.getEventLocation());
			preparedStatement.setString(6, form.getEventDescription());
			preparedStatement.setBytes(7, form.getEventAttach());
			preparedStatement.setObject(8, form.getEventCost());
			preparedStatement.setString(9, form.getJustification());
			preparedStatement.setString(10, form.getGradingFormat());
			preparedStatement.setBytes(11, form.getPreApprovalAttach());
			preparedStatement.setDouble(12, form.getHoursMissed());
			preparedStatement.setDouble(13, form.getReimbursmentAmount());
			preparedStatement.setString(14, form.getStatus());
			preparedStatement.setBoolean(15, form.isSupervisorApproved());
			preparedStatement.setBoolean(16, form.isDepHeadApproved());
			
			log.info("FormDaoPostgres.insertForm[In try block: Attempting to execute:" + preparedStatement + "]");
			
			int rowsAffected = preparedStatement.executeUpdate();
			log.info("FormDaoPostgres.insertForm[In try block: Effected: " + rowsAffected + " rows(s)]");
			
			conn.commit();
			conn.setAutoCommit(true);
			
			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                form.setFormId(generatedKeys.getInt(1));
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
			log.info("FormDaoPostgres.insertForm[In try block: Created form with id:" + form.getFormId() + "]");
			
		} catch (SQLException e) {
			log.info("FormDaoPostgres.insertForm[In catch block: SQLException: " 
					+ e.getMessage() 
					+ "]");
		}
	}

	@Override
	public List<Form> selectForms() {
		log.info("FormDaoPostgres.readForm[Received request, creating select * statement]");
		
		List<Form> forms = new ArrayList<Form>();
		
		String sql = "select * from reimbursement_form";
		
		try(Connection conn = connUtil.createConnection()){
			preparedStatement = conn.prepareStatement(sql);
			
			log.info("FormDaoPostgres.selectForms[Created prepared statement, attempting to execute]");
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				forms.add(new Form(
						rs.getInt("form_id"),
						rs.getInt("employee_id"),
						rs.getString("event_type"),
						rs.getString("event_date"),
						rs.getString("event_time"),
						rs.getString("event_location"),
						rs.getString("event_description"),
						rs.getDouble("event_cost"),
						rs.getBytes("event_attach"),
						rs.getString("justification"),
						rs.getString("grading_format"),
						rs.getString("grade"),
						rs.getBytes("pre_approval_attach"),
						rs.getDouble("hours_missed"),
						rs.getDouble("reimbursment_amount"),
						rs.getString("status"),
						rs.getBoolean("supervisor_approval"),
						rs.getBoolean("dep_head_approval"),
						rs.getBoolean("ben_co_approval"),
						rs.getBoolean("rejected"),
						rs.getString("rej_reason")
						));
			}
			
			log.info("FormDaoPostgres.selectForms[Result of query as a list of forms: " 
					+ forms.toString() + "]");
			
		}catch (Exception e) {
			log.info("FormDaoPostgres.selectForms[In catch block, SQLException: " 
					+ e.getMessage() 
					+ "]");
		}
		
		return forms;
	}

	@Override
	public boolean updateForm(int formId, Form form) {
		log.info("FormDaoPostgres.updateForm[Received formId: " 
				+ formId + " and form:" + form.toString() 
				+ ", parsing data for update]");
		
		boolean updated = false;
		
		String sql = "update reimbursement_form set "
				+ "employee_id=?, event_type=?::event_type_enum, event_date=?::date, event_time=?::time, event_location=?, event_description=?, "
				+ "event_attach=?, event_cost=? justification=?, grading_format=?, grade=?, pre_approval_attach=?, "
				+ "hours_time_missed=?, reimbursment_amount=?, status=?, supervisor_approval=?, "
				+ "dep_head_approval=?, ben_co_approval=?, rejected=?, rej_reason=?) "
				+ "where form_id = ?;";
		
		
		try(Connection conn = connUtil.createConnection()) {
			conn.setAutoCommit(false);
			preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			log.info("FormDaoPostgres.insertFrom[Preparing insert statement with received form]");
			
			preparedStatement.setInt(1, form.getEmployeeId());
			preparedStatement.setString(2, form.getEventType());
			preparedStatement.setString(3, form.getEventDate());
			preparedStatement.setString(4, form.getEventTime());
			preparedStatement.setString(5, form.getEventLocation());
			preparedStatement.setString(6, form.getEventDescription());
			preparedStatement.setBytes(7, form.getEventAttach());
			preparedStatement.setObject(8, form.getEventCost());
			preparedStatement.setString(9, form.getJustification());
			preparedStatement.setString(10, form.getGradingFormat());
			preparedStatement.setBytes(11, form.getPreApprovalAttach());
			preparedStatement.setDouble(12, form.getHoursMissed());
			preparedStatement.setDouble(13, form.getReimbursmentAmount());
			preparedStatement.setString(14, form.getStatus());
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	

}
