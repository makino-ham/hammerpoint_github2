package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Senkou;

public class SenkouDAO {
	//データベース接続に使用する情報
	private final String DRIVER_NAME="com.mysql.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/hammerpoint?useUnicode=true&characterEncoding=UTF-8";
	//MySQLのユーザ名
	private final String DB_USER = "root";
	//MySQLのパスワード
	private final String DB_PASS = "earth";

	public List<Senkou> senkouselect() {
		Connection conn = null;

		List<Senkou> senkouList = new ArrayList<Senkou>();

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//select文の準備
			String sql = "select senkou_ID, senkou_name, gakka_ID, senkou_flag from senkou;";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			//selectを実行し、結果表(result)を取得
			ResultSet rs = pstmt.executeQuery();

			//結果表に格納されたレコードの内容をsenkouインスタンスに設定し、ArrayListインスタンスに追加
			while(rs.next()) {
				int id = rs.getInt("senkou_ID");
				String name = rs.getString("senkou_name");
				int gakkaid = rs.getInt("gakka_ID");
				int flag = rs.getInt("senkou_flag");

				//senkou型の変数に、取得したデータを引数として代入→リストに追加
				Senkou senkou = new Senkou(id, name, gakkaid, flag);
				senkouList.add(senkou);
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
		return senkouList;
	}

	public boolean create(Senkou senkou) {
		Connection conn = null;

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//INSERT文の準備(senkou_idは自動連番なので指定しなくてよい
			String sql = "INSERT INTO SENKOU(SENKOU_NAME, GAKKA_ID) VALUES(?, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//INSERT文中の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, senkou.getSenkouName());
			pStmt.setInt(2, senkou.getGakkaId());

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

	public boolean senkouhenkou(Senkou senkou) {
		Connection conn = null;

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//update文の準備(gakka_idは指定しない)
			String sql = "update senkou set senkou_name = ? where senkou_id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//update文中の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, senkou.getSenkouName());
			pStmt.setInt(2, senkou.getSenkouId());

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

	public Senkou senkouhenkoukensaku(String senkouid) {
		Senkou senkou = null;
		Connection conn = null;

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//select文の準備
			String sql = "select senkou_ID, senkou_name from senkou where senkou_ID = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//select分の?に入れるものの設定
			pStmt.setString(1, senkouid);

			//select文を実行し、結果表（Result)を取得
			ResultSet rs = pStmt.executeQuery();

			//結果表に格納されたレコードの内容を
			if (rs.next()) {
				//結果表からデータを取得
				int senkouId = rs.getInt("senkou_ID");
				String senkouName = rs.getString("senkou_name");
				senkou = new Senkou(senkouId, senkouName);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return senkou;
	}

	public boolean senkousakujo(Senkou senkou) {
		Connection conn = null;

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//update文の準備(gakka_idは指定しない)
			String sql = "update senkou set senkou_flag = 1 where senkou_id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//update文中の「?」に使用する値を設定しSQLを完成
			pStmt.setInt(1, senkou.getSenkouId());

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
