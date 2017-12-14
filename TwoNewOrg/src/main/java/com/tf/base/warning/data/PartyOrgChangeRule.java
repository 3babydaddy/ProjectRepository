package com.tf.base.warning.data;

import java.util.List;

import com.tf.base.warning.domain.WarningDetail;
import com.tf.base.warning.domain.WarningRuleInfo;

/**   
* @Title  PartyOrgChangeRule.java 
* @Description  04-党组织变动预警 
* @author wanghw
* @date 2017年11月24日 下午3:54:01 
* @version V1.0   
*/
public class PartyOrgChangeRule implements RuleHandler{

	@Override
	public List<WarningDetail> match(List<WarningRuleInfo> rule) {
		for (WarningRuleInfo warningRuleInfo : rule) {

//			System.out
//					.println(this.getClass().getName() + "match || warningRuleInfo ====" + warningRuleInfo.toString());
		}
		// TODO Auto-generated method stub
		return null;
	}

	

}
