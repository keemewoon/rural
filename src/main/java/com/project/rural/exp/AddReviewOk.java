package com.project.rural.exp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.rural.CheckMember;

/**
 * 농촌체험에서 사용자(일반)가 리뷰를 작성하기 위한 클래스 
 * @author Jiyoon Kyeong
 *
 */
@WebServlet("/exp/addreviewok.do")
public class AddReviewOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		CheckMember cm = new CheckMember();
		cm.check(req, resp);
		
		String detail = req.getParameter("detail");
		String pseq = req.getParameter("pseq"); //글번호
		String star = req.getParameter("star");
		
		ExpDAO dao = new ExpDAO();
		ExpReviewDTO dto = new ExpReviewDTO();

		HttpSession session = req.getSession();
		dto.setId(session.getAttribute("id").toString());		

		dto.setPseq(pseq);
		dto.setDetail(detail);
		dto.setStar(star);
		
		int result = dao.addReview(dto);
		
		System.out.println(result);
		if (result == 1) {
			resp.sendRedirect("/rural/exp/view.do?seq=" + pseq + "#review-section");
		} else {
			
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html; charset=UTF-8");
			
			PrintWriter writer = resp.getWriter();
			
			writer.print("<html>");
			writer.print("<body>");
			writer.print("<script>");
			writer.print("alert('체험승인된 회원만 작성가능합니다!');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.print("</body>");
			writer.print("</html>");
			
			writer.close();
		}

	}
}