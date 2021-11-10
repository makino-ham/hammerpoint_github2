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
			List<Doubutu> doubutuList = new ArrayList<Doubutu>();
			DoubutuDAO doubutuDAO = new DoubutuDAO();
			doubutuList = doubutuDAO.doubutuListOut();
			List<ClassRoom> classList = new ArrayList<ClassRoom>();
			ClassDAO classDAO = new ClassDAO();
			classList = classDAO.classListOut();
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
			request.setAttribute("classList", classList);
			forwardPath = "/WEB-INF/jsp/seitokensaku.jsp";
		} else {
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
			request.setAttribute("seito", seito);
			request.setAttribute("classList", classList);
			request.setAttribute("doubutuList", doubutuList);
			forwardPath = "/WEB-INF/jsp/seitohenkou.jsp";
		//設定されたフォワード先にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
		}

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
			//データベースへの登録処理
			Seito seito = new Seito(gakuseki, seitoName, mail, classRoom, doubutu, gender);
			SeitoDAO seitoDAO = new SeitoDAO();
			seitoDAO.create(seito);
			forwardPath = "/WEB-INF/jsp/seito.jsp";
		} else if (action.equals("kensaku")) {
			int classId = Integer.parseInt(request.getParameter("classSelect"));
			List<Seito> seitoList = new ArrayList<Seito>();
			SeitoDAO seitoDAO = new SeitoDAO();
			//生徒リストの作成
			seitoList = seitoDAO.seitoListOut(classId);
			List<ClassRoom> classList = new ArrayList<ClassRoom>();
			ClassDAO classDAO = new ClassDAO();
			classList = classDAO.classListOut();
			//検索ボタンを押したかチェック
			int check = 1;
			request.setAttribute("classList", classList);
			request.setAttribute("check", check);
			request.setAttribute("seitoList", seitoList);
			forwardPath = "/WEB-INF/jsp/seitokensaku.jsp";
		}  else if (action.equals("sakujo")) {
			String [] gakusekiId = request.getParameterValues("gakusekiId");

		} else {//変更処理
			String seitoName = request.getParameter("seitoName");
			String mail = request.getParameter("mail");
			int doubutuId = Integer.parseInt(request.getParameter("doubutuSelect"));
			Seito seito = new Seito(action, seitoName, mail, doubutuId);//actionは学籍番号
			SeitoDAO seitoDAO = new SeitoDAO();
			seitoDAO.seitoHenkouTouroku(seito);
			forwardPath =  "/WEB-INF/jsp/seito.jsp";
		}
		//設定されたフォワード先にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}
