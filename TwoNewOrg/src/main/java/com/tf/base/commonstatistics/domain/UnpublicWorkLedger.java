package com.tf.base.commonstatistics.domain;

import java.util.Date;

import com.tf.base.common.excel.Excel;

public class UnpublicWorkLedger {
	
	@Excel(name = "单位")
	private String createOrg;
	
	@Excel(number = true)
	private int number;
	
	private String belocatedAddress;
	//企业坐落地
	@Excel(name = "园区")
	private String park;
	@Excel(name = "楼宇")
	private String building;
	@Excel(name = "其他")
	private String adOther;

	@Excel(name = "园区级别")
	private String level;

	@Excel(name = "是否为亿元楼宇")
	private String millionBuildingIs;

	//所在园区名称
	private String inparkName;
	//所在商务楼宇名称
	private String buildingName;
	@Excel(name = "所在园区/商务楼宇名称")
	private String inparkBuildName;

	@Excel(name = "企业名称")
	private String name;

	@Excel(name = "企业注册地")
	private String registerAddress;

	@Excel(name = "企业经营地")
	private String operateAddress;
	
	@Excel(name = "联系电话")
	private String contactPhone;
	
	private String industryType;
	//企业类型
	@Excel(name = "私营企业")
	private String privateBusiness;
	@Excel(name = "港澳台商投资企业")
	private String mainlangBusiness;
	@Excel(name = "外商投资企业")
	private String foreignBusiness;
	
	@Excel(name = "年营业收入")
	private String businessVolume;
	
	@Excel(name = "从业人员数量")
	private String jobinTotalnum;
	
	@Excel(name = "是否规模以上企业")
	private String onScaleIs;
	
	@Excel(name = "企业出资人姓名")
	private String sponsorName;
	
	@Excel(name = "是否是党员")
	private String sponsorPartymemberIs;
	
	@Excel(name = "是否兼任党组织书记")
	private String sponsorPartyorgSecretaryIs;
	
	@Excel(name = "是否担任区县级以上（含区县）“两代表一委员”")
	private String sponsorTwodeputyAcommitteeIs;
	
	@Excel(name = "是否成立党组织")
	private String isSetUpPartyOrg;
	
	@Excel(name = "党组织成立时间")
	private Date partyOrgTime;
	
	private String partyOrgForm;
	//党组织组建形式
	@Excel(name = "单独建立")
	private String aloneCreate;
	@Excel(name = "联合建立")
	private String unionCreate;
	@Excel(name = "网格建立")
	private String gridCreate;
	
	private String coverCreate;

	private String partyOrgType;
	//党组织类别
	@Excel(name = "党支部")
	private String partyBranch;
	@Excel(name = "党总支")
	private String generalParty;
	@Excel(name = "党委")
	private String partyCommittee;
	
	private String absencePartyOrgReasion;
	//未建立党组织原因
	@Excel(name = "没有党员")
	private String noParty;
	@Excel(name = "企业出资人不支持")
	private String noSupport;
	@Excel(name = "上级党组织未及时指导")
	private String noGuidance;
	@Excel(name = "其他原因")
	private String partyOther;
	
	@Excel(name = "是否选派党建工作指导员或联络员")
	private String isInstructor;

	@Excel(name = "党组织书记姓名")
	private String secretaryName;
	
	private String secretarySource;
	//书记来源
	@Excel(name = "出资人担任")
	private String contributor;
	@Excel(name = "中层管理人员担任")
	private String midBear;
	@Excel(name = "上级党组织选派")
	private String upBear;
	@Excel(name = "其他人员担任")
	private String bearOther;
	
	@Excel(name = "企业党务工作者人数")
	private String deputySecretaryNum;

	@Excel(name = "专职人数")
	private String deputySecretaryFullNum;
	
	@Excel(name = "组织关系在非公企业的党员")
	private String partymbrInUnpublicNum;
	
	@Excel(name = "35岁以下人数")
	private String partymbrUnderThirtyfiveNum;

	@Excel(name = "中层管理人员人数")
	private String partymbrMiddleManagerNum;
	
	@Excel(name = "中高级专业技术人员人数")
	private String partymbrOnMiddletechNum;
	
	@Excel(name = "生产经营一线职工人数")
	private String partymbrFrontlineNum;

	@Excel(name = "组织关系不在非公企业的党员总数")
	private String partymbrNotinUnpublicNum;
	
	@Excel(name = "农村党员数")
	private String partymbrInVillageNum;
	
	@Excel(name = "近三年新发展党员数")
	private String threeYearsMember;

	@Excel(name = "近三年处置不合格党员数")
	private String threeYearsUnqualifieds;
	
	@Excel(name = "是否对党员轮训一遍")
	private String isTrainingInRotation;
	
	@Excel(name = "党员是否按时足额主动交纳党费")
	private String isPartyMemberTrain;

	@Excel(name = "工会")
	private String hasSociaty;
	
	@Excel(name = "共青团")
	private String hasYouthLeague;
	
	@Excel(name = "妇联")
	private String hasWomenLeague;

	@Excel(name = "是否有单独活动场所")
	private String isOneself;
	
	@Excel(name = "使用面积")
	private String stageArea;
	
	@Excel(name = "党建工作经费是否纳入企业管理费年度预算")
	private String isIntoManage;

	@Excel(name = "是否按规定开展三会一课")
	private String isDevelopListen;
	
	@Excel(name = "是否按规定每年开展民主评议党员")
	private String isDevelopDiscussions;
	
	@Excel(name = "是否按规定每年开展党员党性分析")
	private String isDevelopAnalysis;

	@Excel(name = "党组织是否按规定进行换届")
	private String isChangeEveryyear;

	
	private String partyOrgTimeTxt;

	public String getBelocatedAddress() {
		return belocatedAddress;
	}

	public void setBelocatedAddress(String belocatedAddress) {
		this.belocatedAddress = belocatedAddress;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMillionBuildingIs() {
		return millionBuildingIs;
	}

	public void setMillionBuildingIs(String millionBuildingIs) {
		this.millionBuildingIs = millionBuildingIs;
	}

	public String getInparkName() {
		return inparkName;
	}

	public void setInparkName(String inparkName) {
		this.inparkName = inparkName;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegisterAddress() {
		return registerAddress;
	}

	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}

	public String getOperateAddress() {
		return operateAddress;
	}

	public void setOperateAddress(String operateAddress) {
		this.operateAddress = operateAddress;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public String getBusinessVolume() {
		return businessVolume;
	}

	public void setBusinessVolume(String businessVolume) {
		this.businessVolume = businessVolume;
	}

	public String getJobinTotalnum() {
		return jobinTotalnum;
	}

	public void setJobinTotalnum(String jobinTotalnum) {
		this.jobinTotalnum = jobinTotalnum;
	}

	public String getOnScaleIs() {
		return onScaleIs;
	}

	public void setOnScaleIs(String onScaleIs) {
		this.onScaleIs = onScaleIs;
	}

	public String getSponsorName() {
		return sponsorName;
	}

	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}

	public String getSponsorPartymemberIs() {
		return sponsorPartymemberIs;
	}

	public void setSponsorPartymemberIs(String sponsorPartymemberIs) {
		this.sponsorPartymemberIs = sponsorPartymemberIs;
	}

	public String getSponsorPartyorgSecretaryIs() {
		return sponsorPartyorgSecretaryIs;
	}

	public void setSponsorPartyorgSecretaryIs(String sponsorPartyorgSecretaryIs) {
		this.sponsorPartyorgSecretaryIs = sponsorPartyorgSecretaryIs;
	}

	public String getSponsorTwodeputyAcommitteeIs() {
		return sponsorTwodeputyAcommitteeIs;
	}

	public void setSponsorTwodeputyAcommitteeIs(String sponsorTwodeputyAcommitteeIs) {
		this.sponsorTwodeputyAcommitteeIs = sponsorTwodeputyAcommitteeIs;
	}

	public String getIsSetUpPartyOrg() {
		return isSetUpPartyOrg;
	}

	public void setIsSetUpPartyOrg(String isSetUpPartyOrg) {
		this.isSetUpPartyOrg = isSetUpPartyOrg;
	}

	public Date getPartyOrgTime() {
		return partyOrgTime;
	}

	public void setPartyOrgTime(Date partyOrgTime) {
		this.partyOrgTime = partyOrgTime;
	}

	public String getPartyOrgForm() {
		return partyOrgForm;
	}

	public void setPartyOrgForm(String partyOrgForm) {
		this.partyOrgForm = partyOrgForm;
	}

	public String getPartyOrgType() {
		return partyOrgType;
	}

	public void setPartyOrgType(String partyOrgType) {
		this.partyOrgType = partyOrgType;
	}

	public String getAbsencePartyOrgReasion() {
		return absencePartyOrgReasion;
	}

	public void setAbsencePartyOrgReasion(String absencePartyOrgReasion) {
		this.absencePartyOrgReasion = absencePartyOrgReasion;
	}

	public String getIsInstructor() {
		return isInstructor;
	}

	public void setIsInstructor(String isInstructor) {
		this.isInstructor = isInstructor;
	}

	public String getSecretaryName() {
		return secretaryName;
	}

	public void setSecretaryName(String secretaryName) {
		this.secretaryName = secretaryName;
	}

	public String getSecretarySource() {
		return secretarySource;
	}

	public void setSecretarySource(String secretarySource) {
		this.secretarySource = secretarySource;
	}

	public String getDeputySecretaryNum() {
		return deputySecretaryNum;
	}

	public void setDeputySecretaryNum(String deputySecretaryNum) {
		this.deputySecretaryNum = deputySecretaryNum;
	}

	public String getDeputySecretaryFullNum() {
		return deputySecretaryFullNum;
	}

	public void setDeputySecretaryFullNum(String deputySecretaryFullNum) {
		this.deputySecretaryFullNum = deputySecretaryFullNum;
	}

	public String getPartymbrInUnpublicNum() {
		return partymbrInUnpublicNum;
	}

	public void setPartymbrInUnpublicNum(String partymbrInUnpublicNum) {
		this.partymbrInUnpublicNum = partymbrInUnpublicNum;
	}

	public String getPartymbrUnderThirtyfiveNum() {
		return partymbrUnderThirtyfiveNum;
	}

	public void setPartymbrUnderThirtyfiveNum(String partymbrUnderThirtyfiveNum) {
		this.partymbrUnderThirtyfiveNum = partymbrUnderThirtyfiveNum;
	}

	public String getPartymbrMiddleManagerNum() {
		return partymbrMiddleManagerNum;
	}

	public void setPartymbrMiddleManagerNum(String partymbrMiddleManagerNum) {
		this.partymbrMiddleManagerNum = partymbrMiddleManagerNum;
	}

	public String getPartymbrOnMiddletechNum() {
		return partymbrOnMiddletechNum;
	}

	public void setPartymbrOnMiddletechNum(String partymbrOnMiddletechNum) {
		this.partymbrOnMiddletechNum = partymbrOnMiddletechNum;
	}

	public String getPartymbrFrontlineNum() {
		return partymbrFrontlineNum;
	}

	public void setPartymbrFrontlineNum(String partymbrFrontlineNum) {
		this.partymbrFrontlineNum = partymbrFrontlineNum;
	}

	public String getPartymbrNotinUnpublicNum() {
		return partymbrNotinUnpublicNum;
	}

	public void setPartymbrNotinUnpublicNum(String partymbrNotinUnpublicNum) {
		this.partymbrNotinUnpublicNum = partymbrNotinUnpublicNum;
	}

	public String getPartymbrInVillageNum() {
		return partymbrInVillageNum;
	}

	public void setPartymbrInVillageNum(String partymbrInVillageNum) {
		this.partymbrInVillageNum = partymbrInVillageNum;
	}

	public String getThreeYearsMember() {
		return threeYearsMember;
	}

	public void setThreeYearsMember(String threeYearsMember) {
		this.threeYearsMember = threeYearsMember;
	}

	public String getThreeYearsUnqualifieds() {
		return threeYearsUnqualifieds;
	}

	public void setThreeYearsUnqualifieds(String threeYearsUnqualifieds) {
		this.threeYearsUnqualifieds = threeYearsUnqualifieds;
	}

	public String getIsTrainingInRotation() {
		return isTrainingInRotation;
	}

	public void setIsTrainingInRotation(String isTrainingInRotation) {
		this.isTrainingInRotation = isTrainingInRotation;
	}

	public String getIsPartyMemberTrain() {
		return isPartyMemberTrain;
	}

	public void setIsPartyMemberTrain(String isPartyMemberTrain) {
		this.isPartyMemberTrain = isPartyMemberTrain;
	}

	public String getHasSociaty() {
		return hasSociaty;
	}

	public void setHasSociaty(String hasSociaty) {
		this.hasSociaty = hasSociaty;
	}

	public String getHasYouthLeague() {
		return hasYouthLeague;
	}

	public void setHasYouthLeague(String hasYouthLeague) {
		this.hasYouthLeague = hasYouthLeague;
	}

	public String getHasWomenLeague() {
		return hasWomenLeague;
	}

	public void setHasWomenLeague(String hasWomenLeague) {
		this.hasWomenLeague = hasWomenLeague;
	}

	public String getIsOneself() {
		return isOneself;
	}

	public void setIsOneself(String isOneself) {
		this.isOneself = isOneself;
	}

	public String getStageArea() {
		return stageArea;
	}

	public void setStageArea(String stageArea) {
		this.stageArea = stageArea;
	}

	public String getIsIntoManage() {
		return isIntoManage;
	}

	public void setIsIntoManage(String isIntoManage) {
		this.isIntoManage = isIntoManage;
	}

	public String getIsDevelopListen() {
		return isDevelopListen;
	}

	public void setIsDevelopListen(String isDevelopListen) {
		this.isDevelopListen = isDevelopListen;
	}

	public String getIsDevelopDiscussions() {
		return isDevelopDiscussions;
	}

	public void setIsDevelopDiscussions(String isDevelopDiscussions) {
		this.isDevelopDiscussions = isDevelopDiscussions;
	}

	public String getIsDevelopAnalysis() {
		return isDevelopAnalysis;
	}

	public void setIsDevelopAnalysis(String isDevelopAnalysis) {
		this.isDevelopAnalysis = isDevelopAnalysis;
	}

	public String getIsChangeEveryyear() {
		return isChangeEveryyear;
	}

	public void setIsChangeEveryyear(String isChangeEveryyear) {
		this.isChangeEveryyear = isChangeEveryyear;
	}

	public String getCreateOrg() {
		return createOrg;
	}

	public void setCreateOrg(String createOrg) {
		this.createOrg = createOrg;
	}

	public String getPartyOrgTimeTxt() {
		return partyOrgTimeTxt;
	}

	public void setPartyOrgTimeTxt(String partyOrgTimeTxt) {
		this.partyOrgTimeTxt = partyOrgTimeTxt;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getAdOther() {
		return adOther;
	}

	public void setAdOther(String adOther) {
		this.adOther = adOther;
	}

	public String getPrivateBusiness() {
		return privateBusiness;
	}

	public void setPrivateBusiness(String privateBusiness) {
		this.privateBusiness = privateBusiness;
	}

	public String getMainlangBusiness() {
		return mainlangBusiness;
	}

	public void setMainlangBusiness(String mainlangBusiness) {
		this.mainlangBusiness = mainlangBusiness;
	}

	public String getForeignBusiness() {
		return foreignBusiness;
	}

	public void setForeignBusiness(String foreignBusiness) {
		this.foreignBusiness = foreignBusiness;
	}

	public String getAloneCreate() {
		return aloneCreate;
	}

	public void setAloneCreate(String aloneCreate) {
		this.aloneCreate = aloneCreate;
	}

	public String getUnionCreate() {
		return unionCreate;
	}

	public void setUnionCreate(String unionCreate) {
		this.unionCreate = unionCreate;
	}

	public String getGridCreate() {
		return gridCreate;
	}

	public void setGridCreate(String gridCreate) {
		this.gridCreate = gridCreate;
	}

	public String getCoverCreate() {
		return coverCreate;
	}

	public void setCoverCreate(String coverCreate) {
		this.coverCreate = coverCreate;
	}

	public String getPartyBranch() {
		return partyBranch;
	}

	public void setPartyBranch(String partyBranch) {
		this.partyBranch = partyBranch;
	}

	public String getGeneralParty() {
		return generalParty;
	}

	public void setGeneralParty(String generalParty) {
		this.generalParty = generalParty;
	}

	public String getPartyCommittee() {
		return partyCommittee;
	}

	public void setPartyCommittee(String partyCommittee) {
		this.partyCommittee = partyCommittee;
	}

	public String getNoParty() {
		return noParty;
	}

	public void setNoParty(String noParty) {
		this.noParty = noParty;
	}

	public String getNoSupport() {
		return noSupport;
	}

	public void setNoSupport(String noSupport) {
		this.noSupport = noSupport;
	}

	public String getNoGuidance() {
		return noGuidance;
	}

	public void setNoGuidance(String noGuidance) {
		this.noGuidance = noGuidance;
	}

	public String getPartyOther() {
		return partyOther;
	}

	public void setPartyOther(String partyOther) {
		this.partyOther = partyOther;
	}

	public String getContributor() {
		return contributor;
	}

	public void setContributor(String contributor) {
		this.contributor = contributor;
	}

	public String getMidBear() {
		return midBear;
	}

	public void setMidBear(String midBear) {
		this.midBear = midBear;
	}

	public String getUpBear() {
		return upBear;
	}

	public void setUpBear(String upBear) {
		this.upBear = upBear;
	}

	public String getBearOther() {
		return bearOther;
	}

	public void setBearOther(String bearOther) {
		this.bearOther = bearOther;
	}

	public String getInparkBuildName() {
		return inparkBuildName;
	}

	public void setInparkBuildName(String inparkBuildName) {
		this.inparkBuildName = inparkBuildName;
	}
	
	
}
