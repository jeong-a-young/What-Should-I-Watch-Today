package domain;

public class CinemaUtil {

	private String area;
	private String title;
	private String date;
	private String time;
	
	public CinemaUtil(String area, String title, String date, String time) {
		this.area = area;
		this.title = title;
		this.date = date;
		this.time = time;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setString(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
