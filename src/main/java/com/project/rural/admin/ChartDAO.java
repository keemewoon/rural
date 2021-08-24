package com.project.rural.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.rural.DBUtil;

/**
 * 관리자 - 홈페이지 통계(차트)와 관련된 내용을 DB접속 후 제공하는 클래스
 * 
 * @author 김영혁
 */
public class ChartDAO {

	private Connection conn;
	private Statement stat;
	private ResultSet rs;
	
	/**
	 * DB에 접속하기 위한 DBUtil Class 호출, 접속
	 * 
	 */
	public ChartDAO() {
		
		try {	
			conn = DBUtil.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 사용자의 이름, 작성글 수 데이터를 불러오는 메소드
	 * 
	 * @return 해당 sql 쿼리문의 결과값을 반환
	 */
	public ArrayList<ChartDTO> list1() {
		
		try {
			
			String sql = String.format("select * from vwList");
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<ChartDTO> list = new ArrayList<ChartDTO>();
			
			while ( rs.next() ) {
				
				ChartDTO dto = new ChartDTO();
				
				dto.setName(rs.getString("name"));
				dto.setCnt(rs.getString("total"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 카테고리별 이름, 게시글 수 데이터를 불러오는 메소드
	 * 
	 * @return 해당 sql 쿼리문의 결과값을 반환
	 */
	public ChartDTO list2() {
		
		try {
			
			String sql = String.format("select * from vwCategoryTotal");
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ChartDTO dto = new ChartDTO();
			
			if ( rs.next() ) {
				
				
				dto.setCommunity(rs.getString("community"));
				dto.setNotice(rs.getString("notice"));
				dto.setWorker(rs.getString("worker"));
				dto.setExp(rs.getString("exp"));
				dto.setMarket(rs.getString("market"));
				dto.setFarm(rs.getString("farm"));
				
			}
			
			return dto;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 사용자의 상태(정상/정지/탈퇴) 데이터를 불러오는 메소드
	 * 
	 * @return 해당 sql 쿼리문의 결과값을 반환
	 */
	public ChartDTO list3() {
		
		try {
			
			String sql = String.format("select * from vwMemberStatusTotal");
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ChartDTO dto = new ChartDTO();
			
			if ( rs.next() ) {
				
				
				dto.setNormal(rs.getString("normal"));
				dto.setOut(rs.getString("out"));
				dto.setStop(rs.getString("stop"));
				
			}
			
			return dto;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 사용자의 유형(일반/농부) 데이터를 불러오는 메소드
	 * 
	 * @return 해당 sql 쿼리문의 결과값을 반환
	 */
	public ChartDTO list4() {
		
		try {
			
			String sql = String.format("select * from vwMemberTypeTotal");
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ChartDTO dto = new ChartDTO();
			
			if ( rs.next() ) {
				
				dto.setOrdinary(rs.getString("ordinary"));
				dto.setFarmer(rs.getString("farmer"));
				
			}
			
			return dto;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}


}
