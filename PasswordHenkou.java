package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PasswordDAO;
import model.Password;

/**
 * Servlet implementation class PasswordHenkou
 */
@WebServlet("/PasswordHenkou")
public class PasswordHenkou extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		//フォワード先
		String forwardPath = null;

		//actionの値をリクエストパラメータから取得
		String action = request.getParameter("action");

		if(action.equals("aaa")) {
			HttpSession session=request.getSession();
			Password pass = new Password();
			pass = (Password) session.getAttribute("account");
			String password = request.getParameter("newpass");
			String password2 = request.getParameter("input_pass");
			PasswordDAO passwordDAO = new PasswordDAO();
			forwardPath =  "/WEB-INF/jsp/password.jsp";
			if(password == "") {
				request.setAttribute("errorMsg", "パスワードが未入力です");
			}
			else if(password2 == ""){
				request.setAttribute("errorMsg2", "確認用パスワードが未入力です");
			}
			else {
				int siskanId = pass.getSiskanid();
				Password pw = new Password(password,siskanId);
				passwordDAO.update(pw);
				forwardPath =  "/WEB-INF/jsp/sisukanhenkoukanryou.jsp";
			}
		}

		//フォワード文の記述
			RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
			dispatcher.forward(request, response);
	}

}
