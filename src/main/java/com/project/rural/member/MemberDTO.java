package com.project.rural.member;

/**
 * 유저 정보와 관련된 Getter & Setter
 * 
 * @author 김영혁
 */
public class MemberDTO {

	private String id;
	private String pw;
	private String name;
	private String birth;
	private String gender;
	private String tel;
	private String email;
	private String address;
	private String lv;
	private String regDate;
	private String isOut;
	private String isStop;
	
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
	 * 패스워드 Getter
	 * @return 패스워드
	 */
	public String getPw() {
		return pw;
	}
	
	/**
	 * 패스워드 Setter
	 * @param 패스워드
	 */
	public void setPw(String pw) {
		this.pw = pw;
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
	 * 생년월일 Getter
	 * @return 생년월일
	 */
	public String getBirth() {
		return birth;
	}
	
	/**
	 * 생년월일 Setter
	 * @param 생년월일
	 */
	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	/**
	 * 성별 Getter
	 * @return 성별
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * 성별 Setter
	 * @param 성별
	 */
	public void setGender(String gender) {
		this.gender = gender;
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
	 * @param 전화번호
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	/**
	 * 이메일 Getter
	 * @return 이메일
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * 이메일 Setter
	 * @param 이메일
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @param 주소
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 권한 등급 Getter
	 * @return 권한 등급
	 */
	public String getLv() {
		return lv;
	}
	
	/**
	 * 권한 등급 Setter
	 * @param 권한 등급
	 */
	public void setLv(String lv) {
		this.lv = lv;
	}
	
	/**
	 * 가입일자 Getter
	 * @return 가입일자
	 */
	public String getRegDate() {
		return regDate;
	}
	
	/**
	 * 가입일자 Setter
	 * @param 가입일자
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	/**
	 * 탈퇴유무 Getter
	 * @return 탈퇴유무
	 */
	public String getIsOut() {
		return isOut;
	}
	
	/**
	 * 탈퇴유무 Setter
	 * @param 탈퇴유무
	 */
	public void setIsOut(String isOut) {
		this.isOut = isOut;
	}
	
	/**
	 * 정지 유무 Getter
	 * @return 정지유무
	 */
	public String getIsStop() {
		return isStop;
	}
	
	/**
	 * 정지 유무 Setter
	 * @param 정지유무
	 */
	public void setIsStop(String isStop) {
		this.isStop = isStop;
	}
	
}
