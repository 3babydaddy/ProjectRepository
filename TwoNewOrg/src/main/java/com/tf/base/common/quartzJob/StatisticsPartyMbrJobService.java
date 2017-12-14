package com.tf.base.common.quartzJob;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.tf.base.socialorg.domain.SocialOrgPmbrCount;
import com.tf.base.socialorg.persistence.SocialOrgPmbrCountMapper;
import com.tf.base.unpublic.domain.UnpublicOrgPmbrCount;
import com.tf.base.unpublic.persistence.UnpublicOrgPmbrCountMapper;

import tk.mybatis.mapper.entity.Example;

@Service
public class StatisticsPartyMbrJobService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UnpublicOrgPmbrCountMapper unpublicOrgPmbrCountMapper;
	
	@Autowired
	private SocialOrgPmbrCountMapper socialOrgPmbrCountMapper;
	
	@SuppressWarnings("rawtypes")
	public void excute() {
		
			
		logger.debug("==============================>:statistics partyMbr start");
		
		List<Map> unpublicOrgList = unpublicOrgPmbrCountMapper.selectChangeOrgList();
		
		if(!CollectionUtils.isEmpty(unpublicOrgList)){
			for (Map map : unpublicOrgList) {
				String orgId = (String) map.get("unpublic_org_info_id");
				String peopleNum = (String) map.get("people");
				
				Example example = new Example(UnpublicOrgPmbrCount.class);
				example.createCriteria().andEqualTo("unpublicOrgInfoId", orgId);
				
				UnpublicOrgPmbrCount count = new UnpublicOrgPmbrCount();
				count.setPartymbrUnderThirtyfiveNum(Integer.parseInt(peopleNum));
				unpublicOrgPmbrCountMapper.updateByExampleSelective(count, example);
			}
		}
		
		
		List<Map> socialOrgList = socialOrgPmbrCountMapper.selectChangeOrgList();
		if(!CollectionUtils.isEmpty(socialOrgList)){
			for (Map map : unpublicOrgList) {
				String orgId = (String) map.get("social_org_info_id");
				String peopleNum = (String) map.get("people");
				
				Example example = new Example(SocialOrgPmbrCount.class);
				example.createCriteria().andEqualTo("socialOrgInfoId", orgId);
				
				SocialOrgPmbrCount count = new SocialOrgPmbrCount();
				count.setPartymbrUnderThirtyfiveNum(Integer.parseInt(peopleNum));
				socialOrgPmbrCountMapper.updateByExampleSelective(count, example);
			}
		}
		
		
		logger.debug("==============================>:end statistics partyMbr");
	}
}
