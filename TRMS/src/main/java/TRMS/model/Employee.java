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
	
	private double pending_funds;
	
	private double approved_funds;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int employee_id, String first_name, String last_name, String user_name, String pass_word,
			String email, int dep, int reports_to, boolean is_ben_co, boolean is_dep_head, boolean is_supervisor,
			double pending_funds, double approved_funds) {
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
		this.pending_funds = pending_funds;
		this.approved_funds = approved_funds;
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

	public double getPending_funds() {
		return pending_funds;
	}

	public void setPending_funds(double pending_funds) {
		this.pending_funds = pending_funds;
	}

	public double getApproved_funds() {
		return approved_funds;
	}

	public void setApproved_funds(double approved_funds) {
		this.approved_funds = approved_funds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(approved_funds);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + dep;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + employee_id;
		result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + (is_ben_co ? 1231 : 1237);
		result = prime * result + (is_dep_head ? 1231 : 1237);
		result = prime * result + (is_supervisor ? 1231 : 1237);
		result = prime * result + ((last_name == null) ? 0 : last_name.hashCode());
		result = prime * result + ((pass_word == null) ? 0 : pass_word.hashCode());
		temp = Double.doubleToLongBits(pending_funds);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (Double.doubleToLongBits(approved_funds) != Double.doubleToLongBits(other.approved_funds))
			return false;
		if (dep != other.dep)
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
		if (is_supervisor != other.is_supervisor)
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		if (pass_word == null) {
			if (other.pass_word != null)
				return false;
		} else if (!pass_word.equals(other.pass_word))
			return false;
		if (Double.doubleToLongBits(pending_funds) != Double.doubleToLongBits(other.pending_funds))
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

	@Override
	public String toString() {
		return "Employee [employee_id=" + employee_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", user_name=" + user_name + ", pass_word=" + pass_word + ", email=" + email + ", dep=" + dep
				+ ", reports_to=" + reports_to + ", is_ben_co=" + is_ben_co + ", is_dep_head=" + is_dep_head
				+ ", is_supervisor=" + is_supervisor + ", pending_funds=" + pending_funds + ", approved_funds="
				+ approved_funds + "]";
	}
	
}
