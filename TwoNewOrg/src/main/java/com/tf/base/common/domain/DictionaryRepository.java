/**
 * 
 */
package com.tf.base.common.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tf.base.common.persistence.DataDictionaryMapper;


/**
 * @author yxd
 * 
 */
@Service
public class DictionaryRepository {

	private static DictionaryRepository instance = null;

	@Autowired
	private DataDictionaryMapper dataDictionaryMapper;

	/**
	 * 所有字典数据的集合
	 */
	private Map<String, List<DataDictionary>> allConllection = new HashMap<String, List<DataDictionary>>();

	/**
	 * 所有级联字典数据的集合
	 */
	private Map<String, List<DataDictionary>> subConllection = new HashMap<String, List<DataDictionary>>();

	@PostConstruct
	public void init() {
		instance = this;
		initData();
    }  
	/**
	 * 数据加载到静态map中
	 */
	public void initData(){
		
		List<DataDictionary> list = dataDictionaryMapper.selectAll();
		if (list == null || list.isEmpty()) {
			return;
		}
		for (DataDictionary dataDictionary : list) {
			List<DataDictionary> tempList = allConllection.get(dataDictionary.getDmm());
			if (tempList == null || tempList.isEmpty()) {
				tempList = new ArrayList<DataDictionary>();
				tempList.add(dataDictionary);
				allConllection.put(dataDictionary.getDmm(), tempList);
			} else {
				tempList.add(dataDictionary);
			}
			if (StringUtils.hasLength(dataDictionary.getPdmm())) {
				List<DataDictionary> tempList1 = subConllection.get(dataDictionary.getPdmm());
				if (tempList1 == null || tempList1.isEmpty()) {
					tempList1 = new ArrayList<DataDictionary>();
					tempList1.add(dataDictionary);
					subConllection.put(dataDictionary.getPdmm(), tempList1);
				} else {
					tempList1.add(dataDictionary);
				}
			}
		}
		
		
	}
	/**
	 * 手动重新加载数据字典数据
	 */
	public synchronized  void loadCache(){
		subConllection = new HashMap<String, List<DataDictionary>>();
		allConllection = new HashMap<String, List<DataDictionary>>();
		initData();
	}
	
	/**
	 * 查询指定主代码的所有枚举
	 * 
	 * @param dmm
	 * @return
	 */
	public List<DataDictionary> findByDmm(String dmm) {
		return allConllection.get(dmm);
	}
	
	/**
	 * 在指定DMM中获取指定 code的列表
	 * @param dmm
	 * @param codes
	 * @return
	 */
	public List<DataDictionary> findByDmmAndCode(String dmm,String[] codes) {
		
		List<DataDictionary> newList = new ArrayList<DataDictionary>();
		
		List<DataDictionary>  datalist =  allConllection.get(dmm);
		if(datalist !=null && !datalist.isEmpty()){
			
			for(String code : codes){
				
				for (DataDictionary data : datalist) {
					
					if(data.getCode().equals(code)){
						
						newList.add(data);
					}
				}
			}
		}
		return newList ;
		
	}
	
	

	/**
	 * 查询指定父主代码下的子枚举
	 * 
	 * @param pdmm
	 * @return
	 */
	public List<DataDictionary> findByPdmm(String pdmm) {
		return subConllection.get(pdmm);
	}

	/**
	 * 根据主代码和子项代码检索对应的value
	 * 
	 * @param dmm
	 * @param code
	 * @return
	 */
	public String getValueByCode(String dmm, String code) {

		List<DataDictionary> list = findByDmm(dmm);
		if (list != null && !list.isEmpty()) {
			for (DataDictionary dataDictionary : list) {
				if (dataDictionary.getCode().equals(code)) {
					return dataDictionary.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * 根据主代码和子项value检索对应的子项代码
	 * 
	 * @param dmm
	 * @param value
	 * @return
	 */
	public String getCodeByValue(String dmm, String value) {
		List<DataDictionary> list = findByDmm(dmm);
		if (list != null && !list.isEmpty()) {
			for (DataDictionary dataDictionary : list) {
				if (!StringUtils.isEmpty(value) && value.equals(dataDictionary.getValue())) {
					return dataDictionary.getCode();
				}
			}
		}
		return null;
	}


	@PreDestroy
	public void dostory() {
	}

	/**
	 * 获取实例
	 * 
	 * @return
	 */
	public static DictionaryRepository getInstance() {
		return instance;
	}

	/**
	 * 根据主代码和子项代码检索对应的Object
	 * 
	 * @param dmm
	 * @param code
	 * @return
	 */
	public DataDictionary getObjectByCode(String dmm, String code) {
		List<DataDictionary> list = findByDmm(dmm);
		if (list != null && !list.isEmpty()) {
			for (DataDictionary dataDictionary : list) {
				if (dataDictionary.getCode().equals(code)) {
					return dataDictionary;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * 根据name或者value匹配对应的对象
	 * 
	 * @param dmm
	 * @param nameOrValue
	 * @return
	 */
	public List<DataDictionary> findDataDictionarysLikeNameOrValue(String dmm, String nameOrValue) {
		List<DataDictionary> dataDictionarys = findByDmm(dmm);
		if (null == nameOrValue)
			return dataDictionarys;
		List<DataDictionary> list = new ArrayList<DataDictionary>();
		if (dataDictionarys != null && !dataDictionarys.isEmpty()) {
			for (DataDictionary dataDictionary : dataDictionarys) {
				if (dataDictionary.getCode().indexOf(nameOrValue) >= 0
						|| dataDictionary.getValue().indexOf(nameOrValue) >= 0) {
					list.add(dataDictionary);
				}
			}
		}
		return list;
	}
}
