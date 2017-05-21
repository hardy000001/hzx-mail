/**
 * 
 */
package com.lq.lss.controller.stock;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.easyui.controller.easyui.EasyUIController;
import com.lq.easyui.dto.ResultDto;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.MaterialInfoService;
import com.lq.lss.core.service.MaterialTypeService;
import com.lq.lss.core.service.MchBaseinfoService;
import com.lq.lss.core.service.RepairInfoService;
import com.lq.lss.core.service.StockInService;
import com.lq.lss.core.service.StockTransferService;
import com.lq.lss.dto.BusPurDetailDto;
import com.lq.lss.dto.CStockInRepairinfoDto;
import com.lq.lss.dto.StockInDetailDto;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.BConsumableType;
import com.lq.lss.model.BMaterialInfo;
import com.lq.lss.model.BMaterialType;
import com.lq.lss.model.BRepairInfo;
import com.lq.lss.model.CStockIn;
import com.lq.lss.model.CStockInDetail;
import com.lq.lss.model.CStockTransfer;
import com.lq.lss.model.EasyUIDept;
import com.lq.lss.model.EasyUITree;
import com.lq.lss.model.MchBaseinfo;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.SystemConst;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;
import com.lq.util.TimeUtil;

/**
 * @author eric
 */
@Controller
@RequestMapping(value = "/user/stock/stocktemporaryinAdd.htm")
public class StockTemporaryInAddController extends EasyUIController<CStockIn, String, StockInService> {

	@Resource
	private MaterialTypeService materialTypeService;
	@Resource
	private StockInService stockInService;
	@Resource
	private MaterialInfoService materialInfoService;
	@Resource
	private MchBaseinfoService mchInfoService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	@Resource
	private RepairInfoService repairInfoService;
	
	@Value("/stock/stock_temporary_in_add")
	private String indexView;

	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {

		SessionUser user = LoginSessionUtils.getUserInSession(request);
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		modelMap.put("add", PermResourceConst.STOCK_TEMPORARY_IN_ADD);
		modelMap.put("update", PermResourceConst.STOCK_TEMPORARY_IN_UPDATE);
		modelMap.put("del", PermResourceConst.STOCK_TEMPORARY_IN_DEL);
		modelMap.put("check", PermResourceConst.STOCK_TEMPORARY_IN_CHECK);
		 List<BRepairInfo> repairList=repairInfoService.loadAll();
		modelMap.put("repairList", repairList);
		modelMap.put("deptid", user.getCenterId());
		
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}


	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=getMaInfoList")
	public ModelAndView getMaInfoList(HttpServletRequest request, HttpServletResponse response) {
	
		List<BMaterialInfo> tList = materialInfoService.loadAll();

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(tList));
	}
	
	
	@RequestMapping(params = "method=getMchInfoList")
	public ModelAndView getMchInfoList(HttpServletRequest request, HttpServletResponse response) {
	
		List<MchBaseinfo> tList = mchInfoService.loadAll();

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(tList));
	}
	
	
	@RequestMapping(params = "method=save")
	@RequiresPermissions(PermResourceConst.STOCK_TEMPORARY_IN_ADD)
	@Override
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
		
	    String itemData =	request.getParameter("itemData");
	    String inSerialno =	request.getParameter("tsfSerialno");
	    String fromMchcode =	request.getParameter("fromMchcode");
	    String zxFee =	request.getParameter("zxFee");
	    String otherFee =	request.getParameter("otherFee");
	    String transportFee =	request.getParameter("transportFee");
	    
	    String outOperator =	request.getParameter("outOperator");
	    String inOperator =	request.getParameter("inOperator");
	    String remark =	request.getParameter("remark");
	    String transDate =	request.getParameter("transDate");
	    String carNo =	request.getParameter("carNo");
	    String carDriver =	request.getParameter("carDriver");
	    String isrepair =	request.getParameter("isrepair");
	      
		JSONArray json = JSONArray.fromObject(itemData);
		
		@SuppressWarnings("unchecked")
		List<StockInDetailDto> dataList = (List<StockInDetailDto>) JSONArray
				.toCollection(json, StockInDetailDto.class);
		
		
		List<CStockInRepairinfoDto> repairs=repairItemJsonDispose(itemData, inSerialno);
	    //SessionHelper
	    SessionUser sessionUser = LoginSessionUtils.getUserInSession(request);
	    
	    //入库信息对象
	    CStockIn cStockIn=new CStockIn();
	    cStockIn.setInSerialno(inSerialno);
	    cStockIn.setDeptid(sessionUser.getCenterId());
	    cStockIn.setCreateoper(sessionUser.getOperCode());
	    cStockIn.setMchcode(fromMchcode);
	    if(zxFee.isEmpty()){
	    	zxFee="0";
	    }
	    if(transportFee.isEmpty()){
	    	transportFee="0";
	    }
	    if(otherFee.isEmpty()){
	    	otherFee="0";
	    }
	    cStockIn.setZxFee(new BigDecimal(Double.parseDouble(zxFee)));
	    cStockIn.setOtherFee(new BigDecimal(Double.parseDouble(otherFee)));
	    cStockIn.setTransportFee(new BigDecimal(Double.parseDouble(otherFee)));
	    
	    cStockIn.setCreateoper(sessionUser.getLoginName());
	    cStockIn.setOutOperator(outOperator);
	    cStockIn.setInOperator(inOperator);
	    cStockIn.setIsrepair(isrepair);
	    
	    
	    //入库状态为未审核
	    cStockIn.setStatus("1");
	    cStockIn.setRemark(remark);
	    Date cdate=null;
	    if(transDate!=null || !transDate.equals("")){
	     cdate=TimeUtil.get().parseTime(transDate);
	    }
	    cStockIn.setTransDate(cdate);
	    cStockIn.setCarNo(carNo);
	    cStockIn.setCarDriver(carDriver);
	    cStockIn.setCreatetime(new Date());
	  
	   
		ResultDto<?> resultDto
	    = stockInService.saveStockInRdTx(cStockIn, dataList,repairs);
		 adminAuditLogService.log(sessionUser.getUserId(), TradeType.STOCK_TEMPORARY_IN.getType(),"新增暂存单",cStockIn,0L);
		return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
	}
	
	
	/**
	 * 查询类别名称tree
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(params = "method=getMaTypeList")
	public ModelAndView getConTypeList(HttpServletRequest request,HttpServletResponse response) {

		String typeId = ServletRequestUtils.getStringParameter(request, "id","");
		String typeName = ServletRequestUtils.getStringParameter(request,"typeName", "");

		BConsumableType conType = new BConsumableType();
		if (StringUtils.hasText(typeId)) {
			conType.setTypeid(Integer.parseInt(typeId));
		}
		if (StringUtils.hasText(typeName)) {
			conType.setName(URLDecoder.decode(typeName));
		}

		List<EasyUITree> treeList = new ArrayList<EasyUITree>();

		if(!StringUtils.hasText(typeId)){  // 无tree的id 值，默认加载物资类别tree
			List<BMaterialType> typeList = materialTypeService.loadAll();
			for (BMaterialType materialType : typeList) { 	// 只做二级处理
				if (materialType.getParentid() == 0) { // 一级节点
					EasyUITree tree = new EasyUITree();
					tree.setState("open");
					tree.setId(materialType.getTypeid() + "");
					tree.setText(materialType.getName());
					// 加载子节点
					List<EasyUITree> treeList1 = new ArrayList<EasyUITree>();
					for (BMaterialType materialType1 : typeList) {
						if ( materialType1.getParentid()==materialType.getTypeid()) {
							EasyUITree tree1 = new EasyUITree();
							tree1.setState("closed");
							tree1.setId(materialType1.getTypeid() + "");
							tree1.setText(materialType1.getName());
							treeList1.add(tree1);
							//加载材料物资信息
							   List<EasyUITree> treeList2 = new ArrayList<EasyUITree>();
						      List<BMaterialInfo> maInfoList = materialInfoService.queryMaInfoList(materialType1.getTypeid());
						        for (BMaterialInfo bMaterialInfo : maInfoList) {
						    		EasyUITree treeInfo = new EasyUITree();
									treeInfo.setState("open");
									treeInfo.setAttributes(bMaterialInfo.getAccountFlag());
									treeInfo.setFiled1("1"); // 表示材料项目
									treeInfo.setId(bMaterialInfo.getCode());
									treeInfo.setText(bMaterialInfo.getName());
									treeList2.add(treeInfo);
							   }
						        tree1.setChildren(treeList2);
						}
					}
					tree.setChildren(treeList1);
					treeList.add(tree);
				}
			}
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(treeList));
	}
	
	
	
	/**
	 * 维修项json字符处理
	 * @param json
	 * @param inSerialno 入库流水号
	 * @return
	 */
	protected static List<CStockInRepairinfoDto> repairItemJsonDispose(String json,String inSerialno) {
		 
		 JSONArray arry = JSONArray.fromObject(json);
        
         //List<Map<String, String>> rsList = new ArrayList<Map<String, String>>(); 
         List<CStockInRepairinfoDto> stockInRepairinfoDtos=null;
         for (int i = 0; i < arry.size(); i++) 
         { 
             JSONObject jsonObject = arry.getJSONObject(i); 
             //Map<String, String> map = new HashMap<String, String>(); 
             String autoNo=inSerialno+i;
             for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) 
             {
            	 CStockInRepairinfoDto stockInRepairinfoDto=null;
                 String key = (String) iter.next(); 
                 String value = jsonObject.get(key).toString(); 
                 String prefix=SystemConst.PROPERTY_PREFIX;
                 if(StringUtils.hasLength(value) && key.contains(prefix))
                 {
                	     if(stockInRepairinfoDtos==null){
                	    	 stockInRepairinfoDtos=new ArrayList<CStockInRepairinfoDto>();
                	     }
                	     stockInRepairinfoDto=new CStockInRepairinfoDto();
                	     stockInRepairinfoDto.setReceiptDetailId(autoNo);
                	     stockInRepairinfoDto.setRepairId(key.replace(prefix, ""));
                	     stockInRepairinfoDto.setRepairFee(value);
                	     stockInRepairinfoDtos.add(stockInRepairinfoDto);
                 }
                 //map.put(key, value); 
             } 
             //rsList.add(map); 
         } 
         return stockInRepairinfoDtos;
	}

}
