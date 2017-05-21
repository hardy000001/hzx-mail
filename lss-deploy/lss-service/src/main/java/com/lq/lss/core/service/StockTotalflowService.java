package com.lq.lss.core.service;

import java.util.Map;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.StockTotalflowDto;
import com.lq.lss.model.CStockTotalflow;
import com.lq.pager.PageParam;
import com.lq.pager.Pager;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-23 15:27:44
 */
public interface StockTotalflowService extends EasyUIService<CStockTotalflow, String>{

   
	/**
	 * 调拨
	 * @param pageParam
	 * @param stockTotalflowDto
	 * @return
	 */
	Pager<CStockTotalflow> queryTranseferPage(PageParam pageParam,StockTotalflowDto stockTotalflowDto);
	/**
	 * 中心资产
	 * @param pageParam
	 * @param stockTotalflowDto
	 * @return
	 */
	Pager<CStockTotalflow> queryCenterTotalPage(PageParam pageParam,StockTotalflowDto stockTotalflowDto);
	/**
	 * 查找汇总数据
	 * @param stockTotalflowDto
	 * @return
	 */
	CStockTotalflow queryTotalData(StockTotalflowDto stockTotalflowDto);
	/**
	 * 获取分页流水汇总数据
	 * @param pageParam
	 * @param map
	 * @return
	 */
	Pager<Map<String, Object>> queryFlowTotalPager(PageParam pageParam,StockTotalflowDto stockTotalflowDto);
}