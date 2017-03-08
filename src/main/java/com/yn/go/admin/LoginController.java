package com.yn.go.admin;

import java.util.Map;
import java.util.UUID;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.yn.go.common.AdminInterceptor;
import com.yn.go.common.model.Admin;


public class LoginController extends Controller{

	//@ActionKey("/admin/login")
	@Clear(AdminInterceptor.class)
	public void login(){
		Map<String,Object> resultMap =Maps.newHashMap();
		int errcode =0;
		String errmsg ="OK";
		try {
			String name = getPara("name");
			String password = getPara("password");
			if(!Strings.isNullOrEmpty(name)&&!Strings.isNullOrEmpty(password)){
				if(Admin.dao.checkAdminAndPassword(name, password)){
					//保存session  
					Map<String,Object> user =Maps.newHashMap();
					user.put("name", name);
					user.put("token", UUID.randomUUID().toString());
					user.put("createDatetime", System.currentTimeMillis());
					setSessionAttr("user",user);
				}else{
					errcode =1001;
					errmsg="用户名或密码不正确";
				}
			}else{
				errcode =1000;
				errmsg="用户名或密码不能为空";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			errcode =9999;
			errmsg="操作异常，请稍候重试";
		}
		resultMap.put("errcode", errcode);
		resultMap.put("errmsg", errmsg);
		renderJson(resultMap);
	}
	
	//@ActionKey("/admin/login")
	@Clear(AdminInterceptor.class)
	public void tologin(){
		render("login.html");
	}
	
}