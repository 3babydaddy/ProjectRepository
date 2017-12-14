package com.tf.base.socialorg.persistence;

import org.apache.ibatis.annotations.Param;

import com.tf.base.socialorg.domain.SocialPartyOrgChangeInfo;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SocialPartyOrgChangeInfoMapper extends MySqlMapper<SocialPartyOrgChangeInfo>, Mapper<SocialPartyOrgChangeInfo> {
    int deleteByPrimaryKey(Integer id);

    int insert(SocialPartyOrgChangeInfo record);

    int insertSelective(SocialPartyOrgChangeInfo record);

    SocialPartyOrgChangeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SocialPartyOrgChangeInfo record);

    int updateByPrimaryKey(SocialPartyOrgChangeInfo record);
    
    int deleteChangeInfo(@Param("partyOrgId") String partyOrgId);
}