package com.project.rural.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 아이디/비밀번호 찾기 페이지로 매핑시켜주는 클래스
 * @author 유기호
 *
 */
@WebServlet("/member/loginfind.do")
public class LoginFind extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/loginfind.jsp");
		dispatcher.forward(req, resp);

	}

}
