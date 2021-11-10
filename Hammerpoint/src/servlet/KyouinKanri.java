package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClassDAO;
import dao.gakkaDAO;
import model.ClassRoom;
import model.Gakka;

/**
 * Servlet implementation class KyouinKanri
 */
@WebServlet("/KyouinKanri")
public class KyouinKanri extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//フォワード先
		String forwardPath = null;

		//actionの値をリクエストパラメータから取得
		String action = request.getParameter("action");

		if(action.equals("touroku")) {
			//フォワード先を教員登録画面に設定
			List<Gakka> gakkaList = new ArrayList<Gakka>();
			gakkaDAO gakkaDAO = new gakkaDAO();
			gakkaList = gakkaDAO.gakkaselect();
			request.setAttribute("gakkaList", gakkaList);

			forwardPath = "/WEB-INF/jsp/kyouintouroku.jsp";
		}else if(action.equals("hensaku")) {
			List<ClassRoom> classList = new ArrayList<ClassRoom>();
			ClassDAO classDAO = new ClassDAO();
			classList = classDAO.classListOut();
			request.setAttribute("classList", classList);

			//フォワード先を教員変更削除検索画面に設定
			forwardPath = "/WEB-INF/jsp/kyouinhensaku.jsp";
		}

		//フォワード文の記述
		List<ClassRoom> classList = new ArrayList<ClassRoom>();
		ClassDAO classDAO = new ClassDAO();
		classList = classDAO.classListOut();
		request.setAttribute("classList", classList);

		//フォワード先を教員変更削除検索画面に設定
			RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
			dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//actionの値をリクエストパラメータから取得
		String action = request.getParameter("action");

		//フォワード先
		String forwardPath = null;

		if(action.equals("touroku")) {
		}

	}

}
