package com.antbean.photo.controller;

import java.util.Date;
import java.util.List;

import com.antbean.photo.model.Album;
import com.antbean.photo.utils.ResultJson;
import com.antbean.photo.utils.SystemUtils;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class AlbumController extends Controller {
	public void index() {
		List<Album> albums = Album.dao.find("SELECT * FROM t_album WHERE is_deleted=0 ORDER BY sort");
		setAttr("albums", albums);
		setAttr("imgServer", SystemUtils.getImgServer());
		render("index.jsp");
	}

	public void detail() {
		setAttr("imgServer", SystemUtils.getImgServer());
		render("detail.jsp");
	}

	public void create() {
		try {
			Record album = new Record().set("name", getPara("name")).set("desc", getPara("desc"))
					.set("cover_uri", getPara("coverUri")).set("gmt_update", new Date()).set("gmt_created", new Date());
			boolean success = Db.save("t_album", album);
			if (success) {
				renderJson(ResultJson.getSuccessResult());
				return;
			}
			renderJson(ResultJson.getFailResult("创建失败"));
		} catch (Exception e) {
			renderJson(ResultJson.getFailResult("创建失败:" + e.getMessage()));
			return;
		}
	}
}
