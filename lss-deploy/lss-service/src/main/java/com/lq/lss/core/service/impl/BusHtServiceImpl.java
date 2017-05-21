package com.lq.lss.core.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.BusHtDao;
import com.lq.lss.core.dao.BusHtRentinfoDao;
import com.lq.lss.core.dao.BusHtRepairinfoDao;
import com.lq.lss.core.dao.BusHtSettlementDao;

import com.lq.lss.core.service.BusHtService;
import com.lq.lss.dto.AuditDto;
import com.lq.lss.dto.BusHtDto;
import com.lq.lss.dto.BusHtRentinfoDto;
import com.lq.lss.dto.BusHtRepairinfoDto;
import com.lq.lss.dto.BusHtSettlementDto;
import com.lq.lss.enums.CheckStatus;
import com.lq.lss.model.CBusHt;
import com.lq.lss.model.CBusHtRentinfo;
import com.lq.lss.model.CBusHtRepairinfo;
import com.lq.lss.model.CBusHtSettlement;
import com.lq.lss.utils.exception.jta.BusinessException;
import com.lq.util.TimeUtil;
/**
 * 
 * @author  作者: hzx
 * @date 创建时间: 2016-10-26下午5:04:51
 */
@Service
public class BusHtServiceImpl extends EasyUIServiceBase<CBusHt, String, BusHtDao> implements BusHtService{
	
	
	@Resource
	private BusHtSettlementDao busHtSettlementDao;
	@Resource
	private BusHtRentinfoDao busHtRentinfoDao;
	@Resource
	private BusHtRepairinfoDao busHtRepairinfoDao;
	@Resource
	private BusHtDao busHtDao;
	@Override
	public ResultDto<String> saveBusHtRdTx(BusHtDto busHtDto) throws BusinessException{
		ResultDto<String> result=null;
		try 
		{
			            String htcode=busHtDto.getHtcode();
	                    CBusHt busHt2=modelDao.findById(htcode);
	                    
	                    if(busHt2!=null){
	                    	return new ResultDto<String>(false,"数据已存在，不可重复添加");
	                    }
						
			            CBusHt busHt=new CBusHt();
			            busHt.setHtcode(htcode);
			            busHt.setDeptid(busHtDto.getDeptid());
			            busHt.setComName(busHtDto.getComName());
			            busHt.setComAddress(busHtDto.getComAddress());
			            busHt.setComLinkman(busHtDto.getComLinkman());
			            busHt.setComTel(busHtDto.getComTel());
			            busHt.setComType(busHtDto.getComType());
			            busHt.setProjectName(busHtDto.getProjectName());
						Double projectDeposit=Double.valueOf(busHtDto.getProjectDeposit());
						busHt.setProjectDeposit(new BigDecimal(projectDeposit));
						busHt.setProjectAdress(busHtDto.getProjectAdress());
						Date beginDate=TimeUtil.get().parseDate(busHtDto.getBeginDate());
						busHt.setBeginDate(beginDate);
						Date endDate=TimeUtil.get().parseDate(busHtDto.getEndDate());
						busHt.setEndDate(endDate);
						Double totalDays=Double.valueOf(busHtDto.getTotalDays());
						busHt.setTotalDays(new BigDecimal(totalDays));
						busHt.setHtType(busHtDto.getHtType());
			            busHt.setStatus("1");
			            busHt.setCreateoper(busHtDto.getCreateoper());
						Date cdate=TimeUtil.get().parseTime(busHtDto.getCreatetime());
						busHt.setCreatetime(cdate);
						busHt.setLesseeCusCode(busHtDto.getLesseeCusCode());
						busHt.setAssureCusCode(busHtDto.getAssureCusCode());
						
						modelDao.create(busHt);
						log.debug("---------合同主表数据保存成功--------");
						
						CBusHtSettlement busHtSettlement=new CBusHtSettlement();
			           
						busHtSettlement.setHtcode(htcode);
						BusHtSettlementDto busHtSettlementDto=busHtDto.getBusHtSettlementDto();
						
						Double getonFee=Double.valueOf(busHtSettlementDto.getGetonFee());
						busHtSettlement.setGetonFee(new BigDecimal(getonFee));
						
						Double getoffFee=Double.valueOf(busHtSettlementDto.getGetoffFee());
						busHtSettlement.setGetoffFee(new BigDecimal(getoffFee));
						
						Double transportFee=Double.valueOf(busHtSettlementDto.getTransportFee());
						busHtSettlement.setTransportFee(new BigDecimal(transportFee));
						
						Double deposit=Double.valueOf(busHtSettlementDto.getDeposit());
						busHtSettlement.setDeposit(new BigDecimal(deposit));
						
						Double lateFee=Double.valueOf(busHtSettlementDto.getLateFee());
						busHtSettlement.setLateFee(new BigDecimal(lateFee));
						
						Double bagFee=Double.valueOf(busHtSettlementDto.getBagFee());
						busHtSettlement.setBagFee(new BigDecimal(bagFee));
						
						busHtSettlement.setLateBegdate(busHtSettlementDto.getLateBegdate());
						busHtSettlement.setZxfeeBelong(busHtSettlementDto.getZxfeeBelong());
						busHtSettlement.setTransportfeeBelong(busHtSettlementDto.getTransportfeeBelong());
						busHtSettlement.setOtherfeeBelong(busHtSettlementDto.getOtherfeeBelong());
						busHtSettlement.setSettlementType(busHtSettlementDto.getSettlementType());
						
						busHtSettlementDao.create(busHtSettlement);
						log.debug("---------合同从表杂费数据保存成功--------");
						
						List<BusHtRentinfoDto> busHtRentinfoDtos=busHtDto.getBusHtRentinfoDtos();
						List<CBusHtRentinfo> busHtRentinfos=new ArrayList<CBusHtRentinfo>();
	
						if(busHtRentinfoDtos.size()>0)
						{
                             for (BusHtRentinfoDto detailList : busHtRentinfoDtos) 
                             {
                            	     CBusHtRentinfo busHtRentinfo=new CBusHtRentinfo();
                            	     busHtRentinfo.setHtcode(htcode);
                            	     busHtRentinfo.setMaterialcode(detailList.getMaterialcode());		
 									 Double quantiy=Double.valueOf(detailList.getQuantity());
 									 busHtRentinfo.setQuantity(new BigDecimal(quantiy));
 									 busHtRentinfo.setTonQantity(detailList.getTonQantity());
 									 Double rentalDay=Double.valueOf(detailList.getRentalDay());
 									 busHtRentinfo.setRentalDay(new BigDecimal(rentalDay));
 									 busHtRentinfo.setUnit(detailList.getUnit());
 									 busHtRentinfos.add(busHtRentinfo);
							 }
                             busHtRentinfoDao.batchCreate(busHtRentinfos);
						     log.debug("=========合同从表租费数据保存成功=======");
						}
						
						List<BusHtRepairinfoDto> bHtRepairinfoDtos=busHtDto.getBusHtRepairinfoDtos();
						List<CBusHtRepairinfo> busHtRepairinfos=new ArrayList<CBusHtRepairinfo>();
	
						if(bHtRepairinfoDtos.size()>0)
						{
                             for (BusHtRepairinfoDto detailList : bHtRepairinfoDtos) 
                             {
                            	     CBusHtRepairinfo busHtRepairinfo=new CBusHtRepairinfo();
                            	     busHtRepairinfo.setHtcode(htcode);
                            	     busHtRepairinfo.setMaterialcode(detailList.getMaterialcode());	
 									 Double price=Double.valueOf(detailList.getPrice());
 									 busHtRepairinfo.setPrice(new BigDecimal(price));
 									 busHtRepairinfo.setUnit(detailList.getUnit());
 									 busHtRepairinfo.setRepairId(detailList.getRepairId());
 									 busHtRepairinfos.add(busHtRepairinfo);
							 }
                             busHtRepairinfoDao.batchCreate(busHtRepairinfos);
						     log.debug("=========合同从表维修费数据保存成功=======");
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
	public ResultDto<String> updateBusHtRdTx(BusHtDto busHtDto) throws BusinessException{
		ResultDto<String> result=null;
		try 
		{
			
			            String htcode=busHtDto.getHtcode();
			            CBusHt busHt2=modelDao.findById(htcode);
			            
			            if( busHt2!=null){
			            	   
				            	//已审核数据不可以修改
						        String status=busHt2.getStatus();
							    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
							    {
							    	return new ResultDto<String>(false,"已审核数据不可以修改");
							    }
			            	
					            CBusHt busHt=new CBusHt();
					            busHt.setHtcode(htcode);
					            busHt.setDeptid(busHtDto.getDeptid());
					            busHt.setComName(busHtDto.getComName());
					            busHt.setComAddress(busHtDto.getComAddress());
					            busHt.setComLinkman(busHtDto.getComLinkman());
					            busHt.setComTel(busHtDto.getComTel());
					            busHt.setComType(busHtDto.getComType());
					            busHt.setProjectName(busHtDto.getProjectName());
								Double projectDeposit=Double.valueOf(busHtDto.getProjectDeposit());
								busHt.setProjectDeposit(new BigDecimal(projectDeposit));
								busHt.setProjectAdress(busHtDto.getProjectAdress());
								Date beginDate=TimeUtil.get().parseDate(busHtDto.getBeginDate());
								busHt.setBeginDate(beginDate);
								Date endDate=TimeUtil.get().parseDate(busHtDto.getEndDate());
								busHt.setEndDate(endDate);
								Double totalDays=Double.valueOf(busHtDto.getTotalDays());
								busHt.setTotalDays(new BigDecimal(totalDays));
								busHt.setHtType(busHtDto.getHtType());
					            busHt.setStatus(busHtDto.getStatus());
					            busHt.setAuditingoper(busHtDto.getAuditingoper());
					            Date cdate=TimeUtil.get().parseTime(busHtDto.getCreatetime());
								busHt.setCreatetime(cdate);
								Date auditingtime=TimeUtil.get().parseTime(busHtDto.getAuditingtime());
								busHt.setAuditingtime(auditingtime);
								busHt.setLesseeCusCode(busHtDto.getLesseeCusCode());
								busHt.setAssureCusCode(busHtDto.getAssureCusCode());
								
								modelDao.update(busHt);
								log.debug("---------合同主表数据修改成功--------");
								
								CBusHtSettlement busHtSettlement=new CBusHtSettlement();
								busHtSettlement.setHtcode(htcode);
								BusHtSettlementDto busHtSettlementDto=busHtDto.getBusHtSettlementDto();
								
								Double getonFee=Double.valueOf(busHtSettlementDto.getGetonFee());
								busHtSettlement.setGetonFee(new BigDecimal(getonFee));
								
								Double getoffFee=Double.valueOf(busHtSettlementDto.getGetoffFee());
								busHtSettlement.setGetoffFee(new BigDecimal(getoffFee));
								
								Double transportFee=Double.valueOf(busHtSettlementDto.getTransportFee());
								busHtSettlement.setTransportFee(new BigDecimal(transportFee));
								
								Double deposit=Double.valueOf(busHtSettlementDto.getDeposit());
								busHtSettlement.setDeposit(new BigDecimal(deposit));
								
								Double lateFee=Double.valueOf(busHtSettlementDto.getLateFee());
								busHtSettlement.setLateFee(new BigDecimal(lateFee));
								
								Double bagFee=Double.valueOf(busHtSettlementDto.getBagFee());
								busHtSettlement.setBagFee(new BigDecimal(bagFee));
								
								busHtSettlement.setLateBegdate(busHtSettlementDto.getLateBegdate());
								busHtSettlement.setZxfeeBelong(busHtSettlementDto.getZxfeeBelong());
								busHtSettlement.setTransportfeeBelong(busHtSettlementDto.getTransportfeeBelong());
								busHtSettlement.setOtherfeeBelong(busHtSettlementDto.getOtherfeeBelong());
								busHtSettlement.setSettlementType(busHtSettlementDto.getSettlementType());
								
								busHtSettlementDao.update(busHtSettlement);
								log.debug("---------合同从表杂费数据修改成功--------");
								
								//修改明细是先删除再创建
								busHtRepairinfoDao.deleteById(htcode);
								log.debug("=============合同从表修理费数据删除成功==============");
								busHtRentinfoDao.deleteById(htcode);
								log.debug("=============合同从表租费数据删除成功==============");
								
								List<BusHtRentinfoDto> busHtRentinfoDtos=busHtDto.getBusHtRentinfoDtos();
								List<CBusHtRentinfo> busHtRentinfos=new ArrayList<CBusHtRentinfo>();
			
								if(busHtRentinfoDtos.size()>0)
								{
		                             for (BusHtRentinfoDto detailList : busHtRentinfoDtos) 
		                             {
		                            	     CBusHtRentinfo busHtRentinfo=new CBusHtRentinfo();
		                            	     busHtRentinfo.setHtcode(htcode);
		                            	     busHtRentinfo.setMaterialcode(detailList.getMaterialcode());		
		 									 Double quantiy=Double.valueOf(detailList.getQuantity());
		 									 busHtRentinfo.setQuantity(new BigDecimal(quantiy));
		 									 busHtRentinfo.setTonQantity(detailList.getTonQantity());
		 									 Double rentalDay=Double.valueOf(detailList.getRentalDay());
		 									 busHtRentinfo.setRentalDay(new BigDecimal(rentalDay));
		 									 busHtRentinfo.setUnit(detailList.getUnit());
		 									 busHtRentinfos.add(busHtRentinfo);
									 }
		                             busHtRentinfoDao.batchCreate(busHtRentinfos);
								     log.debug("=========合同从表租费数据保存成功=======");
								}
								
								List<BusHtRepairinfoDto> bHtRepairinfoDtos=busHtDto.getBusHtRepairinfoDtos();
								List<CBusHtRepairinfo> busHtRepairinfos=new ArrayList<CBusHtRepairinfo>();
			
								if(bHtRepairinfoDtos.size()>0)
								{
		                             for (BusHtRepairinfoDto detailList : bHtRepairinfoDtos) 
		                             {
		                            	     CBusHtRepairinfo busHtRepairinfo=new CBusHtRepairinfo();
		                            	     busHtRepairinfo.setHtcode(htcode);
		                            	     busHtRepairinfo.setMaterialcode(detailList.getMaterialcode());	
		 									 Double price=Double.valueOf(detailList.getPrice());
		 									 busHtRepairinfo.setPrice(new BigDecimal(price));
		 									 busHtRepairinfo.setUnit(detailList.getUnit());
		 									 busHtRepairinfo.setRepairId(detailList.getRepairId());
		 									 busHtRepairinfos.add(busHtRepairinfo);
									 }
		                             busHtRepairinfoDao.batchCreate(busHtRepairinfos);
								     log.debug("=========合同从表维修费数据保存成功=======");
								}
								log.debug("《《《《《《《《《《 合同从表数据修改成功》》》》》》》》》》");
							    result=new ResultDto<String>(true,"数据修改成功");
							    
			            }
			            else
			            {
				            	result=new ResultDto<String>(false,"数据不存在");
				        }
			
		} catch (NumberFormatException e){
						log.error("合同数据转化异常"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		} catch (Exception e) {
						log.error("合同数据修改失败"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		}

	    return result;
	}

	@Override
	public ResultDto<String> deleteBusHtById(String htcode, String deptId) {
		ResultDto<String> result=null;
	    
		try {
			          
			            CBusHt busHt2=modelDao.findById(htcode);
						
						if( busHt2 !=null && String.valueOf(busHt2.getDeptid()).equals(deptId))
						{
								//审核后不再允许删除
						        String status=busHt2.getStatus();
							    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
							    {
							    	return new ResultDto<String>(false,"已审核数据不可以删除");
							    }
								
							    busHtSettlementDao.deleteById(htcode);
							    log.debug("=========合同从表杂费数据删除成功========");
							    
							    busHtRentinfoDao.deleteById(htcode);
							    log.debug("---------合同从表租费数据删除成功--------");
							    
							    busHtRepairinfoDao.deleteById(htcode);
							    log.debug("=========合同从表维修数据删除成功========");
							   
							    modelDao.deleteById(htcode);
							    log.debug("---------合同主表数据删除成功--------");
							    
							    result=new ResultDto<String>(true,"删除数据成功");
						}else
						{ 
							    result=new ResultDto<String>(false,"该数据不在本中心不允许删除");
						}
			
			
		} catch (NumberFormatException e) {
						log.error("合同删除数据转化异常"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		} catch (Exception e) {
						log.error("合同删除异常"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
     }
		
	 return result;
	}

	@Override
	public ResultDto<String> auditInfoRdTx(AuditDto auditDto) {

        try {
        	    CBusHt cBusHt=new CBusHt();
				String htcode=auditDto.getId();
				CBusHt cBusHt2=modelDao.findById(htcode);
				String deptid=String.valueOf(auditDto.getDeptId());
				if(cBusHt2!=null && String.valueOf(cBusHt2.getDeptid()).equals(deptid)){
						//审核后不再审核
				        String status=cBusHt2.getStatus();
					    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
					    {
					    	return new ResultDto<String>(false,"已审核数据不可以再审核");
					    }
					    cBusHt.setHtcode(htcode);
						
					    cBusHt.setStatus(auditDto.getStatus());
					    cBusHt.setAuditingoper(auditDto.getUserId());
					    cBusHt.setAuditingtime(new Date());
						
						modelDao.update(cBusHt);
						log.debug("*************合同审核修改成功*************");
						
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
	public CBusHt findCBusHtbyHtcode(String htcode) {
		
		return modelDao.findById(htcode);
	}

	@Override
	public List<CBusHt> findCBusHtList(String deptId) {
		// TODO Auto-generated method stub
		return busHtDao.findCBusHtList(deptId);
	}

	

}
