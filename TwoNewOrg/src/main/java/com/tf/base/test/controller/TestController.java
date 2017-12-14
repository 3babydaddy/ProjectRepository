package com.tf.base.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tf.base.common.constants.CommonConstants;
import com.tf.base.common.domain.DataDictionary;
import com.tf.base.common.domain.DictionaryRepository;
import com.tf.base.common.utils.ExcelUtils;
import com.tf.base.test.domain.Users;
import com.tf.base.test.domain.UsersParams;
import com.tf.base.test.service.TestService;

@Controller
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private DictionaryRepository dictionaryRepository;

	/**
	 * 初始化
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/test/list",method = RequestMethod.GET)
	public String test(Model model){
		initPage(model);
		return "test/testList";
	}
	
	/**
	 * 列表展示
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/test/list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> list(UsersParams params){
		
		return testService.queryList(params);
		
	}
	
	/**
	 * 单表查询 列表展示
	 * @param params
	 * @param response
	 */
	@RequestMapping(value="/test/listWithSingle",method = RequestMethod.POST)
	@ResponseBody
	public void listWithSingle(UsersParams params,HttpServletResponse response){
		
		testService.queryListWithSingle(params,response);
		
	}
	
	
	/**
	 * Excel导出
	 * @param params
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value="/test/export")
	public void exportExcel(UsersParams params,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		
		List<Users> dataList = testService.queryListWithExport(params);
		
		try {
			String[] headers = new String[] { "姓名", "性别", "年龄", "手机号"};
			String[] fieldNames = new String[] { "name", "gender","age", "tel"};
			
			ExcelUtils.exportExcel("用户列表", headers, fieldNames, dataList, response, null,"1.0");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查看
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/test/look",method = RequestMethod.GET)
	public String look(Model model,String id){
		Users users = testService.queryById(id);
		model.addAttribute("users", users);
		return "test/testLook";
	}
	/**
	 * 编辑
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/test/edit",method = RequestMethod.GET)
	public String edit(Model model,String id){
		Users users = testService.queryById(id);
		model.addAttribute("users", users);
		
		initPage(model);
		return "test/testEdit";
	}
	
	/**
	 * 保存
	 * @param model
	 * @param users
	 * @return
	 */
	@RequestMapping(value="/test/save",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> save(Model model,Users users){
		
		return testService.save(users);
	}
	/**
	 * 删除
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/test/delete",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delete(Model model,String id){
		
		return testService.delete(id);
	}
	/**
	 * 初始化参数
	 * @param model
	 */
	private void initPage(Model model){
			
		List<DataDictionary> genderList = dictionaryRepository.findByDmm(CommonConstants.GENDER);
		model.addAttribute("genderList", genderList);
	}
}
