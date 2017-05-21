package com.lq.lss.core.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.constant.NoticeConst;
import com.lq.lss.core.dao.CStockFlowDao;
import com.lq.lss.core.dao.CStockInRepairinfoDao;
import com.lq.lss.core.dao.StockInDao;
import com.lq.lss.core.dao.StockInDetailDao;
import com.lq.lss.core.service.CStockFlowService;
import com.lq.lss.core.service.StockInService;
import com.lq.lss.core.service.TNoticeService;
import com.lq.lss.dto.CStockInRepairinfoDto;
import com.lq.lss.dto.StockInDetailDto;
import com.lq.lss.enums.CheckStatus;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.CStockIn;
import com.lq.lss.model.CStockInDetail;
import com.lq.lss.model.CStockInRepairinfo;
import com.lq.lss.utils.exception.jta.BusinessException;
import com.lq.pager.PageParam;
import com.lq.pager.Pager;

/**
 * 
 * @author Eric
 *
 */
@Service
public class StockInServiceImpl extends EasyUIServiceBase<CStockIn, String, StockInDao> implements StockInService {

	
	@Resource
	private StockInDetailDao stockInDetailDao;
	
	@Resource
	private CStockInRepairinfoDao stockInRepairinfoDao;
	
	@Resource
	private TNoticeService tNoticeService;
	
	

	public void setStockInDetailDao(StockInDetailDao stockInDetailDao) {
		this.stockInDetailDao = stockInDetailDao;
	}
	
	@Resource
	private CStockFlowDao cStockFlowDao;

	public void setCStockFlowDao(CStockFlowDao stockInDetailDao) {
		this.cStockFlowDao = cStockFlowDao;
	}
	
	@Resource
    private CStockFlowService cStockFlowService;

	@Override
	public ResultDto<String> deleteStockInByIdRdTx(String inSerialno, String deptId) 
			throws BusinessException {
   
		ResultDto<String> result=null;
    
	try {
		
		CStockIn stockIn=modelDao.findById(inSerialno);
		
		if( stockIn !=null)
		{
			
			if(!String.valueOf(CheckStatus.INIT.getCode()).equals(stockIn.getStatus()))
		    {
		    	return new ResultDto<String>(false,"已审核单据不可以删除");
		    }
			
			stockInRepairinfoDao.deleteById(inSerialno);
			log.debug("=========入库维修数据删除成功========");
			
			stockInDetailDao.deleteById(inSerialno);
			
			log.debug("=========入库申请从表数据删除成功========");
			   
			modelDao.deleteById(inSerialno);
			log.debug("========入库申请主表数据删除成功========");
			    
			    result=new ResultDto<String>(true,"删除数据成功");
		}else
		{ 
			    result=new ResultDto<String>(false,"不存在该条数据");
		}

		
	} catch (NumberFormatException e) {
					log.error("入库申请数据转化异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
	} catch (Exception e) {
					log.error("入库申请数据删除异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
    }
	
    return result;
	}
	

	@Override
	public ResultDto<String> saveStockInRdTx(CStockIn cStockIn,
			List<StockInDetailDto> cStockInDetails,
			List<CStockInRepairinfoDto> repairinfoDtos)throws BusinessException {

		ResultDto<String> result=null;
		try 
		{

			//入库主表增加
			modelDao.create(cStockIn);
			
			log.debug("---------入库申请主表数据保存成功--------");

			List<CStockInDetail> cStockInDetail=new ArrayList<CStockInDetail>();
			
	
			
			if(cStockInDetails.size()>0)
			{
				 int i=0;
                 for (StockInDetailDto detailList : cStockInDetails) 
                 {
                	 String autoNo=cStockIn.getInSerialno()+i++;
                	 CStockInDetail nCStockInDetail=new CStockInDetail();
                	 nCStockInDetail.setId(autoNo);
                	 nCStockInDetail.setInSerialno(cStockIn.getInSerialno().toString());
                	 nCStockInDetail.setMaterialCode(detailList.getMaterialCode());
                	 nCStockInDetail.setTotalS(detailList.getTotalS());
                	 nCStockInDetail.setTotalM(new BigDecimal(detailList.getTotalM()));
                	 cStockInDetail.add(nCStockInDetail);
				 }
                 
         		//入库明细增加
                 stockInDetailDao.batchCreate(cStockInDetail);
                 
			     log.debug("=========入库申请从表数据保存成功=======");
			     
			        //添加维修项
					List<CStockInRepairinfo> stockInRepairinfos=new ArrayList<CStockInRepairinfo>();
					
					if(repairinfoDtos!=null && repairinfoDtos.size()>0)
					{
                      for (CStockInRepairinfoDto detailList : repairinfoDtos) 
                      {
                    	     CStockInRepairinfo stockInRepairinfo=new CStockInRepairinfo();
                    	     stockInRepairinfo.setReceiptDetailId(detailList.getReceiptDetailId());
                    	     stockInRepairinfo.setRepairId(detailList.getRepairId());
                     	     if(StringUtils.hasLength(detailList.getRepairFee()))
                     	     {
                     	    	 Double fee=Double.valueOf(detailList.getRepairFee());
                     	    	 stockInRepairinfo.setRepairFee(new BigDecimal(fee));
                     	     }
                     	     stockInRepairinfo.setInSerialno(cStockIn.getInSerialno().toString());
                     	     stockInRepairinfos.add(stockInRepairinfo);
						 }
                         stockInRepairinfoDao.batchCreate(stockInRepairinfos);
					     log.debug("=========入库维修数据保存成功=======");
					}
			     
			     
			     //保存库存流水表
			     //cStockFlowService.saveStockFlow(TradeType.STOCK_IN, cStockFlowDtos);
			}
				

			result=new ResultDto<String>(true,"数据保存成功");

			
		} catch (NumberFormatException e){
						log.error("数据转化异常"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		} catch (Exception e) {
						log.error("数据保存失败"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		}

	    return result;
	}

	
	
	@Override
	public ResultDto<String> updateStockInRdTx(CStockIn cStockIn,
			List<StockInDetailDto> cStockInDetails,List<CStockInRepairinfoDto> repairinfoDtos) {
		ResultDto<String> result=null;
		try 
		{
			
			CStockIn stockIn=modelDao.findById(cStockIn.getInSerialno());
			
			if( stockIn !=null)
			{
				if(String.valueOf(CheckStatus.INIT.getConfirm()).equals(stockIn.getStatus()))
			    {
			    	return new ResultDto<String>(false,"已审核单据不可以修改");
			    }
				//入库申请主表增加
				modelDao.update(cStockIn);
				
				log.debug("---------入库申请主表数据修改成功--------");
				
				stockInDetailDao.deleteById(cStockIn.getInSerialno());
				
				log.debug("=========入库申请从表数据删除成功========");
				
			
				List<CStockInDetail> cStockInDetail=new ArrayList<CStockInDetail>();
				
				if(cStockInDetails.size()>0)
				{
					 int i=0;
	                 for (StockInDetailDto detailList : cStockInDetails) 
	                 {
	                	 CStockInDetail nCStockInDetail=new CStockInDetail();
	                	 String autoNo=cStockIn.getInSerialno()+i++;
	                	 
	                	 nCStockInDetail.setId(autoNo);
	                	 nCStockInDetail.setInSerialno(cStockIn.getInSerialno().toString());
	                	 nCStockInDetail.setMaterialCode(detailList.getMaterialCode());
	                	 nCStockInDetail.setTotalS(detailList.getTotalS());
	                	 nCStockInDetail.setTotalM(new BigDecimal(detailList.getTotalM()));
	                	 cStockInDetail.add(nCStockInDetail);
	                	 
					 }
	                 
	         		//入库明细增加
	                 stockInDetailDao.batchCreate(cStockInDetail);
	                 
				     log.debug("=========入库申请从表数据保存成功=======");
				}
				
				
				stockInRepairinfoDao.deleteById(cStockIn.getInSerialno());
				log.debug("---------入库维修数据删除成功--------");
				
				//添加维修项
				List<CStockInRepairinfo> stockInRepairinfos=new ArrayList<CStockInRepairinfo>();
				if(repairinfoDtos!=null && repairinfoDtos.size()>0)
				{
                  for (CStockInRepairinfoDto detailList : repairinfoDtos) 
                  {
                	     CStockInRepairinfo stockInRepairinfo=new CStockInRepairinfo();
                	     stockInRepairinfo.setReceiptDetailId(detailList.getReceiptDetailId());
                	     stockInRepairinfo.setRepairId(detailList.getRepairId());
                 	     if(StringUtils.hasLength(detailList.getRepairFee()))
                 	     {
                 	    	 Double fee=Double.valueOf(detailList.getRepairFee());
                 	    	 stockInRepairinfo.setRepairFee(new BigDecimal(fee));
                 	     }
                 	     stockInRepairinfo.setInSerialno(cStockIn.getInSerialno().toString());
                 	     stockInRepairinfos.add(stockInRepairinfo);
					 }
                     stockInRepairinfoDao.batchCreate(stockInRepairinfos);
				     log.debug("=========入库维修数据保存成功=======");
				}
				   
				result=new ResultDto<String>(true,"数据更新成功");
				
			}else
			{ 
				result=new ResultDto<String>(false,"不存在入库数据");
			}

			
		} catch (NumberFormatException e){
						log.error("数据转化异常"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		} catch (Exception e) {
						log.error("数据保存失败"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		}
		return result;
	}


	@Override
	public ResultDto<String> auditStockInByIdRdTx(CStockIn cStockIn) {
		
		ResultDto<String> result=null;
		try 
		{
			CStockIn stockIn2=modelDao.findById(cStockIn.getInSerialno());
			//根据状态进行不同的操作
			 String rejectCode=String.valueOf(CheckStatus.REJECT.getCode());
			 
			 if(rejectCode.equals(cStockIn.getStatus()))
			 {
				  String title="";
				  if(cStockIn.getInSerialno().contains("RK"))
				  {
					  title=NoticeConst.NOTICE_RK_TITLE;
				  }else{
					  title=NoticeConst.NOTICE_ZC_TITLE;
				  }
				  Integer fromId=Integer.parseInt(cStockIn.getUserId()+"");
				  Integer toId=Integer.parseInt(stockIn2.getOperId());
				  tNoticeService.createNotice(fromId, cStockIn.getDeptid(), toId, title, cStockIn.getInSerialno());
			 }
			
			if (String.valueOf(CheckStatus.OK.getCode()).equals(cStockIn.getStatus()))
			{
				
				result=cStockFlowService.saveStockFlowRdTx(TradeType.STOCK_IN,cStockIn.getInSerialno());
				
				if (!result.isSuccess()){
					//操作流水失败
					throw new BusinessException(result.getErrorMsg());
				}
				log.debug("---------入库申请审核流水处理--------");
	
			}
			
			modelDao.update(cStockIn);
			
			result=new ResultDto<String>(true,"入库数据审核成功");


		} catch (Exception e) {
						log.error("数据保存失败"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		}

	    return result;
	}


	@Override
	public Pager<CStockIn> pagerList(PageParam pageParam, CStockIn cStockIn) {
		// TODO Auto-generated method stub
		return modelDao.findByPage("pagerListinPage", "incount", pageParam, cStockIn);
	}


	@Override
	public ResultDto<String> antiAuditStockInByIdRdTx(CStockIn cStockIn) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		CStockIn stockIn2=modelDao.findById(cStockIn.getInSerialno());
		try 
		{

			//根据状态进行不同的操作
			
			
			if (stockIn2.getStatus().equals("0"))
			{
				
				result=cStockFlowService.saveStockFlowRdTx(TradeType.ANTI_AUDIT_STOCK_IN,cStockIn.getInSerialno());
				
				if (!result.isSuccess()){
					//操作流水失败
					throw new BusinessException(result.getErrorMsg());
				}
				log.debug("---------入库申请审核流水处理--------");
	
			}
			
			modelDao.update(cStockIn);
			
			result=new ResultDto<String>(true,"入库数据审核成功");


		} catch (Exception e) {
						log.error("数据保存失败"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		}

	    return result;
	}


	@Override
	public ResultDto<String> auditStockTemporaryInByIdRdTx(CStockIn cStockIn) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try 
		{
			CStockIn stockIn2=modelDao.findById(cStockIn.getInSerialno());
			//根据状态进行不同的操作
			 String rejectCode=String.valueOf(CheckStatus.REJECT.getCode());
			 
			 if(rejectCode.equals(cStockIn.getStatus()))
			 {
				  String title="";
				  if(cStockIn.getInSerialno().contains("RK"))
				  {
					  title=NoticeConst.NOTICE_RK_TITLE;
				  }else{
					  title=NoticeConst.NOTICE_ZC_TITLE;
				  }
				  Integer fromId=Integer.parseInt(cStockIn.getUserId()+"");
				  Integer toId=Integer.parseInt(stockIn2.getOperId());
				  tNoticeService.createNotice(fromId, cStockIn.getDeptid(), toId, title, cStockIn.getInSerialno());
			 }
			
			if (String.valueOf(CheckStatus.OK.getCode()).equals(cStockIn.getStatus()))
			{
				
				result=cStockFlowService.saveStockFlowRdTx(TradeType.STOCK_TEMPORARY_IN,cStockIn.getInSerialno());
				
				if (!result.isSuccess()){
					//操作流水失败
					throw new BusinessException(result.getErrorMsg());
				}
				log.debug("--------暂存申请审核流水处理--------");
	
			}
			
			modelDao.update(cStockIn);
			
			result=new ResultDto<String>(true,"暂存数据审核成功");


		} catch (Exception e) {
						log.error("数据保存失败"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		}

	    return result;
	}


	@Override
	public ResultDto<String> antiAuditStockTemporaryInByIdRdTx(CStockIn cStockIn) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		CStockIn stockIn2=modelDao.findById(cStockIn.getInSerialno());
		try 
		{

			//根据状态进行不同的操作
			
			
			if (stockIn2.getStatus().equals("0"))
			{
				
				result=cStockFlowService.saveStockFlowRdTx(TradeType.ANTI_STOCK_TEMPORARY_IN,cStockIn.getInSerialno());
				
				if (!result.isSuccess()){
					//操作流水失败
					throw new BusinessException(result.getErrorMsg());
				}
				log.debug("---------暂存反审核流水处理--------");
	
			}
			
			modelDao.update(cStockIn);
			
			result=new ResultDto<String>(true,"暂存反审核成功");


		} catch (Exception e) {
						log.error("数据保存失败"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		}

	    return result;
	}
	
	
}
