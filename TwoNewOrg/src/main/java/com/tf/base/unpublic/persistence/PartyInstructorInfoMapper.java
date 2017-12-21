package com.tf.base.unpublic.persistence;

import org.apache.ibatis.annotations.Param;

import com.tf.base.unpublic.domain.PartyInstructorInfo;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface PartyInstructorInfoMapper extends MySqlMapper<PartyInstructorInfo>, Mapper<PartyInstructorInfo> {
    int deleteByPrimaryKey(Integer partyInstructorId);

    int insert(PartyInstructorInfo record);

    int insertSelective(PartyInstructorInfo record);

    PartyInstructorInfo selectByPrimaryKey(Integer partyInstructorId);

    int updateByPrimaryKeySelective(PartyInstructorInfo record);

    int updateByPrimaryKey(PartyInstructorInfo record);
    
    int deleteInstructInfo(@Param("partyOrgId") String partyOrgId);
}