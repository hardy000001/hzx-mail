package com.lq.lss.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.model.CStockCenterTransfer;
import com.lq.lss.model.CStockCenterTransferDetail;
import com.lq.lss.model.CStockRemodeling;
import com.lq.lss.model.CStockTemporary;
import com.lq.lss.model.CStockTemporaryDetail;

@Repository
public class StockCenterTransferDetailDao extends LssSimpleBaseDao<CStockCenterTransferDetail, String>{
		
	
	/**
	 * 按名称查询类别
	 * 
	 * @param typeName
	 * @return BConsumableType 或null
	 */
  public List<CStockCenterTransferDetail> findByCStockCenterTransferDetail (String tsfSerialno){
	  return (List<CStockCenterTransferDetail>)findListByParams("findBytsfSerialno",tsfSerialno);
  }
  
 
  
}
