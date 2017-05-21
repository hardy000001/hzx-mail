package com.lq.lss.controller.stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.annotation.Resource;
import net.sf.json.JSONArray;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;
import com.lq.easyui.dto.ResultDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.lq.lss.model.CStockOut;
import com.lq.lss.model.CStockOutDetail;
import com.lq.lss.model.SessionUser;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.StockOutDetailService;
import com.lq.lss.core.service.StockOutService;
import com.lq.lss.dto.StockOutDetailDto;
import com.lq.lss.dto.StockOutDto;
import com.lq.lss.enums.TradeType;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-10-08 09:26:28
 */
@Controller
@RequestMapping(value ="/user/stock/stockOutUpdate.htm")
public class StockOutUpdateController extends ShiroBaseController<CStockOut, String, StockOutService>{
	
	@Resource
	private StockOutService stockOutService;
	@Resource
	private StockOutDetailService stockOutDetailService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	
	
	@Value("/stock/stock_out_update")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		String outSerialno=ServletRequestUtils.getStringParameter(request, "id", "");
//		String loginName=ServletRequestUtils.getStringParameter(request, "loginName", "");

		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		CStockOut stockOut=stockOutService.get(outSerialno);
		List<CStockOutDetail> detailList=stockOutDetailService.queryStockOutListById(outSerialno);
		
		modelMap.put("user", user);
		modelMap.put("stockOut", stockOut);
		modelMap.put("detailList", detailList);
		
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	
	
	@RequestMapping(params = "method=updateApply")
	@RequiresPermissions(PermResourceConst.CENTER_OUT_UPDATE)
	public ModelAndView updateApply(HttpServletRequest request,
			HttpServletResponse response, StockOutDto stockOutDto) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<String> result=null;
		
		try 
		{

			    String outSerialno=stockOutDto.getOutSerialno();
			    stockOutDto.setOutSerialno(outSerialno);
			    stockOutDto.setUserId(user.getUserId());
			    stockOutDto.setDeptid(user.getCenterId());
				
				String listStr =stockOutDto.getDataList();
				JSONArray json = JSONArray.fromObject(listStr);
				@SuppressWarnings("unchecked")
				List<StockOutDetailDto> dataList = (List<StockOutDetailDto>) JSONArray
						.toCollection(json, StockOutDetailDto.class);
				stockOutDto.setStockOutDetailDtos(dataList);
		
				ResultDto<String> validateResult = StockOutAddController
						.validataData(stockOutDto);
			          if(validateResult.isSuccess())
			          {
			        	       result=stockOutService.updateStockOutInfoRdTx(stockOutDto);
			          }else
			          {
			        	       result=validateResult;
			          }
			     //TODO 修改操作日志  编号不是Long类型
				 adminAuditLogService.log(user.getUserId(), TradeType.STOCK_OUT.getType(),"修改出库单",stockOutDto,0L);         
		} catch (Exception e) {
			    logger.error("修改出库申请数据出现异常", e);
			    result=new ResultDto<String>(false,"数据提交失败");
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}
	
}
