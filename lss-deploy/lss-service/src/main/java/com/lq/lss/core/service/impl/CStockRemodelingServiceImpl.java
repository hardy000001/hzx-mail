package com.lq.lss.core.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.CStockRemodelingDao;
import com.lq.lss.core.dao.CStockRemodelingDetailDao;
import com.lq.lss.core.service.CStockFlowService;
import com.lq.lss.core.service.CStockRemodelingService;
import com.lq.lss.dto.CStockRemodelingDetailDto;
import com.lq.lss.dto.CStockRemodelingDto;
import com.lq.lss.enums.CheckStatus;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.CStockRemodeling;
import com.lq.lss.model.CStockRemodelingDetail;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.exception.jta.BusinessException;


@Service
public class CStockRemodelingServiceImpl extends EasyUIServiceBase<CStockRemodeling, String, CStockRemodelingDao> implements CStockRemodelingService{
	@Resource
	CStockRemodelingDetailDao srddao;
	@Resource
	CStockRemodelingDao cstockRemodelingDao;
	@Resource
    private CStockFlowService cStockFlowService;
	@Override
	public ResultDto<String> saveCStockRemodelingRdTx(CStockRemodelingDto srd, SessionUser user) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		String rmdId="";
		try {
			CStockRemodeling cStockRemodeling=new CStockRemodeling();
			cStockRemodeling.setRemSerialNo(srd.getRemSerialNo());
			cStockRemodeling.setDeptid(user.getCenterId());
			cStockRemodeling.setMchcode(srd.getMchcode());
			
			if(srd.getTotalM()=="" || srd.getTotalM()==null){
				cStockRemodeling.setTotalM(new BigDecimal(0));
			}else{
				cStockRemodeling.setTotalM(new BigDecimal(srd.getTotalM()));
			}
			
			if(srd.getTotalS()=="" || srd.getTotalS()==null){
				cStockRemodeling.setTotalS(new BigDecimal(0));
			}else{
				cStockRemodeling.setTotalS(new BigDecimal(srd.getTotalS()));
			}
			
			if(srd.getTotalT()=="" || srd.getTotalT()==null){
				cStockRemodeling.setTotalT(new BigDecimal(0));
			}else{
				cStockRemodeling.setTotalT(new BigDecimal(srd.getTotalT()));
			}
			
			
			cStockRemodeling.setTotalAmt(new BigDecimal(0));
			cStockRemodeling.setStatus("1");
			cStockRemodeling.setCreateoper(user.getUserId()+"");
			cStockRemodeling.setCreatetime(new Date());
			rmdId=modelDao.create(cStockRemodeling);
			log.debug("---------加工改制主表数据保存成功--------");
			
			List<CStockRemodelingDetailDto> cstockRemodelingDetailDto=srd.getCstockRemodelingDetailDto();
			List<CStockRemodelingDetail> srld=new ArrayList<CStockRemodelingDetail>();
			System.out.println("cstockRemodelingDetailDto长度============="+cstockRemodelingDetailDto.size());
			if(cstockRemodelingDetailDto.size()>0)
			{
				for (CStockRemodelingDetailDto detailList : cstockRemodelingDetailDto) 
					{
						CStockRemodelingDetail cStockRemodelingDetail=new CStockRemodelingDetail();
						cStockRemodelingDetail.setRemSerialNo(srd.getRemSerialNo());
						cStockRemodelingDetail.setRmdId(Integer.parseInt(rmdId));
						cStockRemodelingDetail.setCode(detailList.getCode());
						if(("").equals(detailList.getCodeOldnum())){
						cStockRemodelingDetail.setCodeOldnum(new BigDecimal(0));
						}else{
						cStockRemodelingDetail.setCodeOldnum(new BigDecimal(detailList.getCodeOldnum()));
						}
						if(("").equals(detailList.getCodeNewnum())){
							cStockRemodelingDetail.setCodeNewnum(new BigDecimal(0));	
						}else{
							cStockRemodelingDetail.setCodeNewnum(new BigDecimal(detailList.getCodeNewnum()));	
						}
						cStockRemodelingDetail.setUnit(detailList.getUnit());
						cStockRemodelingDetail.setPrice(new BigDecimal(0));
						cStockRemodelingDetail.setTotalS(new BigDecimal(0));
						srld.add(cStockRemodelingDetail);
					}
				 srddao.batchCreate(srld);
				 log.debug("=========采购申请从表数据保存成功=======");
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
	public ResultDto<String> deleteCsrdlById(String remSerialNo,String deptId) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
			CStockRemodeling cStockRemodeling=cstockRemodelingDao.findByCStockRemodeling(remSerialNo);
			if(cStockRemodeling!=null && String.valueOf(cStockRemodeling.getDeptid()).equals(deptId)){
				String status=cStockRemodeling.getStatus();
				if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
			    {
			    	return new ResultDto<String>(false,"已审核数据不可以删除");
			    }
				srddao.deleteById(remSerialNo);
				System.out.println("删除加工改制从表——————————————————————————————————————————");
				cstockRemodelingDao.deleteById(remSerialNo);
				System.out.println("删除加工改制主表——————————————————————————————————————————");
				result=new ResultDto<String>(true,"删除数据成功");
			}else
			{ 
			    result=new ResultDto<String>(false,"该数据不在本中心不允许删除");
			}
			
		} catch (NumberFormatException e) {
			log.error("加工改制删除申请数据转化异常"+e.getMessage());
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		} catch (Exception e) {
					log.error("加工改制数据删除异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
		}
				return result;
	}
	@Override
	public CStockRemodeling findbyRemSerialNo(String remSerialNo) {
		// TODO Auto-generated method stub
		return cstockRemodelingDao.findByCStockRemodeling(remSerialNo);
	}
	@Override
	public ResultDto<String> updatePurInfoRdTx(CStockRemodelingDto srd, SessionUser user) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
			
			String remSerialNo=srd.getRemSerialNo();
			CStockRemodeling  cst= cstockRemodelingDao.findByCStockRemodeling(remSerialNo);
			 String deptid=String.valueOf(srd.getDeptid());
			if(cst!=null && String.valueOf(cst.getDeptid()).equals(deptid)){
				String status=cst.getStatus();
			    if(String.valueOf(CheckStatus.INIT.getConfirm()).equals(status))
			    {
			    	return new ResultDto<String>(false,"已审核数据不可以修改");
			    }
				srddao.deleteById(remSerialNo);
				log.debug("=============加工改制从表数据删除成功==============");
				List<CStockRemodelingDetailDto> cstockRemodelingDetailDto=srd.getCstockRemodelingDetailDto();
				List<CStockRemodelingDetail> srld=new ArrayList<CStockRemodelingDetail>();
				if(cstockRemodelingDetailDto.size()>0)
				{
					for (CStockRemodelingDetailDto detailList : cstockRemodelingDetailDto) 
						{
							CStockRemodelingDetail cStockRemodelingDetail=new CStockRemodelingDetail();
							cStockRemodelingDetail.setRemSerialNo(srd.getRemSerialNo());
							cStockRemodelingDetail.setRmdId(Integer.parseInt(cst.getId()));
							cStockRemodelingDetail.setCode(detailList.getCode());
							if(("").equals(detailList.getCodeOldnum())){
								cStockRemodelingDetail.setCodeOldnum(new BigDecimal(0));
								}else{
								cStockRemodelingDetail.setCodeOldnum(new BigDecimal(detailList.getCodeOldnum()));
								}
								if(("").equals(detailList.getCodeNewnum())){
									cStockRemodelingDetail.setCodeNewnum(new BigDecimal(0));	
								}else{
									cStockRemodelingDetail.setCodeNewnum(new BigDecimal(detailList.getCodeNewnum()));	
								}
							cStockRemodelingDetail.setUnit(detailList.getUnit());
							cStockRemodelingDetail.setPrice(new BigDecimal(0));
							cStockRemodelingDetail.setTotalS(new BigDecimal(0));
							srld.add(cStockRemodelingDetail);
						}
					 srddao.batchCreate(srld);
					 log.debug("=========采购申请从表数据修改成功=======");
				}
				
				CStockRemodeling cStockRemodeling=new CStockRemodeling();
				cStockRemodeling.setRemSerialNo(srd.getRemSerialNo());
				cStockRemodeling.setDeptid(user.getCenterId());
				cStockRemodeling.setMchcode(srd.getMchcode());
				if(srd.getTotalM()=="" || srd.getTotalM()==null){
					cStockRemodeling.setTotalM(new BigDecimal(0));
				}else{
					cStockRemodeling.setTotalM(new BigDecimal(srd.getTotalM()));
				}
				
				if(srd.getTotalS()=="" || srd.getTotalS()==null){
					cStockRemodeling.setTotalS(new BigDecimal(0));
				}else{
					cStockRemodeling.setTotalS(new BigDecimal(srd.getTotalS()));
				}
				
				if(srd.getTotalT()=="" || srd.getTotalT()==null){
					cStockRemodeling.setTotalT(new BigDecimal(0));
				}else{
					cStockRemodeling.setTotalT(new BigDecimal(srd.getTotalT()));
				}
				cStockRemodeling.setTotalAmt(new BigDecimal(0));
				cStockRemodeling.setCreateoper(user.getUserId()+"");
				cStockRemodeling.setCreatetime(new Date());
				cStockRemodeling.setStatus("1");
				modelDao.update(cStockRemodeling);
				log.debug("---------加工改制主表数据修改成功--------");
				result=new ResultDto<String>(true,"数据修改成功");	
			}else
			{ 
		        result=new ResultDto<String>(false,"该数据不在本中心不允许修改");
			}
			
		} catch (NumberFormatException e){
			log.error("加工改制数据转化异常"+e.getMessage());
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		} catch (Exception e) {
					log.error("加工改制数据修改异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
		}
		
		
		return result;
	}
	@Override
	public ResultDto<String> auditStockRemodelingByIdRdTx(CStockRemodelingDto cStockRemodelingDto) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try 
		{
			CStockRemodeling  cStockRemodeling=new CStockRemodeling();
			String remseriano= cStockRemodelingDto.getRemSerialNo();
			CStockRemodeling  cStockRemodeling2=modelDao.findById(remseriano);
			String deptid=String.valueOf(cStockRemodelingDto.getDeptid());
			System.out.println("cStockRemodelingDto.getDeptid()"+cStockRemodelingDto.getDeptid());
			
			if(cStockRemodeling2!=null && String.valueOf(cStockRemodeling2.getDeptid()).equals(deptid))
			{
				
				 String status=cStockRemodeling2.getStatus();
				    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
				    {
				    	return new ResultDto<String>(false,"已审核数据不可以再审核");
				    }
				  //根据状态进行不同的操作
					
					if (String.valueOf(CheckStatus.OK.getCode()).equals(cStockRemodelingDto.getStatus()))
					{
						
						result=cStockFlowService.saveStockFlowRdTx(TradeType.REMODELING, cStockRemodelingDto.getRemSerialNo());
						
						if (!result.isSuccess()){
							//操作流水失败
							throw new BusinessException(result.getErrorMsg());
						}
						log.debug("---------入库申请审核流水处理--------");
			
					}
					cStockRemodeling.setRemSerialNo(cStockRemodelingDto.getRemSerialNo());
					cStockRemodeling.setStatus(cStockRemodelingDto.getStatus());
					
					cStockRemodeling.setAuditingoper(cStockRemodelingDto.getUserId()+"");
					cStockRemodeling.setAuditingtime(new Date());
					modelDao.update(cStockRemodeling);
					
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

	

}
