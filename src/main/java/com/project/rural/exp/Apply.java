package com.project.rural.exp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.rural.CheckMember;

/**
 * 농촌체험(일반)에서 원하는 모집공고에 신청하기 위한 클래스
 * @author Jiyoon Kyeong
 *
 */
@WebServlet("/exp/apply.do")
public class Apply extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CheckMember cm = new CheckMember();
		cm.check(req, resp);
		
		String seq = req.getParameter("seq");
		/**
		 * 신청시 농부가 정해진 기간내에서만 선택하도록 매개변수로 받아와야한다.
		 */
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
	
		req.setAttribute("seq", seq);
		req.setAttribute("startDate", startDate);
		req.setAttribute("endDate", endDate);
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/exp/apply.jsp");
		dispatcher.forward(req, resp);

	}
}