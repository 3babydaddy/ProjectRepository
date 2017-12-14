package com.tf.base.basemanage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tf.base.basemanage.domain.PersonalSet;
import com.tf.base.common.constants.CommonConstants.LOG_OPERATION_TYPE;
import com.tf.base.common.domain.DictionaryRepository;
import com.tf.base.common.service.BaseService;
import com.tf.base.common.service.LogService;
import com.tf.base.common.utils.LogInfoExtUtil;
import com.tf.base.user.controller.UserInfo;
import com.tf.permission.client.constants.PermissionConstants;
import com.tf.permission.client.entity.User;
import com.tf.permission.client.service.PermissionClientService;


@Controller
public class PersonalSetController {

	@Autowired
	private DictionaryRepository dict;
	
	@Autowired
	private BaseService baseService;
	@Autowired
	private LogService logService;
	
	@Autowired
	private PermissionClientService permissionClientService;
	
	@RequestMapping(value="/personal/set",method=RequestMethod.GET)
	public String setinit(Model model){
		model.addAttribute("name", baseService.getShowName());
		model.addAttribute("tel", ((User)baseService.getBaseInfo("user")).getTel());
		model.addAttribute("password", ((User)baseService.getBaseInfo("user")).getPassword());
		return "personalset/edit";
	}
	
	@RequestMapping(value="/personal/set",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> set(PersonalSet personalSet,HttpSession session){
		
		User oldUser = (User) session.getAttribute(PermissionConstants.CURRENT_USER);
		
		if(!oldUser.getPassword().equals(personalSet.getOldpassword())){
			return returnMsg(0, "原始密码不正确!");
		}
		int i = permissionClientService.modifyPassWordAndTel(baseService.getUserName(),personalSet.getName(),personalSet.getPassword() ,personalSet.getTel());
		if(i > 0){
			User user = permissionClientService.findUserByUsername(baseService.getUserName());
			session.setAttribute(PermissionConstants.CURRENT_USER,user);
			
			UserInfo oldu = new UserInfo();
			UserInfo newu = new UserInfo();
			BeanUtils.copyProperties(oldUser, oldu);
			BeanUtils.copyProperties(user, newu);
			//修改日志
			logService.saveLog(LOG_OPERATION_TYPE.MODIFY.toString(), 
					logService.getDetailInfo("log.personalset.modify",
							baseService.getUserName(),
							LogInfoExtUtil.getModifyLog(dict, oldu , newu)));
			return returnMsg(1, "修改成功!");
		}
		else
			return returnMsg(0, "修改失败!");
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
