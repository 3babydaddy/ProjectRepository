package com.tf.base.warning.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tf.base.warning.data.WarningRuleEngine;
import com.tf.base.warning.domain.WarningRuleInfo;

@Component
public class ListenRules implements WarningListener {
	
	@Autowired
	private WarningRuleEngine engine;

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void add(WarningRuleInfo warn) {
		// TODO Auto-generated method stub
		log.info("[ add warn] [info ={}]", warn.toString());
		engine.refreshEngineRule();
	}

	@Override
	public void on(int warnId) {
		// TODO Auto-generated method stub
		log.info("[ on warn] [info ={}]", warnId);
		engine.refreshEngineRule();
	}

	@Override
	public void off(int warnId) {
		// TODO Auto-generated method stub
		log.info("[ off warn] [info ={}]", warnId);
		engine.refreshEngineRule();
	}

	@Override
	public void update(WarningRuleInfo warn) {
		// TODO Auto-generated method stub
		log.info("[ update warn] [info ={}]", warn.toString());
		engine.refreshEngineRule();
	}

}
