package com.project.rural.worker;

public class WorkerDTO {
	private String seq;
	private String id;
	private String city;
	private String gu;
	private String address;
	private String cropInfo;
	private String totalPerson;
	private String pay;
	private String minAge;
	private String maxAge;
	private String tel;
	private String startDate;
	private String endDate;
	private String regDate;
	private String detail;
	private String isCareer;
	private String isEnd;
	private String title;
	private String isPass;
	private String pseq;
	
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
	 * @return 제목
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * 
	 * @param 제목
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	private String name;
	
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
	 * @param 글번호
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	/**
	 * 
	 * @return 글번호
	 */
	public String getSeq() {
		return seq;
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
	 * @param 아이디
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 
	 * @return (지역)시
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * 
	 * @param (지역)시
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * 
	 * @return (지역)구
	 */
	public String getGu() {
		return gu;
	}
	
	/**
	 * 
	 * @param (지역)구
	 */
	public void setGu(String gu) {
		this.gu = gu;
	}
	
	/**
	 * 
	 * @return 상세주소
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 
	 * @param 상세주소
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 
	 * @return 작물정보
	 */
	public String getCropInfo() {
		return cropInfo;
	}
	
	/**
	 * 
	 * @param 작물정보
	 */
	public void setCropInfo(String cropInfo) {
		this.cropInfo = cropInfo;
	}
	
	/**
	 * 
	 * @return 인원수
	 */
	public String getTotalPerson() {
		return totalPerson;
	}
	
	/**
	 * 
	 * @param 인원수
	 */
	public void setTotalPerson(String totalPerson) {
		this.totalPerson = totalPerson;
	}
	
	/**
	 * 
	 * @return 수당
	 */
	public String getPay() {
		return pay;
	}
	
	/**
	 * 
	 * @param 수당
	 */
	public void setPay(String pay) {
		this.pay = pay;
	}
	
	/**
	 * 
	 * @return 최저나이대
	 */
	public String getMinAge() {
		return minAge;
	}
	
	/**
	 * 
	 * @param 최저나이대
	 */
	public void setMinAge(String minAge) {
		this.minAge = minAge;
	}
	
	/**
	 * 
	 * @return 최고나이대
	 */
	public String getMaxAge() {
		return maxAge;
	}
	
	/**
	 * 
	 * @param 최고나이대
	 */
	public void setMaxAge(String maxAge) {
		this.maxAge = maxAge;
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
	 * @return 시작날짜
	 */
	public String getStartDate() {
		return startDate;
	}
	
	/**
	 * 
	 * @param 시작날짜
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * 
	 * @return 종료날짜
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * 
	 * @param 종료날짜
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	 * 
	 * @return 상세 내용
	 */
	public String getDetail() {
		return detail;
	}
	
	/**
	 * 
	 * @param 상세 내용
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	/**
	 * 
	 * @return 경력
	 */
	public String getIsCareer() {
		return isCareer;
	}
	
	/**
	 * 
	 * @param 경력
	 */
	public void setIsCareer(String isCareer) {
		this.isCareer = isCareer;
	}
	
	/**
	 * 
	 * @return 현재 상태
	 */
	public String getIsEnd() {
		return isEnd;
	}
	
	/**
	 * 
	 * @param 현재 상태
	 */
	public void setIsEnd(String isEnd) {
		this.isEnd = isEnd;
	}
	
}
