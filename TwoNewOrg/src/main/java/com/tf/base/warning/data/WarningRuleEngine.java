package com.tf.base.warning.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tf.base.common.constants.CommonConstants.WARNING_SWITCH;
import com.tf.base.warning.domain.WarningRuleInfo;
import com.tf.base.warning.persistence.WarningRuleMapper;

/**
 * @Title WarningRuleEngine.java
 * @Description 预警规则引擎
 * @author wanghw
 * @date 2017年11月22日 上午11:55:47
 * @version V1.0
 */
@Component
public class WarningRuleEngine {

	@Autowired
	private WarningRuleMapper warningRuleMapper;
	
//	@Autowired
//	private RedisDao redisDao;

	private static Map<String, ArrayList<WarningRuleInfo>> rules;

	static {
		initData();
	}

	/**
	 * 预警规则缓存 <br>
	 * 初始化预警规则 01.党组织建立预警 02.党组织换届预警 03.党员情况变更预警 04.党组织变动预警 05.手工发布
	 */
	private static void initData() {
		rules = new ConcurrentHashMap<String, ArrayList<WarningRuleInfo>>() {

			private static final long serialVersionUID = 1L;

			{
				put("01", new ArrayList<WarningRuleInfo>());
				put("02", new ArrayList<WarningRuleInfo>());
				put("03", new ArrayList<WarningRuleInfo>());
				put("04", new ArrayList<WarningRuleInfo>());
				put("05", new ArrayList<WarningRuleInfo>());
			}
		};
	}

	/**
	 * 初始化规则引擎<br>
	 * 从DB 查询规则列表存放到hash中缓存
	 */
	@PostConstruct
	public void initEngine() {
		WarningRuleInfo record = new WarningRuleInfo();
		// 仅加载规则配置开关开放的规则
		record.setWarningSwitch(WARNING_SWITCH.ON.toString());
		List<WarningRuleInfo> select = warningRuleMapper.select(record);
		Set<Entry<String, ArrayList<WarningRuleInfo>>> entrySet = rules.entrySet();
		for (Entry<String, ArrayList<WarningRuleInfo>> entry : entrySet) {
			ArrayList<WarningRuleInfo> values = new ArrayList<WarningRuleInfo>();
			for (WarningRuleInfo info : select) {
				if (info.getWarningType().equals(entry.getKey())) {
					values.add(info);
				}
			}
			rules.put(entry.getKey(), values);
		}
		//redisDao.set("rules", rules);
	};

	/**
	 * 刷新引擎规则
	 */
	public void refreshEngineRule() {
		if (!rules.isEmpty()) {
			rules.clear();
		}
		initData();
		initEngine();
	}

	/**
	 * 获取缓存中的预警规则
	 * 
	 * @return 预警规则hash
	 */
	public Map<String, ArrayList<WarningRuleInfo>> getRules() {
		return rules;
	};

}
