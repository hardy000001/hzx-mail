package com.lq.lss.core.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.BusHtDao;
import com.lq.lss.core.dao.BusPurDao;
import com.lq.lss.core.dao.BusPurDetailDao;
import com.lq.lss.core.dao.BusSaleDao;
import com.lq.lss.core.dao.BusSaleDetailDao;
import com.lq.lss.core.dao.CStockCompensateDao;
import com.lq.lss.core.dao.CStockCompensateDetailDao;
import com.lq.lss.core.dao.CStockFlowDao;
import com.lq.lss.core.dao.CStockRemodelingDao;
import com.lq.lss.core.dao.CStockRemodelingDetailDao;
import com.lq.lss.core.dao.MaterialTypeDao;
import com.lq.lss.core.dao.StockCenterTransferDao;
import com.lq.lss.core.dao.StockCenterTransferDetailDao;
import com.lq.lss.core.dao.StockInDao;
import com.lq.lss.core.dao.StockInDetailDao;
import com.lq.lss.core.dao.StockInfoDao;
import com.lq.lss.core.dao.StockOutDao;
import com.lq.lss.core.dao.StockOutDetailDao;
import com.lq.lss.core.dao.StockReceiptDao;
import com.lq.lss.core.dao.StockReceiptDetailDao;
import com.lq.lss.core.dao.StockSendDao;
import com.lq.lss.core.dao.StockSendDetailDao;
import com.lq.lss.core.dao.StockTemporaryDao;
import com.lq.lss.core.dao.StockTemporaryDetailDao;
import com.lq.lss.core.dao.StockTotalflowDao;
import com.lq.lss.core.dao.StockTransferDao;
import com.lq.lss.core.dao.StockTransferDetailDao;
import com.lq.lss.core.service.CStockFlowService;
import com.lq.lss.dto.CStockFlowDto;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.BMaterialType;
import com.lq.lss.model.CBusHt;
import com.lq.lss.model.CBusPur;
import com.lq.lss.model.CBusPurDetail;
import com.lq.lss.model.CBusSale;
import com.lq.lss.model.CBusSaleDetail;
import com.lq.lss.model.CStockCenterTransfer;
import com.lq.lss.model.CStockCenterTransferDetail;
import com.lq.lss.model.CStockCompensate;
import com.lq.lss.model.CStockCompensateDetail;
import com.lq.lss.model.CStockFlow;
import com.lq.lss.model.CStockIn;
import com.lq.lss.model.CStockInDetail;
import com.lq.lss.model.CStockInfo;
import com.lq.lss.model.CStockOut;
import com.lq.lss.model.CStockOutDetail;
import com.lq.lss.model.CStockReceipt;
import com.lq.lss.model.CStockReceiptDetail;
import com.lq.lss.model.CStockRemodeling;
import com.lq.lss.model.CStockRemodelingDetail;
import com.lq.lss.model.CStockSend;
import com.lq.lss.model.CStockSendDetail;
import com.lq.lss.model.CStockTemporary;
import com.lq.lss.model.CStockTemporaryDetail;
import com.lq.lss.model.CStockTotalflow;
import com.lq.lss.model.CStockTransfer;
import com.lq.lss.model.CStockTransferDetail;
import com.lq.lss.utils.SystemConst;
import com.lq.lss.utils.exception.jta.BusinessException;

/**
 *
 * @author  作者: Eric
 * @date 创建时间: 2016-10-08 09:26:28
 */
@Service
public class CStockFlowServiceImpl extends EasyUIServiceBase<CStockFlow, String, CStockFlowDao> implements CStockFlowService{

    @Resource
	private CStockFlowDao cStockFlowDao;
    
    @Resource
	private StockInDetailDao stockInDetailDao;
    @Resource
	private StockOutDetailDao stockOutDetailDao;
    
    @Resource
	private StockInDao stockInDao;
    
    @Resource
	private StockInfoDao stockInfoDao;
    @Resource
   	private StockOutDao stockOutDao;
    @Resource
   	private BusPurDao busPurDao;
    @Resource
	private BusPurDetailDao busPurDetailDao;
    @Resource
   	private BusSaleDao busSaleDao;
    @Resource
	private BusSaleDetailDao busSaleDetailDao;
    @Resource
   	private StockTotalflowDao stockTotalflowDao;
    
    
    @Resource
	StockTemporaryDao cstdao;
	@Resource
	StockTemporaryDetailDao stdedao;
	@Resource
	CStockRemodelingDetailDao srddao;
	@Resource
	CStockRemodelingDao cstockRemodelingDao;
	@Resource
	StockCenterTransferDetailDao centerTransferDetailDao;
	@Resource
	StockCenterTransferDao centerTransferDao;
	
	@Resource
	private StockSendDao stockSendDao;
	@Resource
	private StockSendDetailDao  stockSendDetailDao;
	@Resource
	private StockReceiptDao stockReceiptDao;
	@Resource
	private StockReceiptDetailDao  stockReceiptDetailDao;
	@Resource
	private MaterialTypeDao materialTypeDao;
	@Resource
	private BusHtDao busHtDao;
	@Resource
    private StockTransferDetailDao stockTransferDetailDao;
	@Resource
    private StockTransferDao stockTransferlDao;
	@Resource
	private CStockCompensateDao cStockCompensateDao;
	@Resource
	private CStockCompensateDetailDao cStockCompensateDetailDao;
	
	
	@Override
	public  ResultDto<String> saveStockFlowRdTx(TradeType tradeType,String orderNo) throws BusinessException{ 
		
		    ResultDto<String> result=null;
		    boolean isAddTotal=true;    //是否添加汇总
			//*根据不同的交易做不同的交易流水处理
		  	try {
		  		switch ( tradeType )
				{	
		  		
					case STOCK_TEMPORARY_IN: 
			    	result=saveStockTemporaryInFlow(orderNo);
			       break;
				    case STOCK_IN: 
				    	result=saveStockInFlow(orderNo);
				       break;
				    case ANTI_AUDIT_STOCK_IN: 
				    	result=saveAntiStockInFlow(orderNo);
				    	isAddTotal=false;
				       break;
				    case STOCK_OUT: 
				    	result=saveStockOutFlow(orderNo);
				        break;
				    case STOCK_TEMPORARY_OUT: 
				    	result=saveStockTemporaryOutFlow(orderNo);
				        break;    
				    case ANTI_STOCK_OUT: 
				    	result=saveAntiStockOutFlow(orderNo);
				    	isAddTotal=false;
				       break;
				    case STOCK_CG: 
				    	result=saveBusPurFlow(orderNo);
					       break;
				    case STOCK_XS: 
				    	result=saveBusSaleFlow(orderNo);
					       break;
				    case OTHER_IN: 
				     
				       break;
				    case REMODELING: 
				    	result=saveStockRemodelingFlow(orderNo);  
					       break;       
				    case TEMPORARY_IN: 
				    	result=saveStockTemporaryFlow(orderNo);  
					       break; 
				    case STOCKCENTERTRANSFER_OUT: 
				    	result=saveStockCenterTransferFlow(orderNo);  
					       break;
				    case STOCKCENTERTRANSFER_IN: 
				    	result=saveStockCenterTransferInFlow(orderNo);  
					       break;
				    case STOCK_SEND: 
				    	result=saveStockSendInFlow(orderNo);  
					       break;
				    case ANTI_AUDIT_STOCK_SEND: 
				    	result=saveAntiStockSendFlow(orderNo);
				    	isAddTotal=false;
				       break;       
				    case STOCK_RECEIPT: 
				    	result=saveStockReceiptFlow(orderNo);  
					       break;
				    case ANTI_STOCK_RECEIPT: 
				    	result=saveAntiStockReceiptFlow(orderNo);  
				    	isAddTotal=false;
					       break;
				    case TRANSFER_MUTUAL: 
				    	result=saveStockTransferFlow(orderNo);  
					       break;
				    case STOCK_COMPENSATE: 
				    	result=saveCStockCompensateFlow(orderNo);  
					       break;
				    case STOCK_TRANSFER_OUT: 
				    	result=saveStockTransferOutFlow(orderNo);  
					       break;
				    case STOCK_TRANSFER_IN: 
				    	result=saveStockTransferInFlow(orderNo);  
					       break;
				    case ANTI_STOCK_TRANSFER_OUT: 
				    	result=saveAntiStockTransferOutFlow(orderNo); 
				    	isAddTotal=false;
					       break;
				    case ANTI_STOCK_TRANSFER_IN: 
				    	result=saveAntiStockTransferInFlow(orderNo); 
				    	isAddTotal=false;
					       break;
				    case ANTI_STOCK_TEMPORARY_IN: 
				    	result=saveAntiStockTemporaryInFlow(orderNo);
				    	isAddTotal=false;   
				    case ANTI_STOCK_TEMPORARY_OUT: 
				    	result=saveAntiStockTemporaryOutFlow(orderNo);
				    	isAddTotal=false;  
				    case ANTI_STOCK_CENTERTRANSFER_IN: 
				    	result=saveAntiStockCentertransferInFlow(orderNo);
				    	isAddTotal=false;  
				    case ANTI_STOCK_CENTERTRANSFER_OUT: 
				    	result=saveAntiStockCentertransferOutFlow(orderNo);
				    	isAddTotal=false;  
				   default:
				}
				
				if(result.isSuccess()&& isAddTotal){
					  //新增一个业务需要在该方法中也对应修改
					  saveTotalFlow(orderNo,tradeType);
				}
				
				result=new ResultDto<String>(true,"保存成功");
				
		    } catch (Exception e) {
				log.error("数据保存失败"+e.getMessage());
				e.printStackTrace();
				result=new ResultDto<String>(false,e.getMessage());
				throw new BusinessException(e.getMessage());
		    }
		    
			
	    return result;
	}
private ResultDto<String> saveAntiStockCentertransferOutFlow(String orderNo) {
		// TODO Auto-generated method stub
	ResultDto<String> result=null;
	try{
		CStockCenterTransfer  csr=centerTransferDao.findByCStockCenterTransfer(orderNo);
		List<CStockCenterTransferDetail> cStockCenterTransferDetails=centerTransferDetailDao.findByCStockCenterTransferDetail(orderNo);

    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
    if(cStockCenterTransferDetails.size()>0)
    {
    	
    	
    	cStockFlowDao.deleteById(orderNo);
		 log.debug("=========库存流水删除成功=======");  
		stockTotalflowDao.deleteById(orderNo);
		 log.debug("=========中心财务汇总流水表删除成功=======");  
    	for (CStockCenterTransferDetail cStockCenterTransferDetail : cStockCenterTransferDetails) 
    		{
		   		
		    	 //库存汇总更新
		    	 CStockInfo cStockInfo=new CStockInfo();
		    	 
		    	 cStockInfo.setDeptid(csr.getFromDeptid());
		    	 cStockInfo.setMchcode(csr.getFromMchcode());
		    	 cStockInfo.setMaterialcode(cStockCenterTransferDetail.getMaterialcode());  	
		    	 cStockInfo.setTotalM(new BigDecimal(0));
		    	 cStockInfo.setTotalT(new BigDecimal(0));
		    	 cStockInfo.setTotalS(cStockCenterTransferDetail.getTotalS());
		    	 cStockInfo.setModifytime(csr.getCreatetime());
		    	 cStockInfo.setChangeType(+1);
		    	 cStockInfo.setIsHave("1");
		    	 cStockInfo.setTransferTotalS(new BigDecimal(0));
			    
		    	
		    	 cStockInfos.add(cStockInfo);	 
    		}
    	
	     //库存汇总更新
	     stockInfoDao.batchUpdate(cStockInfos);
	     log.debug("=========库存汇总数据保存成功======="); 
	     
    	
	    }
    result=new ResultDto<String>(true,"数据保存成功");
	
   	} catch (NumberFormatException e) {
   		log.error("交易流水数据转化异常"+e.getMessage());
   		e.printStackTrace();
   		throw new BusinessException(e.getMessage());
   	} catch (Exception e) {
   			log.error("交易流水数据保存异常"+e.getMessage());
   			e.printStackTrace();
   			throw new BusinessException(e.getMessage());
   	}
   	
   	return result;
	}
private ResultDto<String> saveAntiStockCentertransferInFlow(String orderNo) {
		// TODO Auto-generated method stub
	ResultDto<String> result=null;
	try{
		CStockCenterTransfer  csr=centerTransferDao.findByCStockCenterTransfer(orderNo);
		List<CStockCenterTransferDetail> cStockCenterTransferDetails=centerTransferDetailDao.findByCStockCenterTransferDetail(orderNo);

    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
    if(cStockCenterTransferDetails.size()>0)
    {
    	
    	
    	cStockFlowDao.deleteById(orderNo);
		 log.debug("=========库存流水删除成功=======");  
		stockTotalflowDao.deleteById(orderNo);
		 log.debug("=========中心财务汇总流水表删除成功=======");  
    	for (CStockCenterTransferDetail cStockCenterTransferDetail : cStockCenterTransferDetails) 
    		{
		   		
		    	 //库存汇总更新
		    	 CStockInfo cStockInfo=new CStockInfo();
		    	 
		    	 cStockInfo.setDeptid(csr.getFromDeptid());
		    	 cStockInfo.setMchcode(csr.getToMchcode());
		    	 cStockInfo.setMaterialcode(cStockCenterTransferDetail.getMaterialcode());  	
		    	 cStockInfo.setTotalM(new BigDecimal(0));
		    	 cStockInfo.setTotalT(new BigDecimal(0));
		    	 cStockInfo.setTotalS(cStockCenterTransferDetail.getTotalS());
		    	 cStockInfo.setModifytime(csr.getCreatetime());
		    	 cStockInfo.setChangeType(-1);
		    	 cStockInfo.setIsHave("1");
		    	 cStockInfo.setTransferTotalS(new BigDecimal(0));
			    
		    	
		    	 cStockInfos.add(cStockInfo);	 
    		}
    	
	     //库存汇总更新
	     stockInfoDao.batchUpdate(cStockInfos);
	     log.debug("=========库存汇总数据保存成功======="); 
	     
    	
	    }
    result=new ResultDto<String>(true,"数据保存成功");
	
   	} catch (NumberFormatException e) {
   		log.error("交易流水数据转化异常"+e.getMessage());
   		e.printStackTrace();
   		throw new BusinessException(e.getMessage());
   	} catch (Exception e) {
   			log.error("交易流水数据保存异常"+e.getMessage());
   			e.printStackTrace();
   			throw new BusinessException(e.getMessage());
   	}
   	
   	return result;
	}
private ResultDto<String> saveAntiStockTemporaryOutFlow(String orderNo) {
		// TODO Auto-generated method stub
ResultDto<String> result=null;
    
	try {

		//获取订单信息
		CStockOut cStockOut =stockOutDao.findById(orderNo);
		
		List<CStockOutDetail> cStockOutDetails=stockOutDetailDao.queryStockOutListById(orderNo);
		//
	 
	    
	    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
		
		if(cStockOutDetails.size()>0)
		{
			
			cStockFlowDao.deleteById(orderNo);
			 log.debug("=========库存流水删除成功=======");  
			stockTotalflowDao.deleteById(orderNo);
			 log.debug("=========中心财务汇总流水表删除成功=======");  
             for (CStockOutDetail cStockOutDetail : cStockOutDetails) 
             {
          
            	 //库存汇总更新
            	 CStockInfo cStockInfo=new CStockInfo();
            	 
            	 cStockInfo.setDeptid(cStockOut.getDeptid());
            	 cStockInfo.setMchcode(cStockOut.getMchcode());
            	 cStockInfo.setMaterialcode(cStockOutDetail.getMaterialcode());
            	 cStockInfo.setTotalS(cStockOutDetail.getTotalS());
            	 cStockInfo.setTotalM(new BigDecimal(0));
            	 cStockInfo.setTotalT(new BigDecimal(0));
            	 cStockInfo.setModifytime(cStockOut.getCreatetime());
            	 cStockInfo.setChangeType(+1);
            	 cStockInfo.setIsHave("1");
            	 cStockInfo.setTransferTotalS(new BigDecimal(0));
            	 cStockInfos.add(cStockInfo);	 
            	 
			 }  
		     
		     //库存汇总更新
		     stockInfoDao.batchUpdate(cStockInfos);
		     log.debug("=========库存汇总数据保存成功=======");    
		     
		}
		
		result=new ResultDto<String>(true,"数据保存成功");
				
	} catch (NumberFormatException e) {
					log.error("交易流水数据转化异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
	} catch (Exception e) {
					log.error("交易流水数据保存异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
    }
	
    return result;
	}
private ResultDto<String> saveAntiStockTemporaryInFlow(String orderNo) {
		// TODO Auto-generated method stub
ResultDto<String> result=null;
    
	try {

		//获取订单信息
		CStockIn cStockIn =stockInDao.findById(orderNo);
		
		List<CStockInDetail> cStockInDetails=stockInDetailDao.queryStockInDetailListById(orderNo);
		//
	 
	    
	    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
		
		if(cStockInDetails.size()>0)
		{
			
			cStockFlowDao.deleteById(orderNo);
			 log.debug("=========库存流水删除成功=======");  
			stockTotalflowDao.deleteById(orderNo);
			 log.debug("=========中心财务汇总流水表删除成功=======");  
             for (CStockInDetail cStockInDetail : cStockInDetails) 
             {
          
            	 //库存汇总更新
            	 CStockInfo cStockInfo=new CStockInfo();
            	 
            	 cStockInfo.setDeptid(cStockIn.getDeptid());
            	 cStockInfo.setMchcode(cStockIn.getMchcode());
            	 cStockInfo.setMaterialcode(cStockInDetail.getMaterialCode());
            	 cStockInfo.setTotalS(cStockInDetail.getTotalS());
            	 cStockInfo.setTotalM(new BigDecimal(0));
            	 cStockInfo.setTotalT(new BigDecimal(0));
            	 cStockInfo.setModifytime(cStockIn.getCreatetime());
            	 cStockInfo.setChangeType(-1);
            	 cStockInfo.setIsHave("1");
            	 cStockInfo.setTransferTotalS(new BigDecimal(0));
            	 cStockInfos.add(cStockInfo);	 
            	 
			 }  
		     
		     //库存汇总更新
		     stockInfoDao.batchUpdate(cStockInfos);
		     log.debug("=========库存汇总数据保存成功=======");    
		     
		}
		
		result=new ResultDto<String>(true,"数据保存成功");
				
	} catch (NumberFormatException e) {
					log.error("交易流水数据转化异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
	} catch (Exception e) {
					log.error("交易流水数据保存异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
    }
	
    return result;
	}
private ResultDto<String> saveAntiStockSendFlow(String orderNo) {
		// TODO Auto-generated method stub
	ResultDto<String> result=null;
	try{
	CStockSend  cStockSend=stockSendDao.findById(orderNo);
	List<CStockSendDetail> cStockSendDetails=stockSendDetailDao.findByCStockSendDetail(orderNo);


    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
    if(cStockSendDetails.size()>0)
    {
    	
    	
    	cStockFlowDao.deleteById(orderNo);
		 log.debug("=========库存流水删除成功=======");  
		stockTotalflowDao.deleteById(orderNo);
		 log.debug("=========中心财务汇总流水表删除成功=======");  
    	for (CStockSendDetail stockSendDetail : cStockSendDetails) 
    		{
		   		
		    	 //库存汇总更新
		    	 CStockInfo cStockInfo=new CStockInfo();
		    	 
		    	 cStockInfo.setDeptid(cStockSend.getDeptid());
		    	 cStockInfo.setMchcode(cStockSend.getMchcode());
		    	 cStockInfo.setMaterialcode(stockSendDetail.getMaterialcode());  	
		    	 cStockInfo.setTotalM(new BigDecimal(0));
		    	 cStockInfo.setTotalT(new BigDecimal(0));
		    	 cStockInfo.setTotalS(stockSendDetail.getTotalS());
		    	 cStockInfo.setModifytime(cStockSend.getCreatetime());
		    	 cStockInfo.setChangeType(+1);
		    	 cStockInfo.setIsHave("1");
		    	 cStockInfo.setTransferTotalS(new BigDecimal(0));
			    
		    	
		    	 cStockInfos.add(cStockInfo);	 
    		}
    	
	     //库存汇总更新
	     stockInfoDao.batchUpdate(cStockInfos);
	     log.debug("=========库存汇总数据保存成功======="); 
	     
    	
	    }
    result=new ResultDto<String>(true,"数据保存成功");
	
   	} catch (NumberFormatException e) {
   		log.error("交易流水数据转化异常"+e.getMessage());
   		e.printStackTrace();
   		throw new BusinessException(e.getMessage());
   	} catch (Exception e) {
   			log.error("交易流水数据保存异常"+e.getMessage());
   			e.printStackTrace();
   			throw new BusinessException(e.getMessage());
   	}
   	
   	return result;
	}
private ResultDto<String> saveStockTemporaryOutFlow(String orderNo) {
		// TODO Auto-generated method stub
ResultDto<String> result=null;
    
	try {

		//获取订单信息
		CStockOut cStockOut =stockOutDao.findById(orderNo);
		
		List<CStockOutDetail> cStockOutDetails=stockOutDetailDao.queryStockOutListById(orderNo);
		//
	    List<CStockFlow> cStockFlows=new ArrayList<CStockFlow>();
	    
	    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
		
		if(cStockOutDetails.size()>0)
		{
             for (CStockOutDetail cStockOutDetail : cStockOutDetails) 
             {
            	 //库存流水减少
            	 CStockFlow cStockFlow=new CStockFlow();
            	 cStockFlow.setMaterialcode(cStockOutDetail.getMaterialcode());
            	 cStockFlow.setAmt(cStockOutDetail.getTotalS());
            	 cStockFlow.setMchcode(cStockOut.getMchcode());
            	 cStockFlow.setChangeType("-1");
            	 cStockFlow.setCreateoper(cStockOut.getCreateoper());
            	 cStockFlow.setCreattime(cStockOut.getCreatetime());
            	 cStockFlow.setDeptid(cStockOut.getDeptid());
            	 cStockFlow.setOrderNo(cStockOut.getOutSerialno());
            	 cStockFlow.setOrderType(String.valueOf(TradeType.STOCK_TEMPORARY_OUT.getType()));
            	 cStockFlows.add(cStockFlow);
            	 //库存汇总更新
            	 CStockInfo cStockInfo=new CStockInfo();
            	 
            	 cStockInfo.setDeptid(cStockOut.getDeptid());
            	 cStockInfo.setMchcode(cStockOut.getMchcode());
            	 cStockInfo.setMaterialcode(cStockOutDetail.getMaterialcode());
            	 cStockInfo.setTotalS(cStockOutDetail.getTotalS());
            	 cStockInfo.setTotalM(new BigDecimal(0));
            	 cStockInfo.setTotalT(new BigDecimal(0));
            	 cStockInfo.setModifytime(cStockOut.getCreatetime());
            	 cStockInfo.setChangeType(-1);
            	 cStockInfo.setIsHave("1");
            	 cStockInfo.setTransferTotalS(new BigDecimal(0));
            	 cStockInfos.add(cStockInfo);	 
            	 
			 }
     		 //库存流水增加
             cStockFlowDao.batchCreate(cStockFlows);
		     log.debug("=========库存流水数据保存成功=======");    
		     
		     //库存汇总更新
		     stockInfoDao.batchUpdate(cStockInfos);
		     log.debug("=========库存汇总数据保存成功=======");    
		     result=new ResultDto<String>(true,"数据保存成功");
		     
		}
				
	} catch (NumberFormatException e) {
					log.error("交易流水数据转化异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
	} catch (Exception e) {
					log.error("交易流水数据保存异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
    }
	
    return result;
	}
private ResultDto<String> saveStockTemporaryInFlow(String orderNo) {
		// TODO Auto-generated method stub
ResultDto<String> result=null;
    
	try {

		//获取订单信息
		CStockIn cStockIn =stockInDao.findById(orderNo);
		
		List<CStockInDetail> cStockInDetails=stockInDetailDao.queryStockInDetailListById(orderNo);
		//
	    List<CStockFlow> cStockFlows=new ArrayList<CStockFlow>();
	    
	    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
		
		if(cStockInDetails.size()>0)
		{
             for (CStockInDetail cStockInDetail : cStockInDetails) 
             {
            	 //库存流水增加
            	 CStockFlow cStockFlow=new CStockFlow();
            	 cStockFlow.setMaterialcode(cStockInDetail.getMaterialCode());
            	 cStockFlow.setAmt(cStockInDetail.getTotalS());
            	 cStockFlow.setMchcode(cStockIn.getMchcode());
            	 cStockFlow.setChangeType("1");
            	 cStockFlow.setCreateoper(cStockIn.getCreateoper());
            	 cStockFlow.setCreattime(cStockIn.getCreatetime());
            	 cStockFlow.setDeptid(cStockIn.getDeptid());
            	 cStockFlow.setOrderNo(cStockIn.getInSerialno());
            	 cStockFlow.setOrderType(String.valueOf(TradeType.STOCK_TEMPORARY_IN.getType()));
            	 cStockFlows.add(cStockFlow);
            	 //库存汇总更新
            	 CStockInfo cStockInfo=new CStockInfo();
            	 
            	 cStockInfo.setDeptid(cStockIn.getDeptid());
            	 cStockInfo.setMchcode(cStockIn.getMchcode());
            	 cStockInfo.setMaterialcode(cStockInDetail.getMaterialCode());
            	 cStockInfo.setTotalS(cStockInDetail.getTotalS());
            	 cStockInfo.setTotalM(new BigDecimal(0));
            	 cStockInfo.setTotalT(new BigDecimal(0));
            	 cStockInfo.setModifytime(cStockIn.getCreatetime());
            	 cStockInfo.setChangeType(+1);
            	 cStockInfo.setIsHave("1");
            	 cStockInfo.setTransferTotalS(new BigDecimal(0));
            	 cStockInfos.add(cStockInfo);	 
            	 
			 }
     		 //库存流水增加
             cStockFlowDao.batchCreate(cStockFlows);
		     log.debug("=========库存流水数据保存成功=======");    
		     
		     //库存汇总更新
		     stockInfoDao.batchUpdate(cStockInfos);
		     log.debug("=========库存汇总数据保存成功=======");    
		     
		}
		
		result=new ResultDto<String>(true,"数据保存成功");
				
	} catch (NumberFormatException e) {
					log.error("交易流水数据转化异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
	} catch (Exception e) {
					log.error("交易流水数据保存异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
    }
	
    return result;
	}
private ResultDto<String> saveAntiStockTransferInFlow(String orderNo) {
		// TODO Auto-generated method stub
ResultDto<String> result=null;
    
	try {

		//获取订单信息
		CStockTransfer  cStockTransfer=stockTransferlDao.findById(orderNo);
		
		List<CStockTransferDetail> cStockTransferDetails=(List<CStockTransferDetail>) stockTransferDetailDao.queryDetailList(orderNo);
	
	 System.out.println("cStockTransferDetails.size()"+cStockTransferDetails.size());
	    
	    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
	    List<CStockInfo> cStockInfos1=new ArrayList<CStockInfo>();
		
		if(cStockTransferDetails.size()>0)
		{
			
			cStockFlowDao.deleteById(orderNo);
			 log.debug("=========库存流水删除成功=======");  
			stockTotalflowDao.deleteById(orderNo);
			 log.debug("=========中心财务汇总流水表删除成功=======");  
			 for (CStockTransferDetail cStockTransferDetail : cStockTransferDetails) 
	    		{
			   		
			    	 CStockInfo cStockInfo=new CStockInfo();
			    	 
			    	 cStockInfo.setDeptid(cStockTransfer.getFromDeptid());
			    	 cStockInfo.setMchcode(cStockTransfer.getFromMchcode());
			    	 cStockInfo.setMaterialcode(cStockTransferDetail.getMaterialCode());  	
			    	 cStockInfo.setTotalM(new BigDecimal(0));
			    	 cStockInfo.setTotalT(new BigDecimal(0));
			    	 cStockInfo.setTotalS(cStockTransferDetail.getTotalS());
			    	 cStockInfo.setTransferTotalS(cStockTransferDetail.getTotalS());
			    	 cStockInfo.setModifytime(cStockTransfer.getCreatetime());
			    	 cStockInfo.setChangeType(+1);
				    
			    	 cStockInfos.add(cStockInfo);	 
	    		}
	    	
			 for (CStockTransferDetail cStockTransferDetail : cStockTransferDetails) 
     		{
 		   		
 		    	 CStockInfo cStockInfo=new CStockInfo();
 		    	 
 		    	 cStockInfo.setDeptid(cStockTransfer.getFromDeptid());
 		    	 cStockInfo.setMchcode("100000");
 		    	 cStockInfo.setMaterialcode(cStockTransferDetail.getMaterialCode());  	
 		    	 cStockInfo.setTotalM(new BigDecimal(0));
 		    	 cStockInfo.setTotalT(new BigDecimal(0));
 		    	 cStockInfo.setTotalS(cStockTransferDetail.getTotalS());
 		    	 cStockInfo.setTransferTotalS(cStockTransferDetail.getTotalS());
 		    	 cStockInfo.setModifytime(cStockTransfer.getCreatetime());
 		    	 cStockInfo.setChangeType(-1);
 		   
 			    
 		    	
 		    	 cStockInfos1.add(cStockInfo);	 
     		}
		     
		     //库存汇总更新
		     stockInfoDao.batchUpdate(cStockInfos);
		     stockInfoDao.batchUpdate(cStockInfos1);
		     log.debug("=========库存汇总数据保存成功=======");    
		     
		}
		
		result=new ResultDto<String>(true,"数据保存成功");
				
	} catch (NumberFormatException e) {
					log.error("交易流水数据转化异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
	} catch (Exception e) {
					log.error("交易流水数据保存异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
    }
	
    return result;
	}
private ResultDto<String> saveAntiStockTransferOutFlow(String orderNo) {
		// TODO Auto-generated method stub
ResultDto<String> result=null;
    
	try {

		//获取订单信息
		CStockTransfer  cStockTransfer=stockTransferlDao.findById(orderNo);
		
		List<CStockTransferDetail> cStockTransferDetails=(List<CStockTransferDetail>) stockTransferDetailDao.queryDetailList(orderNo);
	
	 System.out.println("cStockTransferDetails.size()"+cStockTransferDetails.size());
	    
	    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
	    List<CStockInfo> cStockInfos1=new ArrayList<CStockInfo>();
		
		if(cStockTransferDetails.size()>0)
		{
			
			cStockFlowDao.deleteById(orderNo);
			 log.debug("=========库存流水删除成功=======");  
			stockTotalflowDao.deleteById(orderNo);
			 log.debug("=========中心财务汇总流水表删除成功=======");  
			 for (CStockTransferDetail cStockTransferDetail : cStockTransferDetails) 
	    		{
			   		
			    	 CStockInfo cStockInfo=new CStockInfo();
			    	 
			    	 cStockInfo.setDeptid(cStockTransfer.getFromDeptid());
			    	 cStockInfo.setMchcode(cStockTransfer.getToMchcode());
			    	 cStockInfo.setMaterialcode(cStockTransferDetail.getMaterialCode());  	
			    	 cStockInfo.setTotalM(new BigDecimal(0));
			    	 cStockInfo.setTotalT(new BigDecimal(0));
			    	 cStockInfo.setTotalS(cStockTransferDetail.getTotalS());
			    	 cStockInfo.setTransferTotalS(cStockTransferDetail.getTotalS());
			    	 cStockInfo.setModifytime(cStockTransfer.getCreatetime());
			    	 cStockInfo.setChangeType(-1);
				    
			    	 cStockInfos.add(cStockInfo);	 
	    		}
	    	
			 for (CStockTransferDetail cStockTransferDetail : cStockTransferDetails) 
     		{
 		   		
 		    	 CStockInfo cStockInfo=new CStockInfo();
 		    	 
 		    	 cStockInfo.setDeptid(cStockTransfer.getFromDeptid());
 		    	 cStockInfo.setMchcode("100000");
 		    	 cStockInfo.setMaterialcode(cStockTransferDetail.getMaterialCode());  	
 		    	 cStockInfo.setTotalM(new BigDecimal(0));
 		    	 cStockInfo.setTotalT(new BigDecimal(0));
 		    	 cStockInfo.setTotalS(cStockTransferDetail.getTotalS());
 		    	 cStockInfo.setTransferTotalS(cStockTransferDetail.getTotalS());
 		    	 cStockInfo.setModifytime(cStockTransfer.getCreatetime());
 		    	 cStockInfo.setChangeType(+1);
 		   
 			    
 		    	
 		    	 cStockInfos1.add(cStockInfo);	 
     		}
		     
		     //库存汇总更新
		     stockInfoDao.batchUpdate(cStockInfos);
		     stockInfoDao.batchUpdate(cStockInfos1);
		     log.debug("=========库存汇总数据保存成功=======");    
		     
		}
		
		result=new ResultDto<String>(true,"数据保存成功");
				
	} catch (NumberFormatException e) {
					log.error("交易流水数据转化异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
	} catch (Exception e) {
					log.error("交易流水数据保存异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
    }
	
    return result;
	}
private ResultDto<String> saveStockTransferInFlow(String orderNo) {
		// TODO Auto-generated method stub
	ResultDto<String> result=null;
	try{
		CStockTransfer  csr=stockTransferlDao.findById(orderNo);
	List<CStockTransferDetail> cStockTransferDetails=(List<CStockTransferDetail>) stockTransferDetailDao.queryDetailList(orderNo);
	List<CStockFlow> cStockFlows=new ArrayList<CStockFlow>();
	List<CStockFlow> cStockFlows1=new ArrayList<CStockFlow>();
    
    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
    List<CStockInfo> cStockInfos1=new ArrayList<CStockInfo>();
    if(cStockTransferDetails.size()>0)
    {
    	for (CStockTransferDetail cStockTransferDetail : cStockTransferDetails) 
    		{
		   		 CStockFlow cStockFlow=new CStockFlow();
		    	 cStockFlow.setMaterialcode(cStockTransferDetail.getMaterialCode());
		    	 cStockFlow.setAmt(cStockTransferDetail.getTotalS());
		    	 cStockFlow.setMchcode(csr.getFromMchcode());
		    	 cStockFlow.setChangeType("-1");
		    	 cStockFlow.setCreateoper(csr.getCreateoper());
		    	 cStockFlow.setCreattime(csr.getCreatetime());
		    	 cStockFlow.setDeptid(csr.getFromDeptid());
		    	 cStockFlow.setOrderNo(csr.getTsfSerialno());
		    	 cStockFlow.setOrderType(String.valueOf(TradeType.STOCK_TRANSFER_IN.getType()));
		    	 cStockFlows.add(cStockFlow);
		    	 //库存汇总更新
		    	 CStockInfo cStockInfo=new CStockInfo();
		    	 
		    	 cStockInfo.setDeptid(csr.getFromDeptid());
		    	 cStockInfo.setMchcode(csr.getFromMchcode());
		    	 cStockInfo.setMaterialcode(cStockTransferDetail.getMaterialCode());  	
		    	 cStockInfo.setTotalM(new BigDecimal(0));
		    	 cStockInfo.setTotalT(new BigDecimal(0));
		    	 cStockInfo.setTotalS(cStockTransferDetail.getTotalS());
		    	 cStockInfo.setTransferTotalS(cStockTransferDetail.getTotalS());
		    	 cStockInfo.setModifytime(csr.getCreatetime());
		    	 cStockInfo.setChangeType(-1);
			    
		    	 cStockInfos.add(cStockInfo);	 
    		}
    	
    
    	if(cStockTransferDetails.size()>0)
        {
        	for (CStockTransferDetail cStockTransferDetail : cStockTransferDetails) 
        		{
    		   		 CStockFlow cStockFlow=new CStockFlow();
    		    	 cStockFlow.setMaterialcode(cStockTransferDetail.getMaterialCode());
    		    	 cStockFlow.setAmt(cStockTransferDetail.getTotalS());
    		    	 cStockFlow.setMchcode("100000");
    		    	 cStockFlow.setChangeType("+1");
    		    	 cStockFlow.setCreateoper(csr.getCreateoper());
    		    	 cStockFlow.setCreattime(csr.getCreatetime());
    		    	 cStockFlow.setDeptid(csr.getFromDeptid());
    		    	 cStockFlow.setOrderNo(csr.getTsfSerialno());
    		    	 cStockFlow.setOrderType(String.valueOf(TradeType.STOCK_TRANSFER_IN.getType()));
    		    	 cStockFlows1.add(cStockFlow);
    		    	 //库存汇总更新
    		    	 CStockInfo cStockInfo=new CStockInfo();
    		    	 
    		    	 cStockInfo.setDeptid(csr.getFromDeptid());
    		    	 cStockInfo.setMchcode("100000");
    		    	 cStockInfo.setMaterialcode(cStockTransferDetail.getMaterialCode());  	
    		    	 cStockInfo.setTotalM(new BigDecimal(0));
    		    	 cStockInfo.setTotalT(new BigDecimal(0));
    		    	 cStockInfo.setTotalS(cStockTransferDetail.getTotalS());
    		    	 cStockInfo.setTransferTotalS(cStockTransferDetail.getTotalS());
    		    	 cStockInfo.setModifytime(csr.getCreatetime());
    		    	 cStockInfo.setChangeType(+1);
    		   
    			    
    		    	
    		    	 cStockInfos1.add(cStockInfo);	 
        		}
    	
        }
    	
    	
    	 cStockFlowDao.batchCreate(cStockFlows);
    	 cStockFlowDao.batchCreate(cStockFlows1);
	     log.debug("=========库存流水数据保存成功=======");    
	  
	     //库存汇总更新
	     stockInfoDao.batchUpdate(cStockInfos);
	     stockInfoDao.batchUpdate(cStockInfos1);
	     log.debug("=========库存汇总数据保存成功======="); 
	     
    	
	    }
    result=new ResultDto<String>(true,"数据保存成功");
	
   	} catch (NumberFormatException e) {
   		log.error("交易流水数据转化异常"+e.getMessage());
   		e.printStackTrace();
   		throw new BusinessException(e.getMessage());
   	} catch (Exception e) {
   			log.error("交易流水数据保存异常"+e.getMessage());
   			e.printStackTrace();
   			throw new BusinessException(e.getMessage());
   	}
   	
   	return result;
	}
private ResultDto<String> saveStockTransferOutFlow(String orderNo) {
		// TODO Auto-generated method stub
	ResultDto<String> result=null;
	try{
		CStockTransfer  csr=stockTransferlDao.findById(orderNo);
	List<CStockTransferDetail> cStockTransferDetails=(List<CStockTransferDetail>) stockTransferDetailDao.queryDetailList(orderNo);
	List<CStockFlow> cStockFlows=new ArrayList<CStockFlow>();
	List<CStockFlow> cStockFlows1=new ArrayList<CStockFlow>();
    
    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
    List<CStockInfo> cStockInfos1=new ArrayList<CStockInfo>();
    if(cStockTransferDetails.size()>0)
    {
    	for (CStockTransferDetail cStockTransferDetail : cStockTransferDetails) 
    		{
		   		 CStockFlow cStockFlow=new CStockFlow();
		    	 cStockFlow.setMaterialcode(cStockTransferDetail.getMaterialCode());
		    	 cStockFlow.setAmt(cStockTransferDetail.getTotalS());
		    	 cStockFlow.setMchcode(csr.getToMchcode());
		    	 cStockFlow.setChangeType("+1");
		    	 cStockFlow.setCreateoper(csr.getCreateoper());
		    	 cStockFlow.setCreattime(csr.getCreatetime());
		    	 cStockFlow.setDeptid(csr.getToDeptid());
		    	 cStockFlow.setOrderNo(csr.getTsfSerialno());
		    	 cStockFlow.setOrderType(String.valueOf(TradeType.STOCK_TRANSFER_OUT.getType()));
		    	 cStockFlows.add(cStockFlow);
		    	 //库存汇总更新
		    	 CStockInfo cStockInfo=new CStockInfo();
		    	 
		    	 cStockInfo.setDeptid(csr.getToDeptid());
		    	 cStockInfo.setMchcode(csr.getToMchcode());
		    	 cStockInfo.setMaterialcode(cStockTransferDetail.getMaterialCode());  	
		    	 cStockInfo.setTotalM(new BigDecimal(0));
		    	 cStockInfo.setTotalT(new BigDecimal(0));
		    	 cStockInfo.setTotalS(cStockTransferDetail.getTotalS());
		    	 cStockInfo.setTransferTotalS(cStockTransferDetail.getTotalS());
		    	 cStockInfo.setModifytime(csr.getCreatetime());
		    	 cStockInfo.setChangeType(+1);
			    
		    	 cStockInfos.add(cStockInfo);	 
    		}
    	
    
    	if(cStockTransferDetails.size()>0)
        {
        	for (CStockTransferDetail cStockTransferDetail : cStockTransferDetails) 
        		{
    		   		 CStockFlow cStockFlow=new CStockFlow();
    		    	 cStockFlow.setMaterialcode(cStockTransferDetail.getMaterialCode());
    		    	 cStockFlow.setAmt(cStockTransferDetail.getTotalS());
    		    	 cStockFlow.setMchcode("100000");
    		    	 cStockFlow.setChangeType("-1");
    		    	 cStockFlow.setCreateoper(csr.getCreateoper());
    		    	 cStockFlow.setCreattime(csr.getCreatetime());
    		    	 cStockFlow.setDeptid(csr.getFromDeptid());
    		    	 cStockFlow.setOrderNo(csr.getTsfSerialno());
    		    	 cStockFlow.setOrderType(String.valueOf(TradeType.STOCK_TRANSFER_OUT.getType()));
    		    	 cStockFlows1.add(cStockFlow);
    		    	 //库存汇总更新
    		    	 CStockInfo cStockInfo=new CStockInfo();
    		    	 
    		    	 cStockInfo.setDeptid(csr.getFromDeptid());
    		    	 cStockInfo.setMchcode("100000");
    		    	 cStockInfo.setMaterialcode(cStockTransferDetail.getMaterialCode());  	
    		    	 cStockInfo.setTotalM(new BigDecimal(0));
    		    	 cStockInfo.setTotalT(new BigDecimal(0));
    		    	 cStockInfo.setTotalS(cStockTransferDetail.getTotalS());
    		    	 cStockInfo.setTransferTotalS(cStockTransferDetail.getTotalS());
    		    	 cStockInfo.setModifytime(csr.getCreatetime());
    		    	 cStockInfo.setChangeType(-1);
    		   
    			    
    		    	
    		    	 cStockInfos1.add(cStockInfo);	 
        		}
    	
        }
    	
    	
    	 cStockFlowDao.batchCreate(cStockFlows);
    	 cStockFlowDao.batchCreate(cStockFlows1);
	     log.debug("=========库存流水数据保存成功=======");    
	  
	     //库存汇总更新
	     stockInfoDao.batchUpdate(cStockInfos);
	     stockInfoDao.batchUpdate(cStockInfos1);
	     log.debug("=========库存汇总数据保存成功======="); 
	     
    	
	    }
    result=new ResultDto<String>(true,"数据保存成功");
	
   	} catch (NumberFormatException e) {
   		log.error("交易流水数据转化异常"+e.getMessage());
   		e.printStackTrace();
   		throw new BusinessException(e.getMessage());
   	} catch (Exception e) {
   			log.error("交易流水数据保存异常"+e.getMessage());
   			e.printStackTrace();
   			throw new BusinessException(e.getMessage());
   	}
   	
   	return result;
	}
private ResultDto<String> saveAntiStockInFlow(String orderNo) {
		// TODO Auto-generated method stub
ResultDto<String> result=null;
    
	try {

		//获取订单信息
		CStockIn cStockIn =stockInDao.findById(orderNo);
		
		List<CStockInDetail> cStockInDetails=stockInDetailDao.queryStockInDetailListById(orderNo);
		//
	    List<CStockFlow> cStockFlows=new ArrayList<CStockFlow>();
	    
	    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
		
		if(cStockInDetails.size()>0)
		{
			
			cStockFlowDao.deleteById(orderNo);
			 log.debug("=========库存流水删除成功=======");  
			stockTotalflowDao.deleteById(orderNo);
			 log.debug("=========中心财务汇总流水表删除成功=======");  
             for (CStockInDetail cStockInDetail : cStockInDetails) 
             {
          
            	 //库存汇总更新
            	 CStockInfo cStockInfo=new CStockInfo();
            	 
            	 cStockInfo.setDeptid(cStockIn.getDeptid());
            	 cStockInfo.setMchcode(cStockIn.getMchcode());
            	 cStockInfo.setMaterialcode(cStockInDetail.getMaterialCode());
            	 cStockInfo.setTotalS(cStockInDetail.getTotalS());
            	 cStockInfo.setTotalM(new BigDecimal(0));
            	 cStockInfo.setTotalT(new BigDecimal(0));
            	 cStockInfo.setModifytime(cStockIn.getCreatetime());
            	 cStockInfo.setChangeType(-1);
            	 cStockInfo.setIsHave("1");
            	 cStockInfo.setTransferTotalS(new BigDecimal(0));
            	 cStockInfos.add(cStockInfo);	 
            	 
			 }  
		     
		     //库存汇总更新
		     stockInfoDao.batchUpdate(cStockInfos);
		     log.debug("=========库存汇总数据保存成功=======");    
		     
		}
		
		result=new ResultDto<String>(true,"数据保存成功");
				
	} catch (NumberFormatException e) {
					log.error("交易流水数据转化异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
	} catch (Exception e) {
					log.error("交易流水数据保存异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
    }
	
    return result;
	}
private ResultDto<String> saveCStockCompensateFlow(String orderNo) {
		// TODO Auto-generated method stub
	ResultDto<String> result=null;
	try{
		CStockCompensate  cStockCompensate=cStockCompensateDao.findById(orderNo);
	List<CStockCompensateDetail> cStockCompensateDetails=(List<CStockCompensateDetail>) cStockCompensateDetailDao.findCStockCompensateDetailByIds(orderNo);
	List<CStockFlow> cStockFlows=new ArrayList<CStockFlow>();
    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>(); 
    if(cStockCompensateDetails.size()>0)
    {
    	for (CStockCompensateDetail cStockCompensateDetail : cStockCompensateDetails) 
    		{
		   		 CStockFlow cStockFlow=new CStockFlow();
		    	 cStockFlow.setMaterialcode(cStockCompensateDetail.getMaterialcode());
		    	 cStockFlow.setAmt(cStockCompensateDetail.getTotalS());
		    	 cStockFlow.setMchcode("100000");
		    	 cStockFlow.setChangeType("-1");
		    	 cStockFlow.setCreateoper(cStockCompensate.getCreateoper());
		    	 cStockFlow.setCreattime(cStockCompensate.getCreatetime());
		    	 cStockFlow.setDeptid(cStockCompensate.getDeptid());
		    	 cStockFlow.setOrderNo(cStockCompensate.getCompensateSerialno());
		    	 cStockFlow.setHtcode(cStockCompensate.getHtcode());
		    	 cStockFlow.setOrderType(String.valueOf(TradeType.STOCK_COMPENSATE.getType()));
		    	 cStockFlows.add(cStockFlow);
		    	 //库存汇总更新
		    	 CStockInfo cStockInfo=new CStockInfo();
		    	 
		    	 cStockInfo.setDeptid(cStockCompensate.getDeptid());
		    	 cStockInfo.setMchcode("100000");
		    	 cStockInfo.setMaterialcode(cStockCompensateDetail.getMaterialcode());  	
		    	 cStockInfo.setTotalM(new BigDecimal(0));
		    	 cStockInfo.setTotalT(new BigDecimal(0));
		    	 cStockInfo.setTotalS(cStockCompensateDetail.getTotalS());
		    	 cStockInfo.setTransferTotalS(new BigDecimal(0));
		    	 cStockInfo.setModifytime(cStockCompensate.getCreatetime());
		    	 cStockInfo.setChangeType(-1);
			    
		    	 cStockInfos.add(cStockInfo);	 
    		}
    	
 
       
    	
    	
    	 cStockFlowDao.batchCreate(cStockFlows);
	     log.debug("=========库存流水数据保存成功=======");    
	  
	     //库存汇总更新
	     stockInfoDao.batchUpdate(cStockInfos);
	     log.debug("=========库存汇总数据保存成功======="); 
	     
    	
	    }
    result=new ResultDto<String>(true,"数据保存成功");
	
   	} catch (NumberFormatException e) {
   		log.error("交易流水数据转化异常"+e.getMessage());
   		e.printStackTrace();
   		throw new BusinessException(e.getMessage());
   	} catch (Exception e) {
   			log.error("交易流水数据保存异常"+e.getMessage());
   			e.printStackTrace();
   			throw new BusinessException(e.getMessage());
   	}
   	
   	return result;
	}
private ResultDto<String> saveStockTransferFlow(String orderNo) {
		// TODO Auto-generated method stub
	ResultDto<String> result=null;
	try{
		CStockTransfer  csr=stockTransferlDao.findById(orderNo);
	List<CStockTransferDetail> cStockTransferDetails=(List<CStockTransferDetail>) stockTransferDetailDao.queryDetailList(orderNo);
	List<CStockFlow> cStockFlows=new ArrayList<CStockFlow>();
	List<CStockFlow> cStockFlows1=new ArrayList<CStockFlow>();
    
    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
    List<CStockInfo> cStockInfos1=new ArrayList<CStockInfo>();
    if(cStockTransferDetails.size()>0)
    {
    	for (CStockTransferDetail cStockTransferDetail : cStockTransferDetails) 
    		{
		   		 CStockFlow cStockFlow=new CStockFlow();
		    	 cStockFlow.setMaterialcode(cStockTransferDetail.getMaterialCode());
		    	 cStockFlow.setAmt(cStockTransferDetail.getTotalS());
		    	 cStockFlow.setMchcode(csr.getToMchcode());
		    	 cStockFlow.setChangeType("-1");
		    	 cStockFlow.setCreateoper(csr.getCreateoper());
		    	 cStockFlow.setCreattime(csr.getCreatetime());
		    	 cStockFlow.setDeptid(csr.getToDeptid());
		    	 cStockFlow.setOrderNo(csr.getTsfSerialno());
		    	 cStockFlow.setOrderType(String.valueOf(TradeType.TRANSFER_MUTUAL.getType()));
		    	 cStockFlows.add(cStockFlow);
		    	 //库存汇总更新
		    	 CStockInfo cStockInfo=new CStockInfo();
		    	 
		    	 cStockInfo.setDeptid(csr.getToDeptid());
		    	 cStockInfo.setMchcode(csr.getToMchcode());
		    	 cStockInfo.setMaterialcode(cStockTransferDetail.getMaterialCode());  	
		    	 cStockInfo.setTotalM(new BigDecimal(0));
		    	 cStockInfo.setTotalT(new BigDecimal(0));
		    	 cStockInfo.setTotalS(cStockTransferDetail.getTotalS());
		    	 cStockInfo.setTransferTotalS(cStockTransferDetail.getTotalS());
		    	 cStockInfo.setModifytime(csr.getCreatetime());
		    	 cStockInfo.setChangeType(-1);
			    
		    	 cStockInfos.add(cStockInfo);	 
    		}
    	
    
    	if(cStockTransferDetails.size()>0)
        {
        	for (CStockTransferDetail cStockTransferDetail : cStockTransferDetails) 
        		{
    		   		 CStockFlow cStockFlow=new CStockFlow();
    		    	 cStockFlow.setMaterialcode(cStockTransferDetail.getMaterialCode());
    		    	 cStockFlow.setAmt(cStockTransferDetail.getTotalS());
    		    	 cStockFlow.setMchcode(csr.getFromMchcode());
    		    	 cStockFlow.setChangeType("+1");
    		    	 cStockFlow.setCreateoper(csr.getCreateoper());
    		    	 cStockFlow.setCreattime(csr.getCreatetime());
    		    	 cStockFlow.setDeptid(csr.getFromDeptid());
    		    	 cStockFlow.setOrderNo(csr.getTsfSerialno());
    		    	 cStockFlow.setOrderType(String.valueOf(TradeType.TRANSFER_MUTUAL.getType()));
    		    	 cStockFlows1.add(cStockFlow);
    		    	 //库存汇总更新
    		    	 CStockInfo cStockInfo=new CStockInfo();
    		    	 
    		    	 cStockInfo.setDeptid(csr.getFromDeptid());
    		    	 cStockInfo.setMchcode(csr.getFromMchcode());
    		    	 cStockInfo.setMaterialcode(cStockTransferDetail.getMaterialCode());  	
    		    	 cStockInfo.setTotalM(new BigDecimal(0));
    		    	 cStockInfo.setTotalT(new BigDecimal(0));
    		    	 cStockInfo.setTotalS(cStockTransferDetail.getTotalS());
    		    	 cStockInfo.setTransferTotalS(cStockTransferDetail.getTotalS());
    		    	 cStockInfo.setModifytime(csr.getCreatetime());
    		    	 cStockInfo.setChangeType(+1);
    		   
    			    
    		    	
    		    	 cStockInfos1.add(cStockInfo);	 
        		}
    	
        }
    	
    	
    	 cStockFlowDao.batchCreate(cStockFlows);
    	 cStockFlowDao.batchCreate(cStockFlows1);
	     log.debug("=========库存流水数据保存成功=======");    
	  
	     //库存汇总更新
	     stockInfoDao.batchUpdate(cStockInfos);
	     stockInfoDao.batchUpdate(cStockInfos1);
	     log.debug("=========库存汇总数据保存成功======="); 
	     
    	
	    }
    result=new ResultDto<String>(true,"数据保存成功");
	
   	} catch (NumberFormatException e) {
   		log.error("交易流水数据转化异常"+e.getMessage());
   		e.printStackTrace();
   		throw new BusinessException(e.getMessage());
   	} catch (Exception e) {
   			log.error("交易流水数据保存异常"+e.getMessage());
   			e.printStackTrace();
   			throw new BusinessException(e.getMessage());
   	}
   	
   	return result;
	}
/**
 * 保存汇总数据
 * @param orderNo
 */
private  synchronized void saveTotalFlow(String orderNo,TradeType tradeType){
	
	try{
		
		    BigDecimal transportFee=new BigDecimal(0.0);
		    BigDecimal otherFee=new BigDecimal(0.0);
		    BigDecimal zxFee=new BigDecimal(0.0);
		    String fromMchcode=null;
		    String toMchcode=null;
		    String htcode=null;
		    boolean isMinus=false;
		    Date tradeTime=new Date();
			switch ( tradeType )
			{
			    case STOCK_IN: 
			    CStockIn cStockIn =stockInDao.findById(orderNo);
			    toMchcode=cStockIn.getMchcode();
			    transportFee=cStockIn.getTransportFee();
			    otherFee=cStockIn.getOtherFee();
			    zxFee=cStockIn.getZxFee();
			    tradeTime=cStockIn.getTransDate();
			       break;
			    case STOCK_OUT: 
			    CStockOut cStockOut =stockOutDao.findById(orderNo);	
			    fromMchcode=cStockOut.getMchcode();
			    transportFee=cStockOut.getTransportFee();
			    otherFee=cStockOut.getOtherFee();
			    zxFee=cStockOut.getZxFee();
			    isMinus=true;
			    tradeTime=cStockOut.getTransDate();
			        break;
			    case STOCK_CG:
				CBusPur cBusPur =busPurDao.findById(orderNo);
				fromMchcode=cBusPur.getFromMchcode();
				tradeTime=cBusPur.getCreatetime();
				       break;
			    case STOCK_XS: 
			    CBusSale cBusSale =busSaleDao.findById(orderNo);	
			    toMchcode=cBusSale.getToMchcode();
			    isMinus=true;
			    tradeTime=cBusSale.getCreatetime();
				       break;
			    case OTHER_IN: 
			     
			       break;
			    case REMODELING: 
			    	
				       break;       
			    case TEMPORARY_IN: 
			    CStockTemporary cst=cstdao.findById(orderNo);
			    toMchcode=cst.getMchcode();
			    otherFee=cst.getOtherFee();
			    zxFee=cst.getZxFee();
			    tradeTime=cst.getCreatetime();
				       break; 
			    case STOCKCENTERTRANSFER_OUT: 
			    CStockCenterTransfer  csr=centerTransferDao.findByCStockCenterTransfer(orderNo);
			    fromMchcode=csr.getFromMchcode();
			    toMchcode=csr.getToMchcode();
			    transportFee=csr.getTransportFee();
			    otherFee=csr.getOtherFee();
			    zxFee=csr.getZxFee();
			    isMinus=true;
			    tradeTime=csr.getTsfSdate();
				       break;
			    case STOCKCENTERTRANSFER_IN: 
			    CStockCenterTransfer  csrf=centerTransferDao.findByCStockCenterTransfer(orderNo);
			    fromMchcode=csrf.getFromMchcode();
			    toMchcode=csrf.getToMchcode();
			    transportFee=csrf.getTransportFee();
			    otherFee=csrf.getOtherFee();
			    zxFee=csrf.getZxFee();
			    tradeTime=csrf.getTsfSdate();
				       break;
			    case STOCK_SEND: 
			    CStockSend  cStockSend=stockSendDao.findById(orderNo);
			    htcode=cStockSend.getHtcode();
			    CBusHt busHt=busHtDao.findById(htcode);
			    fromMchcode=cStockSend.getMchcode();
			    toMchcode=busHt.getLesseeCusCode();
			    transportFee=cStockSend.getTransportFee();
			    otherFee=cStockSend.getOtherFee();
			    zxFee=cStockSend.getZxFee();
			    isMinus=true;
			    tradeTime=cStockSend.getSendtime();
				       break;
			    case STOCK_RECEIPT: 
			    CStockReceipt  cStockReceipt=stockReceiptDao.findById(orderNo);	
			    htcode=cStockReceipt.getHtcode();
			    busHt=busHtDao.findById(htcode);
			    fromMchcode=busHt.getLesseeCusCode();
			    toMchcode=cStockReceipt.getMchcode();
			    transportFee=cStockReceipt.getTransportFee();
			    otherFee=cStockReceipt.getOtherFee();
			    zxFee=cStockReceipt.getZxFee();
			    tradeTime=cStockReceipt.getReceipttime();
				       break;
			    case STOCK_TRANSFER_OUT: 
			    CStockTransfer  cStockTransfer=stockTransferlDao.findById(orderNo);
			    fromMchcode=cStockTransfer.getFromMchcode();
			    toMchcode=cStockTransfer.getToMchcode();
			    transportFee=cStockTransfer.getTransportFee();
			    otherFee=cStockTransfer.getOtherFee();
			    zxFee=cStockTransfer.getZxFee();
			    tradeTime=cStockTransfer.getTsfSdate();
				       break;
			    case STOCK_TRANSFER_IN: 
			    CStockTransfer  cStockTransferIn=stockTransferlDao.findById(orderNo);
			    fromMchcode=cStockTransferIn.getFromMchcode();
			    toMchcode=cStockTransferIn.getToMchcode();
			    transportFee=cStockTransferIn.getTransportFee();
			    otherFee=cStockTransferIn.getOtherFee();
			    zxFee=cStockTransferIn.getZxFee();
			    tradeTime=cStockTransferIn.getTsfSdate();
				       break;
			    case STOCK_TEMPORARY_IN:
		    	CStockIn cStockIns =stockInDao.findById(orderNo);
			    toMchcode=cStockIns.getMchcode();
			    transportFee=cStockIns.getTransportFee();
			    otherFee=cStockIns.getOtherFee();
			    zxFee=cStockIns.getZxFee();
			    tradeTime=cStockIns.getTransDate();
			    	   break;
			    case STOCK_TEMPORARY_OUT:
		    	CStockOut cStockOuts =stockOutDao.findById(orderNo);	
			    fromMchcode=cStockOuts.getMchcode();
			    transportFee=cStockOuts.getTransportFee();
			    otherFee=cStockOuts.getOtherFee();
			    zxFee=cStockOuts.getZxFee();
			    isMinus=true;
			    tradeTime=cStockOuts.getTransDate();
			    	   break;
			    case TRANSFER_MUTUAL: 
		    	CStockTransfer  stockTransfer=stockTransferlDao.findById(orderNo);
			    fromMchcode=stockTransfer.getFromMchcode();
			    toMchcode=stockTransfer.getToMchcode();
			    transportFee=stockTransfer.getTransportFee();
			    otherFee=stockTransfer.getOtherFee();
			    zxFee=stockTransfer.getZxFee();
			    tradeTime=stockTransfer.getTsfSdate();
			    
			   default:
			}
			
			List<CStockFlowDto> stockFlows=modelDao.findStockFlowListById(orderNo);
			
			CStockFlowDto stockFlowDto=new CStockFlowDto();
			if(stockFlows.size()>0){
				 stockFlowDto=stockFlows.get(0);
			}
			List<BMaterialType> materialTypes=materialTypeDao.loadAll();
			List<Integer> typeList=new ArrayList<Integer>();
			Integer tId=0;
			for (BMaterialType bMaterialType : materialTypes) {
				tId=bMaterialType.getTypeid();
				typeList.add(tId);
			}
			Collections.sort(typeList); //排序

			CStockTotalflow cStockTotalflow=new CStockTotalflow();
			cStockTotalflow.setOrderNo(orderNo);
			
			Double totalGguan=0.0;
			
			Double totalKjian=0.0;
			Double totalDguan=0.0;
			Double totalTguan=0.0;
			Double totalLkjia=0.0;
			Double totalCgang=0.0;
			Double totalTwgzgang=0.0;
			Double totalGzgang=0.0;
			Double totalTwang=0.0;
			Double totalItem1=0.0;
			Double totalItem2=0.0;
			for (int i = 0; i < stockFlows.size(); i++) {
					Integer typeId=stockFlows.get(i).getTypeid();
					Double amt=stockFlows.get(i).getAmt();
					Double covertRatio=Double.parseDouble(stockFlows.get(i).getCovertRatio());
					if(typeId.equals(typeList.get(1))) //钢管
					{
							if(amt!=null)
							{
								totalGguan=totalGguan+amt*covertRatio;
							}
						
					}
					if(typeId.equals(typeList.get(2))) //扣件
					{
							if(amt!=null)
							{
								totalKjian=totalKjian+amt*covertRatio;
							}
						
					}
					if(typeId.equals(typeList.get(3))) //套管(只)
					{
							if(amt!=null)
							{
								totalTguan=totalTguan+amt*covertRatio;
							}
						
					}
					if(typeId.equals(typeList.get(4))) //短管(只)
					{
							if(amt!=null)
							{
								totalDguan=totalDguan+amt*covertRatio;
							}
						
					}
					if(typeId.equals(typeList.get(5))) //轮扣架
					{
							if(amt!=null)
							{
								totalLkjia=totalLkjia+amt*covertRatio;
							}
						
					}
					if(typeId.equals(typeList.get(6))) //槽钢
					{
							if(amt!=null)
							{
								totalCgang=totalCgang+amt*covertRatio;
							}
						
					}
					if(typeId.equals(typeList.get(7))) //12#工字钢
					{
							if(amt!=null)
							{
								totalTwgzgang=totalTwgzgang+amt*covertRatio;
							}
						
					}
					if(typeId.equals(typeList.get(8))) //工字钢
					{
							if(amt!=null)
							{
								totalGzgang=totalGzgang+amt*covertRatio;
							}
						
					}
//					if(typeId.equals(typeList.get(9))) //铁网汇总
//					{
//							if(amt!=null)
//							{
//								totalTwang=totalTwang+amt*covertRatio;
//							}
//						
//					}
//					if(typeId.equals(typeList.get(10))) //备份一
//					{
//							if(amt!=null)
//							{
//								totalTwang=totalTwang+amt*covertRatio;
//							}
//						
//					}
//					if(typeId.equals(typeList.get(11))) //备份二
//					{
//							if(amt!=null)
//							{
//								totalTwang=totalTwang+amt*covertRatio;
//							}
//						
//					}
				  
			}
			cStockTotalflow.setDeptid(stockFlowDto.getDeptid());
			
			if(fromMchcode==null){
				fromMchcode=SystemConst.ZX_MCHCODE;
			}
			if(toMchcode==null){
				toMchcode=SystemConst.ZX_MCHCODE;
			}
			cStockTotalflow.setFromMchcode(fromMchcode);
			cStockTotalflow.setToMchcode(toMchcode);
			cStockTotalflow.setOrderType(stockFlowDto.getOrderType());
			cStockTotalflow.setTransportFee(transportFee);
			cStockTotalflow.setOtherFee(otherFee);
			cStockTotalflow.setZxFee(zxFee);
			cStockTotalflow.setTotalGguan(new BigDecimal(isMinus?-totalGguan:totalGguan));
			cStockTotalflow.setTotalKjian(new BigDecimal(isMinus?-totalKjian:totalKjian));
			cStockTotalflow.setTotalTguan(new BigDecimal(isMinus?-totalTguan:totalTguan));
			cStockTotalflow.setTotalDguan(new BigDecimal(isMinus?-totalDguan:totalDguan));
			cStockTotalflow.setTotalLkjia(new BigDecimal(isMinus?-totalLkjia:totalLkjia));
			cStockTotalflow.setTotalCgang(new BigDecimal(isMinus?-totalCgang:totalCgang));
			cStockTotalflow.setTotalTwgzgang(new BigDecimal(isMinus?-totalTwgzgang:totalTwgzgang));
			cStockTotalflow.setTotalGzgang(new BigDecimal(isMinus?-totalGzgang:totalGzgang));
			cStockTotalflow.setTotalTwang(new BigDecimal(isMinus?-totalTwang:totalTwang));
			cStockTotalflow.setTotalItem1(new BigDecimal(isMinus?-totalItem1:totalItem1));
			cStockTotalflow.setTotalItem2(new BigDecimal(isMinus?-totalItem2:totalItem2));
			cStockTotalflow.setTradetime(tradeTime);
			cStockTotalflow.setHtcode(htcode);
		    stockTotalflowDao.create(cStockTotalflow);
		    log.debug("=========库存流水汇总数据保存成功=======");    
	     
    	
   	} catch (NumberFormatException e) {
	   		log.error("库存流水汇总数据转化异常"+e.getMessage());
	   		e.printStackTrace();
	   		throw new BusinessException(e.getMessage());
   	} catch (Exception e) {
   			log.error("库存流水汇总数据保存异常"+e.getMessage());
   			e.printStackTrace();
   			throw new BusinessException(e.getMessage());
   	}
   	
}




private ResultDto<String> saveStockSendInFlow(String orderNo) {
		// TODO Auto-generated method stub
	ResultDto<String> result=null;
	try{
	CStockSend  cStockSend=stockSendDao.findById(orderNo);
	List<CStockSendDetail> cStockSendDetails=stockSendDetailDao.findByCStockSendDetail(orderNo);
	List<CStockFlow> cStockFlows=new ArrayList<CStockFlow>();
    
    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
    if(cStockSendDetails.size()>0)
    {
    	for (CStockSendDetail stockSendDetail : cStockSendDetails) 
    		{
		   		 CStockFlow cStockFlow=new CStockFlow();
		   		 cStockFlow.setHtcode(cStockSend.getHtcode());
		    	 cStockFlow.setMaterialcode(stockSendDetail.getMaterialcode());
		    	 cStockFlow.setAmt(stockSendDetail.getTotalS());
		    	 cStockFlow.setMchcode(cStockSend.getMchcode());
		    	 cStockFlow.setChangeType("-1");
		    	 cStockFlow.setCreateoper(cStockSend.getCreateoper());
		    	 cStockFlow.setCreattime(cStockSend.getCreatetime());
		    	 cStockFlow.setDeptid(cStockSend.getDeptid());
		    	 cStockFlow.setOrderNo(cStockSend.getSendSerialno());
		    	 cStockFlow.setOrderType(String.valueOf(TradeType.STOCK_SEND.getType()));
		    	 cStockFlows.add(cStockFlow);
		    	 //库存汇总更新
		    	 CStockInfo cStockInfo=new CStockInfo();
		    	 
		    	 cStockInfo.setDeptid(cStockSend.getDeptid());
		    	 cStockInfo.setMchcode(cStockSend.getMchcode());
		    	 cStockInfo.setMaterialcode(stockSendDetail.getMaterialcode());  	
		    	 cStockInfo.setTotalM(new BigDecimal(0));
		    	 cStockInfo.setTotalT(new BigDecimal(0));
		    	 cStockInfo.setTotalS(stockSendDetail.getTotalS());
		    	 cStockInfo.setModifytime(cStockSend.getCreatetime());
		    	 cStockInfo.setChangeType(-1);
		    	 cStockInfo.setTransferTotalS(new BigDecimal(0));
			    
		    	
		    	 cStockInfos.add(cStockInfo);	 
    		}
    	 cStockFlowDao.batchCreate(cStockFlows);
	     log.debug("=========库存流水数据保存成功=======");    
	     
	     //库存汇总更新
	     stockInfoDao.batchUpdate(cStockInfos);
	     log.debug("=========库存汇总数据保存成功======="); 
	     
    	
	    }
    result=new ResultDto<String>(true,"数据保存成功");
	
   	} catch (NumberFormatException e) {
   		log.error("交易流水数据转化异常"+e.getMessage());
   		e.printStackTrace();
   		throw new BusinessException(e.getMessage());
   	} catch (Exception e) {
   			log.error("交易流水数据保存异常"+e.getMessage());
   			e.printStackTrace();
   			throw new BusinessException(e.getMessage());
   	}
   	
   	return result;
	}

private ResultDto<String> saveStockReceiptFlow(String orderNo) {
	
	ResultDto<String> result=null;
	try{
	CStockReceipt  cStockReceipt=stockReceiptDao.findById(orderNo);
	List<CStockReceiptDetail> cStockReceiptDetails=stockReceiptDetailDao.findReceiptDetailListById(orderNo);
	List<CStockFlow> cStockFlows=new ArrayList<CStockFlow>();
	
	List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
	if(cStockReceiptDetails.size()>0)
	{
		for (CStockReceiptDetail stockReceiptDetail : cStockReceiptDetails) 
			{
		   		 CStockFlow cStockFlow=new CStockFlow();
		    	 cStockFlow.setMaterialcode(stockReceiptDetail.getMaterialcode());
		    	 cStockFlow.setAmt(stockReceiptDetail.getTotalS());
		    	 cStockFlow.setHtcode(cStockReceipt.getHtcode());
		    	 cStockFlow.setMchcode(cStockReceipt.getMchcode());
		    	 cStockFlow.setChangeType("1");
		    	 cStockFlow.setCreateoper(cStockReceipt.getCreateoper());
		    	 cStockFlow.setCreattime(cStockReceipt.getCreatetime());
		    	 cStockFlow.setDeptid(cStockReceipt.getDeptid());
		    	 cStockFlow.setOrderNo(cStockReceipt.getReceiptSerialno());
		    	 cStockFlow.setOrderType(String.valueOf(TradeType.STOCK_RECEIPT.getType()));
		    	 cStockFlows.add(cStockFlow);
		    	 //库存汇总更新
		    	 CStockInfo cStockInfo=new CStockInfo();
		    	 
		    	 cStockInfo.setDeptid(cStockReceipt.getDeptid());
		    	 cStockInfo.setMchcode(cStockReceipt.getMchcode());
		    	 cStockInfo.setMaterialcode(stockReceiptDetail.getMaterialcode());  	
		    	 cStockInfo.setTotalM(new BigDecimal(0));
		    	 cStockInfo.setTotalT(new BigDecimal(0));
		    	 cStockInfo.setTotalS(stockReceiptDetail.getTotalS());
		    	 cStockInfo.setModifytime(cStockReceipt.getCreatetime());
		    	 cStockInfo.setChangeType(1);
		    	 cStockInfo.setTransferTotalS(new BigDecimal(0));	
			    
		    	
		    	 cStockInfos.add(cStockInfo);	 
			}
				 cStockFlowDao.batchCreate(cStockFlows);
			     log.debug("=========收料流水数据保存成功=======");    
			     
			     //库存汇总更新
			     stockInfoDao.batchUpdate(cStockInfos);
			     log.debug("=========收料汇总数据保存成功======="); 
	     
	    }
	    result=new ResultDto<String>(true,"数据保存成功");
	
		} catch (NumberFormatException e) {
				log.error("收料交易流水数据转化异常"+e.getMessage());
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
		} catch (Exception e) {
				log.error("收料交易流水数据保存异常"+e.getMessage());
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
		}
		
	return result;
}

private ResultDto<String> saveAntiStockReceiptFlow(String orderNo) {
	// TODO Auto-generated method stub
	ResultDto<String> result=null;
	
	try {
	
		//获取订单信息
		CStockReceipt  cStockReceipt=stockReceiptDao.findById(orderNo);
		
		List<CStockReceiptDetail> cStockReceiptDetails=stockReceiptDetailDao.findReceiptDetailListById(orderNo);
		
	    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
		
		if(cStockReceiptDetails.size()>0)
		{
			
			cStockFlowDao.deleteById(orderNo);
			 log.debug("=========库存流水删除成功=======");  
			stockTotalflowDao.deleteById(orderNo);
			 log.debug("=========中心财务汇总流水表删除成功=======");  
	         for (CStockReceiptDetail cStockReceiptDetail : cStockReceiptDetails) 
	         {
	      
	        	 //库存汇总更新
	        	 CStockInfo cStockInfo=new CStockInfo();
	        	 
	        	 cStockInfo.setDeptid(cStockReceipt.getDeptid());
	        	 cStockInfo.setMchcode(cStockReceipt.getMchcode());
	        	 cStockInfo.setMaterialcode(cStockReceiptDetail.getMaterialcode());
	        	 cStockInfo.setTotalS(cStockReceiptDetail.getTotalS());
	        	 cStockInfo.setTotalM(new BigDecimal(0));
	        	 cStockInfo.setTotalT(new BigDecimal(0));
	        	 cStockInfo.setModifytime(new Date());
	        	 cStockInfo.setChangeType(-1);
	        	 cStockInfo.setIsHave("1");
	        	 cStockInfo.setTransferTotalS(new BigDecimal(0));
	        	 cStockInfos.add(cStockInfo);	 
	        	 
			 }  
		     
		     //库存汇总更新
		     stockInfoDao.batchUpdate(cStockInfos);
		     log.debug("=========库存汇总数据保存成功=======");    
		     
		}
		
		result=new ResultDto<String>(true,"数据保存成功");
			
} catch (NumberFormatException e) {
				log.error("交易流水数据转化异常"+e.getMessage());
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
} catch (Exception e) {
				log.error("交易流水数据保存异常"+e.getMessage());
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
}

return result;
}


private ResultDto<String> saveStockCenterTransferInFlow(String orderNo) {
		// TODO Auto-generated method stub
	ResultDto<String> result=null;
	try{
	CStockCenterTransfer  csr=centerTransferDao.findByCStockCenterTransfer(orderNo);
	List<CStockCenterTransferDetail> cStockCenterTransferDetails=centerTransferDetailDao.findByCStockCenterTransferDetail(orderNo);
	List<CStockFlow> cStockFlows=new ArrayList<CStockFlow>();
    
    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
    if(cStockCenterTransferDetails.size()>0)
    {
    	for (CStockCenterTransferDetail cStockCenterTransferDetail : cStockCenterTransferDetails) 
    		{
		   		 CStockFlow cStockFlow=new CStockFlow();
		    	 cStockFlow.setMaterialcode(cStockCenterTransferDetail.getMaterialcode());
		    	 cStockFlow.setAmt(cStockCenterTransferDetail.getTotalS());
		    	 cStockFlow.setMchcode(csr.getToMchcode());
		    	 cStockFlow.setChangeType("1");
		    	 cStockFlow.setCreateoper(csr.getCreateoper());
		    	 cStockFlow.setCreattime(csr.getCreatetime());
		    	 cStockFlow.setDeptid(csr.getFromDeptid());
		    	 cStockFlow.setOrderNo(csr.getTsfSerialno());
		    	 cStockFlow.setOrderType(String.valueOf(TradeType.STOCKCENTERTRANSFER_IN.getType()));
		    	 cStockFlows.add(cStockFlow);
		    	 //库存汇总更新
		    	 CStockInfo cStockInfo=new CStockInfo();
		    	 
		    	 cStockInfo.setDeptid(csr.getFromDeptid());
		    	 cStockInfo.setMchcode(csr.getToMchcode());
		    	 cStockInfo.setMaterialcode(cStockCenterTransferDetail.getMaterialcode());  	
		    	 cStockInfo.setTotalM(new BigDecimal(0));
		    	 cStockInfo.setTotalT(new BigDecimal(0));
		    	 cStockInfo.setTotalS(cStockCenterTransferDetail.getTotalS());
		    	 cStockInfo.setTransferTotalS(cStockCenterTransferDetail.getTotalS());
		    	 cStockInfo.setModifytime(csr.getCreatetime());
		    	 cStockInfo.setChangeType(+1);
			    	
			    
		    	
		    	 cStockInfos.add(cStockInfo);	 
    		}
    	 cStockFlowDao.batchCreate(cStockFlows);
	     log.debug("=========库存流水数据保存成功=======");    
	     
	     //库存汇总更新
	     stockInfoDao.batchUpdate(cStockInfos);
	     log.debug("=========库存汇总数据保存成功======="); 
	     
    	
	    }
    result=new ResultDto<String>(true,"数据保存成功");
	
   	} catch (NumberFormatException e) {
   		log.error("交易流水数据转化异常"+e.getMessage());
   		e.printStackTrace();
   		throw new BusinessException(e.getMessage());
   	} catch (Exception e) {
   			log.error("交易流水数据保存异常"+e.getMessage());
   			e.printStackTrace();
   			throw new BusinessException(e.getMessage());
   	}
   	
   	return result;
	}



private ResultDto<String> saveStockCenterTransferFlow(String orderNo) {
		// TODO Auto-generated method stub
	ResultDto<String> result=null;
	try{
	CStockCenterTransfer  csr=centerTransferDao.findByCStockCenterTransfer(orderNo);
	List<CStockCenterTransferDetail> cStockCenterTransferDetails=centerTransferDetailDao.findByCStockCenterTransferDetail(orderNo);
	List<CStockFlow> cStockFlows=new ArrayList<CStockFlow>();
    
    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
    if(cStockCenterTransferDetails.size()>0)
    {
    	for (CStockCenterTransferDetail cStockCenterTransferDetail : cStockCenterTransferDetails) 
    		{
		   		 CStockFlow cStockFlow=new CStockFlow();
		    	 cStockFlow.setMaterialcode(cStockCenterTransferDetail.getMaterialcode());
		    	 cStockFlow.setAmt(cStockCenterTransferDetail.getTotalS());
		    	 cStockFlow.setMchcode(csr.getFromMchcode());
		    	 cStockFlow.setChangeType("-1");
		    	 cStockFlow.setCreateoper(csr.getCreateoper());
		    	 cStockFlow.setCreattime(csr.getCreatetime());
		    	 cStockFlow.setDeptid(csr.getFromDeptid());
		    	 cStockFlow.setOrderNo(csr.getTsfSerialno());
		    	 cStockFlow.setOrderType(String.valueOf(TradeType.STOCKCENTERTRANSFER_OUT.getType()));
		    	 cStockFlows.add(cStockFlow);
		    	 //库存汇总更新
		    	 CStockInfo cStockInfo=new CStockInfo();
		    	 
		    	 cStockInfo.setDeptid(csr.getFromDeptid());
		    	 cStockInfo.setMchcode(csr.getFromMchcode());
		    	 cStockInfo.setMaterialcode(cStockCenterTransferDetail.getMaterialcode());  	
		    	 cStockInfo.setTotalM(new BigDecimal(0));
		    	 cStockInfo.setTotalT(new BigDecimal(0));
		    	 cStockInfo.setTotalS(cStockCenterTransferDetail.getTotalS());
		    	 cStockInfo.setTransferTotalS(cStockCenterTransferDetail.getTotalS());
		    	 cStockInfo.setModifytime(csr.getCreatetime());
		    	 cStockInfo.setChangeType(-1);
			    	
			    
		    	
		    	 cStockInfos.add(cStockInfo);	 
    		}
    	 cStockFlowDao.batchCreate(cStockFlows);
	     log.debug("=========库存流水数据保存成功=======");    
	     
	     //库存汇总更新
	     stockInfoDao.batchUpdate(cStockInfos);
	     log.debug("=========库存汇总数据保存成功======="); 
	     
    	
	    }
    result=new ResultDto<String>(true,"数据保存成功");
	
   	} catch (NumberFormatException e) {
   		log.error("交易流水数据转化异常"+e.getMessage());
   		e.printStackTrace();
   		throw new BusinessException(e.getMessage());
   	} catch (Exception e) {
   			log.error("交易流水数据保存异常"+e.getMessage());
   			e.printStackTrace();
   			throw new BusinessException(e.getMessage());
   	}
   	
   	return result;
	}



private ResultDto<String> saveStockRemodelingFlow(String orderNo) {
		// TODO Auto-generated method stub
	ResultDto<String> result=null;
	try{
	CStockRemodeling  csr=cstockRemodelingDao.findByCStockRemodeling(orderNo);
	List<CStockRemodelingDetail> cStockRemodelingDetails=srddao.findByCStockRemodeling(orderNo);
	List<CStockFlow> cStockFlows=new ArrayList<CStockFlow>();
    
    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
    if(cStockRemodelingDetails.size()>0)
    {
    	for (CStockRemodelingDetail cStockRemodelingDetail : cStockRemodelingDetails) 
    		{
		   		 CStockFlow cStockFlow=new CStockFlow();
		    	 cStockFlow.setMaterialcode(cStockRemodelingDetail.getCode());
		    	 cStockFlow.setAmt(cStockRemodelingDetail.getTotalS());
		    	 cStockFlow.setMchcode(csr.getMchcode());
		    	 if(cStockRemodelingDetail.getCodeOldnum().equals(new BigDecimal(0)))
		    	 {
		    		 cStockFlow.setChangeType("+1");
		    	 }
		    	 if(cStockRemodelingDetail.getCodeNewnum().equals(new BigDecimal(0)))
		    	 {
		    		 cStockFlow.setChangeType("-1");
		    	 }
		    	
		    	 cStockFlow.setCreateoper(csr.getCreateoper());
		    	 cStockFlow.setCreattime(csr.getCreatetime());
		    	 cStockFlow.setDeptid(csr.getDeptid());
		    	 cStockFlow.setOrderNo(csr.getRemSerialNo());
		    	 cStockFlow.setOrderType(String.valueOf(TradeType.REMODELING.getType()));
		    	 cStockFlows.add(cStockFlow);
		    	 //库存汇总更新
		    	 CStockInfo cStockInfo=new CStockInfo();
		    	 
		    	 cStockInfo.setDeptid(csr.getDeptid());
		    	 cStockInfo.setMchcode(csr.getMchcode());
		    	 cStockInfo.setMaterialcode(cStockRemodelingDetail.getCode());  	
		    	 cStockInfo.setTotalM(new BigDecimal(0));
		    	 cStockInfo.setTotalT(new BigDecimal(0));
		    	 cStockInfo.setModifytime(csr.getCreatetime());
		    	 cStockInfo.setTransferTotalS(new BigDecimal(0));
		    	 if(cStockRemodelingDetail.getCodeOldnum().equals(new BigDecimal(0)))
		    	 {
		    		
		    	 cStockInfo.setChangeType(+1);
		    	 cStockInfo.setTotalS(cStockRemodelingDetail.getCodeNewnum());
		    	 System.out.println("执行+"+cStockRemodelingDetail.getCode()+cStockRemodelingDetail.getCodeNewnum());
		    	 }
		    	 if(cStockRemodelingDetail.getCodeNewnum().equals(new BigDecimal(0)))
		    	 {
		    		
		    		 cStockInfo.setChangeType(-1);
			    	 cStockInfo.setTotalS(cStockRemodelingDetail.getCodeOldnum());	
			    	 System.out.println("执行-"+cStockRemodelingDetail.getCode()+cStockRemodelingDetail.getCodeOldnum());
		    	 }
		    	 cStockInfos.add(cStockInfo);	 
    		}
    	 cStockFlowDao.batchCreate(cStockFlows);
	     log.debug("=========库存流水数据保存成功=======");    
	     
	     //库存汇总更新
	     stockInfoDao.batchUpdate(cStockInfos);
	     log.debug("=========库存汇总数据保存成功======="); 
	     
    	
	    }
    result=new ResultDto<String>(true,"数据保存成功");
	
   	} catch (NumberFormatException e) {
   		log.error("交易流水数据转化异常"+e.getMessage());
   		e.printStackTrace();
   		throw new BusinessException(e.getMessage());
   	} catch (Exception e) {
   			log.error("交易流水数据保存异常"+e.getMessage());
   			e.printStackTrace();
   			throw new BusinessException(e.getMessage());
   	}
   	
   	return result;
	}


public ResultDto<String> saveStockTemporaryFlow(String orderNo) {
		// TODO Auto-generated method stub
	ResultDto<String> result=null;
	try {
		CStockTemporary cst=cstdao.findById(orderNo);
		
		List<CStockTemporaryDetail> cStockTemporaryDetails=stdedao.findByCStockTemporaryDetail(orderNo);

		List<CStockFlow> cStockFlows=new ArrayList<CStockFlow>();
	    
	    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
	    
	    if(cStockTemporaryDetails.size()>0)
	    {
	    	for (CStockTemporaryDetail cStockTemporaryDetail : cStockTemporaryDetails) {
	    		 CStockFlow cStockFlow=new CStockFlow();
            	 cStockFlow.setMaterialcode(cStockTemporaryDetail.getMaterialcode());
            	 cStockFlow.setAmt(cStockTemporaryDetail.getTotalS());
            	 cStockFlow.setMchcode(cst.getMchcode());
            	 cStockFlow.setChangeType("1");
            	 cStockFlow.setCreateoper(cst.getCreateoper());
            	 cStockFlow.setCreattime(cst.getCreatetime());
            	 cStockFlow.setDeptid(cst.getDeptid());
            	 cStockFlow.setOrderNo(cst.getTemSerialno());
            	 cStockFlow.setOrderType(String.valueOf(TradeType.TEMPORARY_IN.getType()));
            	 cStockFlows.add(cStockFlow);
            	 //库存汇总更新
            	 CStockInfo cStockInfo=new CStockInfo();
            	 
            	 cStockInfo.setDeptid(cst.getDeptid());
            	 cStockInfo.setMchcode(cst.getMchcode());
            	 cStockInfo.setMaterialcode(cStockTemporaryDetail.getMaterialcode());
            	 cStockInfo.setTotalS(cStockTemporaryDetail.getTotalS());
            	 cStockInfo.setTotalM(new BigDecimal(0));
            	 cStockInfo.setTotalT(new BigDecimal(0));
            	 cStockInfo.setModifytime(cst.getCreatetime());
            	 cStockInfo.setChangeType(+1);
            	 cStockInfo.setTransferTotalS(new BigDecimal(0));
            	 cStockInfos.add(cStockInfo);	 
			}
	    	 //库存流水增加
            cStockFlowDao.batchCreate(cStockFlows);
		     log.debug("=========库存流水数据保存成功=======");    
		     
		     //库存汇总更新
		     stockInfoDao.batchUpdate(cStockInfos);
		     log.debug("=========库存汇总数据保存成功======="); 
		     
		     

	    }
		
	    result=new ResultDto<String>(true,"数据保存成功");
		
	} catch (NumberFormatException e) {
		log.error("交易流水数据转化异常"+e.getMessage());
		e.printStackTrace();
		throw new BusinessException(e.getMessage());
	} catch (Exception e) {
			log.error("交易流水数据保存异常"+e.getMessage());
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
	}
	
	return result;
	}



public ResultDto<String> saveStockInFlow(String orderNo) {
	
	ResultDto<String> result=null;
    
	try {

		//获取订单信息
		CStockIn cStockIn =stockInDao.findById(orderNo);
		
		List<CStockInDetail> cStockInDetails=stockInDetailDao.queryStockInDetailListById(orderNo);
		//
	    List<CStockFlow> cStockFlows=new ArrayList<CStockFlow>();
	    
	    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
		
		if(cStockInDetails.size()>0)
		{
             for (CStockInDetail cStockInDetail : cStockInDetails) 
             {
            	 //库存流水增加
            	 CStockFlow cStockFlow=new CStockFlow();
            	 cStockFlow.setMaterialcode(cStockInDetail.getMaterialCode());
            	 cStockFlow.setAmt(cStockInDetail.getTotalS());
            	 cStockFlow.setMchcode(cStockIn.getMchcode());
            	 cStockFlow.setChangeType("1");
            	 cStockFlow.setCreateoper(cStockIn.getCreateoper());
            	 cStockFlow.setCreattime(cStockIn.getCreatetime());
            	 cStockFlow.setDeptid(cStockIn.getDeptid());
            	 cStockFlow.setOrderNo(cStockIn.getInSerialno());
            	 cStockFlow.setOrderType(String.valueOf(TradeType.STOCK_IN.getType()));
            	 cStockFlows.add(cStockFlow);
            	 //库存汇总更新
            	 CStockInfo cStockInfo=new CStockInfo();
            	 
            	 cStockInfo.setDeptid(cStockIn.getDeptid());
            	 cStockInfo.setMchcode(cStockIn.getMchcode());
            	 cStockInfo.setMaterialcode(cStockInDetail.getMaterialCode());
            	 cStockInfo.setTotalS(cStockInDetail.getTotalS());
            	 cStockInfo.setTotalM(new BigDecimal(0));
            	 cStockInfo.setTotalT(new BigDecimal(0));
            	 cStockInfo.setModifytime(cStockIn.getCreatetime());
            	 cStockInfo.setChangeType(+1);
            	 cStockInfo.setIsHave("1");
            	 cStockInfo.setTransferTotalS(new BigDecimal(0));
            	 cStockInfos.add(cStockInfo);	 
            	 
			 }
     		 //库存流水增加
             cStockFlowDao.batchCreate(cStockFlows);
		     log.debug("=========库存流水数据保存成功=======");    
		     
		     //库存汇总更新
		     stockInfoDao.batchUpdate(cStockInfos);
		     log.debug("=========库存汇总数据保存成功=======");    
		     
		}
		
		result=new ResultDto<String>(true,"数据保存成功");
				
	} catch (NumberFormatException e) {
					log.error("交易流水数据转化异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
	} catch (Exception e) {
					log.error("交易流水数据保存异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
    }
	
    return result;
}
public ResultDto<String> saveStockOutFlow(String orderNo) {
	
	ResultDto<String> result=null;
    
	try {

		//获取订单信息
		CStockOut cStockOut =stockOutDao.findById(orderNo);
		
		List<CStockOutDetail> cStockOutDetails=stockOutDetailDao.queryStockOutListById(orderNo);
		//
	    List<CStockFlow> cStockFlows=new ArrayList<CStockFlow>();
	    
	    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
		
		if(cStockOutDetails.size()>0)
		{
             for (CStockOutDetail cStockOutDetail : cStockOutDetails) 
             {
            	 //库存流水减少
            	 CStockFlow cStockFlow=new CStockFlow();
            	 cStockFlow.setMaterialcode(cStockOutDetail.getMaterialcode());
            	 cStockFlow.setAmt(cStockOutDetail.getTotalS());
            	 cStockFlow.setMchcode(cStockOut.getMchcode());
            	 cStockFlow.setChangeType("-1");
            	 cStockFlow.setCreateoper(cStockOut.getCreateoper());
            	 cStockFlow.setCreattime(cStockOut.getCreatetime());
            	 cStockFlow.setDeptid(cStockOut.getDeptid());
            	 cStockFlow.setOrderNo(cStockOut.getOutSerialno());
            	 cStockFlow.setOrderType(String.valueOf(TradeType.STOCK_OUT.getType()));
            	 cStockFlows.add(cStockFlow);
            	 //库存汇总更新
            	 CStockInfo cStockInfo=new CStockInfo();
            	 
            	 cStockInfo.setDeptid(cStockOut.getDeptid());
            	 cStockInfo.setMchcode(cStockOut.getMchcode());
            	 cStockInfo.setMaterialcode(cStockOutDetail.getMaterialcode());
            	 cStockInfo.setTotalS(cStockOutDetail.getTotalS());
            	 cStockInfo.setTotalM(new BigDecimal(0));
            	 cStockInfo.setTotalT(new BigDecimal(0));
            	 cStockInfo.setModifytime(cStockOut.getCreatetime());
            	 cStockInfo.setChangeType(-1);
            	 cStockInfo.setIsHave("1");
            	 cStockInfo.setTransferTotalS(new BigDecimal(0));
            	 cStockInfos.add(cStockInfo);	 
            	 
			 }
     		 //库存流水增加
             cStockFlowDao.batchCreate(cStockFlows);
		     log.debug("=========库存流水数据保存成功=======");    
		     
		     //库存汇总更新
		     stockInfoDao.batchUpdate(cStockInfos);
		     log.debug("=========库存汇总数据保存成功=======");    
		     result=new ResultDto<String>(true,"数据保存成功");
		     
		}
				
	} catch (NumberFormatException e) {
					log.error("交易流水数据转化异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
	} catch (Exception e) {
					log.error("交易流水数据保存异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
    }
	
    return result;
}

private ResultDto<String> saveAntiStockOutFlow(String orderNo) {
	// TODO Auto-generated method stub
ResultDto<String> result=null;

try {

	//获取订单信息
	CStockOut cStockOut =stockOutDao.findById(orderNo);
	
	List<CStockOutDetail> cStockOutDetails=stockOutDetailDao.queryStockOutListById(orderNo);
	
    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
	
	if(cStockOutDetails.size()>0)
	{
		
		cStockFlowDao.deleteById(orderNo);
		 log.debug("=========库存流水删除成功=======");  
		stockTotalflowDao.deleteById(orderNo);
		 log.debug("=========中心财务汇总流水表删除成功=======");  
         for (CStockOutDetail cStockOutDetail : cStockOutDetails) 
         {
      
        	 //库存汇总更新
        	 CStockInfo cStockInfo=new CStockInfo();
        	 
        	 cStockInfo.setDeptid(cStockOut.getDeptid());
        	 cStockInfo.setMchcode(cStockOut.getMchcode());
        	 cStockInfo.setMaterialcode(cStockOutDetail.getMaterialcode());
        	 cStockInfo.setTotalS(cStockOutDetail.getTotalS());
        	 cStockInfo.setTotalM(new BigDecimal(0));
        	 cStockInfo.setTotalT(new BigDecimal(0));
        	 cStockInfo.setModifytime(cStockOut.getCreatetime());
        	 cStockInfo.setChangeType(+1);
        	 cStockInfo.setIsHave("1");
        	 cStockInfo.setTransferTotalS(new BigDecimal(0));
        	 cStockInfos.add(cStockInfo);	 
        	 
		 }  
	     
	     //库存汇总更新
	     stockInfoDao.batchUpdate(cStockInfos);
	     log.debug("=========库存汇总数据保存成功=======");    
	     
	}
	
	result=new ResultDto<String>(true,"数据保存成功");
			
} catch (NumberFormatException e) {
				log.error("交易流水数据转化异常"+e.getMessage());
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
} catch (Exception e) {
				log.error("交易流水数据保存异常"+e.getMessage());
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
}

return result;
}
public ResultDto<String> saveBusPurFlow(String orderNo) {
	
	ResultDto<String> result=null;
    
	try {

		//获取订单信息
		CBusPur cBusPur =busPurDao.findById(orderNo);
		
		List<CBusPurDetail> cBusPurDetails=busPurDetailDao.queryPurDetailListById(orderNo);
		//
	    List<CStockFlow> cStockFlows=new ArrayList<CStockFlow>();
	    
	    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
		
		if(cBusPurDetails.size()>0)
		{
             for (CBusPurDetail cBusPurDetail : cBusPurDetails) 
             {
            	 //采购：库存流水增加
            	 CStockFlow cStockFlow=new CStockFlow();
            	 cStockFlow.setMaterialcode(cBusPurDetail.getMaterialId()+"");
            	 cStockFlow.setAmt(cBusPurDetail.getQuantity());
            	 cStockFlow.setMchcode(SystemConst.ZX_MCHCODE);
            	 cStockFlow.setChangeType("1");
            	 cStockFlow.setCreateoper(cBusPur.getCreateoper());
            	 cStockFlow.setCreattime(cBusPur.getCreatetime());
            	 cStockFlow.setDeptid(cBusPur.getDeptid());
            	 cStockFlow.setOrderNo(cBusPur.getPurSerialno());
            	 cStockFlow.setOrderType(String.valueOf(TradeType.STOCK_CG.getType()));
            	 cStockFlows.add(cStockFlow);
            	 //库存汇总更新
            	 CStockInfo cStockInfo=new CStockInfo();
            	 
            	 cStockInfo.setDeptid(cBusPur.getDeptid());
            	 cStockInfo.setMchcode(SystemConst.ZX_MCHCODE);
            	 cStockInfo.setMaterialcode(cBusPurDetail.getMaterialId()+"");
            	 cStockInfo.setTotalS(cBusPurDetail.getQuantity());
            	 cStockInfo.setTotalM(new BigDecimal(0));
            	 cStockInfo.setTotalT(new BigDecimal(0));
            	 cStockInfo.setModifytime(cBusPur.getCreatetime());
            	 cStockInfo.setChangeType(1);
            	 cStockInfo.setTransferTotalS(new BigDecimal(0));
            	 cStockInfos.add(cStockInfo);	 
            	 
			 }
     		 //库存流水增加
             cStockFlowDao.batchCreate(cStockFlows);
		     log.debug("=========采购库存流水数据保存成功=======");    
		     
		     //库存汇总更新
		     stockInfoDao.batchUpdate(cStockInfos);
		     log.debug("=========采购库存汇总数据保存成功=======");    
		     result=new ResultDto<String>(true,"数据保存成功");
		     
		}
				
	} catch (NumberFormatException e) {
					log.error("交易流水数据转化异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
	} catch (Exception e) {
					log.error("交易流水数据保存异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
    }
	
    return result;
}
public ResultDto<String> saveBusSaleFlow(String orderNo) {
	
	ResultDto<String> result=null;
    
	try {

		//获取订单信息
		CBusSale cBusSale =busSaleDao.findById(orderNo);
		
		List<CBusSaleDetail> cBusSaleDetails=busSaleDetailDao.queryDetailListById(orderNo);
		//
	    List<CStockFlow> cStockFlows=new ArrayList<CStockFlow>();
	    
	    List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
		
		if(cBusSaleDetails.size()>0)
		{
             for (CBusSaleDetail cBusSaleDetail : cBusSaleDetails) 
             {
            	 //销售：库存流水减少
            	 CStockFlow cStockFlow=new CStockFlow();
            	 cStockFlow.setMaterialcode(cBusSaleDetail.getMaterialId()+"");
            	 cStockFlow.setAmt(cBusSaleDetail.getQuantity());
            	 cStockFlow.setMchcode(SystemConst.ZX_MCHCODE);
            	 cStockFlow.setChangeType("-1");
            	 cStockFlow.setCreateoper(cBusSale.getCreateoper());
            	 cStockFlow.setCreattime(cBusSale.getCreatetime());
            	 cStockFlow.setDeptid(cBusSale.getDeptid());
            	 cStockFlow.setOrderNo(cBusSale.getSaleSerialno());
            	 cStockFlow.setOrderType(String.valueOf(TradeType.STOCK_XS.getType()));
            	 cStockFlows.add(cStockFlow);
            	 //库存汇总更新
            	 CStockInfo cStockInfo=new CStockInfo();
            	 
            	 cStockInfo.setDeptid(cBusSale.getDeptid());
            	 cStockInfo.setMchcode(SystemConst.ZX_MCHCODE);
            	 cStockInfo.setMaterialcode(cBusSaleDetail.getMaterialId()+"");
            	 cStockInfo.setTotalS(cBusSaleDetail.getQuantity());
            	 cStockInfo.setTotalM(new BigDecimal(0));
            	 cStockInfo.setTotalT(new BigDecimal(0));
            	 cStockInfo.setModifytime(cBusSale.getCreatetime());
            	 cStockInfo.setChangeType(-1);
            	 cStockInfo.setTransferTotalS(new BigDecimal(0));
            	 cStockInfos.add(cStockInfo);	 
            	 
			 }
     		 //库存流水增加
             cStockFlowDao.batchCreate(cStockFlows);
		     log.debug("=========销售库存流水数据保存成功=======");    
		     
		     //库存汇总更新
		     stockInfoDao.batchUpdate(cStockInfos);
		     log.debug("=========销售库存汇总数据保存成功=======");    
		     result=new ResultDto<String>(true,"数据保存成功");
		     
		}
				
	} catch (NumberFormatException e) {
					log.error("交易流水数据转化异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
	} catch (Exception e) {
					log.error("交易流水数据保存异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
    }
	
    return result;
	}
}


