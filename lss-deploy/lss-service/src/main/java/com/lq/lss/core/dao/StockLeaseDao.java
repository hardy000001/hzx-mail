package com.lq.lss.core.dao;

import org.springframework.stereotype.Repository;

import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.model.CStockLease;
import com.lq.lss.model.CStockTemporary;
/**
 * 
 * @author lanbo
 *
 */
@Repository
public class StockLeaseDao extends LssSimpleBaseDao<CStockLease, String> {

	 public CStockLease findByCStockLease (String lsSerialno){
		  return (CStockLease)findObjectByParams("findBylsSerialno",lsSerialno);
	  }
}
