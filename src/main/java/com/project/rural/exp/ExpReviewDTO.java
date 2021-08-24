package com.project.rural.exp;

/**
 * 모집공고에 달린 리뷰의 데이터에 접근하여 CRUD작업을 하기 위한 변수와 getter/setter
 * @author Jiyoon Kyeong
 *
 */
public class ExpReviewDTO {

	private String seq;
	private String pseq;
	private String id;
	private String detail;
	private String regDate;
	private String star;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getPseq() {
		return pseq;
	}
	public void setPseq(String pseq) {
		this.pseq = pseq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
}
