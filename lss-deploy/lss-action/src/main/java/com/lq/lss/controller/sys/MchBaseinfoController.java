package com.lq.lss.controller.sys;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.annotation.Resource;

import com.lq.lss.controller.shiro.ShiroBaseController;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;

import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;
import com.lq.easyui.dto.ResultDto;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.lq.lss.model.MchBaseinfo;
import com.lq.lss.model.MchRule;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.SystemConst;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.controller.util.CodeGenerater;
import com.lq.lss.controller.util.UploadFileUtil;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.MchBaseinfoService;
import com.lq.lss.core.service.MchRuleService;
import com.lq.lss.dto.MchBaseinfoDto;
import com.lq.lss.enums.CheckStatus;
import com.lq.lss.enums.TradeType;

/**
 * 租赁商户
 * @author  作者: hzx
 * @date 创建时间: 2016-08-31 10:21:24
 */
@Controller
@RequestMapping(value ="/user/sys/mchBaseinfo.htm")
public class MchBaseinfoController extends ShiroBaseController<MchBaseinfo, Integer, MchBaseinfoService> {
	
	@Resource
	private MchBaseinfoService mchBaseinfoService;
	@Resource
	private MchRuleService mchRuleService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	
	
	
	
	@Value("/base/mch_baseinfo")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user=LoginSessionUtils.getUserInSession(request);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.SYS_MCH_ADD);
        modelMap.put("update", PermResourceConst.SYS_MCH_UPDATE);
        modelMap.put("del", PermResourceConst.SYS_MCH_DEL);
        modelMap.put("check", PermResourceConst.SYS_MCH_CHECK);
        
		List<MchRule> mchRuleList=mchRuleService.loadAll();
		modelMap.put("mchRuleList", mchRuleList);
		modelMap.put("deptId", user.getCenterId());
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	@RequestMapping(params = "method=openAccountApply")
	@RequiresPermissions(PermResourceConst.SYS_MCH_ADD)
	public ModelAndView openAccountApply(HttpServletRequest request, HttpServletResponse response,
			MchBaseinfoDto mchBaseinfoDto) {
		
		SessionUser user=LoginSessionUtils.getUserInSession(request);
		
		String mchlicence="";
		String contracturl="";
		MchBaseinfo mchBaseinfo=null;

		ResultDto<String> result=null;
		try {
			mchBaseinfoDto.setMchrule(1); //设置默认规则
            ResultDto<String> resultData=validataData(mchBaseinfoDto, user);
			if (!resultData.isSuccess()) {
				result = resultData;
				return AjaxModelAndViewUtils.writeMsgReturnNull(response,
						JSONUtil.toJSonString(result));
			}
			mchlicence = UploadFileUtil.uploadFile(request, "mchlicence","ZZ");
			contracturl = UploadFileUtil.uploadFile(request, "contracturl","HT");
			
			mchBaseinfo=new MchBaseinfo();
			//页面传入信息
			mchBaseinfo.setMchname(mchBaseinfoDto.getMchname());
			mchBaseinfo.setMchrule(mchBaseinfoDto.getMchrule());
			mchBaseinfo.setMchtel(mchBaseinfoDto.getMchtel());
			mchBaseinfo.setMchaddress(mchBaseinfoDto.getMchaddress());
			mchBaseinfo.setMchlicence(mchlicence);
			mchBaseinfo.setContracturl(contracturl);
			mchBaseinfo.setBankinfo(mchBaseinfoDto.getBankinfo());
			mchBaseinfo.setBankaccount(mchBaseinfoDto.getBankaccount());
			mchBaseinfo.setAccountno(mchBaseinfoDto.getAccountno());
			//session信息
			mchBaseinfo.setDeptid(user.getCenterId());
			mchBaseinfo.setCreateoper(Integer.parseInt(user.getUserId()+""));
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
			      String maxMchCode=mchBaseinfoService.queryMaxMchCodeByDeptId("");
			      logger.debug("最大商户号为"+maxMchCode);
			      String mchcode=CodeGenerater.getInstance().generaterNextMchCode(maxMchCode);	
			      mchBaseinfo.setMchcode(Long.parseLong(mchcode));
			      result=mchBaseinfoService.saveMchBaseinfoRdTx(mchBaseinfo);

				  //操作日志
				  adminAuditLogService.log(user.getUserId(), TradeType.MCH_BASEINFO.getType(),"新增商户",mchBaseinfoDto,0L);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取商户号出现异常"+e.getMessage());
			result=new ResultDto<String>(false, "获取商户号出现异常");
			return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
	}
	
	@RequestMapping(params = "method=remove")
	@RequiresPermissions(PermResourceConst.SYS_MCH_DEL)
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
			result=mchBaseinfoService.deleteInfoById(idStr, user.getCenterId()+"");
			//操作日志
			Long mchcodeL=Long.parseLong(idStr);
			adminAuditLogService.log(user.getUserId(), TradeType.MCH_BASEINFO.getType(),"删除商户",idStr,mchcodeL);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除商户数据出现异常"+e.getMessage());
			result=new ResultDto<String>(false,"删除数据出现异常");
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}
	
	@RequestMapping(params = "method=updateInfo")
	@RequiresPermissions(PermResourceConst.SYS_MCH_UPDATE)
	public ModelAndView updateInfo(HttpServletRequest request, HttpServletResponse response,
			MchBaseinfoDto mchBaseinfoDto,String status) {
		
		SessionUser user=LoginSessionUtils.getUserInSession(request);
		
		String mchlicence="";
		String contracturl="";
		MchBaseinfo mchBaseinfo=null;

		ResultDto<String> result=null;
		
		String mchcode="";

		try {
			
			ResultDto<String> resultData=validataData(mchBaseinfoDto, user);
			  
			if (!resultData.isSuccess()) {
				result = resultData;
				return AjaxModelAndViewUtils.writeMsgReturnNull(response,
						JSONUtil.toJSonString(result));
			}
			mchcode=mchBaseinfoDto.getMchcode()+"";
			String deptId=user.getCenterId()+"";
		    MchBaseinfo mchBaseinfo2=mchBaseinfoService.findMchInfoByIdAndDeptId(mchcode, deptId);
		    if(mchBaseinfo2!=null)
		    {
		    	String status2=mchBaseinfo2.getStatus()+"";
		    	if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status2))
			    {
		    		result=new ResultDto<String>(false,"已审核数据不可以修改");
			    	return AjaxModelAndViewUtils.writeMsgReturnNull(response,
							JSONUtil.toJSonString(result));
			    }
		    }else{
		    	result=new ResultDto<String>(false,"商户不存在");
		    	return AjaxModelAndViewUtils.writeMsgReturnNull(response,
						JSONUtil.toJSonString(result));
		    }
			
			mchlicence = UploadFileUtil.uploadFile(request, "mchlicence","ZZ");
			contracturl = UploadFileUtil.uploadFile(request, "contracturl","HT");
			
			mchBaseinfo=new MchBaseinfo();
			//页面传入信息
			mchBaseinfo.setId(mchBaseinfoDto.getId());
			mchBaseinfo.setMchcode(mchBaseinfoDto.getMchcode());
			mchBaseinfo.setMchname(mchBaseinfoDto.getMchname());
			mchBaseinfo.setMchrule(mchBaseinfoDto.getMchrule());
			mchBaseinfo.setMchtel(mchBaseinfoDto.getMchtel());
			mchBaseinfo.setMchaddress(mchBaseinfoDto.getMchaddress());
			mchBaseinfo.setMchlicence(mchlicence);
			mchBaseinfo.setContracturl(contracturl);
			mchBaseinfo.setBankinfo(mchBaseinfoDto.getBankinfo());
			mchBaseinfo.setBankaccount(mchBaseinfoDto.getBankaccount());
			mchBaseinfo.setAccountno(mchBaseinfoDto.getAccountno());
			//session信息
			mchBaseinfo.setDeptid(user.getCenterId());
			mchBaseinfo.setCreateoper(Integer.parseInt(user.getUserId()+""));
			mchBaseinfo.setModifytime(new Date());
			
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
			mchBaseinfoService.update(mchBaseinfo);
			result=new ResultDto<String>(true, "数据修改成功");
			//操作日志
			Long mchcodeL=Long.parseLong(mchcode);
			adminAuditLogService.log(user.getUserId(), TradeType.MCH_BASEINFO.getType(),"修改商户",mchBaseinfoDto,mchcodeL);
		} catch (Exception e) {
			e.printStackTrace();
			result=new ResultDto<String>(false, "修改数据异常");
			logger.error("修改数据异常"+e.getMessage());
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
	}
	@RequestMapping(params = "method=auditInfo")
	@RequiresPermissions(PermResourceConst.SYS_MCH_CHECK)
	public ModelAndView auditInfo(HttpServletRequest request, HttpServletResponse response,
			MchBaseinfoDto mchBaseinfoDto) {
		
			SessionUser user=LoginSessionUtils.getUserInSession(request);
			
			String status=mchBaseinfoDto.getStatus();
			ResultDto<String> result=null;
			if(!StringUtils.hasLength(status)){
				result=new ResultDto<String>(false,"状态不能为空");
				return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
			}
			
			try {
				mchBaseinfoDto.setCreateoper(Integer.parseInt(user.getUserId()+""));
				mchBaseinfoDto.setDeptid(user.getCenterId()+"");
				result=mchBaseinfoService.updateMchBaseinfoRdTx(mchBaseinfoDto);
				//操作日志
				Long mchcodeL=Long.parseLong(mchBaseinfoDto.getMchcode()+"");
				adminAuditLogService.log(user.getUserId(), TradeType.MCH_BASEINFO.getType(),"审核商户",mchBaseinfoDto,mchcodeL);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				logger.error("数据转换异常");
				result=new ResultDto<String>(false,"数据转换异常");
			}
		
		
		    return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
	}
	
    protected ResultDto<String> validataData(MchBaseinfoDto mchBaseinfoDto,SessionUser user){
    	
    	String deptId=user.getCenterId()+"";
		String mchName=mchBaseinfoDto.getMchname();
		Integer rule=mchBaseinfoDto.getMchrule();
		
		if(deptId==null || SystemConst.ADMIN_CENTER_ID.equals(deptId)){
		    return new ResultDto<String>(false,"中心ID不能为空或中心不存在，请换账号");
	    }
		if(!StringUtils.hasLength(mchName)){
		    return new ResultDto<String>(false,"商户名称不能为空");
	    }
		if(rule==null){
		    return new ResultDto<String>(false,"入驻结算规则不能为空");
	    }
		
		return new ResultDto<String>(true,"数据通过验证");
	}

	/**
	 * 获取当前登陆用户，下属中心下的商户。
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=getMchInfoList")
	public ModelAndView getMchInfoList(HttpServletRequest request, HttpServletResponse response) {
		
		//获取中心ID
		SessionUser user=LoginSessionUtils.getUserInSession(request);
		
		List<MchBaseinfo> tList = mchBaseinfoService.queryMchBaseinfoByDeptId(user.getCenterId().toString());

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(tList));
	}

	/**
	 *  查询所有商户
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=getMchAllList")
	public ModelAndView getMchAllList(HttpServletRequest request, HttpServletResponse response) {

		List<MchBaseinfo> tList = mchBaseinfoService.loadAll();

		return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(tList));
	}
	
	
	
	@RequestMapping(params = "method=getMchInfAndCustomerinfoList")
	public ModelAndView getMchInfAndCustomerinfoList(HttpServletRequest request, HttpServletResponse response) {
		
		//获取中心ID
		SessionUser user=LoginSessionUtils.getUserInSession(request);
		
		List<MchBaseinfo> tList = mchBaseinfoService.querMchInfAndCustomerinfoByDeptId(user.getCenterId().toString());

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(tList));
	}
	
    
}
