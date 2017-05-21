package com.lq.lss.core.service.impl;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.StockTemporaryDao;
import com.lq.lss.core.dao.StockTemporaryDetailDao;
import com.lq.lss.core.service.CStockFlowService;
import com.lq.lss.core.service.StockTemporaryService;
import com.lq.lss.dto.CStockTemporaryDetailDto;
import com.lq.lss.dto.CStockTemporaryDto;
import com.lq.lss.enums.CheckStatus;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.CStockTemporary;
import com.lq.lss.model.CStockTemporaryDetail;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.exception.jta.BusinessException;
import com.lq.util.TimeUtil;


@Service
public class StockTemporaryServiceImpl extends EasyUIServiceBase<CStockTemporary, String, StockTemporaryDao> implements StockTemporaryService{

	@Resource
	StockTemporaryDao cstdao;
	@Resource
	StockTemporaryDetailDao stdedao;
	@Resource
    private CStockFlowService cStockFlowService;

	@Override
	public ResultDto<String> saveCStockTemporaryRdTx(CStockTemporaryDto cstdto, SessionUser user) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
			CStockTemporary cst=new CStockTemporary();
			cst.setTemSerialno(cstdto.getTemSerialno());
			cst.setDeptid(user.getCenterId());
			cst.setMchcode(cstdto.getMchcode());
			if(cstdto.getTotalM()=="" || cstdto.getTotalM()==null){
				cst.setTotalM(new BigDecimal(0));
			}else{
				cst.setTotalM(new BigDecimal(cstdto.getTotalM()));
			}
			if(cstdto.getTotalS()=="" || cstdto.getTotalS()==null){
				cst.setTotalS(new BigDecimal(0));
			}else{
				cst.setTotalS(new BigDecimal(cstdto.getTotalS()));
			}
			if(cstdto.getTotalT()=="" || cstdto.getTotalT()==null){
				cst.setTotalT(new BigDecimal(0));
			}else{
				cst.setTotalT(new BigDecimal(cstdto.getTotalT()));
			}
			if(cstdto.getZxFee()=="" || cstdto.getZxFee()==null){
				cst.setZxFee(new BigDecimal(0));
			}else{
				cst.setZxFee(new BigDecimal(cstdto.getZxFee()));
			}
			if(cstdto.getOtherFee()=="" || cstdto.getOtherFee()==null){
				cst.setOtherFee(new BigDecimal(0));
			}else{
				cst.setOtherFee(new BigDecimal(cstdto.getOtherFee()));
			}
			
			cst.setStatus("1");
			cst.setCreateoper(user.getUserId()+"");
			Date cdate=TimeUtil.get().parseTime(cstdto.getCreatetime());
			cst.setCreatetime(cdate);
			cst.setTradeinfo(cstdto.getTradeinfo());
			modelDao.create(cst);
			log.debug("---------暂存主表数据保存成功--------");
			
			List<CStockTemporaryDetailDto> cStockTemporaryDetailDto=cstdto.getcStockTemporaryDetailDtos();
			List<CStockTemporaryDetail> cStockTemporaryDetails=new ArrayList<CStockTemporaryDetail>();
		
				if(cStockTemporaryDetailDto.size()>0){
					for (CStockTemporaryDetailDto detailList : cStockTemporaryDetailDto) {
						CStockTemporaryDetail  cStockTemporaryDetail=new CStockTemporaryDetail();
						cStockTemporaryDetail.setTemSerialno(cstdto.getTemSerialno());
						cStockTemporaryDetail.setMaterialcode(detailList.getMaterialcode());
						cStockTemporaryDetail.setTotalM(new BigDecimal(detailList.getTotalM()));
						cStockTemporaryDetail.setTotalT(new BigDecimal(0));
						cStockTemporaryDetail.setTotalS(new BigDecimal(detailList.getTotalS()));
						cStockTemporaryDetail.setUnit(detailList.getUnit());
						cStockTemporaryDetails.add(cStockTemporaryDetail);
					}
					stdedao.batchCreate(cStockTemporaryDetails);
					log.debug("=========暂存从表数据保存成功=======");
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
	public ResultDto<String> deleteCstpBytemSerialno(String temSerialno,String deptid) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
			CStockTemporary cStockTemporary=cstdao.findByCStockTemporary(temSerialno);
			if(cStockTemporary!=null && String.valueOf(cStockTemporary.getDeptid()).equals(deptid)){
				String status=cStockTemporary.getStatus();
				if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
			    {
			    	return new ResultDto<String>(false,"已审核数据不可以删除");
			    }
			stdedao.deleteById(temSerialno);
			System.out.println("删除暂存从表——————————————————————————————————————————");
			cstdao.deleteById(temSerialno);
			System.out.println("删除暂存主表——————————————————————————————————————————");
			result=new ResultDto<String>(true,"删除数据成功");
		}else
		{ 
		    result=new ResultDto<String>(false,"不存在该条数据");
		}
		
		} catch (NumberFormatException e) {
		log.error("暂存删除申请数据转化异常"+e.getMessage());
		e.printStackTrace();
		throw new BusinessException(e.getMessage());
		} catch (Exception e) {
				log.error("暂存数据删除异常"+e.getMessage());
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
		}
			return result;
	}

	@Override
	public ResultDto<String> updateTemporaryRdTx(CStockTemporaryDto cStockTemporaryDto, SessionUser user) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
			String  temSerialno = cStockTemporaryDto.getTemSerialno();
			CStockTemporary cStockTemporary=  cstdao.findByCStockTemporary(temSerialno);
			 String deptid=String.valueOf(cStockTemporaryDto.getDeptid());
			if(cStockTemporary!=null && String.valueOf(cStockTemporary.getDeptid()).equals(deptid))
			{
				String status=cStockTemporary.getStatus();
			    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
			    {
			    	return new ResultDto<String>(false,"已审核数据不可以修改");
			    }
				
				
				stdedao.deleteById(temSerialno);
				log.debug("=============加工改制从表数据删除成功==============");
				List<CStockTemporaryDetailDto> cStockTemporaryDetailDto=cStockTemporaryDto.getcStockTemporaryDetailDtos();
				List<CStockTemporaryDetail> cStockTemporaryDetails=new ArrayList<CStockTemporaryDetail>();
				if(cStockTemporaryDetailDto.size()>0)
				{
					for (CStockTemporaryDetailDto detailList : cStockTemporaryDetailDto) {
						CStockTemporaryDetail  cStockTemporaryDetail=new CStockTemporaryDetail();
						cStockTemporaryDetail.setTemSerialno(cStockTemporaryDto.getTemSerialno());
						cStockTemporaryDetail.setMaterialcode(detailList.getMaterialcode());
						cStockTemporaryDetail.setTotalM(new BigDecimal(detailList.getTotalM()));
						cStockTemporaryDetail.setTotalT(new BigDecimal(0));
						cStockTemporaryDetail.setTotalS(new BigDecimal(detailList.getTotalS()));
						cStockTemporaryDetail.setUnit(detailList.getUnit());
						cStockTemporaryDetails.add(cStockTemporaryDetail);
					}
					stdedao.batchCreate(cStockTemporaryDetails);
					log.debug("=========暂存从表数据修改成功=======");
				}
				CStockTemporary cst=new CStockTemporary();
				cst.setTemSerialno(cStockTemporaryDto.getTemSerialno());
				cst.setMchcode(cStockTemporaryDto.getMchcode());
				if(cStockTemporaryDto.getTotalM()=="" || cStockTemporaryDto.getTotalM()==null){
					cst.setTotalM(new BigDecimal(0));
				}else{
					cst.setTotalM(new BigDecimal(cStockTemporaryDto.getTotalM()));
				}
				if(cStockTemporaryDto.getTotalS()=="" || cStockTemporaryDto.getTotalS()==null){
					cst.setTotalS(new BigDecimal(0));
				}else{
					cst.setTotalS(new BigDecimal(cStockTemporaryDto.getTotalS()));
				}
				if(cStockTemporaryDto.getTotalT()=="" || cStockTemporaryDto.getTotalT()==null){
					cst.setTotalT(new BigDecimal(0));
				}else{
					cst.setTotalT(new BigDecimal(cStockTemporaryDto.getTotalT()));
				}
				if(cStockTemporaryDto.getZxFee()=="" || cStockTemporaryDto.getZxFee()==null){
					cst.setZxFee(new BigDecimal(0));
				}else{
					cst.setZxFee(new BigDecimal(cStockTemporaryDto.getZxFee()));
				}
				if(cStockTemporaryDto.getOtherFee()=="" || cStockTemporaryDto.getOtherFee()==null){
					cst.setOtherFee(new BigDecimal(0));
				}else{
					cst.setOtherFee(new BigDecimal(cStockTemporaryDto.getOtherFee()));
				}
				cst.setTradeinfo(cStockTemporaryDto.getTradeinfo());
				modelDao.update(cst);
				log.debug("---------暂存主表数据修改成功--------");
				result=new ResultDto<String>(true,"数据修改成功");	
			}else
			{ 
		        result=new ResultDto<String>(false,"不存在该条数据");
			}
			
		} catch (NumberFormatException e){
			log.error("暂存数据转化异常"+e.getMessage());
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		} catch (Exception e) {
					log.error("暂存数据修改异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
		}
		
		
		return result;
	}

	@Override
	public ResultDto<String> auditStockTemporaryByIdRdTx(CStockTemporaryDto cStockTemporaryDto) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try 
		{
			CStockTemporary cStockTemporary=new CStockTemporary();
			String temserialno=cStockTemporaryDto.getTemSerialno();
			CStockTemporary cStockTemporary2=modelDao.findById(temserialno);
			String deptid=String.valueOf(cStockTemporaryDto.getDeptid());
			
			if(cStockTemporary2!=null && String.valueOf(cStockTemporary2.getDeptid()).equals(deptid))
			{
				 String status=cStockTemporary2.getStatus();
				    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
				    {
				    	return new ResultDto<String>(false,"已审核数据不可以再审核");
				    }
				  //根据状态进行不同的操作
				    if (String.valueOf(CheckStatus.OK.getCode()).equals(cStockTemporaryDto.getStatus()))
					{
						
						result=cStockFlowService.saveStockFlowRdTx(TradeType.TEMPORARY_IN, cStockTemporaryDto.getTemSerialno());
						
						if (!result.isSuccess()){
							//操作流水失败
							throw new BusinessException(result.getErrorMsg());
						}
						log.debug("---------入库申请审核流水处理--------");
			
					}
				    cStockTemporary.setTemSerialno(cStockTemporaryDto.getTemSerialno());
				    cStockTemporary.setStatus(cStockTemporaryDto.getStatus());
					
				    cStockTemporary.setAuditingoper(cStockTemporaryDto.getUserId()+"");
				    cStockTemporary.setAuditingtime(new Date());
					modelDao.update(cStockTemporary);
					
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
	public CStockTemporary findTemporary(String temSerialno) {
		// TODO Auto-generated method stub
		return cstdao.findByCStockTemporary(temSerialno);
	}

	
}
