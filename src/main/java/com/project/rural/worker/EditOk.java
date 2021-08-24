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
 * @author 수정하기 DB작업 클래스 
 *
 */
@WebServlet("/worker/editok.do")
public class EditOk extends HttpServlet {

	@Override // Annotation - 프로그래밍 기능 주석
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		CheckMember cm = new CheckMember();
		cm.check(req, resp);
		
		String seq = req.getParameter("seq"); // 글번호
		String title = req.getParameter("title"); //제목
		String isEnd = req.getParameter("isEnd"); // 경력유무
		String tel = req.getParameter("tel"); // 전화번호
		String startDate = req.getParameter("startDate"); //시작날짜
		String endDate = req.getParameter("endDate"); // 종료날짜
		String sido = req.getParameter("sido"); //시(지역)
		String gugun = req.getParameter("gugun"); //도(지역)
		String isCareer = "";
		
		/**
		 * 경력사항이 있으면 n, 없으면 y를 반환
		 */
		if(req.getParameter("isCareer") == null) {
			isCareer = "n";
		} else {
			isCareer = "y";
		}
		
		String totalPerson = req.getParameter("totalPerson");
		String minAge = req.getParameter("minAge");
		String maxAge = req.getParameter("maxAge");
		String cropInfo = req.getParameter("cropInfo");
		String pay = req.getParameter("pay");
		String address = req.getParameter("address");
		String detail = req.getParameter("detail");
		
		WorkerDAO dao = new WorkerDAO();
		WorkerDTO dto = new WorkerDTO();
		
		dto.setSeq(seq);
		dto.setTitle(title);
		dto.setIsEnd(isEnd);
		dto.setTel(tel);
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setCity(sido);
		dto.setGu(gugun);
		dto.setIsCareer(isCareer);
		dto.setTotalPerson(totalPerson);
		dto.setMinAge(minAge);
		dto.setMaxAge(maxAge);
		dto.setCropInfo(cropInfo);
		dto.setPay(pay);
		dto.setAddress(address);
		dto.setDetail(detail);
		
		int result = dao.editok(dto);
		
		if (result == 1) {
			resp.sendRedirect("/rural/worker/addlist.do");
		} else {
			resp.sendRedirect("/rural/worker/edit.do?seq=" + seq);
		}	

	}
}