package com.yn.go.common;


import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class AdminInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		Object user = controller.getSessionAttr("user");
		if(user!=null){
			inv.invoke();
		}else{
			controller.redirect("/admin/login/tologin");
		}
		
	}

}
