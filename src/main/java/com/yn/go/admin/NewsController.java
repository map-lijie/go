package com.yn.go.admin;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.yn.go.common.Filters;
import com.yn.go.common.model.News;

public class NewsController extends Controller{
	private final static  Logger LOGGER =Logger.getLogger(NewsController.class);
	public void tolist(){
		setAttr("active", "news");
		render("news.html");
	}
	
	public void list(){
		Filters filters = JSON.parseObject(getPara("filters"), Filters.class);
		if(LOGGER.isInfoEnabled())
			LOGGER.info("Filters==="+filters);
		Page<Record> paginate = News.dao.paginate(getParaToInt("page", 1), getParaToInt("rows", 10),filters);
		Map<String,Object> resultMap =Maps.newHashMap();
		resultMap.put("total", paginate.getTotalPage());
		resultMap.put("page", paginate.getPageNumber());
		resultMap.put("records", paginate.getTotalRow());
		resultMap.put("rows", paginate.getList());
		renderJson(resultMap);
	}
	
	
	public void save(){
		String oper = getPara("oper");
		String result = "success";
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Map<String,Object> user = (Map)getSessionAttr("user");
		try {
			News news = getBean(News.class, "",true);
			news.setAdminId((Integer)user.get("id"));
			if("edit".equals(oper)){
				/*StringBuilder sb =new StringBuilder();
				sb.append("update  t_news set title=?,admin_id=?,description=?,update_datetime=?");
				List<Object> params =Lists.newArrayList();
				params.add(news.getTitle());
				
				params.add(news.getAdminId());
				params.add(news.getDescription());
				params.add(new Date());
				sb.append(" where id=?");
				params.add(news.getId());
				Db.update(sb.toString(), params.toArray());*/
				news.setUpdateDatetime(new Date()).removeNullValueAttrs().update();
			}else if("del".equals(oper)){
				news.deleteById(news.getId());
			}else{
				news.setCreateDatetime(new Date());
				news.setUpdateDatetime(new Date());
				news.save();
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
