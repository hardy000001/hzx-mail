package com.lq.lss.controller.util;

import java.io.File;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lq.lss.utils.LssConfig;

public class DownloadFileUtil {
	
	private static String savePath=LssConfig.get().getUpfile();
	
	/**
	 * 文件下载
	 * @param request
	 * @param response
	 * @param fileName
	 * @throws Exception
	 */
	public static void downloadFile(HttpServletRequest request,
			HttpServletResponse response,String fileName,String url) throws Exception{
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		if(url==null){
			url = request.getSession().getServletContext().getRealPath("/"+"templates/");
		}
		String downLoadPath = url +"/"+ fileName;;
		try {
			long fileLength = new File(downLoadPath).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
			+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
			deleteFile(savePath+"/"+fileName);
		}
		
	}
	/**
	 * 删除文件
	 * @param 全路径
	 */
	public static void deleteFile(String fullPath){
		
		File file = new File(fullPath);
		if (file.exists()) {
			file.delete();
		}		
	}
	
}
