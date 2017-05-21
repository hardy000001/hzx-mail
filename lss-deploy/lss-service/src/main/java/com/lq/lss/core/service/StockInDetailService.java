package com.lq.lss.core.service;

import java.util.List;
import java.util.Map;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.model.CBusPurDetail;
import com.lq.lss.model.CStockInDetail;

/**
 * 
 * @author Eric
 *
 */
public interface StockInDetailService extends EasyUIService<CStockInDetail, String> {
	
    /**
     * 查询明细
     * @param inSerialno 入库流水号
     * @return
     */
	List<CStockInDetail> queryStockInDetailListById(String inSerialno);
	
	/**
	 * 根据id得到入库明细信息
	 * @param inSerialno
	 * @return
	 */
	public List<Map<String, Object>> getInDetailListById(String inSerialno);
}
