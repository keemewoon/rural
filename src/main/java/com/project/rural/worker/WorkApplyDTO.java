package com.project.rural.worker;

public class WorkApplyDTO {

	private String id;
	private String seq;
	private String pseq;
	private String totalApply;
	private String detail;
	private String isPass;
	private String regDate;
	private String isCareer;
	
	private String name;
	private String age;
	private String gender;
	private String tel;
	private String birth;
	
	/**
	 * 
	 * @return 생년월일
	 */
	public String getBirth() {
		return birth;
	}
	/**
	 * 
	 * @param birth
	 */
	public void setBirth(String birth) {
		this.birth = birth;
	}
	/**
	 * 
	 * @return 아이디 
	 */
	public String getId() {
		return id;
	}
	/**
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 
	 * @return 신청내역번호
	 */
	public String getSeq() {
		return seq;
	}
	/**
	 * 
	 * @param 신청번호
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	/**
	 * 
	 * @return 글번호
	 */
	public String getPseq() {
		return pseq;
	}
	
	/**
	 * 
	 * @param 글번호
	 */
	public void setPseq(String pseq) {
		this.pseq = pseq;
	}
	
	/**
	 * 
	 * @return 인원수
	 */
	public String getTotalApply() {
		return totalApply;
	}
	/**
	 * 
	 * @param 인원수
	 */
	public void setTotalApply(String totalApply) {
		this.totalApply = totalApply;
	}
	/**
	 * 
	 * @return 상세내용
	 */
	public String getDetail() {
		return detail;
	}
	
	/**
	 * 
	 * @param 상세내용
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	/**
	 * 
	 * @return 승인여부
	 */
	public String getIsPass() {
		return isPass;
	}
	/**
	 * 
	 * @param 승인여부
	 */
	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}
	/**
	 * 
	 * @return 등록날짜
	 */
	public String getRegDate() {
		return regDate;
	}
	/**
	 * 
	 * @param 등록날짜
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	/**
	 * 
	 * @return 이름
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @param 이름
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @return 나이
	 */
	public String getAge() {
		return age;
	}
	
	/**
	 * 
	 * @param 나이
	 */
	public void setAge(String age) {
		this.age = age;
	}
	
	/**
	 * 
	 * @return 성별
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * 
	 * @param 성별
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * 
	 * @return 전화번호
	 */
	public String getTel() {
		return tel;
	}
	
	/**
	 * 
	 * @param 전화번호
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	/**
	 * 
	 * @return 경력유무
	 */
	public String getIsCareer() {
		return isCareer;
	}
	
	/**
	 * 
	 * @param 경력유무
	 */
	public void setIsCareer(String isCareer) {
		this.isCareer = isCareer;
	}
	
}
