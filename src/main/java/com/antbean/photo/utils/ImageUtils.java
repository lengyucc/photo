package com.antbean.photo.utils;

import java.util.Arrays;
import java.util.List;

import net.coobird.thumbnailator.util.ThumbnailatorUtils;

public class ImageUtils {

	public static final List<String> SUPPORTED_IMAGE_FORMATS = ThumbnailatorUtils.getSupportedOutputFormats();

	public static final List<String> SUPPORTED_VIDEO_FORMATS = Arrays.asList("MP3", "mp3");

	public static void main(String[] args) {
		List<String> list = ThumbnailatorUtils.getSupportedOutputFormats();
		System.out.println(list);
	}
}
