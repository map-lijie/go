package com.yn.go.admin;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.yn.go.common.Filters;
import com.yn.go.common.SHA1Util;
import com.yn.go.common.model.Admin;

public class AdminController extends Controller{
	private final static  Logger LOGGER =Logger.getLogger(AdminController.class);
	
	public void tolist(){
		setAttr("active", "admin");
		render("admin.html");
	}
	
	public void list(){
		Filters filters = JSON.parseObject(getPara("filters"), Filters.class);
		if(LOGGER.isInfoEnabled())
			LOGGER.info("Filters==="+filters);
		Map user = getSessionAttr("user");
		Integer adminType = Integer.valueOf(user.get("adminType")+"");
		Page<Admin> paginate = Admin.dao.paginate(getParaToInt("page", 1), getParaToInt("rows", 10),adminType,filters);
		Map<String,Object> resultMap =Maps.newHashMap();
		resultMap.put("total", paginate.getTotalPage());
		resultMap.put("page", paginate.getPageNumber());
		resultMap.put("records", paginate.getTotalRow());
		resultMap.put("rows", paginate.getList());
		renderJson(resultMap);
	
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updatePasswd(){
		String result = "0";
		try {
			String oldPasswd = SHA1Util.getSha1(getPara("oldPasswd"));
			String newPasswd =SHA1Util.getSha1(getPara("newPasswd"));
			Map<String,Object> user = (Map)getSessionAttr("user");
			Integer id = (Integer) user.get("id");
			int resultcode = Admin.dao.updatePassword(id, oldPasswd, newPasswd);
			if(resultcode==0)
				result = "1";
			if(resultcode==1)
				removeSessionAttr("user");
		} catch (Exception e) {
			e.printStackTrace();
			result ="-1";
		}
		Map<String,Object> resultMap =Maps.newHashMap();
		resultMap.put("status", result);
		renderJson(resultMap);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void save(){
		String oper = getPara("oper");
		String result = "success";
		try {
			Admin admin = getBean(Admin.class, "",true);
			if(admin.getPassword()!=null)
				admin.setPassword(SHA1Util.getSha1(admin.getPassword()));
			if("edit".equals(oper)){
				admin.setUpdateDatetime(new Date()).removeNullValueAttrs().update();
				Map<String,Object> user = (Map)getSessionAttr("user");
				Integer id = (Integer) user.get("id");
				if(admin.getPassword()!=null&&id.intValue() ==admin.getId().intValue()){//修改自己的密码需要重登陆
					removeSessionAttr("user");
				}
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
