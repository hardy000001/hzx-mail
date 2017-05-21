package com.lq.lss.core.dao;
                                                    
import java.util.List;

import org.springframework.stereotype.Repository;
import com.lq.lss.model.CStockFlow;
import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.dto.CStockFlowDto;

/**
 *
 * @author  作者: Eric
 * @date 创建时间: 2016-10-08 09:26:28
 */
@Repository
public class CStockFlowDao extends LssSimpleBaseDao<CStockFlow, String>{

	@SuppressWarnings("unchecked")
	public List<CStockFlowDto>  findStockFlowListById(String orderNo){
		return (List<CStockFlowDto>) findListByParams("findStockFlowListById", orderNo);
	}
}