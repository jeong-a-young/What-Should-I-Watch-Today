package domain;

public class InquiryUtil {

	private String title;
	private String area;
	private String date;
	private String time;
	private String seat;
	
	public InquiryUtil(String title, String area, String date, String time, String seat) {
		this.title = title;
		this.area = area;
		this.date = date;
		this.time = time;
		this.seat = seat;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}
	
}
