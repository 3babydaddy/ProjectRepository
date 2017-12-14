package com.tf.base.socialorg.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tf.base.common.service.BaseService;
import com.tf.base.socialorg.domain.SocialPartyOrgBuilding;
import com.tf.base.socialorg.persistence.SocialPartyOrgBuildingMapper;
import com.tf.base.unpublic.domain.AttachmentCommonInfo;
import com.tf.base.unpublic.domain.CancelReasonInfo;
import com.tf.base.unpublic.persistence.AttachmentCommonInfoMapper;
import com.tf.base.unpublic.persistence.CancelReasonInfoMapper;

import tk.mybatis.mapper.entity.Example;

@Service
public class PartyConstructionService {
	
	@Autowired
	private BaseService baseService;
	@Autowired
	private CancelReasonInfoMapper cancelReasonInfoMapper;
	@Autowired
	private SocialPartyOrgBuildingMapper socialPartyOrgBuildingMapper;
	@Autowired
	private AttachmentCommonInfoMapper attachmentCommonInfoMapper;
	
	/**
	 * 新增党建信息
	 * @param main
	 * @param sponsor
	 * @param league
	 * @param pmbrCount
	 * @param params
	 */
	@Transactional
	public void addOrg(SocialPartyOrgBuilding main)throws Exception {
		//SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Calendar cal = Calendar.getInstance(); 
		main.setCreater(baseService.getUserName());
		main.setCreateTime(new Date());
		main.setYear(cal.get(Calendar.YEAR)+"");
		main.setSocialPartyOrgId(Integer.parseInt(main.getPartyOrgId()));
		socialPartyOrgBuildingMapper.insertSelective(main);
		
	}

	/**
	 * 修改党建信息
	 * @param main
	 * @param sponsor
	 * @param league
	 * @param pmbrCount
	 * @param params
	 */
	@Transactional
	public void updateOrg(SocialPartyOrgBuilding main)throws Exception {
		socialPartyOrgBuildingMapper.updateByPrimaryKeySelective(main);
	}

	/**
	 * 撤销党组织
	 * @param id
	 * @param remarks
	 */
	@Transactional
	public void cancelOrg(CancelReasonInfo reason, SocialPartyOrgBuilding info) {
		cancelReasonInfoMapper.insertSelective(reason);
		socialPartyOrgBuildingMapper.updateByPrimaryKeySelective(info);
	}
	
	/**
	 * 取消撤销党组织
	 * @param id
	 * @param remarks
	 */
	@Transactional
	public void nocancel(SocialPartyOrgBuilding main) {
		socialPartyOrgBuildingMapper.updateByPrimaryKeySelective(main);
		Example example = new Example(CancelReasonInfo.class);
		example.setOrderByClause("createTime desc");
		example.createCriteria().andEqualTo("partyOrgId", main.getId()).andEqualTo("type", '1').andEqualTo("status", 1);
		List<CancelReasonInfo> unpublicOrgCancelRecords = cancelReasonInfoMapper.selectByExample(example);
		if(unpublicOrgCancelRecords.size() > 0){
			CancelReasonInfo uocRecord = unpublicOrgCancelRecords.get(0);
			uocRecord.setStatus("0");
			cancelReasonInfoMapper.updateByPrimaryKeySelective(uocRecord);
		}
		Example example1 = new Example(AttachmentCommonInfo.class);
		example1.createCriteria().andEqualTo("mainTableId", main.getId()).andEqualTo("modular", 1).andEqualTo("type", 2).andEqualTo("action", 1).andEqualTo("status", 1);
		List<AttachmentCommonInfo> dataFiles = attachmentCommonInfoMapper.selectByExample(example1);
		if(dataFiles.size() > 0){
			for(AttachmentCommonInfo acInfo : dataFiles){
				acInfo.setStatus(0);
				attachmentCommonInfoMapper.updateByPrimaryKeySelective(acInfo);
			}
		}
	}

}
