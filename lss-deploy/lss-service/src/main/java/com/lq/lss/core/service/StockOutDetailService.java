package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.model.CStockOutDetail;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-10-08 09:26:28
 */
public interface StockOutDetailService extends EasyUIService<CStockOutDetail, String>{

	 /**
     * 查询明细
     * @param outSerialno 出库流水号
     * @return
     */
	List<CStockOutDetail> queryStockOutListById(String outSerialno);
	

}