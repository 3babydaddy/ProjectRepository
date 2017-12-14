package com.tf.base.unpublic.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.tf.base.unpublic.domain.UnpublicPartyOrgInfo;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface UnpublicPartyOrgInfoMapper extends MySqlMapper<UnpublicPartyOrgInfo>, Mapper<UnpublicPartyOrgInfo> {
    int deleteByPrimaryKey(Integer id);

    int insert(UnpublicPartyOrgInfo record);

    int insertSelective(UnpublicPartyOrgInfo record);

    UnpublicPartyOrgInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UnpublicPartyOrgInfo record);

    int updateByPrimaryKey(UnpublicPartyOrgInfo record);
    
    List<UnpublicPartyOrgInfo> queryList(@Param("params")UnpublicPartyOrgInfo params);
}