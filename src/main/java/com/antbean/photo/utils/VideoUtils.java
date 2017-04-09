package com.antbean.photo.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class VideoUtils {
	public static final String FFMPEG_PATH = VideoUtils.class.getClassLoader()
			.getResource("ffmpeg-20170404-1229007-win64-static/bin/ffmpeg.exe").getPath();

	public static void main(String[] args) throws IOException {
		String videoPath = "d:/test/test.mp4";
		String imgPath = "d:/test/testaa.jpg";

		File frameFile = new File(imgPath);
		processFrame(new File(videoPath), frameFile);

		// FileUtils.copyFile(frameFile, new File("d:/test/testBb.jpg"));

	}

	public static void processFrame(File videoFile, File frameFile) throws IOException {
		List<String> commands = new java.util.ArrayList<String>();
		commands.add(FFMPEG_PATH);
		commands.add("-i");
		commands.add(videoFile.getAbsolutePath());
		commands.add("-y");
		commands.add("-f");
		commands.add("image2");
//		commands.add("-ss");
//		commands.add("8");// 这个参数是设置截取视频多少秒时的画面
		// commands.add("-t");
		// commands.add("0.001");
		// commands.add("-s");
		// commands.add("700x525");
		commands.add(frameFile.getAbsolutePath());
		ProcessBuilder builder = new ProcessBuilder();
		builder.command(commands);
		Process process = builder.start();
		InputStream input = process.getInputStream();
		byte[] buff = new byte[input.available()];
		input.read(buff, 0, buff.length);
		System.out.println(new String(buff));
	}
}
