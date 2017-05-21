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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.lq.lss.model.CStockOut;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.SystemConst;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
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
@RequestMapping(value ="/user/stock/stockOutAdd.htm")
public class StockOutAddController extends ShiroBaseController<CStockOut, String, StockOutService>{
	
	@Resource
	private StockOutService stockOutService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	
	
	@Value("/stock/stock_out_add")
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
	
	
	
	@RequestMapping(params = "method=outApply")
	@RequiresPermissions(PermResourceConst.CENTER_OUT_ADD)
	public ModelAndView outApply(HttpServletRequest request,
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
		
			          ResultDto<String> validateResult=validataData(stockOutDto);
			          if(validateResult.isSuccess())
			          {
			        	       result=stockOutService.saveStockOutApplyRdTx(stockOutDto);
			          }else
			          {
			        	       result=validateResult;
			          }
			    //操作日志
			    adminAuditLogService.log(user.getUserId(), TradeType.STOCK_OUT.getType(),"出库单",stockOutDto,0L);    
			    
		} catch (Exception e) {
			    logger.error("提交出库申请数据出现异常", e);
			    result=new ResultDto<String>(false,"数据提交失败");
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}
	
	
	
	protected static ResultDto<String> validataData(StockOutDto stockOutDto){
		
		String deptId=stockOutDto.getDeptid()+"";
		if(deptId==null || SystemConst.ADMIN_CENTER_ID.equals(deptId)){
		    return new ResultDto<String>(false,"中心ID不能为空或中心不存在，请换账号");
	    }
		String outSerialno=stockOutDto.getOutSerialno();
		String mchcode=stockOutDto.getMchcode();
		if(!StringUtils.hasLength(outSerialno)){
		    return new ResultDto<String>(false,"流水号不能为空");
	    }
		if(!StringUtils.hasLength(mchcode)){
		    return new ResultDto<String>(false,"商户号不能为空");
	    }
		
//		String totalM=stockOutDto.getTotalM();
//		if(!StringUtils.hasLength(totalM)){
//		    return new ResultDto<String>(false,"出库总米数不能为空");
//	    }
//		String totalS=stockOutDto.getTotalS();
//		if(!StringUtils.hasLength(totalS)){
//		    return new ResultDto<String>(false,"出库总数量不能为空");
//	    }
//		String totalT=stockOutDto.getTotalT();
//		if(!StringUtils.hasLength(totalT)){
//		    return new ResultDto<String>(false,"出库总重量不能为空");
//	    }
		
		List<StockOutDetailDto> stockOutDetailDtos=stockOutDto.getStockOutDetailDtos();
		int i=1;
		String materialcode;
		//String totalM_;
		String totalS_;
		//String totalT_;
		
		for (StockOutDetailDto stockOutDetailDto : stockOutDetailDtos) {
			   
			    if(stockOutDetailDto==null){
			    	    return new ResultDto<String>(false,"第"+i+"行,项目参数为空");
			    }
			    materialcode=stockOutDetailDto.getMaterialcode();
			    //totalM_=stockOutDetailDto.getTotalM();
			    totalS_=stockOutDetailDto.getTotalS();
			    //totalT_=stockOutDetailDto.getTotalT();
				
				if (!StringUtils.hasLength(materialcode))
				{
					    return new ResultDto<String>(false,"第"+i+"行,物资编号不能为空");
				}
//				if (!StringUtils.hasLength(totalM_))
//				{
//					    return new ResultDto<String>(false,"第"+i+"行,出库总长度不能为空");
//				}
				if (!StringUtils.hasLength(totalS_))
				{
					    return new ResultDto<String>(false,"第"+i+"行,数量 不能为空");
				}
//				if (!StringUtils.hasLength(totalT_))
//				{
//					    return new ResultDto<String>(false,"第"+i+"行,出库总量不能为空");
//				}
				try {
						//double totalMD=Double.parseDouble(totalM_);
						//double totalSD=Double.parseDouble(totalS_);
						//double totalTD=Double.parseDouble(totalT_);
//						
//						if(totalTD!=totalMD*totalSD)
//						{
//							return new ResultDto<String>(false,"第"+i+"行,出库总量有误");
//						}
				} catch (NumberFormatException e) {
					    e.printStackTrace();
					    return new ResultDto<String>(false,"第"+i+"行,数据转化异常");
				}
				i++;
		}
		return new ResultDto<String>(true,"数据通过验证");
	}
	
	
	
	
}
