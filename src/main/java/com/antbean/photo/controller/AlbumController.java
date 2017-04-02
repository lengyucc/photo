package com.antbean.photo.controller;

import java.util.List;

import com.antbean.photo.model.Album;
import com.antbean.photo.utils.SystemUtils;
import com.jfinal.core.Controller;

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
}
