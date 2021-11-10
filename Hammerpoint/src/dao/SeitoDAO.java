package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Seito;

public class SeitoDAO {
	private final String DRIVER_NAME ="com.mysql.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/hammerpoint?useUnicode=true&characterEncoding=UTF-8";
	private final String DB_USER = "root";
	private final String DB_PASS = "earth";
	public boolean create(Seito seito) {
		//データベース接続
		Connection conn = null;
		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//insert文の準備
			String sql = "insert into seito(gakuseki_ID, seito_name, mail, class_ID, doubutu_ID, gender) values(?, ?, ?, ?, ?, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//insert文中の「？」に使用する値を設定しSQLを完成
			pStmt.setString(1, seito.getGakusekiId());
			pStmt.setString(2, seito.getSeitoName());
			pStmt.setString(3, seito.getMail());
			pStmt.setInt(4, seito.getClassId());
			pStmt.setInt(5,seito.getDoubutuId());
			pStmt.setInt(6, seito.getGender());

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
	}	public List<Seito> seitoListOut(int classId) {
		Connection conn = null;
		List<Seito> seitoList = new ArrayList<Seito>();

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//select文の準備
			String sql = "select seito.gakuseki_ID, seito.seito_name, seito.gender, doubutu.doubutu_name "
					+ "from seito "
					+ "join doubutu "
					+ "on seito.doubutu_ID = doubutu.doubutu_ID "
					+ "where seito.seito_flag != 2 and seito.class_ID = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//select分の?に入れるものの設定
			pStmt.setInt(1, classId);

			//select文を実行し、結果表（Result)を取得
			ResultSet rs = pStmt.executeQuery();

			//結果表に格納されたレコードの内容を
			//BookDatインスタンスに設定し、ArrayListインスタンスに追加
			while(rs.next()) {
				String gakusekiId = rs.getString("seito.gakuseki_ID");
				String seitoName = rs.getString("seito.seito_name");
				int gender = rs.getInt("seito.gender");
				String doubutuName = rs.getString("doubutu.doubutu_name");
				Seito seito = new Seito(gakusekiId, seitoName, gender, doubutuName);
				seitoList.add(seito);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return seitoList;
	}

	public Seito seitoHenkou(String gakusekiId) {
		Seito seito = null;
		Connection conn = null;

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//select文の準備
			String sql = "select seito.gakuseki_ID, seito.seito_name, seito.gender, seito.mail, seito.doubutu_ID, doubutu.doubutu_name, class.class_name "
					+ "from seito "
					+ "join doubutu "
					+ "on seito.doubutu_ID = doubutu.doubutu_ID "
					+ "join class "
					+ "on seito.class_ID = class.class_ID "
					+ "where seito.gakuseki_ID = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//select分の?に入れるものの設定
			pStmt.setString(1, gakusekiId);

			//select文を実行し、結果表（Result)を取得
			ResultSet rs = pStmt.executeQuery();

			//結果表に格納されたレコードの内容を
			if (rs.next()) {
				//結果表からデータを取得
				String gakusekiBangou = rs.getString("seito.gakuseki_ID");
				String seitoName = rs.getString("seito.seito_name");
				int  gender= rs.getInt("seito.gender");
				String mail = rs.getString("seito.mail");
				int doubutuId = rs.getInt("seito.doubutu_ID");
				String doubutuName= rs.getString("doubutu.doubutu_name");
				String className = rs.getString("class.class_name");
				seito = new Seito(gakusekiBangou, seitoName, gender, mail, doubutuId, doubutuName, className);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return seito;
	}

	public boolean seitoHenkouTouroku(Seito seito) {
		Connection conn = null;
		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			//select文を準備
			String sql = "update seito set seito_name = ?, mail = ?, doubutu_ID = ? where gakuseki_ID = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, seito.getSeitoName());
			pStmt.setString(2, seito.getMail());
			pStmt.setInt(3, seito.getDoubutuId());
			pStmt.setString(4, seito.getGakusekiId());
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
	public boolean seitoSakujo(String gakusekiId[]) {
		Connection conn = null;
		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			//select文を準備
			String sql = "update seito set seito_name = ?, mail = ?, doubutu_ID = ? where gakuseki_ID = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, seito.getSeitoName());
			pStmt.setString(2, seito.getMail());
			pStmt.setInt(3, seito.getDoubutuId());
			pStmt.setString(4, seito.getGakusekiId());
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