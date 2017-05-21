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
import com.lq.lss.core.service.StockTransferDetailService;
import com.lq.lss.core.service.StockTransferService;
import com.lq.lss.dto.CStockTransferDto;
import com.lq.lss.model.CStockTransfer;
import com.lq.lss.model.CStockTransferDetail;
import com.lq.util.TimeUtil;

/**
 * 入库单打印
 * @author  作者: CH
 * @date 创建时间: 
 */
@Controller
@RequestMapping(value = "/user/print/transferoutPrint.htm")
public class StockTransferOutPrintController extends
		EasyUIController<CStockTransfer, String, StockTransferService> {


	@Resource
	private StockTransferService stockTransferService;
	@Resource
	private StockTransferDetailService stockTransferDetailService;

	@Value("/print/transfer_out_print")
	private String printView;

	
	
	@RequestMapping(params = "method=printInfo")
	public ModelAndView printInfo(CStockTransferDto cStockTransferDto,
			HttpServletRequest request, HttpServletResponse response) {

		    String tsfSerialno=cStockTransferDto.getTsfSerialno();
			Map<String, Object> modelMap = new HashMap<String, Object>();
			if(cStockTransferDto.getTsfSdate() !=null){
			Date cdate=TimeUtil.get().parseTime(cStockTransferDto.getTsfSdate());
			cStockTransferDto.setJytime(cdate);
			}
			List<CStockTransferDetail> detailList = null;
			
			if (StringUtils.hasText(tsfSerialno)) {
				detailList = stockTransferDetailService.queryDetailList(tsfSerialno);
			}
			float a=(float)detailList.size()/26;
			
			double fenye = Math.ceil(a);
			System.out.println("分页++++++++++++++++++"+fenye);
			
			modelMap.put("cStockTransfer", cStockTransferDto);
			modelMap.put("detailList", detailList);
			modelMap.put("fenye", fenye);
			
			modelMap.put("totalList", totalInList(detailList));
			
		    return new ModelAndView(printView, modelMap);
	}
	
	protected static List<CStockTransferDetail> totalInList(List<CStockTransferDetail> detailList)
	{
		    List<CStockTransferDetail> totalList=new ArrayList<CStockTransferDetail>();
		    List<CStockTransferDetail> dataList=null;
		    Map<String, List<CStockTransferDetail>> modelMap = new TreeMap<String, List<CStockTransferDetail>>();
		   
		    for (CStockTransferDetail stockTransferDetail : detailList) 
		    {
		    	   
					if(modelMap.get(stockTransferDetail.getTypeId())==null)
					{
							dataList=new ArrayList<CStockTransferDetail>();
							dataList.add(stockTransferDetail);
							modelMap.put(stockTransferDetail.getTypeId(), dataList);
					}else{
							dataList.add(stockTransferDetail);
							modelMap.put(stockTransferDetail.getTypeId(),dataList);
					}
			}
		    
		    for (Map.Entry<String, List<CStockTransferDetail>> entry : modelMap.entrySet()) 
		    {
		    	   
		    	CStockTransferDetail cStockTransferDetail2=new CStockTransferDetail();
		    	   List<CStockTransferDetail> typeList=entry.getValue();
		    	   Double total=0.0;
		    	   Double num=0.0;
		    	   Double ratio=0.0;
		    	   String fname="";
		    	   String convertFlag="";
		    	   for (CStockTransferDetail cStockTransferDetail : typeList) 
		    	   {
		    		   num=Double.parseDouble(cStockTransferDetail.getTotalS()+"");
		    		   ratio=Double.parseDouble(cStockTransferDetail.getCovertratio()+"");
		    		   total=total+num*ratio;
		    		   fname=cStockTransferDetail.getFname();
		    		   convertFlag=cStockTransferDetail.getConvertFlag();
				   }
		    	   cStockTransferDetail2.setFname(fname);
		    	   cStockTransferDetail2.setTotalS(new BigDecimal(total));
		    	   cStockTransferDetail2.setConvertFlag(convertFlag);
		    	   totalList.add(cStockTransferDetail2);
		    }
		    
		    return totalList;
	}

}
