package com.tf.base.common.schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tf.base.cover.domain.CoverPartyOrgBuilding;
import com.tf.base.cover.domain.CoverPartyOrgInfo;
import com.tf.base.cover.persistence.CoverPartyOrgBuildingMapper;
import com.tf.base.cover.persistence.CoverPartyOrgInfoMapper;
import com.tf.base.socialorg.domain.SocialPartyOrgBuilding;
import com.tf.base.socialorg.domain.SocialPartyOrgInfo;
import com.tf.base.socialorg.persistence.SocialPartyOrgBuildingMapper;
import com.tf.base.socialorg.persistence.SocialPartyOrgInfoMapper;
import com.tf.base.unpublic.domain.UnpublicPartyOrgInfo;
import com.tf.base.unpublic.domain.UnpulicPartyOrgBuilding;
import com.tf.base.unpublic.persistence.UnpublicPartyOrgInfoMapper;
import com.tf.base.unpublic.persistence.UnpulicPartyOrgBuildingMapper;

import tk.mybatis.mapper.entity.Example;

/**
 * @Title PartyConstrutionSchedule.java
 * @Description 新增党建<br>
 * @author tianzhen
 * @date 2017年12月14日
 * @version V1.0
 */
@Component
public class PartyConstrutionSchedule {

	@Autowired
	private UnpublicPartyOrgInfoMapper unpublicPartyOrgInfoMapper;
	@Autowired
	private SocialPartyOrgInfoMapper socialPartyOrgInfoMapper;
	@Autowired
	private CoverPartyOrgInfoMapper coverPartyOrgInfoMapper;
	@Autowired
	private UnpulicPartyOrgBuildingMapper unpulicPartyOrgBuildingMapper;
	@Autowired
	private SocialPartyOrgBuildingMapper socialPartyOrgBuildingMapper;
	@Autowired
	private CoverPartyOrgBuildingMapper coverPartyOrgBuildingMapper;

	private static final Logger logger = LoggerFactory.getLogger(PartyConstrutionSchedule.class);

	/**
	 * 年度党建创建定时任务<br>
	 * 系统为审批通过的党组织创建年度党建<br>
	 * 定时任务周期：每年一月一号
	 */
	@Scheduled(cron = "0 0 0 1 1 ?")
	public void execute() {
		// 增加定时任务异常处理
		try {
			List<UnpulicPartyOrgBuilding> unpulicPartyOrgBuildings = new ArrayList<>();
			List<SocialPartyOrgBuilding> socialPartyOrgBuildings = new ArrayList<>();
			List<CoverPartyOrgBuilding> coverPartyOrgBuildings = new ArrayList<>();
			Calendar cal = Calendar.getInstance();
			String year = cal.get(Calendar.YEAR) + "";
			logger.info("*****************PartyConstrution schedule [START] *****************");
			Example exampleByUn = new Example(UnpublicPartyOrgInfo.class);
			exampleByUn.createCriteria().andEqualTo("status", 2);
			List<UnpublicPartyOrgInfo> unpublicPartyOrgInfos = unpublicPartyOrgInfoMapper.selectByExample(exampleByUn);
			if (unpublicPartyOrgInfos.size() > 0) {
				for (UnpublicPartyOrgInfo info : unpublicPartyOrgInfos) {
					UnpulicPartyOrgBuilding upob = new UnpulicPartyOrgBuilding();
					upob.setUnpublicPartyOrgId(info.getId());
					upob.setYear(year);
					upob.setStatus("1");
					unpulicPartyOrgBuildings.add(upob);
				}
			}
			if (unpulicPartyOrgBuildings.size() > 0) {
				unpulicPartyOrgBuildingMapper.insertList(unpulicPartyOrgBuildings);
				logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>[unpublic party constrution] [total insert  ={}]", unpulicPartyOrgBuildings.size());
			}
			Example exampleBySo = new Example(SocialPartyOrgInfo.class);
			exampleBySo.createCriteria().andEqualTo("status", 2);
			List<SocialPartyOrgInfo> socialPartyOrgInfos = socialPartyOrgInfoMapper.selectByExample(exampleBySo);
			if (socialPartyOrgInfos.size() > 0) {
				for (SocialPartyOrgInfo info : socialPartyOrgInfos) {
					SocialPartyOrgBuilding spob = new SocialPartyOrgBuilding();
					spob.setSocialPartyOrgId(info.getId());
					spob.setYear(year);
					spob.setStatus("1");
					socialPartyOrgBuildings.add(spob);
				}
			}
			if (socialPartyOrgBuildings.size() > 0) {
				socialPartyOrgBuildingMapper.insertList(socialPartyOrgBuildings);
				logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>[social party constrution] [total insert  ={}]", socialPartyOrgBuildings.size());
			}
			Example exampleByCo = new Example(CoverPartyOrgInfo.class);
			exampleByCo.createCriteria().andEqualTo("status", 2);
			List<CoverPartyOrgInfo> coverPartyOrgInfos = coverPartyOrgInfoMapper.selectByExample(exampleByCo);
			if (coverPartyOrgInfos.size() > 0) {
				for (CoverPartyOrgInfo info : coverPartyOrgInfos) {
					CoverPartyOrgBuilding cpob = new CoverPartyOrgBuilding();
					cpob.setCoverPartyOrgId(info.getId());
					cpob.setYear(year);
					cpob.setStatus("1");
					coverPartyOrgBuildings.add(cpob);
				}
			}
			if (coverPartyOrgBuildings.size() > 0) {
				coverPartyOrgBuildingMapper.insertList(coverPartyOrgBuildings);
				logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>[cover party constrution] [total insert  ={}]", coverPartyOrgBuildings.size());
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("PartyConstrution schedule Exception ", e);
		}
		logger.info("*****************PartyConstrution schedule [END] *****************");
	}
}
