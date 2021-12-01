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

import dao.SenkouDAO;
import dao.gakkaDAO;
import model.Gakka;
import model.Senkou;

/**
 * Servlet implementation class SenkouKanri
 */
@WebServlet("/SenkouKanri")
public class SenkouKanri extends HttpServlet {
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
		System.out.println("action:" + action);

		if(action.equals("senkoutouroku")) {
			//Listの宣言
			List<Gakka> gakkalist = new ArrayList<>();
			//インスタンスの作成
			gakkaDAO gakkadao = new gakkaDAO();
			//gakkaDAOのgkkaselectメソッドを呼び出し、学科データを取得しListに代入
			gakkalist = gakkadao.gakkaselect();
			//取得したlistデータをスコープに保存(リクエストスコープ)
			request.setAttribute("gakkalist", gakkalist);
			//フォワード先を専攻登録画面に設定
			forwardPath = "/WEB-INF/jsp/senkoutouroku.jsp";
		} else if(action.equals("senkouhenkoukensaku")) {
			//Listの宣言
			List<Senkou> senkoulist = new ArrayList<>();
			//インスタンスの作成
			SenkouDAO senkoudao = new SenkouDAO();
			//senkouDAOのsenkouselectメソッドを呼び出し、学科データを取得しListに代入
			senkoulist = senkoudao.senkouselect();
			//取得したlistデータをスコープに保存(リクエストスコープ)
			request.setAttribute("senkoulist", senkoulist);
			//フォワード先を専攻変更検索画面に設定
			forwardPath = "/WEB-INF/jsp/senkouhenkoukensaku.jsp";
		} else if(action.equals("slnk")) {
			//リクエストパラメータからidを取得(gakkahenkoukensaku.jsp)
			String id = request.getParameter("id");
			//インスタンスの作成
			SenkouDAO sedao = new SenkouDAO();
			gakkaDAO gadao = new gakkaDAO();
			//senkouIdを引数にして、senkouとgakkaを取得
			Senkou senkou = sedao.senkouhenkoukensaku(id);
			String gakkaid = String.valueOf(senkou.getGakkaId());
			Gakka gakka = gadao.gakkahenkoukensaku(gakkaid);
			//取得したsenkouとgakkaをスコープに保存
			request.setAttribute("senkou", senkou);
			request.setAttribute("gakka", gakka);
			//フォワード先を専攻変更検索画面に設定
			forwardPath = "/WEB-INF/jsp/senkouhenkou.jsp";
		} else if(action.equals("senkousakujo")) {
			//Listの宣言
			List<Senkou> senkoulist = new ArrayList<>();
			//インスタンスの作成
			SenkouDAO senkoudao = new SenkouDAO();
			//gakkaDAOのgakkaselectメソッドを呼び出し、学科データを取得しListに代入
			senkoulist = senkoudao.senkouselect();
			//取得したlistデータをスコープに保存(リクエストスコープ)
			request.setAttribute("senkoulist", senkoulist);
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

		//登録の処理(専攻)
		if (action.equals("sisukantourokukanryou")) {
			//リクエストパラメータから呼び出す(senkoutouroku.jsp)
			String senkou = request.getParameter("senkoutouroku2");
			int gakkaid = Integer.parseInt(request.getParameter("gakka"));

			//senkou型に入れる
			Senkou senkou2 = new Senkou(senkou, gakkaid);

			//daoのインスタンスの作成
			SenkouDAO seDAO = new SenkouDAO();

			//メソッドの呼び出し+結果を変数に入れる
			boolean hantei = seDAO.create(senkou2);

			//hanteiがtrueかfalseか
			if (hantei) {
				//フォワード先をシス管登録完了画面に設定
				forwardPath = "/WEB-INF/jsp/sisukantourokukanryou.jsp";
			} else {
				//フォワード先を専攻登録画面に設定
				forwardPath = "/WEB-INF/jsp/senkoutouroku.jsp";
				//エラーメッセージをリクエストスコープに保存
				request.setAttribute("errorMsg", "エラーです");
			}
		//削除の処理(専攻)
		}else if (action.equals("sisukansakujokanryou")) {
			//リクエストパラメータから呼び出す(senkousakujo.jsp)
			String senkouid = request.getParameter("senkou");
			System.out.println(senkouid);
			int senkouid2 = Integer.parseInt(senkouid);

			//daoのインスタンスの作成
			SenkouDAO seDAO = new SenkouDAO();

			//senkou型に入れる
			Senkou senkou = new Senkou(senkouid2);

			//メソッドの呼び出し+結果を変数に入れる
			boolean hantei = seDAO.senkousakujo(senkou);

			//hanteiがtrueかfalseか
			if (hantei) {
				//フォワード先をシス管削除完了画面に設定
				forwardPath = "/WEB-INF/jsp/sisukansakujokanryou.jsp";
			} else {
				//フォワード先を専攻削除画面に設定
				forwardPath = "/WEB-INF/jsp/senkousakujo.jsp";
				//エラーメッセージをリクエストスコープに保存
				request.setAttribute("errorMsg", "エラーです");
			}
		//変更の処理(専攻)
		} else {
			//リクエストパラメータから呼び出す(senkouhenkou.jsp)
			int senkouid = Integer.parseInt(action);
			String senkouname = request.getParameter("senkouName");

			//senkou型に入れる
			Senkou senkou = new Senkou(senkouid, senkouname);

			//daoのインスタンスの作成
			SenkouDAO seDAO = new SenkouDAO();

			//メソッドの呼び出し+結果を変数に入れる
			boolean hantei = seDAO.senkouhenkou(senkou);

			//hanteiがtrueかfalseか
			if (hantei) {
				//フォワード先をシス管変更完了画面に設定
				forwardPath = "/WEB-INF/jsp/sisukanhenkoukanryou.jsp";
			} else {
				//フォワード先を専攻変更画面に設定
				forwardPath = "/WEB-INF/jsp/senkouhenkou.jsp";
				//エラーメッセージをリクエストスコープに保存
				request.setAttribute("errorMsg", "エラーです");
			}
		}

		//出力画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}
