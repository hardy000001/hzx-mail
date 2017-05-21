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
import com.lq.lss.core.service.StockSendDetailService;
import com.lq.lss.core.service.StockSendService;
import com.lq.lss.dto.CStockSendDto;
import com.lq.lss.model.CStockSend;
import com.lq.lss.model.CStockSendDetail;
import com.lq.util.TimeUtil;

/**
 * 入库单打印
 * @author  作者: CH
 * @date 创建时间: 
 */
@Controller
@RequestMapping(value = "/user/print/stockSendPrint.htm")
public class StockSendPrintController extends
		EasyUIController<CStockSend, String, StockSendService> {


	@Resource
	private StockSendService stocksendService;
	@Resource
	private StockSendDetailService stockSendDetailService;

	@Value("/print/stock_send_print")
	private String printView;

	
	
	@RequestMapping(params = "method=printInfo")
	public ModelAndView printInfo(CStockSendDto cStockSendDto,
			HttpServletRequest request, HttpServletResponse response) {

		    String sendSerialno=cStockSendDto.getSendSerialno();
			Map<String, Object> modelMap = new HashMap<String, Object>();
			if(cStockSendDto.getSendtime() !=null){
			Date cdate=TimeUtil.get().parseTime(cStockSendDto.getSendtime());
			cStockSendDto.setJytime(cdate);
			}
			List<CStockSendDetail> detailList = null;
			
			if (StringUtils.hasText(sendSerialno)) {
				detailList = stockSendDetailService.findCStockSendDetailbySerialno(sendSerialno);
			}
			float a=(float)detailList.size()/26;
			
			double fenye = Math.ceil(a);
			System.out.println("分页++++++++++++++++++"+fenye);
			
			modelMap.put("stockSend", cStockSendDto);
			modelMap.put("detailList", detailList);
			modelMap.put("fenye", fenye);
			
			modelMap.put("totalList", totalInList(detailList));
			
		    return new ModelAndView(printView, modelMap);
	}
	
	private List<CStockSendDetail> totalInList(List<CStockSendDetail> detailList)
	{
		    List<CStockSendDetail> totalList=new ArrayList<CStockSendDetail>();
		    List<CStockSendDetail> dataList=null;
		    Map<String, List<CStockSendDetail>> modelMap = new TreeMap<String, List<CStockSendDetail>>();
		   
		    for (CStockSendDetail cStockSendDetail : detailList) 
		    {
		    	   
					if(modelMap.get(cStockSendDetail.getTypeId())==null)
					{
							dataList=new ArrayList<CStockSendDetail>();
							dataList.add(cStockSendDetail);
							modelMap.put(cStockSendDetail.getTypeId(), dataList);
					}else{
							dataList.add(cStockSendDetail);
							modelMap.put(cStockSendDetail.getTypeId(),dataList);
					}
			}
		    
		    for (Map.Entry<String, List<CStockSendDetail>> entry : modelMap.entrySet()) 
		    {
		    	   
		    	CStockSendDetail cStockSendDetail2=new CStockSendDetail();
		    	   List<CStockSendDetail> typeList=entry.getValue();
		    	   Double total=0.0;
		    	   Double num=0.0;
		    	   Double ratio=0.0;
		    	   String fname="";
		    	   String convertFlag="";
		    	   for (CStockSendDetail cStockSendDetail : typeList) 
		    	   {
		    		   num=Double.parseDouble(cStockSendDetail.getTotalS()+"");
		    		   ratio=Double.parseDouble(cStockSendDetail.getCovertratio()+"");
		    		   total=total+num*ratio;
		    		   fname=cStockSendDetail.getFname();
		    		   convertFlag=cStockSendDetail.getConvertFlag();
				   }
		    	   cStockSendDetail2.setFname(fname);
		    	   cStockSendDetail2.setTotalS(new BigDecimal(total));
		    	   cStockSendDetail2.setConvertFlag(convertFlag);
		    	   totalList.add(cStockSendDetail2);
		    }
		    
		    return totalList;
	}

}
