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
import com.lq.lss.core.dao.StockLeaseDao;
import com.lq.lss.core.dao.StockLeaseDetailDao;
import com.lq.lss.core.dao.StockTemporaryDao;
import com.lq.lss.core.service.CStockFlowService;
import com.lq.lss.core.service.StockCenterTransferService;
import com.lq.lss.core.service.StockLeaseService;
import com.lq.lss.core.service.TNoticeService;
import com.lq.lss.dto.CStockCenterTransferDetailDto;
import com.lq.lss.dto.CStockCenterTransferDto;
import com.lq.lss.dto.CStockLeaseDetailDto;
import com.lq.lss.dto.CStockLeaseDto;
import com.lq.lss.dto.CStockTemporaryDetailDto;
import com.lq.lss.dto.StockOutDetailDto;
import com.lq.lss.dto.StockOutDto;
import com.lq.lss.enums.CheckStatus;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.CStockCenterTransfer;
import com.lq.lss.model.CStockCenterTransferDetail;
import com.lq.lss.model.CStockInfo;
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
public class StockCenterTransferServiceImpl extends EasyUIServiceBase<CStockCenterTransfer, String, StockCenterTransferDao> implements StockCenterTransferService {

	@Resource
	StockCenterTransferDetailDao centerTransferDetailDao;
	@Resource
	StockCenterTransferDao centerTransferDao;
	@Resource
    private CStockFlowService cStockFlowService;
	@Resource
	private StockInfoDao stockInfoDao;
	@Resource
	private TNoticeService tNoticeService;
	@Override
	public ResultDto<String> saveCStockStockCenterTransferRdTx(CStockCenterTransferDto stockCenterTransferDto, SessionUser user) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
//			result = checkStockSum(stockCenterTransferDto);
//			if (!result.isSuccess()) {
//				return result;
//			}
			CStockCenterTransfer csct=new CStockCenterTransfer();
			csct.setTsfSerialno(stockCenterTransferDto.getTsfSerialno());
			csct.setFromDeptid(user.getCenterId());
			csct.setFromMchcode(100000+"");
			csct.setToDepid(user.getCenterId());
			csct.setToMchcode(stockCenterTransferDto.getToMchcode());

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
			csct.setTradetype("5");
			csct.setTradeinfo(stockCenterTransferDto.getTradeinfo());
			csct.setMchOrderNo(stockCenterTransferDto.getMchOrderNo());
			csct.setRemark(stockCenterTransferDto.getRemark());
			csct.setCarNo(stockCenterTransferDto.getCarNo());
			csct.setCarDriver(stockCenterTransferDto.getCarDriver());
			modelDao.create(csct);
			log.debug("---------中心调出主表数据保存成功--------");
			
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
					log.debug("=========中心调出从表数据保存成功=======");
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
	public ResultDto<String> deleteCStockCenterTransfer(String tsfSerialno,String deptId) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
			CStockCenterTransfer csCenterTransfer=centerTransferDao.findByCStockCenterTransfer(tsfSerialno);
			if(csCenterTransfer!=null && String.valueOf(csCenterTransfer.getFromDeptid()).equals(deptId)){
				String status=csCenterTransfer.getStatus();
				if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
			    {
			    	return new ResultDto<String>(false,"已审核数据不可以删除");
			    }
			centerTransferDetailDao.deleteById(tsfSerialno);
			
			centerTransferDao.deleteById(tsfSerialno);
			
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
	public ResultDto<String> updateCStockCenterTransferRdTx(CStockCenterTransferDto stockCenterTransferDto, SessionUser user) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
			
			String  tsfSerialno = stockCenterTransferDto.getTsfSerialno();
			CStockCenterTransfer centerTransfer2 =  centerTransferDao.findByCStockCenterTransfer(tsfSerialno);
			 String deptid=String.valueOf(stockCenterTransferDto.getFromDeptid());
			 System.out.println("stockCenterTransferDto.getFromDeptid()"+stockCenterTransferDto.getFromDeptid());
//			 result = checkStockSum(stockCenterTransferDto);
//				if (!result.isSuccess()) {
//					return result;
//				}验证库存
			 
			if(centerTransfer2!=null && String.valueOf(centerTransfer2.getFromDeptid()).equals(deptid))
			{	
				String status=centerTransfer2.getStatus();
			    if(String.valueOf(CheckStatus.INIT.getConfirm()).equals(status))
			    {
			    	return new ResultDto<String>(false,"已审核数据不可以修改");
			    }
				centerTransferDetailDao.deleteById(tsfSerialno);
				log.debug("=============中心调出从表数据删除成功==============");
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
					log.debug("=========中心调出从表数据修改成功=======");
				}
				CStockCenterTransfer csct=new CStockCenterTransfer();
				csct.setTsfSerialno(stockCenterTransferDto.getTsfSerialno());
				
				csct.setToMchcode(stockCenterTransferDto.getToMchcode());
				
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
				csct.setRemark(stockCenterTransferDto.getRemark());
				csct.setCarNo(stockCenterTransferDto.getCarNo());
				csct.setStatus("1");
				csct.setCarDriver(stockCenterTransferDto.getCarDriver());
				modelDao.update(csct);
				log.debug("---------中心调出主表数据修改成功--------");
				result=new ResultDto<String>(true,"数据修改成功");	
			}else
			{ 
		        result=new ResultDto<String>(false,"该数据不在本中心不允许修改");
			}
			
		} catch (NumberFormatException e){
			log.error("中心调拨数据转化异常"+e.getMessage());
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		} catch (Exception e) {
					log.error("中心调拨数据修改异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
		}
		
		
		return result;
	}
	@Override
	public Pager<CStockCenterTransfer> pagerList(PageParam pageParam, CStockCenterTransfer cStockCenterTransfer) {
		// TODO Auto-generated method stub
		
		return modelDao.findByPage("pagerListoutPage", "outcount", pageParam, cStockCenterTransfer);
	}
	@Override
	public ResultDto<String> auditStockCenterTransferByIdRdTx(CStockCenterTransferDto csStockCenterTransferDto) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try 
		{
			
			CStockCenterTransfer cStockCenterTransfer=new CStockCenterTransfer();
			String tsfSerialno=csStockCenterTransferDto.getTsfSerialno();
			CStockCenterTransfer cStockCenterTransfer2=modelDao.findById(tsfSerialno);
			String deptid=String.valueOf(csStockCenterTransferDto.getFromDeptid());
			//根据状态进行不同的操作
		
			if(cStockCenterTransfer2!=null && String.valueOf(cStockCenterTransfer2.getFromDeptid()).equals(deptid))
			{
				
				String rejectCode=String.valueOf(CheckStatus.REJECT.getCode());
				 
				 if(rejectCode.equals(csStockCenterTransferDto.getStatus()))
				 {
					  String title=NoticeConst.NOTICE_DC_TITLE;
					  Integer fromId=Integer.parseInt(csStockCenterTransferDto.getUserId()+"");
					  Integer toId=Integer.parseInt(cStockCenterTransfer2.getCreateoper());
					  Integer deptId=Integer.parseInt(csStockCenterTransferDto.getFromDeptid());
					  tNoticeService.createNotice(fromId, deptId, toId, title, tsfSerialno);
				 }
				 
				csStockCenterTransferDto.setFromMchcode(cStockCenterTransfer2.getFromMchcode());
				 String status=cStockCenterTransfer2.getStatus();
//				 result = checkStockSum(csStockCenterTransferDto);
//					if (!result.isSuccess()) {
//						return result;
//					}
				    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
				    {
				    	return new ResultDto<String>(false,"已审核数据不可以再审核");
				    }
				   List<CStockCenterTransferDetail> cStockCenterTransferDetails=centerTransferDetailDao.findByCStockCenterTransferDetail(tsfSerialno);
				   List<CStockCenterTransferDetailDto> cStockCenterTransferDetailDtos=new ArrayList<CStockCenterTransferDetailDto>();
				   for (CStockCenterTransferDetail cStockCenterTransferDetail : cStockCenterTransferDetails) 
				   {
					   CStockCenterTransferDetailDto cStockCenterTransferDetailDto=new CStockCenterTransferDetailDto();
					   cStockCenterTransferDetailDto.setMaterialcode(cStockCenterTransferDetail.getMaterialcode());
					   cStockCenterTransferDetailDto.setTotalS(cStockCenterTransferDetail.getTotalS()+"");
					   cStockCenterTransferDetailDtos.add(cStockCenterTransferDetailDto);
				   }
				   csStockCenterTransferDto.setcStockCenterTransferDetaildtos(cStockCenterTransferDetailDtos);
				  
				
				if (String.valueOf(CheckStatus.OK.getCode()).equals(csStockCenterTransferDto.getStatus()))
				{
					
					result=cStockFlowService.saveStockFlowRdTx(TradeType.STOCKCENTERTRANSFER_OUT, csStockCenterTransferDto.getTsfSerialno());
					
					if (!result.isSuccess()){
						//操作流水失败
						throw new BusinessException(result.getErrorMsg());
					}
					log.debug("---------入库申请审核流水处理--------");
		
				}
			
				cStockCenterTransfer.setTsfSerialno(csStockCenterTransferDto.getTsfSerialno());
				cStockCenterTransfer.setStatus(csStockCenterTransferDto.getStatus());
				
				cStockCenterTransfer.setAuditingoper(csStockCenterTransferDto.getUserId()+"");
				cStockCenterTransfer.setAuditingtime(new Date());
				modelDao.update(cStockCenterTransfer);
				System.out.println("修改成功+++++++++++++"+csStockCenterTransferDto.getTradeinfo());
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
	protected ResultDto<String> checkStockSum(CStockCenterTransferDto stockCenterTransferDto){
		String deptid=stockCenterTransferDto.getFromDeptid();
		String mchcode="100000";
		CStockInfo cStockInfo=new CStockInfo();
		cStockInfo.setDeptid(Integer.parseInt(deptid));
		cStockInfo.setMchcode(mchcode);
		List<CStockInfo> cStockInfos=stockInfoDao.findStockTotals(cStockInfo);
		
		List<CStockCenterTransferDetailDto> cStockCenterTransferDetailDtos=stockCenterTransferDto.getcStockCenterTransferDetaildtos();
		
		
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
			for (CStockCenterTransferDetailDto csCenterTransferDetailDto : cStockCenterTransferDetailDtos) {
				   totalS=Double.parseDouble(csCenterTransferDetailDto.getTotalS());
				   if(totalS<=0){
			    		  return new ResultDto<String>(false,"物资数量必须大于0");
			       }
				   materialcode=csCenterTransferDetailDto.getMaterialcode();
				   unit=csCenterTransferDetailDto.getUnit();
				   name=csCenterTransferDetailDto.getCode();
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
	public CStockCenterTransfer queryStockCenterTransfer(String tsfSerialno) {
		// TODO Auto-generated method stub
		return centerTransferDao.findById(tsfSerialno);
	}
	@Override
	public ResultDto<String> antiAuditStockCenterTransferByIdRdTx(CStockCenterTransferDto stockCenterTransferDto) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		CStockCenterTransfer centerTransfer2=new CStockCenterTransfer();
		CStockCenterTransfer centerTransfer=modelDao.findById(stockCenterTransferDto.getTsfSerialno());
		try 
		{

			//根据状态进行不同的操作
			
			
			if (centerTransfer.getStatus().equals("0"))
			{
				
				result=cStockFlowService.saveStockFlowRdTx(TradeType.ANTI_STOCK_CENTERTRANSFER_OUT,stockCenterTransferDto.getTsfSerialno());
				
				if (!result.isSuccess()){
					//操作流水失败
					throw new BusinessException(result.getErrorMsg());
				}
				log.debug("---------中心外调出反审核流水处理--------");
	
			}
			centerTransfer2.setTsfSerialno(stockCenterTransferDto.getTsfSerialno());
			centerTransfer2.setStatus("1");	
			centerTransfer2.setAuditingoper(null);
			centerTransfer2.setAuditingtime(null);
			modelDao.update(centerTransfer2);
			
			result=new ResultDto<String>(true,"中心外调出核成功");


		} catch (Exception e) {
						log.error("数据保存失败"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		}

	    return result;
	}


}
