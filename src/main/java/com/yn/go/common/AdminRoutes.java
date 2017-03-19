package com.yn.go.common;

import com.jfinal.config.Routes;
import com.yn.go.admin.AdminController;
import com.yn.go.admin.ArticleController;
import com.yn.go.admin.IndexController;
import com.yn.go.admin.LoginController;
import com.yn.go.admin.NewsController;
import com.yn.go.admin.TournamentController;
import com.yn.go.admin.TournamentDetailController;
import com.yn.go.admin.TrainingAddressController;
import com.yn.go.admin.UserController;

public class AdminRoutes extends Routes{

	@Override
	public void config() {
		setBaseViewPath("/WEB-INF/admin");
		addInterceptor(new AdminInterceptor());
		add("/admin", AdminController.class);
		add("/admin/index", IndexController.class,"");
		add("/admin/login", LoginController.class,"");
		add("/admin/tournament", TournamentController.class,"/tournament");
		add("/admin/trainingAddress", TrainingAddressController.class,"/trainingAddress");
		add("/admin/user", UserController.class,"/user");
		add("/admin/news", NewsController.class,"/news");
		add("/admin/article", ArticleController.class,"/article");
		add("/admin/tournamentDetail", TournamentDetailController.class,"/tournamentDetail");
	}
}