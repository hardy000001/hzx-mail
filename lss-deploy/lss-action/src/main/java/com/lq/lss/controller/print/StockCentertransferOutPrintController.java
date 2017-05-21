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
import com.lq.lss.core.service.StockCenterTransferDetailService;
import com.lq.lss.core.service.StockCenterTransferService;
import com.lq.lss.core.service.StockSendService;
import com.lq.lss.dto.CStockCenterTransferDto;
import com.lq.lss.model.CStockCenterTransferDetail;
import com.lq.lss.model.CStockSend;
import com.lq.util.TimeUtil;

/**
 * 入库单打印
 * @author  作者: CH
 * @date 创建时间: 
 */
@Controller
@RequestMapping(value = "/user/print/stockCentertransferPrint.htm")
public class StockCentertransferOutPrintController extends
		EasyUIController<CStockSend, String, StockSendService> {


	@Resource
	private StockCenterTransferService centerTransferService;
	@Resource
	private StockCenterTransferDetailService centerTransferDetailService;

	@Value("/print/stock_centertransfer_print")
	private String printView;

	
	
	@RequestMapping(params = "method=printInfo")
	public ModelAndView printInfo(CStockCenterTransferDto cStockCenterTransferDto,
			HttpServletRequest request, HttpServletResponse response) {

		    String tsfSerialno=cStockCenterTransferDto.getTsfSerialno();
			Map<String, Object> modelMap = new HashMap<String, Object>();
		
			List<CStockCenterTransferDetail> detailList = null;
			
			if (StringUtils.hasText(tsfSerialno)) {
				detailList = centerTransferDetailService.findCsctdBytsfSerialno(tsfSerialno);
			}
			float a=(float)detailList.size()/26;		
			double fenye = Math.ceil(a);
			System.out.println("分页++++++++++++++++++"+fenye);
			if(cStockCenterTransferDto.getTsfSdate() !=null){
				Date cdate=TimeUtil.get().parseTime(cStockCenterTransferDto.getTsfSdate());
				cStockCenterTransferDto.setJytime(cdate);
				}
			modelMap.put("stocCenterTransfer", cStockCenterTransferDto);
			modelMap.put("detailList", detailList);
			modelMap.put("totalList", totalInList(detailList));
			modelMap.put("fenye", fenye);
			
		    return new ModelAndView(printView, modelMap);
	}
	
	private List<CStockCenterTransferDetail> totalInList(List<CStockCenterTransferDetail> detailList)
	{
		    List<CStockCenterTransferDetail> totalList=new ArrayList<CStockCenterTransferDetail>();
		    List<CStockCenterTransferDetail> dataList=null;
		    Map<String, List<CStockCenterTransferDetail>> modelMap = new TreeMap<String, List<CStockCenterTransferDetail>>();
		   
		    for (CStockCenterTransferDetail cStockCenterTransferDetail : detailList) 
		    {
		    	   
					if(modelMap.get(cStockCenterTransferDetail.getTypeId())==null)
					{
							dataList=new ArrayList<CStockCenterTransferDetail>();
							dataList.add(cStockCenterTransferDetail);
							modelMap.put(cStockCenterTransferDetail.getTypeId(), dataList);
					}else{
							dataList.add(cStockCenterTransferDetail);
							modelMap.put(cStockCenterTransferDetail.getTypeId(),dataList);
					}
			}
		    
		    for (Map.Entry<String, List<CStockCenterTransferDetail>> entry : modelMap.entrySet()) 
		    {
		    	   
		    	CStockCenterTransferDetail cStockCenterTransferDetail2=new CStockCenterTransferDetail();
		    	   List<CStockCenterTransferDetail> typeList=entry.getValue();
		    	   Double total=0.0;
		    	   Double num=0.0;
		    	   Double ratio=0.0;
		    	   String fname="";
		    	   String convertFlag="";
		    	   for (CStockCenterTransferDetail cStockCenterTransferDetail : typeList) 
		    	   {
		    		   num=Double.parseDouble(cStockCenterTransferDetail.getTotalS()+"");
		    		   ratio=Double.parseDouble(cStockCenterTransferDetail.getCovertratio()+"");
		    		   total=total+num*ratio;
		    		   fname=cStockCenterTransferDetail.getFname();
		    		   convertFlag=cStockCenterTransferDetail.getConvertFlag();
				   }
		    	   cStockCenterTransferDetail2.setFname(fname);
		    	   cStockCenterTransferDetail2.setTotalS(new BigDecimal(total));
		    	   cStockCenterTransferDetail2.setConvertFlag(convertFlag);
		    	   totalList.add(cStockCenterTransferDetail2);
		    }
		    
		    return totalList;
	}

}
