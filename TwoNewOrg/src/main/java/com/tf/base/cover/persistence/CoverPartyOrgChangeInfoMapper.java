package com.tf.base.cover.persistence;

import org.apache.ibatis.annotations.Param;

import com.tf.base.cover.domain.CoverPartyOrgChangeInfo;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface CoverPartyOrgChangeInfoMapper extends MySqlMapper<CoverPartyOrgChangeInfo>, Mapper<CoverPartyOrgChangeInfo> {
    int deleteByPrimaryKey(Integer id);

    int insert(CoverPartyOrgChangeInfo record);

    int insertSelective(CoverPartyOrgChangeInfo record);

    CoverPartyOrgChangeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CoverPartyOrgChangeInfo record);

    int updateByPrimaryKey(CoverPartyOrgChangeInfo record);
    
    int deleteChangeInfo(@Param("partyOrgId") String partyOrgId);
}