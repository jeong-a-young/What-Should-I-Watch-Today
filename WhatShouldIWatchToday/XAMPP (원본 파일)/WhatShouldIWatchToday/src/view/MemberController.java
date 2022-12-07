package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import util.AlertUtil;
import util.JDBCUtil;

import static view.MainController.localId;
import static view.MainController.localName;

public class MemberController implements Initializable {

	public void initialize(URL location, ResourceBundle resources) {
		getMemberInformation();
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

	// 회원정보 가져오기
	@FXML
	private TextField memberName;
	@FXML
	private TextField memberDateOfBirth;
	@FXML
	private TextField memberPhoneNumber;
	@FXML
	private TextField memberId;
	@FXML
	private TextField memberPassword;

	public void getMemberInformation() {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSql = "select * from member where id='" + localId + "'";

		try {
			pstmt = con.prepareStatement(selectSql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberName.setText(rs.getString("name"));
				memberDateOfBirth.setText(rs.getString("date_of_birth"));
				memberPhoneNumber.setText(rs.getString("phone_number"));
				memberId.setText(rs.getString("id"));
				memberPassword.setText(rs.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원정보 변경하기
	public void changeMemberInformation() {

		String name = memberName.getText();
		String dateOfBirth = memberDateOfBirth.getText();
		String phoneNumber = memberPhoneNumber.getText();
		String password = memberPassword.getText();

		PreparedStatement pstmt = null;
		String updateSql = "update member set name='" + name + "',date_of_birth='" + dateOfBirth + "',phone_number='"
				+ phoneNumber + "',password='" + password + "' WHERE id='" + localId + "'";

		try {
			if (name.isEmpty() || phoneNumber.isEmpty() || password.isEmpty()) {
				AlertUtil.warningAlert("공백으로 입력되어있는지 확인해 주시길 바랍니다.", "변경 실패");
			} else if (phoneNumber.length() < 11) {
				AlertUtil.warningAlert("전화번호를 제대로 입력해 주시길 바랍니다.", "변경 실패");
			} else {
				pstmt = con.prepareStatement(updateSql);
				pstmt.executeUpdate();
				localName = name;
				AlertUtil.informationAlert("변경에 성공했습니다.", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			AlertUtil.warningAlert("형식대로 입력하셨는지 확인해 주시길 바랍니다.", "변경 실패");
		}
	}

	// 회원 탈퇴하기
	@FXML
	private Button signOutBtn;

	public void signOut() {

		PreparedStatement pstmt = null;
		String deleteSql = "delete from member where id='" + localId + "'";

		try {
			deleteInquiry();
			pstmt = con.prepareStatement(deleteSql);
			pstmt.executeUpdate();
			AlertUtil.informationAlert("탈퇴 처리되었습니다.", null);
			methodController.changeScene("/view/StartLayout.fxml", signOutBtn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원탈퇴 할 때 예매 내역 삭제
	public void deleteInquiry() {

		PreparedStatement pstmt = null;
		String deleteSql = "delete from inquiry_list where user_id='" + localId + "'";

		try {
			pstmt = con.prepareStatement(deleteSql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
