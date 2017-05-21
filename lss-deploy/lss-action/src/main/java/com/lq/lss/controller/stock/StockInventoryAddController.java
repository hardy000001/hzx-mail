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
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.lss.model.AdminDept;
import com.lq.lss.model.CStockInventory;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.SystemConst;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.AdminDeptService;
import com.lq.lss.core.service.StockInventoryDetailService;
import com.lq.lss.core.service.StockInventoryService;
import com.lq.lss.dto.StockInventoryDetailDto;
import com.lq.lss.dto.StockInventoryDto;
/**
 * 
 * @author  作者: hzx
 * @date 创建时间: 2016-12-29下午4:03:17
 */
@Controller
@RequestMapping(value ="/user/stock/stockInventoryAdd.htm")
public class StockInventoryAddController extends ShiroBaseController<CStockInventory, String, StockInventoryService>{
	
	@Resource
	private StockInventoryService stockInventoryService;
	@Resource
	private StockInventoryDetailService stockInventoryDetailService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	@Resource
	private AdminDeptService adminDeptService;
	
	
	@Value("/stock/stock_inventory_add")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.STOCK_INVENTORY_ADD);
        modelMap.put("update", PermResourceConst.STOCK_INVENTORY_UPDATE);
        modelMap.put("del", PermResourceConst.STOCK_INVENTORY_DEL);
        modelMap.put("check", PermResourceConst.STOCK_INVENTORY_CHECK);
		modelMap.put("deptid", user.getCenterId());
		modelMap.put("mchcode", SystemConst.ZX_MCHCODE);
		
		AdminDept adminDept= adminDeptService.get(user.getCenterId());
		if(adminDept==null){
			adminDept=new AdminDept();
		}
		modelMap.put("adminDept", adminDept);
		modelMap.put("user", user);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	@RequestMapping(params = "method=getInventoryList")
	public ModelAndView gerSaleDetailList(HttpServletRequest request,HttpServletResponse response) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		
		String deptId=String.valueOf(user.getCenterId());
		
		String mchcode = ServletRequestUtils.getStringParameter(request,
				"mchcode", "");
		
		List<StockInventoryDetailDto> detialList = null;
		
		if(StringUtils.hasText(mchcode)){
			 detialList = stockInventoryDetailService.queryInventoryByMchcode(deptId, mchcode);
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(detialList));
	}
	
	@RequestMapping(params = "method=inventoryApply")
	@RequiresPermissions(PermResourceConst.STOCK_INVENTORY_ADD)
	public ModelAndView inventoryApply(HttpServletRequest request,
			HttpServletResponse response, StockInventoryDto stockInventoryDto) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<String> result=null;
		
		try 
		{

				String inventorySerialno=stockInventoryDto.getInventorySerialno();
				stockInventoryDto.setInventorySerialno(inventorySerialno);
				stockInventoryDto.setDeptid(user.getCenterId());
				stockInventoryDto.setCreateoper(user.getUserId()+"");
				
				String listStr =stockInventoryDto.getDataList();
				JSONArray json = JSONArray.fromObject(listStr);
				@SuppressWarnings("unchecked")
				List<StockInventoryDetailDto> dataList = (List<StockInventoryDetailDto>) JSONArray
						.toCollection(json, StockInventoryDetailDto.class);
				stockInventoryDto.setStockInventoryDetailDtos(dataList);
		
			          ResultDto<String> validateResult=validataData(stockInventoryDto);
			          if(validateResult.isSuccess())
			          {
			        	       result=stockInventoryService.saveInventoryApplyRdTx(stockInventoryDto);
			          }else
			          {
			        	       result=validateResult;
			          }
			    //操作日志
			    //adminAuditLogService.log(user.getUserId(), TradeType.STOCK_OUT.getType(),"出库单",stockInventoryDto,0L);    
			    
		} catch (Exception e) {
			    logger.error("提交盘点申请数据出现异常", e);
			    result=new ResultDto<String>(false,"数据提交失败");
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}
	
	
	
	protected static ResultDto<String> validataData(StockInventoryDto stockInventoryDto){
		
		String deptId=stockInventoryDto.getDeptid()+"";
		if(deptId==null || SystemConst.ADMIN_CENTER_ID.equals(deptId)){
		    return new ResultDto<String>(false,"中心ID不能为空或中心不存在，请换账号");
	    }
		String inventorySerialno=stockInventoryDto.getInventorySerialno();
		String mchcode=stockInventoryDto.getMchcode();
		if(!StringUtils.hasLength(inventorySerialno)){
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
		
		List<StockInventoryDetailDto> stockInventoryDetailDtos=stockInventoryDto.getStockInventoryDetailDtos();
		int i=1;
		String materialcode;
		//String totalM_;
		//String totalS_;
		//String totalT_;
		
		for (StockInventoryDetailDto stockInventoryDetailDto : stockInventoryDetailDtos) {
			   
			    if(stockInventoryDetailDto==null){
			    	    return new ResultDto<String>(false,"第"+i+"行,项目参数为空");
			    }
			    materialcode=stockInventoryDetailDto.getMaterialcode();
			    //totalM_=stockOutDetailDto.getTotalM();
			    //totalS_=stockInventoryDetailDto.getTotalS();
			    //totalT_=stockOutDetailDto.getTotalT();
				
				if (!StringUtils.hasLength(materialcode))
				{
					    return new ResultDto<String>(false,"第"+i+"行,物资编号不能为空");
				}
//				if (!StringUtils.hasLength(totalM_))
//				{
//					    return new ResultDto<String>(false,"第"+i+"行,出库总长度不能为空");
//				}
//				if (!StringUtils.hasLength(totalS_))
//				{
//					    return new ResultDto<String>(false,"第"+i+"行,数量 不能为空");
//				}
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
