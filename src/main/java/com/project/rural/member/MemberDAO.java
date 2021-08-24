package com.project.rural.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.project.rural.DBUtil;
import com.project.rural.community.CommunityDTO;

/**
 * 유저정보와 관련된 내용을 DB접속 후 제공하는 클래스
 * 
 * @author 김영혁
 */
public class MemberDAO {

	private Connection conn;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	/**
	 * DB 접속을 위한 생성자 
	 * 
	 */
	public MemberDAO() {
		
		try {
			
			conn = DBUtil.open();
			
		} catch (Exception e) {
			System.out.println("MemberDAO.MemberDAO()");
			e.printStackTrace();
		}
		
	}

	/**
	 * LoginOk 서블릿에서 사용자가 입력한 아이디/패스워드가 맞는지 DB에 SELECT하는 메소드
	 * 
	 * @param 유저DTO
	 * @return 아이디, 이름, 생년월일, 성별, 전화번호, 이메일, 주소, 권한등급, 가입일, 탈퇴유무, 정지유무
	 */
	public MemberDTO login(MemberDTO dto) {
		
		try {
			
			String sql = "select * from tblUser where id=? and pw=?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPw());
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				MemberDTO result = new MemberDTO();
				
				result.setId(rs.getString("id"));
				result.setName(rs.getString("name"));
				result.setBirth(rs.getString("birth"));
				result.setGender(rs.getString("gender"));
				result.setTel(rs.getString("tel"));
				result.setEmail(rs.getString("email"));
				result.setAddress(rs.getString("address"));
				result.setLv(rs.getString("lv"));
				result.setRegDate(rs.getString("regDate"));
				result.setIsOut(rs.getString("isOut"));
				result.setIsStop(rs.getString("isStop"));
				
				return result;
			}
			
		} catch (Exception e) {
			System.out.println("MemberDAO.login()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * SignUpOk 서블릿에서 사용자가 입력한 회원가입 폼 내용을 DB에 INSERT하는 작업 
	 * 
	 * @param 유저DTO
	 * @return 결과값(0 or 1)
	 */
	public int signup(MemberDTO dto) {
		
		try {
			
			String sql = "insert into tblUser (id, pw, name, birth, gender, tel, email, address, lv, regDate, isOut, isStop) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, default, default, default)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPw());
			pstat.setString(3, dto.getName());
			pstat.setString(4, dto.getBirth());
			pstat.setString(5, dto.getGender());
			pstat.setString(6, dto.getTel());
			pstat.setString(7, dto.getEmail());
			pstat.setString(8, dto.getAddress());
			pstat.setString(9, dto.getLv());
			
			return pstat.executeUpdate(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * FindIdOk 서블릿에서 사용자의 아이디 찾기를 위한 DB에 SELECT 검사 하는 메소드
	 * 
	 * @param 유저DTO
	 * @return 아이디
	 */
	public MemberDTO idFind(MemberDTO dto) {
		
		try {
			
			String sql = "select id from tblUser where name=? and email=?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getEmail());
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				MemberDTO result = new MemberDTO();
				result.setId(rs.getString("id"));
				
				return result;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * FindPwOk 서블릿에서 사용자가 비밀번호를 찾기 위한 DB에 SELECT 검사 하는 메소드
	 * 
	 * @param 유저DTO
	 * @return 아이디, 이름, 비밀번호
	 */
	public MemberDTO pwFind(MemberDTO dto) {
		
		try {
			
			String sql = "select * from tblUser where id=? and name=? and email=?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getName());
			pstat.setString(3, dto.getEmail());
			
			rs = pstat.executeQuery();
			System.out.println(sql);
			if (rs.next()) {
				
				MemberDTO result = new MemberDTO();
				result.setId(rs.getString("id"));
				result.setName(rs.getString("name"));
				result.setPw(rs.getString("pw"));
				
				return result;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	/**
	 * FindPwOk 서블릿에서 사용자의 비밀번호 찾기에 성공했다면 해당 사용자의 비밀번호 초기화를 DB에 UPDATE하는 메소드
	 * 
	 * @param 아이디
	 * @param 초기화 비밀번호
	 */
	public void pwClear(String id, int u_pw) {
		
		try {
			
			String sql = "update tblUser set pw=? where id=?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setInt(1, u_pw);
			pstat.setString(2, id);
			
			pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * MyEdit 서블릿에서 해당 사용자의 정보 확인을 위한 DB에 SELECT하는 메소드
	 * 
	 * @param 아이디
	 * @return 아이디, 이름, 생년월일, 성별, 전화번호, 이메일, 주소, 등급
	 */
	public MemberDTO get(String id) {
		
		try {
			
			String sql = "select * from tblUser where id=?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			
			rs = pstat.executeQuery();
			
			if ( rs.next() ) {
				
				MemberDTO dto = new MemberDTO();
				
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getString("birth"));
				dto.setGender(rs.getString("gender"));
				dto.setTel(rs.getString("tel"));
				dto.setEmail(rs.getString("email"));
				dto.setAddress(rs.getString("address"));
				dto.setLv(rs.getString("lv"));
				
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * MyEditOk 서블릿에서 해당 사용자 정보 수정을 위한 DB에 UPDAPTE하는 메소드 
	 * 
	 * @param 유저DTO
	 * @return 결과값(0 or 1)
	 */
	public int edit(MemberDTO dto) {
		
		try {
			
			String sql = "update tblUser set gender=?, tel=?, email=?, pw=?, address=? where id=?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getGender());
			pstat.setString(2, dto.getTel());
			pstat.setString(3, dto.getEmail());
			pstat.setString(4, dto.getPw());
			pstat.setString(5, dto.getAddress());
			pstat.setString(6, dto.getId());
			
			return pstat.executeUpdate(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * MyEdit, MyDelOk 서블릿에서 개인 정보 수정하기 전에<br>
	 * 해당 사용자의 패스워드를 한번 확인하기 위해 DB에 SELECT하는 메소드
	 * 
	 * @param 아이디
	 * @param 비밀번호
	 * @return 결과값(0 or 1)
	 */
	public int pwCheck(String id, String nowpw) {
		
		try {
			
			String sql = "select * from tblUser where id=? and pw=?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, id);
			pstat.setString(2, nowpw);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * MyDelOk 서블릿에서 회원 탈퇴를 위한 DB에 UPDATE하는 메소드
	 * 
	 * @param 아이디
	 */
	public void del(String id) {
		
		try {
			
			String sql = "update tblUser set isOut='y' where id=?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 해당 사용자의 아이디로 정보를 불러오기 위한 DB에 SELECT 하는 메소드
	 * 
	 * @param 아이디
	 * @return 결과값(true or false)
	 */
	public boolean checkId(String id) {
	      
	      try {
	         
	         String sql = "select * from tblUser where id=?";
	         
	         pstat = conn.prepareStatement(sql);
	         pstat.setString(1, id);
	         
	         rs = pstat.executeQuery();
	         
	         if (rs.next()) {         
	            return false;
	         } else {
	            return true;
	         }
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      
	      return false;
	}

	/**
	 * 관리자가 모든 사용자의 정보를 조건별로 확인하기 위한 DB에 SELECT 하는 메소드<br>
	 * 조건1. 회원 상태별 체크 유무<br>
	 * 조건2. 회원 유형별 체크 유무<br>
	 * 
	 * @param HashMap에 저장된 조건 내용
	 * @return 아이디, 이름, 등급, 가입일자, 탈퇴유무, 정지유무
	 */
	public ArrayList<MemberDTO> memberList(HashMap<String, String> map) {
		
		try {
			
			String where = "";
			
			if (map.get("isSearch").equals("y")) {
				where += " where";
			}
			
			// 탈퇴, 정지, 정상 list
			if (map.get("sort") != null && !map.get("sort").equals("")) {
				if (map.get("sort").equals("전체")) {
					
				} else if (map.get("sort").equals("탈퇴")) {
					where += String.format(" isOut = 'y' and");
				} else if (map.get("sort").equals("정지")) {
					where += String.format(" isStop = 'y' and isOut = 'n' and");
				} else if (map.get("sort").equals("정상")) {
					where += String.format(" isStop = 'n' and isOut = 'n' and");
				} 
			}
			
			// 전체, 일반, 농업인 id 검색
			if (map.get("column") != null && !map.get("column").equals("") ) {
				if (map.get("column").equals("전체")) {
					where += String.format(" lv = 1 and id like '%%%s%%' or lv = 2 and id like '%%%s%%'"
							, map.get("search"), map.get("search"));
				} else if (map.get("column").equals("일반")) {
					where += String.format(" lv = 1 and id like '%%%s%%'"
							, map.get("search"));
				} else if (map.get("column").equals("농업인")) {
					where += String.format(" lv = 2 and id like '%%%s%%'"
							, map.get("search"));
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
			
			String sql = String.format("select * from (select u.*, rownum as rnum from tblUser u %s) where rnum between %s and %s"
											, where
											, map.get("begin")
							                , map.get("end"));
			
			pstat = conn.prepareStatement(sql);
				
			rs = pstat.executeQuery();
				
			ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
			
			while ( rs.next() ) {
				
				MemberDTO dto = new MemberDTO();
				
				dto.setId(rs.getString("id"));
				dto.setLv(rs.getString("lv"));
				dto.setName(rs.getString("name"));
				dto.setRegDate(rs.getString("regdate"));
				dto.setIsOut(rs.getString("isout"));
				dto.setIsStop(rs.getString("isstop"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	
	/**
	 * 관리자가 회원관리 페이지에서 페이지 바 구현을 위한 조건에 맞는 총 회원 수를 DB에 SELECT하는 메소드
	 * 
	 * @param HashMap에 저장된 조건 내용
	 * @return 총 페이지 수 
	 */
	public int getTotalCount(HashMap<String, String> map) {
		
		try {
			
				String where = "";
				
				if (map.get("isSearch").equals("y")) {
					where += " where";
				}
				
				// 탈퇴, 정지, 정상 list
				if (map.get("sort") != null && !map.get("sort").equals("")) {
					if (map.get("sort").equals("전체")) {
						
					} else if (map.get("sort").equals("탈퇴")) {
						where += String.format(" isOut = 'y' and");
					} else if (map.get("sort").equals("정지")) {
						where += String.format(" isStop = 'y' and isOut = 'n' and");
					} else if (map.get("sort").equals("정상")) {
						where += String.format(" isStop = 'n' and isOut = 'n' and");
					} 
				}
				
				// 전체, 일반, 농업인 id 검색
				if (map.get("column") != null && !map.get("column").equals("") ) {
					if (map.get("column").equals("전체")) {
						where += String.format(" lv = 1 and id like '%%%s%%' or lv = 2 and id like '%%%s%%'"
								, map.get("search"), map.get("search"));
					} else if (map.get("column").equals("일반")) {
						where += String.format(" lv = 1 and id like '%%%s%%'"
								, map.get("search"));
					} else if (map.get("column").equals("농업인")) {
						where += String.format(" lv = 2 and id like '%%%s%%'"
								, map.get("search"));
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
				
				String sql = String.format("select count(*) as total from tblUser %s", where);
				
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
	 * 관리자가 해당 사용자를 정상 상태에서 정지 상태로 DB에 UPDATE하는 메소드
	 * 
	 * @param 아이디
	 * @return 결과값(0 or 1)
	 */
	public int stopY(String id) {
		
		try {
			
			String sql = "update tblUser set isStop='y' where id=?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}

	/**
	 * 관리자가 해당 사용자를 정지 상태에서 정상 상태로 DB에 UPDATE하는 메소드
	 * 
	 * @param 아이디
	 * @return 결과값(0 or 1)
	 */
	public int stopN(String id) {
		
		try {
			
			String sql = "update tblUser set isStop='n' where id=?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}


}
