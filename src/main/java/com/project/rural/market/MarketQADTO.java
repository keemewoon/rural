package com.project.rural.market;

public class MarketQADTO {

	/**
	 * @param seq QnA글번호
	 * @param pseq 부모글번호(=글번호)
	 * @param id QnA글쓴이
	 * @param title 제목
	 * @param detailq 문의내용
	 * @param detaila 답변내용
	 * @param regdatea 문의쓴날짜
	 * @param regdateq 답변쓴날짜
	 * @param isa 답변이있으면 y,없으면 n
	 */
	private String seq;
	private String pseq;
	private String id;
	private String title;
	private String detailq;
	private String detaila;
	private String regdatea;
	private String regdateq;
	private String isa;

	public String getRegdatea() {
		return regdatea;
	}
	public void setRegdatea(String regdatea) {
		this.regdatea = regdatea;
	}
	public String getRegdateq() {
		return regdateq;
	}
	public void setRegdateq(String regdateq) {
		this.regdateq = regdateq;
	}
	public String getDetailq() {
		return detailq;
	}
	public void setDetailq(String detailq) {
		this.detailq = detailq;
	}
	public String getDetaila() {
		return detaila;
	}
	public void setDetaila(String detaila) {
		this.detaila = detaila;
	}

	private String name;


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIsa() {
		return isa;
	}
	public void setIsa(String isa) {
		this.isa = isa;
	}



}
