package com.project.rural.community;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.project.rural.DBUtil;

public class CommentDAO {
	
	private Connection conn;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	/**
	 * DB 접속을 위한 생성자
	 * 
	 */
	public CommentDAO() {
		
		try {
			
			conn = DBUtil.open();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * AddComment 서블릿에서 해당 글의 댓글을 작성하기 위한 DB에 INSERT하는 메소드
	 * 
	 * @param 댓글DTO
	 * @return 결과값(0 or 1)
	 */
	public int addComment(CommentDTO dto) {

		try {
			
			String sql = "insert into tblCComment (seq, id, postseq, detail, regdate)"
					+ " values (seqCCom.nextVal, ?, ?, ?, default)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPostSeq());
			pstat.setString(3, dto.getDetail());
			
			return pstat.executeUpdate(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	/**
	 * View 서블릿에서 댓글 내용을 확인하기 위한 DB에 SELECT하는 메소드
	 * 
	 * @param HashMap에 저장된 조건 내용
	 * @return 번호, 댓글 내용, 아이디, 작성일자, 글 번호, 이름
	 */
	public ArrayList<CommentDTO> listComment(HashMap<String, String> map) {
		
		try {
			
			String sql = String.format("select * from (select c.*, rownum as rnum from vwCComment c where postSeq = ?) where rnum between %s and %s"
										, map.get("begin")
										, map.get("end"));
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, map.get("seq"));
			
			rs = pstat.executeQuery();
			
			ArrayList<CommentDTO> clist = new ArrayList<CommentDTO>();
			
			while ( rs.next() ) {
				
				CommentDTO dto = new CommentDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setDetail(rs.getString("detail"));
				dto.setId(rs.getString("id"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setPseq(rs.getString("pseq"));
				dto.setName(rs.getString("name"));
				
				clist.add(dto);
				
			}
			
			return clist;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * DelComment 서블릿에서 댓글 삭제를 위한 DB에 DELETE하는 메소드
	 * 
	 * @param 글 번호
	 * @return 결과값(0 or 1)
	 */
	public int delComment(String seq) {
		
		try {
			
			String sql = "delete from tblCComment where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			return pstat.executeUpdate(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	/**
	 * DelOk 서블릿에서 해당 댓글에 달린 답글을 전부 삭제를 위한 DB에 DELETE하는 메소드
	 * 
	 * @param 글 번호
	 */
	public void delAllComment(String seq) {
		
		try {
			
			String sql = "delete from tblCComment where postSeq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);		
			pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * AddReply 서블릿에서 해당 댓글 아래 답글을 달기 위한 DB에 INSERT하는 메소드
	 * 
	 * @param 댓글DTO
	 * @return 결과값(0 or 1)
	 */
	public int addReply(CommentDTO dto) {
		
		try {
			
			String sql = "insert into tblCComment (seq, id, postseq, pseq, detail, regdate)"
					+ " values (seqCCom.nextVal, ?, ?, ?, ?, default)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPostSeq());
			pstat.setString(3, dto.getPseq());
			pstat.setString(4, dto.getDetail());
			
			return pstat.executeUpdate(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * View 서블릿에서 해당 댓글 아래에 존재하는 답글 리스트를 출력하기 위한 DB에 SELECT 하는 작업
	 * 
	 * @param 글 번호
	 * @return 번호, 내용, 아이디, 작성일자, 글 번호, 이름
	 */
	public ArrayList<CommentDTO> listReply(String seq) {
		
		try {
			
			String sql = "select c.*, (select name from tblUser where id = c.id) as name "
					+ "from tblCComment c where pSeq = ? order by seq asc";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<CommentDTO> clist = new ArrayList<CommentDTO>();
			
			while ( rs.next() ) {
				
				CommentDTO dto = new CommentDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setDetail(rs.getString("detail"));
				dto.setId(rs.getString("id"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setPseq(rs.getString("pseq"));
				dto.setName(rs.getString("name"));
				
				clist.add(dto);
				
			}
			
			return clist;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
