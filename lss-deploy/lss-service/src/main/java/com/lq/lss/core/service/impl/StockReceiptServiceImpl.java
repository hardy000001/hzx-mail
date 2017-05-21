package com.lq.lss.core.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.CStockInfo;
import com.lq.lss.model.CStockReceipt;
import com.lq.lss.model.CStockReceiptDetail;
import com.lq.lss.model.CStockReceiptRepairinfo;
import com.lq.lss.utils.SystemConst;
import com.lq.lss.utils.exception.jta.BusinessException;
import com.lq.lss.constant.NoticeConst;
import com.lq.lss.core.dao.StockInfoDao;
import com.lq.lss.core.dao.StockReceiptDao;
import com.lq.lss.core.dao.StockReceiptDetailDao;
import com.lq.lss.core.dao.StockReceiptRepairinfoDao;
import com.lq.lss.core.service.CStockFlowService;
import com.lq.lss.core.service.StockReceiptService;
import com.lq.lss.core.service.TNoticeService;
import com.lq.lss.dto.AuditDto;
import com.lq.lss.dto.StockReceiptDetailDto;
import com.lq.lss.dto.StockReceiptDto;
import com.lq.lss.dto.StockReceiptRepairinfoDto;
import com.lq.lss.enums.CheckStatus;
import com.lq.lss.enums.TradeType;
import com.lq.util.TimeUtil;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-09 12:31:33
 */
@Service
public class StockReceiptServiceImpl extends EasyUIServiceBase<CStockReceipt, String, StockReceiptDao> implements StockReceiptService{

    @Resource
	private StockReceiptDetailDao stockReceiptDetailDao;
    @Resource
	private StockReceiptRepairinfoDao stockReceiptRepairinfoDao;
    @Resource
    private CStockFlowService cStockFlowService;
    @Resource
	private StockInfoDao stockInfoDao;
    @Resource
	private TNoticeService tNoticeService;

	@Override
	public ResultDto<String> saveStockReceiptApplyRdTx(
			StockReceiptDto stockReceiptDto) {
		ResultDto<String> result=null;
		try 
		{

						
			            CStockReceipt stockReceipt=new CStockReceipt();
			            String receiptSerialno=stockReceiptDto.getReceiptSerialno();
			            stockReceipt.setHtcode(stockReceiptDto.getHtcode());
			            stockReceipt.setReceiptSerialno(receiptSerialno);
			            stockReceipt.setDeptid(stockReceiptDto.getDeptid());
			            stockReceipt.setMchcode(stockReceiptDto.getMchcode());
			            stockReceipt.setCarNo(stockReceiptDto.getCarNo());
			            stockReceipt.setCarDriver(stockReceiptDto.getCarDriver());
			            stockReceipt.setLessee(stockReceiptDto.getLessee());
			            stockReceipt.setRenter(stockReceiptDto.getRenter());
			            stockReceipt.setIsrepair(stockReceiptDto.getIsrepair());
			            stockReceipt.setTradeinfo("");
			            
						if(StringUtils.hasLength(stockReceiptDto.getZxFee())){
							Double zxFee=Double.valueOf(stockReceiptDto.getZxFee());
							stockReceipt.setZxFee(new BigDecimal(zxFee));
						}
						if(StringUtils.hasLength(stockReceiptDto.getTransportFee())){
							Double transportFee=Double.valueOf(stockReceiptDto.getTransportFee());
							stockReceipt.setTransportFee(new BigDecimal(transportFee));
						}
						if(StringUtils.hasLength(stockReceiptDto.getOtherFee())){
							Double otherFee=Double.valueOf(stockReceiptDto.getOtherFee());
							stockReceipt.setOtherFee(new BigDecimal(otherFee));
						}
						if(StringUtils.hasLength(stockReceiptDto.getRepairFee())){
							Double repairFee=Double.valueOf(stockReceiptDto.getRepairFee());
							stockReceipt.setRepairFee(new BigDecimal(repairFee));
						}
						
						stockReceipt.setStatus("1");
						stockReceipt.setCreateoper(stockReceiptDto.getCreateoper());
						Date cdate=TimeUtil.get().parseTime(stockReceiptDto.getReceipttime());
						stockReceipt.setCreatetime(new Date());
						stockReceipt.setReceipttime(cdate);
						stockReceipt.setRemark(stockReceiptDto.getRemark());
						
						modelDao.create(stockReceipt);
						log.debug("---------收料申请主表数据保存成功--------");
						
						
						List<StockReceiptDetailDto> stockReceiptDetailDtos=stockReceiptDto.getStockReceiptDetailDtos();
						List<CStockReceiptDetail> stockReceiptDetails=new ArrayList<CStockReceiptDetail>();
						
						if(stockReceiptDetailDtos!=null && stockReceiptDetailDtos.size()>0)
						{
							 int i=0;
                             for (StockReceiptDetailDto detailList : stockReceiptDetailDtos) 
                             {
                            	     String autoNo=receiptSerialno+i++;
                            	     CStockReceiptDetail stockReceiptDetail=new CStockReceiptDetail();
                            	     stockReceiptDetail.setId(autoNo);
                            	     stockReceiptDetail.setReceiptSerialno(receiptSerialno);
                            	     stockReceiptDetail.setMaterialcode(detailList.getMaterialcode());
                            	     if(StringUtils.hasLength(detailList.getTotalM())){
                            	    	 Double totalM_=Double.valueOf(detailList.getTotalM());
                            	    	 stockReceiptDetail.setTotalM(new BigDecimal(totalM_));
                            	     }
                            	     if(StringUtils.hasLength(detailList.getTotalS())){
                            	    	 Double totalS_=Double.valueOf(detailList.getTotalS());
                            	    	 stockReceiptDetail.setTotalS(new BigDecimal(totalS_));
                            	     }
                            	     if(StringUtils.hasLength(detailList.getTotalT())){
                            	    	 Double totalT_=Double.valueOf(detailList.getTotalT());
                            	    	 stockReceiptDetail.setTotalT(new BigDecimal(totalT_));
                            	     }
                            	     stockReceiptDetail.setUnit(detailList.getUnit());
                            	     stockReceiptDetails.add(stockReceiptDetail);
							 }
                             stockReceiptDetailDao.batchCreate(stockReceiptDetails);
						     log.debug("=========收料申请从表数据保存成功=======");
						}
						
						List<StockReceiptRepairinfoDto> stockReceiptRepairinfoDtos=stockReceiptDto.getStockReceiptRepairinfoDtos();
						List<CStockReceiptRepairinfo> stockReceiptRepairinfos=new ArrayList<CStockReceiptRepairinfo>();
						
						if(stockReceiptRepairinfoDtos!=null && stockReceiptRepairinfoDtos.size()>0)
						{
                             for (StockReceiptRepairinfoDto detailList : stockReceiptRepairinfoDtos) 
                             {
                            	     CStockReceiptRepairinfo stockReceiptRepairinfo=new CStockReceiptRepairinfo();
                            	     stockReceiptRepairinfo.setReceiptDetailId(detailList.getReceiptDetailId());
                            	     stockReceiptRepairinfo.setRepairId(detailList.getRepairId());
                            	     if(StringUtils.hasLength(detailList.getRepairFee()))
                            	     {
                            	    	 Double fee=Double.valueOf(detailList.getRepairFee());
                            	    	 stockReceiptRepairinfo.setRepairFee(new BigDecimal(fee));
                            	     }
                            	     stockReceiptRepairinfo.setReceiptSerialno(receiptSerialno);
                            	     stockReceiptRepairinfos.add(stockReceiptRepairinfo);
							 }
                             stockReceiptRepairinfoDao.batchCreate(stockReceiptRepairinfos);
						     log.debug("=========收料维修数据保存成功=======");
						}
					
					
					    result=new ResultDto<String>(true,"数据保存成功");
			
		} catch (NumberFormatException e){
						log.error("保存收料数据转化异常"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		} catch (Exception e) {
						log.error("收料数据保存失败"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		}

	    return result;
	}

	@Override
	public ResultDto<String> updateStockReceiptRdTx(
			StockReceiptDto stockReceiptDto) {
		ResultDto<String> result=null;
		try 
		{
						
						
	                    String receiptSerialno=stockReceiptDto.getReceiptSerialno();
	                    Integer deptid=stockReceiptDto.getDeptid();
	                    
	                    CStockReceipt stockReceipt2=modelDao.findById(receiptSerialno);
						if(stockReceipt2!=null && Integer.valueOf(stockReceipt2.getDeptid()).equals(deptid))
						{
								//已审核数据不可以修改
						        String status=stockReceipt2.getStatus();
							    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
							    {
							    	if(!String.valueOf(CheckStatus.REJECT.getCode()).equals(status))
							    	{
							    		return new ResultDto<String>(false,"已审核数据不可以修改");
							    	}
							    }
					            CStockReceipt stockReceipt=new CStockReceipt();
					            stockReceipt.setHtcode(stockReceiptDto.getHtcode());
					            stockReceipt.setReceiptSerialno(receiptSerialno);
					            stockReceipt.setDeptid(deptid);
					            stockReceipt.setMchcode(stockReceiptDto.getMchcode());
					            stockReceipt.setCarNo(stockReceiptDto.getCarNo());
					            stockReceipt.setCarDriver(stockReceiptDto.getCarDriver());
					            stockReceipt.setLessee(stockReceiptDto.getLessee());
					            stockReceipt.setRenter(stockReceiptDto.getRenter());
					            stockReceipt.setTradeinfo("");
					            stockReceipt.setIsrepair(stockReceiptDto.getIsrepair());
					            
								if(StringUtils.hasLength(stockReceiptDto.getZxFee())){
									Double zxFee=Double.valueOf(stockReceiptDto.getZxFee());
									stockReceipt.setZxFee(new BigDecimal(zxFee));
								}
								if(StringUtils.hasLength(stockReceiptDto.getTransportFee())){
									Double transportFee=Double.valueOf(stockReceiptDto.getTransportFee());
									stockReceipt.setTransportFee(new BigDecimal(transportFee));
								}
								if(StringUtils.hasLength(stockReceiptDto.getOtherFee())){
									Double otherFee=Double.valueOf(stockReceiptDto.getOtherFee());
									stockReceipt.setOtherFee(new BigDecimal(otherFee));
								}
								if(StringUtils.hasLength(stockReceiptDto.getRepairFee())){
									Double repairFee=Double.valueOf(stockReceiptDto.getRepairFee());
									stockReceipt.setRepairFee(new BigDecimal(repairFee));
								}
								
								stockReceipt.setStatus(CheckStatus.INIT.getCode()+"");
								Date cdate=TimeUtil.get().parseTime(stockReceiptDto.getReceipttime());
								stockReceipt.setReceipttime(cdate);
								stockReceipt.setRemark(stockReceiptDto.getRemark());
								
								modelDao.update(stockReceipt);
								log.debug("---------收料申请主表数据修改成功--------");
								
								//修改明细是先删除再创建
								stockReceiptDetailDao.deleteById(receiptSerialno);
			            	    log.debug("=============收料申请从表数据删除成功==============");
								
								List<StockReceiptDetailDto> stockReceiptDetailDtos=stockReceiptDto.getStockReceiptDetailDtos();
								List<CStockReceiptDetail> stockReceiptDetails=new ArrayList<CStockReceiptDetail>();
								
								if(stockReceiptDetailDtos!=null && stockReceiptDetailDtos.size()>0)
								{
									 int i=0;
		                             for (StockReceiptDetailDto detailList : stockReceiptDetailDtos) 
		                             {
		                            	     String autoNo=receiptSerialno+i++;
		                            	     CStockReceiptDetail stockReceiptDetail=new CStockReceiptDetail();
		                            	     stockReceiptDetail.setId(autoNo);
		                            	     stockReceiptDetail.setReceiptSerialno(receiptSerialno);
		                            	     stockReceiptDetail.setMaterialcode(detailList.getMaterialcode());
		                            	     if(StringUtils.hasLength(detailList.getTotalM())){
		                            	    	 Double totalM_=Double.valueOf(detailList.getTotalM());
		                            	    	 stockReceiptDetail.setTotalM(new BigDecimal(totalM_));
		                            	     }
		                            	     if(StringUtils.hasLength(detailList.getTotalS())){
		                            	    	 Double totalS_=Double.valueOf(detailList.getTotalS());
		                            	    	 stockReceiptDetail.setTotalS(new BigDecimal(totalS_));
		                            	     }
		                            	     if(StringUtils.hasLength(detailList.getTotalT())){
		                            	    	 Double totalT_=Double.valueOf(detailList.getTotalT());
		                            	    	 stockReceiptDetail.setTotalT(new BigDecimal(totalT_));
		                            	     }
		                            	     stockReceiptDetail.setUnit(detailList.getUnit());
		                            	     stockReceiptDetails.add(stockReceiptDetail);
									 }
		                             stockReceiptDetailDao.batchCreate(stockReceiptDetails);
								     log.debug("=========收料申请从表数据保存成功=======");
								}
								
								stockReceiptRepairinfoDao.deleteById(receiptSerialno);
							    log.debug("========收料维修信息数据删除成功========");
							    
								List<StockReceiptRepairinfoDto> stockReceiptRepairinfoDtos=stockReceiptDto.getStockReceiptRepairinfoDtos();
								List<CStockReceiptRepairinfo> stockReceiptRepairinfos=new ArrayList<CStockReceiptRepairinfo>();
								
								if(stockReceiptRepairinfoDtos!=null && stockReceiptRepairinfoDtos.size()>0)
								{
		                             for (StockReceiptRepairinfoDto detailList : stockReceiptRepairinfoDtos) 
		                             {
		                            	     CStockReceiptRepairinfo stockReceiptRepairinfo=new CStockReceiptRepairinfo();
		                            	     stockReceiptRepairinfo.setReceiptDetailId(detailList.getReceiptDetailId());
		                            	     stockReceiptRepairinfo.setRepairId(detailList.getRepairId());
		                            	     if(StringUtils.hasLength(detailList.getRepairFee()))
		                            	     {
		                            	    	 Double fee=Double.valueOf(detailList.getRepairFee());
		                            	    	 stockReceiptRepairinfo.setRepairFee(new BigDecimal(fee));
		                            	     }
		                            	     stockReceiptRepairinfo.setReceiptSerialno(receiptSerialno);
		                            	     stockReceiptRepairinfos.add(stockReceiptRepairinfo);
									 }
		                             stockReceiptRepairinfoDao.batchCreate(stockReceiptRepairinfos);
								     log.debug("=========收料维修数据保存成功=======");
								}
                                log.debug("************收料维修数据修改成功*************");
								
								result=new ResultDto<String>(true,"数据修改成功");
						}else
						{ 
						        result=new ResultDto<String>(false,"该数据不在本中心不允许修改");
					    }
		} catch (NumberFormatException e){
						log.error("修改收料数据转化异常"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		} catch (Exception e) {
						log.error("收料数据修改失败"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		}

	    return result;
	}

	@Override
	public ResultDto<String> deleteStockReceiptById(String receiptSerialno,
			String deptId) {
	        ResultDto<String> result=null;
		    
			try {
				          
				            CStockReceipt stockReceipt2=modelDao.findById(receiptSerialno);
					        if(stockReceipt2!=null && String.valueOf(stockReceipt2.getDeptid()).equals(deptId))
					        {
								    //审核后不再允许删除
							        String status=stockReceipt2.getStatus();
								    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
								    {
								    	
								    	return new ResultDto<String>(false,"已审核数据不可以删除");
								    	
								    }
								    stockReceiptRepairinfoDao.deleteById(receiptSerialno);
								    log.debug("========收料维修信息数据删除成功========");
								    
								    stockReceiptDetailDao.deleteById(receiptSerialno);
								    log.debug("========收料申请从表数据删除成功========");
								   
								    modelDao.deleteById(receiptSerialno);
								    log.debug("---------收料申请主表数据删除成功--------");
								    
								    result=new ResultDto<String>(true,"删除出库数据成功");
							}else
							{ 
								    result=new ResultDto<String>(false,"该数据不在本中心不允许删除");
							}
				
				
			} catch (NumberFormatException e) {
							log.error("删除出库数据转化异常"+e.getMessage());
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
			} catch (Exception e) {
							log.error("出库数据删除异常"+e.getMessage());
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
	     }
			
		 return result;
	}

	@Override
	public ResultDto<String> auditInfoRdTx(AuditDto auditDto) {
			try {
				   
					String receiptSerialno=auditDto.getId();
					CStockReceipt stockReceipt2=modelDao.findById(receiptSerialno);
					
					if(stockReceipt2!=null){
						   
						String rejectCode=String.valueOf(CheckStatus.REJECT.getCode());
						 
						 if(rejectCode.equals(auditDto.getStatus()))
						 {
							  String title=NoticeConst.NOTICE_SL_TITLE;
							  Integer fromId=Integer.parseInt(auditDto.getUserId()+"");
							  Integer toId=Integer.parseInt(stockReceipt2.getCreateoper());
							  Integer deptId=Integer.parseInt(auditDto.getDeptId());
							  tNoticeService.createNotice(fromId, deptId, toId, title, receiptSerialno);
						 }
						
							//审核后不再审核
					        String status=stockReceipt2.getStatus();
						    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
						    {
						    	return new ResultDto<String>(false,"已审核数据不可以再审核");
						    }
						    
						    StockReceiptDto stockReceiptDto=new StockReceiptDto();
						    stockReceiptDto.setDeptid(Integer.parseInt(auditDto.getDeptId()));
						    stockReceiptDto.setMchcode(SystemConst.ZX_MCHCODE);
							List<CStockReceiptDetail> stockReceiptDetails = stockReceiptDetailDao
									.findReceiptDetailListById(receiptSerialno);
                            List<StockReceiptDetailDto> stockReceiptDetailDtos=new ArrayList<StockReceiptDetailDto>();
                            
						    for (CStockReceiptDetail cStockReceiptDetail : stockReceiptDetails) {
						    	 StockReceiptDetailDto stockReceiptDetailDto=new StockReceiptDetailDto();
						    	 stockReceiptDetailDto.setMaterialcode(cStockReceiptDetail.getMaterialcode());
						    	 stockReceiptDetailDto.setTotalS(cStockReceiptDetail.getTotalS()+"");
						    	 stockReceiptDetailDtos.add(stockReceiptDetailDto);
							}
						    stockReceiptDto.setStockReceiptDetailDtos(stockReceiptDetailDtos);

						    CStockReceipt stockReceipt=new CStockReceipt();
						    stockReceipt.setReceiptSerialno(receiptSerialno);
							
						    stockReceipt.setStatus(auditDto.getStatus());
						    stockReceipt.setAuditingoper(auditDto.getUserId());
						    stockReceipt.setAuditingtime(new Date());
							
							modelDao.update(stockReceipt);
							log.debug("*************收料审核修改成功*************");
							
							if(String.valueOf(CheckStatus.OK.getCode()).equals(auditDto.getStatus()))
							{
									cStockFlowService.saveStockFlowRdTx(TradeType.STOCK_RECEIPT, receiptSerialno);
									log.debug("---------收料审核流水处理成功--------");
							}
							
					}else
					{
						    return new ResultDto<String>(false,"该数据不在本中心不允许审核");
					}
			} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
			}
		    return new ResultDto<String>(true,"审核通过");
	}
	/**
	 * 检查库存数据
	 * @return
	 */
	protected ResultDto<String> checkStockSum(StockReceiptDto stockReceiptDto){
		
		Integer deptid=stockReceiptDto.getDeptid();
		String mchcode=stockReceiptDto.getMchcode();
		CStockInfo cStockInfo=new CStockInfo();
		cStockInfo.setDeptid(deptid);
		cStockInfo.setMchcode(mchcode);
		List<CStockInfo> cStockInfos=stockInfoDao.findStockSum(cStockInfo);
		
		List<StockReceiptDetailDto> stockReceiptDetailDtos=stockReceiptDto.getStockReceiptDetailDtos();
		
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
			for (StockReceiptDetailDto stockReceiptDetailDto : stockReceiptDetailDtos) {
				   totalS=Double.parseDouble(stockReceiptDetailDto.getTotalS());
				   if(totalS<=0){
			    		  return new ResultDto<String>(false,"物资数量必须大于0");
			       }
				   materialcode=stockReceiptDetailDto.getMaterialcode();
				   name=stockReceiptDetailDto.getName();
				   for (CStockInfo stockInfo : cStockInfos) {
					      code=stockInfo.getMaterialcode();
					      if(code.equals(materialcode)){
					    	  num=Double.parseDouble(stockInfo.getTotalS()+"");
					    	  unit=stockInfo.getUnit();
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
	public ResultDto<String> antiAuditInfoRdTx(AuditDto auditDto) {
		try {
			   
			String receiptSerialno=auditDto.getId();
			CStockReceipt stockReceipt2=modelDao.findById(receiptSerialno);
			
			if(stockReceipt2!=null){
				
					//审核后不再审核
			        String status=stockReceipt2.getStatus();
			        
			        CStockReceipt stockReceipt=new CStockReceipt();
				    stockReceipt.setReceiptSerialno(receiptSerialno);
					
				    stockReceipt.setStatus(auditDto.getStatus());
				    stockReceipt.setAuditingoper(auditDto.getUserId());
				    stockReceipt.setAuditingtime(new Date());
					
					modelDao.update(stockReceipt);
					log.debug("*************收料反审核修改成功*************");
					
				    if(String.valueOf(CheckStatus.OK.getCode()).equals(status))
				    {
				    	cStockFlowService.saveStockFlowRdTx(TradeType.ANTI_STOCK_RECEIPT, receiptSerialno);
						log.debug("---------收料反审核流水处理成功--------");
				    }
				    
					
			}else
			{
				    return new ResultDto<String>(false,"该数据不在本中心不允许审核");
			}
	} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
	}
    return new ResultDto<String>(true,"反审核通过");
	}
}