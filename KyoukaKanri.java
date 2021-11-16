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

import dao.ClassDAO;
import dao.KyouinDAO;
import dao.KyoukaDAO;
import dao.gakkaDAO;
import model.ClassRoom;
import model.Gakka;
import model.Kyouin;
import model.Kyouka;

/**
 * Servlet implementation class KyoukaKanri
 */
@WebServlet("/KyoukaKanri")
public class KyoukaKanri extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		//フォワード先
		String forwardPath = null;
		if (action == null) {
				forwardPath = "/WEB-INF/jsp/kyouka.jsp";
		} else if (action.equals("touroku")) {//
				System.out.println("<<<<<-----");
				//教員リストを作成
				List<Kyouin> kyouinList = new ArrayList<Kyouin>();
				KyouinDAO kyouinDAO = new KyouinDAO();
				kyouinList = kyouinDAO.kyouinListOut();
				//学科リストを作成
				List<Gakka> gakkaList = new ArrayList<Gakka>();
				gakkaDAO gakkaDAO = new gakkaDAO();
				gakkaList = gakkaDAO.gakkaselect();
				int check = 0;
				request.setAttribute("check", check);
				request.setAttribute("gakkaList", gakkaList);
				request.setAttribute("kyouinList", kyouinList);
				forwardPath = "/WEB-INF/jsp/kyoukatouroku.jsp";
		} else if (action.equals("hensaku")) {//
				//クラスリストを作成
				List<ClassRoom> classList = new ArrayList<ClassRoom>();
				ClassDAO classDAO = new ClassDAO();
				classList = classDAO.classListOut();
				int check = 0;
				request.setAttribute("check", check);
				request.setAttribute("classList", classList);
				forwardPath = "/WEB-INF/jsp/kyoukahensaku.jsp";
		} else {//変更
				Kyouka kyouka = new Kyouka();
				KyoukaDAO kyoukaDAO = new KyoukaDAO();
				kyouka = kyoukaDAO.kyoukaHenkou(Integer.parseInt(action));
				//学科Idを取得
				Kyouin kyouin = new Kyouin();
				kyouin = kyoukaDAO.kyouinGakka(kyouka.getKyouinId());
				int gakkaId = kyouin.getGakkaId();
				//クラスリストを作成
				List<ClassRoom> classList = new ArrayList<ClassRoom>();
				ClassDAO classDAO = new ClassDAO();
				classList = classDAO.classListOut();
				//教員リストを作成
				List<Kyouin> kyouinList = new ArrayList<Kyouin>();
				kyouinList = kyoukaDAO.kyouinKensaku(gakkaId);
				//学科リストを作成
				List<Gakka> gakkaList = new ArrayList<Gakka>();
				gakkaDAO gakkaDAO = new gakkaDAO();
				gakkaList = gakkaDAO.gakkaselect();
				int check = 1;
//				セッションスコープにユーザ情報を保存
				HttpSession session=request.getSession();
				session.setAttribute("kyouka", kyouka);
				request.setAttribute("check", check);
				request.setAttribute("gakkaId", gakkaId);
				request.setAttribute("classList", classList);
				request.setAttribute("kyouinList", kyouinList);
				request.setAttribute("gakkaList", gakkaList);
				forwardPath = "/WEB-INF/jsp/kyoukahenkou.jsp";
		}
		//設定されたフォワード先にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		//フォワード先
		String forwardPath = null;
		//登録の処理
		if (action.equals("kensaku")) {
				int gakkaId = Integer.parseInt(request.getParameter("gakkaSelect"));
				HttpSession session=request.getSession();
				if (gakkaId == 999) {
						String gakkaMsg = "学科を選択してください";
						int check = 0;
						request.setAttribute("check", check);
						request.setAttribute("gakkaMsg", gakkaMsg);
				} else {
						//教員リストを作成
						List<Kyouin> kyouinList = new ArrayList<Kyouin>();
						KyoukaDAO kyoukaDAO = new KyoukaDAO();
						kyouinList = kyoukaDAO.kyouinKensaku(gakkaId);
						//クラスリストを作成
						List<ClassRoom> classList = new ArrayList<ClassRoom>();
						ClassDAO classDAO = new ClassDAO();
						classList = classDAO.classListOut();
						int check = 1;
						request.setAttribute("classList", classList);
						request.setAttribute("check", check);
						session.setAttribute("kyouinList", kyouinList);
				}
				//学科リストを作成
				List<Gakka> gakkaList = new ArrayList<Gakka>();
				gakkaDAO gakkaDAO = new gakkaDAO();
				gakkaList = gakkaDAO.gakkaselect();
				//セッションスコープに学科IDを保存
				session.setAttribute("gakkaId", gakkaId);
				request.setAttribute("gakkaList", gakkaList);
				forwardPath = "/WEB-INF/jsp/kyoukatouroku.jsp";

		} else if (action.equals("touroku")) {
				int kyouinId = Integer.parseInt(request.getParameter("kyouinSelect"));
				int classId = Integer.parseInt(request.getParameter("classSelect"));
				String kyoukaName = request.getParameter("kyoukaName");
				//セッションスコープを宣言
				HttpSession session=request.getSession();
				//必須項目が入力されていないかチェック
				if (kyouinId == 99999 || classId == 999|| kyoukaName == "") {
						Kyouka kyouka = new Kyouka(kyouinId, classId, kyoukaName);
						int check = 1;
						//クラスリストを作成
						List<ClassRoom> classList = new ArrayList<ClassRoom>();
						ClassDAO classDAO = new ClassDAO();
						classList = classDAO.classListOut();
						//学科リストを作成
						List<Gakka> gakkaList = new ArrayList<Gakka>();
						gakkaDAO gakkaDAO = new gakkaDAO();
						gakkaList = gakkaDAO.gakkaselect();
						session.setAttribute("classId", classId);
						session.setAttribute("kyouinId", kyouinId);
						request.setAttribute("gakkaList", gakkaList);
						request.setAttribute("check", check);
						request.setAttribute("classList", classList);
						request.setAttribute("kyouka", kyouka);
						if (kyouinId == 99999) {
								String kyouinMsg = "教員が選択されていません";
								request.setAttribute("kyouinMsg", kyouinMsg);
							}
						if (classId == 999) {
								String classMsg = "クラスが選択されていません";
								request.setAttribute("classMsg", classMsg);
						}
						if (kyoukaName == "") {
								String kyoukaMsg = "教科名が入力されていません";
								request.setAttribute("kyoukaMsg", kyoukaMsg);
						}
						forwardPath = "/WEB-INF/jsp/kyoukatouroku.jsp";
				} else {
						Kyouka kyouka = new Kyouka(kyouinId, classId, kyoukaName);
						KyoukaDAO kyoukaDAO = new KyoukaDAO();
						kyoukaDAO.create(kyouka);
						session.removeAttribute("classId");
						session.removeAttribute("kyouinId");
						forwardPath = "/WEB-INF/jsp/kyouka.jsp";
				}
		} else if (action.equals("hensaku")) {
				int classId = Integer.parseInt(request.getParameter("classSelect"));
				if (classId == 999) {
						String classErrorMsg = "クラスが選択されていません";
						request.setAttribute("classErrorMsg", classErrorMsg);
						int check = 0;
						request.setAttribute("check", check);
				} else {
						//教科リストを作成
						List<Kyouka> kyoukaList = new ArrayList<Kyouka>();
						KyoukaDAO kyoukaDAO = new KyoukaDAO();
						kyoukaList = kyoukaDAO.hensakuListOut(classId);
						int check = 1;
//					セッションスコープに教科リストとクラスIDを保存
						HttpSession session=request.getSession();
						session.setAttribute("classId", classId);
						session.setAttribute("kyoukaList", kyoukaList);
						request.setAttribute("check", check);
				}
				//クラスリストを作成
				List<ClassRoom> classList = new ArrayList<ClassRoom>();
				ClassDAO classDAO = new ClassDAO();
				classList = classDAO.classListOut();
				request.setAttribute("classList", classList);
				forwardPath = "/WEB-INF/jsp/kyoukahensaku.jsp";
		} else if(action.equals("kensaku2")) {
				int gakkaId = Integer.parseInt(request.getParameter("gakkaSelect"));
				//教員リストを作成
				List<Kyouin> kyouinList = new ArrayList<Kyouin>();
				KyoukaDAO kyoukaDAO = new KyoukaDAO();
				kyouinList = kyoukaDAO.kyouinKensaku(gakkaId);
				//クラスリストを作成
				List<ClassRoom> classList = new ArrayList<ClassRoom>();
				ClassDAO classDAO = new ClassDAO();
				classList = classDAO.classListOut();
				//学科リストを作成
				List<Gakka> gakkaList = new ArrayList<Gakka>();
				gakkaDAO gakkaDAO = new gakkaDAO();
				gakkaList = gakkaDAO.gakkaselect();
				int check = 1;
				request.setAttribute("gakkaList", gakkaList);
				request.setAttribute("gakkaId", gakkaId);
				request.setAttribute("classList", classList);
				request.setAttribute("check", check);
				request.setAttribute("kyouinList", kyouinList);
				forwardPath = "/WEB-INF/jsp/kyoukahenkou.jsp";
		} else if (action.equals("sakujo")) {
				String [] kyoukaIdS = request.getParameterValues("kyoukaId");
				//何も選択されていない場合変更削除画面に遷移する。
				if (kyoukaIdS == null) {
						int check = 1;
						String checkErrorMsg = "チェックが入っていません";
						//クラスリストを作成
						List<ClassRoom> classList = new ArrayList<ClassRoom>();
						ClassDAO classDAO = new ClassDAO();
						classList = classDAO.classListOut();
						request.setAttribute("checkErrorMsg", checkErrorMsg);
						request.setAttribute("check", check);
						request.setAttribute("classList", classList);
						forwardPath = "/WEB-INF/jsp/kyoukahensaku.jsp";
				} else {
					List<Integer> kyoukaId = new ArrayList<Integer>();
					//文字列型教員ID配列を数値型教員ID配列に移動
					for (String kyouka: kyoukaIdS) {
							kyoukaId.add(Integer.parseInt(kyouka));
					}
					KyoukaDAO kyoukaDAO = new KyoukaDAO();
					kyoukaDAO.kyoukaSakujo(kyoukaId);
					forwardPath = "/WEB-INF/jsp/kyouka.jsp";
				}
		} else {//教科の変更
				int kyoukaId = Integer.parseInt(action);
				int  kyouinId = Integer.parseInt(request.getParameter("kyouinSelect"));
				int classId = Integer.parseInt(request.getParameter("classSelect"));
				String kyoukaName = request.getParameter("kyoukaName");
				Kyouka kyouka = new Kyouka(kyoukaId, kyoukaName, kyouinId, classId);
				KyoukaDAO kyoukaDAO = new KyoukaDAO();
				kyoukaDAO.kyoukaHenkouTouroku(kyouka);
//				セッションスコープを破棄
				HttpSession session=request.getSession();
				session.removeAttribute("kyouka");
				forwardPath = "/WEB-INF/jsp/kyouka.jsp";
		}
		//設定されたフォワード先にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}
