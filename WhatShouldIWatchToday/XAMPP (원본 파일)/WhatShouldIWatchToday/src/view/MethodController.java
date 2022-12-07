package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.MovieUtil;
import domain.ScheduleUtil;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.JDBCUtil;

public class MethodController {

	JDBCUtil db = new JDBCUtil();
	Connection con = db.getConnection();

	// 화면 전환 메소드
	public void changeScene(String url, Button btn) {
		try {
			Parent main = FXMLLoader.load(getClass().getResource(url));
			Scene scene = new Scene(main);
			Stage primaryStage = (Stage) btn.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 팝업 화면 메소드
	public void popUpScene(Button btn, Stage stage, String url, String title) {

		Stage mainStage = (Stage) btn.getScene().getWindow();
		stage = new Stage(StageStyle.DECORATED);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(mainStage);

		try {
			Parent root = FXMLLoader.load(getClass().getResource(url));

			Scene sc = new Scene(root);
			sc.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			stage.setScene(sc);
			stage.setResizable(false);
			stage.setTitle(title);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// select 메소드
	public String selectValue(String selectValueName, String tableName, String conditionName, String conditionValue,
			String columnName) {

		String getValueBowl = "";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSql = "select " + selectValueName + " from " + tableName + " where " + conditionName + "='"
				+ conditionValue + "'";

		try {
			pstmt = con.prepareStatement(selectSql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				getValueBowl = rs.getString(columnName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getValueBowl;
	}

	// Movie ID 값을 돌려주는 메소드
	public String getMovieId(String title) {
		String movie_id = selectValue("movie_id", "movie", "title", title, "movie_id");
		return movie_id;
	}

	// 영화 제목을 돌려주는 메소드
	public String getTitle(String movie_id) {
		String title = selectValue("title", "movie", "movie_id", movie_id, "title");
		return title;
	}

	// 지역 ID 값을 돌려주는 메소드
	public String getAreaId(String area) {
		String area_id = selectValue("area_id", "cinema", "area", area, "area_id");
		return area_id;
	}

	// 영화관 지역을 돌려주는 메소드
	public String getArea(String area_id) {
		String area = selectValue("area", "cinema", "area_id", area_id, "area");
		return area;
	}

	// 선택한 영화의 Movie ID 값을 돌려주는 메소드
	public String choiceMovie(TableView<MovieUtil> titleName) {
		MovieUtil mu = titleName.getSelectionModel().getSelectedItem();
		String title = mu.getTitle();
		String movie_id = getMovieId(title);
		return movie_id;
	}

	// Inquiry List에서 Movie ID 값을 찾아 돌려주는 메소드
	public String getInquiryListMovieId() {
		String movie_id = selectValue("movie_id", "inquiry_list", "seat", "82", "movie_id");
		return movie_id;
	}

	// 선택한 스케줄의 지역 ID 값을 돌려주는 메소드
	public String choiceSchedule(TableView<ScheduleUtil> titleName) {
		ScheduleUtil su = titleName.getSelectionModel().getSelectedItem();
		String area = su.getArea();
		String area_id = getAreaId(area);
		return area_id;
	}

	// 좌석 번호가 '82'인 것을 삭제하는 메소드
	public void deleteSeat82() {

		PreparedStatement pstmt = null;
		String deleteSql = "delete from inquiry_list where seat='82'";

		try {
			pstmt = con.prepareStatement(deleteSql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 예매 내역 변경 기능에 쓰일 select 메소드
	public void selectInquiry(String selectSql, String columnName, ObservableList<String> itemsName,
			ComboBox<String> comboBoxName) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(selectSql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String getValueBowl = rs.getString(columnName);
				itemsName.add(getValueBowl);
				comboBoxName.setItems(itemsName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}