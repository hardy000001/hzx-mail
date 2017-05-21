package com.lq.lss.core.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.CBusSale;
import com.lq.lss.model.CBusSaleDetail;
import com.lq.lss.model.CStockInfo;
import com.lq.lss.utils.exception.jta.BusinessException;
import com.lq.lss.constant.NoticeConst;
import com.lq.lss.core.dao.BusSaleDao;
import com.lq.lss.core.dao.BusSaleDetailDao;
import com.lq.lss.core.dao.StockInfoDao;
import com.lq.lss.core.service.BusSaleService;
import com.lq.lss.core.service.CStockFlowService;
import com.lq.lss.core.service.TNoticeService;
import com.lq.lss.dto.BusSaleDetailDto;
import com.lq.lss.dto.BusSaleDto;
import com.lq.lss.enums.CheckStatus;
import com.lq.lss.enums.TradeType;
import com.lq.util.TimeUtil;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-08-31 10:21:24
 */
@Service
public class BusSaleServiceImpl extends EasyUIServiceBase<CBusSale, String, BusSaleDao> implements BusSaleService{

    @Resource
	private BusSaleDetailDao busSaleDetailDao;
    @Resource
   	private StockInfoDao stockInfoDao;
    @Resource
    private CStockFlowService cStockFlowService;
    @Resource
	private TNoticeService tNoticeService;

	@Override
	public ResultDto<String> saveSaleApplyRdTx(BusSaleDto busSaleDto) {
		ResultDto<String> result=null;
		try 
		{
			
						result = checkStockSum(busSaleDto);
						if (!result.isSuccess()) {
							return result;
						}
			            CBusSale busSale=new CBusSale();
			            String saleSerialno=busSaleDto.getSaleSerialno();
						busSale.setSaleSerialno(saleSerialno);
						busSale.setDeptid(Integer.valueOf(busSaleDto.getDeptId()));
						busSale.setToMchcode(busSaleDto.getToMchcode());
						Double totalAmt=Double.valueOf(busSaleDto.getTotalAmt());
						busSale.setTotalAmt(new BigDecimal(totalAmt));
						busSale.setCustomer(busSaleDto.getCustomer());
						busSale.setTel(busSaleDto.getTel());
						busSale.setOthers(busSaleDto.getOthers());
						busSale.setStatus(String.valueOf(CheckStatus.INIT.getCode()));
						busSale.setCreateoper(busSaleDto.getUserId()+"");
						Date cdate=TimeUtil.get().parseTime(busSaleDto.getCreatetime());
						busSale.setCreatetime(cdate);
						
						modelDao.create(busSale);
						log.debug("---------销售申请主表数据保存成功--------");
						
						
						List<BusSaleDetailDto> busSaleDetailDto=busSaleDto.getBusSaleDetailDtos();
						List<CBusSaleDetail> busSaleDetails=new ArrayList<CBusSaleDetail>();
						
						if(busSaleDetailDto.size()>0)
						{
                             for (BusSaleDetailDto detailList : busSaleDetailDto) 
                             {
                                	 CBusSaleDetail cBusSaleDetail=new CBusSaleDetail();
 									 cBusSaleDetail.setSaleSerialno(saleSerialno+"");
 									 cBusSaleDetail.setMaterialId(detailList.getMaterialId());		
 									 Double quantiy=Double.valueOf(detailList.getQuantity());
 									 cBusSaleDetail.setQuantity(new BigDecimal(quantiy));
 									 Double price=Double.valueOf(detailList.getPrice());
 									 cBusSaleDetail.setPrice(new BigDecimal(price));
 									 cBusSaleDetail.setUnit(detailList.getUnit());
 									 Double amt=Double.valueOf(detailList.getTotalAmt());
 									 cBusSaleDetail.setTotalAmt(new BigDecimal(amt));
 									 busSaleDetails.add(cBusSaleDetail);
							 }
                         	 busSaleDetailDao.batchCreate(busSaleDetails);
						     log.debug("=========销售申请从表数据保存成功=======");
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
	public ResultDto<String> updateSaleInfoRdTx(BusSaleDto busSaleDto) {
		ResultDto<String> result=null;
        
		try 
		{
				
				CBusSale busSale=new CBusSale();
				String saleSerialno=busSaleDto.getSaleSerialno();
				CBusSale cBusSale2=modelDao.findById(saleSerialno);
				String deptid=busSaleDto.getDeptId();
				if( cBusSale2 !=null  && String.valueOf(cBusSale2.getDeptid()).equals(deptid))
				{
					    
						//审核后不可再修改
				        String status=cBusSale2.getStatus();
					    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
					    {
					    	if(!String.valueOf(CheckStatus.REJECT.getCode()).equals(status))
					    	{
					    	      return new ResultDto<String>(false,"已审核数据不可以修改");
					    	}
					    }
					    
					    result = checkStockSum(busSaleDto);
						if (!result.isSuccess()) {
							return result;
						}

						//修改明细是先删除再创建
						busSaleDetailDao.deleteById(saleSerialno);
						log.debug("=============销售申请从表数据删除成功==============");
						
						List<BusSaleDetailDto> busSaleDetailDto=busSaleDto.getBusSaleDetailDtos();
						List<CBusSaleDetail> busSaleDetails=new ArrayList<CBusSaleDetail>();
						
						if(busSaleDetailDto.size()>0)
						{
                             for (BusSaleDetailDto detailList : busSaleDetailDto) 
                             {
                                	 CBusSaleDetail cBusSaleDetail=new CBusSaleDetail();
 									 cBusSaleDetail.setSaleSerialno(saleSerialno+"");
 									 cBusSaleDetail.setMaterialId(detailList.getMaterialId());		
 									 Double quantiy=Double.valueOf(detailList.getQuantity());
 									 cBusSaleDetail.setQuantity(new BigDecimal(quantiy));
 									 Double price=Double.valueOf(detailList.getPrice());
 									 cBusSaleDetail.setPrice(new BigDecimal(price));
 									 cBusSaleDetail.setUnit(detailList.getUnit());
 									 Double amt=Double.valueOf(detailList.getTotalAmt());
 									 cBusSaleDetail.setTotalAmt(new BigDecimal(amt));
 									 busSaleDetails.add(cBusSaleDetail);
							 }
                             busSaleDetailDao.batchCreate(busSaleDetails);
 							 log.debug("=========销售申请从表数据保存成功=======");	
						}
						log.debug("《《《《《《《《《《 销售申请从表数据修改成功》》》》》》》》》》");
						
						busSale.setSaleSerialno(saleSerialno);
						busSale.setDeptid(Integer.valueOf(busSaleDto.getDeptId()));
						busSale.setToMchcode(busSaleDto.getToMchcode());
						Double totalAmt=Double.valueOf(busSaleDto.getTotalAmt());
						busSale.setTotalAmt(new BigDecimal(totalAmt));
						busSale.setCustomer(busSaleDto.getCustomer());
						busSale.setTel(busSaleDto.getTel());
						busSale.setOthers(busSaleDto.getOthers());
						busSale.setStatus(busSaleDto.getStatus());
						Date cdate=TimeUtil.get().parseTime(busSaleDto.getCreatetime());
						busSale.setCreatetime(cdate);
						busSale.setStatus(CheckStatus.INIT.getCode()+"");
						modelDao.update(busSale);
						log.debug("*************销售申请主表数据修改成功*************");
						
						result=new ResultDto<String>(true,"数据修改成功");
				}else
				{ 
				        result=new ResultDto<String>(false,"该数据不在本中心不允许修改");
			    }
				
			
		} catch (NumberFormatException e){
						log.error("销售申请数据转化异常"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		} catch (Exception e) {
						log.error("销售申请数据修改异常"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		}
	
	
        return result;
	}
	
	/**
	 * 检查库存数据
	 * @return
	 */
	protected ResultDto<String> checkStockSum(BusSaleDto busSaleDto){
		
		Integer deptid=Integer.parseInt(busSaleDto.getDeptId());
		String mchcode=busSaleDto.getMchcode();
		CStockInfo cStockInfo=new CStockInfo();
		cStockInfo.setDeptid(deptid);
		cStockInfo.setMchcode(mchcode);
		List<CStockInfo> cStockInfos=stockInfoDao.findStockSum(cStockInfo);
		
		List<BusSaleDetailDto> busSaleDetailDtos=busSaleDto.getBusSaleDetailDtos();
		
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
			for (BusSaleDetailDto busSaleDetailDto : busSaleDetailDtos) {
				   totalS=Double.parseDouble(busSaleDetailDto.getQuantity());
				   if(totalS<=0){
			    		  return new ResultDto<String>(false,"物资数量必须大于0");
			       }
				   materialcode=busSaleDetailDto.getMaterialId()+"";
                   name=busSaleDetailDto.getName();
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
	public ResultDto<String> deleteSaleInfoById(String saleSerialno,
			String deptId) {
		 ResultDto<String> result=null;
		    
			try {
				          
							CBusSale cBusSale2=modelDao.findById(saleSerialno);
							
							if( cBusSale2 !=null && String.valueOf(cBusSale2.getDeptid()).equals(deptId))
							{
									//审核后不可删除
							        String status=cBusSale2.getStatus();
								    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
								    {
								    	return new ResultDto<String>(false,"已审核数据不可以删除");
								    }
								    busSaleDetailDao.deleteById(saleSerialno);
								    log.debug("=========销售申请从表数据删除成功========");
								   
								    modelDao.deleteById(saleSerialno);
								    log.debug("---------销售申请主表数据删除成功--------");
								    
								    result=new ResultDto<String>(true,"删除数据成功");
							}else
							{ 
								    result=new ResultDto<String>(false,"该数据不在本中心不允许删除");
							}
				
				
			} catch (NumberFormatException e) {
							log.error("销售申请数据转化异常"+e.getMessage());
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
			} catch (Exception e) {
							log.error("销售申请数据删除异常"+e.getMessage());
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
         }
			
		 return result;
	}

	@Override
	public ResultDto<String> auditInfoRdTx(BusSaleDto busSaleDto) 
			throws BusinessException{
			try {
					CBusSale busSale=new CBusSale();
					String saleSerialno=busSaleDto.getSaleSerialno();
					CBusSale cBusSale2=modelDao.findById(saleSerialno);
					String deptid=busSaleDto.getDeptId();
					if(cBusSale2!=null && String.valueOf(cBusSale2.getDeptid()).equals(deptid)){
						    
							String rejectCode=String.valueOf(CheckStatus.REJECT.getCode());
							 
							 if(rejectCode.equals(busSaleDto.getStatus()))
							 {
								  String title=NoticeConst.NOTICE_XS_TITLE;
								  Integer fromId=Integer.parseInt(busSaleDto.getUserId()+"");
								  Integer toId=Integer.parseInt(cBusSale2.getCreateoper());
								  Integer deptId=Integer.parseInt(busSaleDto.getDeptId());
								  tNoticeService.createNotice(fromId, deptId, toId, title, saleSerialno);
							 }
						
							//审核后不再审核
					        String status=cBusSale2.getStatus();
						    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
						    {
						    	return new ResultDto<String>(false,"已审核数据不可以再审核");
						    }
						    
						    List<CBusSaleDetail> busSaleDetails=busSaleDetailDao.queryDetailListById(saleSerialno);
						    List<BusSaleDetailDto> busSaleDetailDtos=new ArrayList<BusSaleDetailDto>();
						    for (CBusSaleDetail cBusSaleDetail : busSaleDetails) {
						    	BusSaleDetailDto busSaleDetailDto=new BusSaleDetailDto();
						    	busSaleDetailDto.setMaterialId(cBusSaleDetail.getMaterialId());
						    	busSaleDetailDto.setQuantity(cBusSaleDetail.getQuantity()+"");
						    	busSaleDetailDto.setName(cBusSaleDetail.getName());
						    	busSaleDetailDtos.add(busSaleDetailDto);
							}
						    busSaleDto.setBusSaleDetailDtos(busSaleDetailDtos);
						    
						    ResultDto<String> result = checkStockSum(busSaleDto);
							if (!result.isSuccess()) {
								return result;
							}
							busSale.setSaleSerialno(saleSerialno);
							busSale.setStatus(busSaleDto.getStatus());
							busSale.setAuditingoper(busSaleDto.getUserId()+"");
							busSale.setAuditingtime(new Date());
							modelDao.update(busSale);

							log.debug("*************销售申请审核修改成功*************");
							
							if(String.valueOf(CheckStatus.OK.getCode()).equals(busSaleDto.getStatus()))
							{
									cStockFlowService.saveStockFlowRdTx(TradeType.STOCK_XS, saleSerialno);
									log.debug("---------销售审核流水处理成功--------");
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