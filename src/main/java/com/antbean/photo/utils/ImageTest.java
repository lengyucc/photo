package com.antbean.photo.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageTest {
	public static void main(String[] args) throws IOException {
		// square2Square("e:/images/test4.jpg", "e:/images/test4_1.jpg", 800);
		// square2Square("e:/images/test4.jpg", "e:/images/test4_2.jpg", 400);

		square2Crosswise("e:/images/test4.jpg", "e:/images/test4_3.jpg", 400, 100);
		
		square2Vertical("e:/images/test4.jpg", "e:/images/test4_3.jpg", 200, 900);
	}

	/**
	 * 方形转竖形
	 * 
	 * @param source
	 * @param dest
	 * @param width
	 * @throws IOException
	 */
	public static void square2Vertical(String source, String dest, int destWidth, int destHeight) throws IOException {
		File srcFile = new File(source);
		BufferedImage srcImg = ImageIO.read(srcFile);
		int srcWidth = srcImg.getWidth();
		int srcHeight = srcImg.getHeight();
		if (srcWidth != srcHeight) {
			throw new IllegalArgumentException("原图不是一个方形");
		}
		if (destWidth >= destHeight) {
			throw new IllegalArgumentException("目标不是一个竖形");
		}
		
		// 先转成与竖形等高的方形
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Thumbnails.of(source).scale(1.0 * destHeight / srcWidth).toOutputStream(out);
		// 把转后的方形截取出竖形
		Thumbnails.of(new ByteArrayInputStream(out.toByteArray())).sourceRegion(Positions.CENTER, destWidth, destHeight)
		.size(destWidth, destHeight).toFile(dest);
	}
	
	/**
	 * 方形转横形
	 * 
	 * @param source
	 * @param dest
	 * @param width
	 * @throws IOException
	 */
	public static void square2Crosswise(String source, String dest, int destWidth, int destHeight) throws IOException {
		File srcFile = new File(source);
		BufferedImage srcImg = ImageIO.read(srcFile);
		int srcWidth = srcImg.getWidth();
		int srcHeight = srcImg.getHeight();
		if (srcWidth != srcHeight) {
			throw new IllegalArgumentException("原图不是一个方形");
		}
		if (destWidth <= destHeight) {
			throw new IllegalArgumentException("目标不是一个横形");
		}

		// 先转成与横形等宽的方形
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Thumbnails.of(source).scale(1.0 * destWidth / srcWidth).toOutputStream(out);
		// 把转后的方形截取出横形
		Thumbnails.of(new ByteArrayInputStream(out.toByteArray())).sourceRegion(Positions.CENTER, destWidth, destHeight)
				.size(destWidth, destHeight).toFile(dest);
	}

	/**
	 * 方形转方形
	 * 
	 * @param source
	 * @param dest
	 * @param destWidth
	 * @throws IOException
	 */
	public static void square2Square(String source, String dest, int destWidth) throws IOException {
		File srcFile = new File(source);
		BufferedImage srcImg = ImageIO.read(srcFile);
		int srcWidth = srcImg.getWidth();
		int srcHeight = srcImg.getHeight();
		if (srcWidth != srcHeight) {
			throw new IllegalArgumentException("原图不是一个方形");
		}

		Thumbnails.of(source).scale(1.0 * destWidth / srcWidth).toFile(dest);
	}
}
