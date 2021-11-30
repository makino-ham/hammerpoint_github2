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
import dao.DoubutuDAO;
import dao.SeitoDAO;
import model.ClassRoom;
import model.Doubutu;
import model.Seito;

/**
 * Servlet implementation class SeitoKanri
 */
@WebServlet("/SeitoKanri")
public class SeitoKanri extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		//フォワード先
		String forwardPath = null;
		if (action == null) {
			forwardPath = "/WEB-INF/jsp/seito.jsp";
		} else if (action.equals("touroku")) {
			//動物リストを作成
			List<Doubutu> doubutuList = new ArrayList<Doubutu>();
			DoubutuDAO doubutuDAO = new DoubutuDAO();
			doubutuList = doubutuDAO.doubutuListOut();
			//クラスリストを作成
			List<ClassRoom> classList = new ArrayList<ClassRoom>();
			ClassDAO classDAO = new ClassDAO();
			classList = classDAO.classListOut();
			//チェック用変数
			int check = 0;
			//sessionが残っていた場合削除
			HttpSession session=request.getSession();
			session.removeAttribute("seito");
			request.setAttribute("check", check);
			request.setAttribute("classList", classList);
			request.setAttribute("doubutuList", doubutuList);
			forwardPath = "/WEB-INF/jsp/seitotouroku.jsp";
		} else if(action.equals("kensaku")) {
			List<ClassRoom> classList = new ArrayList<ClassRoom>();
			ClassDAO classDAO = new ClassDAO();
			classList = classDAO.classListOut();
			//チェック用変数
			int check = 0;
			request.setAttribute("check", check);
			/*ユーザー情報をセッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("check", check);*/
			//sessionが残っていた場合削除
			HttpSession session=request.getSession();
			session.removeAttribute("seito");
			request.setAttribute("classList", classList);
			forwardPath = "/WEB-INF/jsp/seitokensaku.jsp";
		} else if (action.equals("joutai")) {
			List<ClassRoom> classList = new ArrayList<ClassRoom>();
			ClassDAO classDAO = new ClassDAO();
			classList = classDAO.classListOut();
			int check = 0;
			//sessionが残っていた場合削除
			HttpSession session=request.getSession();
			session.removeAttribute("seito");
			request.setAttribute("classList", classList);
			request.setAttribute("check", check);
			forwardPath = "/WEB-INF/jsp/seitojoutai.jsp";
		} else if (action.equals("ikkatu")) {
			//クラスリストを作成
			List<ClassRoom> classList = new ArrayList<ClassRoom>();
			ClassDAO classDAO = new ClassDAO();
			classList = classDAO.classListOut();
			//動物リストを作成
			List<Doubutu> doubutuList = new ArrayList<Doubutu>();
			DoubutuDAO doubutuDAO = new DoubutuDAO();
			doubutuList = doubutuDAO.doubutuListOut();
			request.setAttribute("classList", classList);
			request.setAttribute("doubutuList", doubutuList);
			forwardPath = "/WEB-INF/jsp/seitoikkatutouroku.jsp";
		}	else {
			Seito seito = new Seito();
			SeitoDAO seitoDAO = new SeitoDAO();
			seito = seitoDAO.seitoHenkou(action);//学籍番号を引数にセットする
			//動物リストを作成
			List<Doubutu> doubutuList = new ArrayList<Doubutu>();
			DoubutuDAO doubutuDAO = new DoubutuDAO();
			doubutuList = doubutuDAO.doubutuListOut();
			//クラスリストを作成
			List<ClassRoom> classList = new ArrayList<ClassRoom>();
			ClassDAO classDAO = new ClassDAO();
			classList = classDAO.classListOut();
			HttpSession session=request.getSession();
			//sessionスコープの破棄
			session.removeAttribute("classId");
			session.removeAttribute("seitoList");
			session.setAttribute("seito", seito);
			request.setAttribute("classList", classList);
			request.setAttribute("doubutuList", doubutuList);
			forwardPath = "/WEB-INF/jsp/seitohenkou.jsp";
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
		if (action.equals("touroku")) {
			String gakuseki = request.getParameter("gakuseki");
			String seitoName = request.getParameter("seitoName");
			int gender = Integer.parseInt(request.getParameter("gender"));
			String mail = request.getParameter("mail");
			int doubutu = Integer.parseInt(request.getParameter("doubutuSelect"));
			int classRoom = Integer.parseInt(request.getParameter("classSelect"));
			//必須項目が入力されていないかチェック
			if (gakuseki.length() < 8 || seitoName == "" || mail == "" || doubutu == 999 || classRoom == 999) {
					//設定されたフォワード先にフォワード
					Seito seito = new Seito(gakuseki, seitoName, mail, classRoom, doubutu, gender);
					int check = 1;
					//動物リストを作成
					List<Doubutu> doubutuList = new ArrayList<Doubutu>();
					DoubutuDAO doubutuDAO = new DoubutuDAO();
					doubutuList = doubutuDAO.doubutuListOut();
					//クラスリストを作成
					List<ClassRoom> classList = new ArrayList<ClassRoom>();
					ClassDAO classDAO = new ClassDAO();
					classList = classDAO.classListOut();
					request.setAttribute("check", check);
					request.setAttribute("doubutuList", doubutuList);
					request.setAttribute("classList", classList);
					request.setAttribute("seito", seito);
					if (gakuseki.length() < 8) {
							String gakusekiMsg = "学籍番号が無効です";
							request.setAttribute("gakusekiMsg", gakusekiMsg);
						}
					if (seitoName == "") {
							String nameMsg = "生徒名が入力されていません";
							request.setAttribute("nameMsg", nameMsg);
					}
					if (mail == "") {
							String mailMsg = "メールアドレスが入力されていません";
							request.setAttribute("mailMsg", mailMsg);
					}
					if (classRoom == 999) {
							String classMsg = "クラスが選択されていません";
							request.setAttribute("classMsg", classMsg);
					}
					if (doubutu == 999) {
							String doubutuMsg = "動物が選択されていません";
							request.setAttribute("doubutuMsg", doubutuMsg);
					}
					forwardPath = "/WEB-INF/jsp/seitotouroku.jsp";
				} else {
					//データベースへの登録処理
					Seito seito = new Seito(gakuseki, seitoName, mail, classRoom, doubutu, gender);
					SeitoDAO seitoDAO = new SeitoDAO();
					seitoDAO.create(seito);
					forwardPath = "/WEB-INF/jsp/seito.jsp";
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
				dispatcher.forward(request, response);
		} else if (action.equals("kensaku")) {//seitokensaku画面から遷移
				int classId = Integer.parseInt(request.getParameter("classSelect"));
				if (classId == 999) {
						String errorMsg = "クラスが選択されていません";
						request.setAttribute("errorMsg", errorMsg);
						int check = 2;
						request.setAttribute("check", check);
				} else {
						List<Seito> seitoList = new ArrayList<Seito>();
						SeitoDAO seitoDAO = new SeitoDAO();
						//生徒リストの作成
						seitoList = seitoDAO.seitoListOut(classId);
						//検索ボタンを押したかチェック
						int check = 1;
//						セッションスコープに情報を保存
						HttpSession session=request.getSession();
						request.setAttribute("check", check);
						session.setAttribute("classId", classId);
						session.setAttribute("seitoList", seitoList);
				}
				//クラスリストの作成
					List<ClassRoom> classList = new ArrayList<ClassRoom>();
					ClassDAO classDAO = new ClassDAO();
					classList = classDAO.classListOut();
					request.setAttribute("classList", classList);
					forwardPath = "/WEB-INF/jsp/seitokensaku.jsp";
		}  else if (action.equals("sakujo")) {
					String [] gakusekiId = request.getParameterValues("gakusekiId");
					//何も選択されていない場合変更削除画面に遷移する。
					if (gakusekiId == null) {
							int check = 3;
							//クラスリストを作成
							List<ClassRoom> classList = new ArrayList<ClassRoom>();
							ClassDAO classDAO = new ClassDAO();
							classList = classDAO.classListOut();
							request.setAttribute("check", check);
							request.setAttribute("classList", classList);
							forwardPath = "/WEB-INF/jsp/seitokensaku.jsp";
					} else {
							SeitoDAO seitoDAO = new SeitoDAO();
							seitoDAO.seitoSakujo(gakusekiId);
//							セッションスコープの破棄
							HttpSession session=request.getSession();
							session.removeAttribute("classId");
							session.removeAttribute("seitoList");
							forwardPath =  "/WEB-INF/jsp/seito.jsp";
					}

		}  else if (action.equals("joutaiKensaku")) {//seitojoutai画面から遷移
					String gakusekiId = request.getParameter("gakusekiId");
					//在学もしくは休学のフラグを取得
					int flag = Integer.parseInt(request.getParameter("flag"));
					//生徒リストの作成
					List<Seito> seitoList = new ArrayList<Seito>();
					SeitoDAO seitoDAO = new SeitoDAO();
					//クラスリストの作成
					List<ClassRoom> classList = new ArrayList<ClassRoom>();
					ClassDAO classDAO = new ClassDAO();
					classList = classDAO.classListOut();
					if (gakusekiId == "") {//学籍番号が入力されていない場合
							int classId = Integer.parseInt(request.getParameter("classSelect"));
							if (classId == 999) {
									int check = 4;
									request.setAttribute("check", check);
							} else {
									//生徒リストの作成
									seitoList = seitoDAO.joutaiKensaku(flag, classId);
									int check = 1;
									request.setAttribute("classId", classId);
									request.setAttribute("check", check);
									request.setAttribute("seitoList", seitoList);
							}
					} else {//学籍番号が入力されている場合
								//生徒リストの作成
								seitoList = seitoDAO.gakusekiIdKensaku(flag, gakusekiId);

								if (seitoList.size() != 0) {
										Seito seito = new Seito();
										seito = seitoDAO.seitoClassId(gakusekiId);
										int classId = seito.getClassId();
										int check = 3;
										request.setAttribute("gakusekiId", gakusekiId);
										request.setAttribute("check", check);
										request.setAttribute("classId", classId);
										request.setAttribute("seitoList", seitoList);
								} else {
										int check = 2;
										request.setAttribute("check", check);
								}
						}
						request.setAttribute("flag", flag);
						request.setAttribute("classList", classList);
						System.out.println(">>>>***");
						forwardPath = "/WEB-INF/jsp/seitojoutai.jsp";

			} else if (action.equals("joutaiTouroku")) {//途中
					String [] gakusekiId = request.getParameterValues("gakusekiId");
					if (gakusekiId == null) {
							int check = 5;
							//クラスリストの作成
							List<ClassRoom> classList = new ArrayList<ClassRoom>();
							ClassDAO classDAO = new ClassDAO();
							classList = classDAO.classListOut();
							request.setAttribute("classList", classList);
							request.setAttribute("check", check);
							forwardPath = "/WEB-INF/jsp/seitojoutai.jsp";
					} else {
							int flag = Integer.parseInt(request.getParameter("flag"));
							SeitoDAO seitoDAO = new SeitoDAO();
							if (flag == 4) {
								seitoDAO.fukugakuHenkou(gakusekiId);
							} else {
								seitoDAO.flagHenkou(gakusekiId, flag);
							}
							forwardPath =  "/WEB-INF/jsp/seito.jsp";
					}
			} else {//変更処理
					String seitoName = request.getParameter("seitoName");
					String mail = request.getParameter("mail");
					int doubutuId = Integer.parseInt(request.getParameter("doubutuSelect"));
//					セッションスコープの宣言
					HttpSession session=request.getSession();
					if (seitoName == "" || mail == "" || doubutuId == 999) {
							if (seitoName == "") {
									String nameErrorMsg = "生徒名を入力してください";
									request.setAttribute("nameErrorMsg", nameErrorMsg);
							} else {
									Seito seito = (Seito) session.getAttribute("seito");
									seito.setSeitoName(seitoName);
							}
							if (mail == "") {
									String mailErrorMsg = "メールアドレスが入力されていません";
									request.setAttribute("mailErrorMsg", mailErrorMsg);
							}
							if (doubutuId == 999) {
									String doubutuErrorMsg = "動物を選択してください";
									request.setAttribute("doubutuErrorMsg", doubutuErrorMsg);
							}
							//動物リストを作成
							List<Doubutu> doubutuList = new ArrayList<Doubutu>();
							DoubutuDAO doubutuDAO = new DoubutuDAO();
							doubutuList = doubutuDAO.doubutuListOut();
							request.setAttribute("doubutuList", doubutuList);
							forwardPath =  "/WEB-INF/jsp/seitohenkou.jsp";
					} else {
							Seito seito = new Seito(action, seitoName, mail, doubutuId);//actionは学籍番号
							SeitoDAO seitoDAO = new SeitoDAO();
							seitoDAO.seitoHenkouTouroku(seito);
							session.removeAttribute("seito");
							forwardPath =  "/WEB-INF/jsp/seito.jsp";
					}
			}
			//設定されたフォワード先にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
			dispatcher.forward(request, response);
		}

}
