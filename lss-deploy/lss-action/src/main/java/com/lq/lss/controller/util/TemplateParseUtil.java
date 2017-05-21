package com.lq.lss.controller.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.springframework.core.io.DefaultResourceLoader;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TemplateParseUtil {
	
	public static final String TEMPLATE_PATH = "src/main/resources";
	public static final String ENCODING = "UTF-8";


	/** 
     * 解析模板生成Excel stream
     * @param templateName 模板名称 
     * @param data 数据参数 
     * @throws IOException 
     * @throws TemplateException 
     */  
    public static ByteArrayOutputStream parse(String templateName,Map<String,Object> data,String url) throws IOException, TemplateException {  
        //初始化工作  
        Configuration cfg = new Configuration();  
        //设置默认编码格式为UTF-8  
        cfg.setDefaultEncoding(ENCODING);   
        //全局数字格式  
        cfg.setNumberFormat("0"); 
        //指定模板路径
		org.springframework.core.io.Resource resource =new DefaultResourceLoader().getResource(url);
//        //设置模板文件位置  
        cfg.setDirectoryForTemplateLoading(new File(url));
        cfg.setObjectWrapper(new DefaultObjectWrapper());  
//        //加载模板  
        Template template = cfg.getTemplate(templateName,ENCODING);  
        ByteArrayOutputStream outStream = null;
        Writer out = null;
        try{  
            outStream = new ByteArrayOutputStream();  
            out = new OutputStreamWriter(outStream,ENCODING);  
            template.process(data, out);  
            return outStream;  
        }finally{  
            out.close();  
        }     
    }  
  
  
}
