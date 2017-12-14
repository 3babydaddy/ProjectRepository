package com.tf.base.socialorg.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tf.base.socialorg.domain.SocialPartyOrgBuilding;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SocialPartyOrgBuildingMapper extends MySqlMapper<SocialPartyOrgBuilding>, Mapper<SocialPartyOrgBuilding> {
    int deleteByPrimaryKey(Integer id);

    int insert(SocialPartyOrgBuilding record);

    int insertSelective(SocialPartyOrgBuilding record);

    SocialPartyOrgBuilding selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SocialPartyOrgBuilding record);

    int updateByPrimaryKey(SocialPartyOrgBuilding record);
    
    List<SocialPartyOrgBuilding> queryList(@Param("params") SocialPartyOrgBuilding params);
}