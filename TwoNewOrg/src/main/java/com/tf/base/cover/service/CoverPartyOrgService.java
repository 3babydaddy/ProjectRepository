package com.tf.base.cover.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tf.base.common.service.BaseService;
import com.tf.base.cover.domain.CoverOrgInfo;
import com.tf.base.cover.domain.CoverOrgPmbrCount;
import com.tf.base.cover.domain.CoverOrgPmbrInfo;
import com.tf.base.cover.domain.CoverPartyOrgBuilding;
import com.tf.base.cover.domain.CoverPartyOrgChangeInfo;
import com.tf.base.cover.domain.CoverPartyOrgInfo;
import com.tf.base.cover.persistence.CoverOrgPmbrInfoMapper;
import com.tf.base.cover.persistence.CoverPartyOrgBuildingMapper;
import com.tf.base.cover.persistence.CoverPartyOrgChangeInfoMapper;
import com.tf.base.cover.persistence.CoverPartyOrgInfoMapper;
import com.tf.base.socialorg.domain.SocialOrgInfo;
import com.tf.base.socialorg.persistence.SocialOrgInfoMapper;
import com.tf.base.unpublic.domain.AttachmentCommonInfo;
import com.tf.base.unpublic.domain.CancelReasonInfo;
import com.tf.base.unpublic.domain.DeputySecretaryInfo;
import com.tf.base.unpublic.domain.UnpublicOrgInfo;
import com.tf.base.unpublic.persistence.AttachmentCommonInfoMapper;
import com.tf.base.unpublic.persistence.CancelReasonInfoMapper;
import com.tf.base.unpublic.persistence.DeputySecretaryInfoMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgInfoMapper;

import tk.mybatis.mapper.entity.Example;

@Service
public class CoverPartyOrgService {
	
	@Autowired
	private BaseService baseService;
	@Autowired
	private AttachmentCommonInfoMapper attachmentCommonInfoMapper;
	@Autowired
	private DeputySecretaryInfoMapper deputySecretaryInfoMapper;
	@Autowired
	private CancelReasonInfoMapper cancelReasonInfoMapper;
	@Autowired
	private CoverPartyOrgInfoMapper coverPartyOrgInfoMapper;
	@Autowired
	private CoverPartyOrgChangeInfoMapper coverPartyOrgChangeInfoMapper;
	@Autowired
	private CoverOrgPmbrInfoMapper coverOrgPmbrInfoMapper;
	@Autowired
	private UnpublicOrgInfoMapper unpublicOrgInfoMapper;
	@Autowired
	private SocialOrgInfoMapper socialOrgInfoMapper;
	@Autowired
	private CoverPartyOrgBuildingMapper coverPartyOrgBuildingMapper;
	
	/**
	 * 新增党组织
	 * @param main
	 * @param sponsor
	 * @param league
	 * @param pmbrCount
	 * @param params
	 */
	@Transactional
	public void addOrg(CoverPartyOrgInfo coverPartyOrgInfo, List<CoverPartyOrgChangeInfo> pociList, 
			List<DeputySecretaryInfo> dsiList)throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		if(coverPartyOrgInfo.getPartyOrgTimeTxt() != null && coverPartyOrgInfo.getPartyOrgTimeTxt().length()>0){
			coverPartyOrgInfo.setPartyOrgTime(sdf.parse(coverPartyOrgInfo.getPartyOrgTimeTxt()));
		}
		if(coverPartyOrgInfo.getSecretaryBirthdayTxt() != null && coverPartyOrgInfo.getSecretaryBirthdayTxt().length()>0){
			coverPartyOrgInfo.setSecretaryBirthday(sdf.parse(coverPartyOrgInfo.getSecretaryBirthdayTxt()));
		}
		
		coverPartyOrgInfo.setCreateOrg(baseService.getCurrentUserDeptId());
		coverPartyOrgInfo.setCreater(baseService.getUserName());
		coverPartyOrgInfo.setCreateTime(new Date());
		coverPartyOrgInfoMapper.insertSelective(coverPartyOrgInfo);
		for(CoverPartyOrgChangeInfo poci : pociList){
			//把主表中的id赋值到换届和附件中
			AttachmentCommonInfo attachmentCommonInfo = attachmentCommonInfoMapper.selectByPrimaryKey(poci.getChangeAttachmentId());
			poci.setCoverPartyOrgId(coverPartyOrgInfo.getId());
			attachmentCommonInfo.setMainTableId(coverPartyOrgInfo.getId());
			//更新换届的附件信息
			attachmentCommonInfoMapper.updateByPrimaryKeySelective(attachmentCommonInfo);
		}
		//更新主表中的附件信息
		if(coverPartyOrgInfo.getPartyOrgAttachment() != null && coverPartyOrgInfo.getPartyOrgAttachment().length() > 0){
			AttachmentCommonInfo aCInfo = attachmentCommonInfoMapper.selectByPrimaryKey(Integer.valueOf(coverPartyOrgInfo.getPartyOrgAttachment()));
			aCInfo.setMainTableId(coverPartyOrgInfo.getId());
			attachmentCommonInfoMapper.updateByPrimaryKeySelective(aCInfo);
		}
		//批量插入换届信息
		if(pociList.size() > 0){
			coverPartyOrgChangeInfoMapper.insertList(pociList);
		}
		
		//批量插入党副信息
		for(DeputySecretaryInfo dsInfo :dsiList){
			dsInfo.setType("3");
			dsInfo.setPartyOrgId(coverPartyOrgInfo.getId()+"");
		}
		if(dsiList.size() > 0){
			deputySecretaryInfoMapper.insertList(dsiList);
		}
		
	}

	/**
	 * 修改党组织
	 * @param main
	 * @param sponsor
	 * @param league
	 * @param pmbrCount
	 * @param params
	 */
	@Transactional
	public void updateOrg(CoverPartyOrgInfo coverPartyOrgInfo, List<CoverPartyOrgChangeInfo> pociList, 
			List<DeputySecretaryInfo> dsiList)throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		if(coverPartyOrgInfo.getPartyOrgTimeTxt() != null && coverPartyOrgInfo.getPartyOrgTimeTxt().length()>0){
			coverPartyOrgInfo.setPartyOrgTime(sdf.parse(coverPartyOrgInfo.getPartyOrgTimeTxt()));
		}
		if(coverPartyOrgInfo.getSecretaryBirthdayTxt() != null && coverPartyOrgInfo.getSecretaryBirthdayTxt().length()>0){
			coverPartyOrgInfo.setSecretaryBirthday(sdf.parse(coverPartyOrgInfo.getSecretaryBirthdayTxt()));
		}
		coverPartyOrgInfoMapper.updateByPrimaryKeySelective(coverPartyOrgInfo);
		for(CoverPartyOrgChangeInfo poci : pociList){
			//把主表中的id赋值到换届和附件中
			AttachmentCommonInfo attachmentCommonInfo = attachmentCommonInfoMapper.selectByPrimaryKey(poci.getChangeAttachmentId());
			poci.setCoverPartyOrgId(coverPartyOrgInfo.getId());
			attachmentCommonInfo.setMainTableId(coverPartyOrgInfo.getId());
			//更新换届的附件信息
			attachmentCommonInfoMapper.updateByPrimaryKeySelective(attachmentCommonInfo);
		}
		//更新主表中的附件信息
		if(coverPartyOrgInfo.getPartyOrgAttachment() != null && coverPartyOrgInfo.getPartyOrgAttachment().length() > 0){
			AttachmentCommonInfo aCInfo = attachmentCommonInfoMapper.selectByPrimaryKey(Integer.valueOf(coverPartyOrgInfo.getPartyOrgAttachment()));
			aCInfo.setMainTableId(coverPartyOrgInfo.getId());
			attachmentCommonInfoMapper.updateByPrimaryKeySelective(aCInfo);
		}
		
		coverPartyOrgChangeInfoMapper.deleteChangeInfo(coverPartyOrgInfo.getId()+"");
		//批量插入换届信息
		if(pociList.size() > 0){
			coverPartyOrgChangeInfoMapper.insertList(pociList);
		}
		
		//批量插入党副信息
		deputySecretaryInfoMapper.deleteDeputysecretaryInfo(coverPartyOrgInfo.getId()+"");
		for(DeputySecretaryInfo dsInfo :dsiList){
			dsInfo.setType("3");
			dsInfo.setPartyOrgId(coverPartyOrgInfo.getId()+"");
		}
		if(dsiList.size() > 0){
			deputySecretaryInfoMapper.insertList(dsiList);
		}
		
	}

	/**
	 * 撤销党组织
	 * @param id
	 * @param remarks
	 */
	@Transactional
	public void cancelOrg(CancelReasonInfo reason, CoverPartyOrgInfo info) {
		cancelReasonInfoMapper.insertSelective(reason);
		coverPartyOrgInfoMapper.updateByPrimaryKeySelective(info);
	}
	
	/**
	 * 取消撤销党组织
	 * @param id
	 * @param remarks
	 */
	@Transactional
	public void nocancelOrg(CoverPartyOrgInfo main) {
		coverPartyOrgInfoMapper.updateByPrimaryKeySelective(main);
		Example example = new Example(CancelReasonInfo.class);
		example.setOrderByClause("createTime desc");
		example.createCriteria().andEqualTo("partyOrgId", main.getId()).andEqualTo("type", '3').andEqualTo("status", 1);
		List<CancelReasonInfo> socialOrgCancelRecords = cancelReasonInfoMapper.selectByExample(example);
		if(socialOrgCancelRecords.size() > 0){
			CancelReasonInfo uocRecord = socialOrgCancelRecords.get(0);
			uocRecord.setStatus("0");
			cancelReasonInfoMapper.updateByPrimaryKeySelective(uocRecord);
		}
		Example example1 = new Example(AttachmentCommonInfo.class);
		example1.createCriteria().andEqualTo("mainTableId", main.getId()).andEqualTo("modular", 3).andEqualTo("type", 1).andEqualTo("action", 1).andEqualTo("status", 1);
		List<AttachmentCommonInfo> dataFiles = attachmentCommonInfoMapper.selectByExample(example1);
		if(dataFiles.size() > 0){
			for(AttachmentCommonInfo acInfo : dataFiles){
				acInfo.setStatus(0);
				attachmentCommonInfoMapper.updateByPrimaryKeySelective(acInfo);
			}
		}
	}

	/**
	 * 计算覆盖党员的数量
	 * @param id
	 * @param remarks
	 */
	public CoverOrgPmbrCount getPmrCount(String id){
		int partymbrIncoverNum = 0;
		int partymbrNotIncoverNum = 0;
		int partymbrUnderThirtyfiveNum = 0;
		int partymbrOnCollegeNum = 0;
		int partymbrUnderHighschoolNum = 0;
		Example example = new Example(CoverOrgPmbrInfo.class);
		example.createCriteria().andEqualTo("coverOrgInfoId", id).andEqualTo("status", 1);
		List<CoverOrgPmbrInfo> resultList = coverOrgPmbrInfoMapper.selectByExample(example);
		if(resultList.size() > 0){
			for(CoverOrgPmbrInfo copi : resultList){
				//计算从覆盖组织转入党员总数
				if(copi.getIsFromCoverOrg() != null && "1".equals(copi.getIsFromCoverOrg())){
					partymbrIncoverNum++;
				}
				//计算不从覆盖组织转入党员总数
				if(copi.getIsFromCoverOrg() != null && "0".equals(copi.getIsFromCoverOrg())){
					partymbrNotIncoverNum++;
				}
				Date date = new Date();
				long year = (date.getTime() - copi.getBirthday().getTime())/(1000*60*60*24*365);
				//35岁以下
				if(year < 35){
					partymbrUnderThirtyfiveNum++;
				}
				//大学及以上学历
				if(copi.getEducation() != null && Integer.parseInt(copi.getEducation()) > 3){
					partymbrOnCollegeNum++;
				}
				//高中及以下学历
				if(copi.getEducation() != null && Integer.parseInt(copi.getEducation()) < 4){
					partymbrUnderHighschoolNum++;
				}
			}
		}
		CoverOrgPmbrCount coverOrgPmbrCount = new CoverOrgPmbrCount();
		coverOrgPmbrCount.setPartymbrIncoverNum(partymbrIncoverNum);
		coverOrgPmbrCount.setPartymbrNotIncoverNum(partymbrNotIncoverNum);
		coverOrgPmbrCount.setPartymbrUnderThirtyfiveNum(partymbrUnderThirtyfiveNum);
		coverOrgPmbrCount.setPartymbrOnCollegeNum(partymbrOnCollegeNum);
		coverOrgPmbrCount.setPartymbrUnderHighschoolNum(partymbrUnderHighschoolNum);
		return coverOrgPmbrCount;
	}
	
	/**
	 *保存覆盖组织页面信息
	 * @param id
	 * @param remarks
	 */
	@Transactional
	public void saveCoverInfo(String mainId, List<CoverOrgInfo> orgIdList) {
		//把原来的覆盖党组织清空
		Example example = new Example(UnpublicOrgInfo.class);
		example.createCriteria().andEqualTo("coverPartyOrgId", mainId);
		List<UnpublicOrgInfo> unpublicOrgInfos = unpublicOrgInfoMapper.selectByExample(example);
		for(UnpublicOrgInfo orgInfo : unpublicOrgInfos){
			orgInfo.setCoverPartyOrgId(null);
			unpublicOrgInfoMapper.updateByPrimaryKey(orgInfo);
		}
		Example example1 = new Example(SocialOrgInfo.class);
		example1.createCriteria().andEqualTo("coverPartyOrgId", mainId);
		List<SocialOrgInfo> socialOrgInfos = socialOrgInfoMapper.selectByExample(example1);
		for(SocialOrgInfo orgInfo : socialOrgInfos){
			orgInfo.setCoverPartyOrgId(null);
			socialOrgInfoMapper.updateByPrimaryKey(orgInfo);
		}
		//给组织赋予新的覆盖党组织
		if(orgIdList.size() > 0){
			for(int i = 0; i < orgIdList.size(); i++){
				if("1".equals(orgIdList.get(i).getOrgType())){
					SocialOrgInfo socialOrgInfo = socialOrgInfoMapper.selectByPrimaryKey(orgIdList.get(i).getId());
					socialOrgInfo.setCoverPartyOrgId(mainId);
					socialOrgInfoMapper.updateByPrimaryKeySelective(socialOrgInfo);
				}else if("0".equals(orgIdList.get(i).getOrgType())){
					UnpublicOrgInfo unpublicOrgInfo = unpublicOrgInfoMapper.selectByPrimaryKey(orgIdList.get(i).getId());
					unpublicOrgInfo.setCoverPartyOrgId(mainId);
					unpublicOrgInfoMapper.updateByPrimaryKeySelective(unpublicOrgInfo);
				}
			}
		}
	}

	/**
	 * 党组织上报审核
	 * @param id
	 * @param remarks
	 */
	@Transactional
	public void orgsSetStatus(String partyOrgIds,String status) {
		Calendar cal = Calendar.getInstance(); 
		String year = cal.get(Calendar.YEAR)+"";
		String[] partyOrgArray = partyOrgIds.split(",");
		for(int i = 0; i < partyOrgArray.length; i++){
			if(partyOrgArray[i] != null && partyOrgArray[i].length() > 0){
				CoverPartyOrgInfo info = coverPartyOrgInfoMapper.selectByPrimaryKey(Integer.parseInt(partyOrgArray[i]));
				info.setStatus(status);
				coverPartyOrgInfoMapper.updateByPrimaryKeySelective(info);
				
				Example exampleByCo = new Example(CoverPartyOrgBuilding.class);
				exampleByCo.createCriteria().andEqualTo("coverPartyOrgId", Integer.parseInt(partyOrgArray[i])).andEqualTo("year", year);
				List<CoverPartyOrgBuilding> dataList = coverPartyOrgBuildingMapper.selectByExample(exampleByCo);
				if(dataList.size() == 0){
					CoverPartyOrgBuilding coverPartyOrgBuilding = new CoverPartyOrgBuilding();
					coverPartyOrgBuilding.setCoverPartyOrgId(Integer.parseInt(partyOrgArray[i]));
					coverPartyOrgBuilding.setYear(year);
					coverPartyOrgBuilding.setStatus("1");
					coverPartyOrgBuildingMapper.insert(coverPartyOrgBuilding);
				}
			}
		}
	}
	
}
