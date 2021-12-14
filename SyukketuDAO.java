package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Syukketu;

public class SyukketuDAO {
		private final String DRIVER_NAME ="com.mysql.jdbc.Driver";
		private final String JDBC_URL = "jdbc:mysql://localhost:3306/hammerpoint?useUnicode=true&characterEncoding=UTF-8";
		private final String DB_USER = "root";
		private final String DB_PASS = "earth";

		public boolean create(Syukketu syukketu) {
			//データベース接続
			Connection conn = null;
			try {
				//データベースへ接続
				Class.forName("com.mysql.jdbc.Driver");
				Class.forName(DRIVER_NAME);
				conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

				//insert文の準備
				String sql = "insert into syukketu(gakuseki_ID, kyouka_ID, jigen, hiduke, syukketu) values(?, ?, ?, ?, ?);";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				//insert文中の「？」に使用する値を設定しSQLを完成
				pStmt.setString(1,syukketu.getGakusekiId());
				pStmt.setInt(2, syukketu.getKyoukaId());
				pStmt.setInt(3, syukketu.getJigen());
				pStmt.setString(4, syukketu.getHiduke());
				pStmt.setInt(5, syukketu.getSyukketu());

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
		public Syukketu syukketuHenkouKensaku(Syukketu syukketu) {
			Syukketu s = null;
			Connection conn = null;

			try {
				//データベースへ接続
				Class.forName("com.mysql.jdbc.Driver");
				Class.forName(DRIVER_NAME);
				conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

				//select文の準備
				String sql = "select syukketu.gakuseki_ID, seito_name, syukketu.kyouka_ID, kyouka_name, jigen, hiduke, syukketu "
						+ "from syukketu "
						+ "join seito "
						+ "on syukketu.gakuseki_ID = seito.gakuseki_ID "
						+ "join kyouka "
						+ "on syukketu.kyouka_ID = kyouka.kyouka_ID "
						+ "where syukketu.gakuseki_ID = ? and syukketu.kyouka_ID = ? and jigen = ? and hiduke = ?;";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				//select分の?に入れるものの設定
				pStmt.setString(1, syukketu.getGakusekiId());
				pStmt.setInt(2, syukketu.getKyoukaId());
				pStmt.setInt(3, syukketu.getJigen());
				pStmt.setString(4, syukketu.getHiduke());

				//select文を実行し、結果表（Result)を取得
				ResultSet rs = pStmt.executeQuery();

				//結果表に格納されたレコードの内容を
				if (rs.next()) {
						//結果表からデータを取得
						String gakusekiId = rs.getString("syukketu.gakuseki_ID");
						String seitoName = rs.getString("seito_name");
						int kyoukaId = rs.getInt("syukketu.kyouka_ID");
						String kyoukaName = rs.getString("kyouka_name");
						int jigen = rs.getInt("jigen");
						String hiduke = rs.getString("hiduke");
						int sFlag = rs.getInt("syukketu");
						s = new Syukketu(gakusekiId, seitoName, kyoukaId, kyoukaName, jigen, hiduke, sFlag);
				}

			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			return s;
		}
		public boolean syukketuHenkou(Syukketu syukketu) {
			Connection conn = null;
			try {
				//データベースへ接続
				Class.forName("com.mysql.jdbc.Driver");
				Class.forName(DRIVER_NAME);
				conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				//update文を準備
				String sql = "update syukketu set syukketu = ?, riyuu = ? where gakuseki_ID = ? and kyouka_ID = ? and jigen = ? and hiduke = ?;";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setInt(1, syukketu.getSyukketu());
				pStmt.setString(2, syukketu.getRiyuu());
				pStmt.setString(3, syukketu.getGakusekiId());
				pStmt.setInt(4, syukketu.getKyoukaId());
				pStmt.setInt(5, syukketu.getJigen());
				pStmt.setString(6, syukketu.getHiduke());
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
		public List<String> hidukeListOut(int kyoukaId, String gakusekiId) {
			Connection conn = null;
			List<String> hidukeList = new ArrayList<String>();

			try {
				//データベースへ接続
				Class.forName("com.mysql.jdbc.Driver");
				Class.forName(DRIVER_NAME);
				conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

				//select文の準備
				String sql = "select DISTINCT hiduke "
						+ "from syukketu "
						+ "where kyouka_ID = ? and gakuseki_ID = ? "
						+ "order by hiduke desc;";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				//select分の?に入れるものの設定
				pStmt.setInt(1, kyoukaId);
				pStmt.setString(2, gakusekiId);
				//select文を実行し、結果表（Result)を取得
				ResultSet rs = pStmt.executeQuery();

				//結果表に格納されたレコードの内容を
				//BookDatインスタンスに設定し、ArrayListインスタンスに追加
				while(rs.next()) {
					String hiduke = rs.getString("hiduke");
					hidukeList.add(hiduke);
				}

			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			return hidukeList;
		}
		public List<Syukketu> countListout(int kyoukaId, String gakusekiId, List<String> hiduke) {
			Connection conn = null;
			List<Syukketu> syukketuList = new ArrayList<Syukketu>();

			try {
				//データベースへ接続
				Class.forName("com.mysql.jdbc.Driver");
				Class.forName(DRIVER_NAME);
				conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

				//select文の準備
				String sql = "select count(jigen) "
						+ "from syukketu "
						+ "where kyouka_ID = ? and gakuseki_ID = ?  and hiduke = ? "
						+ "order by hiduke desc;";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				//select分の?に入れるものの設定
				pStmt.setInt(1, kyoukaId);
				pStmt.setString(2, gakusekiId);
				for (String h: hiduke) {
						pStmt.setString(3, h);
						//select文を実行し、結果表（Result)を取得
						ResultSet rs = pStmt.executeQuery();
						if (rs.next()) {
								int count = rs.getInt("count(jigen)");
								Syukketu syukketu = new Syukketu();
								syukketu.setCount(count);
								syukketu.setHiduke(h);
								syukketuList.add(syukketu);
						}
				}

			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			return syukketuList;
		}
		public List<Syukketu> jigenListOut(String gakusekiId, int kyoukaId) {
			Connection conn = null;
			List<Syukketu> jigenList = new ArrayList<Syukketu>();

			try {
				//データベースへ接続
				Class.forName("com.mysql.jdbc.Driver");
				Class.forName(DRIVER_NAME);
				conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

				//select文の準備
				String sql = "select jigen, hiduke "
						+ "from syukketu "
						+ "where gakuseki_ID = ? and kyouka_ID = ? "
						+ "order by hiduke desc;";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				//select分の?に入れるものの設定
				pStmt.setString(1, gakusekiId);
				pStmt.setInt(2, kyoukaId);
				//select文を実行し、結果表（Result)を取得
				ResultSet rs = pStmt.executeQuery();

				//結果表に格納されたレコードの内容を
				//BookDatインスタンスに設定し、ArrayListインスタンスに追加
				while(rs.next()) {
						int jigen = rs.getInt("jigen");
						String hiduke = rs.getString("hiduke");
						Syukketu syukketu = new Syukketu();
						syukketu.setJigen(jigen);
						syukketu.setHiduke(hiduke);
						jigenList.add(syukketu);
				}

			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			return jigenList;
		}
		public List<Syukketu> syukketuListOut(String gakusekiId, int kyoukaId) {
			Connection conn = null;
			List<Syukketu> sList = new ArrayList<Syukketu>();

			try {
				//データベースへ接続
				Class.forName("com.mysql.jdbc.Driver");
				Class.forName(DRIVER_NAME);
				conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

				//select文の準備
				String sql = "select syukketu, hiduke "
						+ "from syukketu "
						+ "where gakuseki_ID = ? and kyouka_ID = ? "
						+ "order by hiduke desc;";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				//select分の?に入れるものの設定
				pStmt.setString(1, gakusekiId);
				pStmt.setInt(2, kyoukaId);
				//select文を実行し、結果表（Result)を取得
				ResultSet rs = pStmt.executeQuery();

				//結果表に格納されたレコードの内容を
				//BookDatインスタンスに設定し、ArrayListインスタンスに追加
				while(rs.next()) {
						int sFlag = rs.getInt("syukketu");
						String hiduke = rs.getString("hiduke");
						Syukketu syukketu = new Syukketu();
						syukketu.setSyukketu(sFlag);
						syukketu.setHiduke(hiduke);
						sList.add(syukketu);
				}

			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			return sList;
		}
		//出欠の一括表示
		public List<Syukketu> syukketuListOut() {
			Connection conn = null;
			List<Syukketu> syukketuList = new ArrayList<Syukketu>();

			try {
				//データベースへ接続
				Class.forName("com.mysql.jdbc.Driver");
				Class.forName(DRIVER_NAME);
				conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

				//select文の準備
				String sql = "select gakuseki_ID, syukketu from syukketu;";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				//select文を実行し、結果表（Result)を取得
				ResultSet rs = pstmt.executeQuery();

				//結果表に格納されたレコードの内容を
				//BookDatインスタンスに設定し、ArrayListインスタンスに追加
				while(rs.next()) {
					String gakusekiId = rs.getString("gakuseki_ID");
					int syukketu = rs.getInt("syukketu");
					Syukketu s = new Syukketu(gakusekiId, syukketu);
					syukketuList.add(s);
				}

			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			return syukketuList;
		}
}
