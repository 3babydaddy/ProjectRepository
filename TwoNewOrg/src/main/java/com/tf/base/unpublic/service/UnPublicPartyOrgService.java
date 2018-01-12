package com.tf.base.unpublic.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tf.base.common.service.BaseService;
import com.tf.base.unpublic.domain.AttachmentCommonInfo;
import com.tf.base.unpublic.domain.CancelReasonInfo;
import com.tf.base.unpublic.domain.DeputySecretaryInfo;
import com.tf.base.unpublic.domain.LowerPartyOrg;
import com.tf.base.unpublic.domain.PartyInstructorInfo;
import com.tf.base.unpublic.domain.UnpublicOrgInfo;
import com.tf.base.unpublic.domain.UnpublicPartyOrgChangeInfo;
import com.tf.base.unpublic.domain.UnpublicPartyOrgInfo;
import com.tf.base.unpublic.domain.UnpulicPartyOrgBuilding;
import com.tf.base.unpublic.persistence.AttachmentCommonInfoMapper;
import com.tf.base.unpublic.persistence.CancelReasonInfoMapper;
import com.tf.base.unpublic.persistence.DeputySecretaryInfoMapper;
import com.tf.base.unpublic.persistence.LowerPartyOrgMapper;
import com.tf.base.unpublic.persistence.PartyInstructorInfoMapper;
import com.tf.base.unpublic.persistence.UnpublicOrgInfoMapper;
import com.tf.base.unpublic.persistence.UnpublicPartyOrgChangeInfoMapper;
import com.tf.base.unpublic.persistence.UnpublicPartyOrgInfoMapper;
import com.tf.base.unpublic.persistence.UnpulicPartyOrgBuildingMapper;

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
	@Autowired
	private UnpulicPartyOrgBuildingMapper unpulicPartyOrgBuildingMapper;
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
	public void addOrg(UnpublicPartyOrgInfo unpublicPartyOrgInfo, List<UnpublicPartyOrgChangeInfo> pociList, 
			List<DeputySecretaryInfo> dsiList, List<PartyInstructorInfo> instructList, List<LowerPartyOrg> lowerPartyOrgList, String[] orgInfoArray)throws Exception {
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
			AttachmentCommonInfo attachmentCommonInfo = attachmentCommonInfoMapper.selectByPrimaryKey(poci.getChangeAttachmentId());
			poci.setSocialPartyOrgId(unpublicPartyOrgInfo.getId());
			if(attachmentCommonInfo != null){
				attachmentCommonInfo.setMainTableId(unpublicPartyOrgInfo.getId());
				//更新换届的附件信息
				attachmentCommonInfoMapper.updateByPrimaryKeySelective(attachmentCommonInfo);
			}
		}
		//更新主表中的附件信息
		if(unpublicPartyOrgInfo.getPartyOrgAttachment() != null && unpublicPartyOrgInfo.getPartyOrgAttachment().length() > 0){
			AttachmentCommonInfo aCInfo = attachmentCommonInfoMapper.selectByPrimaryKey(Integer.valueOf(unpublicPartyOrgInfo.getPartyOrgAttachment()));
			if(aCInfo != null){
				aCInfo.setMainTableId(unpublicPartyOrgInfo.getId());
				attachmentCommonInfoMapper.updateByPrimaryKeySelective(aCInfo);
			}
		}
		//批量插入换届信息
		if(pociList.size() > 0){
			unpublicPartyOrgChangeInfoMapper.insertList(pociList);
		}
		for(int j = 0; j < orgInfoArray.length; j++){
			UnpublicOrgInfo unpublicOrgInfo = unpublicOrgInfoMapper.selectByPrimaryKey(Integer.valueOf(orgInfoArray[j]));
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
		//批量插入指导员信息
		for(PartyInstructorInfo pInfo :instructList){
			pInfo.setType("2");
			pInfo.setPartyOrgId(unpublicPartyOrgInfo.getId());
		}
		if(instructList.size() > 0){
			partyInstructorInfoMapper.insertList(instructList);
		}
		
		//批量插入下级党组织信息
		for(LowerPartyOrg lInfo : lowerPartyOrgList){
			lInfo.setType("2");
			lInfo.setPartyOrgId(unpublicPartyOrgInfo.getId());
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
	public void updateOrg(UnpublicPartyOrgInfo unpublicPartyOrgInfo, List<UnpublicPartyOrgChangeInfo> pociList, 
			List<DeputySecretaryInfo> dsiList, List<PartyInstructorInfo> instructList, List<LowerPartyOrg> lowerPartyOrgList, String[] orgInfoArray)throws Exception {
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
			AttachmentCommonInfo attachmentCommonInfo = attachmentCommonInfoMapper.selectByPrimaryKey(poci.getChangeAttachmentId());
			poci.setSocialPartyOrgId(unpublicPartyOrgInfo.getId());
			if(attachmentCommonInfo != null){
				attachmentCommonInfo.setMainTableId(unpublicPartyOrgInfo.getId());
				//更新换届的附件信息
				attachmentCommonInfoMapper.updateByPrimaryKeySelective(attachmentCommonInfo);
			}
		}
		//更新主表中的附件信息
		if(unpublicPartyOrgInfo.getPartyOrgAttachment() != null && unpublicPartyOrgInfo.getPartyOrgAttachment().length() > 0){
			AttachmentCommonInfo aCInfo = attachmentCommonInfoMapper.selectByPrimaryKey(Integer.valueOf(unpublicPartyOrgInfo.getPartyOrgAttachment()));
			if(aCInfo != null){
				aCInfo.setMainTableId(unpublicPartyOrgInfo.getId());
				attachmentCommonInfoMapper.updateByPrimaryKeySelective(aCInfo);
			}
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
		
		//批量插入指导员信息
		partyInstructorInfoMapper.deleteInstructInfo(unpublicPartyOrgInfo.getId()+"");
		for(PartyInstructorInfo pInfo :instructList){
			pInfo.setType("2");
			pInfo.setPartyOrgId(unpublicPartyOrgInfo.getId());
		}
		if(instructList.size() > 0){
			partyInstructorInfoMapper.insertList(instructList);
		}
		
		//批量插入下级党组织信息
		lowerPartyOrgMapper.deleteLowerInfo(unpublicPartyOrgInfo.getId()+"");
		for(LowerPartyOrg lInfo : lowerPartyOrgList){
			lInfo.setType("2");
			lInfo.setPartyOrgId(unpublicPartyOrgInfo.getId());
		}
		if(lowerPartyOrgList.size() > 0){
			lowerPartyOrgMapper.insertList(lowerPartyOrgList);
		}
		//更新组织信息
		unpublicOrgInfoMapper.updateOrgPartyOrgId(unpublicPartyOrgInfo.getId()+"");
		for(int j = 0; j < orgInfoArray.length; j++){
			UnpublicOrgInfo unpublicOrgInfo = unpublicOrgInfoMapper.selectByPrimaryKey(Integer.valueOf(orgInfoArray[j]));
			unpublicOrgInfo.setUnpublicPartyOrgId(unpublicPartyOrgInfo.getId()+"");
			unpublicOrgInfoMapper.updateByPrimaryKeySelective(unpublicOrgInfo);
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
	
	/**
	 * 撤销党组织
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
				UnpublicPartyOrgInfo info = new UnpublicPartyOrgInfo();
				info.setId(Integer.parseInt(partyOrgArray[i]));
				info.setStatus(status);
				unpublicPartyOrgInfoMapper.updateByPrimaryKeySelective(info);
				
				Example example = new Example(UnpulicPartyOrgBuilding.class);
				example.setOrderByClause("createTime desc");
				example.createCriteria().andEqualTo("unpublicPartyOrgId", Integer.parseInt(partyOrgArray[i])).andEqualTo("year", year);
				List<UnpulicPartyOrgBuilding> dataList = unpulicPartyOrgBuildingMapper.selectByExample(example);
				if(dataList.size() == 0){
					UnpulicPartyOrgBuilding unpulicPartyOrgBuilding = new UnpulicPartyOrgBuilding();
					unpulicPartyOrgBuilding.setUnpublicPartyOrgId(Integer.parseInt(partyOrgArray[i]));
					unpulicPartyOrgBuilding.setYear(year);
					unpulicPartyOrgBuilding.setStatus("1");
					unpulicPartyOrgBuildingMapper.insert(unpulicPartyOrgBuilding);
				}
			}
		}
	}

}
