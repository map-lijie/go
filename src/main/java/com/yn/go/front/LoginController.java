package com.yn.go.front;

import java.util.Map;
import java.util.UUID;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.yn.go.common.FrontInterceptor;
import com.yn.go.common.model.User;

//@Before(SessionInViewInterceptor.class)
public class LoginController extends Controller{

	@Clear(FrontInterceptor.class)
	public void login(){
		Map<String,Object> resultMap =Maps.newHashMap();
		int errcode =0;
		String errmsg ="OK";
		try {
			String name = getPara("userName");
			String password = getPara("password");
			if(!Strings.isNullOrEmpty(name)&&!Strings.isNullOrEmpty(password)){
				User user = User.dao.checkUserAndPassword(name, password);
				if(user!=null){
					//保存session  type
					Map<String,Object> userMap =Maps.newHashMap();
					userMap.put("userName", name);
					userMap.put("id", user.getId());
					userMap.put("type", user.getType());
					userMap.put("token", UUID.randomUUID().toString());
					userMap.put("createDatetime", System.currentTimeMillis());
					setSessionAttr("frontuser",userMap);
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
	
	/*@Clear(FrontInterceptor.class)
	public void tologin(){
		render("login.html");
	}*/
	
	public void logout(){
		Map<String,Object> resultMap =Maps.newHashMap();
		int errcode =0;
		String errmsg ="OK";
		try {
			removeSessionAttr("frontuser");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			errcode =9999;
			errmsg="操作异常，请稍候重试";
		}
		resultMap.put("errcode", errcode);
		resultMap.put("errmsg", errmsg);
		//renderJson(resultMap);
		render("/login.html");
	}
	
}
