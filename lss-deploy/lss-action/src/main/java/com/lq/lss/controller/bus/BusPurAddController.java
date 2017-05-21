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
import com.lq.lss.model.CBusPur;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.SystemConst;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.BusPurService;
import com.lq.lss.dto.BusPurDetailDto;
import com.lq.lss.dto.BusPurDto;
import com.lq.lss.enums.TradeType;

/**
 * 采购
 * @author  作者: hzx
 * @date 创建时间: 2016-08-31 10:21:24
 */
@Controller
@RequestMapping(value ="/user/bus/busPurAdd.htm")
public class BusPurAddController extends ShiroBaseController<CBusPur, String, BusPurService> {
	
	@Resource
	private BusPurService busPurService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	
	
	@Value("/bus/bus_pur_add")
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
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=purApply")
	@RequiresPermissions(PermResourceConst.CENTER_PUR_ADD)
	public ModelAndView purApply(HttpServletRequest request,
			HttpServletResponse response, BusPurDto busPurDto) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<String> result=null;
		
		try 
		{

						String purSerialno=busPurDto.getPurSerialno();
						busPurDto.setPurSerialno(purSerialno);
						busPurDto.setDeptid(user.getCenterId());  //中心id
						busPurDto.setMchcode(SystemConst.ZX_MCHCODE);
						
						String listStr = request.getParameter("dataList");
						JSONArray json = JSONArray.fromObject(listStr);
						List<BusPurDetailDto> dataList = (List<BusPurDetailDto>) JSONArray
								.toCollection(json, BusPurDetailDto.class);
						busPurDto.setBusPurDetailDtos(dataList);
				
					          ResultDto<String> validateResult=validataData(busPurDto);
					          if(validateResult.isSuccess())
					          {
					        	       result=busPurService.savePurApplyRdTx(busPurDto, user);
					          }else
					          {
					        	       result=validateResult;
					          }
					    //操作日志
						adminAuditLogService.log(user.getUserId(), TradeType.STOCK_CG.getType(),"采购单",busPurDto,0L);    
		} catch (Exception e) {
			    logger.error("提交采购申请数据出现异常", e);
			    result=new ResultDto<String>(false,"数据提交失败");
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}
	
	
	
	protected static ResultDto<String> validataData(BusPurDto busPurDto){
		
		String deptId=busPurDto.getDeptid()+"";
		if(deptId==null || SystemConst.ADMIN_CENTER_ID.equals(deptId)){
		    return new ResultDto<String>(false,"中心ID不能为空或中心不存在，请换账号");
	    }
		
		String purTotalAmt=busPurDto.getTotalAmt();
		if(!StringUtils.hasLength(purTotalAmt)){
		    return new ResultDto<String>(false,"总金额不能为空");
	    }
		List<BusPurDetailDto> busPurDetailDto=busPurDto.getBusPurDetailDtos();
		int i=1;
		Integer materialId;
		String unit;
		String price;
		String quantity;
		String totalAmt;
		for (BusPurDetailDto busPurDetailDto2 : busPurDetailDto) {
			    
			    if(busPurDetailDto2==null){
		    	    return new ResultDto<String>(false,"第"+i+"行,项目参数为空");
		        }
			    materialId=busPurDetailDto2.getMaterialId();
				unit=busPurDetailDto2.getUnit();
				price=busPurDetailDto2.getPrice();
				quantity=busPurDetailDto2.getQuantity();
				totalAmt=busPurDetailDto2.getTotalAmt();
				
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
	public static void main(String[] args) {
		double priceD=20.01;
		double quantityD=10.0;
		double totalAmtD=200.1;
		double num=quantityD*priceD;
		System.out.println(num);
		if(totalAmtD!=num){
			System.out.println("ok");
		}else{
			System.out.println("no");
		}
	}
}
