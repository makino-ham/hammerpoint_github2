package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Kyouin;

public class KyouinDAO {
	private final String DRIVER_NAME ="com.mysql.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/hammerpoint?useUnicode=true&characterEncoding=UTF-8";
	private final String DB_USER = "root";
	private final String DB_PASS = "earth";

	//登録
		public boolean create(Kyouin kyouin) {
			//データベース接続
			Connection conn = null;
			try {
				//データベースへ接続
				Class.forName("com.mysql.jdbc.Driver");
				Class.forName(DRIVER_NAME);
				conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

				//insert文の準備
				String sql = "insert into kyouin(kyouin_ID, password, kan_sei, kan_mei, huri_sei, huri_mei, gender, gakkaName) values(?, ?, ?, ?, ?, ?, ?, ?);";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				//insert文中の「？」に使用する値を設定しSQLを完成
				pStmt.setInt(1, kyouin.getKyouinId());
				pStmt.setString(2, kyouin.getPassword());
				pStmt.setString(3, kyouin.getKanSei());
				pStmt.setString(4, kyouin.getKanMei());
				pStmt.setString(5, kyouin.getHuriSei());
				pStmt.setString(6, kyouin.getHuriMei());
				pStmt.setInt(7, kyouin.getGender());
				pStmt.setString(8, kyouin.getGakkaName());

				//insert文を実行(resultには追加された行数が代入される)
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

	public List<Kyouin> kyouinListOut(int gakkaId) {
		Connection conn = null;
		List<Kyouin> kyouinList = new ArrayList<Kyouin>();

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//select文の準備
			String sql = "select kyouin.kyouin_ID, gakka.gakka_name, kyouin.gender, concat(kyouin.kan_sei,kyouin.kan_mei)"
					+ "from kyouin "
					+ "join gakka "
					+ "on kyouin.gakka_ID = gakka.gakka_ID "
					+ "where kyouin.flag != 1 and gakka.gakka_ID = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//select分の?に入れるものの設定
			pStmt.setInt(1, gakkaId);

			//select文を実行し、結果表（Result)を取得
			ResultSet rs = pStmt.executeQuery();

			//結果表に格納されたレコードの内容を
			//BookDatインスタンスに設定し、ArrayListインスタンスに追加
			while(rs.next()) {
				String gakkaName = rs.getString("gakka.gakka_name");
				String kyouinName = rs.getString("concat(kyouin.kan_sei,kyouin.kan_mei)");
				int gender = rs.getInt("kyouin.gender");
				int kyouinId = rs.getInt("kyouin.kyouin_ID");
				Kyouin kyouin = new Kyouin(gakkaName,gender,kyouinName,kyouinId);
				kyouinList.add(kyouin);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return kyouinList;
	}

	public List<Kyouin> kyouinIdKensaku(int flag, String kId) {
		Connection conn = null;
		List<Kyouin> kyouinList = new ArrayList<Kyouin>();

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//select文の準備
			String sql = "select kyouin.kyouin_ID, kyouin.kan_sei, kyouin.kan_mei, kyouin.huri_sei, kyouin.huri_mei, gakka.gakka_name "
					+ "from kyouin "
					+ "join gakka "
					+ "on kyouin.gakka_ID = gakka.gakka_ID "
					+ "where kyouin.flag = ? and kyouin.kyouin_ID = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//select分の?に入れるものの設定
			pStmt.setInt(1, flag);
			pStmt.setString(2, kId);

			//select文を実行し、結果表（Result)を取得
			ResultSet rs = pStmt.executeQuery();

			//結果表に格納されたレコードの内容を
			//seitoインスタンスを作成
			if (rs.next()) {
				int kyouinId = rs.getInt("kyouin.kyouin_ID");
				String kanSei = rs.getString("kyouin.kan_sei");
				String kanMei = rs.getString("kyouin.kan_mei");
				String huriSei = rs.getString("kyouin.huri_sei");
				String huriMei = rs.getString("kyouin.huri_mei");
				String gakkaName = rs.getString("gakka.gakka_name");
				Kyouin kyouin = new Kyouin(kyouinId, kanSei, kanMei, huriSei, huriMei, gakkaName);
				kyouinList.add(kyouin);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return kyouinList;
	}

	public Kyouin kyouinHenkou(String kId) {
		Kyouin kyouin = null;
		Connection conn = null;

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//select文の準備
			String sql = "select kyouin.kyouin_ID, kyouin.kan_sei, kyouin.kan_mei, kyouin.huri_sei, kyouin.huri_mei, gakka.gakka_name "
					+ "from kyouin "
					+ "join gakka "
					+ "on kyouin.gakka_ID = gakka.gakka_ID "
					+ "where kyouin.kyouin_ID = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//select分の?に入れるものの設定
			pStmt.setString(1, kId);

			//select文を実行し、結果表（Result)を取得
			ResultSet rs = pStmt.executeQuery();

			//結果表に格納されたレコードの内容を
			if (rs.next()) {
				//結果表からデータを取得
				int kyouinId = rs.getInt("kyouin.kyouin_ID");
				String kanSei = rs.getString("kyouin.kan_sei");
				String kanMei = rs.getString("kyouin.kan_mei");
				String huriSei = rs.getString("kyouin.huri_sei");
				String huriMei = rs.getString("kyouin.huri_mei");
				String gakkaName = rs.getString("gakka.gakka_name");
				kyouin = new Kyouin(kyouinId, kanSei, kanMei, huriSei, huriMei, gakkaName);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return kyouin;
	}

}
