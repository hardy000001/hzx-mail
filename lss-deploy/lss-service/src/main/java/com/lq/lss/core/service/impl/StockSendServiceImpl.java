package com.lq.lss.core.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.constant.NoticeConst;
import com.lq.lss.core.dao.StockCenterTransferDao;
import com.lq.lss.core.dao.StockCenterTransferDetailDao;
import com.lq.lss.core.dao.StockInfoDao;
import com.lq.lss.core.dao.StockSendDao;
import com.lq.lss.core.dao.StockSendDetailDao;
import com.lq.lss.core.service.CStockFlowService;
import com.lq.lss.core.service.StockSendService;
import com.lq.lss.core.service.TNoticeService;
import com.lq.lss.dto.CStockCenterTransferDetailDto;
import com.lq.lss.dto.CStockCenterTransferDto;
import com.lq.lss.dto.CStockSendDetailDto;
import com.lq.lss.dto.CStockSendDto;
import com.lq.lss.enums.CheckStatus;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.CStockCenterTransfer;
import com.lq.lss.model.CStockIn;
import com.lq.lss.model.CStockInfo;
import com.lq.lss.model.CStockSend;
import com.lq.lss.model.CStockSendDetail;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.exception.jta.BusinessException;
import com.lq.util.TimeUtil;

/**
 * 
 * @author ch
 *
 */
@Service
public class StockSendServiceImpl extends EasyUIServiceBase<CStockSend, String, StockSendDao> implements StockSendService {

	@Resource
	StockCenterTransferDetailDao centerTransferDetailDao;
	@Resource
	StockCenterTransferDao centerTransferDao;
	@Resource
    private CStockFlowService cStockFlowService;
	@Resource
	private StockInfoDao stockInfoDao;
	@Resource
	private StockSendDao stockSendDao;
	@Resource
	private StockSendDetailDao  stockSendDetailDao;
	@Resource
	private TNoticeService tNoticeService;
	@Override
	public ResultDto<String> saveCStockSendRdTx(CStockSendDto cStockSendDto, SessionUser user) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
//			result = checkStockSum(cStockSendDto);
//			if (!result.isSuccess()) {
//				return result;
//			} 验证库存
			
			CStockSend cStockSend=new CStockSend();
			cStockSend.setSendSerialno(cStockSendDto.getSendSerialno());
			cStockSend.setDeptid(user.getCenterId());
			cStockSend.setMchcode(100000+"");
			cStockSend.setHtcode(cStockSendDto.getHtcode());
			cStockSend.setCarNo(cStockSendDto.getCarNo());
			cStockSend.setCarDriver(cStockSendDto.getCarDriver());
			cStockSend.setLessee(cStockSendDto.getLessee());
			cStockSend.setRenter(cStockSendDto.getRenter());
			cStockSend.setZxFee(new BigDecimal(cStockSendDto.getZxFee()));
			cStockSend.setTransportFee(new BigDecimal(cStockSendDto.getTransportFee()));
			cStockSend.setOtherFee(new BigDecimal(cStockSendDto.getOtherFee()));
			cStockSend.setStatus("1");
			cStockSend.setTradeinfo("");
			cStockSend.setCreateoper(user.getUserId()+"");
			cStockSend.setCreatetime(new Date());
			Date cdate=TimeUtil.get().parseTime(cStockSendDto.getSendtime());
			cStockSend.setSendtime(cdate);
			cStockSend.setRemark(cStockSendDto.getRemark());
			modelDao.create(cStockSend);
			log.debug("---------发料主表数据保存成功--------");
			
			List<CStockSendDetailDto> cStockSendDetailDtos=cStockSendDto.getcStockSendDetailDto();
			List<CStockSendDetail> sendDetails=new ArrayList<CStockSendDetail>();
		
				if(cStockSendDetailDtos.size()>0){
					for (CStockSendDetailDto detailList : cStockSendDetailDtos) {
						CStockSendDetail  stocksendDetail=new CStockSendDetail();
						stocksendDetail.setSendSerialno(cStockSendDto.getSendSerialno());
						stocksendDetail.setMaterialcode(detailList.getMaterialcode());
						stocksendDetail.setTotalM(new BigDecimal(detailList.getTotalM()));
						stocksendDetail.setTotalT(new BigDecimal(0));
						stocksendDetail.setTotalS(new BigDecimal(detailList.getTotalS()));
						//单价目前没取
//						cStockLeaseDetail.setPrice(new BigDecimal(0));
						stocksendDetail.setUnit(detailList.getUnit());
						sendDetails.add(stocksendDetail);
					}
					stockSendDetailDao.batchCreate(sendDetails);
					log.debug("=========发料从表数据保存成功=======");
				}
				result=new ResultDto<String>(true,"数据保存成功");
			
		} catch (NumberFormatException e) {
			// TODO: handle exception
			log.error("数据转化异常"+e.getMessage());
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			log.error("数据保存失败"+e.getMessage());
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		
		return result;
	}
	@Override
	public ResultDto<String> deleteCStockSend(String sendSerialno,String deptId) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
			CStockSend csStockSend=stockSendDao.findCStockSend(sendSerialno);
			if(csStockSend!=null && String.valueOf(csStockSend.getDeptid()).equals(deptId)){
				String status=csStockSend.getStatus();
				if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
			    {
			    	return new ResultDto<String>(false,"已审核数据不可以删除");
			    }
				stockSendDetailDao.deleteById(sendSerialno);
			
				stockSendDao.deleteById(sendSerialno);
			
			result=new ResultDto<String>(true,"删除数据成功");
		}else
		{ 
		    result=new ResultDto<String>(false,"该数据不在本中心不允许删除");
		}
		
		} catch (NumberFormatException e) {
		log.error("中心调拨删除申请数据转化异常"+e.getMessage());
		e.printStackTrace();
		throw new BusinessException(e.getMessage());
		} catch (Exception e) {
				log.error("中心调拨数据删除异常"+e.getMessage());
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
		}
			return result;
	}
	@Override
	public ResultDto<String> updateCStockSendRdTx(CStockSendDto cStockSendDto, SessionUser user) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
			
			String  sendSerialno = cStockSendDto.getSendSerialno();
			CStockSend stockSend2 =  stockSendDao.findById(sendSerialno);
			String deptid=String.valueOf(cStockSendDto.getDeptid());
			if(stockSend2!=null && String.valueOf(stockSend2.getDeptid()).equals(deptid))
			{	
				String status=stockSend2.getStatus();
			    if(String.valueOf(CheckStatus.INIT.getConfirm()).equals(status))
			    {
			    	return new ResultDto<String>(false,"已审核数据不可以修改");
			    }
			    stockSendDetailDao.deleteById(sendSerialno);
				log.debug("=============发料从表数据删除成功==============");
				List<CStockSendDetailDto> cStockSendDetailDtos=cStockSendDto.getcStockSendDetailDto();
				List<CStockSendDetail> sendDetails=new ArrayList<CStockSendDetail>();
				if(cStockSendDetailDtos.size()>0){
					for (CStockSendDetailDto detailList : cStockSendDetailDtos) {
						CStockSendDetail  stocksendDetail=new CStockSendDetail();
						stocksendDetail.setSendSerialno(cStockSendDto.getSendSerialno());
						stocksendDetail.setMaterialcode(detailList.getMaterialcode());
						stocksendDetail.setTotalM(new BigDecimal(detailList.getTotalM()));
						stocksendDetail.setTotalT(new BigDecimal(0));
						stocksendDetail.setTotalS(new BigDecimal(detailList.getTotalS()));
						//单价目前没取
//						cStockLeaseDetail.setPrice(new BigDecimal(0));
						stocksendDetail.setUnit(detailList.getUnit());
						sendDetails.add(stocksendDetail);
					}
					stockSendDetailDao.batchCreate(sendDetails);
					log.debug("=========发料从表数据修改成功=======");
				}
				CStockSend cStockSend=new CStockSend();
				cStockSend.setSendSerialno(cStockSendDto.getSendSerialno());
				cStockSend.setCarNo(cStockSendDto.getCarNo());
				cStockSend.setCarDriver(cStockSendDto.getCarDriver());
				cStockSend.setLessee(cStockSendDto.getLessee());
				cStockSend.setRenter(cStockSendDto.getRenter());
				cStockSend.setZxFee(new BigDecimal(cStockSendDto.getZxFee()));
				cStockSend.setTransportFee(new BigDecimal(cStockSendDto.getTransportFee()));
				cStockSend.setOtherFee(new BigDecimal(cStockSendDto.getOtherFee()));
				cStockSend.setStatus("1");
				cStockSend.setTradeinfo("");
				Date cdate=TimeUtil.get().parseTime(cStockSendDto.getSendtime());
				cStockSend.setSendtime(cdate);
				cStockSend.setRemark(cStockSendDto.getRemark());
				modelDao.update(cStockSend);
				log.debug("---------发料主表数据修改成功--------");
				result=new ResultDto<String>(true,"数据修改成功");	
			}else
			{ 
		        result=new ResultDto<String>(false,"该数据不在本中心不允许修改");
			}
			
		} catch (NumberFormatException e){
			log.error("发料主数据转化异常"+e.getMessage());
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		} catch (Exception e) {
					log.error("发料主数据修改异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
		}
		
		
		return result;
	}
	
	@Override
	public ResultDto<String> auditCStockSendByIdRdTx(CStockSendDto cStockSendDto) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try 
		{
			
			CStockSend cStockSend=new CStockSend();
			String sendSerialno=cStockSendDto.getSendSerialno();
			CStockSend cStockSend2=modelDao.findById(sendSerialno);
			String deptid=String.valueOf(cStockSendDto.getDeptid());
			//根据状态进行不同的操作
			String rejectCode=String.valueOf(CheckStatus.REJECT.getCode());
			 
			 if(rejectCode.equals(cStockSendDto.getStatus()))
			 {
				  String title=NoticeConst.NOTICE_FL_TITLE;
				  Integer fromId=Integer.parseInt(cStockSendDto.getUserId()+"");
				  Integer toId=Integer.parseInt(cStockSend2.getCreateoper());
				  tNoticeService.createNotice(fromId, cStockSendDto.getDeptid(), toId, title, sendSerialno);
			 }
		
			if(cStockSend2!=null && String.valueOf(cStockSend2.getDeptid()).equals(deptid))
			{
				cStockSendDto.setMchcode(cStockSend2.getMchcode());
				 String status=cStockSend2.getStatus();
				    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
				    {
				    	return new ResultDto<String>(false,"已审核数据不可以再审核");
				    }
				   List<CStockSendDetail> cStockSendDetails=stockSendDetailDao.findByCStockSendDetail(sendSerialno);
				   List<CStockSendDetailDto> cStockSendDetailDtos=new ArrayList<CStockSendDetailDto>();
				   for (CStockSendDetail cStockSendDetail : cStockSendDetails) 
				   {
					   CStockSendDetailDto cStockSendDetailDto=new CStockSendDetailDto();
					   cStockSendDetailDto.setMaterialcode(cStockSendDetail.getMaterialcode());
					   cStockSendDetailDto.setTotalS(cStockSendDetail.getTotalS()+"");
					   cStockSendDetailDtos.add(cStockSendDetailDto);
				   }
				   cStockSendDto.setcStockSendDetailDto(cStockSendDetailDtos);;
//				   result = checkStockSum(cStockSendDto);
//					if (!result.isSuccess()) {
//						return result;
//					}库存验证
				
				if (String.valueOf(CheckStatus.OK.getCode()).equals(cStockSendDto.getStatus()))
				{
					
					result=cStockFlowService.saveStockFlowRdTx(TradeType.STOCK_SEND, cStockSendDto.getSendSerialno());
					
					if (!result.isSuccess()){
						//操作流水失败
						throw new BusinessException(result.getErrorMsg());
					}
					log.debug("---------入库申请审核流水处理--------");
		
				}
			
				cStockSend.setSendSerialno(cStockSendDto.getSendSerialno());
				cStockSend.setStatus(cStockSendDto.getStatus());
				
				cStockSend.setAuditingoper(cStockSendDto.getUserId()+"");
				cStockSend.setAuditingtime(new Date());
				modelDao.update(cStockSend);;
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
	protected ResultDto<String> checkStockSum(CStockSendDto cStockSendDto){
	
		String deptid=cStockSendDto.getDeptid()+"";
		System.out.println("cStockSendDto.getDeptid()"+deptid);
	
		String mchcode="100000";
		CStockInfo cStockInfo=new CStockInfo();
		cStockInfo.setDeptid(Integer.parseInt(deptid));
		cStockInfo.setMchcode(mchcode);
		List<CStockInfo> cStockInfos=stockInfoDao.findStockTotals(cStockInfo);
		
		List<CStockSendDetailDto> cStockSendDetailDtos=cStockSendDto.getcStockSendDetailDto();
		
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
			for (CStockSendDetailDto cStockSendDetailDto : cStockSendDetailDtos) {
				   totalS=Double.parseDouble(cStockSendDetailDto.getTotalS());
				   if(totalS<=0){
			    		  return new ResultDto<String>(false,"物资数量必须大于0");
			       }
				   materialcode=cStockSendDetailDto.getMaterialcode();
				   unit=cStockSendDetailDto.getUnit();
				   name=cStockSendDetailDto.getCode();
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
	public ResultDto<String> antiAuditCStockSendByIdRdTx(CStockSendDto cStockSendDto) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		CStockSend cStockSend2=new CStockSend();
		CStockSend cStockSend=modelDao.findById(cStockSendDto.getSendSerialno());
		try 
		{

			//根据状态进行不同的操作
			
			
			if (cStockSend.getStatus().equals("0"))
			{
				
				result=cStockFlowService.saveStockFlowRdTx(TradeType.ANTI_AUDIT_STOCK_SEND,cStockSendDto.getSendSerialno());
				
				if (!result.isSuccess()){
					//操作流水失败
					throw new BusinessException(result.getErrorMsg());
				}
				log.debug("---------发料申请审核流水处理--------");
	
			}
			
			
			cStockSend2.setSendSerialno(cStockSendDto.getSendSerialno());
			cStockSend2.setStatus("1");
			
			cStockSend2.setAuditingoper(null);
			cStockSend2.setAuditingtime(null);
			
			modelDao.update(cStockSend2);
			
			result=new ResultDto<String>(true,"发料数据反审核成功");


		} catch (Exception e) {
						log.error("数据保存失败"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		}

	    return result;
	}


}
