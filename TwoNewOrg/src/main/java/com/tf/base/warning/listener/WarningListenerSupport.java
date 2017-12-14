package com.tf.base.warning.listener;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tf.base.warning.domain.WarningRuleInfo;

/**
 * @Title WarningListenerSupport.java
 * @Description 事件监听管理 <br>
 *              （1）当对象发生某个动作，都要告诉监听管理器，监听管理器做相应的处理 <br>
 *              （2）监听管理者，包括监听器的行为都是依据的动作为定。
 * @author wanghw
 * @date 2017年11月22日 上午10:43:39
 * @version V1.0
 */
@Component
public class WarningListenerSupport {

	@Autowired
	private List<WarningListener> listeners;

	public void addAction(WarningRuleInfo warm) {
		for (WarningListener listener : listeners) {
			listener.add(warm);
		}
	}

	public void onAction(int warnId) {
		for (WarningListener listener : listeners) {
			listener.on(warnId);
		}
	}

	public void offAction(int warnId) {
		for (WarningListener listener : listeners) {
			listener.off(warnId);
		}
	}

	public void updateAction(WarningRuleInfo warm) {
		for (WarningListener listener : listeners) {
			listener.update(warm);
		}
	}


}
