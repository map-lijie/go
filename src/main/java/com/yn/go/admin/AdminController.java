package com.yn.go.admin;

import com.jfinal.core.Controller;
import com.yn.go.common.model.Admin;

public class AdminController extends Controller{

	
	
	public void save(){
		Admin admin = getBean(Admin.class, "");
		admin.save();
		renderJson(admin);
	}
	
}
