package TeamProject;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

public class UserDAO extends UserBase {

	String sql = "";

	public String infoId, chatId;
	public int infoRank, infoScore;

	public UserDAO() {

	}

	public boolean userLogin(UserDTO dto) { // 로그인
		try {
			con = DBUtil.getCon();

			sql = "SELECT PWD FROM USERINFO WHERE ID = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId()); // SQL 인젝션 방지
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString(1).equals(dto.getPwd())) {
					showMsg("로그인 성공");
					return true;
				} else if (!rs.getString(1).equals(dto.getPwd())) {
					showMsg("비밀번호가 틀렸습니다");
					return false;
				}
			} else {
				showMsg("없는 아이디입니다");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("userLogin()에서 예외: " + e);

		} finally {
			close();
		}
		return false;
	}

	public boolean chkUserID(UserDTO dto) { // 아이디 중복체크
		try {
			con = DBUtil.getCon();

			sql = "SELECT COUNT(*) CNT FROM USERINFO WHERE ID = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				int n = rs.getInt("CNT");
				if (n > 0) {
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println("chkUserID()에서 예외: " + e);

		} finally {
			close();
		}
		return false;
	}

	public boolean createAccount(UserDTO dto) { // 계정 생성
		try {
			con = DBUtil.getCon();

			sql = "INSERT INTO USERINFO (IDX, ID, PWD, NAME, EMAIL, TEL, INDATE, ADMIN) "
					+ "VALUES(USERINFO_SEQ.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE, 0)";
			// 어드민 권한은 기본 0 권한 수정은 미리 등록된 어드민계정을 통해 관리자전용 페이지에서 수정 가능
			
			/* 이메일칸 아이디만 치고 SQL 등록시 '아이디@team2.com' 되게 만들기 
			 * 연락처 '-' 없이 숫자만 치고 SQL 등록시 '010-0000-0000' 되게 만들기
			 * */
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getTel());

			int n = pstmt.executeUpdate();

			if (n > 0) {
				showMsg("회원가입에 성공했습니다");
				return true;
			} else {
				showMsg("회원가입에 실패했습니다");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("createAccount()에서 예외: " + e);
			return false;
		} finally {
			close();
		}
	}

	// 회원정보 아이디찾기 비밀번호찾기
	public boolean findID(UserDTO dto) {
		try {
			con = DBUtil.getCon();

			sql = "SELECT ID FROM USERINFO " + "WHERE NAME = ? AND EMAIL = ? AND TEL = ? ";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getTel());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String id = rs.getString("ID");
				showMsg("아이디: " + id);
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println("findID()에서 예외: " + e);
			return false;
		} finally {
			close();
		}

	}

	public boolean findPWD(UserDTO dto) {
		try {
			con = DBUtil.getCon();

			sql = "SELECT PWD FROM USERINFO WHERE ID = ? AND NAME = ? AND EMAIL= ? AND TEL = ? ";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getTel());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String pwd = rs.getString("PWD");
				showMsg("비밀번호: " + pwd);
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println("findPWD()에서 예외: " + e);
			return false;
		} finally {
			close();
		}
	}

	// 어드민 유저정보 조회/검색 삭제/수정
	public ArrayList<UserDTO> inquiryUser() { // 유저 조회
		try {
			con = DBUtil.getCon();

			sql = "SELECT IDX, ID, NAME, EMAIL, TEL, RANK, SCORE, INDATE, ADMIN FROM USERINFO ORDER BY IDX ASC";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			ArrayList<UserDTO> arr = null;
			arr = makeList(rs);
			return arr;

		} catch (SQLException e) {
			System.out.println("inquiryUser()에서 예외: " + e);
			return null;
		} finally {
			close();
		}
	}

	private ArrayList<UserDTO> makeList(ResultSet rs) throws SQLException { // 조회
		ArrayList<UserDTO> arr = new ArrayList<>();

		while (rs.next()) {
			int idx = rs.getInt("IDX");
			String id = rs.getString("ID");
			String name = rs.getString("NAME");
			String email = rs.getString("EMAIL");
			String tel = rs.getString("TEL");
			int rank = rs.getInt("RANK");
			int score = rs.getInt("SCORE");
			java.sql.Date date = rs.getDate("INDATE");
			int admin = rs.getInt("ADMIN");

			UserDTO dto = new UserDTO(idx, id, name, email, tel, rank, score, date, admin);
			arr.add(dto);
		}
		return arr;
	}

	public UserDTO selectInfo(Integer idx) { // 테이블 선택시
		try {
			con = DBUtil.getCon();

			sql = "SELECT IDX, ID, NAME, EMAIL, TEL, RANK, SCORE, INDATE, ADMIN FROM USERINFO WHERE IDX = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();

			ArrayList<UserDTO> arr = makeList(rs);
			if (arr != null && arr.size() == 1) {
				UserDTO dto = arr.get(0);
				return dto;
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println("selectInfo()에세 예외: " + e);
			return null;
		} finally {
			close();
		}
	}

	public ArrayList<UserDTO> searchUser(UserDTO dto) {
		try {
			con = DBUtil.getCon();

			sql = "SELECT IDX, ID, NAME, EMAIL, TEL, RANK, SCORE, INDATE, ADMIN FROM USERINFO WHERE IDX = ? ";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, dto.getIdx());

			rs = pstmt.executeQuery();

			ArrayList<UserDTO> arr = null;
			arr = makeList(rs);
			return arr;

			/*
			 * 검색결과가 없을때 showMsg("해당 유저는 없습니다");
			 */

		} catch (SQLException e) {
			System.out.println("searchUser()에서 예외: " + e);
		} finally {
			close();
		}
		return null;
	}

	public boolean updateUser(UserDTO dto) {
		try {
			con = DBUtil.getCon();

			sql = "UPDATE USERINFO SET NAME = ?, EMAIL = ?, TEL = ?, RANK = ?, SCORE = ?, ADMIN = ? WHERE IDX = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getTel());
			pstmt.setInt(4, dto.getRank());
			pstmt.setInt(5, dto.getScore());
			pstmt.setInt(6, dto.getAdmin());
			pstmt.setInt(7, dto.getIdx());

			int n = pstmt.executeUpdate();

			if (n > 0) {
				showMsg("정보를 수정하였습니다");
				return true;
			} else {
				showMsg("정보 수정을 실팰했습니다");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("updateUser()에세 예외: " + e);
			return false;
		} finally {
			close();
		}
	}

	public boolean deleteUser(UserDTO dto) {
		try {
			con = DBUtil.getCon();

			sql = "DELETE FROM USERINFO WHERE IDX = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, dto.getIdx());
			int n = pstmt.executeUpdate();

			if (n > 0) {
				showMsg("정보를 삭제했습니다");
				return true;
			} else {
				showMsg("정보 삭제를 실패했습니다");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("deleteUser에세 예외: " + e);
			return false;
		} finally {
			close();
		}
	}

	public boolean chkAdmin(UserDTO dto) { // 어드민 권리 확인
		try {
			con = DBUtil.getCon();

			sql = "SELECT ADMIN FROM USERINFO WHERE ID = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getId());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				int n = rs.getInt("ADMIN");
				if (n > 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			System.out.println("chkAdmin()에서 예외: " + e);
		} finally {
			close();
		}
		return false;
	}

	// 채팅 / 유저 인포 SQL
	public void userInfo(UserDTO dto) {
		try {
			con = DBUtil.getCon();

			sql = "SELECT ID, RANK, SCORE FROM USERINFO WHERE ID = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getId());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				infoId = rs.getString("ID");
				infoRank = rs.getInt("RANK");
				infoScore = rs.getInt("SCORE");
			}

		} catch (SQLException e) {
			System.out.println("userInfo()에서 예외: " + e);
		}
	}

	public void chat(UserDTO dto) { //채팅시 아이디 불러오는 용도
		try {
			con = DBUtil.getCon();

			sql = "SELECT ID FROM USERINFO WHERE ID = ?";
			
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getId());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				chatId = rs.getString("ID");
			}

		} catch (SQLException e) {
			System.out.println("chat()에서 예외: " + e);
		}
	}
	
	// 게임 실행시 점수 증가 sql 
	public void upScore(UserDTO dto) {
		try {
			con = DBUtil.getCon();

			sql = "UPDATE USERINFO SET SCORE = USERSCORE_SEQ.NEXTVAL WHERE IDX = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getIdx());
			
			int n = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("chat()에서 예외: " + e);
		}
	}

	public void showMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
}

