package com.tf.base.socialorg.domain;

import javax.persistence.Column;

public class AllParams {

	private String mainId;
	private String chargeId;
	private String jobinId;
	private String pmbrCountId;
	
	//基础
	private String name;

    private String nature;

    private String category;

    private String registerOrg;

    private String businessDirectorOrg;

    private String address;

    //从业人员

    private Integer jobinTotalnum;

    private Integer jobinMajorNum;

    private Integer jobinPluralityNum;

    private Integer jobinPartymemberNum;

    private Integer jobinSocialteamGroupmemberNum;

    private Integer jobinSocialteamIndividualmemberNum;
    
    //负责人
    private String chargeName;

    private String chargePartymemberIs;

    private String chargePartyorgSecretaryIs;

    private String chargeTwodeputyAcommitteeIs;

    private String chargeTwodeputyAcommitteeType;

    private String chargeTwodeputyAcommitteeTypeOther;
    
    //是否上报 1.是 0否
	private Integer reportHigher;
	
	//其他特殊条件统计用到
	private String otherCondition;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRegisterOrg() {
		return registerOrg;
	}

	public void setRegisterOrg(String registerOrg) {
		this.registerOrg = registerOrg;
	}

	public String getBusinessDirectorOrg() {
		return businessDirectorOrg;
	}

	public void setBusinessDirectorOrg(String businessDirectorOrg) {
		this.businessDirectorOrg = businessDirectorOrg;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getJobinTotalnum() {
		return jobinTotalnum;
	}

	public void setJobinTotalnum(Integer jobinTotalnum) {
		this.jobinTotalnum = jobinTotalnum;
	}

	public Integer getJobinMajorNum() {
		return jobinMajorNum;
	}

	public void setJobinMajorNum(Integer jobinMajorNum) {
		this.jobinMajorNum = jobinMajorNum;
	}

	public Integer getJobinPluralityNum() {
		return jobinPluralityNum;
	}

	public void setJobinPluralityNum(Integer jobinPluralityNum) {
		this.jobinPluralityNum = jobinPluralityNum;
	}

	public Integer getJobinPartymemberNum() {
		return jobinPartymemberNum;
	}

	public void setJobinPartymemberNum(Integer jobinPartymemberNum) {
		this.jobinPartymemberNum = jobinPartymemberNum;
	}

	public Integer getJobinSocialteamGroupmemberNum() {
		return jobinSocialteamGroupmemberNum;
	}

	public void setJobinSocialteamGroupmemberNum(Integer jobinSocialteamGroupmemberNum) {
		this.jobinSocialteamGroupmemberNum = jobinSocialteamGroupmemberNum;
	}

	public Integer getJobinSocialteamIndividualmemberNum() {
		return jobinSocialteamIndividualmemberNum;
	}

	public void setJobinSocialteamIndividualmemberNum(Integer jobinSocialteamIndividualmemberNum) {
		this.jobinSocialteamIndividualmemberNum = jobinSocialteamIndividualmemberNum;
	}

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public String getChargePartymemberIs() {
		return chargePartymemberIs;
	}

	public void setChargePartymemberIs(String chargePartymemberIs) {
		this.chargePartymemberIs = chargePartymemberIs;
	}

	public String getChargePartyorgSecretaryIs() {
		return chargePartyorgSecretaryIs;
	}

	public void setChargePartyorgSecretaryIs(String chargePartyorgSecretaryIs) {
		this.chargePartyorgSecretaryIs = chargePartyorgSecretaryIs;
	}

	public String getChargeTwodeputyAcommitteeIs() {
		return chargeTwodeputyAcommitteeIs;
	}

	public void setChargeTwodeputyAcommitteeIs(String chargeTwodeputyAcommitteeIs) {
		this.chargeTwodeputyAcommitteeIs = chargeTwodeputyAcommitteeIs;
	}

	public String getChargeTwodeputyAcommitteeType() {
		return chargeTwodeputyAcommitteeType;
	}

	public void setChargeTwodeputyAcommitteeType(String chargeTwodeputyAcommitteeType) {
		this.chargeTwodeputyAcommitteeType = chargeTwodeputyAcommitteeType;
	}

	public String getChargeTwodeputyAcommitteeTypeOther() {
		return chargeTwodeputyAcommitteeTypeOther;
	}

	public void setChargeTwodeputyAcommitteeTypeOther(String chargeTwodeputyAcommitteeTypeOther) {
		this.chargeTwodeputyAcommitteeTypeOther = chargeTwodeputyAcommitteeTypeOther;
	}

	public Integer getReportHigher() {
		return reportHigher;
	}

	public void setReportHigher(Integer reportHigher) {
		this.reportHigher = reportHigher;
	}

	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	public String getChargeId() {
		return chargeId;
	}

	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}

	public String getJobinId() {
		return jobinId;
	}

	public void setJobinId(String jobinId) {
		this.jobinId = jobinId;
	}

	public String getPmbrCountId() {
		return pmbrCountId;
	}

	public void setPmbrCountId(String pmbrCountId) {
		this.pmbrCountId = pmbrCountId;
	}

	public String getOtherCondition() {
		return otherCondition;
	}

	public void setOtherCondition(String otherCondition) {
		this.otherCondition = otherCondition;
	}
	
	
	
}
