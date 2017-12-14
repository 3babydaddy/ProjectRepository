package com.tf.base.common.quartzJob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tf.base.basemanage.domain.DataOnOff;
import com.tf.base.basemanage.persistence.DataOnOffMapper;
import com.tf.base.common.constants.CommonConstants;
import com.tf.base.common.service.DbBackupService;

@Service
public class DbBackupJobService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DataOnOffMapper dataOnOffMapper;
	
	@Autowired
	private DbBackupService dbBackupService;

	public void excute() {
		
		DataOnOff param  = new DataOnOff();
		param.setModule(CommonConstants.MODULE_DATABASE_BACKUP);
		DataOnOff dataOnOff = dataOnOffMapper.selectOne(param);
		
		//开启时备份
		if(CommonConstants.SWITCH_ON.equals(dataOnOff.getOnOff())){
			
			logger.debug("==============================>:backup start");
			dbBackupService.back("系统");
			logger.debug("==============================>:end backup");
		}
	}
}
