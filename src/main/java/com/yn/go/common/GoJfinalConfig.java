package com.yn.go.common;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.yn.go.admin.AdminController;
import com.yn.go.admin.IndexController;
import com.yn.go.admin.LoginController;
import com.yn.go.admin.NewsController;
import com.yn.go.admin.TournamentController;
import com.yn.go.admin.TrainingAddressController;
import com.yn.go.admin.UserController;
import com.yn.go.common.model._MappingKit;

public class GoJfinalConfig extends JFinalConfig{

	@Override
	public void configConstant(Constants me) {
		PropKit.use("config.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));
	}

	@Override
	public void configRoute(Routes me) {
		me.add(new AdminRoutes());
		me.add(new FrontRoutes());
	}
	
	private class AdminRoutes extends Routes{

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
		}
	}
	
	private class FrontRoutes extends Routes{

		@Override
		public void config() {
			setBaseViewPath("/front");
			
		}
		
	} 

	@Override
	public void configEngine(Engine me) {
		me.addSharedFunction("/WEB-INF/admin/common/head.html");
		me.addSharedFunction("/WEB-INF/admin/common/menu.html");
		
	}

	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
		me.add(druidPlugin);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		_MappingKit.mapping(arp);
		me.add(arp);
	}
	
	public static DruidPlugin createDruidPlugin() {
		return new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		me.add(new SessionInViewInterceptor());
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
		
	}

}
