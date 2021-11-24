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
import dao.JikanwariDAO;
import dao.KyoukaDAO;
import model.ClassRoom;
import model.Jikanwari;
import model.Kyouka;

/**
 * Servlet implementation class JikanwariKanri
 */
@WebServlet("/JikanwariKanri")
public class JikanwariKanri extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		String zenbu=request.getParameter("zenbu");
//		System.out.println(zenbu);
		if(action==null){
			String[] z = zenbu.split(",");
			int kyoukaId=Integer.parseInt(z[0]);
			int youbiId=Integer.parseInt(z[1]);
			int jigenId=Integer.parseInt(z[2]);

			HttpSession session=request.getSession();
			JikanwariDAO jikanwariDAO=new JikanwariDAO();
			Jikanwari jikanwari=jikanwariDAO.jikanwariHenkou(kyoukaId,youbiId,jigenId);
			session.setAttribute("jikanwari", jikanwari);

//			曜日出すやつ
			List<Jikanwari> youbiList=new ArrayList<Jikanwari>();
			youbiList=jikanwariDAO.youbiListOut();
			request.setAttribute("youbiList",youbiList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/jikanwarihenkou.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("kanri")){//~~時間割管理
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/jikanwari.jsp");
			dispatcher.forward(request, response);
}else if (action.equals("touroku")) {//~~時間割登録
		List<ClassRoom> classList = new ArrayList<ClassRoom>();
		ClassDAO classDAO=new ClassDAO();
		classList=classDAO.classListOut();
		int check = 0;
		request.setAttribute("check", check);
		request.setAttribute("classList", classList);
	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/jikanwaritouroku.jsp");
	dispatcher.forward(request, response);
	}else if (action.equals("ikkatu")) {//~~時間割一括登録
	List<ClassRoom> classList = new ArrayList<ClassRoom>();
	ClassDAO classDAO=new ClassDAO();
	classList=classDAO.classListOut();
//	教科一覧だすやつ
	List<Kyouka> kyoukaList = new ArrayList<Kyouka>();
	KyoukaDAO kyoukaDAO = new KyoukaDAO();
	kyoukaList = kyoukaDAO.kyoukaIchiran();
	request.setAttribute("kyoukaList", kyoukaList);

	int check = 0;
	request.setAttribute("check", check);
	request.setAttribute("classList", classList);
RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/jikanwariikkatutouroku.jsp");
dispatcher.forward(request, response);
}else if (action.equals("hensaku")) {//~~時間割変更削除一覧
//	曜日出すやつ
	JikanwariDAO jikanwariDAO=new JikanwariDAO();
	List<Jikanwari> youbiList=new ArrayList<Jikanwari>();
	youbiList=jikanwariDAO.youbiListOut();
	request.setAttribute("youbiList",youbiList);

	List<ClassRoom> classList = new ArrayList<ClassRoom>();
	ClassDAO classDAO=new ClassDAO();
	classList=classDAO.classListOut();
	int check = 0;
	request.setAttribute("check", check);
	request.setAttribute("classList", classList);
RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/jikanwarihensaku.jsp");
dispatcher.forward(request, response);
}
}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		リクエストパラメータの取得
//		時間割登録のプルダウン（クラス選択）
		String action=request.getParameter("action");
		request.setCharacterEncoding("UTF-8");
		String forwardPath = null;

		if(action.equals("henkou")){
				//必須項目が入力されていないかチェック
				String[] sakujo =request.getParameterValues("jikanwariId");
//				System.out.println(sakujo[0]);
				if (sakujo==null) {
						//設定されたフォワード先にフォワード
						int check = 1;
								String errerMsg = "チェックボックスが選択されていません";
//								教科の名前出すやつ
								int classId=Integer.parseInt(request.getParameter("classSelect"));
								HttpSession session=request.getSession();
								session.setAttribute("classId", classId);
								List<Kyouka> kyoukaList = new ArrayList<Kyouka>();
								KyoukaDAO kyoukaDAO = new KyoukaDAO();
								kyoukaList = kyoukaDAO.kyoukaListOut(classId);
								session.setAttribute("kyoukaList", kyoukaList);
//								クラスの名前出すやつ
								List<ClassRoom> classList=new ArrayList<ClassRoom>();
								ClassDAO jikanwaridao=new ClassDAO();
								classList=jikanwaridao.classListOut();
								request.setAttribute("classList", classList);
//								曜日出すやつ
								List<Jikanwari> youbiList=new ArrayList<Jikanwari>();
								JikanwariDAO jikanwariDAO = new JikanwariDAO();
								youbiList=jikanwariDAO.youbiListOut();
								request.setAttribute("youbiList",youbiList);

								request.setAttribute("check", check);
								request.setAttribute("errerMsg", errerMsg);
								RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/jikanwarihensaku.jsp");
								dispatcher.forward(request, response);

						}else {//チェックボックス選択したら

							List<Jikanwari> jikanwariList = new ArrayList<Jikanwari>();
							for (String zenbu: sakujo) {
								String[] z = zenbu.split(",");
								int kyoukaId=Integer.parseInt(z[0]);
								int youbiId=Integer.parseInt(z[1]);
								int jigenId=Integer.parseInt(z[2]);
								Jikanwari jikanwari = new Jikanwari();
								jikanwari.setKyoukaId(kyoukaId);
								jikanwari.setYoubiId(youbiId);
								jikanwari.setJigen(jigenId);
								jikanwariList.add(jikanwari);
								System.out.println(z[0]+","+z[1]+","+z[2]);
							}
//							for(int i=0;i<3;i++) {
//								System.out.print(jikanwariList.get(i).getYoubiId()+",");
//							System.out.print(jikanwariList.get(i).getKyoukaId()+",");
//							System.out.print(jikanwariList.get(i).getJigen()+",");
//							}
							//データベースへの登録処理
							JikanwariDAO jikanwarit=new JikanwariDAO();
							jikanwarit.flagHenkou(jikanwariList);
							HttpSession session=request.getSession();
							session.removeAttribute("youbiId");
							session.removeAttribute("jigenId");
							session.removeAttribute("jikanwariList");
							forwardPath = "/WEB-INF/jsp/jikanwari.jsp";
							}

}else if(action.equals("kensaku")){
//		クラスの名前出すやつ
			List<ClassRoom> classList=new ArrayList<ClassRoom>();
			ClassDAO jikanwaridao=new ClassDAO();
			classList=jikanwaridao.classListOut();
			request.setAttribute("classList", classList);
//			教科の名前出すやつ
		int classId=Integer.parseInt(request.getParameter("classSelect"));
		HttpSession session=request.getSession();
		session.setAttribute("classId", classId);
		List<Kyouka> kyoukaList = new ArrayList<Kyouka>();
		KyoukaDAO kyoukaDAO = new KyoukaDAO();
		kyoukaList = kyoukaDAO.kyoukaListOut(classId);
		session.setAttribute("kyoukaList", kyoukaList);
//		曜日出すやつ
		List<Jikanwari> youbiList=new ArrayList<Jikanwari>();
		JikanwariDAO jikanwariDAO = new JikanwariDAO();
		youbiList=jikanwariDAO.youbiListOut();
		request.setAttribute("youbiList",youbiList);
		int check = 1;
		request.setAttribute("check", check);

		forwardPath = "/WEB-INF/jsp/jikanwaritouroku.jsp";
		}else if(action.equals("touroku")){
			//登録の処理
			String[] jigen =request.getParameterValues("jigen");
				//必須項目が入力されていないかチェック
				if (jigen==null) {
						//設定されたフォワード先にフォワード
						int check = 1;
								String errerMsg = "チェックボックスが選択されていません";
//								クラスの名前出すやつ
								List<ClassRoom> classList=new ArrayList<ClassRoom>();
								ClassDAO jikanwaridao=new ClassDAO();
								classList=jikanwaridao.classListOut();
								request.setAttribute("classList", classList);
//								曜日出すやつ
								List<Jikanwari> youbiList=new ArrayList<Jikanwari>();
								JikanwariDAO jikanwariDAO = new JikanwariDAO();
								youbiList=jikanwariDAO.youbiListOut();
								request.setAttribute("youbiList",youbiList);

								request.setAttribute("check", check);
								request.setAttribute("errerMsg", errerMsg);
								RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/jikanwaritouroku.jsp");
								dispatcher.forward(request, response);
					}else {
						HttpSession session=request.getSession();
						session.removeAttribute("classId");
						int kyoukaId=Integer.parseInt(request.getParameter("kyoukaRadio"));
						int youbiId=Integer.parseInt(request.getParameter("youbiSelect"));
						int jugyousuu=Integer.parseInt(request.getParameter("jugyousuu"));
						int jigenId=Integer.parseInt(request.getParameter("jigen"));

						//データベースへの登録処理
						Jikanwari jikanwari = new Jikanwari(kyoukaId,youbiId,jugyousuu,jigenId);
						JikanwariDAO jikanwarit=new JikanwariDAO();
						jikanwarit.create(jikanwari, jigen);
						forwardPath = "/WEB-INF/jsp/seito.jsp";
					}
			forwardPath = "/WEB-INF/jsp/jikanwari.jsp";
		}else if(action.equals("hensaku")){//seitokensaku画面から遷移
			//登録の処理
			int youbiId=Integer.parseInt(request.getParameter("youbiSelect"));
			int jigenId=Integer.parseInt(request.getParameter("jigenSelect"));
			int check=1;
//			時間割
			List<Jikanwari> jikanwariList = new ArrayList<Jikanwari>();
			JikanwariDAO jikanwariDAO=new JikanwariDAO();
			jikanwariList=jikanwariDAO.jikanwariHenkou(youbiId, jigenId);
//			曜日出すやつ
			List<Jikanwari> youbiList=new ArrayList<Jikanwari>();
			youbiList=jikanwariDAO.youbiListOut();
			request.setAttribute("youbiList",youbiList);

			HttpSession session=request.getSession();
			session.setAttribute("youbiId", youbiId);
			session.setAttribute("jigenId", jigenId);
			session.setAttribute("jikanwariList", jikanwariList);
			request.setAttribute("check",check);
			forwardPath = "/WEB-INF/jsp/jikanwarihensaku.jsp";

		} else {
						HttpSession session=request.getSession();
						Jikanwari jikanwari=(Jikanwari) session.getAttribute("jikanwari");
						session.removeAttribute("youbiId");
						session.removeAttribute("jigenId");
						session.removeAttribute("jikanwariList");

						int youbiId=Integer.parseInt(request.getParameter("youbiSelect"));
						int jugyousuu=Integer.parseInt(request.getParameter("jugyousuu"));
						int jigenId=Integer.parseInt(request.getParameter("jigen"));
						session.removeAttribute("jikanwari");
						//データベースへの登録処理
						Jikanwari jikanwari2 = new Jikanwari(youbiId,jugyousuu,jigenId);
						JikanwariDAO jikanwarit=new JikanwariDAO();
						jikanwarit.jikanwariHenkouTouroku(jikanwari,jikanwari2);
						forwardPath = "/WEB-INF/jsp/jikanwari.jsp";
					}
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
		}
}



