package com.tf.base.warning.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Title: WarningRuleInfo.java
 * @Description: 预警规则实体类
 * @author wanghw
 * @date 2017年11月17日 下午1:55:33
 * @version V1.0
 */
@Table(name = "warning_rules_config_info")
public class WarningRuleInfo {

	/**
	 * 主键
	 */
	@Id
	@Column(name = "warning_rules_config_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer warningRulesConfigId;

	/**
	 * 预警名称(事由)
	 */
	@Column(name = "warning_title")
	private String warningTitle;

	/**
	 * 预警规则描述
	 */
	@Column(name = "warning_rules_content")
	private String warningContent;

	/**
	 * 提醒信息
	 */
	@Column(name = "warning_msg")
	private String warningMsg;

	/**
	 * 预警周期
	 */
	@Column(name = "warning_cycle")
	private String warningCycle;

	/**
	 * 预警开关 1.开启 0.关闭
	 */
	@Column(name = "warning_switch")
	private String warningSwitch;

	/**
	 * 预警类型 01.党组织建立预警 02党组织换届预警 03.党员情况变更预警 04. 党组织变动预警 05.手工发布
	 */
	@Column(name = "warning_type")
	private String warningType;
	
	/**
	 * 未及时更新超时天数
	 */
	@Column(name="over_time")
	private Integer overTime;

	/**
	 * 涉及接收提醒人 1.工作人员 2.主管领导 3- 一把手
	 */
	@Column(name="warning_peoples_types")
	private String warningPeoples;
	
	/**
	 * 预期开始时间
	 */
	@Column(name="expect_start_time")
	private Date expectStartTime;
	
	/**
	 * 预期结束时间
	 */
	@Column(name="expect_end_time")
	private Date expectEndTime;
	
	/**
	 * 实际开始时间
	 */
	@Column(name="fact_start_time")
	private Date factStartTime;
	
	/**
	 * 实际结束时间
	 */
	@Column(name="fact_end_time")
	private Date factEndTime;

	/**
	 * 规则创建时间
	 */
	@Column(name="create_time")
	private Date createTime;

	/**
	 * 规则创建人
	 */
	@Column(name="creator")
	private String creator;

	/**
	 * 状态 1-有效 0-无效
	 */
	@Column(name="status")
	private String status;

	public Integer getWarningRulesConfigId() {
		return warningRulesConfigId;
	}

	public void setWarningRulesConfigId(Integer warningRulesConfigId) {
		this.warningRulesConfigId = warningRulesConfigId;
	}

	public String getWarningTitle() {
		return warningTitle;
	}

	public void setWarningTitle(String warningTitle) {
		this.warningTitle = warningTitle;
	}

	public String getWarningContent() {
		return warningContent;
	}

	public void setWarningContent(String warningContent) {
		this.warningContent = warningContent;
	}

	public String getWarningMsg() {
		return warningMsg;
	}

	public void setWarningMsg(String warningMsg) {
		this.warningMsg = warningMsg;
	}

	public String getWarningCycle() {
		return warningCycle;
	}

	public void setWarningCycle(String warningCycle) {
		this.warningCycle = warningCycle;
	}

	public String getWarningSwitch() {
		return warningSwitch;
	}

	public void setWarningSwitch(String warningSwitch) {
		this.warningSwitch = warningSwitch;
	}

	public String getWarningType() {
		return warningType;
	}

	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}

	public Integer getOverTime() {
		return overTime;
	}

	public void setOverTime(Integer overTime) {
		this.overTime = overTime;
	}

	public String getWarningPeoples() {
		return warningPeoples;
	}

	public void setWarningPeoples(String warningPeoples) {
		this.warningPeoples = warningPeoples;
	}

	public Date getExpectStartTime() {
		return expectStartTime;
	}

	public void setExpectStartTime(Date expectStartTime) {
		this.expectStartTime = expectStartTime;
	}

	public Date getExpectEndTime() {
		return expectEndTime;
	}

	public void setExpectEndTime(Date expectEndTime) {
		this.expectEndTime = expectEndTime;
	}

	public Date getFactStartTime() {
		return factStartTime;
	}

	public void setFactStartTime(Date factStartTime) {
		this.factStartTime = factStartTime;
	}

	public Date getFactEndTime() {
		return factEndTime;
	}

	public void setFactEndTime(Date factEndTime) {
		this.factEndTime = factEndTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "WarningRuleInfo [warningRulesConfigId=" + warningRulesConfigId + ", warningTitle=" + warningTitle
				+ ", warningContent=" + warningContent + ", warningMsg=" + warningMsg + ", warningCycle=" + warningCycle
				+ ", warningSwitch=" + warningSwitch + ", warningType=" + warningType + ", overTime=" + overTime
				+ ", warningPeoples=" + warningPeoples + ", expectStartTime=" + expectStartTime + ", expectEndTime="
				+ expectEndTime + ", factStartTime=" + factStartTime + ", factEndTime=" + factEndTime + ", createTime="
				+ createTime + ", creator=" + creator + ", status=" + status + "]";
	}

	

}
