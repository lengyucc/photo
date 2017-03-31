package com.antbean.photo.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.util.ThumbnailatorUtils;

public class ImageUtils {

	public static final List<String> SUPPORTED_IMAGE_FORMATS = ThumbnailatorUtils.getSupportedOutputFormats();

	public static final List<String> SUPPORTED_VIDEO_FORMATS = Arrays.asList("MP3", "mp3");

	public static void main(String[] args) throws IOException {
		// List<String> list = ThumbnailatorUtils.getSupportedOutputFormats();
		// System.out.println(list);
//		thumbnail("E:/images/test1.jpg", "E:/images/test1_1.jpg", 360, 360); // 550
																				// 396
		
		File sourceFile = new File("E:/images/test1_1.jpg");
		BufferedImage bufferedImage = ImageIO.read(sourceFile);
		System.out.println("width:" + bufferedImage.getWidth() + ", height:" + bufferedImage.getHeight());
	}

	public static void thumbnail(String source, String dest, int width, int height) throws IOException {
		// Thumbnails.of(source).size(width, height).toFile(dest);
		// Thumbnails.of(source).forceSize(width, height).toFile(dest);

		File sourceFile = new File(source);
		BufferedImage bufferedImage = ImageIO.read(sourceFile);
		int srcWidth = bufferedImage.getWidth();
		int srcHeight = bufferedImage.getHeight();
		ImageShape srcShape = getImageShape(srcWidth, srcHeight);
		ImageShape destShape = getImageShape(width, height);

		switch (srcShape) {
		case square: {
			if (destShape == ImageShape.square) {
				// 方形 -> 方形
				Thumbnails.of(source).scale(1.0 * width / srcWidth).toFile(dest);
			} else if (destShape == ImageShape.crosswise) {
				// 方形 -> 横形
				Thumbnails.of(source).scale(1.0 * width / srcWidth).sourceRegion(0, (width - height) / 2, width, height)
						.toFile(dest);
			} else if (destShape == ImageShape.vertical) {
				// 方形 -> 竖形
				Thumbnails.of(source).scale(1.0 * height / srcWidth)
						.sourceRegion((height - width) / 2, 0, width, height).toFile(dest);
			}
			break;
		}
		case crosswise: {
			if (destShape == ImageShape.square) {
				System.out.println("横形 -> 方形");
				// 横形 -> 方形
				double proportion = 1.0 * height / srcHeight;
				Thumbnails.of(source).scale(proportion)
						.sourceRegion((int) ((proportion * srcWidth - width) / 2), 0, width, height).toFile(dest);
			} else if (destShape == ImageShape.crosswise) {
				System.out.println("横形 -> 横形");
				// 横形 -> 横形
				double xp = 1.0 * width / srcWidth; // 横向缩放比
				double yp = 1.0 * height / srcHeight; // 竖向缩放比
				double proportion = Math.max(xp, yp);
				Thumbnails.of(source).scale(proportion)
						.sourceRegion((int) ((proportion * srcWidth - width) / 2), 0, width, height).toFile(dest);
			} else if (destShape == ImageShape.vertical) {
				
			}
			break;
		}
		case vertical: {
			if (destShape == ImageShape.square) {

			} else if (destShape == ImageShape.crosswise) {

			} else if (destShape == ImageShape.vertical) {

			}
			break;
		}
		}
	}

	public static ImageShape getImageShape(int width, int height) {
		return (width > height ? ImageShape.crosswise : (width < height ? ImageShape.vertical : ImageShape.square));
	}

	public enum ImageShape {
		square, crosswise, vertical
	}
}
