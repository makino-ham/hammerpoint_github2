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

import dao.KyouinDAO;
import dao.gakkaDAO;
import model.Gakka;
import model.Kyouin;

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
			List<Gakka> gakkaList = new ArrayList<Gakka>();
			gakkaDAO gakkaDAO = new gakkaDAO();
			gakkaList = gakkaDAO.gakkaselect();
			request.setAttribute("gakkaList", gakkaList);

			//フォワード先を教員登録画面に設定
			forwardPath = "/WEB-INF/jsp/kyouintouroku.jsp";
		}else if(action.equals("hensaku")) {
			List<Gakka> gakkaList = new ArrayList<Gakka>();
			gakkaDAO gakkaDAO = new gakkaDAO();
			gakkaList = gakkaDAO.gakkaselect();
			int check = 0;
			request.setAttribute("check", check);
			request.setAttribute("gakkaList", gakkaList);

			//フォワード先を教員変更削除検索画面に設定
			forwardPath = "/WEB-INF/jsp/kyouinhensaku.jsp";
		}else if(action.equals("kyouin")) {
			forwardPath = "/WEB-INF/jsp/kyouin.jsp";
		}else  {
			Kyouin kyouin = new Kyouin();
			KyouinDAO kyouinDAO = new KyouinDAO();
			kyouin = kyouinDAO.kyouinHenkou(action);//kyouinIdを引数にセットする
			request.setAttribute("kyouin", kyouin);
			List<Gakka> gakkaList = new ArrayList<Gakka>();
			gakkaDAO gakkaDAO = new gakkaDAO();
			gakkaList = gakkaDAO.gakkaselect();
			request.setAttribute("kyouin", kyouin);
			request.setAttribute("gakkaList", gakkaList);

			forwardPath = "/WEB-INF/jsp/kyouinhenkou.jsp";
		}

		//フォワード文の記述
			RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
			dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//フォワード先
		String forwardPath = null;

		//actionの値をリクエストパラメータから取得
		String action = request.getParameter("action");

		if(action.equals("touroku")) {
			int kyouinId = Integer.parseInt("kyouin_ID");
			String password = request.getParameter("password");
			String kan_sei = request.getParameter("kan_sei");
			String kan_mei = request.getParameter("kan_mei");
			String huri_sei = request.getParameter("huri_sei");
			String huri_mei = request.getParameter("huri_mei");
			int gender = Integer.parseInt("gender");
			String gakkaName = request.getParameter("gakkaName");
			Kyouin kyouin = new Kyouin(kyouinId,password,kan_sei,kan_mei,huri_sei,huri_mei,gender,gakkaName);
			KyouinDAO kyouinDAO = new KyouinDAO();
			kyouinDAO.create(kyouin);

			//フォワード先をシス管登録完了画面に設定
			forwardPath = "/WEB-INF/jsp/shisukantourokukanryou.jsp";
		}else if(action.equals("hensaku")) {
			int gakkaId = Integer.parseInt(request.getParameter("gakkaSelect"));
			List<Kyouin> kyouinList = new ArrayList<Kyouin>();
			KyouinDAO kyouinDAO = new KyouinDAO();
			kyouinList = kyouinDAO.kyouinListOut(gakkaId);
			List<Gakka> gakkaList = new ArrayList<Gakka>();
			gakkaDAO gakkaDAO = new gakkaDAO();
			gakkaList = gakkaDAO.gakkaselect();
			int check = 1;
			request.setAttribute("check", check);
			request.setAttribute("kyouinList", kyouinList);
			request.setAttribute("gakkaId", gakkaId);
			request.setAttribute("gakkaList",gakkaList);
			forwardPath = "/WEB-INF/jsp/kyouinhensaku.jsp";
		}

		//フォワード文の記述
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}
