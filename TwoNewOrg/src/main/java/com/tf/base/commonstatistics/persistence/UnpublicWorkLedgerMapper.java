package com.tf.base.commonstatistics.persistence;


import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.tf.base.commonstatistics.domain.QueryParam;
import com.tf.base.commonstatistics.domain.UnpublicWorkLedger;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface UnpublicWorkLedgerMapper extends MySqlMapper<UnpublicWorkLedger>, Mapper<UnpublicWorkLedger> {

	List<UnpublicWorkLedger> queryList(@Param("params") QueryParam params);
	
}