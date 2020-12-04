package TRMS.model;

public class Form {
	
	private int formId;
	
	private int employeeId;
	
	private String eventType;
	
	private String eventDate;
	
	private String eventTime;
	
	private String eventLocation;
	
	private String eventDescription;
	
	private double eventCost;
	
	private byte[] eventAttach;
	
	private String justification;
	
	private String gradingFormat;
	
	private String grade;
	
	private byte[] preApprovalAttach;
	
	private double hoursMissed;
	
	private double reimbursmentAmount;
	
	private String status;
	
	private boolean supervisorApproved;
	
	private boolean depHeadApproved;
	
	private boolean benCoApproved;
	
	private boolean rejected;
	
	private String rejReason;

	public Form() {
		super();
	}

	public Form(int formId, int employeeId, String eventType, String eventDate, String eventTime, String eventLocation,
			String eventDescription, double eventCost, byte[] eventAttach, String justification,
			String gradingFormat, String grade, byte[] preApprovalAttach, double hoursMissed,
			double reimbursmentAmount, String status, boolean supervisorApproved, boolean depHeadApproved,
			boolean benCoApproved, boolean rejected, String rejReason) {
		super();
		this.formId = formId;
		this.employeeId = employeeId;
		this.eventType = eventType;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.eventLocation = eventLocation;
		this.eventDescription = eventDescription;
		this.eventCost = eventCost;
		this.eventAttach = eventAttach;
		this.justification = justification;
		this.gradingFormat = gradingFormat;
		this.grade = grade;
		this.preApprovalAttach = preApprovalAttach;
		this.hoursMissed = hoursMissed;
		this.reimbursmentAmount = reimbursmentAmount;
		this.status = status;
		this.supervisorApproved = supervisorApproved;
		this.depHeadApproved = depHeadApproved;
		this.benCoApproved = benCoApproved;
		this.rejected = rejected;
		this.rejReason = rejReason;
	}
	
	public Form(int employeeId, String eventType, String eventDate, String eventTime, String eventLocation,
			String eventDescription, double eventCost, byte[] eventAttach, String justification,
			String gradingFormat, byte[] preApprovalAttach, double hoursMissed) {
		super();
		this.formId = -1;
		this.employeeId = employeeId;
		this.eventType = eventType;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.eventLocation = eventLocation;
		this.eventDescription = eventDescription;
		this.eventCost = eventCost;
		this.eventAttach = eventAttach;
		this.justification = justification;
		this.gradingFormat = gradingFormat;
		this.grade = null;
		this.preApprovalAttach = preApprovalAttach;
		this.hoursMissed = hoursMissed;
		this.reimbursmentAmount = 0;
		this.status = "pending";
		this.supervisorApproved = false;
		this.depHeadApproved = false;
		this.benCoApproved = false;
		this.rejected = false;
		this.rejReason = null;
	}

	public int getFormId() {
		return formId;
	}

	public void setFormId(int formId) {
		this.formId = formId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public double getEventCost() {
		return eventCost;
	}

	public void setEventCost(double eventCost) {
		this.eventCost = eventCost;
	}

	public byte[] getEventAttach() {
		return eventAttach;
	}

	public void setEventAttach(byte[] eventAttach) {
		this.eventAttach = eventAttach;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public String getGradingFormat() {
		return gradingFormat;
	}

	public void setGradingFormat(String gradingFormat) {
		this.gradingFormat = gradingFormat;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public byte[] getPreApprovalAttach() {
		return preApprovalAttach;
	}

	public void setPreApprovalAttach(byte[] preApprovalAttach) {
		this.preApprovalAttach = preApprovalAttach;
	}

	public double getHoursMissed() {
		return hoursMissed;
	}

	public void setHoursMissed(double hoursMissed) {
		this.hoursMissed = hoursMissed;
	}

	public double getReimbursmentAmount() {
		return reimbursmentAmount;
	}

	public void setReimbursmentAmount(double reimbursmentAmount) {
		this.reimbursmentAmount = reimbursmentAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isSupervisorApproved() {
		return supervisorApproved;
	}

	public void setSupervisorApproved(boolean supervisorApproved) {
		this.supervisorApproved = supervisorApproved;
	}

	public boolean isDepHeadApproved() {
		return depHeadApproved;
	}

	public void setDepHeadApproved(boolean depHeadApproved) {
		this.depHeadApproved = depHeadApproved;
	}

	public boolean isBenCoApproved() {
		return benCoApproved;
	}

	public void setBenCoApproved(boolean benCoApproved) {
		this.benCoApproved = benCoApproved;
	}

	public boolean isRejected() {
		return rejected;
	}

	public void setRejected(boolean rejected) {
		this.rejected = rejected;
	}

	public String getRejReason() {
		return rejReason;
	}

	public void setRejReason(String rejReason) {
		this.rejReason = rejReason;
	}

	@Override
	public String toString() {
		return "Form [formId=" + formId + ", employeeId=" + employeeId + ", eventType=" + eventType + ", eventDate="
				+ eventDate + ", eventTime=" + eventTime + ", eventLocation=" + eventLocation + ", eventDescription="
				+ eventDescription + ", eventCost=" + eventCost + ", eventAttach=" + eventAttach + ", justification="
				+ justification + ", gradingFormat=" + gradingFormat + ", grade=" + grade + ", preApprovalAttach="
				+ preApprovalAttach + ", hoursMissed=" + hoursMissed + ", reimbursmentAmount=" + reimbursmentAmount
				+ ", status=" + status + ", supervisorApproved=" + supervisorApproved + ", depHeadApproved="
				+ depHeadApproved + ", benCoApproved=" + benCoApproved + ", rejected=" + rejected + ", rejReason="
				+ rejReason + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (benCoApproved ? 1231 : 1237);
		result = prime * result + (depHeadApproved ? 1231 : 1237);
		result = prime * result + employeeId;
		result = prime * result + ((eventAttach == null) ? 0 : eventAttach.hashCode());
		long temp;
		temp = Double.doubleToLongBits(eventCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((eventDate == null) ? 0 : eventDate.hashCode());
		result = prime * result + ((eventDescription == null) ? 0 : eventDescription.hashCode());
		result = prime * result + ((eventLocation == null) ? 0 : eventLocation.hashCode());
		result = prime * result + ((eventTime == null) ? 0 : eventTime.hashCode());
		result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result + formId;
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + ((gradingFormat == null) ? 0 : gradingFormat.hashCode());
		temp = Double.doubleToLongBits(hoursMissed);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((justification == null) ? 0 : justification.hashCode());
		result = prime * result + ((preApprovalAttach == null) ? 0 : preApprovalAttach.hashCode());
		temp = Double.doubleToLongBits(reimbursmentAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((rejReason == null) ? 0 : rejReason.hashCode());
		result = prime * result + (rejected ? 1231 : 1237);
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + (supervisorApproved ? 1231 : 1237);
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
		Form other = (Form) obj;
		if (benCoApproved != other.benCoApproved)
			return false;
		if (depHeadApproved != other.depHeadApproved)
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (eventAttach == null) {
			if (other.eventAttach != null)
				return false;
		} else if (!eventAttach.equals(other.eventAttach))
			return false;
		if (Double.doubleToLongBits(eventCost) != Double.doubleToLongBits(other.eventCost))
			return false;
		if (eventDate == null) {
			if (other.eventDate != null)
				return false;
		} else if (!eventDate.equals(other.eventDate))
			return false;
		if (eventDescription == null) {
			if (other.eventDescription != null)
				return false;
		} else if (!eventDescription.equals(other.eventDescription))
			return false;
		if (eventLocation == null) {
			if (other.eventLocation != null)
				return false;
		} else if (!eventLocation.equals(other.eventLocation))
			return false;
		if (eventTime == null) {
			if (other.eventTime != null)
				return false;
		} else if (!eventTime.equals(other.eventTime))
			return false;
		if (eventType == null) {
			if (other.eventType != null)
				return false;
		} else if (!eventType.equals(other.eventType))
			return false;
		if (formId != other.formId)
			return false;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		if (gradingFormat == null) {
			if (other.gradingFormat != null)
				return false;
		} else if (!gradingFormat.equals(other.gradingFormat))
			return false;
		if (Double.doubleToLongBits(hoursMissed) != Double.doubleToLongBits(other.hoursMissed))
			return false;
		if (justification == null) {
			if (other.justification != null)
				return false;
		} else if (!justification.equals(other.justification))
			return false;
		if (preApprovalAttach == null) {
			if (other.preApprovalAttach != null)
				return false;
		} else if (!preApprovalAttach.equals(other.preApprovalAttach))
			return false;
		if (Double.doubleToLongBits(reimbursmentAmount) != Double.doubleToLongBits(other.reimbursmentAmount))
			return false;
		if (rejReason == null) {
			if (other.rejReason != null)
				return false;
		} else if (!rejReason.equals(other.rejReason))
			return false;
		if (rejected != other.rejected)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (supervisorApproved != other.supervisorApproved)
			return false;
		return true;
	}

}
