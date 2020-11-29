package TRMS.model;

public class Department {
	
	private int depId;
	
	private String dep_name;

	public int getDepId() {
		return depId;
	}

	public Department(int depId, String dep_name) {
		super();
		this.depId = depId;
		this.dep_name = dep_name;
	}

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

	public String getDep_name() {
		return dep_name;
	}

	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}

	@Override
	public String toString() {
		return "Department [depId=" + depId + ", dep_name=" + dep_name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + depId;
		result = prime * result + ((dep_name == null) ? 0 : dep_name.hashCode());
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
		Department other = (Department) obj;
		if (depId != other.depId)
			return false;
		if (dep_name == null) {
			if (other.dep_name != null)
				return false;
		} else if (!dep_name.equals(other.dep_name))
			return false;
		return true;
	}
	
	
}
