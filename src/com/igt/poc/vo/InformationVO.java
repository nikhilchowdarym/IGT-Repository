package com.igt.poc.vo;

public class InformationVO {
	private Integer id;
	private String occupation;
	
	private String income;
	private String education;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	@Override
	public String toString() {
		return "informationPojo [id=" + id + ", occupation=" + occupation
				+ ", income=" + income + ", education=" + education + "]";
	}

}
