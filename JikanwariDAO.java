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
	public List<Jikanwari> jikanwariHenkou(int youbiId,int jigenId) {
		List<Jikanwari> jikanwariList = new ArrayList<Jikanwari>();
		Connection conn = null;

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//select文の準備
			String sql = "select youbi.youbi_name,jikanwari.kyouka_ID,kyouka.kyouka_name,jikanwari.jugyou_suu,jikanwari.jigen \r\n" +
					"from jikanwari\r\n" +
					"join youbi \r\n" +
					"on jikanwari.youbi_ID=youbi.youbi_ID \r\n" +
					"join kyouka \r\n" +
					"on jikanwari.kyouka_ID=kyouka.kyouka_ID where jikanwari.youbi_ID=? and jikanwari.jigen=?and jikanwari_flag=0;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//select分の?に入れるものの設定
			pStmt.setInt(1, youbiId);
			pStmt.setInt(2,jigenId);

			//select文を実行し、結果表（Result)を取得
			ResultSet rs = pStmt.executeQuery();

			//結果表に格納されたレコードの内容を
			while(rs.next()) {				//結果表からデータを取得
				String youbiName = rs.getString("youbi.youbi_name");
				int  kyoukaId= rs.getInt("jikanwari.kyouka_ID");
				String kyoukaName = rs.getString("kyouka.kyouka_name");
				int  jugyouSuu= rs.getInt("jikanwari.jugyou_suu");
				int jigen = rs.getInt("jikanwari.jigen");

				Jikanwari jikanwari = new Jikanwari(youbiName,kyoukaId,kyoukaName,jugyouSuu,jigen);
				jikanwariList.add(jikanwari);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return jikanwariList;
	}
	public boolean jikanwariHenkouTouroku(Jikanwari jikanwari,Jikanwari jikanwari2) {
		Connection conn = null;
		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			//update文を準備
			String sql = "update jikanwari set youbi_ID=?,jugyou_suu=?,jigen=? where kyouka_ID=? and youbi_ID=? and jigen=?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, jikanwari2.getYoubiId());
			pStmt.setInt(2, jikanwari2.getJugyouSuu());
			pStmt.setInt(3, jikanwari2.getJigen());
			pStmt.setInt(4, jikanwari.getKyoukaId());
			pStmt.setInt(5, jikanwari.getYoubiId());
			pStmt.setInt(6, jikanwari.getJigen());

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
	public Jikanwari jikanwariHenkou(int kyoukaId,int youbiId,int jigen) {
		Jikanwari jikanwari = null;
		Connection conn = null;

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//select文の準備
			String sql = "select jikanwari.youbi_ID,jikanwari.kyouka_ID,kyouka.kyouka_name,jikanwari.jugyou_suu,jikanwari.jigen \r\n" +
					"from jikanwari\r\n" +
					"join kyouka \r\n" +
					"on jikanwari.kyouka_ID=kyouka.kyouka_ID \r\n" +
					"where jikanwari.kyouka_ID=? and jikanwari.youbi_ID=? and jikanwari.jigen=? and jikanwari_flag=0;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//select分の?に入れるものの設定
			pStmt.setInt(1, kyoukaId);
			pStmt.setInt(2, youbiId);
			pStmt.setInt(3, jigen);

			//select文を実行し、結果表（Result)を取得
			ResultSet rs = pStmt.executeQuery();

			//結果表に格納されたレコードの内容を
			if (rs.next()) {
				//結果表からデータを取得
				int youbinoId=rs.getInt("jikanwari.youbi_ID");
				int kyoukanoId=rs.getInt("jikanwari.kyouka_ID");
				String kyoukaName = rs.getString("kyouka.kyouka_name");
				int  jugyouSuu= rs.getInt("jikanwari.jugyou_suu");
				int  jigenId= rs.getInt("jikanwari.jigen");

				jikanwari=new Jikanwari(youbinoId,kyoukanoId,kyoukaName,jugyouSuu,jigenId);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return jikanwari;
	}
	public boolean flagHenkou(List<Jikanwari>jikanwari2) {
		Connection conn = null;
		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			//select文を準備
			String sql = "update jikanwari set jikanwari_flag = 1 where kyouka_ID=? and youbi_ID=? and jigen=?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			for (Jikanwari jikanwarigati: jikanwari2) {
				pStmt.setInt(1, jikanwarigati.getKyoukaId());
				pStmt.setInt(2,jikanwarigati.getYoubiId());
				pStmt.setInt(3,jikanwarigati.getJigen());

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


}