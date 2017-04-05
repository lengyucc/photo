package com.antbean.photo.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSONArray;
import com.antbean.photo.model.Album;
import com.antbean.photo.model.AlbumPhoto;
import com.antbean.photo.model.Photo;
import com.antbean.photo.utils.Pager;
import com.antbean.photo.utils.ResultJson;
import com.antbean.photo.utils.SystemUtils;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.Tx;

public class PhotoController extends Controller {

	public static void main(String[] args) {
		// String data =
		// "{\"photos\":[{\"uri\":\"5da1cfc1-6bc9-40ec-9ad7-dd064135c1d9.jpg\",\"coverUri\":\"5da1cfc1-6bc9-40ec-9ad7-dd064135c1d9-c.jpg\"},{\"uri\":\"15ff159d-41bf-4943-9673-c350920eb64c.jpg\",\"coverUri\":\"15ff159d-41bf-4943-9673-c350920eb64c-c.jpg\"},{\"uri\":\"c355252b-d324-4783-a62b-e9ec90d85a54.jpg\",\"coverUri\":\"c355252b-d324-4783-a62b-e9ec90d85a54-c.jpg\"}],\"albumId\":\"4\"}";
	}

	public void index() {
		Integer albumId = getParaToInt("aid");
		Integer curPage = getParaToInt("p", 1);
		Integer pageSize = SystemUtils.getPageSize();
		Album album = Album.dao.findById(albumId);
		List<Photo> photos = Photo.dao.find("" //
				+ "	SELECT                                                    " //
				+ "		t.*                                                    " //
				+ "	FROM                                                    " //
				+ "		t_photo t                                              " //
				+ "	LEFT JOIN r_album_photo t1 ON t.id = t1.photo_id        " //
				+ "	AND t1.is_deleted = 0                                   " //
				+ "	WHERE                                                   " //
				+ "		t1.album_id = ?                                        " //
				+ "	ORDER BY                                                " //
				+ "		t.gmt_update desc                                                " //
				+ "	LIMIT                                                " //
				+ "		? , ?", albumId, (curPage - 1) * pageSize, pageSize);
		Long count = Db.queryLong("" //
				+ "	SELECT                                                    " //
				+ "		COUNT(t.id)                                                    " //
				+ "	FROM                                                    " //
				+ "		t_photo t                                              " //
				+ "	LEFT JOIN r_album_photo t1 ON t.id = t1.photo_id        " //
				+ "	AND t1.is_deleted = 0                                   " //
				+ "	WHERE                                                   " //
				+ "		t1.album_id = ?", albumId);
		setAttr("album", album);
		Pager<Photo> pager = new Pager<>(count, curPage, photos, pageSize);
		setAttr("pager", pager);
		setAttr("photos", photos);
		setAttr("imgServer", SystemUtils.getImgServer());
		render("index.jsp");
	}

	@Before(Tx.class)
	public void create() {
		try {
			int albumId = getParaToInt("albumId");
			String photosJson = getPara("photos");
			List<Photo> photos = JSONArray.parseArray(photosJson).toJavaList(Photo.class);

			for (Photo photo : photos) {
				File photoFile = new File(SystemUtils.getImgBaseDir(), photo.getUri());
				photo.setLength((int) FileUtils.sizeOf(photoFile));
				if (!photo.getIsVideo()) {
					try {
						BufferedImage img = ImageIO.read(photoFile);
						photo.setWidth(img.getWidth());
						photo.setHeight(img.getHeight());
					} catch (IOException e) {
						renderJson(ResultJson.getFailResult("读取照片信息失败:" + e.getMessage()));
						return;
					}
				}
				photo.setGmtCreated(new Date());
				photo.setGmtUpdate(new Date());
				photo.save();

				AlbumPhoto albumPhoto = new AlbumPhoto();
				albumPhoto.setAlbumId(albumId);
				albumPhoto.setPhotoId(photo.getId().intValue());
				albumPhoto.setGmtCreated(new Date());
				albumPhoto.setGmtUpdate(new Date());
				albumPhoto.save();
			}
			renderJson(ResultJson.getSuccessResult());
			return;
		} catch (Exception e) {
			renderJson(ResultJson.getFailResult("上传失败:" + e.getMessage()));
			return;
		}
	}
}
