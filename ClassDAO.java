package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ClassRoom;

public class ClassDAO {
	private final String DRIVER_NAME ="com.mysql.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/hammerpoint?useUnicode=true&characterEncoding=UTF-8";
	private final String DB_USER = "root";
	private final String DB_PASS = "earth";

	public List<ClassRoom> classListOut() {
		Connection conn = null;
		List<ClassRoom> classList = new ArrayList<ClassRoom>();

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//select文の準備
			String sql = "select class_ID, class_name from class;";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			//select文を実行し、結果表（Result)を取得
			ResultSet rs = pstmt.executeQuery();

			//結果表に格納されたレコードの内容を
			//BookDatインスタンスに設定し、ArrayListインスタンスに追加
			while(rs.next()) {
				int classId = rs.getInt("class_ID");
				String className = rs.getString("class_name");
				ClassRoom classroom = new ClassRoom(classId, className);
				classList.add(classroom);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return classList;
	}
	public boolean create(ClassRoom classRoom) {
		//データベース接続
		Connection conn = null;
		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//insert文の準備
			String sql = "insert into class(gakka_ID, senkou_ID, class_name, gakunen, kyouin_ID) values(?, ?, ?, ?, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//insert文中の「？」に使用する値を設定しSQLを完成
			pStmt.setInt(1, classRoom.getGakkaId());
			pStmt.setInt(2, classRoom.getSenkouId());
			pStmt.setString(3, classRoom.getClassName());
			pStmt.setInt(4, classRoom.getGakunen());
			pStmt.setInt(5, classRoom.getKyouinId());

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
	public String classNameGakka(int gakkaId) {
		String className= null;
		Connection conn = null;

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//select文の準備
			String sql = "select substring(gakka_name, 1, 3) from gakka where gakka_ID = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//select分の?に入れるものの設定
			pStmt.setInt(1, gakkaId);
			//select文を実行し、結果表（Result)を取得
			ResultSet rs = pStmt.executeQuery();
			//結果表に格納されたレコードの内容を
			if (rs.next()) {
				//結果表からデータを取得
				String gakkaName = rs.getString("substring(gakka_name, 1, 3)");
				className = gakkaName;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return className;
	}
	public String classNameSenkou( int senkouId) {
		String className= null;
		Connection conn = null;

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//select文の準備
			String sql= "select substring(senkou_name, 1, 3) from senkou where senkou_ID = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//select分の?に入れるものの設定
			pStmt.setInt(1, senkouId);
			//select文を実行し、結果表（Result)を取得
			ResultSet rs = pStmt.executeQuery();;
			//結果表に格納されたレコードの内容を
			if (rs.next()) {
				//結果表からデータを取得
				String senkouName = rs.getString("substring(senkou_name, 1, 3)");
				className = senkouName;
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return className;
	}
	public List<ClassRoom> classListOut2(int gakkaId, int gakunen) {
		Connection conn = null;
		List<ClassRoom> classList = new ArrayList<ClassRoom>();

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//select文の準備
			String sql = "select class_ID, class_name "
					+ "from class "
					+ "where flag = 0 and gakka_ID = ? and gakunen = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//select分の?に入れるものの設定
			pStmt.setInt(1, gakkaId);
			pStmt.setInt(2, gakunen);
			//select文を実行し、結果表（Result)を取得
			ResultSet rs = pStmt.executeQuery();

			//結果表に格納されたレコードの内容を
			//BookDatインスタンスに設定し、ArrayListインスタンスに追加
			while(rs.next()) {
				int classId = rs.getInt("class_ID");
				String className = rs.getString("class_name");
				ClassRoom classRoom = new ClassRoom(classId, className);
				classList.add(classRoom);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return classList;
	}
	public ClassRoom classHenkou(int classId) {
		ClassRoom classRoom = null;
		Connection conn = null;

		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//select文の準備
			String sql = "select class_ID, class_name, gakka_ID, senkou_ID, kyouin_ID, gakunen "
					+ "from class "
					+ "where class_ID = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//select分の?に入れるものの設定
			pStmt.setInt(1, classId);

			//select文を実行し、結果表（Result)を取得
			ResultSet rs = pStmt.executeQuery();

			//結果表に格納されたレコードの内容を
			if (rs.next()) {
					//結果表からデータを取得
					int id = rs.getInt("class_ID");
					String className= rs.getString("class_name");
					int gakkaId = rs.getInt("gakka_ID");
					int senkouId = rs.getInt("senkou_ID");
					int kyouinId = rs.getInt("kyouin_ID");
					int gakunen = rs.getInt("gakunen");
					classRoom = new ClassRoom(id, gakkaId, senkouId, className, gakunen, kyouinId);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return classRoom;
	}
	public boolean classHenkouTouroku(ClassRoom classRoom) {
		Connection conn = null;
		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			//update文を準備
			String sql = "update class set class_name = ?, kyouin_ID = ? where class_ID = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, classRoom.getClassName());
			pStmt.setInt(2, classRoom.getKyouinId());
			pStmt.setInt(3, classRoom.getClassId());
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
	public boolean classSakujo(List<Integer> classId) {
		Connection conn = null;
		try {
			//データベースへ接続
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			//select文を準備
			String sql = "update class set flag = 1 where class_ID = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			for (int id: classId) {
					pStmt.setInt(1, id);
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
