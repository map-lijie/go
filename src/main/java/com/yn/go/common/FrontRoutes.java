package com.yn.go.common;

import com.jfinal.config.Routes;
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
		addInterceptor(new FrontInterceptor());
		add("/index", IndexController.class,"");
		add("/login", LoginController.class,"");
		add("/tournament", TournamentController.class,"/tournament");
		add("/trainingAddress", TrainingAddressController.class,"/trainingAddress");
		add("/user", UserController.class,"/user");
		add("/news", NewsController.class,"/news");
		add("/tournamentDetail", TournamentDetailController.class,"/tournamentDetail");
	}
	
} 
