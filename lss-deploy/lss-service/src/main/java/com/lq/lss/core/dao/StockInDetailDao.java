package com.lq.lss.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.model.CBusPurDetail;
import com.lq.lss.model.CStockInDetail;
/**
 * 
 * @author Eric
 *
 */
@Repository
public class StockInDetailDao extends LssSimpleBaseDao<CStockInDetail, String> {
	
	
	@SuppressWarnings("unchecked")
	public List<CStockInDetail> queryStockInDetailListById(String inSerialno){
		return (List<CStockInDetail>) findListByParams("queryStockInDetailListById", inSerialno);
	}

}
