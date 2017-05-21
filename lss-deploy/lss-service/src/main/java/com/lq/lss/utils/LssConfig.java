package com.lq.lss.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LssConfig {

	private static LssConfig instance = new LssConfig();

	private String upfile;
	
	
	private LssConfig() {
		Properties props = new Properties();
		try {
			InputStream fis = new FileInputStream("/opt/lq/lss/config.properties");
			props.load(fis);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
		
		//上传目录
		this.upfile = props.getProperty("lss.upfile");
		
	}

	public static LssConfig get() {
		return instance;
	}

	public String getUpfile() {
		return upfile;
	}

	public void setUpfile(String upfile) {
		this.upfile = upfile;
	}
	
	
    
}
