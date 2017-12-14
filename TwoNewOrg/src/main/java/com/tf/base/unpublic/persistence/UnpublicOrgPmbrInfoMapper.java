package com.tf.base.unpublic.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tf.base.unpublic.domain.QueryPmbrParams;
import com.tf.base.unpublic.domain.UnpublicOrgPmbrInfo;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface UnpublicOrgPmbrInfoMapper extends MySqlMapper<UnpublicOrgPmbrInfo>, Mapper<UnpublicOrgPmbrInfo> {

	List<UnpublicOrgPmbrInfo> queryList(@Param("params")QueryPmbrParams params);
	
	List<UnpublicOrgPmbrInfo> queryListByOrgId(@Param("name") String name, @Param("orgIdsArray") String[] orgIdsArray);
}