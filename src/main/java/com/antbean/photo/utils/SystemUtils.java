package com.antbean.photo.utils;

public class SystemUtils {
	private String imgBaseDir;
	private String imgServer;
	private int linkCoverWidth;
	private int linkCoverHeight;
	private int albumCoverWidth;
	private int albumCoverHeight;
	private int photoCoverWidth;
	private int photoCoverHeight;

	private static final SystemUtils instance = new SystemUtils();

	private SystemUtils() {
	}

	public static void init(String imgBaseDir, String imgServer, int linkCoverWidth, int linkCoverHeight,
			int albumCoverWidth, int albumCoverHeight, int photoCoverWidth, int photoCoverHeight) {
		instance.imgBaseDir = imgBaseDir;
		instance.imgServer = imgServer;
		instance.linkCoverWidth = linkCoverWidth;
		instance.linkCoverHeight = linkCoverHeight;
		instance.albumCoverWidth = albumCoverWidth;
		instance.albumCoverHeight = albumCoverHeight;
		instance.photoCoverWidth = photoCoverWidth;
		instance.photoCoverHeight = photoCoverHeight;
	}

	public static String getImgBaseDir() {
		return instance.imgBaseDir;
	}

	public static String getImgServer() {
		return instance.imgServer;
	}

	public static int getLinkCoverWidth() {
		return instance.linkCoverWidth;
	}

	public static int getLinkCoverHeight() {
		return instance.linkCoverHeight;
	}

	public static int getAlbumCoverWidth() {
		return instance.albumCoverWidth;
	}

	public static int getAlbumCoverHeight() {
		return instance.albumCoverHeight;
	}

	public static int getPhotoCoverWidth() {
		return instance.photoCoverWidth;
	}

	public static int getPhotoCoverHeight() {
		return instance.photoCoverHeight;
	}

}
