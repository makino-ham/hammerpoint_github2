package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Gakka;

public class gakkaDAO {
	//データベース接続に使用する情報
	private final String DRIVER_NAME="com.mysql.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/hammerpoint?useUnicode=true&characterEncoding=UTF-8";
	//MySQLのユーザ名
	private final String DB_USER = "root";
	//MySQLのパスワード
	private final String DB_PASS = "earth";

	public List<Gakka> gakkaselect() {
		Connection conn = null;

		List<Gakka> gakkaList = new ArrayList<Gakka>();

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//select文の準備
			String sql = "select gakka_ID, gakka_name from gakka";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			//selectを実行し、結果表(result)を取得
			ResultSet rs = pstmt.executeQuery();

			//結果表に格納されたレコードの内容をgakkaインスタンスに設定し、ArrayListインスタンスに追加
			while(rs.next()) {
				int id = rs.getInt("gakka_ID");
				String name = rs.getString("gakka_name");

				//gakka方の変数に、取得したデータを引数として代入→リストに追加
				Gakka gakka = new Gakka(id, name);
				gakkaList.add(gakka);
			}

		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			//データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return gakkaList;
	}

	public boolean create(Gakka gakka) {
		Connection conn = null;

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//INSERT文の準備(gakka_idは自動連番なので指定しなくてよい
			String sql = "INSERT INTO GAKKA(GAKKA_NAME) VALUES(?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//INSERT文中の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, gakka.getGakkaName());

			//INSERT文を実行(result二は追加された行数が代入される)
			int result = pStmt.executeUpdate();
			//1ではない(追加データがない)場合
			if (result != 1) {
				return false;
			}
		//エラーチェック
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean gakkahenkou(Gakka gakka) {
		Connection conn = null;

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//update文の準備(gakka_idは指定しない)
			String sql = "update gakka set gakka_name = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//update文中の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, gakka.getGakkaName());

			//update文を実行(result二は追加された行数が代入される)
			int result = pStmt.executeUpdate();
			//1ではない(追加データがない)場合
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
