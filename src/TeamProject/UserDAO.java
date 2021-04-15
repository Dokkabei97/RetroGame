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

	public boolean userLogin(UserDTO dto) { // �α���
		try {
			con = DBUtil.getCon();

			sql = "SELECT PWD FROM USERINFO WHERE ID = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId()); // SQL ������ ����
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString(1).equals(dto.getPwd())) {
					showMsg("�α��� ����");
					return true;
				} else if (!rs.getString(1).equals(dto.getPwd())) {
					showMsg("��й�ȣ�� Ʋ�Ƚ��ϴ�");
					return false;
				}
			} else {
				showMsg("���� ���̵��Դϴ�");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("userLogin()���� ����: " + e);

		} finally {
			close();
		}
		return false;
	}

	public boolean chkUserID(UserDTO dto) { // ���̵� �ߺ�üũ
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
			System.out.println("chkUserID()���� ����: " + e);

		} finally {
			close();
		}
		return false;
	}

	public boolean createAccount(UserDTO dto) { // ���� ����
		try {
			con = DBUtil.getCon();

			sql = "INSERT INTO USERINFO (IDX, ID, PWD, NAME, EMAIL, TEL, INDATE, ADMIN) "
					+ "VALUES(USERINFO_SEQ.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE, 0)";
			// ���� ������ �⺻ 0 ���� ������ �̸� ��ϵ� ���ΰ����� ���� ���������� ���������� ���� ����
			
			/* �̸���ĭ ���̵� ġ�� SQL ��Ͻ� '���̵�@team2.com' �ǰ� ����� 
			 * ����ó '-' ���� ���ڸ� ġ�� SQL ��Ͻ� '010-0000-0000' �ǰ� �����
			 * */
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getTel());

			int n = pstmt.executeUpdate();

			if (n > 0) {
				showMsg("ȸ�����Կ� �����߽��ϴ�");
				return true;
			} else {
				showMsg("ȸ�����Կ� �����߽��ϴ�");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("createAccount()���� ����: " + e);
			return false;
		} finally {
			close();
		}
	}

	// ȸ������ ���̵�ã�� ��й�ȣã��
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
				showMsg("���̵�: " + id);
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println("findID()���� ����: " + e);
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
				showMsg("��й�ȣ: " + pwd);
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println("findPWD()���� ����: " + e);
			return false;
		} finally {
			close();
		}
	}

	// ���� �������� ��ȸ/�˻� ����/����
	public ArrayList<UserDTO> inquiryUser() { // ���� ��ȸ
		try {
			con = DBUtil.getCon();

			sql = "SELECT IDX, ID, NAME, EMAIL, TEL, RANK, SCORE, INDATE, ADMIN FROM USERINFO ORDER BY IDX ASC";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			ArrayList<UserDTO> arr = null;
			arr = makeList(rs);
			return arr;

		} catch (SQLException e) {
			System.out.println("inquiryUser()���� ����: " + e);
			return null;
		} finally {
			close();
		}
	}

	private ArrayList<UserDTO> makeList(ResultSet rs) throws SQLException { // ��ȸ
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

	public UserDTO selectInfo(Integer idx) { // ���̺� ���ý�
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
			System.out.println("selectInfo()���� ����: " + e);
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
			 * �˻������ ������ showMsg("�ش� ������ �����ϴ�");
			 */

		} catch (SQLException e) {
			System.out.println("searchUser()���� ����: " + e);
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
				showMsg("������ �����Ͽ����ϴ�");
				return true;
			} else {
				showMsg("���� ������ �����߽��ϴ�");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("updateUser()���� ����: " + e);
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
				showMsg("������ �����߽��ϴ�");
				return true;
			} else {
				showMsg("���� ������ �����߽��ϴ�");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("deleteUser���� ����: " + e);
			return false;
		} finally {
			close();
		}
	}

	public boolean chkAdmin(UserDTO dto) { // ���� �Ǹ� Ȯ��
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
			System.out.println("chkAdmin()���� ����: " + e);
		} finally {
			close();
		}
		return false;
	}

	// ä�� / ���� ���� SQL
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
			System.out.println("userInfo()���� ����: " + e);
		}
	}

	public void chat(UserDTO dto) { //ä�ý� ���̵� �ҷ����� �뵵
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
			System.out.println("chat()���� ����: " + e);
		}
	}
	
	// ���� ����� ���� ���� sql 
	public void upScore(UserDTO dto) {
		try {
			con = DBUtil.getCon();

			sql = "UPDATE USERINFO SET SCORE = USERSCORE_SEQ.NEXTVAL WHERE IDX = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getIdx());
			
			int n = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("chat()���� ����: " + e);
		}
	}

	public void showMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
}

