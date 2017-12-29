package com.tf.base.unpublic.domain;

import com.tf.base.common.constants.CommonConstants;
import com.tf.base.common.excel.Excel;

public class UnpublicOrgExportBean {

	/**
	 * 企业名称
	 */
	@Excel(name = "企业名称")
	private String name;

	/**
	 * 企业类型
	 */
	@Excel(name = "企业类型", dictCode = CommonConstants.ENTERPRISE_TYPE)
	private String industryType;

	/**
	 * 国家
	 */
	@Excel(name = "国家", dictCode = CommonConstants.NATIONALITY)
	private String nationality;

	/**
	 * 行业类型
	 */
	@Excel(name = "行业类型", dictCode = CommonConstants.BUSINESS_TYPE)
	private String businessType;

	@Excel(name = "联系方式")
	private String contactPhone;

	/**
	 * 企业坐落地
	 */
	@Excel(name = "企业坐落地")
	private String belocatedAddress;

	/**
	 * 园区级别
	 */
	@Excel(name = "园区等级",dictCode=CommonConstants.ZONE_LEVEL)
	private String level;

	/**
	 * 所在园区名称
	 */
	@Excel(name = "所在园区名称")
	private String inparkName;

	/**
	 * 是否为亿元楼宇
	 */
	@Excel(name = "是否为亿元楼宇", dictCode = CommonConstants.YES_NO)
	private String millionBuildingIs;

	/**
	 * 所在商务楼宇名称
	 */
	@Excel(name = "所在商务楼宇名称")
	private String buildingName;

	/**
	 * 状态 1.临时保存 2.正常, 4.已撤销 0.已删除
	 */
	@Excel(name = "状态", dictCode = CommonConstants.UNPUBLIC_ORG_STATUS)
	private String status;

	/**
	 * 年营业收入
	 */
	@Excel(name = "年营业收入")
	private float businessVolume;

	/**
	 * 从业人员数量
	 */
	@Excel(name = "从业人员数量")
	private Integer jobinTotalnum;

	/**
	 * 是否规模以上企业
	 */
	@Excel(name = "是否规模以上企业", dictCode = CommonConstants.YES_NO)
	private String onScaleIs;

	/**
	 * 企业注册地
	 */
	@Excel(name = "企业注册地")
	private String registerAddress;

	/**
	 * 企业注册地:地区等级
	 */
	@Excel(name = "企业注册地")
	private String registerAddressLevel;

	/**
	 * 注册地-省
	 */
	@Excel(name = "注册地省")
	private String registerAddressProvince;

	/**
	 * 注册地-市
	 */
	@Excel(name = "注册地市")
	private String registerAddressCity;

	/**
	 * 注册地-区
	 */
	@Excel(name = "注册地区")
	private String registerAddressDistrict;

	/**
	 * 注册地-街道
	 */
	@Excel(name = "注册地街道")
	private String registerAddressStreet;

	/**
	 * 是否存在企业经营地
	 */
	@Excel(name = "是否存在企业经营地", dictCode = CommonConstants.YES_NO)
	private String isHaveAddress;

	/**
	 * 企业经营地
	 */
	@Excel(name = "企业经营地")
	private String operateAddress;

	/**
	 * 企业出资人姓名
	 */
	@Excel(name = "企业出资人姓名")
	private String sponsorName;

	/**
	 * 企业出资人是否党员
	 */
	@Excel(name = "是否党员", dictCode = CommonConstants.YES_NO)
	private String sponsorPartymemberIs;

	/**
	 * 企业出资人是否兼任党组织书记
	 */
	@Excel(name = "是否兼任党组织书记", dictCode = CommonConstants.YES_NO)
	private String sponsorPartyorgSecretaryIs;

	/**
	 * 企业出资人是否担任区县级以上（含区县）“两代表一委员
	 */
	@Excel(name = "是否担任区县级以上（含区县）“两代表一委员", dictCode = CommonConstants.YES_NO)
	private String sponsorTwodeputyAcommitteeIs;

	/**
	 * 企业出资人担任区县级以上（含区县）“两代表一委员类型
	 */
	@Excel(name = "担任区县级以上（含区县）“两代表一委员类型")
	private String sponsorTwodeputyAcommitteeType;

	/**
	 * 类型为其他时，录入的内容
	 */
	@Excel(name = "类型为其他时")
	private String sponsorTwodeputyAcommitteeTypeOther;

	/**
	 * 工会 1.有 0 无
	 */
	@Excel(name = "工会", dictCode = CommonConstants.HAS_NOT)
	private String hasSociaty;

	/**
	 * 共青团 1 有 0.无
	 */
	@Excel(name = "共青团", dictCode = CommonConstants.HAS_NOT)
	private String hasYouthLeague;

	/**
	 * 妇联 1.有 0.无
	 */
	@Excel(name = "妇联", dictCode = CommonConstants.HAS_NOT)
	private String hasWomenLeague;

}
