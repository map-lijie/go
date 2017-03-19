package com.yn.go.front;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.yn.go.common.SerialNoBuilder;
import com.yn.go.common.model.TournamentDetail;

public class TournamentDetailController extends Controller{

	private SerialNoBuilder serialNoBuilder =new SerialNoBuilder();
	
	/**
	 * 报名
	 */
	public void apply(){
		String result = "success";
		try {
			TournamentDetail tournamentDetail = getBean(TournamentDetail.class, "");
			tournamentDetail.setCreateDatetime(new Date())
			.setUpdateDatetime(new Date())
			.setStatus(10)
			.setOrderId(serialNoBuilder.getSerialNo())
			.save();
		} catch (Exception e) {
			e.printStackTrace();
			result ="error";
		}
		
		Map<String,Object> resultMap =Maps.newHashMap();
		resultMap.put("status", result);
		renderJson(resultMap);
	}
	
	/**
	 * 批量报名
	 */
	public void batchApply(){
		String[] batchData = getParaValues("batchData");
		String result = "success";
		try {
		   Db.batchSave(Lists.transform(Arrays.asList(batchData), new Function<String, TournamentDetail>() {
				@Override
				public TournamentDetail apply(String input) {
					List<String> list = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(input);
					return new TournamentDetail()
					.setCreateDatetime(new Date())
					.setUpdateDatetime(new Date())
					.setStatus(10)
					.setTId(Integer.valueOf(list.get(0)))
					.setUserId(Integer.valueOf(list.get(1)))
					.setOrderId(serialNoBuilder.getSerialNo());
				}
			}), batchData.length);
		} catch (Exception e) {
			e.printStackTrace();
			result ="error";
		}
		Map<String,Object> resultMap =Maps.newHashMap();
		resultMap.put("status", result);
		renderJson(resultMap);
	}
	
	
	public void tolist(){
		setAttr("active", "tournamentDetail");
		render("tournamentDetail.html");
	}
	
	public void list(){
		Page<Record> paginate = TournamentDetail.dao.paginate(getParaToInt("page", 1), getParaToInt("rows", 10));
		Map<String,Object> resultMap =Maps.newHashMap();
		resultMap.put("total", paginate.getTotalPage());
		resultMap.put("page", paginate.getPageNumber());
		resultMap.put("records", paginate.getTotalRow());
		resultMap.put("rows", paginate.getList());
		renderJson(resultMap);
	}
	
	
	
}
