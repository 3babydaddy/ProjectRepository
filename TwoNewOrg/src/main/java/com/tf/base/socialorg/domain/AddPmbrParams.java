package com.tf.base.socialorg.domain;

import java.util.Date;


public class AddPmbrParams {

	private Integer id;
    private Integer socialOrgInfoId;

    private String name;
    private String gender;

    private String education;

    private String birthday;

    private String createOrg;

    private String partymbrInSocialorgIs;

    private String partymbrGroupInSocialorgIs;

    private String status;
	private String type;
	private String otherCondition;
	public Integer getSocialOrgInfoId() {
		return socialOrgInfoId;
	}
	public void setSocialOrgInfoId(Integer socialOrgInfoId) {
		this.socialOrgInfoId = socialOrgInfoId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getCreateOrg() {
		return createOrg;
	}
	public void setCreateOrg(String createOrg) {
		this.createOrg = createOrg;
	}
	public String getPartymbrInSocialorgIs() {
		return partymbrInSocialorgIs;
	}
	public void setPartymbrInSocialorgIs(String partymbrInSocialorgIs) {
		this.partymbrInSocialorgIs = partymbrInSocialorgIs;
	}
	public String getPartymbrGroupInSocialorgIs() {
		return partymbrGroupInSocialorgIs;
	}
	public void setPartymbrGroupInSocialorgIs(String partymbrGroupInSocialorgIs) {
		this.partymbrGroupInSocialorgIs = partymbrGroupInSocialorgIs;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOtherCondition() {
		return otherCondition;
	}
	public void setOtherCondition(String otherCondition) {
		this.otherCondition = otherCondition;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	
	
}
