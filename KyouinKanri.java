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
import javax.servlet.http.HttpSession;

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
			int gakkaId = kyouin.getGakkaId();
			request.setAttribute("gakkaId", gakkaId);
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
			String genderS = request.getParameter("gender");
			int gakkaId = Integer.parseInt(request.getParameter("gakkaSelect"));
			if(kyouinIdS == "" || password == "" || kan_sei == "" || kan_mei == "" || huri_sei == "" || huri_mei == "" || genderS == null || gakkaId == 999) {
				if(kyouinIdS == "") {
					request.setAttribute("errorMsg", "教員IDが未入力です");
				}
				else if(password == ""){
					request.setAttribute("errorMsg2", "パスワードが未入力です");
				}
				else if(kan_sei == "" || kan_mei == "" || huri_sei == "" || huri_mei == "") {
					request.setAttribute("errorMsg3", "名前が未入力または名前の入力が不完全です");
				}
				else if(genderS == null) {
					request.setAttribute("errorMsg4", "性別が未選択です");
				}
				else if(gakkaId == 999) {
					request.setAttribute("errorMsg5", "学科が未選択です");
				}
				List<Gakka> gakkaList = new ArrayList<Gakka>();
				gakkaDAO gakkaDAO = new gakkaDAO();
				gakkaList = gakkaDAO.gakkaselect();
				List<Kyouin> kyouinList = new ArrayList<Kyouin>();
				KyouinDAO kyouinDAO = new KyouinDAO();
				kyouinList = kyouinDAO.kyouinListOut(gakkaId);
				int check = 1;
				//セッションスコープに情報を保存
				HttpSession session=request.getSession();
				request.setAttribute("check", check);
				request.setAttribute("kyouinIdS", kyouinIdS);
				request.setAttribute("password", password);
				request.setAttribute("kan_sei", kan_sei);
				request.setAttribute("kan_mei", kan_mei);
				request.setAttribute("huri_sei", huri_sei);
				request.setAttribute("huri_mei", huri_mei);
				if (genderS != null) {
					int gender = Integer.parseInt(genderS);
					request.setAttribute("gender", gender);
				}
				else {
					request.setAttribute("genderS", genderS);
				}
				request.setAttribute("gakkaId", gakkaId);
				request.setAttribute("gakkaList",gakkaList);
				session.setAttribute("kyouinList", kyouinList);
				forwardPath = "/WEB-INF/jsp/kyouintouroku.jsp";
			}
			else {
				int kyouinId = Integer.parseInt(kyouinIdS);
				int gender = Integer.parseInt(genderS);
				Kyouin kyouin = new Kyouin(kyouinId,password,kan_sei,kan_mei,huri_sei,huri_mei,gender,gakkaId);
				KyouinDAO kyouinDAO = new KyouinDAO();
				kyouinDAO.create(kyouin);

				//フォワード先をシス管登録完了画面に設定
				forwardPath = "/WEB-INF/jsp/shisukantourokukanryou.jsp";
			}
		}else if(action.equals("hensaku")) {
			int gakkaId = Integer.parseInt(request.getParameter("gakkaSelect"));
			if(gakkaId == 999 ) {
				int check = 0;
				request.setAttribute("check", check);
				request.setAttribute("errorMsg", "学科が未選択です");
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
		}
		else if(action.equals("sakujo")){
			String [] kyouinIdS = request.getParameterValues("kyouinId");
			//何も選択されていない場合変更削除画面に遷移する。
			System.out.println(kyouinIdS);
			if (kyouinIdS == null) {
				int check = 1;
				request.setAttribute("errorMsg", "チェックが入っていません");
				forwardPath = "/WEB-INF/jsp/kyouinhensaku.jsp";
				//学科リストを作成
				List<Gakka> gakkaList = new ArrayList<Gakka>();
				gakkaDAO gakkaDAO = new gakkaDAO();
				gakkaList = gakkaDAO.gakkaselect();
				request.setAttribute("check", check);
				request.setAttribute("gakkaList", gakkaList);
				RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
				dispatcher.forward(request, response);
			}
			KyouinDAO kyouinDAO = new KyouinDAO();
			kyouinDAO.kyouinSakujo(kyouinIdS);
			forwardPath =  "/WEB-INF/jsp/sisukansakujokanryou.jsp";
		}
		else {
			String kyouinIdS = request.getParameter("action");
			System.out.println(kyouinIdS);
			String kan_sei = request.getParameter("kanSei");
			String kan_mei = request.getParameter("kanMei");
			String huri_sei = request.getParameter("huriSei");
			String huri_mei = request.getParameter("huriMei");
			int gakkaId = Integer.parseInt(request.getParameter("gakkaSelect"));
			KyouinDAO kyouinDAO = new KyouinDAO();
			if(gakkaId == 999 ) {
				//学科リストを作成
				List<Gakka> gakkaList = new ArrayList<Gakka>();
				gakkaDAO gakkaDAO = new gakkaDAO();
				gakkaList = gakkaDAO.gakkaselect();
				//教員リストを作成
				List<Kyouin> kyouinList = new ArrayList<Kyouin>();
				int kyouinId = Integer.parseInt(kyouinIdS);
				kyouinList = kyouinDAO.kyouinListOut2(kyouinId);
				int check = 1;
				Kyouin kyouin = new Kyouin(kyouinId,kan_sei,kan_mei,huri_sei,huri_mei,gakkaId);
				HttpSession session=request.getSession();
				request.setAttribute("check", check);
				request.setAttribute("kyouin", kyouin);
				request.setAttribute("gakkaList",gakkaList);
				request.setAttribute("errorMsg", "学科が未選択です");
				session.setAttribute("kyouinList", kyouinList);
				System.out.println(kyouinList);
				forwardPath = "/WEB-INF/jsp/kyouinhenkou.jsp";
			}else {
				int kyouinId = Integer.parseInt(kyouinIdS);
				Kyouin kyouin = new Kyouin(kyouinId,kan_sei,kan_mei,huri_sei,huri_mei,gakkaId);
				kyouinDAO.update(kyouin);
				forwardPath = "/WEB-INF/jsp/sisukanhenkoukanryou.jsp";
			}
		}

		//フォワード文の記述
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}