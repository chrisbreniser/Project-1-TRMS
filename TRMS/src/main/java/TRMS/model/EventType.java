package TRMS.model;

public class EventType {
	
	private int eventId;
	
	private String eventType;
	
	private double coverageDec;

	public EventType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EventType(int eventId, String eventType, double coverageDec) {
		super();
		this.eventId = eventId;
		this.eventType = eventType;
		this.coverageDec = coverageDec;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public double getCoverageDec() {
		return coverageDec;
	}

	public void setCoverageDec(double coverageDec) {
		this.coverageDec = coverageDec;
	}

	@Override
	public String toString() {
		return "EventType [eventId=" + eventId + ", eventType=" + eventType + ", coverageDec=" + coverageDec + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(coverageDec);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + eventId;
		result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
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
		EventType other = (EventType) obj;
		if (Double.doubleToLongBits(coverageDec) != Double.doubleToLongBits(other.coverageDec))
			return false;
		if (eventId != other.eventId)
			return false;
		if (eventType == null) {
			if (other.eventType != null)
				return false;
		} else if (!eventType.equals(other.eventType))
			return false;
		return true;
	}
	
}
