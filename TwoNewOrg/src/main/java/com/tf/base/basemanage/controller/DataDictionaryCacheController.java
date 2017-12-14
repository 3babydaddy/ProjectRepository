package com.tf.base.basemanage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tf.base.basemanage.domain.DictionaryForm;
import com.tf.base.common.domain.DataDictionary;
import com.tf.base.common.domain.DictionaryRepository;
import com.tf.base.common.persistence.DataDictionaryMapper;
import com.tf.base.common.service.BaseService;
import com.tf.base.common.utils.PageUtil;

import net.sf.json.JSONObject;

@Controller
public class DataDictionaryCacheController {

	
	
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private DictionaryRepository dictionaryRepository;
	
	@Autowired
	private DataDictionaryMapper dataDictionaryMapper;
	
	/**
	 * 数据字典页面初始化
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/systemset/datadictionary", method = RequestMethod.GET)
	public String dataDictionaryInit(HttpServletRequest request,Model model) {
		
		return "systemset/datadictionary";
	}
	/**
	 * 根据条件显示数据
	 * @param form
	 * @param response
	 */
	@RequestMapping(value="/systemset/datadictionaryList")
	@ResponseBody
	public void datadictionaryList(DictionaryForm form,HttpServletResponse response){
		
		PageHelper.startPage(form.getPage(),form.getRows());
		List<DataDictionary> dataDictionaries= dataDictionaryMapper.selectByCondition(form);
		PageUtil.returnPage(response, new PageInfo<DataDictionary>(dataDictionaries));
	}
	
	/**
	 * 根据ID查询配置信息
	 * （展示修改页面dialog）
	 * @param id
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/systemset/reloadcache", method = RequestMethod.POST)
	public void reloadcache(Model model,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			dictionaryRepository.loadCache();
			map.put("data","重新加载成功");
			map.put("errorMsg", null);
		}catch (Exception e) {
			map.put("data"    , "重新加载异常");
			map.put("errorMsg", "重新加载异常"+e.getMessage());
		}
		try {
			response.getWriter().print(JSONObject.fromObject(map).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加
	 * @param dataDictionary
	 * @param isLoad
	 * @return
	 */
	@RequestMapping(value="/systemset/addInfo")
	@ResponseBody
	public Map<String,Object> addInfo(DataDictionary dataDictionary,String isLoad ){
		Map<String, Object> result = new HashMap<String, Object>();
		int i=0;
		try{
			dataDictionaryMapper.insertSelective(dataDictionary);
			if(isLoad!=null && "1".equals(isLoad)){//加载缓存
				dictionaryRepository.loadCache();
			}
			System.out.println(isLoad);
			i++;
		}catch(Exception e){
			e.printStackTrace();
		}
		if(i>0){
			result.put("status",1);
			result.put("msg","添加成功！");
		}else{
			result.put("status",0);
			result.put("errorMsg","添加失败！");
		}
		return result;
	}
	/**
	 * 编辑
	 * @param dataDictionary
	 * @param id
	 * @param isLoad
	 * @return
	 */
	@RequestMapping(value="/systemset/editInfo")
	@ResponseBody
	public Map<String,Object> editInfo(DataDictionary dataDictionary,Integer id,String isLoad ){
		Map<String, Object> result = new HashMap<String, Object>();
		int i=0;
		try{
			dataDictionary.setId(id);
			dataDictionaryMapper.updateByPrimaryKeySelective(dataDictionary);
			if(isLoad!=null && "1".equals(isLoad)){//加载缓存
				dictionaryRepository.loadCache();
			}
			i++;
		}catch(Exception e){
			e.printStackTrace();
		}
		if(i>0){
			result.put("status",1);
			result.put("msg","修改成功！");
		}else{
			result.put("status",0);
			result.put("errorMsg","修改失败！");
		}
		return result;
	}
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/systemset/delInfo")
	@ResponseBody
	public Map<String,Object> delInfo(Integer id){
		Map<String, Object> result = new HashMap<String, Object>();
		int i=0;
		try{
			i = dataDictionaryMapper.deleteByPrimaryKey(id);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(i>0){
			result.put("status",1);
			result.put("msg","删除成功！");
		}else{
			result.put("status",0);
			result.put("errorMsg","删除失败！");
		}
		return result;
	}
}
