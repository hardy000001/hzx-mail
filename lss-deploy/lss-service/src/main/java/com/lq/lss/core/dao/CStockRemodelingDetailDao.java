package com.lq.lss.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.model.CStockRemodeling;
import com.lq.lss.model.CStockRemodelingDetail;

@Repository
public class CStockRemodelingDetailDao extends LssSimpleBaseDao<CStockRemodelingDetail, String>{
		
	
	
	/**
	 * 按名称查询类别
	 * 
	 * @param typeName
	 * @return BConsumableType 或null
	 */
  public List<CStockRemodelingDetail> findByCStockRemodeling (String remSerialNo){
	  return (List<CStockRemodelingDetail>)findListByParams("findByRemSerialNo",remSerialNo);
  }
}
