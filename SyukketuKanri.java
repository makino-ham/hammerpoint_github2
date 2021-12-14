package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.KyoukaDAO;
import dao.SeitoDAO;
import dao.SyukketuDAO;
import model.Kyouka;
import model.Password;
import model.Seito;
import model.Syukketu;

/**
 * Servlet implementation class SyukketuKanri
 */
@WebServlet("/SyukketuKanri")
public class SyukketuKanri extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String action=request.getParameter("action");
			String forwardPath = null;
			if (action.equals("touroku")) {
					forwardPath = "/WEB-INF/jsp/syukketutourokukensaku.jsp";
			} else if (action.equals("henkou")) {
					HttpSession session=request.getSession();
					KyoukaDAO kyoukaDAO = new KyoukaDAO();
					Password account = new Password();
					account = (Password) session.getAttribute("account");
					List<Kyouka> listOfAllMyKyouka = kyoukaDAO.kyouinKyoukaListOut(account.getKyouinid());
					session.setAttribute("listOfAllMyKyouka", listOfAllMyKyouka);
					forwardPath = "/WEB-INF/jsp/syukketuhenkoukensaku.jsp";
			} else if (action.equals("joukyou")) {
					HttpSession session=request.getSession();
					KyoukaDAO kyoukaDAO = new KyoukaDAO();
					Password account = new Password();
					account = (Password) session.getAttribute("account");
					List<Kyouka> listOfAllMyKyouka = kyoukaDAO.kyouinKyoukaListOut(account.getKyouinid());
					session.setAttribute("listOfAllMyKyouka", listOfAllMyKyouka);
					forwardPath = "/WEB-INF/jsp/syukketujoukyou.jsp";
			}
			RequestDispatcher dispatcher=request.getRequestDispatcher(forwardPath);
			dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");
			String forwardPath = null;
			if (action.equals("tourokuKensaku")) {
					String zenbu = request.getParameter("zenbu");
					if (zenbu == null) {
							request.setAttribute("radioErrorMsg", "教科が選択されていません");
							forwardPath = "/WEB-INF/jsp/syukketutourokukensaku.jsp";
					} else {
							HttpSession session=request.getSession();
							String[] z = zenbu.split(",");
							int kyoukaId=Integer.parseInt(z[0]);
							int jigen=Integer.parseInt(z[1]);
							int gakunen = Integer.parseInt(z[2]);
							int classId = Integer.parseInt(z[3]);
							String kyoukaName = z[4];
							String senkouName = z[5];
							List<Seito> seitoList = new ArrayList<Seito>();
							SeitoDAO seitoDAO = new SeitoDAO();
							seitoList = seitoDAO.seitoListOut(classId);
							session.setAttribute("kyoukaId", kyoukaId);
							session.setAttribute("jigen", jigen);
							session.setAttribute("classId", classId);
							session.setAttribute("gakunen", gakunen);
							session.setAttribute("kyoukaName", kyoukaName);
							session.setAttribute("senkouName", senkouName);
							session.setAttribute("seitoList", seitoList);
							forwardPath = "/WEB-INF/jsp/syukketutouroku.jsp";
					}
			} else if (action.equals("touroku")) {
					HttpSession session=request.getSession();
					SeitoDAO seitoDAO = new SeitoDAO();
					List<Seito> seitoList = seitoDAO.seitoListOut((int)session.getAttribute("classId"));
					List<String> gakusekiList = new ArrayList<String>();//学籍番号と出欠フラグを入れるリスト
					//学籍番号と出欠フラグを取得する
					for (Seito seito: seitoList) {
							String gakuseki = request.getParameter(seito.getGakusekiId());
							gakusekiList.add(gakuseki);
					}
					int jigen = (int) session.getAttribute("jigen");
					int kyoukaId = (int) session.getAttribute("kyoukaId");
					//日付の取得
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date today2 = cal.getTime();
					String today = sdf.format(today2);
					//出欠DAOの呼び出し
					SyukketuDAO syukketuDAO = new SyukketuDAO();
					//登録の処理
					for (String gakuseki: gakusekiList) {
							String[] g = gakuseki.split(",");
							String gakusekiId = g[0];
							int flag = Integer.parseInt(g[1]);
							Syukketu syukketu = new Syukketu(gakusekiId, kyoukaId, jigen, today, flag);
							syukketuDAO.create(syukketu);
					}
					session.removeAttribute("kyoukaId");
					session.removeAttribute("jigen");
					session.removeAttribute("classId");
					session.removeAttribute("gakunen");
					session.removeAttribute("kyoukaName");
					session.removeAttribute("senkouName");
					session.removeAttribute("seitoList");
					forwardPath = "/WEB-INF/jsp/kmain.jsp";
			} else if (action.equals("henkoukensaku")) {
					HttpSession session=request.getSession();
					String kyoukaIdS = request.getParameter("kyoukaId");
					int jigen = Integer.parseInt(request.getParameter("jigen"));
					String gakusekiId = request.getParameter("gakusekiId");
					String month = null;
					String day = null;
					String date = null;
					if (kyoukaIdS == null || gakusekiId == "") {
							if (kyoukaIdS == null) {
									request.setAttribute("kyoukaErrorMsg", "教科が選択されていません");
							}
							if (gakusekiId == "") {
									request.setAttribute("gakusekiErrorMsg", "学籍番号が入力されていません");
							}
							request.setAttribute("jigen", jigen);
							request.setAttribute("kyoukaIdS", kyoukaIdS);
							request.setAttribute("gakusekiId", gakusekiId);
							forwardPath = "/WEB-INF/jsp/syukketuhenkoukensaku.jsp";
					} else {
							int kyoukaId = Integer.parseInt(request.getParameter("kyoukaId"));
							//monthが1桁の場合0を付ける処理
							if (Integer.parseInt(request.getParameter("month")) <= 9) {
									month = "0" + request.getParameter("month");
							} else {
									month = request.getParameter("month");
							}
							//dayが1桁の場合0を付ける処理
							if (Integer.parseInt(request.getParameter("day")) <= 9) {
									day = "0" + request.getParameter("day");
							} else {
									day = request.getParameter("day");
							}
							date = request.getParameter("year") + "-" + month + "-" + day;
							System.out.println(date);
							Syukketu syukketu = new Syukketu(gakusekiId, kyoukaId, jigen, date);
							SyukketuDAO syukketuDAO = new SyukketuDAO();
							syukketu = syukketuDAO.syukketuHenkouKensaku(syukketu);
							if (syukketu == null) {
									request.setAttribute("nullErrorMsg", "該当する出欠記録が存在しません");
									request.setAttribute("kyoukaIdS", kyoukaIdS);
									request.setAttribute("gakusekiId", gakusekiId);
									forwardPath = "/WEB-INF/jsp/syukketuhenkoukensaku.jsp";
							} else {
									session.setAttribute("syukketu", syukketu);
									forwardPath = "/WEB-INF/jsp/syukketuhenkou.jsp";
							}
					}
			} else if (action.equals("henkou")) {
					HttpSession session=request.getSession();
					int sFlag = Integer.parseInt(request.getParameter("syukketu"));
					String riyuu = request.getParameter("riyuu");
					if (riyuu == "") {
							request.setAttribute("riyuuErrorMsg", "理由が入力されていません");
							forwardPath = "/WEB-INF/jsp/syukketuhenkou.jsp";
					} else {
							Syukketu syukketu = (Syukketu) session.getAttribute("syukketu");
							String oldSyukketu = null;
//							変更前の出欠
							if (syukketu.getSyukketu() == 0) {
									oldSyukketu = "出席";
							} else if (syukketu.getSyukketu() == 1) {
									oldSyukketu = "欠席";
							} else {
									oldSyukketu = "公欠";
							}
							String sinSyukketu = null;
//							変更後の出欠
							if (sFlag == 0) {
									sinSyukketu = "出席";
							} else if(sFlag == 1) {
									sinSyukketu = "欠席"	;
							} else {
									sinSyukketu = "公欠";
							}
							riyuu = oldSyukketu + "→" + sinSyukketu + "：" + riyuu;
							Syukketu newSyukketu = new Syukketu(syukketu.getGakusekiId(), syukketu.getKyoukaId(), syukketu.getJigen(), syukketu.getHiduke(), sFlag, riyuu);
							SyukketuDAO syukketuDAO = new SyukketuDAO();
							syukketuDAO.syukketuHenkou(newSyukketu);
							session.removeAttribute("syukketu");
							forwardPath = "/WEB-INF/jsp/kmain.jsp";
					}
			} else if (action.equals("joukyoukensaku")) {
					int kyoukaId = Integer.parseInt(request.getParameter("kyoukaSelect"));
					String gakusekiId = request.getParameter("gakusekiId");
					//DAOを宣言
					SyukketuDAO syukketuDAO = new SyukketuDAO();
					SeitoDAO seitoDAO = new SeitoDAO();
					//日付を取得
					List<String> hidukeList = null;
					hidukeList = syukketuDAO.hidukeListOut(kyoukaId, gakusekiId);
					List<Syukketu> syukketuList = null;
					syukketuList = syukketuDAO.countListout(kyoukaId, gakusekiId, hidukeList);
					//時限を取得
					List<Syukketu> jigenList = null;
					jigenList = syukketuDAO.jigenListOut(gakusekiId, kyoukaId);
					//生徒を取得
					Seito seito = new Seito();
					seito = seitoDAO.seitoKensaku(gakusekiId);
					//出欠を取得
					List<Syukketu> sList = null;
					sList = syukketuDAO.syukketuListOut(gakusekiId, kyoukaId);
					int check = 1;
					request.setAttribute("kyoukaId", kyoukaId);
					request.setAttribute("gakusekiId", gakusekiId);
					request.setAttribute("seito", seito);
					request.setAttribute("check", check);
					request.setAttribute("sList", sList);
					request.setAttribute("jigenList", jigenList);
					request.setAttribute("syukketuList", syukketuList);
					forwardPath = "/WEB-INF/jsp/syukketujoukyou.jsp";
			}
			RequestDispatcher dispatcher=request.getRequestDispatcher(forwardPath);
			dispatcher.forward(request, response);
	}

}
