package com.tf.base.unpublic.persistence;

import org.apache.ibatis.annotations.Param;

import com.tf.base.unpublic.domain.DeputySecretaryInfo;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface DeputySecretaryInfoMapper extends MySqlMapper<DeputySecretaryInfo>, Mapper<DeputySecretaryInfo> {
    int deleteByPrimaryKey(Integer id);

    int insert(DeputySecretaryInfo record);

    int insertSelective(DeputySecretaryInfo record);

    DeputySecretaryInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeputySecretaryInfo record);

    int updateByPrimaryKey(DeputySecretaryInfo record);
    
    int deleteDeputysecretaryInfo(@Param("partyOrgId") String partyOrgId);
}