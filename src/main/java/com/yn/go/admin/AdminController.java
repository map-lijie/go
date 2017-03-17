package com.yn.go.admin;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.yn.go.common.SHA1Util;
import com.yn.go.common.model.Admin;

public class AdminController extends Controller{

	
	public void tolist(){
		setAttr("active", "admin");
		render("admin.html");
	}
	
	public void list(){
		Page<Admin> paginate = Admin.dao.paginate(getParaToInt("page", 1), getParaToInt("rows", 10));
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
		try {
			Admin admin = getBean(Admin.class, "",true);
			if(admin.getPassword()!=null)
				admin.setPassword(SHA1Util.getSha1(admin.getPassword()));
			if("edit".equals(oper)){
				Map<String,Object> user = (Map)getSessionAttr("user");
				Integer id = (Integer) user.get("id");
				
				admin.setUpdateDatetime(new Date()).removeNullValueAttrs().update();
			}else if("del".equals(oper)){
				admin.deleteById(admin.getId());
			}else{
				admin.save();
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
