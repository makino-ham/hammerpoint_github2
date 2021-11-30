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
import model.ClassRoom;
import model.Doubutu;

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
				String classIdS = request.getParameter("class_ID");
				String seitoName = request.getParameter("seito_name");
				String genderS = request.getParameter("gender");
				String doubutuIdS = request.getParameter("doubutu_ID");
				List<ClassRoom> classList = new ArrayList<ClassRoom>();
				ClassDAO classDAO = new ClassDAO();
				classList = classDAO.classListOut();
		} else if (action.equals("doubutu")) {
				DataDAO dataDAO = new DataDAO();
				List<Doubutu> doubutuList = dataDAO.doubutuDataOut2();
				request.setAttribute("doubutuList", doubutuList);
				forwardPath = "/WEB-INF/jsp/doubutuData.jsp";
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
		}

	}
