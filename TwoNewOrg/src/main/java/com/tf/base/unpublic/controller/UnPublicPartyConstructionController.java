package com.tf.base.unpublic.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
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
import com.tf.base.unpublic.domain.AttachmentCommonInfo;
import com.tf.base.unpublic.domain.CancelReasonInfo;
import com.tf.base.unpublic.domain.UnpublicPartyOrgInfo;
import com.tf.base.unpublic.domain.UnpulicPartyOrgBuilding;
import com.tf.base.unpublic.persistence.AttachmentCommonInfoMapper;
import com.tf.base.unpublic.persistence.CancelReasonInfoMapper;
import com.tf.base.unpublic.persistence.UnpublicPartyOrgInfoMapper;
import com.tf.base.unpublic.persistence.UnpulicPartyOrgBuildingMapper;
import com.tf.base.unpublic.service.UnPublicPartyConstructionService;

import tk.mybatis.mapper.entity.Example;

@Controller
public class UnPublicPartyConstructionController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DictionaryRepository dict;
	
	@Autowired
	private BaseService baseService;
	@Autowired
	private LogService logService;
	@Autowired
	private UnPublicPartyConstructionService unPublicPartyConstructionService;
	@Autowired
	private CancelReasonInfoMapper cancelReasonInfoMapper;
	@Autowired
	private UnpulicPartyOrgBuildingMapper unpulicPartyOrgBuildingMapper;
	@Autowired
	private UnpublicPartyOrgInfoMapper unpublicPartyOrgInfoMapper;
	@Autowired
	private AttachmentCommonInfoMapper attachmentCommonInfoMapper;
	
	/**
	 * 初始化页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/unpublic/partyconstructionlist",method=RequestMethod.GET)
	public String orginit(Model model){
		
		editPage(model);
		return "unpublic/partyConstructionList";
	}
	
	/**
	 * 年度党建情况列表展示
	 * @param params
	 * @param page
	 * @param rows
	 * @param response
	 */
	@RequestMapping(value="/unpublic/partyconstructionlist",method=RequestMethod.POST)
	public void orglist(UnpulicPartyOrgBuilding params, int page,int rows,HttpServletResponse response){
		String deptId = baseService.getCurrentUserDeptId();
		logger.debug("当前登录用户部门ID:===========>" + deptId + " ,是否为区委部门?" + baseService.isQuWeiDept());
		
		if(!baseService.isQuWeiDept()){
			params.setCreateOrg(deptId);
		}else{
			//工委列表展示过滤掉组织状态为正常;但还没有新建党组织的数据
			params.setIsQuWeiSign("1");
		}
		PageHelper.startPage(page, rows, true);
		List<UnpulicPartyOrgBuilding> list = unpulicPartyOrgBuildingMapper.queryList(params);
		this.convertRows(list);
		PageUtil.returnPage(response, new PageInfo<UnpulicPartyOrgBuilding>(list));
		
		
	}
	
	/**
	 * 查看年度党建情况
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/unpublic/partyconstructionlook",method=RequestMethod.GET)
	public String orglook(String id, String partyOrgName, Model model)throws Exception{
		Integer mainId = Integer.parseInt(id);
		UnpulicPartyOrgBuilding main = unpulicPartyOrgBuildingMapper.selectByPrimaryKey(mainId);
		if(main.getRectifyAtachementId() != null){
			AttachmentCommonInfo attachmentCommonInfo = attachmentCommonInfoMapper.selectByPrimaryKey(Integer.valueOf(main.getRectifyAtachementId()));
			main.setRectifyAtachementIdTxt(attachmentCommonInfo.getFilename());
		}
		main.setPartyOrgName(new String(partyOrgName.getBytes("iso-8859-1"),"utf-8"));
		model.addAttribute("main", main);
		editPage(model);
		//查看日志
		logService.saveLog(LOG_OPERATION_TYPE.VIEW.toString(), 
				logService.getDetailInfo("log.unpublic.view",
						baseService.getUserName(),main.getPartyOrgName()));
		return "unpublic/partyConstructionLook";
	}
	
	/**
	 * 新增或者编辑年度党建情况
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/unpublic/partyconstructionedit",method=RequestMethod.GET)
	public String orgedit(String id, String partyOrgId, String partyOrgName, String clickSign, Model model)throws Exception{
		
		if(!StringUtils.isEmpty(id)){
			Integer mainId = Integer.parseInt(id);
			UnpulicPartyOrgBuilding main = unpulicPartyOrgBuildingMapper.selectByPrimaryKey(mainId);
			if(main.getRectifyAtachementId() != null){
				AttachmentCommonInfo attachmentCommonInfo = attachmentCommonInfoMapper.selectByPrimaryKey(Integer.valueOf(main.getRectifyAtachementId()));
				main.setRectifyAtachementIdTxt(attachmentCommonInfo.getFilename());
			}
			UnpublicPartyOrgInfo info = unpublicPartyOrgInfoMapper.selectByPrimaryKey(main.getUnpublicPartyOrgId());
			main.setPartyOrgName(info.getPartyOrgName());
			model.addAttribute("main", main);
			model.addAttribute("clickSign", clickSign);
		}else{
			UnpulicPartyOrgBuilding main = new UnpulicPartyOrgBuilding();
			main.setUnpublicPartyOrgId(Integer.parseInt(partyOrgId));
			main.setPartyOrgName(new String(partyOrgName.getBytes("iso-8859-1"),"utf-8"));
			model.addAttribute("main", main);
		}
		editPage(model);
		return "unpublic/partyConstructionEdit"; 
	}
	
	/**
	 * 保存年度党建情况
	 * @param model
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/unpublic/partyconstructionedit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> orgSave(Model model,UnpulicPartyOrgBuilding main){
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
				unPublicPartyConstructionService.addOrg(main);
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
				unPublicPartyConstructionService.updateOrg(main);
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
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/unpublic/partyConstructionsSetStatus",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> orgsSetStatus(Model model,String partyOrgIds,String status){
		String[] partyOrgArray = partyOrgIds.split(",");
		for(int i = 0; i < partyOrgArray.length; i++){
			if(partyOrgArray[i] != null && partyOrgArray[i].length() > 0){
				UnpulicPartyOrgBuilding info = unpulicPartyOrgBuildingMapper.selectByPrimaryKey(Integer.parseInt(partyOrgArray[i]));
				info.setStatus(status);
				unpulicPartyOrgBuildingMapper.updateByPrimaryKeySelective(info);
			}
		}
		return returnMsg(1, "操作成功!");
	}
	/**
	 * 年度党建退回
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/unpublic/partyConstructionSetStatus",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> orgSetStatus(Model model,String id,String status){
		UnpulicPartyOrgBuilding info = unpulicPartyOrgBuildingMapper.selectByPrimaryKey(Integer.parseInt(id));
		info.setStatus(status);
		unpulicPartyOrgBuildingMapper.updateByPrimaryKeySelective(info);
		return returnMsg(1, "操作成功!");
	}
	
	/**
	 * 删除年度党建情况
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/unpublic/partyconstructiondelete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> orgdelete(Model model,String id){
		try {
			UnpulicPartyOrgBuilding info = unpulicPartyOrgBuildingMapper.selectByPrimaryKey(Integer.parseInt(id));
			info.setStatus("0");
			unpulicPartyOrgBuildingMapper.updateByPrimaryKeySelective(info);
			//删除日志
			logService.saveLog(LOG_OPERATION_TYPE.DELETE.toString(), 
					logService.getDetailInfo("log.unpublic.delete",
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
	@RequestMapping(value="/unpublic/partyconstructioncancel",method=RequestMethod.GET)
	public String orgcancel(String id,String method,Model model){
		Example example = new Example(CancelReasonInfo.class);
		example.setOrderByClause("createTime desc");
		example.createCriteria().andEqualTo("partyOrgId", Integer.parseInt(id)).andEqualTo("type", '2').andEqualTo("status", 1);
		List<CancelReasonInfo> unpublicOrgCancelRecords = cancelReasonInfoMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(unpublicOrgCancelRecords)){
			model.addAttribute("reason", unpublicOrgCancelRecords.get(0));
		}
		
		model.addAttribute("id", id);
		model.addAttribute("method", method);
		return "unpublic/partyConstructionCancel";
	}
	/**
	 * 撤销年度党建情况
	 * @param model
	 * @param id
	 * @param remarks
	 * @return
	 */
	@RequestMapping(value="/unpublic/partyconstructioncancel",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> orgCancel(Model model,String id,String remarks){
		
		try {
			CancelReasonInfo reason = new CancelReasonInfo();
			reason.setType("2");
			reason.setPartyOrgId(Integer.parseInt(id));
			reason.setReason(remarks);
			reason.setCreator(baseService.getUserName());
			reason.setCreateTime(new Date());
			reason.setStatus("1");
			UnpulicPartyOrgBuilding info = new UnpulicPartyOrgBuilding();
			info.setId(Integer.parseInt(id));
			info.setStatus("3");
			unPublicPartyConstructionService.cancelOrg(reason, info);
			
			UnpulicPartyOrgBuilding main = unpulicPartyOrgBuildingMapper.selectByPrimaryKey(id);
			UnpublicPartyOrgInfo upoInfo = unpublicPartyOrgInfoMapper.selectByPrimaryKey(main.getUnpublicPartyOrgId()+"");
			//撤销日志
			logService.saveLog(LOG_OPERATION_TYPE.DELETE.toString(), 
					logService.getDetailInfo("log.unpublic.cancel",
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
	@RequestMapping(value="/unpublic/partyConstructionNocancel",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> nocancel(Model model,String id,String remarks){
		
		UnpulicPartyOrgBuilding main = new UnpulicPartyOrgBuilding();
		main.setId(Integer.parseInt(id));
		main.setStatus("1");
		//main.setUpdateTime(new Date());
		//main.setUpdator(baseService.getUserName());
		unPublicPartyConstructionService.nocancel(main);
		try {
			main = unpulicPartyOrgBuildingMapper.selectByPrimaryKey(main.getId());
			UnpublicPartyOrgInfo info = unpublicPartyOrgInfoMapper.selectByPrimaryKey(main.getUnpublicPartyOrgId()+"");
			//取消撤销日志
			logService.saveLog(LOG_OPERATION_TYPE.DELETE.toString(), 
					logService.getDetailInfo("log.unpublic.nocancel",
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
	 * @param id
	 * @param remarks
	 * @return
	 */
	@RequestMapping(value="/unpublic/partyConstructionCancelok",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> cancelok(Model model,String partyOrgIds,String remarks){
		try {
			String[] partyOrgIdArray = partyOrgIds.split(",");
			for(int i = 0; i < partyOrgIdArray.length; i++){
				UnpulicPartyOrgBuilding main = unpulicPartyOrgBuildingMapper.selectByPrimaryKey(Integer.parseInt(partyOrgIdArray[i]));
				main.setStatus("4");//已撤销
				unpulicPartyOrgBuildingMapper.updateByPrimaryKeySelective(main);
				
				UnpublicPartyOrgInfo info = unpublicPartyOrgInfoMapper.selectByPrimaryKey(main.getUnpublicPartyOrgId()+"");
//				//撤销审核通过日志
				logService.saveLog(LOG_OPERATION_TYPE.MODIFY.toString(), 
						logService.getDetailInfo("log.unpublic.cancelok",
								baseService.getUserName(),info.getPartyOrgName()));
			}
			return returnMsg(1, "审核通过，撤销该组织成功！");
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
		List<DataDictionary> partyorgStatusList = dict.findByDmm(CommonConstants.UNPUBLIC_ORG_STATUS);
		model.addAttribute("yesNoList", yesNoList);
		model.addAttribute("partyorgStatusList", partyorgStatusList);
		model.addAttribute("createOrgName", baseService.getDeptNameById(baseService.getCurrentUserDeptId()));
		model.addAttribute("createOrgId", baseService.getCurrentUserDeptId());
	}
	
	
	
	/**
	 * 转换数据集合
	 * @param rows
	 */
	private void convertRows(List<UnpulicPartyOrgBuilding> rows) {
		for (UnpulicPartyOrgBuilding info : rows) {
			this.convertRow(info);
		}
	}


	/**
	 * 转换单条数据
	 * @param info
	 */
	private void convertRow(UnpulicPartyOrgBuilding main) {

		main.setIsTrainingInRotationTxt(dict.getValueByCode(CommonConstants.YES_NO, main.getIsTrainingInRotation()));
		main.setIsPartyMemberTrainTxt(dict.getValueByCode(CommonConstants.YES_NO, main.getIsPartyMemberTrain()));
		main.setIsPartyMemberTrainTxt(dict.getValueByCode(CommonConstants.YES_NO, main.getIsIntoManage()));
		main.setIsDevelopListenTxt(dict.getValueByCode(CommonConstants.YES_NO, main.getIsDevelopListen()));
		main.setIsDevelopDiscussionsTxt(dict.getValueByCode(CommonConstants.YES_NO, main.getIsDevelopDiscussions()));
		main.setIsChangeEveryyearTxt(dict.getValueByCode(CommonConstants.YES_NO, main.getIsChangeEveryyear()));
		main.setIsBackwardPartyTxt(dict.getValueByCode(CommonConstants.YES_NO, main.getIsBackwardParty()));
		main.setIsRectifyPartyTxt(dict.getValueByCode(CommonConstants.YES_NO, main.getIsRectifyParty()));
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
