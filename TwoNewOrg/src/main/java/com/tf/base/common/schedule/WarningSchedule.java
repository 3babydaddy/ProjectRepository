package com.tf.base.common.schedule;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tf.base.warning.data.RuleHandler;
import com.tf.base.warning.data.RulesFactory;
import com.tf.base.warning.data.WarningRuleEngine;
import com.tf.base.warning.domain.WarningRuleInfo;

/**
 * @Title WarningSchedule.java
 * @Description 预警定时任务<br>
 *              1.获取预警规则到缓存,如果规则修改更新缓存<br>
 *              2.根据预警规则配置获取触发预警阈值的机构<br>
 *              3.将触发预警的机构对应的相关人员数据存放到数据库保存<br>
 * @author wanghw
 * @date 2017年11月22日 上午11:36:22
 * @version V1.0
 */
@Component
public class WarningSchedule {
	@Autowired
	private WarningRuleEngine engine;

	@Scheduled(cron = "0/5 * * * * ?")
	public void execute() {
		Map<String, ArrayList<WarningRuleInfo>> rules = engine.getRules();

		// TODO: 根据规则获取触发规则的 机构列表
		Set<Entry<String, ArrayList<WarningRuleInfo>>> entrySet = rules.entrySet();
		for (Entry<String, ArrayList<WarningRuleInfo>> entry : entrySet) {
			String key = entry.getKey();
			ArrayList<WarningRuleInfo> rule = entry.getValue();
			RuleHandler handler = RulesFactory.createRule(key);
			handler.match(rule);
		}

		// TODO: 根据机构列表 获取相关类型的责任人

		// TODO： 将获取到的数据集合存放到redis

		// TODO: 根据redis数据 调取外部接口 发送短信
	}
}
