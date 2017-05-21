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

import com.lq.easyui.dto.ResultDto;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.AdminDeptService;
import com.lq.lss.core.service.StockCenterTransferDetailService;
import com.lq.lss.core.service.StockCenterTransferService;
import com.lq.lss.dto.CStockCenterTransferDetailDto;
import com.lq.lss.dto.CStockCenterTransferDto;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.AdminDept;
import com.lq.lss.model.CStockCenterTransfer;
import com.lq.lss.model.CStockCenterTransferDetail;
import com.lq.lss.model.SessionUser;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;

import net.sf.json.JSONArray;



@Controller
@RequestMapping(value = "/user/stock/centertransferEdit.htm")
public class StockCenterTransferUpdateController extends ShiroBaseController<CStockCenterTransfer, String, StockCenterTransferService> {

	@Resource
	StockCenterTransferService centerTransferService;
	@Resource
	StockCenterTransferDetailService centerTransferDetailService;
	@Resource
	private AdminDeptService deptService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	@Value("/stock/stockCentertransferEdit")
	private String indexView;

	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		String tsfSerialno=ServletRequestUtils.getStringParameter(request, "id", "");
		String loginName=ServletRequestUtils.getStringParameter(request, "loginName", "");
		AdminDept adminDept = deptService.get(user.getCenterId());
	
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.CENTER_CENTERTRANSFER_ADD);
        modelMap.put("update", PermResourceConst.CENTER_CENTERTRANSFER_UPDATE);
        modelMap.put("del", PermResourceConst.CENTER_CENTERTRANSFER_DEL);
        modelMap.put("check", PermResourceConst.CENTER_CENTERTRANSFER_CHECK);
		CStockCenterTransfer  cStockCenterTransfer   =centerTransferService.get(tsfSerialno);
		
		
		List<CStockCenterTransferDetail> detailList= centerTransferDetailService.findCsctdBytsfSerialno(tsfSerialno);
		
		cStockCenterTransfer.setOperName(user.getLoginName());
		cStockCenterTransfer.setLoginName(loginName);
		cStockCenterTransfer.setTsfSerialno(tsfSerialno);
		modelMap.put("cStockCenterTransfer", cStockCenterTransfer);
		modelMap.put("detailList", detailList);
		modelMap.put("adminDept", adminDept);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}

	@RequestMapping(params = "method=cstockCenterTransferUpdate")
	@RequiresPermissions(PermResourceConst.CENTER_CENTERTRANSFER_UPDATE)
	public ModelAndView cstockCenterTransferUpdate(HttpServletRequest request, HttpServletResponse response,CStockCenterTransferDto stockCenterTransferDto) {
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<String> result=null;
		try {
			String listStr=request.getParameter("rows");
			JSONArray json = JSONArray.fromObject(listStr);
			List<CStockCenterTransferDetailDto> dataList =(List<CStockCenterTransferDetailDto>)JSONArray.toCollection(json, CStockCenterTransferDetailDto.class);
			stockCenterTransferDto.setcStockCenterTransferDetaildtos(dataList);
			result=centerTransferService.updateCStockCenterTransferRdTx(stockCenterTransferDto, user);
			adminAuditLogService.log(user.getUserId(), TradeType.STOCKCENTERTRANSFER_OUT.getType(),"修改调出单",stockCenterTransferDto,0L);
		
		} catch (Exception e) {
			logger.error("修改加工改制数据出现异常", e);
			result=new ResultDto<String>(false,"数据提交失败");
		}
		
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
	}
}
