package com.yn.go.front;

import java.util.Map;

import com.google.common.collect.Maps;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.yn.go.common.model.Article;

public class ArticleController extends Controller{

	public void tolist(){
		setAttr("active", "article");
		int type = getParaToInt("type",5);
		setAttr("type", type);
		render("article.html");
	}
	
	public void list(){
		Page<Record> paginate = Article.dao.paginate(getParaToInt("page", 1), getParaToInt("rows", 10),getParaToInt("type",5));
		Map<String,Object> resultMap =Maps.newHashMap();
		resultMap.put("total", paginate.getTotalPage());
		resultMap.put("page", paginate.getPageNumber());
		resultMap.put("records", paginate.getTotalRow());
		resultMap.put("rows", paginate.getList());
		renderJson(resultMap);
	}
}
