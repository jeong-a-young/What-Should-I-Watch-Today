package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import domain.ScheduleUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import util.AlertUtil;
import util.JDBCUtil;

public class ScheduleChoiceController implements Initializable {

	public void initialize(URL location, ResourceBundle resources) {
		getSchedule();
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

	// 영화 선택 화면 전환
	@FXML
	private Button changeMovieChoiceBtn;

	public void changeMovieChoice() {
		methodController.deleteSeat82();
		methodController.changeScene("/view/MovieChoiceLayout.fxml", changeMovieChoiceBtn);
	}

	// 스케줄 가져오기
	@FXML
	private TableView<ScheduleUtil> scheduleTableView;
	@FXML
	private TableColumn<ScheduleUtil, String> scheduleArea;
	@FXML
	private TableColumn<ScheduleUtil, String> scheduleDate;
	@FXML
	private TableColumn<ScheduleUtil, String> scheduleTime;

	ObservableList<ScheduleUtil> scheduleObservableList;

	public void getSchedule() {

		String movie_id = methodController.getInquiryListMovieId();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSql = "select * from cinema where movie_id='" + movie_id + "'";

		try {
			pstmt = con.prepareStatement(selectSql);
			rs = pstmt.executeQuery();
			scheduleObservableList = FXCollections.observableArrayList();
			while (rs.next()) {
				String area = rs.getString("area");
				String date = rs.getString("date");
				String time = rs.getString("time");
				scheduleArea.setCellValueFactory(new PropertyValueFactory<>("area"));
				scheduleDate.setCellValueFactory(new PropertyValueFactory<>("date"));
				scheduleTime.setCellValueFactory(new PropertyValueFactory<>("time"));
				scheduleObservableList.add(new ScheduleUtil(area, date, time));
				scheduleTableView.setItems(scheduleObservableList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 스케줄 선택하기
	@FXML
	private Button changeSeatChoiceBtn;

	public void insertSchedule() {

		int choiceScheduleIndax = scheduleTableView.getSelectionModel().getSelectedIndex();
		if (choiceScheduleIndax < 0)
			AlertUtil.warningAlert("스케줄을 선택해 주세요.", null);

		ScheduleUtil su = scheduleTableView.getSelectionModel().getSelectedItem();
		String area_id = methodController.choiceSchedule(scheduleTableView);
		String date = su.getDate();
		String time = su.getTime();

		PreparedStatement pstmt = null;
		String updateSql = "update inquiry_list set area_id='" + area_id + "',date='" + date + "',time='" + time
				+ "' WHERE seat='82'";

		try {
			pstmt = con.prepareStatement(updateSql);
			pstmt.executeUpdate();
			methodController.changeScene("/view/SeatChoiceLayout.fxml", changeSeatChoiceBtn);
		} catch (Exception e) {
			e.printStackTrace();
			AlertUtil.warningAlert("스케줄을 선택해 주세요.", null);
		}
	}
}
