package com.tf.base.warning.listener;

import com.tf.base.warning.domain.WarningRuleInfo;

/**
 * @Title WarningListener.java
 * @Description （1）监听器接口，将来不同的组件监听warning的变动，都可以声明一个监听器，实现该接口<br>
 *              （2）将监听器注册到warningService的监听管理者内部
 * @author wanghw
 * @date 2017年11月22日 上午10:43:05
 * @version V1.0
 */
public interface WarningListener {

	void add(WarningRuleInfo warm);

	void on(int warnId);

	void off(int warnId);

	void update(WarningRuleInfo warm);

}
