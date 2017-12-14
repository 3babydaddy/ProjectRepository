package com.tf.base.unpublic.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
import com.tf.base.common.domain.DataDictionary;
import com.tf.base.common.domain.DictionaryRepository;
import com.tf.base.common.service.BaseService;
import com.tf.base.common.utils.ExcelUtils;
import com.tf.base.common.utils.PageUtil;
import com.tf.base.unpublic.domain.AddPmbrParams;
import com.tf.base.unpublic.domain.QueryPmbrParams;
import com.tf.base.unpublic.domain.RemovePmbrParams;
import com.tf.base.unpublic.domain.UnpublicOrgPmbrInfo;
import com.tf.base.unpublic.persistence.UnpublicOrgPmbrInfoMapper;
import com.tf.base.unpublic.service.UnPublicOrgService;

@Controller
public class UnPublicOtherOrgController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DictionaryRepository dict;
	
	@Autowired
	private BaseService baseService;
	@Autowired
	private UnPublicOrgService unPublicOrgService;
	@Autowired
	private UnpublicOrgPmbrInfoMapper unpublicOrgPmbrInfoMapper;

	/**
	 * 初始化页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/unpublic/otherlist",method=RequestMethod.GET)
	public String topartyMbrList(Model model,String id){
		List<DataDictionary> otherConditionList = dict.findByDmm(CommonConstants.UNPUBLIC_PM_OTHER_CONDITION);
		
		model.addAttribute("id", id);
		model.addAttribute("method", "edit");
		model.addAttribute("otherConditionList", otherConditionList);
		return "unpublic/otherPartyMbrList";
	}
	
	@RequestMapping(value="/unpublic/otherPartyMbrList",method=RequestMethod.POST)
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
			unpublicOrgPmbrInfo.setPartymbrNotinUnpublicIs(dict.getValueByCode(CommonConstants.YES_NO, unpublicOrgPmbrInfo.getPartymbrNotinUnpublicIs()));
			unpublicOrgPmbrInfo.setPartymbrOnMiddletechIs(dict.getValueByCode(CommonConstants.YES_NO, unpublicOrgPmbrInfo.getPartymbrOnMiddletechIs()));
			unpublicOrgPmbrInfo.setEducation(dict.getValueByCode(CommonConstants.FINAL_EDUCATION, unpublicOrgPmbrInfo.getEducation()));
		}
		PageUtil.returnPage(response, new PageInfo<UnpublicOrgPmbrInfo>(list));
	}
	
	@RequestMapping(value="/unpublic/otherImportPartymbr",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> importPartymbrSave(@RequestParam("dataFile") CommonsMultipartFile file, String mainId,
			HttpServletResponse response){
		
		// 没数据，返回错误
		if (file.getSize() == 0) {
			return returnMsg(0, "请上传文件.");
		}
		
		String titleArr [] = {"姓名","性别","出生日期","学历","工作单位及所在地","转入类型","农村党员"};
		
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
				params.setWorkunitAndAddress(map.get("工作单位及所在地"));
				params.setType(dict.getCodeByValue(CommonConstants.PARTY_MBR_CHANGE_TYPE, map.get("转入类型")));
				
				params.setPartymbrNotinUnpublicIs("1");//默认选中
				params.setPartymbrInVillageIs(dict.getCodeByValue(CommonConstants.YES_NO, map.get("农村党员")));
				
				unPublicOrgService.addPartymbr(params);;
				
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
	 * 新增党员页面
	 * @param mainId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/unpublic/otherAddPartymbr",method=RequestMethod.GET)
	public String addPartymbr(String mainId,Model model){
		partyMbrInit(model);
		model.addAttribute("mainId", mainId);
		return "unpublic/otherAddPartyMbr";
	}
	/**
	 * 新增党员信息
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/unpublic/otherAddPartymbr",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addPartymbrSave(AddPmbrParams params){
		
		try {
			
			unPublicOrgService.addPartymbr(params);
			
			return returnMsg(1, "添加党员成功!");
		} catch (Exception e) {
			logger.debug("新增非公组织其他党员信息时出现异常:{}", e.getMessage(),e);
			return returnMsg(0, "添加党员失败：" + e.getMessage());
		}
		
		
	}
	/**
	 * 减少党员页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/unpublic/otherRemovePartymbr",method=RequestMethod.GET)
	public String removePartymbr(String id,Model model){
		UnpublicOrgPmbrInfo pmbrInfo = unpublicOrgPmbrInfoMapper.selectByPrimaryKey(id);
		model.addAttribute("id", id);
		model.addAttribute("pmbrInfo", pmbrInfo);
		return "unpublic/otherRemovePartyMbr";
	}
	
	/**
	 * 减少党员信息
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/unpublic/otherRemovePartymbr",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> removePartymbrSave(RemovePmbrParams params){
		
		try {
			unPublicOrgService.delPartymbr(params);
			return returnMsg(1, "减少党员成功!");
		} catch (Exception e) {
			logger.debug("减少非公组织其他党员信息时出现异常:{}", e.getMessage(),e);
			return returnMsg(0, "减少党员失败："+e.getMessage());
		}
				
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
