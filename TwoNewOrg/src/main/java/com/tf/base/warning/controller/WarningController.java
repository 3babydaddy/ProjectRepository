package com.tf.base.warning.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tf.base.common.domain.DataDictionary;
import com.tf.base.common.domain.DictionaryRepository;
import com.tf.base.common.utils.PageUtil;
import com.tf.base.warning.domain.WarningRuleInfo;
import com.tf.base.warning.service.WarningService;

/**
 * @Title: WarningController.java
 * @Description: 智能预警控制类
 * @author wanghw
 * @date 2017年11月17日 上午9:57:32
 * @version V1.0
 */
@Controller
@RequestMapping("/warning")
public class WarningController {

	@Autowired
	private WarningService warnService;

	@Autowired
	private DictionaryRepository dict;

	/**
	 * 跳转预警设置页面
	 * 
	 * @return 页面资源路径
	 */
	@RequestMapping("/set")
	public String initWarningSetting(Model model) {
		initPage(model);
		// TODO: listener test 后续删除
		WarningRuleInfo info = new WarningRuleInfo();
		info.setWarningTitle("测试预警监听器");
		warnService.add(info);
		return "warning/warningSet";
	}

	@RequestMapping(value = "/setList", method = RequestMethod.POST)
	public void warningSetList(WarningRuleInfo params, int page, int rows, HttpServletResponse response) {
		PageHelper.startPage(page, rows, true);
		// TODO: 模拟查询数据
		List<WarningRuleInfo> list = new ArrayList<WarningRuleInfo>();
		PageUtil.returnPage(response, new PageInfo<WarningRuleInfo>(list));
	}

	/**
	 * 初始化页面下拉菜单相关数据字典项
	 * 
	 * @param model
	 */
	private void initPage(Model model) {
		List<DataDictionary> switchs = dict.findByDmm("WARNING_SWITCH");
		List<DataDictionary> cycles = dict.findByDmm("WARNING_CYCLE");
		model.addAttribute("switchs", switchs);
		model.addAttribute("cycles", cycles);
	}

	/**
	 * 跳转预警查询页面
	 * 
	 * @return 页面资源路径
	 */
	@RequestMapping("/query")
	public String initWarningQuery() {
		return "warning/warningQuery";
	}

}