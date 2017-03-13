package com.yn.go.admin;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

public class IndexController extends Controller{

	@ActionKey("admin/index")
	public void index(){
		render("index.html");
	}
}
