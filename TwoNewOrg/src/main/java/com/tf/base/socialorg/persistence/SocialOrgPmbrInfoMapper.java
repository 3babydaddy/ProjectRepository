package com.tf.base.socialorg.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tf.base.socialorg.domain.QueryPmbrParams;
import com.tf.base.socialorg.domain.SocialOrgPmbrInfo;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SocialOrgPmbrInfoMapper extends MySqlMapper<SocialOrgPmbrInfo>, Mapper<SocialOrgPmbrInfo> {

	List<SocialOrgPmbrInfo> queryList(@Param("params")QueryPmbrParams params);
	
	List<SocialOrgPmbrInfo> queryListByOrgId(@Param("prmName") String prmName, @Param("orgIdsArray") String[] orgIdsArray);
}