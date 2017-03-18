package com.yn.go.front;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.yn.go.common.SHA1Util;
import com.yn.go.common.model.TrainingAddress;
import com.yn.go.common.model.User;

public class UserController extends Controller{

	
	public void tolist(){
		int type = getParaToInt("type",2);
		setAttr("type", type);
		setAttr("active", "user");
		render("user.html");
	}
	
	public void list(){
		Page<Record> paginate = User.dao.paginate(getParaToInt("page", 1), getParaToInt("rows", 10),getParaToInt("type",2));
		Map<String,Object> resultMap =Maps.newHashMap();
		resultMap.put("total", paginate.getTotalPage());
		resultMap.put("page", paginate.getPageNumber());
		resultMap.put("records", paginate.getTotalRow());
		resultMap.put("rows", paginate.getList());
		renderJson(resultMap);
	}
	
	public void listAll(){
		List<Record> list = Db.find("select id,user_name as userName from t_user where type=2");
		renderJson(list);
	}
	
	
	
	public void save(){
		int type = getParaToInt("type",2);
		String oper = getPara("oper");
		String result = "success";
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Map<String,Object> users = (Map)getSessionAttr("user");
		try {
			User user = getBean(User.class, "",true);
			if(user.getPassword()!=null)
				user.setPassword(SHA1Util.getSha1(user.getPassword()));
			user.setAdminId((Integer)users.get("id"));
			if("edit".equals(oper)){
				user.setUpdateDatetime(new Date()).removeNullValueAttrs().update();
			}else if("del".equals(oper)){
				user.deleteById(user.getId());
			}else{
				user.setCreateDatetime(new Date());
				user.setUpdateDatetime(new Date());
				user.setType(type).save();
				Iterable<String> taIds = Splitter.on(",").trimResults().omitEmptyStrings().split(getPara("taIds"));
				for(String taId:taIds){
					TrainingAddress t =new TrainingAddress();
					t.setId(Integer.parseInt(taId)).setUserId(user.getId()).setUpdateDatetime(new Date()).removeNullValueAttrs().update();
				}
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
