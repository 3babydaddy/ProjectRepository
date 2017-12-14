package com.tf.base.warning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tf.base.warning.domain.WarningRuleInfo;
import com.tf.base.warning.listener.WarningListenerSupport;

@Service
public class WarningServiceImpl implements WarningService {

	/**
	 * 通知 监听管理器 各种操作
	 */
	@Autowired
	private WarningListenerSupport support;

	@Override
	public void add(WarningRuleInfo warm) {
		support.addAction(warm);
		// TODO：业务功能实现
	}

	@Override
	public void on(int warnId) {
		support.onAction(warnId);
		// TODO：业务功能实现
	}

	@Override
	public void off(int warnId) {
		support.offAction(warnId);
		// TODO：业务功能实现
	}

	@Override
	public void update(WarningRuleInfo warm) {
		support.updateAction(warm);
		// TODO：业务功能实现
	}

}
