package com.tf.base.intelligentwarn.controller;

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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tf.base.common.constants.CommonConstants;
import com.tf.base.common.domain.DataDictionary;
import com.tf.base.common.domain.DictionaryRepository;
import com.tf.base.common.service.BaseService;
import com.tf.base.common.service.LogService;
import com.tf.base.common.utils.GeneralCalcUtils;
import com.tf.base.common.utils.PageUtil;
import com.tf.base.unpublic.domain.UnpublicOrgInfo;
import com.tf.base.unpublic.persistence.UnpublicOrgInfoMapper;

@Controller
public class EarlyWarnInfoController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DictionaryRepository dict;
	
	@Autowired
	private BaseService baseService;
	@Autowired
	private LogService logService;
	
	@Autowired
	private UnpublicOrgInfoMapper unpublicOrgInfoMapper;
	
	/**
	 * 初始化页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/intelligentwarn/earlywarninfolist",method=RequestMethod.GET)
	public String orginit(Model model){
		
		editPage(model);
		return "intelligentwarn/earlyWarnInfoList";
	}
	
	/**
	 * 预警织信息列表展示
	 * @param params
	 * @param page
	 * @param rows
	 * @param response
	 */
	@RequestMapping(value="/intelligentwarn/earlywarninfolist",method=RequestMethod.POST)
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
	 * 编辑页面初始化参数
	 * @param model
	 */
	private void editPage(Model model){
		List<DataDictionary> personnelCategoryList = dict.findByDmm(CommonConstants.PERSONNEL_CATEGORY);
		model.addAttribute("personnelCategoryList", personnelCategoryList);
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
