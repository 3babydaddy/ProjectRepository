package com.tf.base.warning.service;

import com.tf.base.warning.domain.WarningRuleInfo;

/**
 * @Title: RuleBase.java
 * @Description: 定义一个核心数据的对象的接口
 * @author wanghw
 * @date 2017年11月22日 上午9:50:13
 * @version V1.0
 */

public interface WarningService {

	/**
	 * 发布新的预警
	 * 
	 * @param
	 */
	void add(WarningRuleInfo warn);

	/**
	 * 开启预警
	 * 
	 * @param warn
	 */
	void on(int warnId);

	/**
	 * 
	 * 关闭预警
	 * 
	 * @param warn
	 */
	void off(int warnId);

	/**
	 * 更新预警
	 * 
	 * @param warn
	 */
	void update(WarningRuleInfo warn);

}
