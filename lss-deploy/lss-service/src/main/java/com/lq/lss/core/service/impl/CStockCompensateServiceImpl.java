package com.lq.lss.core.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.CBusHt;
import com.lq.lss.model.CStockCompensate;
import com.lq.lss.model.CStockCompensateDetail;
import com.lq.lss.model.CStockSend;
import com.lq.lss.model.CStockSendDetail;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.exception.jta.BusinessException;
import com.lq.util.TimeUtil;
import com.lq.lss.core.dao.BusHtDao;
import com.lq.lss.core.dao.CStockCompensateDao;
import com.lq.lss.core.dao.CStockCompensateDetailDao;
import com.lq.lss.core.service.CStockCompensateService;
import com.lq.lss.core.service.CStockFlowService;
import com.lq.lss.dto.CStockCompensateDetailDto;
import com.lq.lss.dto.CStockCompensateDto;
import com.lq.lss.dto.CStockSendDetailDto;
import com.lq.lss.enums.CheckStatus;
import com.lq.lss.enums.TradeType;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-28 15:32:42
 */
@Service
public class CStockCompensateServiceImpl extends EasyUIServiceBase<CStockCompensate, String, CStockCompensateDao> implements CStockCompensateService{

    @Resource
	private CStockCompensateDao cStockCompensateDao;
    @Resource
	private BusHtDao busHtDao;
    @Resource
	private CStockCompensateDetailDao cStockCompensateDetailDao;
    @Resource
    private CStockFlowService cStockFlowService;

	@Override
	public ResultDto<String> saveCStockCompensateRdTx(CStockCompensateDto cStockCompensateDto, SessionUser user) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
			
			CStockCompensate cStockCompensate=new CStockCompensate();
			CBusHt busHt= busHtDao.findById(cStockCompensateDto.getHtcode());
			System.out.println("busHt.getLesseeCusCode()+报错"+busHt.getLesseeCusCode());
			cStockCompensate.setCompensateSerialno(cStockCompensateDto.getCompensateSerialno());
			cStockCompensate.setDeptid(user.getCenterId());
			cStockCompensate.setMchcode(busHt.getLesseeCusCode());
			cStockCompensate.setHtcode(cStockCompensateDto.getHtcode());
			
			cStockCompensate.setLessee(cStockCompensateDto.getLessee());
			cStockCompensate.setRenter(cStockCompensateDto.getRenter());
			
			cStockCompensate.setStatus("1");
			cStockCompensate.setFlag("1");
			cStockCompensate.setRemark(cStockCompensateDto.getRemark());
			cStockCompensate.setTradeinfo(cStockCompensateDto.getTradeinfo());
			cStockCompensate.setCreateoper(user.getUserId()+"");
			cStockCompensate.setCreatetime(new Date());
			
			modelDao.create(cStockCompensate);
			log.debug("---------发料主表数据保存成功--------");
			
			List<CStockCompensateDetailDto> cStockCompensateDetailDtos=cStockCompensateDto.getcStockCompensateDetailDtos();
			List<CStockCompensateDetail> cStockCompensateDetails=new ArrayList<CStockCompensateDetail>();
		
				if(cStockCompensateDetailDtos.size()>0){
					for (CStockCompensateDetailDto detailList : cStockCompensateDetailDtos) {
						CStockCompensateDetail  cStockCompensateDetail=new CStockCompensateDetail();
						cStockCompensateDetail.setCompensateSerialno(cStockCompensateDto.getCompensateSerialno());
						cStockCompensateDetail.setMaterialcode(detailList.getMaterialcode());
						cStockCompensateDetail.setTotalS(new BigDecimal(detailList.getTotalS()));
						cStockCompensateDetail.setTotalM(new BigDecimal(detailList.getTotalM()));
						cStockCompensateDetail.setPrice(new BigDecimal(detailList.getPrice()));
						
						cStockCompensateDetail.setUnit(detailList.getUnit());
						cStockCompensateDetails.add(cStockCompensateDetail);
					}
					cStockCompensateDetailDao.batchCreate(cStockCompensateDetails);
					log.debug("=========发料从表数据保存成功=======");
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
	public ResultDto<String> deleteCStockCompensate(String compensateSerialno, String deptId) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
			CStockCompensate cStockCompensate=cStockCompensateDao.findById(compensateSerialno);
			if(cStockCompensate!=null && String.valueOf(cStockCompensate.getDeptid()).equals(deptId)){
				String status=cStockCompensate.getStatus();
				if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
			    {
			    	return new ResultDto<String>(false,"已审核数据不可以删除");
			    }
				cStockCompensateDetailDao.deleteById(compensateSerialno);
			
				cStockCompensateDao.deleteById(compensateSerialno);
			
			result=new ResultDto<String>(true,"删除数据成功");
		}else
		{ 
		    result=new ResultDto<String>(false,"该数据不在本中心不允许删除");
		}
		
		} catch (NumberFormatException e) {
		log.error("赔偿删除申请数据转化异常"+e.getMessage());
		e.printStackTrace();
		throw new BusinessException(e.getMessage());
		} catch (Exception e) {
				log.error("赔偿数据删除异常"+e.getMessage());
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
		}
			return result;
	}

	@Override
	public ResultDto<String> updateCStockCompensateRdTx(CStockCompensateDto cStockCompensateDto, SessionUser user) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
			
			String  compensateSerialno = cStockCompensateDto.getCompensateSerialno();
			System.out.println("compensateSerialno=================="+compensateSerialno);
			CStockCompensate cStockCompensate2 =  cStockCompensateDao.findById(compensateSerialno);
			System.out.println("cStockCompensate2=================="+cStockCompensate2.getDeptid());
			String deptid=String.valueOf(cStockCompensateDto.getDeptid());
			if(cStockCompensate2!=null && String.valueOf(cStockCompensate2.getDeptid()).equals(deptid))
			{	
				String status=cStockCompensate2.getStatus();
			    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
			    {
			    	return new ResultDto<String>(false,"已审核数据不可以修改");
			    }
			    cStockCompensateDetailDao.deleteById(compensateSerialno);
				log.debug("=============赔偿从表数据删除成功==============");
				List<CStockCompensateDetailDto> cStockCompensateDetailDtos=cStockCompensateDto.getcStockCompensateDetailDtos();
				List<CStockCompensateDetail> cStockCompensateDetails=new ArrayList<CStockCompensateDetail>();
				if(cStockCompensateDetailDtos.size()>0){
					for (CStockCompensateDetailDto detailList : cStockCompensateDetailDtos) {
						CStockCompensateDetail  cStockCompensateDetail=new CStockCompensateDetail();
						cStockCompensateDetail.setCompensateSerialno(cStockCompensateDto.getCompensateSerialno());
						cStockCompensateDetail.setMaterialcode(detailList.getMaterialcode());
						cStockCompensateDetail.setTotalS(new BigDecimal(detailList.getTotalS()));
						cStockCompensateDetail.setTotalM(new BigDecimal(detailList.getTotalM()));
						cStockCompensateDetail.setPrice(new BigDecimal(detailList.getPrice()));
						
						cStockCompensateDetail.setUnit(detailList.getUnit());
						cStockCompensateDetails.add(cStockCompensateDetail);
					}
					cStockCompensateDetailDao.batchCreate(cStockCompensateDetails);
					log.debug("=========发料从表数据保存成功=======");
				}
				CStockCompensate cStockCompensate=new CStockCompensate();
				
				cStockCompensate.setCompensateSerialno(cStockCompensateDto.getCompensateSerialno());
			
				cStockCompensate.setHtcode(cStockCompensateDto.getHtcode());
				
				cStockCompensate.setLessee(cStockCompensateDto.getLessee());
				cStockCompensate.setRenter(cStockCompensateDto.getRenter());
				
				cStockCompensate.setStatus("1");
				cStockCompensate.setFlag("1");
				cStockCompensate.setRemark(cStockCompensateDto.getRemark());
				cStockCompensate.setTradeinfo(cStockCompensateDto.getTradeinfo());
				cStockCompensate.setCreateoper(user.getUserId()+"");
				modelDao.update(cStockCompensate);
				log.debug("---------赔偿主表数据修改成功--------");
				result=new ResultDto<String>(true,"数据修改成功");	
			}else
			{ 
		        result=new ResultDto<String>(false,"该数据不在本中心不允许修改");
			}
			
		} catch (NumberFormatException e){
			log.error("发料主数据转化异常"+e.getMessage());
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		} catch (Exception e) {
					log.error("发料主数据修改异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
		}
		
		
		return result;
	}

	@Override
	public ResultDto<String> auditCStockCompensateRdTx(CStockCompensateDto cStockCompensateDto) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try 
		{
			
			CStockCompensate cStockCompensate=new CStockCompensate();
			String compensateSerialno=cStockCompensateDto.getCompensateSerialno();
			CStockCompensate cStockCompensate2=modelDao.findById(compensateSerialno);
			String deptid=String.valueOf(cStockCompensateDto.getDeptid());
			//根据状态进行不同的操作
		
			if(cStockCompensate2!=null && String.valueOf(cStockCompensate2.getDeptid()).equals(deptid))
			{
				//cStockSendDto.setMchcode(cStockSend2.getMchcode());
				 String status=cStockCompensate2.getStatus();
				    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
				    {
				    	return new ResultDto<String>(false,"已审核数据不可以再审核");
				    }
				   List<CStockCompensateDetailDto> cStockCompensateDetails=cStockCompensateDetailDao.findCStockCompensateDetail(compensateSerialno);
	
				
				if (String.valueOf(CheckStatus.OK.getCode()).equals(cStockCompensateDto.getStatus()))
				{
					
					result=cStockFlowService.saveStockFlowRdTx(TradeType.STOCK_COMPENSATE, cStockCompensateDto.getCompensateSerialno());
					
					if (!result.isSuccess()){
						//操作流水失败
						throw new BusinessException(result.getErrorMsg());
					}
					log.debug("---------赔偿申请审核流水处理--------");
		
				}
			
				cStockCompensate.setCompensateSerialno(cStockCompensateDto.getCompensateSerialno());
				cStockCompensate.setStatus(cStockCompensateDto.getStatus());
				
				cStockCompensate.setAuditingoper(cStockCompensateDto.getUserId()+"");
				cStockCompensate.setAuditingtime(new Date());
				modelDao.update(cStockCompensate);;
				result=new ResultDto<String>(true,"赔偿数据审核成功");
			
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