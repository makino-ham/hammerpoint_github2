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
		request.setCharacterEncoding("UTF-8");

		//フォワード先
		String forwardPath = null;

		//actionの値をリクエストパラメータから取得
		String action = request.getParameter("action");

		if(action.equals("touroku")) {
			String kyouinIdS = request.getParameter("kyouin_ID");
			String password = request.getParameter("password");
			String kan_sei = request.getParameter("kanSei");
			String kan_mei = request.getParameter("kanMei");
			String huri_sei = request.getParameter("huriSei");
			String huri_mei = request.getParameter("huriMei");
			int gender = Integer.parseInt(request.getParameter("gender"));
			if(kyouinIdS == "") {
				request.setAttribute("errorMsg", "教員IDが未入力です");
			}
			else if(password == ""){
				request.setAttribute("errorMsg2", "パスワードが未入力です");
			}
			else if(kan_sei == "" || kan_mei == "" || huri_sei == "" || huri_mei == "") {
				request.setAttribute("errorMsg3", "名前が未入力または名前の入力が不完全です");
			}
			else if(gender != 1 && gender != 2) {
				request.setAttribute("errorMsg4", "性別が未選択です");
			}

			int kyouinId = Integer.parseInt(kyouinIdS);
			int gakkaId = Integer.parseInt(request.getParameter("gakkaSelect"));
			Kyouin kyouin = new Kyouin(kyouinId,password,kan_sei,kan_mei,huri_sei,huri_mei,gender,gakkaId);
			KyouinDAO kyouinDAO = new KyouinDAO();
			kyouinDAO.create(kyouin);

			//フォワード先をシス管登録完了画面に設定
			forwardPath = "/WEB-INF/jsp/shisukantourokukanryou.jsp";

		}else if(action.equals("hensaku")) {
			int gakkaId = Integer.parseInt(request.getParameter("gakkaSelect"));
			if(gakkaId == 999 ) {
				int check = 0;
				request.setAttribute("check", check);
				request.setAttribute("errorMsg", "学科が選択されていません");
			}else {
				List<Kyouin> kyouinList = new ArrayList<Kyouin>();
				KyouinDAO kyouinDAO = new KyouinDAO();
				kyouinList = kyouinDAO.kyouinListOut(gakkaId);
				int check = 1;
				request.setAttribute("check", check);
				request.setAttribute("kyouinList", kyouinList);
			}
			List<Gakka> gakkaList = new ArrayList<Gakka>();
			gakkaDAO gakkaDAO = new gakkaDAO();
			gakkaList = gakkaDAO.gakkaselect();
			request.setAttribute("gakkaId", gakkaId);
			request.setAttribute("gakkaList",gakkaList);
			forwardPath = "/WEB-INF/jsp/kyouinhensaku.jsp";

		}else {
			String kan_sei = request.getParameter("kanSei");
			String kan_mei = request.getParameter("kanMei");
			String huri_sei = request.getParameter("huriSei");
			String huri_mei = request.getParameter("huriMei");
			int kyouinId = Integer.parseInt(action);
			int gakkaId = Integer.parseInt(request.getParameter("gakkaSelect"));
			Kyouin kyouin = new Kyouin(kyouinId,kan_sei,kan_mei,huri_sei,huri_mei,gakkaId);
			KyouinDAO kyouinDAO = new KyouinDAO();
			kyouinDAO.update(kyouin);
			forwardPath = "/WEB-INF/jsp/sisukanhenkoukanryou.jsp";
		}

		//フォワード文の記述
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}
