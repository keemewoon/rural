package com.project.rural.community;
/**
 * 댓글과 관련된 Getter & Setter
 * 
 * @author 김영혁
 */
public class CommentDTO {
	
	private String seq;
	private String pseq;
	private String postSeq;
	private String id;
	private String detail;
	private String regdate;
	private String name;
	
	/**
	 * 글 번호 Getter
	 * @return 글 번호
	 */
	public String getSeq() {
		return seq;
	}
	
	/**
	 * 글 번호 Setter
	 * @param 글 번호
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	/**
	 * 댓글 번호 Getter
	 * @return 댓글 번호
	 */
	public String getPseq() {
		return pseq;
	}
	
	/**
	 * 댓글 번호 Setter
	 * @param 댓글 번호
	 */
	public void setPseq(String pseq) {
		this.pseq = pseq;
	}
	
	/**
	 * 해당 게시글 번호 Getter
	 * @return 해당 게시글 번호
	 */
	public String getPostSeq() {
		return postSeq;
	}
	
	/**
	 * 해당 게시글 번호 Setter
	 * @param 해당 게시글 번호
	 */
	public void setPostSeq(String postSeq) {
		this.postSeq = postSeq;
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
	 * @param 아이디
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 댓글 내용 Getter
	 * @return 댓글 내용
	 */
	public String getDetail() {
		return detail;
	}
	
	/**
	 * 댓글 내용 Setter
	 * @param 댓글 내용
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	/**
	 * 작성일자 Getter
	 * @return 작성일자
	 */
	public String getRegdate() {
		return regdate;
	}
	
	/**
	 * 작성일자 Setter
	 * @param 작성일자
	 */
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	/**
	 * 이름 Getter
	 * @return 이름
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 이름 Setter
	 * @param 이름
	 */
	public void setName(String name) {
		this.name = name;
	}

}
