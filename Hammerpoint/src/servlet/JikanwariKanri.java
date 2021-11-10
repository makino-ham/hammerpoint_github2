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
import dao.IkkatutourokuDAO;
import dao.KyoukaDAO;
import model.ClassRoom;
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
		if(action.equals("touroku")||(action.equals("hensaku"))){//登録か変更削除の場合、一括登録は下のelse ifから

		int classId=Integer.parseInt(request.getParameter("classSelect"));
		List<Kyouka> kyoukaList = new ArrayList<Kyouka>();
		KyoukaDAO kyoukaDAO = new KyoukaDAO();
		kyoukaList = kyoukaDAO.kyoukaListOut(classId);
		List<ClassRoom> classList=new ArrayList<ClassRoom>();
		ClassDAO jikanwaridao=new ClassDAO();
		classList=jikanwaridao.classListOut();
		request.setAttribute("kyoukaList", kyoukaList);
		request.setAttribute("classList",classList);
		int check = 1;
		request.setAttribute("check", check);
//もう一度遷移
//ここで時間割登録と時間割変更削除一覧の画面に分岐する

		if(action.equals("touroku")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/jikanwaritouroku.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("hensaku")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/jikanwarihensaku.jsp");
			dispatcher.forward(request, response);
		}
		}
		else if(action.equals("ikkatutouroku")){//一括登録の場合

			IkkatutourokuDAO ikkatutourokuDAO = new IkkatutourokuDAO();

//			IkkatutourokuDAO touroku=ikkatutourokuDAO.Ikkatutouroku();

			int check = 1;
			request.setAttribute("check", check);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Ikkatutouroku.jsp");
			dispatcher.forward(request, response);



				}
			}
		}



