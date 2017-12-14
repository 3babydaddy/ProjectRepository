package com.tf.base.test.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tf.base.test.domain.Users;
import com.tf.base.test.domain.UsersParams;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface UsersMapper extends MySqlMapper<Users>, Mapper<Users> {

	List<Users> queryList(@Param(value = "params")UsersParams params);
}