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
import com.lq.lss.core.dao.StockLeaseDao;
import com.lq.lss.core.dao.StockLeaseDetailDao;
import com.lq.lss.core.dao.StockTemporaryDao;
import com.lq.lss.core.service.CStockFlowService;
import com.lq.lss.core.service.StockCenterTransferInService;
import com.lq.lss.core.service.StockCenterTransferService;
import com.lq.lss.core.service.StockLeaseService;
import com.lq.lss.core.service.TNoticeService;
import com.lq.lss.dto.CStockCenterTransferDetailDto;
import com.lq.lss.dto.CStockCenterTransferDto;
import com.lq.lss.dto.CStockLeaseDetailDto;
import com.lq.lss.dto.CStockLeaseDto;
import com.lq.lss.dto.CStockTemporaryDetailDto;
import com.lq.lss.enums.CheckStatus;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.CStockCenterTransfer;
import com.lq.lss.model.CStockCenterTransferDetail;
import com.lq.lss.model.CStockIn;
import com.lq.lss.model.CStockLease;
import com.lq.lss.model.CStockLeaseDetail;
import com.lq.lss.model.CStockTemporary;
import com.lq.lss.model.CStockTemporaryDetail;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.exception.jta.BusinessException;
import com.lq.pager.PageParam;
import com.lq.pager.Pager;
import com.lq.util.TimeUtil;

/**
 * 
 * @author ch
 *
 */
@Service
public class StockCenterTransferInServiceImpl extends EasyUIServiceBase<CStockCenterTransfer, String, StockCenterTransferDao> implements StockCenterTransferInService {

	@Resource
	StockCenterTransferDetailDao centerTransferDetailDao;
	@Resource
	StockCenterTransferDao centerTransferDao;
	@Resource
    private CStockFlowService cStockFlowService;
	@Resource
	private TNoticeService tNoticeService;
	@Override
	public ResultDto<String> saveCStockStockCenterTransferInRdTx(CStockCenterTransferDto stockCenterTransferDto, SessionUser user) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
			CStockCenterTransfer csct=new CStockCenterTransfer();
			csct.setTsfSerialno(stockCenterTransferDto.getTsfSerialno());
			csct.setFromDeptid(user.getCenterId());
			csct.setFromMchcode(stockCenterTransferDto.getFromMchcode());
			csct.setToDepid(user.getCenterId());
			csct.setToMchcode(100000+"");
			
			if(stockCenterTransferDto.getZxFee()=="" || stockCenterTransferDto.getZxFee()==null){
				csct.setZxFee(new BigDecimal(0));
			}else{
				csct.setZxFee(new BigDecimal(stockCenterTransferDto.getZxFee()));
			}
			if(stockCenterTransferDto.getOtherFee()=="" || stockCenterTransferDto.getOtherFee()==null){
				csct.setOtherFee(new BigDecimal(0));
			}else{
				csct.setOtherFee(new BigDecimal(stockCenterTransferDto.getOtherFee()));
			}			
			if(stockCenterTransferDto.getTransportFee()=="" || stockCenterTransferDto.getTransportFee()==null){
				csct.setTransportFee(new BigDecimal(0));
			}else{
				csct.setTransportFee(new BigDecimal(stockCenterTransferDto.getTransportFee()));
			}
			csct.setStatus("1");
			csct.setCreateoper(user.getUserId()+"");
			Date cdate=TimeUtil.get().parseTime(stockCenterTransferDto.getTsfSdate());
			csct.setTsfSdate(cdate);
			csct.setCreatetime(new Date());
			csct.setTraInOper(stockCenterTransferDto.getTraInOper());
			csct.setTraOutOper(stockCenterTransferDto.getTraOutOper());
			csct.setTradetype("6");
			csct.setTradeinfo(stockCenterTransferDto.getTradeinfo());
			csct.setMchOrderNo(stockCenterTransferDto.getMchOrderNo());
			csct.setRemark(stockCenterTransferDto.getRemark());
			csct.setCarNo(stockCenterTransferDto.getCarNo());
			csct.setCarDriver(stockCenterTransferDto.getCarDriver());
			modelDao.create(csct);
			log.debug("---------中心调入主表数据保存成功--------");
			
			List<CStockCenterTransferDetailDto> csCenterTransferDetailDtos=stockCenterTransferDto.getcStockCenterTransferDetaildtos();
			List<CStockCenterTransferDetail> csCenterTransferDetails=new ArrayList<CStockCenterTransferDetail>();
		
				if(csCenterTransferDetailDtos.size()>0){
					for (CStockCenterTransferDetailDto detailList : csCenterTransferDetailDtos) {
						CStockCenterTransferDetail  csCenterTransferDetail=new CStockCenterTransferDetail();
						csCenterTransferDetail.setTsfSerialno(stockCenterTransferDto.getTsfSerialno());
						csCenterTransferDetail.setMaterialcode(detailList.getMaterialcode());
						csCenterTransferDetail.setTotalM(new BigDecimal(detailList.getTotalM()));
						csCenterTransferDetail.setTotalT(new BigDecimal(0));
						csCenterTransferDetail.setTotalS(new BigDecimal(detailList.getTotalS()));
						//单价目前没取
//						cStockLeaseDetail.setPrice(new BigDecimal(0));
						csCenterTransferDetail.setUnit(detailList.getUnit());
						csCenterTransferDetails.add(csCenterTransferDetail);
					}
					centerTransferDetailDao.batchCreate(csCenterTransferDetails);
					log.debug("=========中心调入从表数据保存成功=======");
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
	public ResultDto<String> deleteCStockCenterTransferIn(String tsfSerialno,String deptId) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
			CStockCenterTransfer csCenterTransfer=centerTransferDao.findByCStockCenterTransfer(tsfSerialno);
			if(csCenterTransfer!=null && String.valueOf(csCenterTransfer.getFromDeptid()).equals(deptId)){
				String status=csCenterTransfer.getStatus();
				if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
			    {
			    	return new ResultDto<String>(false,"已审核单据不可以删除");
			    }
			centerTransferDetailDao.deleteById(tsfSerialno);
			System.out.println("删除中心调入从表——————————————————————————————————————————");
			centerTransferDao.deleteById(tsfSerialno);
			System.out.println("删除中心调入主表——————————————————————————————————————————");
			result=new ResultDto<String>(true,"删除数据成功");
		}else
		{ 
		    result=new ResultDto<String>(false,"该数据不在本中心不允许删除");
		}
		
		} catch (NumberFormatException e) {
		log.error("中心调入删除申请数据转化异常"+e.getMessage());
		e.printStackTrace();
		throw new BusinessException(e.getMessage());
		} catch (Exception e) {
				log.error("中心调入数据删除异常"+e.getMessage());
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
		}
			return result;
	}
	@Override
	public ResultDto<String> updateCStockCenterTransferInRdTx(CStockCenterTransferDto stockCenterTransferDto, SessionUser user) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
			String  tsfSerialno = stockCenterTransferDto.getTsfSerialno();
			CStockCenterTransfer centerTransfer2 =  centerTransferDao.findByCStockCenterTransfer(tsfSerialno);
			 String deptid=String.valueOf(stockCenterTransferDto.getFromDeptid());
			if(centerTransfer2!=null && String.valueOf(centerTransfer2.getFromDeptid()).equals(deptid))
			{
				
				String status=centerTransfer2.getStatus();
			    if(String.valueOf(CheckStatus.INIT.getConfirm()).equals(status))
			    {
			    	return new ResultDto<String>(false,"已审核单据不可以修改");
			    }
				centerTransferDetailDao.deleteById(tsfSerialno);
				log.debug("=============中心调入从表数据删除成功==============");
				List<CStockCenterTransferDetailDto> csCenterTransferDetailDtos=stockCenterTransferDto.getcStockCenterTransferDetaildtos();
				List<CStockCenterTransferDetail> csCenterTransferDetails=new ArrayList<CStockCenterTransferDetail>();
				if(csCenterTransferDetailDtos.size()>0)
				{
					for (CStockCenterTransferDetailDto detailList : csCenterTransferDetailDtos) {
						CStockCenterTransferDetail  csCenterTransferDetail=new CStockCenterTransferDetail();
						csCenterTransferDetail.setTsfSerialno(stockCenterTransferDto.getTsfSerialno());
						csCenterTransferDetail.setMaterialcode(detailList.getMaterialcode());
						csCenterTransferDetail.setTotalM(new BigDecimal(detailList.getTotalM()));
						csCenterTransferDetail.setTotalT(new BigDecimal(0));
						csCenterTransferDetail.setTotalS(new BigDecimal(detailList.getTotalS()));
						//单价目前没取
//						cStockLeaseDetail.setPrice(new BigDecimal(0));
						csCenterTransferDetail.setUnit(detailList.getUnit());
						csCenterTransferDetails.add(csCenterTransferDetail);
					}
					centerTransferDetailDao.batchCreate(csCenterTransferDetails);
					log.debug("=========中心调入从表数据修改成功=======");
				}
				CStockCenterTransfer csct=new CStockCenterTransfer();
				csct.setTsfSerialno(stockCenterTransferDto.getTsfSerialno());
				csct.setFromMchcode(stockCenterTransferDto.getFromMchcode());
				
				
				if(stockCenterTransferDto.getZxFee()=="" || stockCenterTransferDto.getZxFee()==null){
					csct.setZxFee(new BigDecimal(0));
				}else{
					csct.setZxFee(new BigDecimal(stockCenterTransferDto.getZxFee()));
				}
				if(stockCenterTransferDto.getOtherFee()=="" || stockCenterTransferDto.getOtherFee()==null){
					csct.setOtherFee(new BigDecimal(0));
				}else{
					csct.setOtherFee(new BigDecimal(stockCenterTransferDto.getOtherFee()));
				}			
				if(stockCenterTransferDto.getTransportFee()=="" || stockCenterTransferDto.getTransportFee()==null){
					csct.setTransportFee(new BigDecimal(0));
				}else{
					csct.setTransportFee(new BigDecimal(stockCenterTransferDto.getTransportFee()));
				}
				csct.setCreateoper(user.getUserId()+"");
				Date cdate=TimeUtil.get().parseTime(stockCenterTransferDto.getTsfSdate());
				csct.setTsfSdate(cdate);
				csct.setTraInOper(stockCenterTransferDto.getTraInOper());
				csct.setTraOutOper(stockCenterTransferDto.getTraOutOper());
				csct.setTradeinfo(stockCenterTransferDto.getTradeinfo());
				csct.setMchOrderNo(stockCenterTransferDto.getMchOrderNo());
				csct.setStatus("1");
				csct.setRemark(stockCenterTransferDto.getRemark());
				csct.setCarNo(stockCenterTransferDto.getCarNo());
				csct.setCarDriver(stockCenterTransferDto.getCarDriver());
				modelDao.update(csct);
				log.debug("---------中心调入主表数据修改成功--------");
				result=new ResultDto<String>(true,"数据修改成功");	
			}else
			{ 
		        result=new ResultDto<String>(false,"该数据不在本中心不允许修改");
			}
			
		} catch (NumberFormatException e){
			log.error("中心调入数据转化异常"+e.getMessage());
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		} catch (Exception e) {
					log.error("中心调入数据修改异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
		}
		
		
		return result;
	}
	@Override
	public Pager<CStockCenterTransfer> pagerList(PageParam pageParam, CStockCenterTransfer cStockCenterTransfer) {
		// TODO Auto-generated method stub
		return modelDao.findByPage("pagerListinPage", "incount", pageParam, cStockCenterTransfer);
	}
	@Override
	public ResultDto<String> auditStockCenterTransferInByIdRdTx(CStockCenterTransferDto stockCenterTransferDto) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try 
		{
			CStockCenterTransfer cStockCenterTransfer=new CStockCenterTransfer();
			String tsfSerialno=stockCenterTransferDto.getTsfSerialno();
			CStockCenterTransfer cStockCenterTransfer2=modelDao.findById(tsfSerialno);
			String deptid=String.valueOf(stockCenterTransferDto.getFromDeptid());
			//根据状态进行不同的操作
			if(cStockCenterTransfer2!=null && String.valueOf(cStockCenterTransfer2.getFromDeptid()).equals(deptid))
			{
				
				String rejectCode=String.valueOf(CheckStatus.REJECT.getCode());
				 
				 if(rejectCode.equals(stockCenterTransferDto.getStatus()))
				 {
					  String title=NoticeConst.NOTICE_DR_TITLE;
					  Integer fromId=Integer.parseInt(stockCenterTransferDto.getUserId()+"");
					  Integer toId=Integer.parseInt(cStockCenterTransfer2.getCreateoper());
					  Integer deptId=Integer.parseInt(stockCenterTransferDto.getFromDeptid());
					  tNoticeService.createNotice(fromId, deptId, toId, title, tsfSerialno);
				 }
				 
				 String status=cStockCenterTransfer2.getStatus();
				    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
				    {
				    	return new ResultDto<String>(false,"已审核数据不可以再审核");
				    }
				    if (String.valueOf(CheckStatus.OK.getCode()).equals(stockCenterTransferDto.getStatus()))
					{
						
						result=cStockFlowService.saveStockFlowRdTx(TradeType.STOCKCENTERTRANSFER_IN, stockCenterTransferDto.getTsfSerialno());
						
						if (!result.isSuccess()){
							//操作流水失败
							throw new BusinessException(result.getErrorMsg());
						}
						log.debug("---------入库申请审核流水处理--------");
			
					}
					
				    cStockCenterTransfer.setTsfSerialno(stockCenterTransferDto.getTsfSerialno());
					cStockCenterTransfer.setStatus(stockCenterTransferDto.getStatus());
					
					cStockCenterTransfer.setAuditingoper(stockCenterTransferDto.getUserId()+"");
					cStockCenterTransfer.setAuditingtime(new Date());
					modelDao.update(cStockCenterTransfer);
					
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
	public ResultDto<String> antiAuditStockCenterTransferInByIdRdTx(CStockCenterTransferDto stockCenterTransferDto) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		CStockCenterTransfer centerTransfer2=new CStockCenterTransfer();
		CStockCenterTransfer centerTransfer=modelDao.findById(stockCenterTransferDto.getTsfSerialno());
		try 
		{

			//根据状态进行不同的操作
			
			
			if (centerTransfer.getStatus().equals("0"))
			{
				
				result=cStockFlowService.saveStockFlowRdTx(TradeType.ANTI_STOCK_CENTERTRANSFER_IN,stockCenterTransferDto.getTsfSerialno());
				
				if (!result.isSuccess()){
					//操作流水失败
					throw new BusinessException(result.getErrorMsg());
				}
				log.debug("---------中心外调入反审核流水处理--------");
	
			}
			centerTransfer2.setTsfSerialno(stockCenterTransferDto.getTsfSerialno());
			centerTransfer2.setStatus("1");	
			centerTransfer2.setAuditingoper(null);
			centerTransfer2.setAuditingtime(null);
			modelDao.update(centerTransfer2);
			
			result=new ResultDto<String>(true,"中心外调入核成功");


		} catch (Exception e) {
						log.error("数据保存失败"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		}

	    return result;
	}

	


}
