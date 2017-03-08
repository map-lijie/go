package com.yn.go.admin;

import com.jfinal.core.Controller;
import com.yn.go.common.model.Tournament;

public class TournamentController extends Controller{

	
	public void save(){
		Tournament tournament = getBean(Tournament.class,"");
		tournament.save();
		renderJson(tournament);
	}
}
