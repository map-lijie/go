package com.yn.go.common;

import com.jfinal.config.Routes;
import com.yn.go.alipay.AlipayController;
import com.yn.go.front.ArticleController;
import com.yn.go.front.IndexController;
import com.yn.go.front.LoginController;
import com.yn.go.front.NewsController;
import com.yn.go.front.TournamentController;
import com.yn.go.front.TournamentDetailController;
import com.yn.go.front.TrainingAddressController;
import com.yn.go.front.UserController;

public class FrontRoutes extends Routes{

	@Override
	public void config() {
		setBaseViewPath("/WEB-INF/front");
		addInterceptor(new FrontInterceptor());
		add("/index", IndexController.class,"");
		add("/login", LoginController.class,"");
		add("/tournament", TournamentController.class,"");
		add("/trainingAddress", TrainingAddressController.class,"");
		add("/user", UserController.class,"");
		add("/news", NewsController.class,"");
		add("/tournamentDetail", TournamentDetailController.class,"");
		add("/article", ArticleController.class,"");
		add("/alipay", AlipayController.class,"");
	}
	
} 
