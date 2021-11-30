package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.KyoukaDAO;
import dao.PasswordDAO;
import model.Kyouka;
import model.Password;

/**
 * Servlet implementation class TeacherKanri
 */
@WebServlet("/TeacherKanri")
public class TeacherKanri extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
	if(action==null||action.equals("syukketu")) {
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/syukketu.jsp");
		dispatcher.forward(request, response);
	}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		int id=Integer.parseInt(request.getParameter("id"));
		String pass=request.getParameter("pass");
		Password account=null;
		String action=request.getParameter("action");
		if(action.equals("kyouin")) {
//		教員ログイン----------------------------------------------
//			ログイン処理の実行
//			入力されたIDとパスワードをLoginクラスに保存
			Password password=new Password();
			password.setKyouinid(id);
			password.setPass(pass);
//			AccountDAOのfindByLoginメソッドの呼び出し
			PasswordDAO dao=new PasswordDAO();
			account=dao.kpassninsyou(password);
//	ログイン成否
	if(account!=null) {//ログイン成功時
//		セッションスコープにユーザ情報を保存
		int kyouinId=Integer.parseInt(request.getParameter("id"));
//		曜日を取得するやつ
		int today = 100;
		 Calendar cal = Calendar.getInstance();
		 today=cal.get(Calendar.DAY_OF_WEEK) - 2;
		    System.out.println("今日の曜日は"+today);

		List<Kyouka> kyoukaList = new ArrayList<Kyouka>();
		KyoukaDAO kyoukaDAO = new KyoukaDAO();
		kyoukaList = kyoukaDAO.teacherkyoukaListOut(kyouinId,today);



		HttpSession session=request.getSession();
		session.setAttribute("today", today);
		session.setAttribute("kyoukaList", kyoukaList);
		session.setAttribute("account", account);
//		画面の遷移
//		シス管メイン画面に遷移する。
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/kmain.jsp");
		dispatcher.forward(request, response);
	}else {//ログイン失敗時
//		ログイン画面へ遷移するためサーブレットにリダイレクト。
//		サーブレットからサーブレット
		String msg="入力項目にミスがあります。";
		request.setAttribute("err", msg);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/klogin.jsp");
		dispatcher.forward(request, response);

			}
//	ここまでログイン-----------------------------------------------
		}
	}

}
