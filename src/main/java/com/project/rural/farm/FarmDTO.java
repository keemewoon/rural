package com.project.rural.farm;

/**
 * 주말농장 정보를 담는 클래스
 * @author 유기호
 *
 */
public class FarmDTO {

	private String seq;
	private String id;
	private String name;
	private String tel;
	private String address;
	private String area;
	private String price;
	private String detail;
	private String isEnd;
	private String regDate;
	private String image;
	private String extraArea;
	private String avgStar;
	private String reviewCount;
	private String ownerName;
	
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
	 * 농장이름 Getter
	 * @return 농장이름
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 농장이름 Setter
	 * @param name 농장이름
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 전화번호 Getter
	 * @return 전화번호
	 */
	public String getTel() {
		return tel;
	}
	
	/**
	 * 전화번호 Setter
	 * @param tel 전화번호
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	/**
	 * 주소 Getter
	 * @return 주소
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * 주소 Setter
	 * @param address 주소
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 총면적 Getter
	 * @return 총면적
	 */
	public String getArea() {
		return area;
	}
	
	/**
	 * 총면적 Setter
	 * @param area 총면적
	 */
	public void setArea(String area) {
		this.area = area;
	}
	
	/**
	 * 임대료 Getter
	 * @return 임대료
	 */
	public String getPrice() {
		return price;
	}
	
	/**
	 * 임대료 Setter
	 * @param price 임대료
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	
	/**
	 * 상세내용 Getter
	 * @return 상세내용
	 */
	public String getDetail() {
		return detail;
	}
	
	/**
	 * 상세내용 Setter
	 * @param detail 상세내용
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	/**
	 * 마감여부 Getter
	 * @return 마감여부
	 */
	public String getIsEnd() {
		return isEnd;
	}
	
	/**
	 * 마감여부 Setter
	 * @param isEnd 마감여부
	 */
	public void setIsEnd(String isEnd) {
		this.isEnd = isEnd;
	}
	
	/**
	 * 등록날짜 Getter
	 * @return 등록날짜
	 */
	public String getRegDate() {
		return regDate;
	}
	
	/**
	 * 등록날짜 Setter
	 * @param regDate 임대등록날짜료
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	/**
	 * 이미지파일명 Getter
	 * @return 이미지파일명
	 */
	public String getImage() {
		return image;
	}
	
	/**
	 * 이미지파일명 Setter
	 * @param image 이미지파일명
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	/**
	 * 잔여면적 Getter
	 * @return 잔여면적
	 */
	public String getExtraArea() {
		return extraArea;
	}
	
	/**
	 * 잔여면적 Setter
	 * @param extraArea 잔여면적
	 */
	public void setExtraArea(String extraArea) {
		this.extraArea = extraArea;
	}
	
	/**
	 * 평균별점 Getter
	 * @return 평균별점
	 */
	public String getAvgStar() {
		return avgStar;
	}
	
	/**
	 * 평균별점 Setter
	 * @param avgStar 평균별점
	 */
	public void setAvgStar(String avgStar) {
		this.avgStar = avgStar;
	}
	
	/**
	 * 총리뷰수 Getter
	 * @return 총리뷰수
	 */
	public String getReviewCount() {
		return reviewCount;
	}
	
	/**
	 * 총리뷰수 Setter
	 * @param reviewCount 총리뷰수
	 */
	public void setReviewCount(String reviewCount) {
		this.reviewCount = reviewCount;
	}
	
	/**
	 * 농장주이름 Getter
	 * @return 농장주이름
	 */
	public String getOwnerName() {
		return ownerName;
	}
	
	/**
	 * 농장주이름 Setter
	 * @param ownerName 농장주이름
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

}
