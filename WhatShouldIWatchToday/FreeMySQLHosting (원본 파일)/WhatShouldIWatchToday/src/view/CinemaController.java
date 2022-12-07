package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import domain.CinemaUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.JDBCUtil;

public class CinemaController implements Initializable {

	public void initialize(URL location, ResourceBundle resources) {
		getCinema();
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

	// 좌석 배치도 화면 전환
	@FXML
	private Button changeSeatBtn;
	private Stage seatStage;

	public void changeSeat() {
		methodController.popUpScene(changeSeatBtn, seatStage, "/view/SeatLayout.fxml", "Seating Chart");
	}

	// 영화관 정보 가져오기
	@FXML
	private TableView<CinemaUtil> cinemaTableView;
	@FXML
	private TableColumn<CinemaUtil, String> cinemaArea;
	@FXML
	private TableColumn<CinemaUtil, String> cinemaTitle;
	@FXML
	private TableColumn<CinemaUtil, String> cinemaDate;
	@FXML
	private TableColumn<CinemaUtil, String> cinemaTime;

	ObservableList<CinemaUtil> cinemaObservableList;

	public void getCinema() {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSql = "select * from cinema";

		try {
			pstmt = con.prepareStatement(selectSql);
			rs = pstmt.executeQuery();
			cinemaObservableList = FXCollections.observableArrayList();
			while (rs.next()) {
				String area = rs.getString("area");
				String movie_id = rs.getString("movie_id");
				String title = methodController.getTitle(movie_id);
				String date = rs.getString("date");
				String time = rs.getString("time");
				cinemaArea.setCellValueFactory(new PropertyValueFactory<>("area"));
				cinemaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
				cinemaDate.setCellValueFactory(new PropertyValueFactory<>("date"));
				cinemaTime.setCellValueFactory(new PropertyValueFactory<>("time"));
				cinemaObservableList.add(new CinemaUtil(area, title, date, time));
				cinemaTableView.setItems(cinemaObservableList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
