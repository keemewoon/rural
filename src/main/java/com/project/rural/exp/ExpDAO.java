package com.project.rural.exp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.project.rural.DBUtil;

/**
 * 농촌체험관련 데이터를 관리하기 위해 DB에 접속하는 클래스
 * @author Jiyoon kyeong
 */
public class ExpDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	/**
	 * DBUtil 클래스를 호출하여 DB 접속
	 * 초기 생성자
	 */
	public ExpDAO() {
		
		try {
			
			conn = DBUtil.open();
			
		} catch (Exception e) {
			System.out.println("ExpDAO.ExpDAO()");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * (농부)사용자가 농촌체험 공고를 입력하여 데이터에 해당 정보를 추가하는 메소드
	 * @param dto
	 * @return 쿼리문이(select) 실행된 레코드의 개수
	 */
	public int add(ExpDTO dto) {
		
		try {
			
			String sql = "insert into tblEXP values (seqExp.nextval,?,?,?,?,?,?,?,default,?,?,?,?,?,default,?)";
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getAddress());
			pstat.setString(3, dto.getTown());
			pstat.setString(4, dto.getExpInfo());
			pstat.setString(5, dto.getName());
			pstat.setString(6, dto.getStartDate());
			pstat.setString(7, dto.getEndDate());
			pstat.setString(8, dto.getTel());
			pstat.setString(9, dto.getSite());
			pstat.setString(10, dto.getTownDetail());
			pstat.setString(11, dto.getDetail());
			pstat.setString(12, dto.getTotalPerson());
			pstat.setString(13, dto.getImage());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ExpDAO.add()");
			e.printStackTrace();
		}
		return 0;
	}


	/**
	 * 농촌체험 전체 정보, 검색별 정보, 신청가능한 정보등을 불러오는 메소드
	 * @param map
	 *  검색한 내용(시도/구군/지역검색/지원가능)을 포함한다. 
	 * @return list 
	 * 데이터에서 해당정보를 불러와 list에 넣어 반환한다.
	 */
	public ArrayList<ExpDTO> list(HashMap<String, String> map) {
		try {

			String where = "";
			
			if (map.get("fSearch").equals("y")) {
				/**
				 * 검색
				 * where name like %홍길동%
				 * where all like '%날씨%'
				 */
								
				if(!map.get("paramSido").equals("")) {
					where += String.format(" and address like '%%%s%%' ", map.get("paramSido"));
				} 
				if (!map.get("paramGugun").equals("")) {
					where += String.format(" and address like '%%%s%%' ", map.get("paramGugun"));
				} 
				if (!map.get("paramExpInf").equals("")) {
					where += String.format(" and expInfo like '%%%s%%' ", map.get("paramExpInf"));
				} 
				if (!map.get("paramRegion").equals("")) {
					where += String.format(" and address like '%%%s%%' ", map.get("paramRegion"));
				}
				if (map.get("paramOnlyApply").equals("Y")) {
					where += String.format(" and  totalperson> applyperson ");
				}
			}

			
			/**
			 * 페이지 조건 <-> 검색 조건을 분리해야 함
			 */
			String sql = String.format("select * from (select f.*, rownum as rowCnt from vwExp f where 1=1 %s) where rowCnt between %s and %s order by seq desc"
									, where, map.get("begin"), map.get("end"));
			pstat = conn.prepareStatement(sql);
			
			rs = pstat.executeQuery();
			
			ArrayList<ExpDTO> list = new ArrayList<ExpDTO>(); //옮겨담을 큰상자
			
			while (rs.next()) {
				/**
				 * 레코드 1줄 -> BoardDTO1개
				 */
				ExpDTO dto = new ExpDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setExpInfo(rs.getString("expInfo")); //
				dto.setImage(rs.getString("image"));
				dto.setStartDate(rs.getString("startDate"));
				dto.setEndDate(rs.getString("endDate"));
				dto.setTown(rs.getString("town"));
				dto.setName(rs.getString("name"));
				dto.setIsnew(rs.getString("isnew"));
				dto.setAddress(rs.getString("address"));//
				dto.setStarRs(rs.getString("starRs"));//
				
				
				list.add(dto);
			}
			
		
			return list;
			
			
		} catch (Exception e) {
			System.out.println("ExpDAO.list()");
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * (상세보기페이지) 글번호를 받고 글번호에 해당되는 레코드 내용을 전부 DTO에 담아준다. 
	 * @param seq
	 * @param id
	 * @return dto
	 */
	public ExpDTO get(String seq, String id) {

		try {
			System.out.println(seq);
			
			String sql = "select  e.*, (select name from tblUser where id = e.id) as userName, (select count(*) from tblExpApply where pseq = e.seq and id=? ) as applyCnt,  (select nvl(sum(totalperson), 0) from tblExpApply where pseq = e.seq) applyperson from tblExp e where seq=?";
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, id);
			pstat.setString(2, seq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				/**
				 * 반환값은 dto (레코드 1줄) 
				 */
				ExpDTO dto = new ExpDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setTown(rs.getString("town"));
				dto.setAddress(rs.getString("address"));
				dto.setTel(rs.getString("tel"));
				dto.setSite(rs.getString("site"));
				dto.setUserName(rs.getString("userName"));
				dto.setTownDetail(rs.getString("townDetail"));
				dto.setDetail(rs.getString("detail"));
				dto.setRegDate(rs.getString("regDate"));
				dto.setExpInfo(rs.getString("expInfo"));
				dto.setImage(rs.getString("image"));
				dto.setTotalPerson(rs.getString("totalPerson"));
				dto.setStartDate(rs.getString("startDate"));
				dto.setEndDate(rs.getString("endDate"));
				dto.setApplyCnt(rs.getInt("applyCnt"));
				dto.setApplyperson(rs.getInt("applyperson"));
				
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("ExpDAO.get()");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * (수정하기페이지)에서 수정한 내용으로 데이터를 update시키기 위한 메소드
	 * @param dto
	 * @return 실행여부 
	 * ;실행했으면 1 실행에 실패했으면 0
	 */
	public int edit(ExpDTO dto) {
		try {
			
			
			String sql = "update tblExp set expInfo=?, town=?, name=?, totalPerson=?, startDate=?, endDate=?, tel=?, address=?, site=?, image=?, townDetail=?, detail=? where seq=?";
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getExpInfo());
			pstat.setString(2, dto.getTown());
			pstat.setString(3, dto.getName());
			pstat.setString(4, dto.getTotalPerson());
			pstat.setString(5, dto.getStartDate());
			pstat.setString(6, dto.getEndDate());
			pstat.setString(7, dto.getTel());
			pstat.setString(8, dto.getAddress());
			pstat.setString(9, dto.getSite());
			pstat.setString(10, dto.getImage());
			pstat.setString(11, dto.getTownDetail());
			pstat.setString(12, dto.getDetail());
			pstat.setString(13, dto.getSeq());
			
			/**
			 * 실행에 성공하면 0아닌 숫자가 반환한다.
			 */
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ExpDAO.edit()");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * (농부)사용자가 등록한 공고 내역들을 불러오는 메소드
	 * @param map
	 * map ; (농부)사용자의 id/페이징을위한 시작,끝 페이지 
	 * @return list
	 */
	public ArrayList<ExpDTO> addList(HashMap<String, String> map) {
		try {
			String sql = String.format("select b.* from (select a.*, rownum as rnum from (select e.*, (select count(*)as count from tblExpApply where pseq = e.seq)as count from tblExp e where id = '%s' order by seq desc)a)b where b.rnum between %s and %s", map.get("id"), map.get("begin"), map.get("end"));
			pstat= conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			


			/**
			 * 옮겨담기
			 */
			ArrayList<ExpDTO> list = new ArrayList<ExpDTO>();
			while(rs.next()) {
				
				ExpDTO dto = new ExpDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setExpInfo(rs.getString("expInfo")); //
				dto.setImage(rs.getString("image"));
				dto.setStartDate(rs.getString("startDate"));
				dto.setEndDate(rs.getString("endDate"));
				dto.setTown(rs.getString("town"));
				dto.setName(rs.getString("name"));
				dto.setAddress(rs.getString("address"));//
				dto.setCount(rs.getString("count"));
				
				
				list.add(dto);
				
			}
			
			return list;
		} catch (Exception e) {
			System.out.println("ExpDAO.addList()");
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 등록한 모집공고를 삭제하기위한 메소드 
	 * @param seq
	 * @return 실행여부
	 */
	public int del(String seq) {
		try {
			String sql = "delete from tblExp where seq =?";
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ExpDAO.del()");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 일반회원이 농부가 올린 모집공고에 지원하는 메소드
	 * @param dto
	 * @return 실행여부
	 */
	public int apply(ExpApplyDTO dto) {
		try {
			
			String sql = "insert into tblExpApply values (seqExpApply.nextval,?,?,?,?,default,?,?,default)";
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getPseq());
			pstat.setString(2, dto.getId());
			pstat.setString(3, dto.getStartDate());
			pstat.setString(4, dto.getEndDate());
			pstat.setString(5, dto.getTotalPerson());
			pstat.setString(6, dto.getDetail());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ExpDAO.apply()");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 일반 사용자가 자신이 신청한 지원내역을 불러오는 메소드
	 * @param map
	 * @return list
	 */
	public ArrayList<ExpApplyDTO> applyList(HashMap<String, String> map) {

		try {
			
			/**
			 * map.get("id") : 일반 사용자의 id
			 */
			String sql = String.format("select * from (select e.*, rownum as rnum from vwExpApply e where id = '%s') where rnum between %s and %s order by seq desc", map.get("id"), map.get("begin"), map.get("end"));
			
			pstat= conn.prepareStatement(sql);
			
			rs = pstat.executeQuery();
			
			//옮겨담기
			ArrayList<ExpApplyDTO> list = new ArrayList<ExpApplyDTO>();
			while(rs.next()) {
				
				ExpApplyDTO dto = new ExpApplyDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setPseq(rs.getString("pseq"));
				dto.setId(rs.getString("id"));
				dto.setStartDate(rs.getString("startDate"));
				dto.setEndDate(rs.getString("endDate"));
				dto.setRegDate(rs.getString("regDate"));
				dto.setTotalPerson(rs.getString("totalPerson"));
				dto.setDetail(rs.getString("detail"));
				dto.setIsPass(rs.getString("isPass"));
				dto.setExpName(rs.getString("expName"));
				dto.setExpInfo(rs.getString("expInfo"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("ExpDAO.applyList()");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * (농부)사용자가 자신이 올린 공고에 신청한 지원자들의 내역을 확인하는 메소드
	 * @param map
	 * @return list
	 */
	public ArrayList<ExpApplyDTO> appliCantList(HashMap<String, String> map) {

		try {
			
			String sql = "select * from vwExpApply where pseq = "+ map.get("seq");
			
			/**
			 * selection 태그로 미승인/승인 값을 선택했을시
			 */
			if(map.get("isView")!= null && !map.get("isView").equals("")) {
				sql += "and isPass = '" + map.get("isView") +"' order by seq desc";
			} 
			pstat= conn.prepareStatement(sql);
			
			rs = pstat.executeQuery();
			
		
			ArrayList<ExpApplyDTO> list = new ArrayList<ExpApplyDTO>();
			while(rs.next()) {
				
				ExpApplyDTO dto = new ExpApplyDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setPseq(rs.getString("pseq"));
				dto.setId(rs.getString("id"));
				dto.setStartDate(rs.getString("startDate"));
				dto.setEndDate(rs.getString("endDate"));
				dto.setRegDate(rs.getString("regDate"));
				dto.setTotalPerson(rs.getString("totalPerson"));
				dto.setDetail(rs.getString("detail"));
				dto.setIsPass(rs.getString("isPass"));
				dto.setExpName(rs.getString("expName"));
				dto.setExpInfo(rs.getString("expInfo"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setAge(rs.getString("age"));
				dto.setTel(rs.getString("tel"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("ExpDAO.appliCantList()");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 일반사용자가 상세보기 페이지에서 리뷰를 추가할수 있는 메소드
	 * @param dto
	 * @return 0(실패) 또는 1(실행한레코드의 개수)
	 */
	public int addReview(ExpReviewDTO dto) {
		
		try {
			
			/**
			 * 해당 모집에 승인여부가 y 인 사용자/id가 일반사용자/ 해당모집글인 조건으로 리뷰를 남길수 있다.
			 * tblExpApply에서 먼저 확인 -> 거르기
			 */
			String sql = "select* from tblExpApply where pseq=? and id=? and isPass ='y'";
			pstat = conn.prepareStatement(sql);
	
			
			pstat.setString(1, dto.getPseq());
			pstat.setString(2, dto.getId());
			
			rs = pstat.executeQuery();
					
			/**
			 * 위에서 걸러진 tblExpApply을 한바퀴 돌면서 insert문 실행! 
			 */
			if (rs.next()) {
				
				sql = "insert into tblExpReview values (seqExpReview.nextval,?,?,?,default,?)";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, dto.getPseq());
				pstat.setString(2, dto.getId());
				pstat.setString(3, dto.getDetail());
				pstat.setString(4, dto.getStar());
				
				return pstat.executeUpdate();
			} else {
				return 0;
			}
			
		
			
		} catch (Exception e) {
			System.out.println("ExpDAO.addReview()");
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 자세히보기 페이지에서 글번호 받아 해당글에 속한 리뷰 목록들을 불러오는 메소드
	 * @param pseq
	 * @return list
	 */
	public ArrayList<ExpReviewDTO> reviewList(String pseq) {
		
		try {
			
			String sql = String.format("select * from tblExpReview where pseq = ? order by seq desc");
			pstat= conn.prepareStatement(sql);
			pstat.setString(1, pseq);
			
			rs = pstat.executeQuery();
			
			ArrayList<ExpReviewDTO> list = new ArrayList<ExpReviewDTO>();
			while(rs.next()) {
				
				ExpReviewDTO dto = new ExpReviewDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setDetail(rs.getString("detail"));
				dto.setId(rs.getString("id"));
				dto.setRegDate(rs.getString("regDate"));
				dto.setPseq(rs.getString("pseq"));
				dto.setStar(rs.getString("star"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("ExpDAO.reviewList()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 일반사용자가 지원신청한내용을 취소(삭제)하는 메소드
	 * @param seq
	 * seq; 자신이 지원한 모집글번호
	 * @return  0(실패) 또는 1(실행한레코드의 개수)
	 */
	public int expApplyDel(String seq) {
		
		try {
			String sql = "delete from tblExpApply where seq =?";
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ExpDAO.expApplyDel()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * 일반사용자가 자신이 남긴 리뷰를 삭제하는 메소드 
	 * @param seq
	 * seq; 자신이 리뷰를 남긴 모집글번호
	 * @return 0(실패) 또는 1(실행한레코드의 개수)
	 */
	public int expReviewDel(String seq) {
		try {
			String sql = "delete from tblExpReview where seq =?";
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ExpDAO.expReviewDel()");
			e.printStackTrace();
		}
		return 0;
	}
	
	
	/**
	 * (농부)사용자가 모집공고글을 삭제하기전에 해당 글에 달린  리뷰들을 모두 삭제하는 메소드
	 * @param seq
	 */
	public void delAllReview(String seq) {
		try {
			
			String sql = "delete from tblExpReview where pseq = ?";
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1,  seq);
			pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ExpDAO.delAllReview()");
			e.printStackTrace();
		}
	}
	/**
	 * (농부)사용자가 모집공고글을 삭제하기전에 해당 글에 신청된 신청내역을 모두 삭제하는 메소드
	 * @param seq
	 */
	public void delAllApply(String seq) {
		try {
			
			String sql = "delete from tblExpApply where pseq =?";
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println("ExpDAO.delAllApply()");
			e.printStackTrace();
		}
	}

	/**
	 * 신청내역을 일괄적으로 승인기위한 메소드 (미승인/승인 내역중에 미승인 내역을 한꺼번에)
	 * @param seqs
	 * @return tmp
	 * ;승인처리된 개수
	 */
	public int passApply(String[] seqs) {
		int tmp = 0;
		
		try {
			
			String sql = "update tblExpApply set isPass = 'y' where seq =?";
			
			/**
			 * seqs(신청글번호) 배열을 모두 찾아내서 ?에 넣기
			 */
			for (String seq : seqs) {
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				pstat.executeUpdate();
				
				/**
				 * 승인처리된 갯수
				 */
				tmp++; 
			}
			
			return tmp;
			
		} catch (Exception e) {
			System.out.println("ExpDAO.passApply()");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 농촌체험 메인페이지에서 페이징을 위헤 총 게시글(모집공고)의 숫자를 알아내기 위한 메소드
	 * @param map
	 * @return rs.getInt("cnt")
	 * cnt ; 총 게시글의 갯수
	 */
	public int getTotalCount(HashMap<String, String> map) {

		try {
			
			String where = "";
			
			if (map.get("fSearch").equals("y")) {
				
				
				/**
				 * 검색
				 * where name like %홍길동%
				 * where all like '%날씨%'
				 */
				
				if(!map.get("paramSido").equals("")) {
					where += String.format(" and address like '%%%s%%' ", map.get("paramSido"));
				}
				if (!map.get("paramGugun").equals("")) {
					where += String.format(" and address like '%%%s%%' ", map.get("paramGugun"));
				}
				if (!map.get("paramExpInf").equals("")) {
					where += String.format(" and expInfo like '%%%s%%' ", map.get("paramExpInf"));
				}
				if (!map.get("paramRegion").equals("")) {
					where += String.format(" and address like '%%%s%%' ", map.get("paramRegion"));
				}
				if (map.get("paramOnlyApply").equals("Y")) {
					where += String.format(" and  totalperson> applyperson ");
				}
			}

			
			String sql = String.format("select count(*) as cnt from vwExp where 1=1 %s order by seq desc",  where);
			
			pstat = conn.prepareStatement(sql);
			
			rs = pstat.executeQuery();
			
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("cnt");
			}
			
			
		} catch (Exception e) {
			System.out.println("ExpDAO.getTotalCount()");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * (농촌)사용자의 등록내역페이지에서 페이징을 위헤 총 게시글(모집공고)의 숫자를 알아내기 위한 메소드 
	 * @param map
	 * @return rs.getInt("cnt")
	 * cnt ; 총 등록한 게시글의 갯수
	 */
	public int totalAddCount(HashMap<String, String> map) {
		try {
			
			
			String sql = "select count(*) as cnt from tblExp where id=?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("id"));
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("cnt");
			}
			
		} catch (Exception e) {
			System.out.println("ExpDAO.totalAddCount()");
			e.printStackTrace();
		}
		return 0;
	}

	
	/**
	 * 해당 게시글에 달린 리뷰들의 개수와 별(1~5)점의 평균을 불러오기위한 메소드
	 * @param seq
	 * @return starRs
	 * 
	 */
	public String starAvg(String seq) {
		try {
			
			String sql = "select T1.starAvg || '('||T1.cnt || ')'as starRs from (select TO_CHAR(ROUND(AVG(NVL(STAR, 0)), 1), 'FM9.0') as starAvg, count(*) as cnt from tblExpReview where pseq=?) T1";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs= pstat.executeQuery();
			
			if(rs.next()) return rs.getString("starRs");
			
			
		} catch (Exception e) {
			System.out.println("ExpDAO.starAvg()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 일반사용자의 신청내역페이지에서 페이징을 위해 신청내역 갯수를 알아내기 위한 메소드
	 * @param map
	 * @return
	 */
	public int totalApplyCount(HashMap<String, String> map) {
		try {

			String sql = "select count(*) as cnt from tblExpApply where id=?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("id"));
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("cnt");
			}
			
		} catch (Exception e) {
			System.out.println("ExpDAO.totalApplyCount()");
			e.printStackTrace();
		}
		return 0;
	}

}


