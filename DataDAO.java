package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Doubutu;
import model.Seito;

public class DataDAO {
		private final String DRIVER_NAME ="com.mysql.jdbc.Driver";
		private final String JDBC_URL = "jdbc:mysql://localhost:3306/hammerpoint?useUnicode=true&characterEncoding=UTF-8";
		private final String DB_USER = "root";
		private final String DB_PASS = "earth";

		public List<Doubutu> doubutuDataOut() {
			List<Doubutu> doubutuCountList = new ArrayList<Doubutu>();
			Connection conn = null;

			try {
				//データベースへ接続
				Class.forName("com.mysql.jdbc.Driver");
				Class.forName(DRIVER_NAME);
				conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

				//select文の準備
				String sql1 = "select count(doubutu_ID) from seito where doubutu_ID = 1;";
				String sql2 = "select count(doubutu_ID) from seito where doubutu_ID = 2;";
				String sql3 = "select count(doubutu_ID) from seito where doubutu_ID = 3;";
				String sql4 = "select count(doubutu_ID) from seito where doubutu_ID = 4;";
				String sql5 = "select count(doubutu_ID) from seito where doubutu_ID = 5;";
				String sql6 = "select count(doubutu_ID) from seito where doubutu_ID = 6;";
				String sql7 = "select count(doubutu_ID) from seito where doubutu_ID = 7;";
				String sql8 = "select count(doubutu_ID) from seito where doubutu_ID = 8;";
				String sql9 = "select count(doubutu_ID) from seito where doubutu_ID = 9;";
				String sql10 = "select count(doubutu_ID) from seito where doubutu_ID = 10;";
				PreparedStatement pStmt1 = conn.prepareStatement(sql1);
				PreparedStatement pStmt2 = conn.prepareStatement(sql2);
				PreparedStatement pStmt3 = conn.prepareStatement(sql3);
				PreparedStatement pStmt4 = conn.prepareStatement(sql4);
				PreparedStatement pStmt5 = conn.prepareStatement(sql5);
				PreparedStatement pStmt6 = conn.prepareStatement(sql6);
				PreparedStatement pStmt7 = conn.prepareStatement(sql7);
				PreparedStatement pStmt8 = conn.prepareStatement(sql8);
				PreparedStatement pStmt9 = conn.prepareStatement(sql9);
				PreparedStatement pStmt10 = conn.prepareStatement(sql10);
				//select文を実行し、結果表（Result)を取得
				ResultSet rs = pStmt1.executeQuery();
				//結果表に格納されたレコードの内容を
				if (rs.next()) {
					int doubutuCount = rs.getInt("count(doubutu_ID)");
					String count = "";
					for (int i = 0; i < doubutuCount; i++) {
						count += "★";
					}
					Doubutu doubutu = new Doubutu("猫", count);
					doubutuCountList.add(doubutu);
				}
				rs = pStmt2.executeQuery();
				if (rs.next()) {
					int doubutuCount = rs.getInt("count(doubutu_ID)");
					String count = "";
					for (int i = 0; i < doubutuCount; i++) {
						count += "★";
					}
					Doubutu doubutu = new Doubutu("カバ", count);
					doubutuCountList.add(doubutu);
				}
				rs = pStmt3.executeQuery();
				if (rs.next()) {
					int doubutuCount = rs.getInt("count(doubutu_ID)");
					String count = "";
					for (int i = 0; i < doubutuCount; i++) {
						count += "★";
					}
					Doubutu doubutu = new Doubutu("象", count);
					doubutuCountList.add(doubutu);
				}
				rs = pStmt4.executeQuery();
				if (rs.next()) {
					int doubutuCount = rs.getInt("count(doubutu_ID)");
					String count = "";
					for (int i = 0; i < doubutuCount; i++) {
						count += "★";
					}
					Doubutu doubutu = new Doubutu("チーター", count);
					doubutuCountList.add(doubutu);
				}
				rs = pStmt5.executeQuery();
				if (rs.next()) {
					int doubutuCount = rs.getInt("count(doubutu_ID)");
					String count = "";
					for (int i = 0; i < doubutuCount; i++) {
						count += "★";
					}
					Doubutu doubutu = new Doubutu("ライオン", count);
					doubutuCountList.add(doubutu);
				}
				rs = pStmt6.executeQuery();
				if (rs.next()) {
					int doubutuCount = rs.getInt("count(doubutu_ID)");
					String count = "";
					for (int i = 0; i < doubutuCount; i++) {
						count += "★";
					}
					Doubutu doubutu = new Doubutu("虎", count);
					doubutuCountList.add(doubutu);
				}
				rs = pStmt7.executeQuery();
				if (rs.next()) {
					int doubutuCount = rs.getInt("count(doubutu_ID)");
					String count = "";
					for (int i = 0; i < doubutuCount; i++) {
						count += "★";
					}
					Doubutu doubutu = new Doubutu("ネズミ", count);
					doubutuCountList.add(doubutu);
				}
				rs = pStmt8.executeQuery();
				if (rs.next()) {
					int doubutuCount = rs.getInt("count(doubutu_ID)");
					String count = "";
					for (int i = 0; i < doubutuCount; i++) {
						count += "★";
					}
					Doubutu doubutu = new Doubutu("牛", count);
					doubutuCountList.add(doubutu);
				}
				rs = pStmt9.executeQuery();
				if (rs.next()) {
					int doubutuCount = rs.getInt("count(doubutu_ID)");
					String count = "";
					for (int i = 0; i < doubutuCount; i++) {
						count += "★";
					}
					Doubutu doubutu = new Doubutu("馬", count);
					doubutuCountList.add(doubutu);
				}
				rs = pStmt10.executeQuery();
				if (rs.next()) {
					int doubutuCount = rs.getInt("count(doubutu_ID)");
					String count = "";
					for (int i = 0; i < doubutuCount; i++) {
						count += "★";
					}
					Doubutu doubutu = new Doubutu("犬", count);
					doubutuCountList.add(doubutu);
				}

			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			return doubutuCountList;
		}
		public List<Doubutu> doubutuDataOut2() {
			List<Doubutu> doubutuCountList = new ArrayList<Doubutu>();
			Connection conn = null;
			try {
				//データベースへ接続
				Class.forName("com.mysql.jdbc.Driver");
				Class.forName(DRIVER_NAME);
				conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

				String sql1 = "select count(doubutu_ID) from seito where doubutu_ID =";
				DataDAO dataDAO = new DataDAO();
				//動物の数を入手
				Doubutu Dcount = new Doubutu();
				Dcount = dataDAO.doubutuCount();
				int count = Dcount.getCount();
				//動物の名前を入手
				List<Doubutu> doubutuNameList = dataDAO.doubutuNameOut();
				for (int i = 0; i < count; i++) {
					PreparedStatement pStmt = conn.prepareStatement("select count(doubutu_ID) from seito where doubutu_ID =" + doubutuNameList.get(i).getDoubutuId());
					//select文を実行し、結果表（Result)を取得
					ResultSet rs = pStmt.executeQuery();
					//結果表に格納されたレコードの内容を
					if (rs.next()) {
						int doubutuCount = rs.getInt("count(doubutu_ID)");
						String ster = "";
						for (int j = 0; j < doubutuCount; j++) {
							ster += "★";
						}
						Doubutu doubutu = new Doubutu(doubutuNameList.get(i).getDoubutuName(), ster);
						doubutuCountList.add(doubutu);
					}

				}

			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			return doubutuCountList;
		}
		public Doubutu doubutuCount() {
			Doubutu doubutu = null;
			Connection conn = null;

			try {
				//データベースへ接続
				Class.forName("com.mysql.jdbc.Driver");
				Class.forName(DRIVER_NAME);
				conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

				//select文の準備
				String sql= "select count(*) from doubutu;";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				//select文を実行し、結果表（Result)を取得
				ResultSet rs = pStmt.executeQuery();;
				//結果表に格納されたレコードの内容を
				if (rs.next()) {
					//結果表からデータを取得
					int count = rs.getInt("count(*)");
					doubutu = new Doubutu(count);
				}

			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			return doubutu;
		}
		public List<Doubutu> doubutuNameOut() {
			Connection conn = null;
			List<Doubutu> doubutuList = new ArrayList<Doubutu>();

			try {
				//データベースへ接続
				Class.forName("com.mysql.jdbc.Driver");
				Class.forName(DRIVER_NAME);
				conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

				//select文の準備
				String sql = "select doubutu_ID, doubutu_name from doubutu;";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				//select文を実行し、結果表（Result)を取得
				ResultSet rs = pstmt.executeQuery();

				//結果表に格納されたレコードの内容を
				//BookDatインスタンスに設定し、ArrayListインスタンスに追加
				while(rs.next()) {
					int doubutuId = rs.getInt("doubutu_ID");
					String doubutuName = rs.getString("doubutu_name");
					Doubutu doubutu = new Doubutu(doubutuId, doubutuName);
					doubutuList.add(doubutu);
				}

			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			return doubutuList;
		}
		public List<Seito> kaikinsyouListOut(List<String> gakusekiList) {
			Connection conn = null;
			List<Seito> seitoList = new ArrayList<Seito>();

			try {
				//データベースへ接続
				Class.forName("com.mysql.jdbc.Driver");
				Class.forName(DRIVER_NAME);
				conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

				//select文の準備
				String sql = "select gakuseki_ID, seito_name, gender "
						+ "from seito "
						+ "where gakuseki_ID = ?  and seito_flag = 0;";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				for (String id: gakusekiList) {
						pStmt.setString(1, id);
						//select文を実行し、結果表（Result)を取得
						ResultSet rs = pStmt.executeQuery();
						if (rs.next()) {
								String gakusekiId = rs.getString("gakuseki_ID");
								String seitoName = rs.getString("seito_name");
								int gender = rs.getInt("gender");
								Seito seito = new Seito(gakusekiId, seitoName, gender);
								seitoList.add(seito);
						}
				}

			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			return seitoList;
		}
}