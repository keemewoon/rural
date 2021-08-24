package com.project.rural.farm;

/**
 * 주말농장 리뷰 정보를 담는 클래스
 * @author 유기호
 *
 */
public class ReviewDTO {

	private String seq;
	private String pseq;
	private String id;
	private String detail;
	private String regDate;
	private String star;

	/**
	 * 번호 Getter
	 * @return 번호
	 */
	public String getSeq() {
		return seq;
	}

	/**
	 * 번호 Setter
	 * @param seq 번호
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}

	/**
	 * 주말농장 번호 Getter
	 * @return 주말농장 번호
	 */
	public String getPseq() {
		return pseq;
	}

	/**
	 * 번주말농장 번호 Setter
	 * @param pseq 주말농장 번호
	 */
	public void setPseq(String pseq) {
		this.pseq = pseq;
	}

	/**
	 * 아이디 Getter
	 * @return 아이디
	 */
	public String getId() {
		return id;
	}

	/**
	 * 아이디 Setter
	 * @param id 아이디
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 내용 Getter
	 * @return 내용
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * 내용 Setter
	 * @param detail 내용
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * 작성날짜 Getter
	 * @return 작성날짜
	 */
	public String getRegDate() {
		return regDate;
	}

	/**
	 * 작성날짜 Setter
	 * @param regDate 작성날짜
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	/**
	 * 별점 Getter
	 * @return 별점
	 */
	public String getStar() {
		return star;
	}

	/**
	 * 별점 Setter
	 * @param star 별점
	 */
	public void setStar(String star) {
		this.star = star;
	}
	
}
