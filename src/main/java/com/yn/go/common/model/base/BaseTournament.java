package com.yn.go.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTournament<M extends BaseTournament<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public M setName(java.lang.String name) {
		set("name", name);
		return (M)this;
	}

	public java.lang.String getName() {
		return get("name");
	}

	public M setDescription(java.lang.String description) {
		set("description", description);
		return (M)this;
	}

	public java.lang.String getDescription() {
		return get("description");
	}

	public M setDanGrading(java.lang.Integer danGrading) {
		set("dan_grading", danGrading);
		return (M)this;
	}

	public java.lang.Integer getDanGrading() {
		return get("dan_grading");
	}

	public M setFee(java.lang.Integer fee) {
		set("fee", fee);
		return (M)this;
	}

	public java.lang.Integer getFee() {
		return get("fee");
	}

	public M setAdminId(java.lang.Integer adminId) {
		set("admin_id", adminId);
		return (M)this;
	}

	public java.lang.Integer getAdminId() {
		return get("admin_id");
	}

	public M setStatus(java.lang.Integer status) {
		set("status", status);
		return (M)this;
	}

	public java.lang.Integer getStatus() {
		return get("status");
	}

	public M setStartDatetime(java.util.Date startDatetime) {
		set("start_datetime", startDatetime);
		return (M)this;
	}

	public java.util.Date getStartDatetime() {
		return get("start_datetime");
	}

	public M setEndDatetime(java.util.Date endDatetime) {
		set("end_datetime", endDatetime);
		return (M)this;
	}

	public java.util.Date getEndDatetime() {
		return get("end_datetime");
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

}
