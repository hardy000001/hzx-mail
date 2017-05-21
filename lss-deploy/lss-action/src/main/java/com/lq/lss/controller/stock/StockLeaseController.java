/**
 * 
 */
package com.lq.lss.controller.stock;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.lq.easyui.controller.easyui.EasyUIController;
import com.lq.easyui.dto.ResultDto;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.CStockFlowService;
import com.lq.lss.core.service.CStockRemodelingService;
import com.lq.lss.core.service.MaterialInfoService;
import com.lq.lss.core.service.MaterialTypeService;
import com.lq.lss.core.service.StockLeaseDetailService;
import com.lq.lss.core.service.StockLeaseService;
import com.lq.lss.core.service.StockTemporaryDetailService;
import com.lq.lss.core.service.StockTemporaryService;
import com.lq.lss.model.BConsumableType;
import com.lq.lss.model.BMaterialInfo;
import com.lq.lss.model.BMaterialType;
import com.lq.lss.model.CBusSaleDetail;
import com.lq.lss.model.CStockLease;
import com.lq.lss.model.CStockLeaseDetail;
import com.lq.lss.model.CStockRemodeling;
import com.lq.lss.model.CStockTemporary;
import com.lq.lss.model.CStockTemporaryDetail;
import com.lq.lss.model.EasyUITree;
import com.lq.lss.model.SessionUser;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;


@Controller
@RequestMapping(value = "/user/stock/lease.htm")
public class StockLeaseController extends EasyUIController<CStockLease, String, StockLeaseService> {

	@Resource
	StockLeaseService slservice;
	@Resource
	StockLeaseDetailService sldservice;
	
	@Value("/stock/stockLease")
	private String indexView;

	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		int a = ServletRequestUtils.getIntParameter(request, "a", 0);
		int b = ServletRequestUtils.getIntParameter(request, "b", 0);
		int c = ServletRequestUtils.getIntParameter(request, "c", 0);
		int d = ServletRequestUtils.getIntParameter(request, "d", 0);
		int e = ServletRequestUtils.getIntParameter(request, "e", 0);
		if (a == 0) notAdd = true;
		if (b == 0) notUpdate = true;
		if (c == 0) notDelete = true;
		if (d == 0) notExport = true;
		 if(e==0) notAudit=true;
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", notAdd ? 0 : 1);
		modelMap.put("upd", notUpdate ? 0 : 1);
		modelMap.put("del", notDelete ? 0 : 1);
		modelMap.put("exp", notExport ? 0 : 1);
		modelMap.put("aud", notAudit ? 0 : 1);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}

	@RequestMapping(params = "method=getLeaseDetailList")
	public ModelAndView getLeaseDetailList(HttpServletRequest request,HttpServletResponse response) {
		
		String lsSerialno = ServletRequestUtils.getStringParameter(request,
				"id", "");
		
		List<CStockLeaseDetail> detialList = null;
		
		if (StringUtils.hasText(lsSerialno)) {
			                detialList = sldservice.findCsldBylsSerialno(lsSerialno);
		}

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(detialList));
	}
	
	@RequestMapping(params = "method=removeStockLease")
	public ModelAndView removeStockLease(HttpServletRequest request, HttpServletResponse response){
		ResultDto<String> result = null;
			if (notDelete) {
			result = new ResultDto<String>(false, deleteAlertMsg);
			return AjaxModelAndViewUtils.writeMsgReturnNull(response,
					JSONUtil.toJSonString(result));
			}
			String idStr = ServletRequestUtils.getStringParameter(request, "id", null);
			if (!StringUtils.hasLength(idStr)) {
				result = new ResultDto<String>(false, "id或ids不能为空");
				return AjaxModelAndViewUtils.writeMsgReturnNull(response,
						JSONUtil.toJSonString(result));
			}try {
					result=slservice.deleteCStockLease(idStr);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logger.error("删除采购数据出现异常"+e.getMessage());
				result=new ResultDto<String>(false,"删除数据出现异常");
			}
				
			return AjaxModelAndViewUtils.writeMsgReturnNull(response,
					JSONUtil.toJSonString(result));
			}
}
