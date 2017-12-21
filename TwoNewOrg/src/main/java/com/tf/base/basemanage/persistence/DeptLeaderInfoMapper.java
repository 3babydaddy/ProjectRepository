package com.tf.base.basemanage.persistence;

import org.apache.ibatis.annotations.Param;

import com.tf.base.basemanage.domain.DeptLeaderInfo;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface DeptLeaderInfoMapper extends MySqlMapper<DeptLeaderInfo>, Mapper<DeptLeaderInfo> {
	
	int getUpdateCount(@Param("deptId") Integer deptId);
	
	DeptLeaderInfo queryUpdateDeptInfo(@Param("deptId") String deptId);
}