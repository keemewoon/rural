package com.project.rural.exp;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 농촌체험(공통) 카테고리 상세보기페이지
 * @author Jiyoon Kyeong
 *
 */
@WebServlet("/exp/view.do")
public class View extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		/**
		 * 데이터 받아주기
		 */ 
		String seq = req.getParameter("seq");
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		
		HttpSession session = req.getSession();
		Object oId = session.getAttribute("id");//.toString();
		
		/**
		 * null에 toString()하면 error이기때문에 처리해주기
		 */
		String id = oId == null ? "" : oId.toString();
		
		ExpDAO dao = new ExpDAO();
		
		/**
		 * 상세페이지 각 테이블에 필요한 정보 받아오기
		 */
		ExpDTO dto = dao.get(seq,id);
		
		/**
		 * 리뷰목록 받아오기
		 */
		ArrayList<ExpReviewDTO> rlist = dao.reviewList(seq);
		/**
		 * 리뷰 평점 받아오기
		 */
		String starAvg = dao.starAvg(seq);
		
		/**
		 * 주소 상세빼고 부터는 자르기
		 */
		if (dto.getAddress().contains(",")) {
			
			String address = dto.getAddress();
			int idx = address.indexOf(",");
			
			address = address.substring(0,idx);
		
			/**
			 * 가공한 데이터 다시 집어넣기
			 */
			dto.setMap(address);
		}

	
		req.setAttribute("rlist", rlist);
		req.setAttribute("dto", dto);
		req.setAttribute("startDate", startDate);
		req.setAttribute("endDate", endDate);
		req.setAttribute("starAvg", starAvg);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/exp/view.jsp");
		dispatcher.forward(req, resp);

	}
}
