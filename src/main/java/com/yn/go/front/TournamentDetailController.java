package com.yn.go.front;

import java.util.Date;
import java.util.Map;

import com.google.common.collect.Maps;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.yn.go.common.model.TournamentDetail;

public class TournamentDetailController extends Controller{

	
	public void tolist(){
		setAttr("active", "tournamentDetail");
		render("tournamentDetail.html");
	}
	
	public void list(){
		Page<Record> paginate = TournamentDetail.dao.paginate(getParaToInt("page", 1), getParaToInt("rows", 10));
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
				tournamentDetail.setUpdateDatetime(new Date()).removeNullValueAttrs().update();
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
