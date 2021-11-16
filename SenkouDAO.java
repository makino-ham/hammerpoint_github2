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
				String sql = "select senkou_ID, senkou_name from senkou;";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				//selectを実行し、結果表(result)を取得
				ResultSet rs = pstmt.executeQuery();

				//結果表に格納されたレコードの内容をgakkaインスタンスに設定し、ArrayListインスタンスに追加
				while(rs.next()) {
					int id = rs.getInt("senkou_ID");
					String name = rs.getString("senkou_name");

					//gakka方の変数に、取得したデータを引数として代入→リストに追加
					Senkou senkou = new Senkou(id, name);
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


}
