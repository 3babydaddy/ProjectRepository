package com.tf.base.basemanage.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tf.base.basemanage.domain.DeptLeaderInfo;
import com.tf.base.basemanage.domain.DeptLeaderInfoTem;
import com.tf.base.basemanage.persistence.DeptLeaderInfoMapper;
import com.tf.base.basemanage.persistence.DeptLeaderInfoTemMapper;
import com.tf.base.common.constants.CommonConstants;
import com.tf.base.common.constants.CommonConstants.LOG_OPERATION_TYPE;
import com.tf.base.common.domain.DataDictionary;
import com.tf.base.common.domain.DictionaryRepository;
import com.tf.base.common.service.BaseService;
import com.tf.base.common.service.LogService;
import com.tf.base.common.utils.LogInfoExtUtil;
import com.tf.permission.client.constants.PermissionConstants;
import com.tf.permission.client.entity.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Controller
public class DeptLeaderController {

	@Autowired
	private DeptLeaderInfoMapper deptLeaderInfoMapper;
	
	@Autowired
	private DeptLeaderInfoTemMapper deptLeaderInfoTemMapper;
	
	@Autowired
	private DictionaryRepository dictionaryRepository;
	
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private LogService logService;
	
	/**
	 * 初始化页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/deptleader/list",method=RequestMethod.GET)
	public String leaderinit(Model model){
		
		initPage(model);
		return "deptleader/list";
	}
	/**
	 * 人员列表
	 * @param info
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/deptleader/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> leaderlist(DeptLeaderInfo info,int page ,int rows){
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		int start = (page-1)* rows;
		
		Example example = new Example(DeptLeaderInfo.class);
		Criteria createCriteria = example.createCriteria();
		example.setOrderByClause("create_time desc");
		createCriteria.andEqualTo("del", CommonConstants.DEL_FLAG_NO);
		if(!StringUtils.isEmpty(info.getName())){
			createCriteria.andEqualTo("name", info.getName());
		}
		if(!StringUtils.isEmpty(info.getPost())){
			createCriteria.andEqualTo("post", info.getPost());
		}
		if(!StringUtils.isEmpty(info.getPostLevel())){
			createCriteria.andEqualTo("postLevel", info.getPostLevel());
		}
		if(!StringUtils.isEmpty(info.getContactTel())){
			createCriteria.andEqualTo("contactTel", info.getContactTel());
		}
		if(!StringUtils.isEmpty(info.getType())){
			createCriteria.andEqualTo("type", info.getType());
		}
		
		//区委部门
		if(!baseService.isQuWeiDept()){
			createCriteria.andEqualTo("deptId", baseService.getDepartment());
		}
		
		int total = deptLeaderInfoMapper.selectCountByExample(example);
		
		List<DeptLeaderInfo> list = new ArrayList<>();
		if(total == 0){
			result.put("rows", list);
			result.put("total", total);
			return result;
		}
		list = deptLeaderInfoMapper.selectByExampleAndRowBounds(example, new RowBounds(start,rows));
		this.convertRows(list);
		
		result.put("total", total);
		result.put("rows", list);
		
		return result;
	}
	/**
	 * 查看
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/deptleader/look",method=RequestMethod.GET)
	public String look(String id,Model model){
		
		DeptLeaderInfo info = deptLeaderInfoMapper.selectByPrimaryKey(id);
		this.convertRow(info);
		
		//查看日志
		logService.saveLog(LOG_OPERATION_TYPE.VIEW.toString(), 
				logService.getDetailInfo("log.deptleader.view",
						baseService.getUserName(),info.getName()));
		model.addAttribute("info", info);
		return "deptleader/look";
	}
	/**
	 * 编辑页面
	 * @param id
	 * @param model
	 * @param method
	 * @return
	 */
	@RequestMapping(value="/deptleader/edit",method=RequestMethod.GET)
	public String edit(String id,Model model,String method){
		
		if("edit".equals(method)){
			
			DeptLeaderInfo info = deptLeaderInfoMapper.selectByPrimaryKey(id);
			if(info == null){
				model.addAttribute("msg", "信息不存在!");
			}
			//this.convertRow(info);
			info.setDeptName(baseService.getDeptNameById(info.getDeptId()));
			model.addAttribute("info", info);
		}else{
			DeptLeaderInfo info = new DeptLeaderInfo();
			info.setDeptId(baseService.getDepartment());
			info.setDeptName(baseService.getDeptName());
			model.addAttribute("info", info);
		}
		
		model.addAttribute("method", method);
		initPage(model);
		return "deptleader/edit";
	}
	/**
	 * 保存信息
	 * @param model
	 * @param method
	 * @param info
	 * @return
	 */
	@RequestMapping(value="/deptleader/save")
	@ResponseBody
	public  Map<String,Object> save(Model model,String method,DeptLeaderInfo info,HttpSession session){
		String msg = "";
		int i = 0;
		if("edit".equals(method)){
			DeptLeaderInfo oldInfo = deptLeaderInfoMapper.selectByPrimaryKey(info.getId());
			//部门：修改需区委同意，才能修改表中数据；区委：不用审核，直接修改
			if(!baseService.isQuWeiDept()){
				//i = deptLeaderInfoMapper.updateByPrimaryKeySelective(info);
//				DeptLeaderInfoTem deptLeaderInfoTem = deptLeaderInfoTemMapper.selectByPrimaryKey(info.getId());
//				if(deptLeaderInfoTem != null){
//					deptLeaderInfoTem = this.translateRow(info);
//					i = deptLeaderInfoTemMapper.updateByPrimaryKeySelective(deptLeaderInfoTem);
//				}else{
				DeptLeaderInfoTem deptLeaderInfoTem = this.translateRow(info, session);
				i = deptLeaderInfoTemMapper.insert(deptLeaderInfoTem);
				oldInfo.setStatus("3");
				deptLeaderInfoMapper.updateByPrimaryKeySelective(oldInfo);
				//}
				msg = "修改成功;请等待审核结果";
			}else{
				i = deptLeaderInfoMapper.updateByPrimaryKeySelective(info);
				msg = "修改成功";
			}
			
			if(i > 0){
				try {
					//修改日志
					StringBuffer sb = new StringBuffer();
					sb.append(LogInfoExtUtil.getModifyLog(dictionaryRepository, oldInfo,info));
					logService.saveLog(LOG_OPERATION_TYPE.MODIFY.toString(), 
							logService.getDetailInfo("log.deptleader.modify",
									baseService.getUserName(),oldInfo.getName(),
									sb));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			return returnMsg(1, i  > 0 ? msg :"修改失败");
		}
		else{
			
			info.setCreateTime(new Date());
			info.setCreator(baseService.getUserName());
			i = deptLeaderInfoMapper.insertSelective(info);
			
			if(i > 0){
				try {
					//新增日志
					StringBuffer sb = new StringBuffer();
					sb.append(LogInfoExtUtil.getAddLog(dictionaryRepository, info));
					logService.saveLog(LOG_OPERATION_TYPE.CREATE.toString(), 
							logService.getDetailInfo("log.deptleader.create",
									baseService.getUserName(),info.getName(),
									sb));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			return returnMsg(1, i  > 0 ? "新增成功.":"新增失败");
		}
	}
	

	/**
	 * 删除信息
	 * @param id
	 * @param method
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/deptleader/del",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> del(String id,String status,String method,Model model)throws Exception{
		String msg = "";
		status = new String(status.getBytes("iso-8859-1"),"utf-8");
		DeptLeaderInfo info = new DeptLeaderInfo();
		info.setId(Integer.parseInt(id));
		if("临时保存".equals(status)){
			info.setDel(CommonConstants.DEL_FLAG_YES);
			info.setStatus("0");
			msg = "删除成功";
		}else{
			info.setStatus("4");
			msg = "删除上报成功";
		}
		//info.setDel(CommonConstants.DEL_FLAG_YES);
		if(deptLeaderInfoMapper.updateByPrimaryKeySelective(info) > 0){

			try {
				info = deptLeaderInfoMapper.selectByPrimaryKey(id);
				//删除日志
				logService.saveLog(LOG_OPERATION_TYPE.MODIFY.toString(), 
						logService.getDetailInfo("log.deptleader.delete",
								baseService.getUserName(),info.getName()));
			} catch (Exception e) {
				// TODO: handle exception
			}
			return returnMsg(1, msg);
		}
		else
			return returnMsg(0, "删除失败");
	}
	
	/**
	 * 审核信息
	 * @param id
	 * @param method
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/deptleader/audit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> audit(String id,String status,String method,Model model)throws Exception{
		status = new String(status.getBytes("iso-8859-1"),"utf-8");
		DeptLeaderInfo info = new DeptLeaderInfo();
		info.setId(Integer.parseInt(id));
		if("删除申请".equals(status)){
			info.setDel(CommonConstants.DEL_FLAG_YES);
			info.setStatus("0");
		}else{
			info.setStatus("2");
			DeptLeaderInfoTem deptLeaderInfoTem = deptLeaderInfoTemMapper.selectByPrimaryKey(info.getId());
			if(deptLeaderInfoTem != null && deptLeaderInfoTem.getId() != null){
				info = this.reverseTranslateRow(deptLeaderInfoTem);
				deptLeaderInfoTemMapper.deleteByPrimaryKey(info.getId());
			}
		}
		if(deptLeaderInfoMapper.updateByPrimaryKeySelective(info) > 0){

			try {
				info = deptLeaderInfoMapper.selectByPrimaryKey(id);
				//删除日志
				logService.saveLog(LOG_OPERATION_TYPE.DELETE.toString(), 
						logService.getDetailInfo("log.deptleader.delete",
								baseService.getUserName(),info.getName()));
			} catch (Exception e) {
				// TODO: handle exception
			}
			return returnMsg(1, "审核成功");
		}
		else
			return returnMsg(0, "审核失败");
	}
	
	@RequestMapping(value="/deptleader/getAllDept")
	@ResponseBody
	public String getAllDept(Model model){
		JSONObject children = new JSONObject();
		children.element("id", 11).element("text", "aaaaa");
		JSONArray childJsonArray = new JSONArray();
		childJsonArray.add(children);
		
		JSONObject json = new JSONObject();
		json.element("id", 1).element("text", "bbbb").element("children", childJsonArray);
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(json);
		return jsonArray.toString();
	}
	
	/**
	 * 初始化参数
	 * @param model
	 */
	private void initPage(Model model){
			
		List<DataDictionary> postLevelList = dictionaryRepository.findByDmm(CommonConstants.POST_LEVEL);
		List<DataDictionary> leaderLevelList = dictionaryRepository.findByDmm(CommonConstants.LEADER_LEVEL);
		
		model.addAttribute("postLevelList", postLevelList);
		model.addAttribute("leaderLevelList", leaderLevelList);
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
	
	/**
	 * 转换数据集合
	 * @param rows
	 */
	private void convertRows(List<DeptLeaderInfo> rows) {
		for (DeptLeaderInfo info : rows) {
			this.convertRow(info);
		}
	}


	/**
	 * 转换单条数据
	 * @param info
	 */
	private void convertRow(DeptLeaderInfo info) {

		info.setPostLevel(dictionaryRepository.getValueByCode(CommonConstants.POST_LEVEL,info.getPostLevel()));
		info.setType(dictionaryRepository.getValueByCode(CommonConstants.LEADER_LEVEL,info.getType()));
		info.setDeptName(baseService.getDeptNameById(info.getDeptId()));
		info.setStatus(dictionaryRepository.getValueByCode(CommonConstants.STATUS,info.getStatus()));
	}
	
	/**
	 * 部门信息实体类向临时的实体类转化
	 * @param info
	 */
	private DeptLeaderInfoTem translateRow(DeptLeaderInfo info, HttpSession session){
		DeptLeaderInfoTem deptLeaderInfoTem = new DeptLeaderInfoTem();
		User user = (User) session.getAttribute(PermissionConstants.CURRENT_USER);
		deptLeaderInfoTem.setId(info.getId());
		deptLeaderInfoTem.setDeptId(info.getDeptId());
		deptLeaderInfoTem.setName(info.getName());
		deptLeaderInfoTem.setPost(info.getPost());
		deptLeaderInfoTem.setPostLevel(info.getPostLevel());
		deptLeaderInfoTem.setContactTel(info.getContactTel());
		deptLeaderInfoTem.setType(info.getType());
		deptLeaderInfoTem.setCreateTime(new Date());
		deptLeaderInfoTem.setCreator(user.getUsername());
		deptLeaderInfoTem.setDel("0");
		deptLeaderInfoTem.setStatus("2");
		return deptLeaderInfoTem;
	}
	
	/**
	 * 临时的实体类向部门信息实体类类转化
	 * @param info
	 */
	private DeptLeaderInfo reverseTranslateRow(DeptLeaderInfoTem info){
		DeptLeaderInfo deptLeaderInfo = new DeptLeaderInfo();
		deptLeaderInfo.setId(info.getId());
		deptLeaderInfo.setDeptId(info.getDeptId());
		deptLeaderInfo.setName(info.getName());
		deptLeaderInfo.setPost(info.getPost());
		deptLeaderInfo.setPostLevel(info.getPostLevel());
		deptLeaderInfo.setContactTel(info.getContactTel());
		deptLeaderInfo.setType(info.getType());
		deptLeaderInfo.setCreateTime(info.getCreateTime());
		deptLeaderInfo.setCreator(info.getCreator());
		deptLeaderInfo.setDel(info.getDel());
		deptLeaderInfo.setStatus(info.getStatus());
		return deptLeaderInfo;
	}
}
