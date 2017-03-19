package com.yn.go.common.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.yn.go.common.model.base.BaseAdmin;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Admin extends BaseAdmin<Admin> {
	public static final Admin dao = new Admin().dao();
	
	public Admin checkAdminAndPassword(String name,String password){
		
		return findFirst("select id,type from t_admin where name =? and password =?",name,password);
	}
	
	public List<Admin> listAll(int pageNumber,int pageSize){
		return find("select * from t_admin order by id asc" );
		//return paginate(pageNumber, pageSize, "", "");
		
	}
	
	public Page<Admin> paginate(int pageNumber,int pageSize){
		return paginate(pageNumber, pageSize, "select *", " from t_admin order by id asc");
		
	}
	
    public int updatePassword(Integer id,String oldpassword,String newpassword){
		return Db.update("update t_admin set password =? where id =? and password =?", newpassword,id,oldpassword);
		//return ;
	}
}
