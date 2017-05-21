/**
 * 
 */
package com.lq.lss.controller.print;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.easyui.controller.easyui.EasyUIController;
import com.lq.lss.core.service.StockInDetailService;
import com.lq.lss.core.service.StockInService;
import com.lq.lss.core.service.StockSendDetailService;
import com.lq.lss.core.service.StockSendService;
import com.lq.lss.core.service.StockTemporaryDetailService;
import com.lq.lss.core.service.StockTemporaryService;
import com.lq.lss.dto.CStockSendDto;
import com.lq.lss.dto.CStockTemporaryDto;
import com.lq.lss.dto.StockInDto;
import com.lq.lss.model.CStockIn;
import com.lq.lss.model.CStockInDetail;
import com.lq.lss.model.CStockSend;
import com.lq.lss.model.CStockSendDetail;
import com.lq.lss.model.CStockTemporaryDetail;

/**
 * 入库单打印
 * @author  作者: CH
 * @date 创建时间: 
 */
@Controller
@RequestMapping(value = "/user/print/stockTemporaryPrint.htm")
public class StockTemporaryPrintController extends
		EasyUIController<CStockSend, String, StockSendService> {


	@Resource
	private StockTemporaryService srrservice;
	@Resource
	private StockTemporaryDetailService stdservice;

	@Value("/print/stock_temporary_print")
	private String printView;

	
	
	@RequestMapping(params = "method=printInfo")
	public ModelAndView printInfo(CStockTemporaryDto cStockTemporaryDto,
			HttpServletRequest request, HttpServletResponse response) {

		    String temSerialno=cStockTemporaryDto.getTemSerialno();
			Map<String, Object> modelMap = new HashMap<String, Object>();
		
			List<CStockTemporaryDetail> detailList = null;
			
			if (StringUtils.hasText(temSerialno)) {
				detailList = stdservice.findCstdpBytemSerialno(temSerialno);
			}
			
			modelMap.put("stockTemporary", cStockTemporaryDto);
			modelMap.put("detailList", detailList);
			modelMap.put("totalList", totalInList(detailList));
			
		    return new ModelAndView(printView, modelMap);
	}
	
	private List<CStockTemporaryDetail> totalInList(List<CStockTemporaryDetail> detailList)
	{
		    List<CStockTemporaryDetail> totalList=new ArrayList<CStockTemporaryDetail>();
		    List<CStockTemporaryDetail> dataList=null;
		    Map<String, List<CStockTemporaryDetail>> modelMap = new HashMap<String, List<CStockTemporaryDetail>>();
		   
		    for (CStockTemporaryDetail stockTemporaryDetail : detailList) 
		    {
		    	   
					if(modelMap.get(stockTemporaryDetail.getTypeId())==null)
					{
							dataList=new ArrayList<CStockTemporaryDetail>();
							dataList.add(stockTemporaryDetail);
							modelMap.put(stockTemporaryDetail.getTypeId(), dataList);
					}else{
							dataList.add(stockTemporaryDetail);
							modelMap.put(stockTemporaryDetail.getTypeId(),dataList);
					}
			}
		    
		    for (Map.Entry<String, List<CStockTemporaryDetail>> entry : modelMap.entrySet()) 
		    {
		    	   
		    	CStockTemporaryDetail cStockTemporaryDetail2=new CStockTemporaryDetail();
		    	   List<CStockTemporaryDetail> typeList=entry.getValue();
		    	   Double total=0.0;
		    	   Double num=0.0;
		    	   Double ratio=0.0;
		    	   String fname="";
		    	   String convertFlag="";
		    	   for (CStockTemporaryDetail cStockTemporaryDetail : typeList) 
		    	   {
		    		   num=Double.parseDouble(cStockTemporaryDetail.getTotalS()+"");
		    		   ratio=Double.parseDouble(cStockTemporaryDetail.getCovertratio()+"");
		    		   total=total+num*ratio;
		    		   fname=cStockTemporaryDetail.getFname();
		    		   convertFlag=cStockTemporaryDetail.getConvertFlag();
				   }
		    	   cStockTemporaryDetail2.setFname(fname);
		    	   cStockTemporaryDetail2.setTotalS(new BigDecimal(total));
		    	   cStockTemporaryDetail2.setConvertFlag(convertFlag);
		    	   totalList.add(cStockTemporaryDetail2);
		    }
		    
		    return totalList;
	}

}
