package com.project.rural.exp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.rural.CheckMember;

/**
 * 농촌체험에서 사용자(농부)가 일반회원들의 신청내역을 승인처리하는 클래스
 * @author Jiyoon Kyeong
 *
 */
@WebServlet("/exp/applypassok.do")
public class ApplyPassOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		CheckMember cm = new CheckMember();
		cm.check(req, resp);
		
		/**
		 * 배열로 신청번호를 받을수 있다.
		 */
		String[] seqs = req.getParameterValues("seqs");
		String pseq = req.getParameter("pseq");
		
		ExpDAO dao = new ExpDAO();
		
		/**
		 * 승인처리된 항목들의 갯수를 반환받는다.
		 */
		int result = dao.passApply(seqs);
		
		/**
		 * 승인처리된 항복들을 일괄적으로 처리하게 했음으로
		 * 0보다 크다는 조건으로 해야한다.
		 */
		if(result > 0) {
			resp.sendRedirect("/rural/exp/applicant.do?seq=" + pseq);
		} else {
			resp.setCharacterEncoding("UTF-8");
			
			PrintWriter writer = resp.getWriter();
			
			writer.print("<html>");
			writer.print("<body>");
			writer.print("<script>");
			writer.print("alert('승인항목을 선택해주세요.');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.print("</body>");
			writer.print("</html>");
			
			writer.close();
			
		}
		 


	}
}