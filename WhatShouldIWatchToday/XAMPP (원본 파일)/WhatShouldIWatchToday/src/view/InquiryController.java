package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import domain.InquiryUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import util.AlertUtil;
import util.JDBCUtil;

import static view.MainController.localId;

public class InquiryController implements Initializable {

	public void initialize(URL location, ResourceBundle resources) {
		getInquiry();
		methodController.selectInquiry("select title from movie", "title", titleItems, title);
		seat.setItems(seatItems);
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

	// 예약 정보 가져오기
	@FXML
	private TableView<InquiryUtil> inquiryTableView;
	@FXML
	private TableColumn<InquiryUtil, String> inquiryTitle;
	@FXML
	private TableColumn<InquiryUtil, String> inquiryArea;
	@FXML
	private TableColumn<InquiryUtil, String> inquiryDate;
	@FXML
	private TableColumn<InquiryUtil, String> inquiryTime;
	@FXML
	private TableColumn<InquiryUtil, String> inquirySeat;

	ObservableList<InquiryUtil> inquiryObservableList;

	public void getInquiry() {
		methodController.deleteSeat82();
		checkInquiry();
	}

	// 예매 내역 변경
	@FXML
	private ComboBox<String> title = new ComboBox<String>();
	@FXML
	private ComboBox<String> area = new ComboBox<String>();
	@FXML
	private ComboBox<String> date = new ComboBox<String>();
	@FXML
	private ComboBox<String> time = new ComboBox<String>();
	@FXML
	private ComboBox<String> seat = new ComboBox<String>();

	private ObservableList<String> titleItems = FXCollections.observableArrayList();
	private ObservableList<String> areaItems = FXCollections.observableArrayList();
	private ObservableList<String> dateItems = FXCollections.observableArrayList();
	private ObservableList<String> timeItems = FXCollections.observableArrayList();
	private ObservableList<String> seatItems = FXCollections.observableArrayList("A1", "A2", "A3", "A4", "A5", "A6",
			"A7", "A8", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8",
			"D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8");

	// 지역 가져오기
	public void getInquiryChangeArea() {
		String choiceTitle = title.getValue();
		String movie_id = methodController.getMovieId(choiceTitle);
		methodController.selectInquiry("select area from cinema where movie_id='" + movie_id + "'", "area", areaItems,
				area);
	}

	// 날짜 가져오기
	public void getInquiryChangeDate() {
		String choiceArea = area.getValue();
		methodController.selectInquiry("select date from cinema where area='" + choiceArea + "'", "date", dateItems,
				date);
	}

	// 시간 가져오기
	public void getInquiryChangeTime() {
		String choiceTitle = title.getValue();
		String movie_id = methodController.getMovieId(choiceTitle);
		String choiceArea = area.getValue();
		String choiceDate = date.getValue();
		methodController.selectInquiry("select time from cinema where movie_id='" + movie_id + "' and area='"
				+ choiceArea + "' and date='" + choiceDate + "'", "time", timeItems, time);
	}

	// 좌석 가져오기
	public int getInquiryChangeSeat() {

		int seatCount = 0;

		String choiceTitle = title.getValue();
		String choiceArea = area.getValue();

		String movie_id = methodController.getMovieId(choiceTitle);
		String area_id = methodController.getAreaId(choiceArea);
		String choiceDate = date.getValue();
		String choiceTime = time.getValue();
		String choiceSeat = seat.getValue();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSql = "select seat from inquiry_list where movie_id='" + movie_id + "' and area_id='" + area_id
				+ "' and date='" + choiceDate + "' and time='" + choiceTime + "'";

		try {
			pstmt = con.prepareStatement(selectSql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String seat = rs.getString("seat");
				if (seat.equals(choiceSeat))
					seatCount++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seatCount;
	}

	// update
	public void changeInquiryInformation() {

		int choiceInquiryIndax = inquiryTableView.getSelectionModel().getSelectedIndex();
		if (choiceInquiryIndax < 0)
			AlertUtil.warningAlert("예매 내역을 선택해 주세요.", null);

		InquiryUtil iu = inquiryTableView.getSelectionModel().getSelectedItem();

		// 바꿀 값
		String choiceTitle = title.getValue();
		String choiceArea = area.getValue();

		String choiceMovieId = methodController.getMovieId(choiceTitle);
		String choiceAreaId = methodController.getAreaId(choiceArea);
		String choiceDate = date.getValue();
		String choiceTime = time.getValue();
		String choiceSeat = seat.getValue();

		// 원래 값
		String getTitle = iu.getTitle();
		String getArea = iu.getArea();

		String getMovieId = methodController.getMovieId(getTitle);
		String getAreaId = methodController.getAreaId(getArea);
		String getDate = iu.getDate();
		String getTime = iu.getTime();
		String getSeat = iu.getSeat();

		PreparedStatement pstmt = null;
		String updateSql1 = "UPDATE `inquiry_list` SET `user_id`='" + localId + "', `movie_id`='" + choiceMovieId
				+ "',`area_id`='" + choiceAreaId + "',`date`='" + choiceDate + "',`time`='" + choiceTime + "',`seat`='"
				+ choiceSeat + "' WHERE user_id='" + localId + "' and movie_id='" + getMovieId + "' and area_id='"
				+ getAreaId + "' and date='" + getDate + "' and time='" + getTime + "' and seat='" + getSeat + "'";

		int seatCount = getInquiryChangeSeat();

		try {
			if (seatCount == 0) {
				pstmt = con.prepareStatement(updateSql1);
				pstmt.executeUpdate();
				AlertUtil.informationAlert("변경에 성공했습니다.", null);
				checkInquiry();
			} else if (seatCount >= 1) {
				AlertUtil.warningAlert("이미 선택된 좌석입니다.", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 예매 취소
	public void InquiryCancel() {

		int choiceInquiryIndex = inquiryTableView.getSelectionModel().getSelectedIndex();
		if (choiceInquiryIndex < 0) {
			AlertUtil.warningAlert("예매 내역을 선택해 주세요.", null);
		}

		InquiryUtil iu = inquiryTableView.getSelectionModel().getSelectedItem();
		String title = iu.getTitle();
		String movie_id = methodController.getMovieId(title);
		String area = iu.getArea();
		String area_id = methodController.getAreaId(area);
		String date = iu.getDate();
		String time = iu.getTime();
		String seat = iu.getSeat();

		PreparedStatement pstmt = null;
		String deleteSql = "delete from inquiry_list where movie_id='" + movie_id + "' and area_id='" + area_id
				+ "' and date='" + date + "' and time='" + time + "' and seat='" + seat + "'";

		try {
			pstmt = con.prepareStatement(deleteSql);
			pstmt.executeUpdate();
			AlertUtil.informationAlert("예매가 취소되었습니다.", null);
			checkInquiry();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------------------------------------------------------

	// 예매 내역을 확인하는 메소드
	public void checkInquiry() {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSql = "select * from inquiry_list where user_id='" + localId + "'";

		try {
			pstmt = con.prepareStatement(selectSql);
			rs = pstmt.executeQuery();
			inquiryObservableList = FXCollections.observableArrayList();
			while (rs.next()) {
				String movie_id = rs.getString("movie_id");
				String title = methodController.getTitle(movie_id);
				String area_id = rs.getString("area_id");
				String area = methodController.getArea(area_id);
				String date = rs.getString("date");
				String time = rs.getString("time");
				String seat = rs.getString("seat");
				inquiryTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
				inquiryArea.setCellValueFactory(new PropertyValueFactory<>("area"));
				inquiryDate.setCellValueFactory(new PropertyValueFactory<>("date"));
				inquiryTime.setCellValueFactory(new PropertyValueFactory<>("time"));
				inquirySeat.setCellValueFactory(new PropertyValueFactory<>("seat"));
				inquiryObservableList.add(new InquiryUtil(title, area, date, time, seat));
				inquiryTableView.setItems(inquiryObservableList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
