package com.yn.go.front;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

public class IndexController extends Controller{

	@ActionKey("index")
	public void index(){
		render("index.html");
	}
}
