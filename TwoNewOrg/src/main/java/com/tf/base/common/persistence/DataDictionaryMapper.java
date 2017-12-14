package com.tf.base.common.persistence;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tf.base.basemanage.domain.DictionaryForm;
import com.tf.base.common.domain.DataDictionary;


public interface DataDictionaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataDictionary record);

    int insertSelective(DataDictionary record);

    DataDictionary selectByPrimaryKey(Integer id);
    
    List<DataDictionary> selectAll();

    int updateByPrimaryKeySelective(DataDictionary record);

    int updateByPrimaryKey(DataDictionary record);
    
    List<DataDictionary> dynamicQuery(@Param("dmm")String dmm,@Param("inputTxt")String inputTxt);
    
    List<DataDictionary> dynamicQueryList(@Param("dmm")String dmm);
	
	int getIsExistsSame(DataDictionary record);

	int getMaxOrders(@Param("dmm")String dmm,@Param("pdmm")String pdmm);
	
	List<DataDictionary> selectByCondition(DictionaryForm form);
}