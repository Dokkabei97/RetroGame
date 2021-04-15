package TeamProject;

import java.util.*;
import java.sql.*;

public class DBUtil {

	private static String url = "jdbc:oracle:thin:@localhost:1521:XE";  // @localhost 를 발표때 선생님 ip로 변경 그리고 실행전에 sql 실행하여 테이블 등록
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
