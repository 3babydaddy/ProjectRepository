package com.tf.base.unpublic.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tf.base.common.service.BaseService;
import com.tf.base.unpublic.domain.AttachmentCommonInfo;
import com.tf.base.unpublic.domain.CancelReasonInfo;
import com.tf.base.unpublic.domain.UnpulicPartyOrgBuilding;
import com.tf.base.unpublic.persistence.AttachmentCommonInfoMapper;
import com.tf.base.unpublic.persistence.CancelReasonInfoMapper;
import com.tf.base.unpublic.persistence.UnpulicPartyOrgBuildingMapper;

import tk.mybatis.mapper.entity.Example;

@Service
public class UnPublicPartyConstructionService {
	
	@Autowired
	private BaseService baseService;
	@Autowired
	private AttachmentCommonInfoMapper attachmentCommonInfoMapper;
	@Autowired
	private CancelReasonInfoMapper cancelReasonInfoMapper;
	@Autowired
	private UnpulicPartyOrgBuildingMapper unpulicPartyOrgBuildingMapper;
	
	/**
	 * 新增党建信息
	 * @param main
	 * 
	 */
	@Transactional
	public void addOrg(UnpulicPartyOrgBuilding main)throws Exception {
		//SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Calendar cal = Calendar.getInstance(); 
		main.setCreater(baseService.getUserName());
		main.setCreateTime(new Date());
		main.setYear(cal.get(Calendar.YEAR)+"");
		unpulicPartyOrgBuildingMapper.insertSelective(main);
		AttachmentCommonInfo attachmentCommonInfo = attachmentCommonInfoMapper.selectByPrimaryKey(main.getRectifyAtachementId());
		if(attachmentCommonInfo != null){
			attachmentCommonInfo.setMainTableId(main.getId());
			attachmentCommonInfoMapper.updateByPrimaryKeySelective(attachmentCommonInfo);
		}
	}

	/**
	 * 修改党建信息
	 * @param main
	 * 
	 */
	@Transactional
	public void updateOrg(UnpulicPartyOrgBuilding main)throws Exception {
		main.setCreater(baseService.getUserName());
		main.setCreateTime(new Date());
		unpulicPartyOrgBuildingMapper.updateByPrimaryKeySelective(main);
		AttachmentCommonInfo attachmentCommonInfo = attachmentCommonInfoMapper.selectByPrimaryKey(main.getRectifyAtachementId());
		if(attachmentCommonInfo != null){
			attachmentCommonInfo.setMainTableId(main.getId());
			attachmentCommonInfoMapper.updateByPrimaryKeySelective(attachmentCommonInfo);
		}
	}

	/**
	 * 撤销党组织
	 * @param reason
	 * @param info
	 */
	@Transactional
	public void cancelOrg(CancelReasonInfo reason, UnpulicPartyOrgBuilding info) {
		cancelReasonInfoMapper.insertSelective(reason);
		unpulicPartyOrgBuildingMapper.updateByPrimaryKeySelective(info);
	}
	
	/**
	 * 取消撤销党组织
	 * @param main
	 * 
	 */
	@Transactional
	public void nocancel(UnpulicPartyOrgBuilding main) {
		unpulicPartyOrgBuildingMapper.updateByPrimaryKeySelective(main);
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
