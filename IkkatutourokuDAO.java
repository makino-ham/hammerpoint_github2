package dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IkkatutourokuDAO {
private final static String DRIVER_NAME ="com.mysql.jdbc.Driver";
private final static String JDBC_URL = "jdbc:mysql://localhost:3306/hammerpoint?useUnicode=true&characterEncoding=UTF-8";
private final static String DB_USER = "root";
private final static String DB_PASS = "earth";

	public boolean Ikkatutouroku(String ikkatuFile) throws IOException {
		 //ファイル読み込みで使用する３つのクラス
		  FileInputStream fi = null;
		  InputStreamReader is = null;
		  BufferedReader br = null;
			Connection conn = null;
			int result=0;
		  try {
				Class.forName("com.mysql.jdbc.Driver");
				Class.forName(DRIVER_NAME);
				conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

		    //読み込みファイルのインスタンス生成
		    //ファイル名を指定する
		    fi = new FileInputStream(ikkatuFile);
		    is = new InputStreamReader(fi);
		    br = new BufferedReader(is);
		    //読み込み行
		    String line;
		    //読み込み行数の管理
		    int i = 0;
		    //列名を管理する為の配列
		    String[] arr = null;
		    //1行ずつ読み込みを行う
		    while ((line = br.readLine()) != null) {
		        //先頭行は列名
		        if (i == 0) {
			        //カンマで分割した内容を配列に格納する
			        // arrの中身{"youbi_ID","kyouka_ID","jigen","jugyou_suu"};
			        arr = line.split(",");
		        } else {
		            //カンマで分割した内容を配列に格納する
		            String[] data = line.split(",");
		            //配列の中身を順位表示する。列数(=列名を格納した配列の要素数)分繰り返す
					String sql = "insert into jikanwari(youbi_ID,kyouka_ID, jigen,jugyou_suu) values(?,?,?,?);";
					PreparedStatement pStmt = conn.prepareStatement(sql);

//				//insert文中の「？」に使用する値を設定しSQLを完成
				pStmt.setInt(1,Integer.parseInt(data[0]));
				pStmt.setInt(2,Integer.parseInt(data[1]));
				pStmt.setInt(3,Integer.parseInt(data[2]));
				pStmt.setInt(4,Integer.parseInt(data[3]));



//				insert文を実行(resultには追加された行数が代入される)
				result = pStmt.executeUpdate();

				if (result != 1) {
					return false;
				}
		    }
		      //行数のインクリメント
		      i++;

		    }//テスト用の}
		      }catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("エラーエラー");
				return false;
			}
			return true;

	}

}



