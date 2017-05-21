package com.lq.lss.core.service.impl;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.constant.NoticeConst;
import com.lq.lss.core.dao.StockInfoDao;
import com.lq.lss.core.dao.StockTransferDao;
import com.lq.lss.core.dao.StockTransferDetailDao;
import com.lq.lss.core.service.CStockFlowService;
import com.lq.lss.core.service.StockTransferService;
import com.lq.lss.core.service.TNoticeService;
import com.lq.lss.dto.CStockSendDetailDto;
import com.lq.lss.dto.CStockSendDto;
import com.lq.lss.enums.CheckStatus;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.CStockIn;
import com.lq.lss.model.CStockInfo;
import com.lq.lss.model.CStockTemporary;
import com.lq.lss.model.CStockTransfer;
import com.lq.lss.model.CStockTransferDetail;
import com.lq.lss.utils.exception.jta.BusinessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

/**
 * @author lanbo
 */
@Service
public class StockTransferServiceImpl extends EasyUIServiceBase<CStockTransfer, String, StockTransferDao> implements StockTransferService {

    @Resource
    private StockTransferDetailDao stockTransferDetailDao;
    @Resource
    private CStockFlowService cStockFlowService;
	@Resource
	private StockInfoDao stockInfoDao;
	@Resource
	private TNoticeService tNoticeService;

    @Override

    public ResultDto<String> saveStockTransferRdTx(CStockTransfer cStockTransfer, List<CStockTransferDetail> cStockTransferDetails) throws BusinessException {
        ResultDto<String> resultDto = new ResultDto<String>();

        
//        cStockTransfer.setcStockTransferDetail(cStockTransferDetails);
//        resultDto = checkStockSum(cStockTransfer);
//		if (!resultDto.isSuccess()) {
//			return resultDto;
//		}
        modelDao.create(cStockTransfer);
        stockTransferDetailDao.batchCreate(cStockTransferDetails);

        resultDto.setSuccess(true);

        log.info("======新增调拨单=====");
        return resultDto;
    }

    @Override
    public ResultDto<String> updateStockTransferRdTx(CStockTransfer cStockTransfer, List<CStockTransferDetail> cStockTransferDetails) {
        ResultDto<String> resultDto = new ResultDto<String>();
        modelDao.update(cStockTransfer);
        stockTransferDetailDao.deleteById(cStockTransfer.getTsfSerialno());
        stockTransferDetailDao.batchCreate(cStockTransferDetails);
        resultDto.setSuccess(true);
        log.info("======更新调拨单=====");
        return resultDto;
    }

    @Override
    public void deleteStockTransferByIdsRdTx(String transSerialNo) {

        stockTransferDetailDao.deleteById(transSerialNo);
        modelDao.deleteById(transSerialNo);
        log.info("======删除调拨单=====");

    }

	@Override
	public ResultDto<String> auditStockTransferOutByIdRdTx(CStockTransfer CStockTransfer) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try 
		{
			CStockTransfer CStockTransfer1=new CStockTransfer();
			String tsfserialno=CStockTransfer.getTsfSerialno();
			CStockTransfer CStockTransfer2=modelDao.findById(tsfserialno);
			String deptid=String.valueOf(CStockTransfer.getFromDeptid());
			
			if(CStockTransfer2!=null && String.valueOf(CStockTransfer2.getFromDeptid()).equals(deptid))
			{
				 String rejectCode=String.valueOf(CheckStatus.REJECT.getCode());
				 
				 if(rejectCode.equals(CStockTransfer.getStatus()))
				 {
					  String title=NoticeConst.NOTICE_NBDC_TITLE;
					  Integer fromId=Integer.parseInt(CStockTransfer.getAuditingoper());
					  Integer toId=Integer.parseInt(CStockTransfer2.getCreateoper());
					  Integer deptId=CStockTransfer.getFromDeptid();
					  tNoticeService.createNotice(fromId, deptId, toId, title, tsfserialno);
				 }    
				 
				 String status=CStockTransfer2.getStatus();
				    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
				    {
				    	return new ResultDto<String>(false,"已审核数据不可以再审核");
				    }
				  //根据状态进行不同的操作
				    if (String.valueOf(CheckStatus.OK.getCode()).equals(CStockTransfer.getStatus()))
					{
						
						result=cStockFlowService.saveStockFlowRdTx(TradeType.STOCK_TRANSFER_OUT, tsfserialno);
						
						if (!result.isSuccess()){
							//操作流水失败
							throw new BusinessException(result.getErrorMsg());
						}
						log.debug("---------入库申请审核流水处理--------");
			
					}
				    CStockTransfer1.setTsfSerialno(tsfserialno);
				    CStockTransfer1.setStatus(CStockTransfer.getStatus());
					
				    CStockTransfer1.setAuditingoper(CStockTransfer.getAuditingoper());
				    CStockTransfer1.setAuditingtime(new Date());
					modelDao.update(CStockTransfer1);
					
					result=new ResultDto<String>(true,"入库数据审核成功");
			}else{
				 result=new ResultDto<String>(false,"该数据不在本中心不允许修改");
			}
			
			
			
			


		} catch (Exception e) {
						log.error("数据保存失败"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		}

	    return result;
	}
	
	
	
	
	@Override
	public ResultDto<String> auditStockTransferByIdRdTx(CStockTransfer CStockTransfer) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try 
		{
			CStockTransfer CStockTransfer1=new CStockTransfer();
			String tsfserialno=CStockTransfer.getTsfSerialno();
			CStockTransfer CStockTransfer2=modelDao.findById(tsfserialno);
			String deptid=String.valueOf(CStockTransfer.getFromDeptid());
			
			if(CStockTransfer2!=null && String.valueOf(CStockTransfer2.getFromDeptid()).equals(deptid))
			{
				 String status=CStockTransfer2.getStatus();
				    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
				    {
				    	return new ResultDto<String>(false,"已审核数据不可以再审核");
				    }
				  //根据状态进行不同的操作
				    if (String.valueOf(CheckStatus.OK.getCode()).equals(CStockTransfer.getStatus()))
					{
						
						result=cStockFlowService.saveStockFlowRdTx(TradeType.TRANSFER_MUTUAL, tsfserialno);
						
						if (!result.isSuccess()){
							//操作流水失败
							throw new BusinessException(result.getErrorMsg());
						}
						log.debug("---------入库申请审核流水处理--------");
			
					}
				    CStockTransfer1.setTsfSerialno(tsfserialno);
				    CStockTransfer1.setStatus(CStockTransfer.getStatus());
					
				    CStockTransfer1.setAuditingoper(CStockTransfer.getAuditingoper());
				    CStockTransfer1.setAuditingtime(new Date());
					modelDao.update(CStockTransfer1);
					
					result=new ResultDto<String>(true,"入库数据审核成功");
			}else{
				 result=new ResultDto<String>(false,"该数据不在本中心不允许修改");
			}
			
			
			
			


		} catch (Exception e) {
						log.error("数据保存失败"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		}

	    return result;
	}
	
	
	@Override
	public ResultDto<String> auditStockTransferInByIdRdTx(CStockTransfer CStockTransfer) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try 
		{
			CStockTransfer CStockTransfer1=new CStockTransfer();
			String tsfserialno=CStockTransfer.getTsfSerialno();
			CStockTransfer CStockTransfer2=modelDao.findById(tsfserialno);
			String deptid=String.valueOf(CStockTransfer.getFromDeptid());
			
			if(CStockTransfer2!=null && String.valueOf(CStockTransfer2.getFromDeptid()).equals(deptid))
			{
				 String rejectCode=String.valueOf(CheckStatus.REJECT.getCode());
				 
				 if(rejectCode.equals(CStockTransfer.getStatus()))
				 {
					  String title=NoticeConst.NOTICE_NBDR_TITLE;
					  Integer fromId=Integer.parseInt(CStockTransfer.getAuditingoper());
					  Integer toId=Integer.parseInt(CStockTransfer2.getCreateoper());
					  Integer deptId=CStockTransfer.getFromDeptid();
					  tNoticeService.createNotice(fromId, deptId, toId, title, tsfserialno);
				 }    
				
				 String status=CStockTransfer2.getStatus();
				    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
				    {
				    	return new ResultDto<String>(false,"已审核数据不可以再审核");
				    }
				  //根据状态进行不同的操作
				    if (String.valueOf(CheckStatus.OK.getCode()).equals(CStockTransfer.getStatus()))
					{
						
						result=cStockFlowService.saveStockFlowRdTx(TradeType.STOCK_TRANSFER_IN, tsfserialno);
						
						if (!result.isSuccess()){
							//操作流水失败
							throw new BusinessException(result.getErrorMsg());
						}
						log.debug("---------入库申请审核流水处理--------");
			
					}
				    CStockTransfer1.setTsfSerialno(tsfserialno);
				    CStockTransfer1.setStatus(CStockTransfer.getStatus());
					
				    CStockTransfer1.setAuditingoper(CStockTransfer.getAuditingoper());
				    CStockTransfer1.setAuditingtime(new Date());
					modelDao.update(CStockTransfer1);
					
					result=new ResultDto<String>(true,"入库数据审核成功");
			}else{
				 result=new ResultDto<String>(false,"该数据不在本中心不允许修改");
			}
			
			
			
			


		} catch (Exception e) {
						log.error("数据保存失败"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		}

	    return result;
	}
	
	/**
	 * 检查库存数据
	 * @return
	 */
	protected ResultDto<String> checkStockSum(CStockTransfer cStockTransfer){
	
		String deptid=cStockTransfer.getFromDeptid()+"";
	
	
		String mchcode="100000";
		CStockInfo cStockInfo=new CStockInfo();
		cStockInfo.setDeptid(Integer.parseInt(deptid));
		cStockInfo.setMchcode(mchcode);
		List<CStockInfo> cStockInfos=stockInfoDao.findStockTotals(cStockInfo);
		
		List<CStockTransferDetail> cStockTransferDetails=cStockTransfer.getcStockTransferDetail();
		
		StringBuilder sdr=new StringBuilder();
		
	    boolean isPass=true;
		
		if(cStockInfos!=null && cStockInfos.size()>0)
		{
			Double totalS;
			Double num;
			Long dif=0l;
			String materialcode;
			String code;
			String name="";
			String unit="";
			for (CStockTransferDetail cStockTransferDetail : cStockTransferDetails) {
				   totalS=cStockTransferDetail.getTotalS().doubleValue();
				   if(totalS<=0){
			    		  return new ResultDto<String>(false,"物资数量必须大于0");
			       }
				   materialcode=cStockTransferDetail.getMaterialCode();
				   unit=cStockTransferDetail.getUnit();
				   name=cStockTransferDetail.getName();
				   for (CStockInfo stockInfo : cStockInfos) {
					      code=stockInfo.getMaterialcode();
					      if(code.equals(materialcode)){
					    	  num=Double.parseDouble(stockInfo.getTotalS()+"");
					    	  if(totalS>num){
					    		  dif=Long.parseLong(String.format("%.0f", totalS-num));
					    		  sdr.append("物资【"+name+"】库存不足,需补充"+dif+""+unit+";");
					    		  isPass=false;
					    	  }
					    	  break;
					      }
					      
				   }
			}
		}else{
			return new ResultDto<String>(false,"商户库存信息未初始化");
		}
		
		return new ResultDto<String>(isPass,sdr.toString());
	}

	@Override
	public ResultDto<String> antiAuditStockTransferOutByIdRdTx(CStockTransfer cStockTransfer) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		CStockTransfer cStockTransfer2=modelDao.findById(cStockTransfer.getTsfSerialno());
		try 
		{

			//根据状态进行不同的操作
			
			
			if (cStockTransfer2.getStatus().equals("0"))
			{
				
				result=cStockFlowService.saveStockFlowRdTx(TradeType.ANTI_STOCK_TRANSFER_OUT,cStockTransfer.getTsfSerialno());
				
				if (!result.isSuccess()){
					//操作流水失败
					throw new BusinessException(result.getErrorMsg());
				}
				log.debug("---------中心内部调出申请审核流水处理--------");
	
			}
			
			modelDao.update(cStockTransfer);
			
			result=new ResultDto<String>(true,"中心内部调出审核成功");


		} catch (Exception e) {
						log.error("数据保存失败"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		}

	    return result;
	}


	
	@Override
	public ResultDto<String> antiAuditStockTransferInByIdRdTx(CStockTransfer cStockTransfer) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		CStockTransfer cStockTransfer2=modelDao.findById(cStockTransfer.getTsfSerialno());
		try 
		{

			//根据状态进行不同的操作
			
			
			if (cStockTransfer2.getStatus().equals("0"))
			{
				
				result=cStockFlowService.saveStockFlowRdTx(TradeType.ANTI_STOCK_TRANSFER_IN,cStockTransfer.getTsfSerialno());
				
				if (!result.isSuccess()){
					//操作流水失败
					throw new BusinessException(result.getErrorMsg());
				}
				log.debug("---------中心内部调出申请审核流水处理--------");
	
			}
			
			modelDao.update(cStockTransfer);
			
			result=new ResultDto<String>(true,"中心内部调出审核成功");


		} catch (Exception e) {
						log.error("数据保存失败"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		}

	    return result;
	}

}
