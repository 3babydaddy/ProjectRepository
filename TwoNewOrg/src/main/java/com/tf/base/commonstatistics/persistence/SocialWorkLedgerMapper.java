package com.tf.base.commonstatistics.persistence;


import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.tf.base.commonstatistics.domain.QueryParam;
import com.tf.base.commonstatistics.domain.SocialWorkLedger;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SocialWorkLedgerMapper extends MySqlMapper<SocialWorkLedger>, Mapper<SocialWorkLedger> {

	List<SocialWorkLedger> queryList(@Param("params") QueryParam params);
	
}