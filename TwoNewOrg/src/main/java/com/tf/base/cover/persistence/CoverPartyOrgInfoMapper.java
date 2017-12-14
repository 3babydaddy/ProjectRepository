package com.tf.base.cover.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tf.base.cover.domain.CoverOrgInfo;
import com.tf.base.cover.domain.CoverPartyOrgInfo;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface CoverPartyOrgInfoMapper extends MySqlMapper<CoverPartyOrgInfo>, Mapper<CoverPartyOrgInfo> {
    int deleteByPrimaryKey(Integer id);

    int insert(CoverPartyOrgInfo record);

    int insertSelective(CoverPartyOrgInfo record);

    CoverPartyOrgInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CoverPartyOrgInfo record);

    int updateByPrimaryKey(CoverPartyOrgInfo record);
    
    List<CoverPartyOrgInfo> queryList(@Param("params") CoverPartyOrgInfo params);
    
    List<CoverOrgInfo> queryCoverOrgInfoList(@Param("name") String name);
    
    List<CoverOrgInfo> queryCoverOrgInfoByExample(@Param("name") String name, @Param("coverPartyOrgId") String coverPartyOrgId);
}