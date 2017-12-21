package com.tf.base.commonstatistics.domain;

import java.util.Date;

import com.tf.base.common.excel.Excel;

public class SocialWorkLedger {

	@Excel(number = true)
	private int number;
	
	@Excel(name = "社会组织名称")
	private String name;
	
	@Excel(name = "社会组织性质")
	private String nature;
	
	@Excel(name = "社会组织类别")
	private String category;
	
	@Excel(name = "登记机构")
	private String registerOrg;
	
	@Excel(name = "业务主管单位")
	private String businessDirectorOrg;
	
	@Excel(name = "住地")
	private String address;
	
	private String annualSurvey;
	//年检结果
	@Excel(name = "合格")
	private String qualified;
	@Excel(name = "基本合格")
	private String basicQualified;
	@Excel(name = "不合格")
	private String noQualified;
	@Excel(name = "未参检")
	private String noJoin;
	
	@Excel(name = "从业人员总数")
	private String jobinTotalnum;
	
	@Excel(name = "专职人数")
	private String jobinMajorNum;
	
	@Excel(name = "兼职人数")
	private String jobinPluralityNum;
	
	@Excel(name = "从业中共党员数")
	private String jobinPartymemberNum;
	
	@Excel(name = "负责人是否党员")
	private String chargePartymemberIs;
	
	@Excel(name = "负责人是否兼任党组织书记")
	private String chargePartyorgSecretaryIs;
	
	@Excel(name = "负责人是否担任区县级以上（含区县）“两代表一委员”")
	private String chargeTwodeputyAcommitteeIs;
	
	@Excel(name = "是否成立党组织")
	private String isSetUpPartyOrg;
	
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
	
	@Excel(name = "党组织成立时间")
	private Date partyOrgTime;
	
	@Excel(name = "上次换届时间")
	private String partyChangeTime;
	
	@Excel(name = "党组织名称")
	private String partyOrgName;
	
	@Excel(name = "党组织联系电话")
	private String partyOrgTel;
	
	private String partyOrgForm;
	//党组织组建形式
	@Excel(name = "在会员中建")
	private String aloneCreate;
	@Excel(name = "在理事会中建")
	private String unionCreate;
	@Excel(name = "在业务主管部门联合建")
	private String gridCreate;
	@Excel(name = "其他")
	private String coverCreate;
	
	@Excel(name = "是否选派党建工作指导员或联络员")
	private String isInstructor;
	
	private String partyOrgType;
	//党组织类别
	@Excel(name = "党支部")
	private String partyBranch;
	@Excel(name = "党总支")
	private String generalParty;
	@Excel(name = "党委")
	private String partyCommittee;
	@Excel(name = "联合党支部")
	private String partyGridCommittee;
	
	@Excel(name = "联合党支部覆盖社会组织数量(个)")
	private String coverOrgNum;
	
	@Excel(name = "上级党组织名称")
	private String subjectionPartyName;
	
	@Excel(name = "书记名称")
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
	
	@Excel(name = "党务副书记人数")
	private String deputySecretaryNum;
	
	@Excel(name = "党务副书记专职人数")
	private String deputySecretaryFullNum;
	
	@Excel(name = "党务副书记兼职人数")
	private String deputySecretaryPartNum;
	
	@Excel(name = "其它党务工作者人数")
	private String deputySecretaryOtherNum;
	
	@Excel(name = "其他党务工作者专职")
	private String fullTimeWorkers;
	
	@Excel(name = "其他党务工作者兼职")
	private String partTimeWorkers;
	
	@Excel(name = "党务工作者是否参加1次以上培训")
	private String isPartyMemberTrain;
	
	@Excel(name = "纳入社会组织党组织管理的党员总数")
	private String partymbrInSocielorgNum;
	
	@Excel(name = "组织关系在社会组织党组织的党员数")
	private String partymbrGroupInSocielorgNum;
	
	@Excel(name = "35岁以下")
	private String partymbrUnderThirtyfiveNum;
	
	@Excel(name = "大学及以上学历")
	private String partymbrOnCollegeNum;
	
	@Excel(name = "高中及以下学历")
	private String partymbrUnderHighschoolNum;
	
	@Excel(name = "近三年新发展党员数")
	private String threeYearsMember;
	
	@Excel(name = "近3年处置不合格党员数（人）")
	private String threeYearsUnqualifieds;
	
	@Excel(name = "是否每年对党员轮训一遍")
	private String isTrainingInRotation;

	@Excel(name = "平均每年党建工作经费总额（元）")
	private String totalPayDues;
	
	@Excel(name = "市区财政支出数额（元）")
	private String cityFunds;
	
	@Excel(name = "区管党费支持数额（元）")
	private String districtFunds;
	
	@Excel(name = "社会组织管理经费支持数额（元）")
	private String socialOrgFunds;
	
	@Excel(name = "其他来源")
	private String otherContent;
	
	@Excel(name = "单独建设活动场所面积")
	private String stageArea;
	
	@Excel(name = "与其他单位党组织共用活动场所")
	private String othersShareStage;
	
	@Excel(name = "与社区共用活动场所")
	private String communityShareStage;
	
	@Excel(name = "其他场所")
	private String otherInfo;
	
	@Excel(name = "每季度召开党员大会（次）")
	private String quarterPartyMeetingTimes;
	
	@Excel(name = "每月召开支部委员会（次）")
	private String monthPartyMeetingTimes;
	
	@Excel(name = "是否每月召开1—2次党小组会")
	private String partyMeetingMonth;
	
	@Excel(name = "每年组织党员听党课（次）")
	private String listenPartyClass;
	
	@Excel(name = "每年召开组织生活会的次数")
	private String lifeMeetingTimes;
	
	@Excel(name = "每年开展民主评议党员的党支部数")
	private String developPartyBranchNo;
	
	@Excel(name = "上年度党员交纳党费总额（元）")
	private String yearTotalPayDues;
	
	@Excel(name = "是否将党组织建设有关内容纳入学校章程")
	private String isPartyIntoSchool;
	
	private String parentPartyOrgType;
	//党组织所隶属上级党组织
	@Excel(name = "区教育局党组织")
	private String eduPartOrg;
	@Excel(name = "区人力资源社会保障局党组织")
	private String socialPartyOrg;
	@Excel(name = "区社会组织党委")
	private String socialParty;
	@Excel(name = "乡镇(街道)党组织")
	private String townParyOrg;
	@Excel(name = "社区党组织")
	private String communityPartyOrg;
	@Excel(name = "其他")
	private String otherPartyOrg;
	
	@Excel(name = "书记名称")
	private String secretaryName1;
	
	//书记来源
	@Excel(name = "由校长兼任")
	private String prinConcurrently ;
	@Excel(name = "中层管理人员担任")
	private String midBear1;
	@Excel(name = "出资人担任")
	private String contributor1;
	@Excel(name = "上级党组织选派")
	private String upBear1;
	
	@Excel(name = "是否为理事会成员")
	private String isBoardOfficer;
	
	@Excel(name = "是否配备专职副书记")
	private String deputySecretaryFullIs;
	
	@Excel(name = "副书记是否为理事会成员")
	private String deputySecreraryNumberIs;
	
	@Excel(name = "其它委员担任校长")
	private String schoolmasterHold;
	
	@Excel(name = "其它委员担任副校长")
	private String deputySchoolmasterHold;
	
	@Excel(name = "其它委员担任其它管理层职务")
	private String otherMessageHold;
	
	@Excel(name = "在学校从事专职工作半年以上未转入组织关系的")
	private String schoolGroupNotIn;
	
	@Excel(name = "是否设立思想政治教育工作机构")
	private String isIdeologicalPoliticalOrg;
	
	@Excel(name = "是否设立德育工作机构")
	private String isMoralEducationOrg;
	
	@Excel(name = "党建工作经费是否列入学校年度经费预算")
	private String isIntoManage;

	
	private String partyOrgTimeTxt;
	
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

	public String getAnnualSurvey() {
		return annualSurvey;
	}

	public void setAnnualSurvey(String annualSurvey) {
		this.annualSurvey = annualSurvey;
	}

	public String getJobinTotalnum() {
		return jobinTotalnum;
	}

	public void setJobinTotalnum(String jobinTotalnum) {
		this.jobinTotalnum = jobinTotalnum;
	}

	public String getJobinMajorNum() {
		return jobinMajorNum;
	}

	public void setJobinMajorNum(String jobinMajorNum) {
		this.jobinMajorNum = jobinMajorNum;
	}

	public String getJobinPluralityNum() {
		return jobinPluralityNum;
	}

	public void setJobinPluralityNum(String jobinPluralityNum) {
		this.jobinPluralityNum = jobinPluralityNum;
	}

	public String getJobinPartymemberNum() {
		return jobinPartymemberNum;
	}

	public void setJobinPartymemberNum(String jobinPartymemberNum) {
		this.jobinPartymemberNum = jobinPartymemberNum;
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

	public String getIsSetUpPartyOrg() {
		return isSetUpPartyOrg;
	}

	public void setIsSetUpPartyOrg(String isSetUpPartyOrg) {
		this.isSetUpPartyOrg = isSetUpPartyOrg;
	}

	public String getAbsencePartyOrgReasion() {
		return absencePartyOrgReasion;
	}

	public void setAbsencePartyOrgReasion(String absencePartyOrgReasion) {
		this.absencePartyOrgReasion = absencePartyOrgReasion;
	}

	public Date getPartyOrgTime() {
		return partyOrgTime;
	}

	public void setPartyOrgTime(Date partyOrgTime) {
		this.partyOrgTime = partyOrgTime;
	}

	public String getCoverOrgNum() {
		return coverOrgNum;
	}

	public void setCoverOrgNum(String coverOrgNum) {
		this.coverOrgNum = coverOrgNum;
	}

	public String getPartyOrgName() {
		return partyOrgName;
	}

	public void setPartyOrgName(String partyOrgName) {
		this.partyOrgName = partyOrgName;
	}

	public String getPartyOrgTel() {
		return partyOrgTel;
	}

	public void setPartyOrgTel(String partyOrgTel) {
		this.partyOrgTel = partyOrgTel;
	}

	public String getPartyOrgForm() {
		return partyOrgForm;
	}

	public void setPartyOrgForm(String partyOrgForm) {
		this.partyOrgForm = partyOrgForm;
	}

	public String getIsInstructor() {
		return isInstructor;
	}

	public void setIsInstructor(String isInstructor) {
		this.isInstructor = isInstructor;
	}

	public String getPartyOrgType() {
		return partyOrgType;
	}

	public void setPartyOrgType(String partyOrgType) {
		this.partyOrgType = partyOrgType;
	}

	public String getSubjectionPartyName() {
		return subjectionPartyName;
	}

	public void setSubjectionPartyName(String subjectionPartyName) {
		this.subjectionPartyName = subjectionPartyName;
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

	public String getDeputySecretaryPartNum() {
		return deputySecretaryPartNum;
	}

	public void setDeputySecretaryPartNum(String deputySecretaryPartNum) {
		this.deputySecretaryPartNum = deputySecretaryPartNum;
	}

	public String getDeputySecretaryOtherNum() {
		return deputySecretaryOtherNum;
	}

	public void setDeputySecretaryOtherNum(String deputySecretaryOtherNum) {
		this.deputySecretaryOtherNum = deputySecretaryOtherNum;
	}

	public String getPartTimeWorkers() {
		return partTimeWorkers;
	}

	public void setPartTimeWorkers(String partTimeWorkers) {
		this.partTimeWorkers = partTimeWorkers;
	}

	public String getFullTimeWorkers() {
		return fullTimeWorkers;
	}

	public void setFullTimeWorkers(String fullTimeWorkers) {
		this.fullTimeWorkers = fullTimeWorkers;
	}

	public String getIsPartyMemberTrain() {
		return isPartyMemberTrain;
	}

	public void setIsPartyMemberTrain(String isPartyMemberTrain) {
		this.isPartyMemberTrain = isPartyMemberTrain;
	}

	public String getPartymbrInSocielorgNum() {
		return partymbrInSocielorgNum;
	}

	public void setPartymbrInSocielorgNum(String partymbrInSocielorgNum) {
		this.partymbrInSocielorgNum = partymbrInSocielorgNum;
	}

	public String getPartymbrGroupInSocielorgNum() {
		return partymbrGroupInSocielorgNum;
	}

	public void setPartymbrGroupInSocielorgNum(String partymbrGroupInSocielorgNum) {
		this.partymbrGroupInSocielorgNum = partymbrGroupInSocielorgNum;
	}

	public String getPartymbrUnderThirtyfiveNum() {
		return partymbrUnderThirtyfiveNum;
	}

	public void setPartymbrUnderThirtyfiveNum(String partymbrUnderThirtyfiveNum) {
		this.partymbrUnderThirtyfiveNum = partymbrUnderThirtyfiveNum;
	}

	public String getPartymbrOnCollegeNum() {
		return partymbrOnCollegeNum;
	}

	public void setPartymbrOnCollegeNum(String partymbrOnCollegeNum) {
		this.partymbrOnCollegeNum = partymbrOnCollegeNum;
	}

	public String getPartymbrUnderHighschoolNum() {
		return partymbrUnderHighschoolNum;
	}

	public void setPartymbrUnderHighschoolNum(String partymbrUnderHighschoolNum) {
		this.partymbrUnderHighschoolNum = partymbrUnderHighschoolNum;
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

	public String getTotalPayDues() {
		return totalPayDues;
	}

	public void setTotalPayDues(String totalPayDues) {
		this.totalPayDues = totalPayDues;
	}

	public String getCityFunds() {
		return cityFunds;
	}

	public void setCityFunds(String cityFunds) {
		this.cityFunds = cityFunds;
	}

	public String getDistrictFunds() {
		return districtFunds;
	}

	public void setDistrictFunds(String districtFunds) {
		this.districtFunds = districtFunds;
	}

	public String getSocialOrgFunds() {
		return socialOrgFunds;
	}

	public void setSocialOrgFunds(String socialOrgFunds) {
		this.socialOrgFunds = socialOrgFunds;
	}

	public String getOtherContent() {
		return otherContent;
	}

	public void setOtherContent(String otherContent) {
		this.otherContent = otherContent;
	}

	public String getStageArea() {
		return stageArea;
	}

	public void setStageArea(String stageArea) {
		this.stageArea = stageArea;
	}

	public String getOthersShareStage() {
		return othersShareStage;
	}

	public void setOthersShareStage(String othersShareStage) {
		this.othersShareStage = othersShareStage;
	}

	public String getCommunityShareStage() {
		return communityShareStage;
	}

	public void setCommunityShareStage(String communityShareStage) {
		this.communityShareStage = communityShareStage;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getQuarterPartyMeetingTimes() {
		return quarterPartyMeetingTimes;
	}

	public void setQuarterPartyMeetingTimes(String quarterPartyMeetingTimes) {
		this.quarterPartyMeetingTimes = quarterPartyMeetingTimes;
	}

	public String getMonthPartyMeetingTimes() {
		return monthPartyMeetingTimes;
	}

	public void setMonthPartyMeetingTimes(String monthPartyMeetingTimes) {
		this.monthPartyMeetingTimes = monthPartyMeetingTimes;
	}

	public String getPartyMeetingMonth() {
		return partyMeetingMonth;
	}

	public void setPartyMeetingMonth(String partyMeetingMonth) {
		this.partyMeetingMonth = partyMeetingMonth;
	}

	public String getListenPartyClass() {
		return listenPartyClass;
	}

	public void setListenPartyClass(String listenPartyClass) {
		this.listenPartyClass = listenPartyClass;
	}

	public String getLifeMeetingTimes() {
		return lifeMeetingTimes;
	}

	public void setLifeMeetingTimes(String lifeMeetingTimes) {
		this.lifeMeetingTimes = lifeMeetingTimes;
	}

	public String getDevelopPartyBranchNo() {
		return developPartyBranchNo;
	}

	public void setDevelopPartyBranchNo(String developPartyBranchNo) {
		this.developPartyBranchNo = developPartyBranchNo;
	}

	public String getYearTotalPayDues() {
		return yearTotalPayDues;
	}

	public void setYearTotalPayDues(String yearTotalPayDues) {
		this.yearTotalPayDues = yearTotalPayDues;
	}

	public String getIsPartyIntoSchool() {
		return isPartyIntoSchool;
	}

	public void setIsPartyIntoSchool(String isPartyIntoSchool) {
		this.isPartyIntoSchool = isPartyIntoSchool;
	}

	public String getParentPartyOrgType() {
		return parentPartyOrgType;
	}

	public void setParentPartyOrgType(String parentPartyOrgType) {
		this.parentPartyOrgType = parentPartyOrgType;
	}

	public String getIsBoardOfficer() {
		return isBoardOfficer;
	}

	public void setIsBoardOfficer(String isBoardOfficer) {
		this.isBoardOfficer = isBoardOfficer;
	}

	public String getDeputySecretaryFullIs() {
		return deputySecretaryFullIs;
	}

	public void setDeputySecretaryFullIs(String deputySecretaryFullIs) {
		this.deputySecretaryFullIs = deputySecretaryFullIs;
	}

	public String getDeputySecreraryNumberIs() {
		return deputySecreraryNumberIs;
	}

	public void setDeputySecreraryNumberIs(String deputySecreraryNumberIs) {
		this.deputySecreraryNumberIs = deputySecreraryNumberIs;
	}

	public String getSchoolmasterHold() {
		return schoolmasterHold;
	}

	public void setSchoolmasterHold(String schoolmasterHold) {
		this.schoolmasterHold = schoolmasterHold;
	}

	public String getDeputySchoolmasterHold() {
		return deputySchoolmasterHold;
	}

	public void setDeputySchoolmasterHold(String deputySchoolmasterHold) {
		this.deputySchoolmasterHold = deputySchoolmasterHold;
	}

	public String getOtherMessageHold() {
		return otherMessageHold;
	}

	public void setOtherMessageHold(String otherMessageHold) {
		this.otherMessageHold = otherMessageHold;
	}

	public String getSchoolGroupNotIn() {
		return schoolGroupNotIn;
	}

	public void setSchoolGroupNotIn(String schoolGroupNotIn) {
		this.schoolGroupNotIn = schoolGroupNotIn;
	}

	public String getIsIdeologicalPoliticalOrg() {
		return isIdeologicalPoliticalOrg;
	}

	public void setIsIdeologicalPoliticalOrg(String isIdeologicalPoliticalOrg) {
		this.isIdeologicalPoliticalOrg = isIdeologicalPoliticalOrg;
	}

	public String getIsMoralEducationOrg() {
		return isMoralEducationOrg;
	}

	public void setIsMoralEducationOrg(String isMoralEducationOrg) {
		this.isMoralEducationOrg = isMoralEducationOrg;
	}

	public String getIsIntoManage() {
		return isIntoManage;
	}

	public void setIsIntoManage(String isIntoManage) {
		this.isIntoManage = isIntoManage;
	}

	public String getPartyOrgTimeTxt() {
		return partyOrgTimeTxt;
	}

	public void setPartyOrgTimeTxt(String partyOrgTimeTxt) {
		this.partyOrgTimeTxt = partyOrgTimeTxt;
	}

	public String getQualified() {
		return qualified;
	}

	public void setQualified(String qualified) {
		this.qualified = qualified;
	}

	public String getBasicQualified() {
		return basicQualified;
	}

	public void setBasicQualified(String basicQualified) {
		this.basicQualified = basicQualified;
	}

	public String getNoQualified() {
		return noQualified;
	}

	public void setNoQualified(String noQualified) {
		this.noQualified = noQualified;
	}

	public String getNoJoin() {
		return noJoin;
	}

	public void setNoJoin(String noJoin) {
		this.noJoin = noJoin;
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

	public String getPartyGridCommittee() {
		return partyGridCommittee;
	}

	public void setPartyGridCommittee(String partyGridCommittee) {
		this.partyGridCommittee = partyGridCommittee;
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getPartyChangeTime() {
		return partyChangeTime;
	}

	public void setPartyChangeTime(String partyChangeTime) {
		this.partyChangeTime = partyChangeTime;
	}

	public String getEduPartOrg() {
		return eduPartOrg;
	}

	public void setEduPartOrg(String eduPartOrg) {
		this.eduPartOrg = eduPartOrg;
	}

	public String getSocialPartyOrg() {
		return socialPartyOrg;
	}

	public void setSocialPartyOrg(String socialPartyOrg) {
		this.socialPartyOrg = socialPartyOrg;
	}

	public String getSocialParty() {
		return socialParty;
	}

	public void setSocialParty(String socialParty) {
		this.socialParty = socialParty;
	}

	public String getTownParyOrg() {
		return townParyOrg;
	}

	public void setTownParyOrg(String townParyOrg) {
		this.townParyOrg = townParyOrg;
	}

	public String getCommunityPartyOrg() {
		return communityPartyOrg;
	}

	public void setCommunityPartyOrg(String communityPartyOrg) {
		this.communityPartyOrg = communityPartyOrg;
	}

	public String getOtherPartyOrg() {
		return otherPartyOrg;
	}

	public void setOtherPartyOrg(String otherPartyOrg) {
		this.otherPartyOrg = otherPartyOrg;
	}

	public String getSecretaryName1() {
		return secretaryName1;
	}

	public void setSecretaryName1(String secretaryName1) {
		this.secretaryName1 = secretaryName1;
	}

	public String getPrinConcurrently() {
		return prinConcurrently;
	}

	public void setPrinConcurrently(String prinConcurrently) {
		this.prinConcurrently = prinConcurrently;
	}

	public String getMidBear1() {
		return midBear1;
	}

	public void setMidBear1(String midBear1) {
		this.midBear1 = midBear1;
	}

	public String getContributor1() {
		return contributor1;
	}

	public void setContributor1(String contributor1) {
		this.contributor1 = contributor1;
	}

	public String getUpBear1() {
		return upBear1;
	}

	public void setUpBear1(String upBear1) {
		this.upBear1 = upBear1;
	}
	
	
}
