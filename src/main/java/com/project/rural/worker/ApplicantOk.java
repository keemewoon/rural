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
 * @author 신청현황보기 DB작업 클래스
 *
 */
@WebServlet("/worker/applicantok.do")
public class ApplicantOk extends HttpServlet {

	@Override // Annotation - 프로그래밍 기능 주석
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CheckMember cm = new CheckMember();
		cm.check(req, resp);
		
		/**
		 * 글번호 받아오기 
		 */
		String seq = req.getParameter("seq");
		
		WorkApplyDAO dao = new WorkApplyDAO();
		WorkApplyDTO dto = new WorkApplyDTO();
		
		dto.setSeq(seq);
		
		/**
		 * 결과 - 후처리 
		 * 성공이면 1을 반환, 실패면 0을 반환.
		 */
		int result = dao.applycantok(dto);
		
		if(result == 1) {
			resp.sendRedirect("/rural/worker/applicant.do?seq=" + seq);
		} else {
			resp.sendRedirect("/rural/worker/applicant.do?seq=" + seq);
		}
	}
}