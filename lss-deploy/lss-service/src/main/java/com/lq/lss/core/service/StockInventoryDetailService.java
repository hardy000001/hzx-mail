package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.StockInventoryDetailDto;
import com.lq.lss.model.CStockInventoryDetail;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-29 13:26:15
 */
public interface StockInventoryDetailService extends EasyUIService<CStockInventoryDetail, String>{

    /**
     * 查询盘点明明细
     * @param inventorySerialno
     * @return
     */
	List<StockInventoryDetailDto> queryInventoryDetailList(String inventorySerialno);
	
	 /**
	  * 查询商户盘点信息
	  * @param deptId
	  * @param mchcode
	  * @return
	  */
	List<StockInventoryDetailDto> queryInventoryByMchcode(String deptId,String mchcode);

}