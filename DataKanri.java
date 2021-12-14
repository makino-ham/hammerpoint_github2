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
import dao.DataDAO;
import dao.SeitoDAO;
import dao.SyukketuDAO;
import model.ClassRoom;
import model.Doubutu;
import model.Seito;
import model.Syukketu;

/**
 * Servlet implementation class DataKanri
 */
@WebServlet("/DataKanri")
public class DataKanri extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String forwardPath = null;

		String action = request.getParameter("action");

		if (action == null) {
				forwardPath = "/WEB-INF/jsp/data.jsp";
		} else if (action.equals("seito")) {
				List<ClassRoom> classList = new ArrayList<ClassRoom>();
				ClassDAO classDAO = new ClassDAO();
				classList = classDAO.classListOut();
				request.setAttribute("classList", classList);
				forwardPath = "/WEB-INF/jsp/seitodata.jsp";
		} else if (action.equals("doubutu")) {
				DataDAO dataDAO = new DataDAO();
				List<Doubutu> doubutuList = dataDAO.doubutuDataOut2();
				request.setAttribute("doubutuList", doubutuList);
				forwardPath = "/WEB-INF/jsp/doubutuData.jsp";
		} else if (action.equals("kaikinsyou")) {
				List<ClassRoom> classList = new ArrayList<ClassRoom>();
				ClassDAO classDAO = new ClassDAO();
				classList = classDAO.classListOut();
				//セッションスコープに情報を保存
				request.setAttribute("classList", classList);
				forwardPath = "/WEB-INF/jsp/kaikinsyoudata.jsp";
		}
			//フォワード文の記述
			RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
			dispatcher.forward(request, response);

		}



		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			//フォワード先
			String forwardPath = null;

			//actionの値をリクエストパラメータから取得
			String action = request.getParameter("action");

			if(action.equals("kensaku")) {
					int classId = Integer.parseInt(request.getParameter("classSelect"));
					if(classId == 999) {
							request.setAttribute("errorMsg", "クラスIDが未選択です");
					}else {
							List<Seito> seitoList = new ArrayList<Seito>();
							SeitoDAO seitoDAO = new SeitoDAO();
							seitoList = seitoDAO.seitoListOut(classId);
							int check = 1;
							request.setAttribute("check", check);
							request.setAttribute("classId", classId);
							request.setAttribute("seitoList", seitoList);
					}
					List<ClassRoom> classList = new ArrayList<ClassRoom>();
					ClassDAO classDAO = new ClassDAO();
					classList = classDAO.classListOut();
					request.setAttribute("classList", classList);
					forwardPath = "/WEB-INF/jsp/seitodata.jsp";
			} else if (action.equals("kaikinsyou")) {
					int classId = Integer.parseInt(request.getParameter("classSelect"));
					if (classId == 999) {
							request.setAttribute("errorMsg", "クラスIDが未選択です");
					} else {
							int check = 1;
							//生徒のリストを作成
							List<Seito> seitoList = new ArrayList<Seito>();
							SeitoDAO seitoDAO = new SeitoDAO();
							seitoList = seitoDAO.seitoListOut(classId);
							//出欠のリストを作成
							List<Syukketu> syukketuList = new ArrayList<Syukketu>();
							SyukketuDAO syukketuDAO = new SyukketuDAO();
							syukketuList = syukketuDAO.syukketuListOut();
							//選別された出欠リスト
							List<Syukketu> senbetuSyukketuList = new ArrayList<Syukketu>();
							//選別された学籍番号リスト
							List<String> senbetuGakusekiList = new ArrayList<String>();
							//選別された生徒リスト
							List<Seito> senbetuSeitoList = new ArrayList<Seito>();
							//すべての出欠リストから特定のクラスのものだけを選別リストに追加する処理
							for (Syukketu syukketu: syukketuList) {
									for (Seito seito: seitoList) {
											if (syukketu.getGakusekiId().equals(seito.getGakusekiId())) {
													senbetuSyukketuList.add(syukketu);
											}
									}
							}
							int i = 0;//学生が存在していた場合は１存在していない場合は0
							//選別した出欠リストから欠席が一つでもある生徒のみを抽出し、選別学籍リストに追加する処理
							for (Syukketu syukketu: senbetuSyukketuList) {
									if (syukketu.getSyukketu() == 1) {
											for (String gakuseki: senbetuGakusekiList) {
													if (syukketu.getGakusekiId().equals(gakuseki)) {
															i = 1;
															break;//すでに学生が存在していた場合処理を終了
													}
											}
											if (i == 0) {
													senbetuGakusekiList.add(syukketu.getGakusekiId());
											}
									}
									i = 0;
							}
							//生徒リストから欠席のある生徒のみを抜いて、選別された生徒リストを作成
							for (Seito seito: seitoList) {
									for (String gakuseki: senbetuGakusekiList) {
											if (seito.getGakusekiId().equals(gakuseki)) {
													i = 1;
													break;
											}
									}
									if (i == 0) {
											senbetuSeitoList.add(seito);
									} else {
											i = 0;
									}
							}
							request.setAttribute("classId", classId);
							request.setAttribute("check", check);
							request.setAttribute("seitoList", senbetuSeitoList);

					}
					List<ClassRoom> classList = new ArrayList<ClassRoom>();
					ClassDAO classDAO = new ClassDAO();
					classList = classDAO.classListOut();
					request.setAttribute("classList", classList);
					forwardPath = "/WEB-INF/jsp/kaikinsyoudata.jsp";
			}

			//フォワード文の記述
			RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
			dispatcher.forward(request, response);
			}
		}


