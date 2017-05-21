package com.lq.lss.controller.excel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Value;

import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;
import com.lq.util.RandomUtil;
import com.lq.util.TimeUtil;
import com.lq.util.ValidUtil;
import com.lq.easyui.dto.ResultDto;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lq.lss.model.AdminDept;
import com.lq.lss.model.BMaterialInfo;
import com.lq.lss.model.BMaterialType;
import com.lq.lss.model.CBusHt;
import com.lq.lss.model.CBusHtRentinfo;
import com.lq.lss.model.CStockIn;
import com.lq.lss.model.CStockInfo;
import com.lq.lss.model.CStockInventory;
import com.lq.lss.model.CStockTransfer;
import com.lq.lss.model.CStockTransferDetail;
import com.lq.lss.model.CustomerInfo;
import com.lq.lss.model.CustomerRentinfo;
import com.lq.lss.model.MchBaseinfo;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.SystemConst;
import com.lq.lss.core.dao.CustomerRentinfoDao;
import com.lq.lss.core.dao.MaterialInfoDao;
import com.lq.lss.core.dao.StockInDetailDao;
import com.lq.lss.core.dao.StockInfoDao;
import com.lq.lss.core.dao.StockOutDetailDao;
import com.lq.lss.core.service.AdminDeptService;
import com.lq.lss.core.service.BusHtRentinfoService;
import com.lq.lss.core.service.BusHtService;
import com.lq.lss.core.service.CustomerInfoService;
import com.lq.lss.core.service.MaterialTypeService;
import com.lq.lss.core.service.MchBaseinfoService;
import com.lq.lss.core.service.StockCenterTransferInService;
import com.lq.lss.core.service.StockCenterTransferService;
import com.lq.lss.core.service.StockInService;
import com.lq.lss.core.service.StockInfoService;
import com.lq.lss.core.service.StockInventoryDetailService;
import com.lq.lss.core.service.StockInventoryService;
import com.lq.lss.core.service.StockOutService;
import com.lq.lss.core.service.StockReceiptService;
import com.lq.lss.core.service.StockSendService;
import com.lq.lss.core.service.StockTransferService;
import com.lq.lss.dto.AuditDto;
import com.lq.lss.dto.CStockCenterTransferDetailDto;
import com.lq.lss.dto.CStockCenterTransferDto;
import com.lq.lss.dto.CStockInRepairinfoDto;
import com.lq.lss.dto.CStockSendDetailDto;
import com.lq.lss.dto.CStockSendDto;
import com.lq.lss.dto.StockInDetailDto;
import com.lq.lss.dto.StockInventoryDetailDto;
import com.lq.lss.dto.StockOutDetailDto;
import com.lq.lss.dto.StockOutDto;
import com.lq.lss.dto.StockReceiptDetailDto;
import com.lq.lss.dto.StockReceiptDto;

/**
 * Excel 导入数据
 * @author  作者: hzx
 * @date 创建时间: 2017-1-2下午8:28:02
 */
@Controller
@RequestMapping(value ="/user/excel/importInitData.htm")
public class ImportInitDataController extends ShiroBaseController<CStockInventory, String, StockInventoryService>{
	
	@Resource
	private StockInventoryService stockInventoryService;
	@Resource
	private StockInventoryDetailService stockInventoryDetailService;
	@Resource
	private AdminDeptService adminDeptService;
	@Resource
	private StockInfoService stockInfoService;
	@Resource
	private MchBaseinfoService mchBaseinfoService;
	@Resource
	private BusHtService  busHtService;
	@Resource
	private BusHtRentinfoService busHtRentinfoService;
	@Resource
	MaterialTypeService materialTypeService;
	@Resource
	private CustomerRentinfoDao customerRentinfoDao;
	@Resource
	private CustomerInfoService customerInfoService;
	@Resource
    private MaterialInfoDao materialInfoDao;
	@Resource
	private StockInfoDao stockInfoDao;
	@Resource
	private StockOutService stockOutService;
	@Resource
	private StockOutDetailDao stockOutDetailDao;
	@Resource
	private StockInService stockInService;
	@Resource
	private StockInDetailDao stockInDetailDao;
	@Resource
	private StockCenterTransferInService stockCenterTransferInService;
	@Resource
	private StockCenterTransferService stockCenterTransferService;
	
	@Resource
	private StockSendService sendService;
	@Resource
	private StockReceiptService stockReceiptService;
	@Resource
	private StockTransferService stockTransferService;
	
	@Value("/excel/import_init_data")
	private String indexView;
	
	private String impData="2017-01-01 10:00:00";
	
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
	
	@RequestMapping(params = "method=saveStockInitData")
	public ModelAndView saveStockInitData(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<String> resultDto=new ResultDto<String>();
		
		String deptId=String.valueOf(user.getCenterId());
		
		try {
			
			 List<BMaterialInfo> bMaterialInfos=materialInfoDao.loadAll();
			 
			 List<MchBaseinfo> tList = mchBaseinfoService.querMchInfAndCustomerinfoByDeptId(deptId);
			 int i=0;
			 Date dateImp=TimeUtil.get().parseTime(impData);
			 
			 for (MchBaseinfo mchBaseinfo : tList) 
			 {
				 List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>(); 
				 logger.debug("=========正初始化"+mchBaseinfo.getMchname()+""+mchBaseinfo.getMchcode());
				 
				 List<StockInventoryDetailDto> stockInfo = stockInfoDao
						.findInventoryByMchcode(mchBaseinfo.getDeptid() + "",
								mchBaseinfo.getMchcode() + "");
				

				 for (BMaterialInfo bMaterialInfo : bMaterialInfos) {
					 
					 boolean isAdd=true;
					 for (StockInventoryDetailDto stockInventoryDetailDto : stockInfo) 
					 {
							  String code=stockInventoryDetailDto.getMaterialcode();
							  if(bMaterialInfo.getCode().equals(code))
							  {
								  isAdd=false;
								  break;
							  }
					 }
					 if(isAdd)
					 {
							 CStockInfo cStockInfo=new CStockInfo();
							 cStockInfo.setDeptid(Integer.parseInt(deptId));
							 cStockInfo.setMchcode(mchBaseinfo.getMchcode()+"");
							 cStockInfo.setMaterialcode(bMaterialInfo.getCode());
							 cStockInfo.setUnit(bMaterialInfo.getAccountFlag());
							 cStockInfo.setTotalS(new BigDecimal(0));
							 cStockInfo.setTotalM(new BigDecimal(0));
							 cStockInfo.setTotalT(new BigDecimal(0));
							 cStockInfo.setModifytime(dateImp);
							 cStockInfos.add(cStockInfo);
					 }
					 
				 }
				 if(cStockInfos!=null && cStockInfos.size()>0)
				 {
					 stockInfoDao.batchCreate(cStockInfos);
				 }
				 
				 logger.debug("==================" + mchBaseinfo.getMchname()
						+ "库存信息添加成功=========商户号：" + mchBaseinfo.getMchcode());
				 i++;
			 }
			 resultDto.setSuccess(true);
			 resultDto.setErrorMsg("库存数据初始化成功"+i+"个");
		} catch (Exception e) {
			logger.error("初始化库存信息异常");
			resultDto.setSuccess(false);
			resultDto.setErrorMsg("初始化库存信息异常");
			e.printStackTrace();
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(resultDto));
	}
	
	@RequestMapping(params = "method=saveData")
	public ModelAndView saveData(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<List<StockInventoryDetailDto>> resultDto=null;
		InputStream is=null;
		String deptId=String.valueOf(user.getCenterId());
		String mchcode = ServletRequestUtils.getStringParameter(request,
				"mchcode", "");
		try {
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			CommonsMultipartFile fileStream = (CommonsMultipartFile) multipartRequest.getFile("excelFile");
			
			String fileName=fileStream.getOriginalFilename().replace(".xlsx", "");
			    //根据文件名执行不一样任务
				if(fileName.contains("调拨"))
				{
					
					return AjaxModelAndViewUtils.writeMsgReturnNull(response,
							JSONUtil.toJSonString(new ResultDto<String>(false,"请选择暂存单")));
				}
                
			    is=fileStream.getInputStream();
				resultDto=readExcelFile(is);
				if(resultDto.isSuccess())
				{
					List<StockInventoryDetailDto> detialList = stockInventoryDetailService
							.queryInventoryByMchcode(deptId, mchcode);
					
					List<StockInventoryDetailDto> dataList=updateData(resultDto, deptId, mchcode, detialList);
					
					resultDto.setParamObj(dataList);
				}
			
		} catch (Exception e) {
			logger.error("读取Excel文件异常");
			e.printStackTrace();
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(resultDto));
	}
	
	
	@RequestMapping(params = "method=batchSaveData")
	public ModelAndView batchSaveData(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<List<StockInventoryDetailDto>> resultDto=null;
		
		String deptId=String.valueOf(user.getCenterId());
		String mchcode = ServletRequestUtils.getStringParameter(request,
				"mchcode", "");
		try {
			
			List<StockInventoryDetailDto> detialList = stockInventoryDetailService
					.queryInventoryByMchcode(deptId, "100000");
			
			List<MchBaseinfo> tList = mchBaseinfoService.querMchInfAndCustomerinfoByDeptId(deptId);
			
			  String path="E:/imp/data";
			  File file=new File(path);
			  File[] tempList = file.listFiles();
			  System.out.println("该目录下对象个数："+tempList.length);
			  for (int i = 0; i < tempList.length; i++) {
			   if (tempList[i].isFile())
               {
				    String fileName=tempList[i].getName().replace(".xlsx", "");
				    //根据文件名执行不一样任务
					if(fileName.contains("暂存"))
					{
						String name=fileName.replace("暂存", "");
						System.out.println("----------正在读 文     件："+tempList[i]);
						resultDto=readExcelFile(tempList[i]+"");
						if(resultDto.isSuccess())
						{
							MchBaseinfo mc=getMchcode(tList, name);
							//addZcData(resultDto, deptId, mc,detialList);
							mchcode=mc.getMchcode()+"";
							updateData(resultDto, deptId, mchcode,detialList);
						}
						System.out.println("=========="+tempList[i]+"执行成功=====");
					}
					if(fileName.contains("调拨"))
					{
							String name=fileName.replace("调拨", "");
							System.out.println("----------正在读 文     件："+tempList[i]);
							resultDto=readExcelFile(tempList[i]+"");
							if(resultDto.isSuccess())
							{
								MchBaseinfo mc=getMchcode(tList, name);
								//addZcData(resultDto, deptId, mc,detialList);
								mchcode=mc.getMchcode()+"";
								addDbData(resultDto, deptId, mc,detialList, user);
							}
							System.out.println("=========="+tempList[i]+"执行成功=====");
					}
					
			      
			   }
			   if (tempList[i].isDirectory()) 
			   {
			       System.out.println("文件夹："+tempList[i]);
			   }
			  }
			
			
		} catch (Exception e) {
			logger.error("读取Excel文件异常");
			e.printStackTrace();
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(resultDto));
	}
	
	@RequestMapping(params = "method=batchZcData")
	public ModelAndView batchZcData(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<List<StockInventoryDetailDto>> resultDto=null;
		
		String deptId=String.valueOf(user.getCenterId());
		String mchcode = ServletRequestUtils.getStringParameter(request,
				"mchcode", "");
		try {
			
			List<StockInventoryDetailDto> detialList = stockInventoryDetailService
					.queryInventoryByMchcode(deptId, "100000");
			
			List<MchBaseinfo> tList = mchBaseinfoService.querMchInfAndCustomerinfoByDeptId(deptId);
			
			  String path="E:/imp/data";
			  File file=new File(path);
			  File[] tempList = file.listFiles();
			  System.out.println("该目录下对象个数："+tempList.length);
			  for (int i = 0; i < tempList.length; i++) {
			   if (tempList[i].isFile())
               {
				    String fileName=tempList[i].getName().replace(".xlsx", "");
				    //根据文件名执行不一样任务
					if(fileName.contains("暂存"))
					{
						String name=fileName.replace("暂存", "");
						System.out.println("----------正在读 文     件："+tempList[i]);
						resultDto=readExcelFile(tempList[i]+"");
						if(resultDto.isSuccess())
						{
							MchBaseinfo mc=getMchcode(tList, name);
							//addZcData(resultDto, deptId, mc,detialList);
							mchcode=mc.getMchcode()+"";
							updateData(resultDto, deptId, mchcode,detialList);
						}
						System.out.println("=========="+tempList[i]+"执行成功=====");
					}
					
			   }
			   if (tempList[i].isDirectory()) 
			   {
			       System.out.println("文件夹："+tempList[i]);
			   }
			  }
			
			
		} catch (Exception e) {
			logger.error("读取Excel文件异常");
			e.printStackTrace();
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(resultDto));
	}
	
	private  static String getCode(String form){
		String code="";
		
		code = new SimpleDateFormat("YYMMDDmmss").format(new Date())
				+ RandomUtil.get().randomDegital(3);

		return form+code;
	 }
	
	@RequestMapping(params = "method=batchInitLease")
	public ModelAndView batchInitLease(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<List<StockInventoryDetailDto>> resultDto=null;
		
		String deptId=String.valueOf(user.getCenterId());
		
		String htcode = "";
		try {
			
			List<CBusHt> tList=busHtService.loadAll();
			
			List<StockInventoryDetailDto> detialList = stockInventoryDetailService
					.queryInventoryByMchcode(deptId, "100000");
			
			  String path="E:/imp/data2/b2";
			  File file=new File(path);
			  File[] tempList = file.listFiles();
			  System.out.println("该目录下对象个数："+tempList.length);
			  for (int i = 0; i < tempList.length; i++) {
			   if (tempList[i].isFile())
               {
				    String fileName=tempList[i].getName().replace(".xlsx", "");
				   
						String name=fileName.replace("-长兴岛项目", "").replace("大华医院", "大华");
						       name=name.replace("南通兴江建建安", "南通兴江建").replace("工地", "");
						System.out.println("----------正在读 文     件："+tempList[i]);
						resultDto=readExcelLeaseFile(tempList[i]+"");
						if(resultDto.isSuccess())
						{
							
							htcode=getHtcode(tList, name);
						    resultDto.setParamObj(addLeaseData(resultDto, htcode, detialList,user));
							
							
						}
						System.out.println("=========="+tempList[i]+"执行成功=====");
					
			   }
			   if (tempList[i].isDirectory()) 
			   {
			       System.out.println("文件夹："+tempList[i]);
			   }
			  }
			
			
		} catch (Exception e) {
			logger.error("读取Excel文件异常");
			e.printStackTrace();
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(resultDto));
	}
	@RequestMapping(params = "method=batchInitPrice")
	public ModelAndView batchInitPrice(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<List<StockInventoryDetailDto>> resultDto=null;
		
		String deptId=String.valueOf(user.getCenterId());
		
		String htcode = "";
		try {
			
			List<CBusHt> tList=busHtService.loadAll();
			
			List<BMaterialType> materialTypes=materialTypeService.loadAll();
			
			  String path="E:/imp/data2/b1";
			  File file=new File(path);
			  File[] tempList = file.listFiles();
			  System.out.println("该目录下对象个数："+tempList.length);
			  for (int i = 0; i < tempList.length; i++) {
			   if (tempList[i].isFile())
               {
				    String fileName=tempList[i].getName().replace(".xlsx", "");
				   
						String name=fileName.replace("-长兴岛项目", "").replace("大华医院", "大华");
						       name=name.replace("南通兴江建建安", "南通兴江建");
						System.out.println("----------正在读 文     件："+tempList[i]);
						resultDto=readExcelPriceFile(tempList[i]+"");
						if(resultDto.isSuccess())
						{
							if("调拨价格".equals(name))
							{
								resultDto.setParamObj(addAllotData(resultDto, htcode, materialTypes,deptId));
							}else{
								htcode=getHtcode(tList, name);
								resultDto.setParamObj(addPriceData(resultDto, htcode, materialTypes));
							}
							
						}
						System.out.println("=========="+tempList[i]+"执行成功=====");
					
			   }
			   if (tempList[i].isDirectory()) 
			   {
			       System.out.println("文件夹："+tempList[i]);
			   }
			  }
			
			
		} catch (Exception e) {
			logger.error("读取Excel文件异常");
			e.printStackTrace();
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(resultDto));
	}
	private String getHtcode(List<CBusHt> tList,String name){
		String htcode="";
		for (CBusHt busHt : tList) {
			 if(busHt.getProjectName().contains(name)){
				 htcode=busHt.getHtcode();
				 break;
			 }
		}
		return htcode;
		
	}
	
	private MchBaseinfo getMchcode(List<MchBaseinfo> tList,String name){
		MchBaseinfo baseinfo=null;
		for (MchBaseinfo mchBaseinfo : tList) {
			 if(mchBaseinfo.getMchname().contains(name)){
				 baseinfo=mchBaseinfo;
				 break;
			 }
		}
		return baseinfo;
		
	}
	
	private List<StockInventoryDetailDto> addAllotData(
			ResultDto<List<StockInventoryDetailDto>> resultDto, String cusCode,
			List<BMaterialType> materialTypes,String deptId) {

		List<StockInventoryDetailDto> detailDtos=resultDto.getParamObj();
		
		List<StockInventoryDetailDto> dataList=new ArrayList<StockInventoryDetailDto>();
		
		for (BMaterialType mtype : materialTypes) 
		{
					for (StockInventoryDetailDto detailDto : detailDtos) 
					{
						String _name=mtype.getName();
						String name= detailDto.getName().replaceAll(" ", "");
						String realTotalS= detailDto.getRealTotalS();
						
						if(_name.equals(name))
						{
							    StockInventoryDetailDto newDetailDto=new StockInventoryDetailDto();
							    newDetailDto.setMaterialcode(mtype.getTypeid()+"");
							    newDetailDto.setName(detailDto.getName());
							    newDetailDto.setRealTotalS(realTotalS);
							    newDetailDto.setUnit(mtype.getAccountFlag());
								dataList.add(newDetailDto);
								break;
						}
				}
			
		}
		
		List<CustomerInfo> tList = customerInfoService.loadAll();
		for (CustomerInfo customerInfo : tList) {
			 if(customerInfo.getCustype().equals("1")){
				 
				 List<CustomerRentinfo> customerRentinfos=new ArrayList<CustomerRentinfo>();
				 for (StockInventoryDetailDto detailList : dataList) 
	             {
		            	 String _totalS=detailList.getRealTotalS();
		            	 if(!StringUtils.hasLength(_totalS))
		            	 {
		            		 _totalS="0";
		            	 }
		            	 CustomerRentinfo customerRentinfo=new CustomerRentinfo();
			        		customerRentinfo.setCuscode(customerInfo.getCuscode());
			        		customerRentinfo.setMaterialcode(detailList.getMaterialcode());
			        		Double quantiy=Double.valueOf(1);
			        		customerRentinfo.setQuantity(new BigDecimal(quantiy));
			        		customerRentinfo.setTonQantity(1);
			        		Double rentalDay=Double.valueOf(_totalS);
							customerRentinfo.setRentalDay(new BigDecimal(rentalDay));
							customerRentinfo.setUnit(detailList.getUnit());
							customerRentinfos.add(customerRentinfo);
				 }
	             customerRentinfoDao.batchCreate(customerRentinfos);
	             logger.debug("=========调拨客户租费数据保存成功=======");
			 }
            
		}
		return dataList;
	}
	
	private List<StockInventoryDetailDto> addPriceData(
			ResultDto<List<StockInventoryDetailDto>> resultDto, String htcode,
			List<BMaterialType> materialTypes) {

		List<StockInventoryDetailDto> detailDtos=resultDto.getParamObj();
		
		List<StockInventoryDetailDto> dataList=new ArrayList<StockInventoryDetailDto>();
		
		for (BMaterialType mtype : materialTypes) 
		{
					for (StockInventoryDetailDto detailDto : detailDtos) 
					{
						String _name=mtype.getName();
						String name= detailDto.getName().replaceAll(" ", "");
						String realTotalS= detailDto.getRealTotalS();
						if(_name.contains("工字钢"))
						{
							_name="工字钢";
						}
						if(_name.equals(name))
						{
							    StockInventoryDetailDto newDetailDto=new StockInventoryDetailDto();
							    newDetailDto.setMaterialcode(mtype.getTypeid()+"");
							    newDetailDto.setName(detailDto.getName());
							    newDetailDto.setRealTotalS(realTotalS);
							    newDetailDto.setUnit(detailDto.getUnit());
								dataList.add(newDetailDto);
								break;
						}
				}
			
		}
		List<CBusHtRentinfo> busHtRentinfos=new ArrayList<CBusHtRentinfo>();
//		if(busHtRentinfoDtos.size()>0)
//		{
             for (StockInventoryDetailDto detailList : dataList) 
             {
	            	 String _totalS=detailList.getRealTotalS();
	            	 if(!StringUtils.hasLength(_totalS))
	            	 {
	            		 _totalS="0";
	            	 }
            	     CBusHtRentinfo busHtRentinfo=new CBusHtRentinfo();
            	     busHtRentinfo.setHtcode(htcode);
            	     busHtRentinfo.setMaterialcode(detailList.getMaterialcode());		
						 Double quantiy=Double.valueOf(1);
						 busHtRentinfo.setQuantity(new BigDecimal(quantiy));
						 busHtRentinfo.setTonQantity(1);
						 Double rentalDay=Double.valueOf(_totalS);
						 busHtRentinfo.setRentalDay(new BigDecimal(rentalDay));
						 busHtRentinfo.setUnit(detailList.getUnit());
						 busHtRentinfos.add(busHtRentinfo);
			 }
             busHtRentinfoService.batchAdd(busHtRentinfos);
		     logger.debug("=========合同从表租费数据保存成功=======");
//		}
		return dataList;
	}
	
	private List<StockInventoryDetailDto> addLeaseData(
			ResultDto<List<StockInventoryDetailDto>> resultDto, String htcode,
			List<StockInventoryDetailDto> materialTypes,SessionUser user) {

		List<StockInventoryDetailDto> detailDtos=resultDto.getParamObj();
		
		List<StockInventoryDetailDto> dataList=new ArrayList<StockInventoryDetailDto>();
		
		List<StockInventoryDetailDto> dataList1=new ArrayList<StockInventoryDetailDto>();
		List<StockInventoryDetailDto> dataList2=new ArrayList<StockInventoryDetailDto>();
	
		
		for (StockInventoryDetailDto mtype : materialTypes) 
		{
					for (StockInventoryDetailDto detailDto : detailDtos) 
					{
						String _name=mtype.getName();
						String name= detailDto.getName().replaceAll(" ", "");
						String diffrate= detailDto.getDiffrate();
						
						if(_name.equals(name))
						{
								if(diffrate.contains("-"))
							    {
									detailDto.setMaterialcode(mtype.getMaterialcode());
									detailDto.setDiffrate(diffrate.replace("-", ""));
									detailDto.setUnit(mtype.getUnit());
									detailDto.setAccountFlag(mtype.getAccountFlag());
									detailDto.setCovertRatio(mtype.getCovertRatio());
									dataList1.add(detailDto);
									dataList.add(detailDto);
									break;
							    } else
							    {
							    	detailDto.setMaterialcode(mtype.getMaterialcode());
							    	detailDto.setUnit(mtype.getUnit());
							    	detailDto.setAccountFlag(mtype.getAccountFlag());
									detailDto.setCovertRatio(mtype.getCovertRatio());
							    	dataList2.add(detailDto);
									dataList.add(detailDto);
									break;
							    }
								
						}
				}
			
		}
		
		String _totalM="0";
		
		if(dataList1!=null && dataList1.size()>0)
		{
			StockReceiptDto stockReceiptDto=new StockReceiptDto();
			String _code=getCode("SL");
		    stockReceiptDto.setHtcode(htcode);
		    stockReceiptDto.setReceiptSerialno(_code);
		    stockReceiptDto.setDeptid(user.getCenterId());
		    stockReceiptDto.setMchcode("100000");
		    stockReceiptDto.setCarNo("");
		    stockReceiptDto.setCarDriver("");
		    stockReceiptDto.setLessee("center");
		    stockReceiptDto.setRenter("center");
		    stockReceiptDto.setZxFee("0");
		    stockReceiptDto.setTransportFee("0");
		    stockReceiptDto.setOtherFee("0");
		    stockReceiptDto.setRemark("初期导入");
		    stockReceiptDto.setCreatetime(impData);
		    stockReceiptDto.setStatus("1");
		    
		    List<StockReceiptDetailDto> stockReceiptDetailDtos=new ArrayList<StockReceiptDetailDto>();
			//
			for (StockInventoryDetailDto stockInventoryDetailDto : dataList1) {
				 //库存汇总更新
				 StockReceiptDetailDto stockReceiptDetailDto=new StockReceiptDetailDto();
				 stockReceiptDetailDto.setReceiptSerialno(_code);
				 String _totalS=stockInventoryDetailDto.getDiffrate();
				 String _unit=stockInventoryDetailDto.getUnit();
				 if(!StringUtils.hasLength(_totalS))
	        	 {
	        		 _totalS="0";
	        	 }
				 Double _toatl = Double.parseDouble(_totalS)
		                   * Double.parseDouble(stockInventoryDetailDto.getCovertRatio());
				 _totalM=String.format("%.2f", _toatl);
				 stockReceiptDetailDto.setTotalS(_totalS);
				 stockReceiptDetailDto.setTotalM(_totalM);
				 stockReceiptDetailDto.setTotalT("0");
				 stockReceiptDetailDto.setUnit(_unit);
				 stockReceiptDetailDto.setMaterialcode(stockInventoryDetailDto.getMaterialcode());
				 stockReceiptDetailDtos.add(stockReceiptDetailDto);	 
			}
			stockReceiptDto.setStockReceiptDetailDtos(stockReceiptDetailDtos);
			stockReceiptService.saveStockReceiptApplyRdTx(stockReceiptDto);
			
			AuditDto auditDto=new AuditDto();
			auditDto.setId(_code);
			auditDto.setStatus("0");
			auditDto.setDeptId(user.getCenterId()+"");
			stockReceiptService.auditInfoRdTx(auditDto);
		}
		
        if(dataList2!=null && dataList2.size()>0)
        {
        	CStockSendDto cStockSendDto=new CStockSendDto();
    		String code=getCode("FL");
    		cStockSendDto.setSendSerialno(code);
    		cStockSendDto.setDeptid(user.getCenterId());
    		cStockSendDto.setHtcode(htcode);
    		cStockSendDto.setCarNo("");
    		cStockSendDto.setCarDriver("");
    		cStockSendDto.setLessee("center");
    		cStockSendDto.setRenter("center");
    		cStockSendDto.setZxFee("0");
    		cStockSendDto.setTransportFee("0");
    		cStockSendDto.setOtherFee("0");
    		cStockSendDto.setStatus("1");
    		cStockSendDto.setTradeinfo("");
    		cStockSendDto.setCreateoper(user.getUserId()+"");
    		cStockSendDto.setSendtime(impData);
    		cStockSendDto.setRemark("初期导入");
    		cStockSendDto.setCreatetime(impData);
    		cStockSendDto.setStatus("1");
    		
    		List<CStockSendDetailDto> cStockSendDetailDtos=new ArrayList<CStockSendDetailDto>();
    		//
    		for (StockInventoryDetailDto stockInventoryDetailDto : dataList2) {
    			 //库存汇总更新
    			 CStockSendDetailDto cStockSendDetailDto=new CStockSendDetailDto();
    			 cStockSendDetailDto.setSendSerialno(code);
    			 String _totalS=stockInventoryDetailDto.getDiffrate();
    			 String _unit=stockInventoryDetailDto.getUnit();
    			 if(!StringUtils.hasLength(_totalS))
            	 {
            		 _totalS="0";
            	 }
    			 Double _toatl = Double.parseDouble(_totalS)
		                   * Double.parseDouble(stockInventoryDetailDto.getCovertRatio());
				 _totalM=String.format("%.2f", _toatl);
    			 cStockSendDetailDto.setTotalS(_totalS);
    			 cStockSendDetailDto.setTotalM(_totalM);
    			 cStockSendDetailDto.setTotalT("0");
    			 cStockSendDetailDto.setUnit(_unit);
    			 cStockSendDetailDto.setMaterialcode(stockInventoryDetailDto.getMaterialcode());
    			 cStockSendDetailDtos.add(cStockSendDetailDto);	 
    		}
    		cStockSendDto.setcStockSendDetailDto(cStockSendDetailDtos);
    		sendService.saveCStockSendRdTx(cStockSendDto, user);
    		cStockSendDto.setStatus("0");
    		sendService.auditCStockSendByIdRdTx(cStockSendDto);
		}
		
		
//		CStockSendDto cStockSendDto=new CStockSendDto();
//		String code=getCode("FL");
//		cStockSendDto.setSendSerialno(code);
//		cStockSendDto.setDeptid(user.getCenterId());
//		cStockSendDto.setHtcode(htcode);
//		cStockSendDto.setCarNo("");
//		cStockSendDto.setCarDriver("");
//		cStockSendDto.setLessee("center");
//		cStockSendDto.setRenter("center");
//		cStockSendDto.setZxFee("0");
//		cStockSendDto.setTransportFee("0");
//		cStockSendDto.setOtherFee("0");
//		cStockSendDto.setStatus("1");
//		cStockSendDto.setTradeinfo("");
//		cStockSendDto.setCreateoper(user.getUserId()+"");
//		cStockSendDto.setRemark("初期导入");
//		cStockSendDto.setCreatetime(impData);
//		
//		List<CStockSendDetailDto> cStockSendDetailDtos=new ArrayList<CStockSendDetailDto>();
//		//
//		for (StockInventoryDetailDto stockInventoryDetailDto : dataList) {
//			 //库存汇总更新
//			 CStockSendDetailDto cStockSendDetailDto=new CStockSendDetailDto();
//			 cStockSendDetailDto.setSendSerialno(code);
//			 String _totalS=stockInventoryDetailDto.getRealTotalS();
//			 String _unit=stockInventoryDetailDto.getUnit();
//			 if(!StringUtils.hasLength(_totalS))
//        	 {
//        		 _totalS="0";
//        	 }
//			 cStockSendDetailDto.setTotalS(_totalS);
//			 cStockSendDetailDto.setTotalM("0");
//			 cStockSendDetailDto.setTotalT("0");
//			 cStockSendDetailDto.setUnit(_unit);
//			 cStockSendDetailDto.setMaterialcode(stockInventoryDetailDto.getMaterialcode());
//			 cStockSendDetailDtos.add(cStockSendDetailDto);	 
//		}
//		cStockSendDto.setcStockSendDetailDto(cStockSendDetailDtos);
//		sendService.saveCStockSendRdTx(cStockSendDto, user);
//		cStockSendDto.setStatus("0");
//		sendService.auditCStockSendByIdRdTx(cStockSendDto);
//		
//		
//		StockReceiptDto stockReceiptDto=new StockReceiptDto();
//		String _code=getCode("SL");
//	    stockReceiptDto.setHtcode(htcode);
//	    stockReceiptDto.setReceiptSerialno(_code);
//	    stockReceiptDto.setDeptid(user.getCenterId());
//	    stockReceiptDto.setMchcode("100000");
//	    stockReceiptDto.setCarNo("");
//	    stockReceiptDto.setCarDriver("");
//	    stockReceiptDto.setLessee("center");
//	    stockReceiptDto.setRenter("center");
//	    stockReceiptDto.setZxFee("0");
//	    stockReceiptDto.setTransportFee("0");
//	    stockReceiptDto.setOtherFee("0");
//	    stockReceiptDto.setRemark("初期导入");
//	    stockReceiptDto.setCreatetime(impData);
//	    
//	    List<StockReceiptDetailDto> stockReceiptDetailDtos=new ArrayList<StockReceiptDetailDto>();
//		//
//		for (StockInventoryDetailDto stockInventoryDetailDto : dataList) {
//			 //库存汇总更新
//			 StockReceiptDetailDto stockReceiptDetailDto=new StockReceiptDetailDto();
//			 stockReceiptDetailDto.setReceiptSerialno(_code);
//			 String _totalS=stockInventoryDetailDto.getAccTotalS();
//			 String _unit=stockInventoryDetailDto.getUnit();
//			 if(!StringUtils.hasLength(_totalS))
//        	 {
//        		 _totalS="0";
//        	 }
//			 stockReceiptDetailDto.setTotalS(_totalS);
//			 stockReceiptDetailDto.setTotalM("0");
//			 stockReceiptDetailDto.setTotalT("0");
//			 stockReceiptDetailDto.setUnit(_unit);
//			 stockReceiptDetailDto.setMaterialcode(stockInventoryDetailDto.getMaterialcode());
//			 stockReceiptDetailDtos.add(stockReceiptDetailDto);	 
//		}
//		stockReceiptDto.setStockReceiptDetailDtos(stockReceiptDetailDtos);
//		stockReceiptService.saveStockReceiptApplyRdTx(stockReceiptDto);
//		
//		AuditDto auditDto=new AuditDto();
//		auditDto.setId(_code);
//		auditDto.setStatus("0");
//		auditDto.setDeptId(user.getCenterId()+"");
//		stockReceiptService.auditInfoRdTx(auditDto);
	
		return dataList;
	}
	
	
	private  List<StockInventoryDetailDto> addZcData(ResultDto<List<StockInventoryDetailDto>> resultDto,
			String deptId, MchBaseinfo mc,
			List<StockInventoryDetailDto> detialList) {

		List<StockInventoryDetailDto> detailDtos=resultDto.getParamObj();
		
		List<StockInventoryDetailDto> dataList=new ArrayList<StockInventoryDetailDto>();
		List<StockInventoryDetailDto> dataList2=new ArrayList<StockInventoryDetailDto>();
		
		for (StockInventoryDetailDto stockInventoryDetailDto : detialList) 
		{
				
				String meterialname=stockInventoryDetailDto.getName();
				
				for (StockInventoryDetailDto detailDto : detailDtos) 
				{
						String name= detailDto.getName().replaceAll(" ", "");
						String realTotalS= detailDto.getRealTotalS();
						String accTotalS= detailDto.getAccTotalS();
						if(meterialname.equals(name))
						{
							    if(realTotalS.contains("-"))
							    {
							    	stockInventoryDetailDto.setRealTotalS(realTotalS);
									stockInventoryDetailDto.setAccTotalS(accTotalS);
									dataList.add(stockInventoryDetailDto);
									break;
							    } else
							    {
							    	stockInventoryDetailDto.setRealTotalS(realTotalS);
									stockInventoryDetailDto.setAccTotalS(accTotalS);
									dataList2.add(stockInventoryDetailDto);
									break;
							    }
								
						}
				}
			
		}
		String mchcode=mc.getMchcode()+"";
		String type=mc.getType();
		String _totalM="0";
		if(dataList!=null && dataList.size()>0)
		{
			String code="";
			if("-1".equals(type)){
				 code=getCode("RK");
			}else{
				 code=getCode("ZC");
			}
			//入库信息对象
		    CStockIn cStockIn=new CStockIn();
		    cStockIn.setInSerialno(code);
		    cStockIn.setDeptid(Integer.parseInt(deptId));
		    cStockIn.setMchcode(mchcode);
		    
		    cStockIn.setZxFee(new BigDecimal(0.0));
		    cStockIn.setOtherFee(new BigDecimal(0.0));
		    cStockIn.setTransportFee(new BigDecimal(0.0));
		    
		    cStockIn.setOutOperator("center");
		    cStockIn.setInOperator("center");
		    
		    List<StockInDetailDto> cStockInDetails=new ArrayList<StockInDetailDto>();
			//
			for (StockInventoryDetailDto stockInventoryDetailDto : dataList) {
				 //库存汇总更新
				 StockInDetailDto stockInDetailDto=new StockInDetailDto();
				 stockInDetailDto.setInSerialno(code);
				 String _totalS=stockInventoryDetailDto.getRealTotalS();
				 if(!StringUtils.hasLength(_totalS))
	        	 {
	        		 _totalS="0";
	        	 }
				 Double _toatl = Double.parseDouble(_totalS)
		                   * Double.parseDouble(stockInventoryDetailDto.getCovertRatio());
				 _totalM=String.format("%.2f", _toatl);
				 stockInDetailDto.setTotalS(new BigDecimal(_totalS));
				 stockInDetailDto.setTotalM(_totalM);
				 stockInDetailDto.setMaterialCode(stockInventoryDetailDto.getMaterialcode());
				 cStockInDetails.add(stockInDetailDto);	 
			}
		    //入库状态为未审核
		    cStockIn.setStatus("1");
		    List<CStockInRepairinfoDto> repairs=null;
			stockInService.saveStockInRdTx(cStockIn, cStockInDetails,repairs);
			logger.debug("=============暂存单成功=======");
		}
		if(dataList2!=null && dataList2.size()>0)
		{
			String code=getCode("TZC");
			StockOutDto stockOutDto=new StockOutDto(); 
			stockOutDto.setOutSerialno(code);
			stockOutDto.setDeptid(Integer.parseInt(deptId));
			stockOutDto.setMchcode(mchcode);
		    
			stockOutDto.setZxFee("0");
			stockOutDto.setOtherFee("0");
			stockOutDto.setTransportFee("0");
		    
			stockOutDto.setOutOperator("center");
			stockOutDto.setInOperator("center");
		    
		    List<StockOutDetailDto> cStockOutDetailDtos=new ArrayList<StockOutDetailDto>();
			//
			for (StockInventoryDetailDto stockInventoryDetailDto : dataList2) {
				 //库存汇总更新
				 StockOutDetailDto stockOutDetailDto=new StockOutDetailDto();
				 stockOutDetailDto.setOutSerialno(code);
				 String _totalS=stockInventoryDetailDto.getRealTotalS();
				 if(!StringUtils.hasLength(_totalS))
	        	 {
	        		 _totalS="0";
	        	 }
				 Double _toatl = Double.parseDouble(_totalS)
		                   * Double.parseDouble(stockInventoryDetailDto.getCovertRatio());
				 _totalM=String.format("%.2f", _toatl);
				 stockOutDetailDto.setTotalS(_totalS);
				 stockOutDetailDto.setTotalM(_totalM);
				 stockOutDetailDto.setMaterialcode(stockInventoryDetailDto.getMaterialcode());
				 cStockOutDetailDtos.add(stockOutDetailDto);	 
				
			}
		    //入库状态为未审核
			stockOutDto.setStatus("1");
			stockOutDto.setStockOutDetailDtos(cStockOutDetailDtos);
			stockOutService.saveStockOutApplyRdTx(stockOutDto);
			logger.debug("=============提暂存单成功=======");
		}
		
		return dataList;
	}
	
	
	private  List<StockInventoryDetailDto> addDbData(ResultDto<List<StockInventoryDetailDto>> resultDto,
			String deptId, MchBaseinfo mc,
			List<StockInventoryDetailDto> detialList,SessionUser user) {

		List<StockInventoryDetailDto> detailDtos=resultDto.getParamObj();
		
		List<StockInventoryDetailDto> dataList=new ArrayList<StockInventoryDetailDto>();
		List<StockInventoryDetailDto> dataList2=new ArrayList<StockInventoryDetailDto>();
		
		for (StockInventoryDetailDto stockInventoryDetailDto : detialList) 
		{
				
				String meterialname=stockInventoryDetailDto.getName();
				
				for (StockInventoryDetailDto detailDto : detailDtos) 
				{
						String name= detailDto.getName().replaceAll(" ", "");
						String realTotalS= detailDto.getRealTotalS();
						String accTotalS= detailDto.getAccTotalS();
						if(meterialname.equals(name))
						{
							    if(realTotalS.contains("-"))
							    {
							    	stockInventoryDetailDto.setRealTotalS(realTotalS.replace("-", ""));
									stockInventoryDetailDto.setAccTotalS(accTotalS);
									dataList.add(stockInventoryDetailDto);
									break;
							    } else
							    {
							    	stockInventoryDetailDto.setRealTotalS(realTotalS);
									stockInventoryDetailDto.setAccTotalS(accTotalS);
									dataList2.add(stockInventoryDetailDto);
									break;
							    }
								
						}
				}
			
		}
		String mchcode=mc.getMchcode()+"";
		String type=mc.getType();
		String _totalM="0";
		Date dateImp=TimeUtil.get().parseTime(impData);
		if(dataList2!=null && dataList2.size()>0)
		{
			
				 String code="";
					if("-1".equals(type)){
						    code=getCode("XHDB");
						    CStockTransfer cStockTransfer = new CStockTransfer();
						    cStockTransfer.setFromMchcode(mchcode);
						    cStockTransfer.setToMchcode(SystemConst.ZX_MCHCODE);
				            cStockTransfer.setTsfEdate(dateImp);
				            cStockTransfer.setTsfSdate(dateImp);
				            cStockTransfer.setFromDeptid(user.getCenterId());
				            cStockTransfer.setToDeptid(user.getCenterId());
				            cStockTransfer.setStatus("1");
				            cStockTransfer.setZxFee(new BigDecimal(0));
				            cStockTransfer.setOtherFee(new BigDecimal(0));
				            cStockTransfer.setTransportFee(new BigDecimal(0));
				            cStockTransfer.setTsfSerialno(code);
				            cStockTransfer.setCreatetime(dateImp);
				            cStockTransfer.setCreateoper(user.getUserId()+"");
							
						    List<CStockTransferDetail> cStockTransferDetails=new ArrayList<CStockTransferDetail>();
							//
							for (StockInventoryDetailDto stockInventoryDetailDto : dataList2) {
								 
		                       	 String _totalS=stockInventoryDetailDto.getRealTotalS();
		 						 if(!StringUtils.hasLength(_totalS))
		 			        	 {
		 			        		 _totalS="0";
		 			        	 }
								 Double _toatl = Double.parseDouble(_totalS)
						                   * Double.parseDouble(stockInventoryDetailDto.getCovertRatio());
								 _totalM=String.format("%.2f", _toatl);
		 						 CStockTransferDetail cStockTransferDetail = new CStockTransferDetail();
		 		                 cStockTransferDetail.setTsfSerialno(code);
		 		                 cStockTransferDetail.setMaterialCode(stockInventoryDetailDto.getMaterialcode());
		 		                 cStockTransferDetail.setTotalS(new BigDecimal(_totalS));
		 		                 cStockTransferDetail.setTotalT(new BigDecimal(0));
		 		                 cStockTransferDetail.setTotalM(new BigDecimal(_totalM));
		 		                 cStockTransferDetail.setUnit(stockInventoryDetailDto.getUnit());
		 		                 cStockTransferDetail.setStatus("0");
		 		                 cStockTransferDetails.add(cStockTransferDetail);
							}
						 stockTransferService.saveStockTransferRdTx(cStockTransfer, cStockTransferDetails);
						
						 cStockTransfer.setStatus("0");
						 //stockTransferService.auditStockTemporaryByIdRdTx(cStockTransfer);
					}else{
						 code=getCode("DC");
				    //调出信息对象
				    CStockCenterTransferDto cStockCenterTransferDto=new CStockCenterTransferDto();
					cStockCenterTransferDto.setTsfSerialno(code);
					cStockCenterTransferDto.setFromDeptid(deptId);
					cStockCenterTransferDto.setFromMchcode("100000");
					cStockCenterTransferDto.setToMchcode(mchcode);
					cStockCenterTransferDto.setZxFee("0");
					cStockCenterTransferDto.setOtherFee("0");
					cStockCenterTransferDto.setTransportFee("0");
					cStockCenterTransferDto.setTraOutOper("center");
					cStockCenterTransferDto.setTraInOper("center");
					cStockCenterTransferDto.setTradetype("5");
					cStockCenterTransferDto.setTsfSdate(impData);
					cStockCenterTransferDto.setTsfEdate(impData);
				    
				    List<CStockCenterTransferDetailDto> cStockDetails=new ArrayList<CStockCenterTransferDetailDto>();
					//
					for (StockInventoryDetailDto stockInventoryDetailDto : dataList2) {
						 //库存汇总更新
						 CStockCenterTransferDetailDto centerTransferDetailDto=new CStockCenterTransferDetailDto();
						 centerTransferDetailDto.setTsfSerialno(code);
						 String _totalS=stockInventoryDetailDto.getRealTotalS();
						 if(!StringUtils.hasLength(_totalS))
			        	 {
			        		 _totalS="0";
			        	 }
						 Double _toatl = Double.parseDouble(_totalS)
				                   * Double.parseDouble(stockInventoryDetailDto.getCovertRatio());
						 _totalM=String.format("%.2f", _toatl);
						 centerTransferDetailDto.setTotalS(_totalS);
						 centerTransferDetailDto.setTotalM(_totalM);
						 centerTransferDetailDto.setMaterialcode(stockInventoryDetailDto.getMaterialcode());
						 cStockDetails.add(centerTransferDetailDto);	 
					}
					cStockCenterTransferDto.setcStockCenterTransferDetaildtos(cStockDetails);
					cStockCenterTransferDto.setStatus("1");
				    stockCenterTransferService.saveCStockStockCenterTransferRdTx(cStockCenterTransferDto, user);
				    cStockCenterTransferDto.setStatus("0");
				    cStockCenterTransferDto.setFromDeptid(deptId);
				    cStockCenterTransferDto.setToDepid(deptId);
				    stockCenterTransferService.auditStockCenterTransferByIdRdTx(cStockCenterTransferDto);
					logger.debug("=============调出单成功=======");
			}
			
		}
		if(dataList!=null && dataList.size()>0)
		{
					String code="";
					if("-1".equals(type)){
						code=getCode("XHDB");
					    CStockTransfer cStockTransfer = new CStockTransfer();
			            cStockTransfer.setFromMchcode(SystemConst.ZX_MCHCODE);
					    cStockTransfer.setToMchcode(mchcode);
			            cStockTransfer.setTsfEdate(dateImp);
			            cStockTransfer.setTsfSdate(dateImp);
			            cStockTransfer.setFromDeptid(user.getCenterId());
			            cStockTransfer.setToDeptid(user.getCenterId());
			            cStockTransfer.setStatus("1");
			            cStockTransfer.setZxFee(new BigDecimal(0));
			            cStockTransfer.setOtherFee(new BigDecimal(0));
			            cStockTransfer.setTransportFee(new BigDecimal(0));
			            cStockTransfer.setTsfSerialno(code);
			            cStockTransfer.setCreatetime(dateImp);
			            cStockTransfer.setCreateoper(user.getUserId()+"");
						
					    List<CStockTransferDetail> cStockTransferDetails=new ArrayList<CStockTransferDetail>();
						//
						for (StockInventoryDetailDto stockInventoryDetailDto : dataList) {
							 
		                   	 String _totalS=stockInventoryDetailDto.getRealTotalS();
								 if(!StringUtils.hasLength(_totalS))
					        	 {
					        		 _totalS="0";
					        	 }
								 Double _toatl = Double.parseDouble(_totalS)
						                   * Double.parseDouble(stockInventoryDetailDto.getCovertRatio());
								 _totalM=String.format("%.2f", _toatl);
								 CStockTransferDetail cStockTransferDetail = new CStockTransferDetail();
				                 cStockTransferDetail.setTsfSerialno(code);
				                 cStockTransferDetail.setMaterialCode(stockInventoryDetailDto.getMaterialcode());
				                 cStockTransferDetail.setTotalS(new BigDecimal(_totalS));
				                 cStockTransferDetail.setTotalT(new BigDecimal(0));
				                 cStockTransferDetail.setTotalM(new BigDecimal(_totalM));
				                 cStockTransferDetail.setUnit(stockInventoryDetailDto.getUnit());
				                 cStockTransferDetail.setStatus("0");
				                 cStockTransferDetails.add(cStockTransferDetail);
						}
					 stockTransferService.saveStockTransferRdTx(cStockTransfer, cStockTransferDetails);
					
					 cStockTransfer.setStatus("0");
					 //stockTransferService.auditStockTemporaryByIdRdTx(cStockTransfer);
				 
			}else{
				 code=getCode("DR");
				    //调出信息对象
					CStockCenterTransferDto cStockCenterTransferDto=new CStockCenterTransferDto();
					cStockCenterTransferDto.setTsfSerialno(code);
					cStockCenterTransferDto.setFromDeptid(deptId);
					cStockCenterTransferDto.setFromMchcode(mchcode);
					cStockCenterTransferDto.setToMchcode("100000");
					cStockCenterTransferDto.setZxFee("0");
					cStockCenterTransferDto.setOtherFee("0");
					cStockCenterTransferDto.setTransportFee("0");
					cStockCenterTransferDto.setTraOutOper("center");
					cStockCenterTransferDto.setTraInOper("center");
					cStockCenterTransferDto.setTradetype("6");
					cStockCenterTransferDto.setTsfSdate(impData);
					cStockCenterTransferDto.setTsfEdate(impData);
				    
				    List<CStockCenterTransferDetailDto> cStockInDetails=new ArrayList<CStockCenterTransferDetailDto>();
					//
					for (StockInventoryDetailDto stockInventoryDetailDto : dataList) {
						 //库存汇总更新
						 CStockCenterTransferDetailDto centerTransferDetailDto=new CStockCenterTransferDetailDto();
						 centerTransferDetailDto.setTsfSerialno(code);
						 String _totalS=stockInventoryDetailDto.getRealTotalS();
						 if(!StringUtils.hasLength(_totalS))
			        	 {
			        		 _totalS="0";
			        	 }
						 Double _toatl = Double.parseDouble(_totalS)
				                   * Double.parseDouble(stockInventoryDetailDto.getCovertRatio());
						 _totalM=String.format("%.2f", _toatl);
						 centerTransferDetailDto.setTotalS(_totalS);
						 centerTransferDetailDto.setTotalM(_totalM);
						 centerTransferDetailDto.setUnit(stockInventoryDetailDto.getUnit());
						 centerTransferDetailDto.setMaterialcode(stockInventoryDetailDto.getMaterialcode());
						 cStockInDetails.add(centerTransferDetailDto);	 
					}
				    //入库状态为审核
					cStockCenterTransferDto.setStatus("1");
					cStockCenterTransferDto.setcStockCenterTransferDetaildtos(cStockInDetails);
				    stockCenterTransferInService.saveCStockStockCenterTransferInRdTx(cStockCenterTransferDto, user);
					logger.debug("=============调入单成功=======");
					cStockCenterTransferDto.setStatus("0");
					stockCenterTransferInService.auditStockCenterTransferInByIdRdTx(cStockCenterTransferDto);
			}
			
		}
		
		return dataList;
	}
	
	private  List<StockInventoryDetailDto> updateData(ResultDto<List<StockInventoryDetailDto>> resultDto,
			String deptId, String mchcode,
			List<StockInventoryDetailDto> detialList) {

		List<StockInventoryDetailDto> detailDtos=resultDto.getParamObj();
		
		List<StockInventoryDetailDto> dataList=new ArrayList<StockInventoryDetailDto>();
		
		for (StockInventoryDetailDto stockInventoryDetailDto : detialList) 
		{
				
				String meterialname=stockInventoryDetailDto.getName();
				
				for (StockInventoryDetailDto detailDto : detailDtos) 
				{
						String name= detailDto.getName().replaceAll(" ", "");
						String realTotalS= detailDto.getRealTotalS();
						String accTotalS= detailDto.getAccTotalS();
						if(meterialname.equals(name))
						{
								stockInventoryDetailDto.setRealTotalS(realTotalS);
								stockInventoryDetailDto.setAccTotalS(accTotalS);
								dataList.add(stockInventoryDetailDto);
								break;
						}
				}
			
		}
		List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
		
		//
		Date dateImp=TimeUtil.get().parseTime(impData);
		for (StockInventoryDetailDto stockInventoryDetailDto : dataList) {
			 //库存汇总更新
        	 CStockInfo cStockInfo=new CStockInfo();
        	 cStockInfo.setDeptid(Integer.parseInt(deptId));
        	 cStockInfo.setMchcode(mchcode);
        	 cStockInfo.setMaterialcode(stockInventoryDetailDto.getMaterialcode());
        	 String _totalS=stockInventoryDetailDto.getRealTotalS();
        	 if(!StringUtils.hasLength(_totalS))
        	 {
        		 _totalS="0";
        	 }
        	 cStockInfo.setTotalS(new BigDecimal(_totalS));
        	 cStockInfo.setTotalM(new BigDecimal(0));
        	 cStockInfo.setTotalT(new BigDecimal(0));
        	 cStockInfo.setModifytime(dateImp);
        	 cStockInfo.setChangeType(-1);  //导入的数据是的为负的
        	 cStockInfo.setIsHave("1");
        	 cStockInfo.setTransferTotalS(new BigDecimal(0));
        	 cStockInfos.add(cStockInfo);	 
		}
		stockInfoService.updateStockInfoRdTx(cStockInfos);
		logger.debug("=============库存更新了成功=======");
		return dataList;
	}
	
    protected static ResultDto<List<StockInventoryDetailDto>> readExcelFile(InputStream is){
		
		ResultDto<List<StockInventoryDetailDto>> resultDto=new ResultDto<List<StockInventoryDetailDto>>();
		List<StockInventoryDetailDto> detailDtos=new ArrayList<StockInventoryDetailDto>();
		// 根据输入流创建Workbook对象
		Workbook wb;
		try {
				wb = WorkbookFactory.create(is);
				// get到Sheet对象 
				Sheet sheet = wb.getSheetAt(0);
				int bh=0;
				for (int rowNum = 3; rowNum <= sheet.getLastRowNum(); rowNum++) {
					Row row = sheet.getRow(rowNum);
					if (row == null) {
						continue;
					}
					bh=rowNum+1;
					StockInventoryDetailDto stockInventoryDetailDto=new StockInventoryDetailDto();
					
					Cell name = row.getCell(0);
					if (name == null) {
						 continue;
					}
					String _name=getValue(name).replace("（只）", "").replace("顶托", "上托");
					//处理特殊的工字钢类型   6.0米18#工字钢    6米工字钢
					_name=_name.replaceAll(" ", "");
				    if(_name.equals("6米工字钢"))
				    {
				    	   _name=_name.replace("米工字钢", "米18#工字钢").replace("6", "6.0");
				    }else
				    {
				    	   _name=_name.replace("米工字钢", "米18#工字钢");
				    }
					stockInventoryDetailDto.setName(_name);
					
					Cell num = row.getCell(2);
					if(num==null){
						 continue;
					}
					String _num=getValue(num);
					if(ValidUtil.get().isNumber(_num) || "".equals(_num))
					{
						stockInventoryDetailDto.setRealTotalS(_num);
					}else{
						resultDto.setErrorMsg("第"+bh+"行，期末总数只能为数字");
						resultDto.setSuccess(false);
						return resultDto;
					}
					
					Cell lastNum = row.getCell(3);
					if(lastNum==null){
						 continue;
					}
					String _lastNum=getValue(lastNum);
					if(ValidUtil.get().isNumber(_lastNum) || "".equals(_lastNum))
					{
						stockInventoryDetailDto.setAccTotalS(_lastNum);
					}else{
						resultDto.setErrorMsg("第"+bh+"行，期末库存只能为数字");
						resultDto.setSuccess(false);
						return resultDto;
					}
					
					detailDtos.add(stockInventoryDetailDto);
				}
				//全部验证通过就设置为true;
				resultDto.setSuccess(true);
				resultDto.setParamObj(detailDtos);
		} catch (Exception e) {
			    e.printStackTrace();
			    resultDto.setErrorMsg("读取Excel文件失败");
				resultDto.setSuccess(false);
		} finally{
			try 
			{
				if(is!=null)
				{
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultDto;
	}
    protected static ResultDto<List<StockInventoryDetailDto>> readExcelPriceFile(String path){
		
		ResultDto<List<StockInventoryDetailDto>> resultDto=new ResultDto<List<StockInventoryDetailDto>>();
		List<StockInventoryDetailDto> detailDtos=new ArrayList<StockInventoryDetailDto>();
		// 根据输入流创建Workbook对象
		Workbook wb;
		try {
				wb = WorkbookFactory.create(new File(path));
				// get到Sheet对象 
				Sheet sheet = wb.getSheetAt(0);
				
				for (int rowNum = 3; rowNum <= sheet.getLastRowNum(); rowNum++) {
					Row row = sheet.getRow(rowNum);
					if (row == null) {
						continue;
					}
					
					StockInventoryDetailDto stockInventoryDetailDto=new StockInventoryDetailDto();
					
					Cell name = row.getCell(2);
					if (name == null) {
						 continue;
					}
					String _name=getValue(name);
					if(_name.contains("套管"))
					{
						_name="套管(只)";
					}
					if(_name.contains("短管"))
					{
						_name="短管(只)";
					}
					stockInventoryDetailDto.setName(_name);
					
					Cell unit = row.getCell(3);
					if(unit==null){
						 continue;
					}
					String _unit=getValue(unit);
					stockInventoryDetailDto.setUnit(_unit);
					
					Cell num = row.getCell(4);
					if(num==null){
						 continue;
					}
					String _num=getValue(num);
					if(ValidUtil.get().isNumber(_num) || "".equals(_num))
					{
						stockInventoryDetailDto.setRealTotalS(_num);
					}
					detailDtos.add(stockInventoryDetailDto);
				}
				//全部验证通过就设置为true;
				resultDto.setSuccess(true);
				resultDto.setParamObj(detailDtos);
		} catch (Exception e) {
			    e.printStackTrace();
			    resultDto.setErrorMsg("读取Excel文件失败");
				resultDto.setSuccess(false);
		} 
		return resultDto;
	}
    protected static ResultDto<List<StockInventoryDetailDto>> readExcelFile(String path){
		
		ResultDto<List<StockInventoryDetailDto>> resultDto=new ResultDto<List<StockInventoryDetailDto>>();
		List<StockInventoryDetailDto> detailDtos=new ArrayList<StockInventoryDetailDto>();
		// 根据输入流创建Workbook对象
		Workbook wb;
		try {
				wb = WorkbookFactory.create(new File(path));
				// get到Sheet对象 
				Sheet sheet = wb.getSheetAt(0);
				int bh=0;
				for (int rowNum = 3; rowNum <= sheet.getLastRowNum(); rowNum++) {
					Row row = sheet.getRow(rowNum);
					if (row == null) {
						continue;
					}
					bh=rowNum+1;
					StockInventoryDetailDto stockInventoryDetailDto=new StockInventoryDetailDto();
					
					Cell name = row.getCell(0);
					if (name == null) {
						 continue;
					}
					String _name=getValue(name).replace("（只）", "").replace("顶托", "上托");
					//处理特殊的工字钢类型   6.0米18#工字钢    6米工字钢
					_name=_name.replaceAll(" ", "");
				    if(_name.equals("6米工字钢"))
				    {
				    	   _name=_name.replace("米工字钢", "米18#工字钢").replace("6", "6.0");
				    }else
				    {
				    	   _name=_name.replace("米工字钢", "米18#工字钢");
				    }
					stockInventoryDetailDto.setName(_name);
					
					Cell num = row.getCell(2);
					if(num==null){
						 continue;
					}
					String _num=getValue(num);
					if(ValidUtil.get().isNumber(_num) || "".equals(_num))
					{
						stockInventoryDetailDto.setRealTotalS(_num);
					}else{
						resultDto.setErrorMsg("第"+bh+"行，期末总数只能为数字");
						resultDto.setSuccess(false);
						return resultDto;
					}
					
					Cell lastNum = row.getCell(3);
					if(lastNum==null){
						 continue;
					}
					String _lastNum=getValue(lastNum);
					if(ValidUtil.get().isNumber(_lastNum) || "".equals(_lastNum))
					{
						stockInventoryDetailDto.setAccTotalS(_lastNum);
					}else{
						resultDto.setErrorMsg("第"+bh+"行，期末库存只能为数字");
						resultDto.setSuccess(false);
						return resultDto;
					}
					
					detailDtos.add(stockInventoryDetailDto);
				}
				//全部验证通过就设置为true;
				resultDto.setSuccess(true);
				resultDto.setParamObj(detailDtos);
		} catch (Exception e) {
			    e.printStackTrace();
			    resultDto.setErrorMsg("读取Excel文件失败");
				resultDto.setSuccess(false);
		} 
		return resultDto;
	}
    protected static ResultDto<List<StockInventoryDetailDto>> readExcelLeaseFile(String path){
		
		ResultDto<List<StockInventoryDetailDto>> resultDto=new ResultDto<List<StockInventoryDetailDto>>();
		List<StockInventoryDetailDto> detailDtos=new ArrayList<StockInventoryDetailDto>();
		// 根据输入流创建Workbook对象
		Workbook wb;
		try {
				wb = WorkbookFactory.create(new File(path));
				// get到Sheet对象 
				Sheet sheet = wb.getSheetAt(0);
				int bh=0;
				for (int rowNum = 3; rowNum <= sheet.getLastRowNum(); rowNum++) {
					Row row = sheet.getRow(rowNum);
					if (row == null) {
						continue;
					}
					bh=rowNum+1;
					StockInventoryDetailDto stockInventoryDetailDto=new StockInventoryDetailDto();
					
					Cell name = row.getCell(0);
					if (name == null) {
						 continue;
					}
					String _name=getValue(name).replace("（只）", "").replace("顶托", "上托");
					//处理特殊的工字钢类型   6.0米18#工字钢    6米工字钢
					_name=_name.replaceAll(" ", "");
				    if(_name.equals("6米工字钢"))
				    {
				    	   _name=_name.replace("米工字钢", "米18#工字钢").replace("6", "6.0");
				    }else
				    {
				    	   _name=_name.replace("米工字钢", "米18#工字钢");
				    }
					stockInventoryDetailDto.setName(_name);
					
					Cell firstNum = row.getCell(6);
					if(firstNum==null){
						 continue;
					}
					String _firstNum=getValue(firstNum);
					if(ValidUtil.get().isNumber(_firstNum) || "".equals(_firstNum))
					{
						stockInventoryDetailDto.setDiffrate(_firstNum);
					}else{
						resultDto.setErrorMsg("第"+bh+"行，期末数量只能为数字");
						resultDto.setSuccess(false);
						return resultDto;
					}
					
					Cell num = row.getCell(3);
					if(num==null){
						 continue;
					}
					String _num=getValue(num);
					if(ValidUtil.get().isNumber(_num) || "".equals(_num))
					{
						stockInventoryDetailDto.setRealTotalS(_num);
					}else{
						resultDto.setErrorMsg("第"+bh+"行，累计发出只能为数字");
						resultDto.setSuccess(false);
						return resultDto;
					}
					
					Cell lastNum = row.getCell(4);
					if(lastNum==null){
						 continue;
					}
					String _lastNum=getValue(lastNum);
					if(ValidUtil.get().isNumber(_lastNum) || "".equals(_lastNum))
					{
						stockInventoryDetailDto.setAccTotalS(_lastNum);
					}else{
						resultDto.setErrorMsg("第"+bh+"行，累计收入只能为数字");
						resultDto.setSuccess(false);
						return resultDto;
					}
					
					detailDtos.add(stockInventoryDetailDto);
				}
				//全部验证通过就设置为true;
				resultDto.setSuccess(true);
				resultDto.setParamObj(detailDtos);
		} catch (Exception e) {
			    e.printStackTrace();
			    resultDto.setErrorMsg("读取Excel文件失败");
				resultDto.setSuccess(false);
		} 
		return resultDto;
	}
	
	/**
	 * 得到Excel表中的值
	 * 
	 * @param hssfCell
	 *            Excel中的每一个格子
	 * @return Excel中每一个格子中的值
	 */
	@SuppressWarnings("static-access")
	private static String getValue(Cell cell) {
		if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
			// 返回布尔类型的值
			return String.valueOf(cell.getBooleanCellValue());
		}else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
		    //先看是否是日期格式
	        if(DateUtil.isCellDateFormatted(cell)){
	        //读取日期格式
	    	return String.valueOf(cell.getDateCellValue());
	        }else{
	        //读取数字
	    	//返回数值类型的值 
	    	return String.valueOf(cell.getNumericCellValue());
	        }
		}else if(cell.getCellType() ==cell.CELL_TYPE_FORMULA ){
		    //读取公式
			return String.valueOf(cell.getCellFormula());
		} else {
			// 返回字符串类型的值
			return String.valueOf(cell.getStringCellValue());
		}
	}

	
	
	
}
