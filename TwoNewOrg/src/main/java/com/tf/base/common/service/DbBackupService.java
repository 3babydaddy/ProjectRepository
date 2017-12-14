package com.tf.base.common.service;

import java.io.File;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tf.base.basemanage.domain.DatabaseBackupRecord;
import com.tf.base.basemanage.persistence.DatabaseBackupRecordMapper;
import com.tf.base.common.constants.CommonConstants;
import com.tf.base.common.utils.ApplicationProperties;
import com.tf.base.common.utils.DataBackupUtil;

@Service
public class DbBackupService {

	static Logger logger = LoggerFactory.getLogger(DataBackupUtil.class);
	
//	public static void main(String[] args) {
//		
//		DataBackupUtil.back();
//	}
	
	@Autowired
	private DatabaseBackupRecordMapper dbBackupRecordMapper;
	
	public boolean back(String operator){
		
		ApplicationProperties app = new ApplicationProperties();
		String ip = app.getValueByKey("db.ip");
		String name = app.getValueByKey("db.name");
		String username = app.getValueByKey("db.username");
		String password = app.getValueByKey("db.password");
		String backupPath = app.getValueByKey("db.backup.path");
		
		String fileName = new Date().getTime()+".sql";
		String dervierPath = CommonConstants.ROOT_DIR + "/resources/db/";
		try {
			logger.debug("backup database : [" + ip +  "][" + name + "]["+username+"]["+password+"]["+backupPath+"][]");
			long start = System.currentTimeMillis();
			boolean r = DataBackupUtil.exportDatabaseTool(ip, username, password, backupPath, fileName, name,dervierPath);
			long end = System.currentTimeMillis();
			if(r)backAfter(backupPath,fileName,operator,end-start);
			return r;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private void backAfter(String backupPath, String fileName, String operator, long spentTime){
		long filesize = new File(backupPath + fileName).length();
		
		DatabaseBackupRecord record = new DatabaseBackupRecord();
		record.setBackupTime(new Date());
		record.setFilename(fileName);
		record.setOperator(operator);
		record.setPath(backupPath);
		record.setFilesize(String.valueOf(filesize));
		record.setSpentTime(String.valueOf(spentTime));
		
		dbBackupRecordMapper.insertSelective(record);
	}
}
