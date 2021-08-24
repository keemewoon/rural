package com.project.rural.market;

import java.util.ArrayList;

public class MarketDTO {

	/**
	 * @param seq 글번호
	 * @param id 글쓴이(농부계정)
	 * @param marketInfo 카테고리
	 * @param brandName 브랜드이름
	 * @param tel 전화번호
	 * @param address 주소
	 * @param site 사이트주소
	 * @param detail 상세내용
	 * @param regDate 글쓴일자
	 * @param image 썸네일이미지
	 * @param isnew 얼마나 오래되었는지?
	 * @param farmername 농부이름
	 */
	private String seq;
	private String id;
	private String marketInfo;
	private String name;
	private String brandName;
	private String tel;
	private String address;
	private String site;
	private String detail;
	private String regDate;
	private String image;

	private String isnew; //새글 표시
	private String farmername;

	private ArrayList<String> images;


	public ArrayList<String> getImages() {
		return images;
	}
	public void setImages(ArrayList<String> images) {
		this.images = images;
	}
	public String getFarmername() {
		return farmername;
	}
	public void setFarmername(String farmername) {
		this.farmername = farmername;
	}
	public String getIsnew() {
		return isnew;
	}
	public void setIsnew(String isnew) {
		this.isnew = isnew;
	}
	public void setMarketInfo(String marketInfo) {
		this.marketInfo = marketInfo;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMarketInfo() {
		return marketInfo;
	}
	public void setMaketInfo(String marketInfo) {
		this.marketInfo = marketInfo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}



}



