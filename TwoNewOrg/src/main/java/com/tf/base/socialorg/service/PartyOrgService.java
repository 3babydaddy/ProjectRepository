package com.tf.base.socialorg.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tf.base.common.service.BaseService;
import com.tf.base.socialorg.domain.SocialOrgInfo;
import com.tf.base.socialorg.domain.SocialPartyOrgChangeInfo;
import com.tf.base.socialorg.domain.SocialPartyOrgInfo;
import com.tf.base.socialorg.persistence.SocialOrgInfoMapper;
import com.tf.base.socialorg.persistence.SocialPartyOrgChangeInfoMapper;
import com.tf.base.socialorg.persistence.SocialPartyOrgInfoMapper;
import com.tf.base.unpublic.domain.AttachmentCommonInfo;
import com.tf.base.unpublic.domain.CancelReasonInfo;
import com.tf.base.unpublic.domain.DeputySecretaryInfo;
import com.tf.base.unpublic.persistence.AttachmentCommonInfoMapper;
import com.tf.base.unpublic.persistence.CancelReasonInfoMapper;
import com.tf.base.unpublic.persistence.DeputySecretaryInfoMapper;

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
			List<DeputySecretaryInfo> dsiList, String[] orgInfoArray)throws Exception {
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
			AttachmentCommonInfo attachmentCommonInfo = new AttachmentCommonInfo();
			poci.setSocialPartyOrgId(socialPartyOrgInfo.getId());
			attachmentCommonInfo.setId(poci.getChangeAttachmentId());
			attachmentCommonInfo.setMainTableId(socialPartyOrgInfo.getId());
			//更新换届的附件信息
			attachmentCommonInfoMapper.updateByPrimaryKeySelective(attachmentCommonInfo);
		}
		//更新主表中的附件信息
		AttachmentCommonInfo aCInfo = new AttachmentCommonInfo();
		if(socialPartyOrgInfo.getPartyOrgAttachment() != null && socialPartyOrgInfo.getPartyOrgAttachment().length() > 0){
			aCInfo.setId(Integer.valueOf(socialPartyOrgInfo.getPartyOrgAttachment()));
			aCInfo.setMainTableId(socialPartyOrgInfo.getId());
			attachmentCommonInfoMapper.updateByPrimaryKeySelective(aCInfo);
		}
		//批量插入换届信息
		if(pociList.size() > 0){
			socialPartyOrgChangeInfoMapper.insertList(pociList);
		}
		for(int j = 0; j < orgInfoArray.length; j++){
			SocialOrgInfo socialOrgInfo = new SocialOrgInfo();
			socialOrgInfo.setId(Integer.valueOf(orgInfoArray[j]));
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
			List<DeputySecretaryInfo> dsiList)throws Exception {
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
			AttachmentCommonInfo attachmentCommonInfo = new AttachmentCommonInfo();
			poci.setSocialPartyOrgId(socialPartyOrgInfo.getId());
			attachmentCommonInfo.setId(poci.getChangeAttachmentId());
			attachmentCommonInfo.setMainTableId(socialPartyOrgInfo.getId());
			//更新换届的附件信息
			attachmentCommonInfoMapper.updateByPrimaryKeySelective(attachmentCommonInfo);
		}
		//更新主表中的附件信息
		AttachmentCommonInfo aCInfo = new AttachmentCommonInfo();
		if(socialPartyOrgInfo.getPartyOrgAttachment() != null && socialPartyOrgInfo.getPartyOrgAttachment().length() > 0){
			aCInfo.setId(Integer.valueOf(socialPartyOrgInfo.getPartyOrgAttachment()));
			aCInfo.setMainTableId(socialPartyOrgInfo.getId());
			attachmentCommonInfoMapper.updateByPrimaryKeySelective(aCInfo);
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

}
