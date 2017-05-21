package com.lq.lss.controller.bus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.annotation.Resource;

import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
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

import com.lq.lss.model.CBusSale;
import com.lq.lss.model.CBusSaleDetail;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.SystemConst;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.BusSaleDetailService;
import com.lq.lss.core.service.BusSaleService;
import com.lq.lss.dto.BusSaleDetailDto;
import com.lq.lss.dto.BusSaleDto;
import com.lq.lss.enums.TradeType;

/**
 * 销售
 * @author  作者: hzx
 * @date 创建时间: 2016-08-31 10:21:24
 */
@Controller
@RequestMapping(value ="/user/bus/busSaleUpdate.htm")
public class BusSaleUpdateController extends ShiroBaseController<CBusSale, String, BusSaleService> {
	
	@Resource
	private BusSaleService busSaleService;
	@Resource
	private BusSaleDetailService busSaleDetailService ;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	
	
	@Value("/bus/bus_sale_update")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		
		String saleSerialno=ServletRequestUtils.getStringParameter(request, "id", "");
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		CBusSale busSale=busSaleService.get(saleSerialno);
		List<CBusSaleDetail> detailList=busSaleDetailService.querySaleDetailListById(saleSerialno);
		
		busSale.setSaleSerialno(saleSerialno);
		
		modelMap.put("busSale", busSale);
		modelMap.put("detailList", detailList);
		
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	
	
	@RequestMapping(params = "method=updateApply")
	@RequiresPermissions(PermResourceConst.CENTER_SALE_UPDATE)
	public ModelAndView updateApply(HttpServletRequest request,
			HttpServletResponse response, BusSaleDto busSaleDto) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<String> result=null;
		
		try 
		{

				String saleSerialno=busSaleDto.getSaleSerialno();
				busSaleDto.setSaleSerialno(saleSerialno);
				busSaleDto.setUserId(user.getUserId());
				busSaleDto.setDeptId(user.getCenterId()+"");
				busSaleDto.setMchcode(SystemConst.ZX_MCHCODE);
				
				String listStr =busSaleDto.getDataList();
				JSONArray json = JSONArray.fromObject(listStr);
				@SuppressWarnings("unchecked")
				List<BusSaleDetailDto> dataList = (List<BusSaleDetailDto>) JSONArray
						.toCollection(json, BusSaleDetailDto.class);
				busSaleDto.setBusSaleDetailDtos(dataList);
		
				ResultDto<String> validateResult = BusSaleAddController
						.validataData(busSaleDto);
			          if(validateResult.isSuccess())
			          {
			        	       result=busSaleService.updateSaleInfoRdTx(busSaleDto);
			          }else
			          {
			        	       result=validateResult;
			          }
			    //TODO 修改操作日志  编号不是Long类型
				adminAuditLogService.log(user.getUserId(), TradeType.STOCK_XS.getType(),"修改销售单",busSaleDto,0L);    
		} catch (Exception e) {
			    logger.error("提交销售申请数据出现异常", e);
			    result=new ResultDto<String>(false,"数据提交失败");
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}
	
	
	
}
