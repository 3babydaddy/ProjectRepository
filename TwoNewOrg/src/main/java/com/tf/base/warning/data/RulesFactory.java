package com.tf.base.warning.data;

public class RulesFactory {

	public static RuleHandler createRule(String type) {
		if (type.equals("01")) {
			return new PartyOrgBuildRule();
		} else if (type.equals("02")) {
			return new PartyOrgChangeSessionRule();
		} else if (type.equals("03")) {
			return new PartyMemberChangeRule();
		} else if (type.equals("04")) {
			return new PartyOrgChangeRule();
		} else {
			return new CustomReleaseRule();
		}
	}

}
