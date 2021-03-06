package com.tf.base.unpublic.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tf.base.common.constants.CommonConstants;
import com.tf.base.common.constants.CommonConstants.LOG_OPERATION_TYPE;
import com.tf.base.common.controller.CitysController;
import com.tf.base.common.domain.DataDictionary;
import com.tf.base.common.domain.DictionaryRepository;
import com.tf.base.common.excel.ExcelUtil;
import com.tf.base.common.service.BaseService;
import com.tf.base.common.service.ExportFileService;
import com.tf.base.common.service.LogService;
import com.tf.base.common.utils.ExcelUtils;
import com.tf.base.common.utils.GeneralCalcUtils;
import com.tf.base.common.utils.PageUtil;
import com.tf.base.unpublic.domain.AddPmbrParams;
import com.tf.base.unpublic.domain.AllParams;
import com.tf.base.unpublic.domain.QueryPmbrParams;
import com.tf.base.unpublic.domain.RemovePmbrParams;
import com.tf.base.unpublic.domain.UnpublicOrgAddressInfo;
import com.tf.base.unpublic.domain.UnpublicOrgCancelRecord;
import com.tf.base.unpublic.domain.UnpublicOrgExportBean;
import com.tf.base.unpublic.domain.UnpublicOrgInfo;
import com.tf.base.unpublic.domain.UnpublicOrgInfoOtherCount;
import com.tf.base.unpublic.domain.UnpublicOrgLeagueInfo;
import com.tf.base.unpublic.domain.UnpublicOrgPmbrChangeInfo;
import com.tf.base.unpublic.domain.UnpublicOrgPmbrCount;
import com.tf.base.unpublic.domain.UnpublicOrgPmbrInfo;
import com.tf.base.unpublic.domain.UnpublicOrgPmbrOtherCount;
import com.tf.base.unpublic.domain.UnpublicOrgSponsorInfo;
import com.tf.base.unpublic.persistence.UnpublicOrgAddressInfoMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgCancelRecordMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgInfoMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgInfoOtherCountMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgLeagueInfoMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgPmbrChangeInfoMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgPmbrCountMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgPmbrInfoMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgPmbrOtherCountMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgSponsorInfoMapper;
import com.tf.base.unpublic.service.UnPublicOrgService;

import tk.mybatis.mapper.entity.Example;

@Controller
public class UnPublicOrgController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DictionaryRepository dict;
	@Autowired
	private  ExportFileService exportFileService;
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
	@Autowired
	private UnpublicOrgPmbrChangeInfoMapper unpublicOrgPmbrChangeInfoMapper;
	@Autowired
	private UnpublicOrgPmbrOtherCountMapper unpublicOrgPmbrOtherCountMapper;
	
	
	/**
	 * 初始化页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/unpublic/orglist",method=RequestMethod.GET)
	public String orginit(Model model){
		
		initPage(model);
		return "unpublic/orgList";
	}
	
	/**
	 * 列表展示
	 * @param params
	 * @param page
	 * @param rows
	 * @param response
	 */
	@RequestMapping(value="/unpublic/orglist",method=RequestMethod.POST)
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
	 * 查看组织信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/unpublic/orglook",method=RequestMethod.GET)
	public String orglook(String id,Model model){
		Integer mainId = Integer.parseInt(id);
		//基础信息
		UnpublicOrgInfo main = new UnpublicOrgInfo();
		main = unpublicOrgInfoMapper.selectByPrimaryKey(id);
		this.convertRow(main);
		
		//企业主要出资人
		UnpublicOrgSponsorInfo sponsor = new UnpublicOrgSponsorInfo();
		sponsor.setUnpublicOrgInfoId(mainId);
		sponsor = unpublicOrgSponsorInfoMapper.selectOne(sponsor);
		sponsor.setSponsorPartymemberIsTxt(dict.getValueByCode(CommonConstants.YES_NO, sponsor.getSponsorPartymemberIs()));
		sponsor.setSponsorPartyorgSecretaryIsTxt(dict.getValueByCode(CommonConstants.YES_NO, sponsor.getSponsorPartyorgSecretaryIs()));
		sponsor.setSponsorTwodeputyAcommitteeIsTxt(dict.getValueByCode(CommonConstants.YES_NO, sponsor.getSponsorTwodeputyAcommitteeIs()));
		
		//主要出资人类型
		String deputyType = sponsor.getSponsorTwodeputyAcommitteeType();
		if(StringUtils.isNotEmpty(deputyType)){
			model.addAttribute("deputyTypes", Arrays.asList(deputyType.split(",")));
		}
		
		//党员统计信息
		UnpublicOrgPmbrCount pmbrCount = new UnpublicOrgPmbrCount();
		pmbrCount.setUnpublicOrgInfoId(mainId);
		pmbrCount = unpublicOrgPmbrCountMapper.selectOne(pmbrCount);
		
		//群团信息
		UnpublicOrgLeagueInfo league = new UnpublicOrgLeagueInfo();
		league.setUnpublicOrgInfoId(mainId);
		league = unpublicOrgLeagueInfoMapper.selectOne(league);
		league.setHasSociatyTxt(dict.getValueByCode(CommonConstants.HAS_NOT,league.getHasSociaty()));
		league.setHasWomenLeagueTxt(dict.getValueByCode(CommonConstants.HAS_NOT, league.getHasWomenLeague()));
		league.setHasYouthLeagueTxt(dict.getValueByCode(CommonConstants.HAS_NOT, league.getHasYouthLeague()));
		
		UnpublicOrgAddressInfo address = new UnpublicOrgAddressInfo();
		address.setUnpublicOrgInfoId(mainId);
		List<UnpublicOrgAddressInfo> addressList = unpublicOrgAddressInfoMapper.select(address);
		
		UnpublicOrgInfoOtherCount otherCount = new UnpublicOrgInfoOtherCount();
		otherCount.setUnpublicOrgInfoId(mainId);
		otherCount.setFieldValue(1);
		List<UnpublicOrgInfoOtherCount> otherCounts = unpublicOrgInfoOtherCountMapper.select(otherCount);
		List<String> others =new ArrayList<>();
		for (UnpublicOrgInfoOtherCount count : otherCounts) {
			others.add(count.getFieldName());
		}
		String otherStr = StringUtils.join(others.toArray(),",");
		String otherCondition = splitConvertField(CommonConstants.UNPUBLIC_ORG_OTHER_CONDITION,otherStr);
		main.setOtherCondition(otherCondition);
		
		model.addAttribute("main", main);
		model.addAttribute("sponsor", sponsor);
		model.addAttribute("pmbrCount", pmbrCount);
		model.addAttribute("league", league);
		model.addAttribute("addressList", addressList);
		
		editPage(model);
		
		//查看日志
		logService.saveLog(LOG_OPERATION_TYPE.VIEW.toString(), 
				logService.getDetailInfo("log.unpublic.view",
						baseService.getUserName(),main.getName()));
		return "unpublic/orgLook";
	}
	
	/**
	 * 编辑组织信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/unpublic/orgedit",method=RequestMethod.GET)
	public String orgedit(String id, String clickSign, Model model){
		boolean flag = false;
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
			//查看该组织下是否有党员
			Example example = new Example(UnpublicOrgPmbrInfo.class);
			example.setOrderByClause("createTime desc");
			example.createCriteria().andEqualTo("unpublicOrgInfoId", Integer.parseInt(id)).andEqualTo("status", "1");
			List<UnpublicOrgPmbrInfo> unpublicOrgPmbrInfos = unpublicOrgPmbrInfoMapper.selectByExample(example);
			if(unpublicOrgPmbrInfos.size() > 0){
				flag = true;
			}
			
			model.addAttribute("main", main);
			model.addAttribute("sponsor", sponsor);
			model.addAttribute("league", league);
			model.addAttribute("pmbrCount", pmbrCount);
			model.addAttribute("addressList", addressList);
			model.addAttribute("otherCounts", otherCounts);
			model.addAttribute("flag", flag);
			model.addAttribute("clickSign", clickSign);
		}else{
			UnpublicOrgInfo main = new UnpublicOrgInfo();
			model.addAttribute("main", main);
			model.addAttribute("flag", flag);
		}
		editPage(model);
		return "unpublic/orgEdit";
	}
	
	/**
	 * 保存组织信息
	 * @param model
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/unpublic/orgedit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> orgSave(Model model,AllParams params){

		UnpublicOrgInfo main = new UnpublicOrgInfo();
		BeanUtils.copyProperties(params, main);
		if(StringUtils.isNotEmpty(params.getMainId())){
			main.setId(Integer.parseInt(params.getMainId()));
		}
		
		UnpublicOrgSponsorInfo sponsor = new UnpublicOrgSponsorInfo();
		BeanUtils.copyProperties(params, sponsor);
		
		UnpublicOrgLeagueInfo league = new UnpublicOrgLeagueInfo();
		BeanUtils.copyProperties(params, league);
		
		UnpublicOrgPmbrCount pmbrCount = new UnpublicOrgPmbrCount();
		BeanUtils.copyProperties(params, pmbrCount);
		
		
		Integer mainId = main.getId();
		
		if(params.getReportHigher().intValue() == 1){
			main.setStatus("5");//上报申请中
		}else{
			main.setStatus("1");//临时保存
		}
		if(mainId == null || StringUtils.isEmpty(mainId.toString())){
			
			try {
				unPublicOrgService.addOrg(main,sponsor,league,pmbrCount,params);
				return returnMsg(1, "新增成功!");
			} catch (Exception e) {
				logger.debug("新增非公组织信息时出现异常:{}", e.getMessage(),e);
				return returnMsg(0, "新增失败:" + e.getMessage());
			}
		}else{
			
			try {
				unPublicOrgService.updateOrg(main,sponsor,league,pmbrCount,params);
				return returnMsg(1, "修改成功!");
			} catch (Exception e) {
				logger.debug("修改非公组织信息时出现异常:{}", e.getMessage(),e);
				return returnMsg(0, "修改失败:" + e.getMessage());
			}
		}
		
	}
	
	@RequestMapping(value="/unpublic/orgSetStatus",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> orgSetStatus(Model model,String partyOrgIds,String status){
		String[] partyOrgIdArray = partyOrgIds.split(",");
		for(int i = 0; i < partyOrgIdArray.length; i++){
			UnpublicOrgInfo info = unpublicOrgInfoMapper.selectByPrimaryKey(partyOrgIdArray[i]);
			info.setStatus(status);
			info.setUpdateTime(new Date());
			info.setUpdator(baseService.getUserName());
			unpublicOrgInfoMapper.updateByPrimaryKey(info);
		}
		
		return returnMsg(1, "操作成功!");
	}
	
	/**
	 * 删除组织信息
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/unpublic/orgdelete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> orgdelete(Model model,String partyOrgIds){
		String[] partyOrgIdArray = partyOrgIds.split(",");
		try {
			unPublicOrgService.delOrg(partyOrgIdArray);
			for(int i = 0; i < partyOrgIdArray.length; i++){
				UnpublicOrgInfo main = unpublicOrgInfoMapper.selectByPrimaryKey(partyOrgIdArray[i]);
				//删除日志
				logService.saveLog(LOG_OPERATION_TYPE.DELETE.toString(), 
						logService.getDetailInfo("log.unpublic.delete",
								baseService.getUserName(),main.getName()));
			}
			return returnMsg(1, "删除成功!");
		} catch (Exception e) {
			logger.debug("删除非公组织信息时出现异常:{}", e.getMessage(),e);
			return returnMsg(0, "删除失败："+e.getMessage());
		}
	}
	
	
	/**
	 * 撤销组织页面
	 * @param id
	 * @param method
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/unpublic/orgcancel",method=RequestMethod.GET)
	public String orgcancel(String id,String method,Model model){
		Example example = new Example(UnpublicOrgCancelRecord.class);
		example.setOrderByClause("createTime desc");
		example.createCriteria().andEqualTo("unpubliceconOrgInfoId", Integer.parseInt(id));
		List<UnpublicOrgCancelRecord> unpublicOrgCancelRecords = unpublicOrgCancelRecordMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(unpublicOrgCancelRecords)){
			model.addAttribute("reason", unpublicOrgCancelRecords.get(0));
		}
		
		model.addAttribute("id", id);
		model.addAttribute("method", method);
		return "unpublic/orgCancel";
	}
	/**
	 * 撤销组织信息
	 * @param model
	 * @param id
	 * @param remarks
	 * @return
	 */
	@RequestMapping(value="/unpublic/orgcancel",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> orgCancel(Model model,String id,String remarks){
		
		try {
			unPublicOrgService.cancelOrg(id,remarks);
			
			UnpublicOrgInfo main = unpublicOrgInfoMapper.selectByPrimaryKey(id);
			//撤销日志
			logService.saveLog(LOG_OPERATION_TYPE.DELETE.toString(), 
					logService.getDetailInfo("log.unpublic.cancel",
							baseService.getUserName(),main.getName()));
			return returnMsg(1, "撤销成功，等待上级部门审核！");
		} catch (Exception e) {
			logger.debug("撤销非公组织信息时出现异常:{}", e.getMessage(),e);
			return returnMsg(0, "撤销失败:" + e.getMessage());
		}
		
	}
	/**
	 * 取消撤销
	 * @param model
	 * @param id
	 * @param remarks
	 * @return
	 */
	@RequestMapping(value="/unpublic/nocancel",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> nocancel(Model model,String id,String remarks){
		
		UnpublicOrgInfo main = unpublicOrgInfoMapper.selectByPrimaryKey(Integer.parseInt(id));
		main.setStatus("2");
		main.setUpdateTime(new Date());
		main.setUpdator(baseService.getUserName());
		unpublicOrgInfoMapper.updateByPrimaryKey(main);
		
		try {
			
			main = unpublicOrgInfoMapper.selectByPrimaryKey(id);
			//取消撤销日志
			logService.saveLog(LOG_OPERATION_TYPE.DELETE.toString(), 
					logService.getDetailInfo("log.unpublic.nocancel",
							baseService.getUserName(),main.getName()));
			return returnMsg(1, "取消撤销该组织成功！");
		} catch (Exception e) {
			logger.debug("取消撤销社会组织信息时出现异常:{}", e.getMessage(),e);
			return returnMsg(0, "撤销失败:" + e.getMessage());
		}
	}
	/**
	 * 审核
	 * @param model
	 * @param id
	 * @param remarks
	 * @return
	 */
	@RequestMapping(value="/unpublic/cancelok",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> cancelok(Model model,String partyOrgIds,String remarks){
		try {
			String[] partyOrgIdArray = partyOrgIds.split(",");
			for(int i = 0; i < partyOrgIdArray.length; i++){
				UnpublicOrgInfo main = unpublicOrgInfoMapper.selectByPrimaryKey(Integer.parseInt(partyOrgIdArray[i]));
				main.setStatus("4");//已撤销
				main.setUpdateTime(new Date());
				main.setUpdator(baseService.getUserName());
				unpublicOrgInfoMapper.updateByPrimaryKey(main);
				
				main = unpublicOrgInfoMapper.selectByPrimaryKey(partyOrgIdArray[i]);
				//撤销审核通过日志
				logService.saveLog(LOG_OPERATION_TYPE.MODIFY.toString(), 
						logService.getDetailInfo("log.unpublic.cancelok",
								baseService.getUserName(),main.getName()));
			}
			return returnMsg(1, "审核通过，撤销该组织成功！");
		} catch (Exception e) {
			logger.debug("撤销审核通过社会组织信息时出现异常:{}", e.getMessage(),e);
			return returnMsg(0, "撤销失败:" + e.getMessage());
		}
		
	}
	
	/**
	 * 党员页面
	 * @param id
	 * @param method
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/unpublic/partyMbrList",method=RequestMethod.GET)
	public String topartyMbrList(String id,String method,Model model){
		
		UnpublicOrgInfo main = unpublicOrgInfoMapper.selectByPrimaryKey(id);
		List<DataDictionary> otherConditionList = dict.findByDmm(CommonConstants.UNPUBLIC_PM_OTHER_CONDITION);
		
		model.addAttribute("id", id);
		model.addAttribute("method", method);
		model.addAttribute("main", main);
		model.addAttribute("otherConditionList", otherConditionList);
		return "unpublic/partyMbrList";
	}
	/**
	 * 党员列表
	 * @param response
	 * @param id
	 * @param page
	 * @param rows
	 * @param params
	 */
	@RequestMapping(value="/unpublic/partyMbrList",method=RequestMethod.POST)
	public void partyMbrList(HttpServletResponse response,String id,int page,int rows,QueryPmbrParams params){
		
		params.setUnpublicOrgInfoId(Integer.parseInt(id));
		PageHelper.startPage(page, rows, true);
		List<UnpublicOrgPmbrInfo> list = unpublicOrgPmbrInfoMapper.queryList(params);
		for (UnpublicOrgPmbrInfo unpublicOrgPmbrInfo : list) {
			unpublicOrgPmbrInfo.setGender(dict.getValueByCode(CommonConstants.GENDER, unpublicOrgPmbrInfo.getGender()));
			unpublicOrgPmbrInfo.setPartymbrFrontlineIs(dict.getValueByCode(CommonConstants.YES_NO, unpublicOrgPmbrInfo.getPartymbrFrontlineIs()));
			unpublicOrgPmbrInfo.setPartymbrInUnpublicIs(dict.getValueByCode(CommonConstants.YES_NO, unpublicOrgPmbrInfo.getPartymbrInUnpublicIs()));
			unpublicOrgPmbrInfo.setPartymbrInVillageIs(dict.getValueByCode(CommonConstants.YES_NO, unpublicOrgPmbrInfo.getPartymbrInVillageIs()));
			unpublicOrgPmbrInfo.setPartymbrMiddleManagerIs(dict.getValueByCode(CommonConstants.YES_NO, unpublicOrgPmbrInfo.getPartymbrMiddleManagerIs()));
			unpublicOrgPmbrInfo.setPartymbrNotinUnpublicIs(dict.getValueByCode(CommonConstants.YES_NO, unpublicOrgPmbrInfo.getPartymbrInUnpublicIs() == "0" ? "1" : "0"));
			unpublicOrgPmbrInfo.setPartymbrOnMiddletechIs(dict.getValueByCode(CommonConstants.YES_NO, unpublicOrgPmbrInfo.getPartymbrOnMiddletechIs()));
			unpublicOrgPmbrInfo.setEducation(dict.getValueByCode(CommonConstants.FINAL_EDUCATION, unpublicOrgPmbrInfo.getEducation()));
			unpublicOrgPmbrInfo.setType(dict.getValueByCode(CommonConstants.PARTY_MBR_CHANGE_TYPE, unpublicOrgPmbrInfo.getType()));
		}
		PageUtil.returnPage(response, new PageInfo<UnpublicOrgPmbrInfo>(list));
	}

	/**
	 * 新增党员页面
	 * @param mainId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/unpublic/addPartymbr",method=RequestMethod.GET)
	public String addPartymbr(String mainId,Model model){
		partyMbrInit(model);
		model.addAttribute("mainId", mainId);
		return "unpublic/addPartyMbr";
	}
	/**
	 * 新增党员信息
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/unpublic/addPartymbr",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addPartymbrSave(AddPmbrParams params){
		
		try {
			
			unPublicOrgService.addPartymbr(params);
			
			return returnMsg(1, "添加党员成功!");
		} catch (Exception e) {
			logger.debug("新增非公组织党员信息时出现异常:{}", e.getMessage(),e);
			return returnMsg(0, "添加党员失败：" + e.getMessage());
		}
		
		
	}
	/**
	 * 修改党员页面
	 * @param mainId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/unpublic/editPartymbr",method=RequestMethod.GET)
	public String editPartymbr(String mainId,String id, Model model){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.partyMbrInit(model);
		UnpublicOrgPmbrInfo pmbrInfo = unpublicOrgPmbrInfoMapper.selectByPrimaryKey(Integer.parseInt(id));
		if(pmbrInfo.getBirthday() != null){
			pmbrInfo.setBirthdayTxt(sdf.format(pmbrInfo.getBirthday()));
		}
		UnpublicOrgPmbrChangeInfo temChangeInfo = new UnpublicOrgPmbrChangeInfo();
		temChangeInfo.setUnpublicOrgPartymbrInfoId(Integer.parseInt(id));
		temChangeInfo.setUnpublicOrgInfoId(Integer.parseInt(mainId));
		temChangeInfo.setStatus("1");
		UnpublicOrgPmbrChangeInfo pmbrChangeInfo = unpublicOrgPmbrChangeInfoMapper.selectOne(temChangeInfo);
		if(pmbrChangeInfo != null){
			pmbrInfo.setType(pmbrChangeInfo.getType());
		}
		UnpublicOrgPmbrOtherCount pmbrOtherConut = new UnpublicOrgPmbrOtherCount();
		pmbrOtherConut.setUnpublicOrgPartymbrInfoId(Integer.parseInt(id));
		pmbrOtherConut.setUnpublicOrgInfoId(Integer.parseInt(mainId));
		UnpublicOrgPmbrOtherCount temOtherCounts = unpublicOrgPmbrOtherCountMapper.selectOne(pmbrOtherConut);
		model.addAttribute("main", pmbrInfo);
		model.addAttribute("orgId", mainId);
		model.addAttribute("temOtherCounts", temOtherCounts);
		return "unpublic/editPartyMbr";
	}
	/**
	 * 修改党员信息
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/unpublic/editPartymbr",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editPartymbrSave(AddPmbrParams params){
		try {
			unPublicOrgService.editPartymbr(params);
			return returnMsg(1, "修改党员成功!");
		} catch (Exception e) {
			logger.debug("修改非公组织党员信息时出现异常:{}", e.getMessage(),e);
			return returnMsg(0, "修改党员失败：" + e.getMessage());
		}
		
		
	}
	/**
	 * 减少党员页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/unpublic/removePartymbr",method=RequestMethod.GET)
	public String removePartymbr(String id,Model model){
		UnpublicOrgPmbrInfo pmbrInfo = unpublicOrgPmbrInfoMapper.selectByPrimaryKey(id);
		model.addAttribute("id", id);
		model.addAttribute("pmbrInfo", pmbrInfo);
		return "unpublic/removePartyMbr";
	}
	
	/**
	 * 减少党员信息
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/unpublic/removePartymbr",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> removePartymbrSave(RemovePmbrParams params){
		
		try {
			unPublicOrgService.delPartymbr(params);
			return returnMsg(1, "减少党员成功!");
		} catch (Exception e) {
			logger.debug("减少非公组织党员信息时出现异常:{}", e.getMessage(),e);
			return returnMsg(0, "减少党员失败："+e.getMessage());
		}
				
	}
	
	@RequestMapping(value="/unpublic/importPartymbr",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> importPartymbrSave(@RequestParam("dataFile") CommonsMultipartFile file, String mainId,
			HttpServletResponse response){
		
		// 没数据，返回错误
		if (file.getSize() == 0) {
			return returnMsg(0, "请上传文件.");
		}
		
		String titleArr [] = {"姓名","性别","出生日期","学历","转入类型","组织关系在非公企业","中层管理人员",
				"中高级专业技术人员","生产经营一线职工","组织关系不在非公企业","农村党员"};
		
		int total = 0 , succ = 0 ,  fail = 0;
		String msg = "";
		try {
			List<Map<String,String>> readExcel = ExcelUtils.readExcel(titleArr, file.getInputStream());
			
			total = readExcel.size();
			//存入DB
			AddPmbrParams params = null;
			for (Map<String, String> map : readExcel) {
				params = new AddPmbrParams();
				
				params.setUnpublicOrgInfoId(Integer.parseInt(mainId));
				
				params.setName(map.get("姓名"));
				params.setGender(dict.getCodeByValue(CommonConstants.GENDER, map.get("性别")));
				params.setBirthday(map.get("出生日期"));
				params.setEducation(dict.getCodeByValue(CommonConstants.FINAL_EDUCATION,map.get("学历")));
				params.setType(dict.getCodeByValue(CommonConstants.PARTY_MBR_CHANGE_TYPE, map.get("转入类型")));
				
				params.setPartymbrInUnpublicIs(dict.getCodeByValue(CommonConstants.YES_NO, map.get("组织关系在非公企业")));
				params.setPartymbrMiddleManagerIs(dict.getCodeByValue(CommonConstants.YES_NO, map.get("中层管理人员")));
				params.setPartymbrOnMiddletechIs(dict.getCodeByValue(CommonConstants.YES_NO, map.get("中高级专业技术人员")));
				params.setPartymbrFrontlineIs(dict.getCodeByValue(CommonConstants.YES_NO, map.get("生产经营一线职工")));
				params.setPartymbrNotinUnpublicIs(dict.getCodeByValue(CommonConstants.YES_NO, map.get("组织关系不在非公企业")));
				params.setPartymbrInVillageIs(dict.getCodeByValue(CommonConstants.YES_NO, map.get("农村党员")));
				
				unPublicOrgService.addPartymbr(params);
				
				succ ++;
			}
			
			msg  = "共:" +total+ "条,导入成功:" + succ+ "条,失败:" +fail+ "条";
			return returnMsg(1, "导入成功!" + msg);
		} catch (IOException e) {
			e.printStackTrace();
			return returnMsg(0, "读取Excel出现错误:" + e.getMessage() + msg);
		}
	
	}
	
	/**
	 * 未更新党员信息说明
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/unpublic/noUpdateRel",method=RequestMethod.GET)
	public String noUpdateRel(Model model){
		
		return "unpublic/noUpdateRel";		
	}	
	
	@RequestMapping(value="/unpublic/noUpdateRel",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> noUpdateRelSave(String id, String remarks){
		Map<String, Object> resultMap = new HashMap<>();
		try{
			UnpublicOrgPmbrChangeInfo unpublicOrgPmbrChangeInfo = new UnpublicOrgPmbrChangeInfo();
			unpublicOrgPmbrChangeInfo.setUnpublicOrgInfoId(Integer.parseInt(id));
			unpublicOrgPmbrChangeInfo.setDescription(remarks);
			unpublicOrgPmbrChangeInfo.setCreator(baseService.getUserName());
			unpublicOrgPmbrChangeInfo.setCreateTime(new Date());
			unpublicOrgPmbrChangeInfo.setCreateOrg(baseService.getCurrentUserDeptId());
			unpublicOrgPmbrChangeInfoMapper.insertSelective(unpublicOrgPmbrChangeInfo);
			resultMap.put("status", 1);
			resultMap.put("msg", "提交成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("status", 0);
			resultMap.put("msg", "提交失败"+e.getMessage());
		}
		return resultMap;
	}
	
	/**
	 * 党员页面初始化参数
	 * @param model
	 */
	private void partyMbrInit(Model model){
		List<DataDictionary> genderList = dict.findByDmm(CommonConstants.GENDER);
		List<DataDictionary> changeTypeList = dict.findByDmm(CommonConstants.PARTY_MBR_CHANGE_TYPE);
		List<DataDictionary> educationList = dict.findByDmm(CommonConstants.FINAL_EDUCATION);
		List<DataDictionary> otherConditionList = dict.findByDmm(CommonConstants.UNPUBLIC_PM_OTHER_CONDITION);
		model.addAttribute("genderList", genderList);
		model.addAttribute("changeTypeList", changeTypeList);
		model.addAttribute("educationList", educationList);
		model.addAttribute("otherConditionList", otherConditionList);
	}
	
	/**
	 * 初始化参数
	 * @param model
	 */
	private void initPage(Model model){
			
		List<DataDictionary> yesNoList = dict.findByDmm(CommonConstants.YES_NO);
		List<DataDictionary> enterpriseTypeList = dict.findByDmm(CommonConstants.ENTERPRISE_TYPE);
		List<DataDictionary> zoneLevelList = dict.findByDmm(CommonConstants.ZONE_LEVEL);
		List<DataDictionary> otherConditionList = dict.findByDmm(CommonConstants.UNPUBLIC_ORG_OTHER_CONDITION);
		List<DataDictionary> partyorgStatusList = dict.findByDmm(CommonConstants.UNPUBLIC_ORG_STATUS);
		model.addAttribute("yesNoList", yesNoList);
		model.addAttribute("enterpriseTypeList", GeneralCalcUtils.convertMap(enterpriseTypeList));
		model.addAttribute("zoneLevelList", zoneLevelList);
		model.addAttribute("otherConditionList", otherConditionList);
		model.addAttribute("partyorgStatusList", partyorgStatusList);
		model.addAttribute("createOrgName", baseService.getDeptNameById(baseService.getCurrentUserDeptId()));
		model.addAttribute("createOrgId", baseService.getCurrentUserDeptId());
		// 批量上报开关 例如 ：0关闭 1开启 
		model.addAttribute("batchSwitch",dict.getValueByCode("UNPUBLIC_BATCH_SWITCH", "SWITCH"));
	}
	/**
	 * 编辑页面初始化参数
	 * @param model
	 */
	private void editPage(Model model){
		
		List<DataDictionary> yesNoList = dict.findByDmm(CommonConstants.YES_NO);
		List<DataDictionary> hasNotList = dict.findByDmm(CommonConstants.HAS_NOT);
		
		List<DataDictionary> enterpriseBelocatedAddressList = dict.findByDmm(CommonConstants.ENTERPRISE_BELOCATED_ADDRESS);
		List<DataDictionary> enterpriseTypeList = dict.findByDmm(CommonConstants.ENTERPRISE_TYPE);
		List<DataDictionary> notionalityList = dict.findByDmm(CommonConstants.NATIONALITY);
		List<DataDictionary> businessTypeList = dict.findByDmm(CommonConstants.BUSINESS_TYPE);
		List<DataDictionary> zoneLevelList = dict.findByDmm(CommonConstants.ZONE_LEVEL);
		List<DataDictionary> partyDeputyTypeList = dict.findByDmm(CommonConstants.PARTY_DEPUTY_TYPE);
		
		List<DataDictionary> addressLevelList = dict.findByDmm(CommonConstants.ADDRESS_LEVEL);
		List<DataDictionary> otherConditionList = dict.findByDmm(CommonConstants.UNPUBLIC_ORG_OTHER_CONDITION);
		
		
		model.addAttribute("yesNoList", yesNoList);
		model.addAttribute("hasNotList", hasNotList);
		
		model.addAttribute("enterpriseBelocatedAddressList", enterpriseBelocatedAddressList);
		model.addAttribute("enterpriseTypeList",  GeneralCalcUtils.convertMap(enterpriseTypeList));
		model.addAttribute("notionalityList", notionalityList);
		model.addAttribute("businessTypeList", businessTypeList);
		model.addAttribute("zoneLevelList", zoneLevelList);
		model.addAttribute("partyDeputyTypeList", partyDeputyTypeList);
		model.addAttribute("addressLevelList", addressLevelList);
		model.addAttribute("otherConditionList", otherConditionList);
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
	
	@RequestMapping(value = "/unpublic/exportUnpublicExcel")
	@ResponseBody
	private String exportUnpublicExcel(UnpublicOrgInfo params,HttpServletResponse response) {
		String filePath = exportFileService.createFilePath();
		String orderby = " main.status desc , main.create_time desc";
		if (!baseService.isQuWeiDept()) {
			params.setCreateOrg(baseService.getCurrentUserDeptId());
		}
		List<UnpublicOrgExportBean> list = unpublicOrgInfoMapper.queryExportList(params,orderby);
		if(list.size() > 0){
			//获取所有的市区
			String[] cityStr = CitysController.getListByCity().split(",");
			for(UnpublicOrgExportBean info : list){
				for(int i = 0; i < cityStr.length; i++){
					if(cityStr[i].contains(info.getRegisterAddressCity()) && info.getRegisterAddressCity().length() > 0){
						info.setRegisterAddressCity(cityStr[i].substring(cityStr[i].indexOf(":")+1));
					}
				}
				//获取企业经营地
				if(info.getOperateAddress().contains("^")){
					String temp = info.getOperateAddress().substring(info.getOperateAddress().lastIndexOf("^")+1);
					info.setOperateAddress(temp);
				}
			}
		}
		ExcelUtil<UnpublicOrgExportBean> excelUtils = new ExcelUtil<UnpublicOrgExportBean>(UnpublicOrgExportBean.class);
		try {
			excelUtils.writeToFile(list, filePath);
			return JSONObject.toJSONString(filePath);
		} catch (Exception e) {
			e.printStackTrace();
			return JSONObject.toJSONString("1");
		}
	}
	
	@RequestMapping(value = "/unpublic/exportUnpublicExcelFile")
	public void doDown(String filePath, HttpServletResponse response, HttpServletRequest request)throws Exception {
		try {
			filePath = java.net.URLDecoder.decode(filePath,"UTF-8");
			exportFileService.doDown(filePath, "unpublicOrg.xlsx", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
