package com.project.rural.market;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.project.rural.DBUtil;

public class MarketDAO {

	/**
	 * @param Connection DB연결
	 * @param Statement
	 * @param PreparedStatement
	 * @param ResultSet
	 */
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	/**
	 * 기본생성자
	 */
	public MarketDAO() {
		try {

			conn = DBUtil.open();

		} catch (Exception e) {
			System.out.println("MarketDAO.MarketDAO()");
			e.printStackTrace();
		}
	}

	/**
	 * Market > add 서블릿
	 * @param dto tblMarket에 담긴 모든 값
	 * @return 성공시 pstat.executeUpdate()반환 실패시 0
	 */
	public int add(MarketDTO dto) {

		try {

			String sql = "insert into tblMarket(seq, id, marketinfo, name, brandname, tel, address, site, detail, regdate, image)"
					+ "    values(seqMarket.nextVal, ?, ?, ?, ?, ?, ?, ?, ?, default, ?)";

			pstat = conn.prepareStatement(sql);

			// pstat.setString(1, dto.getSeq());
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getMarketInfo());
			pstat.setString(3, dto.getName());
			pstat.setString(4, dto.getBrandName());
			pstat.setString(5, dto.getTel());
			pstat.setString(6, dto.getAddress());
			pstat.setString(7, dto.getSite());
			pstat.setString(8, dto.getDetail());
			pstat.setString(9, dto.getImage());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * Market > list서블릿
	 * @param map 담긴 모든 정보
	 * @return 성공시 list반환 실패시 0
	 */
	public ArrayList<MarketDTO> list(HashMap<String, String> map) {

		try {

			// 메소드에서 하는 일
			// 1. 목록보기
			// 2. 검색하기

			String where = "";
			String sql = "";

			if (map.get("isSearch").equals("y")
					|| (map.get("marketinfo") != null && !map.get("marketinfo").equals(""))) {
				if (map.get("isLike").equals("y"))
					where += "and";
				else
					where += "where";
			} // 검색이 하나라도 존재하는 경우 where로 시작함

			/**
			 * 검색
			 */
			if (map.get("isSearch").equals("y")) {
				where += String.format(" brandName like '%%%s%%' ", map.get("search"));

			}

			if (map.get("marketinfo") != null && !map.get("marketinfo").equals("")) {
				if (where.equals("where") || where.equals("and"))
					where += String.format(" marketinfo like '%%%s%%' ", map.get("marketinfo"));
				else
					where += String.format("and marketinfo like '%%%s%%' ", map.get("marketinfo"));
			}

			if (map.get("isLike").equals("y")) {

				sql = String.format(
						"select * from (select b.*, l.id as likeid, rownum as rnum from tblMarket b inner join tblmarketlike l on b.seq=l.seq) where rnum between 1 and 10 and likeid = '%s' %s order by seq desc",
						map.get("id"), where, map.get("begin"), map.get("end"));

				System.out.println("sql:" + sql);
			} else {
				sql = String.format(
						"select * from (select b.*, rownum as rnum from tblMarket b %s) where rnum between %s and %s order by seq desc",
						where, map.get("begin"), map.get("end"));
			}


			pstat = conn.prepareStatement(sql);

			rs = pstat.executeQuery();

			ArrayList<MarketDTO> list = new ArrayList<MarketDTO>(); // 옮겨 담을 큰 상자

			while (rs.next()) {

				// 레코드 1줄 -> BoardDTO 한개

				MarketDTO dto = new MarketDTO();

				dto.setSeq(rs.getString("seq"));
				dto.setId(rs.getString("id"));
				dto.setMaketInfo(rs.getString("marketInfo"));
				dto.setName(rs.getString("name"));
				dto.setBrandName(rs.getString("brandname"));
				dto.setTel(rs.getString("tel"));
				dto.setAddress(rs.getString("address"));
				dto.setSite(rs.getString("site"));
				dto.setDetail(rs.getString("detail"));
				dto.setRegDate(rs.getString("regDate"));
				dto.setImage(rs.getString("image"));
				// dto.setIsnew(rs.getString("isnew"));

				list.add(dto);

			}
			// System.out.println("list: " + list);
			return list;

		} catch (Exception e) {
			System.out.println("MarketDAO.list()");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Market > view 서블릿
	 * @param seq 얻어올 글번호
	 * @return 성공시 dto반환 실패시 null
	 */
	public MarketDTO get(String seq) {

		try {

			String sql = "select b.*, (select name from tblUser where id = b.id) as farmername from tblMarket b where seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			if (rs.next()) {

				MarketDTO dto = new MarketDTO();

				dto.setSeq(rs.getString("seq"));
				dto.setId(rs.getString("id"));
				dto.setMaketInfo(rs.getString("marketInfo"));
				dto.setName(rs.getString("name"));
				dto.setBrandName(rs.getString("brandname"));
				dto.setTel(rs.getString("tel"));
				dto.setAddress(rs.getString("address"));
				dto.setSite(rs.getString("site"));
				dto.setDetail(rs.getString("detail"));
				dto.setRegDate(rs.getString("regDate"));
				dto.setImage(rs.getString("image"));
				dto.setFarmername(rs.getString("farmername"));

				return dto;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Market > delOk 서블릿
	 * @param seq 삭제할 글번호
	 * @return 성공시 pstat.executeUpdate, 실패시 0
	 */
	public int del(String seq) {

		try {

			String sql = "delete from tblMarket where seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("MarketDAO.del()");
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * market > EditOk서블릿
	 * @param dto sql에 담긴 모든 값
	 * @return 성공시 pstat.executeUpdate 실패시 0
	 */
	public int edit(MarketDTO dto) {

		try {

			String sql = "update tblMarket set marketinfo = ?, name = ?, brandname = ? , tel = ?, address = ? , site = ?, detail = ? , image  =? where seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, dto.getMarketInfo());
			pstat.setString(2, dto.getName());
			pstat.setString(3, dto.getBrandName());
			pstat.setString(4, dto.getTel());
			pstat.setString(5, dto.getAddress());
			pstat.setString(6, dto.getSite());
			pstat.setString(7, dto.getDetail());
			pstat.setString(8, dto.getImage());
			pstat.setString(9, dto.getSeq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("MarketDAO.edit()");
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * market > addlist서블릿
	 * @param map 등록내역리스트에서 사용할 모든값
	 * @return 성공시 pstat.executeUpdate 실패시 null
	 */
	public ArrayList<MarketDTO> addlist(HashMap<String, String> map) {

		try {

			String sql = String.format("select * from (select b.*, rownum as rnum from tblMarket b where id = '%s') where rnum between %s and %s order by seq desc"
													, map.get("id")
													, map.get("begin")
													, map.get("end"));

			pstat = conn.prepareStatement(sql);

			System.out.println("idDAO:" + map.get("id"));

			rs = pstat.executeQuery();

			ArrayList<MarketDTO> addlist = new ArrayList<MarketDTO>(); // 옮겨 담을 큰 상자

			while (rs.next()) {

				// 레코드 1줄 -> BoardDTO 한개

				MarketDTO dto = new MarketDTO();

				dto.setSeq(rs.getString("seq"));
				dto.setId(rs.getString("id"));
				dto.setMaketInfo(rs.getString("marketInfo"));
				dto.setName(rs.getString("name"));
				dto.setBrandName(rs.getString("brandname"));
				dto.setTel(rs.getString("tel"));
				dto.setAddress(rs.getString("address"));
				dto.setSite(rs.getString("site"));
				dto.setDetail(rs.getString("detail"));
				dto.setRegDate(rs.getString("regDate"));
				dto.setImage(rs.getString("image"));

				/**
				 * 리스트에 옮겨담기
				 */
				addlist.add(dto);

			}
			return addlist;

		} catch (Exception e) {
			System.out.println("MarketDAO.addlist()");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 *  Market > AddQna 서블릿,
	 * @param dto addQna에 필요한 모든 값
	 * @return 성공시 pstat.executeUpdate 실패시 0
	 */
	public int addQna(MarketQADTO dto) {

		try {

			String sql = "insert into tblMarketQA(seq, pseq, id, title, detailq, regdateq, detaila, regdatea, isa)"
					+ "    values(seqMarketQA.nextVal, ?, ?, ?, ?, default, ?, default, default)";

			pstat = conn.prepareStatement(sql);

			// pstat.setString(1, dto.getSeq());
			pstat.setString(1, dto.getPseq());
			pstat.setString(2, dto.getId());
			pstat.setString(3, dto.getTitle());
			pstat.setString(4, dto.getDetailq());
			pstat.setString(5, dto.getDetaila());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("MarketDAO.addQna()");
			e.printStackTrace();

		}

		return 0;

	}

	/**
	 * market > view 서블릿
	 * @param seq Qna를 볼 글번호
	 * @return 성공시 qlist 실패시 null
	 */
	public ArrayList<MarketQADTO> listQna(String seq) {

		try {

			String sql = "select c.*, (select name from tblUser where id = c.id ) as name from tblMarketQA c where pseq = ? order by seq desc";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			ArrayList<MarketQADTO> qlist = new ArrayList<MarketQADTO>();

			while (rs.next()) {
				MarketQADTO dto = new MarketQADTO();
				dto.setSeq(rs.getString("seq"));
				dto.setId(rs.getString("id"));
				dto.setTitle(rs.getString("title"));
				dto.setDetailq(rs.getString("detailq"));
				dto.setRegdateq(rs.getString("regdateq"));
				dto.setDetaila(rs.getString("detaila"));
				dto.setRegdatea(rs.getString("regdatea"));
				dto.setIsa(rs.getString("isa"));
				dto.setName(rs.getString("name"));

				qlist.add(dto);

			}
			return qlist;

		} catch (Exception e) {
			System.out.println("MarketDAO.listQna()");
			e.printStackTrace();

		}
		return null;
	}

	/**
	 * market > addReqna서블릿
	 * @param dto 추가할 답변에 사용할 정보
	 * @return 성공시 pstat.executeUpdate 실패시 0
	 */
	public int addReqna(MarketQADTO dto) {

		try {

			String sql = "update tblMarketQA set detaila = ?, regdatea = sysdate, isa ='y' where seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, dto.getDetaila());
			pstat.setString(2, dto.getSeq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("MarketDAO.addReqna()");
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * market > delReqna서블릿
	 * @param dto 삭제할 답변에 들어가는 정보
	 * @return 성공시 pstat.executeUpdate 실패시 0
	 */
	public int delReqna(MarketQADTO dto) {
		try {

			String sql = "update tblMarketQA set detaila = null, regdatea = null, isa ='n' where seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, dto.getSeq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("MarketDAO.delReqna()");
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * market > likeok 서블릿(좋아요 클릭)
	 * @param dto 좋아요 클릭에 필요한 정보
	 * @return 성공시 pstat.executeUpdate 실패시 0
	 */
	public int addlike(LikeDTO dto) {
		try {

			System.out.println("id: "+dto.getId());
			System.out.println("seq: "+dto.getSeq());
			String sql = "insert into tblMarketlike(id, seq) values(?, ?)";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getSeq());

			/* System.out.println("MarketDAO:" + dto.getSeq()); */

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("MarketDAO.addlike()");
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 *  market > likelist 서블릿(좋아요 누른 게시글 확인)
	 * @param id 현재 로그인한 아이디
	 * @return 성공시 likelist옮겨담기 실패시 null
	 */
	ArrayList<LikeDTO> likelist(String id) {

		try {

			String sql = String.format(
					"select l.id as id, l.seq as seq from tblMarket m inner join tblMarketlike l on m.seq = l.seq where l.id =  '%s' ", id);

			pstat = conn.prepareStatement(sql);

			rs = pstat.executeQuery();

			ArrayList<LikeDTO> likelist = new ArrayList<LikeDTO>(); // 옮겨 담을 큰 상자

			while (rs.next()) {

				// 레코드 1줄 -> BoardDTO 한개

				LikeDTO dto = new LikeDTO();

				dto.setId(rs.getString("id"));
				dto.setSeq(rs.getString("seq"));

				// 리스트에 옮겨담기
				likelist.add(dto);

			}
			return likelist;

		} catch (Exception e) {
			System.out.println("MarketDAO.likelist()");
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 이미지 추가를 위해 필요한 메소드
	 * @param images 추가할 이미지 리스트
	 */
	public void addImg(ArrayList<String> images) {
		try {

			String seq = "";
			String sqlCom = "select max(seq) as seq from tblMarket";
			pstat = conn.prepareStatement(sqlCom);

			rs = pstat.executeQuery();

			if (rs.next()) {
				seq = rs.getString("seq");

				String sql = "insert into tblMarketImg (seq, image, pseq) values (seqMarketImg.nextVal, ?,?)";

				pstat = conn.prepareStatement(sql);

				for (String image : images) {
					pstat.setString(1, image);
					pstat.setString(2, seq);

					pstat.executeUpdate();
					System.out.println(sql + "," + image + "," + seq);
				}
			}

		} catch (Exception e) {
			System.out.println("MarketDAO.addImg()");
			e.printStackTrace();
		}

	}

	/**
	 * 이미지를 받아오기 위한 메소드
	 * @param seq 글번호
	 * @return 성공시 listImg 반환 실패시 null
	 */
	public ArrayList<String> listImg(String seq) {

		try {

			String sql = "select image from tblMarketImg where pseq=?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			ArrayList<String> listImg = new ArrayList<String>();

			while (rs.next()) {
				listImg.add(rs.getString("image"));
			}

			return listImg;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 이미지 삭제시 필요한 메소드
	 * @param seq 글번호
	 * @return 성공시 pstat.executeUpdate, 실패시 0
	 */
	public int delAllImg(String seq) {

		try {

			/**
			 * 게시글에 속한 이미지 삭제
			 */
			String sql = "delete from tblMarketImg where pseq=?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 이미지 수정시 필요한 메소드
	 * @param images 이미지
	 * @param seq 글번호
	 * @return 성공시 pstat.executeUpdate 실패시 0
	 */
	public int editImg(ArrayList<String> images, String seq) {

		try {

			String sql = "insert into tblMarketImg (seq, image, pseq) " + "values (seqMarketImg.NextVal, ?, ?)";

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
	 * market> delok서블릿 Qna모두 삭제
	 * @param seq 글번호
	 * @return 성공시 pstat.executeUpdate, 실패시 0
	 */
	public int delAllQna(String seq) {
		try {

			String sql = "delete from tblMarketQA where pseq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("MarketDAO.delAllQna()");
			e.printStackTrace();
		}

		return 0;

	}

	/**
	 * Qna삭제시 필요한 메소드 > View서블릿
	 * @param seq 글번호
	 * @return 성공시 pstat.executeUpdate, 실패시 0
	 */
	public int delQna(String seq) {
		try {

			String sql = "delete from tblMarketQA where seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);
			System.out.println("dao seq:" + seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("MarketDAO.delQna()");
			e.printStackTrace();
		}
		return 0;

	}

	/**
	 * 좋아요 모두 삭제(글삭제시 필요) > View
	 * @param seq 글번호
	 * @return 성공시 pstat.executeUpdate, 실패시 0
	 */
	public int delAlllike(String seq) {

		try {

			String sql = "delete from tblMarketLike where seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("MarketDAO.delAlllike()");
			e.printStackTrace();
		}

		return 0;

	}

	/**
	 * market > List 서블릿이 총 게시물수 알려달라고 요청
	 * @param map Marketlist에서 필요한 모든 정보
	 * @return 성공시 pstat.executeUpdate, 실패시 0
	 */
	public int getTotalCount(HashMap<String, String> map) {

		try {

			String where = "";

			if (map.get("isSearch").equals("y")) {
				// 검색
				where = String.format(" where brandName like '%%%s%%' ", map.get("search"));

			}
			if (map.get("marketinfo") != null && !map.get("marketinfo").equals("")) {
				where = String.format(" where marketinfo like '%%%s%%' ", map.get("marketinfo"));

			}

			String sql = String.format("select count(*) as cnt from tblMarket %s", where);

			pstat = conn.prepareStatement(sql);

			rs = pstat.executeQuery();

			if (rs.next()) {
				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			System.out.println("MarketDAO.getTotalCount()");
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 좋아요 삭제
	 * @param dto 좋아요 삭제시 필요한 정보
	 * @return 성공시 pstat.executeUpdate, 실패시 0
	 */
	public int dellike(LikeDTO dto) {

		try {

			String sql = "delete from tblMarketlike where seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, dto.getSeq());
			System.out.println("seq: "+dto.getSeq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("MarketDAO.dellike()");
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 *
	 * @param map
	 * @return
	 */
	public int getTotalCountAdd(HashMap<String, String> map) {
		try {

			String where = "";


			String sql = String.format(" select count(*) as cnt from tblMarket where id ='%s' "
											,map.get("id"));

			System.out.println("id:" + map.get("id"));

			pstat = conn.prepareStatement(sql);

			rs = pstat.executeQuery();

			if(rs.next()) {
				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			System.out.println("MarketDAO.getTotalCountAdd()");
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 좋아요를 카운트 하기위한 메소드
	 * @param seq 글번호
	 * @return 성공시 pstat.executeUpdate 실패시 0
	 */
	public int likecnt(String seq) {

		try {

			String sql = String.format("select seq, count(seq) as cnt from tblMarketlike where seq = '%s' group by seq"
							,seq);

			/* System.out.println("sql:" + sql); */

			pstat = conn.prepareStatement(sql);

			rs = pstat.executeQuery();

			if(rs.next()) {
				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			System.out.println("MarketDAO.likecnt()");
			e.printStackTrace();
		}

		return 0;
	}
}
