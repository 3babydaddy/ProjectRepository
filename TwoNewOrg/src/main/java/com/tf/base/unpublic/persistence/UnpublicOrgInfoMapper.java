package com.tf.base.unpublic.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tf.base.unpublic.domain.QueryPmbrParams;
import com.tf.base.unpublic.domain.UnpublicOrgInfo;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface UnpublicOrgInfoMapper extends MySqlMapper<UnpublicOrgInfo>, Mapper<UnpublicOrgInfo> {

	List<UnpublicOrgInfo> queryList(@Param("params")UnpublicOrgInfo params, @Param("orderby")String orderby);
	
	int updateOrgPartyOrgId(@Param("partyOrgId") String partyOrgId);
	
	List<UnpublicOrgInfo> getList(@Param("params") QueryPmbrParams params);
}