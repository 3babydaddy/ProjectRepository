package com.tf.base.unpublic.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tf.base.unpublic.domain.UnpublicOrgPmbrCount;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface UnpublicOrgPmbrCountMapper extends MySqlMapper<UnpublicOrgPmbrCount>, Mapper<UnpublicOrgPmbrCount> {
	
	List<Map> selectChangeOrgList();
	
	UnpublicOrgPmbrCount getPmbrCount(@Param("orgIdArray") String[] orgIdArray);
}