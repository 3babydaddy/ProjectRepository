package com.tf.base.socialorg.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.tf.base.socialorg.domain.SocialOrgInfo;
import com.tf.base.socialorg.domain.SocialOrgPmbrCount;
import com.tf.base.socialorg.domain.SocialOrgPmbrInfo;
import com.tf.base.socialorg.domain.SocialPartyOrgChangeInfo;
import com.tf.base.socialorg.domain.SocialPartyOrgInfo;
import com.tf.base.socialorg.persistence.SocialOrgInfoMapper;
import com.tf.base.socialorg.persistence.SocialOrgPmbrCountMapper;
import com.tf.base.socialorg.persistence.SocialOrgPmbrInfoMapper;
import com.tf.base.socialorg.persistence.SocialPartyOrgChangeInfoMapper;
import com.tf.base.socialorg.persistence.SocialPartyOrgInfoMapper;
import com.tf.base.socialorg.service.PartyOrgService;
import com.tf.base.unpublic.domain.AttachmentCommonInfo;
import com.tf.base.unpublic.domain.CancelReasonInfo;
import com.tf.base.unpublic.domain.DeputySecretaryInfo;
import com.tf.base.unpublic.domain.QueryPmbrParams;
import com.tf.base.unpublic.persistence.AttachmentCommonInfoMapper;
import com.tf.base.unpublic.persistence.CancelReasonInfoMapper;
import com.tf.base.unpublic.persistence.DeputySecretaryInfoMapper;
import tk.mybatis.mapper.entity.Example;

@Controller
public class PartyOrgController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DictionaryRepository dict;
	@Autowired
	private BaseService baseService;
	@Autowired
	private LogService logService;
	@Autowired
	private PartyOrgService partyOrgService;
	@Autowired
	private SocialOrgInfoMapper socialOrgInfoMapper;
	@Autowired
	private SocialPartyOrgInfoMapper socialPartyOrgInfoMapper;
	@Autowired
	private AttachmentCommonInfoMapper attachmentCommonInfoMapper;
	@Autowired
	private SocialPartyOrgChangeInfoMapper socialPartyOrgChangeInfoMapper;
	@Autowired
	private CancelReasonInfoMapper cancelReasonInfoMapper;
	@Autowired
	private DeputySecretaryInfoMapper deputySecretaryInfoMapper;
	@Autowired
	private SocialOrgPmbrCountMapper socialOrgPmbrCountMapper;
	@Autowired
	private SocialOrgPmbrInfoMapper socialOrgPmbrInfoMapper;
	
	/**
	 * 初始化页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/socialorg/partyorglist",method=RequestMethod.GET)
	public String orginit(Model model){
		
		initPage(model);
		return "socialorg/partyOrgList";
	}
	
	/**
	 * 党组织信息列表展示
	 * @param params
	 * @param page
	 * @param rows
	 * @param response
	 */
	@RequestMapping(value="/socialorg/partyorglist",method=RequestMethod.POST)
	public void orglist(SocialPartyOrgInfo params,
			int page,int rows,HttpServletResponse response){
		String deptId = baseService.getCurrentUserDeptId();
		logger.debug("当前登录用户部门ID:===========>" + deptId + " ,是否为区委部门?" + baseService.isQuWeiDept());
		if(!baseService.isQuWeiDept()){
			params.setCreateOrg(deptId);
		}
		PageHelper.startPage(page, rows, true);
		List<SocialPartyOrgInfo> list = socialPartyOrgInfoMapper.queryList(params);
		this.convertRows(list);
		PageUtil.returnPage(response, new PageInfo<SocialPartyOrgInfo>(list));
		
		
	}
	
	/**
	 * 查看党组织信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/socialorg/partyOrglook",method=RequestMethod.GET)
	public String orglook(String id, Model model){
		List<SocialPartyOrgChangeInfo> changeDateList = new ArrayList<>();
		List<DeputySecretaryInfo> deputsecList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Integer mainId = Integer.parseInt(id);
		
		SocialPartyOrgInfo socialPartyOrgInfo = socialPartyOrgInfoMapper.selectByPrimaryKey(mainId);
		if(socialPartyOrgInfo.getPartyOrgAttachment() != "" && socialPartyOrgInfo.getPartyOrgAttachment().length() > 0){
			AttachmentCommonInfo attachmentCommonInfo = attachmentCommonInfoMapper.selectByPrimaryKey(Integer.parseInt(socialPartyOrgInfo.getPartyOrgAttachment()));
			socialPartyOrgInfo.setFilepartyOrgAttachment(attachmentCommonInfo.getFilename());
		}
		socialPartyOrgInfo.setInstructorUnitTxt(baseService.getDeptNameById(socialPartyOrgInfo.getInstructorUnit()));
		socialPartyOrgInfo.setSecretaryCompanyTxt(baseService.getDeptNameById(socialPartyOrgInfo.getSecretaryCompany()));
		//查看换届的相关信息
		Example example = new Example(SocialPartyOrgChangeInfo.class);
		example.createCriteria().andEqualTo("socialPartyOrgId", mainId).andEqualTo("status", 1);
		changeDateList = socialPartyOrgChangeInfoMapper.selectByExample(example);
		for(SocialPartyOrgChangeInfo upocInfo : changeDateList){
			AttachmentCommonInfo acInfo = attachmentCommonInfoMapper.selectByPrimaryKey(upocInfo.getChangeAttachmentId());
			upocInfo.setChangeAttachmentName(acInfo.getFilename());
			upocInfo.setChangeTimeTxt(sdf.format(upocInfo.getChangeTime()));
		}
		//查询党副的相关信息
		Example dsexample = new Example(DeputySecretaryInfo.class);
		dsexample.createCriteria().andEqualTo("partyOrgId", mainId).andEqualTo("type", "1").andEqualTo("status", 1);
		deputsecList = deputySecretaryInfoMapper.selectByExample(dsexample);
		for(DeputySecretaryInfo dsInfo : deputsecList){
			dsInfo.setDeputySecretaryBirthdayTxt(sdf.format(dsInfo.getDeputySecretaryBirthday()));
		}
		//组装建立党组织的单位字符串
		String orgNames = "";
		String orgIds1 = "";
		Example example1 = new Example(SocialOrgInfo.class);
		example1.createCriteria().andEqualTo("socialPartyOrgId", mainId);
		List<SocialOrgInfo> orgInfoList = socialOrgInfoMapper.selectByExample(example1);
		for(SocialOrgInfo info : orgInfoList){
			if(orgNames == ""){
				orgNames = info.getName();
				orgIds1 = info.getId()+"";
			}else{
				orgNames = orgNames +","+ info.getName();
				orgIds1 = orgIds1 + "," + info.getId();
			}
		}
		//时间的类型转换
		if(socialPartyOrgInfo.getPartyOrgTime() != null){
			socialPartyOrgInfo.setPartyOrgTimeTxt(sdf.format(socialPartyOrgInfo.getPartyOrgTime()));
		}
		if(socialPartyOrgInfo.getSecretaryBirthday() != null){
			socialPartyOrgInfo.setSecretaryBirthdayTxt(sdf.format(socialPartyOrgInfo.getSecretaryBirthday()));
		}
		String[] orgIdArray = orgIds1.split(",");
		SocialOrgPmbrCount opcInfo = socialOrgPmbrCountMapper.getPmbrCount(orgIdArray);
		model.addAttribute("main", socialPartyOrgInfo);
		model.addAttribute("changeDateList", changeDateList);
		model.addAttribute("deputsecList", deputsecList);
		model.addAttribute("orgNames", orgNames);
		model.addAttribute("opcInfo", opcInfo);
		editPage(model);
		
		//查看日志
		logService.saveLog(LOG_OPERATION_TYPE.VIEW.toString(), 
				logService.getDetailInfo("log.socialorg.view",
						baseService.getUserName(),socialPartyOrgInfo.getPartyOrgName()));
		return "socialorg/partyOrgLook";
	}
	
	/**
	 * 编辑党组织信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/socialorg/partyorgedit",method=RequestMethod.GET)
	public String orgedit(String id, String orgIds, String orgNames, String nature, Model model)throws Exception{
		List<SocialPartyOrgChangeInfo> changeDateList = new ArrayList<>();
		List<DeputySecretaryInfo> deputsecList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		if(!StringUtils.isEmpty(id)){
			Integer mainId = Integer.parseInt(id);
			
			SocialPartyOrgInfo socialPartyOrgInfo = socialPartyOrgInfoMapper.selectByPrimaryKey(mainId);
			if(socialPartyOrgInfo.getPartyOrgAttachment() != "" && socialPartyOrgInfo.getPartyOrgAttachment().length() > 0){
				AttachmentCommonInfo attachmentCommonInfo = attachmentCommonInfoMapper.selectByPrimaryKey(Integer.parseInt(socialPartyOrgInfo.getPartyOrgAttachment()));
				socialPartyOrgInfo.setFilepartyOrgAttachment(attachmentCommonInfo.getFilename());
			}
			socialPartyOrgInfo.setInstructorUnitTxt(baseService.getDeptNameById(socialPartyOrgInfo.getInstructorUnit()));
			socialPartyOrgInfo.setSecretaryCompanyTxt(baseService.getDeptNameById(socialPartyOrgInfo.getSecretaryCompany()));
			//查看换届的相关信息
			Example example = new Example(SocialPartyOrgChangeInfo.class);
			example.createCriteria().andEqualTo("socialPartyOrgId", mainId).andEqualTo("status", 1);
			changeDateList = socialPartyOrgChangeInfoMapper.selectByExample(example);
			for(SocialPartyOrgChangeInfo upocInfo : changeDateList){
				AttachmentCommonInfo acInfo = attachmentCommonInfoMapper.selectByPrimaryKey(upocInfo.getChangeAttachmentId());
				upocInfo.setChangeAttachmentName(acInfo.getFilename());
				upocInfo.setChangeTimeTxt(sdf.format(upocInfo.getChangeTime()));
			}
			//查询党副的相关信息
			Example dsexample = new Example(DeputySecretaryInfo.class);
			dsexample.createCriteria().andEqualTo("partyOrgId", mainId).andEqualTo("type", "1").andEqualTo("status", 1);
			deputsecList = deputySecretaryInfoMapper.selectByExample(dsexample);
			for(DeputySecretaryInfo dsInfo : deputsecList){
				dsInfo.setDeputySecretaryBirthdayTxt(sdf.format(dsInfo.getDeputySecretaryBirthday()));
			}
			//组装建立党组织的单位字符串
			String  orgNames1 = "";
			String  orgIds1 = "";
			Example example1 = new Example(SocialOrgInfo.class);
			example1.createCriteria().andEqualTo("socialPartyOrgId", mainId).andEqualTo("status", 2);
			List<SocialOrgInfo> orgInfoList = socialOrgInfoMapper.selectByExample(example1);
			for(SocialOrgInfo info : orgInfoList){
				if(orgNames1 == ""){
					orgNames1 = info.getName();
					orgIds1 = info.getId()+"";
				}else{
					orgNames1 = orgNames1 +","+ info.getName();
					orgIds1 = orgIds1 + "," + info.getId();
				}
			}
			if(socialPartyOrgInfo.getPartyOrgTime() != null){
				socialPartyOrgInfo.setPartyOrgTimeTxt(sdf.format(socialPartyOrgInfo.getPartyOrgTime()));
			}
			if(socialPartyOrgInfo.getSecretaryBirthday() != null){
				socialPartyOrgInfo.setSecretaryBirthdayTxt(sdf.format(socialPartyOrgInfo.getSecretaryBirthday()));
			}
			String[] orgIdArray = orgIds1.split(",");
			SocialOrgPmbrCount opcInfo = socialOrgPmbrCountMapper.getPmbrCount(orgIdArray);
			model.addAttribute("main", socialPartyOrgInfo);
			model.addAttribute("changeDateList", changeDateList);
			model.addAttribute("deputsecList", deputsecList);
			model.addAttribute("orgNames", orgNames1);
			model.addAttribute("orgIds", orgIds1);
			model.addAttribute("opcInfo", opcInfo);
			model.addAttribute("nature", nature);
		}else{
			String[] orgIdArray = orgIds.split(",");
			SocialOrgPmbrCount opcInfo = socialOrgPmbrCountMapper.getPmbrCount(orgIdArray);
			
			SocialPartyOrgInfo main = new SocialPartyOrgInfo();
			model.addAttribute("main", main);
			model.addAttribute("orgIds", orgIds);
			model.addAttribute("changeDateList", changeDateList);
			model.addAttribute("deputsecList", deputsecList);
			model.addAttribute("orgNames", new String(orgNames.getBytes("iso-8859-1"),"utf-8"));
			model.addAttribute("opcInfo", opcInfo);
			model.addAttribute("nature", nature);
		}
		editPage(model);
		return "socialorg/partyOrgEdit"; 
	}
	
	/**
	 * 保存党组织信息
	 * @param model
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/socialorg/partyorgedit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> orgSave(String partyOrgIds, SocialPartyOrgInfo socialPartyOrgInfo, HttpServletRequest request, Model model)throws Exception{
		List<SocialPartyOrgChangeInfo> pociList = new ArrayList<>();
		List<DeputySecretaryInfo> dsiList = new ArrayList<>();
		String msg = "";
		String[] orgInfoArray = partyOrgIds.split(",");
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		//换届信息和党副信息
		for(int i = 0; i < 8; i++){
        	String changeTime = request.getParameter("partymbrInUnpublicNum"+i);
        	String fileId = request.getParameter("partymbrUnderThirtyfiveNum"+i);
        	if(changeTime != null && changeTime.length()>0 && fileId != null && fileId.length()>0){
        		SocialPartyOrgChangeInfo changeInfo = new SocialPartyOrgChangeInfo();
        		changeInfo.setChangeTime(sdf.parse(changeTime));
        		changeInfo.setChangeAttachmentId(Integer.parseInt(fileId));
        		changeInfo.setCreater(baseService.getUserName());
        		changeInfo.setCreateTime(new Date());
        		changeInfo.setStatus("1");
        		pociList.add(changeInfo);
        	}
        	DeputySecretaryInfo deputySecretaryInfo = new DeputySecretaryInfo();
        	String type = request.getParameter("deputySecretaryType"+i);
        	String name = request.getParameter("deputySecretaryName"+i);
        	String brithday = request.getParameter("deputySecretaryBirthdayTxt"+i);
        	String sex = request.getParameter("deputySecretarySex"+i);
        	String education = request.getParameter("deputySecretaryEducation"+i);
        	String isFullTime = request.getParameter("deputySecretaryIsFullTime"+i);
        	String isBoardOfficer = request.getParameter("isBoardOfficer"+i);
        	if(name != null && name.length() > 0 && brithday != null && brithday.length() > 0){
        		deputySecretaryInfo.setDeputySecretaryType(type);
            	deputySecretaryInfo.setDeputySecretaryName(name);
            	deputySecretaryInfo.setDeputySecretaryBirthday(sdf.parse(brithday));
            	deputySecretaryInfo.setDeputySecretarySex(sex);
            	deputySecretaryInfo.setDeputySecretaryEducation(education);
            	deputySecretaryInfo.setDeputySecretaryIsFullTime(isFullTime);
            	deputySecretaryInfo.setIsBoardOfficer(isBoardOfficer);
            	deputySecretaryInfo.setCreateOrg(baseService.getCurrentUserDeptId());
            	deputySecretaryInfo.setCreater(baseService.getUserName());
            	deputySecretaryInfo.setCreateTime(new Date());
            	deputySecretaryInfo.setStatus("1");
            	dsiList.add(deputySecretaryInfo);
        	}
        	
        }
		Integer mainId = socialPartyOrgInfo.getId();
		if(socialPartyOrgInfo.getReportHigher() == 1){
			socialPartyOrgInfo.setStatus("5");//上报申请中
			msg = "上报成功";
		}else{
			socialPartyOrgInfo.setStatus("1");//临时保存
			msg = "新增成功";
		}
		if(mainId == null || StringUtils.isEmpty(mainId.toString())){
			
			try {
				partyOrgService.addOrg(socialPartyOrgInfo, pociList, dsiList, orgInfoArray);
				return returnMsg(1, msg);
			} catch (Exception e) {
				logger.debug("新增社会组织信息时出现异常:{}", e.getMessage(),e);
				return returnMsg(0, "新增失败:" + e.getMessage());
			}
		}else{
			try {
				partyOrgService.updateOrg(socialPartyOrgInfo, pociList, dsiList);
				if(socialPartyOrgInfo.getReportHigher() == 0){
					msg = "修改成功";
				}
				return returnMsg(1, msg);
			} catch (Exception e) {
				logger.debug("修改社会组织信息时出现异常:{}", e.getMessage(),e);
				return returnMsg(0, "修改失败:" + e.getMessage());
			}
		}
	}
	/**
	 * 党组织信息退回
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/socialorg/partyOrgSetStatus",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> orgSetStatus(Model model,String id,String status){
		SocialPartyOrgInfo info = new SocialPartyOrgInfo();
		info.setId(Integer.parseInt(id));
		info.setStatus(status);
		
		//info.setUpdateTime(new Date());
		//info.setUpdator(baseService.getUserName());
		socialPartyOrgInfoMapper.updateByPrimaryKeySelective(info);
		return returnMsg(1, "操作成功!");
	}
	
	/**
	 * 党组织信息上报审核
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/socialorg/partyOrgsSetStatus",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> orgsSetStatus(Model model,String partyOrgIds,String status){
		String[] partyOrgArray = partyOrgIds.split(",");
		for(int i = 0; i < partyOrgArray.length; i++){
			if(partyOrgArray[i] != null && partyOrgArray[i].length() > 0){
				SocialPartyOrgInfo info = new SocialPartyOrgInfo();
				info.setId(Integer.parseInt(partyOrgArray[i]));
				info.setStatus(status);
				
				//info.setUpdateTime(new Date());
				//info.setUpdator(baseService.getUserName());
				socialPartyOrgInfoMapper.updateByPrimaryKeySelective(info);
			}
		}
		return returnMsg(1, "操作成功!");
	}
	
	/**
	 * 删除党组织信息
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/socialorg/partyorgdelete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> orgdelete(Model model,String id){
		SocialPartyOrgInfo info = new SocialPartyOrgInfo();
		info.setId(Integer.parseInt(id));
		info.setStatus("0");
		try {
			socialPartyOrgInfoMapper.updateByPrimaryKeySelective(info);
			//删除日志
			logService.saveLog(LOG_OPERATION_TYPE.DELETE.toString(), 
					logService.getDetailInfo("log.socialorg.delete",
							baseService.getUserName(),info.getPartyOrgName()));
			
			return returnMsg(1, "删除成功!");
		} catch (Exception e) {
			logger.debug("删除非公党信息时出现异常:{}", e.getMessage(),e);
			return returnMsg(0, "删除失败："+e.getMessage());
		}
	}
	
	
	/**
	 * 撤销党组织页面
	 * @param id
	 * @param method
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/socialorg/partyorgcancel",method=RequestMethod.GET)
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
		return "socialorg/partyOrgCancel";
	}
	
	/**
	 * 撤销党组织页面中的文件展示
	 * @param id
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/socialorg/partyShowlFile",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> showFile(String id, int page,int rows){
		Map<String,Object> result = new HashMap<>();
		int start = (page-1)*rows;
		Example example = new Example(AttachmentCommonInfo.class);
		example.createCriteria().andEqualTo("mainTableId", id).andEqualTo("modular", 1).andEqualTo("type", 1).andEqualTo("action", 1).andEqualTo("status", 1);
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
	 * 撤销党组织信息
	 * @param model
	 * @param id
	 * @param remarks
	 * @return
	 */
	@RequestMapping(value="/socialorg/partyorgcancel",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> orgCancel(Model model,String id,String remarks){
		
		try {
			CancelReasonInfo reason = new CancelReasonInfo();
			reason.setPartyOrgId(Integer.parseInt(id));
			reason.setType("1");
			reason.setReason(remarks);
			reason.setCreateTime(new Date());
			reason.setCreator(baseService.getUserName());
			reason.setStatus(CommonConstants.STATUS_FLAG_VALID);
			SocialPartyOrgInfo info = new SocialPartyOrgInfo();
			info.setId(Integer.parseInt(id));
			info.setStatus("3");
			
			partyOrgService.cancelOrg(reason, info);
			SocialPartyOrgInfo main = socialPartyOrgInfoMapper.selectByPrimaryKey(id);
			//撤销日志
			logService.saveLog(LOG_OPERATION_TYPE.DELETE.toString(), 
					logService.getDetailInfo("log.socialorg.cancel",
							baseService.getUserName(),main.getPartyOrgName()));
			return returnMsg(1, "撤销成功，等待上级部门审核！");
		} catch (Exception e) {
			logger.debug("撤销非公党组织信息时出现异常:{}", e.getMessage(),e);
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
	@RequestMapping(value="/socialorg/partyNocancel",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> nocancel(Model model,String id,String remarks){
		
		SocialPartyOrgInfo main = new SocialPartyOrgInfo();
		main.setId(Integer.parseInt(id));
		main.setStatus("2");
//		main.setUpdateTime(new Date());
//		main.setUpdator(baseService.getUserName());
		partyOrgService.nocancelOrg(main);
		try {
			
			main = socialPartyOrgInfoMapper.selectByPrimaryKey(id);
			//取消撤销日志
			logService.saveLog(LOG_OPERATION_TYPE.DELETE.toString(), 
					logService.getDetailInfo("log.socialorg.nocancel",
							baseService.getUserName(),main.getPartyOrgName()));
			return returnMsg(1, "取消撤销该组织成功！");
		} catch (Exception e) {
			logger.debug("取消撤销党组织信息时出现异常:{}", e.getMessage(),e);
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
	@RequestMapping(value="/socialorg/partyCancelok",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> cancelok(Model model,String partyOrgIds,String remarks){
		try {
			String[] partyOrgIdArray = partyOrgIds.split(",");
			for(int i = 0; i < partyOrgIdArray.length; i++){
				SocialPartyOrgInfo main = new SocialPartyOrgInfo();
				main.setId(Integer.parseInt(partyOrgIdArray[i]));
				main.setStatus("4");
//				main.setUpdateTime(new Date());
//				main.setUpdator(baseService.getUserName());
				socialPartyOrgInfoMapper.updateByPrimaryKeySelective(main);
			}
			//main = socialPartyOrgInfoMapper.selectByPrimaryKey(id);
			//撤销审核通过日志
//			logService.saveLog(LOG_OPERATION_TYPE.MODIFY.toString(), 
//					logService.getDetailInfo("log.socialorg.cancelok",
//							baseService.getUserName(),main.getPartyOrgName()));
			return returnMsg(1, "审核通过，撤销组织成功！");
		} catch (Exception e) {
			logger.debug("撤销审核通过党组织信息时出现异常:{}", e.getMessage(),e);
			return returnMsg(0, "撤销失败:" + e.getMessage());
		}
		
	}
	
	
	/**
	 * 文件上传页面
	 * @param id
	 * @param method
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/socialorg/uploadFile",method=RequestMethod.GET)     
	public String fileUploadShow(AttachmentCommonInfo attachmentCommonInfo,String sign, String method,Model model) throws UnsupportedEncodingException{
		attachmentCommonInfo.setFilename(new String(attachmentCommonInfo.getFilename().getBytes("iso-8859-1"),"utf-8"));
		model.addAttribute("main", attachmentCommonInfo); 
		model.addAttribute("sign", sign);
		model.addAttribute("method", method);
		return "unpublic/partyOrgFileUpload";
	}
	/**
	 * 党组织上传文件展示
	 * @param id
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/socialorg/partycancelFile",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> uploadFile(String id, int page,int rows){
		Map<String,Object> result = new HashMap<>();
		int start = (page-1)*rows;
		Example example = new Example(AttachmentCommonInfo.class);
		example.createCriteria().andEqualTo("id", id).andEqualTo("status", 1);
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
	 * 展示党员基本信息页面
	 * @param id
	 * @param method
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/socialorg/showPartyInfo",method=RequestMethod.GET)
	public String jumpShowPartyInfo(String orgIds, Model model){
		
		model.addAttribute("orgIds", orgIds);
		return "socialorg/showPartyInfoPop";
	}
	
	/**
	 * 展示党员基本信息页面
	 * @param id
	 * @param method
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/socialorg/showPartyInfo",method=RequestMethod.POST)
	public void showPartyInfo(HttpServletResponse response, int page, int rows, QueryPmbrParams params){
		String[] orgIdsArray = params.getOrgIds().split(",");
		PageHelper.startPage(page, rows, true);
		List<SocialOrgPmbrInfo> list = socialOrgPmbrInfoMapper.queryListByOrgId(params.getName(), orgIdsArray);
		for (SocialOrgPmbrInfo socialOrgPmbrInfo : list) {
			socialOrgPmbrInfo.setGender(dict.getValueByCode(CommonConstants.GENDER, socialOrgPmbrInfo.getGender()));
			socialOrgPmbrInfo.setPartymbrGroupInSocialorgIs(dict.getValueByCode(CommonConstants.YES_NO, socialOrgPmbrInfo.getPartymbrGroupInSocialorgIs()));
			socialOrgPmbrInfo.setPartymbrInSocialorgIs(dict.getValueByCode(CommonConstants.YES_NO, socialOrgPmbrInfo.getPartymbrInSocialorgIs()));
			socialOrgPmbrInfo.setEducation(dict.getValueByCode(CommonConstants.FINAL_EDUCATION, socialOrgPmbrInfo.getEducation()));
		}
		PageUtil.returnPage(response, new PageInfo<SocialOrgPmbrInfo>(list));
	}
	
	/**
	 * 初始化参数
	 * @param model
	 */
	private void initPage(Model model){
			
		List<DataDictionary> yesNoList = dict.findByDmm(CommonConstants.YES_NO);
		List<DataDictionary> partyOrgClassList = dict.findByDmm(CommonConstants.PARTY_ORG_CLASS);
		List<DataDictionary> partyOrgFormList = dict.findByDmm(CommonConstants.PARTY_ORG_FORM);
		model.addAttribute("yesNoList", yesNoList);
		model.addAttribute("partyOrgClassList", partyOrgClassList);
		model.addAttribute("partyOrgFormList", partyOrgFormList);
	}
	/**
	 * 编辑页面初始化参数
	 * @param model
	 */
	private void editPage(Model model){
		
		List<DataDictionary> yesNoList = dict.findByDmm(CommonConstants.YES_NO);
		
		List<DataDictionary> partyOrgClassList = dict.findByDmm(CommonConstants.PARTY_ORG_CLASS);
		List<DataDictionary> partyOrgFormList = dict.findByDmm(CommonConstants.PARTY_ORG_FORM);
		List<DataDictionary> noEstablishPartyOrgReasonList = dict.findByDmm(CommonConstants.NO_ESTABLISH_PARTY_ORG_REASON);
		List<DataDictionary> sourceList = dict.findByDmm(CommonConstants.SOURCE);
		List<DataDictionary> partyTypeList = dict.findByDmm(CommonConstants.PARTY_SECRETARIES_AND_COMMISSION_TYPE);
		List<DataDictionary> finalEducationList = dict.findByDmm(CommonConstants.FINAL_EDUCATION);
		List<DataDictionary> genderList = dict.findByDmm(CommonConstants.GENDER);
		
		model.addAttribute("yesNoList", yesNoList);
		model.addAttribute("partyOrgClassList", partyOrgClassList);
		model.addAttribute("partyOrgFormList", partyOrgFormList);
		model.addAttribute("noEstablishPartyOrgReasonList", noEstablishPartyOrgReasonList);
		model.addAttribute("sourceList", sourceList);
		model.addAttribute("partyTypeList", partyTypeList);
		model.addAttribute("finalEducationList", finalEducationList);
		model.addAttribute("genderList", genderList);

	}
	
	/**
	 * 转换数据集合
	 * @param rows
	 */
	private void convertRows(List<SocialPartyOrgInfo> rows) {
		for (SocialPartyOrgInfo info : rows) {
			this.convertRow(info);
		}
	}

	/**
	 * 转换单条数据
	 * @param info
	 */
	private void convertRow(SocialPartyOrgInfo main) {

		main.setPartyOrgFormTxt(dict.getValueByCode(CommonConstants.PARTY_ORG_FORM,main.getPartyOrgForm()));
		main.setPartyOrgTypeTxt(dict.getValueByCode(CommonConstants.PARTY_ORG_CLASS,main.getPartyOrgType()));
		main.setStatusTxt(dict.getValueByCode(CommonConstants.UNPUBLIC_ORG_STATUS,main.getStatus()));
		
		main.setInstructorUnitTxt(baseService.getDeptNameById(main.getInstructorUnit()));
		main.setSecretaryCompanyTxt(baseService.getDeptNameById(main.getSecretaryCompany()));
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
