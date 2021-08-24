package com.project.rural.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.rural.CheckMember;

/**
 * 관리자 - 홈페이지 관리 페이지(차트 4개)<br>
 * 1. 회원별 게시글 수 TOP 10<br>
 * 2. 카테고리별 게시글 수 TOP 10<br>
 * 3. 회원 상태별 비율<br>
 * 4. 회원 유형별 비유<br>
 * 
 * @author 김영혁
 */
@WebServlet("/admin/manage.do")
public class Manage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CheckMember cm = new CheckMember();
		cm.check(req, resp);
		
		ChartDAO dao = new ChartDAO();
		
		ArrayList<ChartDTO> list1 = dao.list1(); // 게시물 목록
		ChartDTO list2 = dao.list2(); // 카테고리별 게시물
		ChartDTO list3 = dao.list3(); // 회원상태
		ChartDTO list4 = dao.list4(); // 회원유형
		
		req.setAttribute("list1", list1);
		req.setAttribute("list2", list2);
		req.setAttribute("list3", list3);
		req.setAttribute("list4", list4);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/manage.jsp");
		dispatcher.forward(req, resp);

	}
}