package com.lq.lss.core.dao;

import org.springframework.stereotype.Repository;

import com.lq.lss.core.dao.base.LssSimpleBaseDao;

import com.lq.lss.model.CStockRemodeling;

@Repository
public class CStockRemodelingDao extends LssSimpleBaseDao<CStockRemodeling, String>{
		
	
	/**
	 * 按名称查询类别
	 * 
	 * @param typeName
	 * @return BConsumableType 或null
	 */
  public CStockRemodeling findByCStockRemodeling (String remSerialNo){
	  return (CStockRemodeling)findObjectByParams("findByRemSerialNo",remSerialNo);
  }
  
 
  
}
