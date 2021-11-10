package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Seni
 */
@WebServlet("/Seni")
public class Seni extends HttpServlet {
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
		if(action.equals("smain")) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/smain.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("seito")) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/seito.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("kyouin")) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/kyouin.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("kyouka")) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/kyouka.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("data")) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/data.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("jikanwari")) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/jikanwari.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("gakkasenkou")) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/gakkasenkou.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("class")) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/class.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("password")) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/password.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("seitojoutai")) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/seitojoutai.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
