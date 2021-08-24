package com.project.rural.farm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.rural.CheckMember;

/**
 * 주말농장 수정 페이지로 매핑시켜주는 클래스
 * @author 유기호
 *
 */
@WebServlet("/farm/edit.do")
public class Edit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CheckMember cm = new CheckMember();
		cm.check(req, resp);
		
		String seq = req.getParameter("seq");
		
		FarmDAO dao = new FarmDAO();
		
		FarmDTO dto = dao.get(seq);
		
		String detail = dto.getDetail();
		detail = detail.replace("<br>", "\r\n");
		dto.setDetail(detail);
		
		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/farm/edit.jsp");
		dispatcher.forward(req, resp);
	}

}
