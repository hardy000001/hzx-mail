package com.lq.lss.core.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.BusHtDao;
import com.lq.lss.core.dao.CustomerInfoDao;
import com.lq.lss.core.dao.MaterialTypeDao;
import com.lq.lss.core.dao.MchBaseinfoDao;
import com.lq.lss.core.dao.SContractMoneyDao;
import com.lq.lss.core.dao.SMchMaterialinfoDao;
import com.lq.lss.core.dao.SMchMoneyDao;
import com.lq.lss.core.dao.SMchRentmaterialDao;
import com.lq.lss.core.dao.SMchTransmaterialDao;
import com.lq.lss.core.dao.STransferMoneyDao;
import com.lq.lss.core.dao.StockTotalflowDao;
import com.lq.lss.core.service.SContractMoneyService;
import com.lq.lss.dto.BusHtDto;
import com.lq.lss.dto.StockTransTotalflowDto;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.BMaterialType;
import com.lq.lss.model.CBusHt;
import com.lq.lss.model.CStockTotalflow;
import com.lq.lss.model.CustomerInfo;
import com.lq.lss.model.MchBaseinfo;
import com.lq.lss.model.SContractMoney;
import com.lq.lss.model.SMchMaterialinfo;
import com.lq.lss.model.SMchMoney;
import com.lq.lss.model.SMchRentmaterial;
import com.lq.lss.model.SMchTransmaterial;
import com.lq.lss.model.STransferMoney;
import com.lq.lss.utils.exception.jta.BusinessException;
import com.lq.util.TimeUtil;
/**
 *
 * @author  作者: Eric
 * @date 创建时间: 2016-11-24 17:06:41
 */
@Service
public class SContractMoneyServiceImpl extends EasyUIServiceBase<SContractMoney, String, SContractMoneyDao> implements SContractMoneyService{

    @Resource
	private SContractMoneyDao sContractMoneyDao;
    
    @Resource
	private SMchMaterialinfoDao sMchMaterialinfoDao;
    
    @Resource
 	private SMchRentmaterialDao sMchRentmaterialDao;
    
    @Resource
 	private SMchTransmaterialDao sMchTransmaterialDao; 
  
    @Resource
	private StockTotalflowDao stockTotalflowDao;
    @Resource
    private CustomerInfoDao customerInfoDao;
    
    @Resource
    private MaterialTypeDao materialTypeDao;
    
    @Resource
   	private BusHtDao cBusHtInfoDao;
    
    @Resource
   	private STransferMoneyDao sTransferMoneyDao;
    
    
    @Resource
   	private MchBaseinfoDao mchBaseinfoDao;
    
    @Resource
   	private SMchMoneyDao sMchMoneyDao;
    
 
	public ResultDto<String> settleMentDealRdTx(String setdate,Integer deptId) throws BusinessException{
    	// TODO Auto-generated method stub
    	
        ResultDto<String> result=null;
        
    	try {
    		
    		//根据结算月份处理不同的月份数据
    		TimeUtil.get();
    		
    		String dNow=TimeUtil.get().getDate();
			int nMonth=TimeUtil.getMonthSpace(dNow, setdate) ;
			
			//获取上个月的期初期末值
   		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
   		    Calendar cal = Calendar.getInstance();
   		    
   		    String sDate,eDate=setdate;
           
   			//根据日期循环结算
   			for(int i=0 ;i<nMonth+1;i++){
   				
   				cal.setTime(sdf.parse(setdate));
   				
   	            cal.add(Calendar.MONTH, i);
   	            
   	            String sLastDate=sdf.format(cal.getTime()).toString();
   	            
   	            sDate=sLastDate+"-01";
   	            
   	            //如果是当前月份之前开始循环月份，结算截止日期为今天
   	            
   	            if(i==nMonth){
   	            	eDate=dNow;
   	            }else{
   	            	eDate=sLastDate+"-31";;
   	            }
 
    	    	log.debug("=========结算调拨开始======="); 
   	    		settleTransferDeal(sDate,eDate,deptId);
   	    		log.debug("=========结算调拨结束======="); 
//   	    		
   	    		log.debug("=========租赁结算开始======="); 
   	    		settleRentDeal(sDate,eDate,deptId);
   	    		log.debug("=========租赁结算结束======="); 
   	    		
   	    		log.debug("=========入驻商户结算开始======="); 
   	    		settleMchDeal(sDate,eDate,deptId);
   	    		log.debug("=========入驻商户结算结束======="); 
   	    		
   	    		
   	    		log.debug("=========调拨费用计算开始======="); 
   	    		settleTransferFee(sDate,eDate,deptId);
   	    		log.debug("=========调拨费用计算结束======="); 
   	    		
   	    		log.debug("=========项目租赁费用计算开始======="); 
   	    		settleRentFee(sDate,eDate,deptId);
   	    		log.debug("=========项目租赁计算结束======="); 
   	    		
//   	    		
   	    		log.debug("=========入驻客户租赁费用计算开始======="); 
   	    		settleMchFee(sDate,eDate,deptId);
   	    		log.debug("=========入驻客户租赁计算结束======="); 
   	    	
   			}
   				
    		result=new ResultDto<String>(true,"结算成功");
  
        } catch (Exception e) {
			log.error("数据保存失败"+e.getMessage());
			e.printStackTrace();
			result=new ResultDto<String>(false,e.getMessage());
			//throw new BusinessException(e.getMessage());
        }
    	
    	return result;
    }
    
	
	
    
    /**
     * 调拨费用计算
     * @param 
     */
    public void settleRentFee(String sDate,String eDate,Integer deptId) {
        
    	try {
    		
    		String setdate=sDate.substring(0, 7);
    		
    		//根据结算日期获取上月结转费用
    		 List<StockTransTotalflowDto> beginFeeList=sMchRentmaterialDao.findBeginFeeBySettleDate(setdate);
    		   		
    		 Map<String,BigDecimal> beginFeemap=new HashMap<String,BigDecimal>(); 
          
 	    	//根据客户信息更新期初期末以及处理上月期末数据
 	     	for (StockTransTotalflowDto stockTransTotalflowDto : beginFeeList) 
 	 		{
 	     		beginFeemap.put(stockTransTotalflowDto.getHtCode(), stockTransTotalflowDto.getBeginFee());
 	 		}

 	     	
 	     	//根据合同获取租赁费用
 	     	
    		List<StockTransTotalflowDto> rentlist =stockTotalflowDao.findTotalRentFee(sDate,eDate);
    		
    		List<SContractMoney> scList=new ArrayList<SContractMoney>();
    		
    		
 
    		for (StockTransTotalflowDto stockTransTotalflowDto : rentlist) 
 			{
    			SContractMoney sContractMoney=new SContractMoney();
    			
    			//将期初金额加到总费用
     			if(stockTransTotalflowDto.getOrderType().equals("13")){
     				//项目发料处理
     				sContractMoney.setCuscode(stockTransTotalflowDto.getToMchcode());
     				sContractMoney.setRentFee(stockTransTotalflowDto.getTotalFee().add(beginFeemap.get(stockTransTotalflowDto.getHtCode())));
     			}else{
			    	//项目收料处理------期初只处理调入的费用
     				sContractMoney.setCuscode(stockTransTotalflowDto.getFromMchcode());
     				sContractMoney.setRentFee(stockTransTotalflowDto.getTotalFee().multiply(new BigDecimal(-1)));
     			}
     			sContractMoney.setDeptid(deptId);
     			sContractMoney.setHtcode(stockTransTotalflowDto.getHtCode());
     			sContractMoney.setSettleMonth(setdate);
     			
     			sContractMoney.setOtherFee(new BigDecimal(0));
     			sContractMoney.setZxFee(new BigDecimal(0));
     			sContractMoney.setTransportFee(new BigDecimal(0));
    			
     			scList.add(sContractMoney);
 			}
    		//更新数据
    		if(scList.size()>0)
    		{
    			sContractMoneyDao.batchUpdate(scList);
    		}
    		//关于期初金额计算调拨数量进行更新数据
    		
     	   //计算维修费用 会合杂费一起更新费用表
    		
     		List<StockTransTotalflowDto> repairFeelist =stockTotalflowDao.findTotalRepairFee(sDate,eDate);
     		
     		Map<String,BigDecimal> repairFeemap=new HashMap<String,BigDecimal>(); 
              
  	    	//根据客户信息更新期初期末以及处理上月期末数据
  	     	for (StockTransTotalflowDto stockTransTotalflowDto : repairFeelist) 
  	 		{
  	     		repairFeemap.put(stockTransTotalflowDto.getHtCode(), stockTransTotalflowDto.getTotalFee());
  	 		}

    		
    		//运输费杂费其他费用计算
    		
    		List<SContractMoney> otherFeeList=new ArrayList<SContractMoney>();
    		
    		List<StockTransTotalflowDto> transferOtherFeelist =stockTotalflowDao.findTotalRentOthrerFee(sDate,eDate);
    	    
    		for (StockTransTotalflowDto stockTransTotalflowDto : transferOtherFeelist) 
 			{
    			SContractMoney sContractMoney=new SContractMoney();
    			
    			//将期初金额加到总费用
     			if(stockTransTotalflowDto.getOrderType().equals("13")){
     				//项目发料处理
     				sContractMoney.setCuscode(stockTransTotalflowDto.getToMchcode());	
     			}else{
			    	//项目收料处理------期初只处理调入的费用
     				sContractMoney.setCuscode(stockTransTotalflowDto.getFromMchcode());
     			}

     			sContractMoney.setHtcode(stockTransTotalflowDto.getHtCode());
     			
     			sContractMoney.setRentFee(new BigDecimal(0));
     			sContractMoney.setTransportFee(stockTransTotalflowDto.getTotalTransportFee());
     			sContractMoney.setOtherFee(stockTransTotalflowDto.getTotalOtherFee());
     			sContractMoney.setZxFee(stockTransTotalflowDto.getTotalZxFee());
     			sContractMoney.setDeptid(deptId);
     			sContractMoney.setSettleMonth(setdate);
     			
     			//维修费用修改
     			if(repairFeemap.get(stockTransTotalflowDto.getHtCode())!=null)
     			{
     				sContractMoney.setRepairFee(repairFeemap.get(stockTransTotalflowDto.getHtCode()));
     			}else
     			{
     				sContractMoney.setRepairFee(new BigDecimal(0));
     			}

     			otherFeeList.add(sContractMoney);
 			}
    		
    		//更新数据
    		if(otherFeeList.size()>0)
    		{
    			sContractMoneyDao.batchUpdate(otherFeeList);
    		}
    		
    	   //计算赔偿费用
    		
    		
    	} catch (Exception e) {
    		log.error("结算异常"+e.getMessage());
    		e.printStackTrace();
    		throw new BusinessException(e.getMessage());
        }
    }
	
	
    
    /**
     * 调拨费用计算
     * @param 
     */
    public void settleTransferFee(String sDate,String eDate,Integer deptId) {

        
    	try {
    		
    		String setdate=sDate.substring(0, 7);
    		
    		List<StockTransTotalflowDto> beginFeeList=sMchMaterialinfoDao.findBeginFeeBySettleDate(setdate);
    		
    		
    		 Map<String,BigDecimal> beginFeemap=new HashMap<String,BigDecimal>(); 
          
 	    	//根据客户信息更新期初期末以及处理上月期末数据
 	     	for (StockTransTotalflowDto stockTransTotalflowDto : beginFeeList) 
 	 		{
 	     		beginFeemap.put(stockTransTotalflowDto.getFromMchcode(), stockTransTotalflowDto.getBeginFee());
 	 		}

    		List<StockTransTotalflowDto> transferlist =stockTotalflowDao.findTotalTransferFee(sDate,eDate);
    		
    		List<STransferMoney> scList=new ArrayList<STransferMoney>();
    		
    		for (StockTransTotalflowDto stockTransTotalflowDto : transferlist) 
 			{
    			STransferMoney sTransferMoney=new STransferMoney();
    			
    			//将期初金额加到总费用
     			if(stockTransTotalflowDto.getOrderType().equals("11")){
     				//调拨调入处理
     				sTransferMoney.setCuscode(stockTransTotalflowDto.getFromMchcode());
     				sTransferMoney.setTransferFee(stockTransTotalflowDto.getTotalFee().add(beginFeemap.get(stockTransTotalflowDto.getFromMchcode())));
     			}else{
			    	//调拨调出处理------期初只处理调入的费用
     				sTransferMoney.setCuscode(stockTransTotalflowDto.getToMchcode());
     				sTransferMoney.setTransferFee(stockTransTotalflowDto.getTotalFee().multiply(new BigDecimal(-1)));
     			}
    			
     			sTransferMoney.setOtherFee(new BigDecimal(0));
     			sTransferMoney.setZxFee(new BigDecimal(0));
     			sTransferMoney.setTransportFee(new BigDecimal(0));
     			
     			
     			sTransferMoney.setDeptid(deptId);
     			sTransferMoney.setSettleMonth(setdate);
    			
     			scList.add(sTransferMoney);
 			}
    		
    		
    		//更新数据
    		if(scList.size()>0)
    		{
    			sTransferMoneyDao.batchUpdate(scList);
    		}
    		//关于期初金额计算调拨数量进行更新数据
    		
    		//运输费杂费其他费用计算
    		
    		List<STransferMoney> otherFeeList=new ArrayList<STransferMoney>();
    		
    		List<StockTransTotalflowDto> transferOtherFeelist =stockTotalflowDao.findTotalTsfOthrerFee(sDate,eDate);
    	    
    		for (StockTransTotalflowDto stockTransTotalflowDto : transferOtherFeelist) 
 			{
    			STransferMoney sTransferMoney=new STransferMoney();
    			
    			//将期初金额加到总费用
     			if(stockTransTotalflowDto.getOrderType().equals("11")){
     				//调拨调入处理
     				sTransferMoney.setCuscode(stockTransTotalflowDto.getFromMchcode());
     			}else{
			    	//调拨调出处理------期初只处理调入的费用
     				sTransferMoney.setCuscode(stockTransTotalflowDto.getToMchcode());
     			}
     			sTransferMoney.setTransportFee(stockTransTotalflowDto.getTotalTransportFee());
     			sTransferMoney.setTransferFee(new BigDecimal(0));
     			sTransferMoney.setOtherFee(stockTransTotalflowDto.getTotalOtherFee());
     			sTransferMoney.setZxFee(stockTransTotalflowDto.getTotalZxFee());
     			sTransferMoney.setDeptid(deptId);
     			sTransferMoney.setSettleMonth(setdate);
     			
    			
     			otherFeeList.add(sTransferMoney);
 			}
    		
    		//更新数据
    		if(otherFeeList.size()>0)
    		{
    			sTransferMoneyDao.batchUpdate(otherFeeList);
    		}
    		
    		
    		
    	} catch (Exception e) {
    		log.error("结算异常"+e.getMessage());
    		e.printStackTrace();
    		throw new BusinessException(e.getMessage());
        }
    }
    

    /**
     * 调拨结算处理
     * @param 
     */
    public void settleTransferDeal(String sDate,String eDate,Integer deptId) {
    	
    	try {
    		
    		String setdate=sDate.substring(0, 7);
    		//获取所有调拨客户信息以及期初期末值
    		List<CustomerInfo> cusList=customerInfoDao.findCustomerInfoByDeptIdAndCustype(deptId.toString());
    		
    		//获取物资所有大类
  			BMaterialType bMaterialType=new BMaterialType();
  			bMaterialType.setParentid(6);
  			bMaterialType.setOrderStr("typeid");
  			List<BMaterialType> malist =materialTypeDao.findBmtList(bMaterialType);
  			 
  			//String nsetdate=setdate.substring(0, 4)+"-"+setdate.substring(4);
  			

  			//删除当前结算月结算数据
  			sMchMaterialinfoDao.deleteBySetDate(setdate);
  			
  			
  		    //获取上个月的期初期末值
    		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
    		 
    		 Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(setdate));
            cal.add(Calendar.MONTH, -1);
            
            String sLastDate=sdf.format(cal.getTime()).toString();
            
            
      		 SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
    		 Date date1 = df2.parse(setdate+"-01 00:00:01"); 
     	
            
            //获取期初期末数据
            List<SMchMaterialinfo> sMchMater= new ArrayList<SMchMaterialinfo>();
            sMchMater=sMchMaterialinfoDao.findMchMaterialinfoList(sLastDate);
            
            Map<String,BigDecimal> lastMonthmap=new HashMap<String,BigDecimal>(); 
            //期初期末数据记录
            Map<String,String> mapData=new HashMap<String,String>(); 
            String ifCuscode="";
           
	    	//根据客户信息更新期初期末以及处理上月期末数据
	     	for (SMchMaterialinfo sMchMaterialinfo : sMchMater) 
	 		{
	     		lastMonthmap.put(sMchMaterialinfo.getTypeid()+"|"+sMchMaterialinfo.getDeptid()+"|"+sMchMaterialinfo.getCuscode(), sMchMaterialinfo.getEndNum())	;
	     		//数据保存
	     		if(sMchMaterialinfo.getCuscode().equals(ifCuscode)){
	     			String s=mapData.get(sMchMaterialinfo.getCuscode());
	     			s=s+"&"+sMchMaterialinfo.getTypeid()+"|"+sMchMaterialinfo.getEndNum()+"|"+sMchMaterialinfo.getDeptid()+"|"+sMchMaterialinfo.getTransferIn()+"|"+sMchMaterialinfo.getTransferOut();
	     			mapData.put(sMchMaterialinfo.getCuscode(), s);
	     		}else
	     		{
	     			mapData.put(sMchMaterialinfo.getCuscode(), sMchMaterialinfo.getTypeid()+"|"+sMchMaterialinfo.getEndNum()+"|"+sMchMaterialinfo.getDeptid()+"|"+sMchMaterialinfo.getTransferIn()+"|"+sMchMaterialinfo.getTransferOut());
	     		}
	     		ifCuscode=sMchMaterialinfo.getCuscode();
	 		}
	     	
	     	
	     	 List<STransferMoney> sTransMoney= new ArrayList<STransferMoney>();
	     	 
    		//根据客户信息插入初值
     		for (CustomerInfo customerInfo : cusList) 
 			{
     			//根据客户号插入初始值 
     			List<SMchMaterialinfo> smchm= new ArrayList<SMchMaterialinfo>();
     			
     			
     			//根据物资信息增加客户各物资大类的初始化信息
     			for (BMaterialType nbMaterialType : malist) 
     			{
     				//获取本月期初值
     				String sMch= nbMaterialType.getTypeid()+"|"+customerInfo.getDeptid()+"|"+customerInfo.getCuscode();
     				
     				SMchMaterialinfo nsMchMaterialinfo=new SMchMaterialinfo();
     				
     				//根据上月的数据设置本月期初
     				if(lastMonthmap.get(sMch)!=null){
     					
     					nsMchMaterialinfo.setBeginNum(lastMonthmap.get(sMch));
     					nsMchMaterialinfo.setEndNum(lastMonthmap.get(sMch));
     					
     				}else
     				{
     					nsMchMaterialinfo.setBeginNum(new BigDecimal(0));
     					nsMchMaterialinfo.setEndNum(new BigDecimal(0));
     				}
     				nsMchMaterialinfo.setCuscode(customerInfo.getCuscode());
     				nsMchMaterialinfo.setDeptid(Integer.parseInt(customerInfo.getDeptid()));	
     				nsMchMaterialinfo.setTypeid(nbMaterialType.getTypeid());
					
					nsMchMaterialinfo.setTransferIn(new BigDecimal(0));
					nsMchMaterialinfo.setTransferOut(new BigDecimal(0));
					nsMchMaterialinfo.setSaleNum(new BigDecimal(0));
					nsMchMaterialinfo.setPurchaseNum(new BigDecimal(0));
					nsMchMaterialinfo.setUnit(nbMaterialType.getAccountFlag());
					nsMchMaterialinfo.setSettledate(setdate);
					
					smchm.add(nsMchMaterialinfo);	
     			}
     			
				STransferMoney sTransferMoney=new STransferMoney();
				sTransferMoney.setCuscode(customerInfo.getCuscode());
				sTransferMoney.setDeptid(Integer.parseInt(customerInfo.getDeptid()));
				sTransferMoney.setSettleMonth(setdate);
				sTransferMoney.setTransferFee(new BigDecimal(0));
				sTransferMoney.setRentFee(new BigDecimal(0));
				sTransferMoney.setRepairFee(new BigDecimal(0));
				sTransferMoney.setDueFee(new BigDecimal(0));
				sTransferMoney.setTransportFee(new BigDecimal(0));
				sTransferMoney.setOtherFee(new BigDecimal(0));
				sTransferMoney.setTotalFee(new BigDecimal(0));
				sTransferMoney.setZxFee(new BigDecimal(0));
				sTransferMoney.setRedressFee(new BigDecimal(0));
				sTransferMoney.setReceivedFee(new BigDecimal(0));
				sTransferMoney.setStopFee(new BigDecimal(0));
				
				sTransMoney.add(sTransferMoney);
				
				
     			 //初始化数据
 				sMchMaterialinfoDao.batchCreate(smchm);
 				
 			}
     		
     		//初始化调拨费用表
     	   sTransferMoneyDao.deleteBySetDate(setdate);
     	   
		   sTransferMoneyDao.batchCreate(sTransMoney);
     		
	     	
	     	List<CStockTotalflow> cStockTotalflowlist=new ArrayList<CStockTotalflow>();
	     	
	     	
	     	  for(Map.Entry<String,String> entry:mapData.entrySet()){  
	     		  
	     		 CStockTotalflow cStockTotalflow=new CStockTotalflow();
	     		 
	     		 cStockTotalflow.setFromMchcode(entry.getKey()); 
	     		 cStockTotalflow.setToMchcode("100000");

	     		 //entry.getKey() 
	     		  String[] sData=entry.getValue().split("&");
	     		  
	     		  
	     		cStockTotalflow.setTotalGguan(new BigDecimal(0));
	     		cStockTotalflow.setTotalKjian(new BigDecimal(0));
	     		cStockTotalflow.setTotalTguan(new BigDecimal(0));
	     		cStockTotalflow.setTotalDguan(new BigDecimal(0));
	     		cStockTotalflow.setTotalLkjia(new BigDecimal(0));
	     		cStockTotalflow.setTotalCgang(new BigDecimal(0));
	     		cStockTotalflow.setTotalTwgzgang(new BigDecimal(0));
	     		cStockTotalflow.setTotalGzgang(new BigDecimal(0));
	     		cStockTotalflow.setTotalTwang(new BigDecimal(0));
	     		
	     		cStockTotalflow.setTotalItem1(new BigDecimal(0));
	     		cStockTotalflow.setTotalItem2(new BigDecimal(0));
	     		
	     		  for(int i=0;i<sData.length ;i++)
	     		  {
	     			 String[] typeid= sData[i].split("\\|");
	     			 
	     			 if(malist.get(0).getTypeid().toString().equals(typeid[0])){
	     				 
	     				cStockTotalflow.setTotalGguan(new BigDecimal(typeid[1]));
	     				 
	     			 }else if(malist.get(1).getTypeid().toString().equals(typeid[0])){
	     				cStockTotalflow.setTotalKjian(new BigDecimal(typeid[1]));
	     				 
	     			 }else if(malist.get(2).getTypeid().toString().equals(typeid[0])){
	     				cStockTotalflow.setTotalTguan(new BigDecimal(typeid[1]));
	     				 
	     			 }else if(malist.get(3).getTypeid().toString().equals(typeid[0])){
	     				cStockTotalflow.setTotalDguan(new BigDecimal(typeid[1]));
	     				 
	     			 }else if(malist.get(4).getTypeid().toString().equals(typeid[0])){
	     				cStockTotalflow.setTotalLkjia(new BigDecimal(typeid[1]));
	     				 
	     			 }else if(malist.get(5).getTypeid().toString().equals(typeid[0])){
	     				cStockTotalflow.setTotalCgang(new BigDecimal(typeid[1]));
	     				 
	     			 }else if(malist.get(6).getTypeid().toString().equals(typeid[0])){
	     				cStockTotalflow.setTotalTwgzgang(new BigDecimal(typeid[1]));
	     			 }else if(malist.get(7).getTypeid().toString().equals(typeid[0])){
	     				cStockTotalflow.setTotalGzgang(new BigDecimal(typeid[1]));
	     			 }	
	     			 
	     			cStockTotalflow.setDeptid(Integer.parseInt(typeid[2]));
	     			
	     		  }
	   
	     		cStockTotalflow.setTradetime(date1);
	     		String strOrder="TFB"+df2.format(new Date()).replace("-", "").replace(":", "").replace(" ", "")+(int)(Math.random()*100);
	     		cStockTotalflow.setOrderNo(strOrder);
	     		cStockTotalflow.setOrderType(String.valueOf(TradeType.TRANSFER_BEGIN.getType()));
	     		cStockTotalflow.setZxFee(new BigDecimal(0));
	     		cStockTotalflow.setTransportFee(new BigDecimal(0));
	     		cStockTotalflow.setOtherFee(new BigDecimal(0));
	     		
	     	    cStockTotalflowlist.add(cStockTotalflow);
	     		 
	     		
	     	  }
	     	
	     	  if(cStockTotalflowlist.size()>0){
	     	   log.debug("=========插入流水汇总表信息 临时关闭=======");   
	     		//插入流水汇总表信息 临时关闭
	     		 //stockTotalflowDao.batchCreate(cStockTotalflowlist);
	     	  }
	     	
     		
		    //获取交易汇总信息
	    	List<CStockTotalflow> cStockTotalflows=new ArrayList<CStockTotalflow>();
	    	    
	    	
	    	//cStockTotalflows=stockTotalflowDao.findTotalTransferFlow(sDate,eDate);
	    	cStockTotalflows=stockTotalflowDao.findTotalFlowData(sDate,eDate);  
//	        //根据客户信息插入初值
//	     	for (CStockTotalflow cStockTotalflow : cStockTotalflows) 
//	 		{
//	     				     			
//	     		if(cStockTotalflow.getOrderType().equals("11")){
//     				//调拨调入处理
//     				saveTransInAndOut(malist,cStockTotalflow,setdate,1);
//     				
//     			}else if(cStockTotalflow.getOrderType().equals("12")){
//			    	//调拨调出处理
//			    	saveTransInAndOut(malist,cStockTotalflow,setdate,-1);
//     			}
//	 		}
	    	
	     	for (CStockTotalflow cStockTotalflow : cStockTotalflows) 
	 		{
	     				     			
	     		if(cStockTotalflow.getOrderType().equals("11")){
     				//调拨调入处理
	     			saveTransData(malist,cStockTotalflow,setdate,1);
     				
     			}else if(cStockTotalflow.getOrderType().equals("12")){
			    	//调拨调出处理
     				saveTransData(malist,cStockTotalflow,setdate,-1);
     			}
	 		}
    	} catch (Exception e) {
    		log.error("结算异常"+e.getMessage());
    		e.printStackTrace();
    		throw new BusinessException(e.getMessage());
        }
    }
    
    
    
    /**
     * 调拨数据更新处理
     * @param 
     */
    private void saveTransData(List<BMaterialType> malist,CStockTotalflow cStockTotalflow,String setDate,Integer dataChang){
    	

        try{
        	
           	//调入信息处理
    	    Map<String,BigDecimal> inmap=new HashMap<String,BigDecimal>(); 
    	     
    	    String mchCode=cStockTotalflow.getFromMchcode();
    	    
    	    if(dataChang==-1)
    	    {
    	    	mchCode=cStockTotalflow.getToMchcode();
    	    }
    	   
        	SMchMaterialinfo sMchMaterialinfo=new SMchMaterialinfo();
        	
        	sMchMaterialinfo.setCuscode(mchCode);
        	
        	sMchMaterialinfo.setTypeid(Integer.parseInt(cStockTotalflow.getTypeId()));
        	
        	//计算期初和调入调出数据
        	//调入调出
            if(dataChang==1)
    	    {
            	sMchMaterialinfo.setTransferIn(cStockTotalflow.getTotalAmt());
    	    	sMchMaterialinfo.setTransferOut(new BigDecimal(0));
    	   	
    	    }else
    	    {
    	    	sMchMaterialinfo.setTransferIn(new BigDecimal(0));
    	    	sMchMaterialinfo.setTransferOut(cStockTotalflow.getTotalAmt());
    	    	
    	    }
            
            sMchMaterialinfo.setEndNum(cStockTotalflow.getTotalAmt());
        	
        	sMchMaterialinfo.setPurchaseNum(new BigDecimal(0));
        	sMchMaterialinfo.setSaleNum(new BigDecimal(0));

        	sMchMaterialinfo.setDeptid(cStockTotalflow.getDeptid());

        	sMchMaterialinfo.setSettledate(setDate);
    	    
        	sMchMaterialinfoDao.update(sMchMaterialinfo);
        	
    	} catch (Exception e) {
    		log.error("调拨数据结算异常"+e.getMessage());
    		e.printStackTrace();
    		throw new BusinessException(e.getMessage());
        }
 
	    
    	
//	    List<SMchMaterialinfo> sList= new ArrayList<SMchMaterialinfo>();
//	    
//	    for(Map.Entry<String,BigDecimal> entry:inmap.entrySet()){  
//	    	
//	    	String[] info=entry.getKey().split("\\|");
//	    	
//	    	SMchMaterialinfo sMchMaterialinfo=new SMchMaterialinfo();
//	    	
//	    	sMchMaterialinfo.setCuscode(info[1]);
//	    	
//	    	sMchMaterialinfo.setTypeid(Integer.parseInt(info[0]));
//	    	
//	    	//计算期初和调入调出数据
//	    	//调入调出
//	        if(dataChang==1)
//		    {
//	        	sMchMaterialinfo.setTransferIn(entry.getValue());
//		    	sMchMaterialinfo.setTransferOut(new BigDecimal(0));
//		    	
//		    	
//		    }else
//		    {
//		    	sMchMaterialinfo.setTransferIn(new BigDecimal(0));
//		    	sMchMaterialinfo.setTransferOut(entry.getValue());
//		    	
//		    }
//	        
//	        sMchMaterialinfo.setEndNum(entry.getValue());
//	    	
//	    	sMchMaterialinfo.setPurchaseNum(new BigDecimal(0));
//	    	sMchMaterialinfo.setSaleNum(new BigDecimal(0));
//
//	    	sMchMaterialinfo.setDeptid(cStockTotalflow.getDeptid());
//
//	    	sMchMaterialinfo.setSettledate(setDate);
//	    	
//	    	sList.add(sMchMaterialinfo);   
//	   }   
	    
	    
//	    //批量修改外部调入交易数据
//	    if(sList.size()>0)
//	    {
//	    	sMchMaterialinfoDao.batchUpdate(sList);
//	    }
    	
    }
    
    
    /**
     * 调拨处理
     * @param 
     */
    private void saveTransInAndOut(List<BMaterialType> malist,CStockTotalflow cStockTotalflow,String setDate,Integer dataChang){
    	//调入信息处理
	    Map<String,BigDecimal> inmap=new HashMap<String,BigDecimal>(); 
	     
	    String mchCode=cStockTotalflow.getFromMchcode();
	    
	    if(dataChang==-1)
	    {
	    	mchCode=cStockTotalflow.getToMchcode();
	    }
	    
		inmap.put(malist.get(0).getTypeid()+"|"+mchCode,cStockTotalflow.getTotalGguan());
		inmap.put(malist.get(1).getTypeid()+"|"+mchCode,cStockTotalflow.getTotalKjian());
		inmap.put(malist.get(2).getTypeid()+"|"+mchCode,cStockTotalflow.getTotalTguan());
		inmap.put(malist.get(3).getTypeid()+"|"+mchCode,cStockTotalflow.getTotalDguan());
		inmap.put(malist.get(4).getTypeid()+"|"+mchCode,cStockTotalflow.getTotalLkjia());
		inmap.put(malist.get(5).getTypeid()+"|"+mchCode,cStockTotalflow.getTotalCgang());
		inmap.put(malist.get(6).getTypeid()+"|"+mchCode,cStockTotalflow.getTotalTwgzgang());
		inmap.put(malist.get(7).getTypeid()+"|"+mchCode,cStockTotalflow.getTotalGzgang());
		
		cStockTotalflow.setTotalCgang(new BigDecimal(0));
 		cStockTotalflow.setTotalTwgzgang(new BigDecimal(0));
 		cStockTotalflow.setTotalGzgang(new BigDecimal(0));
 		cStockTotalflow.setTotalTwang(new BigDecimal(0));
    	
	    List<SMchMaterialinfo> sList= new ArrayList<SMchMaterialinfo>();
	    for(Map.Entry<String,BigDecimal> entry:inmap.entrySet()){  
	    	
	    	String[] info=entry.getKey().split("\\|");
	    	SMchMaterialinfo sMchMaterialinfo=new SMchMaterialinfo();
	    	
	    	sMchMaterialinfo.setCuscode(info[1]);
	    	
	    	sMchMaterialinfo.setTypeid(Integer.parseInt(info[0]));
	    	
	    	//计算期初和调入调出数据
	    	//调入调出
	        if(dataChang==1)
		    {
	        	sMchMaterialinfo.setTransferIn(entry.getValue());
		    	sMchMaterialinfo.setTransferOut(new BigDecimal(0));
		    	
		    	
		    }else
		    {
		    	sMchMaterialinfo.setTransferIn(new BigDecimal(0));
		    	sMchMaterialinfo.setTransferOut(entry.getValue());
		    }
	        
	        sMchMaterialinfo.setEndNum(entry.getValue());
	    	
	    	sMchMaterialinfo.setPurchaseNum(new BigDecimal(0));
	    	sMchMaterialinfo.setSaleNum(new BigDecimal(0));

	    	sMchMaterialinfo.setDeptid(cStockTotalflow.getDeptid());

	    	sMchMaterialinfo.setSettledate(setDate);
	    	
	    	sList.add(sMchMaterialinfo);   
	   }   
	    
	    
	    //批量修改外部调入交易数据
	    if(sList.size()>0)
	    {
	    	sMchMaterialinfoDao.batchUpdate(sList);
	    }
    	
    }
    
    
    /**
     * 入驻客户出入库调拨
     * @param 
     */
    private void saveMchInAndOut(List<BMaterialType> malist,CStockTotalflow cStockTotalflow,String setDate){
    	
    	try {
    		
        	//调入信息处理
    	    Map<String,BigDecimal> inmap=new HashMap<String,BigDecimal>(); 
    	     
    	    String mchCode=cStockTotalflow.getFromMchcode();
    	    
    	    if(cStockTotalflow.getOrderType().equals("1") ||cStockTotalflow.getOrderType().equals("29") )
    	    {
    	    	mchCode=cStockTotalflow.getToMchcode();
    	    }
    		
    	SMchTransmaterial sMchMaterialinfo=new SMchTransmaterial();
	    	
	    	sMchMaterialinfo.setMchcode(mchCode);
	    	
	    	sMchMaterialinfo.setTypeid(Integer.parseInt(cStockTotalflow.getTypeId()));
	    	
	     	sMchMaterialinfo.setTransferIn(new BigDecimal(0));
	    	sMchMaterialinfo.setTransferOut(new BigDecimal(0));
	    	sMchMaterialinfo.setStockIn(new BigDecimal(0));
	    	sMchMaterialinfo.setStockOut(new BigDecimal(0));
	    	
	    	
     		if(cStockTotalflow.getOrderType().equals("1")){
 				//入库处理
     			sMchMaterialinfo.setStockIn(cStockTotalflow.getTotalAmt());
     			
 			}else if(cStockTotalflow.getOrderType().equals("2")){
		    	//出库处理
 				sMchMaterialinfo.setStockOut(cStockTotalflow.getTotalAmt());
 			
 			}else if(cStockTotalflow.getOrderType().equals("29")){
 				//中心内调入
 				sMchMaterialinfo.setTransferIn(cStockTotalflow.getTotalAmt());
 				
 			}else{
 				//中心内调出
 				sMchMaterialinfo.setTransferOut(cStockTotalflow.getTotalAmt());
 				
 			}
	    	
	    
	        sMchMaterialinfo.setEndNum(cStockTotalflow.getTotalAmt());
	    	
	    	sMchMaterialinfo.setPurchaseNum(new BigDecimal(0));
	    	sMchMaterialinfo.setSaleNum(new BigDecimal(0));

	    	sMchMaterialinfo.setDeptid(cStockTotalflow.getDeptid());

	    	sMchMaterialinfo.setSettledate(setDate);
	    	
	    	//sList.add(sMchMaterialinfo);   
	  // }   
	    
		     sMchTransmaterialDao.update(sMchMaterialinfo);
		        		
    		
    		
    		
       	} catch (Exception e) {
    		log.error("入驻客户物资结算异常"+e.getMessage());
    		e.printStackTrace();
    		throw new BusinessException(e.getMessage());
        }
    	

	    
//		inmap.put(malist.get(0).getTypeid()+"|"+mchCode,cStockTotalflow.getTotalGguan());
//		inmap.put(malist.get(1).getTypeid()+"|"+mchCode,cStockTotalflow.getTotalKjian());
//		inmap.put(malist.get(2).getTypeid()+"|"+mchCode,cStockTotalflow.getTotalTguan());
//		inmap.put(malist.get(3).getTypeid()+"|"+mchCode,cStockTotalflow.getTotalDguan());
//		inmap.put(malist.get(4).getTypeid()+"|"+mchCode,cStockTotalflow.getTotalLkjia());
//		inmap.put(malist.get(5).getTypeid()+"|"+mchCode,cStockTotalflow.getTotalCgang());
//		inmap.put(malist.get(6).getTypeid()+"|"+mchCode,cStockTotalflow.getTotalTwgzgang());
//		inmap.put(malist.get(7).getTypeid()+"|"+mchCode,cStockTotalflow.getTotalGzgang());
//		
//		cStockTotalflow.setTotalCgang(new BigDecimal(0));
// 		cStockTotalflow.setTotalTwgzgang(new BigDecimal(0));
// 		cStockTotalflow.setTotalGzgang(new BigDecimal(0));
// 		cStockTotalflow.setTotalTwang(new BigDecimal(0));
    	
	   // List<SMchTransmaterial> sList= new ArrayList<SMchTransmaterial>();
	  
	   // for(Map.Entry<String,BigDecimal> entry:inmap.entrySet()){  
	    	
	    	//String[] info=entry.getKey().split("\\|");
	
	   
	    
	    
//	    //批量修改外部调入交易数据
//	    if(sList.size()>0)
//	    {
//	      sMchTransmaterialDao.batchUpdate(sList);
//	    }
    	
    }
    
    
   
    
    /**
     * 租赁结算处理
     * @param 
     */
    
    
    public void settleRentDeal(String sDate,String eDate,Integer deptId) {
    	

        
    	try {
    		
    		String setdate=sDate.substring(0, 7);
    		
    		//获取所有调拨客户信息以及期初期末值
    		List<CBusHt> cbushtList=cBusHtInfoDao.findCBusHtList(deptId.toString());
    		
    		

  		    //获取上个月的期初期末值
    	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
    		 
    		Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(setdate));
            cal.add(Calendar.MONTH, -1);
            
            String sLastDate=sdf.format(cal.getTime()).toString();
            
            //获取期初值
            List<SMchRentmaterial> sMchRentList=sMchRentmaterialDao.findMchMaterialinfoList(sLastDate);
            Map<String,BigDecimal> lastMonthmap=new HashMap<String,BigDecimal>(); 
        
	    	//根据客户信息更新期初期末以及处理上月期末数据
	     	for (SMchRentmaterial sMchRentmaterial : sMchRentList) 
	 		{
	     		lastMonthmap.put(sMchRentmaterial.getTypeid()+"|"+sMchRentmaterial.getDeptid()+"|"+sMchRentmaterial.getHtcode(), sMchRentmaterial.getEndNum())	;
	 		}
            
    		//获取物资所有大类
  			BMaterialType bMaterialType=new BMaterialType();
  			bMaterialType.setParentid(6);
  			bMaterialType.setOrderStr("typeid");
  			List<BMaterialType> malist =materialTypeDao.findBmtList(bMaterialType);
  
  			//删除当前结算月结算数据
  			sMchRentmaterialDao.deleteBySetDate(setdate);
  			
  			
  			List<SContractMoney> sContactMoneyList= new ArrayList<SContractMoney>();
  			
  			
    		//根据客户信息插入初值
     		for (CBusHt cBusHtInfo : cbushtList) 
 			{
     			//根据客户号插入初始值 
     			List<SMchRentmaterial> smchm= new ArrayList<SMchRentmaterial>();
     			
     			//根据物资信息增加客户各物资大类的初始化信息
     			for (BMaterialType nbMaterialType : malist) 
     			{
     				
     				SMchRentmaterial sMchRentmaterial=new SMchRentmaterial();
     				sMchRentmaterial.setHtcode(cBusHtInfo.getHtcode());
     				sMchRentmaterial.setCuscode(cBusHtInfo.getLesseeCusCode());
     				sMchRentmaterial.setDeptid(deptId);	
     				sMchRentmaterial.setTypeid(nbMaterialType.getTypeid());
     				//获取本月期初值
     				String sMch=nbMaterialType.getTypeid()+"|"+ cBusHtInfo.getDeptid()+"|"+cBusHtInfo.getHtcode();
     				
     				//根据上月的数据设置本月期初
     				if(lastMonthmap.get(sMch)!=null){
     					
     					sMchRentmaterial.setBeginNum(lastMonthmap.get(sMch));
     					sMchRentmaterial.setEndNum(lastMonthmap.get(sMch));
     				}else
     				{
     					sMchRentmaterial.setBeginNum(new BigDecimal(0));
     					sMchRentmaterial.setEndNum(new BigDecimal(0));
     					
     				}
     				
     				sMchRentmaterial.setSendNum(new BigDecimal(0));
     				sMchRentmaterial.setReceiptNum(new BigDecimal(0));
     				sMchRentmaterial.setIndemnifyNum(new BigDecimal(0));
     				sMchRentmaterial.setUnit(nbMaterialType.getAccountFlag());
					sMchRentmaterial.setSettledate(setdate);
					smchm.add(sMchRentmaterial);
     			}
     			  //初始化数据
     			sMchRentmaterialDao.batchCreate(smchm);
     			
     			SContractMoney sContractMoney=new SContractMoney();
     			sContractMoney.setCuscode(cBusHtInfo.getLesseeCusCode());
     			sContractMoney.setHtcode(cBusHtInfo.getHtcode());
     			sContractMoney.setSettleMonth(setdate);
     			sContractMoney.setTransferFee(new BigDecimal(0));
     			sContractMoney.setRentFee(new BigDecimal(0));
     			sContractMoney.setRepairFee(new BigDecimal(0));
     			sContractMoney.setDueFee(new BigDecimal(0));
     			sContractMoney.setTransportFee(new BigDecimal(0));
     			sContractMoney.setOtherFee(new BigDecimal(0));
     			sContractMoney.setTotalFee(new BigDecimal(0));
     			sContractMoney.setZxFee(new BigDecimal(0));
     			sContractMoney.setRedressFee(new BigDecimal(0));
     			sContractMoney.setReceivedFee(new BigDecimal(0));
     			sContractMoney.setStopFee(new BigDecimal(0));
     			sContractMoney.setDeptid(deptId);
     			sContactMoneyList.add(sContractMoney);
 			}
                //合同金额初始化
     		    sContractMoneyDao.deleteBySetDate(setdate);
     		    sContractMoneyDao.batchCreate(sContactMoneyList);
				//获取交易汇总信息
	    	    List<CStockTotalflow> cStockTotalflows=new ArrayList<CStockTotalflow>();
	    	    
	    	    cStockTotalflows=stockTotalflowDao.findTotalRentData(sDate,eDate);
	    	    
	    		//根据客户信息插入初值
	     		for (CStockTotalflow cStockTotalflow : cStockTotalflows) 
	 			{
//	     			//根据不同的商户号 不同的交易做不同的数据修改     			
//	     			if(cStockTotalflow.getOrderType().equals("13")){
//	     				//发料处理
//	     				saveSendAndReceive(malist,cStockTotalflow,setdate,1);
//	     				
//	     			}else if(cStockTotalflow.getOrderType().equals("14")){
//    			    	//收料处理
//	     				saveSendAndReceive(malist,cStockTotalflow,setdate,-1);
//	     			}
	 			}

    		
    	} catch (Exception e) {
    		log.error("结算异常"+e.getMessage());
    		e.printStackTrace();
    		throw new BusinessException(e.getMessage());
        }
    }
    
    
    
    
    /**
     * 租赁结算处理
     * @param 
     */
    public void settleMchDeal(String sDate,String eDate,Integer deptId) {
      
    	try {
    		
    	    String setdate=sDate.substring(0, 7);
    		//获取所有入驻客户信息以及期初期末值
    		List<MchBaseinfo> mchList=mchBaseinfoDao.loadAll();
    		//获取物资所有大类
  			BMaterialType bMaterialType=new BMaterialType();
  			bMaterialType.setParentid(6);
  			bMaterialType.setOrderStr("typeid");
  			List<BMaterialType> malist =materialTypeDao.findBmtList(bMaterialType);

  			//删除当前结算月结算数据
  			sMchTransmaterialDao.deleteBySetDate(setdate);
  			
  			
  		    //获取上个月的期初期末值
    		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
    		 
    		 Calendar cal = Calendar.getInstance();
	         cal.setTime(sdf.parse(setdate));
	         cal.add(Calendar.MONTH, -1);
            
             String sLastDate=sdf.format(cal.getTime()).toString();
            
      		 SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
    		 Date date1 = df2.parse(setdate+"-01 00:00:01"); 
     	
            
            //获取期初期末数据
            List<SMchTransmaterial> sMchTransList=sMchTransmaterialDao.findMchMaterialinfoList(sLastDate);
             
            Map<String,BigDecimal> lastMonthmap=new HashMap<String,BigDecimal>(); 
            //期初期末数据记录
            Map<String,String> mapData=new HashMap<String,String>(); 
            String ifCuscode="";
           
	    	//根据客户信息更新期初期末以及处理上月期末数据
	     	for (SMchTransmaterial sMchMaterialinfo : sMchTransList) 
	 		{
	     		lastMonthmap.put(sMchMaterialinfo.getTypeid()+"|"+sMchMaterialinfo.getDeptid()+"|"+sMchMaterialinfo.getMchcode(), sMchMaterialinfo.getEndNum())	;
//	     		//数据保存
//	     		if(sMchMaterialinfo.getMchcode().equals(ifCuscode)){
//	     			String s=mapData.get(sMchMaterialinfo.getMchcode());
//	     			s=s+"&"+sMchMaterialinfo.getTypeid()+"|"+sMchMaterialinfo.getEndNum()+"|"+sMchMaterialinfo.getDeptid()+"|"+sMchMaterialinfo.getTransferIn()+"|"+sMchMaterialinfo.getTransferOut();
//	     			mapData.put(sMchMaterialinfo.getMchcode(), s);
//	     		}else
//	     		{
//	     			mapData.put(sMchMaterialinfo.getMchcode(), sMchMaterialinfo.getTypeid()+"|"+sMchMaterialinfo.getEndNum()+"|"+sMchMaterialinfo.getDeptid()+"|"+sMchMaterialinfo.getTransferIn()+"|"+sMchMaterialinfo.getTransferOut());
//	     		}
//	     		ifCuscode=sMchMaterialinfo.getMchcode();
	 		}
	     	
	     	
	    	
	     	 List<STransferMoney> sTransMoney= new ArrayList<STransferMoney>();
	     	 
   		    //根据客户信息插入初值
    		for (MchBaseinfo mchInfo : mchList) 
			{
    			//根据客户号插入初始值 
    			List<SMchTransmaterial> smchm= new ArrayList<SMchTransmaterial>();
    			
    			
    			//根据物资信息增加客户各物资大类的初始化信息
    			for (BMaterialType nbMaterialType : malist) 
    			{
    				//获取本月期初值
    				String sMch= nbMaterialType.getTypeid()+"|"+mchInfo.getDeptid()+"|"+mchInfo.getMchcode();
    				
    				SMchTransmaterial nsMchTranMaterialinfo=new SMchTransmaterial();
    				
    				//根据上月的数据设置本月期初
    				if(lastMonthmap.get(sMch)!=null){
    					
    					nsMchTranMaterialinfo.setBeginNum(lastMonthmap.get(sMch));
    					nsMchTranMaterialinfo.setEndNum(lastMonthmap.get(sMch));
    					
    				}else
    				{
    					nsMchTranMaterialinfo.setBeginNum(new BigDecimal(0));
    					nsMchTranMaterialinfo.setEndNum(new BigDecimal(0));
    				}
    				nsMchTranMaterialinfo.setMchcode(mchInfo.getMchcode().toString());
    				nsMchTranMaterialinfo.setDeptid(Integer.parseInt(mchInfo.getDeptid().toString()));	
    				nsMchTranMaterialinfo.setTypeid(nbMaterialType.getTypeid());
					
    				nsMchTranMaterialinfo.setTransferIn(new BigDecimal(0));
    				nsMchTranMaterialinfo.setTransferOut(new BigDecimal(0));
    				nsMchTranMaterialinfo.setStockIn(new BigDecimal(0));
    				nsMchTranMaterialinfo.setStockOut(new BigDecimal(0));
    				nsMchTranMaterialinfo.setSaleNum(new BigDecimal(0));
    				nsMchTranMaterialinfo.setPurchaseNum(new BigDecimal(0));
    				nsMchTranMaterialinfo.setUnit(nbMaterialType.getAccountFlag());
    				nsMchTranMaterialinfo.setSettledate(setdate);
					
					smchm.add(nsMchTranMaterialinfo);	
    			}
    			
    			
    			
//				STransferMoney sTransferMoney=new STransferMoney();
//				sTransferMoney.setCuscode(customerInfo.getCuscode());
//				sTransferMoney.setDeptid(Integer.parseInt(customerInfo.getDeptid()));
//				sTransferMoney.setSettleMonth(setdate);
//				sTransferMoney.setTransferFee(new BigDecimal(0));
//				sTransferMoney.setRentFee(new BigDecimal(0));
//				sTransferMoney.setRepairFee(new BigDecimal(0));
//				sTransferMoney.setDueFee(new BigDecimal(0));
//				sTransferMoney.setTransportFee(new BigDecimal(0));
//				sTransferMoney.setOtherFee(new BigDecimal(0));
//				sTransferMoney.setTotalFee(new BigDecimal(0));
//				sTransferMoney.setZxFee(new BigDecimal(0));
//				sTransferMoney.setRedressFee(new BigDecimal(0));
//				sTransferMoney.setReceivedFee(new BigDecimal(0));
//				sTransferMoney.setStopFee(new BigDecimal(0));
				
				//sTransMoney.add(sTransferMoney);
				
    			 //初始化数据
    			sMchTransmaterialDao.batchCreate(smchm);
				
			}
    		
    		
     		
		    //获取交易汇总信息
	    	List<CStockTotalflow> cStockTotalflows=new ArrayList<CStockTotalflow>();
	    	    
	    	cStockTotalflows=stockTotalflowDao.findTotalMchTransferFlow(sDate,eDate);
	    	   
	    	
	        //根据客户信息插入初值
	     	for (CStockTotalflow cStockTotalflow : cStockTotalflows) 
	 		{
	     		saveMchInAndOut(malist,cStockTotalflow,setdate);
	 		}   		
    		
    		//初始化调拨费用表
    	   //sTransferMoneyDao.deleteBySetDate(setdate);
    	   
		   //sTransferMoneyDao.batchCreate(sTransMoney);
	     	
	     	
	     	
	     	
	     	
            
//    		//获取物资所有大类
//  			BMaterialType bMaterialType=new BMaterialType();
//  			bMaterialType.setParentid(6);
//  			bMaterialType.setOrderStr("typeid");
//  			List<BMaterialType> malist =materialTypeDao.findBmtList(bMaterialType);
//  
//  			//删除当前结算月结算数据
//  			sMchRentmaterialDao.deleteBySetDate(setdate);
  			
  			
  		  List<SMchMoney> sMchMoneyList= new ArrayList<SMchMoney>();
  			
  			
  			
    		//根据客户信息插入初值
     		for (MchBaseinfo mchBaseinfo : mchList) 
 			{
     			//根据客户号插入初始值 
     			//List<SMchRentmaterial> smchm= new ArrayList<SMchRentmaterial>();

     			SMchMoney sMchMoney=new SMchMoney();
     			sMchMoney.setMchcode(mchBaseinfo.getMchcode().toString());
     			sMchMoney.setSettleMonth(setdate);
     			sMchMoney.setTransferFee(new BigDecimal(0));
     			sMchMoney.setRentFee(new BigDecimal(0));
     			sMchMoney.setRepairFee(new BigDecimal(0));
     			sMchMoney.setDueFee(new BigDecimal(0));
     			sMchMoney.setTransportFee(new BigDecimal(0));
     			sMchMoney.setOtherFee(new BigDecimal(0));
     			sMchMoney.setTotalFee(new BigDecimal(0));
     			sMchMoney.setZxFee(new BigDecimal(0));
     			sMchMoney.setRedressFee(new BigDecimal(0));
     			sMchMoney.setReceivedFee(new BigDecimal(0));
     			sMchMoney.setStopFee(new BigDecimal(0));
     			sMchMoney.setDeptid(deptId);
     			sMchMoneyList.add(sMchMoney);
 			}
                //合同金额初始化
     		    sMchMoneyDao.deleteBySetDate(setdate);
     		    sMchMoneyDao.batchCreate(sMchMoneyList);
     		    
//				//获取交易汇总信息
//	    	    List<CStockTotalflow> cStockTotalflows=new ArrayList<CStockTotalflow>();
//	    	    
//	    	    cStockTotalflows=stockTotalflowDao.findTotalRentData(setdate);
//	    	    
//	    		//根据客户信息插入初值
//	     		for (CStockTotalflow cStockTotalflow : cStockTotalflows) 
//	 			{
//	     			//根据不同的商户号 不同的交易做不同的数据修改     			
//	     			if(cStockTotalflow.getOrderType().equals("13")){
//	     				//发料处理
//	     				saveSendAndReceive(malist,cStockTotalflow,setdate,1);
//	     				
//	     			}else if(cStockTotalflow.getOrderType().equals("14")){
//    			    	//收料处理
//	     				saveSendAndReceive(malist,cStockTotalflow,setdate,-1);
//	     			}
//	 			}
//	     		
    	} catch (Exception e) {
    		log.error("结算异常"+e.getMessage());
    		e.printStackTrace();
    		throw new BusinessException(e.getMessage());
        }
    }
    
    
    /**
     * 入驻客户费用计算
     * @param 
     */
    public void settleMchFee(String sDate,String eDate,Integer deptId) {

        
    	try {
    		   		

    		
    		String setdate=sDate.substring(0, 7);
    		
    		//根据调拨期初计算调拨费用
//    		List<StockTransTotalflowDto> beginFeeList=sMchMaterialinfoDao.findBeginFeeBySettleDate(setdate);
//    		
//    		
   		    Map<String,BigDecimal> beginFeemap=new HashMap<String,BigDecimal>(); 
//         
//	    	//根据客户信息更新期初期末以及处理上月期末数据
//	     	for (StockTransTotalflowDto stockTransTotalflowDto : beginFeeList) 
//	 		{
//	     		beginFeemap.put(stockTransTotalflowDto.getFromMchcode(), stockTransTotalflowDto.getBeginFee());
//	 		}
    		
    	
   		List<StockTransTotalflowDto> transferlist =stockTotalflowDao.findTotalMchTransferFee(sDate,eDate);
   		
   		List<SMchMoney> scList=new ArrayList<SMchMoney>();
   		
   		for (StockTransTotalflowDto stockTransTotalflowDto : transferlist) 
			{
   			//STransferMoney sTransferMoney=new STransferMoney();
   			SMchMoney sMchMoney=new SMchMoney();
   			
   			//将期初金额加到总费用
    			if(stockTransTotalflowDto.getOrderType().equals("32")){
    				//调拨调入处理
    				sMchMoney.setMchcode(stockTransTotalflowDto.getFromMchcode());
    				//sMchMoney.setTransferFee(stockTransTotalflowDto.getTotalFee().add(beginFeemap.get(stockTransTotalflowDto.getFromMchcode())));
    				sMchMoney.setTransferFee(stockTransTotalflowDto.getTotalFee());
    			}else{
			    	//调拨调出处理------期初只处理调入的费用
    				sMchMoney.setMchcode(stockTransTotalflowDto.getToMchcode());
    				sMchMoney.setTransferFee(stockTransTotalflowDto.getTotalFee().multiply(new BigDecimal(-1)));
    			}
   			
    			sMchMoney.setOtherFee(new BigDecimal(0));
    			sMchMoney.setZxFee(new BigDecimal(0));
    			sMchMoney.setTransportFee(new BigDecimal(0));
    			
    			
    			sMchMoney.setDeptid(deptId);
    			sMchMoney.setSettleMonth(setdate);
   			
    			scList.add(sMchMoney);
			}    		
    		
			//更新数据
			if(scList.size()>0)
			{
				sMchMoneyDao.batchUpdate(scList);
			}
    		
    		
    		//运输费杂费其他费用计算
    		
    		List<SMchMoney> otherFeeList=new ArrayList<SMchMoney>();
    		
    		List<StockTransTotalflowDto> transferOtherFeelist =stockTotalflowDao.findTotalMchOthrerFee(sDate,eDate);
    	    
    		for (StockTransTotalflowDto stockTransTotalflowDto : transferOtherFeelist) 
 			{
    			SMchMoney sMchMoney=new SMchMoney();
    			
    			//将期初金额加到总费用
     			if(stockTransTotalflowDto.getOrderType().equals("1")){
     				//项目发料处理
     				sMchMoney.setMchcode(stockTransTotalflowDto.getToMchcode());	
     			}else{
			    	//项目收料处理------期初只处理调入的费用
     				sMchMoney.setMchcode(stockTransTotalflowDto.getFromMchcode());
     			}
     			sMchMoney.setTransferFee(new BigDecimal(0));
     			sMchMoney.setRentFee(new BigDecimal(0));
     			
     			if(stockTransTotalflowDto.getTotalTransportFee()!=null){
     				sMchMoney.setTransportFee(stockTransTotalflowDto.getTotalTransportFee());
     			}
     			
     			
     			sMchMoney.setOtherFee(stockTransTotalflowDto.getTotalOtherFee());
     			sMchMoney.setZxFee(stockTransTotalflowDto.getTotalZxFee());
     			sMchMoney.setDeptid(deptId);
     			sMchMoney.setSettleMonth(setdate);
    			
     			otherFeeList.add(sMchMoney);
 			}
    		
    		
    		//更新数据
    		if(otherFeeList.size()>0)
    		{
    			sMchMoneyDao.batchUpdate(otherFeeList);
    		}
    		
    		
    	} catch (Exception e) {
    		log.error("结算异常"+e.getMessage());
    		e.printStackTrace();
    		throw new BusinessException(e.getMessage());
        }
    }
	
  
    
    /**
     * 收发料处理
     * @param 
     */
    private void saveSendAndReceive(List<BMaterialType> malist,CStockTotalflow cStockTotalflow,String setDate,Integer dataChang ){
    	
    	try
    	{
    		 String htCode=cStockTotalflow.getHtcode();
 	    	//String[] info=entry.getKey().split("\\|");
 	    	SMchRentmaterial sMchRentmaterial=new SMchRentmaterial();
 	    	sMchRentmaterial.setHtcode(htCode);
 	    	sMchRentmaterial.setTypeid(Integer.parseInt(cStockTotalflow.getTypeId()));
 	        if(dataChang==1)
 		    {
 	        	sMchRentmaterial.setSendNum(cStockTotalflow.getTotalAmt());
 	        	sMchRentmaterial.setReceiptNum(new BigDecimal(0));
 		    }else
 		    {
 		    	sMchRentmaterial.setSendNum(new BigDecimal(0));
 		    	sMchRentmaterial.setReceiptNum(cStockTotalflow.getTotalAmt());
 		    }
 	        sMchRentmaterial.setIndemnifyNum(new BigDecimal(0));
 	        sMchRentmaterial.setEndNum(cStockTotalflow.getTotalAmt().multiply(new BigDecimal(dataChang)));
 	        sMchRentmaterial.setDeptid(cStockTotalflow.getDeptid());
 	        sMchRentmaterial.setSettledate(setDate);
 	        
 	        sMchRentmaterialDao.update(sMchRentmaterial);
    		
     	} catch (Exception e) {
    		log.error("结算异常"+e.getMessage());
    		e.printStackTrace();
    		throw new BusinessException(e.getMessage());
        }
    	
    	
//    	//调入信息处理
//	    Map<String,BigDecimal> inmap=new HashMap<String,BigDecimal>(); 
//	    
//	   
//	    	    
//		inmap.put(malist.get(0).getTypeid()+"|"+mchCode,cStockTotalflow.getTotalGguan());
//		inmap.put(malist.get(1).getTypeid()+"|"+mchCode,cStockTotalflow.getTotalKjian());
//		inmap.put(malist.get(2).getTypeid()+"|"+mchCode,cStockTotalflow.getTotalTguan());
//		inmap.put(malist.get(3).getTypeid()+"|"+mchCode,cStockTotalflow.getTotalDguan());
//		inmap.put(malist.get(4).getTypeid()+"|"+mchCode,cStockTotalflow.getTotalLkjia());
//		inmap.put(malist.get(5).getTypeid()+"|"+mchCode,cStockTotalflow.getTotalCgang());
//		inmap.put(malist.get(6).getTypeid()+"|"+mchCode,cStockTotalflow.getTotalTwgzgang());
//		inmap.put(malist.get(7).getTypeid()+"|"+mchCode,cStockTotalflow.getTotalGzgang());
//		
//    	
//	    List<SMchRentmaterial> sList= new ArrayList<SMchRentmaterial>();
//	    
//	    for(Map.Entry<String,BigDecimal> entry:inmap.entrySet()){  
//	    	
//	    	String[] info=entry.getKey().split("\\|");
//	    	SMchRentmaterial sMchRentmaterial=new SMchRentmaterial();
//	    	sMchRentmaterial.setHtcode(info[1]);
//	    	sMchRentmaterial.setTypeid(Integer.parseInt(info[0]));
//	    	
//	        if(dataChang==1)
//		    {
//	        	sMchRentmaterial.setSendNum(entry.getValue());
//	        	sMchRentmaterial.setReceiptNum(new BigDecimal(0));
//		    }else
//		    {
//		    	sMchRentmaterial.setSendNum(new BigDecimal(0));
//		    	sMchRentmaterial.setReceiptNum(entry.getValue());
//		    }
//	        sMchRentmaterial.setIndemnifyNum(new BigDecimal(0));
//	        sMchRentmaterial.setEndNum(entry.getValue().multiply(new BigDecimal(dataChang)));
//	        sMchRentmaterial.setDeptid(cStockTotalflow.getDeptid());
//	        sMchRentmaterial.setSettledate(setDate);
//	    	sList.add(sMchRentmaterial);   
//	   }  
//	    
//	    //批量修改外部调入交易数据
//	    if(sList.size()>0)
//	    {
//	    	sMchRentmaterialDao.batchUpdate(sList);
//	    }
    	
    }
    
}