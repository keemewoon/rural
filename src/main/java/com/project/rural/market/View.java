package com.project.rural.market;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/market/view.do")
public class View extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		/**
		 * 1.데이터 가져오기(seq)
		 */
		HttpSession session = req.getSession();
		String seq = req.getParameter("seq");

		//2.
		MarketDAO dao = new MarketDAO();
		MarketDTO dto = dao.get(seq);


		/**
		 * 2.5 현재보고 있는 글에 달린 QnA목록 가져오기
		 * @param qlist 현재 글번호(= 댓글의 부모글 번호)
		 */
		ArrayList<MarketQADTO> qlist = dao.listQna(seq);

		/**
		 * @param ilist 이미지 불러오기
		 */
		ArrayList<String> ilist = dao.listImg(seq);

		/**
		 * @param cnt 좋아요 몇개인지 불러오기
		 */
		int cnt = dao.likecnt(seq);
		String detail = dto.getDetail();

		detail = detail.replace("\r\n", "<br>");

		dto.setDetail(detail);


		/**
		 * 3.MarketDTO 반환 > JSP 호출하기 + 전달하기
		 */
		req.setAttribute("dto", dto);
		req.setAttribute("qlist", qlist);
		req.setAttribute("ilist", ilist);
		req.setAttribute("cnt", cnt);

		System.out.println("cnt: " + cnt);



		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/market/view.jsp");
		dispatcher.forward(req, resp);

	}

}