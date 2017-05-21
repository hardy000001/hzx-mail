package com.lq.lss.controller.print;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.annotation.Resource;
import com.lq.lss.controller.shiro.ShiroBaseController;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.lss.model.CBusSale;
import com.lq.lss.model.CBusSaleDetail;
import com.lq.lss.core.service.BusSaleDetailService;
import com.lq.lss.core.service.BusSaleService;
import com.lq.lss.dto.BusSaleDto;

/**
 * 销售打印
 * @author  作者: hzx
 * @date 创建时间: 2016-12-26上午10:18:38
 */
@Controller
@RequestMapping(value ="/user/print/salePrint.htm")
public class BusSalePrintController extends ShiroBaseController<CBusSale, String, BusSaleService> {
	
	@Resource
	private BusSaleService busSaleService;
	@Resource
	private BusSaleDetailService busSaleDetailService;
	
	
	@Value("/print/bus_sale_print")
	private String printView;
	
	
	@RequestMapping(params = "method=printInfo")
	public ModelAndView printInfo(BusSaleDto busSaleDto,
			HttpServletRequest request, HttpServletResponse response) {

		    String saleSerialno=busSaleDto.getSaleSerialno();
			Map<String, Object> modelMap = new HashMap<String, Object>();
		
			List<CBusSaleDetail> detailList = null;
			
			if (StringUtils.hasText(saleSerialno)) {
				detailList = busSaleDetailService.querySaleDetailListById(saleSerialno);
			}
			
			modelMap.put("busSale", busSaleDto);
			modelMap.put("detailList", detailList);
			modelMap.put("totalList", totalSaleList(detailList));
			
		    return new ModelAndView(printView, modelMap);
	}
	
	private List<CBusSaleDetail> totalSaleList(List<CBusSaleDetail> detailList)
	{
		    List<CBusSaleDetail> totalList=new ArrayList<CBusSaleDetail>();
		    List<CBusSaleDetail> dataList=null;
		    Map<String, List<CBusSaleDetail>> modelMap = new HashMap<String, List<CBusSaleDetail>>();
		   
		    for (CBusSaleDetail cBusSaleDetail : detailList) 
		    {
					if(modelMap.get(cBusSaleDetail.getTypeId())==null)
					{
							dataList=new ArrayList<CBusSaleDetail>();
							dataList.add(cBusSaleDetail);
							modelMap.put(cBusSaleDetail.getTypeId(), dataList);
					}else{
							dataList.add(cBusSaleDetail);
							modelMap.put(cBusSaleDetail.getTypeId(),dataList);
					}
			}
		    
		    for (Map.Entry<String, List<CBusSaleDetail>> entry : modelMap.entrySet()) 
		    {
		    	   
		    	   CBusSaleDetail cBusSaleDetail2=new CBusSaleDetail();
		    	   List<CBusSaleDetail> typeList=entry.getValue();
		    	   Double total=0.0;
		    	   Double num=0.0;
		    	   Double ratio=0.0;
		    	   String fname="";
		    	   String convertFlag="";
		    	   for (CBusSaleDetail cBusSaleDetail : typeList) 
		    	   {
		    		   num=Double.parseDouble(cBusSaleDetail.getQuantity()+"");
		    		   ratio=Double.parseDouble(cBusSaleDetail.getCovertRatio());
		    		   total=total+num*ratio;
		    		   fname=cBusSaleDetail.getFname();
		    		   convertFlag=cBusSaleDetail.getConvertFlag();
				   }
		    	   cBusSaleDetail2.setFname(fname);
		    	   cBusSaleDetail2.setTotalAmt(new BigDecimal(total));
		    	   cBusSaleDetail2.setConvertFlag(convertFlag);
		    	   totalList.add(cBusSaleDetail2);
		    }
		    
		    return totalList;
	}
}
