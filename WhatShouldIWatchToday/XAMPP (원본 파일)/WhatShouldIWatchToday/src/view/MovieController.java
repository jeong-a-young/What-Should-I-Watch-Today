package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import domain.MovieUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import util.JDBCUtil;

public class MovieController implements Initializable {

	public void initialize(URL location, ResourceBundle resources) {
		getMovie();
	}

	MethodController methodController = new MethodController();

	JDBCUtil db = new JDBCUtil();
	Connection con = db.getConnection();

	// 메인 화면 전환
	@FXML
	private Button changeMainBtn;

	public void changeMain() {
		methodController.changeScene("/view/MainLayout.fxml", changeMainBtn);
	}

	// 영화 정보 가져오기
	@FXML
	private TableView<MovieUtil> movieTableView;
	@FXML
	private TableColumn<MovieUtil, String> movieId;
	@FXML
	private TableColumn<MovieUtil, String> movieTitle;
	@FXML
	private TableColumn<MovieUtil, String> movieGenre;

	ObservableList<MovieUtil> movieObservableList;

	public void getMovie() {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectSql = "select * from movie";

		try {
			pstmt = con.prepareStatement(selectSql);
			rs = pstmt.executeQuery();
			movieObservableList = FXCollections.observableArrayList();
			while (rs.next()) {
				String movie_id = rs.getString("movie_id");
				String title = rs.getString("title");
				String genre = rs.getString("genre");
				movieId.setCellValueFactory(new PropertyValueFactory<>("movie_id"));
				movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
				movieGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
				movieObservableList.add(new MovieUtil(movie_id, title, genre));
				movieTableView.setItems(movieObservableList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
