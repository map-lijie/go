package com.yn.go.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTrainingAddress<M extends BaseTrainingAddress<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public M setTrainingName(java.lang.String trainingName) {
		set("training_name", trainingName);
		return (M)this;
	}

	public java.lang.String getTrainingName() {
		return get("training_name");
	}

	public M setAddress(java.lang.String address) {
		set("address", address);
		return (M)this;
	}

	public java.lang.String getAddress() {
		return get("address");
	}

	public M setCreateDatetime(java.util.Date createDatetime) {
		set("create_datetime", createDatetime);
		return (M)this;
	}

	public java.util.Date getCreateDatetime() {
		return get("create_datetime");
	}

	public M setUpdateDatetime(java.util.Date updateDatetime) {
		set("update_datetime", updateDatetime);
		return (M)this;
	}

	public java.util.Date getUpdateDatetime() {
		return get("update_datetime");
	}

	public M setUserId(java.lang.Integer userId) {
		set("user_id", userId);
		return (M)this;
	}

	public java.lang.Integer getUserId() {
		return get("user_id");
	}

	public M setAdminId(java.lang.Integer adminId) {
		set("admin_id", adminId);
		return (M)this;
	}

	public java.lang.Integer getAdminId() {
		return get("admin_id");
	}

}