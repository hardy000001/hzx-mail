package com.lq.lss.controller.sys;

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
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.controller.util.UploadFileUtil;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.lss.model.CustomerInfo;
import com.lq.lss.model.CustomerRentinfo;
import com.lq.lss.model.MchRule;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.SystemConst;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.CustomerInfoService;
import com.lq.lss.core.service.CustomerRentinfoService;
import com.lq.lss.core.service.MchRuleService;
import com.lq.lss.dto.AuditDto;
import com.lq.lss.dto.CustomerInfoDto;
import com.lq.lss.dto.CustomerRentinfoDto;
import com.lq.lss.enums.CheckStatus;
import com.lq.lss.enums.TradeType;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-15 14:03:12
 */
@Controller
@RequestMapping(value ="/user/sys/customerRentinfoUpdate.htm")
public class CustomerRentinfoUpdateController extends ShiroBaseController<CustomerRentinfo, String, CustomerRentinfoService>{
	
	@Resource
	private CustomerInfoService customerInfoService;
	@Resource
	private MchRuleService mchRuleService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	@Resource
	private CustomerRentinfoService customerRentinfoService;
	
	
	@Value("/base/customer_rentinfo_update")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user=LoginSessionUtils.getUserInSession(request);
		String id = ServletRequestUtils.getStringParameter(request, "id", "");
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.SYS_CUSTOMER_RENTINFO_ADD);
        modelMap.put("update", PermResourceConst.SYS_CUSTOMER_RENTINFO_UPDATE);
        modelMap.put("del", PermResourceConst.SYS_CUSTOMER_RENTINFO_DEL);
        modelMap.put("check", PermResourceConst.SYS_CUSTOMER_RENTINFO_CHEKE);
        
		List<MchRule> mchRuleList=mchRuleService.loadAll();
		modelMap.put("mchRuleList", mchRuleList);
		
		modelMap.put("cuscode", id);
		
		if(!SystemConst.ADMIN_CENTER_ID.equals(String.valueOf(user.getCenterId())))
		{
			modelMap.put("deptId", user.getCenterId());
		}
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=updateInfo")
	@RequiresPermissions(PermResourceConst.SYS_CUSTOMER_RENTINFO_UPDATE)
	public ModelAndView updateInfo(HttpServletRequest request, HttpServletResponse response,
			CustomerInfoDto customerInfoDto) {
		
		SessionUser user=LoginSessionUtils.getUserInSession(request);
		
		String cuslicence="";
		String contracturl="";
		
		ResultDto<String> result=null;
		String cuscode="";

		try {
			customerInfoDto.setDeptid(user.getCenterId()+"");
			customerInfoDto.setCreateoper(Integer.parseInt(user.getUserId()+""));
			
			String listStr = request.getParameter("dataList");
			JSONArray json = JSONArray.fromObject(listStr);
			List<CustomerRentinfoDto> dataList = (List<CustomerRentinfoDto>) JSONArray
					.toCollection(json, CustomerRentinfoDto.class);
			customerInfoDto.setCustomerRentinfoDtos(dataList);
			
			ResultDto<String> resultData=CustomerRentinfoAddController.validataData(customerInfoDto);
			  
			if (!resultData.isSuccess()) {
				result = resultData;
				return AjaxModelAndViewUtils.writeMsgReturnNull(response,
						JSONUtil.toJSonString(result));
			}
			cuscode=customerInfoDto.getCuscode();
			String deptId=user.getCenterId()+"";
			CustomerInfo customerInfo2=customerInfoService.findCustomerInfoByIdAndDeptId(cuscode, deptId);
		    if(customerInfo2!=null)
		    {     
		    	  //审核后不再允许修改
		          String status2=customerInfo2.getStatus()+"";
			      if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status2))
			      {
			    	result=new ResultDto<String>(false,"已审核数据不可以修改");
			    	return AjaxModelAndViewUtils.writeMsgReturnNull(response,
							JSONUtil.toJSonString(result));
			      }
		    	  cuslicence = UploadFileUtil.uploadFile(request, "cuslicenceFile","CZZ");
			      contracturl = UploadFileUtil.uploadFile(request, "contracturlFile","CHT");
			
				  customerInfoDto.setCuslicence(cuslicence);
				  customerInfoDto.setContracturl(contracturl);
		    	
		    }else{
		    	result=new ResultDto<String>(false,"该数据不在本中心不允许修改");
		    	return AjaxModelAndViewUtils.writeMsgReturnNull(response,
						JSONUtil.toJSonString(result));
		    }
			
		} catch(NumberFormatException e){
			e.printStackTrace();
			logger.error("数据转换出现异常。。。"+e.getMessage());
			result=new ResultDto<String>(false, "数据转换出现异常");
			return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("上传文件出现异常。。。"+e.getMessage());
			result=new ResultDto<String>(false, "上传文件出现异常");
			return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
		}
		
		
		try {
			customerInfoService.updateCustomerInfoRdTx(customerInfoDto);
			result=new ResultDto<String>(true, "数据修改成功");
			//操作日志
			Long cuscodeL=Long.parseLong(cuscode);
			adminAuditLogService.log(user.getUserId(), TradeType.CUSTOMER_INFO.getType(),"修改客户",customerInfoDto,cuscodeL);
		} catch (Exception e) {
			e.printStackTrace();
			result=new ResultDto<String>(false, "修改数据异常");
			logger.error("客户信息修改数据异常"+e.getMessage());
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
	}
	@RequestMapping(params = "method=auditInfo")
	@RequiresPermissions(PermResourceConst.SYS_CUSTOMER_RENTINFO_CHEKE)
	public ModelAndView auditInfo(HttpServletRequest request, HttpServletResponse response,
			CustomerInfoDto customerInfoDto) {
		
			SessionUser user=LoginSessionUtils.getUserInSession(request);
			
			String status=customerInfoDto.getStatus();
			ResultDto<String> result=null;
			if(!StringUtils.hasLength(status)){
				result=new ResultDto<String>(false,"状态不能为空");
				return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
			}
			
			try {
				AuditDto auditDto=new AuditDto();
				auditDto.setId(customerInfoDto.getCuscode());
				auditDto.setStatus(status);
				auditDto.setDeptId(user.getCenterId()+"");
				auditDto.setObj("1");
				result=customerInfoService.auditInfoRdTx(auditDto);
				//操作日志
				Long cuscodeL=Long.parseLong(customerInfoDto.getCuscode());
				adminAuditLogService.log(user.getUserId(), TradeType.CUSTOMER_INFO.getType(),"审核客户",customerInfoDto,cuscodeL);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				logger.error("数据转换异常");
				result=new ResultDto<String>(false,"数据转换异常");
			}
		
		
		    return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
	}
	/**
	 * 获取用户信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=getCustomer")
	public ModelAndView getCustomer(HttpServletRequest request, HttpServletResponse response) {
		
		String id = ServletRequestUtils.getStringParameter(request, "id", "");

		CustomerInfo tList =customerInfoService.queryCustomerInfoById(id);

		return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(tList));
	}
	
   
	
}
