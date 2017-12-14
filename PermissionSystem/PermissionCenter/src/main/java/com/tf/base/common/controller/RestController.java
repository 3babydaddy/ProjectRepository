package com.tf.base.common.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tf.base.common.domain.LogInfo;
import com.tf.base.common.domain.SystemUserInfo;
import com.tf.base.common.persistence.RestMapper;
import com.tf.base.common.utils.MD5Utils;
import com.tf.base.common.utils.ServletUtils;
import com.tf.base.department.domain.DepartmentInfo;
import com.tf.base.department.persistence.DepartmentQueryMapper;
import com.tf.base.log.persistence.LogInfoCreateMapper;
import com.tf.base.resource.domain.ResourceInfo;
import com.tf.base.resource.persistence.ResourceQueryMapper;
import com.tf.base.user.domain.UserInfo;
import com.tf.base.user.persistence.UserModifyMapper;
import com.tf.base.user.persistence.UserQueryMapper;

@Controller
public class RestController {
	
	@Autowired
	private ResourceQueryMapper resourceQueryMapper;
	
	@Autowired
	private UserQueryMapper userQueryMapper;
	
	@Autowired
	private RestMapper restMapper;
	
	@Autowired
	private DepartmentQueryMapper departmentQueryMapper;
	
	@Autowired
	private UserModifyMapper userModifyMapper;
	@Autowired
	private LogInfoCreateMapper logInfoCreateMapper;

	@RequestMapping(value="/queryAll/{sysId}",method = RequestMethod.GET)
	@ResponseBody
	public List<ResourceInfo> getAllResourceInfo(@PathVariable String sysId) {
		
		return resourceQueryMapper.getResourceInfoBySys(sysId);
	}
	
	@RequestMapping(value="/queryByUser/{sysId}/{user}",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getResourceInfo(@PathVariable String sysId, @PathVariable String user) {
		
		UserInfo userInfo = restMapper.getUserInfo(sysId, user);
		
		List<ResourceInfo> res = new ArrayList<ResourceInfo>();
		
		List<String> allRes = new ArrayList<String>();
		
		String password = null;
		if (userInfo != null && !StringUtils.isEmpty(userInfo.getPassword())) {
			
			password = userInfo.getPassword();
			List<String>  depr = restMapper.getDaprRes(sysId, user);
			List<String>  role = restMapper.getRoleRes(sysId, user);
			
			allRes.addAll(depr);
			
			for (String item : role) {
				
				if (!allRes.contains(item)) {
					
					allRes.add(item);
				}
			}
			
			if (allRes.size() > 0) {
				res = restMapper.getRes(allRes);
			}
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("Password", password);
		result.put("Permission", res);
		result.put("UserInfo", userInfo);
		
		return result;
	}
	
	@RequestMapping(value="/user/passwordmodify/{userName}/{oldPassword}/{newPassword}",method = RequestMethod.GET)
	@ResponseBody
	public int modifyPassword(@PathVariable String userName,@PathVariable String oldPassword,@PathVariable String newPassword) {
		
		if (restMapper.checkPassword(userName, oldPassword,MD5Utils.md5(oldPassword,userName)) == 0) {
			
			return -1;
		}
		
		restMapper.modifyPassword(userName, newPassword,MD5Utils.md5(newPassword,userName));
		
		return 0;
	}
	
	@RequestMapping(value="/user/passwordandtelmodify",method = RequestMethod.POST)
	@ResponseBody
	public int modifyPasswordAndtel(HttpServletRequest request) {
		String req = ServletUtils.receiveData(request);
		JSONObject params = JSON.parseObject(req);
		
		String userName = params.getString("userName");
		 String newShowName = params.getString("newShowName");
		 String newPassword = params.getString("newPassword");
		 String newTel = params.getString("newTel");
		
		int i = restMapper.modifyPasswordAndTel(userName,newShowName, newPassword,MD5Utils.md5(newPassword,userName),newTel);
		
		return i;
	}
	
	@RequestMapping(value="/user/getsystemUser/{systemId}",method = RequestMethod.GET)
	@ResponseBody
	public List<SystemUserInfo> getsystemUser(@PathVariable String systemId) {
		
		return restMapper.getSystemUserInfo(systemId);
	}
	
	@RequestMapping(value="/user/queryUserInfosByDepartId/{departId}",method = RequestMethod.GET)
	@ResponseBody
	public List<UserInfo> queryUserInfosByDepartId(@PathVariable String departId) {
//		Map<String, Object> result = new HashMap<String, Object>();
		List<UserInfo> queryUserList = null;
		if (!StringUtils.isEmpty(departId)) {
			UserInfo userInfo = new UserInfo();
			userInfo.setDepartment(departId);
			userInfo.setAvail("0");
			queryUserList = userQueryMapper.findUserInfoByParams(userInfo);
			if (queryUserList == null || queryUserList.isEmpty()) {
//				result.put("userList", queryUserList);
				return new ArrayList<UserInfo>();
			}
		}
		return queryUserList;
	}
	
	@RequestMapping(value="/user/queryUserInfosByRoleId/{roleId}" ,method = RequestMethod.GET)
	@ResponseBody
	public List<UserInfo> queryUserInfosByRoleId(@PathVariable String roleId){
		List<UserInfo> queryUserList = null;
		if(!StringUtils.isEmpty(roleId)){
			Map<String, String> map = new HashMap<String,String>();
			map.put("roleId", roleId);
			map.put("avail", "0");
			queryUserList = userQueryMapper.findUserInfoByRoleId(map);
			if(queryUserList == null || queryUserList.isEmpty()){
				return new ArrayList<UserInfo>();
			}
		}
		return queryUserList;
	}
	
	
	
	@RequestMapping(value="/dept/queryAllDepartments",method = RequestMethod.GET)
	@ResponseBody
	public List<DepartmentInfo> queryAllDepartments() {
		return departmentQueryMapper.getAllDepartmentInfo();
	}
	
	@RequestMapping(value="/updatePassword" , method = RequestMethod.GET)
	public void updatePassword(HttpServletResponse response){
		try {
			List<UserInfo> list = userQueryMapper.selectAll();
			for(UserInfo user : list){
				user.setEncPassword(MD5Utils.md5(user.getPassword(),user.getUsername()));
				userModifyMapper.modifyPasswordByuserName(user);
			}
			response.getWriter().println("updatePassword success！");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				response.getWriter().println("updatePassword fail："+ e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value="/log/savelog",method = RequestMethod.POST)
	@ResponseBody
	public int savelog(HttpServletRequest request) {
		String req = ServletUtils.receiveData(request);
		JSONObject params = JSON.parseObject(req);
		LogInfo log = new LogInfo();
		log.setCreatetime(params.getDate("createtime"));//操作时间
		UserInfo userInfo = restMapper.getUserInfo(params.getString("systemid"), params.getString("username"));
		log.setUserid(userInfo.getId());
		log.setCreator(params.getString("username"));
		log.setDescription(params.getString("desc"));
		log.setSystemid(params.getString("systemid"));
		log.setType(params.getString("type"));
		log.setIp(params.getString("ip"));
		int i = logInfoCreateMapper.saveLog(log);
		return  i;
	}
	
}
