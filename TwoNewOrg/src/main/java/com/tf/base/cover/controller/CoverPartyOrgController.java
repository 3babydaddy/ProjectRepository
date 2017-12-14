package com.tf.base.cover.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tf.base.common.constants.CommonConstants;
import com.tf.base.common.constants.CommonConstants.LOG_OPERATION_TYPE;
import com.tf.base.common.domain.DataDictionary;
import com.tf.base.common.domain.DictionaryRepository;
import com.tf.base.common.service.BaseService;
import com.tf.base.common.service.LogService;
import com.tf.base.common.utils.ExcelUtils;
import com.tf.base.common.utils.PageUtil;
import com.tf.base.cover.domain.CoverOrgInfo;
import com.tf.base.cover.domain.CoverOrgPmbrCount;
import com.tf.base.cover.domain.CoverOrgPmbrInfo;
import com.tf.base.cover.domain.CoverPartyOrgChangeInfo;
import com.tf.base.cover.domain.CoverPartyOrgInfo;
import com.tf.base.cover.persistence.CoverOrgPmbrInfoMapper;
import com.tf.base.cover.persistence.CoverPartyOrgChangeInfoMapper;
import com.tf.base.cover.persistence.CoverPartyOrgInfoMapper;
import com.tf.base.cover.service.CoverPartyOrgService;
import com.tf.base.unpublic.domain.AttachmentCommonInfo;
import com.tf.base.unpublic.domain.CancelReasonInfo;
import com.tf.base.unpublic.domain.DeputySecretaryInfo;
import com.tf.base.unpublic.domain.QueryPmbrParams;
import com.tf.base.unpublic.domain.UnpublicOrgPmbrChangeInfo;
import com.tf.base.unpublic.persistence.AttachmentCommonInfoMapper;
import com.tf.base.unpublic.persistence.CancelReasonInfoMapper;
import com.tf.base.unpublic.persistence.DeputySecretaryInfoMapper;
import tk.mybatis.mapper.entity.Example;

@Controller
public class CoverPartyOrgController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DictionaryRepository dict;
	@Autowired
	private BaseService baseService;
	@Autowired
	private LogService logService;
	@Autowired
	private CoverPartyOrgService coverPartyOrgService;
	@Autowired
	private AttachmentCommonInfoMapper attachmentCommonInfoMapper;
	@Autowired
	private CancelReasonInfoMapper cancelReasonInfoMapper;
	@Autowired
	private DeputySecretaryInfoMapper deputySecretaryInfoMapper;
	@Autowired
	private CoverPartyOrgInfoMapper coverPartyOrgInfoMapper;
	@Autowired
	private CoverOrgPmbrInfoMapper coverOrgPmbrInfoMapper;
	@Autowired
	private CoverPartyOrgChangeInfoMapper coverPartyOrgChangeInfoMapper;
	
	/**
	 * 初始化页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cover/partyorglist",method=RequestMethod.GET)
	public String orginit(Model model){
		
		initPage(model);
		return "cover/partyOrgList";
	}
	
	/**
	 * 党组织信息列表展示
	 * @param params
	 * @param page
	 * @param rows
	 * @param response
	 */
	@RequestMapping(value="/cover/partyorglist",method=RequestMethod.POST)
	public void orglist(CoverPartyOrgInfo params,
			int page,int rows,HttpServletResponse response){
		String deptId = baseService.getCurrentUserDeptId();
		logger.debug("当前登录用户部门ID:===========>" + deptId + " ,是否为区委部门?" + baseService.isQuWeiDept());
		if(!baseService.isQuWeiDept()){
			params.setCreateOrg(deptId);
		}
		PageHelper.startPage(page, rows, true);
		List<CoverPartyOrgInfo> list = coverPartyOrgInfoMapper.queryList(params);
		this.convertRows(list);
		PageUtil.returnPage(response, new PageInfo<CoverPartyOrgInfo>(list));
		
		
	}
	
	/**
	 * 查看党组织信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cover/partyOrglook",method=RequestMethod.GET)
	public String orglook(String id, Model model){
		List<CoverPartyOrgChangeInfo> changeDateList = new ArrayList<>();
		List<DeputySecretaryInfo> deputsecList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Integer mainId = Integer.parseInt(id);
		
		CoverPartyOrgInfo coverPartyOrgInfo = coverPartyOrgInfoMapper.selectByPrimaryKey(mainId);
		if(coverPartyOrgInfo.getPartyOrgAttachment() != "" && coverPartyOrgInfo.getPartyOrgAttachment().length() > 0){
			AttachmentCommonInfo attachmentCommonInfo = attachmentCommonInfoMapper.selectByPrimaryKey(Integer.parseInt(coverPartyOrgInfo.getPartyOrgAttachment()));
			coverPartyOrgInfo.setFilepartyOrgAttachment(attachmentCommonInfo.getFilename());
		}
		coverPartyOrgInfo.setInstructorUnitTxt(baseService.getDeptNameById(coverPartyOrgInfo.getInstructorUnit()));
		coverPartyOrgInfo.setSecretaryCompanyTxt(baseService.getDeptNameById(coverPartyOrgInfo.getSecretaryCompany()));
		//查看换届的相关信息
		Example example = new Example(CoverPartyOrgChangeInfo.class);
		example.createCriteria().andEqualTo("coverPartyOrgId", mainId).andEqualTo("status", 1);
		changeDateList = coverPartyOrgChangeInfoMapper.selectByExample(example);
		for(CoverPartyOrgChangeInfo upocInfo : changeDateList){
			AttachmentCommonInfo acInfo = attachmentCommonInfoMapper.selectByPrimaryKey(upocInfo.getChangeAttachmentId());
			upocInfo.setChangeAttachmentName(acInfo.getFilename());
			upocInfo.setChangeTimeTxt(sdf.format(upocInfo.getChangeTime()));
		}
		//查询党副的相关信息
		Example dsexample = new Example(DeputySecretaryInfo.class);
		dsexample.createCriteria().andEqualTo("partyOrgId", mainId).andEqualTo("type", "3").andEqualTo("status", 1);
		deputsecList = deputySecretaryInfoMapper.selectByExample(dsexample);
		for(DeputySecretaryInfo dsInfo : deputsecList){
			dsInfo.setDeputySecretaryBirthdayTxt(sdf.format(dsInfo.getDeputySecretaryBirthday()));
		}
		
		//时间的类型转换
		if(coverPartyOrgInfo.getPartyOrgTime() != null){
			coverPartyOrgInfo.setPartyOrgTimeTxt(sdf.format(coverPartyOrgInfo.getPartyOrgTime()));
		}
		if(coverPartyOrgInfo.getSecretaryBirthday() != null){
			coverPartyOrgInfo.setSecretaryBirthdayTxt(sdf.format(coverPartyOrgInfo.getSecretaryBirthday()));
		}
		// 党员数量计算
		CoverOrgPmbrCount copc = coverPartyOrgService.getPmrCount(id);
		
		model.addAttribute("main", coverPartyOrgInfo);
		model.addAttribute("changeDateList", changeDateList);
		model.addAttribute("deputsecList", deputsecList);
		model.addAttribute("copc", copc);
		editPage(model);
		
		//查看日志
		logService.saveLog(LOG_OPERATION_TYPE.VIEW.toString(), 
				logService.getDetailInfo("log.cover.view",
						baseService.getUserName(),coverPartyOrgInfo.getPartyOrgName()));
		return "cover/partyOrgLook";
	}
	
	/**
	 * 编辑党组织信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cover/partyorgedit",method=RequestMethod.GET)
	public String orgedit(String id, Model model)throws Exception{
		List<CoverPartyOrgChangeInfo> changeDateList = new ArrayList<>();
		List<DeputySecretaryInfo> deputsecList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		if(!StringUtils.isEmpty(id)){
			Integer mainId = Integer.parseInt(id);
			
			CoverPartyOrgInfo coverPartyOrgInfo = coverPartyOrgInfoMapper.selectByPrimaryKey(mainId);
			if(coverPartyOrgInfo.getPartyOrgAttachment() != "" && coverPartyOrgInfo.getPartyOrgAttachment().length() > 0){
				AttachmentCommonInfo attachmentCommonInfo = attachmentCommonInfoMapper.selectByPrimaryKey(Integer.parseInt(coverPartyOrgInfo.getPartyOrgAttachment()));
				coverPartyOrgInfo.setFilepartyOrgAttachment(attachmentCommonInfo.getFilename());
			}
			coverPartyOrgInfo.setInstructorUnitTxt(baseService.getDeptNameById(coverPartyOrgInfo.getInstructorUnit()));
			coverPartyOrgInfo.setSecretaryCompanyTxt(baseService.getDeptNameById(coverPartyOrgInfo.getSecretaryCompany()));
			//查看换届的相关信息
			Example example = new Example(CoverPartyOrgChangeInfo.class);
			example.createCriteria().andEqualTo("coverPartyOrgId", mainId).andEqualTo("status", 1);
			changeDateList = coverPartyOrgChangeInfoMapper.selectByExample(example);
			for(CoverPartyOrgChangeInfo upocInfo : changeDateList){
				AttachmentCommonInfo acInfo = attachmentCommonInfoMapper.selectByPrimaryKey(upocInfo.getChangeAttachmentId());
				upocInfo.setChangeAttachmentName(acInfo.getFilename());
				upocInfo.setChangeTimeTxt(sdf.format(upocInfo.getChangeTime()));
			}
			//查询党副的相关信息
			Example dsexample = new Example(DeputySecretaryInfo.class);
			dsexample.createCriteria().andEqualTo("partyOrgId", mainId).andEqualTo("type", "3").andEqualTo("status", 1);
			deputsecList = deputySecretaryInfoMapper.selectByExample(dsexample);
			for(DeputySecretaryInfo dsInfo : deputsecList){
				dsInfo.setDeputySecretaryBirthdayTxt(sdf.format(dsInfo.getDeputySecretaryBirthday()));
			}
			
			if(coverPartyOrgInfo.getPartyOrgTime() != null){
				coverPartyOrgInfo.setPartyOrgTimeTxt(sdf.format(coverPartyOrgInfo.getPartyOrgTime()));
			}
			if(coverPartyOrgInfo.getSecretaryBirthday() != null){
				coverPartyOrgInfo.setSecretaryBirthdayTxt(sdf.format(coverPartyOrgInfo.getSecretaryBirthday()));
			}
			//党员数量计算
			CoverOrgPmbrCount copc = coverPartyOrgService.getPmrCount(id);
			model.addAttribute("main", coverPartyOrgInfo);
			model.addAttribute("changeDateList", changeDateList);
			model.addAttribute("deputsecList", deputsecList);
			model.addAttribute("copc", copc);
		}else{
						
			CoverPartyOrgInfo main = new CoverPartyOrgInfo();
			CoverOrgPmbrCount copc = new CoverOrgPmbrCount();
			model.addAttribute("main", main);
			model.addAttribute("changeDateList", changeDateList);
			model.addAttribute("copc", copc);
		}
		editPage(model);
		return "cover/partyOrgEdit"; 
	}
	
	/**
	 * 保存党组织信息
	 * @param model
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/cover/partyorgedit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> orgSave(CoverPartyOrgInfo coverPartyOrgInfo, HttpServletRequest request, Model model)throws Exception{
		List<CoverPartyOrgChangeInfo> pociList = new ArrayList<>();
		List<DeputySecretaryInfo> dsiList = new ArrayList<>();
		String msg = "";
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		//换届信息和党副信息
		for(int i = 0; i < 8; i++){
        	String changeTime = request.getParameter("partymbrInUnpublicNum"+i);
        	String fileId = request.getParameter("partymbrUnderThirtyfiveNum"+i);
        	if(changeTime != null && changeTime.length()>0 && fileId != null && fileId.length()>0){
        		CoverPartyOrgChangeInfo changeInfo = new CoverPartyOrgChangeInfo();
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
		Integer mainId = coverPartyOrgInfo.getId();
		if(coverPartyOrgInfo.getReportHigher() == 1){
			coverPartyOrgInfo.setStatus("5");//上报申请中
			msg = "上报成功";
		}else{
			coverPartyOrgInfo.setStatus("1");//临时保存
			msg = "新增成功";
		}
		if(mainId == null || StringUtils.isEmpty(mainId.toString())){
			
			try {
				coverPartyOrgService.addOrg(coverPartyOrgInfo, pociList, dsiList);
				return returnMsg(1, msg);
			} catch (Exception e) {
				logger.debug("新增覆盖党组织信息时出现异常:{}", e.getMessage(),e);
				return returnMsg(0, "新增失败:" + e.getMessage());
			}
		}else{
			try {
				coverPartyOrgService.updateOrg(coverPartyOrgInfo, pociList, dsiList);
				if(coverPartyOrgInfo.getReportHigher() == 0){
					msg = "修改成功";
				}
				return returnMsg(1, msg);
			} catch (Exception e) {
				logger.debug("修改覆盖党组织信息时出现异常:{}", e.getMessage(),e);
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
	@RequestMapping(value="/cover/partyOrgSetStatus",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> orgSetStatus(Model model,String id,String status){
		CoverPartyOrgInfo info = new CoverPartyOrgInfo();
		info.setId(Integer.parseInt(id));
		info.setStatus(status);
		
		//info.setUpdateTime(new Date());
		//info.setUpdator(baseService.getUserName());
		coverPartyOrgInfoMapper.updateByPrimaryKeySelective(info);
		return returnMsg(1, "操作成功!");
	}
	
	/**
	 * 党组织信息上报审核
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/cover/partyOrgsSetStatus",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> orgsSetStatus(Model model,String partyOrgIds,String status){
		String[] partyOrgArray = partyOrgIds.split(",");
		for(int i = 0; i < partyOrgArray.length; i++){
			if(partyOrgArray[i] != null && partyOrgArray[i].length() > 0){
				CoverPartyOrgInfo info = new CoverPartyOrgInfo();
				info.setId(Integer.parseInt(partyOrgArray[i]));
				info.setStatus(status);
				
				//info.setUpdateTime(new Date());
				//info.setUpdator(baseService.getUserName());
				coverPartyOrgInfoMapper.updateByPrimaryKeySelective(info);
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
	@RequestMapping(value="/cover/partyorgdelete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> orgdelete(Model model,String id){
		CoverPartyOrgInfo info = new CoverPartyOrgInfo();
		info.setId(Integer.parseInt(id));
		info.setStatus("0");
		try {
			coverPartyOrgInfoMapper.updateByPrimaryKeySelective(info);
			//删除日志
			logService.saveLog(LOG_OPERATION_TYPE.DELETE.toString(), 
					logService.getDetailInfo("log.cover.delete",
							baseService.getUserName(),info.getPartyOrgName()));
			
			return returnMsg(1, "删除成功!");
		} catch (Exception e) {
			logger.debug("删除覆盖党信息时出现异常:{}", e.getMessage(),e);
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
	@RequestMapping(value="/cover/partyorgcancel",method=RequestMethod.GET)
	public String orgcancel(String id,String method,Model model){
		Example example = new Example(CancelReasonInfo.class);
		example.setOrderByClause("createTime desc");
		example.createCriteria().andEqualTo("partyOrgId", Integer.parseInt(id)).andEqualTo("type", '3').andEqualTo("status", 1);
		List<CancelReasonInfo> unpublicOrgCancelRecords = cancelReasonInfoMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(unpublicOrgCancelRecords)){
			model.addAttribute("reason", unpublicOrgCancelRecords.get(0));
		}
		
		model.addAttribute("id", id);
		model.addAttribute("method", method);
		return "cover/partyOrgCancel";
	}
	
	/**
	 * 撤销党组织页面中的文件展示
	 * @param id
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/cover/partyShowlFile",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> showFile(String id, int page,int rows){
		Map<String,Object> result = new HashMap<>();
		int start = (page-1)*rows;
		Example example = new Example(AttachmentCommonInfo.class);
		example.createCriteria().andEqualTo("mainTableId", id).andEqualTo("modular", 3).andEqualTo("type", 1).andEqualTo("action", 1).andEqualTo("status", 1);
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
	@RequestMapping(value="/cover/partyorgcancel",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> orgCancel(Model model,String id,String remarks){
		
		try {
			CancelReasonInfo reason = new CancelReasonInfo();
			reason.setPartyOrgId(Integer.parseInt(id));
			reason.setType("3");
			reason.setReason(remarks);
			reason.setCreateTime(new Date());
			reason.setCreator(baseService.getUserName());
			reason.setStatus(CommonConstants.STATUS_FLAG_VALID);
			CoverPartyOrgInfo info = new CoverPartyOrgInfo();
			info.setId(Integer.parseInt(id));
			info.setStatus("3");
			
			coverPartyOrgService.cancelOrg(reason, info);
			CoverPartyOrgInfo main = coverPartyOrgInfoMapper.selectByPrimaryKey(id);
			//撤销日志
			logService.saveLog(LOG_OPERATION_TYPE.DELETE.toString(), 
					logService.getDetailInfo("log.cover.cancel",
							baseService.getUserName(),main.getPartyOrgName()));
			return returnMsg(1, "撤销成功，等待上级部门审核！");
		} catch (Exception e) {
			logger.debug("撤销覆盖党组织信息时出现异常:{}", e.getMessage(),e);
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
	@RequestMapping(value="/cover/partyNocancel",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> nocancel(Model model,String id,String remarks){
		
		CoverPartyOrgInfo main = new CoverPartyOrgInfo();
		main.setId(Integer.parseInt(id));
		main.setStatus("2");
//		main.setUpdateTime(new Date());
//		main.setUpdator(baseService.getUserName());
		coverPartyOrgService.nocancelOrg(main);
		try {
			
			main = coverPartyOrgInfoMapper.selectByPrimaryKey(id);
			//取消撤销日志
			logService.saveLog(LOG_OPERATION_TYPE.DELETE.toString(), 
					logService.getDetailInfo("log.cover.nocancel",
							baseService.getUserName(),main.getPartyOrgName()));
			return returnMsg(1, "取消撤销该党组织成功！");
		} catch (Exception e) {
			logger.debug("取消撤销覆盖党组织信息时出现异常:{}", e.getMessage(),e);
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
	@RequestMapping(value="/cover/partyCancelok",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> cancelok(Model model,String partyOrgIds,String remarks){
		try {
			String[] partyOrgIdArray = partyOrgIds.split(",");
			for(int i = 0; i < partyOrgIdArray.length; i++){
				CoverPartyOrgInfo main = new CoverPartyOrgInfo();
				main.setId(Integer.parseInt(partyOrgIdArray[i]));
				main.setStatus("4");
//				main.setUpdateTime(new Date());
//				main.setUpdator(baseService.getUserName());
				coverPartyOrgInfoMapper.updateByPrimaryKeySelective(main);
				
				main = coverPartyOrgInfoMapper.selectByPrimaryKey(partyOrgIdArray[i]);
				//撤销审核通过日志
				logService.saveLog(LOG_OPERATION_TYPE.MODIFY.toString(), 
						logService.getDetailInfo("log.socialorg.cancelok",
								baseService.getUserName(),main.getPartyOrgName()));
			}
			
			return returnMsg(1, "审核通过，撤销组织成功！");
		} catch (Exception e) {
			logger.debug("撤销审核通过覆盖党组织信息时出现异常:{}", e.getMessage(),e);
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
	@RequestMapping(value="/cover/uploadFile",method=RequestMethod.GET)     
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
	@RequestMapping(value="/cover/partycancelFile",method=RequestMethod.POST)
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
	@RequestMapping(value="/cover/showPartyInfo",method=RequestMethod.GET)
	public String jumpShowPartyInfo(String partyOrgId, Model model){
		
		model.addAttribute("partyOrgId", partyOrgId);
		return "cover/showPartyInfoPop";
	}
	
	/**
	 * 展示党员基本信息页面
	 * @param id
	 * @param method
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cover/showPartyInfo",method=RequestMethod.POST)
	public void showPartyInfo(HttpServletResponse response, int page, int rows, QueryPmbrParams params){
		PageHelper.startPage(page, rows, true);
		List<CoverOrgPmbrInfo> list = coverOrgPmbrInfoMapper.queryListByOrgId(params.getName(), params.getCoverPartyOrgId());
		for (CoverOrgPmbrInfo coverOrgPmbrInfo : list) {
			coverOrgPmbrInfo.setGender(dict.getValueByCode(CommonConstants.GENDER, coverOrgPmbrInfo.getGender()));
			coverOrgPmbrInfo.setEducation(dict.getValueByCode(CommonConstants.FINAL_EDUCATION, coverOrgPmbrInfo.getEducation()));
			coverOrgPmbrInfo.setIsFromCoverOrg(dict.getValueByCode(CommonConstants.YES_NO, coverOrgPmbrInfo.getIsFromCoverOrg()));
		}
		PageUtil.returnPage(response, new PageInfo<CoverOrgPmbrInfo>(list));
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
	private void convertRows(List<CoverPartyOrgInfo> rows) {
		for (CoverPartyOrgInfo info : rows) {
			this.convertRow(info);
		}
	}

	/**
	 * 转换单条数据
	 * @param info
	 */
	private void convertRow(CoverPartyOrgInfo main) {

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
	
	/**
	 * 党员页面
	 * @param id
	 * @param method
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cover/partyMbrList",method=RequestMethod.GET)
	public String topartyMbrList(String id,String method,Model model){
		
		CoverPartyOrgInfo main = coverPartyOrgInfoMapper.selectByPrimaryKey(id);
		List<DataDictionary> otherConditionList = dict.findByDmm(CommonConstants.UNPUBLIC_PM_OTHER_CONDITION);
		
		model.addAttribute("id", id);
		model.addAttribute("method", method);
		model.addAttribute("main", main);
		model.addAttribute("otherConditionList", otherConditionList);
		return "cover/partyMbrList";
	}
	/**
	 * 党员列表
	 * @param response
	 * @param id
	 * @param page
	 * @param rows
	 * @param params
	 */
	@RequestMapping(value="/cover/partyMbrList",method=RequestMethod.POST)
	public void partyMbrList(HttpServletResponse response,String id,int page,int rows,QueryPmbrParams params){
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		params.setCoverPartyOrgId(id);
		PageHelper.startPage(page, rows, true);
		List<CoverOrgPmbrInfo> list = coverOrgPmbrInfoMapper.queryList(params);
		for (CoverOrgPmbrInfo coverOrgPmbrInfo : list) {
			coverOrgPmbrInfo.setBirthdayTxt(sdf.format(coverOrgPmbrInfo.getBirthday()));
			coverOrgPmbrInfo.setGender(dict.getValueByCode(CommonConstants.GENDER, coverOrgPmbrInfo.getGender()));
			coverOrgPmbrInfo.setEducation(dict.getValueByCode(CommonConstants.FINAL_EDUCATION, coverOrgPmbrInfo.getEducation()));
			coverOrgPmbrInfo.setIsFromCoverOrg(dict.getValueByCode(CommonConstants.YES_NO, coverOrgPmbrInfo.getIsFromCoverOrg()));
		}
		PageUtil.returnPage(response, new PageInfo<CoverOrgPmbrInfo>(list));
	}

	/**
	 * 新增党员页面
	 * @param mainId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cover/addPartymbr",method=RequestMethod.GET)
	public String addPartymbr(String mainId,Model model){
		partyMbrInit(model);
		model.addAttribute("mainId", mainId);
		return "cover/addPartyMbr";
	}
	/**
	 * 新增党员信息
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/cover/addPartymbr",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addPartymbrSave(CoverOrgPmbrInfo coverOrgPmbrInfo){
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		try {
			coverOrgPmbrInfo.setBirthday(sdf.parse(coverOrgPmbrInfo.getBirthdayTxt()));
			coverOrgPmbrInfo.setCreateOrg(baseService.getCurrentUserDeptId());
			coverOrgPmbrInfo.setCreater(baseService.getUserName());
			coverOrgPmbrInfo.setCreateTime(new Date());
			coverOrgPmbrInfo.setStatus("1");
			coverOrgPmbrInfoMapper.insertSelective(coverOrgPmbrInfo);
			return returnMsg(1, "添加党员成功!");
		} catch (Exception e) {
			logger.debug("新增覆盖党组织党员信息时出现异常:{}", e.getMessage(),e);
			return returnMsg(0, "添加党员失败：" + e.getMessage());
		}
		
		
	}
	/**
	 * 减少党员页面
	 * @param id
	 * @param model
	 * @return
	 */
//	@RequestMapping(value="/cover/removePartymbr",method=RequestMethod.GET)
//	public String removePartymbr(String id,Model model){
//		CoverOrgPmbrInfo pmbrInfo = coverOrgPmbrInfoMapper.selectByPrimaryKey(id);
//		model.addAttribute("id", id);
//		model.addAttribute("pmbrInfo", pmbrInfo);
//		return "cover/removePartyMbr";
//	}
	
	/**
	 * 减少党员信息
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/cover/removePartymbr",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> removePartymbrSave(String id){
		
		try {
			CoverOrgPmbrInfo coverOrgPmbrInfo = new CoverOrgPmbrInfo();
			coverOrgPmbrInfo.setId(Integer.parseInt(id));
			coverOrgPmbrInfo.setStatus("0");
			coverOrgPmbrInfoMapper.updateByPrimaryKeySelective(coverOrgPmbrInfo);
			return returnMsg(1, "减少党员成功!");
		} catch (Exception e) {
			logger.debug("减少覆盖党组织党员信息时出现异常:{}", e.getMessage(),e);
			return returnMsg(0, "减少党员失败："+e.getMessage());
		}
				
	}
	
	@RequestMapping(value="/cover/importPartymbr",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> importPartymbrSave(@RequestParam("dataFile") CommonsMultipartFile file, String mainId,
			HttpServletResponse response){
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		// 没数据，返回错误
		if (file.getSize() == 0) {
			return returnMsg(0, "请上传文件.");
		}
		
		String titleArr [] = {"姓名","性别","出生日期","学历","是否从覆盖组织转入"};
		
		int total = 0 , succ = 0 ,  fail = 0;
		String msg = "";
		try {
			List<Map<String,String>> readExcel = ExcelUtils.readExcel(titleArr, file.getInputStream());
			
			total = readExcel.size();
			//存入DB
			List<CoverOrgPmbrInfo> copiList = new ArrayList<>();
			for (Map<String, String> map : readExcel) {
				CoverOrgPmbrInfo params = new CoverOrgPmbrInfo();
				
				params.setCoverOrgInfoId(Integer.parseInt(mainId));
				
				params.setName(map.get("姓名"));
				params.setGender(dict.getCodeByValue(CommonConstants.GENDER, map.get("性别")));
				try{
					params.setBirthday(sdf.parse(map.get("出生日期")));
				}catch(Exception e){
					e.printStackTrace();
				}
				params.setEducation(dict.getCodeByValue(CommonConstants.FINAL_EDUCATION,map.get("学历")));
				params.setIsFromCoverOrg(dict.getCodeByValue(CommonConstants.YES_NO, map.get("是否从覆盖组织转入")));
				params.setCreateOrg(baseService.getCurrentUserDeptId());
				params.setCreater(baseService.getUserName());
				params.setCreateTime(new Date());
				params.setStatus("1");
				copiList.add(params);
				succ ++;
			}
			if(copiList.size() > 0){
				coverOrgPmbrInfoMapper.insertList(copiList);
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
	@RequestMapping(value="/cover/noUpdateRel",method=RequestMethod.GET)
	public String noUpdateRel(Model model){
		
		return "cover/noUpdateRel";		
	}	
	
	@RequestMapping(value="/cover/noUpdateRel",method=RequestMethod.POST)
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
			//unpublicOrgPmbrChangeInfoMapper.insertSelective(unpublicOrgPmbrChangeInfo);
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
	 * 覆盖组织页面
	 * @param id
	 * @param method
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cover/coverPartyOrg",method=RequestMethod.GET)
	public String coverpartyorg(String id,String method,Model model){
		
		model.addAttribute("coverPartyOrgId", id);
		model.addAttribute("method", method);
		return "cover/coverOrgInfoPop";
	}
	
	/**
	 * 覆盖组织页面信息
	 * @param model
	 * @param id
	 * @param remarks
	 * @return
	 */
	@RequestMapping(value="/cover/coverPartyOrg",method=RequestMethod.POST)
	@ResponseBody
	public void coverPartyOrg(String name, String coverPartyOrgId, String method,
			int page,int rows,HttpServletResponse response){
		List<CoverOrgInfo> list = new ArrayList<>();
		PageHelper.startPage(page, rows, true);
		if("cover".equals(method)){
			list = coverPartyOrgInfoMapper.queryCoverOrgInfoList(name);
		}else if("edit".equals(method)){
			list = coverPartyOrgInfoMapper.queryCoverOrgInfoByExample(name, coverPartyOrgId);
		}
		this.converRows(list);
		PageUtil.returnPage(response, new PageInfo<CoverOrgInfo>(list));
		
	}
	
	/**
	 * 保存覆盖组织页面信息
	 * @param model
	 * @param id
	 * @param remarks
	 * @return
	 */
	@RequestMapping(value="/cover/saveCoverInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveCoverInfo(String mainId, String orgIds){
		Map<String, Object> resultMap = new HashMap<>();
		try{
			String[] orgIdArray = orgIds.split(",");
			coverPartyOrgService.saveCoverInfo(mainId, orgIdArray);
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
		List<DataDictionary> yesOrNoList = dict.findByDmm(CommonConstants.YES_NO);
		model.addAttribute("genderList", genderList);
		model.addAttribute("changeTypeList", changeTypeList);
		model.addAttribute("educationList", educationList);
		model.addAttribute("otherConditionList", otherConditionList);
		model.addAttribute("yesOrNoList", yesOrNoList);
	}
	
	/**
	 * 转换数据集合
	 * @param rows
	 */
	private void converRows(List<CoverOrgInfo> rows) {
		for (CoverOrgInfo info : rows) {
			this.converRow(info);
		}
	}

	/**
	 * 转换单条数据
	 * @param info
	 */
	private void converRow(CoverOrgInfo main) {
		if(main.getNature() != null && main.getIndustryType() == null){
			main.setOrgType(dict.getValueByCode(CommonConstants.ORG_NATURE,main.getNature()));
		}else{
			main.setOrgType(dict.getValueByCode(CommonConstants.ENTERPRISE_TYPE, main.getIndustryType()));
		}
		main.setCreateOrgTxt(baseService.getDeptNameById(main.getCreateOrg()));
	}
	
}


