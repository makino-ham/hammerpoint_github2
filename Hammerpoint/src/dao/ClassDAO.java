package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ClassRoom;

public class ClassDAO {
	private final String DRIVER_NAME ="com.mysql.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/hammerpoint?useUnicode=true&characterEncoding=UTF-8";
	private final String DB_USER = "root";
	private final String DB_PASS = "earth";

	public List<ClassRoom> classListOut() {
		Connection conn = null;
		List<ClassRoom> classList = new ArrayList<ClassRoom>();

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//select文の準備
			String sql = "select class_ID, class_name from class;";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			//select文を実行し、結果表（Result)を取得
			ResultSet rs = pstmt.executeQuery();

			//結果表に格納されたレコードの内容を
			//BookDatインスタンスに設定し、ArrayListインスタンスに追加
			while(rs.next()) {
				int classId = rs.getInt("class_ID");
				String className = rs.getString("class_name");
				ClassRoom classroom = new ClassRoom(classId, className);
				classList.add(classroom);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return classList;
	}

}

