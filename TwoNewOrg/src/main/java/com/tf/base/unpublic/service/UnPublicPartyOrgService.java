package com.tf.base.unpublic.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tf.base.common.service.BaseService;
import com.tf.base.unpublic.domain.AttachmentCommonInfo;
import com.tf.base.unpublic.domain.CancelReasonInfo;
import com.tf.base.unpublic.domain.DeputySecretaryInfo;
import com.tf.base.unpublic.domain.UnpublicOrgInfo;
import com.tf.base.unpublic.domain.UnpublicPartyOrgChangeInfo;
import com.tf.base.unpublic.domain.UnpublicPartyOrgInfo;
import com.tf.base.unpublic.persistence.AttachmentCommonInfoMapper;
import com.tf.base.unpublic.persistence.CancelReasonInfoMapper;
import com.tf.base.unpublic.persistence.DeputySecretaryInfoMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgInfoMapper;
import com.tf.base.unpublic.persistence.UnpublicPartyOrgChangeInfoMapper;
import com.tf.base.unpublic.persistence.UnpublicPartyOrgInfoMapper;

import tk.mybatis.mapper.entity.Example;

@Service
public class UnPublicPartyOrgService {
	
	@Autowired
	private BaseService baseService;
	@Autowired
	private UnpublicPartyOrgInfoMapper unpublicPartyOrgInfoMapper;
	@Autowired
	private AttachmentCommonInfoMapper attachmentCommonInfoMapper;
	@Autowired
	private UnpublicPartyOrgChangeInfoMapper unpublicPartyOrgChangeInfoMapper;
	@Autowired
	private UnpublicOrgInfoMapper unpublicOrgInfoMapper;
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
	public void addOrg(UnpublicPartyOrgInfo unpublicPartyOrgInfo, List<UnpublicPartyOrgChangeInfo> pociList, 
			List<DeputySecretaryInfo> dsiList, String[] orgInfoArray)throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		if(unpublicPartyOrgInfo.getPartyOrgTimeTxt() != null && unpublicPartyOrgInfo.getPartyOrgTimeTxt().length()>0){
			unpublicPartyOrgInfo.setPartyOrgTime(sdf.parse(unpublicPartyOrgInfo.getPartyOrgTimeTxt()));
		}
		if(unpublicPartyOrgInfo.getSecretaryBirthdayTxt() != null && unpublicPartyOrgInfo.getSecretaryBirthdayTxt().length()>0){
			unpublicPartyOrgInfo.setSecretaryBirthday(sdf.parse(unpublicPartyOrgInfo.getSecretaryBirthdayTxt()));
		}
		
		unpublicPartyOrgInfo.setCreateOrg(baseService.getCurrentUserDeptId());
		unpublicPartyOrgInfo.setCreater(baseService.getUserName());
		unpublicPartyOrgInfo.setCreateTime(new Date());
		unpublicPartyOrgInfoMapper.insertSelective(unpublicPartyOrgInfo);
		for(UnpublicPartyOrgChangeInfo poci : pociList){
			//把主表中的id赋值到换届和附件中
			AttachmentCommonInfo attachmentCommonInfo = new AttachmentCommonInfo();
			poci.setSocialPartyOrgId(unpublicPartyOrgInfo.getId());
			attachmentCommonInfo.setId(poci.getChangeAttachmentId());
			attachmentCommonInfo.setMainTableId(unpublicPartyOrgInfo.getId());
			//更新换届的附件信息
			attachmentCommonInfoMapper.updateByPrimaryKeySelective(attachmentCommonInfo);
		}
		//更新主表中的附件信息
		AttachmentCommonInfo aCInfo = new AttachmentCommonInfo();
		if(unpublicPartyOrgInfo.getPartyOrgAttachment() != null && unpublicPartyOrgInfo.getPartyOrgAttachment().length() > 0){
			aCInfo.setId(Integer.valueOf(unpublicPartyOrgInfo.getPartyOrgAttachment()));
			aCInfo.setMainTableId(unpublicPartyOrgInfo.getId());
			attachmentCommonInfoMapper.updateByPrimaryKeySelective(aCInfo);
		}
		//批量插入换届信息
		if(pociList.size() > 0){
			unpublicPartyOrgChangeInfoMapper.insertList(pociList);
		}
		for(int j = 0; j < orgInfoArray.length; j++){
			UnpublicOrgInfo unpublicOrgInfo = new UnpublicOrgInfo();
			unpublicOrgInfo.setId(Integer.valueOf(orgInfoArray[j]));
			unpublicOrgInfo.setUnpublicPartyOrgId(unpublicPartyOrgInfo.getId()+"");
			unpublicOrgInfoMapper.updateByPrimaryKeySelective(unpublicOrgInfo);
		}
		//批量插入党副信息
		for(DeputySecretaryInfo dsInfo :dsiList){
			dsInfo.setType("2");
			dsInfo.setPartyOrgId(unpublicPartyOrgInfo.getId()+"");
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
	public void updateOrg(UnpublicPartyOrgInfo unpublicPartyOrgInfo, List<UnpublicPartyOrgChangeInfo> pociList, 
			List<DeputySecretaryInfo> dsiList)throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		if(unpublicPartyOrgInfo.getPartyOrgTimeTxt() != null && unpublicPartyOrgInfo.getPartyOrgTimeTxt().length()>0){
			unpublicPartyOrgInfo.setPartyOrgTime(sdf.parse(unpublicPartyOrgInfo.getPartyOrgTimeTxt()));
		}
		if(unpublicPartyOrgInfo.getSecretaryBirthdayTxt() != null && unpublicPartyOrgInfo.getSecretaryBirthdayTxt().length()>0){
			unpublicPartyOrgInfo.setSecretaryBirthday(sdf.parse(unpublicPartyOrgInfo.getSecretaryBirthdayTxt()));
		}
		unpublicPartyOrgInfoMapper.updateByPrimaryKeySelective(unpublicPartyOrgInfo);
		for(UnpublicPartyOrgChangeInfo poci : pociList){
			//把主表中的id赋值到换届和附件中
			AttachmentCommonInfo attachmentCommonInfo = new AttachmentCommonInfo();
			poci.setSocialPartyOrgId(unpublicPartyOrgInfo.getId());
			attachmentCommonInfo.setId(poci.getChangeAttachmentId());
			attachmentCommonInfo.setMainTableId(unpublicPartyOrgInfo.getId());
			//更新换届的附件信息
			attachmentCommonInfoMapper.updateByPrimaryKeySelective(attachmentCommonInfo);
		}
		//更新主表中的附件信息
		AttachmentCommonInfo aCInfo = new AttachmentCommonInfo();
		if(unpublicPartyOrgInfo.getPartyOrgAttachment() != null && unpublicPartyOrgInfo.getPartyOrgAttachment().length() > 0){
			aCInfo.setId(Integer.valueOf(unpublicPartyOrgInfo.getPartyOrgAttachment()));
			aCInfo.setMainTableId(unpublicPartyOrgInfo.getId());
			attachmentCommonInfoMapper.updateByPrimaryKeySelective(aCInfo);
		}
		
		unpublicPartyOrgChangeInfoMapper.deleteChangeInfo(unpublicPartyOrgInfo.getId()+"");
		//批量插入换届信息
		if(pociList.size() > 0){
			unpublicPartyOrgChangeInfoMapper.insertList(pociList);
		}
		
		//批量插入党副信息
		deputySecretaryInfoMapper.deleteDeputysecretaryInfo(unpublicPartyOrgInfo.getId()+"");
		for(DeputySecretaryInfo dsInfo :dsiList){
			dsInfo.setType("2");
			dsInfo.setPartyOrgId(unpublicPartyOrgInfo.getId()+"");
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
	public void cancelOrg(CancelReasonInfo reason, UnpublicPartyOrgInfo info) {
		cancelReasonInfoMapper.insertSelective(reason);
		unpublicPartyOrgInfoMapper.updateByPrimaryKeySelective(info);
	}
	
	/**
	 * 取消撤销党组织
	 * @param id
	 * @param remarks
	 */
	@Transactional
	public void nocancelOrg(UnpublicPartyOrgInfo main) {
		unpublicPartyOrgInfoMapper.updateByPrimaryKeySelective(main);
		Example example = new Example(CancelReasonInfo.class);
		example.setOrderByClause("createTime desc");
		example.createCriteria().andEqualTo("partyOrgId", main.getId()).andEqualTo("type", '2').andEqualTo("status", 1);
		List<CancelReasonInfo> unpublicOrgCancelRecords = cancelReasonInfoMapper.selectByExample(example);
		if(unpublicOrgCancelRecords.size() > 0){
			CancelReasonInfo uocRecord = unpublicOrgCancelRecords.get(0);
			uocRecord.setStatus("0");
			cancelReasonInfoMapper.updateByPrimaryKeySelective(uocRecord);
		}
	}

}
