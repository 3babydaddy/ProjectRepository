package com.tf.base.socialorg.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tf.base.socialorg.domain.SocialPartyOrgInfo;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SocialPartyOrgInfoMapper extends MySqlMapper<SocialPartyOrgInfo>, Mapper<SocialPartyOrgInfo> {
    int deleteByPrimaryKey(Integer id);

    int insert(SocialPartyOrgInfo record);

    int insertSelective(SocialPartyOrgInfo record);

    SocialPartyOrgInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SocialPartyOrgInfo record);

    int updateByPrimaryKey(SocialPartyOrgInfo record);
    
    List<SocialPartyOrgInfo> queryList(@Param("params") SocialPartyOrgInfo params);
}