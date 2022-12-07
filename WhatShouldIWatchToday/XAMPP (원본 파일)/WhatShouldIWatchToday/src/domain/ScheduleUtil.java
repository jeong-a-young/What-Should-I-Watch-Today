package domain;

public class ScheduleUtil {

	private String area;
	private String date;
	private String time;
	
	public ScheduleUtil(String area, String date, String time) {
		this.area = area;
		this.date = date;
		this.time = time;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
