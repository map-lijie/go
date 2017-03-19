package com.yn.go.front;

import java.util.List;

import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.yn.go.common.FrontInterceptor;
import com.yn.go.common.model.Article;
import com.yn.go.common.model.News;

@Clear(FrontInterceptor.class)
public class IndexController extends Controller{

	@ActionKey("index")
	public void index(){
		setAttr("saishihuodongList", Article.dao.findList(5, 10));//赛事活动
		setAttr("newsList", News.dao.findList(6));//新闻动态
		setAttr("rencaipeiyangList",  Article.dao.findList(2, 6));//人才培养
		setAttr("hangyewenhuaList",  Article.dao.findList(3, 6));//行业文化
		setAttr("bisaixinshangList",  Article.dao.findList(1, 10));//比赛欣赏
		List<Record> notices = Article.dao.findList(0, 1);
		setAttr("notice",  notices!=null&&!notices.isEmpty()?notices.get(0):null);//消息通知
		render("index.html");
	}
}
