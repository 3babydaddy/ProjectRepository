package com.tf.base.basemanage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tf.base.basemanage.domain.DatabaseBackupRecord;
import com.tf.base.basemanage.persistence.DatabaseBackupRecordMapper;
import com.tf.base.common.constants.CommonConstants.LOG_OPERATION_TYPE;
import com.tf.base.common.service.BaseService;
import com.tf.base.common.service.DbBackupService;
import com.tf.base.common.service.LogService;
import com.tf.base.common.utils.LogInfoExtUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;


@Controller
public class DbBackupController {

	@Autowired
	private BaseService baseService;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private DbBackupService dbBackupService;
	
	@Autowired
	private DatabaseBackupRecordMapper dbBackupRecordMapper;
	
	@RequestMapping(value="/dbbackup/list",method=RequestMethod.GET)
	public String dbbackup(){
		return "dbbackup/list";
	}
	
	@RequestMapping(value="/dbbackup/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> dbbackuplist(int page,int rows,String startTime,String endTime){
		Map<String,Object> result = new HashMap<String,Object>();
		int start = (page-1)* rows;
		
		Example example = new Example(DatabaseBackupRecord.class);
		Criteria createCriteria = example.createCriteria();
		example.setOrderByClause("backup_time desc");
		if(!StringUtils.isEmpty(startTime)){
			createCriteria.andGreaterThanOrEqualTo("backupTime", startTime);
		}
		if(!StringUtils.isEmpty(endTime)){
			createCriteria.andLessThanOrEqualTo("backupTime", endTime);
		}
		
		int total = dbBackupRecordMapper.selectCountByExample(example);
		
		List<DatabaseBackupRecord> list = new ArrayList<>();
		if(total == 0){
			result.put("rows", list);
			result.put("total", total);
			return result;
		}
		list = dbBackupRecordMapper.selectByExampleAndRowBounds(example, new RowBounds(start,rows));
		
		result.put("total", total);
		result.put("rows", list);
		return result;
	}
	
	@RequestMapping(value="/dbbackup/manual",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> backup(Model model){
		
		if(dbBackupService.back(baseService.getShowName())){
			
			//修改日志
			logService.saveLog(LOG_OPERATION_TYPE.CREATE.toString(), 
					logService.getDetailInfo("log.dbbackup.create",
							baseService.getUserName()));
			return returnMsg(1, "备份成功！");
		}
		return returnMsg(0, "备份失败!");
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
