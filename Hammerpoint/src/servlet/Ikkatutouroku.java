package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IkkatutourokuDAO;

/**
 * Servlet implementation class Ikkatutouroku
 */
@WebServlet("/Ikkatutouroku")
public class Ikkatutouroku extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String ikkatuFile=request.getParameter("ikkatutouroku");
		IkkatutourokuDAO ikkatutourokudao=new IkkatutourokuDAO();
		ikkatutourokudao.Ikkatutouroku(ikkatuFile);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/jikanwariikkatutouroku.jsp");
		dispatcher.forward(request, response);
	}

}
