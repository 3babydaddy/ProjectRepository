package com.tf.base.common.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tf.base.common.domain.NodeInfo;
import com.tf.base.common.persistence.CommonMapper;
import com.tf.base.department.domain.DepartmentResource;
import com.tf.base.department.persistence.DepartmentCreateMapper;
import com.tf.base.resource.domain.ResourceInfo;
import com.tf.base.resource.persistence.ResourceQueryMapper;

@Controller
public class CommonController {

	@Autowired
	private ResourceQueryMapper resourceQueryMapper;
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Autowired
	private DepartmentCreateMapper departmentCreateMapper;
	
	@RequestMapping(value = "/common/treeinfo", method = RequestMethod.POST)
	@ResponseBody
	public List<NodeInfo> initTree(String system,String department, String role,String user) {
		
		List<ResourceInfo> info = resourceQueryMapper.getResourceInfoBySys(system);
		
		List<NodeInfo> result = new ArrayList<NodeInfo>();
		
		List<String> checked = getCheckedId(system, department, role, user);
		
		for (ResourceInfo item : info) {
			
			NodeInfo node = new NodeInfo();
			
			node.setId(item.getId());
			node.setName(item.getResourcename());
			node.setpId(StringUtils.isEmpty(item.get_parentId()) ? "0":item.get_parentId());
			node.setOpen(StringUtils.isEmpty(item.getResourcelevel()));
			
			node.setChecked(checked.contains(item.getId()));
			result.add(node);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/common/treesave", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public int saveTree(String system,String department, String role,String user,String selectedIds) {
		
		String[] selected = selectedIds.split(",");
		
		List<DepartmentResource> drInfo = new ArrayList<DepartmentResource>();
		
		DepartmentResource dr;
		
		for (String item : selected) {
			
			if (StringUtils.isEmpty(item)) {
				continue;
			}
			
			dr = new DepartmentResource();
			
			dr.setResourceid(item);
			
			drInfo.add(dr);
		}
		
		departmentCreateMapper.deleteDepartmentResouces(department, system);
		
		if (drInfo.size() > 0) {
			departmentCreateMapper.insertDepartmentResouces(drInfo,department,system);
		}
		
		return 0;
	}
	
	private List<String> getCheckedId(String system,String department, String role,String user) {
		
		if (!StringUtils.isEmpty(department)) {
			
			return commonMapper.getDepartResource(system, department);
		}
		
		if (!StringUtils.isEmpty(role)) {
			
			return commonMapper.getRoleResource(system, role);
		}

		if (!StringUtils.isEmpty(user)) {
			
			return commonMapper.getUserResource(system, user);
		}
		
		return new ArrayList<String>();
	}
}
