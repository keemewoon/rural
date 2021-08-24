package com.project.rural.admin;

/**
 * 관리자 - 페이지 통계(차트)와 관련된 Getter & Setter
 * 
 * @author 김영혁
 */
public class ChartDTO {
	
	private String name;
	private String cnt;
	private String community;
	private String exp;
	private String worker;
	private String farm;
	private String market;
	private String notice;
	private String normal;
	private String out;
	private String stop;
	private String ordinary;
	private String farmer;
	
	/**
	 * 일반 사용자 Getter
	 * @return 일반사용자
	 */
	public String getOrdinary() {
		return ordinary;
	}
	
	/**
	 * 일반 사용자 Setter
	 * @param 일반 사용자
	 */
	public void setOrdinary(String ordinary) {
		this.ordinary = ordinary;
	}
	
	/**
	 * 농업인 Getter
	 * @return 농업인
	 */
	public String getFarmer() {
		return farmer;
	}
	
	/**
	 * 농업인 Setter
	 * @param 농업인
	 */
	public void setFarmer(String farmer) {
		this.farmer = farmer;
	}
	
	/**
	 * 정상 Getter
	 * @return 정상
	 */
	public String getNormal() {
		return normal;
	}
	
	/**
	 * 정상 Setter
	 * @param 정상
	 */
	public void setNormal(String normal) {
		this.normal = normal;
	}
	
	/**
	 * 탈퇴 Getter
	 * @return 탈퇴
	 */
	public String getOut() {
		return out;
	}
	
	/**
	 * 탈퇴 Setter
	 * @param 탈퇴
	 */
	public void setOut(String out) {
		this.out = out;
	}
	
	/**
	 * 정지 Getter
	 * @return 정지
	 */
	public String getStop() {
		return stop;
	}
	
	/**
	 * 정지 Setter
	 * @param 정지
	 */
	public void setStop(String stop) {
		this.stop = stop;
	}
	
	/**
	 * 농촌체험 Getter
	 * @return 농촌체험
	 */
	public String getExp() {
		return exp;
	}
	
	/**
	 * 농촌체험 Setter
	 * @param 농촌체험
	 */
	public void setExp(String exp) {
		this.exp = exp;
	}
	
	/**
	 * 주말농장 Getter
	 * @return 주말농장
	 */
	public String getFarm() {
		return farm;
	}
	
	/**
	 * 주말농장 Setter
	 * @param 주말농장
	 */
	public void setFarm(String farm) {
		this.farm = farm;
	}
	
	/**
	 * 농작물 직거래 Getter
	 * @return 농작물 직거래
	 */
	public String getMarket() {
		return market;
	}
	
	/**
	 * 농작물 직거래 Setter
	 * @param 농작물 직거래
	 */
	public void setMarket(String market) {
		this.market = market;
	}
	
	/**
	 * 알림마당 Getter
	 * @return 알림마당
	 */
	public String getNotice() {
		return notice;
	}
	
	/**
	 * 알림마당 Setter
	 * @param 알림마당
	 */
	public void setNotice(String notice) {
		this.notice = notice;
	}
	
	/**
	 * 일손돕기 Getter
	 * @return 일손돕기
	 */
	public String getWorker() {
		return worker;
	}
	
	/**
	 * 일손돕기 Setter
	 * @param 일손돕기
	 */
	public void setWorker(String worker) {
		this.worker = worker;
	}
	
	/**
	 * 사용자 이름 Getter
	 * @return 사용자 이름
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 사용자 이름 Setter
	 * @param 사용자 이름
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 글 개수 Getter
	 * @return 글 개수
	 */
	public String getCnt() {
		return cnt;
	}
	
	/**
	 * 글 개수 Setter
	 * @param 글 개수
	 */
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	
	/**
	 * 커뮤니티 Getter
	 * @return 커뮤니티
	 */
	public String getCommunity() {
		return community;
	}
	
	/**
	 * 커뮤니티 Setter
	 * @param 커뮤니티
	 */
	public void setCommunity(String community) {
		this.community = community;
	}


}
