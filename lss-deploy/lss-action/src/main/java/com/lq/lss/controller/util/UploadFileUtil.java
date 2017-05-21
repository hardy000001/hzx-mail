package com.lq.lss.controller.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.LssConfig;
import com.lq.util.RandomUtil;
import com.lq.util.qiniu.QiNiuUploadPicUtil;


public class UploadFileUtil {

	private static final String  BUCKET="ryfitsport";
	
	/**
	 * 上传多个文件
	 * @param request
	 * @param params
	 * @param prefix 前缀
	 * @return
	 */
	public  static String[]  uploadFiles(HttpServletRequest request,String params[],String prefix){
		
		String [] strs=new String [params.length];
		InputStream bis=null;
		
		String userStr = "";
		SessionUser sessionUser = LoginSessionUtils.getUserInSession(request);
		if(sessionUser != null){
			userStr = sessionUser.getLoginName()+"_";
		}
		
		userStr += RandomUtil.get().randomDegital(6)+"_";
		
		for(int i=0;i<params.length;i++){
			String param = params[i];
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			CommonsMultipartFile fileStream = (CommonsMultipartFile) multipartRequest.getFile(param);
			if(fileStream==null || fileStream.getBytes().length==0){
				continue;
			}
			String orname = fileStream.getOriginalFilename();
			
			int index = orname.lastIndexOf(".");
			String suffix = "";
			if(index !=-1){
				suffix = orname.substring(index);
			}
			String timeStr =new SimpleDateFormat("MMddHHmmss").format(new Date());
			String newName =  prefix+"_"+timeStr+"_"+userStr+i+suffix;
			strs[i]=newName;
			try {
				//暂时先上传到本地
				String dir=LssConfig.get().getUpfile();//"E://var/local/up_dirs/app_file";
				FileCopyUtils.copy(fileStream.getBytes(), new File(dir+"/"+newName));
				
				//上传到七牛服务器
//				bis=fileStream.getInputStream();
//				QiNiuUploadPicUtil.upPic(newName,BUCKET, 3600l, true,bis);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return strs;
	}
	
	/**
	 * 上传单个文件
	 * @param request
	 * @param fileId
	 * @param prefix 前缀
	 * @return
	 */
	public  static String  uploadFile(HttpServletRequest request,String fileId,String prefix){
		String [] str=uploadFiles(request, new String[]{fileId},prefix);
		return str[0];
	}
	
	
}
