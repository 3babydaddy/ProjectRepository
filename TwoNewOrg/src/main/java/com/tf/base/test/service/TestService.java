package com.tf.base.test.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tf.base.common.constants.CommonConstants;
import com.tf.base.common.domain.DictionaryRepository;
import com.tf.base.common.utils.BeanUtils;
import com.tf.base.common.utils.PageUtil;
import com.tf.base.test.domain.Users;
import com.tf.base.test.domain.UsersParams;
import com.tf.base.test.persistence.UsersMapper;

@Service
public class TestService {

	@Autowired
	private DictionaryRepository dictionaryRepository;
	
	@Autowired
	private UsersMapper usersMapper;
	
	/**
	 * 单表分页
	 * @param params
	 * @return
	 */
	public Map<String,Object> queryList(UsersParams params){
		Map<String,Object> result = new HashMap<String,Object>();
		int start = (params.getPage()-1)*params.getRows();
		Users users = new Users(); 
		BeanUtils.copyPropertiesIgnoreNull(params, users);
		int total = usersMapper.selectCount(users);
		List<Users> rows = new ArrayList<>();
		if (total == 0) {
			result.put("rows", rows);
			result.put("total", total);
			return result;
		}
		rows= usersMapper.selectByRowBounds(users, new RowBounds(start, params.getRows()));
		
		this.convertRows(rows);
		result.put("total", total);
		result.put("rows", rows);
		
		return result;
	}
	

	/**
	 * 自定义sql 多表分页
	 * @param params
	 * @param response
	 */
	public void queryListWithSingle(UsersParams params, HttpServletResponse response) {

		PageHelper.startPage(params.getPage(), params.getRows(), true);
		List<Users> list = usersMapper.queryList(params);
		this.convertRows(list);
		PageUtil.returnPage(response, new PageInfo<Users>(list));
	}

	/**
	 * 查询导出Excel数据
	 * @param params
	 * @return
	 */
	public List<Users> queryListWithExport(UsersParams params){
		List<Users> rows = new ArrayList<>();
		Users users = new Users(); 
		BeanUtils.copyPropertiesIgnoreNull(params, users);
		rows = usersMapper.select(users);
		this.convertRows(rows);
		return rows;
	}
	

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public Users queryById(String id) {
		
		Users users = usersMapper.selectByPrimaryKey(id);
		this.convertRow(users);
		return users;
	}
	

	/**
	 * 保存
	 * @param users
	 * @return
	 */
	public Map<String, Object> save(Users users) {
		int i = usersMapper.updateByPrimaryKey(users);
		if(i > 0){
			return returnMsg(1,"修改成功!");
		}else{
			return returnMsg(0,"修改失败!");
		}
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public Map<String, Object> delete(String id) {
		int i = usersMapper.deleteByPrimaryKey(id);
		if(i > 0)
			return returnMsg(1,"删除成功!");
		else
			return returnMsg(0,"删除失败!");
	}

	/**
	 * 转换数据集合
	 * @param rows
	 */
	private void convertRows(List<Users> rows) {
		for (Users users : rows) {
			this.convertRow(users);
		}
	}


	/**
	 * 转换单条数据
	 * @param users
	 */
	private void convertRow(Users users) {

		users.setGenderStr(dictionaryRepository.getValueByCode(CommonConstants.GENDER, users.getGender().toString()));
	}

	/**
	 * 返回信息及标示
	 * @param status
	 * @param msg
	 * @return
	 */
	private Map<String,Object> returnMsg(int status,String msg){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", status);
		result.put("msg", msg);
		return result;
	}

}
