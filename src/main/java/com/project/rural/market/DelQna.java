package com.project.rural.market;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.rural.CheckMember;



@WebServlet("/market/delqna.do")
public class DelQna extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CheckMember cm = new CheckMember();
		cm.check(req, resp);

		// 할일
		// 1. 데이터 가져오기(seq, pseq)
		// 2. DB작업 > DAO위임 > delete
		// 3. 결과 처리

		/**
		 *  1. 데이터 가져오기(seq, pseq)
		 *  @param pseq 보고있던 글번호(=작성중인 댓글 부모 글번호)
		 *  @param seq 삭제할 댓글번호
		 */
		String pseq = req.getParameter("pseq");
		String seq = req.getParameter("seq");

		// 2.
		MarketDAO dao = new MarketDAO();



		int result = dao.delQna(seq); // 1,0
		System.out.println("서블릿 pseq:" + pseq);
		System.out.println("서블릿 seq:" + seq);



		// 3.
		if (result == 1) {
			/**
			 * 보고있던 글 번호를 가지고 돌아가기
			 */
			resp.sendRedirect("/rural/market/view.do?seq=" + pseq);
		} else {
			resp.setCharacterEncoding("UTF-8");

			PrintWriter writer = resp.getWriter();
			writer.print("<html>");
			writer.print("<body>");
			writer.print("<script>");
			writer.print("alert('DelQna error');");
			writer.print("history.back()");
			writer.print("</script>");
			writer.print("</body>");
			writer.print("</html>");

			writer.close();
		}


	}

}
