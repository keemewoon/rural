package com.project.rural.notice;

import java.util.ArrayList;

/**
 * 게시글과 관련된 Getter & Setter
 * 
 * @author 김영혁
 */
public class NoticeDTO {

	private String seq;
	private String id;
	private String category;
	private String title;
	private String detail;
	private String regdate;
	private String cnt;
	private String name;
	private String isnew;
	private ArrayList<String> images;
	private String pseq;
	private String imgCnt;
	
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
	 * 카테고리 Getter
	 * @return 카테고리
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * 카테고리 Setter
	 * @param 카테고리
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
	 * 글 제목 Getter
	 * @return 글 제목
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * 글 제목 Setter
	 * @param 글 제목
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 글 내용 Getter
	 * @return 글 내용
	 */
	public String getDetail() {
		return detail;
	}
	
	/**
	 * 글 내용 Setter
	 * @param 글 내용
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
	 * 조회수 Getter
	 * @return 조회수
	 */
	public String getCnt() {
		return cnt;
	}
	
	/**
	 * 조회수 Setter
	 * @param 조회수
	 */
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	
	/**
	 * 댓글번호 Getter
	 * @return 댓글번호
	 */
	public String getPseq() {
		return pseq;
	}
	
	/**
	 * 댓글번호 Setter
	 * @param 댓글번호
	 */
	public void setPseq(String pseq) {
		this.pseq = pseq;
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
	
	/**
	 * 최신글 Getter
	 * @return 최신글
	 */
	public String getIsnew() {
		return isnew;
	}
	
	/**
	 * 최신글 Setter
	 * @param 최신글
	 */
	public void setIsnew(String isnew) {
		this.isnew = isnew;
	}
	
	/**
	 * 이미지 Getter
	 * @return 이미지
	 */
	public ArrayList<String> getImages() {
		return images;
	}
	
	/**
	 * 이미지 Setter
	 * @param 이미지
	 */
	public void setImages(ArrayList<String> images) {
		this.images = images;
	}
	
	/**
	 * 이미지 수 Getter
	 * @return 이미지 수
	 */
	public String getImgCnt() {
		return imgCnt;
	}
	
	/**
	 * 이미지 수 Setter
	 * @param 이미지 수
	 */
	public void setImgCnt(String imgCnt) {
		this.imgCnt = imgCnt;
	}
}
