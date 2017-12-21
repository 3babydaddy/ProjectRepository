package com.tf.base.unpublic.persistence;

import org.apache.ibatis.annotations.Param;

import com.tf.base.unpublic.domain.LowerPartyOrg;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface LowerPartyOrgMapper extends MySqlMapper<LowerPartyOrg>, Mapper<LowerPartyOrg> {
    int deleteByPrimaryKey(Integer lowerPartyOrgId);

    int insert(LowerPartyOrg record);

    int insertSelective(LowerPartyOrg record);

    LowerPartyOrg selectByPrimaryKey(Integer lowerPartyOrgId);

    int updateByPrimaryKeySelective(LowerPartyOrg record);

    int updateByPrimaryKey(LowerPartyOrg record);
    
    int deleteLowerInfo(@Param("partyOrgId") String partyOrgId);
}