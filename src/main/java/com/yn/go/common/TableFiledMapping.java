package com.yn.go.common;


public class TableFiledMapping {

	private final String table;
	
	private final String filedName;
	
	private final String finalFiledName;

	public TableFiledMapping(String table, String filedName){
		this(table, filedName, null);
	}
	
	public TableFiledMapping(String table, String filedName,
			String finalFiledName) {
		super();
		this.table =  table==null||"".equals(table.trim())?"":table.trim()+".";
		this.filedName = filedName;
		this.finalFiledName = finalFiledName;
	}

	public String getTable() {
		return table;
	}

	public String getFiledName() {
		return filedName;
	}

	public String getFinalFiledName() {
		return finalFiledName;
	}

	@Override
	public String toString() {
		return "TableFiledMapping [table=" + table + ", filedName=" + filedName
				+ ", finalFiledName=" + finalFiledName + "]";
	}

	
	
}