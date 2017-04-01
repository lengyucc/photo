package com.antbean.photo.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageTest {
	public static void main(String[] args) throws IOException {
		// square2Square("e:/images/test4.jpg", "e:/images/test4_1.jpg", 800);
		// square2Square("e:/images/test4.jpg", "e:/images/test4_2.jpg", 400);

		// square2Crosswise("e:/images/test4.jpg", "e:/images/test4_3.jpg", 400,
		// 100);

		// square2Vertical("e:/images/test4.jpg", "e:/images/test4_3.jpg", 200,
		// 900);

		// crosswise2Square("e:/images/test1.jpg", "e:/images/test1_1.jpg",
		// 300);
		// crosswise2Square("e:/images/test1.jpg", "e:/images/test1_2.jpg",
		// 396);
		// crosswise2Square("e:/images/test1.jpg", "e:/images/test1_3.jpg",
		// 550);

		// crosswise2Crosswise("e:/images/test1.jpg", "e:/images/test1_1.jpg",
		// 550, 360);
		// crosswise2Crosswise("e:/images/test1.jpg", "e:/images/test1_2.jpg",
		// 360, 350);
		// crosswise2Crosswise("e:/images/test1.jpg", "e:/images/test1_3.jpg",
		// 580, 320);

		// crosswise2Vertical("e:/images/test1.jpg", "e:/images/test1_1.jpg",
		// 200, 396);
		// crosswise2Vertical("e:/images/test1.jpg", "e:/images/test1_2.jpg",
		// 100, 360);
		// crosswise2Vertical("e:/images/test1.jpg", "e:/images/test1_3.jpg",
		// 300, 900);

		 vertical2Square("e:/images/test2.jpg", "e:/images/test2_1.jpg", 200);
		 vertical2Square("e:/images/test2.jpg", "e:/images/test2_2.jpg", 900);
		 vertical2Square("e:/images/test2.jpg", "e:/images/test2_3.jpg", 550);
		 vertical2Square("e:/images/test2.jpg", "e:/images/test2_4.jpg", 396);

		// vertical2Crosswise("e:/images/test2.jpg", "e:/images/test2_1.jpg",
		// 200,100);
		// vertical2Crosswise("e:/images/test2.jpg", "e:/images/test2_2.jpg",
		// 900,100);
		// vertical2Crosswise("e:/images/test2.jpg", "e:/images/test2_3.jpg",
		// 600,200);

		// vertical2Vertical("e:/images/test2.jpg", "e:/images/test2_1.jpg",
		// 100, 400);
		// vertical2Vertical("e:/images/test2.jpg", "e:/images/test2_2.jpg",
		// 200, 600);
		// vertical2Vertical("e:/images/test2.jpg", "e:/images/test2_3.jpg", 50, 300);
	}

	/**
	 * 竖形转竖形
	 */
	public static void vertical2Vertical(String source, String dest, int destWidth, int destHeight) throws IOException {
		File srcFile = new File(source);
		BufferedImage srcImg = ImageIO.read(srcFile);
		int srcWidth = srcImg.getWidth();
		int srcHeight = srcImg.getHeight();
		if (srcWidth >= srcHeight) {
			throw new IllegalArgumentException("原图不是一个横形");
		}
		if (destWidth >= destHeight) {
			throw new IllegalArgumentException("目标不是一个竖形");
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Thumbnails.of(source).scale(Math.max(1.0 * destWidth / srcWidth, 1.0 * destHeight / srcHeight))
				.toOutputStream(out);
		ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
		Thumbnails.of(input).sourceRegion(Positions.CENTER, destWidth, destHeight).size(destWidth, destHeight)
				.toFile(dest);
		IOUtils.closeQuietly(input);
		IOUtils.closeQuietly(out);
	}

	/**
	 * 竖形转横形
	 */
	public static void vertical2Crosswise(String source, String dest, int destWidth, int destHeight)
			throws IOException {
		File srcFile = new File(source);
		BufferedImage srcImg = ImageIO.read(srcFile);
		int srcWidth = srcImg.getWidth();
		int srcHeight = srcImg.getHeight();
		if (srcWidth >= srcHeight) {
			throw new IllegalArgumentException("原图不是一个横形");
		}
		if (destWidth <= destHeight) {
			throw new IllegalArgumentException("目标不是一个横形");
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Thumbnails.of(source).scale(1.0 * destWidth / srcWidth).toOutputStream(out);
		Thumbnails.of(new ByteArrayInputStream(out.toByteArray())).sourceRegion(Positions.CENTER, destWidth, destHeight)
				.size(destWidth, destHeight).toFile(dest);
	}

	/**
	 * 竖形转方形
	 * 
	 * @param source
	 * @param dest
	 * @param destWidth
	 * @param destHeight
	 * @throws IOException
	 */
	public static void vertical2Square(String source, String dest, int destWidth) throws IOException {
		File srcFile = new File(source);
		BufferedImage srcImg = ImageIO.read(srcFile);
		int srcWidth = srcImg.getWidth();
		int srcHeight = srcImg.getHeight();
		if (srcWidth >= srcHeight) {
			throw new IllegalArgumentException("原图不是一个横形");
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Thumbnails.of(source).scale(1.0 * destWidth / srcWidth).toOutputStream(out);
		Thumbnails.of(new ByteArrayInputStream(out.toByteArray())).sourceRegion(Positions.CENTER, destWidth, destWidth)
				.size(destWidth, destWidth).toFile(dest);
	}

	/**
	 * 横形转竖形
	 * 
	 * @param source
	 * @param dest
	 * @param destWidth
	 * @param destHeight
	 * @throws IOException
	 */
	public static void crosswise2Vertical(String source, String dest, int destWidth, int destHeight)
			throws IOException {
		File srcFile = new File(source);
		BufferedImage srcImg = ImageIO.read(srcFile);
		int srcWidth = srcImg.getWidth();
		int srcHeight = srcImg.getHeight();
		if (srcWidth <= srcHeight) {
			throw new IllegalArgumentException("原图不是一个横形");
		}
		if (destWidth >= destHeight) {
			throw new IllegalArgumentException("目标不是一个竖形");
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Thumbnails.of(source).scale(1.0 * destHeight / srcHeight).toOutputStream(out);
		Thumbnails.of(new ByteArrayInputStream(out.toByteArray())).sourceRegion(Positions.CENTER, destWidth, destHeight)
				.size(destWidth, destHeight).toFile(dest);
	}

	/**
	 * 横形转横形
	 * 
	 * @param source
	 * @param dest
	 * @param destWidth
	 * @param destHeight
	 * @throws IOException
	 */
	public static void crosswise2Crosswise(String source, String dest, int destWidth, int destHeight)
			throws IOException {
		File srcFile = new File(source);
		BufferedImage srcImg = ImageIO.read(srcFile);
		int srcWidth = srcImg.getWidth();
		int srcHeight = srcImg.getHeight();
		if (srcWidth <= srcHeight) {
			throw new IllegalArgumentException("原图不是一个横形");
		}
		if (destWidth <= destHeight) {
			throw new IllegalArgumentException("目标不是一个横形");
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Thumbnails.of(source).scale(Math.max(1.0 * destWidth / srcWidth, 1.0 * destHeight / srcHeight))
				.toOutputStream(out);
		Thumbnails.of(new ByteArrayInputStream(out.toByteArray())).sourceRegion(Positions.CENTER, destWidth, destHeight)
				.size(destWidth, destHeight).toFile(dest);
	}

	/**
	 * 横形转方形
	 * 
	 * @param source
	 * @param dest
	 * @param destWidth
	 * @param destHeight
	 * @throws IOException
	 */
	public static void crosswise2Square(String source, String dest, int destWidth) throws IOException {
		File srcFile = new File(source);
		BufferedImage srcImg = ImageIO.read(srcFile);
		int srcWidth = srcImg.getWidth();
		int srcHeight = srcImg.getHeight();
		if (srcWidth <= srcHeight) {
			throw new IllegalArgumentException("原图不是一个横形");
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Thumbnails.of(source).scale(1.0 * destWidth / srcHeight).toOutputStream(out);
		Thumbnails.of(new ByteArrayInputStream(out.toByteArray())).sourceRegion(Positions.CENTER, destWidth, destWidth)
				.size(destWidth, destWidth).toFile(dest);
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

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Thumbnails.of(source).scale(1.0 * destHeight / srcWidth).toOutputStream(out);
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

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Thumbnails.of(source).scale(1.0 * destWidth / srcWidth).toOutputStream(out);
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
