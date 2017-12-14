package com.tf.base.unpublic.domain;

public class QueryPmbrParams extends UnpublicOrgPmbrInfo {

	private String otherCondition;
	
	private String name;
	private String flag;
	private String orgIds;
	private String coverPartyOrgId;

	public String getOtherCondition() {
		return otherCondition;
	}

	public void setOtherCondition(String otherCondition) {
		this.otherCondition = otherCondition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public String getCoverPartyOrgId() {
		return coverPartyOrgId;
	}

	public void setCoverPartyOrgId(String coverPartyOrgId) {
		this.coverPartyOrgId = coverPartyOrgId;
	}
}
