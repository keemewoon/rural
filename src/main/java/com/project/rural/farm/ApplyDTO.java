package com.project.rural.farm;

/**
 * 주말농장 신청 정보를 담는 클래스
 * @author 유기호
 *
 */
public class ApplyDTO {

	private String seq;
	private String pseq;
	private String id; //신청자 아이디
	private String area;
	private String regDate;
	private String isPass;
	private String passDate;
	private String name; //농장명
	private String price; //임대료
	private String address; //농장 주소
	private String tel; //농장 전화번호
	private String ownerId; //농장주 아이디
	private String ownerName; //농장주 이름
	private String userName; //신청자 이름
	private String userTel; //신청자 전화번호
	
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
	 * 주말농장 번호 Setter
	 * @param pseq 주말농장 번호
	 */
	public void setPseq(String pseq) {
		this.pseq = pseq;
	}

	/**
	 * 신청자 아이디 Getter
	 * @return 신청자 아이디
	 */
	public String getId() {
		return id;
	}

	/**
	 * 신청자 아이디 Setter
	 * @param id 신청자 아이디
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 임대면적 Getter
	 * @return 임대면적
	 */
	public String getArea() {
		return area;
	}

	/**
	 * 임대면적 Setter
	 * @param area 임대면적
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * 신청날짜 Getter
	 * @return 신청날짜
	 */
	public String getRegDate() {
		return regDate;
	}

	/**
	 * 신청날짜 Setter
	 * @param regDate 신청날짜
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	/**
	 * 승인여부 Getter
	 * @return 번호승인여부
	 */
	public String getIsPass() {
		return isPass;
	}

	/**
	 * 승인여부 Setter
	 * @param isPass 승인여부
	 */
	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}

	/**
	 * 승인날짜 Getter
	 * @return 승인날짜
	 */
	public String getPassDate() {
		return passDate;
	}

	/**
	 * 승인날짜 Setter
	 * @param passDate 승인날짜
	 */
	public void setPassDate(String passDate) {
		this.passDate = passDate;
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
	 * 농장주 아이디 Getter
	 * @return 농장주 아이디
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * 농장주 아이디 Setter
	 * @param ownerId 농장주 아이디
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * 농장주 이름 Getter
	 * @return 농장주 이름
	 */
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * 농장주 이름 Setter
	 * @param ownerName 농장주 이름
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	/**
	 * 신청자 이름 Getter
	 * @return 신청자 이름
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 신청자 이름 Setter
	 * @param userName 신청자 이름
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 신청자 전화번호 Getter
	 * @return 신청자 전화번호
	 */
	public String getUserTel() {
		return userTel;
	}

	/**
	 * 신청자 전화번호 Setter
	 * @param userTel 신청자 전화번호
	 */
	public void setUserTel(String userTel) {
		this.userTel = userTel;
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
	
}
