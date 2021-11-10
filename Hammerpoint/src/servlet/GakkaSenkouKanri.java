package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GakkaSenkouKanri
 */
@WebServlet("/GakkaSenkouKanri")
public class GakkaSenkouKanri extends HttpServlet {
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

		if(action.equals("gakkasenkousentaku")) {
			//フォワード先を学科専攻登録選択画面に設定
			forwardPath = "/WEB-INF/jsp/gakkasenkousentaku.jsp";
		} else if(action.equals("gakkahenkousentaku")) {
			//フォワード先を学科変更の選択画面に設定
			forwardPath = "/WEB-INF/jsp/gakkahenkousentaku.jsp";
		} else if(action.equals("gakkasakujosentaku")) {
			//フォワード先を学科専攻登録選択画面に設定
			forwardPath = "/WEB-INF/jsp/gakkasakujosentaku.jsp";
		} else if(action.equals("gakkatouroku")) {
			//フォワード先を学科登録画面に設定
			forwardPath = "/WEB-INF/jsp/gakkatouroku.jsp";
		} else if(action.equals("senkoutouroku")) {
			//フォワード先を専攻登録画面に設定
			forwardPath = "/WEB-INF/jsp/senkoutouroku.jsp";
		} else if(action.equals("gakkasakujosentaku")) {
			//フォワード先を学科専攻削除選択画面に設定
			forwardPath = "/WEB-INF/jsp/gakkasenkousentaku.jsp";
		} else if(action.equals("gakkasakujo")) {
			//フォワード先を学科削除画面に設定
			forwardPath = "/WEB-INF/jsp/gakkasakujo.jsp";
		} else if(action.equals("senkousakujo")) {
			//フォワード先を専攻削除画面に設定
			forwardPath = "/WEB-INF/jsp/senkousakujo.jsp";
		} else {

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
		doGet(request, response);
	}

}
