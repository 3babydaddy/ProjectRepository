package com.tf.base.unpublic.persistence;

import com.tf.base.unpublic.domain.CancelReasonInfo;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface CancelReasonInfoMapper extends MySqlMapper<CancelReasonInfo>, Mapper<CancelReasonInfo> {
    int deleteByPrimaryKey(Integer id);

    int insert(CancelReasonInfo record);

    int insertSelective(CancelReasonInfo record);

    CancelReasonInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CancelReasonInfo record);

    int updateByPrimaryKey(CancelReasonInfo record);
}