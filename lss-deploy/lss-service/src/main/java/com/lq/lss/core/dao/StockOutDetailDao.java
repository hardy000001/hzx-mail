package com.lq.lss.core.dao;
                                
import java.util.List;

import org.springframework.stereotype.Repository;
import com.lq.lss.model.CStockOutDetail;
import com.lq.lss.core.dao.base.LssSimpleBaseDao;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-10-08 09:26:28
 */
@Repository
public class StockOutDetailDao extends LssSimpleBaseDao<CStockOutDetail, String>{

	
	@SuppressWarnings("unchecked")
	public List<CStockOutDetail> queryStockOutListById(String outSerialno) {
		
		return (List<CStockOutDetail>) findListByParams("queryStockOutListById", outSerialno);
	}

}