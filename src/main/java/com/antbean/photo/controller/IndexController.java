package com.antbean.photo.controller;

import java.util.HashMap;
import java.util.Map;

import com.antbean.photo.utils.SystemUtils;
import com.jfinal.core.Controller;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法 详见 JFinal 俱乐部:
 * http://jfinal.com/club
 * 
 * IndexController
 */
public class IndexController extends Controller {
	public void index() {
		render("index.jsp");
	}

	public void upImg() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imgBaseDir", SystemUtils.getImgBaseDir());
		map.put("imgServer", SystemUtils.getImgServer());
		renderJson(map);
	}
}
