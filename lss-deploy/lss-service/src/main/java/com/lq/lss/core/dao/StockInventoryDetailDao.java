package com.lq.lss.core.dao;
                                    
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lq.lss.model.CStockInventoryDetail;
import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.dto.StockInventoryDetailDto;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-29 13:26:15
 */
@Repository
public class StockInventoryDetailDao extends LssSimpleBaseDao<CStockInventoryDetail, String>{
 
	@SuppressWarnings("unchecked")
	public List<StockInventoryDetailDto> findInventoryDetail(String inventorySerialno) {
		return (List<StockInventoryDetailDto>) findListByParams("findInventoryDetail", inventorySerialno);
	}
}