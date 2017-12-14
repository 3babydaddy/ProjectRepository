package com.tf.base.warning.domain;

/**
 * @Title WarningDetail.java
 * @Description 预警明细信息实体类
 * @author wanghw
 * @date 2017年11月24日 下午3:34:16
 * @version V1.0
 */
public class WarningDetail {

	private String warningOrg;

	private String warningMsg;

	private String peopleId;

	private String peopleName;

	private String warningRuleId;

	private String warningTimes;

	public String getWarningOrg() {
		return warningOrg;
	}

	public void setWarningOrg(String warningOrg) {
		this.warningOrg = warningOrg;
	}

	public String getWarningMsg() {
		return warningMsg;
	}

	public void setWarningMsg(String warningMsg) {
		this.warningMsg = warningMsg;
	}

	public String getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}

	public String getPeopleName() {
		return peopleName;
	}

	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}

	public String getWarningRuleId() {
		return warningRuleId;
	}

	public void setWarningRuleId(String warningRuleId) {
		this.warningRuleId = warningRuleId;
	}

	public String getWarningTimes() {
		return warningTimes;
	}

	public void setWarningTimes(String warningTimes) {
		this.warningTimes = warningTimes;
	}

}
