package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Kyouka;

public class KyoukaDAO {

	private final String DRIVER_NAME ="com.mysql.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/hammerpoint?useUnicode=true&characterEncoding=UTF-8";
	private final String DB_USER = "root";
	private final String DB_PASS = "earth";

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~^
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~^

public List<Kyouka> kyoukaListOut(int classId) {
	//データベース接続
	Connection conn = null;
	List<Kyouka> kyoukaList = new ArrayList<Kyouka>();
	try {
		//データベースへ接続
		Class.forName("com.mysql.jdbc.Driver");
		Class.forName(DRIVER_NAME);
		conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

		//insert文の準備
		String sql = "select kyouka_name,kyouka_ID from kyouka where class_ID=?;";
		PreparedStatement pStmt = conn.prepareStatement(sql);

//		//insert文中の「？」に使用する値を設定しSQLを完成
		pStmt.setInt(1, classId);

		//select文を実行し、結果表(Result)を取得
		ResultSet rs = pStmt.executeQuery();

		while(rs.next()) {
			String kyoukaName = rs.getString("kyouka_name");
			int kyoukaId = rs.getInt("kyouka_ID");
			Kyouka kyouka = new Kyouka(kyoukaName, kyoukaId);
			kyoukaList.add(kyouka);
		}
	} catch (SQLException | ClassNotFoundException e) {
		e.printStackTrace();
		return null;
	}
	return kyoukaList;
	}
}