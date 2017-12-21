package com.tf.base.socialorg.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tf.base.common.constants.CommonConstants;
import com.tf.base.common.constants.CommonConstants.LOG_OPERATION_TYPE;
import com.tf.base.common.domain.DataDictionary;
import com.tf.base.common.domain.DictionaryRepository;
import com.tf.base.common.service.BaseService;
import com.tf.base.common.service.LogService;
import com.tf.base.common.utils.DateUtil;
import com.tf.base.common.utils.GeneralCalcUtils;
import com.tf.base.common.utils.LogInfoExtUtil;
import com.tf.base.socialorg.domain.AddPmbrParams;
import com.tf.base.socialorg.domain.AllParams;
import com.tf.base.socialorg.domain.RemovePmbrParams;
import com.tf.base.socialorg.domain.SocialOrgCancelRecord;
import com.tf.base.socialorg.domain.SocialOrgChargeDeputyType;
import com.tf.base.socialorg.domain.SocialOrgChargeInfo;
import com.tf.base.socialorg.domain.SocialOrgInfo;
import com.tf.base.socialorg.domain.SocialOrgInfoOtherCount;
import com.tf.base.socialorg.domain.SocialOrgJobinCount;
import com.tf.base.socialorg.domain.SocialOrgPmbrChangeInfo;
import com.tf.base.socialorg.domain.SocialOrgPmbrCount;
import com.tf.base.socialorg.domain.SocialOrgPmbrInfo;
import com.tf.base.socialorg.domain.SocialOrgPmbrOtherCount;
import com.tf.base.socialorg.persistence.SocialOrgCancelRecordMapper;
import com.tf.base.socialorg.persistence.SocialOrgChargeDeputyTypeMapper;
import com.tf.base.socialorg.persistence.SocialOrgChargeInfoMapper;
import com.tf.base.socialorg.persistence.SocialOrgInfoMapper;
import com.tf.base.socialorg.persistence.SocialOrgInfoOtherCountMapper;
import com.tf.base.socialorg.persistence.SocialOrgJobinCountMapper;
import com.tf.base.socialorg.persistence.SocialOrgPmbrChangeInfoMapper;
import com.tf.base.socialorg.persistence.SocialOrgPmbrCountMapper;
import com.tf.base.socialorg.persistence.SocialOrgPmbrInfoMapper;
import com.tf.base.socialorg.persistence.SocialOrgPmbrOtherCountMapper;

import tk.mybatis.mapper.entity.Example;

@Service
public class OrgService {
	
	@Autowired
	private BaseService baseService;
	@Autowired
	private LogService logService;
	@Autowired
	private DictionaryRepository dictionaryRepository;
	
	@Autowired
	private SocialOrgChargeDeputyTypeMapper socialOrgChargeDeputyTypeMapper;
	@Autowired
	private SocialOrgInfoMapper socialOrgInfoMapper;
	@Autowired
	private SocialOrgJobinCountMapper socialOrgJobinCountMapper;
	@Autowired
	private SocialOrgCancelRecordMapper socialOrgCancelRecordMapper;
	@Autowired
	private SocialOrgPmbrChangeInfoMapper socialOrgPmbrChangeInfoMapper;
	@Autowired
	private SocialOrgPmbrCountMapper socialOrgPmbrCountMapper;
	@Autowired
	private SocialOrgChargeInfoMapper socialOrgChargeInfoMapper;
	@Autowired
	private SocialOrgPmbrInfoMapper socialOrgPmbrInfoMapper;
	@Autowired
	private SocialOrgPmbrOtherCountMapper socialOrgPmbrOtherCountMapper;
	@Autowired
	private SocialOrgInfoOtherCountMapper socialOrgInfoOtherCountMapper;

	public void addDeputyTypes(Integer mainId, SocialOrgChargeInfo charge) {

		String types = charge.getChargeTwodeputyAcommitteeType();
		String typeArr [] = types.split(",");
		
		List<SocialOrgChargeDeputyType> deputyTypes = new ArrayList<>();
		SocialOrgChargeDeputyType add = null;
		for (String t : typeArr) {
			add = new SocialOrgChargeDeputyType();
			add.setSocialOrgChargeInfoId(charge.getId());
			add.setSocialOrgInfoId(mainId);
			add.setDeputyType(t);
			if(t.equals("10")){
				add.setDeputyTypeOther(charge.getChargeTwodeputyAcommitteeTypeOther());
			}
			deputyTypes.add(add);
		}
		
		socialOrgChargeDeputyTypeMapper.insertList(deputyTypes);
	}

	public void delDeputyTypes(Integer mainId, Integer chargeId) {

		SocialOrgChargeDeputyType del = new SocialOrgChargeDeputyType();
		del.setSocialOrgChargeInfoId(chargeId);
		del.setSocialOrgInfoId(mainId);
		socialOrgChargeDeputyTypeMapper.delete(del);
	}
	@Transactional
	public void addPartymbr(AddPmbrParams params) {

		Date birthday = DateUtil.getDateFromString(params.getBirthday());
				
		SocialOrgPmbrInfo pmbrInfo = new SocialOrgPmbrInfo();
		BeanUtils.copyProperties(params, pmbrInfo);
		pmbrInfo.setCreateOrg(baseService.getDepartment());//填报单位
		pmbrInfo.setCreateTime(new Date());
		pmbrInfo.setCreator(baseService.getUserName());
		pmbrInfo.setStatus("1");
		pmbrInfo.setBirthday(birthday);
		socialOrgPmbrInfoMapper.insertSelective(pmbrInfo);
		
		SocialOrgPmbrChangeInfo pmbrChangeInfo = new SocialOrgPmbrChangeInfo();
		pmbrChangeInfo.setSocialOrgInfoId(params.getSocialOrgInfoId());
		pmbrChangeInfo.setSocialOrgPartymbrInfoId(pmbrInfo.getId());
		pmbrChangeInfo.setType(params.getType());
		pmbrChangeInfo.setCreateOrg(baseService.getDepartment());//填报单位
		pmbrChangeInfo.setCreateTime(new Date());
		pmbrChangeInfo.setCreator(baseService.getUserName());
		pmbrChangeInfo.setStatus("1");//增加
		socialOrgPmbrChangeInfoMapper.insertSelective(pmbrChangeInfo);
		
		//计算党员数量
		SocialOrgPmbrCount oldCount = new SocialOrgPmbrCount();
		oldCount.setSocialOrgInfoId(params.getSocialOrgInfoId());
		oldCount = socialOrgPmbrCountMapper.selectOne(oldCount);
		
		//学历
		List<String> edus_max = Arrays.asList("4","5","6","7");//大学及以上学历
		List<String> edus_min = Arrays.asList("1","2","3");//高中及以下学历
		
		if(edus_max.contains(params.getEducation())){
			oldCount.setPartymbrOnCollegeNum(oldCount.getPartymbrOnCollegeNum()+1);
		}
		
		if(edus_min.contains(params.getEducation())){
			oldCount.setPartymbrUnderHighschoolNum(oldCount.getPartymbrUnderHighschoolNum()+1);
		}
		//年龄
		int age = 0;
		try {
			age = GeneralCalcUtils.getAge(birthday);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(age < 35){
			oldCount.setPartymbrUnderThirtyfiveNum(oldCount.getPartymbrUnderThirtyfiveNum()+1);
		}
		List<String> others = Arrays.asList("1");
		//纳入社会组织党组织管理
		if(others.contains(params.getPartymbrInSocialorgIs())){
			oldCount.setPartymbrInSocielorgNum(oldCount.getPartymbrInSocielorgNum()+1);
		}
		//组织关系在社会组织党组织
		if(others.contains(params.getPartymbrGroupInSocialorgIs())){
			oldCount.setPartymbrGroupInSocielorgNum(oldCount.getPartymbrGroupInSocielorgNum()+1);
		}
		socialOrgPmbrCountMapper.updateByPrimaryKeySelective(oldCount);
		
		//从业党员数
		Example example = new Example(SocialOrgJobinCount.class);
		example.createCriteria().andEqualTo("socialOrgInfoId", oldCount.getSocialOrgInfoId());
		
		SocialOrgJobinCount count = new SocialOrgJobinCount();
		count.setSocialOrgInfoId(oldCount.getSocialOrgInfoId());
		count = socialOrgJobinCountMapper.selectOne(count);
		
		count.setJobinPartymemberNum(count.getJobinPartymemberNum()+1);
		socialOrgJobinCountMapper.updateByExampleSelective(count, example);
		
		//其他特殊条件统计项
		String otherCondition = params.getOtherCondition();
		if(StringUtils.isNotEmpty(otherCondition)){
			List<DataDictionary> otherConditionList = dictionaryRepository.findByDmm(CommonConstants.SOCIAL_PM_OTHER_CONDITION);
			List<String> otherList = Arrays.asList(otherCondition.split(","));
			
			List<SocialOrgPmbrOtherCount> otherCountList = new ArrayList<>();
			SocialOrgPmbrOtherCount otherCount = null;
			for (DataDictionary dataDictionary : otherConditionList) {
				otherCount = new SocialOrgPmbrOtherCount();
				if(otherList.contains(dataDictionary.getCode())){
					otherCount.setFieldName(dataDictionary.getCode());
					otherCount.setFieldValue(1);
				}else{
					otherCount.setFieldName(dataDictionary.getCode());
					otherCount.setFieldValue(0);
				}
				
				otherCount.setSocialOrgInfoId(params.getSocialOrgInfoId());
				otherCount.setSocialOrgPartymbrInfoId(pmbrInfo.getId());
				
				otherCountList.add(otherCount);
			}
			socialOrgPmbrOtherCountMapper.insertList(otherCountList);
		}
		
		
		try {
			//新增党员日志
			StringBuffer sb = new StringBuffer();
			sb.append(LogInfoExtUtil.getAddLog(dictionaryRepository, pmbrInfo));
			sb.append(LogInfoExtUtil.getAddLog(dictionaryRepository, pmbrChangeInfo));
			logService.saveLog(LOG_OPERATION_TYPE.CREATE.toString(), 
					logService.getDetailInfo("log.socialorg.partymbr.create",
							baseService.getUserName(),pmbrInfo.getName(),
							sb));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Transactional
	public void delPartymbr(RemovePmbrParams params) {
		SocialOrgPmbrInfo oldPmbrInfo = socialOrgPmbrInfoMapper.selectByPrimaryKey(params.getPmbrInfoId());
		
		SocialOrgPmbrInfo pmbrInfo = new SocialOrgPmbrInfo();
		pmbrInfo.setStatus("0");
		pmbrInfo.setId(Integer.parseInt(params.getPmbrInfoId()));
		socialOrgPmbrInfoMapper.updateByPrimaryKeySelective(pmbrInfo);
		
		SocialOrgPmbrChangeInfo pmbrChangeInfo = new SocialOrgPmbrChangeInfo();
		pmbrChangeInfo.setSocialOrgInfoId(oldPmbrInfo.getSocialOrgInfoId());
		pmbrChangeInfo.setSocialOrgPartymbrInfoId(pmbrInfo.getId());
		pmbrChangeInfo.setGowhere(params.getGowhere());
		pmbrChangeInfo.setContact(params.getContact());
		pmbrChangeInfo.setCreateOrg(baseService.getDepartment());//填报单位
		pmbrChangeInfo.setCreateTime(new Date());
		pmbrChangeInfo.setCreator(baseService.getUserName());
		pmbrChangeInfo.setStatus("2");//减少
		socialOrgPmbrChangeInfoMapper.insertSelective(pmbrChangeInfo);
		
		//计算党员数量
		SocialOrgPmbrCount oldCount = new SocialOrgPmbrCount();
		oldCount.setSocialOrgInfoId(oldPmbrInfo.getSocialOrgInfoId());
		oldCount = socialOrgPmbrCountMapper.selectOne(oldCount);
		
		if(oldCount != null){
			//学历
			List<String> edus_max = Arrays.asList("4","5","6","7");//大学及以上学历
			List<String> edus_min = Arrays.asList("1","2","3");//高中及以下学历
			
			if(edus_max.contains(oldPmbrInfo.getEducation())){
				oldCount.setPartymbrOnCollegeNum(oldCount.getPartymbrOnCollegeNum()-1);
			}
			
			if(edus_min.contains(oldPmbrInfo.getEducation())){
				oldCount.setPartymbrUnderHighschoolNum(oldCount.getPartymbrUnderHighschoolNum()-1);
			}
			//年龄
			int age = 0;
			try {
				age = GeneralCalcUtils.getAge(oldPmbrInfo.getBirthday());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(age < 35){
				oldCount.setPartymbrUnderThirtyfiveNum(oldCount.getPartymbrUnderThirtyfiveNum()-1);
			}
			
			//其他条件信息
				
			List<String> others = Arrays.asList("1");
			//纳入社会组织党组织管理
			if(others.contains(oldPmbrInfo.getPartymbrInSocialorgIs())){
				oldCount.setPartymbrInSocielorgNum(oldCount.getPartymbrInSocielorgNum()-1);
			}
			//组织关系在社会组织党组织
			if(others.contains(oldPmbrInfo.getPartymbrGroupInSocialorgIs())){
				oldCount.setPartymbrGroupInSocielorgNum(oldCount.getPartymbrGroupInSocielorgNum()-1);
			}
			socialOrgPmbrCountMapper.updateByPrimaryKeySelective(oldCount);
			
			//从业党员数
			Example example = new Example(SocialOrgJobinCount.class);
			example.createCriteria().andEqualTo("socialOrgInfoId", oldCount.getSocialOrgInfoId());
			
			SocialOrgJobinCount count = new SocialOrgJobinCount();
			count.setSocialOrgInfoId(oldCount.getSocialOrgInfoId());
			count = socialOrgJobinCountMapper.selectOne(count);
			
			count.setJobinPartymemberNum(count.getJobinPartymemberNum()-1);
			socialOrgJobinCountMapper.updateByExampleSelective(count, example);
		}
		
		
		
		//减少党员日志
		logService.saveLog(LOG_OPERATION_TYPE.DELETE.toString(), 
				logService.getDetailInfo("log.socialorg.partymbr.delete",
						baseService.getUserName(),oldPmbrInfo.getName()));
	}
	@Transactional
	public void addOrg(SocialOrgInfo main, SocialOrgJobinCount count, SocialOrgChargeInfo charge,
			SocialOrgPmbrCount pmbrCount, AllParams params) {

		main.setInitIs(1);
		main.setCreateTime(new Date());
		main.setCreator(baseService.getUserName());
		main.setCreateOrg(baseService.getDepartment());
		socialOrgInfoMapper.insertSelective(main);
		count.setSocialOrgInfoId(main.getId());
		socialOrgJobinCountMapper.insertSelective(count);
		charge.setSocialOrgInfoId(main.getId());
		socialOrgChargeInfoMapper.insertSelective(charge);
		pmbrCount.setSocialOrgInfoId(main.getId());
		socialOrgPmbrCountMapper.insertSelective(pmbrCount);
		
		this.addDeputyTypes(main.getId(),charge);
		
		this.addOtherCondition(params,main.getId());
		try {
			//新增日志
			StringBuffer sb = new StringBuffer();
			sb.append(LogInfoExtUtil.getAddLog(dictionaryRepository, main));
			sb.append(LogInfoExtUtil.getAddLog(dictionaryRepository, count));
			sb.append(LogInfoExtUtil.getAddLog(dictionaryRepository, charge));
			sb.append(LogInfoExtUtil.getAddLog(dictionaryRepository, pmbrCount));
			if(params.getReportHigher().intValue() == 1){
				logService.saveLog(LOG_OPERATION_TYPE.CREATE.toString(), 
						logService.getDetailInfo("log.socialorg.create.reporthigher",
								baseService.getUserName(),main.getName(),
								sb));
			}else{
				logService.saveLog(LOG_OPERATION_TYPE.CREATE.toString(), 
						logService.getDetailInfo("log.socialorg.create",
								baseService.getUserName(),main.getName(),
								sb));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

	@Transactional
	public void updateOrg(SocialOrgInfo main, SocialOrgJobinCount count, SocialOrgChargeInfo charge,
			SocialOrgPmbrCount pmbrCount, AllParams params) {
		Integer mainId = main.getId();
		
		//基础信息
		SocialOrgInfo oldMain = socialOrgInfoMapper.selectByPrimaryKey(mainId);
		
		//从业人员
		SocialOrgJobinCount oldCount = new SocialOrgJobinCount();
		oldCount.setSocialOrgInfoId(mainId);
		oldCount = socialOrgJobinCountMapper.selectOne(oldCount);
		//负责人信息
		SocialOrgChargeInfo oldCharge = new SocialOrgChargeInfo();
		oldCharge.setSocialOrgInfoId(mainId);
		oldCharge = socialOrgChargeInfoMapper.selectOne(oldCharge);
		//党员统计信息
		SocialOrgPmbrCount oldPmbrCount = new SocialOrgPmbrCount();
		oldPmbrCount.setSocialOrgInfoId(mainId);
		oldPmbrCount = socialOrgPmbrCountMapper.selectOne(oldPmbrCount);
		
		//修改信息
		main.setUpdateTime(new Date());
		main.setUpdator(baseService.getUserName());
		socialOrgInfoMapper.updateByPrimaryKeySelective(main);
		count.setId(Integer.parseInt(params.getJobinId()));
		socialOrgJobinCountMapper.updateByPrimaryKeySelective(count);
		charge.setId(Integer.parseInt(params.getChargeId()));
		socialOrgChargeInfoMapper.updateByPrimaryKeySelective(charge);
		
		this.delDeputyTypes(main.getId(),charge.getId());
		
		this.addDeputyTypes(main.getId(),charge);
		
		this.delOtherCondition(main.getId());
		this.addOtherCondition(params,main.getId());
		try {
			
			
			//修改日志
			StringBuffer sb = new StringBuffer();
			sb.append(LogInfoExtUtil.getModifyLog(dictionaryRepository, oldMain,main));
			sb.append(LogInfoExtUtil.getModifyLog(dictionaryRepository, oldCount,count));
			sb.append(LogInfoExtUtil.getModifyLog(dictionaryRepository, oldCharge,charge));
			sb.append(LogInfoExtUtil.getModifyLog(dictionaryRepository, oldPmbrCount,pmbrCount));
			if(params.getReportHigher().intValue() == 1){
				logService.saveLog(LOG_OPERATION_TYPE.MODIFY.toString(), 
						logService.getDetailInfo("log.socialorg.modify.reporthigher",
								baseService.getUserName(),oldMain.getName(),
								sb));
			}else{
				logService.saveLog(LOG_OPERATION_TYPE.MODIFY.toString(), 
						logService.getDetailInfo("log.socialorg.modify",
								baseService.getUserName(),oldMain.getName(),
								sb));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Transactional
	public void delOrg(String[] partyOrgIdArray) {
		for(int i = 0; i < partyOrgIdArray.length; i++){
			SocialOrgInfo info = socialOrgInfoMapper.selectByPrimaryKey(Integer.parseInt(partyOrgIdArray[i]));
			info.setStatus("0");
			socialOrgInfoMapper.updateByPrimaryKeySelective(info);
		}
	}

	@Transactional
	public void cancelOrg(String id, String remarks) {

		SocialOrgCancelRecord reason = new SocialOrgCancelRecord();
		reason.setSocialOrgInfoId(Integer.parseInt(id));
		reason.setReason(remarks);
		reason.setCreateTime(new Date());
		reason.setCreator(baseService.getUserName());
		reason.setStatus(CommonConstants.STATUS_FLAG_VALID);
		socialOrgCancelRecordMapper.insertSelective(reason);
		
		SocialOrgInfo main = socialOrgInfoMapper.selectByPrimaryKey(Integer.parseInt(id));
		main.setStatus("3");
		socialOrgInfoMapper.updateByPrimaryKeySelective(main);
	}

	private void addOtherCondition(AllParams params, Integer mainId) {

		//其他特殊条件统计项
		String otherCondition = params.getOtherCondition();
		if(StringUtils.isNotEmpty(otherCondition)){
			List<DataDictionary> otherConditionList = dictionaryRepository.findByDmm(CommonConstants.SOCIAL_ORG_OTHER_CONDITION);
			List<String> otherList = Arrays.asList(otherCondition.split(","));
			
			List<SocialOrgInfoOtherCount> otherCountList = new ArrayList<>();
			SocialOrgInfoOtherCount otherCount = null;
			for (DataDictionary dataDictionary : otherConditionList) {
				otherCount = new SocialOrgInfoOtherCount();
				if(otherList.contains(dataDictionary.getCode())){
					otherCount.setFieldName(dataDictionary.getCode());
					otherCount.setFieldValue(1);
				}else{
					otherCount.setFieldName(dataDictionary.getCode());
					otherCount.setFieldValue(0);
				}
				
				otherCount.setSocialOrgInfoId(mainId);
				
				otherCountList.add(otherCount);
			}
			socialOrgInfoOtherCountMapper.insertList(otherCountList);
		}
	}
	

	private void delOtherCondition(Integer id) {
		SocialOrgInfoOtherCount c = new SocialOrgInfoOtherCount();
		c.setSocialOrgInfoId(id);
		socialOrgInfoOtherCountMapper.delete(c);
	}

	
}
