package com.antbean.photo.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.util.ThumbnailatorUtils;

public class ImageUtils {

	public static final List<String> SUPPORTED_IMAGE_FORMATS = ThumbnailatorUtils.getSupportedOutputFormats();

	public static final List<String> SUPPORTED_VIDEO_FORMATS = Arrays.asList("MP3", "mp3");

	public static void main(String[] args) throws IOException {
//		thumbnail("d:/test/1.jpg", "d:/test/1_1.jpg", 1000, 618);

		// thumbnail("e:/images/test4.jpg", "e:/images/test4_1.jpg", 700, 700);
		// thumbnail("e:/images/test4.jpg", "e:/images/test4_2.jpg", 400, 400);
		// thumbnail("e:/images/test4.jpg", "e:/images/test4_3.jpg", 1000,
		// 1000);

		// thumbnail("e:/images/test4.jpg", "e:/images/test4_1.jpg", 700, 100);
		// thumbnail("e:/images/test4.jpg", "e:/images/test4_2.jpg", 1000, 700);
		// thumbnail("e:/images/test4.jpg", "e:/images/test4_3.jpg", 1000, 800);
		// thumbnail("e:/images/test4.jpg", "e:/images/test4_4.jpg", 600, 100);

		// thumbnail("e:/images/test4.jpg", "e:/images/test4_1.jpg", 200, 900);
		// thumbnail("e:/images/test4.jpg", "e:/images/test4_2.jpg", 150, 400);
		// thumbnail("e:/images/test4.jpg", "e:/images/test4_3.jpg", 800, 1000);

		// thumbnail("e:/images/test1.jpg", "e:/images/test1_1.jpg", 300, 300);
		// thumbnail("e:/images/test1.jpg", "e:/images/test1_2.jpg", 396, 396);
		// thumbnail("e:/images/test1.jpg", "e:/images/test1_3.jpg", 550, 550);

		// thumbnail("e:/images/test1.jpg", "e:/images/test1_1.jpg", 550, 360);
		// thumbnail("e:/images/test1.jpg", "e:/images/test1_2.jpg", 360, 350);
		// thumbnail("e:/images/test1.jpg", "e:/images/test1_3.jpg", 580, 320);

		// thumbnail("e:/images/test1.jpg", "e:/images/test1_1.jpg", 200, 396);
		// thumbnail("e:/images/test1.jpg", "e:/images/test1_2.jpg", 100, 360);
		// thumbnail("e:/images/test1.jpg", "e:/images/test1_3.jpg", 300, 900);

		// thumbnail("e:/images/test2.jpg", "e:/images/test2_1.jpg", 200, 200);
		// thumbnail("e:/images/test2.jpg", "e:/images/test2_2.jpg", 900, 900);
		// thumbnail("e:/images/test2.jpg", "e:/images/test2_3.jpg", 550, 550);
		// thumbnail("e:/images/test2.jpg", "e:/images/test2_4.jpg", 396, 396);

		// thumbnail("e:/images/test2.jpg", "e:/images/test2_1.jpg", 200, 100);
		// thumbnail("e:/images/test2.jpg", "e:/images/test2_2.jpg", 900, 300);
		// thumbnail("e:/images/test2.jpg", "e:/images/test2_3.jpg", 600, 200);

		// thumbnail("e:/images/test2.jpg", "e:/images/test2_1.jpg", 100, 400);
		// thumbnail("e:/images/test2.jpg", "e:/images/test2_2.jpg", 200, 600);
		// thumbnail("e:/images/test2.jpg", "e:/images/test2_3.jpg", 50, 300);
	}

	public static void thumbnail(File source, File dest, int width, int height) throws IOException {
//		File sourceFile = new File(source);
		BufferedImage bufferedImage = ImageIO.read(source);
		int srcWidth = bufferedImage.getWidth();
		int srcHeight = bufferedImage.getHeight();
		ImageShapes srcShape = getImageShape(srcWidth, srcHeight);
		ImageShapes destShape = getImageShape(width, height);

		switch (srcShape) {
		case square: {
			if (destShape == ImageShapes.square) {
				System.out.println("square -> square");
				Thumbnails.of(source).scale(1.0 * width / srcWidth).toFile(dest);
			} else if (destShape == ImageShapes.crosswise) {
				System.out.println("square -> crosswise");
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				Thumbnails.of(source).scale(1.0 * width / srcWidth).toOutputStream(out);
				ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
				Thumbnails.of(input).sourceRegion(Positions.CENTER, width, height).size(width, height).toFile(dest);
				IOUtils.closeQuietly(input);
				IOUtils.closeQuietly(out);
			} else if (destShape == ImageShapes.vertical) {
				System.out.println("square -> vertical");
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				Thumbnails.of(source).scale(1.0 * height / srcWidth).toOutputStream(out);
				ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
				Thumbnails.of(input).sourceRegion(Positions.CENTER, width, height).size(width, height).toFile(dest);
				IOUtils.closeQuietly(input);
				IOUtils.closeQuietly(out);
			}
			break;
		}
		case crosswise: {
			if (destShape == ImageShapes.square) {
				System.out.println("crosswise -> square");
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				Thumbnails.of(source).scale(1.0 * width / srcHeight).toOutputStream(out);
				ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
				Thumbnails.of(input).sourceRegion(Positions.CENTER, width, width).size(width, width).toFile(dest);
				IOUtils.closeQuietly(input);
				IOUtils.closeQuietly(out);
			} else if (destShape == ImageShapes.crosswise) {
				System.out.println("crosswise -> crosswise");
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				Thumbnails.of(source).scale(Math.max(1.0 * width / srcWidth, 1.0 * height / srcHeight))
						.toOutputStream(out);
				ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
				Thumbnails.of(input).sourceRegion(Positions.CENTER, width, height).size(width, height).toFile(dest);
				IOUtils.closeQuietly(input);
				IOUtils.closeQuietly(out);
			} else if (destShape == ImageShapes.vertical) {
				System.out.println("crosswise -> vertical");
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				Thumbnails.of(source).scale(1.0 * height / srcHeight).toOutputStream(out);
				ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
				Thumbnails.of(input).sourceRegion(Positions.CENTER, width, height).size(width, height).toFile(dest);
				IOUtils.closeQuietly(input);
				IOUtils.closeQuietly(out);
			}
			break;
		}
		case vertical: {
			if (destShape == ImageShapes.square) {
				System.out.println("vertical -> square");
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				Thumbnails.of(source).scale(1.0 * width / srcWidth).toOutputStream(out);
				ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
				Thumbnails.of(input).sourceRegion(Positions.CENTER, width, width).size(width, width).toFile(dest);
				IOUtils.closeQuietly(input);
				IOUtils.closeQuietly(out);
			} else if (destShape == ImageShapes.crosswise) {
				System.out.println("vertical -> crosswise");
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				Thumbnails.of(source).scale(1.0 * width / srcWidth).toOutputStream(out);
				ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
				Thumbnails.of(input).sourceRegion(Positions.CENTER, width, height).size(width, height).toFile(dest);
				IOUtils.closeQuietly(input);
				IOUtils.closeQuietly(out);
			} else if (destShape == ImageShapes.vertical) {
				System.out.println("vertical -> vertical");
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				Thumbnails.of(source).scale(Math.max(1.0 * width / srcWidth, 1.0 * height / srcHeight))
						.toOutputStream(out);
				ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
				Thumbnails.of(input).sourceRegion(Positions.CENTER, width, height).size(width, height).toFile(dest);
				IOUtils.closeQuietly(input);
				IOUtils.closeQuietly(out);
			}
			break;
		}
		}
	}

	public static void check(File... images) throws IOException {
		if (images != null) {
			for (File image : images) {
				ImageIO.read(image);
			}
		}
	}

	public static ImageShapes getImageShape(int width, int height) {
		return (width > height ? ImageShapes.crosswise : (width < height ? ImageShapes.vertical : ImageShapes.square));
	}

	public enum ImageShapes {
		square, crosswise, vertical
	}
}
