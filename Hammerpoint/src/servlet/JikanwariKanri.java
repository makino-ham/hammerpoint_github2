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

		if(action.equals("kanri")){//~~時間割管理
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
	int check = 0;
	request.setAttribute("check", check);
	request.setAttribute("classList", classList);
RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/jikanwariikkatutouroku.jsp");
dispatcher.forward(request, response);
}else if (action.equals("hensaku")) {//~~時間割変更削除一覧
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

		if(action.equals("kensaku")){
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
						jikanwarit.create(jikanwari);
						forwardPath = "/WEB-INF/jsp/seito.jsp";
					}
			forwardPath = "/WEB-INF/jsp/jikanwari.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
				}
}





