package com.project.rural.worker;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.rural.CheckMember;

/**
 * 
 * @author 신청취소하기 DB작업 클래스
 *
 */
@WebServlet("/worker/applydelok.do")
public class ApplyDelOk extends HttpServlet {

	@Override // Annotation - 프로그래밍 기능 주석
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CheckMember cm = new CheckMember();
		cm.check(req, resp);
		
		/**
		 * 신청을 취소할 게시물 번호
		 */
		String seq = req.getParameter("seq");
		
		WorkApplyDAO dao = new WorkApplyDAO();
		
		/**
		 * 결과 반환.
		 * 성공하면 1반환, 실패하면 0을 반환.
		 */
		int result = dao.cancel(seq);
		
		if(result == 1) {
			resp.sendRedirect("/rural/worker/applylist.do");
		} else {
			resp.sendRedirect("/rural/worker/applylist.do");
		}

	}
}