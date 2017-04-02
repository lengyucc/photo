package com.antbean.photo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.antbean.photo.model.Link;
import com.antbean.photo.utils.SystemUtils;
import com.jfinal.core.Controller;

public class IndexController extends Controller {
	public void index() {
		List<Link> links = Link.dao.find("SELECT * FROM t_link WHERE is_deleted=0 ORDER BY sort");
		setAttr("links", links);
		setAttr("imgServer", SystemUtils.getImgServer());
		render("index.jsp");
	}

	public void upImg() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imgBaseDir", SystemUtils.getImgBaseDir());
		map.put("imgServer", SystemUtils.getImgServer());
		map.put("test", "中文");
		renderJson(map);
	}
}
