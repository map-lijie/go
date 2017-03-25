package com.yn.go.common;

import java.util.List;

public class BuildParams {

	private final Boolean isCondition;
	private final String defaultTable;
	private final List<TableFiledMapping> tfms;
	public BuildParams(){
		this(false,"a",null);
	}
	
	public BuildParams(Boolean isCondition){
		this(isCondition,"a",null);
	}
	public BuildParams(Boolean isCondition,String defaultTable){
		this(isCondition,defaultTable,null);
	}
	
	public BuildParams(Boolean isCondition,List<TableFiledMapping> tfms){
		this(isCondition,"a",tfms);
	}
	
	public BuildParams(String defaultTable,List<TableFiledMapping> tfms){
		this(false,defaultTable,tfms);
	}
	
	public BuildParams(List<TableFiledMapping> tfms){
		this(false,"a",tfms);
	}
	
	public BuildParams(Boolean isCondition, String defaultTable,
			List<TableFiledMapping> tfms) {
		super();
		this.isCondition = isCondition==null?false:isCondition;
		this.defaultTable = defaultTable==null||"".equals(defaultTable.trim())?"":defaultTable.trim()+"."; 
		this.tfms = tfms;
	}
	
	
	public Boolean getIsCondition() {
		return isCondition;
	}


	public String getDefaultTable() {
		return defaultTable;
	}


	public List<TableFiledMapping> getTfms() {
		return tfms;
	}


	@Override
	public String toString() {
		return "BuildParams [isCondition=" + isCondition + ", defaultTable="
				+ defaultTable + ", tfms=" + tfms + "]";
	}
	
	
}
