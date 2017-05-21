/**
 * 
 */
package com.lq.lss.controller.stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.lq.lss.core.service.CustomerInfoService;
import com.lq.lss.core.service.AdminDeptService;
import com.lq.lss.core.service.MchBaseinfoService;
import com.lq.lss.core.service.StockSendService;
import com.lq.lss.dto.CStockSendDetailDto;
import com.lq.lss.dto.CStockSendDto;
import com.lq.lss.model.CStockSend;
import com.lq.lss.model.CustomerInfo;
import com.lq.lss.model.AdminDept;
import com.lq.lss.model.SessionUser;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;

import net.sf.json.JSONArray;



@Controller
@RequestMapping(value = "/user/stock/stocksendAdd.htm")
public class StockSendAddController extends EasyUIController<CStockSend, String, StockSendService> {

	
	@Resource
	private MchBaseinfoService mchInfoService;
	@Resource
	private AdminDeptService deptService;
	@Resource
	private CustomerInfoService customerInfoService;
	@Resource
	private StockSendService sendService;
	@Value("/stock/stock_send_add")
	private String indexView;
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		AdminDept adminDept= deptService.get(user.getCenterId());
		if(adminDept==null){
			adminDept=new AdminDept();
		}
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.CENTER_SEND_ADD);
        modelMap.put("update", PermResourceConst.CENTER_SEND_UPDATE);
        modelMap.put("del", PermResourceConst.CENTER_SEND_DEL);
        modelMap.put("check", PermResourceConst.CENTER_SEND_CHECK);
		modelMap.put("user", user);
		modelMap.put("adminDept", adminDept);
		
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}

	
	
	
	
	
		//加载公司
		@RequestMapping(params = "method=getCustomerInfos")
		public ModelAndView getCustomerInfos(HttpServletRequest request, HttpServletResponse response) {
			SessionUser user = LoginSessionUtils.getUserInSession(request); 
		
			List<CustomerInfo> tList = customerInfoService.queryCustomerInfoByDeptIdAndCustype(user.getCenterId()+"");
			
			
			
			return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(tList));
		}

		
		@RequestMapping(params = "method=saveStockSendAll")
		@RequiresPermissions(PermResourceConst.CENTER_SEND_ADD)
		public ModelAndView saveStockSendAll(HttpServletRequest request, HttpServletResponse response,
				CStockSendDto cStockSendDto) {
			SessionUser user = LoginSessionUtils.getUserInSession(request); 
			ResultDto<String> resultDto = null;
			try {
				 cStockSendDto.setDeptid(user.getCenterId());
				 String listStr=request.getParameter("rows");
				 String lsSerialno=request.getParameter("lsSerialno");
				 JSONArray json=JSONArray.fromObject(listStr);
				 List<CStockSendDetailDto> dataList =(List<CStockSendDetailDto>)JSONArray.toCollection(json, CStockSendDetailDto.class);
				 cStockSendDto.setcStockSendDetailDto(dataList);
				 if(cStockSendDto!=null){
					 resultDto= sendService.saveCStockSendRdTx(cStockSendDto, user);
					 
				 }
		
				} catch (Exception e) {
					 logger.error("提交暂存请数据出现异常", e);
					 resultDto=new ResultDto<String>(false,"数据提交失败");
			}
			
			return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
		}
		
	
}
