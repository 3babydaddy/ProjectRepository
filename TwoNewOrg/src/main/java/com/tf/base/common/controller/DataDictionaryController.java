package com.tf.base.common.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tf.base.common.constants.CommonConstants;
import com.tf.base.common.domain.DataDictionary;
import com.tf.base.common.domain.DictionaryRepository;

@Controller
public class DataDictionaryController {
	
	@Autowired
	private DictionaryRepository dictionaryRepository;

	@RequestMapping("/data/getNationalityDatas")
	@ResponseBody
	public List<DataDictionary> getNationalityDatas(String industryType) {
		if (StringUtils.isEmpty(industryType)) {
			return dictionaryRepository
					.findByDmm(CommonConstants.ENTERPRISE_TYPE_NATIONALITY);
		}
		
		if(industryType.startsWith("02")){
			return dictionaryRepository.findByPdmm("02");
		}else
			return dictionaryRepository.findByDmm(CommonConstants.NATIONALITY);
		 
	}
}
