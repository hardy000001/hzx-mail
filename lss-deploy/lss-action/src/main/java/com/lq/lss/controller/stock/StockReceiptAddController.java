package com.lq.lss.controller.stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;
import com.lq.easyui.dto.ResultDto;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.lss.model.AdminDept;
import com.lq.lss.model.BRepairInfo;
import com.lq.lss.model.CStockReceipt;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.SystemConst;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.AdminDeptService;
import com.lq.lss.core.service.RepairInfoService;
import com.lq.lss.core.service.StockReceiptService;
import com.lq.lss.dto.StockReceiptDetailDto;
import com.lq.lss.dto.StockReceiptDto;
import com.lq.lss.dto.StockReceiptRepairinfoDto;
import com.lq.lss.enums.TradeType;

/**
 * 收料
 * @author  作者: hzx
 * @date 创建时间: 2016-11-09 12:31:33
 */
@Controller
@RequestMapping(value ="/user/stock/stockReceiptAdd.htm")
public class StockReceiptAddController extends ShiroBaseController<CStockReceipt, String, StockReceiptService>{
	
	@Resource
	private StockReceiptService stockReceiptService;
	@Resource
	private RepairInfoService repairInfoService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	@Resource
	private AdminDeptService adminDeptService;
	
	@Value("/stock/stock_receipt_add")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		AdminDept adminDept= adminDeptService.get(user.getCenterId());
		if(adminDept==null){
			adminDept=new AdminDept();
		}
		List<BRepairInfo> repairList=repairInfoService.loadAll();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("user", user);
		modelMap.put("repairList", repairList);
		modelMap.put("adminDept", adminDept);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	
	
	@RequestMapping(params = "method=receiptApply")
	@RequiresPermissions(PermResourceConst.CENTER_RECEIPT_ADD)
	public ModelAndView receiptApply(HttpServletRequest request,
			HttpServletResponse response, StockReceiptDto stockReceiptDto) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<String> result=null;
		
		try 
		{

				String receiptSerialno=stockReceiptDto.getReceiptSerialno();
				stockReceiptDto.setReceiptSerialno(receiptSerialno);
				stockReceiptDto.setCreateoper(user.getUserId()+"");
				stockReceiptDto.setDeptid(user.getCenterId());
				stockReceiptDto.setMchcode(SystemConst.ZX_MCHCODE);
				
				String listStr =stockReceiptDto.getDataList();
				
				JSONArray json = JSONArray.fromObject(listStr);
				@SuppressWarnings("unchecked")
				List<StockReceiptDetailDto> dataList = (List<StockReceiptDetailDto>) JSONArray
						.toCollection(json, StockReceiptDetailDto.class);
				stockReceiptDto.setStockReceiptDetailDtos(dataList);
				List<StockReceiptRepairinfoDto> repairs=repairItemJsonDispose(listStr, receiptSerialno);
				stockReceiptDto.setStockReceiptRepairinfoDtos(repairs);
		
			          ResultDto<String> validateResult=validataData(stockReceiptDto);
			          if(validateResult.isSuccess())
			          {
			        	       result=stockReceiptService.saveStockReceiptApplyRdTx(stockReceiptDto);
			          }else
			          {
			        	       result=validateResult;
			          }
			    //操作日志
			    adminAuditLogService.log(user.getUserId(), TradeType.STOCK_RECEIPT.getType(),"收料单",stockReceiptDto,0L);    
		} catch (Exception e) {
			    logger.error("提交收料申请数据出现异常", e);
			    result=new ResultDto<String>(false,"数据提交失败");
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}
	
	
	
	protected static ResultDto<String> validataData(StockReceiptDto stockReceiptDto){
		
		String deptId=stockReceiptDto.getDeptid()+"";
		if(deptId==null || SystemConst.ADMIN_CENTER_ID.equals(deptId)){
		    return new ResultDto<String>(false,"中心ID不能为空或中心不存在，请换账号");
	    }
		String receiptSerialno=stockReceiptDto.getReceiptSerialno();
		String mchcode=stockReceiptDto.getMchcode();
		if(!StringUtils.hasLength(receiptSerialno)){
		    return new ResultDto<String>(false,"流水号不能为空");
	    }
		if(!StringUtils.hasLength(mchcode)){
		    return new ResultDto<String>(false,"商户号不能为空");
	    }
		
		
		List<StockReceiptDetailDto> stockReceiptDetailDtos=stockReceiptDto.getStockReceiptDetailDtos();
		int i=1;
		String materialcode;
		String totalM_;
		String totalS_;
//		String totalT_;
		
		for (StockReceiptDetailDto stockReceiptDetailDto : stockReceiptDetailDtos) {
			   
			    if(stockReceiptDetailDto==null){
			    	    return new ResultDto<String>(false,"第"+i+"行,项目参数为空");
			    }
			    materialcode=stockReceiptDetailDto.getMaterialcode();
			    totalM_=stockReceiptDetailDto.getTotalM();
			    totalS_=stockReceiptDetailDto.getTotalS();
//			    totalT_=stockReceiptDetailDto.getTotalT();
				
				if (!StringUtils.hasLength(materialcode))
				{
					    return new ResultDto<String>(false,"第"+i+"行,物资编号不能为空");
				}
				if (!StringUtils.hasLength(totalM_))
				{
					    return new ResultDto<String>(false,"第"+i+"行,出库总长度不能为空");
				}
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
	/**
	 * 维修项json字符处理
	 * @param json
	 * @param receiptSerialno 出库流水号
	 * @return
	 */
	protected static List<StockReceiptRepairinfoDto> repairItemJsonDispose(String json,String receiptSerialno) {
		 
		 JSONArray arry = JSONArray.fromObject(json);
        
         //List<Map<String, String>> rsList = new ArrayList<Map<String, String>>(); 
         List<StockReceiptRepairinfoDto> stockReceiptRepairinfoDtos=null;
         for (int i = 0; i < arry.size(); i++) 
         { 
             JSONObject jsonObject = arry.getJSONObject(i); 
             //Map<String, String> map = new HashMap<String, String>(); 
             String autoNo=receiptSerialno+i;
             for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) 
             {
            	 StockReceiptRepairinfoDto stockReceiptRepairinfoDto=null;
                 String key = (String) iter.next(); 
                 String value = jsonObject.get(key).toString(); 
                 String prefix=SystemConst.PROPERTY_PREFIX;
                 if(StringUtils.hasLength(value) && key.contains(prefix))
                 {
                	     if(stockReceiptRepairinfoDtos==null){
                	    	        stockReceiptRepairinfoDtos=new ArrayList<StockReceiptRepairinfoDto>();
                	     }
                	     stockReceiptRepairinfoDto=new StockReceiptRepairinfoDto();
                	     stockReceiptRepairinfoDto.setReceiptDetailId(autoNo);
                	     stockReceiptRepairinfoDto.setRepairId(key.replace(prefix, ""));
                	     stockReceiptRepairinfoDto.setRepairFee(value);
                	     stockReceiptRepairinfoDtos.add(stockReceiptRepairinfoDto);
                 }
                 //map.put(key, value); 
             } 
             //rsList.add(map); 
         } 
         return stockReceiptRepairinfoDtos;
	}
	
	
}
