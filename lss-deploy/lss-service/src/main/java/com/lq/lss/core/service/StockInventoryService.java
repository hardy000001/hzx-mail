package com.lq.lss.core.service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.AuditDto;
import com.lq.lss.dto.StockInventoryDto;
import com.lq.lss.model.CStockInventory;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-29 13:26:15
 */
public interface StockInventoryService extends EasyUIService<CStockInventory, String>{

  
	/**
     * 保存盘点单
     * @param stockInventoryDto
     * @return
     */
	public ResultDto<String> saveInventoryApplyRdTx(StockInventoryDto stockInventoryDto);
	/**
     * 修改盘点信息
     * @param stockInventoryDto
     * @return
     */
	public ResultDto<String> updateInventoryRdTx(StockInventoryDto stockInventoryDto);
	/**
     * 删除盘点信息
     * @param 盘点流水 inventorySerialno
     * @return
     */
	public ResultDto<String> deleteInventoryById(String inventorySerialno,String deptId);
	/**
	 * 审核
	 * @param auditDto
	 */
	public ResultDto<String> auditInfoRdTx(AuditDto auditDto);

}