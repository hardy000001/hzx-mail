package com.lq.lss.controller.bus;

import java.util.Date;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.lss.model.CBusHt;
import com.lq.lss.model.SessionUser;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.BusHtRentinfoService;
import com.lq.lss.core.service.BusHtRepairinfoService;
import com.lq.lss.core.service.BusHtService;
import com.lq.lss.core.service.BusHtSettlementService;
import com.lq.lss.dto.BusHtDto;
import com.lq.lss.dto.BusHtRentinfoDto;
import com.lq.lss.dto.BusHtRepairinfoDto;
import com.lq.lss.dto.BusHtSettlementDto;
import com.lq.lss.enums.TradeType;

/**
 * 租赁合同
 * @author  作者: hzx
 * @date 创建时间: 2016-10-20 17:22:18
 */
@Controller
@RequestMapping(value ="/user/bus/busHtAdd.htm")
public class BusHtAddController extends ShiroBaseController<CBusHt, String, BusHtService>{

	
	@Resource
	private BusHtService  busHtService;

	@Resource
	private BusHtRentinfoService busHtRentinfoService;

	@Resource
	private BusHtRepairinfoService busHtRepairinfoService;
	
	@Resource
	private BusHtSettlementService busHtSettlementService;
	
	@Resource
	private AdminAuditLogService adminAuditLogService;
	
	@Value("/bus/bus_ht_add")
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
	
	
	@RequestMapping(params = "method=savaHt")
	@RequiresPermissions(PermResourceConst.CENTER_HT_ADD)
	public ModelAndView savaHt(HttpServletRequest request,
			HttpServletResponse response, BusHtDto busHtDto,BusHtSettlementDto busHtSettlementDto) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<String> result=null;
		
		try 
		{
			    busHtDto.setBusHtSettlementDto(busHtSettlementDto);
			    busHtDto.setDeptid(user.getCenterId()+"");
				busHtDto.setCreateoper(user.getUserId()+"");
				
				//租费
				String listStr =busHtDto.getDataList();
				JSONArray json = JSONArray.fromObject(listStr);
				@SuppressWarnings("unchecked")
				List<BusHtRentinfoDto> dataList = (List<BusHtRentinfoDto>) JSONArray
						.toCollection(json, BusHtRentinfoDto.class);
				busHtDto.setBusHtRentinfoDtos(dataList);
				//维修
				String listStr2 =busHtDto.getDataList2();
				JSONArray json2 = JSONArray.fromObject(listStr2);
				@SuppressWarnings("unchecked")
				List<BusHtRepairinfoDto> dataList2 = (List<BusHtRepairinfoDto>) JSONArray
						.toCollection(json2, BusHtRepairinfoDto.class);
				busHtDto.setBusHtRepairinfoDtos(dataList2);
		
			          ResultDto<String> validateResult=validataData(busHtDto);
			          if(validateResult.isSuccess())
			          {
			        	       result=busHtService.saveBusHtRdTx(busHtDto);
			          }else
			          {
			        	       result=validateResult;
			          }
			    //操作日志
				adminAuditLogService.log(user.getUserId(), TradeType.BUS_HT.getType(),"新增合同",busHtDto,0L);
		} catch (Exception e) {
			    logger.error("提交合同数据出现异常", e);
			    result=new ResultDto<String>(false,"数据提交失败");
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}
	
	
	
	protected static ResultDto<String> validataData(BusHtDto busHtDto){
		
		String comName=busHtDto.getComName();
		if(!StringUtils.hasLength(comName)){
		    return new ResultDto<String>(false,"公司名称不能为空");
	    }
		String comLinkman=busHtDto.getComLinkman();
		if(!StringUtils.hasLength(comLinkman)){
		    return new ResultDto<String>(false,"公司联系人不能为空");
	    }
		String comTel=busHtDto.getComTel();
		if(!StringUtils.hasLength(comTel)){
		    return new ResultDto<String>(false,"联系方式不能为空");
	    }
		String comType=String.valueOf(busHtDto.getComType());
		if(!StringUtils.hasLength(comType)){
		    return new ResultDto<String>(false,"公司类型不能为空");
	    }
		String beginDate=busHtDto.getBeginDate();
		if(!StringUtils.hasLength(beginDate)){
		    return new ResultDto<String>(false,"项目开始时间不能为空");
	    }
		String endDate=busHtDto.getEndDate();
		if(!StringUtils.hasLength(endDate)){
		    return new ResultDto<String>(false,"项目结束时间不能为空");
	    }
		
		List<BusHtRentinfoDto> busHtRentinfoDtos=busHtDto.getBusHtRentinfoDtos();
		int i=1;
		String materialCode;
		String unit;
		String price;
		String quantity;
		
		for (BusHtRentinfoDto busHtRentinfoDto : busHtRentinfoDtos) {
			   
			    if(busHtRentinfoDto==null){
			    	    return new ResultDto<String>(false,"第"+i+"行,项目参数为空");
			    }
			    materialCode=busHtRentinfoDto.getMaterialcode();
				unit=busHtRentinfoDto.getUnit();
				price=busHtRentinfoDto.getRentalDay();
				quantity=busHtRentinfoDto.getQuantity();
				
				if (materialCode==null)
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
				i++;
		}
		return new ResultDto<String>(true,"数据通过验证");
	}
	
	/**
	 * 计算时间差
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 */
	protected static Integer getTotalDays(Date dateStart, Date dateEnd) {
		return  (int) ((dateEnd.getTime() - dateStart.getTime()) / 1000 / 60 / 60 / 24);
	}
	
}
