package com.tf.base.socialorg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tf.base.common.constants.CommonConstants;
import com.tf.base.common.constants.CommonConstants.LOG_OPERATION_TYPE;
import com.tf.base.common.domain.DataDictionary;
import com.tf.base.common.domain.DataFile;
import com.tf.base.common.domain.DictionaryRepository;
import com.tf.base.common.persistence.DataFileMapper;
import com.tf.base.common.service.BaseService;
import com.tf.base.common.service.LogService;
import com.tf.base.common.utils.ExcelUtils;
import com.tf.base.common.utils.GeneralCalcUtils;
import com.tf.base.common.utils.PageUtil;
import com.tf.base.socialorg.domain.AddPmbrParams;
import com.tf.base.socialorg.domain.AllParams;
import com.tf.base.socialorg.domain.QueryPmbrParams;
import com.tf.base.socialorg.domain.RemovePmbrParams;
import com.tf.base.socialorg.domain.SocialOrgCancelRecord;
import com.tf.base.socialorg.domain.SocialOrgChargeInfo;
import com.tf.base.socialorg.domain.SocialOrgInfo;
import com.tf.base.socialorg.domain.SocialOrgInfoOtherCount;
import com.tf.base.socialorg.domain.SocialOrgJobinCount;
import com.tf.base.socialorg.domain.SocialOrgPmbrChangeInfo;
import com.tf.base.socialorg.domain.SocialOrgPmbrCount;
import com.tf.base.socialorg.domain.SocialOrgPmbrInfo;
import com.tf.base.socialorg.persistence.SocialOrgCancelRecordMapper;
import com.tf.base.socialorg.persistence.SocialOrgChargeInfoMapper;
import com.tf.base.socialorg.persistence.SocialOrgInfoMapper;
import com.tf.base.socialorg.persistence.SocialOrgInfoOtherCountMapper;
import com.tf.base.socialorg.persistence.SocialOrgJobinCountMapper;
import com.tf.base.socialorg.persistence.SocialOrgPmbrChangeInfoMapper;
import com.tf.base.socialorg.persistence.SocialOrgPmbrCountMapper;
import com.tf.base.socialorg.persistence.SocialOrgPmbrInfoMapper;
import com.tf.base.socialorg.service.OrgService;

import tk.mybatis.mapper.entity.Example;

@Controller
public class OrgController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DictionaryRepository dictionaryRepository;
	
	@Autowired
	private BaseService baseService;
	@Autowired
	private LogService logService;
	
	@Autowired
	private DictionaryRepository dict;
	
	@Autowired
	private DataFileMapper dataFileMapper;
	
	@Autowired
	private SocialOrgInfoMapper socialOrgInfoMapper;
	@Autowired
	private SocialOrgJobinCountMapper socialOrgJobinCountMapper;
	@Autowired
	private SocialOrgCancelRecordMapper socialOrgCancelRecordMapper;
	@Autowired
	private SocialOrgPmbrCountMapper socialOrgPmbrCountMapper;
	@Autowired
	private SocialOrgChargeInfoMapper socialOrgChargeInfoMapper;
	@Autowired
	private SocialOrgPmbrInfoMapper socialOrgPmbrInfoMapper;
	@Autowired
	private SocialOrgInfoOtherCountMapper socialOrgInfoOtherCountMapper;
	@Autowired
	private OrgService orgService;
	@Autowired
	private SocialOrgPmbrChangeInfoMapper socialOrgPmbrChangeInfoMapper;
	
	/**
	 * 初始化页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/socialorg/orglist",method=RequestMethod.GET)
	public String orginit(Model model){
		editPage(model);
		return "socialorg/orgList";
	}
	
	/**
	 * 组织信息列表
	 * @param params
	 * @param page
	 * @param rows
	 * @param response
	 */
	@RequestMapping(value="/socialorg/orglist",method=RequestMethod.POST)
	public void orglist(SocialOrgInfo params, int page,int rows,
			HttpServletRequest request, HttpServletResponse response){
		String deptId = baseService.getCurrentUserDeptId();
		logger.debug("当前登录用户部门ID:===========>" + deptId + " ,是否为区委部门?" + baseService.isQuWeiDept());
		String orderby = "";
		//查看
		if(baseService.isQuWeiDept()){
			//查询所有数据
			orderby = " main.status desc, main.create_time desc";
		//编辑
		}else{
			//根据deptId查询数据
			params.setCreateOrg(deptId);
			
			orderby = " main.update_time desc , main.create_time desc";
		}
		PageHelper.startPage(page, rows, true);
		List<SocialOrgInfo> list = socialOrgInfoMapper.queryList(params,orderby);
		this.convertRows(list);
		PageUtil.returnPage(response, new PageInfo<SocialOrgInfo>(list));
		
	}

	/**
	 * 查看组织信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/socialorg/orglook",method=RequestMethod.GET)
	public String orglook(String id,Model model){
		Integer mainId = Integer.parseInt(id);
		//基础信息
		SocialOrgInfo main = socialOrgInfoMapper.selectByPrimaryKey(mainId);
		main.setStatusTxt(dictionaryRepository.getValueByCode(CommonConstants.SOCIAL_ORG_STATUS, main.getStatus()));
		main.setNatureTxt(dictionaryRepository.getValueByCode(CommonConstants.ORG_NATURE,main.getNature()));
		main.setCategoryTxt(dictionaryRepository.getValueByCode(CommonConstants.ORG_CATEGORY, main.getCategory()));
		main.setIsIdeologicalPoliticalOrgTxt(dictionaryRepository.getValueByCode(CommonConstants.YES_NO,main.getIsIdeologicalPoliticalOrg()));
		main.setIsMoralEducationOrgTxt(dictionaryRepository.getValueByCode(CommonConstants.YES_NO,main.getIsMoralEducationOrg()));
		//从业人员
		SocialOrgJobinCount count = new SocialOrgJobinCount();
		count.setSocialOrgInfoId(mainId);
		count = socialOrgJobinCountMapper.selectOne(count);
		//负责人信息
		SocialOrgChargeInfo charge = new SocialOrgChargeInfo();
		charge.setSocialOrgInfoId(mainId);
		charge = socialOrgChargeInfoMapper.selectOne(charge);
		charge.setChargePartymemberIsTxt(dictionaryRepository.getValueByCode(CommonConstants.YES_NO, charge.getChargePartymemberIs()));
		charge.setChargePartyorgSecretaryIsTxt(dictionaryRepository.getValueByCode(CommonConstants.YES_NO, charge.getChargePartyorgSecretaryIs()));
		charge.setChargeTwodeputyAcommitteeIsTxt(dictionaryRepository.getValueByCode(CommonConstants.YES_NO, charge.getChargeTwodeputyAcommitteeIs()));
		
		//负责人类型
		String deputyType = charge.getChargeTwodeputyAcommitteeType();
		if(StringUtils.isNotEmpty(deputyType)){
			model.addAttribute("deputyTypes", Arrays.asList(deputyType.split(",")));
		}
		
		//党员统计信息
		SocialOrgPmbrCount pmbrCount = new SocialOrgPmbrCount();
		pmbrCount.setSocialOrgInfoId(mainId);
		pmbrCount = socialOrgPmbrCountMapper.selectOne(pmbrCount);
		
		//临时统计项
		SocialOrgInfoOtherCount otherCount = new SocialOrgInfoOtherCount();
		otherCount.setSocialOrgInfoId(mainId);
		otherCount.setFieldValue(1);
		List<SocialOrgInfoOtherCount> otherCounts = socialOrgInfoOtherCountMapper.select(otherCount);
		List<String> others =new ArrayList<>();
		for (SocialOrgInfoOtherCount c : otherCounts) {
			others.add(c.getFieldName());
		}
		String otherStr = StringUtils.join(others.toArray(),",");
		String otherCondition = GeneralCalcUtils.splitConvertField(dict,CommonConstants.SOCIAL_ORG_OTHER_CONDITION,otherStr);
		main.setOtherCondition(otherCondition);
		
		model.addAttribute("main", main);
		model.addAttribute("count", count);
		model.addAttribute("charge", charge);
		model.addAttribute("pmbrCount", pmbrCount);
		
		//查看日志
		logService.saveLog(LOG_OPERATION_TYPE.VIEW.toString(), 
				logService.getDetailInfo("log.socialorg.view",
						baseService.getUserName(),main.getName()));
		editPage(model);
		return "socialorg/orgLook";
	}
	/**
	 * 组织信息编辑页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/socialorg/orgedit",method=RequestMethod.GET)
	public String orgedit(String id, String clickSign, Model model){
		boolean flag = false;
		if(!StringUtils.isEmpty(id)){
			Integer mainId = Integer.parseInt(id);
			//基础信息
			SocialOrgInfo main = socialOrgInfoMapper.selectByPrimaryKey(mainId);
			//从业人员
			SocialOrgJobinCount count = new SocialOrgJobinCount();
			count.setSocialOrgInfoId(mainId);
			count = socialOrgJobinCountMapper.selectOne(count);
			//负责人信息
			SocialOrgChargeInfo charge = new SocialOrgChargeInfo();
			charge.setSocialOrgInfoId(mainId);
			charge = socialOrgChargeInfoMapper.selectOne(charge);
			//负责人类型
			String deputyType = charge.getChargeTwodeputyAcommitteeType();
			if(StringUtils.isNotEmpty(deputyType)){
				model.addAttribute("deputyTypes", Arrays.asList(deputyType.split(",")));
			}
			
			//党员统计信息
			SocialOrgPmbrCount pmbrCount = new SocialOrgPmbrCount();
			pmbrCount.setSocialOrgInfoId(mainId);
			pmbrCount = socialOrgPmbrCountMapper.selectOne(pmbrCount);
			
			SocialOrgInfoOtherCount otherCount = new SocialOrgInfoOtherCount();
			otherCount.setSocialOrgInfoId(mainId);
			otherCount.setFieldValue(1);
			List<SocialOrgInfoOtherCount> otherCounts = socialOrgInfoOtherCountMapper.select(otherCount);
			//查看该组织下是否有党员
			Example example = new Example(SocialOrgPmbrInfo.class);
			example.setOrderByClause("createTime desc");
			example.createCriteria().andEqualTo("socialOrgInfoId", Integer.parseInt(id)).andEqualTo("status", "1");
			List<SocialOrgPmbrInfo> unpublicOrgPmbrInfos = socialOrgPmbrInfoMapper.selectByExample(example);
			if(unpublicOrgPmbrInfos.size() > 0){
				flag = true;
			}
			model.addAttribute("main", main);
			model.addAttribute("count", count);
			model.addAttribute("charge", charge);
			model.addAttribute("pmbrCount", pmbrCount);
			model.addAttribute("otherCounts", otherCounts);
			model.addAttribute("flag", flag);
			model.addAttribute("clickSign", clickSign);
		}else{
			SocialOrgInfo main = new SocialOrgInfo();
			main.setBusinessDirectorOrg(baseService.getDeptName());
			model.addAttribute("main", main);
			model.addAttribute("flag", flag);
		}
		editPage(model);
		return "socialorg/orgEdit";
	}
	/**
	 * 编辑组织
	 * @param model
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/socialorg/orgedit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> orgSave(Model model,AllParams params){

		SocialOrgInfo main = new SocialOrgInfo();
		BeanUtils.copyProperties(params, main);
		if(StringUtils.isNotEmpty(params.getMainId())){
			main.setId(Integer.parseInt(params.getMainId()));
		}
		
		
		SocialOrgJobinCount count = new SocialOrgJobinCount();
		BeanUtils.copyProperties(params, count);
		
		SocialOrgChargeInfo charge = new SocialOrgChargeInfo();
		BeanUtils.copyProperties(params, charge);
		
		SocialOrgPmbrCount pmbrCount = new SocialOrgPmbrCount();
		BeanUtils.copyProperties(params, pmbrCount);
		
		Integer mainId = main.getId();
		
		if(params.getReportHigher().intValue() == 1){
			main.setStatus("5");//上报
		}else{
			main.setStatus("1");//临时保存
		}
		if(mainId == null || StringUtils.isEmpty(mainId.toString())){
			try {
				orgService.addOrg(main, count,charge,pmbrCount,params);
				return returnMsg(1, "新增成功!");
			} catch (Exception e) {
				logger.debug("新增社会组织信息时出现异常:{}", e.getMessage(),e);
				return returnMsg(0, "新增失败:" + e.getMessage());
			}
			
		}else{
			try {
				orgService.updateOrg(main, count,charge,pmbrCount ,params);
				return returnMsg(1, "修改成功!");
			} catch (Exception e) {
				logger.debug("修改社会组织信息时出现异常:{}", e.getMessage(),e);
				return returnMsg(0, "修改失败:" + e.getMessage());
			}
			
		}
		
	}
	
	@RequestMapping(value="/socialorg/orgSetStatus",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> orgSetStatus(Model model,String partyOrgIds,String status){
		String[] partyOrgIdArray = partyOrgIds.split(",");
		for(int i = 0; i < partyOrgIdArray.length; i++){
			SocialOrgInfo info = socialOrgInfoMapper.selectByPrimaryKey(partyOrgIdArray[i]);
			info.setStatus(status);
			info.setUpdateTime(new Date());
			info.setUpdator(baseService.getUserName());
			socialOrgInfoMapper.updateByPrimaryKeySelective(info);
		}
		return returnMsg(1, "操作成功!");
	}
	/**
	 * 删除组织
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/socialorg/orgdelete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> orgdelete(Model model,String partyOrgIds){
		String[] partyOrgIdArray = partyOrgIds.split(",");
		try {
			orgService.delOrg(partyOrgIdArray);
			for(int i = 0; i < partyOrgIdArray.length; i++){
				SocialOrgInfo main = socialOrgInfoMapper.selectByPrimaryKey(partyOrgIdArray[i]);
				//删除日志
				logService.saveLog(LOG_OPERATION_TYPE.DELETE.toString(), 
						logService.getDetailInfo("log.socialorg.delete",
								baseService.getUserName(),main.getName()));
			}
			
			return returnMsg(1, "删除成功!");
		} catch (Exception e) {
			logger.debug("删除社会组织信息时出现异常:{}", e.getMessage(),e);
			return returnMsg(0, "删除失败："+e.getMessage());
		}
	}
	

	/**
	 * 撤销页面
	 * @param id
	 * @param method
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/socialorg/orgcancel",method=RequestMethod.GET)
	public String orgcancel(String id,String method,Model model){
		
		Example example = new Example(SocialOrgCancelRecord.class);
		example.setOrderByClause("createTime desc");
		example.createCriteria().andEqualTo("socialOrgInfoId", Integer.parseInt(id));
		List<SocialOrgCancelRecord> socialOrgCancelRecords = socialOrgCancelRecordMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(socialOrgCancelRecords)){
			model.addAttribute("reason", socialOrgCancelRecords.get(0));
		}
		
		model.addAttribute("id", id);
		model.addAttribute("method", method);
		return "socialorg/orgCancel";
	}
	/**
	 * 撤销页面文件列表
	 * @param id
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/socialorg/cancelFile",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> cancelFile(String id,
			int page,int rows){
		
		Map<String,Object> result = new HashMap<>();
		
		int start = (page-1)*rows;
		
		Example example = new Example(DataFile.class);
		example.createCriteria().andEqualTo("model", "1")//社会组织信息
		.andEqualTo("modelTbId", id).andEqualTo("status", 1);
		
		int total = dataFileMapper.selectCountByExample(example);
		if(total == 0){
			result.put("rows", new ArrayList<>());
			result.put("total", total);
			return result;
		}
		List<DataFile> dataFiles = dataFileMapper.selectByExampleAndRowBounds(example, new RowBounds(start, rows));
		result.put("total", total);
		result.put("rows", dataFiles);
		return result;
		
	}
	
	/**
	 * 撤销
	 * @param model
	 * @param id
	 * @param remarks
	 * @return
	 */
	@RequestMapping(value="/socialorg/orgcancel",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> orgCancel(Model model,String id,String remarks){
		
		try {
			orgService.cancelOrg(id,remarks);
			
			SocialOrgInfo main = socialOrgInfoMapper.selectByPrimaryKey(id);
			//撤销日志
			logService.saveLog(LOG_OPERATION_TYPE.DELETE.toString(), 
					logService.getDetailInfo("log.socialorg.cancel",
							baseService.getUserName(),main.getName()));
			return returnMsg(1, "撤销成功，等待上级部门审核！");
		} catch (Exception e) {
			logger.debug("撤销社会组织信息时出现异常:{}", e.getMessage(),e);
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
	@RequestMapping(value="/socialorg/nocancel",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> nocancel(Model model,String id,String remarks){
		
		SocialOrgInfo main = socialOrgInfoMapper.selectByPrimaryKey(Integer.parseInt(id));
		main.setStatus("2");
		main.setUpdateTime(new Date());
		main.setUpdator(baseService.getUserName());
		socialOrgInfoMapper.updateByPrimaryKey(main);
		
		try {
			
			main = socialOrgInfoMapper.selectByPrimaryKey(id);
			//取消撤销日志
			logService.saveLog(LOG_OPERATION_TYPE.DELETE.toString(), 
					logService.getDetailInfo("log.socialorg.nocancel",
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
	@RequestMapping(value="/socialorg/cancelok",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> cancelok(Model model,String partyOrgIds,String remarks){
		
		try {
			String[] partyOrgIdArray = partyOrgIds.split(",");
			for(int i = 0; i < partyOrgIdArray.length; i++){
				SocialOrgInfo main = socialOrgInfoMapper.selectByPrimaryKey(Integer.parseInt(partyOrgIdArray[i]));
				main.setStatus("4");//已撤销
				main.setUpdateTime(new Date());
				main.setUpdator(baseService.getUserName());
				socialOrgInfoMapper.updateByPrimaryKey(main);
				
				main = socialOrgInfoMapper.selectByPrimaryKey(partyOrgIdArray[i]);
				//撤销审核通过日志
				logService.saveLog(LOG_OPERATION_TYPE.MODIFY.toString(), 
						logService.getDetailInfo("log.socialorg.cancelok",
								baseService.getUserName(),main.getName()));
			}
			return returnMsg(1, "审核通过，撤销该组织成功！");
		} catch (Exception e) {
			logger.debug("撤销审核通过社会组织信息时出现异常:{}", e.getMessage(),e);
			return returnMsg(0, "撤销失败:" + e.getMessage());
		}
		
	}
	
	/**
	 * 党员信息页面
	 * @param id
	 * @param method
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/socialorg/partyMbrList",method=RequestMethod.GET)
	public String topartyMbrList(String id,String method,Model model){
		SocialOrgInfo main = socialOrgInfoMapper.selectByPrimaryKey(id);
		List<DataDictionary> otherConditionList = dict.findByDmm(CommonConstants.SOCIAL_PM_OTHER_CONDITION);
		
		model.addAttribute("id", id);
		model.addAttribute("method", method);
		model.addAttribute("main", main);
		model.addAttribute("otherConditionList", otherConditionList);
		return "socialorg/partyMbrList";
	}
	/**
	 * 党员列表
	 * @param response
	 * @param id
	 * @param page
	 * @param rows
	 * @param params
	 */
	@RequestMapping(value="/socialorg/partyMbrList",method=RequestMethod.POST)
	public void partyMbrList(HttpServletResponse response,String id,int page,int rows,QueryPmbrParams params){
		
		params.setSocialOrgInfoId(Integer.parseInt(id));
		PageHelper.startPage(page, rows, true);
		List<SocialOrgPmbrInfo> list = socialOrgPmbrInfoMapper.queryList(params);
		for (SocialOrgPmbrInfo socialOrgPmbrInfo : list) {
			socialOrgPmbrInfo.setGender(dictionaryRepository.getValueByCode(CommonConstants.GENDER, socialOrgPmbrInfo.getGender()));
			socialOrgPmbrInfo.setPartymbrGroupInSocialorgIs(dict.getValueByCode(CommonConstants.YES_NO, socialOrgPmbrInfo.getPartymbrGroupInSocialorgIs()));
			socialOrgPmbrInfo.setPartymbrInSocialorgIs(dict.getValueByCode(CommonConstants.YES_NO, socialOrgPmbrInfo.getPartymbrInSocialorgIs()));
			socialOrgPmbrInfo.setEducation(dict.getValueByCode(CommonConstants.FINAL_EDUCATION, socialOrgPmbrInfo.getEducation()));
		}
		PageUtil.returnPage(response, new PageInfo<SocialOrgPmbrInfo>(list));
	}
	
	/**
	 * 新增党员页面
	 * @param mainId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/socialorg/addPartymbr",method=RequestMethod.GET)
	public String addPartymbr(String mainId,Model model){
		
		partyMbrInit(model);
		model.addAttribute("mainId", mainId);
		return "socialorg/addPartyMbr";
	}
	
	/**
	 * 新增党员
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/socialorg/addPartymbr",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addPartymbrSave(AddPmbrParams params){

		try {
			
			orgService.addPartymbr(params);
			
			return returnMsg(1, "添加党员成功!");
		} catch (Exception e) {
			logger.debug("新增社会组织党员信息时出现异常:{}", e.getMessage(),e);
			return returnMsg(0, "添加党员失败：" + e.getMessage());
		}
		
	}
	
	@RequestMapping(value="/socialorg/removePartymbr",method=RequestMethod.GET)
	public String removePartymbr(String id,Model model){
		SocialOrgPmbrInfo pmbrInfo = socialOrgPmbrInfoMapper.selectByPrimaryKey(id);
		model.addAttribute("id", id);
		model.addAttribute("pmbrInfo", pmbrInfo);
		return "socialorg/removePartyMbr";
	}
	@RequestMapping(value="/socialorg/removePartymbr",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> removePartymbrSave(RemovePmbrParams params){
		
		try {
			orgService.delPartymbr(params);
			return returnMsg(1, "减少党员成功!");
		} catch (Exception e) {
			logger.debug("减少社会组织党员信息时出现异常:{}", e.getMessage(),e);
			return returnMsg(0, "减少党员失败："+e.getMessage());
		}
				
	}
	
	@RequestMapping(value="/socialorg/importPartymbr",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> importPartymbrSave(@RequestParam("dataFile") CommonsMultipartFile file, String mainId,
			HttpServletResponse response){
		
		// 没数据，返回错误
		if (file.getSize() == 0) {
			return returnMsg(0, "请上传文件.");
		}
		
		String titleArr [] = {"姓名","性别","出生日期","学历","转入类型","是否纳入社会组织党组织管理","是否组织关系在社会组织党组织"};
		
		int total = 0 , succ = 0 ,  fail = 0;
		String msg = "";
		try {
			List<Map<String,String>> readExcel = ExcelUtils.readExcel(titleArr, file.getInputStream());
			
			total = readExcel.size();
			//存入DB
			AddPmbrParams params = null;
			for (Map<String, String> map : readExcel) {
				params = new AddPmbrParams();
				
				params.setSocialOrgInfoId(Integer.parseInt(mainId));
				
				params.setName(map.get("姓名"));
				params.setGender(dict.getCodeByValue(CommonConstants.GENDER, map.get("性别")));
				params.setBirthday(map.get("出生日期"));
				params.setEducation(dict.getCodeByValue(CommonConstants.FINAL_EDUCATION,map.get("学历")));
				params.setType(dict.getCodeByValue(CommonConstants.PARTY_MBR_CHANGE_TYPE, map.get("转入类型")));
				
				params.setPartymbrInSocialorgIs(dict.getCodeByValue(CommonConstants.YES_NO, map.get("是否纳入社会组织党组织管理")));
				params.setPartymbrGroupInSocialorgIs(dict.getCodeByValue(CommonConstants.YES_NO, map.get("是否组织关系在社会组织党组织")));
				
				orgService.addPartymbr(params);;
				
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
	@RequestMapping(value="/socialorg/noUpdateRel",method=RequestMethod.GET)
	public String noUpdateRel(String id, Model model){
		model.addAttribute("id", id);
		return "socialorg/noUpdateRel";		
	}	
	
	@RequestMapping(value="/socialorg/noUpdateRel",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> noUpdateRelSave(String id, String remarks){
		Map<String, Object> resultMap = new HashMap<>();
		try{
			SocialOrgPmbrChangeInfo socialOrgPmbrChangeInfo = new SocialOrgPmbrChangeInfo();
			socialOrgPmbrChangeInfo.setSocialOrgInfoId(Integer.parseInt(id));
			socialOrgPmbrChangeInfo.setDescription(remarks);
			socialOrgPmbrChangeInfo.setCreator(baseService.getUserName());
			socialOrgPmbrChangeInfo.setCreateTime(new Date());
			socialOrgPmbrChangeInfo.setCreateOrg(baseService.getCurrentUserDeptId());
			socialOrgPmbrChangeInfoMapper.insertSelective(socialOrgPmbrChangeInfo);
			resultMap.put("status", 1);
			resultMap.put("msg", "提交成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("status", 0);
			resultMap.put("msg", "提交失败"+ e.getMessage());
		}
		;
		return resultMap;
	}
	
	private void partyMbrInit(Model model){
		List<DataDictionary> genderList = dictionaryRepository.findByDmm(CommonConstants.GENDER);
		List<DataDictionary> changeTypeList = dictionaryRepository.findByDmm(CommonConstants.PARTY_MBR_CHANGE_TYPE);
		List<DataDictionary> educationList = dictionaryRepository.findByDmm(CommonConstants.FINAL_EDUCATION);
		List<DataDictionary> otherConditionList = dict.findByDmm(CommonConstants.SOCIAL_PM_OTHER_CONDITION);
		model.addAttribute("genderList", genderList);
		model.addAttribute("changeTypeList", changeTypeList);
		model.addAttribute("educationList", educationList);
		model.addAttribute("otherConditionList", otherConditionList);
	}
	private void editPage(Model model){
		
		List<DataDictionary> yesNoList = dictionaryRepository.findByDmm(CommonConstants.YES_NO);
		List<DataDictionary> hasNotList = dictionaryRepository.findByDmm(CommonConstants.HAS_NOT);
		List<DataDictionary> orgNatureList = dictionaryRepository.findByDmm(CommonConstants.ORG_NATURE);
		List<DataDictionary> orgCategoryList = dictionaryRepository.findByDmm(CommonConstants.ORG_CATEGORY);
		List<DataDictionary> partyDeputyTypeList = dictionaryRepository.findByDmm(CommonConstants.PARTY_DEPUTY_TYPE);
		List<DataDictionary> otherConditionList = dict.findByDmm(CommonConstants.SOCIAL_ORG_OTHER_CONDITION);
		List<DataDictionary> partyorgStatusList = dict.findByDmm(CommonConstants.UNPUBLIC_ORG_STATUS);
		
		model.addAttribute("yesNoList", yesNoList);
		model.addAttribute("hasNotList", hasNotList);
		
		model.addAttribute("orgNatureList", GeneralCalcUtils.convertMap(orgNatureList));
		model.addAttribute("orgCategoryList", orgCategoryList);
		model.addAttribute("partyDeputyTypeList", partyDeputyTypeList);
		model.addAttribute("otherConditionList", otherConditionList);
		model.addAttribute("partyorgStatusList", partyorgStatusList);
		model.addAttribute("createOrgName", baseService.getDeptNameById(baseService.getCurrentUserDeptId()));
		model.addAttribute("createOrgId", baseService.getCurrentUserDeptId());
		// 批量上报开关 例如 ：0关闭 1开启 
		model.addAttribute("batchSwitch",dict.getValueByCode("SOCIAL_BATCH_SWITCH", "SWITCH"));
	}
	
	/**
	 * 转换数据集合
	 * @param rows
	 */
	private void convertRows(List<SocialOrgInfo> rows) {
		for (SocialOrgInfo info : rows) {
			this.convertRow(info);
		}
	}


	/**
	 * 转换单条数据
	 * @param info
	 */
	private void convertRow(SocialOrgInfo info) {

		info.setNatureTxt(dictionaryRepository.getValueByCode(CommonConstants.ORG_NATURE,info.getNature()));
		info.setCategoryTxt(dictionaryRepository.getValueByCode(CommonConstants.ORG_CATEGORY,info.getCategory()));
		info.setStatusTxt(dictionaryRepository.getValueByCode(CommonConstants.SOCIAL_ORG_STATUS,info.getStatus()));
		info.setCreateOrgTxt(baseService.getDeptNameById(info.getCreateOrg()));
		info.setIsIdeologicalPoliticalOrgTxt(dictionaryRepository.getValueByCode(CommonConstants.YES_NO,info.getIsIdeologicalPoliticalOrg()));
		info.setIsMoralEducationOrgTxt(dictionaryRepository.getValueByCode(CommonConstants.YES_NO,info.getIsMoralEducationOrg()));
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
