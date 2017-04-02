package com.antbean.photo.utils;

public class SystemUtils {
	private String imgBaseDir;
	private String imgServer;

	private static final SystemUtils instance = new SystemUtils();

	private SystemUtils() {
	}

	public static void init(String imgBaseDir, String imgServer) {
		instance.imgBaseDir = imgBaseDir;
		instance.imgServer = imgServer;
	}

	public static String getImgBaseDir() {
		return instance.imgBaseDir;
	}

	public static String getImgServer() {
		return instance.imgServer;
	}

}
