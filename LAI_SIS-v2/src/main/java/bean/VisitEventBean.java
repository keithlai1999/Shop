package bean;

public class VisitEventBean {
	private String ipAddress;
	private String day;
	private String bid;
	private String eventType;
	public VisitEventBean(String ipAddress, String day, String bid, String eventType) {
		// TODO Auto-generated constructor stub
		super();
		this.ipAddress = ipAddress;
		this.day = day;
		this.bid = bid;
		this.eventType = eventType;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

}
