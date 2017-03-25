package com.yn.go.common;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class Filters {

	private  String groupOp;
	private  List<Rule> rules;
	
	public Filters() {
		super();
	}

	public Filters(String groupOp, List<Rule> rules) {
		super();
		this.groupOp = groupOp;
		this.rules = rules;
	}

	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}

	public void setRules(List<Rule> rules) {
		List<Rule> ruleTmpList=Lists.transform(rules, new Function<Rule, Rule>() {

			@Override
			public Rule apply(Rule input) {
				if(input ==null)
					return null;
				if("myac".equalsIgnoreCase(input.getField())||Strings.isNullOrEmpty(input.getData().trim())){
					return null;
				}
				return input;
			}
		});
		ruleTmpList.remove(null);
		this.rules =ruleTmpList;
	}

	public String getGroupOp() {
		return groupOp;
	}

	public List<Rule> getRules() {
		return rules;
	}

	@Override
	public String toString() {
		return "Filters [groupOp=" + groupOp + ", rules=" + rules + "]";
	}
	
	
	
}
