package com.lq.lss.core.dao;

import org.springframework.stereotype.Repository;

import com.lq.lss.core.dao.base.LssSimpleBaseDao;

import com.lq.lss.model.CStockRemodeling;
import com.lq.lss.model.CStockTemporary;

@Repository
public class StockTemporaryDao extends LssSimpleBaseDao<CStockTemporary, String>{
		
	
	/**
	 * 按名称查询类别
	 * 
	 * @param typeName
	 * @return 
	 */
  public CStockTemporary findByCStockTemporary (String temSerialno){
	  return (CStockTemporary)findObjectByParams("findBytemSerialno",temSerialno);
  }
  
 
  
}
