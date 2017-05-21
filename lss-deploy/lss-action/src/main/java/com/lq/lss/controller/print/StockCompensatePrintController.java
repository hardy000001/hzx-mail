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
import com.lq.lss.core.service.CStockCompensateDetailService;
import com.lq.lss.core.service.CStockCompensateService;
import com.lq.lss.core.service.StockInDetailService;
import com.lq.lss.core.service.StockInService;
import com.lq.lss.core.service.StockSendDetailService;
import com.lq.lss.core.service.StockSendService;
import com.lq.lss.core.service.StockTemporaryDetailService;
import com.lq.lss.core.service.StockTemporaryService;
import com.lq.lss.dto.CStockCompensateDetailDto;
import com.lq.lss.dto.CStockCompensateDto;
import com.lq.lss.dto.CStockSendDto;
import com.lq.lss.dto.CStockTemporaryDto;
import com.lq.lss.dto.StockInDto;
import com.lq.lss.model.CStockCompensate;
import com.lq.lss.model.CStockCompensateDetail;
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
@RequestMapping(value = "/user/print/stockCompensatePrint.htm")
public class StockCompensatePrintController extends
		EasyUIController<CStockCompensate, String, CStockCompensateService> {


	@Resource
	private CStockCompensateService scservice;
	@Resource
	private CStockCompensateDetailService scdservice;

	@Value("/print/stock_compensate_print")
	private String printView;

	
	
	@RequestMapping(params = "method=printInfo")
	public ModelAndView printInfo(CStockCompensateDto cStockCompensateDto,
			HttpServletRequest request, HttpServletResponse response) {

		    String compensateSerialno=cStockCompensateDto.getCompensateSerialno();
			Map<String, Object> modelMap = new HashMap<String, Object>();
		
			List<CStockCompensateDetail> detailList = null;
			
			if (StringUtils.hasText(compensateSerialno)) {
				detailList = scdservice.findCStockCompensateDetailByIds(compensateSerialno);
			}
			
			modelMap.put("stockCompensate", cStockCompensateDto);
			modelMap.put("detailList", detailList);
			modelMap.put("totalList", totalInList(detailList));
			
		    return new ModelAndView(printView, modelMap);
	}
	
	private List<CStockCompensateDetail> totalInList(List<CStockCompensateDetail> detailList)
	{
		    List<CStockCompensateDetail> totalList=new ArrayList<CStockCompensateDetail>();
		    List<CStockCompensateDetail> dataList=null;
		    Map<String, List<CStockCompensateDetail>> modelMap = new HashMap<String, List<CStockCompensateDetail>>();
		   
		    for (CStockCompensateDetail stockCompensateDetail : detailList) 
		    {
		    	   
					if(modelMap.get(stockCompensateDetail.getTypeId())==null)
					{
							dataList=new ArrayList<CStockCompensateDetail>();
							dataList.add(stockCompensateDetail);
							modelMap.put(stockCompensateDetail.getTypeId(), dataList);
					}else{
							dataList.add(stockCompensateDetail);
							modelMap.put(stockCompensateDetail.getTypeId(),dataList);
					}
			}
		    
		    for (Map.Entry<String, List<CStockCompensateDetail>> entry : modelMap.entrySet()) 
		    {
		    	   
		    	CStockCompensateDetail stockCompensateDetail2=new CStockCompensateDetail();
		    	   List<CStockCompensateDetail> typeList=entry.getValue();
		    	   Double total=0.0;
		    	   Double num=0.0;
		    	   Double ratio=0.0;
		    	   String fname="";
		    	   String convertFlag="";
		    	   for (CStockCompensateDetail cStockCompensateDetail : typeList) 
		    	   {
		    		   num=Double.parseDouble(cStockCompensateDetail.getTotalS()+"");
		    		   ratio=Double.parseDouble(cStockCompensateDetail.getCovertratio()+"");
		    		   total=total+num*ratio;
		    		   fname=cStockCompensateDetail.getFname();
		    		   convertFlag=cStockCompensateDetail.getConvertFlag();
				   }
		    	   stockCompensateDetail2.setFname(fname);
		    	   stockCompensateDetail2.setTotalS(new BigDecimal(total));
		    	   stockCompensateDetail2.setConvertFlag(convertFlag);
		    	   totalList.add(stockCompensateDetail2);
		    }
		    
		    return totalList;
	}

}
