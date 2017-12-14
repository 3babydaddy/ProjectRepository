package com.tf.base.socialorg.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tf.base.socialorg.domain.SocialOrgPmbrCount;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SocialOrgPmbrCountMapper extends MySqlMapper<SocialOrgPmbrCount>, Mapper<SocialOrgPmbrCount> {
	
	List<Map> selectChangeOrgList();
	
	SocialOrgPmbrCount getPmbrCount(@Param("orgIdArray") String[] orgIdArray);
}