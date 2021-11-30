package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PasswordDAO;
import model.Password;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action=request.getParameter("action");
////		menu画面
		if(action==null||action.equals("start")) {

			RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/start.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("slogin")) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/slogin.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("smain")) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/smain.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("klogin")) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/klogin.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");

		int id=Integer.parseInt(request.getParameter("id"));
		String pass=request.getParameter("pass");
		Password account=null;
		String action=request.getParameter("action");
		if(action.equals("siskan")) {
//			ログイン処理の実行
//			入力されたIDとパスワードをLoginクラスに保存
			Password password=new Password(id,pass);

//			AccountDAOのfindByLoginメソッドの呼び出し
			PasswordDAO dao=new PasswordDAO();
			account=dao.passninsyou(password);
//		ログイン成否
		if(account!=null) {
			System.out.println("アカウント成功");
//			セッションスコープにユーザ情報を保存
			HttpSession session=request.getSession();
			session.setAttribute("account", account);
//			画面の遷移
//			シス管メイン画面に遷移する。
			RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/smain.jsp");
			dispatcher.forward(request, response);
		}else {
//			ログイン画面へ遷移するためサーブレットにリダイレクト。
//			サーブレットからサーブレット
			String msg="入力項目にミスがあります。";
			request.setAttribute("err", msg);
			RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/slogin.jsp");
			dispatcher.forward(request, response);
		}
		}
	}
}
