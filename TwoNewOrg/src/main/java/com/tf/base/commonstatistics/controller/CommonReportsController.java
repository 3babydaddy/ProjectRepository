package com.tf.base.commonstatistics.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tf.base.common.constants.CommonConstants;
import com.tf.base.common.domain.DataDictionary;
import com.tf.base.common.domain.DictionaryRepository;
import com.tf.base.common.excel.Demo;
import com.tf.base.common.excel.ExcelDataFormatter;
import com.tf.base.common.excel.ExcelUtils;
import com.tf.base.common.service.BaseService;
import com.tf.base.common.service.LogService;
import com.tf.base.common.utils.PageUtil;
import com.tf.base.unpublic.domain.UnpublicOrgInfo;
import com.tf.base.unpublic.persistence.UnpublicOrgInfoMapper;

@Controller
public class CommonReportsController {

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
	public void unpublicReportList(UnpublicOrgInfo params, int page, int rows, HttpServletResponse response) {
		String deptId = baseService.getCurrentUserDeptId();
		logger.debug("当前登录用户部门ID:===========>" + deptId + " ,是否为区委部门?" + baseService.isQuWeiDept());

		String orderby = "";
		if (!baseService.isQuWeiDept()) {
			params.setCreateOrg(deptId);
			orderby = " main.update_time desc , main.status desc, main.create_time desc";
		} else {
			orderby = " main.status desc , main.create_time desc";
		}
		PageHelper.startPage(page, rows, true);
		List<UnpublicOrgInfo> list = unpublicOrgInfoMapper.queryList(params, orderby);
		this.convertRows(list);
		list.clear();
		PageUtil.returnPage(response, new PageInfo<UnpublicOrgInfo>(list));
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "/file/exportDemo")
	@ResponseBody
	public boolean export(HttpServletResponse response1) {
		System.out.println("写Excel");
		List<Demo> list = new ArrayList<Demo>();
		Demo u = new Demo();
		u.setAge("3");
		u.setName("fdsafdsa");
		u.setXx(123.23D);
		u.setYy(new Date());
		u.setLocked(false);
		u.setDb(new BigDecimal(123));
		list.add(u);

		u = new Demo();
		u.setAge("23");
		u.setName("fdsafdsa");
		u.setXx(123.23D);
		u.setYy(new Date());
		u.setLocked(true);
		u.setDb(new BigDecimal(234));
		list.add(u);

		u = new Demo();
		u.setAge("123");
		u.setName("fdsafdsa");
		u.setXx(123.23D);
		u.setYy(new Date());
		u.setLocked(false);
		u.setDb(new BigDecimal(2344));
		u.setStatus("1");
		list.add(u);

		u = new Demo();
		u.setAge("年龄");
		u.setName("姓名");
		u.setXx(123.23D);
		u.setYy(new Date());
		u.setLocked(true);
		u.setDb(new BigDecimal(908));
		list.add(u);

//		ExcelDataFormatter edf = new ExcelDataFormatter();
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("true", "真");
//		map.put("false", "假");
//		edf.set("locked", map);

		ExcelUtils excelUtils = new ExcelUtils(Demo.class);
		try {
			excelUtils.writeToFile(list, 6, response1);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 新区民办学校党建工作台账初始化页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/commonstatistics/privateschoolworkledger", method = RequestMethod.GET)
	public String orginit(Model model) {

		editPage(model);
		return "commonstatistics/privateSchoolWorkLedger";
	}

	/**
	 * 新区民办学校党建工作台账列表展示
	 * 
	 * @param params
	 * @param page
	 * @param rows
	 * @param response
	 */
	@RequestMapping(value = "/commonstatistics/privateschoolworkledger", method = RequestMethod.POST)
	public void orglist(UnpublicOrgInfo params, int page, int rows, HttpServletResponse response) {
		String deptId = baseService.getCurrentUserDeptId();
		logger.debug("当前登录用户部门ID:===========>" + deptId + " ,是否为区委部门?" + baseService.isQuWeiDept());

		String orderby = "";
		if (!baseService.isQuWeiDept()) {
			params.setCreateOrg(deptId);
			orderby = " main.update_time desc , main.status desc, main.create_time desc";
		} else {
			orderby = " main.status desc , main.create_time desc";
		}
		PageHelper.startPage(page, rows, true);
		List<UnpublicOrgInfo> list = unpublicOrgInfoMapper.queryList(params, orderby);
		this.convertRows(list);
		list.clear();
		PageUtil.returnPage(response, new PageInfo<UnpublicOrgInfo>(list));
	}

	/**
	 * 列表页面初始化参数
	 * 
	 * @param model
	 */
	private void editPage(Model model) {
		List<DataDictionary> yesNoList = dict.findByDmm(CommonConstants.YES_NO);

		model.addAttribute("yesNoList", yesNoList);
	}

	/**
	 * 转换数据集合
	 * 
	 * @param rows
	 */
	private void convertRows(List<UnpublicOrgInfo> rows) {
		for (UnpublicOrgInfo info : rows) {
			this.convertRow(info);
		}
	}

	/**
	 * 转换单条数据
	 * 
	 * @param info
	 */
	private void convertRow(UnpublicOrgInfo main) {

		main.setStatusTxt(dict.getValueByCode(CommonConstants.UNPUBLIC_ORG_STATUS, main.getStatus()));
		main.setIndustryTypeTxt(dict.getValueByCode(CommonConstants.ENTERPRISE_TYPE, main.getIndustryType()));
		main.setLevelTxt(dict.getValueByCode(CommonConstants.ZONE_LEVEL, main.getLevel()));
		main.setMillionBuildingIsTxt(dict.getValueByCode(CommonConstants.YES_NO, main.getMillionBuildingIs()));
		main.setBelocatedAddressTxt(
				dict.getValueByCode(CommonConstants.ENTERPRISE_BELOCATED_ADDRESS, main.getBelocatedAddress()));
		main.setOnScaleIsTxt(dict.getValueByCode(CommonConstants.YES_NO, main.getOnScaleIs()));

		String businessType = splitConvertField(CommonConstants.BUSINESS_TYPE, main.getBusinessType());

		main.setBusinessTypeTxt(businessType);
		String nationality = splitConvertField(CommonConstants.NATIONALITY, main.getNationality());
		main.setNationlityTxt(nationality);

		main.setCreateOrgTxt(baseService.getDeptNameById(main.getCreateOrg()));
	}

	private String splitConvertField(String code, String value) {
		StringBuffer sb = new StringBuffer();

		if (value == null || value.equals(""))
			return "";
		String values[] = value.split(",");
		if (values.length > 0) {

			for (String v : values) {
				sb.append(dict.getValueByCode(code, v));
				sb.append(",");
			}
			return (!sb.toString().equals("") ? sb.toString().substring(0, sb.toString().length() - 1) : sb.toString());
		} else {
			return "";
		}
	}

	/**
	 * 返回信息及标示
	 * 
	 * @param status
	 * @param msg
	 * @return
	 */
	private Map<String, Object> returnMsg(int status, String msg) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", status);
		result.put("msg", msg);
		return result;
	}
}
