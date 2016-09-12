package com.buybal.fileServie.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DownloadPictureController {
	private static final Logger logger = Logger.getLogger(DownloadPictureController.class);
	
	@RequestMapping(value = "/downloadPicture.do")
	public String doBusiness(HttpServletRequest request, HttpServletResponse response) {
		FileInputStream in = null;
		ServletOutputStream outStream = null;
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String fileName = request.getParameter("fileName"); //要下载文件名称如/home/epaysch/mobileApp/20130624xyz.apk(包含存放路径)
			logger.info("文件下载开始：" + fileName);
			outStream = response.getOutputStream();

			if (StringUtils.isEmpty(fileName)) {
				logger.error("[文件下载]参数文件名为空");
				outStream.write("[文件下载]文件名称为空".getBytes("utf-8"));
				return null;
			}
			File file = new File(fileName);
			// 下载的文件不存在
			if (!file.exists()) {
				outStream.write("[文件下载]文件不存在或已删除".getBytes("utf-8"));
				return null;
			}
			// 解决不同浏览器文件名称乱码问题
			String agent = request.getHeader("USER-AGENT");
			if (agent != null && agent.indexOf("MSIE") == -1) {
				fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
			} else { // IE
				fileName = java.net.URLEncoder.encode(fileName, "utf-8");
			}
			response.setContentLength((int) file.length());
			response.setContentType("application/x-msdownload;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

			int length = 0;
			in = new FileInputStream(file);
			byte[] byteBuffer = new byte[1024];

			while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
				outStream.write(byteBuffer, 0, length);
			}
			outStream.flush();
		} catch (Exception e) {
			logger.error("[文件下载]下载文件异常", e);
		} finally {
			try {
				if (outStream != null) {
					outStream.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				logger.error("[文件下载]下载文件,关闭流异常", e);
			}
		}

		return null;
	}
	
	@RequestMapping(value = "/download.apk")
	public String doBusiness2(HttpServletRequest request, HttpServletResponse response) {
		FileInputStream in = null;
		ServletOutputStream outStream = null;
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String fileName = request.getParameter("fileName"); //要下载文件名称如/home/epaysch/mobileApp/20130624xyz.apk(包含存放路径)
			logger.info("文件下载开始：" + fileName);
			outStream = response.getOutputStream();

			if (StringUtils.isEmpty(fileName)) {
				logger.error("[文件下载]参数文件名为空");
				outStream.write("[文件下载]文件名称为空".getBytes("utf-8"));
				return null;
			}
			File file = new File(fileName);
			// 下载的文件不存在
			if (!file.exists()) {
				outStream.write("[文件下载]文件不存在或已删除".getBytes("utf-8"));
				return null;
			}
			// 解决不同浏览器文件名称乱码问题
			String agent = request.getHeader("USER-AGENT");
			if (agent != null && agent.indexOf("MSIE") == -1) {
				fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
			} else { // IE
				fileName = java.net.URLEncoder.encode(fileName, "utf-8");
			}
			response.setContentLength((int) file.length());
			response.setContentType("application/x-msdownload;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

			int length = 0;
			in = new FileInputStream(file);
			byte[] byteBuffer = new byte[1024];

			while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
				outStream.write(byteBuffer, 0, length);
			}
			outStream.flush();
		} catch (Exception e) {
			logger.error("[文件下载]下载文件异常", e);
		} finally {
			try {
				if (outStream != null) {
					outStream.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				logger.error("[文件下载]下载文件,关闭流异常", e);
			}
		}

		return null;
	}
}
