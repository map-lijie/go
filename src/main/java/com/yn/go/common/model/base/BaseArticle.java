package com.yn.go.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseArticle<M extends BaseArticle<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public M setTitle(java.lang.String title) {
		set("title", title);
		return (M)this;
	}

	public java.lang.String getTitle() {
		return get("title");
	}

	public M setDescription(java.lang.String description) {
		set("description", description);
		return (M)this;
	}

	public java.lang.String getDescription() {
		return get("description");
	}

	public M setType(java.lang.Integer type) {
		set("type", type);
		return (M)this;
	}

	public java.lang.Integer getType() {
		return get("type");
	}

	public M setSubType(java.lang.Integer subType) {
		set("sub_type", subType);
		return (M)this;
	}

	public java.lang.Integer getSubType() {
		return get("sub_type");
	}

	public M setAdminId(java.lang.Integer adminId) {
		set("admin_id", adminId);
		return (M)this;
	}

	public java.lang.Integer getAdminId() {
		return get("admin_id");
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

	public M setImageUrl(java.lang.String imageUrl) {
		set("image_url", imageUrl);
		return (M)this;
	}

	public java.lang.String getImageUrl() {
		return get("image_url");
	}

	public M setIsShow(java.lang.Integer isShow) {
		set("is_show", isShow);
		return (M)this;
	}

	public java.lang.Integer getIsShow() {
		return get("is_show");
	}

}
