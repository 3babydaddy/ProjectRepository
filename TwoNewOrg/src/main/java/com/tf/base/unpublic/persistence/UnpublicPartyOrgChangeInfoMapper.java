package com.tf.base.unpublic.persistence;

import org.apache.ibatis.annotations.Param;

import com.tf.base.unpublic.domain.UnpublicPartyOrgChangeInfo;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface UnpublicPartyOrgChangeInfoMapper extends MySqlMapper<UnpublicPartyOrgChangeInfo>, Mapper<UnpublicPartyOrgChangeInfo> {
    int deleteByPrimaryKey(Integer id);

    int insert(UnpublicPartyOrgChangeInfo record);

    int insertSelective(UnpublicPartyOrgChangeInfo record);

    UnpublicPartyOrgChangeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UnpublicPartyOrgChangeInfo record);

    int updateByPrimaryKey(UnpublicPartyOrgChangeInfo record);
    
    int deleteChangeInfo(@Param("partyOrgId") String partyOrgId);
}