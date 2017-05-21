package com.lq.lss.core.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.CBusPur;
import com.lq.lss.model.CBusPurDetail;
import com.lq.lss.model.CStockInfo;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.exception.jta.BusinessException;
import com.lq.lss.constant.NoticeConst;
import com.lq.lss.core.dao.BusPurDao;
import com.lq.lss.core.dao.BusPurDetailDao;
import com.lq.lss.core.dao.StockInfoDao;
import com.lq.lss.core.service.BusPurService;
import com.lq.lss.core.service.CStockFlowService;
import com.lq.lss.core.service.TNoticeService;
import com.lq.lss.dto.BusPurDetailDto;
import com.lq.lss.dto.BusPurDto;
import com.lq.lss.enums.CheckStatus;
import com.lq.lss.enums.TradeType;
import com.lq.util.TimeUtil;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-08-31 10:21:24
 */
@Service
public class BusPurServiceImpl extends EasyUIServiceBase<CBusPur, String, BusPurDao> implements BusPurService{

    @Resource
	private BusPurDetailDao busPurDetailDao;
    @Resource
	private StockInfoDao stockInfoDao;
    @Resource
    private CStockFlowService cStockFlowService;
    @Resource
	private TNoticeService tNoticeService;

	@Override
	public ResultDto<String> savePurApplyRdTx(BusPurDto busPurDto,
			SessionUser user) throws BusinessException{
		
			
			ResultDto<String> result=null;
			try 
			{
							
							CBusPur cBusPur=new CBusPur();
							String purSerialno=busPurDto.getPurSerialno();
							cBusPur.setPurSerialno(purSerialno);
							cBusPur.setDeptid(user.getCenterId());
							cBusPur.setFromMchcode(busPurDto.getFromMchcode());
							Double totalAmt=Double.valueOf(busPurDto.getTotalAmt());
							cBusPur.setTotalAmt(new BigDecimal(totalAmt));
							cBusPur.setOther(busPurDto.getOther());
							cBusPur.setStatus("1");
							cBusPur.setCreateoper(user.getUserId()+"");
							Date cdate=TimeUtil.get().parseTime(busPurDto.getCreatetime());
							cBusPur.setCreatetime(cdate);
							
							modelDao.create(cBusPur);
							log.debug("---------采购申请主表数据保存成功--------");
							
							
							List<BusPurDetailDto> busPurDetailDto=busPurDto.getBusPurDetailDtos();
							List<CBusPurDetail> busPurDetails=new ArrayList<CBusPurDetail>();
							
							if(busPurDetailDto.size()>0)
							{
                                 for (BusPurDetailDto detailList : busPurDetailDto) 
                                 {
	                                	 CBusPurDetail cBusPurDetail=new CBusPurDetail();
	 									 cBusPurDetail.setPurSerialno(purSerialno+"");
	 									 cBusPurDetail.setMaterialId(detailList.getMaterialId());		
	 									 Double quantiy=Double.valueOf(detailList.getQuantity());
	 									 cBusPurDetail.setQuantity(new BigDecimal(quantiy));
	 									 Double price=Double.valueOf(detailList.getPrice());
	 									 cBusPurDetail.setPrice(new BigDecimal(price));
	 									 cBusPurDetail.setUnit(detailList.getUnit());
	 									 Double amt=Double.valueOf(detailList.getTotalAmt());
	 									 cBusPurDetail.setTotalAmt(new BigDecimal(amt));
	 									 busPurDetails.add(cBusPurDetail);
								 }
                             	 busPurDetailDao.batchCreate(busPurDetails);
    						     log.debug("=========采购申请从表数据保存成功=======");
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
	public ResultDto<String> updatePurInfoRdTx(BusPurDto busPurDto,
			SessionUser user) throws BusinessException{
		
			ResultDto<String> result=null;
			        
			try 
			{
					
					CBusPur cBusPur=new CBusPur();
					String purSerialno=busPurDto.getPurSerialno();
					CBusPur cBusPur2=modelDao.findById(purSerialno);
					String deptid=String.valueOf(busPurDto.getDeptid());
					if( cBusPur2 !=null  && String.valueOf(cBusPur2.getDeptid()).equals(deptid))
					{       
							    //审核后不再允许修改
						        String status=cBusPur2.getStatus();
							    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
							    {
							    	if(!String.valueOf(CheckStatus.REJECT.getCode()).equals(status))
							    	{
							    	   return new ResultDto<String>(false,"已审核数据不可以修改");
							    	}
							    }
						    	//修改明细是先删除再创建
								busPurDetailDao.deleteById(purSerialno);
								log.debug("=============采购申请从表数据删除成功==============");
								
								List<BusPurDetailDto> busPurDetailDto=busPurDto.getBusPurDetailDtos();
								List<CBusPurDetail> busPurDetails=new ArrayList<CBusPurDetail>();
								
								if(busPurDetailDto.size()>0)
								{
	                                 for (BusPurDetailDto detailList : busPurDetailDto) 
	                                 {
		                                	 CBusPurDetail cBusPurDetail=new CBusPurDetail();
		 									 cBusPurDetail.setPurSerialno(purSerialno+"");
		 									 cBusPurDetail.setMaterialId(detailList.getMaterialId());		
		 									 Double quantiy=Double.valueOf(detailList.getQuantity());
		 									 cBusPurDetail.setQuantity(new BigDecimal(quantiy));
		 									 Double price=Double.valueOf(detailList.getPrice());
		 									 cBusPurDetail.setPrice(new BigDecimal(price));
		 									 cBusPurDetail.setUnit(detailList.getUnit());
		 									 Double amt=Double.valueOf(detailList.getTotalAmt());
		 									 cBusPurDetail.setTotalAmt(new BigDecimal(amt));
		 									 busPurDetails.add(cBusPurDetail);
									 }
	                                 busPurDetailDao.batchCreate(busPurDetails);
	     							 log.debug("=========采购申请从表数据保存成功=======");	
								}
								log.debug("《《《《《《《《《《 采购申请从表数据修改成功》》》》》》》》》》");
								
								cBusPur.setPurSerialno(purSerialno);
								cBusPur.setDeptid(user.getCenterId());
								cBusPur.setFromMchcode(busPurDto.getFromMchcode());
								Double totalAmt=Double.valueOf(busPurDto.getTotalAmt());
								cBusPur.setTotalAmt(new BigDecimal(totalAmt));
								cBusPur.setOther(busPurDto.getOther());
								cBusPur.setStatus(CheckStatus.INIT.getCode()+"");
								
								modelDao.update(cBusPur);
								log.debug("*************采购申请主表数据修改成功*************");
								
								result=new ResultDto<String>(true,"数据修改成功");
						    
					}else
					{ 
					        result=new ResultDto<String>(false,"该数据不在本中心不允许修改");
				    }
					
				
			} catch (NumberFormatException e){
							log.error("采购申请数据转化异常"+e.getMessage());
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
			} catch (Exception e) {
							log.error("采购申请数据修改异常"+e.getMessage());
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
			}
		
		
	        return result;
	}
	
	/**
	 * 检查库存数据    采购不需检查库存， 该方法暂时保留在此
	 * @return
	 */
	protected ResultDto<String> checkStockSum(BusPurDto busPurDto){
		
		Integer deptid=busPurDto.getDeptid();
		String mchcode=busPurDto.getMchcode();
		CStockInfo cStockInfo=new CStockInfo();
		cStockInfo.setDeptid(deptid);
		cStockInfo.setMchcode(mchcode);
		List<CStockInfo> cStockInfos=stockInfoDao.findStockSum(cStockInfo);
		
		List<BusPurDetailDto> busPurDetailDtos=busPurDto.getBusPurDetailDtos();
		
		StringBuilder sdr=new StringBuilder();
		
	    boolean isPass=true;
		
		if(cStockInfos!=null && cStockInfos.size()>0)
		{
			Double totalS;
			Double num;
			String materialcode;
			String code;
			for (BusPurDetailDto busPurDetailDto : busPurDetailDtos) {
				   totalS=Double.parseDouble(busPurDetailDto.getQuantity());
				   if(totalS<=0){
			    		  return new ResultDto<String>(false,"物资数量必须大于0");
			       }
				   materialcode=busPurDetailDto.getMaterialId()+"";
				   for (CStockInfo stockInfo : cStockInfos) {
					      code=stockInfo.getMaterialcode();
					      if(code.equals(materialcode)){
					    	  num=Double.parseDouble(stockInfo.getTotalS()+"");
					    	  if(totalS>num){
					    		  sdr.append("编号:"+code+"库存不足;");
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
	public ResultDto<String> deletePurInfoById(String purSerialno, String deptId) 
			throws BusinessException{
		
		    ResultDto<String> result=null;
		    
			try {
				
							CBusPur cBusPur2=modelDao.findById(purSerialno);
							
							if( cBusPur2 !=null && String.valueOf(cBusPur2.getDeptid()).equals(deptId))
							{
									//审核后不再允许删除
							        String status=cBusPur2.getStatus();
								    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
								    {
								    	return new ResultDto<String>(false,"已审核数据不可以删除");
								    }
								    busPurDetailDao.deleteById(purSerialno);
								    log.debug("=========采购申请从表数据删除成功========");
								   
								    modelDao.deleteById(purSerialno);
								    log.debug("---------采购申请主表数据删除成功--------");
								    
								    result=new ResultDto<String>(true,"删除数据成功");
							}else
							{ 
								    result=new ResultDto<String>(false,"该数据不在本中心不允许删除");
							}
				
				
			} catch (NumberFormatException e) {
							log.error("采购申请数据转化异常"+e.getMessage());
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
			} catch (Exception e) {
							log.error("采购申请数据删除异常"+e.getMessage());
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
            }
			
		    return result;
	}

	@Override
	public ResultDto<String> auditInfoRdTx(BusPurDto busPurDto, SessionUser user) 
			throws BusinessException{
		        
		        try {
						CBusPur cBusPur=new CBusPur();
						String purSerialno=busPurDto.getPurSerialno();
						CBusPur cBusPur2=modelDao.findById(purSerialno);
						String deptid=String.valueOf(busPurDto.getDeptid());
						if(cBusPur2!=null && String.valueOf(cBusPur2.getDeptid()).equals(deptid)){
							    
								String rejectCode=String.valueOf(CheckStatus.REJECT.getCode());
								 
								 if(rejectCode.equals(busPurDto.getStatus()))
								 {
									  String title=NoticeConst.NOTICE_CG_TITLE;
									  Integer fromId=Integer.parseInt(user.getUserId()+"");
									  Integer toId=Integer.parseInt(cBusPur2.getCreateoper());
									  tNoticeService.createNotice(fromId, busPurDto.getDeptid(), toId, title, purSerialno);
								 }
							    
								//审核后不再审核
						        String status=cBusPur2.getStatus();
							    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
							    {
							    	return new ResultDto<String>(false,"已审核数据不可以再审核");
							    }
								cBusPur.setPurSerialno(purSerialno);
								
								cBusPur.setStatus(busPurDto.getStatus());
								cBusPur.setAuditingoper(user.getUserId()+"");
								cBusPur.setAuditingtime(new Date());
								
								modelDao.update(cBusPur);
								log.debug("*************采购申请审核修改成功*************");
								
								if(String.valueOf(CheckStatus.OK.getCode()).equals(busPurDto.getStatus()))
								{
									    cStockFlowService.saveStockFlowRdTx(TradeType.STOCK_CG, purSerialno);
										log.debug("---------采购审核流水处理成功--------");
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
	
}