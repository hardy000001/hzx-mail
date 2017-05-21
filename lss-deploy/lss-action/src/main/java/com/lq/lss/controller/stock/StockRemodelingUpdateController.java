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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.lq.easyui.dto.ResultDto;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.CStockRemodelingDetailService;
import com.lq.lss.core.service.CStockRemodelingService;
import com.lq.lss.core.service.MaterialInfoService;
import com.lq.lss.core.service.MaterialTypeService;
import com.lq.lss.dto.CStockRemodelingDetailDto;
import com.lq.lss.dto.CStockRemodelingDto;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.BMaterialInfo;
import com.lq.lss.model.CStockRemodeling;
import com.lq.lss.model.CStockRemodelingDetail;
import com.lq.lss.model.SessionUser;

import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;

import net.sf.json.JSONArray;


@Controller
@RequestMapping(value = "/user/stock/StockRemodelingEdit.htm")
public class StockRemodelingUpdateController extends ShiroBaseController<CStockRemodeling, String, CStockRemodelingService> {

	@Resource
	private MaterialTypeService materialTypeService;
	@Resource
	private MaterialInfoService materialInfoService;
	@Resource
	private CStockRemodelingService srlservice;
	@Resource
	private CStockRemodelingDetailService csrldatailservice;
	@Value("/stock/stockRemodelingEdit")
	private String indexView;
	@Resource
	private AdminAuditLogService adminAuditLogService;

	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		String remSerialNo=ServletRequestUtils.getStringParameter(request, "id", "");
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
	 	modelMap.put("add", PermResourceConst.CENTER_REMODELING_ADD);
        modelMap.put("update", PermResourceConst.CENTER_REMODELING_UPDATE);
        modelMap.put("del", PermResourceConst.CENTER_REMODELING_DEL);
        modelMap.put("check", PermResourceConst.CENTER_REMODELING_CHECK);
		CStockRemodeling cst=srlservice.findbyRemSerialNo(remSerialNo);

		List<CStockRemodelingDetail> detailList= csrldatailservice.fidnCsrddById(remSerialNo);
		modelMap.put("cst",cst);
		modelMap.put("detailList", detailList);
		
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=getMaInfoList")
	public ModelAndView getMaInfoList(HttpServletRequest request, HttpServletResponse response) {
	
		List<BMaterialInfo> tList = materialInfoService.loadAll();

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(tList));
	}
	
	
	
	
	@RequestMapping(params = "method=stockRemodelingupdate")
	@RequiresPermissions(PermResourceConst.CENTER_REMODELING_UPDATE)
	public ModelAndView stockRemodelingupdate(HttpServletRequest request, HttpServletResponse response,CStockRemodelingDto cstockRemodelingDto) {
	
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<String> result=null;
		try {
			
			 String listStr=request.getParameter("rows");
			JSONArray json = JSONArray.fromObject(listStr);
			List<CStockRemodelingDetailDto> dataList =(List<CStockRemodelingDetailDto>)JSONArray.toCollection(json, CStockRemodelingDetailDto.class);
			cstockRemodelingDto.setCstockRemodelingDetailDto(dataList);
			result=srlservice.updatePurInfoRdTx(cstockRemodelingDto, user);
			adminAuditLogService.log(user.getUserId(), TradeType.REMODELING.getType(),"修改加工改制单",cstockRemodelingDto,0L);
		} catch (Exception e) {
			  logger.error("修改加工改制数据出现异常", e);
			  result=new ResultDto<String>(false,"数据提交失败");
		}

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
	}
}
