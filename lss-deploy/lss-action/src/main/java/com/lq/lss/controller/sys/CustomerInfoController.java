package com.lq.lss.controller.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.annotation.Resource;

import com.lq.lss.controller.shiro.ShiroBaseController;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;

import com.lq.pager.PageParam;
import com.lq.pager.Pager;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;
import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.view.EasyUIObject;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.lq.lss.model.CustomerInfo;
import com.lq.lss.model.MchRule;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.SystemConst;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.CodeGenerater;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.controller.util.UploadFileUtil;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.CustomerInfoService;
import com.lq.lss.core.service.MchRuleService;
import com.lq.lss.dto.AuditDto;
import com.lq.lss.dto.CustomerInfoDto;
import com.lq.lss.enums.CheckStatus;
import com.lq.lss.enums.TradeType;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-10-26 11:02:13
 */
@Controller
@RequestMapping(value ="/user/sys/customerInfo.htm")
public class CustomerInfoController extends ShiroBaseController<CustomerInfo, String, CustomerInfoService> {
	
	@Resource
	private CustomerInfoService customerInfoService;
	@Resource
	private MchRuleService mchRuleService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	
	
	@Value("/base/customer_info")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user=LoginSessionUtils.getUserInSession(request);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.SYS_CUSTOMER_ADD);
        modelMap.put("update", PermResourceConst.SYS_CUSTOMER_UPDATE);
        modelMap.put("del", PermResourceConst.SYS_CUSTOMER_DEL);
        modelMap.put("check", PermResourceConst.SYS_CUSTOMER_CHECK);
        
		List<MchRule> mchRuleList=mchRuleService.loadAll();
		modelMap.put("mchRuleList", mchRuleList);
		
		if(!SystemConst.ADMIN_CENTER_ID.equals(String.valueOf(user.getCenterId())))
		{
			modelMap.put("deptId", user.getCenterId());
		}
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	/**
	 * 客户
	 * @param customerInfoDto
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=customerQuery")
	public ModelAndView customerQuery(CustomerInfo customerInfo,
			HttpServletRequest request, HttpServletResponse response) {

		int pageNumber = ServletRequestUtils.getIntParameter(request, "page", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "rows", 10);
		PageParam pageParam = new PageParam(pageNumber, pageSize);
		
		
		EasyUIObject<CustomerInfo> easyui = new EasyUIObject<CustomerInfo>();
		try {
			if(!StringUtils.hasLength(customerInfo.getCustype()))
			{
				customerInfo.setCustype("(2,3,4,5)");
			}else{
				customerInfo.setCustype("("+customerInfo.getCustype()+")");
			}
			Pager<CustomerInfo> page = query(request, pageParam, customerInfo);
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
	 * 调拨客户
	 * @param customerInfoDto
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=allotCustomerQuery")
	public ModelAndView allotCustomerQuery(CustomerInfo customerInfo,
			HttpServletRequest request, HttpServletResponse response) {

		int pageNumber = ServletRequestUtils.getIntParameter(request, "page", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "rows", 10);
		PageParam pageParam = new PageParam(pageNumber, pageSize);
		
		
		EasyUIObject<CustomerInfo> easyui = new EasyUIObject<CustomerInfo>();
		try {
			customerInfo.setCustype("(1)");
			Pager<CustomerInfo> page = query(request, pageParam, customerInfo);
			easyui.setRows(page.getResultList());
			easyui.setPageNumber(page.getCurPage());
			easyui.setPageSize(pageSize);
			easyui.setTotal(page.getTotalCount());
			
			
		} catch (Exception e) {
			logger.error("query查询出现异常", e);
		}
		return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(easyui));
	}
	
	@RequestMapping(params = "method=saveCustomerInfo")
	@RequiresPermissions(PermResourceConst.SYS_CUSTOMER_ADD)
	public ModelAndView saveCustomerInfo(HttpServletRequest request, HttpServletResponse response,
			CustomerInfoDto customerInfoDto) {
		
		SessionUser user=LoginSessionUtils.getUserInSession(request);
		
		String cuslicence="";
		String contracturl="";
		ResultDto<String> result=null;
		try {
			customerInfoDto.setCusrule(1); //设置默认规则
			customerInfoDto.setDeptid(user.getCenterId()+"");
			customerInfoDto.setCreateoper(Integer.parseInt(user.getUserId()+""));
            ResultDto<String> resultData=validataData(customerInfoDto);
			
			if (!resultData.isSuccess()) {
				result = resultData;
				return AjaxModelAndViewUtils.writeMsgReturnNull(response,
						JSONUtil.toJSonString(result));
			}
			cuslicence = UploadFileUtil.uploadFile(request, "cuslicenceFile","CZZ");
			contracturl = UploadFileUtil.uploadFile(request, "contracturlFile","CHT");
			
			customerInfoDto.setCuslicence(cuslicence);
			customerInfoDto.setContracturl(contracturl);
			
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
			synchronized (this) 
			{     
				  //获取部门中最大id
			      String cusCode=customerInfoService.queryMaxCusCodeByDeptId("");
			      logger.debug("最大客户号为"+cusCode);
			      String cuscode=CodeGenerater.getInstance().generaterNextCusCode(cusCode);	
			      customerInfoDto.setCuscode(cuscode);
			}
			result=customerInfoService.saveCustomerInfoRdTx(customerInfoDto);
			
			//操作日志
			adminAuditLogService.log(user.getUserId(), TradeType.CUSTOMER_INFO.getType(),"新增客户",customerInfoDto,0L);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("客户数据保存异常"+e.getMessage());
			result=new ResultDto<String>(false, "客户数据保存异常");
			return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
	}
	
	@RequestMapping(params = "method=remove")
	@RequiresPermissions(PermResourceConst.SYS_CUSTOMER_DEL)
	public ModelAndView remove(HttpServletRequest request, HttpServletResponse response) {

		ResultDto<String> result = null;
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		String idStr = ServletRequestUtils.getStringParameter(request, "id", null);
		
		if (!StringUtils.hasLength(idStr)) {
				result = new ResultDto<String>(false, "id或ids不能为空");
				return AjaxModelAndViewUtils.writeMsgReturnNull(response,
						JSONUtil.toJSonString(result));
		}
		
		try {
			result=customerInfoService.deleteInfoById(idStr, user.getCenterId()+"");
			//操作日志
			Long cuscodeL=Long.parseLong(idStr);
			adminAuditLogService.log(user.getUserId(), TradeType.CUSTOMER_INFO.getType(),"删除客户",idStr,cuscodeL);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除客户数据出现异常"+e.getMessage());
			result=new ResultDto<String>(false,"删除数据出现异常");
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}
	
	@RequestMapping(params = "method=updateInfo")
	@RequiresPermissions(PermResourceConst.SYS_CUSTOMER_UPDATE)
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
			ResultDto<String> resultData=validataData(customerInfoDto);
			  
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
	@RequiresPermissions(PermResourceConst.SYS_CUSTOMER_CHECK)
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
	 * 查询某中心下所有客户
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=getCustomerList")
	public ModelAndView getCustomerList(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user=LoginSessionUtils.getUserInSession(request);
		
		String deptId=user.getCenterId()+"";

		List<CustomerInfo> tList =customerInfoService.queryCustomerInfoByDeptId(deptId);

		return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(tList));
	}
	
    protected ResultDto<String> validataData(CustomerInfoDto customerInfoDto){
    	
    	String  deptId=customerInfoDto.getDeptid();
		String  cusName=customerInfoDto.getCusname();
		Integer rule=customerInfoDto.getCusrule();
		
		if(deptId==null || SystemConst.ADMIN_CENTER_ID.equals(deptId)){
		    return new ResultDto<String>(false,"中心ID不能为空或中心不存在，请换账号");
	    }
		if(!StringUtils.hasLength(cusName)){
		    return new ResultDto<String>(false,"客户名称不能为空");
	    }
		if(rule==null){
		    return new ResultDto<String>(false,"入驻结算规则不能为空");
	    }
		
		return new ResultDto<String>(true,"数据通过验证");
	}
	
	
}
