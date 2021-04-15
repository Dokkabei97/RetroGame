package TeamProject;

import java.util.*;
import java.sql.*;

public class DBUtil {

	private static String url = "jdbc:oracle:thin:@localhost:1521:XE";  // @localhost �� ��ǥ�� ������ ip�� ���� �׸��� �������� sql �����Ͽ� ���̺� ���
	private static String user = "scott", pwd = "tiger";

	private DBUtil() {

	}

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loding Success");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Loding Fail" + e.getMessage());
		}
	}

	public static Connection getCon() throws SQLException {

		Connection con = DriverManager.getConnection(url, user, pwd);
		return con;
	}

}
