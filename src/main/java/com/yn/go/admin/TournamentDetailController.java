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
import com.yn.go.common.model.TournamentDetail;

public class TournamentDetailController extends Controller{
	private final static  Logger LOGGER =Logger.getLogger(TournamentDetailController.class);
	
	public void tolist(){
		setAttr("active", "tournamentDetail");
		render("tournamentDetail.html");
	}
	
	public void list(){
		Filters filters = JSON.parseObject(getPara("filters"), Filters.class);
		if(LOGGER.isInfoEnabled())
			LOGGER.info("Filters==="+filters);
		Page<Record> paginate = TournamentDetail.dao.paginate(getParaToInt("page", 1), getParaToInt("rows", 10),filters);
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
			TournamentDetail tournamentDetail = getBean(TournamentDetail.class, "",true);
			tournamentDetail.setAPayerId((Integer)user.get("id"));
			if("edit".equals(oper)){
				tournamentDetail.setStatus(11)
				.setPayType(2)
				.setUpdateDatetime(new Date())
				.removeNullValueAttrs()
				.update();
			}else if("del".equals(oper)){
				tournamentDetail.deleteById(tournamentDetail.getId());
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
