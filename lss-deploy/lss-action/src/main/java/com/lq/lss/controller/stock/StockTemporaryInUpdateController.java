/**
 * 
 */
package com.lq.lss.controller.stock;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.lq.easyui.controller.easyui.EasyUIController;
import com.lq.easyui.dto.ResultDto;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.RepairInfoService;
import com.lq.lss.core.service.StockInDetailService;
import com.lq.lss.core.service.StockInService;
import com.lq.lss.dto.CStockInRepairinfoDto;
import com.lq.lss.dto.StockInDetailDto;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.BRepairInfo;
import com.lq.lss.model.CStockIn;
import com.lq.lss.model.CStockInDetail;
import com.lq.lss.model.SessionUser;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;
import com.lq.util.TimeUtil;

/**
 * @author lanbo
 */
@Controller
@RequestMapping(value = "/user/stock/stocktemporaryinUpdate.htm")
public class StockTemporaryInUpdateController extends EasyUIController<CStockIn, String, StockInService> {
	@Resource
	private StockInService stockInService;	
	@Resource
	private StockInDetailService stockInDetailService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	@Resource
	private RepairInfoService repairInfoService;
	@Value("/stock/stock_temporary_in_update")
	private String indexView;

	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		modelMap.put("add", PermResourceConst.STOCK_TEMPORARY_IN_ADD);
		modelMap.put("update", PermResourceConst.STOCK_TEMPORARY_IN_UPDATE);
		modelMap.put("del", PermResourceConst.STOCK_TEMPORARY_IN_DEL);
		modelMap.put("check", PermResourceConst.STOCK_TEMPORARY_IN_CHECK);
		
		String inSerialno=ServletRequestUtils.getStringParameter(request, "inSerialno", "");
		
		List<BRepairInfo> repairList=repairInfoService.loadAll();
        //获取入库主表数据
		CStockIn cStockIn=stockInService.get(inSerialno);
		
		//获取入库从表数据
		List<Map<String, Object>> detailList=stockInDetailService.getInDetailListById(inSerialno);
		
		modelMap.put("inSerialno", inSerialno);
		modelMap.put("cStockIn",cStockIn);
		modelMap.put("detailList", detailList);
		modelMap.put("repairList", repairList);
		
		
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	@RequestMapping(params = "method=save")
	@RequiresPermissions(PermResourceConst.STOCK_TEMPORARY_IN_UPDATE)
	@Override
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
		
		
	    String itemData =	request.getParameter("itemData");
	    String inSerialno =	request.getParameter("inSerialno");
	    String fromMchcode =	request.getParameter("fromMchcode");
	    String zxFee =	request.getParameter("zxFee");
	    String otherFee =	request.getParameter("otherFee");
	    String transportFee =	request.getParameter("transportFee");
	    String outOperator =	request.getParameter("outOperator");
	    String inOperator =	request.getParameter("inOperator");
	    String remark =	request.getParameter("remark");
	    String transDate =	request.getParameter("transDate");
	    String carNo =	request.getParameter("carNo");
	    String carDriver =	request.getParameter("carDriver");
	    String isrepair =	request.getParameter("isrepair");
	      
		JSONArray json = JSONArray.fromObject(itemData);
		
		@SuppressWarnings("unchecked")
		List<StockInDetailDto> dataList = (List<StockInDetailDto>) JSONArray
				.toCollection(json, StockInDetailDto.class);
		
		List<CStockInRepairinfoDto> repairs = StockInAddController
				.repairItemJsonDispose(itemData, inSerialno);
	    
	    //SessionHelper
	    SessionUser sessionUser = LoginSessionUtils.getUserInSession(request);
	    
	    //入库信息对象
	    CStockIn cStockIn=new CStockIn();
	    cStockIn.setInSerialno(inSerialno);
	    cStockIn.setDeptid(sessionUser.getCenterId());
	    cStockIn.setCreateoper(sessionUser.getOperCode());
	    
	    if(zxFee.isEmpty()){
	    	zxFee="0";
	    }
	    if(transportFee.isEmpty()){
	    	transportFee="0";
	    }
	    if(otherFee.isEmpty()){
	    	otherFee="0";
	    }
	    
	    cStockIn.setMchcode(fromMchcode);
	    cStockIn.setZxFee(new BigDecimal(Double.parseDouble(zxFee)));
	    cStockIn.setOtherFee(new BigDecimal(Double.parseDouble(otherFee)));
	    cStockIn.setCreateoper(sessionUser.getLoginName());
	    cStockIn.setTransportFee(new BigDecimal(Double.parseDouble(transportFee)));
	    cStockIn.setOutOperator(outOperator);
	    cStockIn.setInOperator(inOperator);
	    cStockIn.setRemark(remark);
	    cStockIn.setStatus("1");
	    Date cdate=null;
	    if(transDate!=null || !transDate.equals("")){
	     cdate=TimeUtil.get().parseTime(transDate);
	    }
	    cStockIn.setTransDate(cdate);
	    cStockIn.setCarNo(carNo);
	    cStockIn.setCarDriver(carDriver);
	    cStockIn.setIsrepair(isrepair);
	    
	   

	    //入库更新操作
		ResultDto<?> resultDto
	    = stockInService.updateStockInRdTx(cStockIn, dataList,repairs);
		 adminAuditLogService.log(sessionUser.getUserId(), TradeType.STOCK_TEMPORARY_IN.getType(),"修改暂存单",cStockIn,0L);
		return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
	}

}
