/**
 * 
 */
package com.lq.lss.controller.stock;


import com.lq.easyui.controller.easyui.EasyUIController;
import com.lq.easyui.dto.ResultDto;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminDeptService;
import com.lq.lss.core.service.StockSendDetailService;
import com.lq.lss.core.service.StockSendService;
import com.lq.lss.dto.CStockSendDetailDto;
import com.lq.lss.dto.CStockSendDto;
import com.lq.lss.model.AdminDept;
import com.lq.lss.model.CStockSend;
import com.lq.lss.model.CStockSendDetail;
import com.lq.lss.model.SessionUser;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;
import net.sf.json.JSONArray;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller
@RequestMapping(value = "/user/stock/stocksendupdate.htm")
public class StockSendUpdateController extends EasyUIController<CStockSend, String, StockSendService> {

	@Resource
	StockSendService stocksendService;
	@Resource
	StockSendDetailService stockSendDetailService;
	
	@Resource
	private AdminDeptService adminDeptService;
	@Value("/stock/stock_send_edit")
	private String indexView;

	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		String sendSerialno=ServletRequestUtils.getStringParameter(request, "id", "");
		String loginName=ServletRequestUtils.getStringParameter(request, "loginName", "");
		AdminDept adminDept= adminDeptService.get(user.getCenterId());
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.CENTER_SEND_ADD);
        modelMap.put("update", PermResourceConst.CENTER_SEND_UPDATE);
        modelMap.put("del", PermResourceConst.CENTER_SEND_DEL);
        modelMap.put("check", PermResourceConst.CENTER_SEND_CHECK);
		CStockSend cStockSend=stocksendService.get(sendSerialno);
		List<CStockSendDetail>  detailList=stockSendDetailService.findCStockSendDetailbySerialno(sendSerialno);
		cStockSend.setOperName(user.getLoginName());
		cStockSend.setLoginName(loginName);
		cStockSend.setSendSerialno(sendSerialno);
		modelMap.put("cStockSend", cStockSend);
		modelMap.put("detailList", detailList);
		modelMap.put("adminDept", adminDept);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}

	
	
	@RequestMapping(params = "method=cstocksendUpdate")
	@RequiresPermissions(PermResourceConst.CENTER_SEND_UPDATE)
	public ModelAndView cstocksendUpdate(HttpServletRequest request, HttpServletResponse response,
			CStockSendDto cStockSendDto) {
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<String> result=null;
		try {
			String listStr=request.getParameter("rows");
			JSONArray json = JSONArray.fromObject(listStr);
			List<CStockSendDetailDto> dataList =(List<CStockSendDetailDto>)JSONArray.toCollection(json, CStockSendDetailDto.class);
			cStockSendDto.setcStockSendDetailDto(dataList);
			result=stocksendService.updateCStockSendRdTx(cStockSendDto, user);
		
		
		} catch (Exception e) {
			logger.error("修改发料改制数据出现异常", e);
			result=new ResultDto<String>(false,"数据提交失败");
		}
		
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
	}
	
}
