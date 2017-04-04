package com.antbean.photo.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.antbean.photo.model.Link;
import com.antbean.photo.utils.ImageUtils;
import com.antbean.photo.utils.ResultJson;
import com.antbean.photo.utils.SystemUtils;
import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;

public class IndexController extends Controller {
	private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

	public void index() {
		List<Link> links = Link.dao.find("SELECT * FROM t_link WHERE is_deleted=0 ORDER BY sort");
		setAttr("links", links);
		setAttr("imgServer", SystemUtils.getImgServer());
		render("index.jsp");
	}

	public void up() {
		List<UploadFile> uploadFiles = getFiles();
		if (uploadFiles == null || uploadFiles.size() == 0) {
			renderJson(ResultJson.getFailResult("没有选择有效文件"));
			return;
		}
		String m = getPara("m"); // 业务模块：1照片 2相册 3首页链接
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		if (uploadFiles.size() == 1) {
			UploadFile uploadFile = uploadFiles.get(0);
			String fileName = uploadFile.getFileName();
			LOG.info("fileName:" + fileName);

			try {
				data.addAll(processUploadFile(uploadFile.getFile(), uploadFile.getFileName(), m));
			} catch (IOException e) {
				renderJson(ResultJson.getFailResult(e.getMessage()));
				return;
			}
		} else {
			for (UploadFile uploadFile : uploadFiles) {
				String fileName = uploadFile.getFileName();
				LOG.info("fileName:" + fileName);
				try {
					data.addAll(processUploadFile(uploadFile.getFile(), uploadFile.getFileName(), m));
				} catch (IOException e) {
					renderJson(ResultJson.getFailResult(e.getMessage()));
					return;
				}
			}
		}
		renderJson(ResultJson.getSuccessResult().cput("data", data).cput("imgServer", SystemUtils.getImgServer()));
		return;
	}

	public static List<Map<String, Object>> processUploadFile(File file, String fileName, String m) throws IOException {
		LOG.info("fileName:" + fileName);
		String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		if (ImageUtils.SUPPORTED_IMAGE_FORMATS.contains(suffixName)) {
			// 图片
			try {
				ImageUtils.check(file);
			} catch (IOException e) {
				throw new IllegalArgumentException("读取图片失败", e);
			}

			String uuid = UUID.randomUUID().toString();
			String saveName = uuid + "." + suffixName;
			String coverName = uuid + "-c." + suffixName;
			String name = fileName.substring(0, fileName.lastIndexOf("."));
			try {
				FileUtils.copyFile(file, new File(SystemUtils.getImgBaseDir(), saveName));
			} catch (IOException e) {
				throw new IOException("转存图片失败", e);
			}
			try {
				if ("1".equals(m)) {
					// 图片
					ImageUtils.thumbnail(file, new File(SystemUtils.getImgBaseDir(), coverName),
							SystemUtils.getPhotoCoverWidth(), SystemUtils.getPhotoCoverHeight());
				} else if ("2".equals(m)) {
					// 相册
					ImageUtils.thumbnail(file, new File(SystemUtils.getImgBaseDir(), coverName),
							SystemUtils.getAlbumCoverWidth(), SystemUtils.getAlbumCoverHeight());
				} else if ("3".equals(m)) {
					// 首页链接
					ImageUtils.thumbnail(file, new File(SystemUtils.getImgBaseDir(), coverName),
							SystemUtils.getLinkCoverWidth(), SystemUtils.getLinkCoverHeight());
				} else {
					throw new IllegalArgumentException("m类型不支持:" + m);
				}
			} catch (IOException e) {
				throw new IOException("处理缩略图失败", e);
			}

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", name);
			map.put("uri", saveName);
			map.put("coverUri", coverName);
			map.put("isVideo", false);
			return Arrays.asList(map);
		} else if (ImageUtils.SUPPORTED_VIDEO_FORMATS.contains(suffixName)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uri", suffixName);
			map.put("isVideo", true);
			// 视频
			return Arrays.asList(map);
		} else if ("zip".equalsIgnoreCase(suffixName)) {
			// zip
			return null;
		} else {
			throw new IllegalArgumentException("不支持的文件类型");
		}
	}
}
