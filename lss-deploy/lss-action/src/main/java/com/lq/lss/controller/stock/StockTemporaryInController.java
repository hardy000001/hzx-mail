/**
 * 
 */
package com.lq.lss.controller.stock;

import java.util.Date;
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

import com.lq.easyui.controller.easyui.EasyUIController;
import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.view.EasyUIObject;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.print.StockInPrintController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.BusPurService;
import com.lq.lss.core.service.CStockFlowService;
import com.lq.lss.core.service.MaterialInfoService;
import com.lq.lss.core.service.MaterialTypeService;
import com.lq.lss.core.service.MchBaseinfoService;
import com.lq.lss.core.service.RepairInfoService;
import com.lq.lss.core.service.StockInDetailService;
import com.lq.lss.core.service.StockInService;
import com.lq.lss.core.service.StockTransferService;
import com.lq.lss.dto.StockOutDetailDto;
import com.lq.lss.dto.StockOutDto;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.BMaterialInfo;
import com.lq.lss.model.BRepairInfo;
import com.lq.lss.model.CBusPurDetail;
import com.lq.lss.model.CStockCenterTransfer;
import com.lq.lss.model.CStockIn;
import com.lq.lss.model.CStockInDetail;
import com.lq.lss.model.CStockTransfer;
import com.lq.lss.model.MchBaseinfo;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.SystemConst;
import com.lq.pager.PageParam;
import com.lq.pager.Pager;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;

/**
 * @author Eric
 */
@Controller
@RequestMapping(value = "/user/stock/stocktemporaryin.htm")
public class StockTemporaryInController extends EasyUIController<CStockIn, String,StockInService > {

	@Resource
	private MaterialTypeService materialTypeService;
	@Resource
	private MaterialInfoService materialInfoService;
	@Resource
	private MchBaseinfoService mchInfoService;
	
	@Resource
	private StockInDetailService stockInDetailService;
	
	@Resource
	private CStockFlowService cStockFlowService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	@Resource
	private RepairInfoService repairInfoService;
	
	
	
	

	@Resource
	private StockInService stockInService;

	
	@Value("/stock/stock_temporary_in")
	private String indexView;

	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {

		SessionUser user = LoginSessionUtils.getUserInSession(request);
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<BRepairInfo> repairList=repairInfoService.loadAll();
		
		modelMap.put("add", PermResourceConst.STOCK_TEMPORARY_IN_ADD);
		modelMap.put("update", PermResourceConst.STOCK_TEMPORARY_IN_UPDATE);
		modelMap.put("del", PermResourceConst.STOCK_TEMPORARY_IN_DEL);
		modelMap.put("check", PermResourceConst.STOCK_TEMPORARY_IN_CHECK);
		modelMap.put("deptid", user.getCenterId());
		modelMap.put("repairList", repairList);
		
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
	
	
	@RequestMapping(params = "method=getMchInfoList")
	public ModelAndView getMchInfoList(HttpServletRequest request, HttpServletResponse response) {
	
		List<MchBaseinfo> tList = mchInfoService.loadAll();

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(tList));
	}
	
	
	
	@RequestMapping(params = "method=queryList")
	public ModelAndView queryList(HttpServletRequest request, HttpServletResponse response,CStockIn cStockIn ) {

		
		int pageNumber = ServletRequestUtils.getIntParameter(request, "page", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "rows", 10);
		String sort = ServletRequestUtils.getStringParameter(request, "sort", null);
		String order = ServletRequestUtils.getStringParameter(request, "order", "");
		PageParam pageParam = new PageParam(pageNumber, pageSize);
		
		EasyUIObject<CStockIn> easyui = new EasyUIObject<CStockIn>();
		try {
			Pager<CStockIn> page = stockInService.pagerList(pageParam, cStockIn);
			easyui.setRows(page.getResultList());
			easyui.setPageNumber(page.getCurPage());
			easyui.setPageSize(pageSize);
			easyui.setTotal(page.getTotalCount());
		} catch (Exception e) {
			logger.error("query查询出现异常", e);
		}
		return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(easyui));
	}
	
	
	/**
	 * 入库删除操作
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=remove")
	@RequiresPermissions(PermResourceConst.STOCK_TEMPORARY_IN_DEL)
	public ModelAndView remove(HttpServletRequest request, HttpServletResponse response) {

		ResultDto<String> result = null;
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		if (notDelete) {
				result = new ResultDto<String>(false, deleteAlertMsg);
				return AjaxModelAndViewUtils.writeMsgReturnNull(response,
						JSONUtil.toJSonString(result));
		}
		String idStr = ServletRequestUtils.getStringParameter(request, "inSerialno", null);
		
		if (!StringUtils.hasLength(idStr)) {
				result = new ResultDto<String>(false, "入库流水号不能为空");
				return AjaxModelAndViewUtils.writeMsgReturnNull(response,
						JSONUtil.toJSonString(result));
		}
		
		try {
			
			result=stockInService.deleteStockInByIdRdTx(idStr, user.getDeptId());
			adminAuditLogService.log(user.getUserId(), TradeType.STOCK_TEMPORARY_IN.getType(),"删除暂存单",idStr,0L);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除入库数据出现异常"+e.getMessage());
			result=new ResultDto<String>(false,"删除数据出现异常");
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}
	
	
	
	@RequestMapping(params = "method=gerStockInDetailList")
	public ModelAndView gerStockInDetailList(HttpServletRequest request,HttpServletResponse response) {
		
		String inSerialno = ServletRequestUtils.getStringParameter(request,
				"inSerialno", "");
		
		List<Map<String, Object>> detailList = null;
		
		if (StringUtils.hasText(inSerialno)) {
			detailList=stockInDetailService.getInDetailListById(inSerialno);
		}
		List<BRepairInfo> repairInfos=repairInfoService.queryRepairInfoByTypeid("");
		List<Map<String, Object>> maps=StockInPrintController.totalInList(detailList, repairInfos);
		
		detailList.add(maps.get(maps.size()-1));

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(detailList));
	}
	
	
	@RequestMapping(params = "method=auditStockIn")
	@RequiresPermissions(PermResourceConst.STOCK_TEMPORARY_IN_CHECK)
	public ModelAndView auditStockIn(HttpServletRequest request,HttpServletResponse response) {
		
		 SessionUser sessionUser = LoginSessionUtils.getUserInSession(request);
		//获取入库流水号
		String inSerialno = ServletRequestUtils.getStringParameter(request,
				"inSerialno", "");
		String status = ServletRequestUtils.getStringParameter(request,
				"status", "");
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<String> result = null;
		
		try {
			CStockIn cStockIn=new CStockIn();
			
			cStockIn.setInSerialno(inSerialno);
			cStockIn.setStatus(status);
			cStockIn.setUserId(Integer.parseInt(user.getUserId()+""));
			cStockIn.setDeptid(user.getCenterId());
			cStockIn.setAuditingoper(user.getLoginName());
			cStockIn.setAuditingtime(new Date());
			result=stockInService.auditStockTemporaryInByIdRdTx(cStockIn);
			adminAuditLogService.log(sessionUser.getUserId(), TradeType.STOCK_TEMPORARY_IN.getType(),"审核暂存单",cStockIn,0L);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("审核入库数据出现异常"+e.getMessage());
			result=new ResultDto<String>(false,"审核出现异常");

		}

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}
	
	
	
	
	protected static ResultDto<String> validataData(StockOutDto stockOutDto){
		
//		String deptId=stockOutDto.getDeptid()+"";
//		if(deptId==null || SystemConst.ADMIN_CENTER_ID.equals(deptId)){
//		    return new ResultDto<String>(false,"中心ID不能为空或中心不存在，请换账号");
//	    }
//		String outSerialno=stockOutDto.getOutSerialno();
//		String mchcode=stockOutDto.getMchcode();
//		if(!StringUtils.hasLength(outSerialno)){
//		    return new ResultDto<String>(false,"流水号不能为空");
//	    }
//		if(!StringUtils.hasLength(mchcode)){
//		    return new ResultDto<String>(false,"商户号不能为空");
//	    }
//		
//		List<StockOutDetailDto> stockOutDetailDtos=stockOutDto.getStockOutDetailDtos();
//		int i=1;
//		String materialcode;
//		//String totalM_;
//		String totalS_;
//		//String totalT_;
//		
//		for (StockOutDetailDto stockOutDetailDto : stockOutDetailDtos) {
//			   
//			    if(stockOutDetailDto==null){
//			    	    return new ResultDto<String>(false,"第"+i+"行,项目参数为空");
//			    }
//			    materialcode=stockOutDetailDto.getMaterialcode();
//			    //totalM_=stockOutDetailDto.getTotalM();
//			    totalS_=stockOutDetailDto.getTotalS();
//			    //totalT_=stockOutDetailDto.getTotalT();
//				
//				if (!StringUtils.hasLength(materialcode))
//				{
//					    return new ResultDto<String>(false,"第"+i+"行,物资编号不能为空");
//				}
////				if (!StringUtils.hasLength(totalM_))
////				{
////					    return new ResultDto<String>(false,"第"+i+"行,出库总长度不能为空");
////				}
//				if (!StringUtils.hasLength(totalS_))
//				{
//					    return new ResultDto<String>(false,"第"+i+"行,数量 不能为空");
//				}
////				if (!StringUtils.hasLength(totalT_))
////				{
////					    return new ResultDto<String>(false,"第"+i+"行,出库总量不能为空");
////				}
//				try {
//						//double totalMD=Double.parseDouble(totalM_);
//						//double totalSD=Double.parseDouble(totalS_);
//						//double totalTD=Double.parseDouble(totalT_);
////						
////						if(totalTD!=totalMD*totalSD)
////						{
////							return new ResultDto<String>(false,"第"+i+"行,出库总量有误");
////						}
//				} catch (NumberFormatException e) {
//					    e.printStackTrace();
//					    return new ResultDto<String>(false,"第"+i+"行,数据转化异常");
//				}
//				i++;
//		}
		return new ResultDto<String>(true,"数据通过验证");
	}
	
	
	
	
	@RequestMapping(params = "method=antiAuditStockTemporaryIn")
	@RequiresPermissions(PermResourceConst.STOCK_TEMPORARY_IN_CHECK)
	public ModelAndView antiAuditStockTemporaryIn(HttpServletRequest request,HttpServletResponse response) {
		
		 SessionUser sessionUser = LoginSessionUtils.getUserInSession(request);
		//获取入库流水号
		String inSerialno = ServletRequestUtils.getStringParameter(request,
				"inSerialno", "");
		String status = ServletRequestUtils.getStringParameter(request,
				"status", "");
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<String> result = null;
		
		try {
			CStockIn cStockIn=new CStockIn();
			

			cStockIn.setInSerialno(inSerialno);
			cStockIn.setStatus("1");
			cStockIn.setAuditingoper(null);
			cStockIn.setAuditingtime(null);
			result=stockInService.antiAuditStockTemporaryInByIdRdTx(cStockIn);
			adminAuditLogService.log(sessionUser.getUserId(), TradeType.ANTI_STOCK_TEMPORARY_IN.getType(),"反审核暂存单",cStockIn,0L);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("审核入库数据出现异常"+e.getMessage());
			result=new ResultDto<String>(false,"审核出现异常");

		
		}
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}

}
