package com.lq.lss.core.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.constant.NoticeConst;
import com.lq.lss.core.dao.CStockFlowDao;
import com.lq.lss.core.dao.StockInfoDao;
import com.lq.lss.core.dao.StockOutDao;
import com.lq.lss.core.dao.StockOutDetailDao;
import com.lq.lss.core.dao.StockTotalflowDao;
import com.lq.lss.core.dao.StockTransferDao;
import com.lq.lss.core.dao.StockTransferDetailDao;
import com.lq.lss.core.service.CStockFlowService;
import com.lq.lss.core.service.StockOutService;
import com.lq.lss.core.service.StockTransferService;
import com.lq.lss.core.service.TNoticeService;
import com.lq.lss.dto.StockOutDetailDto;
import com.lq.lss.dto.StockOutDto;
import com.lq.lss.enums.CheckStatus;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.CStockInfo;
import com.lq.lss.model.CStockOut;
import com.lq.lss.model.CStockOutDetail;
import com.lq.lss.model.CStockTransfer;
import com.lq.lss.model.CStockTransferDetail;
import com.lq.lss.utils.SystemConst;
import com.lq.lss.utils.exception.jta.BusinessException;
import com.lq.util.RandomUtil;
import com.lq.util.TimeUtil;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-10-08 09:26:28
 */
@Service
public class StockOutServiceImpl extends EasyUIServiceBase<CStockOut, String, StockOutDao> implements StockOutService{

	@Resource
	private StockOutDetailDao stockOutDetailDao;
	@Resource
    private CStockFlowService cStockFlowService;
	@Resource
	private StockInfoDao stockInfoDao;
	@Resource
	private TNoticeService tNoticeService;
	@Resource
	private CStockFlowDao cStockFlowDao;
	@Resource
   	private StockTotalflowDao stockTotalflowDao;
	@Resource
    private StockTransferService stockTransferService;
	@Resource
	private StockTransferDao stockTransferDao;
	@Resource
	private StockTransferDetailDao stockTransferDetailDao;
	  
	@Override
	public ResultDto<String> saveStockOutApplyRdTx(StockOutDto stockOutDto) {
		ResultDto<String> result=null;
		try 
		{
//			        	result = checkStockSum(stockOutDto);
//						if (!result.isSuccess()) {
//							return result;
//						}
			           
			            CStockOut stockOut=new CStockOut();
			            String outSerialno=stockOutDto.getOutSerialno();
						stockOut.setOutSerialno(outSerialno);
						stockOut.setDeptid(stockOutDto.getDeptid());
						stockOut.setMchcode(stockOutDto.getMchcode());
						stockOut.setOutOperator(stockOutDto.getOutOperator());
						stockOut.setInOperator(stockOutDto.getInOperator());
						if(StringUtils.hasLength(stockOutDto.getTotalM())){
							Double totalM=Double.valueOf(stockOutDto.getTotalM());
							stockOut.setTotalM(new BigDecimal(totalM));
						}
						if(StringUtils.hasLength(stockOutDto.getTotalS())){
							Double totalS=Double.valueOf(stockOutDto.getTotalS());
							stockOut.setTotalS(new BigDecimal(totalS));
						}
						if(StringUtils.hasLength(stockOutDto.getTotalT())){
							Double totalT=Double.valueOf(stockOutDto.getTotalT());
							stockOut.setTotalT(new BigDecimal(totalT));
						}
						if(StringUtils.hasLength(stockOutDto.getZxFee())){
							Double zxFee=Double.valueOf(stockOutDto.getZxFee());
							stockOut.setZxFee(new BigDecimal(zxFee));
						}
						if(StringUtils.hasLength(stockOutDto.getTransportFee())){
							Double transportFee=Double.valueOf(stockOutDto.getTransportFee());
							stockOut.setTransportFee(new BigDecimal(transportFee));
						}
						if(StringUtils.hasLength(stockOutDto.getOtherFee())){
							Double otherFee=Double.valueOf(stockOutDto.getOtherFee());
							stockOut.setOtherFee(new BigDecimal(otherFee));
						}
						stockOut.setRemark(stockOutDto.getRemark());
						stockOut.setStatus("1");
						stockOut.setCreateoper(stockOutDto.getUserId()+"");
						stockOut.setCreatetime(new Date());
						Date cdate=TimeUtil.get().parseTime(stockOutDto.getTransDate());		
						stockOut.setTransDate(cdate);
						stockOut.setCarNo(stockOutDto.getCarNo());
						stockOut.setCarDriver(stockOutDto.getCarDriver());
						modelDao.create(stockOut);
						log.debug("---------出库申请主表数据保存成功--------");
						
						
						List<StockOutDetailDto> stockOutDetailDto=stockOutDto.getStockOutDetailDtos();
						List<CStockOutDetail> stockOutDetails=new ArrayList<CStockOutDetail>();
						
						if(stockOutDetailDto.size()>0)
						{
                             for (StockOutDetailDto detailList : stockOutDetailDto) 
                             {
                            	     CStockOutDetail stockOutDetail=new CStockOutDetail();
                            	     stockOutDetail.setOutSerialno(outSerialno);
                            	     stockOutDetail.setMaterialcode(detailList.getMaterialcode());
                            	     if(StringUtils.hasLength(detailList.getTotalM())){
                            	    	 Double totalM_=Double.valueOf(detailList.getTotalM());
                                	     stockOutDetail.setTotalM(new BigDecimal(totalM_));
                            	     }
                            	     if(StringUtils.hasLength(detailList.getTotalS())){
                            	    	 Double totalS_=Double.valueOf(detailList.getTotalS());
                 						 stockOutDetail.setTotalS(new BigDecimal(totalS_));
                            	     }
                            	     if(StringUtils.hasLength(detailList.getTotalT())){
                            	    	 Double totalT_=Double.valueOf(detailList.getTotalT());
                 						 stockOutDetail.setTotalT(new BigDecimal(totalT_));
                            	     }
 									 stockOutDetails.add(stockOutDetail);
							 }
                             stockOutDetailDao.batchCreate(stockOutDetails);
						     log.debug("=========出库申请从表数据保存成功=======");
						}
					
					
					    result=new ResultDto<String>(true,"数据保存成功");
			
		} catch (NumberFormatException e){
						log.error("保存出库数据转化异常"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		} catch (Exception e) {
						log.error("出库数据保存失败"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		}

	    return result;
	}

	@Override
	public ResultDto<String> updateStockOutInfoRdTx(StockOutDto stockOutDto) {
		ResultDto<String> result=null;
		try 
		{
						
			
			            CStockOut stockOut=new CStockOut();
			            String outSerialno=stockOutDto.getOutSerialno();
			            
			            CStockOut stockOut2=modelDao.findById(outSerialno);
			            String deptid=String.valueOf(stockOutDto.getDeptid());
			            if(stockOut2!=null && String.valueOf(stockOut2.getDeptid()).equals(deptid)){
			            	    
//				            	result = checkStockSum(stockOutDto);
//								if (!result.isSuccess()) {
//									return result;
//								}
				            	//审核后不可以修改
						        String status=stockOut2.getStatus();
							    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
							    {
							    	if(!String.valueOf(CheckStatus.REJECT.getCode()).equals(status))
							    	{
							    		return new ResultDto<String>(false,"已审核数据不可以修改");
							    	}
							    	
							    }
			            	    //修改明细是先删除再创建
			            	    stockOutDetailDao.deleteById(outSerialno);
			            	    log.debug("=============出库申请从表数据删除成功==============");
			            	    
								List<StockOutDetailDto> stockOutDetailDto=stockOutDto.getStockOutDetailDtos();
								List<CStockOutDetail> stockOutDetails=new ArrayList<CStockOutDetail>();
								
								if(stockOutDetailDto.size()>0)
								{
		                             for (StockOutDetailDto detailList : stockOutDetailDto) 
		                             {
		                            	     CStockOutDetail stockOutDetail=new CStockOutDetail();
		                            	     stockOutDetail.setOutSerialno(outSerialno);
		                            	     stockOutDetail.setMaterialcode(detailList.getMaterialcode());		
		                            	     if(StringUtils.hasLength(detailList.getTotalM())){
		                            	    	 Double totalM_=Double.valueOf(detailList.getTotalM());
		                                	     stockOutDetail.setTotalM(new BigDecimal(totalM_));
		                            	     }
		                            	     if(StringUtils.hasLength(detailList.getTotalS())){
		                            	    	 Double totalS_=Double.valueOf(detailList.getTotalS());
		                 						 stockOutDetail.setTotalS(new BigDecimal(totalS_));
		                            	     }
		                            	     if(StringUtils.hasLength(detailList.getTotalT())){
		                            	    	 Double totalT_=Double.valueOf(detailList.getTotalT());
		                 						 stockOutDetail.setTotalT(new BigDecimal(totalT_));
		                            	     }
		 									 stockOutDetails.add(stockOutDetail);
									 }
		                             stockOutDetailDao.batchCreate(stockOutDetails);
								     log.debug("=========出库申请从表数据保存成功=======");
								}
								log.debug("《《《《《《《《《《出库申请从表数据修改成功》》》》》》》》》》");
			            	    
								stockOut.setOutSerialno(outSerialno);
								stockOut.setDeptid(stockOutDto.getDeptid());
								stockOut.setMchcode(stockOutDto.getMchcode());
								stockOut.setOutOperator(stockOutDto.getOutOperator());
								stockOut.setInOperator(stockOutDto.getInOperator());
								if(StringUtils.hasLength(stockOutDto.getTotalM())){
									Double totalM=Double.valueOf(stockOutDto.getTotalM());
									stockOut.setTotalM(new BigDecimal(totalM));
								}
								if(StringUtils.hasLength(stockOutDto.getTotalS())){
									Double totalS=Double.valueOf(stockOutDto.getTotalS());
									stockOut.setTotalS(new BigDecimal(totalS));
								}
								if(StringUtils.hasLength(stockOutDto.getTotalT())){
									Double totalT=Double.valueOf(stockOutDto.getTotalT());
									stockOut.setTotalT(new BigDecimal(totalT));
								}
								if(StringUtils.hasLength(stockOutDto.getZxFee())){
									Double zxFee=Double.valueOf(stockOutDto.getZxFee());
									stockOut.setZxFee(new BigDecimal(zxFee));
								}
								if(StringUtils.hasLength(stockOutDto.getTransportFee())){
									Double transportFee=Double.valueOf(stockOutDto.getTransportFee());
									stockOut.setTransportFee(new BigDecimal(transportFee));
								}
								if(StringUtils.hasLength(stockOutDto.getOtherFee())){
									Double otherFee=Double.valueOf(stockOutDto.getOtherFee());
									stockOut.setOtherFee(new BigDecimal(otherFee));
								}
								stockOut.setRemark(stockOutDto.getRemark());
								stockOut.setStatus(CheckStatus.INIT.getCode()+"");
								Date cdate=TimeUtil.get().parseTime(stockOutDto.getTransDate());		
								stockOut.setTransDate(cdate);
								stockOut.setCarNo(stockOutDto.getCarNo());
								stockOut.setCarDriver(stockOutDto.getCarDriver());
							
								
								modelDao.update(stockOut);
								log.debug("************出库申请主表数据修改成功*************");
								
								result=new ResultDto<String>(true,"数据修改成功");
			            }else
						{ 
						        result=new ResultDto<String>(false,"该数据不在本中心不允许修改");
					    }
		} catch (NumberFormatException e){
						log.error("修改出库数据转化异常"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		} catch (Exception e) {
						log.error("出库数据修改失败"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		}

	    return result;
	}
	/**
	 * 检查库存数据
	 * @return
	 */
	protected ResultDto<String> checkStockSum(StockOutDto stockOutDto){
		
		Integer deptid=stockOutDto.getDeptid();
		String mchcode=stockOutDto.getMchcode();
		CStockInfo cStockInfo=new CStockInfo();
		cStockInfo.setDeptid(deptid);
		cStockInfo.setMchcode(mchcode);
		List<CStockInfo> cStockInfos=stockInfoDao.findStockSum(cStockInfo);
		
		List<StockOutDetailDto> stockOutDetailDtos=stockOutDto.getStockOutDetailDtos();
		
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
			for (StockOutDetailDto stockOutDetailDto : stockOutDetailDtos) {
				   totalS=Double.parseDouble(stockOutDetailDto.getTotalS());
				   if(totalS<=0){
			    		  return new ResultDto<String>(false,"物资数量必须大于0");
			       }
				   materialcode=stockOutDetailDto.getMaterialcode();
				   name=stockOutDetailDto.getName();
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
	/**
	 * 自动管理库存
	 * @param isAuto 是否自动
	 * @param stockOutDto
	 * @return
	 */
	private ResultDto<String> autoManageStock(StockOutDto stockOutDto)
	{
		
		//自己的库存
		CStockInfo cStockInfo=new CStockInfo();
		cStockInfo.setDeptid(stockOutDto.getDeptid());
		cStockInfo.setMchcode(stockOutDto.getMchcode());
		List<CStockInfo> cStockInfos=stockInfoDao.findStockSum(cStockInfo);
		
		List<StockOutDetailDto> stockOutDetailDtos=stockOutDto.getStockOutDetailDtos();
		
		List<StockOutDetailDto> outDetailList=null;
        
		StringBuilder sdr=new StringBuilder();
		//检验自己的库存是否够  1、自动管理 【库存够就直接出库】【库存不够需调枝星的库存】
		//              2、不自动管理 【库存不够直接提示】
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
			for (StockOutDetailDto stockOutDetailDto : stockOutDetailDtos) {
				   totalS=Double.parseDouble(stockOutDetailDto.getTotalS());
				   
				   materialcode=stockOutDetailDto.getMaterialcode();
				   name=stockOutDetailDto.getName();
				   for (CStockInfo stockInfo : cStockInfos) {
					      code=stockInfo.getMaterialcode();
					      if(code.equals(materialcode)){
					    	  num=Double.parseDouble(stockInfo.getTotalS()+"");
					    	  unit=stockInfo.getUnit();
					    	  if(totalS>num){
					    		  dif=Long.parseLong(String.format("%.0f", totalS-num));
					    		  sdr.append("物资【"+name+"】库存不足,需补充"+dif+""+unit+";");
					    		  isPass=false;
					    		  
					    		  if(outDetailList==null)
					    		  {
					    			   outDetailList=new ArrayList<StockOutDetailDto>();
					    		  }
					    		  StockOutDetailDto stockOutDetailDto2=new StockOutDetailDto();
					    		  stockOutDetailDto2.setMaterialcode(code);
					    		  stockOutDetailDto2.setName(name);
					    		  stockOutDetailDto2.setTotalS(String.format("%.0f", totalS-num));
					    		  stockOutDetailDto2.setUnit(unit);
					    		  String _totalS=stockOutDetailDto2.getTotalS();
							      Double _toatl = Double.parseDouble(_totalS)
									* Double.parseDouble(stockOutDetailDto
											.getCovertRatio());
					              String _totalM=String.format("%.2f", _toatl);
					    		  stockOutDetailDto2.setTotalM(_totalM);
					    		  outDetailList.add(stockOutDetailDto2);
					    	  }
					    	  break;
					      }
					      
				   }
			}
			boolean isAuto="1".equals(stockOutDto.getAuto());
	    	if(!isAuto)return new ResultDto<String>(isPass,sdr.toString());
		}
		//是否是枝星
		boolean isZx=SystemConst.ZX_MCHCODE.equals(stockOutDto.getMchcode());
		
		//调拨枝星的库存，如果枝星的库存不够就提示
		if(outDetailList!=null && outDetailList.size()>0 && !isZx)
		{
			isPass=true;
			sdr=new StringBuilder();
			sdr.append("【枝星库存：】");
			//枝星的库存
			CStockInfo cStockInfo2=new CStockInfo();
			cStockInfo2.setDeptid(stockOutDto.getDeptid());
			cStockInfo2.setMchcode(SystemConst.ZX_MCHCODE);
			List<CStockInfo> zxStockList=stockInfoDao.findStockSum(cStockInfo2);
			
			Double totalS;
			Double num;
			Long dif=0l;
			String materialcode;
			String code;
			String name="";
			String unit="";
			for (StockOutDetailDto stockOutDetailDto : outDetailList) {
				   totalS=Double.parseDouble(stockOutDetailDto.getTotalS());
				   
				   materialcode=stockOutDetailDto.getMaterialcode();
				   name=stockOutDetailDto.getName();
				   for (CStockInfo stockInfo : zxStockList) 
				   {
					      code=stockInfo.getMaterialcode();
					      if(code.equals(materialcode))
					      {
					    	  num=Double.parseDouble(stockInfo.getTotalS()+"");
					    	  unit=stockInfo.getUnit();
					    	  if(totalS>num)
					    	  {
						    		  dif=Long.parseLong(String.format("%.0f", totalS-num));
						    		  sdr.append("物资【"+name+"】库存不足,需补充"+dif+""+unit+";");
						    		  isPass=false;
					    	  }
					    	  break;
					      }
					      
				   }
			}
			if(isPass)
			{
				//自动调出枝星的库存（中心调出）
				String serialNo=getSerialNo("NBDC");
				CStockTransfer cStockTransfer = new CStockTransfer();
				cStockTransfer.setToMchcode(stockOutDto.getMchcode());
	            cStockTransfer.setToDeptid(stockOutDto.getDeptid());
	        
	            cStockTransfer.setTsfSdate(new Date());
	            cStockTransfer.setFromDeptid(stockOutDto.getDeptid());
	            cStockTransfer.setFromMchcode(SystemConst.ZX_MCHCODE);
	            cStockTransfer.setStatus(CheckStatus.INIT.getCode()+"");
	            
	            cStockTransfer.setZxFee(new BigDecimal(0));
	            cStockTransfer.setOtherFee(new BigDecimal(0));
	            cStockTransfer.setTransportFee(new BigDecimal(0));
	            cStockTransfer.setTsfSerialno(serialNo);
	            cStockTransfer.setCreatetime(new Date());
	            cStockTransfer.setCreateoper(stockOutDto.getUserId()+"");
	            
	            cStockTransfer.setOthers("系统自动生成内部调出单据");
	            cStockTransfer.setTraOutOper("center");
	            cStockTransfer.setTraInOper("center");
	            cStockTransfer.setTraType(TradeType.STOCK_TRANSFER_OUT.getType());
				List<CStockTransferDetail> cStockTransferDetails = new ArrayList<CStockTransferDetail>();
				for (StockOutDetailDto stockOutDetailDto : outDetailList) {
					    CStockTransferDetail cStockTransferDetail = new CStockTransferDetail();
		                cStockTransferDetail.setTsfSerialno(serialNo);
		                cStockTransferDetail.setMaterialCode(stockOutDetailDto.getMaterialcode());
		                cStockTransferDetail.setTotalS(new BigDecimal(stockOutDetailDto.getTotalS()));
		                cStockTransferDetail.setTotalT(new BigDecimal(0));
		                cStockTransferDetail.setTotalM(new BigDecimal(stockOutDetailDto.getTotalM()));
		                cStockTransferDetail.setName(stockOutDetailDto.getName());
		                cStockTransferDetail.setUnit(stockOutDetailDto.getUnit());
		                cStockTransferDetail.setStatus(CheckStatus.OK.getCode()+"");
		                cStockTransferDetails.add(cStockTransferDetail);
				}
				stockTransferService.saveStockTransferRdTx(cStockTransfer, cStockTransferDetails);
				
				//审核
				CStockTransfer cStockTransfer2 = new CStockTransfer();
				cStockTransfer2.setTsfSerialno(serialNo);
				cStockTransfer2.setStatus(CheckStatus.OK.getCode()+"");
				cStockTransfer2.setAuditingoper(stockOutDto.getUserId()+"");
			    cStockTransfer2.setFromDeptid(stockOutDto.getDeptid());
				stockTransferService.auditStockTransferOutByIdRdTx(cStockTransfer2);
				return new ResultDto<String>(isPass,serialNo);
			}
		}
		return new ResultDto<String>(isPass,sdr.toString());
	}
	/**
	 * 获取流水号
	 * @param form  来源标示
	 * @return
	 */
	private  static String getSerialNo(String form){
		String code="";
		
		code = new SimpleDateFormat("YYMMDDHHmm").format(new Date())
				+ RandomUtil.get().randomDegital(3);

		return form+code;
	}
	

	@Override
	public ResultDto<String> deleteStockOutInfoById(String outSerialno,String deptId) {
		ResultDto<String> result=null;
	    
		try {
			          
			            CStockOut cStockOut=modelDao.findById(outSerialno);
						
						if( cStockOut !=null && String.valueOf(cStockOut.getDeptid()).equals(deptId))
							{   //审核后不再允许删除
						        String status=cStockOut.getStatus();
							    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
							    {
							    	return new ResultDto<String>(false,"已审核数据不可以删除");
							    }
							    stockOutDetailDao.deleteById(outSerialno);
							    log.debug("========出库申请从表数据删除成功========");
							   
							    modelDao.deleteById(outSerialno);
							    log.debug("---------出库申请主表数据删除成功--------");
							    
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
	public ResultDto<String> auditInfoRdTx(StockOutDto stockOutDto) 
			throws BusinessException{
		 try {
				 CStockOut stockOut=new CStockOut();
				 String outSerialno=stockOutDto.getOutSerialno();
				 
				 CStockOut stockOut2=modelDao.findById(outSerialno);
				 String deptid=String.valueOf(stockOutDto.getDeptid());
				 
				  String rejectCode=String.valueOf(CheckStatus.REJECT.getCode());

				  if(rejectCode.equals(stockOutDto.getStatus()))
				  {
					  String title="";
					  if(outSerialno.contains("CK"))
					  {
						  title=NoticeConst.NOTICE_CK_TITLE;
					  }else{
						  title=NoticeConst.NOTICE_TZC_TITLE;
					  }
					  Integer fromId=Integer.parseInt(stockOutDto.getUserId()+"");
					  Integer toId=Integer.parseInt(stockOut2.getCreateoper());
					  tNoticeService.createNotice(fromId, stockOutDto.getDeptid(), toId, title, outSerialno);
				  }
				  
				 if(stockOut2!=null && String.valueOf(stockOut2.getDeptid()).equals(deptid)){
					        stockOutDto.setMchcode(stockOut2.getMchcode());
						    //审核后不再审核
					        String status=stockOut2.getStatus();
						    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
						    {
						    	return new ResultDto<String>(false,"已审核数据不可以再审核");
						    }
						    
						    List<CStockOutDetail> stockOutDetails=stockOutDetailDao.queryStockOutListById(outSerialno);
						    List<StockOutDetailDto> stockOutDetailDtos=new ArrayList<StockOutDetailDto>();
                            
						    for (CStockOutDetail cStockOutDetail : stockOutDetails) {
						    	 StockOutDetailDto stockOutDetailDto=new StockOutDetailDto();
						    	 stockOutDetailDto.setMaterialcode(cStockOutDetail.getMaterialcode());
						    	 stockOutDetailDto.setTotalS(cStockOutDetail.getTotalS()+"");
						    	 stockOutDetailDto.setName(cStockOutDetail.getName());
						    	 stockOutDetailDto.setCovertRatio(cStockOutDetail.getCovertRatio());
						    	 stockOutDetailDtos.add(stockOutDetailDto);
							}
						    stockOutDto.setStockOutDetailDtos(stockOutDetailDtos);
						    
					        stockOut.setOutSerialno(outSerialno);
						    stockOut.setStatus(stockOutDto.getStatus());
							stockOut.setAuditingoper(stockOutDto.getUserId()+"");
							stockOut.setAuditingtime(new Date());
							
							if(String.valueOf(CheckStatus.OK.getCode()).equals(stockOutDto.getStatus()))
							{

//								    ResultDto<String> result = autoManageStock(stockOutDto);
//								    
//									if (!result.isSuccess()) {
//										return result;
//									}
//									if(StringUtils.hasLength(result.getErrorMsg()))
//									{
//										stockOut.setRelSerialno(result.getErrorMsg());
//									}
								    modelDao.update(stockOut);
									log.debug("************出库申请审核成功*************");
									cStockFlowService.saveStockFlowRdTx(TradeType.STOCK_OUT, outSerialno);
									log.debug("---------出库审核流水处理成功--------");
							}else{
                                    //不是通过的状态，省去校验
								    modelDao.update(stockOut);
								    log.debug("************出库申请审核修改成功*************");
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

	@Override
	public ResultDto<String> antiAuditInfoRdTx(StockOutDto stockOutDto) 
			throws BusinessException{
		 //TODO 出库反审核先保留 
		 try {
				 CStockOut stockOut=new CStockOut();
				 String outSerialno=stockOutDto.getOutSerialno();
				 
				 CStockOut stockOut2=modelDao.findById(outSerialno);
				 String deptid=String.valueOf(stockOutDto.getDeptid());
				 
				  
				 if(stockOut2!=null && String.valueOf(stockOut2.getDeptid()).equals(deptid)){
					        stockOutDto.setMchcode(stockOut2.getMchcode());
						    //审核后不再审核
					        String status=stockOut2.getStatus();

					        stockOut.setOutSerialno(outSerialno);
						    stockOut.setStatus(stockOutDto.getStatus());
							stockOut.setAuditingoper(stockOutDto.getUserId()+"");
							stockOut.setAuditingtime(new Date());
							stockOut.setRelSerialno(" ");
							modelDao.update(stockOut);
							log.debug("************出库申请反审核成功*************");
							
						    if(String.valueOf(CheckStatus.OK.getCode()).equals(status))
						    {

										cStockFlowService.saveStockFlowRdTx(TradeType.ANTI_STOCK_OUT, outSerialno);
										String relSerialNo=stockOut2.getRelSerialno();
										
										if(StringUtils.hasLength(relSerialNo))
										{
											cStockFlowService.saveStockFlowRdTx(TradeType.ANTI_STOCK_TRANSFER_OUT, relSerialNo);
											log.debug("=====关联中心内部调出反审核库存成功====");
											stockTransferDetailDao.deleteById(relSerialNo);
											log.debug("====中心内部调出单明细删除成功====");
											stockTransferDao.deleteById(relSerialNo);
											log.debug("====中心内部调出单删除成功====");
										}
										
										log.debug("---------出库反审核流水处理成功--------");
								
						    }
						    
				 }else
				 {
					    return new ResultDto<String>(false,"该数据不在本中心不允许反审核");
				 }
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		return new ResultDto<String>(true,"反审核通过");
	}

	@Override
	public ResultDto<String> audiStockTemporaryOutRdTx(StockOutDto stockOutDto)throws BusinessException {
		// TODO Auto-generated method stub
		 try {
			 CStockOut stockOut=new CStockOut();
			 String outSerialno=stockOutDto.getOutSerialno();
			 
			 CStockOut stockOut2=modelDao.findById(outSerialno);
			 String deptid=String.valueOf(stockOutDto.getDeptid());
			 
			  String rejectCode=String.valueOf(CheckStatus.REJECT.getCode());

			  if(rejectCode.equals(stockOutDto.getStatus()))
			  {
				  String title="";
				  if(outSerialno.contains("CK"))
				  {
					  title=NoticeConst.NOTICE_CK_TITLE;
				  }else{
					  title=NoticeConst.NOTICE_TZC_TITLE;
				  }
				  Integer fromId=Integer.parseInt(stockOutDto.getUserId()+"");
				  Integer toId=Integer.parseInt(stockOut2.getCreateoper());
				  tNoticeService.createNotice(fromId, stockOutDto.getDeptid(), toId, title, outSerialno);
			  }
			  
			 if(stockOut2!=null && String.valueOf(stockOut2.getDeptid()).equals(deptid)){
				        stockOutDto.setMchcode(stockOut2.getMchcode());
					    //审核后不再审核
				        String status=stockOut2.getStatus();
					    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
					    {
					    	return new ResultDto<String>(false,"已审核数据不可以再审核");
					    }
					    
					    List<CStockOutDetail> stockOutDetails=stockOutDetailDao.queryStockOutListById(outSerialno);
					    List<StockOutDetailDto> stockOutDetailDtos=new ArrayList<StockOutDetailDto>();
                        
					    for (CStockOutDetail cStockOutDetail : stockOutDetails) {
					    	 StockOutDetailDto stockOutDetailDto=new StockOutDetailDto();
					    	 stockOutDetailDto.setMaterialcode(cStockOutDetail.getMaterialcode());
					    	 stockOutDetailDto.setTotalS(cStockOutDetail.getTotalS()+"");
					    	 stockOutDetailDto.setName(cStockOutDetail.getName());
					    	 stockOutDetailDto.setCovertRatio(cStockOutDetail.getCovertRatio());
					    	 stockOutDetailDtos.add(stockOutDetailDto);
						}
					    stockOutDto.setStockOutDetailDtos(stockOutDetailDtos);
					    
				        stockOut.setOutSerialno(outSerialno);
					    stockOut.setStatus(stockOutDto.getStatus());
						stockOut.setAuditingoper(stockOutDto.getUserId()+"");
						stockOut.setAuditingtime(new Date());
						
						if(String.valueOf(CheckStatus.OK.getCode()).equals(stockOutDto.getStatus()))
						{

//							    ResultDto<String> result = autoManageStock(stockOutDto);
//							    
//								if (!result.isSuccess()) {
//									return result;
//								}
//								if(StringUtils.hasLength(result.getErrorMsg()))
//								{
//									stockOut.setRelSerialno(result.getErrorMsg());
//								}
							    modelDao.update(stockOut);
								log.debug("************提暂存申请审核成功*************");
								cStockFlowService.saveStockFlowRdTx(TradeType.STOCK_TEMPORARY_OUT, outSerialno);
								log.debug("---------提暂存核流水处理成功--------");
						}else{
                                //不是通过的状态，省去校验
							    modelDao.update(stockOut);
							    log.debug("************提暂存申请审核修改成功*************");
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

	@Override
	public ResultDto<String> antiAudiStockTemporaryOutRdTx(StockOutDto stockOutDto) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		 try {
			 CStockOut stockOut=new CStockOut();
			 String outSerialno=stockOutDto.getOutSerialno();
			 
			 CStockOut stockOut2=modelDao.findById(outSerialno);
			 String deptid=String.valueOf(stockOutDto.getDeptid());
			 
			  
			 if(stockOut2!=null && String.valueOf(stockOut2.getDeptid()).equals(deptid)){
				        stockOutDto.setMchcode(stockOut2.getMchcode());
					    //审核后不再审核
				        String status=stockOut2.getStatus();

				        stockOut.setOutSerialno(outSerialno);
					    stockOut.setStatus(stockOutDto.getStatus());
						stockOut.setAuditingoper(null);
						stockOut.setAuditingtime(null);
						stockOut.setRelSerialno(" ");
						
						log.debug("************提暂存申请反审核成功*************");
						
					    if(String.valueOf(CheckStatus.OK.getCode()).equals(status))
					    {

					    	result=cStockFlowService.saveStockFlowRdTx(TradeType.ANTI_STOCK_TEMPORARY_OUT, outSerialno);
									if (!result.isSuccess()){
										//操作流水失败
										throw new BusinessException(result.getErrorMsg());
									}
									log.debug("---------暂存反审核流水处理--------");			
							
					    }
					    
					    modelDao.update(stockOut);
					    
			 }else
			 {
				    return new ResultDto<String>(false,"该数据不在本中心不允许反审核");
			 }
	} catch (Exception e) {
		e.printStackTrace();
		throw new BusinessException(e.getMessage());
	}
	return new ResultDto<String>(true,"反审核通过");
	}
}