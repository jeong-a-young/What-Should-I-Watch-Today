package domain;

public class MovieUtil {

	private String movie_id;
	private String title;
	private String genre;
	
	public MovieUtil(String movie_id, String title, String genre) {
		this.movie_id = movie_id;
		this.title = title;
		this.genre = genre;
	}

	public String getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
