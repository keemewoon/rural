package com.project.rural.exp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.rural.CheckMember;

/**
 * 농촌체험에서 사용자(일반)가 지원신청한내용을 취소(삭제)하는 클래스
 * @author Jiyoon Kyeong
 *
 */
@WebServlet("/exp/applydelok.do")
public class ApplyDelOk extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		CheckMember cm = new CheckMember();
		cm.check(req, resp);

		String seq = req.getParameter("seq");

		ExpDAO dao = new ExpDAO();
		ExpApplyDTO dto = new ExpApplyDTO();
		
	
		
		
		/**
		 * 신청내용 삭제하기
		 */
		int result = dao.expApplyDel(seq);

		//3.
		if (result==1) {
			resp.sendRedirect("/rural/exp/applylist.do");
		} else {
			resp.sendRedirect("/rural/exp/applylist.do");
		}

	}
}