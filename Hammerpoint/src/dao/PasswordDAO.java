package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Password;


public class PasswordDAO {

	private final String DRIVER_NAME="com.mysql.jdbc.Driver";
	private final String JDBC_URL="jdbc:mysql://localhost:3306/hammerpoint?useUnicode=true&characterEncoding=UTF-8";

	private final String DB_USER="root";
	private final String DB_PASS="earth";


	public Password passninsyou(Password password){
//		DB接続
//		@SuppressWarnings("unused")
		Password user=null;

		try {
//			データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);

//			select文の準備
			String sql="Select siskan_ID,siskan_name,password from siskan where siskan_ID=? and password=?";
			PreparedStatement pStmt=conn.prepareStatement(sql);
//			insert文中の「？」に使用する値を設定しsqlを完成
			pStmt.setInt(1, password.getSiskanid());
			pStmt.setString(2, password.getPass());

//			selectを実行し、結果表(Result)を取得
			ResultSet rs=pStmt.executeQuery();

//			一致するユーザが存在した場合そのユーザを表すAccountインスタンスを生成
			if(rs.next()) {
				int id=rs.getInt("siskan_ID");
				String name=rs.getString("siskan_name");
				String pass=rs.getString("password");
//				System.out.println(name);
				user=new Password(id,name,pass);
			}
		}
		catch(SQLException e) {
		e.printStackTrace();
		return null;
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
		return null;
	}
//	見つかったユーザまたはnullを返す
	return user;


	}
}