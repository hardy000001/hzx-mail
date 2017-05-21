package com.lq.lss.controller.print;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.annotation.Resource;
import com.lq.lss.controller.shiro.ShiroBaseController;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.lss.model.CStockOut;
import com.lq.lss.model.CStockOutDetail;
import com.lq.lss.model.CStockTransfer;
import com.lq.lss.model.CStockTransferDetail;
import com.lq.util.TimeUtil;
import com.lq.lss.core.service.StockOutDetailService;
import com.lq.lss.core.service.StockOutService;
import com.lq.lss.core.service.StockTransferDetailService;
import com.lq.lss.core.service.StockTransferService;
import com.lq.lss.dto.StockOutDto;

/**
 * 出库单打印
 * @author  作者: hzx
 * @date 创建时间: 2016-12-26下午1:27:18
 */
@Controller
@RequestMapping(value ="/user/print/stockOutPrint.htm")
public class StockOutPrintController extends ShiroBaseController<CStockOut, String, StockOutService> {
	
	@Resource
	private StockOutService stockOutService;
	@Resource
	private StockOutDetailService stockOutDetailService;
	@Resource
	private StockTransferService stockTransferService;
	@Resource
	private StockTransferDetailService stockTransferDetailService;
	
	
	@Value("/print/stock_out_print")
	private String printView;
	
	
	@RequestMapping(params = "method=printInfo")
	public ModelAndView printInfo(StockOutDto stockOutDto,
			HttpServletRequest request, HttpServletResponse response) {

		    String outSerialno=stockOutDto.getOutSerialno();
			Map<String, Object> modelMap = new HashMap<String, Object>();
		
			List<CStockOutDetail> detailList = null;
			
			if (StringUtils.hasText(outSerialno)) {
				detailList = stockOutDetailService.queryStockOutListById(outSerialno);
			}
			float a=(float)detailList.size()/26;		
			double fenye = Math.ceil(a);
			System.out.println("分页++++++++++++++++++"+fenye);
			if(stockOutDto.getTransDate() !=null){
				Date cdate=TimeUtil.get().parseTime(stockOutDto.getTransDate());
				stockOutDto.setJytime(cdate);
				}

			modelMap.put("stockOut", stockOutDto);
			modelMap.put("detailList", detailList);
			modelMap.put("fenye", fenye);
			modelMap.put("totalList", totalOutList(detailList));
			
			//中心内部调拨
			String relSerialno=stockOutDto.getRelSerialno();
			if(StringUtils.hasLength(relSerialno) && relSerialno.length()>4)
			{
				CStockTransfer cStockTransfer=stockTransferService.get(relSerialno);
				
				List<CStockTransferDetail> outDetails = null;
				outDetails = stockTransferDetailService.queryDetailList(relSerialno);
				
				float b=(float)detailList.size()/26;
				
				double outPager = Math.ceil(b);
				System.out.println("分页++++++++++++++++++"+outPager);
				
				modelMap.put("cStockTransfer", cStockTransfer);
				modelMap.put("outDetails", outDetails);
				modelMap.put("outPager", outPager);
			    List<CStockTransferDetail> outTotalList = StockTransferOutPrintController
					.totalInList(outDetails);
				modelMap.put("outTotalList", outTotalList);
			}else{
				modelMap.put("outPager", 0);
				modelMap.put("cStockTransfer", new CStockTransfer());
			}
			
		    return new ModelAndView(printView, modelMap);
	}
	
	private List<CStockOutDetail> totalOutList(List<CStockOutDetail> detailList)
	{
		    List<CStockOutDetail> totalList=new ArrayList<CStockOutDetail>();
		    List<CStockOutDetail> dataList=null;
		    Map<String, List<CStockOutDetail>> modelMap = new TreeMap<String, List<CStockOutDetail>>();
		   
		    for (CStockOutDetail cStockOutDetail : detailList) 
		    {
					if(modelMap.get(cStockOutDetail.getTypeId())==null)
					{
							dataList=new ArrayList<CStockOutDetail>();
							dataList.add(cStockOutDetail);
							modelMap.put(cStockOutDetail.getTypeId(), dataList);
					}else{
							dataList.add(cStockOutDetail);
							modelMap.put(cStockOutDetail.getTypeId(),dataList);
					}
			}
		    
		    for (Map.Entry<String, List<CStockOutDetail>> entry : modelMap.entrySet()) 
		    {
		    	   
		    	   CStockOutDetail cStockOutDetail2=new CStockOutDetail();
		    	   List<CStockOutDetail> typeList=entry.getValue();
		    	   Double total=0.0;
		    	   Double num=0.0;
		    	   Double ratio=0.0;
		    	   String fname="";
		    	   String convertFlag="";
		    	   for (CStockOutDetail cStockOutDetail : typeList) 
		    	   {
		    		   num=Double.parseDouble(cStockOutDetail.getTotalS()+"");
		    		   ratio=Double.parseDouble(cStockOutDetail.getCovertRatio());
		    		   total=total+num*ratio;
		    		   fname=cStockOutDetail.getFname();
		    		   convertFlag=cStockOutDetail.getConvertFlag();
				   }
		    	   cStockOutDetail2.setFname(fname);
		    	   cStockOutDetail2.setTotalS(new BigDecimal(total));
		    	   cStockOutDetail2.setConvertFlag(convertFlag);
		    	   totalList.add(cStockOutDetail2);
		    }
		    
		    return totalList;
	}
	
}
