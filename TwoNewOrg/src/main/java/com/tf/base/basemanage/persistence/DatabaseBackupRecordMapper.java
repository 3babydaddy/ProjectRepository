package com.tf.base.basemanage.persistence;

import com.tf.base.basemanage.domain.DatabaseBackupRecord;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface DatabaseBackupRecordMapper extends MySqlMapper<DatabaseBackupRecord>, Mapper<DatabaseBackupRecord> {
}