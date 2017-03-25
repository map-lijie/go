package com.yn.go.common;

import java.util.List;

public class BuildSql {

	private static class BuildSqlHelper{
		private static final BuildSql instance =new BuildSql();
	}
	
	private BuildSql(){}
	
	public static BuildSql getInstance(){
		return BuildSqlHelper.instance;
	}
	
	public String build(Filters filters,BuildParams buildParams){
		StringBuilder sql =new StringBuilder();
		if(filters!=null){
			List<Rule> rules = filters.getRules();
			int size = rules.size();
			List<TableFiledMapping> tfms = buildParams.getTfms();
			
			int tfmsSize = tfms==null?0:tfms.size();
			for(int i =0;i<size;i++){
				if(i==0){
					if(buildParams.getIsCondition())
						sql.append(" and ");
					else
						sql.append(" where ");
					if("OR".equalsIgnoreCase(filters.getGroupOp())){
						sql.append(" ( ");
					}
				}
				Rule rule =rules.get(i);
				
				String table =buildParams.getDefaultTable();
				String filedName =rule.getField();
				
				for(int  j=0;j<tfmsSize;j++){
					TableFiledMapping tableFiledMapping = tfms.get(j);
					if(tableFiledMapping.getFiledName().equalsIgnoreCase(filedName)){
						table =tableFiledMapping.getTable();
						if(tableFiledMapping.getFinalFiledName()!=null){
							filedName =tableFiledMapping.getFinalFiledName();
						}
						continue;
					}
				}
				/*if("chief_coach_name".equalsIgnoreCase(filedName)){
					table =" b.";
					filedName ="user_name";
				}else if("training_name".equalsIgnoreCase(filedName)){
					table =" c.";
				}*/
				sql.append(" "+table+filedName);
				if("eq".equalsIgnoreCase(rule.getOp())){
					sql.append(" = '"+rule.getData()+"' ");
				}else{
					sql.append(" like '%"+rule.getData() +"%' ");
				}
				if(i!=size-1){
					sql.append(filters.getGroupOp());
				}
			}
			if(size>0&&"OR".equalsIgnoreCase(filters.getGroupOp())){
				sql.append(" ) ");
			}
		}
		sql.append(" order by "+buildParams.getDefaultTable()+"id desc");
		return sql.toString();
	}
}
