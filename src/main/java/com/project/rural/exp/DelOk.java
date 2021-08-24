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
 * 농촌체험(농부회원) 카테고리 모집공고 삭제하기
 * @author Jiyoon Kyeong
 *
 */
@WebServlet("/exp/delok.do")
public class DelOk extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CheckMember cm = new CheckMember();
		cm.check(req, resp);
		

		String seq = req.getParameter("seq");

		
		//2.
		ExpDAO dao = new ExpDAO();
			
		
		/**
		 * 아이디 얻어오기!
		 */
		HttpSession session = req.getSession();
		String lv = session.getAttribute("lv").toString();
		
		/**
		 * 글삭제하기 전에 리뷰/ 신청내역 삭제하기
		 */
		dao.delAllReview(seq);
		dao.delAllApply(seq);
		
		
		/**
		 *  글 삭제하기
		 */
		int result = dao.del(seq);

		//3.
		if (result==1) {
			if (lv.equals("2")) resp.sendRedirect("/rural/exp/addlist.do");
			else if (lv.equals("3")) resp.sendRedirect("/rural/exp/list.do");
		} else {
			if (lv.equals("2")) resp.sendRedirect("/rural/exp/addlist.do");
			else if (lv.equals("3")) resp.sendRedirect("/rural/exp/view.do?seq=" + seq);
		}

	}
}