package com.antbean.photo.config;

import com.antbean.photo.controller.AlbumController;
import com.antbean.photo.controller.IndexController;
import com.antbean.photo.model._MappingKit;
import com.antbean.photo.utils.SystemUtils;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

/**
 * API引导式配置
 */
public class SystemConfig extends JFinalConfig {

	/**
	 * 配置常量
	 */
	@Override
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用PropKit.get(...)获取值
		PropKit.use("a_little_config.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		me.setViewType(ViewType.JSP);
		me.setBaseViewPath("/WEB-INF/jsps");
		me.setBaseUploadPath(PropKit.get("upTempDir"));
	}

	/**
	 * 配置路由
	 */
	@Override
	public void configRoute(Routes me) {
		me.add("/", IndexController.class);
		me.add("/album", AlbumController.class);
	}

	public static C3p0Plugin createC3p0Plugin() {
		return new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
	}

	/**
	 * 配置插件
	 */
	@Override
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		C3p0Plugin C3p0Plugin = createC3p0Plugin();
		me.add(C3p0Plugin);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(C3p0Plugin);
		me.add(arp);

		// 所有配置在 MappingKit 中搞定
		_MappingKit.mapping(arp);
	}

	/**
	 * 配置全局拦截器
	 */
	@Override
	public void configInterceptor(Interceptors me) {

	}

	/**
	 * 配置处理器
	 */
	@Override
	public void configHandler(Handlers me) {

	}

	@Override
	public void afterJFinalStart() {
		super.afterJFinalStart();
		String imgBaseDir = PropKit.get("imgBaseDir");
		String imgServer = PropKit.get("imgServer");
		String linkCoverSize = PropKit.get("linkCoverSize");
		String albumCoverSize = PropKit.get("albumCoverSize");
		String photoCoverSize = PropKit.get("photoCoverSize");

		String[] linkCoverSizes = linkCoverSize.trim().split(",");
		String[] albumCoverSizes = albumCoverSize.trim().split(",");
		String[] photoCoverSizes = photoCoverSize.trim().split(",");
		int linkCoverWidth = Integer.parseInt(linkCoverSizes[0].trim());
		int linkCoverHeight = Integer.parseInt(linkCoverSizes[1].trim());
		int albumCoverWidth = Integer.parseInt(albumCoverSizes[0].trim());
		int albumCoverHeight = Integer.parseInt(albumCoverSizes[1].trim());
		int photoCoverWidth = Integer.parseInt(photoCoverSizes[0].trim());
		int photoCoverHeight = Integer.parseInt(photoCoverSizes[1].trim());

		SystemUtils.init(imgBaseDir, imgServer, linkCoverWidth, linkCoverHeight, albumCoverWidth, albumCoverHeight,
				photoCoverWidth, photoCoverHeight);
	}
}
