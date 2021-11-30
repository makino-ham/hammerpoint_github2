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
import dao.KyoukaDAO;
import dao.SenkouDAO;
import dao.gakkaDAO;
import model.ClassRoom;
import model.Gakka;
import model.Kyouin;
import model.Senkou;

/**
 * Servlet implementation class ClassKanri
 */
@WebServlet("/ClassKanri")
public class ClassKanri extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String action = request.getParameter("action");
			//フォワード先
			String forwardPath = null;
			if (action == null) {
					forwardPath = "/WEB-INF/jsp/classRoom.jsp";
			} else if (action.equals("touroku")) {
					//学科リストを作成
					List<Gakka> gakkaList = new ArrayList<Gakka>();
					gakkaDAO gakkaDAO = new gakkaDAO();
					gakkaList = gakkaDAO.gakkaselect();
					//セッションが残っていた場合削除する
					HttpSession session=request.getSession();
					session.removeAttribute("classRoom");
					session.removeAttribute("className");
					session.removeAttribute("gakkaName");
					session.removeAttribute("senkouName");
					session.removeAttribute("kyouinList");
					session.removeAttribute("gakkaId");
					session.removeAttribute("gakunen");
					session.removeAttribute("classList");
					request.setAttribute("gakkaList", gakkaList);
					forwardPath = "/WEB-INF/jsp/classtouroku.jsp";
			} else if (action.equals("henkou")) {
					//学科リストを作成
					List<Gakka> gakkaList = new ArrayList<Gakka>();
					gakkaDAO gakkaDAO = new gakkaDAO();
					gakkaList = gakkaDAO.gakkaselect();
					request.setAttribute("gakkaList", gakkaList);
					forwardPath = "/WEB-INF/jsp/classhenkou.jsp";
			} else if(action.equals("sinkyuu")) {
					//学科リストを作成
					List<Gakka> gakkaList = new ArrayList<Gakka>();
					gakkaDAO gakkaDAO = new gakkaDAO();
					gakkaList = gakkaDAO.gakkaselect();
					//セッションが残っていた場合削除する
					HttpSession session=request.getSession();
					session.removeAttribute("classRoom");
					session.removeAttribute("className");
					session.removeAttribute("gakkaName");
					session.removeAttribute("senkouName");
					session.removeAttribute("kyouinList");
					session.removeAttribute("gakkaId");
					session.removeAttribute("gakunen");
					session.removeAttribute("classList");
					request.setAttribute("gakkaList", gakkaList);
					forwardPath = "/WEB-INF/jsp/classsinkyuu.jsp";
			}	else {
					ClassRoom classRoom = null;
					ClassDAO classDAO = new ClassDAO();
					classRoom = classDAO.classHenkou(Integer.parseInt(action));
					gakkaDAO gakkaDAO = new gakkaDAO();
					//学科名を取得
					Gakka gakka = gakkaDAO.gakkaOut(classRoom.getGakkaId());
					//専攻名を取得
					SenkouDAO senkouDAO = new SenkouDAO();
					String senkouName = senkouDAO.senkouNameOut(classRoom.getSenkouId());
//					//学年を取得
//					int gakunen = classRoom.getGakunen();
//					//クラスIDを取得
//					int classId = classRoom.getClassId();
					//クラス名を取得
					String[] name = classRoom.getClassName().split("（");
					String className = name[0];
					//教員リストを作成
					List<Kyouin> kyouinList = new ArrayList<Kyouin>();
					KyoukaDAO kyoukaDAO = new KyoukaDAO();
					kyouinList = kyoukaDAO.kyouinKensaku(classRoom.getGakkaId());
					//セッションスコープを宣言
					HttpSession session=request.getSession();
					//検索に使用した学科ID、学年、クラスリストを削除
					session.removeAttribute("gakkaId");
					session.removeAttribute("gakunen");
					session.removeAttribute("classList");
					//セット
//					session.setAttribute("classId", classId);
//					session.setAttribute("gakunen", gakunen);
					session.setAttribute("className", className);
					session.setAttribute("gakkaName", gakka.getGakkaName());
					session.setAttribute("classRoom", classRoom);
					session.setAttribute("senkouName", senkouName);
					session.setAttribute("kyouinList", kyouinList);
					forwardPath = "/WEB-INF/jsp/classhenkoutouroku.jsp";
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
			if (action.equals("kensaku")) {
					int gakkaId = Integer.parseInt(request.getParameter("gakkaSelect"));
					//セッションスコープを宣言
					HttpSession session=request.getSession();
					if (gakkaId == 999) {
							//学科リストを作成
							List<Gakka> gakkaList = new ArrayList<Gakka>();
							gakkaDAO gakkaDAO = new gakkaDAO();
							gakkaList = gakkaDAO.gakkaselect();
							String gakkaErrorMsg = "学科が選択されていません";
							session.setAttribute("gakkaId", gakkaId);
							request.setAttribute("gakkaErrorMsg", gakkaErrorMsg);
							request.setAttribute("gakkaList", gakkaList);
							forwardPath = "/WEB-INF/jsp/classtouroku.jsp";
					} else {
							//専攻リストを作成
							List<Senkou> senkouList = new ArrayList<Senkou>();
							SenkouDAO senkouDAO = new SenkouDAO();
							senkouList = senkouDAO.senkouKensaku(gakkaId);
							//学科リストを作成
							List<Gakka> gakkaList = new ArrayList<Gakka>();
							gakkaDAO gakkaDAO = new gakkaDAO();
							gakkaList = gakkaDAO.gakkaselect();
							//教員リストを作成
							List<Kyouin> kyouinList = new ArrayList<Kyouin>();
							KyoukaDAO kyoukaDAO = new KyoukaDAO();
							kyouinList = kyoukaDAO.kyouinKensaku(gakkaId);
							//学科名を取得
							Gakka gakka = new Gakka();
							gakka = gakkaDAO.gakkaOut(gakkaId);
							String gakkaName = gakka.getGakkaName();
							//画面遷移チェック用変数
							int check = 1;
							session.setAttribute("gakkaName", gakkaName);
							session.setAttribute("kyouinList", kyouinList);
							session.setAttribute("senkouList", senkouList);
							session.setAttribute("gakkaId", gakkaId);
							request.setAttribute("check", check);
							request.setAttribute("gakkaList", gakkaList);
							forwardPath = "/WEB-INF/jsp/classtouroku.jsp";
					}

			} else if (action.equals("touroku")) {
					int senkouId = Integer.parseInt(request.getParameter("senkouSelect"));
					int kyouinId = Integer.parseInt(request.getParameter("kyouinSelect"));
					int gakunen = Integer.parseInt(request.getParameter("gakunenSelect"));
					String className = request.getParameter("className");
					HttpSession session=request.getSession();
					int gakkaId = (int) session.getAttribute("gakkaId");
					if (senkouId == 999 || kyouinId == 999 || gakunen == 999 || className == "") {
							if (senkouId == 999) {
									String senkouErrorMsg = "専攻が選択されていません";
									request.setAttribute("senkouErrorMsg", senkouErrorMsg);
							}
							if (kyouinId == 99999) {
									String kyouinErrorMsg = "アドバイザーが選択されていません";
									request.setAttribute("kyouinErrorMsg", kyouinErrorMsg);
							}
							if (gakunen == 999) {
									String gakunenErrorMsg = "学年が選択されていません";
									request.setAttribute("gakunenErrorMsg", gakunenErrorMsg);
							}
							if (className == "") {
									String classErrorMsg = "クラス名が入力されていません";
									request.setAttribute("classErrorMsg", classErrorMsg);
							}
							int check = 1;
							//学科リストを作成
							List<Gakka> gakkaList = new ArrayList<Gakka>();
							gakkaDAO gakkaDAO = new gakkaDAO();
							gakkaList = gakkaDAO.gakkaselect();
							request.setAttribute("gakkaList", gakkaList);
							request.setAttribute("check", check);
							request.setAttribute("senkouId", senkouId);
							request.setAttribute("kyouinId", kyouinId);
							request.setAttribute("gakunen", gakunen);
							forwardPath = "/WEB-INF/jsp/classtouroku.jsp";
					} else {
							ClassDAO classDAO = new ClassDAO();
							//クラス名の文字列結合
							className = className + "（" + classDAO.classNameGakka(gakkaId)
							+ "，" + classDAO.classNameSenkou(senkouId) + "，" + gakunen + "年）";
							ClassRoom classRoom = new ClassRoom(gakkaId, senkouId, className, gakunen, kyouinId);
							classDAO.create(classRoom);
							session.removeAttribute("gakkaId");
							session.removeAttribute("kyouinList");
							session.removeAttribute("senkouList");
							session.removeAttribute("gakkaName");
							forwardPath = "/WEB-INF/jsp/classtouroku.jsp";
					}
			} else if (action.equals("hensaku")) {
					int gakkaId = Integer.parseInt(request.getParameter("gakkaSelect"));
					int gakunen = Integer.parseInt(request.getParameter("gakunenSelect"));
					//セッションスコープを宣言
					HttpSession session=request.getSession();
					session.setAttribute("gakkaId", gakkaId);
					session.setAttribute("gakunen", gakunen);
					String ggErrorMsg = null;
					if (gakkaId == 999 || gakunen == 999) {
							if (gakkaId == 999 && gakunen == 999) {
									ggErrorMsg = "学科および学年を選択してください";
							} else if (gakkaId == 999) {
									ggErrorMsg = "学科を選択してください";
							} else {
									ggErrorMsg = "学年を選択してください";
							}
							request.setAttribute("ggErrorMsg", ggErrorMsg);
					} else {
							int check = 1;
							ClassDAO classDAO = new ClassDAO();
							List<ClassRoom> classList = new ArrayList<ClassRoom>();
							classList = classDAO.classListOut2(gakkaId, gakunen);
							request.setAttribute("check", check);
							session.setAttribute("classList", classList);
					}
					//学科リストを作成
					List<Gakka> gakkaList = new ArrayList<Gakka>();
					gakkaDAO gakkaDAO = new gakkaDAO();
					gakkaList = gakkaDAO.gakkaselect();
					request.setAttribute("gakkaList", gakkaList);
					forwardPath = "/WEB-INF/jsp/classhenkou.jsp";
			} else if (action.equals("sakujo")) {
					String [] classIdS = request.getParameterValues("classId");
					if (classIdS == null) {//チェックが選択されていなかった場合
							String checkErrorMsg = "チェックが選択されていません";
							int check = 1;
							//学科リストを作成
							List<Gakka> gakkaList = new ArrayList<Gakka>();
							gakkaDAO gakkaDAO = new gakkaDAO();
							gakkaList = gakkaDAO.gakkaselect();
							request.setAttribute("gakkaList", gakkaList);
							request.setAttribute("check", check);
							request.setAttribute("checkErrorMsg", checkErrorMsg);
							forwardPath = "/WEB-INF/jsp/classhenkou.jsp";
					} else {
							List<Integer> classId = new ArrayList<Integer>();
							//文字列型クラスID配列を数値型クラスIDリストに移動
							for (String id: classIdS) {
									classId.add(Integer.parseInt(id));
							}
							ClassDAO classDAO = new ClassDAO();
							classDAO.classSakujo(classId);
							HttpSession session=request.getSession();
							session.removeAttribute("gakkaId");
							session.removeAttribute("gakunen");
							forwardPath = "/WEB-INF/jsp/classRoom.jsp";
					}
			} else if (action.equals("sinsaku")) {
					int gakkaId = Integer.parseInt(request.getParameter("gakkaSelect"));
					//セッションスコープを宣言
					HttpSession session=request.getSession();
					session.setAttribute("gakkaId", gakkaId);
					String ggErrorMsg = null;
					if (gakkaId == 999) {
							ggErrorMsg = "学科を選択してください";
							request.setAttribute("ggErrorMsg", ggErrorMsg);
					} else {
							int check = 1;
							ClassDAO classDAO = new ClassDAO();
							List<ClassRoom> classList = new ArrayList<ClassRoom>();
							classList = classDAO.classListOut3(gakkaId);
							request.setAttribute("check", check);
							session.setAttribute("classList", classList);
					}
					//学科リストを作成
					List<Gakka> gakkaList = new ArrayList<Gakka>();
					gakkaDAO gakkaDAO = new gakkaDAO();
					gakkaList = gakkaDAO.gakkaselect();
					request.setAttribute("gakkaList", gakkaList);
					forwardPath = "/WEB-INF/jsp/classsinkyuu.jsp";
			}	else if (action.equals("sinkyuu")) {
					String [] classIdS = request.getParameterValues("classId");
					if (classIdS == null) {//チェックが選択されていなかった場合
							String checkErrorMsg = "チェックが選択されていません";
							int check = 1;
							//学科リストを作成
							List<Gakka> gakkaList = new ArrayList<Gakka>();
							gakkaDAO gakkaDAO = new gakkaDAO();
							gakkaList = gakkaDAO.gakkaselect();
							request.setAttribute("gakkaList", gakkaList);
							request.setAttribute("check", check);
							request.setAttribute("checkErrorMsg", checkErrorMsg);
							forwardPath = "/WEB-INF/jsp/classsinkyuu.jsp";
					} else {
							List<Integer> classId = new ArrayList<Integer>();
							//文字列型クラスID配列を数値型クラスIDリストに移動
							for (String id: classIdS) {
									classId.add(Integer.parseInt(id));
							}
							ClassDAO classDAO = new ClassDAO();
							classDAO.classSinkyuu(classId);
							HttpSession session=request.getSession();
							session.removeAttribute("gakkaId");
							forwardPath = "/WEB-INF/jsp/classRoom.jsp";
					}
			} else {//変更
					int kyouinId = Integer.parseInt(request.getParameter("kyouinSelect"));
					String className = request.getParameter("className");
					HttpSession session=request.getSession();
					if (kyouinId == 99999 || className == "") {
							if (kyouinId == 99999) {
									String kyouinErrorMsg = "教員を選択してください";
									request.setAttribute("kyouinErrorMsg", kyouinErrorMsg);
							}
							if (className == "") {
									String classErrorMsg = "クラス名を入力してください";
									request.setAttribute("classErrorMsg", classErrorMsg);
							} else {
									session.setAttribute("className", className);
							}
							forwardPath = "/WEB-INF/jsp/classhenkoutouroku.jsp";
					} else {
							ClassRoom classRoom = (ClassRoom) session.getAttribute("classRoom");
							ClassDAO classDAO = new ClassDAO();
							//クラス名の文字列結合
							className = className + "（" + classDAO.classNameGakka(classRoom.getGakkaId())
							+ "，" + classDAO.classNameSenkou(classRoom.getSenkouId()) + "，" + classRoom.getGakunen() + "年）";
							ClassRoom classRoom2 = new ClassRoom(classRoom.getClassId(), className, kyouinId);
							classDAO.classHenkouTouroku(classRoom2);
							session.removeAttribute("classRoom");
							session.removeAttribute("className");
							session.removeAttribute("gakkaName");
							session.removeAttribute("senkouName");
							session.removeAttribute("kyouinList");
							forwardPath = "/WEB-INF/jsp/classRoom.jsp";
					}
			}
			//設定されたフォワード先にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
			dispatcher.forward(request, response);
	}

}
