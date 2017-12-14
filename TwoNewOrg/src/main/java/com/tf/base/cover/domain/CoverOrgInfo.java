package com.tf.base.cover.domain;

public class CoverOrgInfo {

	private Integer id;
	private String name;
	private String orgType;
	private String createOrg;
	private String createOrgTxt;
	private String orgClass;
	private String nature;   //社会组织的性质
	private String industryType; //非公组织的类型
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	public String getCreateOrg() {
		return createOrg;
	}
	public void setCreateOrg(String createOrg) {
		this.createOrg = createOrg;
	}
	public String getCreateOrgTxt() {
		return createOrgTxt;
	}
	public void setCreateOrgTxt(String createOrgTxt) {
		this.createOrgTxt = createOrgTxt;
	}
	public String getOrgClass() {
		return orgClass;
	}
	public void setOrgClass(String orgClass) {
		this.orgClass = orgClass;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getIndustryType() {
		return industryType;
	}
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	
}
