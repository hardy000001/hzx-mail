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
import com.lq.lss.model.CBusPur;
import com.lq.lss.model.CBusPurDetail;
import com.lq.lss.core.service.BusPurDetailService;
import com.lq.lss.core.service.BusPurService;
import com.lq.lss.dto.BusPurDto;

/**
 * 采购打印
 * @author  作者: hzx
 * @date 创建时间: 2016-12-26上午10:02:21
 */
@Controller
@RequestMapping(value ="/user/print/purPrint.htm")
public class BusPurPrintController extends ShiroBaseController<CBusPur, String, BusPurService> {
	
	@Resource
	private BusPurService busPurService;
	@Resource
	private BusPurDetailService busPurDetailService;
	
	
	@Value("/print/bus_pur_print")
	private String printView;
	
	
	@RequestMapping(params = "method=printInfo")
	public ModelAndView printInfo(BusPurDto busPurDto,
			HttpServletRequest request, HttpServletResponse response) {

		    String purSerialno=busPurDto.getPurSerialno();
			Map<String, Object> modelMap = new HashMap<String, Object>();
		
			List<CBusPurDetail> detailList = null;
			
			if (StringUtils.hasText(purSerialno)) {
				     detailList = busPurDetailService.queryPurDetailListById(purSerialno);
			}
			
			modelMap.put("busPur", busPurDto);
			modelMap.put("detailList", detailList);
			modelMap.put("totalList", totalPurList(detailList));
			
		    return new ModelAndView(printView, modelMap);
	}
	
	private List<CBusPurDetail> totalPurList(List<CBusPurDetail> detailList)
	{
		    List<CBusPurDetail> totalList=new ArrayList<CBusPurDetail>();
		    List<CBusPurDetail> dataList=null;
		    Map<String, List<CBusPurDetail>> modelMap = new HashMap<String, List<CBusPurDetail>>();
		   
		    for (CBusPurDetail cBusPurDetail : detailList) 
		    {
					if(modelMap.get(cBusPurDetail.getTypeId())==null)
					{
							dataList=new ArrayList<CBusPurDetail>();
							dataList.add(cBusPurDetail);
							modelMap.put(cBusPurDetail.getTypeId(), dataList);
					}else{
							dataList.add(cBusPurDetail);
							modelMap.put(cBusPurDetail.getTypeId(),dataList);
					}
			}
		    
		    for (Map.Entry<String, List<CBusPurDetail>> entry : modelMap.entrySet()) 
		    {
		    	   
		    	   CBusPurDetail cBusPurDetail2=new CBusPurDetail();
		    	   List<CBusPurDetail> typeList=entry.getValue();
		    	   Double total=0.0;
		    	   Double num=0.0;
		    	   Double ratio=0.0;
		    	   String fname="";
		    	   String convertFlag="";
		    	   for (CBusPurDetail cBusPurDetail : typeList) 
		    	   {
		    		   num=Double.parseDouble(cBusPurDetail.getQuantity()+"");
		    		   ratio=Double.parseDouble(cBusPurDetail.getCovertRatio());
		    		   total=total+num*ratio;
		    		   fname=cBusPurDetail.getFname();
		    		   convertFlag=cBusPurDetail.getConvertFlag();
				   }
		    	   cBusPurDetail2.setFname(fname);
		    	   cBusPurDetail2.setTotalAmt(new BigDecimal(total));
		    	   cBusPurDetail2.setConvertFlag(convertFlag);
		    	   totalList.add(cBusPurDetail2);
		    }
		    
		    return totalList;
	}
	
}
