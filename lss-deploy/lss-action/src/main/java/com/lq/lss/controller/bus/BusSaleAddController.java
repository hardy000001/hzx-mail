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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.lss.model.CBusSale;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.SystemConst;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
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
@RequestMapping(value ="/user/bus/busSaleAdd.htm")
public class BusSaleAddController extends ShiroBaseController<CBusSale, String, BusSaleService> {
	
	@Resource
	private BusSaleService busSaleService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	
	
	@Value("/bus/bus_sale_add")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("user", user);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	
	
	@RequestMapping(params = "method=saleApply")
	@RequiresPermissions(PermResourceConst.CENTER_SALE_ADD)
	public ModelAndView saleApply(HttpServletRequest request,
			HttpServletResponse response, BusSaleDto busSaleDto) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<String> result=null;
		
		try 
		{

				String saleSerialno=busSaleDto.getSaleSerialno();//CodeGenerater.getInstance().generaterNextPurCode();
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
		
			          ResultDto<String> validateResult=validataData(busSaleDto);
			          if(validateResult.isSuccess())
			          {
			        	       result=busSaleService.saveSaleApplyRdTx(busSaleDto);
			          }else
			          {
			        	       result=validateResult;
			          }
			    //操作日志
				adminAuditLogService.log(user.getUserId(), TradeType.STOCK_XS.getType(),"销售单",busSaleDto,0L);      
		} catch (Exception e) {
			    logger.error("提交销售申请数据出现异常", e);
			    result=new ResultDto<String>(false,"数据提交失败");
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}
	
	
	
	protected static ResultDto<String> validataData(BusSaleDto busSaleDto){
		
		String deptId=busSaleDto.getDeptId();
		if(deptId==null || SystemConst.ADMIN_CENTER_ID.equals(deptId)){
		    return new ResultDto<String>(false,"中心ID不能为空或中心不存在，请换账号");
	    }
		
		String purTotalAmt=busSaleDto.getTotalAmt();
		if(!StringUtils.hasLength(purTotalAmt)){
		    return new ResultDto<String>(false,"总金额不能为空");
	    }
		List<BusSaleDetailDto> busSaleDetailDto=busSaleDto.getBusSaleDetailDtos();
		int i=1;
		Integer materialId;
		String unit;
		String price;
		String quantity;
		String totalAmt;
		
		for (BusSaleDetailDto busSaleDetailDto2 : busSaleDetailDto) {
			   
			    if(busSaleDetailDto2==null){
			    	    return new ResultDto<String>(false,"第"+i+"行,项目参数为空");
			    }
			    materialId=busSaleDetailDto2.getMaterialId();
				unit=busSaleDetailDto2.getUnit();
				price=busSaleDetailDto2.getPrice();
				quantity=busSaleDetailDto2.getQuantity();
				totalAmt=busSaleDetailDto2.getTotalAmt();
				
				if (materialId==null)
				{
					    return new ResultDto<String>(false,"第"+i+"行,物资id不能为空");
				}
				if (!StringUtils.hasLength(unit))
				{
					    return new ResultDto<String>(false,"第"+i+"行,单位不能为空");
				}
				if (!StringUtils.hasLength(price))
				{
					    return new ResultDto<String>(false,"第"+i+"行,价格不能为空");
				}
				if (!StringUtils.hasLength(quantity))
				{
					    return new ResultDto<String>(false,"第"+i+"行,数量不能为空");
				}
				if(!StringUtils.hasLength(totalAmt)){
					    return new ResultDto<String>(false,"第"+i+"行,金额不能为空");
				}
//				try {
//						double priceD=Double.parseDouble(price);
//						double quantityD=Double.parseDouble(quantity);
//						double totalAmtD=Double.parseDouble(totalAmt);
//						
//						if(totalAmtD!=priceD*quantityD)
//						{
//							return new ResultDto<String>(false,"第"+i+"行,金额错误");
//						}
//				} catch (NumberFormatException e) {
//					    e.printStackTrace();
//					    return new ResultDto<String>(false,"第"+i+"行,数据转化异常");
//				}
				i++;
		}
		return new ResultDto<String>(true,"数据通过验证");
	}
	
}
