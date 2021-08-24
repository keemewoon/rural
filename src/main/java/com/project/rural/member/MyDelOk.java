package com.project.rural.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/mydelok.do")
public class MyDelOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/**
		 * @param id 아이디
		 * @param pw 패스워드
		 */
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");

		MemberDAO dao = new MemberDAO();

		int result = dao.pwCheck(id, pw);


		/**
		 * result가 1이면 탈퇴를 진행하고 그렇지 않으면 아이디와 비밀번호를 다시 입력받는다.
		 */
		if ( result == 1 ) {
			resp.setCharacterEncoding("UTF-8");

			dao.del(id);
			PrintWriter writer = resp.getWriter();

			writer.print("<html>");
			writer.print("<head>");
			writer.print("<meta charset='UTF-8'>");
			writer.print("</head>");
			writer.print("<body>");
			writer.print("<script>");
			writer.print("alert('회원님의 탈퇴가 완료되었습니다.');");
			writer.print("location.href='/rural/member/logout.do';");
			writer.print("</script>");
			writer.print("</body>");
			writer.print("</html>");

			writer.close();

		} else {
			resp.setCharacterEncoding("UTF-8");

			PrintWriter writer = resp.getWriter();

			writer.print("<html>");
			writer.print("<head>");
			writer.print("<meta charset='UTF-8'>");
			writer.print("</head>");
			writer.print("<body>");
			writer.print("<script>");
			writer.print("alert('아이디 및 패스워드를 잘못 입력하였습니다. 다시 입력 해주시기 바랍니다.');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.print("</body>");
			writer.print("</html>");

			writer.close();
		}


	}

}
