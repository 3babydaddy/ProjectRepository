package com.tf.base.socialorg.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tf.base.common.constants.CommonConstants;
import com.tf.base.common.constants.CommonConstants.LOG_OPERATION_TYPE;
import com.tf.base.common.domain.DataDictionary;
import com.tf.base.common.domain.DictionaryRepository;
import com.tf.base.common.service.BaseService;
import com.tf.base.common.service.LogService;
import com.tf.base.common.utils.PageUtil;
import com.tf.base.socialorg.domain.SocialPartyOrgBuilding;
import com.tf.base.socialorg.domain.SocialPartyOrgInfo;
import com.tf.base.socialorg.persistence.SocialPartyOrgBuildingMapper;
import com.tf.base.socialorg.persistence.SocialPartyOrgInfoMapper;
import com.tf.base.socialorg.service.PartyConstructionService;
import com.tf.base.unpublic.domain.AttachmentCommonInfo;
import com.tf.base.unpublic.domain.CancelReasonInfo;
import com.tf.base.unpublic.domain.UnpublicPartyOrgInfo;
import com.tf.base.unpublic.persistence.AttachmentCommonInfoMapper;
import com.tf.base.unpublic.persistence.CancelReasonInfoMapper;
import tk.mybatis.mapper.entity.Example;

@Controller
public class PartyConstructionController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DictionaryRepository dict;
	
	@Autowired
	private BaseService baseService;
	@Autowired
	private LogService logService;
	@Autowired
	private PartyConstructionService partyConstructionService;
	@Autowired
	private CancelReasonInfoMapper cancelReasonInfoMapper;
	@Autowired
	private SocialPartyOrgBuildingMapper socialPartyOrgBuildingMapper;
	@Autowired
	private SocialPartyOrgInfoMapper socialPartyOrgInfoMapper;
	@Autowired
	private AttachmentCommonInfoMapper attachmentCommonInfoMapper;

	
	/**
	 * 初始化页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/socialorg/partyconstructionlist",method=RequestMethod.GET)
	public String orginit(Model model){
		
		editPage(model);
		return "socialorg/partyConstructionList";
	}
	
	/**
	 * 年度党建情况列表展示
	 * @param params
	 * @param page
	 * @param rows
	 * @param response
	 */
	@RequestMapping(value="/socialorg/partyconstructionlist",method=RequestMethod.POST)
	public void orglist(SocialPartyOrgBuilding params, int page,int rows,HttpServletResponse response){
		String deptId = baseService.getCurrentUserDeptId();
		logger.debug("当前登录用户部门ID:===========>" + deptId + " ,是否为区委部门?" + baseService.isQuWeiDept());
		
		if(!baseService.isQuWeiDept()){
			params.setCreateOrg(deptId);
		}else{
			//工委列表展示过滤掉党组织状态为正常；但还没有新建党建的数据
			params.setIsQuWeiSign("1");
		}
		PageHelper.startPage(page, rows, true);
		List<SocialPartyOrgBuilding> list = socialPartyOrgBuildingMapper.queryList(params);
		this.convertRows(list);
		PageUtil.returnPage(response, new PageInfo<SocialPartyOrgBuilding>(list));
		
		
	}
	
	/**
	 * 查看年度党建情况
	 * @param id
	 * @param nature
	 * @param partyOrgName
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/socialorg/partyconstructionlook",method=RequestMethod.GET)
	public String orglook(String id, String nature, String partyOrgName, Model model)throws Exception{
		Integer mainId = Integer.parseInt(id);
		SocialPartyOrgBuilding main = socialPartyOrgBuildingMapper.selectByPrimaryKey(mainId);
		
		main.setPartyOrgName(new String(partyOrgName.getBytes("iso-8859-1"),"utf-8"));
		model.addAttribute("main", main);
		model.addAttribute("nature", nature);
		editPage(model);
		//查看日志
		logService.saveLog(LOG_OPERATION_TYPE.VIEW.toString(), 
				logService.getDetailInfo("log.socialorg.view",
						baseService.getUserName(),main.getPartyOrgName()));
		return "socialorg/partyConstructionLook";
	}
	
	/**
	 * 新增或者编辑年度党建情况
	 * @param id
	 * @param nature
	 * @param partyOrgId
	 * @param partyOrgName
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/socialorg/partyconstructionedit",method=RequestMethod.GET)
	public String orgedit(String id, String nature, String partyOrgId, String partyOrgName, String clickSign, Model model)throws Exception{
		
		if(!StringUtils.isEmpty(id)){
			Integer mainId = Integer.parseInt(id);
			SocialPartyOrgBuilding main = socialPartyOrgBuildingMapper.selectByPrimaryKey(mainId);
			main.setPartyOrgId(main.getSocialPartyOrgId()+"");
			SocialPartyOrgInfo info = socialPartyOrgInfoMapper.selectByPrimaryKey(main.getSocialPartyOrgId());
			main.setPartyOrgName(info.getPartyOrgName());
			model.addAttribute("main", main);
			model.addAttribute("nature", nature);
			model.addAttribute("clickSign", clickSign);
		}else{
			SocialPartyOrgBuilding main = new SocialPartyOrgBuilding();
			main.setPartyOrgId(Integer.parseInt(partyOrgId)+"");
			main.setPartyOrgName(new String(partyOrgName.getBytes("iso-8859-1"),"utf-8"));
			model.addAttribute("main", main);
			model.addAttribute("nature", nature);
		}
		editPage(model);
		return "socialorg/partyConstructionEdit"; 
	}
	
	/**
	 * 保存年度党建情况
	 * @param model
	 * @param main
	 * @return
	 */
	@RequestMapping(value="/socialorg/partyconstructionedit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> orgSave(Model model,SocialPartyOrgBuilding main){
		String msg = "";
		Integer mainId = main.getId();
		
		if(main.getReportHigher() == 1){
			main.setStatus("5");//上报申请中
			msg = "上报成功";
		}else{
			main.setStatus("1");//临时保存
			msg = "新增成功";
		}
		if(mainId == null || StringUtils.isEmpty(mainId.toString())){
			
			try {
				partyConstructionService.addOrg(main);
				return returnMsg(1, msg);
			} catch (Exception e) {
				logger.debug("新增非公党建信息时出现异常:{}", e.getMessage(),e);
				return returnMsg(0, "新增失败:" + e.getMessage());
			}
		}else{
			if(main.getReportHigher() == 0){
				msg = "修改成功";
			}
			try {
				partyConstructionService.updateOrg(main);
				return returnMsg(1, msg);
			} catch (Exception e) {
				logger.debug("修改非公党建信息时出现异常:{}", e.getMessage(),e);
				return returnMsg(0, "修改失败:" + e.getMessage());
			}
		}
		
	}
	/**
	 * 年度党建上报审核
	 * @param model
	 * @param partyOrgIds
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/socialorg/partyConstructionsSetStatus",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> orgsSetStatus(Model model,String partyOrgIds,String status){
		String[] partyOrgArray = partyOrgIds.split(",");
		for(int i = 0; i < partyOrgArray.length; i++){
			if(partyOrgArray[i] != null && partyOrgArray[i].length() > 0){
				SocialPartyOrgBuilding info = socialPartyOrgBuildingMapper.selectByPrimaryKey(Integer.parseInt(partyOrgArray[i]));
				info.setStatus(status);
				socialPartyOrgBuildingMapper.updateByPrimaryKeySelective(info);
			}
		}
		return returnMsg(1, "操作成功!");
	}
	/**
	 * 年度党建退回
	 * @param model
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/socialorg/partyConstructionSetStatus",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> orgSetStatus(Model model,String id,String status){
		SocialPartyOrgBuilding info = socialPartyOrgBuildingMapper.selectByPrimaryKey(Integer.parseInt(id));
		info.setStatus(status);
		socialPartyOrgBuildingMapper.updateByPrimaryKeySelective(info);
		return returnMsg(1, "操作成功!");
	}
	
	/**
	 * 删除年度党建情况
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/socialorg/partyconstructiondelete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> orgdelete(Model model,String id){
		try {
			SocialPartyOrgBuilding info = socialPartyOrgBuildingMapper.selectByPrimaryKey(Integer.parseInt(id));
			info.setStatus("0");
			socialPartyOrgBuildingMapper.updateByPrimaryKeySelective(info);
			//删除日志
			logService.saveLog(LOG_OPERATION_TYPE.DELETE.toString(), 
					logService.getDetailInfo("log.socialorg.delete",
							baseService.getUserName(),info.getPartyOrgName()));
			
			return returnMsg(1, "删除成功!");
		} catch (Exception e) {
			logger.debug("删除非公组织信息时出现异常:{}", e.getMessage(),e);
			return returnMsg(0, "删除失败："+e.getMessage());
		}
	}
	
	
	/**
	 * 撤销年度党建情况页面
	 * @param id
	 * @param method
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/socialorg/partyconstructioncancel",method=RequestMethod.GET)
	public String orgcancel(String id,String method,Model model){
		Example example = new Example(CancelReasonInfo.class);
		example.setOrderByClause("createTime desc");
		example.createCriteria().andEqualTo("partyOrgId", Integer.parseInt(id)).andEqualTo("type", '1').andEqualTo("status", 1);
		List<CancelReasonInfo> unpublicOrgCancelRecords = cancelReasonInfoMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(unpublicOrgCancelRecords)){
			model.addAttribute("reason", unpublicOrgCancelRecords.get(0));
		}
		
		model.addAttribute("id", id);
		model.addAttribute("method", method);
		return "socialorg/partyConstructionCancel";
	}
	
	/**
	 * 撤销党组织页面中的文件展示
	 * @param id
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/socialorg/partyShowlFiles",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> showFile(String id, int page,int rows){
		Map<String,Object> result = new HashMap<>();
		int start = (page-1)*rows;
		Example example = new Example(AttachmentCommonInfo.class);
		example.createCriteria().andEqualTo("mainTableId", id).andEqualTo("modular", 1).andEqualTo("type", 2).andEqualTo("action", 1).andEqualTo("status", 1);
		int total = attachmentCommonInfoMapper.selectCountByExample(example);
		if(total == 0){
			result.put("rows", new ArrayList<>());
			result.put("total", total);
			return result;
		}
		List<AttachmentCommonInfo> dataFiles = attachmentCommonInfoMapper.selectByExampleAndRowBounds(example, new RowBounds(start, rows));
		result.put("total", total);
		result.put("rows", dataFiles);
		return result;
	}
	/**
	 * 撤销年度党建情况
	 * @param model
	 * @param id
	 * @param remarks
	 * @return
	 */
	@RequestMapping(value="/socialorg/partyconstructioncancel",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> orgCancel(Model model,String id,String remarks){
		
		try {
			CancelReasonInfo reason = new CancelReasonInfo();
			reason.setType("1");
			reason.setPartyOrgId(Integer.parseInt(id));
			reason.setReason(remarks);
			reason.setCreator(baseService.getUserName());
			reason.setCreateTime(new Date());
			reason.setStatus("1");
			SocialPartyOrgBuilding info = new SocialPartyOrgBuilding();
			info.setId(Integer.parseInt(id));
			info.setStatus("3");
			partyConstructionService.cancelOrg(reason, info);
			
			SocialPartyOrgBuilding main = socialPartyOrgBuildingMapper.selectByPrimaryKey(id);
			SocialPartyOrgInfo upoInfo = socialPartyOrgInfoMapper.selectByPrimaryKey(main.getSocialPartyOrgId()+"");
			//撤销日志
			logService.saveLog(LOG_OPERATION_TYPE.DELETE.toString(), 
					logService.getDetailInfo("log.socialorg.cancel",
							baseService.getUserName(),upoInfo.getPartyOrgName()));
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
	@RequestMapping(value="/socialorg/partyConstructionNocancel",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> nocancel(Model model,String id,String remarks){
		
		SocialPartyOrgBuilding main = new SocialPartyOrgBuilding();
		main.setId(Integer.parseInt(id));
		main.setStatus("2");
		//main.setUpdateTime(new Date());
		//main.setUpdator(baseService.getUserName());
		partyConstructionService.nocancel(main);
		try {
			main = socialPartyOrgBuildingMapper.selectByPrimaryKey(main.getId());
			SocialPartyOrgInfo info = socialPartyOrgInfoMapper.selectByPrimaryKey(main.getSocialPartyOrgId()+"");
			//取消撤销日志
			logService.saveLog(LOG_OPERATION_TYPE.DELETE.toString(), 
					logService.getDetailInfo("log.socialorg.nocancel",
							baseService.getUserName(),info.getPartyOrgName()));
			return returnMsg(1, "取消撤销该组织成功！");
		} catch (Exception e) {
			logger.debug("取消撤销社会组织信息时出现异常:{}", e.getMessage(),e);
			return returnMsg(0, "撤销失败:" + e.getMessage());
		}
	}
	/**
	 * 审核
	 * @param model
	 * @param partyOrgIds
	 * @param remarks
	 * @return
	 */
	@RequestMapping(value="/socialorg/partyConstructionCancelok",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> cancelok(Model model,String partyOrgIds,String remarks){
		try {
			String[] partyOrgIdArray = partyOrgIds.split(",");
			for(int i = 0; i < partyOrgIdArray.length; i++){
				SocialPartyOrgBuilding main = socialPartyOrgBuildingMapper.selectByPrimaryKey(Integer.parseInt(partyOrgIdArray[i]));
				main.setStatus("4");//已撤销
				socialPartyOrgBuildingMapper.updateByPrimaryKeySelective(main);
				
				SocialPartyOrgInfo info = socialPartyOrgInfoMapper.selectByPrimaryKey(main.getSocialPartyOrgId()+"");
//				//撤销审核通过日志
				logService.saveLog(LOG_OPERATION_TYPE.MODIFY.toString(), 
						logService.getDetailInfo("log.socialorg.cancelok",
								baseService.getUserName(),info.getPartyOrgName()));
			}
			return returnMsg(1, "审核通过，撤销组织成功！");
		} catch (Exception e) {
			logger.debug("撤销审核通过社会组织信息时出现异常:{}", e.getMessage(),e);
			return returnMsg(0, "撤销失败:" + e.getMessage());
		}
		
	}
	
	/**
	 * 编辑页面初始化参数
	 * @param model
	 */
	private void editPage(Model model){
		List<DataDictionary> yesNoList = dict.findByDmm(CommonConstants.YES_NO);
		List<DataDictionary> annualSurveyList = dict.findByDmm(CommonConstants.ANNUAL_SURVEY);
		List<DataDictionary> partyorgStatusList = dict.findByDmm(CommonConstants.UNPUBLIC_ORG_STATUS);
		model.addAttribute("yesNoList", yesNoList);
		model.addAttribute("annualSurveyList", annualSurveyList);
		model.addAttribute("partyorgStatusList", partyorgStatusList);
		model.addAttribute("createOrgName", baseService.getDeptNameById(baseService.getCurrentUserDeptId()));
		model.addAttribute("createOrgId", baseService.getCurrentUserDeptId());
	}
	
	
	
	/**
	 * 转换数据集合
	 * @param rows
	 */
	private void convertRows(List<SocialPartyOrgBuilding> rows) {
		for (SocialPartyOrgBuilding info : rows) {
			this.convertRow(info);
		}
	}


	/**
	 * 转换单条数据
	 * @param info
	 */
	private void convertRow(SocialPartyOrgBuilding main) {

		main.setAnnualSurveyTxt(dict.getValueByCode(CommonConstants.ANNUAL_SURVEY, main.getAnnualSurvey()));
		main.setStatusTxt(dict.getValueByCode(CommonConstants.UNPUBLIC_ORG_STATUS,main.getStatus()));
		
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
