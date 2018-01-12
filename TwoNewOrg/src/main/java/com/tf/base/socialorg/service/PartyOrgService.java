package com.tf.base.socialorg.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tf.base.common.service.BaseService;
import com.tf.base.socialorg.domain.SocialOrgInfo;
import com.tf.base.socialorg.domain.SocialPartyOrgBuilding;
import com.tf.base.socialorg.domain.SocialPartyOrgChangeInfo;
import com.tf.base.socialorg.domain.SocialPartyOrgInfo;
import com.tf.base.socialorg.persistence.SocialOrgInfoMapper;
import com.tf.base.socialorg.persistence.SocialPartyOrgBuildingMapper;
import com.tf.base.socialorg.persistence.SocialPartyOrgChangeInfoMapper;
import com.tf.base.socialorg.persistence.SocialPartyOrgInfoMapper;
import com.tf.base.unpublic.domain.AttachmentCommonInfo;
import com.tf.base.unpublic.domain.CancelReasonInfo;
import com.tf.base.unpublic.domain.DeputySecretaryInfo;
import com.tf.base.unpublic.domain.LowerPartyOrg;
import com.tf.base.unpublic.domain.PartyInstructorInfo;
import com.tf.base.unpublic.persistence.AttachmentCommonInfoMapper;
import com.tf.base.unpublic.persistence.CancelReasonInfoMapper;
import com.tf.base.unpublic.persistence.DeputySecretaryInfoMapper;
import com.tf.base.unpublic.persistence.LowerPartyOrgMapper;
import com.tf.base.unpublic.persistence.PartyInstructorInfoMapper;

import tk.mybatis.mapper.entity.Example;

@Service
public class PartyOrgService {
	
	@Autowired
	private BaseService baseService;
	@Autowired
	private SocialPartyOrgInfoMapper socialPartyOrgInfoMapper;
	@Autowired
	private AttachmentCommonInfoMapper attachmentCommonInfoMapper;
	@Autowired
	private SocialPartyOrgChangeInfoMapper socialPartyOrgChangeInfoMapper;
	@Autowired
	private SocialOrgInfoMapper socialOrgInfoMapper;
	@Autowired
	private DeputySecretaryInfoMapper deputySecretaryInfoMapper;
	@Autowired
	private CancelReasonInfoMapper cancelReasonInfoMapper;
	@Autowired
	private SocialPartyOrgBuildingMapper socialPartyOrgBuildingMapper;
	@Autowired
	private PartyInstructorInfoMapper partyInstructorInfoMapper;
	@Autowired
	private LowerPartyOrgMapper lowerPartyOrgMapper;
	
	/**
	 * 新增党组织
	 * @param main
	 * @param sponsor
	 * @param league
	 * @param pmbrCount
	 * @param params
	 */
	@Transactional
	public void addOrg(SocialPartyOrgInfo socialPartyOrgInfo, List<SocialPartyOrgChangeInfo> pociList, 
			List<DeputySecretaryInfo> dsiList, List<PartyInstructorInfo> instructList, List<LowerPartyOrg> lowerPartyOrgList, String[] orgInfoArray)throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		if(socialPartyOrgInfo.getPartyOrgTimeTxt() != null && socialPartyOrgInfo.getPartyOrgTimeTxt().length()>0){
			socialPartyOrgInfo.setPartyOrgTime(sdf.parse(socialPartyOrgInfo.getPartyOrgTimeTxt()));
		}
		if(socialPartyOrgInfo.getSecretaryBirthdayTxt() != null && socialPartyOrgInfo.getSecretaryBirthdayTxt().length()>0){
			socialPartyOrgInfo.setSecretaryBirthday(sdf.parse(socialPartyOrgInfo.getSecretaryBirthdayTxt()));
		}
		
		socialPartyOrgInfo.setCreateOrg(baseService.getCurrentUserDeptId());
		socialPartyOrgInfo.setCreater(baseService.getUserName());
		socialPartyOrgInfo.setCreateTime(new Date());
		socialPartyOrgInfoMapper.insertSelective(socialPartyOrgInfo);
		for(SocialPartyOrgChangeInfo poci : pociList){
			//把主表中的id赋值到换届和附件中
			AttachmentCommonInfo attachmentCommonInfo = attachmentCommonInfoMapper.selectByPrimaryKey(poci.getChangeAttachmentId());
			poci.setSocialPartyOrgId(socialPartyOrgInfo.getId());
			if(attachmentCommonInfo != null){
				attachmentCommonInfo.setMainTableId(socialPartyOrgInfo.getId());
				//更新换届的附件信息
				attachmentCommonInfoMapper.updateByPrimaryKeySelective(attachmentCommonInfo);
			}
		}
		//更新主表中的附件信息
		if(socialPartyOrgInfo.getPartyOrgAttachment() != null && socialPartyOrgInfo.getPartyOrgAttachment().length() > 0){
			AttachmentCommonInfo aCInfo = attachmentCommonInfoMapper.selectByPrimaryKey(Integer.valueOf(socialPartyOrgInfo.getPartyOrgAttachment()));
			if(aCInfo != null){
				aCInfo.setMainTableId(socialPartyOrgInfo.getId());
				attachmentCommonInfoMapper.updateByPrimaryKeySelective(aCInfo);
			}
		}
		//批量插入换届信息
		if(pociList.size() > 0){
			socialPartyOrgChangeInfoMapper.insertList(pociList);
		}
		for(int j = 0; j < orgInfoArray.length; j++){
			SocialOrgInfo socialOrgInfo = socialOrgInfoMapper.selectByPrimaryKey(Integer.valueOf(orgInfoArray[j]));
			socialOrgInfo.setSocialPartyOrgId(socialPartyOrgInfo.getId()+"");
			socialOrgInfoMapper.updateByPrimaryKeySelective(socialOrgInfo);
		}
		//批量插入党副信息
		for(DeputySecretaryInfo dsInfo :dsiList){
			dsInfo.setType("1");
			dsInfo.setPartyOrgId(socialPartyOrgInfo.getId()+"");
		}
		if(dsiList.size() > 0){
			deputySecretaryInfoMapper.insertList(dsiList);
		}
		//批量插入指导员信息
		for(PartyInstructorInfo pInfo :instructList){
			pInfo.setType("1");
			pInfo.setPartyOrgId(socialPartyOrgInfo.getId());
		}
		if(instructList.size() > 0){
			partyInstructorInfoMapper.insertList(instructList);
		}
		
		//批量插入下级党组织信息
		for(LowerPartyOrg lInfo : lowerPartyOrgList){
			lInfo.setType("1");
			lInfo.setPartyOrgId(socialPartyOrgInfo.getId());
		}
		if(lowerPartyOrgList.size() > 0){
			lowerPartyOrgMapper.insertList(lowerPartyOrgList);
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
	public void updateOrg(SocialPartyOrgInfo socialPartyOrgInfo, List<SocialPartyOrgChangeInfo> pociList, 
			List<DeputySecretaryInfo> dsiList, List<PartyInstructorInfo> instructList, List<LowerPartyOrg> lowerPartyOrgList, String[] orgInfoArray)throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		if(socialPartyOrgInfo.getPartyOrgTimeTxt() != null && socialPartyOrgInfo.getPartyOrgTimeTxt().length()>0){
			socialPartyOrgInfo.setPartyOrgTime(sdf.parse(socialPartyOrgInfo.getPartyOrgTimeTxt()));
		}
		if(socialPartyOrgInfo.getSecretaryBirthdayTxt() != null && socialPartyOrgInfo.getSecretaryBirthdayTxt().length()>0){
			socialPartyOrgInfo.setSecretaryBirthday(sdf.parse(socialPartyOrgInfo.getSecretaryBirthdayTxt()));
		}
		socialPartyOrgInfoMapper.updateByPrimaryKeySelective(socialPartyOrgInfo);
		for(SocialPartyOrgChangeInfo poci : pociList){
			//把主表中的id赋值到换届和附件中
			AttachmentCommonInfo attachmentCommonInfo = attachmentCommonInfoMapper.selectByPrimaryKey(poci.getChangeAttachmentId());
			poci.setSocialPartyOrgId(socialPartyOrgInfo.getId());
			if(attachmentCommonInfo != null){
				attachmentCommonInfo.setMainTableId(socialPartyOrgInfo.getId());
				//更新换届的附件信息
				attachmentCommonInfoMapper.updateByPrimaryKeySelective(attachmentCommonInfo);
			}
		}
		//更新主表中的附件信息
		if(socialPartyOrgInfo.getPartyOrgAttachment() != null && socialPartyOrgInfo.getPartyOrgAttachment().length() > 0){
			AttachmentCommonInfo aCInfo = attachmentCommonInfoMapper.selectByPrimaryKey(Integer.valueOf(socialPartyOrgInfo.getPartyOrgAttachment()));
			if(aCInfo != null){
				aCInfo.setMainTableId(socialPartyOrgInfo.getId());
				attachmentCommonInfoMapper.updateByPrimaryKeySelective(aCInfo);
			}
		}
		
		socialPartyOrgChangeInfoMapper.deleteChangeInfo(socialPartyOrgInfo.getId()+"");
		//批量插入换届信息
		if(pociList.size() > 0){
			socialPartyOrgChangeInfoMapper.insertList(pociList);
		}
		
		//批量插入党副信息
		deputySecretaryInfoMapper.deleteDeputysecretaryInfo(socialPartyOrgInfo.getId()+"");
		for(DeputySecretaryInfo dsInfo :dsiList){
			dsInfo.setType("1");
			dsInfo.setPartyOrgId(socialPartyOrgInfo.getId()+"");
		}
		if(dsiList.size() > 0){
			deputySecretaryInfoMapper.insertList(dsiList);
		}
		
		//批量插入指导员信息
		partyInstructorInfoMapper.deleteInstructInfo(socialPartyOrgInfo.getId()+"");
		for(PartyInstructorInfo pInfo :instructList){
			pInfo.setType("1");
			pInfo.setPartyOrgId(socialPartyOrgInfo.getId());
		}
		if(instructList.size() > 0){
			partyInstructorInfoMapper.insertList(instructList);
		}
		
		//批量插入下级党组织信息
		lowerPartyOrgMapper.deleteLowerInfo(socialPartyOrgInfo.getId()+"");
		for(LowerPartyOrg lInfo : lowerPartyOrgList){
			lInfo.setType("1");
			lInfo.setPartyOrgId(socialPartyOrgInfo.getId());
		}
		if(lowerPartyOrgList.size() > 0){
			lowerPartyOrgMapper.insertList(lowerPartyOrgList);
		}
		
		//更新党组织信息
		socialOrgInfoMapper.updateOrgPartyOrgId(socialPartyOrgInfo.getId()+"");
		for(int j = 0; j < orgInfoArray.length; j++){
			SocialOrgInfo socialOrgInfo = socialOrgInfoMapper.selectByPrimaryKey(Integer.valueOf(orgInfoArray[j]));
			socialOrgInfo.setSocialPartyOrgId(socialPartyOrgInfo.getId()+"");
			socialOrgInfoMapper.updateByPrimaryKeySelective(socialOrgInfo);
		}
	}

	/**
	 * 撤销党组织
	 * @param id
	 * @param remarks
	 */
	@Transactional
	public void cancelOrg(CancelReasonInfo reason, SocialPartyOrgInfo info) {
		cancelReasonInfoMapper.insertSelective(reason);
		socialPartyOrgInfoMapper.updateByPrimaryKeySelective(info);
	}
	
	/**
	 * 取消撤销党组织
	 * @param id
	 * @param remarks
	 */
	@Transactional
	public void nocancelOrg(SocialPartyOrgInfo main) {
		socialPartyOrgInfoMapper.updateByPrimaryKeySelective(main);
		Example example = new Example(CancelReasonInfo.class);
		example.setOrderByClause("createTime desc");
		example.createCriteria().andEqualTo("partyOrgId", main.getId()).andEqualTo("type", '1').andEqualTo("status", 1);
		List<CancelReasonInfo> socialOrgCancelRecords = cancelReasonInfoMapper.selectByExample(example);
		if(socialOrgCancelRecords.size() > 0){
			CancelReasonInfo uocRecord = socialOrgCancelRecords.get(0);
			uocRecord.setStatus("0");
			cancelReasonInfoMapper.updateByPrimaryKeySelective(uocRecord);
		}
		Example example1 = new Example(AttachmentCommonInfo.class);
		example1.createCriteria().andEqualTo("mainTableId", main.getId()).andEqualTo("modular", 1).andEqualTo("type", 1).andEqualTo("action", 1).andEqualTo("status", 1);
		List<AttachmentCommonInfo> dataFiles = attachmentCommonInfoMapper.selectByExample(example1);
		if(dataFiles.size() > 0){
			for(AttachmentCommonInfo acInfo : dataFiles){
				acInfo.setStatus(0);
				attachmentCommonInfoMapper.updateByPrimaryKeySelective(acInfo);
			}
		}
	}
	
	/**
	 * 党组织上报审核
	 * @param id
	 * @param remarks
	 */
	@Transactional
	public void orgsSetStatus(String partyOrgIds, String status) {
		Calendar cal = Calendar.getInstance(); 
		String year = cal.get(Calendar.YEAR)+"";
		String[] partyOrgArray = partyOrgIds.split(",");
		for(int i = 0; i < partyOrgArray.length; i++){
			if(partyOrgArray[i] != null && partyOrgArray[i].length() > 0){
				SocialPartyOrgInfo info = socialPartyOrgInfoMapper.selectByPrimaryKey(Integer.parseInt(partyOrgArray[i]));
				info.setStatus(status);
				socialPartyOrgInfoMapper.updateByPrimaryKeySelective(info);
				//判断上报审核的党组织是否需要新增党建
				Example exampleBySo = new Example(SocialPartyOrgBuilding.class);
				exampleBySo.createCriteria().andEqualTo("socialPartyOrgId", Integer.parseInt(partyOrgArray[i])).andEqualTo("year", year);
				List<SocialPartyOrgBuilding> dataFiles = socialPartyOrgBuildingMapper.selectByExample(exampleBySo);
				if(dataFiles.size() == 0){
					SocialPartyOrgBuilding socialPartyOrgBuilding = new SocialPartyOrgBuilding();
					socialPartyOrgBuilding.setSocialPartyOrgId(Integer.parseInt(partyOrgArray[i]));
					socialPartyOrgBuilding.setYear(year);
					socialPartyOrgBuilding.setStatus("1");
					socialPartyOrgBuildingMapper.insert(socialPartyOrgBuilding);
				}
			}
		}
	}

}
