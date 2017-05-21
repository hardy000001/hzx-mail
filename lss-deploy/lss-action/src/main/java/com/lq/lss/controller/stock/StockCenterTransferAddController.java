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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.easyui.dto.ResultDto;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.AdminDeptService;
import com.lq.lss.core.service.CustomerInfoService;
import com.lq.lss.core.service.MaterialInfoService;
import com.lq.lss.core.service.MaterialTypeService;
import com.lq.lss.core.service.MchBaseinfoService;
import com.lq.lss.core.service.StockCenterTransferService;
import com.lq.lss.dto.CStockCenterTransferDetailDto;
import com.lq.lss.dto.CStockCenterTransferDto;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.AdminDept;
import com.lq.lss.model.BMaterialInfo;
import com.lq.lss.model.CStockCenterTransfer;
import com.lq.lss.model.CustomerInfo;
import com.lq.lss.model.SessionUser;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;

import net.sf.json.JSONArray;



@Controller
@RequestMapping(value = "/user/stock/centertransferAdd.htm")
public class StockCenterTransferAddController extends ShiroBaseController<CStockCenterTransfer, String, StockCenterTransferService> {

	@Resource
	private MaterialTypeService materialTypeService;
	@Resource
	private MaterialInfoService materialInfoService;
	@Resource
	private MchBaseinfoService mchInfoService;
	@Resource
	private StockCenterTransferService scfservice;
	@Resource
	private AdminDeptService deptService;
	@Resource
	private CustomerInfoService customerInfoService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	
	@Value("/stock/stockCentertransferAdd")
	private String indexView;
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		AdminDept adminDept = deptService.get(user.getCenterId());
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.CENTER_CENTERTRANSFER_ADD);
        modelMap.put("update", PermResourceConst.CENTER_CENTERTRANSFER_UPDATE);
        modelMap.put("del", PermResourceConst.CENTER_CENTERTRANSFER_DEL);
        modelMap.put("check", PermResourceConst.CENTER_CENTERTRANSFER_CHECK);
		modelMap.put("user", user);
		if(adminDept==null){
			adminDept=new AdminDept();
		}
		modelMap.put("adminDept", adminDept);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}

	
	@RequestMapping(params = "method=getMaInfoList")
	public ModelAndView getMaInfoList(HttpServletRequest request, HttpServletResponse response) {
	
		List<BMaterialInfo> tList = materialInfoService.loadAll();

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(tList));
	}
	
	
	
		//加载公司
		@RequestMapping(params = "method=getCustomerInfos")
		public ModelAndView getCustomerInfos(HttpServletRequest request, HttpServletResponse response) {
			SessionUser user = LoginSessionUtils.getUserInSession(request); 
		
			List<CustomerInfo> tList = customerInfoService.queryCustomerInfoByDeptIdAndCustype(user.getCenterId()+"");
			
			
			
			return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(tList));
		}

		
		@RequestMapping(params = "method=saveCStockCenterTransferAll")
		@RequiresPermissions(PermResourceConst.CENTER_CENTERTRANSFER_ADD)
		public ModelAndView saveCStockCenterTransferAll(HttpServletRequest request, HttpServletResponse response,CStockCenterTransferDto stockCenterTransferDto) {
			SessionUser user = LoginSessionUtils.getUserInSession(request); 
			ResultDto<String> resultDto = null;
			try {
				 stockCenterTransferDto.setFromDeptid(user.getCenterId()+"");
				 String listStr=request.getParameter("rows");
				 String lsSerialno=request.getParameter("lsSerialno");
				 JSONArray json=JSONArray.fromObject(listStr);
				 List<CStockCenterTransferDetailDto> dataList =(List<CStockCenterTransferDetailDto>)JSONArray.toCollection(json, CStockCenterTransferDetailDto.class);
				 stockCenterTransferDto.setcStockCenterTransferDetaildtos(dataList);
				 if(stockCenterTransferDto!=null){
					 resultDto= scfservice.saveCStockStockCenterTransferRdTx(stockCenterTransferDto, user);
					 adminAuditLogService.log(user.getUserId(), TradeType.STOCKCENTERTRANSFER_OUT.getType(),"新增调出单",stockCenterTransferDto,0L);
				 }
		
				} catch (Exception e) {
					 logger.error("提交暂存请数据出现异常", e);
					 resultDto=new ResultDto<String>(false,"数据提交失败");
			}
			
			return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
		}
		
	
}
