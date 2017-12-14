package com.tf.base.cover.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tf.base.cover.domain.CoverOrgPmbrInfo;
import com.tf.base.unpublic.domain.QueryPmbrParams;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface CoverOrgPmbrInfoMapper extends MySqlMapper<CoverOrgPmbrInfo>, Mapper<CoverOrgPmbrInfo> {

	List<CoverOrgPmbrInfo> queryList(@Param("params") QueryPmbrParams params);
	
	List<CoverOrgPmbrInfo> queryListByOrgId(@Param("prmName") String prmName, @Param("coverPartyOrgId") String coverPartyOrgId);
}