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
import com.lq.lss.controller.util.CodeGenerater;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.controller.util.UploadFileUtil;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.lq.lss.model.CustomerRentinfo;
import com.lq.lss.model.MchRule;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.SystemConst;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.CustomerInfoService;
import com.lq.lss.core.service.CustomerRentinfoService;
import com.lq.lss.core.service.MchRuleService;
import com.lq.lss.dto.CustomerInfoDto;
import com.lq.lss.dto.CustomerRentinfoDto;
import com.lq.lss.enums.TradeType;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-15 14:03:12
 */
@Controller
@RequestMapping(value ="/user/sys/customerRentinfoAdd.htm")
public class CustomerRentinfoAddController extends ShiroBaseController<CustomerRentinfo, String, CustomerRentinfoService>{
	
	@Resource
	private CustomerInfoService customerInfoService;
	@Resource
	private MchRuleService mchRuleService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	@Resource
	private CustomerRentinfoService customerRentinfoService;
	
	
	@Value("/base/customer_rentinfo_add")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user=LoginSessionUtils.getUserInSession(request);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.SYS_CUSTOMER_RENTINFO_ADD);
        modelMap.put("update", PermResourceConst.SYS_CUSTOMER_RENTINFO_UPDATE);
        modelMap.put("del", PermResourceConst.SYS_CUSTOMER_RENTINFO_DEL);
        modelMap.put("check", PermResourceConst.SYS_CUSTOMER_RENTINFO_CHEKE);
        
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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=saveCustomerRentinfo")
	@RequiresPermissions(PermResourceConst.SYS_CUSTOMER_RENTINFO_ADD)
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
			
			String listStr = request.getParameter("dataList");
			JSONArray json = JSONArray.fromObject(listStr);
			List<CustomerRentinfoDto> dataList = (List<CustomerRentinfoDto>) JSONArray
					.toCollection(json, CustomerRentinfoDto.class);
			customerInfoDto.setCustomerRentinfoDtos(dataList);
			
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
	
    protected static ResultDto<String> validataData(CustomerInfoDto customerInfoDto){
    	
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
