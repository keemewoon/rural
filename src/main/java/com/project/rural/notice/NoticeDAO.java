package com.project.rural.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.project.rural.DBUtil;
/**
 * 알림마당과 관련된 내용을 DB접속 후 제공하는 클래스
 * 
 * @author 김영혁
 */
public class NoticeDAO {

	private Connection conn;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	/**
	 * DB 접속을 위한 생성자 
	 * 
	 */
	public NoticeDAO() {
		
		try {
			
			conn = DBUtil.open();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * AddOk 서블릿에서 게시글 작성을 위한 DB에 INSERT 작업을 하는 메소드
	 * 
	 * @param 알림마당 DTO
	 * @return 결과값(0 or 1)
	 */
	public int add(NoticeDTO dto) {
		
		try {
			
			String sql = "insert into tblNotice (seq, id, category, title, detail, regDate, cnt) "
					+ "values (seqNotice.NextVal, ?, ?, ?, ?, default, default)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getCategory());
			pstat.setString(3, dto.getTitle());
			pstat.setString(4, dto.getDetail());
			
			return pstat.executeUpdate(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * AddOk 서블릿에서 게시글 안에 이미지를 추가하기 위한 DB에 INSERT 작업을 하는 메소드
	 * 
	 * @param 업로드 이미지 목록
	 * @return 결과값(0 or 1)
	 */
	public void addImg(ArrayList<String> images) {
		
		try {
			
			String seq = "";
			String test = "select max(seq) as seq from tblNotice";
			pstat = conn.prepareStatement(test);
			
			rs = pstat.executeQuery();
			
			if ( rs.next() ) {
				seq = rs.getString("seq");
			}
			
			String sql = "insert into tblNoticeImg (seq, image, pseq) "
					+ "values (seqNoticeImg.NextVal, ?, ?)";
			
			for (String image : images) {
				
				pstat = conn.prepareStatement(sql);
				
				pstat.setString(1, image);
				pstat.setString(2, seq);

				pstat.executeUpdate();
				
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * List 서블릿에서 각 조건별로 보기위한 DB에 SELECT 작업을 하는 메소드<br>
	 * 조건1. 카테고리별 체크 유무<br>
	 * 조건2. 정렬 체크 유무<br>
	 * 조건3. 검색 유무<br>
	 * 
	 * @param HashMap에 저장된 조건 내용
	 * @return 번호, 이름, 제목, 내용, 이미지, 카테고리, 최신글유무, 조회수, 작성일자
	 */
	public ArrayList<NoticeDTO> list(HashMap<String, String> map) {
		
		try {
			
			String where = "";
			String order = "";
			
			if (map.get("isSearch").equals("y")) {
				where += " where";
				order += " order";
			}
			
			// 카테고리별 list
			if (map.get("category") != null) {
				
				if (map.get("category").equals("전체보기")) {
					
				} else if (map.get("category").equals("지역행사")) {
					where += String.format(" category='%s' and", map.get("category"));
				} else if (map.get("category").equals("이벤트")) {
					where += String.format(" category='%s' and", map.get("category"));
				} else if (map.get("category").equals("공지사항")) {
					where += String.format(" category='%s' and", map.get("category"));
				} 
			}
			
			// 정렬 list
			if (map.get("sort") != null && !map.get("sort").equals("")) {
				if (map.get("sort").equals("최신순")) {
					order += " by regdate desc";
				} else if (map.get("sort").equals("조회순")) {
					order += " by cnt desc";
				}
			}
			
			// 검색 list
			if (map.get("column") != null && !map.get("column").equals("") ) {
				if (map.get("column").equals("all")) {
					where += String.format(" title like '%%%s%%' or detail like '%%%s%%'"
							, map.get("search"), map.get("search"));
				} else {
					where += String.format(" %s like '%%%s%%'"
							, map.get("column"), map.get("search"));
				}
			}
			
			// 뒷 문장 and 제거
			if (where.endsWith("and")) {
				where = where.substring(0, where.length() - 3);
			}
			
			// where 필요없을때
			if (where.equals(" where")) {
				where = "";
			}

			// order 필요없을때
			if (!order.endsWith("desc")) {
				order = "";
			}
			
			
	        String sql = String.format("select * from (select c.*, rownum as rnum from vwNotice c %s %s) where rnum between %s and %s"
	        							, where
	        							, order
	        			                , map.get("begin")
	        			                , map.get("end"));

	        pstat = conn.prepareStatement(sql);
			
			rs = pstat.executeQuery();
			
			ArrayList<NoticeDTO> list = new ArrayList<NoticeDTO>();
					
			while ( rs.next() ) {
				
				NoticeDTO dto = new NoticeDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setCategory(rs.getString("category"));
				dto.setTitle(rs.getString("title"));
				dto.setDetail(rs.getString("detail"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setCnt(rs.getString("cnt"));
				dto.setName(rs.getString("name"));
				dto.setIsnew(rs.getString("isnew"));
				dto.setImgCnt(rs.getString("imgCnt"));
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * List 서블릿에서 페이지 바 구현을 위한 조건별 총 페이지수를 DB에 SELECT 후 확인하는 메소드
	 * 
	 * @param HashMap에 저장된 조건 내용
	 * @return 해당 페이지 총 수
	 */
	public int getTotalCount(HashMap<String, String> map) {
		
		try {
			
			String where ="";
			
			if (map.get("isSearch").equals("y")) {
				where += " where";
			}
			
			// 카테고리별 list
			if (map.get("category") != null) {
				
				if (map.get("category").equals("전체보기")) {
					
				} else if (map.get("category").equals("지역행사")) {
					where += String.format(" category='%s' and", map.get("category"));
				} else if (map.get("category").equals("이벤트")) {
					where += String.format(" category='%s' and", map.get("category"));
				} else if (map.get("category").equals("공지사항")) {
					where += String.format(" category='%s' and", map.get("category"));
				} 
			}
			
			// 검색 list
			if (map.get("column") != null && !map.get("column").equals("") ) {
				if (map.get("column").equals("all")) {
					where += String.format(" title like '%%%s%%' or detail like '%%%s%%'"
							, map.get("search"), map.get("search"));
				} else {
					where += String.format(" %s like '%%%s%%'"
							, map.get("column"), map.get("search"));
				}
			}
			
			// 뒷 문장 and 제거
			if (where.endsWith("and")) {
				where = where.substring(0, where.length() - 3);
			}
			
			// where 필요없을때
			if (where.equals(" where")) {
				where = "";
			}

			String sql = String.format("select count(*) as total from tblNotice %s", where);
			
			pstat = conn.prepareStatement(sql);
			
			rs = pstat.executeQuery();
			
			if ( rs.next() ) {
				return rs.getInt("total");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * View 서블릿에서 조회수 카운트를 위한 DB에 UPDATE하는 메소드
	 * 
	 * @param 글 번호
	 */
	public void updateReadCount(String seq) {
		
		try {
			
			String sql = "update tblNotice set cnt = cnt + 1 where seq=?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * View 서블릿에서 해당 글 정보를 불러오기 위한 DB에 SELECT하는 메소드 
	 * 
	 * @param 글 번호
	 * @return 번호, 아이디, 이름, 제목, 내용, 카테고리, 조회수, 댓글수, 작성일자
	 */
	public NoticeDTO get(String seq) {
		
		try {
			
			String sql = "select c.*, (select name from tblUser where id = c.id ) as name "
					+ "from tblNotice c where seq=?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			
			if ( rs.next() ) {
				
				NoticeDTO dto = new NoticeDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setDetail(rs.getString("detail"));
				dto.setCategory(rs.getString("category"));
				dto.setCnt(rs.getString("cnt"));
				dto.setRegdate(rs.getString("regdate"));
				
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
		
	/**
	 * View 서블릿에서 해당 글의 이미지 정보를 불러오기 위해 DB에 SELECT하는 메소드
	 * 
	 * @param 글 번호
	 * @return 이미지 목록
	 */
	public ArrayList<String> listImg(String seq) {
		
		
		try {
			
			String sql = "select image from tblNoticeImg where pseq=?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<String> listImg = new ArrayList<String>();
			
			while ( rs.next() ) {
				listImg.add(rs.getString("image"));
			}
			
			return listImg;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}


	/**
	 * EditOk 서블릿에서 해당 글 수정에 필요한 내용들을 DB에 UPDATE하는 메소드
	 * 
	 * @param 알림마당DTO
	 * @return 결과값(0 or 1)
	 */
	public int edit(NoticeDTO dto) {
		
		try {
			
			String sql = "update tblNotice set category=?, title=?, detail=? where seq=?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getCategory());
			pstat.setString(2, dto.getTitle());
			pstat.setString(3, dto.getDetail());
			pstat.setString(4, dto.getSeq());
			
			return pstat.executeUpdate(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * EditOk 서블릿에서 이미지 수정을 위한 새로운 이미지 추가를 DB에 INSERT하는 메소드
	 * 
	 * @param 이미지명
	 * @param 글 번호
	 */
	public int editImg(ArrayList<String> images, String seq) {
		
		try {
			
			String sql = "insert into tblNoticeImg (seq, image, pseq) "
					+ "values (seqNoticeImg.NextVal, ?, ?)";
			
			for (String image : images) {
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, image);
				pstat.setString(2, seq);

				pstat.executeUpdate();
			}
			
			return pstat.executeUpdate(); 

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * DelOk 서블릿에서 해당 글 삭제를 위한 DB에 DELETE하는 메소드
	 * 
	 * @param 글 번호
	 * @return 결과값(0 or 1)
	 */
	public int del(String seq) {
		
		try {
			
			String sql = "delete from tblNotice where seq=?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * DeleOk 서블릿에서 게시글 삭제시 존재하는 이미지도 삭제 하기 위한 DB에 DELETE하는 메소드
	 * 
	 * @param 글 번호
	 * @return 결과값(0 or 1)
	 */
	public int delAllImg(String seq) {
		
		try {
			
			// 게시글에 속한 이미지 삭제
			String sql = "delete from tblNoticeImg where pseq=?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}

	
}
