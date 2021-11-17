package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Jikanwari;

public class JikanwariDAO {
	private final static String DRIVER_NAME ="com.mysql.jdbc.Driver";
	private final static String JDBC_URL = "jdbc:mysql://localhost:3306/hammerpoint?useUnicode=true&characterEncoding=UTF-8";
	private final static String DB_USER = "root";
	private final static String DB_PASS = "earth";

	public boolean create(Jikanwari jikanwari,String[] jigenId) {
		//データベース接続
		Connection conn = null;
		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//insert文の準備
			String sql = "insert into jikanwari(kyouka_ID,youbi_ID,  jugyou_suu, jigen) values(?, ?, ?, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//insert文中の「？」に使用する値を設定しSQLを完成
			pStmt.setInt(1, jikanwari.getKyoukaId());
			pStmt.setInt(2, jikanwari.getYoubiId());
			pStmt.setInt(3, jikanwari.getJugyouSuu());

			//insert文を実行(resultには追加された行数が代入される)
			for (String jigen: jigenId) {
				pStmt.setString(4, jigen);
				//update文を実行(resultには追加された行数が代入される)
				int result = pStmt.executeUpdate();
				if (result != 1) {
					return false;
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public List<Jikanwari> youbiListOut(){
		Connection conn = null;
		List<Jikanwari> youbiList = new ArrayList<Jikanwari>();

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//select文の準備
			String sql = "select youbi_ID,youbi_name from youbi";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			//select文を実行し、結果表（Result)を取得
			ResultSet rs = pstmt.executeQuery();

			//結果表に格納されたレコードの内容を
			//BookDatインスタンスに設定し、ArrayListインスタンスに追加
			while(rs.next()) {
					int youbinoId = rs.getInt("youbi_ID");
					String youbinoName=rs.getString("youbi_name");
					Jikanwari youbi=new Jikanwari(youbinoId,youbinoName);
					youbiList.add(youbi);

			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return youbiList;

	}
	public boolean jikanwariHenkouTouroku(Jikanwari jikanwari) {
		Connection conn = null;
		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			//update文を準備
			String sql = "update jikanwari set kyouka_ID = ?, youbi_ID = ?, jugyou_suu = ?,jigen=? where kyouka_ID = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, jikanwari.getYoubiId());
			pStmt.setInt(2, jikanwari.getJugyouSuu());
			pStmt.setInt(3, jikanwari.getJigen());
			pStmt.setInt(4, jikanwari.getKyoukaId());
			//update文を実行(resultには追加された行数が代入される)
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}


}