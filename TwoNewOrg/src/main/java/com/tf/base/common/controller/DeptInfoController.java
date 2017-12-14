package com.tf.base.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tf.base.common.service.BaseService;
import com.tf.permission.client.entity.DepartmentInfo;

@Controller
public class DeptInfoController {

	@Autowired
	private BaseService baseService;
	
	/**
	 * 仅仅是部门树
	 * @param keyId
	 * @param valueId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/dept/onlyAllDept")
	public String onlyAllDept(String keyId,String valueId,Model model){
		model.addAttribute("keyId", keyId);
		model.addAttribute("valueId", valueId);
		return "common/onlyAllDept";
	}
	
	@RequestMapping(value="/dept/getAllDepts")
	@ResponseBody
	public List<DepartmentInfo> getAllDepts(){
		
		
		return baseService.getAllDepts();
	}
	/**
	 * 包含组织信息的部门树
	 * @param keyId
	 * @param valueId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/dept/hasOrgDept")
	public String hasOrgDept(String keyId,String valueId,Model model){
		model.addAttribute("keyId", keyId);
		model.addAttribute("valueId", valueId);
		return "common/hasOrgDept";
	}
	
	@RequestMapping(value="/dept/getHasOrgAllDepts")
	@ResponseBody
	public List<DepartmentInfo> getHasOrgAllDepts(){
		
		
		List<DepartmentInfo> allDepts =  baseService.getAllDepts();
		
		List<String> ids = new ArrayList<>();
		List<String> parentIds = new ArrayList<>();
		for (DepartmentInfo departmentInfo : allDepts) {
			ids.add(departmentInfo.getId());
			parentIds.add(departmentInfo.getHigherDepart());
		}
		
		List<String> last = new ArrayList<>();
		for (String id : ids) {
			if(parentIds.contains(id)){
				continue;
			}
			last.add(id);
		}
		
		List<DepartmentInfo> lastDepts = new ArrayList<>();
		for (DepartmentInfo departmentInfo : allDepts) {
			for (String id : last) {
				
				if(departmentInfo.getId().equals(id)){
					lastDepts.add(departmentInfo);
				}
			}
		}
		//TODO  
		//获取组织信息  关于部门ID的
		
		
		//构建成与部门相关的 树形结构
		//例如：
		//{id:1,name:"",higherDepart:300031}
		
		return lastDepts;
	}
	
	
}
