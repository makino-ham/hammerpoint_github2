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
		System.out.println("action:" + action);

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
		} else if(action.equals("gakkahenkoukensaku")) {
			//Listの宣言
			List<Gakka> gakkalist = new ArrayList<>();
			//インスタンスの作成
			gakkaDAO gakkadao = new gakkaDAO();
			//gakkaDAOのgkkaselectメソッドを呼び出し、学科データを取得しListに代入
			gakkalist = gakkadao.gakkaselect();
			//取得したlistデータをスコープに保存(リクエストスコープ)
			request.setAttribute("gakkalist", gakkalist);
			//フォワード先を学科変更検索画面に設定
			forwardPath = "/WEB-INF/jsp/gakkahenkoukensaku.jsp";
		} else if(action.equals("gakkahenkou"))  {
			//リクエストパラメータから呼び出す(gakkahenkoukensaku.jsp)
			String gakka = request.getParameter("gakkaname");
			//取得データをスコープへ保存
			request.setAttribute("gakkaName", gakka);
			//フォワード先を学科変更画面に設定
			forwardPath = "/WEB-INF/jsp/gakkahenkou.jsp";
		} else if(action.equals("lnk")){
			//リクエストパラメータからidを取得(gakkahenkoukensaku.jsp)
			String id = request.getParameter("id");
			//インスタンスの作成
			gakkaDAO gadao = new gakkaDAO();
			//gakkaIdを引数にして、gakkaを取得
			Gakka gakka = gadao.gakkahenkoukensaku(id);
			//取得したgakkaをスコープに保存
			request.setAttribute("gakka", gakka);
			//フォワード先を学科変更画面に設定
			forwardPath = "/WEB-INF/jsp/gakkahenkou.jsp";
		} else if(action.equals("senkouhenkoukensaku")){
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
		} else if(action.equals("senkouhenkou")) {
			//リクエストパラメータから呼び出す(gakkahenkoukensaku.jsp)
			String senkou = request.getParameter("senkouname");
			//取得データをスコープへ保存
			request.setAttribute("senkouName", senkou);
			//フォワード先を専攻変更検索画面に設定
			forwardPath = "/WEB-INF/jsp/senkouhenkou.jsp";
		} else if(action.equals("gakkasakujo")) {
			//Listの宣言
			List<Gakka> gakkalist = new ArrayList<>();
			//インスタンスの作成
			gakkaDAO gakkadao = new gakkaDAO();
			//gakkaDAOのgkkaselectメソッドを呼び出し、学科データを取得しListに代入
			gakkalist = gakkadao.gakkaselect();
			//取得したlistデータをスコープに保存(リクエストスコープ)
			request.setAttribute("gakkalist", gakkalist);
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
				forwardPath = "/WEB-INF/jsp/sisukantourokukanryou.jsp";
			} else {
				//フォワード先を学科登録画面に設定
				forwardPath = "/WEB-INF/jsp/gakkatouroku.jsp";
				//エラーメッセージをリクエストスコープに保存
				request.setAttribute("errorMsg", "エラーです");
			}
			//変更検索の処理(学科)
		} else if(action.equals("gakkahenkou")) {
			//リクエストパラメータから呼び出す(gakkahenkoukensaku.jsp)
			String gakka = request.getParameter("gakkaname");

			//取得データをスコープへ保存
			request.setAttribute("gakkaName", gakka);

			//フォワード先を学科変更画面に設定
			forwardPath = "/WEB-INF/jsp/gakkahenkou.jsp";

			//変更の処理(学科)
		} else if(action.equals("sisukanhenkoukanryou")){
			//リクエストパラメータから呼び出す(gakkahenkou.jsp)
			int gakkaid = Integer.parseInt(request.getParameter("id"));
			String gakkaname = request.getParameter("gakkaName");

			//gakka型に入れる
			Gakka gakka = new Gakka(gakkaid, gakkaname);

			//daoのインスタンスの作成
			gakkaDAO gaDAO = new gakkaDAO();

			//メソッドの呼び出し+結果を変数に入れる
			boolean hantei = gaDAO.gakkahenkou(gakka);

			//hanteiがtrueかfalseか
			if (hantei) {
				//フォワード先をシス管変更完了画面に設定
				forwardPath = "/WEB-INF/jsp/sisukanhenkoukanryou.jsp";
			} else {
				//フォワード先を学科変更画面に設定
				forwardPath = "/WEB-INF/jsp/gakkahenkou.jsp";
				//エラーメッセージをリクエストスコープに保存
				request.setAttribute("errorMsg", "エラーです");
			}

		//登録の処理(専攻)
		} else if (action.equals("senkoutouroku")) {
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
		//削除の処理（学科）
		} else if(action.equals("sisukansakujokanryou")) {
			//リクエストパラメータから呼び出す(gakkasakujo.jsp)
			String gakkaid = request.getParameter("gakka");
			System.out.println(gakkaid);
			int gakkaid2 = Integer.parseInt(gakkaid);

			//daoのインスタンスの作成
			gakkaDAO gaDAO = new gakkaDAO();

			//gakka型に入れる
			Gakka gakka = new Gakka(gakkaid2);

			//メソッドの呼び出し+結果を変数に入れる
			boolean hantei = gaDAO.gakkasakujo(gakka);

			//hanteiがtrueかfalseか
			if (hantei) {
				//フォワード先をシス管削除完了画面に設定
				forwardPath = "/WEB-INF/jsp/sisukansakujokanryou.jsp";
			} else {
				//フォワード先を専攻登録画面に設定
				forwardPath = "/WEB-INF/jsp/gakkasakujo.jsp";
				//エラーメッセージをリクエストスコープに保存
				request.setAttribute("errorMsg", "エラーです");
			}
		//変更の処理(学科)
		} else {
			//リクエストパラメータから呼び出す(gakkahenkou.jsp)
			int gakkaid = Integer.parseInt(action);
			String gakkaname = request.getParameter("gakkaName");

			//gakka型に入れる
			Gakka gakka = new Gakka(gakkaid, gakkaname);

			//daoのインスタンスの作成
			gakkaDAO gaDAO = new gakkaDAO();

			//メソッドの呼び出し+結果を変数に入れる
			boolean hantei = gaDAO.gakkahenkou(gakka);

			//hanteiがtrueかfalseか
			if (hantei) {
				//フォワード先をシス管変更完了画面に設定
				forwardPath = "/WEB-INF/jsp/sisukanhenkoukanryou.jsp";
			} else {
				//フォワード先を学科変更画面に設定
				forwardPath = "/WEB-INF/jsp/gakkahenkou.jsp";
				//エラーメッセージをリクエストスコープに保存
				request.setAttribute("errorMsg", "エラーです");
			}
		}

		//出力画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
}
