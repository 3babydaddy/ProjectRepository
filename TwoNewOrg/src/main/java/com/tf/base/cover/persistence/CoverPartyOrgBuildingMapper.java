package com.tf.base.cover.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tf.base.cover.domain.CoverPartyOrgBuilding;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface CoverPartyOrgBuildingMapper extends MySqlMapper<CoverPartyOrgBuilding>, Mapper<CoverPartyOrgBuilding> {
    int deleteByPrimaryKey(Integer id);

    int insert(CoverPartyOrgBuilding record);

    int insertSelective(CoverPartyOrgBuilding record);

    CoverPartyOrgBuilding selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CoverPartyOrgBuilding record);

    int updateByPrimaryKey(CoverPartyOrgBuilding record);
    
    List<CoverPartyOrgBuilding> queryList(@Param("params") CoverPartyOrgBuilding params);
}