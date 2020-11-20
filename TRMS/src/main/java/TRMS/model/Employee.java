package TRMS.model;

public class Employee {
	
	/*
	 * SQL DATA:
	  	employee_id serial not null,
		first_name varchar(255),
		last_name varchar(255),
		user_name varchar(255),
		email varchar(255),
		dep_id int,
		reports_to int,
		is_ben_co bool,
		is_dep_head bool,
	 * 
	 */
	
	private int employee_id;
	
	private String first_name;
	
	private String last_name;
	
	private String user_name;
	
	private String email;
	
	private int dep_id;
	
	private int reports_to;
	
	private boolean is_ben_co;
	
	private boolean is_dep_head;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int employee_id, String first_name, String last_name, String user_name, String email, int dep_id,
			int reports_to, boolean is_ben_co, boolean is_dep_head) {
		super();
		this.employee_id = employee_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.user_name = user_name;
		this.email = email;
		this.dep_id = dep_id;
		this.reports_to = reports_to;
		this.is_ben_co = is_ben_co;
		this.is_dep_head = is_dep_head;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDep_id() {
		return dep_id;
	}

	public void setDep_id(int dep_id) {
		this.dep_id = dep_id;
	}

	public int getReports_to() {
		return reports_to;
	}

	public void setReports_to(int reports_to) {
		this.reports_to = reports_to;
	}

	public boolean isIs_ben_co() {
		return is_ben_co;
	}

	public void setIs_ben_co(boolean is_ben_co) {
		this.is_ben_co = is_ben_co;
	}

	public boolean isIs_dep_head() {
		return is_dep_head;
	}

	public void setIs_dep_head(boolean is_dep_head) {
		this.is_dep_head = is_dep_head;
	}

	@Override
	public String toString() {
		return "Employee [employee_id=" + employee_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", user_name=" + user_name + ", email=" + email + ", dep_id=" + dep_id + ", reports_to=" + reports_to
				+ ", is_ben_co=" + is_ben_co + ", is_dep_head=" + is_dep_head + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dep_id;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + employee_id;
		result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + (is_ben_co ? 1231 : 1237);
		result = prime * result + (is_dep_head ? 1231 : 1237);
		result = prime * result + ((last_name == null) ? 0 : last_name.hashCode());
		result = prime * result + reports_to;
		result = prime * result + ((user_name == null) ? 0 : user_name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (dep_id != other.dep_id)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (employee_id != other.employee_id)
			return false;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (is_ben_co != other.is_ben_co)
			return false;
		if (is_dep_head != other.is_dep_head)
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		if (reports_to != other.reports_to)
			return false;
		if (user_name == null) {
			if (other.user_name != null)
				return false;
		} else if (!user_name.equals(other.user_name))
			return false;
		return true;
	}
	
	

}
