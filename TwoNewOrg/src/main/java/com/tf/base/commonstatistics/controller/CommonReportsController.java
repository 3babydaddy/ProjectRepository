package com.tf.base.commonstatistics.controller;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tf.base.common.constants.CommonConstants;
import com.tf.base.common.domain.DataDictionary;
import com.tf.base.common.domain.DictionaryRepository;
import com.tf.base.common.excel.ExcelUtil;
import com.tf.base.common.service.BaseService;
import com.tf.base.common.service.ExportFileService;
import com.tf.base.common.utils.PageUtil;
import com.tf.base.commonstatistics.domain.QueryParam;
import com.tf.base.commonstatistics.domain.SocialWorkLedger;
import com.tf.base.commonstatistics.domain.UnpublicWorkLedger;
import com.tf.base.commonstatistics.persistence.SocialWorkLedgerMapper;
import com.tf.base.commonstatistics.persistence.UnpublicWorkLedgerMapper;

@Controller
public class CommonReportsController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DictionaryRepository dict;
	@Autowired
	private ExportFileService exportFileService;
	@Autowired
	private BaseService baseService;
	@Autowired
	private UnpublicWorkLedgerMapper unpublicWorkLedgerMapper;
	@Autowired
	private SocialWorkLedgerMapper socialWorkLedgerMapper;

	/**
	 * 新区非公有制经济组织党组织工作台账初始化页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/commonstatistics/unpublicworkledger", method = RequestMethod.GET)
	public String unpublicReportinit(Model model) {

		editPage(model);
		return "commonstatistics/unpublicWorkLedger";
	}

	/**
	 * 新区非公有制经济组织党组织工作台账列表展示
	 * 
	 * @param params
	 * @param page
	 * @param rows
	 * @param response
	 */
	@RequestMapping(value = "/commonstatistics/unpublicworkledger", method = RequestMethod.POST)
	public void unpublicReportList(QueryParam params, int page, int rows, HttpServletResponse response) {
		String deptId = baseService.getCurrentUserDeptId();
		logger.debug("当前登录用户部门ID:===========>" + deptId + " ,是否为区委部门?" + baseService.isQuWeiDept());
		if (!baseService.isQuWeiDept()) {
			params.setCreateOrg(deptId);
		}
		PageHelper.startPage(page, rows, true);
		List<UnpublicWorkLedger> list = unpublicWorkLedgerMapper.queryList(params);
		this.convertRows(list);
		this.convertDict(list);
		PageUtil.returnPage(response, new PageInfo<UnpublicWorkLedger>(list));
	}

	@RequestMapping(value = "/file/exportUnpublicReport")
	@ResponseBody
	public String exportUnpublicReport(QueryParam params, HttpServletResponse response)throws Exception {
		
		String path = ExcelUtil.class.getClassLoader().getResource("").getPath();
		FileInputStream fis = new FileInputStream(path + "/templet/unpublic.xlsx");;
		String filePath = exportFileService.createFilePath(fis);
		
		if (!baseService.isQuWeiDept()) {
			params.setCreateOrg(baseService.getCurrentUserDeptId());
		}
		List<UnpublicWorkLedger> list = unpublicWorkLedgerMapper.queryList(params);
		this.convertRows(list);
		this.convertDict(list);
		ExcelUtil<UnpublicWorkLedger> excelUtils = new ExcelUtil<UnpublicWorkLedger>(UnpublicWorkLedger.class);
		try {
			excelUtils.writeToFile(list, filePath, 6);
			return JSONObject.toJSONString(filePath);
		} catch (Exception e) {
			e.printStackTrace();
			return JSONObject.toJSONString("1");
		}
	}

	@RequestMapping(value = "/file/exportUnpublicReportFile")
	public void exportUnpublicReportFile(String filePath, HttpServletResponse response, HttpServletRequest request) {
		try {
			exportFileService.doDown(filePath, "unpublicWork.xlsx", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 社会工作台账初始化页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/commonstatistics/privateschoolworkledger", method = RequestMethod.GET)
	public String orginit(Model model) {

		editPage(model);
		return "commonstatistics/socialOrgWorkLedger";
	}

	/**
	 * 社会工作台账列表展示
	 * 
	 * @param params
	 * @param page
	 * @param rows
	 * @param response
	 */
	@RequestMapping(value = "/commonstatistics/privateschoolworkledger", method = RequestMethod.POST)
	public void orglist(QueryParam params, int page, int rows, HttpServletResponse response) {
		String deptId = baseService.getCurrentUserDeptId();
		logger.debug("当前登录用户部门ID:===========>" + deptId + " ,是否为区委部门?" + baseService.isQuWeiDept());
		if (!baseService.isQuWeiDept()) {
			params.setCreateOrg(deptId);
		} 
		PageHelper.startPage(page, rows, true);
		List<SocialWorkLedger> list = socialWorkLedgerMapper.queryList(params);
		this.convertRowsBySocial(list);
		this.convertDictBySocial(list);
		PageUtil.returnPage(response, new PageInfo<SocialWorkLedger>(list));
	}

	@RequestMapping(value = "/file/exportSocialReport")
	@ResponseBody
	public String exportSocialReport(QueryParam params, HttpServletResponse response)throws Exception {
		String path = ExcelUtil.class.getClassLoader().getResource("").getPath();
		FileInputStream fis = new FileInputStream(path + "/templet/social.xlsx");
		String filePath = exportFileService.createFilePath(fis);
		if (!baseService.isQuWeiDept()) {
			params.setCreateOrg(baseService.getCurrentUserDeptId());
		}
		List<SocialWorkLedger> list = socialWorkLedgerMapper.queryList(params);
		this.convertRowsBySocial(list);
		this.convertDictBySocial(list);
		ExcelUtil<SocialWorkLedger> excelUtils = new ExcelUtil<SocialWorkLedger>(SocialWorkLedger.class);
		try {
			excelUtils.writeToFile(list, filePath, 6);
			return JSONObject.toJSONString(filePath);
		} catch (Exception e) {
			e.printStackTrace();
			return JSONObject.toJSONString("1");
		}
	}
	
	@RequestMapping(value = "/file/exportSocialReportFile")
	public void exportSocialReportFile(String filePath, HttpServletResponse response, HttpServletRequest request) {
		try {
			exportFileService.doDown(filePath, "socialWork.xlsx", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 列表页面初始化参数
	 * 
	 * @param model
	 */
	private void editPage(Model model) {
		List<DataDictionary> yesNoList = dict.findByDmm(CommonConstants.YES_NO);
		//Calendar cal = Calendar.getInstance(); 
		//String year = cal.get(Calendar.YEAR)+"";
		model.addAttribute("yesNoList", yesNoList);
		//model.addAttribute("year", year);
	}

	/**
	 * 转换数据集合
	 * 
	 * @param rows
	 */
	private void convertRows(List<UnpublicWorkLedger> rows) {
		for (UnpublicWorkLedger info : rows) {
			this.convertRow(info);
		}
	}

	/**
	 * 转换单条数据
	 * 
	 * @param info
	 */
	private void convertRow(UnpublicWorkLedger main) {

		//企业坐落地belocatedAddress 0:其他 1：园区 2：楼宇
		if("0".equals(main.getBelocatedAddress())){
			main.setAdOther("√");
			main.setPark("×");
			main.setBuilding("×");
		}else if("1".equals(main.getBelocatedAddress())){
			main.setAdOther("×");
			main.setPark("√");
			main.setBuilding("×");
		}else if("2".equals(main.getBelocatedAddress())){
			main.setAdOther("×");
			main.setPark("×");
			main.setBuilding("√");
		}
		
		//企业类型industryType 01：私营(企业)类型 02：港澳台(企业)类型 03：外商(企业)类型
		if(main.getIndustryType().startsWith("01")){
			main.setPrivateBusiness("√");
			main.setMainlangBusiness("×");
			main.setForeignBusiness("×");
		}else if(main.getIndustryType().startsWith("02")){
			main.setPrivateBusiness("×");
			main.setMainlangBusiness("√");
			main.setForeignBusiness("×");
		}else if(main.getIndustryType().startsWith("03")){
			main.setPrivateBusiness("×");
			main.setMainlangBusiness("×");
			main.setForeignBusiness("√");
		}
		//党组织组建形式partyOrgForm 0：单独建立 1：联合建立 2：网格建立
		if("0".equals(main.getPartyOrgForm())){
			main.setAloneCreate("√");
			main.setUnionCreate("×");
			main.setGridCreate("×");
		}else if("1".equals(main.getPartyOrgForm())){
			main.setAloneCreate("×");
			main.setUnionCreate("√");
			main.setGridCreate("×");
		}else if("2".equals(main.getPartyOrgForm())){
			main.setAloneCreate("×");
			main.setUnionCreate("×");
			main.setGridCreate("√");
		}
		//党组织类别partyOrgType 0：党委 1：党总支 2：党支部 3联合党支部
		if("0".equals(main.getPartyOrgType())){
			main.setPartyBranch("√");
			main.setGeneralParty("×");
			main.setPartyCommittee("×");
		}else if("1".equals(main.getPartyOrgType())){
			main.setPartyBranch("×");
			main.setGeneralParty("√");
			main.setPartyCommittee("×");
		}else if("2".equals(main.getPartyOrgType())){
			main.setPartyBranch("×");
			main.setGeneralParty("×");
			main.setPartyCommittee("√");
		}
		//未建立党组织原因absencePartyOrgReasion 0：正式党员不足三人 1：其他 2：企业出资人不支持 3：上级党组织未及时指导
		if("0".equals(main.getAbsencePartyOrgReasion())){
			main.setNoParty("√");
			main.setPartyOther("×");
			main.setNoGuidance("×");
			main.setNoSupport("×");
		}else if("1".equals(main.getAbsencePartyOrgReasion())){
			main.setNoParty("×");
			main.setPartyOther("√");
			main.setNoGuidance("×");
			main.setNoSupport("×");
		}else if("2".equals(main.getAbsencePartyOrgReasion())){
			main.setNoParty("×");
			main.setPartyOther("×");
			main.setNoGuidance("√");
			main.setNoSupport("×");
		}else if("3".equals(main.getAbsencePartyOrgReasion())){
			main.setNoParty("×");
			main.setPartyOther("×");
			main.setNoGuidance("×");
			main.setNoSupport("√");
		}
		//书记来源secretarySource 0：出资人担任 1：中层管理人员担任 2：上级党组织选派 3：其他人员担任
		if("0".equals(main.getSecretarySource())){
			main.setContributor("√");
			main.setMidBear("×");
			main.setUpBear("×");
			main.setBearOther("×");
		}else if("1".equals(main.getSecretarySource())){
			main.setContributor("×");
			main.setMidBear("√");
			main.setUpBear("×");
			main.setBearOther("×");
		}else if("2".equals(main.getSecretarySource())){
			main.setContributor("×");
			main.setMidBear("×");
			main.setUpBear("√");
			main.setBearOther("×");
		}else if("3".equals(main.getSecretarySource())){
			main.setContributor("×");
			main.setMidBear("×");
			main.setUpBear("×");
			main.setBearOther("√");
		}
	}

	/**
	 * 转换数据集合
	 * 
	 * @param rows
	 */
	private void convertDict(List<UnpublicWorkLedger> rows) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		for (UnpublicWorkLedger info : rows) {
			if(info.getPartyOrgTime() != null){
				info.setPartyOrgTimeTxt(sdf.format(info.getPartyOrgTime()));
			}
			if(info.getInparkName() != null){
				info.setInparkBuildName(info.getInparkName());
			}else if(info.getBuildingName() != null){
				info.setInparkBuildName(info.getBuildingName());
			}
			info.setCreateOrg(baseService.getDeptNameById(info.getCreateOrg()));
			info.setMillionBuildingIs(dict.getValueByCode(CommonConstants.YES_NO, info.getMillionBuildingIs()));
			info.setBelocatedAddress(dict.getValueByCode(CommonConstants.ENTERPRISE_BELOCATED_ADDRESS, info.getBelocatedAddress()));
			info.setLevel(dict.getValueByCode(CommonConstants.ZONE_LEVEL, info.getLevel()));
			info.setOnScaleIs(dict.getValueByCode(CommonConstants.YES_NO, info.getOnScaleIs()));
			info.setSponsorPartymemberIs(dict.getValueByCode(CommonConstants.YES_NO, info.getSponsorPartymemberIs()));
			info.setSponsorPartyorgSecretaryIs(dict.getValueByCode(CommonConstants.YES_NO, info.getSponsorPartyorgSecretaryIs()));
			info.setSponsorTwodeputyAcommitteeIs(dict.getValueByCode(CommonConstants.YES_NO, info.getSponsorTwodeputyAcommitteeIs()));
			info.setIsSetUpPartyOrg(dict.getValueByCode(CommonConstants.YES_NO, info.getIsSetUpPartyOrg()));
			info.setIsInstructor(dict.getValueByCode(CommonConstants.YES_NO, info.getIsInstructor()));
			info.setIsTrainingInRotation(dict.getValueByCode(CommonConstants.YES_NO, info.getIsTrainingInRotation()));
			info.setIsPartyMemberTrain(dict.getValueByCode(CommonConstants.YES_NO, info.getIsPartyMemberTrain()));
			info.setHasSociaty(dict.getValueByCode(CommonConstants.YES_NO, info.getHasSociaty()));
			info.setHasYouthLeague(dict.getValueByCode(CommonConstants.YES_NO, info.getHasYouthLeague()));
			info.setHasWomenLeague(dict.getValueByCode(CommonConstants.YES_NO, info.getHasWomenLeague()));
			info.setIsOneself(dict.getValueByCode(CommonConstants.YES_NO, info.getIsOneself()));
			info.setIsIntoManage(dict.getValueByCode(CommonConstants.YES_NO, info.getIsIntoManage()));
			info.setIsDevelopListen(dict.getValueByCode(CommonConstants.YES_NO, info.getIsDevelopListen()));
			info.setIsDevelopDiscussions(dict.getValueByCode(CommonConstants.YES_NO, info.getIsDevelopDiscussions()));
			info.setIsDevelopAnalysis(dict.getValueByCode(CommonConstants.YES_NO, info.getIsDevelopAnalysis()));
			info.setIsChangeEveryyear(dict.getValueByCode(CommonConstants.YES_NO, info.getIsChangeEveryyear()));
			
		}
	}
	
	/**
	 * 转换数据集合
	 * 
	 * @param rows
	 */
	private void convertRowsBySocial(List<SocialWorkLedger> rows) {
		for (SocialWorkLedger info : rows) {
			this.convertRowBySocial(info);
		}
	}

	/**
	 * 转换单条数据
	 * 
	 * @param info
	 */
	private void convertRowBySocial(SocialWorkLedger main) {

		
		//党建参检结果annualSurvey 0：合格 1：基本合格 2：不合格3：未参检
		if("0".equals(main.getAnnualSurvey())){
			main.setQualified("√");
			main.setBasicQualified("×");
			main.setNoQualified("×");
			main.setNoJoin("×");
		}else if("1".equals(main.getAnnualSurvey())){
			main.setQualified("×");
			main.setBasicQualified("√");
			main.setNoQualified("×");
			main.setNoJoin("×");
		}else if("2".equals(main.getAnnualSurvey())){
			main.setQualified("×");
			main.setBasicQualified("×");
			main.setNoQualified("√");
			main.setNoJoin("×");
		}else if("3".equals(main.getAnnualSurvey())){
			main.setQualified("×");
			main.setBasicQualified("×");
			main.setNoQualified("×");
			main.setNoJoin("√");
		}
		//党组织组建形式partyOrgForm 0：单独建立 1：联合建立 2：网格建立
		if("0".equals(main.getPartyOrgForm())){
			main.setAloneCreate("√");
			main.setUnionCreate("×");
			main.setGridCreate("×");
			main.setCoverCreate("×");
		}else if("1".equals(main.getPartyOrgForm())){
			main.setAloneCreate("×");
			main.setUnionCreate("√");
			main.setGridCreate("×");
			main.setCoverCreate("×");
		}else if("2".equals(main.getPartyOrgForm())){
			main.setAloneCreate("×");
			main.setUnionCreate("×");
			main.setGridCreate("√");
			main.setCoverCreate("×");
		}else{
			main.setAloneCreate("×");
			main.setUnionCreate("×");
			main.setGridCreate("×");
			main.setCoverCreate("√");
		}
		//党组织类别partyOrgType 0：党委 1：党总支 2：党支部 3联合党支部
		if("0".equals(main.getPartyOrgType())){
			main.setPartyBranch("√");
			main.setGeneralParty("×");
			main.setPartyCommittee("×");
			main.setPartyGridCommittee("×");
		}else if("1".equals(main.getPartyOrgType())){
			main.setPartyBranch("×");
			main.setGeneralParty("√");
			main.setPartyCommittee("×");
			main.setPartyGridCommittee("×");
		}else if("2".equals(main.getPartyOrgType())){
			main.setPartyBranch("×");
			main.setGeneralParty("×");
			main.setPartyCommittee("√");
			main.setPartyGridCommittee("×");
		}else if("3".equals(main.getPartyOrgType())){
			main.setPartyBranch("×");
			main.setGeneralParty("×");
			main.setPartyCommittee("×");
			main.setPartyGridCommittee("√");
		}
		//未建立党组织原因absencePartyOrgReasion 0：正式党员不足三人 1：其他 2：企业出资人不支持 3：上级党组织未及时指导
		if("0".equals(main.getAbsencePartyOrgReasion())){
			main.setNoParty("√");
			main.setPartyOther("×");
			main.setNoGuidance("×");
			main.setNoSupport("×");
		}else if("1".equals(main.getAbsencePartyOrgReasion())){
			main.setNoParty("×");
			main.setPartyOther("√");
			main.setNoGuidance("×");
			main.setNoSupport("×");
		}else if("2".equals(main.getAbsencePartyOrgReasion())){
			main.setNoParty("×");
			main.setPartyOther("×");
			main.setNoGuidance("√");
			main.setNoSupport("×");
		}else if("3".equals(main.getAbsencePartyOrgReasion())){
			main.setNoParty("×");
			main.setPartyOther("×");
			main.setNoGuidance("×");
			main.setNoSupport("√");
		}
		//书记来源secretarySource 0：出资人担任 1：中层管理人员担任 2：上级党组织选派 3：其他人员担任
		main.setSecretaryName1(main.getSecretaryName());
		if("0".equals(main.getSecretarySource())){
			main.setContributor("√");
			main.setMidBear("×");
			main.setUpBear("×");
			main.setBearOther("×");
			main.setContributor1("√");
			main.setMidBear1("×");
			main.setUpBear1("×");
			main.setPrinConcurrently("×");
		}else if("1".equals(main.getSecretarySource())){
			main.setContributor("×");
			main.setMidBear("√");
			main.setUpBear("×");
			main.setBearOther("×");
			main.setContributor1("×");
			main.setMidBear1("√");
			main.setUpBear1("×");
			main.setPrinConcurrently("×");
		}else if("2".equals(main.getSecretarySource())){
			main.setContributor("×");
			main.setMidBear("×");
			main.setUpBear("√");
			main.setBearOther("×");
			main.setContributor1("×");
			main.setMidBear1("×");
			main.setUpBear1("√");
			main.setPrinConcurrently("×");
		}else if("3".equals(main.getSecretarySource())){
			main.setContributor("×");
			main.setMidBear("×");
			main.setUpBear("×");
			main.setBearOther("√");
			main.setContributor1("×");
			main.setMidBear1("×");
			main.setUpBear1("×");
			main.setPrinConcurrently("×");
		}else if("4".equals(main.getSecretarySource())){
			main.setContributor("×");
			main.setMidBear("×");
			main.setUpBear("×");
			main.setBearOther("×");
			main.setContributor1("×");
			main.setMidBear1("×");
			main.setUpBear1("×");
			main.setPrinConcurrently("√");
		}
		//党组织所隶属上级党组织parentPartyOrgType 0:区教育局党组织 
		//1:区人力资源社会保障局党组织 2:区社会组织党委 3:乡镇(街道)党组织 4:社区党组织 5:其他
		if("0".equals(main.getParentPartyOrgType())){
			main.setEduPartOrg("√");
			main.setSocialPartyOrg("×");
			main.setSocialParty("×");
			main.setTownParyOrg("×");
			main.setCommunityPartyOrg("×");
			main.setOtherPartyOrg("×");
		}else if("1".equals(main.getParentPartyOrgType())){
			main.setEduPartOrg("×");
			main.setSocialPartyOrg("√");
			main.setSocialParty("×");
			main.setTownParyOrg("×");
			main.setCommunityPartyOrg("×");
			main.setOtherPartyOrg("×");
		}else if("2".equals(main.getParentPartyOrgType())){
			main.setEduPartOrg("×");
			main.setSocialPartyOrg("×");
			main.setSocialParty("√");
			main.setTownParyOrg("×");
			main.setCommunityPartyOrg("×");
			main.setOtherPartyOrg("×");
		}else if("3".equals(main.getParentPartyOrgType())){
			main.setEduPartOrg("×");
			main.setSocialPartyOrg("×");
			main.setSocialParty("×");
			main.setTownParyOrg("√");
			main.setCommunityPartyOrg("×");
			main.setOtherPartyOrg("×");
		}else if("4".equals(main.getParentPartyOrgType())){
			main.setEduPartOrg("×");
			main.setSocialPartyOrg("×");
			main.setSocialParty("×");
			main.setTownParyOrg("×");
			main.setCommunityPartyOrg("√");
			main.setOtherPartyOrg("×");
		}else if("5".equals(main.getParentPartyOrgType())){
			main.setEduPartOrg("×");
			main.setSocialPartyOrg("×");
			main.setSocialParty("×");
			main.setTownParyOrg("×");
			main.setCommunityPartyOrg("×");
			main.setOtherPartyOrg("√");
		}
	}

	/**
	 * 转换数据集合
	 * 
	 * @param rows
	 */
	private void convertDictBySocial(List<SocialWorkLedger> rows) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		for (SocialWorkLedger info : rows) {
			if(info.getPartyOrgTime() != null){
				info.setPartyOrgTimeTxt(sdf.format(info.getPartyOrgTime()));
			}
			info.setCategory(dict.getValueByCode(CommonConstants.ORG_CATEGORY, info.getCategory()));
			info.setChargePartymemberIs(dict.getValueByCode(CommonConstants.YES_NO, info.getChargePartymemberIs()));
			info.setChargePartyorgSecretaryIs(dict.getValueByCode(CommonConstants.YES_NO, info.getChargePartyorgSecretaryIs()));
			info.setChargeTwodeputyAcommitteeIs(dict.getValueByCode(CommonConstants.YES_NO, info.getChargeTwodeputyAcommitteeIs()));
			info.setIsSetUpPartyOrg(dict.getValueByCode(CommonConstants.YES_NO, info.getIsSetUpPartyOrg()));
			info.setIsInstructor(dict.getValueByCode(CommonConstants.YES_NO, info.getIsInstructor()));
			info.setIsPartyMemberTrain(dict.getValueByCode(CommonConstants.YES_NO, info.getIsPartyMemberTrain()));
			info.setIsTrainingInRotation(dict.getValueByCode(CommonConstants.YES_NO, info.getIsTrainingInRotation()));
			info.setPartyMeetingMonth(dict.getValueByCode(CommonConstants.YES_NO, info.getPartyMeetingMonth()));
			info.setIsBoardOfficer(dict.getValueByCode(CommonConstants.YES_NO, info.getIsBoardOfficer()));
			if(info.getDeputySecretaryFullIs() != null && Integer.parseInt(info.getDeputySecretaryFullIs()) > 0){
				info.setDeputySecretaryFullIs("是");
			}else{
				info.setDeputySecretaryFullIs("否");
			}
			
			info.setDeputySecreraryNumberIs(dict.getValueByCode(CommonConstants.YES_NO, info.getDeputySecreraryNumberIs()));
			info.setIsIdeologicalPoliticalOrg(dict.getValueByCode(CommonConstants.YES_NO, info.getIsIdeologicalPoliticalOrg()));
			info.setIsPartyIntoSchool(dict.getValueByCode(CommonConstants.YES_NO, info.getIsPartyIntoSchool()));
			info.setIsMoralEducationOrg(dict.getValueByCode(CommonConstants.YES_NO, info.getIsMoralEducationOrg()));
			info.setIsIntoManage(dict.getValueByCode(CommonConstants.YES_NO, info.getIsIntoManage()));
		}
	}
	
	
}
