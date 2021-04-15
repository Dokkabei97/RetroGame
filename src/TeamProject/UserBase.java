package TeamProject;

import java.sql.*;

public class UserBase {

	protected Connection con = null;
	protected PreparedStatement pstmt = null;
	protected ResultSet rs = null;
	
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println("close()에사 에서 예외: " + e.getMessage());
		}
	}
	

}
