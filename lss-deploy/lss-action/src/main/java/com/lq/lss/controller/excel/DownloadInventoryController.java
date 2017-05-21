package com.lq.lss.controller.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.DownloadFileUtil;
import com.lq.lss.controller.util.LoginSessionUtils;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.lq.lss.model.CStockInventory;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.LssConfig;
import com.lq.lss.core.service.StockInventoryDetailService;
import com.lq.lss.core.service.StockInventoryService;
import com.lq.lss.dto.StockInventoryDetailDto;

/**
 * 下载盘点Excel
 */
@Controller
@RequestMapping(value ="/user/excel/downloadInventory.htm")
public class DownloadInventoryController extends ShiroBaseController<CStockInventory, String, StockInventoryService>{
	
	@Resource
	private StockInventoryService stockInventoryService;
	@Resource
	private StockInventoryDetailService stockInventoryDetailService;
	
	private static String savePath=LssConfig.get().getUpfile();
	
	
	@RequestMapping(params = "method=getInventoryTemp")
	public ModelAndView getInventoryTemp(HttpServletRequest request, HttpServletResponse response) {

		SessionUser user = LoginSessionUtils.getUserInSession(request);
		String deptId=String.valueOf(user.getCenterId());
		String mchcode = ServletRequestUtils.getStringParameter(request,
				"mchcode", "");
        List<StockInventoryDetailDto> detialList = null;
        DateFormat fdt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String fileName="temp.xls";
        //得到模板文件
      	String tplPath = request.getSession().getServletContext().getRealPath("/");
      	String fullPath	= tplPath+"ftl/excel/"+fileName;
      	
      	try 
      	{
      		
			HSSFWorkbook book = new HSSFWorkbook(new FileInputStream(fullPath));
			
			String absPath = savePath+"/"+fileName;
			OutputStream os  = new FileOutputStream(absPath);
			 // 取得第一张表
	        HSSFSheet sheet = book.getSheetAt(0);
			int ROWNUM_DATA = 3;    // 数据的起始行位置 
			    
			//String[] headers=getMapData(type).get("title").toString().split(",");
			String[] props="materialcode,name,realTotalS".split(",");
			
			//写title
		    HSSFRow row = sheet.createRow(ROWNUM_DATA);
		    
		    if(StringUtils.hasText(mchcode))
		    {
				 detialList = stockInventoryDetailService.queryInventoryByMchcode(deptId, mchcode);
				 for (StockInventoryDetailDto inventoryDetailDto : detialList)
				 {
					 row = sheet.createRow(ROWNUM_DATA++);
					 Object to =inventoryDetailDto;
					 String valus [] = getBeanValues(to, props,fdt);
					 for (int po = 0; po < valus.length; po++) {
						 HSSFCell cell = row.createCell(po);
						 cell.setCellValue(valus[po]);
					 }
				 }
				 
			}
		    
		    book.write(os);
			os.flush();
			os.close();
			DownloadFileUtil.downloadFile(request, response, fileName,savePath);
		} catch (FileNotFoundException e) {
				logger.error("没有找到模板");
				e.printStackTrace();
		} catch (IOException e) {
				logger.error("读取模板失败");
				e.printStackTrace();
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		return null;
	}
	public  String[] getBeanValues(Object t,String props[],DateFormat dataFmt) throws Exception{
        String[]   values   =   new   String[props.length]; 
        Map<String,String> map = beanToMap(t, dataFmt);
	      for   (int   i   =   0;   i   <   props.length;   i++)   { 
	    	 String v =  map.get(props[i]);
	    	 values[i] = v;
	      }
		return values; 
	}
	public Map<String,String> beanToMap(Object t,DateFormat dataFmt) throws Exception{
		Field   fields[]   =   t.getClass().getDeclaredFields(); 
        Map<String,String> map = new HashMap<String,String>();
	      Field.setAccessible(fields,   true);
	      for   (int   i   =   0;   i   <   fields.length;   i++)   { 
	    	 Object o =  fields[i].get(t); 
	    	 String name = fields[i].getName();
	    	 if(o == null ){
	    		  map.put(name,  ""); 
	    	 }
	    	 else if(o instanceof Date){
	    		  map.put(name,  dataFmt.format(o) ); 
	    	 }
	    	 else{
	    		 map.put(name,  String.valueOf(o));
	    	 }
	           
	      }
	      return map;
	}
	
}
