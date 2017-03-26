package com.yn.go.front;

import java.util.Map;

import com.google.common.collect.Maps;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.yn.go.common.model.News;

public class NewsController extends Controller{

	public void tolist(){
		setAttr("active", "news");
		render("news.html");
	}
	
	
	
}
