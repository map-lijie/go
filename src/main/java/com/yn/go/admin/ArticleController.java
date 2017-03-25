package com.yn.go.admin;

import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;
import com.yn.go.common.Filters;
import com.yn.go.common.UrlConstants;
import com.yn.go.common.model.Article;

public class ArticleController extends Controller{
	private final static  Logger LOGGER =Logger.getLogger(ArticleController.class);
	
	public void tolist(){
		setAttr("active", "article");
		int type = getParaToInt("type",5);
		setAttr("type", type);
		render("article.html");
	}
	
	public void list(){
		Filters filters = JSON.parseObject(getPara("filters"), Filters.class);
		if(LOGGER.isInfoEnabled())
			LOGGER.info("Filters==="+filters);
		Page<Record> paginate = Article.dao.paginate(getParaToInt("page", 1), getParaToInt("rows", 10),getParaToInt("type",5),filters);
		Map<String,Object> resultMap =Maps.newHashMap();
		resultMap.put("total", paginate.getTotalPage());
		resultMap.put("page", paginate.getPageNumber());
		resultMap.put("records", paginate.getTotalRow());
		resultMap.put("rows", paginate.getList());
		renderJson(resultMap);
	}
	
	public void uplaodFile(){
		String result = "success";
		String imageUrl=null;
		try {
			UploadFile imageUrlFile =getFile("imageUrl", "/article/image", 1024*1000, "utf-8");
		//	UploadFile imageUrlFile  = getFile("imageUrl", "/image");
			String uploadPath = imageUrlFile.getUploadPath()+"/"+UUID.randomUUID().toString()+".jpg";
			File dest =new File(uploadPath);
			imageUrlFile.getFile().renameTo(dest);
			imageUrl =UrlConstants.URL+"upload/article/image/"+dest.getName();
			//imageUrlFile.getFile()
		} catch (Exception e) {
			e.printStackTrace();
			result ="error";
		}
		Map<String,Object> resultMap =Maps.newHashMap();
		resultMap.put("status", result);
		resultMap.put("data", imageUrl);
		renderJson(resultMap);
	}
	
	public void save(){
		String result = "success";
		try {
			// UploadFile imageUrlFile  = getFile("imageUrl", "/image");
			//UploadFile imageUrl  = getFile("imageUrl", "/page");;
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Map<String,Object> user = (Map)getSessionAttr("user");
			int type = getParaToInt("type",5);
			
			String oper = getPara("oper");
			Article article = getBean(Article.class, "",true);
			article.setAdminId((Integer)user.get("id"));
			if("edit".equals(oper)){
				//article.setImageUrl(url+imageUrl.getUploadPath());
				article.setUpdateDatetime(new Date()).removeNullValueAttrs().update();
			}else if("del".equals(oper)){
				article.deleteById(article.getId());
			}else{
				article.setType(type);
				//article.setImageUrl(url+imageUrl.getUploadPath());
				article.setCreateDatetime(new Date());
				article.setUpdateDatetime(new Date());
				article.save();
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
