/**
 * 
 */
package com.lq.lss.controller.print;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.easyui.controller.easyui.EasyUIController;
import com.lq.lss.core.service.RepairInfoService;
import com.lq.lss.core.service.StockInDetailService;
import com.lq.lss.core.service.StockInService;
import com.lq.lss.dto.StockInDto;
import com.lq.lss.model.BRepairInfo;
import com.lq.lss.model.CStockIn;
import com.lq.lss.model.CStockInDetail;
import com.lq.lss.utils.SystemConst;
import com.lq.util.TimeUtil;

/**
 * 入库单打印
 * @author  作者: hzx
 * @date 创建时间: 2016-12-26下午3:13:43
 */
@Controller
@RequestMapping(value = "/user/print/stocktemporaryInPrint.htm")
public class StockTemporaryInPrintController extends
		EasyUIController<CStockIn, String, StockInService> {


	@Resource
	private StockInService stockInService;
	@Resource
	private StockInDetailService stockInDetailService;
	@Resource
	private RepairInfoService repairInfoService;

	@Value("/print/stock_temporary_in_print")
	private String printView;

	
	@RequestMapping(params = "method=printInfo")
	public ModelAndView printInfo(StockInDto stockInDto,
			HttpServletRequest request, HttpServletResponse response) {

		    String inSerialno=stockInDto.getInSerialno();
			Map<String, Object> modelMap = new HashMap<String, Object>();
		
			List<Map<String, Object>> detailList = null;
			
			if (StringUtils.hasText(inSerialno)) {
				detailList = stockInDetailService.getInDetailListById(inSerialno);
			}
			
			float a=(float)detailList.size()/70;		
			double fenye = Math.ceil(a);
			System.out.println("分页++++++++++++++++++"+fenye);
			if(stockInDto.getTransDate() !=null){
				Date cdate=TimeUtil.get().parseTime(stockInDto.getTransDate());
				stockInDto.setJytime(cdate);
				}
			modelMap.put("stockIn", stockInDto);
			List<BRepairInfo> repairInfos=repairInfoService.queryRepairInfoByTypeid("");
			modelMap.put("detailList", detailList);
			modelMap.put("fenye", fenye);
			
		   
			modelMap.put("totalList", totalInList(detailList,repairInfos));
			
		    return new ModelAndView(printView, modelMap);
	}
	
	public static List<Map<String, Object>> totalInList(
			List<Map<String, Object>> detailList, List<BRepairInfo> repairInfos)
	{
		    List<Map<String, Object>> totalList=new ArrayList<Map<String, Object>>();
		    List<Map<String, Object>> dataList=null;
		    Map<String, List<Map<String, Object>>> modelMap = new TreeMap<String, List<Map<String, Object>>>();
		   
		    for (Map<String, Object> receiptDetail : detailList) 
		    {
					if(modelMap.get(receiptDetail.get("typeId"))==null)
					{
							dataList=new ArrayList<Map<String, Object>>();
							dataList.add(receiptDetail);
							modelMap.put(receiptDetail.get("typeId")+"", dataList);
					}else{
							dataList.add(receiptDetail);
							modelMap.put(receiptDetail.get("typeId")+"",dataList);
					}
			}
		    //所有维修信息
		    StringBuilder sbr=new StringBuilder();
		    
		    for (Map.Entry<String, List<Map<String, Object>>> entry : modelMap.entrySet()) 
		    {
		    	   
		    	   Map<String, Object> cStockReceiptDetail2=new HashMap<String, Object>();
		    	   List<Map<String, Object>> typeList=entry.getValue();
		    	   Double total=0.0;
		    	   Double num=0.0;
		    	   Double ratio=0.0;
		    	   String fname="";
		    	   String convertFlag="";
		    	   String typeId="";
		    	   //用于存储维修项的统计数据
		    	   Map<String, Object> mapNums=new HashMap<String, Object>();
		    	   //大类中的维修信息
		    	   StringBuffer sbf=new StringBuffer();
		    	   Map<String, Object> typeMap=new TreeMap<String, Object>();
		    	   for (Map<String, Object> cStockReceiptDetail : typeList) 
		    	   {
		    		   
		    		   num=Double.parseDouble(cStockReceiptDetail.get("totalS")+"");
		    		   ratio=Double.parseDouble(cStockReceiptDetail.get("covertRatio")+"");
		    		   total=total+num*ratio;
		    		   fname=cStockReceiptDetail.get("fname")+"";
		    		   convertFlag=cStockReceiptDetail.get("convertFlag")+"";
		    		   typeId=cStockReceiptDetail.get("typeId")+"";
		    		   
					   for (BRepairInfo bRepairInfo : repairInfos) 
					   {
						    String _key=SystemConst.PROPERTY_PREFIX+ bRepairInfo.getId();
							Object obj = cStockReceiptDetail.get(_key);
							if (obj != null) 
							{
								Object obj2 = mapNums.get(_key);
								double mapNum=obj2!=null?(Double)obj2:0;
								mapNum=mapNum+Double.parseDouble(obj+"");
								mapNums.put(_key, mapNum);
						        typeMap.put(bRepairInfo.getName(),
								bRepairInfo.getName() + "" + mapNums.get(_key)+ ";");
							}
					   }
				   }
		    	   //
		    	   for (Map.Entry<String, Object> map : typeMap.entrySet()) {
		    		   sbf.append(map.getValue());
		    	   }
		    	   //添加大类
		    	   if(StringUtils.hasText(sbf))
		    	   {
		    		   sbr.append("【"+fname+"】"+sbf+"<br/>");
		    	   }
		    	   
		    	   cStockReceiptDetail2.put("typeId", typeId);
		    	   cStockReceiptDetail2.put("fname",fname);
		    	   cStockReceiptDetail2.put("totalS",new BigDecimal(total));
		    	   cStockReceiptDetail2.put("convertFlag",convertFlag);
		    	   totalList.add(cStockReceiptDetail2);
		    }
			       //添加维修项
		    	   Map<String, Object> cStockReceiptDetail3=new HashMap<String, Object>();
		    	   cStockReceiptDetail3.put("fname",sbr.toString());
		    	   totalList.add(cStockReceiptDetail3);
		    
		    return totalList;
	}
}
