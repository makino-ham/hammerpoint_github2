package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Kyouin;
import model.Kyouka;

public class KyoukaDAO {

	private final String DRIVER_NAME ="com.mysql.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/hammerpoint?useUnicode=true&characterEncoding=UTF-8";
	private final String DB_USER = "root";
	private final String DB_PASS = "earth";

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~^
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~^
	public List<Kyouin> kyouinKensaku(int gakkaId) {
		Connection conn = null;
		List<Kyouin> kyouinList = new ArrayList<Kyouin>();

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//select文の準備
			String sql = "select kyouin_ID, concat(kan_sei, kan_mei) "
					+ "from kyouin "
					+ "where gakka_ID = ? and flag = 0;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//select分の?に入れるものの設定
			pStmt.setInt(1, gakkaId);

			//select文を実行し、結果表（Result)を取得
			ResultSet rs = pStmt.executeQuery();

			//結果表に格納されたレコードの内容を
			//Seitoインスタンスに設定し、ArrayListインスタンスに追加
			while(rs.next()) {
				int kyouinId = rs.getInt("kyouin_ID");
				String kyouinName = rs.getString("concat(kan_sei, kan_mei)");
				Kyouin kyouin = new Kyouin(kyouinId, kyouinName);
				kyouinList.add(kyouin);
			}
		} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				return null;
		}
				return kyouinList;
		}

	public boolean create(Kyouka kyouka) {
		//データベース接続
		Connection conn = null;
		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//insert文の準備
			String sql = "insert into kyouka(kyouin_ID, class_ID, kyouka_name) values(?, ?, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//insert文中の「？」に使用する値を設定しSQLを完成
			pStmt.setInt(1, kyouka.getKyouinId());
			pStmt.setInt(2, kyouka.getClassId());
			pStmt.setString(3, kyouka.getKyoukaName());
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

//			//insert文中の「？」に使用する値を設定しSQLを完成
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
//	/////////////////////////////
	public List<Kyouka> teacherkyoukaListOut(int kyouinId,int today) {
		//データベース接続
		Connection conn = null;
		List<Kyouka> kyoukaList = new ArrayList<Kyouka>();
		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//insert文の準備
			String sql = "select senkou.senkou_name,class.gakunen,kyouka.kyouka_name,kyouka.kyouka_ID,jikanwari.jigen \r\n" +
					"from kyouka \r\n" +
					"join class \r\n" +
					"on kyouka.class_ID=class.class_ID \r\n" +
					"join jikanwari \r\n" +
					"on kyouka.kyouka_ID=jikanwari.kyouka_ID \r\n" +
					"join senkou \r\n" +
					"on senkou.senkou_ID=class.senkou_ID \r\n" +
					"where kyouka.kyouin_ID=? and jikanwari.youbi_ID=? and jikanwari.jikanwari_flag=0 \r\n" +
					"order by jikanwari.jigen;";
			PreparedStatement pStmt = conn.prepareStatement(sql);

//			//insert文中の「？」に使用する値を設定しSQLを完成
			pStmt.setInt(1, kyouinId);
			pStmt.setInt(2, today);
			System.out.println(pStmt);

			//select文を実行し、結果表(Result)を取得
			ResultSet rs = pStmt.executeQuery();

			while(rs.next()) {
				String senkouName=rs.getString("senkou.senkou_name");
				int gakunen=rs.getInt("class.gakunen");
				String kyoukaName = rs.getString("kyouka.kyouka_name");
				int kyoukaId = rs.getInt("kyouka.kyouka_ID");
				int jigen=rs.getInt("jikanwari.jigen");
				Kyouka kyouka = new Kyouka(senkouName,gakunen,kyoukaName, kyoukaId,jigen);
				kyoukaList.add(kyouka);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return kyoukaList;
		}
//	/////////////////////////////
	public List<Kyouka> hensakuListOut(int classId) {
			Connection conn = null;
			List<Kyouka> kyoukaList = new ArrayList<Kyouka>();

		try {
				//データベースへ接続
				Class.forName("com.mysql.jdbc.Driver");
				Class.forName(DRIVER_NAME);
				conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

				//select文の準備
				String sql = "select kyouka_ID, kyouka_name from kyouka where class_ID = ? and flag = 0;";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				//insert文中の「？」に使用する値を設定しSQLを完成
				pStmt.setInt(1, classId);
				//select文を実行し、結果表（Result)を取得
				ResultSet rs = pStmt.executeQuery();

				//結果表に格納されたレコードの内容を
				//BookDatインスタンスに設定し、ArrayListインスタンスに追加
				while(rs.next()) {
						int kyoukaId = rs.getInt("kyouka_ID");
						String kyoukaName = rs.getString("kyouka_name");
						Kyouka kyouka = new Kyouka(kyoukaId, kyoukaName);
						kyoukaList.add(kyouka);
				}

		} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				return null;
		}
			return kyoukaList;
	}

	public Kyouka kyoukaHenkou(int kyoukaId) {
		Kyouka kyouka = null;
		Connection conn = null;

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//select文の準備
			String sql = "select kyouka_ID, kyouka_name, kyouin_ID, class_ID "
					+ "from kyouka "
					+ "where kyouka_ID = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//select分の?に入れるものの設定
			pStmt.setInt(1, kyoukaId);

			//select文を実行し、結果表（Result)を取得
			ResultSet rs = pStmt.executeQuery();

			//結果表に格納されたレコードの内容を
			if (rs.next()) {
				//結果表からデータを取得
				int id = rs.getInt("kyouka_ID");
				String kyoukaName= rs.getString("kyouka_name");
				int kyouinId = rs.getInt("kyouin_ID");
				int classId = rs.getInt("class_ID");
				kyouka = new Kyouka(id, kyoukaName, kyouinId, classId);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return kyouka;
	}
	public Kyouin kyouinGakka(int kyouinId) {
		Kyouin kyouin = null;
		Connection conn = null;

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//select文の準備
			String sql = "select gakka_ID "
					+ "from kyouin "
					+ "where kyouin_ID = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//select分の?に入れるものの設定
			pStmt.setInt(1, kyouinId);

			//select文を実行し、結果表（Result)を取得
			ResultSet rs = pStmt.executeQuery();

			//結果表に格納されたレコードの内容を
			if (rs.next()) {
				//結果表からデータを取得
				int gakkaId = rs.getInt("gakka_ID");
				kyouin = new Kyouin(gakkaId);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return kyouin;
	}
	public boolean kyoukaHenkouTouroku(Kyouka kyouka) {
		Connection conn = null;
		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			//update文を準備
			String sql = "update kyouka set kyouin_ID = ?, class_ID = ?, kyouka_name = ? where kyouka_ID = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, kyouka.getKyouinId());
			pStmt.setInt(2, kyouka.getClassId());
			pStmt.setString(3, kyouka.getKyoukaName());
			pStmt.setInt(4, kyouka.getKyoukaId());
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
	public boolean kyoukaSakujo(List<Integer> kyoukaId) {
		Connection conn = null;
		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			//select文を準備
			String sql = "update kyouka set flag = 1 where kyouka_ID = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			for (int kyouka: kyoukaId) {
				pStmt.setInt(1, kyouka);
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

	 public List<Kyouka> kyoukaIchiran() {
		 Connection conn = null;
		 List<Kyouka> kyoukaList = new ArrayList<Kyouka>();

		 try {
		 //データベースへ接続
		 Class.forName("com.mysql.jdbc.Driver");
		 Class.forName(DRIVER_NAME);
		 conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

		 //select文の準備
		 String sql = "select kyouka_ID, kyouka_name from kyouka;";
		 PreparedStatement pstmt = conn.prepareStatement(sql);

		 //select文を実行し、結果表（Result)を取得
		 ResultSet rs = pstmt.executeQuery();

		 //結果表に格納されたレコードの内容を
		 //BookDatインスタンスに設定し、ArrayListインスタンスに追加
		 while(rs.next()) {
		 int kyoukaId = rs.getInt("kyouka_ID");
		 String kyoukaName = rs.getString("kyouka_name");
		 Kyouka kyouka = new Kyouka(kyoukaId, kyoukaName);
		 kyoukaList.add(kyouka);
		 }

		 } catch (SQLException | ClassNotFoundException e) {
		 e.printStackTrace();
		 return null;
		 }
		 return kyoukaList;
		 }
}