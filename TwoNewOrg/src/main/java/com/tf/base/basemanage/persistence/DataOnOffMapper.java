package com.tf.base.basemanage.persistence;

import com.tf.base.basemanage.domain.DataOnOff;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface DataOnOffMapper extends MySqlMapper<DataOnOff>, Mapper<DataOnOff> {
}