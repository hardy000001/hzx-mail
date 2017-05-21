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
import com.lq.lss.model.CStockInventory;
import com.lq.lss.model.CStockInventoryDetail;
import com.lq.lss.utils.exception.jta.BusinessException;
import com.lq.lss.core.dao.StockInfoDao;
import com.lq.lss.core.dao.StockInventoryDao;
import com.lq.lss.core.dao.StockInventoryDetailDao;
import com.lq.lss.core.service.StockInventoryService;
import com.lq.lss.dto.AuditDto;
import com.lq.lss.dto.StockInventoryDetailDto;
import com.lq.lss.dto.StockInventoryDto;
import com.lq.lss.enums.CheckStatus;
import com.lq.util.TimeUtil;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-29 13:26:15
 */
@Service
public class StockInventoryServiceImpl extends EasyUIServiceBase<CStockInventory, String, StockInventoryDao> implements StockInventoryService{

	@Resource
	private StockInventoryDetailDao stockInventoryDetailDao;
	@Resource
	private StockInfoDao stockInfoDao;
	
	@Override
	public ResultDto<String> saveInventoryApplyRdTx(
			StockInventoryDto stockInventoryDto) {
		
		ResultDto<String> result=null;
		try 
		{
						
			    CStockInventory stockInventory=new CStockInventory();
	            String inventorySerialno=stockInventoryDto.getInventorySerialno();
	            stockInventory.setInventorySerialno(inventorySerialno);
	            stockInventory.setDeptid(stockInventoryDto.getDeptid());
	            stockInventory.setMchcode(stockInventoryDto.getMchcode());
	            stockInventory.setInventoryoper(stockInventoryDto.getInventoryoper());
	            stockInventory.setCreateoper(stockInventoryDto.getCreateoper());
	            stockInventory.setRemark(stockInventoryDto.getRemark());
	            stockInventory.setStatus(String.valueOf(CheckStatus.INIT.getCode()));
				Date cdate=TimeUtil.get().parseTime(stockInventoryDto.getCreatetime());
				stockInventory.setCreatetime(cdate);
				modelDao.create(stockInventory);
				log.debug("---------盘点申请主表数据保存成功--------");
				
				List<StockInventoryDetailDto> stockInventoryDetailDtos=stockInventoryDto.getStockInventoryDetailDtos();
				List<CStockInventoryDetail> stockInventoryDetails=new ArrayList<CStockInventoryDetail>();
				
				if(stockInventoryDetailDtos.size()>0)
				{
	                 for (StockInventoryDetailDto detailList : stockInventoryDetailDtos) 
	                 {
	                	     CStockInventoryDetail stockInventoryDetail=new CStockInventoryDetail();
	                	     stockInventoryDetail.setInventorySerialno(inventorySerialno);
	                	     stockInventoryDetail.setMaterialcode(detailList.getMaterialcode());
	                	     stockInventoryDetail.setUnit(detailList.getUnit());
	                	     stockInventoryDetail.setRemark(detailList.getRemark());
	                	     if(StringUtils.hasLength(detailList.getAccTotalS())){
	                	    	 Double accTotalS_=Double.valueOf(detailList.getAccTotalS());
	                	    	 stockInventoryDetail.setAccTotalS(new BigDecimal(accTotalS_));
	                	     }
	                	     if(StringUtils.hasLength(detailList.getDiffrate())){
	                	    	 Double diffrate_=Double.valueOf(detailList.getDiffrate());
	                	    	 stockInventoryDetail.setDiffrate(new BigDecimal(diffrate_));
	                	     }
	                	     if(StringUtils.hasLength(detailList.getRealTotalS())){
	                	    	 Double realTotalS_=Double.valueOf(detailList.getRealTotalS());
	                	    	 stockInventoryDetail.setRealTotalS(new BigDecimal(realTotalS_));
	                	     }
	                	     stockInventoryDetails.add(stockInventoryDetail);
					 }
	                 stockInventoryDetailDao.batchCreate(stockInventoryDetails);
				     log.debug("=========盘点申请从表数据保存成功=======");
				}
			    result=new ResultDto<String>(true,"数据保存成功");
			
		} catch (NumberFormatException e){
				log.error("保存盘点数据转化异常"+e.getMessage());
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
		} catch (Exception e) {
				log.error("盘点数据保存失败"+e.getMessage());
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
		}

	    return result;
	}

	@Override
	public ResultDto<String> updateInventoryRdTx(
			StockInventoryDto stockInventoryDto) {
		ResultDto<String> result=null;
		try 
		{
						
			    CStockInventory stockInventory=new CStockInventory();
	            String inventorySerialno=stockInventoryDto.getInventorySerialno();
	            
	            CStockInventory stockInventory2=modelDao.findById(inventorySerialno);
	            Integer deptid=stockInventoryDto.getDeptid();
	            if(stockInventory2!=null && stockInventory2.getDeptid().equals(deptid))
	            {
	            	
		            	//审核后不可以修改
				        String status=stockInventory2.getStatus();
					    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
					    {
					    	return new ResultDto<String>(false,"已审核数据不可以修改");
					    }
	            	    
		            	stockInventory.setInventorySerialno(inventorySerialno);
			            stockInventory.setDeptid(deptid);
			            stockInventory.setMchcode(stockInventoryDto.getMchcode());
			            stockInventory.setInventoryoper(stockInventoryDto.getInventoryoper());
			            stockInventory.setAuditingoper(stockInventoryDto.getAuditingoper());
			            stockInventory.setRemark(stockInventoryDto.getRemark());
			            stockInventory.setStatus(stockInventoryDto.getStatus());
			            Date cdate=TimeUtil.get().parseTime(stockInventoryDto.getCreatetime());
						stockInventory.setCreatetime(cdate);
						modelDao.update(stockInventory);
						log.debug("---------盘点申请主表数据修改成功--------");
						
						//修改明细是先删除再创建
					    stockInventoryDetailDao.deleteById(inventorySerialno);
	            	    log.debug("=============盘点申请从表数据删除成功==============");
						
						List<StockInventoryDetailDto> stockInventoryDetailDtos=stockInventoryDto.getStockInventoryDetailDtos();
						List<CStockInventoryDetail> stockInventoryDetails=new ArrayList<CStockInventoryDetail>();
						
						if(stockInventoryDetailDtos.size()>0)
						{
			                 for (StockInventoryDetailDto detailList : stockInventoryDetailDtos) 
			                 {
			                	     CStockInventoryDetail stockInventoryDetail=new CStockInventoryDetail();
			                	     stockInventoryDetail.setInventorySerialno(inventorySerialno);
			                	     stockInventoryDetail.setMaterialcode(detailList.getMaterialcode());
			                	     stockInventoryDetail.setUnit(detailList.getUnit());
			                	     stockInventoryDetail.setRemark(detailList.getRemark());
			                	     if(StringUtils.hasLength(detailList.getAccTotalS())){
			                	    	 Double accTotalS_=Double.valueOf(detailList.getAccTotalS());
			                	    	 stockInventoryDetail.setAccTotalS(new BigDecimal(accTotalS_));
			                	     }
			                	     if(StringUtils.hasLength(detailList.getDiffrate())){
			                	    	 Double diffrate_=Double.valueOf(detailList.getDiffrate());
			                	    	 stockInventoryDetail.setDiffrate(new BigDecimal(diffrate_));
			                	     }
			                	     if(StringUtils.hasLength(detailList.getRealTotalS())){
			                	    	 Double realTotalS_=Double.valueOf(detailList.getRealTotalS());
			                	    	 stockInventoryDetail.setRealTotalS(new BigDecimal(realTotalS_));
			                	     }
			                	     stockInventoryDetails.add(stockInventoryDetail);
							 }
			                 stockInventoryDetailDao.batchCreate(stockInventoryDetails);
						     log.debug("=========盘点申请从表数据保存成功=======");
						}
					    result=new ResultDto<String>(true,"数据保存成功");
	            }else
				{ 
			            result=new ResultDto<String>(false,"该数据不在本中心不允许修改");
		        }
	            
		} catch (NumberFormatException e){
				log.error("保存盘点数据转化异常"+e.getMessage());
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
		} catch (Exception e) {
				log.error("盘点数据保存失败"+e.getMessage());
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
		}

	    return result;
	}

	@Override
	public ResultDto<String> deleteInventoryById(String inventorySerialno,
			String deptId) {
        
		ResultDto<String> result=null;
	    
		try {
			          
			            CStockInventory stockInventory2=modelDao.findById(inventorySerialno);
						
						if( stockInventory2 !=null && String.valueOf(stockInventory2.getDeptid()).equals(deptId))
							{   //审核后不再允许删除
						        String status=stockInventory2.getStatus();
							    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
							    {
							    	return new ResultDto<String>(false,"已审核数据不可以删除");
							    }
							    stockInventoryDetailDao.deleteById(inventorySerialno);
							    log.debug("========盘点申请从表数据删除成功========");
							   
							    modelDao.deleteById(inventorySerialno);
							    log.debug("---------盘点申请主表数据删除成功--------");
							    
							    result=new ResultDto<String>(true,"删除盘点数据成功");
						}else
						{ 
							    result=new ResultDto<String>(false,"该数据不在本中心不允许删除");
						}
			
			
		} catch (NumberFormatException e) {
						log.error("删除盘点数据转化异常"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		} catch (Exception e) {
						log.error("盘点数据删除异常"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
     }
		
	 return result;
	}

	@Override
	public ResultDto<String> auditInfoRdTx(AuditDto auditDto) {

        
		ResultDto<String> result=null;
	    
		try {
			            String inventorySerialno=auditDto.getId();
			            Integer deptId=Integer.valueOf(auditDto.getDeptId());
			            CStockInventory stockInventory2=modelDao.findById(inventorySerialno);
						
						if( stockInventory2 !=null && stockInventory2.getDeptid().equals(deptId))
						{   
								//审核后不再审核
						        String status=stockInventory2.getStatus();
							    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
							    {
							    	return new ResultDto<String>(false,"已审核数据不可以再审核");
							    }
							    CStockInventory stockInventory=new CStockInventory();
							    stockInventory.setInventorySerialno(inventorySerialno);
							    stockInventory.setStatus(auditDto.getStatus());
							    stockInventory.setAuditingtime(new Date());
							    stockInventory.setAuditingoper(auditDto.getUserId());
								modelDao.update(stockInventory);
								log.debug("---------盘点主表修改成功--------");
								
				                List<StockInventoryDetailDto> detailDtos = stockInventoryDetailDao
						                        .findInventoryDetail(inventorySerialno);
				                
				                if(detailDtos!=null && detailDtos.size()>0)
				                {
				                	List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>();
				                	 for (StockInventoryDetailDto detailDto : detailDtos) 
				                	 {
				                		     String totalS=detailDto.getRealTotalS();
				                		     if(StringUtils.hasLength(totalS))
				                		     {
						                		 CStockInfo cStockInfo=new CStockInfo();
						                		 cStockInfo.setDeptid(deptId);
								   		    	 cStockInfo.setMchcode(stockInventory2.getMchcode());
								   		    	 cStockInfo.setMaterialcode(detailDto.getMaterialcode());
								   		    	 cStockInfo.setTotalS(new BigDecimal(totalS));
								   		    	 cStockInfo.setModifytime(new Date());
								   		    	 cStockInfos.add(cStockInfo);
							   		    	 }
									 }
				                	 if(cStockInfos.size()>0)
				                	 {
					                		 stockInfoDao.batchUpdateNum(cStockInfos);
						                	 log.debug("---------盘点库存修改成功--------");
				                	 }
				                }
				                log.debug("---------盘点申请审核通过--------");
							    result=new ResultDto<String>(true,"审核通过");
						}else
						{ 
							    result=new ResultDto<String>(false,"该数据不在本中心不允许审核");
						}
			
			
		} catch (NumberFormatException e) {
						log.error("审核盘点数据转化异常"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		} catch (Exception e) {
						log.error("盘点数据审核异常"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
        }
	    return result;
	}

    
}