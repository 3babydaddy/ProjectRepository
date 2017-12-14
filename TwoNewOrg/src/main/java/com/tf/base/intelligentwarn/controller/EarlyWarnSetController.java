package com.tf.base.intelligentwarn.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.converters.StringArrayConverter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tf.base.common.constants.CommonConstants;
import com.tf.base.common.constants.CommonConstants.LOG_OPERATION_TYPE;
import com.tf.base.common.domain.DataDictionary;
import com.tf.base.common.domain.DictionaryRepository;
import com.tf.base.common.service.BaseService;
import com.tf.base.common.service.LogService;
import com.tf.base.common.utils.GeneralCalcUtils;
import com.tf.base.common.utils.PageUtil;
import com.tf.base.socialorg.domain.SocialOrgChargeInfo;
import com.tf.base.socialorg.domain.SocialOrgInfo;
import com.tf.base.socialorg.domain.SocialOrgInfoOtherCount;
import com.tf.base.socialorg.domain.SocialOrgJobinCount;
import com.tf.base.socialorg.domain.SocialOrgPmbrCount;
import com.tf.base.unpublic.domain.UnpublicOrgAddressInfo;
import com.tf.base.unpublic.domain.UnpublicOrgInfo;
import com.tf.base.unpublic.domain.UnpublicOrgInfoOtherCount;
import com.tf.base.unpublic.domain.UnpublicOrgLeagueInfo;
import com.tf.base.unpublic.domain.UnpublicOrgPmbrCount;
import com.tf.base.unpublic.domain.UnpublicOrgSponsorInfo;
import com.tf.base.unpublic.persistence.UnpublicOrgAddressInfoMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgCancelRecordMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgInfoMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgInfoOtherCountMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgLeagueInfoMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgPmbrCountMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgPmbrInfoMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgSponsorInfoMapper;
import com.tf.base.unpublic.service.UnPublicOrgService;

@Controller
public class EarlyWarnSetController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DictionaryRepository dict;
	
	@Autowired
	private BaseService baseService;
	@Autowired
	private LogService logService;
	
	@Autowired
	private UnpublicOrgInfoMapper unpublicOrgInfoMapper;
	@Autowired
	private UnpublicOrgSponsorInfoMapper unpublicOrgSponsorInfoMapper;
	@Autowired
	private UnpublicOrgCancelRecordMapper unpublicOrgCancelRecordMapper;
	@Autowired
	private UnpublicOrgPmbrCountMapper unpublicOrgPmbrCountMapper;
	@Autowired
	private UnpublicOrgPmbrInfoMapper unpublicOrgPmbrInfoMapper;
	@Autowired
	private UnpublicOrgLeagueInfoMapper unpublicOrgLeagueInfoMapper;
	@Autowired
	private UnpublicOrgAddressInfoMapper unpublicOrgAddressInfoMapper;
	@Autowired
	private UnpublicOrgInfoOtherCountMapper unpublicOrgInfoOtherCountMapper;
	@Autowired
	private UnPublicOrgService unPublicOrgService;
	
	/**
	 * 初始化页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/intelligentwarn/earlywarnsetlist",method=RequestMethod.GET)
	public String orginit(Model model){
		
		editPage(model);
		return "intelligentwarn/earlyWarnSetList";
	}
	
	/**
	 * 预警设置列表展示
	 * @param params
	 * @param page
	 * @param rows
	 * @param response
	 */
	@RequestMapping(value="/intelligentwarn/earlywarnsetlist",method=RequestMethod.POST)
	public void orglist(UnpublicOrgInfo params,
			int page,int rows,HttpServletResponse response){
		String deptId = baseService.getCurrentUserDeptId();
		logger.debug("当前登录用户部门ID:===========>" + deptId + " ,是否为区委部门?" + baseService.isQuWeiDept());
		
		String orderby = "";
		if(!baseService.isQuWeiDept()){
			params.setCreateOrg(deptId);
			orderby = " main.update_time desc , main.status desc, main.create_time desc";
		}else{
			orderby = " main.status desc , main.create_time desc";
		}
		PageHelper.startPage(page, rows, true);
		List<UnpublicOrgInfo> list = unpublicOrgInfoMapper.queryList(params,orderby);
		this.convertRows(list);
		PageUtil.returnPage(response, new PageInfo<UnpublicOrgInfo>(list));
	}
	
	/**
	 * 预警设置编辑页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/intelligentwarn/earlywarnsetedit",method=RequestMethod.GET)
	public String orgedit(String id,Model model){
		if(!StringUtils.isEmpty(id)){
			Integer mainId = Integer.parseInt(id);
			//基础信息
			UnpublicOrgInfo main = unpublicOrgInfoMapper.selectByPrimaryKey(mainId);
			
			String businessType = main.getBusinessType();
			if(StringUtils.isNotEmpty(businessType)){
				model.addAttribute("businessTypes", Arrays.asList(businessType.split(",")));
			}
			String nationality = main.getNationality();
			if(StringUtils.isNotEmpty(nationality)){
				model.addAttribute("nationalitys", Arrays.asList(nationality.split(",")));
			}
			
			//主要出资人
			UnpublicOrgSponsorInfo sponsor = new UnpublicOrgSponsorInfo();
			sponsor.setUnpublicOrgInfoId(mainId);
			sponsor = unpublicOrgSponsorInfoMapper.selectOne(sponsor);
			//群团信息
			UnpublicOrgLeagueInfo league = new UnpublicOrgLeagueInfo();
			league.setUnpublicOrgInfoId(mainId);
			league = unpublicOrgLeagueInfoMapper.selectOne(league);
			//主要出资人类型
			String deputyType = sponsor.getSponsorTwodeputyAcommitteeType();
			if(StringUtils.isNotEmpty(deputyType)){
				model.addAttribute("deputyTypes", Arrays.asList(deputyType.split(",")));
			}
			//党员统计信息
			UnpublicOrgPmbrCount pmbrCount = new UnpublicOrgPmbrCount();
			pmbrCount.setUnpublicOrgInfoId(mainId);
			pmbrCount = unpublicOrgPmbrCountMapper.selectOne(pmbrCount);
			
			UnpublicOrgAddressInfo address = new UnpublicOrgAddressInfo();
			address.setUnpublicOrgInfoId(mainId);
			List<UnpublicOrgAddressInfo> addressList = unpublicOrgAddressInfoMapper.select(address);
			
			UnpublicOrgInfoOtherCount otherCount = new UnpublicOrgInfoOtherCount();
			otherCount.setUnpublicOrgInfoId(mainId);
			otherCount.setFieldValue(1);
			List<UnpublicOrgInfoOtherCount> otherCounts = unpublicOrgInfoOtherCountMapper.select(otherCount);
			
			model.addAttribute("main", main);
			model.addAttribute("sponsor", sponsor);
			model.addAttribute("league", league);
			model.addAttribute("pmbrCount", pmbrCount);
			model.addAttribute("addressList", addressList);
			model.addAttribute("otherCounts", otherCounts);
		}else{
			UnpublicOrgInfo main = new UnpublicOrgInfo();
			main.setName("0");
			model.addAttribute("main", main);
		}
		editPage(model);
		return "intelligentwarn/earlyWarnSetEdit";
	}
	
	/**
	 * 预警设置开启或者关闭
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/intelligentwarn/earlywarnsetstatus",method=RequestMethod.POST)
	public String earlyWarnSetStatus(String id, String status, Redirect redirect){
		//TODO
		return "redirect:/intelligentwarn/earlyWarnSetlist";
	}
	
	/**
	 * 编辑页面初始化参数
	 * @param model
	 */
	private void editPage(Model model){
		
		List<DataDictionary> yesNoList = dict.findByDmm(CommonConstants.YES_NO);
		List<DataDictionary> personnelCategoryList = dict.findByDmm(CommonConstants.PERSONNEL_CATEGORY);
		List<DataDictionary> reminderCycleList = dict.findByDmm(CommonConstants.REMINDER_CYCLE);
		List<DataDictionary> warnSwitchList = dict.findByDmm(CommonConstants.WARN_SWITCH);
		List<DataDictionary> timeUnitList = dict.findByDmm(CommonConstants.TIME_UNIT);
		model.addAttribute("yesNoList", yesNoList);
		model.addAttribute("personnelCategoryList", personnelCategoryList);
		model.addAttribute("reminderCycleList", reminderCycleList);
		model.addAttribute("warnSwitchList", warnSwitchList);
		model.addAttribute("timeUnitList", timeUnitList);
	}
	
	
	
	/**
	 * 转换数据集合
	 * @param rows
	 */
	private void convertRows(List<UnpublicOrgInfo> rows) {
		for (UnpublicOrgInfo info : rows) {
			this.convertRow(info);
		}
	}


	/**
	 * 转换单条数据
	 * @param info
	 */
	private void convertRow(UnpublicOrgInfo main) {

		main.setStatusTxt(dict.getValueByCode(CommonConstants.UNPUBLIC_ORG_STATUS,main.getStatus()));
		main.setIndustryTypeTxt(dict.getValueByCode(CommonConstants.ENTERPRISE_TYPE, main.getIndustryType()));
		main.setLevelTxt(dict.getValueByCode(CommonConstants.ZONE_LEVEL, main.getLevel()));
		main.setMillionBuildingIsTxt(dict.getValueByCode(CommonConstants.YES_NO, main.getMillionBuildingIs()));
		main.setBelocatedAddressTxt(dict.getValueByCode(CommonConstants.ENTERPRISE_BELOCATED_ADDRESS, main.getBelocatedAddress()));
		main.setOnScaleIsTxt(dict.getValueByCode(CommonConstants.YES_NO, main.getOnScaleIs()));
		
		String businessType = splitConvertField(CommonConstants.BUSINESS_TYPE,main.getBusinessType());
		
		main.setBusinessTypeTxt(businessType);
		String nationality = splitConvertField(CommonConstants.NATIONALITY,main.getNationality());
		main.setNationlityTxt(nationality);
		
		main.setCreateOrgTxt(baseService.getDeptNameById(main.getCreateOrg()));
	}
	private String splitConvertField(String code, String value) {
		StringBuffer sb = new StringBuffer();
		
		if(value == null || value.equals(""))
			return "";
		String values[] = value.split(",");
		if(values.length > 0){
			
			for (String v : values) {
				sb.append(dict.getValueByCode(code, v));
				sb.append(",");
			}
			return (!sb.toString().equals("") ? sb.toString().substring(0,sb.toString().length()-1) : sb.toString());
		}else{
			return "";
		}
	}

	/**
	 * 返回信息及标示
	 * @param status
	 * @param msg
	 * @return
	 */
	private Map<String,Object> returnMsg(int status,String msg){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", status);
		result.put("msg", msg);
		return result;
	}
}
