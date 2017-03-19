package com.yn.go.front;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.yn.go.common.model.TrainingAddress;

public class TrainingAddressController extends Controller{

	public void tolist(){
		setAttr("active", "trainingAddress");
		render("trainingAddress.html");
	}
	
	public void listAll(){
		List<Record> list = Db.find("select id,training_name as trainingName,user_id as userId from t_training_address");
		renderJson(list);
	}
	
	public void queryByUserId(){
		Integer userId = getParaToInt("userId", 0);
		List<Record> list = Db.find("select id,training_name as trainingName from t_training_address where user_id =?",userId);
		renderJson(list);
	}
	
	
	public void list(){
		Page<Record> paginate = TrainingAddress.dao.paginate(getParaToInt("page", 1), getParaToInt("rows", 10));
		Map<String,Object> resultMap =Maps.newHashMap();
		resultMap.put("total", paginate.getTotalPage());
		resultMap.put("page", paginate.getPageNumber());
		resultMap.put("records", paginate.getTotalRow());
		resultMap.put("rows", paginate.getList());
		renderJson(resultMap);
	}
	
}
