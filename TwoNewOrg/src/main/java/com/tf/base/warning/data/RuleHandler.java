package com.tf.base.warning.data;

import java.util.List;

import com.tf.base.warning.domain.WarningDetail;
import com.tf.base.warning.domain.WarningRuleInfo;

public interface RuleHandler {
	
	List<WarningDetail> match(List<WarningRuleInfo> rule);

}
