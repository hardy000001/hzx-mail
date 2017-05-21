package com.lq.lss.controller.print;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import com.lq.lss.controller.shiro.ShiroBaseController;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.lss.model.CStockInventory;
import com.lq.lss.core.service.StockInventoryDetailService;
import com.lq.lss.core.service.StockInventoryService;
import com.lq.lss.dto.StockInventoryDetailDto;
import com.lq.lss.dto.StockInventoryDto;

/**
 * 盘点打印
 * @author  作者: hzx
 * @date 创建时间: 2016-12-29 13:26:15
 */
@Controller
@RequestMapping(value ="/user/print/stockInventoryPrint.htm")
public class StockInventoryPrintController extends ShiroBaseController<CStockInventory, String, StockInventoryService>{
	
	@Resource
	private StockInventoryService stockInventoryService;
	@Resource
	private StockInventoryDetailService stockInventoryDetailService;
	
	
	@Value("/print/stock_inventory_print")
	private String printView;
	
	
	@RequestMapping(params = "method=printInfo")
	public ModelAndView printInfo(StockInventoryDto stockInventoryDto,
			HttpServletRequest request, HttpServletResponse response) {

		    String inventorySerialno=stockInventoryDto.getInventorySerialno();
			Map<String, Object> modelMap = new HashMap<String, Object>();
		
			List<StockInventoryDetailDto> detailList = null;
			
			if (StringUtils.hasText(inventorySerialno)) {
				detailList = stockInventoryDetailService.queryInventoryDetailList(inventorySerialno);
			}
			
			modelMap.put("inventory", stockInventoryDto);
			modelMap.put("detailList", detailList);
			modelMap.put("totalList", totalList(detailList));
			
		    return new ModelAndView(printView, modelMap);
	}
	
	private List<StockInventoryDetailDto> totalList(List<StockInventoryDetailDto> detailList)
	{
		    List<StockInventoryDetailDto> totalList=new ArrayList<StockInventoryDetailDto>();
		    List<StockInventoryDetailDto> dataList=null;
		    Map<String, List<StockInventoryDetailDto>> modelMap = new TreeMap<String, List<StockInventoryDetailDto>>();
		   
		    for (StockInventoryDetailDto stockInventoryDetailDto : detailList) 
		    {
		    	   
					if(modelMap.get(stockInventoryDetailDto.getTypeId())==null)
					{
							dataList=new ArrayList<StockInventoryDetailDto>();
							dataList.add(stockInventoryDetailDto);
							modelMap.put(stockInventoryDetailDto.getTypeId(), dataList);
					}else{
							dataList.add(stockInventoryDetailDto);
							modelMap.put(stockInventoryDetailDto.getTypeId(),dataList);
					}
			}
		    
		    for (Map.Entry<String, List<StockInventoryDetailDto>> entry : modelMap.entrySet()) 
		    {
		    	   
		    	   StockInventoryDetailDto stockInventoryDetailDto2=new StockInventoryDetailDto();
		    	   List<StockInventoryDetailDto> typeList=entry.getValue();
		    	   Double totalA=0.0;
		    	   Double totalB=0.0;
		    	   Double totalA_B=0.0;
		    	   Double A_B=0.0;
		    	   Double num=0.0;
		    	   Double num2=0.0;
		    	   Double ratio=0.0;
		    	   String fname="";
		    	   String accountFlag="";
		    	   for (StockInventoryDetailDto stockInventoryDetailDto : typeList) 
		    	   {
		    		   num=Double.parseDouble(stockInventoryDetailDto.getRealTotalS()+"");
		    		   num2=Double.parseDouble(stockInventoryDetailDto.getAccTotalS()+"");
		    		   ratio=Double.parseDouble(stockInventoryDetailDto.getCovertRatio()+"");
		    		   totalA+=num*ratio;
		    		   totalB+=num2*ratio;
		    		   fname=stockInventoryDetailDto.getFname();
		    		   accountFlag=stockInventoryDetailDto.getAccountFlag();
		    		   A_B=num-num2;
		    		  
		    		   stockInventoryDetailDto.setTotalM(String.format("%.2f", A_B));
		    		   totalList.add(stockInventoryDetailDto);
		    		  
				   }
		    	   totalA_B=totalA-totalB;
		    	   stockInventoryDetailDto2.setMaterialcode("小计");
		    	   stockInventoryDetailDto2.setName(fname);
		    	   stockInventoryDetailDto2.setUnit(accountFlag);
		    	   stockInventoryDetailDto2.setRealTotalS(String.format("%.4f", totalA));
		    	   stockInventoryDetailDto2.setAccTotalS(String.format("%.4f", totalB));
		    	   stockInventoryDetailDto2.setTotalM(String.format("%.4f", totalA_B));
		    	   stockInventoryDetailDto2.setAccountFlag(accountFlag);
		    	   totalList.add(stockInventoryDetailDto2);
		    }
		    
		    return totalList;
	}
	
	
}
