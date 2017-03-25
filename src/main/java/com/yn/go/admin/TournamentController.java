package com.yn.go.admin;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.yn.go.common.Filters;
import com.yn.go.common.model.Tournament;

public class TournamentController extends Controller{
	private final static  Logger LOGGER =Logger.getLogger(TournamentController.class);
	
	public void tolist(){
		setAttr("active", "tournament");
		render("tournament.html");
	}
	
	public void list(){
		Filters filters = JSON.parseObject(getPara("filters"), Filters.class);
		if(LOGGER.isInfoEnabled())
			LOGGER.info("Filters==="+filters);
		Page<Record> paginate = Tournament.dao.paginate(getParaToInt("page", 1), getParaToInt("rows", 10),filters);
		Map<String,Object> resultMap =Maps.newHashMap();
		resultMap.put("total", paginate.getTotalPage());
		resultMap.put("page", paginate.getPageNumber());
		resultMap.put("records", paginate.getTotalRow());
		resultMap.put("rows", paginate.getList());
		renderJson(resultMap);
	}
	
	
	
	public void save(){
		String oper = getPara("oper");
		String result = "success";
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Map<String,Object> user = (Map)getSessionAttr("user");
		try {
			Tournament tournament = getBean(Tournament.class, "",true);
			tournament.setAdminId((Integer)user.get("id"));
			if("edit".equals(oper)){
				tournament.setUpdateDatetime(new Date()).removeNullValueAttrs().update();
			}else if("del".equals(oper)){
				tournament.deleteById(tournament.getId());
			}else{
				tournament.setCreateDatetime(new Date());
				tournament.setUpdateDatetime(new Date());
				tournament.save();
			}
		} catch (Exception e) {
			e.printStackTrace();
			result ="error";
		}
		Map<String,Object> resultMap =Maps.newHashMap();
		resultMap.put("status", result);
		renderJson(resultMap);
	}
}
