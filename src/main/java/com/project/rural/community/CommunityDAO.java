package com.project.rural.community;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.project.rural.DBUtil;

/**
 * 커뮤나티와 관련된 내용을 DB접속 후 제공하는 클래스
 * 
 * @author 김영혁
 */
public class CommunityDAO {	
	
	private Connection conn;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	/**
	 * DB 접속을 위한 생성자 
	 * 
	 */
	public CommunityDAO() {
		
		try {
			
			conn = DBUtil.open();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * AddOk 서블릿에서 게시글 작성을 위한 DB에 INSERT 작업을 하는 메소드
	 * 
	 * @param 커뮤니티 DTO
	 * @return 결과값(0 or 1)
	 */
	public int add(CommunityDTO dto) {
		
		try {
			
			String sql = "insert into tblCommunity (seq, id, category, title, detail, regDate, cnt) "
					+ "values (seqCom.NextVal, ?, ?, ?, ?, default, default)";
			
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
			String sqlCom = "select max(seq) as seq from tblCommunity";
			pstat = conn.prepareStatement(sqlCom);
			
			rs = pstat.executeQuery();
			
			if ( rs.next() ) {
				seq = rs.getString("seq");
			}
			
			String sql = "insert into tblCommunityImg (seq, image, pseq) "
					+ "values (seqComImg.NextVal, ?, ?)";
			
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
	 * 조건1. 내가 쓴 글 체크 유무<br>
	 * 조건2. 카테고리별 체크 유무<br>
	 * 조건3. 정렬 체크 유무<br>
	 * 조건4. 검색 유무<br>
	 * 
	 * @param HashMap에 저장된 조건 내용
	 * @return 번호, 아이디, 이름, 제목, 내용, 이미지, 카테고리, 최신글유무, 조회수, 댓글수, 작성일자
	 */
	public ArrayList<CommunityDTO> list(HashMap<String, String> map) {
		
		try {
			
			String where = "";
			String order = "";
			
			if (map.get("isSearch").equals("y")) {
				where += " where";
				order += " order";
			}
			
			// 내가 쓴 글 list
			if (map.get("id") != null && !map.get("id").equals("")) {
				where += String.format(" id='%s'", map.get("id"));
			}
			
			// 카테고리별 list
			if (map.get("category") != null && !map.get("category").equals("")) {
				
				if (map.get("category").equals("전체보기")) {
				} else if (map.get("category").equals("일손돕기")) {
					where += String.format(" category='%s' and", map.get("category"));
				} else if (map.get("category").equals("농촌체험")) {
					where += String.format(" category='%s' and", map.get("category"));
				} else if (map.get("category").equals("주말농장")) {
					where += String.format(" category='%s' and", map.get("category"));
				} else if (map.get("category").equals("농작물 직거래")) {
					where += String.format(" category='%s' and", map.get("category"));
				}
			}
			
			// 정렬 list
			if (map.get("sort") != null && !map.get("sort").equals("")) {
				if (map.get("sort").equals("최신순")) {
					order += " by regdate desc";
				} else if (map.get("sort").equals("조회순")) {
					order += " by cnt desc";
				} else if (map.get("sort").equals("댓글순")) {
					order += " by ccnt desc";
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
			
			
	        String sql = String.format("select * from (select c.*, rownum as rnum from vwCommunity c %s %s) where rnum between %s and %s"
	        							, where
	        							, order
	        			                , map.get("begin")
	        			                , map.get("end"));

	        pstat = conn.prepareStatement(sql);
			
			rs = pstat.executeQuery();
			
			ArrayList<CommunityDTO> list = new ArrayList<CommunityDTO>();
					
			while ( rs.next() ) {
				
				CommunityDTO dto = new CommunityDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setId(rs.getString("id"));
				dto.setCategory(rs.getString("category"));
				dto.setTitle(rs.getString("title"));
				dto.setDetail(rs.getString("detail"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setCnt(rs.getString("cnt"));
				dto.setName(rs.getString("name"));
				dto.setIsnew(rs.getString("isnew"));
				dto.setCcnt(rs.getString("ccnt"));
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
			
			String where = "";
			
			if (map.get("isSearch").equals("y")) {
				where += " where";
			}
			
			// 내가 쓴 글 list
			if (map.get("id") != null && !map.get("id").equals("")) {
				where += String.format(" id='%s'", map.get("id"));
			}
			
			// 카테고리별 list
			if (map.get("category") != null) {
				
				if (map.get("category").equals("전체보기")) {
					
				} else if (map.get("category").equals("일손돕기")) {
					where += String.format(" category='%s' and", map.get("category"));
				} else if (map.get("category").equals("농촌체험")) {
					where += String.format(" category='%s' and", map.get("category"));
				} else if (map.get("category").equals("주말농장")) {
					where += String.format(" category='%s' and", map.get("category"));
				} else if (map.get("category").equals("농작물 직거래")) {
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

			String sql = String.format("select count(*) as total from tblCommunity %s", where);
			
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
			
			String sql = "update tblCommunity set cnt = cnt + 1 where seq=?";
			
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
	public CommunityDTO get(String seq) {
		
		try {
			
			String sql = "select c.*, (select name from tblUser where id = c.id ) as name, (select count(*) from tblCComment where postSeq = c.seq) as ccnt "
					+ "from tblCommunity c where seq=?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if ( rs.next() ) {
				
				CommunityDTO dto = new CommunityDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setDetail(rs.getString("detail"));
				dto.setCategory(rs.getString("category"));
				dto.setCnt(rs.getString("cnt"));
				dto.setCcnt(rs.getString("ccnt"));
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
			
			String sql = "select image from tblCommunityImg where pseq=?";
			
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
	 * @param 커뮤니티DTO
	 * @return 결과값(0 or 1)
	 */
	public int edit(CommunityDTO dto) {
		
		try {
			
			String sql = "update tblCommunity set category=?, title=?, detail=? where seq=?";
			
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
			
			String sql = "insert into tblCommunityImg (seq, image, pseq) "
					+ "values (seqComImg.NextVal, ?, ?)";
			
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
			
			// 게시글 삭제
			String sql = "delete from tblCommunity where seq=?";
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
			String sql = "delete from tblCommunityImg where pseq=?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * View 서블릿에서 댓글 수를 확인 하기 위한 DB에 SELECT하는 메소드
	 * 
	 * @param HashMap에 저장된 조건 내용
	 * @return 총 댓글 수
	 */
	public int getViewTotalCount(HashMap<String, String> map) {
		
		try {
			
			String sql = String.format("select count(*) as total from tblCComment where postseq=?");
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("seq"));
			
			rs = pstat.executeQuery();
			
			if ( rs.next() ) {
				return rs.getInt("total");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

}
