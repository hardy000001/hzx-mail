package com.lq.lss.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.model.CStockLeaseDetail;
import com.lq.lss.model.CStockTemporaryDetail;
/**
 * 
 * @author ch
 *
 */
@Repository
public class StockLeaseDetailDao extends LssSimpleBaseDao<CStockLeaseDetail, String> {


	
	
	 public List<CStockLeaseDetail> findByCStockLeaseDetail (String lsSerialno){
		  return (List<CStockLeaseDetail>)findListByParams("findByLsSerialno",lsSerialno);
	  }
}
