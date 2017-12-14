package com.tf.base.unpublic.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tf.base.unpublic.domain.UnpulicPartyOrgBuilding;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface UnpulicPartyOrgBuildingMapper extends MySqlMapper<UnpulicPartyOrgBuilding>, Mapper<UnpulicPartyOrgBuilding> {
    int deleteByPrimaryKey(Integer id);

    int insert(UnpulicPartyOrgBuilding record);

    int insertSelective(UnpulicPartyOrgBuilding record);

    UnpulicPartyOrgBuilding selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UnpulicPartyOrgBuilding record);

    int updateByPrimaryKey(UnpulicPartyOrgBuilding record);
    
    List<UnpulicPartyOrgBuilding> queryList(@Param("params") UnpulicPartyOrgBuilding params);
}