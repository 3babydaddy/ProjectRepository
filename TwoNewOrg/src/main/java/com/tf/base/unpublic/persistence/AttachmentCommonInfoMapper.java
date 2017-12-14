package com.tf.base.unpublic.persistence;

import com.tf.base.unpublic.domain.AttachmentCommonInfo;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface AttachmentCommonInfoMapper extends MySqlMapper<AttachmentCommonInfo>, Mapper<AttachmentCommonInfo> {
    int deleteByPrimaryKey(Integer id);

    int insert(AttachmentCommonInfo record);

    int insertSelective(AttachmentCommonInfo record);

    AttachmentCommonInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AttachmentCommonInfo record);

    int updateByPrimaryKey(AttachmentCommonInfo record);
}