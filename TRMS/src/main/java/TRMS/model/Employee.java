package TRMS.model;

public class Employee {
	
	private int employee_id;
	
	private String first_name;
	
	private String last_name;
	
	private String user_name;
	
	private String pass_word;
	
	private String email;
	
	private int dep;
	
	private int reports_to;
	
	private boolean is_ben_co;
	
	private boolean is_dep_head;
	
	private boolean is_supervisor;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int employee_id, String first_name, String last_name, String user_name, String pass_word,
			String email, int dep, int reports_to, boolean is_ben_co, boolean is_dep_head, boolean is_supervisor) {
		super();
		this.employee_id = employee_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.user_name = user_name;
		this.pass_word = pass_word;
		this.email = email;
		this.dep = dep;
		this.reports_to = reports_to;
		this.is_ben_co = is_ben_co;
		this.is_dep_head = is_dep_head;
		this.is_supervisor = is_supervisor;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPass_word() {
		return pass_word;
	}

	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDep() {
		return dep;
	}

	public void setDep(int dep) {
		this.dep = dep;
	}

	public int getReports_to() {
		return reports_to;
	}

	public void setReports_to(int reports_to) {
		this.reports_to = reports_to;
	}

	public boolean getIs_ben_co() {
		return is_ben_co;
	}

	public void setIs_ben_co(boolean is_ben_co) {
		this.is_ben_co = is_ben_co;
	}

	public boolean getIs_dep_head() {
		return is_dep_head;
	}

	public void setIs_dep_head(boolean is_dep_head) {
		this.is_dep_head = is_dep_head;
	}

	public boolean getIs_supervisor() {
		return is_supervisor;
	}

	public void setIs_supervisor(boolean is_supervisor) {
		this.is_supervisor = is_supervisor;
	}

	@Override
	public String toString() {
		return "Employee [employee_id=" + employee_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", user_name=" + user_name + ", pass_word=" + pass_word + ", email=" + email + ", dep=" + dep
				+ ", reports_to=" + reports_to + ", is_ben_co=" + is_ben_co + ", is_dep_head=" + is_dep_head
				+ ", is_supervisor=" + is_supervisor + "]";
	}

	
}
