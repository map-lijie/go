package com.yn.go.admin;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.yn.go.common.model.TrainingAddress;

public class TrainingAddressController extends Controller{

	public void tolist(){
		render("trainingAddress.html");
	}
	
	public void listAll(){
		List<Record> list = Db.find("select id,training_name as trainingName,user_id as userId from t_training_address");
		renderJson(list);
	}
	
	
	public void list(){
		Page<Record> paginate = TrainingAddress.dao.paginate(getParaToInt("page", 1), getParaToInt("rows", 10));
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
			TrainingAddress trainingAddress = getBean(TrainingAddress.class, "",true);
			trainingAddress.setAdminId((Integer)user.get("id"));
			if("edit".equals(oper)){
				/*StringBuilder sb =new StringBuilder();
				sb.append("update  t_training_address set training_name=?,admin_id=?,user_id=?,address=?,update_datetime=?");
				List<Object> params =Lists.newArrayList();
				params.add(trainingAddress.getTrainingName());
				params.add(trainingAddress.getAdminId());
				params.add(trainingAddress.getUserId());
				params.add(trainingAddress.getAddress());
				params.add(new Date());
				sb.append(" where id=?");
				params.add(trainingAddress.getId());
				Db.update(sb.toString(), params.toArray());*/
				trainingAddress.setUpdateDatetime(new Date()).removeNullValueAttrs().update();
			}else if("del".equals(oper)){
				trainingAddress.deleteById(trainingAddress.getId());
			}else{
				trainingAddress.setCreateDatetime(new Date());
				trainingAddress.setUpdateDatetime(new Date());
				trainingAddress.save();
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
