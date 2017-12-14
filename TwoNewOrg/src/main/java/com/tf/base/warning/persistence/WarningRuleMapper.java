package com.tf.base.warning.persistence;

import com.tf.base.warning.domain.WarningRuleInfo;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface WarningRuleMapper extends MySqlMapper<WarningRuleInfo>, Mapper<WarningRuleInfo> {

}
