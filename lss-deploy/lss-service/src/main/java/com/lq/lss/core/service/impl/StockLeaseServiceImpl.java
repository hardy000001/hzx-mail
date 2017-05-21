package com.lq.lss.core.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.StockLeaseDao;
import com.lq.lss.core.dao.StockLeaseDetailDao;
import com.lq.lss.core.dao.StockTemporaryDao;
import com.lq.lss.core.service.StockLeaseService;
import com.lq.lss.dto.CStockLeaseDetailDto;
import com.lq.lss.dto.CStockLeaseDto;
import com.lq.lss.dto.CStockTemporaryDetailDto;
import com.lq.lss.model.CStockLease;
import com.lq.lss.model.CStockLeaseDetail;
import com.lq.lss.model.CStockTemporary;
import com.lq.lss.model.CStockTemporaryDetail;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.exception.jta.BusinessException;
import com.lq.util.TimeUtil;

/**
 * 
 * @author ch
 *
 */
@Service
public class StockLeaseServiceImpl extends EasyUIServiceBase<CStockLease, String, StockLeaseDao> implements StockLeaseService {

	@Resource
	StockLeaseDetailDao cslddao;
	@Resource
	StockLeaseDao stockLeaseDao;
	@Override
	public ResultDto<String> saveCStockLeaseRdTx(CStockLeaseDto cStockLeaseDto, SessionUser user) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
			CStockLease csl=new CStockLease();
			csl.setLsSerialno(cStockLeaseDto.getLsSerialno());
			csl.setDeptid(user.getCenterId());
			csl.setMchcode(cStockLeaseDto.getMchcode());
			csl.setTotalM(new BigDecimal(0));
			csl.setTotalS(new BigDecimal(0));
			csl.setTotalT(new BigDecimal(0));
			csl.setTransportFee(new BigDecimal(0));
			csl.setZxFee(new BigDecimal(cStockLeaseDto.getZxFee()));
			csl.setOtherFee(new BigDecimal(cStockLeaseDto.getOtherFee()));
			csl.setStatus("1");
			csl.setCreateoper(user.getUserId()+"");
			Date cdate=TimeUtil.get().parseTime(cStockLeaseDto.getLsSdate());
			csl.setLsSdate(cdate);
			csl.setCreatetime(new Date());
			modelDao.create(csl);
			log.debug("---------中心调拨主表数据保存成功--------");
			
			List<CStockLeaseDetailDto> cStockLeaseDetailDtos=cStockLeaseDto.getCstockleasedetaildto();
			List<CStockLeaseDetail> cStockLeaseDetails=new ArrayList<CStockLeaseDetail>();
		
				if(cStockLeaseDetailDtos.size()>0){
					for (CStockLeaseDetailDto detailList : cStockLeaseDetailDtos) {
						CStockLeaseDetail  cStockLeaseDetail=new CStockLeaseDetail();
						cStockLeaseDetail.setLsSerialno(cStockLeaseDto.getLsSerialno());
						cStockLeaseDetail.setMaterialcode(detailList.getMaterialcode());
						cStockLeaseDetail.setTotalM(new BigDecimal(0));
						cStockLeaseDetail.setTotalT(new BigDecimal(0));
						cStockLeaseDetail.setTotalS(new BigDecimal(detailList.getTotalS()));
						//单价目前没取
						cStockLeaseDetail.setPrice(new BigDecimal(0));
						cStockLeaseDetail.setUnit(detailList.getUnit());
						cStockLeaseDetails.add(cStockLeaseDetail);
					}
					cslddao.batchCreate(cStockLeaseDetails);
					log.debug("=========中心调拨从表数据保存成功=======");
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
	public ResultDto<String> deleteCStockLease(String lsSerialno) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
			CStockLease cStockLease=stockLeaseDao.findByCStockLease(lsSerialno);
			if(cStockLease!=null){
			cslddao.deleteById(lsSerialno);
			System.out.println("删除中心调拨从表——————————————————————————————————————————");
			stockLeaseDao.deleteById(lsSerialno);
			System.out.println("删除中心调拨主表——————————————————————————————————————————");
			result=new ResultDto<String>(true,"删除数据成功");
		}else
		{ 
		    result=new ResultDto<String>(false,"不存在该条数据");
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
	public ResultDto<String> updateTemporaryRdTx(CStockLeaseDto cStockLeaseDto, SessionUser user) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
			String  lsSerialno = cStockLeaseDto.getLsSerialno();
			CStockLease csStockLease=  stockLeaseDao.findByCStockLease(lsSerialno);
			if(csStockLease!=null)
			{
				cslddao.deleteById(lsSerialno);
				log.debug("=============中心调拨从表数据删除成功==============");
				List<CStockLeaseDetailDto> cStockLeaseDetailDtos=cStockLeaseDto.getCstockleasedetaildto();
				List<CStockLeaseDetail> cStockLeaseDetails=new ArrayList<CStockLeaseDetail>();
				if(cStockLeaseDetailDtos.size()>0)
				{
					for (CStockLeaseDetailDto detailList : cStockLeaseDetailDtos) {
						CStockLeaseDetail  cStockLeaseDetail=new CStockLeaseDetail();
						cStockLeaseDetail.setLsSerialno(cStockLeaseDto.getLsSerialno());
						cStockLeaseDetail.setMaterialcode(detailList.getMaterialcode());
						cStockLeaseDetail.setTotalM(new BigDecimal(0));
						cStockLeaseDetail.setTotalT(new BigDecimal(0));
						cStockLeaseDetail.setTotalS(new BigDecimal(detailList.getTotalS()));
						//单价目前没取
						cStockLeaseDetail.setPrice(new BigDecimal(0));
						cStockLeaseDetail.setUnit(detailList.getUnit());
						cStockLeaseDetails.add(cStockLeaseDetail);
					}
					cslddao.batchCreate(cStockLeaseDetails);
					log.debug("=========中心调拨从表数据修改成功=======");
				}
				CStockLease csl=new CStockLease();
				csl.setLsSerialno(cStockLeaseDto.getLsSerialno());
				csl.setDeptid(user.getCenterId());
				csl.setMchcode(cStockLeaseDto.getMchcode());
				csl.setTotalM(new BigDecimal(0));
				csl.setTotalS(new BigDecimal(0));
				csl.setTotalT(new BigDecimal(0));
				csl.setTransportFee(new BigDecimal(0));
				csl.setZxFee(new BigDecimal(cStockLeaseDto.getZxFee()));
				csl.setOtherFee(new BigDecimal(cStockLeaseDto.getOtherFee()));
				csl.setStatus("1");
				csl.setCreateoper(user.getUserId()+"");
				Date cdate=TimeUtil.get().parseTime(cStockLeaseDto.getLsSdate());
				csl.setLsSdate(cdate);
				csl.setCreatetime(new Date());
				
				modelDao.update(csl);
				log.debug("---------中心调拨主表数据修改成功--------");
				result=new ResultDto<String>(true,"数据修改成功");	
			}else
			{ 
		        result=new ResultDto<String>(false,"不存在该条数据");
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

	


}
