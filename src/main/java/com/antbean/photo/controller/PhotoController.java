package com.antbean.photo.controller;

import java.util.List;

import com.antbean.photo.model.Photo;
import com.antbean.photo.utils.SystemUtils;
import com.jfinal.core.Controller;

public class PhotoController extends Controller {
	public void index() {
		List<Photo> photos = Photo.dao.find("SELECT * FROM t_photo WHERE is_deleted=0");
		setAttr("photos", photos);
		setAttr("imgServer", SystemUtils.getImgServer());
		render("index.jsp");
	}
}
