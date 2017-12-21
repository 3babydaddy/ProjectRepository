package com.tf.base.unpublic.service;

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
import com.tf.base.unpublic.domain.AddPmbrParams;
import com.tf.base.unpublic.domain.AllParams;
import com.tf.base.unpublic.domain.RemovePmbrParams;
import com.tf.base.unpublic.domain.UnpublicOrgAddressInfo;
import com.tf.base.unpublic.domain.UnpublicOrgCancelRecord;
import com.tf.base.unpublic.domain.UnpublicOrgInfo;
import com.tf.base.unpublic.domain.UnpublicOrgInfoOtherCount;
import com.tf.base.unpublic.domain.UnpublicOrgLeagueInfo;
import com.tf.base.unpublic.domain.UnpublicOrgPmbrChangeInfo;
import com.tf.base.unpublic.domain.UnpublicOrgPmbrCount;
import com.tf.base.unpublic.domain.UnpublicOrgPmbrInfo;
import com.tf.base.unpublic.domain.UnpublicOrgPmbrOtherCount;
import com.tf.base.unpublic.domain.UnpublicOrgSponsorDeputyType;
import com.tf.base.unpublic.domain.UnpublicOrgSponsorInfo;
import com.tf.base.unpublic.persistence.UnpublicOrgAddressInfoMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgCancelRecordMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgInfoMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgInfoOtherCountMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgLeagueInfoMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgPmbrChangeInfoMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgPmbrCountMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgPmbrInfoMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgPmbrOtherCountMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgSponsorDeputyTypeMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgSponsorInfoMapper;

@Service
public class UnPublicOrgService {
	
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private DictionaryRepository dictionaryRepository;

	@Autowired
	private UnpublicOrgSponsorDeputyTypeMapper unpublicOrgSponsorDeputyTypeMapper;
	
	@Autowired
	private UnpublicOrgInfoMapper unpublicOrgInfoMapper;
	@Autowired
	private UnpublicOrgSponsorInfoMapper unpublicOrgSponsorInfoMapper;
	@Autowired
	private UnpublicOrgCancelRecordMapper unpublicOrgCancelRecordMapper;
	@Autowired
	private UnpublicOrgPmbrChangeInfoMapper unpublicOrgPmbrChangeInfoMapper;
	@Autowired
	private UnpublicOrgPmbrCountMapper unpublicOrgPmbrCountMapper;
	@Autowired
	private UnpublicOrgPmbrInfoMapper unpublicOrgPmbrInfoMapper;
	@Autowired
	private UnpublicOrgLeagueInfoMapper unpublicOrgLeagueInfoMapper;
	@Autowired
	private UnpublicOrgAddressInfoMapper unpublicOrgAddressInfoMapper;
	@Autowired
	private UnpublicOrgPmbrOtherCountMapper unpublicOrgPmbrOtherCountMapper;
	@Autowired
	private UnpublicOrgInfoOtherCountMapper unpublicOrgInfoOtherCountMapper;

	private void addDeputyTypes(Integer mainId, UnpublicOrgSponsorInfo sponsor) {

		String types = sponsor.getSponsorTwodeputyAcommitteeType();
		String typeArr [] = types.split(",");
		
		List<UnpublicOrgSponsorDeputyType> deputyTypes = new ArrayList<>();
		UnpublicOrgSponsorDeputyType add = null;
		for (String t : typeArr) {
			add = new UnpublicOrgSponsorDeputyType();
			add.setUnpublicOrgSponsorInfoId(sponsor.getId());
			add.setUnpublicOrgInfoId(mainId);
			add.setDeputyType(t);
			if(t.equals("10")){
				add.setDeputyTypeOther(sponsor.getSponsorTwodeputyAcommitteeTypeOther());
			}
			deputyTypes.add(add);
		}
		
		unpublicOrgSponsorDeputyTypeMapper.insertList(deputyTypes);
	}

	private void delDeputyTypes(Integer mainId, Integer sponsorId) {

		UnpublicOrgSponsorDeputyType del = new UnpublicOrgSponsorDeputyType();
		del.setUnpublicOrgSponsorInfoId(sponsorId);
		del.setUnpublicOrgInfoId(mainId);
		unpublicOrgSponsorDeputyTypeMapper.delete(del);
	}
	 
    private void delOperateAddress(Integer mainId){
    	UnpublicOrgAddressInfo address = new UnpublicOrgAddressInfo();
    	address.setUnpublicOrgInfoId(mainId);
    	unpublicOrgAddressInfoMapper.delete(address);
    }

	private void addOperateAddress(Integer mainId,  String operateAddressLevel,String operateAddressBigTxt) {

		//企业经营地大字段
		if(StringUtils.isNotEmpty(operateAddressBigTxt)){
			String operateAddressTxtArrs [] = operateAddressBigTxt.split(";");
			List<UnpublicOrgAddressInfo> addressList = new ArrayList<>();
			for (String operateAddressTxt : operateAddressTxtArrs) {
				if(StringUtils.isNotEmpty(operateAddressTxt)){
					UnpublicOrgAddressInfo address = new UnpublicOrgAddressInfo();
					String operateAddressArrs [] = operateAddressTxt.split(",");
					if(operateAddressArrs.length > 0){
						
						String operateAddressLevelX = operateAddressArrs[0];
						address.setOperateAddressLevel(operateAddressLevelX);
					}
					if( operateAddressArrs[0].equals("1")){
						//省 天津特殊
						String operateAddressProvince = operateAddressArrs[1];
						address.setOperateAddressProvince(operateAddressProvince);
						//区
						String operateAddressDistrict = operateAddressArrs[2];
						address.setOperateAddressDistrict(operateAddressDistrict);
						//街道
						String operateAddressStreet = operateAddressArrs[3];
						address.setOperateAddressStreet(operateAddressStreet);
						if(operateAddressArrs.length > 4){
							//详细地址
							String operateAddressDetail = operateAddressArrs[4];
							address.setOperateAddress(operateAddressDetail.substring(1));
						}
						
					}else if( operateAddressArrs[0].equals("2")){
//						String operateAddressCity = operateAddressArrs[1];
//						address.setOperateAddressCity(operateAddressCity);
						//省 天津特殊
						String operateAddressProvince = operateAddressArrs[1];
						address.setOperateAddressProvince(operateAddressProvince);
						//区
						String operateAddressDistrict = operateAddressArrs[2];
						address.setOperateAddressDistrict(operateAddressDistrict);
						if(operateAddressArrs.length > 3){
							//详细地址
							String operateAddressDetail = operateAddressArrs[3];
							address.setOperateAddress(operateAddressDetail.substring(1));
						}
					}else if( operateAddressArrs[0].equals("3")){
						//省
						String operateAddressProvince = operateAddressArrs[1];
						address.setOperateAddressProvince(operateAddressProvince);
						//省市详细都有的情况
						if(operateAddressArrs.length > 3){
							//市
							String operateAddressCity = operateAddressArrs[2];
							address.setOperateAddressCity(operateAddressCity);
							
							//详细地址
							String operateAddressDetail = operateAddressArrs[3];
							address.setOperateAddress(operateAddressDetail.substring(1));
							
						//省市详细有其中两种的情况
						}else if(operateAddressArrs.length > 2){
							String unknow = operateAddressArrs[2];
							if(unknow.startsWith("^")){
								//详细地址时  有特殊符号^
								address.setOperateAddress(unknow);
							}else{
								//市
								address.setOperateAddressCity(unknow);
							}
							
						}
						
					}
					
					address.setUnpublicOrgInfoId(mainId);
					addressList.add(address);
					
				}
			}
			
			unpublicOrgAddressInfoMapper.insertList(addressList);
		}
	}

	/**
	 * 新增组织
	 * @param main
	 * @param sponsor
	 * @param league
	 * @param pmbrCount
	 * @param params
	 */
	@Transactional
	public void addOrg(UnpublicOrgInfo main, UnpublicOrgSponsorInfo sponsor, UnpublicOrgLeagueInfo league,
			UnpublicOrgPmbrCount pmbrCount, AllParams params) {

		main.setInitIs(1);
		main.setCreateTime(new Date());
		main.setCreator(baseService.getUserName());
		main.setCreateOrg(baseService.getDepartment());
		String txt = params.getOperateAddressBigTxt();
		main.setOperateAddress(txt);
		unpublicOrgInfoMapper.insertSelective(main);
		sponsor.setUnpublicOrgInfoId(main.getId());
		unpublicOrgSponsorInfoMapper.insertSelective(sponsor);
		league.setUnpublicOrgInfoId(main.getId());
		unpublicOrgLeagueInfoMapper.insertSelective(league);
		pmbrCount.setUnpublicOrgInfoId(main.getId());
		unpublicOrgPmbrCountMapper.insertSelective(pmbrCount);
		
		this.addDeputyTypes(main.getId(),sponsor);
		
		this.addOperateAddress(main.getId(),params.getOperateAddressLevel(),params.getOperateAddressBigTxt());
		
		this.addOtherCondition(params, main.getId());
		try {
			//新增日志
			StringBuffer sb = new StringBuffer();
			sb.append(LogInfoExtUtil.getAddLog(dictionaryRepository, main));
			sb.append(LogInfoExtUtil.getAddLog(dictionaryRepository, sponsor));
			sb.append(LogInfoExtUtil.getAddLog(dictionaryRepository, league));
			sb.append(LogInfoExtUtil.getAddLog(dictionaryRepository, pmbrCount));
			if(params.getReportHigher().intValue() == 1){
				logService.saveLog(LOG_OPERATION_TYPE.CREATE.toString(), 
						logService.getDetailInfo("log.unpublic.create.reporthigher",
								baseService.getUserName(),main.getName(),
								sb));
			}else{
				logService.saveLog(LOG_OPERATION_TYPE.CREATE.toString(), 
						logService.getDetailInfo("log.unpublic.create",
								baseService.getUserName(),main.getName(),
								sb));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	/**
	 * 修改组织
	 * @param main
	 * @param sponsor
	 * @param league
	 * @param pmbrCount
	 * @param params
	 */
	@Transactional
	public void updateOrg(UnpublicOrgInfo main, UnpublicOrgSponsorInfo sponsor, UnpublicOrgLeagueInfo league,
			UnpublicOrgPmbrCount pmbrCount, AllParams params) {

		//基础信息
		UnpublicOrgInfo oldMain = unpublicOrgInfoMapper.selectByPrimaryKey(main.getId());
		
		//从业人员
		UnpublicOrgSponsorInfo oldSponsor = new UnpublicOrgSponsorInfo();
		oldSponsor.setUnpublicOrgInfoId(main.getId());
		oldSponsor = unpublicOrgSponsorInfoMapper.selectOne(oldSponsor);
		//负责人信息
		UnpublicOrgLeagueInfo oldLeague = new UnpublicOrgLeagueInfo();
		oldLeague.setUnpublicOrgInfoId(main.getId());
		oldLeague = unpublicOrgLeagueInfoMapper.selectOne(oldLeague);
		//党员统计信息
		UnpublicOrgPmbrCount oldPmbrCount = new UnpublicOrgPmbrCount();
		oldPmbrCount.setUnpublicOrgInfoId(main.getId());
		oldPmbrCount = unpublicOrgPmbrCountMapper.selectOne(oldPmbrCount);
		
		//修改信息
		main.setOperateAddress(params.getOperateAddressBigTxt());
		main.setUpdateTime(new Date());
		main.setUpdator(baseService.getUserName());
		unpublicOrgInfoMapper.updateByPrimaryKeySelective(main);
		sponsor.setId(Integer.parseInt(params.getSponsorId()));
		unpublicOrgSponsorInfoMapper.updateByPrimaryKeySelective(sponsor);
		league.setId(Integer.parseInt(params.getLeagueId()));
		unpublicOrgLeagueInfoMapper.updateByPrimaryKeySelective(league);
		
		this.delDeputyTypes(main.getId(),sponsor.getId());
		
		this.addDeputyTypes(main.getId(),sponsor);
		
		this.delOperateAddress(main.getId());
		
		this.addOperateAddress(main.getId(),params.getOperateAddressLevel(),params.getOperateAddressBigTxt());
		
		this.delOtherCondition(main.getId());
		
		this.addOtherCondition(params, main.getId());
		
		try {
			//修改日志
			StringBuffer sb = new StringBuffer();
			sb.append(LogInfoExtUtil.getModifyLog(dictionaryRepository, oldMain,main));
			sb.append(LogInfoExtUtil.getModifyLog(dictionaryRepository, oldSponsor,sponsor));
			sb.append(LogInfoExtUtil.getModifyLog(dictionaryRepository, oldLeague,league));
			sb.append(LogInfoExtUtil.getModifyLog(dictionaryRepository, oldPmbrCount,pmbrCount));
			if(params.getReportHigher().intValue() == 1){
				logService.saveLog(LOG_OPERATION_TYPE.MODIFY.toString(), 
						logService.getDetailInfo("log.unpublic.modify.reporthigher",
								baseService.getUserName(),oldMain.getName(),
								sb));
			}else{
				logService.saveLog(LOG_OPERATION_TYPE.MODIFY.toString(), 
						logService.getDetailInfo("log.unpublic.modify",
								baseService.getUserName(),oldMain.getName(),
								sb));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 删除组织
	 * @param info
	 */
	@Transactional
	public void delOrg(String[] partyOrgIdArray) {
		for(int i = 0; i < partyOrgIdArray.length; i++){
			UnpublicOrgInfo info = new UnpublicOrgInfo();
			info.setId(Integer.parseInt(partyOrgIdArray[i]));
			info.setStatus("0");
			unpublicOrgInfoMapper.updateByPrimaryKeySelective(info);
		}
	}

	/**
	 * 撤销组织
	 * @param id
	 * @param remarks
	 */
	@Transactional
	public void cancelOrg(String id, String remarks) {

		UnpublicOrgCancelRecord reason = new UnpublicOrgCancelRecord();
		reason.setUnpubliceconOrgInfoId(Integer.parseInt(id));
		reason.setReason(remarks);
		reason.setCreateTime(new Date());
		reason.setCreator(baseService.getUserName());
		reason.setStatus(CommonConstants.STATUS_FLAG_VALID);
		unpublicOrgCancelRecordMapper.insertSelective(reason);
		
		UnpublicOrgInfo main = unpublicOrgInfoMapper.selectByPrimaryKey(Integer.parseInt(id));
		main.setStatus("3");//撤销申请中
		unpublicOrgInfoMapper.updateByPrimaryKeySelective(main);
	}

	/**
	 * 新增党员
	 * @param params
	 */
	@Transactional
	public void addPartymbr(AddPmbrParams params) {

		Date birthday = DateUtil.getDateFromString(params.getBirthday());
		
		UnpublicOrgPmbrInfo pmbrInfo = new UnpublicOrgPmbrInfo();
		BeanUtils.copyProperties(params, pmbrInfo);
		pmbrInfo.setCreateOrg(baseService.getDepartment());//填报单位
		pmbrInfo.setCreateTime(new Date());
		pmbrInfo.setCreator(baseService.getUserName());
		pmbrInfo.setStatus("1");
		pmbrInfo.setBirthday(birthday);
		unpublicOrgPmbrInfoMapper.insertSelective(pmbrInfo);
		
		UnpublicOrgPmbrChangeInfo pmbrChangeInfo = new UnpublicOrgPmbrChangeInfo();
		pmbrChangeInfo.setUnpublicOrgInfoId(params.getUnpublicOrgInfoId());
		pmbrChangeInfo.setUnpublicOrgPartymbrInfoId(pmbrInfo.getId());
		pmbrChangeInfo.setType(params.getType());
		pmbrChangeInfo.setCreateOrg(baseService.getDepartment());//填报单位
		pmbrChangeInfo.setCreateTime(new Date());
		pmbrChangeInfo.setCreator(baseService.getUserName());
		pmbrChangeInfo.setStatus("1");//增加
		unpublicOrgPmbrChangeInfoMapper.insertSelective(pmbrChangeInfo);
		
		//计算党员数量
		UnpublicOrgPmbrCount oldCount = new UnpublicOrgPmbrCount();
		oldCount.setUnpublicOrgInfoId(params.getUnpublicOrgInfoId());
		oldCount = unpublicOrgPmbrCountMapper.selectOne(oldCount);
		if(oldCount == null){
			UnpublicOrgPmbrCount count = new UnpublicOrgPmbrCount();
			count.setUnpublicOrgInfoId(params.getUnpublicOrgInfoId());
			count.setInitIs("1");
			unpublicOrgPmbrCountMapper.insertSelective(count);
		}
		if(oldCount != null){
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
			//组织关系在非公企业
			if(others.contains(params.getPartymbrInUnpublicIs())){
				oldCount.setPartymbrInUnpublicNum(oldCount.getPartymbrInUnpublicNum()+1);
			}
			//中层管理人员
			if(others.contains(params.getPartymbrMiddleManagerIs())){
				oldCount.setPartymbrMiddleManagerNum(oldCount.getPartymbrMiddleManagerNum()+1);
			}
			//中高级专业技术人员
			if(others.contains(params.getPartymbrOnMiddletechIs())){
				oldCount.setPartymbrOnMiddletechNum(oldCount.getPartymbrOnMiddletechNum()+1);
			}
			//生产经营一线职工
			if(others.contains(params.getPartymbrFrontlineIs())){
				oldCount.setPartymbrFrontlineNum(oldCount.getPartymbrFrontlineNum()+1);
			}
			//组织关系不在非公企业
			if(others.contains(params.getPartymbrNotinUnpublicIs())){
				oldCount.setPartymbrNotinUnpublicNum(oldCount.getPartymbrNotinUnpublicNum()+1);
			}
			//农村党员
			if(others.contains(params.getPartymbrInVillageIs())){
				oldCount.setPartymbrInVillageNum(oldCount.getPartymbrInVillageNum()+1);
			}
			
			unpublicOrgPmbrCountMapper.updateByPrimaryKeySelective(oldCount);
		}
		
		
		//其他特殊条件统计项
		String otherCondition = params.getOtherCondition();
		if(StringUtils.isNotEmpty(otherCondition)){
			List<DataDictionary> otherConditionList = dictionaryRepository.findByDmm(CommonConstants.UNPUBLIC_PM_OTHER_CONDITION);
			List<String> otherList = Arrays.asList(otherCondition.split(","));
			
			List<UnpublicOrgPmbrOtherCount> otherCountList = new ArrayList<>();
			UnpublicOrgPmbrOtherCount otherCount = null;
			for (DataDictionary dataDictionary : otherConditionList) {
				otherCount = new UnpublicOrgPmbrOtherCount();
				if(otherList.contains(dataDictionary.getCode())){
					otherCount.setFieldName(dataDictionary.getCode());
					otherCount.setFieldValue(1);
				}else{
					otherCount.setFieldName(dataDictionary.getCode());
					otherCount.setFieldValue(0);
				}
				
				otherCount.setUnpublicOrgInfoId(params.getUnpublicOrgInfoId());
				otherCount.setUnpublicOrgPartymbrInfoId(pmbrInfo.getId());
				
				otherCountList.add(otherCount);
			}
			unpublicOrgPmbrOtherCountMapper.insertList(otherCountList);
		}
		
		
		try {
			//新增党员日志
			StringBuffer sb = new StringBuffer();
			sb.append(LogInfoExtUtil.getAddLog(dictionaryRepository, pmbrInfo));
			sb.append(LogInfoExtUtil.getAddLog(dictionaryRepository, pmbrChangeInfo));
			logService.saveLog(LOG_OPERATION_TYPE.CREATE.toString(), 
					logService.getDetailInfo("log.unpublic.partymbr.create",
							baseService.getUserName(),pmbrInfo.getName(),
							sb));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 减少党员
	 * @param params
	 */
	@Transactional
	public void delPartymbr(RemovePmbrParams params) {

		UnpublicOrgPmbrInfo oldPmbrInfo = unpublicOrgPmbrInfoMapper.selectByPrimaryKey(params.getPmbrInfoId());
		
		UnpublicOrgPmbrInfo pmbrInfo = unpublicOrgPmbrInfoMapper.selectByPrimaryKey(Integer.parseInt(params.getPmbrInfoId()));
		pmbrInfo.setStatus("0");
		unpublicOrgPmbrInfoMapper.updateByPrimaryKeySelective(pmbrInfo);
		
		UnpublicOrgPmbrChangeInfo pmbrChangeInfo = new UnpublicOrgPmbrChangeInfo();
		pmbrChangeInfo.setUnpublicOrgInfoId(oldPmbrInfo.getUnpublicOrgInfoId());
		pmbrChangeInfo.setUnpublicOrgPartymbrInfoId(pmbrInfo.getId());
		pmbrChangeInfo.setGowhere(params.getGowhere());
		pmbrChangeInfo.setContact(params.getContact());
		pmbrChangeInfo.setCreateOrg(baseService.getDepartment());//填报单位
		pmbrChangeInfo.setCreateTime(new Date());
		pmbrChangeInfo.setCreator(baseService.getUserName());
		pmbrChangeInfo.setStatus("2");//减少
		unpublicOrgPmbrChangeInfoMapper.insertSelective(pmbrChangeInfo);
		
		//计算党员数量
		UnpublicOrgPmbrCount oldCount = new UnpublicOrgPmbrCount();
		oldCount.setUnpublicOrgInfoId(oldPmbrInfo.getUnpublicOrgInfoId());
		oldCount = unpublicOrgPmbrCountMapper.selectOne(oldCount);
		
		if(oldCount != null){
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
			//组织关系在非公企业
			if(others.contains(oldPmbrInfo.getPartymbrInUnpublicIs())){
				oldCount.setPartymbrInUnpublicNum(oldCount.getPartymbrInUnpublicNum()-1);
			}
			//中层管理人员
			if(others.contains(oldPmbrInfo.getPartymbrMiddleManagerIs())){
				oldCount.setPartymbrMiddleManagerNum(oldCount.getPartymbrMiddleManagerNum()-1);
			}
			//中高级专业技术人员
			if(others.contains(oldPmbrInfo.getPartymbrOnMiddletechIs())){
				oldCount.setPartymbrOnMiddletechNum(oldCount.getPartymbrOnMiddletechNum()-1);
			}
			//生产经营一线职工
			if(others.contains(oldPmbrInfo.getPartymbrFrontlineIs())){
				oldCount.setPartymbrFrontlineNum(oldCount.getPartymbrFrontlineNum()-1);
			}
			//组织关系不在非公企业
			if(others.contains(oldPmbrInfo.getPartymbrNotinUnpublicIs())){
				oldCount.setPartymbrNotinUnpublicNum(oldCount.getPartymbrNotinUnpublicNum()-1);
			}
			//农村党员
			if(others.contains(oldPmbrInfo.getPartymbrInVillageIs())){
				oldCount.setPartymbrInVillageNum(oldCount.getPartymbrInVillageNum()-1);
			}
			unpublicOrgPmbrCountMapper.updateByPrimaryKeySelective(oldCount);
		}
		
		
		//减少党员日志
		logService.saveLog(LOG_OPERATION_TYPE.DELETE.toString(), 
				logService.getDetailInfo("log.unpublic.partymbr.delete",
						baseService.getUserName(),oldPmbrInfo.getName()));
	}  
	
	
	private void addOtherCondition(AllParams params, Integer mainId) {

		//其他特殊条件统计项
		String otherCondition = params.getOtherCondition();
		if(StringUtils.isNotEmpty(otherCondition)){
			List<DataDictionary> otherConditionList = dictionaryRepository.findByDmm(CommonConstants.UNPUBLIC_ORG_OTHER_CONDITION);
			List<String> otherList = Arrays.asList(otherCondition.split(","));
			
			List<UnpublicOrgInfoOtherCount> otherCountList = new ArrayList<>();
			UnpublicOrgInfoOtherCount otherCount = null;
			for (DataDictionary dataDictionary : otherConditionList) {
				otherCount = new UnpublicOrgInfoOtherCount();
				if(otherList.contains(dataDictionary.getCode())){
					otherCount.setFieldName(dataDictionary.getCode());
					otherCount.setFieldValue(1);
				}else{
					otherCount.setFieldName(dataDictionary.getCode());
					otherCount.setFieldValue(0);
				}
				
				otherCount.setUnpublicOrgInfoId(mainId);
				
				otherCountList.add(otherCount);
			}
			unpublicOrgInfoOtherCountMapper.insertList(otherCountList);
		}
	}
	

	private void delOtherCondition(Integer id) {
		UnpublicOrgInfoOtherCount c = new UnpublicOrgInfoOtherCount();
		c.setUnpublicOrgInfoId(id);
		unpublicOrgInfoOtherCountMapper.delete(c);
	}
}
