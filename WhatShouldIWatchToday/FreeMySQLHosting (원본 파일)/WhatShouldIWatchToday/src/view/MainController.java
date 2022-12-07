package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import domain.MovieUtil;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.AlertUtil;
import util.JDBCUtil;

public class MainController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nameLabel.setText(localName + "님,");
	}

	JDBCUtil db = new JDBCUtil();
	Connection con = db.getConnection();

	MethodController methodController = new MethodController();

	@FXML
	private Label nameLabel = new Label();

	// 1. Sign Up (완료)

	// 회원가입 화면 전환
	@FXML
	private Button changeSignUpBtn;

	public void changeSignUp() {
		methodController.changeScene("/view/SignUpLayout.fxml", changeSignUpBtn);
	}

	// 회원 등록
	@FXML
	private TextField signUpName;
	@FXML
	private TextField signUpDateOfBirth;
	@FXML
	private TextField signUpPhoneNumber;
	@FXML
	private TextField signUpId;
	@FXML
	private TextField signUpPassword;

	public void signUp() {

		String getName = signUpName.getText();
		String getDateOfBirth = signUpDateOfBirth.getText();
		String getPhoneNumber = signUpPhoneNumber.getText();
		String getId = signUpId.getText();
		String getPassword = signUpPassword.getText();

		PreparedStatement pstmt = null;
		String insertSql = "insert into member values(?,?,?,?,?)";

		try {
			if (getName.isEmpty() || getPhoneNumber.isEmpty() || getId.isEmpty() || getPassword.isEmpty()) {
				AlertUtil.warningAlert("공백으로 입력되어있는지\n확인해 주시길 바랍니다.", "가입 실패");
			} else if (getPhoneNumber.length() < 11) {
				AlertUtil.warningAlert("전화번호를 제대로 입력해 주시길 바랍니다.", "가입 실패");
			} else {
				pstmt = con.prepareStatement(insertSql);
				pstmt.setString(1, getName);
				pstmt.setString(2, getDateOfBirth);
				pstmt.setString(3, getPhoneNumber);
				pstmt.setString(4, getId);
				pstmt.setString(5, getPassword);
				pstmt.executeUpdate();
				AlertUtil.informationAlert("가입에 성공했습니다.", null);
				changeStart();
			}
		} catch (Exception e) {
			e.printStackTrace();
			AlertUtil.warningAlert("아이디가 중복이거나 형식대로 입력하셨는지\n확인해 주시길 바랍니다.", "가입 실패");
		}
	}

	// 2. Log in (완료)

	// 로그인 화면 전환
	@FXML
	private Button changeLoginBtn;

	public void changeLogin() {
		methodController.changeScene("/view/LoginLayout.fxml", changeLoginBtn);
	}

	// 로그인
	@FXML
	private Button loginBtn;
	@FXML
	private TextField loginId;
	@FXML
	private PasswordField loginPassword;

	public static String localId = "";
	public static String localName = "";

	public void login() {

		String id = loginId.getText();
		String password = loginPassword.getText();

		int count = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSql = "select name, id, password from member";

		try {
			pstmt = con.prepareStatement(selectSql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String getName = rs.getString("name");
				String getId = rs.getString("id");
				String getPassword = rs.getString("password");
				if (id.equals(getId) && password.equals(getPassword)) {
					count++;
					localId = id;
					localName = getName;
					methodController.changeScene("/view/MainLayout.fxml", loginBtn);
					break;
				}
			}
			if (count != 1) {
				AlertUtil.warningAlert("아이디와 비밀번호가 제대로 입력되었는지\n확인해 주시길 바랍니다.", "로그인 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 3. Member (완료)

	// 회원정보 화면 전환
	@FXML
	private Button changeMemberBtn;

	public void changeMember() {
		methodController.changeScene("/view/MemberLayout.fxml", changeMemberBtn);
	}

	// 4. Movie (완료)

	// 영화 화면 전환
	@FXML
	private Button changeMovieBtn;

	public void changeMovie() {
		methodController.changeScene("/view/MovieLayout.fxml", changeMovieBtn);
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

	// 5. Cinema (완료)

	// 영화관 화면 전환
	@FXML
	private Button changeCinemaBtn;

	public void changeCinema() {
		methodController.changeScene("/view/CinemaLayout.fxml", changeCinemaBtn);
	}

	// 6. Reservation (완료)

	// 영화 선택 화면 전환
	@FXML
	private Button changeMovieChoiceBtn;

	public void changeMovieChoice() {
		methodController.deleteSeat82();
		methodController.changeScene("/view/MovieChoiceLayout.fxml", changeMovieChoiceBtn);
	}

	// 좌석 선택하기
	@FXML
	private CheckBox A1;
	@FXML
	private CheckBox A2;
	@FXML
	private CheckBox A3;
	@FXML
	private CheckBox A4;
	@FXML
	private CheckBox A5;
	@FXML
	private CheckBox A6;
	@FXML
	private CheckBox A7;
	@FXML
	private CheckBox A8;
	@FXML
	private CheckBox B1;
	@FXML
	private CheckBox B2;
	@FXML
	private CheckBox B3;
	@FXML
	private CheckBox B4;
	@FXML
	private CheckBox B5;
	@FXML
	private CheckBox B6;
	@FXML
	private CheckBox B7;
	@FXML
	private CheckBox B8;
	@FXML
	private CheckBox C1;
	@FXML
	private CheckBox C2;
	@FXML
	private CheckBox C3;
	@FXML
	private CheckBox C4;
	@FXML
	private CheckBox C5;
	@FXML
	private CheckBox C6;
	@FXML
	private CheckBox C7;
	@FXML
	private CheckBox C8;
	@FXML
	private CheckBox D1;
	@FXML
	private CheckBox D2;
	@FXML
	private CheckBox D3;
	@FXML
	private CheckBox D4;
	@FXML
	private CheckBox D5;
	@FXML
	private CheckBox D6;
	@FXML
	private CheckBox D7;
	@FXML
	private CheckBox D8;
	@FXML
	private CheckBox E1;
	@FXML
	private CheckBox E2;
	@FXML
	private CheckBox E3;
	@FXML
	private CheckBox E4;
	@FXML
	private CheckBox E5;
	@FXML
	private CheckBox E6;
	@FXML
	private CheckBox E7;
	@FXML
	private CheckBox E8;

	public String getSeat() {

		String seat = "";

		if (!A1.isSelected() && !A2.isSelected() && !A3.isSelected() && !A4.isSelected() && !A5.isSelected()
				&& !A6.isSelected() && !A7.isSelected() && !A8.isSelected() && !B1.isSelected() && !B2.isSelected()
				&& !B3.isSelected() && !B4.isSelected() && !B5.isSelected() && !B6.isSelected() && !B7.isSelected()
				&& !B8.isSelected() && !C1.isSelected() && !C2.isSelected() && !C3.isSelected() && !C4.isSelected()
				&& !C5.isSelected() && !C6.isSelected() && !C7.isSelected() && !C8.isSelected() && !D1.isSelected()
				&& !D2.isSelected() && !D3.isSelected() && !D4.isSelected() && !D5.isSelected() && !D6.isSelected()
				&& !D7.isSelected() && !D8.isSelected() && !E1.isSelected() && !E2.isSelected() && !E3.isSelected()
				&& !E4.isSelected() && !E5.isSelected() && !E6.isSelected() && !E7.isSelected() && !E8.isSelected()) {
			AlertUtil.warningAlert("좌석을 선택해 주세요.", null);
		}

		if (A1.isSelected())
			seat = "A1";
		if (A2.isSelected())
			seat = "A2";
		if (A3.isSelected())
			seat = "A3";
		if (A4.isSelected())
			seat = "A4";
		if (A5.isSelected())
			seat = "A5";
		if (A6.isSelected())
			seat = "A6";
		if (A7.isSelected())
			seat = "A7";
		if (A8.isSelected())
			seat = "A8";
		if (B1.isSelected())
			seat = "B1";
		if (B2.isSelected())
			seat = "B2";
		if (B3.isSelected())
			seat = "B3";
		if (B4.isSelected())
			seat = "B4";
		if (B5.isSelected())
			seat = "B5";
		if (B6.isSelected())
			seat = "B6";
		if (B7.isSelected())
			seat = "B7";
		if (B8.isSelected())
			seat = "B8";
		if (C1.isSelected())
			seat = "C1";
		if (C2.isSelected())
			seat = "C2";
		if (C3.isSelected())
			seat = "C3";
		if (C4.isSelected())
			seat = "C4";
		if (C5.isSelected())
			seat = "C5";
		if (C6.isSelected())
			seat = "C6";
		if (C7.isSelected())
			seat = "C7";
		if (C8.isSelected())
			seat = "C8";
		if (D1.isSelected())
			seat = "D1";
		if (D2.isSelected())
			seat = "D2";
		if (D3.isSelected())
			seat = "D3";
		if (D4.isSelected())
			seat = "D4";
		if (D5.isSelected())
			seat = "D5";
		if (D6.isSelected())
			seat = "D6";
		if (D7.isSelected())
			seat = "D7";
		if (D8.isSelected())
			seat = "D8";
		if (E1.isSelected())
			seat = "E1";
		if (E2.isSelected())
			seat = "E2";
		if (E3.isSelected())
			seat = "E3";
		if (E4.isSelected())
			seat = "E4";
		if (E5.isSelected())
			seat = "E5";
		if (E6.isSelected())
			seat = "E6";
		if (E7.isSelected())
			seat = "E7";
		if (E8.isSelected())
			seat = "E8";

		return seat;
	}

	// 예약하기
	public void reservation() {

		// 영화 확인
		String movie_id = methodController.selectValue("movie_id", "inquiry_list", "seat", "82", "movie_id");

		// 영화관 지역 확인
		String area_id = methodController.selectValue("area_id", "inquiry_list", "seat", "82", "area_id");

		// 날짜 확인
		String date = methodController.selectValue("date", "inquiry_list", "seat", "82", "date");

		// 시간 확인
		String time = methodController.selectValue("time", "inquiry_list", "seat", "82", "time");

		// 좌석표 확인
		String getSeat = getSeat();

		int seatCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String seatSelectSql = "select seat from inquiry_list where movie_id='" + movie_id + "' and area_id='" + area_id
				+ "' and date='" + date + "' and time='" + time + "'";

		try {
			pstmt = con.prepareStatement(seatSelectSql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String seat = rs.getString("seat");
				if (seat.equals(getSeat))
					seatCount++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// update
		pstmt = null;
		String updateSql = "update inquiry_list set seat='" + getSeat + "' WHERE movie_id='" + movie_id
				+ "' and area_id='" + area_id + "' and date='" + date + "' and time='" + time + "' and seat='82'";

		try {
			if (seatCount == 0) {
				pstmt = con.prepareStatement(updateSql);
				pstmt.executeUpdate();
				AlertUtil.informationAlert("예약에 성공했습니다.", null);
			} else if (seatCount >= 1) {
				AlertUtil.warningAlert("이미 선택된 좌석입니다.", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 7. Inquiry (완료)

	// 예약 조회 화면 전환
	@FXML
	private Button changeInquiryBtn;

	public void changeInquiry() {
		methodController.changeScene("/view/InquiryLayout.fxml", changeInquiryBtn);
	}

	// ---------------------------------------------------------------------------------------------------------------

	// 시작 화면 전환
	@FXML
	private Button changeStartBtn;

	public void changeStart() {
		methodController.changeScene("/view/StartLayout.fxml", changeStartBtn);
	}

	// 메인 화면 전환
	@FXML
	private Button changeMainBtn;

	public void changeMain() {
		methodController.changeScene("/view/MainLayout.fxml", changeMainBtn);
	}

	// Click Me!
	@FXML
	private Button changeClickMeBtn;
	private Stage clickMeStage;

	public void changeClickMe() {
		methodController.popUpScene(changeClickMeBtn, clickMeStage, "/view/ClickMeLayout.fxml", "들켰다");
	}
}
