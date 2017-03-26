package com.yn.go.front;

import com.jfinal.core.Controller;

public class ArticleController extends Controller{

	public void tolist(){
		setAttr("active", "article");
		int type = getParaToInt("type",5);
		setAttr("type", type);
		render("article.html");
	}
	
}
