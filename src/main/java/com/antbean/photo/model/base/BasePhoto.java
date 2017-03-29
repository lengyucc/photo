package com.antbean.photo.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BasePhoto<M extends BasePhoto<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Long id) {
		set("id", id);
	}

	public java.lang.Long getId() {
		return get("id");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setDesc(java.lang.String desc) {
		set("desc", desc);
	}

	public java.lang.String getDesc() {
		return get("desc");
	}

	public void setUri(java.lang.String uri) {
		set("uri", uri);
	}

	public java.lang.String getUri() {
		return get("uri");
	}

	public void setIsVideo(java.lang.Boolean isVideo) {
		set("is_video", isVideo);
	}

	public java.lang.Boolean getIsVideo() {
		return get("is_video");
	}

	public void setSize(java.lang.Integer size) {
		set("size", size);
	}

	public java.lang.Integer getSize() {
		return get("size");
	}

	public void setIsDeleted(java.lang.Boolean isDeleted) {
		set("is_deleted", isDeleted);
	}

	public java.lang.Boolean getIsDeleted() {
		return get("is_deleted");
	}

	public void setGmtCreated(java.lang.Integer gmtCreated) {
		set("gmt_created", gmtCreated);
	}

	public java.lang.Integer getGmtCreated() {
		return get("gmt_created");
	}

	public void setGmtUpdate(java.lang.Integer gmtUpdate) {
		set("gmt_update", gmtUpdate);
	}

	public java.lang.Integer getGmtUpdate() {
		return get("gmt_update");
	}

}