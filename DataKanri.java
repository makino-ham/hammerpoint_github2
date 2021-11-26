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
import model.ClassRoom;

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

		if(action.equals("seito")) {
			String classIdS = request.getParameter("class_ID");
			String seitoName = request.getParameter("seito_name");
			String genderS = request.getParameter("gender");
			String doubutuIdS = request.getParameter("doubutu_ID");
			List<ClassRoom> classList = new ArrayList<ClassRoom>();
			ClassDAO classDAO = new ClassDAO();
			classList = classDAO.classListOut();
			if(classIdS == "999") {
				request.setAttribute("errorMsg", "クラスIDが未選択です");
			}
			else {

			}
			forwardPath = "/WEB-INF/jsp/seitodata.jsp";
		}
		else if(action.equals("doubutu")) {
			forwardPath = "/WEB-INF/jsp/doubutudata.jsp";
		}
		else if(action.equals("kaikin")) {
			forwardPath = "/WEB-INF/jsp/kaikindata.jsp";
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
