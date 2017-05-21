package com.lq.lss.core.dao;

import org.springframework.stereotype.Repository;

import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.model.CStockCenterTransfer;
import com.lq.lss.model.CStockLease;
import com.lq.lss.model.CStockTemporary;
/**
 * 
 * @author ch
 *
 */
@Repository
public class StockCenterTransferDao extends LssSimpleBaseDao<CStockCenterTransfer, String> {

	 public CStockCenterTransfer findByCStockCenterTransfer (String tsfSerialno){
		  return (CStockCenterTransfer)findObjectByParams("findBytsfSerialno",tsfSerialno);
	  }
}
