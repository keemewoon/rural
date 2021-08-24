package com.project.rural;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 사용자가 로그인한 사용자인지 검사하는 클래스
 * 
 * @author 김영혁
 */
public class CheckMember {

	public void check(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			
			HttpSession session = req.getSession();

			if ( session.getAttribute("id") == null || session.getAttribute("id").toString().equals("") ) {
				
				resp.setCharacterEncoding("UTF-8");
				
				PrintWriter writer = resp.getWriter();
				
				writer.print("<html>");
				writer.print("<head>");
				writer.print("<meta charset='utf-8'>");
				writer.print("</head>");
				writer.print("<body>");
				writer.print("<script>");
				writer.print("alert('로그인 후 사용이 가능합니다. ');");
				writer.print("location.href='/rural/member/login.do';");
				writer.print("</script>");
				writer.print("</html>");
				
				writer.close();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
