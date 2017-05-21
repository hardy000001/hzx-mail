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
import com.lq.lss.model.CBusPur;
import com.lq.lss.model.CBusPurDetail;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.SystemConst;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.BusPurDetailService;
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
@RequestMapping(value ="/user/bus/busPurUpdate.htm")
public class BusPurUpdateController extends ShiroBaseController<CBusPur, String, BusPurService> {
	
	@Resource
	private BusPurService busPurService;
	@Resource
	private BusPurDetailService busPurDetailService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	
	
	@Value("/bus/bus_pur_update")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		
		String purSerialno=ServletRequestUtils.getStringParameter(request, "id", "");
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		CBusPur busPur=busPurService.get(purSerialno);
		List<CBusPurDetail> detailList=busPurDetailService.queryPurDetailListById(purSerialno);
		
		busPur.setPurSerialno(purSerialno);
		
		modelMap.put("busPur",busPur);
		modelMap.put("detailList", detailList);
		
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=updateApply")
	@RequiresPermissions(PermResourceConst.CENTER_PUR_UPDATE)
	public ModelAndView updateApply(HttpServletRequest request,
			HttpServletResponse response, BusPurDto busPurDto) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<String> result=null;
		
		
		try 
		{

						String purSerialno=busPurDto.getPurSerialno();
						busPurDto.setPurSerialno(purSerialno);
						busPurDto.setDeptid(user.getCenterId());
						busPurDto.setMchcode(SystemConst.ZX_MCHCODE);
						
						String listStr = request.getParameter("dataList");
						JSONArray json = JSONArray.fromObject(listStr);
						List<BusPurDetailDto> dataList = (List<BusPurDetailDto>) JSONArray
								.toCollection(json, BusPurDetailDto.class);
						busPurDto.setBusPurDetailDtos(dataList);
				
						ResultDto<String> validateResult = BusPurAddController
								.validataData(busPurDto);
					          if(validateResult.isSuccess())
					          {
					        	       result=busPurService.updatePurInfoRdTx(busPurDto, user);
					          }else
					          {
					        	       result=validateResult;
					          }
					    //TODO 修改操作日志  编号不是Long类型
						adminAuditLogService.log(user.getUserId(), TradeType.STOCK_CG.getType(),"修改采购单",busPurDto,0L);
		} catch (Exception e) {
			    logger.error("提交采购申请数据出现异常", e);
			    result=new ResultDto<String>(false,"数据提交失败");
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}
	
	
	
	
	
}
