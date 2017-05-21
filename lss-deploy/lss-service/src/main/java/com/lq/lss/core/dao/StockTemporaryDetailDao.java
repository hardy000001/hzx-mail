package com.lq.lss.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lq.lss.core.dao.base.LssSimpleBaseDao;

import com.lq.lss.model.CStockRemodeling;
import com.lq.lss.model.CStockTemporary;
import com.lq.lss.model.CStockTemporaryDetail;

@Repository
public class StockTemporaryDetailDao extends LssSimpleBaseDao<CStockTemporaryDetail, String>{
		
	
	/**
	 * 按名称查询类别
	 * 
	 * @param typeName
	 * @return BConsumableType 或null
	 */
  public List<CStockTemporaryDetail> findByCStockTemporaryDetail (String temSerialno){
	  return (List<CStockTemporaryDetail>)findListByParams("findByTemSerialno",temSerialno);
  }
  
 
  
}
