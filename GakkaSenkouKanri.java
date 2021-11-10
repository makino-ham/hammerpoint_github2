package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SenkouDAO;
import dao.gakkaDAO;
import model.Gakka;
import model.Senkou;

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
			//フォワード先を学科専攻変更選択画面に設定
			forwardPath = "/WEB-INF/jsp/gakkahenkousentaku.jsp";
		} else if(action.equals("gakkasakujosentaku")) {
			//フォワード先を学科専攻削除選択画面に設定
			forwardPath = "/WEB-INF/jsp/gakkasakujosentaku.jsp";
		} else if(action.equals("gakkatouroku")) {
			//フォワード先を学科登録画面に設定
			forwardPath = "/WEB-INF/jsp/gakkatouroku.jsp";
		} else if(action.equals("senkoutouroku")) {
			//フォワード先を専攻登録画面に設定
			forwardPath = "/WEB-INF/jsp/senkoutouroku.jsp";
		} else if(action.equals("gakkahenkou")) {
			//フォワード先を学科変更画面に設定
			forwardPath = "/WEB-INF/jsp/gakkahenkou.jsp";
		} else if(action.equals("senkouhenkou")){
			//フォワード先を専攻変更画面に設定
			forwardPath = "/WEB-INF/jsp/senkouhenkou.jsp";
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
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		//フォワード先
		String forwardPath = null;

		//選択の処理(学科)
		if (action.equals("select")) {
			//daoのインスタンスの作成
			gakkaDAO gaDAO = new gakkaDAO();

			//メソッドの呼び出し→リストに確保
			List<Gakka> gaList = gaDAO.gakkaselect();

			//取得データをスコープへ保存
			request.setAttribute("gaList", gaList);

			//フォワード先をxx画面に設定
			forwardPath = "";
		//登録の処理(学科)
		} else if (action.equals("touroku")) {
			//リクエストパラメータから呼び出す(gakkatouroku.jsp)
			String gakka = request.getParameter("gakkatouroku");

			//gakka型に入れる
			Gakka gakka2 = new Gakka(gakka);

			//daoのインスタンスの作成
			gakkaDAO gaDAO = new gakkaDAO();

			//メソッドの呼び出し+結果を変数に入れる
			boolean hantei = gaDAO.create(gakka2);

			//hanteiがtrueかfalseか
			if (hantei) {
				//フォワード先をシス管登録完了画面に設定
				forwardPath = "WEB-INF/jsp/siskantourokukanryou.jsp";
			} else {
				//フォワード先を学科登録画面に設定
				forwardPath = "/WEB-INF/jsp/gakkatouroku.jsp";
				//エラーメッセージをリクエストスコープに保存
				request.setAttribute("errorMsg", "エラーです");
			}
		//変更の処理(学科)
		} else if(action.equals("gakkahenkou")){

		//登録の処理(専攻)
		} else if (action.equals("senkoutouroku")) {
			//リクエストパラメータから呼び出す(senkoutouroku.jsp)
			String senkou = request.getParameter("senkoutouroku2");

			//senkou型に入れる
			Senkou senkou2 = new Senkou(senkou);

			//daoのインスタンスの作成
			SenkouDAO seDAO = new SenkouDAO();

			//メソッドの呼び出し+結果を変数に入れる
			boolean hantei = seDAO.create(senkou2);

			//hanteiがtrueかfalseか
			if (hantei) {
				//フォワード先をシス管登録完了画面に設定
				forwardPath = "WEB-INF/jsp/siskantourokukanryou.jsp";
			} else {
				//フォワード先を専攻登録画面に設定
				forwardPath = "/WEB-INF/jsp/senkoutouroku.jsp";
				//エラーメッセージをリクエストスコープに保存
				request.setAttribute("errorMsg", "エラーです");
			}
		}

		//出力画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}
